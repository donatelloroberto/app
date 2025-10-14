package org.apache.cordova.filetransfer;

import android.net.Uri;
import com.adobe.phonegap.push.PushConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileTransfer extends CordovaPlugin {
    public static int ABORTED_ERR = 4;
    private static final String BOUNDARY = "+++++";
    public static int CONNECTION_ERR = 3;
    public static int FILE_NOT_FOUND_ERR = 1;
    public static int INVALID_URL_ERR = 2;
    private static final String LINE_END = "\r\n";
    private static final String LINE_START = "--";
    private static final String LOG_TAG = "FileTransfer";
    private static final int MAX_BUFFER_SIZE = 16384;
    public static int NOT_MODIFIED_ERR = 5;
    /* access modifiers changed from: private */
    public static HashMap<String, RequestContext> activeRequests = new HashMap<>();

    private static final class RequestContext {
        boolean aborted;
        CallbackContext callbackContext;
        HttpURLConnection connection;
        String source;
        String target;
        File targetFile;

        RequestContext(String source2, String target2, CallbackContext callbackContext2) {
            this.source = source2;
            this.target = target2;
            this.callbackContext = callbackContext2;
        }

        /* access modifiers changed from: package-private */
        public void sendPluginResult(PluginResult pluginResult) {
            synchronized (this) {
                if (!this.aborted) {
                    this.callbackContext.sendPluginResult(pluginResult);
                }
            }
        }
    }

    private static abstract class TrackingInputStream extends FilterInputStream {
        public abstract long getTotalRawBytesRead();

        public TrackingInputStream(InputStream in) {
            super(in);
        }
    }

    private static class ExposedGZIPInputStream extends GZIPInputStream {
        public ExposedGZIPInputStream(InputStream in) throws IOException {
            super(in);
        }

        public Inflater getInflater() {
            return this.inf;
        }
    }

    private static class TrackingGZIPInputStream extends TrackingInputStream {
        private ExposedGZIPInputStream gzin;

        public TrackingGZIPInputStream(ExposedGZIPInputStream gzin2) throws IOException {
            super(gzin2);
            this.gzin = gzin2;
        }

        public long getTotalRawBytesRead() {
            return this.gzin.getInflater().getBytesRead();
        }
    }

    private static class SimpleTrackingInputStream extends TrackingInputStream {
        private long bytesRead = 0;

        public SimpleTrackingInputStream(InputStream stream) {
            super(stream);
        }

        private int updateBytesRead(int newBytesRead) {
            if (newBytesRead != -1) {
                this.bytesRead += (long) newBytesRead;
            }
            return newBytesRead;
        }

        public int read() throws IOException {
            return updateBytesRead(super.read());
        }

        public int read(byte[] bytes, int offset, int count) throws IOException {
            return updateBytesRead(super.read(bytes, offset, count));
        }

        public long getTotalRawBytesRead() {
            return this.bytesRead;
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("upload") || action.equals("download")) {
            String source = args.getString(0);
            String target = args.getString(1);
            if (action.equals("upload")) {
                upload(source, target, args, callbackContext);
                return true;
            }
            download(source, target, args, callbackContext);
            return true;
        } else if (!action.equals("abort")) {
            return false;
        } else {
            abort(args.getString(0));
            callbackContext.success();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static void addHeadersToRequest(URLConnection connection, JSONObject headers) {
        try {
            Iterator<String> keys = headers.keys();
            while (keys.hasNext()) {
                String headerKey = keys.next().toString();
                String cleanHeaderKey = headerKey.replaceAll("\\n", "").replaceAll("\\s+", "").replaceAll(":", "").replaceAll("[^\\x20-\\x7E]+", "");
                JSONArray headerValues = headers.optJSONArray(headerKey);
                if (headerValues == null) {
                    headerValues = new JSONArray();
                    headerValues.put(headers.getString(headerKey).replaceAll("\\s+", " ").replaceAll("\\n", " ").replaceAll("[^\\x20-\\x7E]+", " "));
                }
                connection.setRequestProperty(cleanHeaderKey, headerValues.getString(0));
                for (int i = 1; i < headerValues.length(); i++) {
                    connection.addRequestProperty(headerKey, headerValues.getString(i));
                }
            }
        } catch (JSONException e) {
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.String} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCookies(java.lang.String r12) {
        /*
            r11 = this;
            r4 = 0
            r1 = 0
            org.apache.cordova.CordovaWebView r7 = r11.webView
            java.lang.Class r6 = r7.getClass()
            java.lang.String r7 = "getCookieManager"
            r8 = 0
            java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.reflect.Method r3 = r6.getMethod(r7, r8)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.Class r5 = r3.getReturnType()     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.String r7 = "getCookie"
            r8 = 1
            java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r9 = 0
            java.lang.Class<java.lang.String> r10 = java.lang.String.class
            r8[r9] = r10     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.reflect.Method r2 = r5.getMethod(r7, r8)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            org.apache.cordova.CordovaWebView r7 = r11.webView     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.Object r7 = r3.invoke(r7, r8)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.Object r7 = r5.cast(r7)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r9 = 0
            r8[r9] = r12     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            java.lang.Object r7 = r2.invoke(r7, r8)     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0054, InvocationTargetException -> 0x0052, ClassCastException -> 0x0050 }
            r1 = r0
            r4 = 1
        L_0x003f:
            if (r4 != 0) goto L_0x004f
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()
            if (r7 == 0) goto L_0x004f
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()
            java.lang.String r1 = r7.getCookie(r12)
        L_0x004f:
            return r1
        L_0x0050:
            r7 = move-exception
            goto L_0x003f
        L_0x0052:
            r7 = move-exception
            goto L_0x003f
        L_0x0054:
            r7 = move-exception
            goto L_0x003f
        L_0x0056:
            r7 = move-exception
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.filetransfer.FileTransfer.getCookies(java.lang.String):java.lang.String");
    }

    private void upload(String source, String target, JSONArray args, CallbackContext callbackContext) throws JSONException {
        final JSONObject headers;
        LOG.d(LOG_TAG, "upload " + source + " to " + target);
        final String fileKey = getArgument(args, 2, "file");
        final String fileName = getArgument(args, 3, "image.jpg");
        final String mimeType = getArgument(args, 4, "image/jpeg");
        final JSONObject params = args.optJSONObject(5) == null ? new JSONObject() : args.optJSONObject(5);
        final boolean chunkedMode = args.optBoolean(7) || args.isNull(7);
        if (args.optJSONObject(8) == null) {
            headers = params.optJSONObject("headers");
        } else {
            headers = args.optJSONObject(8);
        }
        final String objectId = args.getString(9);
        final String httpMethod = getArgument(args, 10, "POST");
        final CordovaResourceApi resourceApi = this.webView.getResourceApi();
        LOG.d(LOG_TAG, "fileKey: " + fileKey);
        LOG.d(LOG_TAG, "fileName: " + fileName);
        LOG.d(LOG_TAG, "mimeType: " + mimeType);
        LOG.d(LOG_TAG, "params: " + params);
        LOG.d(LOG_TAG, "chunkedMode: " + chunkedMode);
        LOG.d(LOG_TAG, "headers: " + headers);
        LOG.d(LOG_TAG, "objectId: " + objectId);
        LOG.d(LOG_TAG, "httpMethod: " + httpMethod);
        Uri targetUri = resourceApi.remapUri(Uri.parse(target));
        int uriType = CordovaResourceApi.getUriType(targetUri);
        final boolean useHttps = uriType == 6;
        if (uriType == 5 || useHttps) {
            final RequestContext context = new RequestContext(source, target, callbackContext);
            synchronized (activeRequests) {
                activeRequests.put(objectId, context);
            }
            final String str = source;
            final Uri uri = targetUri;
            final String str2 = target;
            this.f5cordova.getThreadPool().execute(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:100:0x034d, code lost:
                    if (r9 <= 0) goto L_0x0493;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:101:0x034f, code lost:
                    r34 = r34 + r9;
                    r27.setBytesSent((long) r34);
                    r28.write(r6, 0, r9);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:102:0x0372, code lost:
                    if (((long) r34) <= (102400 + r20)) goto L_0x03a7;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:103:0x0374, code lost:
                    r20 = (long) r34;
                    org.apache.cordova.LOG.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "Uploaded " + r34 + " of " + r14 + " bytes");
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:104:0x03a7, code lost:
                    r9 = r24.inputStream.read(r6, 0, java.lang.Math.min(r24.inputStream.available(), 16384));
                    r22.setLoaded((long) r34);
                    r0 = new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.OK, r22.toJSONObject());
                    r0.setKeepCallback(true);
                    r5.sendPluginResult(r0);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:105:0x03fe, code lost:
                    r36 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$200(r24.inputStream);
                    org.apache.cordova.filetransfer.FileTransfer.access$200(r28);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:109:0x040b, code lost:
                    throw r36;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:110:0x040c, code lost:
                    r12 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:112:?, code lost:
                    r13 = org.apache.cordova.filetransfer.FileTransfer.access$400(org.apache.cordova.filetransfer.FileTransfer.CONNECTION_ERR, r6, r11, r10, r12);
                    org.apache.cordova.LOG.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r13.toString(), (java.lang.Throwable) r12);
                    org.apache.cordova.LOG.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "Failed after uploading " + r34 + " of " + r14 + " bytes.");
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.IO_EXCEPTION, r13));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:114:0x0478, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$500());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$500().remove(r18);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:127:0x0493, code lost:
                    if (r18 == false) goto L_0x04a3;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:128:0x0495, code lost:
                    r28.write(r32);
                    r34 = r34 + r32.length;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:129:0x04a3, code lost:
                    r28.flush();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:132:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$200(r24.inputStream);
                    org.apache.cordova.filetransfer.FileTransfer.access$200(r28);
                    r37 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:133:0x04b8, code lost:
                    monitor-enter(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
                    r5.connection = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:137:0x04c7, code lost:
                    monitor-exit(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
                    org.apache.cordova.LOG.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "Sent " + r34 + " of " + r14);
                    r25 = r10.getResponseCode();
                    org.apache.cordova.LOG.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "response code: " + r25);
                    org.apache.cordova.LOG.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "response headers: " + r10.getHeaderFields());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:141:0x052c, code lost:
                    r15 = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:143:?, code lost:
                    r15 = org.apache.cordova.filetransfer.FileTransfer.access$300(r10);
                    r37 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:144:0x0537, code lost:
                    monitor-enter(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:148:0x0544, code lost:
                    if (r5.aborted == false) goto L_0x0616;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:149:0x0546, code lost:
                    monitor-exit(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
                    r37 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:153:0x054d, code lost:
                    monitor-enter(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:156:?, code lost:
                    r5.connection = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:157:0x055c, code lost:
                    monitor-exit(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:159:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$200(r15);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:160:0x0560, code lost:
                    r37 = org.apache.cordova.filetransfer.FileTransfer.access$500();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:161:0x0564, code lost:
                    monitor-enter(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:163:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$500().remove(r18);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:164:0x0576, code lost:
                    monitor-exit(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:174:0x057f, code lost:
                    r12 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:177:?, code lost:
                    org.apache.cordova.LOG.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r12.getMessage(), (java.lang.Throwable) r12);
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.JSON_EXCEPTION));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:179:0x05a1, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$500());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:181:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$500().remove(r18);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:192:0x05bc, code lost:
                    r31 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:194:?, code lost:
                    r13 = org.apache.cordova.filetransfer.FileTransfer.access$400(org.apache.cordova.filetransfer.FileTransfer.CONNECTION_ERR, r6, r11, r10, r31);
                    org.apache.cordova.LOG.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r13.toString(), r31);
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.IO_EXCEPTION, r13));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:196:0x05fe, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$500());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:198:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$500().remove(r18);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:206:?, code lost:
                    r5.connection = r10;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:207:0x0620, code lost:
                    monitor-exit(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:209:?, code lost:
                    r0 = new java.io.ByteArrayOutputStream(java.lang.Math.max(1024, r10.getContentLength()));
                    r6 = new byte[1024];
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:210:0x063b, code lost:
                    r9 = r15.read(r6);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:211:0x063f, code lost:
                    if (r9 <= 0) goto L_0x067e;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:212:0x0641, code lost:
                    r0.write(r6, 0, r9);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:213:0x064b, code lost:
                    r36 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:217:0x0652, code lost:
                    monitor-enter(r5);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
                    r5.connection = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:223:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$200(r15);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:224:0x0665, code lost:
                    throw r36;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:225:0x0666, code lost:
                    r36 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:227:0x066b, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$500());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:229:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$500().remove(r18);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:231:0x067a, code lost:
                    throw r36;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:237:0x067e, code lost:
                    r26 = r0.toString(com.amazon.device.ads.WebRequest.CHARSET_UTF_8);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:240:?, code lost:
                    r37 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:241:0x068e, code lost:
                    monitor-enter(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:244:?, code lost:
                    r5.connection = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:245:0x069d, code lost:
                    monitor-exit(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:247:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$200(r15);
                    org.apache.cordova.LOG.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, "got response from server");
                    org.apache.cordova.LOG.d(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r26.substring(0, java.lang.Math.min(256, r26.length())));
                    r27.setResponseCode(r25);
                    r27.setResponse(r26);
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.OK, r27.toJSONObject()));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:248:0x06e5, code lost:
                    r37 = org.apache.cordova.filetransfer.FileTransfer.access$500();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:249:0x06e9, code lost:
                    monitor-enter(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:251:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$500().remove(r18);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:252:0x06fb, code lost:
                    monitor-exit(r37);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:298:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:299:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:300:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:301:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:302:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:303:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:79:0x02ba, code lost:
                    r12 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
                    r13 = org.apache.cordova.filetransfer.FileTransfer.access$400(org.apache.cordova.filetransfer.FileTransfer.FILE_NOT_FOUND_ERR, r6, r11, r10, r12);
                    org.apache.cordova.LOG.e(org.apache.cordova.filetransfer.FileTransfer.LOG_TAG, r13.toString(), (java.lang.Throwable) r12);
                    r5.sendPluginResult(new org.apache.cordova.PluginResult(org.apache.cordova.PluginResult.Status.IO_EXCEPTION, r13));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:83:0x02f8, code lost:
                    monitor-enter(org.apache.cordova.filetransfer.FileTransfer.access$500());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
                    org.apache.cordova.filetransfer.FileTransfer.access$500().remove(r18);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:95:0x031b, code lost:
                    if (r18 == false) goto L_0x0327;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:98:?, code lost:
                    r28.write(r5);
                    r34 = 0 + r5.length;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:99:0x0327, code lost:
                    r7 = java.lang.Math.min(r24.inputStream.available(), 16384);
                    r6 = new byte[r7];
                    r9 = r24.inputStream.read(r6, 0, r7);
                    r20 = 0;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Removed duplicated region for block: B:110:0x040c A[ExcHandler: IOException (r12v1 'e' java.io.IOException A[CUSTOM_DECLARE]), PHI: r10 r14 r34 
                  PHI: (r10v2 'conn' java.net.HttpURLConnection) = (r10v0 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection) binds: [B:5:0x0031, B:21:0x00c3, B:75:0x02a7, B:107:0x0401, B:131:0x04a8, B:172:0x057e, B:173:?, B:139:0x04ca, B:140:?, B:215:0x064e, B:265:0x0706, B:266:?, B:222:0x0662, B:239:0x068a, B:260:0x0703, B:261:?, B:246:0x069e, B:247:?, B:151:0x0549, B:190:0x05bb, B:191:?, B:158:0x055d, B:159:?, B:60:0x0266, B:61:?, B:30:0x0134] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r14v1 'fixedLength' int) = (r14v0 'fixedLength' int), (r14v0 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v2 'fixedLength' int), (r14v0 'fixedLength' int) binds: [B:5:0x0031, B:21:0x00c3, B:75:0x02a7, B:107:0x0401, B:131:0x04a8, B:172:0x057e, B:173:?, B:139:0x04ca, B:140:?, B:215:0x064e, B:265:0x0706, B:266:?, B:222:0x0662, B:239:0x068a, B:260:0x0703, B:261:?, B:246:0x069e, B:247:?, B:151:0x0549, B:190:0x05bb, B:191:?, B:158:0x055d, B:159:?, B:60:0x0266, B:61:?, B:30:0x0134] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r34v1 'totalBytes' int) = (r34v0 'totalBytes' int), (r34v0 'totalBytes' int), (r34v0 'totalBytes' int), (r34v0 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v4 'totalBytes' int), (r34v0 'totalBytes' int), (r34v0 'totalBytes' int), (r34v0 'totalBytes' int) binds: [B:5:0x0031, B:21:0x00c3, B:75:0x02a7, B:107:0x0401, B:131:0x04a8, B:172:0x057e, B:173:?, B:139:0x04ca, B:140:?, B:215:0x064e, B:265:0x0706, B:266:?, B:222:0x0662, B:239:0x068a, B:260:0x0703, B:261:?, B:246:0x069e, B:247:?, B:151:0x0549, B:190:0x05bb, B:191:?, B:158:0x055d, B:159:?, B:60:0x0266, B:61:?, B:30:0x0134] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x0031] */
                /* JADX WARNING: Removed duplicated region for block: B:192:0x05bc A[ExcHandler: Throwable (r31v0 't' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r10 
                  PHI: (r10v1 'conn' java.net.HttpURLConnection) = (r10v0 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection) binds: [B:5:0x0031, B:21:0x00c3, B:75:0x02a7, B:107:0x0401, B:131:0x04a8, B:172:0x057e, B:173:?, B:139:0x04ca, B:140:?, B:215:0x064e, B:265:0x0706, B:266:?, B:222:0x0662, B:239:0x068a, B:260:0x0703, B:261:?, B:246:0x069e, B:247:?, B:151:0x0549, B:190:0x05bb, B:191:?, B:158:0x055d, B:159:?, B:60:0x0266, B:61:?, B:30:0x0134] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x0031] */
                /* JADX WARNING: Removed duplicated region for block: B:79:0x02ba A[ExcHandler: FileNotFoundException (r12v2 'e' java.io.FileNotFoundException A[CUSTOM_DECLARE]), PHI: r10 
                  PHI: (r10v3 'conn' java.net.HttpURLConnection) = (r10v0 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection), (r10v4 'conn' java.net.HttpURLConnection) binds: [B:5:0x0031, B:21:0x00c3, B:75:0x02a7, B:107:0x0401, B:131:0x04a8, B:172:0x057e, B:173:?, B:139:0x04ca, B:140:?, B:215:0x064e, B:265:0x0706, B:266:?, B:222:0x0662, B:239:0x068a, B:260:0x0703, B:261:?, B:246:0x069e, B:247:?, B:151:0x0549, B:190:0x05bb, B:191:?, B:158:0x055d, B:159:?, B:60:0x0266, B:61:?, B:30:0x0134] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x0031] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r40 = this;
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5
                        r36 = r0
                        r0 = r36
                        boolean r0 = r0.aborted
                        r36 = r0
                        if (r36 == 0) goto L_0x000f
                    L_0x000e:
                        return
                    L_0x000f:
                        r0 = r40
                        java.lang.String r0 = r6
                        r36 = r0
                        android.net.Uri r33 = android.net.Uri.parse(r36)
                        r0 = r40
                        org.apache.cordova.CordovaResourceApi r0 = r7
                        r36 = r0
                        java.lang.String r37 = r33.getScheme()
                        if (r37 == 0) goto L_0x028c
                    L_0x0025:
                        r0 = r36
                        r1 = r33
                        android.net.Uri r29 = r0.remapUri(r1)
                        r10 = 0
                        r34 = 0
                        r14 = -1
                        org.apache.cordova.filetransfer.FileUploadResult r27 = new org.apache.cordova.filetransfer.FileUploadResult     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r27.<init>()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.filetransfer.FileProgressResult r22 = new org.apache.cordova.filetransfer.FileProgressResult     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r22.<init>()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.apache.cordova.CordovaResourceApi r0 = r7     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r0 = r40
                        android.net.Uri r0 = r8     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        java.net.HttpURLConnection r10 = r36.createHttpConnection(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = 1
                        r0 = r36
                        r10.setDoInput(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = 1
                        r0 = r36
                        r10.setDoOutput(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = 0
                        r0 = r36
                        r10.setUseCaches(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        java.lang.String r0 = r9     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r0 = r36
                        r10.setRequestMethod(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.json.JSONObject r0 = r10     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        if (r36 == 0) goto L_0x0081
                        r0 = r40
                        org.json.JSONObject r0 = r10     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        java.lang.String r37 = "Content-Type"
                        boolean r36 = r36.has(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        if (r36 != 0) goto L_0x029d
                    L_0x0081:
                        r18 = 1
                    L_0x0083:
                        if (r18 == 0) goto L_0x0090
                        java.lang.String r36 = "Content-Type"
                        java.lang.String r37 = "multipart/form-data; boundary=+++++"
                        r0 = r36
                        r1 = r37
                        r10.setRequestProperty(r0, r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x0090:
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer r0 = org.apache.cordova.filetransfer.FileTransfer.this     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r0 = r40
                        java.lang.String r0 = r11     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        java.lang.String r11 = r36.getCookies(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        if (r11 == 0) goto L_0x00a9
                        java.lang.String r36 = "Cookie"
                        r0 = r36
                        r10.setRequestProperty(r0, r11)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x00a9:
                        r0 = r40
                        org.json.JSONObject r0 = r10     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        if (r36 == 0) goto L_0x00bc
                        r0 = r40
                        org.json.JSONObject r0 = r10     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r0 = r36
                        org.apache.cordova.filetransfer.FileTransfer.addHeadersToRequest(r10, r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x00bc:
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r4.<init>()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.json.JSONObject r0 = r12     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        r36 = r0
                        java.util.Iterator r16 = r36.keys()     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                    L_0x00cb:
                        boolean r36 = r16.hasNext()     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        if (r36 == 0) goto L_0x013f
                        java.lang.Object r17 = r16.next()     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r36 = java.lang.String.valueOf(r17)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r37 = "headers"
                        boolean r36 = r36.equals(r37)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        if (r36 != 0) goto L_0x00cb
                        java.lang.String r36 = "--"
                        r0 = r36
                        java.lang.StringBuilder r36 = r4.append(r0)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r37 = "+++++"
                        java.lang.StringBuilder r36 = r36.append(r37)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r37 = "\r\n"
                        r36.append(r37)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r36 = "Content-Disposition: form-data; name=\""
                        r0 = r36
                        java.lang.StringBuilder r36 = r4.append(r0)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r37 = r17.toString()     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.StringBuilder r36 = r36.append(r37)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        r37 = 34
                        r36.append(r37)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r36 = "\r\n"
                        r0 = r36
                        java.lang.StringBuilder r36 = r4.append(r0)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r37 = "\r\n"
                        r36.append(r37)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        r0 = r40
                        org.json.JSONObject r0 = r12     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        r36 = r0
                        java.lang.String r37 = r17.toString()     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r36 = r36.getString(r37)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        r0 = r36
                        r4.append(r0)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        java.lang.String r36 = "\r\n"
                        r0 = r36
                        r4.append(r0)     // Catch:{ JSONException -> 0x0131, FileNotFoundException -> 0x02ba, IOException -> 0x040c, Throwable -> 0x05bc }
                        goto L_0x00cb
                    L_0x0131:
                        r12 = move-exception
                        java.lang.String r36 = "FileTransfer"
                        java.lang.String r37 = r12.getMessage()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r36
                        r1 = r37
                        org.apache.cordova.LOG.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r12)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x013f:
                        java.lang.String r36 = "--"
                        r0 = r36
                        java.lang.StringBuilder r36 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = "+++++"
                        java.lang.StringBuilder r36 = r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = "\r\n"
                        r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = "Content-Disposition: form-data; name=\""
                        r0 = r36
                        java.lang.StringBuilder r36 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        java.lang.String r0 = r13     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        java.lang.StringBuilder r36 = r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = "\";"
                        r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = " filename=\""
                        r0 = r36
                        java.lang.StringBuilder r36 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        java.lang.String r0 = r14     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        java.lang.StringBuilder r36 = r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = 34
                        java.lang.StringBuilder r36 = r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = "\r\n"
                        r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = "Content-Type: "
                        r0 = r36
                        java.lang.StringBuilder r36 = r4.append(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        java.lang.String r0 = r15     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        java.lang.StringBuilder r36 = r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = "\r\n"
                        java.lang.StringBuilder r36 = r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = "\r\n"
                        r36.append(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = r4.toString()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = "UTF-8"
                        byte[] r5 = r36.getBytes(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = "\r\n--+++++--\r\n"
                        java.lang.String r37 = "UTF-8"
                        byte[] r32 = r36.getBytes(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.apache.cordova.CordovaResourceApi r0 = r7     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r0 = r36
                        r1 = r29
                        org.apache.cordova.CordovaResourceApi$OpenForReadResult r24 = r0.openForRead(r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        int r0 = r5.length     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r0 = r32
                        int r0 = r0.length     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        int r30 = r36 + r37
                        r0 = r24
                        long r0 = r0.length     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r38 = 0
                        int r36 = (r36 > r38 ? 1 : (r36 == r38 ? 0 : -1))
                        if (r36 < 0) goto L_0x01f9
                        r0 = r24
                        long r0 = r0.length     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r0 = r36
                        int r14 = (int) r0     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        if (r18 == 0) goto L_0x01e6
                        int r14 = r14 + r30
                    L_0x01e6:
                        r36 = 1
                        r0 = r22
                        r1 = r36
                        r0.setLengthComputable(r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        long r0 = (long) r14     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        r0 = r22
                        r1 = r36
                        r0.setTotal(r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x01f9:
                        java.lang.String r36 = "FileTransfer"
                        java.lang.StringBuilder r37 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37.<init>()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r38 = "Content Length: "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r37
                        java.lang.StringBuilder r37 = r0.append(r14)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = r37.toString()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.LOG.d(r36, r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        boolean r0 = r16     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        if (r36 != 0) goto L_0x0225
                        int r36 = android.os.Build.VERSION.SDK_INT     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = 8
                        r0 = r36
                        r1 = r37
                        if (r0 >= r1) goto L_0x02a1
                    L_0x0225:
                        r35 = 1
                    L_0x0227:
                        if (r35 != 0) goto L_0x022f
                        r36 = -1
                        r0 = r36
                        if (r14 != r0) goto L_0x02a4
                    L_0x022f:
                        r35 = 1
                    L_0x0231:
                        if (r35 == 0) goto L_0x02a7
                        r36 = 16384(0x4000, float:2.2959E-41)
                        r0 = r36
                        r10.setChunkedStreamingMode(r0)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = "Transfer-Encoding"
                        java.lang.String r37 = "chunked"
                        r0 = r36
                        r1 = r37
                        r10.setRequestProperty(r0, r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x0245:
                        r10.connect()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r28 = 0
                        java.io.OutputStream r28 = r10.getOutputStream()     // Catch:{ all -> 0x03fe }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x03fe }
                        r37 = r0
                        monitor-enter(r37)     // Catch:{ all -> 0x03fe }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0490 }
                        r36 = r0
                        r0 = r36
                        boolean r0 = r0.aborted     // Catch:{ all -> 0x0490 }
                        r36 = r0
                        if (r36 == 0) goto L_0x0310
                        monitor-exit(r37)     // Catch:{ all -> 0x0490 }
                        r0 = r24
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r36)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r28)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r37)
                        java.util.HashMap r36 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x0289 }
                        r0 = r40
                        java.lang.String r0 = r18     // Catch:{ all -> 0x0289 }
                        r38 = r0
                        r0 = r36
                        r1 = r38
                        r0.remove(r1)     // Catch:{ all -> 0x0289 }
                        monitor-exit(r37)     // Catch:{ all -> 0x0289 }
                        goto L_0x000e
                    L_0x0289:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x0289 }
                        throw r36
                    L_0x028c:
                        java.io.File r37 = new java.io.File
                        r0 = r40
                        java.lang.String r0 = r6
                        r38 = r0
                        r37.<init>(r38)
                        android.net.Uri r33 = android.net.Uri.fromFile(r37)
                        goto L_0x0025
                    L_0x029d:
                        r18 = 0
                        goto L_0x0083
                    L_0x02a1:
                        r35 = 0
                        goto L_0x0227
                    L_0x02a4:
                        r35 = 0
                        goto L_0x0231
                    L_0x02a7:
                        r10.setFixedLengthStreamingMode(r14)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        boolean r0 = r17     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        if (r36 == 0) goto L_0x0245
                        java.lang.String r36 = "FileTransfer"
                        java.lang.String r37 = "setFixedLengthStreamingMode could cause OutOfMemoryException - switch to chunkedMode=true to avoid it if this is an issue."
                        org.apache.cordova.LOG.w((java.lang.String) r36, (java.lang.String) r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        goto L_0x0245
                    L_0x02ba:
                        r12 = move-exception
                        int r36 = org.apache.cordova.filetransfer.FileTransfer.FILE_NOT_FOUND_ERR     // Catch:{ all -> 0x0666 }
                        r0 = r40
                        java.lang.String r0 = r6     // Catch:{ all -> 0x0666 }
                        r37 = r0
                        r0 = r40
                        java.lang.String r0 = r11     // Catch:{ all -> 0x0666 }
                        r38 = r0
                        r0 = r36
                        r1 = r37
                        r2 = r38
                        org.json.JSONObject r13 = org.apache.cordova.filetransfer.FileTransfer.createFileTransferError(r0, r1, r2, r10, r12)     // Catch:{ all -> 0x0666 }
                        java.lang.String r36 = "FileTransfer"
                        java.lang.String r37 = r13.toString()     // Catch:{ all -> 0x0666 }
                        r0 = r36
                        r1 = r37
                        org.apache.cordova.LOG.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r12)     // Catch:{ all -> 0x0666 }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0666 }
                        r36 = r0
                        org.apache.cordova.PluginResult r37 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x0666 }
                        org.apache.cordova.PluginResult$Status r38 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION     // Catch:{ all -> 0x0666 }
                        r0 = r37
                        r1 = r38
                        r0.<init>((org.apache.cordova.PluginResult.Status) r1, (org.json.JSONObject) r13)     // Catch:{ all -> 0x0666 }
                        r36.sendPluginResult(r37)     // Catch:{ all -> 0x0666 }
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r37)
                        java.util.HashMap r36 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x030d }
                        r0 = r40
                        java.lang.String r0 = r18     // Catch:{ all -> 0x030d }
                        r38 = r0
                        r0 = r36
                        r1 = r38
                        r0.remove(r1)     // Catch:{ all -> 0x030d }
                        monitor-exit(r37)     // Catch:{ all -> 0x030d }
                        goto L_0x000e
                    L_0x030d:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x030d }
                        throw r36
                    L_0x0310:
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0490 }
                        r36 = r0
                        r0 = r36
                        r0.connection = r10     // Catch:{ all -> 0x0490 }
                        monitor-exit(r37)     // Catch:{ all -> 0x0490 }
                        if (r18 == 0) goto L_0x0327
                        r0 = r28
                        r0.write(r5)     // Catch:{ all -> 0x03fe }
                        int r0 = r5.length     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        int r34 = r34 + r36
                    L_0x0327:
                        r0 = r24
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        int r8 = r36.available()     // Catch:{ all -> 0x03fe }
                        r36 = 16384(0x4000, float:2.2959E-41)
                        r0 = r36
                        int r7 = java.lang.Math.min(r8, r0)     // Catch:{ all -> 0x03fe }
                        byte[] r6 = new byte[r7]     // Catch:{ all -> 0x03fe }
                        r0 = r24
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        r37 = 0
                        r0 = r36
                        r1 = r37
                        int r9 = r0.read(r6, r1, r7)     // Catch:{ all -> 0x03fe }
                        r20 = 0
                    L_0x034d:
                        if (r9 <= 0) goto L_0x0493
                        int r34 = r34 + r9
                        r0 = r34
                        long r0 = (long) r0     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        r0 = r27
                        r1 = r36
                        r0.setBytesSent(r1)     // Catch:{ all -> 0x03fe }
                        r36 = 0
                        r0 = r28
                        r1 = r36
                        r0.write(r6, r1, r9)     // Catch:{ all -> 0x03fe }
                        r0 = r34
                        long r0 = (long) r0     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        r38 = 102400(0x19000, double:5.05923E-319)
                        long r38 = r38 + r20
                        int r36 = (r36 > r38 ? 1 : (r36 == r38 ? 0 : -1))
                        if (r36 <= 0) goto L_0x03a7
                        r0 = r34
                        long r0 = (long) r0     // Catch:{ all -> 0x03fe }
                        r20 = r0
                        java.lang.String r36 = "FileTransfer"
                        java.lang.StringBuilder r37 = new java.lang.StringBuilder     // Catch:{ all -> 0x03fe }
                        r37.<init>()     // Catch:{ all -> 0x03fe }
                        java.lang.String r38 = "Uploaded "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ all -> 0x03fe }
                        r0 = r37
                        r1 = r34
                        java.lang.StringBuilder r37 = r0.append(r1)     // Catch:{ all -> 0x03fe }
                        java.lang.String r38 = " of "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ all -> 0x03fe }
                        r0 = r37
                        java.lang.StringBuilder r37 = r0.append(r14)     // Catch:{ all -> 0x03fe }
                        java.lang.String r38 = " bytes"
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ all -> 0x03fe }
                        java.lang.String r37 = r37.toString()     // Catch:{ all -> 0x03fe }
                        org.apache.cordova.LOG.d(r36, r37)     // Catch:{ all -> 0x03fe }
                    L_0x03a7:
                        r0 = r24
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        int r8 = r36.available()     // Catch:{ all -> 0x03fe }
                        r36 = 16384(0x4000, float:2.2959E-41)
                        r0 = r36
                        int r7 = java.lang.Math.min(r8, r0)     // Catch:{ all -> 0x03fe }
                        r0 = r24
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        r37 = 0
                        r0 = r36
                        r1 = r37
                        int r9 = r0.read(r6, r1, r7)     // Catch:{ all -> 0x03fe }
                        r0 = r34
                        long r0 = (long) r0     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        r0 = r22
                        r1 = r36
                        r0.setLoaded(r1)     // Catch:{ all -> 0x03fe }
                        org.apache.cordova.PluginResult r23 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x03fe }
                        org.apache.cordova.PluginResult$Status r36 = org.apache.cordova.PluginResult.Status.OK     // Catch:{ all -> 0x03fe }
                        org.json.JSONObject r37 = r22.toJSONObject()     // Catch:{ all -> 0x03fe }
                        r0 = r23
                        r1 = r36
                        r2 = r37
                        r0.<init>((org.apache.cordova.PluginResult.Status) r1, (org.json.JSONObject) r2)     // Catch:{ all -> 0x03fe }
                        r36 = 1
                        r0 = r23
                        r1 = r36
                        r0.setKeepCallback(r1)     // Catch:{ all -> 0x03fe }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        r0 = r36
                        r1 = r23
                        r0.sendPluginResult(r1)     // Catch:{ all -> 0x03fe }
                        goto L_0x034d
                    L_0x03fe:
                        r36 = move-exception
                        r0 = r24
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r28)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        throw r36     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x040c:
                        r12 = move-exception
                        int r36 = org.apache.cordova.filetransfer.FileTransfer.CONNECTION_ERR     // Catch:{ all -> 0x0666 }
                        r0 = r40
                        java.lang.String r0 = r6     // Catch:{ all -> 0x0666 }
                        r37 = r0
                        r0 = r40
                        java.lang.String r0 = r11     // Catch:{ all -> 0x0666 }
                        r38 = r0
                        r0 = r36
                        r1 = r37
                        r2 = r38
                        org.json.JSONObject r13 = org.apache.cordova.filetransfer.FileTransfer.createFileTransferError(r0, r1, r2, r10, r12)     // Catch:{ all -> 0x0666 }
                        java.lang.String r36 = "FileTransfer"
                        java.lang.String r37 = r13.toString()     // Catch:{ all -> 0x0666 }
                        r0 = r36
                        r1 = r37
                        org.apache.cordova.LOG.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r12)     // Catch:{ all -> 0x0666 }
                        java.lang.String r36 = "FileTransfer"
                        java.lang.StringBuilder r37 = new java.lang.StringBuilder     // Catch:{ all -> 0x0666 }
                        r37.<init>()     // Catch:{ all -> 0x0666 }
                        java.lang.String r38 = "Failed after uploading "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ all -> 0x0666 }
                        r0 = r37
                        r1 = r34
                        java.lang.StringBuilder r37 = r0.append(r1)     // Catch:{ all -> 0x0666 }
                        java.lang.String r38 = " of "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ all -> 0x0666 }
                        r0 = r37
                        java.lang.StringBuilder r37 = r0.append(r14)     // Catch:{ all -> 0x0666 }
                        java.lang.String r38 = " bytes."
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ all -> 0x0666 }
                        java.lang.String r37 = r37.toString()     // Catch:{ all -> 0x0666 }
                        org.apache.cordova.LOG.e(r36, r37)     // Catch:{ all -> 0x0666 }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0666 }
                        r36 = r0
                        org.apache.cordova.PluginResult r37 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x0666 }
                        org.apache.cordova.PluginResult$Status r38 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION     // Catch:{ all -> 0x0666 }
                        r0 = r37
                        r1 = r38
                        r0.<init>((org.apache.cordova.PluginResult.Status) r1, (org.json.JSONObject) r13)     // Catch:{ all -> 0x0666 }
                        r36.sendPluginResult(r37)     // Catch:{ all -> 0x0666 }
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r37)
                        java.util.HashMap r36 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x048d }
                        r0 = r40
                        java.lang.String r0 = r18     // Catch:{ all -> 0x048d }
                        r38 = r0
                        r0 = r36
                        r1 = r38
                        r0.remove(r1)     // Catch:{ all -> 0x048d }
                        monitor-exit(r37)     // Catch:{ all -> 0x048d }
                        goto L_0x000e
                    L_0x048d:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x048d }
                        throw r36
                    L_0x0490:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x0490 }
                        throw r36     // Catch:{ all -> 0x03fe }
                    L_0x0493:
                        if (r18 == 0) goto L_0x04a3
                        r0 = r28
                        r1 = r32
                        r0.write(r1)     // Catch:{ all -> 0x03fe }
                        r0 = r32
                        int r0 = r0.length     // Catch:{ all -> 0x03fe }
                        r36 = r0
                        int r34 = r34 + r36
                    L_0x04a3:
                        r28.flush()     // Catch:{ all -> 0x03fe }
                        r0 = r24
                        java.io.InputStream r0 = r0.inputStream     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r36)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r28)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        monitor-enter(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x057c }
                        r36 = r0
                        r38 = 0
                        r0 = r38
                        r1 = r36
                        r1.connection = r0     // Catch:{ all -> 0x057c }
                        monitor-exit(r37)     // Catch:{ all -> 0x057c }
                        java.lang.String r36 = "FileTransfer"
                        java.lang.StringBuilder r37 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37.<init>()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r38 = "Sent "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r37
                        r1 = r34
                        java.lang.StringBuilder r37 = r0.append(r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r38 = " of "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r37
                        java.lang.StringBuilder r37 = r0.append(r14)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = r37.toString()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.LOG.d(r36, r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        int r25 = r10.getResponseCode()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = "FileTransfer"
                        java.lang.StringBuilder r37 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37.<init>()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r38 = "response code: "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r37
                        r1 = r25
                        java.lang.StringBuilder r37 = r0.append(r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = r37.toString()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.LOG.d(r36, r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = "FileTransfer"
                        java.lang.StringBuilder r37 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37.<init>()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r38 = "response headers: "
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.util.Map r38 = r10.getHeaderFields()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.StringBuilder r37 = r37.append(r38)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r37 = r37.toString()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.LOG.d(r36, r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r15 = 0
                        org.apache.cordova.filetransfer.FileTransfer$TrackingInputStream r15 = org.apache.cordova.filetransfer.FileTransfer.getInputStream(r10)     // Catch:{ all -> 0x064b }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x064b }
                        r37 = r0
                        monitor-enter(r37)     // Catch:{ all -> 0x064b }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x067b }
                        r36 = r0
                        r0 = r36
                        boolean r0 = r0.aborted     // Catch:{ all -> 0x067b }
                        r36 = r0
                        if (r36 == 0) goto L_0x0616
                        monitor-exit(r37)     // Catch:{ all -> 0x067b }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        monitor-enter(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x05b9 }
                        r36 = r0
                        r38 = 0
                        r0 = r38
                        r1 = r36
                        r1.connection = r0     // Catch:{ all -> 0x05b9 }
                        monitor-exit(r37)     // Catch:{ all -> 0x05b9 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r15)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r37)
                        java.util.HashMap r36 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x0579 }
                        r0 = r40
                        java.lang.String r0 = r18     // Catch:{ all -> 0x0579 }
                        r38 = r0
                        r0 = r36
                        r1 = r38
                        r0.remove(r1)     // Catch:{ all -> 0x0579 }
                        monitor-exit(r37)     // Catch:{ all -> 0x0579 }
                        goto L_0x000e
                    L_0x0579:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x0579 }
                        throw r36
                    L_0x057c:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x057c }
                        throw r36     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x057f:
                        r12 = move-exception
                        java.lang.String r36 = "FileTransfer"
                        java.lang.String r37 = r12.getMessage()     // Catch:{ all -> 0x0666 }
                        r0 = r36
                        r1 = r37
                        org.apache.cordova.LOG.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r12)     // Catch:{ all -> 0x0666 }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0666 }
                        r36 = r0
                        org.apache.cordova.PluginResult r37 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x0666 }
                        org.apache.cordova.PluginResult$Status r38 = org.apache.cordova.PluginResult.Status.JSON_EXCEPTION     // Catch:{ all -> 0x0666 }
                        r37.<init>(r38)     // Catch:{ all -> 0x0666 }
                        r36.sendPluginResult(r37)     // Catch:{ all -> 0x0666 }
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r37)
                        java.util.HashMap r36 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x05b6 }
                        r0 = r40
                        java.lang.String r0 = r18     // Catch:{ all -> 0x05b6 }
                        r38 = r0
                        r0 = r36
                        r1 = r38
                        r0.remove(r1)     // Catch:{ all -> 0x05b6 }
                        monitor-exit(r37)     // Catch:{ all -> 0x05b6 }
                        goto L_0x000e
                    L_0x05b6:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x05b6 }
                        throw r36
                    L_0x05b9:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x05b9 }
                        throw r36     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x05bc:
                        r31 = move-exception
                        int r36 = org.apache.cordova.filetransfer.FileTransfer.CONNECTION_ERR     // Catch:{ all -> 0x0666 }
                        r0 = r40
                        java.lang.String r0 = r6     // Catch:{ all -> 0x0666 }
                        r37 = r0
                        r0 = r40
                        java.lang.String r0 = r11     // Catch:{ all -> 0x0666 }
                        r38 = r0
                        r0 = r36
                        r1 = r37
                        r2 = r38
                        r3 = r31
                        org.json.JSONObject r13 = org.apache.cordova.filetransfer.FileTransfer.createFileTransferError(r0, r1, r2, r10, r3)     // Catch:{ all -> 0x0666 }
                        java.lang.String r36 = "FileTransfer"
                        java.lang.String r37 = r13.toString()     // Catch:{ all -> 0x0666 }
                        r0 = r36
                        r1 = r37
                        r2 = r31
                        org.apache.cordova.LOG.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x0666 }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0666 }
                        r36 = r0
                        org.apache.cordova.PluginResult r37 = new org.apache.cordova.PluginResult     // Catch:{ all -> 0x0666 }
                        org.apache.cordova.PluginResult$Status r38 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION     // Catch:{ all -> 0x0666 }
                        r0 = r37
                        r1 = r38
                        r0.<init>((org.apache.cordova.PluginResult.Status) r1, (org.json.JSONObject) r13)     // Catch:{ all -> 0x0666 }
                        r36.sendPluginResult(r37)     // Catch:{ all -> 0x0666 }
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r37)
                        java.util.HashMap r36 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x0613 }
                        r0 = r40
                        java.lang.String r0 = r18     // Catch:{ all -> 0x0613 }
                        r38 = r0
                        r0 = r36
                        r1 = r38
                        r0.remove(r1)     // Catch:{ all -> 0x0613 }
                        monitor-exit(r37)     // Catch:{ all -> 0x0613 }
                        goto L_0x000e
                    L_0x0613:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x0613 }
                        throw r36
                    L_0x0616:
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x067b }
                        r36 = r0
                        r0 = r36
                        r0.connection = r10     // Catch:{ all -> 0x067b }
                        monitor-exit(r37)     // Catch:{ all -> 0x067b }
                        java.io.ByteArrayOutputStream r19 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x064b }
                        r36 = 1024(0x400, float:1.435E-42)
                        int r37 = r10.getContentLength()     // Catch:{ all -> 0x064b }
                        int r36 = java.lang.Math.max(r36, r37)     // Catch:{ all -> 0x064b }
                        r0 = r19
                        r1 = r36
                        r0.<init>(r1)     // Catch:{ all -> 0x064b }
                        r36 = 1024(0x400, float:1.435E-42)
                        r0 = r36
                        byte[] r6 = new byte[r0]     // Catch:{ all -> 0x064b }
                        r9 = 0
                    L_0x063b:
                        int r9 = r15.read(r6)     // Catch:{ all -> 0x064b }
                        if (r9 <= 0) goto L_0x067e
                        r36 = 0
                        r0 = r19
                        r1 = r36
                        r0.write(r6, r1, r9)     // Catch:{ all -> 0x064b }
                        goto L_0x063b
                    L_0x064b:
                        r36 = move-exception
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        monitor-enter(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0704 }
                        r38 = r0
                        r39 = 0
                        r0 = r39
                        r1 = r38
                        r1.connection = r0     // Catch:{ all -> 0x0704 }
                        monitor-exit(r37)     // Catch:{ all -> 0x0704 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r15)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        throw r36     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x0666:
                        r36 = move-exception
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r37)
                        java.util.HashMap r38 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x0707 }
                        r0 = r40
                        java.lang.String r0 = r18     // Catch:{ all -> 0x0707 }
                        r39 = r0
                        r38.remove(r39)     // Catch:{ all -> 0x0707 }
                        monitor-exit(r37)     // Catch:{ all -> 0x0707 }
                        throw r36
                    L_0x067b:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x067b }
                        throw r36     // Catch:{ all -> 0x064b }
                    L_0x067e:
                        java.lang.String r36 = "UTF-8"
                        r0 = r19
                        r1 = r36
                        java.lang.String r26 = r0.toString(r1)     // Catch:{ all -> 0x064b }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37 = r0
                        monitor-enter(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ all -> 0x0701 }
                        r36 = r0
                        r38 = 0
                        r0 = r38
                        r1 = r36
                        r1.connection = r0     // Catch:{ all -> 0x0701 }
                        monitor-exit(r37)     // Catch:{ all -> 0x0701 }
                        org.apache.cordova.filetransfer.FileTransfer.safeClose(r15)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = "FileTransfer"
                        java.lang.String r37 = "got response from server"
                        org.apache.cordova.LOG.d(r36, r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.lang.String r36 = "FileTransfer"
                        r37 = 0
                        r38 = 256(0x100, float:3.59E-43)
                        int r39 = r26.length()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        int r38 = java.lang.Math.min(r38, r39)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r26
                        r1 = r37
                        r2 = r38
                        java.lang.String r37 = r0.substring(r1, r2)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.LOG.d(r36, r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r27
                        r1 = r25
                        r0.setResponseCode(r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r27
                        r1 = r26
                        r0.setResponse(r1)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r0 = r40
                        org.apache.cordova.filetransfer.FileTransfer$RequestContext r0 = r5     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36 = r0
                        org.apache.cordova.PluginResult r37 = new org.apache.cordova.PluginResult     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.apache.cordova.PluginResult$Status r38 = org.apache.cordova.PluginResult.Status.OK     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        org.json.JSONObject r39 = r27.toJSONObject()     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r37.<init>((org.apache.cordova.PluginResult.Status) r38, (org.json.JSONObject) r39)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        r36.sendPluginResult(r37)     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                        java.util.HashMap r37 = org.apache.cordova.filetransfer.FileTransfer.activeRequests
                        monitor-enter(r37)
                        java.util.HashMap r36 = org.apache.cordova.filetransfer.FileTransfer.activeRequests     // Catch:{ all -> 0x06fe }
                        r0 = r40
                        java.lang.String r0 = r18     // Catch:{ all -> 0x06fe }
                        r38 = r0
                        r0 = r36
                        r1 = r38
                        r0.remove(r1)     // Catch:{ all -> 0x06fe }
                        monitor-exit(r37)     // Catch:{ all -> 0x06fe }
                        goto L_0x000e
                    L_0x06fe:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x06fe }
                        throw r36
                    L_0x0701:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x0701 }
                        throw r36     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x0704:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x0704 }
                        throw r36     // Catch:{ FileNotFoundException -> 0x02ba, IOException -> 0x040c, JSONException -> 0x057f, Throwable -> 0x05bc }
                    L_0x0707:
                        r36 = move-exception
                        monitor-exit(r37)     // Catch:{ all -> 0x0707 }
                        throw r36
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.filetransfer.FileTransfer.AnonymousClass1.run():void");
                }
            });
            return;
        }
        JSONObject error = createFileTransferError(INVALID_URL_ERR, source, target, (String) null, 0, (Throwable) null);
        LOG.e(LOG_TAG, "Unsupported URI: " + targetUri);
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, error));
    }

    /* access modifiers changed from: private */
    public static void safeClose(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static TrackingInputStream getInputStream(URLConnection conn) throws IOException {
        String encoding = conn.getContentEncoding();
        if (encoding == null || !encoding.equalsIgnoreCase("gzip")) {
            return new SimpleTrackingInputStream(conn.getInputStream());
        }
        return new TrackingGZIPInputStream(new ExposedGZIPInputStream(conn.getInputStream()));
    }

    /* access modifiers changed from: private */
    public static JSONObject createFileTransferError(int errorCode, String source, String target, URLConnection connection, Throwable throwable) {
        BufferedReader reader;
        int httpStatus = 0;
        StringBuilder bodyBuilder = new StringBuilder();
        String body = null;
        if (connection != null) {
            try {
                if (connection instanceof HttpURLConnection) {
                    httpStatus = ((HttpURLConnection) connection).getResponseCode();
                    InputStream err = ((HttpURLConnection) connection).getErrorStream();
                    if (err != null) {
                        reader = new BufferedReader(new InputStreamReader(err, WebRequest.CHARSET_UTF_8));
                        String line = reader.readLine();
                        while (line != null) {
                            bodyBuilder.append(line);
                            line = reader.readLine();
                            if (line != null) {
                                bodyBuilder.append(10);
                            }
                        }
                        body = bodyBuilder.toString();
                        reader.close();
                    }
                }
            } catch (Throwable e) {
                LOG.w(LOG_TAG, "Error getting HTTP status code from connection.", e);
            }
        }
        return createFileTransferError(errorCode, source, target, body, Integer.valueOf(httpStatus), throwable);
    }

    /* access modifiers changed from: private */
    public static JSONObject createFileTransferError(int errorCode, String source, String target, String body, Integer httpStatus, Throwable throwable) {
        JSONObject error = null;
        try {
            JSONObject error2 = new JSONObject();
            try {
                error2.put("code", errorCode);
                error2.put(FirebaseAnalytics.Param.SOURCE, source);
                error2.put("target", target);
                if (body != null) {
                    error2.put(PushConstants.BODY, body);
                }
                if (httpStatus != null) {
                    error2.put("http_status", httpStatus);
                }
                if (throwable != null) {
                    String msg = throwable.getMessage();
                    if (msg == null || "".equals(msg)) {
                        msg = throwable.toString();
                    }
                    error2.put("exception", msg);
                }
                return error2;
            } catch (JSONException e) {
                e = e;
                error = error2;
                LOG.e(LOG_TAG, e.getMessage(), (Throwable) e);
                return error;
            }
        } catch (JSONException e2) {
            e = e2;
            LOG.e(LOG_TAG, e.getMessage(), (Throwable) e);
            return error;
        }
    }

    private static String getArgument(JSONArray args, int position, String defaultString) {
        String arg = defaultString;
        if (args.length() <= position) {
            return arg;
        }
        String arg2 = args.optString(position);
        if (arg2 == null || "null".equals(arg2)) {
            return defaultString;
        }
        return arg2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: java.lang.Boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void download(java.lang.String r25, java.lang.String r26, org.json.JSONArray r27, org.apache.cordova.CallbackContext r28) throws org.json.JSONException {
        /*
            r24 = this;
            java.lang.String r3 = "FileTransfer"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "download "
            java.lang.StringBuilder r4 = r4.append(r6)
            r0 = r25
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r6 = " to "
            java.lang.StringBuilder r4 = r4.append(r6)
            r0 = r26
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            org.apache.cordova.LOG.d(r3, r4)
            r0 = r24
            org.apache.cordova.CordovaWebView r3 = r0.webView
            org.apache.cordova.CordovaResourceApi r7 = r3.getResourceApi()
            r3 = 3
            r0 = r27
            java.lang.String r12 = r0.getString(r3)
            r3 = 4
            r0 = r27
            org.json.JSONObject r10 = r0.optJSONObject(r3)
            android.net.Uri r3 = android.net.Uri.parse(r25)
            android.net.Uri r19 = r7.remapUri(r3)
            int r20 = org.apache.cordova.CordovaResourceApi.getUriType(r19)
            r3 = 6
            r0 = r20
            if (r0 != r3) goto L_0x0094
            r21 = 1
        L_0x004f:
            if (r21 != 0) goto L_0x0097
            r3 = 5
            r0 = r20
            if (r0 == r3) goto L_0x0097
            r9 = 1
        L_0x0057:
            r3 = -1
            r0 = r20
            if (r0 != r3) goto L_0x0099
            int r3 = INVALID_URL_ERR
            r6 = 0
            r4 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            r8 = 0
            r4 = r25
            r5 = r26
            org.json.JSONObject r13 = createFileTransferError(r3, r4, r5, r6, r7, r8)
            java.lang.String r3 = "FileTransfer"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Unsupported URI: "
            java.lang.StringBuilder r4 = r4.append(r6)
            r0 = r19
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            org.apache.cordova.LOG.e(r3, r4)
            org.apache.cordova.PluginResult r3 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r4 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION
            r3.<init>((org.apache.cordova.PluginResult.Status) r4, (org.json.JSONObject) r13)
            r0 = r28
            r0.sendPluginResult(r3)
        L_0x0093:
            return
        L_0x0094:
            r21 = 0
            goto L_0x004f
        L_0x0097:
            r9 = 0
            goto L_0x0057
        L_0x0099:
            r18 = 0
            if (r9 == 0) goto L_0x00a2
            r3 = 1
            java.lang.Boolean r18 = java.lang.Boolean.valueOf(r3)
        L_0x00a2:
            if (r18 != 0) goto L_0x00ce
            r0 = r24
            org.apache.cordova.CordovaWebView r3 = r0.webView     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            java.lang.Class r3 = r3.getClass()     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            java.lang.String r4 = "getWhitelist"
            r6 = 0
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            java.lang.reflect.Method r15 = r3.getMethod(r4, r6)     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            r0 = r24
            org.apache.cordova.CordovaWebView r3 = r0.webView     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            java.lang.Object r22 = r15.invoke(r3, r4)     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            org.apache.cordova.Whitelist r22 = (org.apache.cordova.Whitelist) r22     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            r0 = r22
            r1 = r25
            boolean r3 = r0.isUrlWhiteListed(r1)     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
            java.lang.Boolean r18 = java.lang.Boolean.valueOf(r3)     // Catch:{ NoSuchMethodException -> 0x019d, IllegalAccessException -> 0x019a, InvocationTargetException -> 0x0197 }
        L_0x00ce:
            if (r18 != 0) goto L_0x0113
            r0 = r24
            org.apache.cordova.CordovaWebView r3 = r0.webView     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            java.lang.Class r3 = r3.getClass()     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            java.lang.String r4 = "getPluginManager"
            r6 = 0
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            java.lang.reflect.Method r14 = r3.getMethod(r4, r6)     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            r0 = r24
            org.apache.cordova.CordovaWebView r3 = r0.webView     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            java.lang.Object r16 = r14.invoke(r3, r4)     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            org.apache.cordova.PluginManager r16 = (org.apache.cordova.PluginManager) r16     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            java.lang.Class r3 = r16.getClass()     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            java.lang.String r4 = "shouldAllowRequest"
            r6 = 1
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            r8 = 0
            java.lang.Class<java.lang.String> r11 = java.lang.String.class
            r6[r8] = r11     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            java.lang.reflect.Method r17 = r3.getMethod(r4, r6)     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            r4 = 0
            r3[r4] = r25     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            r0 = r17
            r1 = r16
            java.lang.Object r3 = r0.invoke(r1, r3)     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            r0 = r3
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ NoSuchMethodException -> 0x0194, IllegalAccessException -> 0x0192, InvocationTargetException -> 0x0190 }
            r18 = r0
        L_0x0113:
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            r0 = r18
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x015d
            java.lang.String r3 = "FileTransfer"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Source URL is not in white list: '"
            java.lang.StringBuilder r4 = r4.append(r6)
            r0 = r25
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r6 = "'"
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            org.apache.cordova.LOG.w((java.lang.String) r3, (java.lang.String) r4)
            int r3 = CONNECTION_ERR
            r6 = 0
            r4 = 401(0x191, float:5.62E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            r8 = 0
            r4 = r25
            r5 = r26
            org.json.JSONObject r13 = createFileTransferError(r3, r4, r5, r6, r7, r8)
            org.apache.cordova.PluginResult r3 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r4 = org.apache.cordova.PluginResult.Status.IO_EXCEPTION
            r3.<init>((org.apache.cordova.PluginResult.Status) r4, (org.json.JSONObject) r13)
            r0 = r28
            r0.sendPluginResult(r3)
            goto L_0x0093
        L_0x015d:
            org.apache.cordova.filetransfer.FileTransfer$RequestContext r5 = new org.apache.cordova.filetransfer.FileTransfer$RequestContext
            r0 = r25
            r1 = r26
            r2 = r28
            r5.<init>(r0, r1, r2)
            java.util.HashMap<java.lang.String, org.apache.cordova.filetransfer.FileTransfer$RequestContext> r4 = activeRequests
            monitor-enter(r4)
            java.util.HashMap<java.lang.String, org.apache.cordova.filetransfer.FileTransfer$RequestContext> r3 = activeRequests     // Catch:{ all -> 0x018d }
            r3.put(r12, r5)     // Catch:{ all -> 0x018d }
            monitor-exit(r4)     // Catch:{ all -> 0x018d }
            r0 = r24
            org.apache.cordova.CordovaInterface r3 = r0.f5cordova
            java.util.concurrent.ExecutorService r23 = r3.getThreadPool()
            org.apache.cordova.filetransfer.FileTransfer$2 r3 = new org.apache.cordova.filetransfer.FileTransfer$2
            r4 = r24
            r6 = r26
            r8 = r19
            r11 = r25
            r3.<init>(r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r23
            r0.execute(r3)
            goto L_0x0093
        L_0x018d:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x018d }
            throw r3
        L_0x0190:
            r3 = move-exception
            goto L_0x0113
        L_0x0192:
            r3 = move-exception
            goto L_0x0113
        L_0x0194:
            r3 = move-exception
            goto L_0x0113
        L_0x0197:
            r3 = move-exception
            goto L_0x00ce
        L_0x019a:
            r3 = move-exception
            goto L_0x00ce
        L_0x019d:
            r3 = move-exception
            goto L_0x00ce
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.filetransfer.FileTransfer.download(java.lang.String, java.lang.String, org.json.JSONArray, org.apache.cordova.CallbackContext):void");
    }

    private void abort(String objectId) {
        final RequestContext context;
        synchronized (activeRequests) {
            context = activeRequests.remove(objectId);
        }
        if (context != null) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    synchronized (context) {
                        File file = context.targetFile;
                        if (file != null) {
                            file.delete();
                        }
                        context.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, FileTransfer.createFileTransferError(FileTransfer.ABORTED_ERR, context.source, context.target, (String) null, -1, (Throwable) null)));
                        context.aborted = true;
                        if (context.connection != null) {
                            try {
                                context.connection.disconnect();
                            } catch (Exception e) {
                                LOG.e(FileTransfer.LOG_TAG, "CB-8431 Catch workaround for fatal exception", (Throwable) e);
                            }
                        }
                    }
                }
            });
        }
    }
}
