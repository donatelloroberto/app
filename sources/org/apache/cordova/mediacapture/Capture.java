package org.apache.cordova.mediacapture;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import com.adobe.phonegap.push.PushConstants;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.mediacapture.PendingRequests;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Capture extends CordovaPlugin {
    private static final String AUDIO_3GPP = "audio/3gpp";
    private static final String[] AUDIO_TYPES = {AUDIO_3GPP, "audio/aac", "audio/amr", "audio/wav"};
    private static final int CAPTURE_AUDIO = 0;
    private static final int CAPTURE_IMAGE = 1;
    private static final int CAPTURE_INTERNAL_ERR = 0;
    private static final int CAPTURE_NOT_SUPPORTED = 20;
    private static final int CAPTURE_NO_MEDIA_FILES = 3;
    private static final int CAPTURE_PERMISSION_DENIED = 4;
    private static final int CAPTURE_VIDEO = 2;
    private static final String IMAGE_JPEG = "image/jpeg";
    private static final String LOG_TAG = "Capture";
    private static final String VIDEO_3GPP = "video/3gpp";
    private static final String VIDEO_MP4 = "video/mp4";
    private boolean cameraPermissionInManifest;
    private Uri imageUri;
    private int numPics;
    private final PendingRequests pendingRequests = new PendingRequests();

    /* access modifiers changed from: protected */
    public void pluginInitialize() {
        super.pluginInitialize();
        this.cameraPermissionInManifest = false;
        try {
            String[] permissionsInPackage = this.f5cordova.getActivity().getPackageManager().getPackageInfo(this.f5cordova.getActivity().getPackageName(), 4096).requestedPermissions;
            if (permissionsInPackage != null) {
                for (String permission : permissionsInPackage) {
                    if (permission.equals("android.permission.CAMERA")) {
                        this.cameraPermissionInManifest = true;
                        return;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            LOG.e(LOG_TAG, "Failed checking for CAMERA permission in manifest", (Throwable) e);
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getFormatData")) {
            callbackContext.success(getFormatData(args.getString(0), args.getString(1)));
            return true;
        }
        JSONObject options = args.optJSONObject(0);
        if (action.equals("captureAudio")) {
            captureAudio(this.pendingRequests.createRequest(0, options, callbackContext));
            return true;
        } else if (action.equals("captureImage")) {
            captureImage(this.pendingRequests.createRequest(1, options, callbackContext));
            return true;
        } else if (!action.equals("captureVideo")) {
            return false;
        } else {
            captureVideo(this.pendingRequests.createRequest(2, options, callbackContext));
            return true;
        }
    }

    private JSONObject getFormatData(String filePath, String mimeType) throws JSONException {
        Uri fileUrl;
        if (filePath.startsWith("file:")) {
            fileUrl = Uri.parse(filePath);
        } else {
            fileUrl = Uri.fromFile(new File(filePath));
        }
        JSONObject obj = new JSONObject();
        obj.put("height", 0);
        obj.put("width", 0);
        obj.put("bitrate", 0);
        obj.put("duration", 0);
        obj.put("codecs", "");
        if (mimeType == null || mimeType.equals("") || "null".equals(mimeType)) {
            mimeType = FileHelper.getMimeType(fileUrl, this.f5cordova);
        }
        LOG.d(LOG_TAG, "Mime type = " + mimeType);
        if (mimeType.equals(IMAGE_JPEG) || filePath.endsWith(".jpg")) {
            return getImageData(fileUrl, obj);
        }
        if (Arrays.asList(AUDIO_TYPES).contains(mimeType)) {
            return getAudioVideoData(filePath, obj, false);
        }
        if (mimeType.equals(VIDEO_3GPP) || mimeType.equals(VIDEO_MP4)) {
            return getAudioVideoData(filePath, obj, true);
        }
        return obj;
    }

    private JSONObject getImageData(Uri fileUrl, JSONObject obj) throws JSONException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileUrl.getPath(), options);
        obj.put("height", options.outHeight);
        obj.put("width", options.outWidth);
        return obj;
    }

    private JSONObject getAudioVideoData(String filePath, JSONObject obj, boolean video) throws JSONException {
        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(filePath);
            player.prepare();
            obj.put("duration", player.getDuration() / 1000);
            if (video) {
                obj.put("height", player.getVideoHeight());
                obj.put("width", player.getVideoWidth());
            }
        } catch (IOException e) {
            LOG.d(LOG_TAG, "Error: loading video file");
        }
        return obj;
    }

    private void captureAudio(PendingRequests.Request req) {
        if (!PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE")) {
            PermissionHelper.requestPermission(this, req.requestCode, "android.permission.READ_EXTERNAL_STORAGE");
            return;
        }
        try {
            this.f5cordova.startActivityForResult(this, new Intent("android.provider.MediaStore.RECORD_SOUND"), req.requestCode);
        } catch (ActivityNotFoundException e) {
            this.pendingRequests.resolveWithFailure(req, createErrorObject(20, "No Activity found to handle Audio Capture."));
        }
    }

    private String getTempDirectoryPath() {
        File cache = this.f5cordova.getActivity().getCacheDir();
        cache.mkdirs();
        return cache.getAbsolutePath();
    }

    private void captureImage(PendingRequests.Request req) {
        boolean needExternalStoragePermission;
        boolean needCameraPermission;
        if (!PermissionHelper.hasPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            needExternalStoragePermission = true;
        } else {
            needExternalStoragePermission = false;
        }
        if (!this.cameraPermissionInManifest || PermissionHelper.hasPermission(this, "android.permission.CAMERA")) {
            needCameraPermission = false;
        } else {
            needCameraPermission = true;
        }
        if (!needExternalStoragePermission && !needCameraPermission) {
            this.numPics = queryImgDB(whichContentStore()).getCount();
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            ContentResolver contentResolver = this.f5cordova.getActivity().getContentResolver();
            ContentValues cv = new ContentValues();
            cv.put("mime_type", IMAGE_JPEG);
            this.imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
            LOG.d(LOG_TAG, "Taking a picture and saving to: " + this.imageUri.toString());
            intent.putExtra("output", this.imageUri);
            this.f5cordova.startActivityForResult(this, intent, req.requestCode);
        } else if (needExternalStoragePermission && needCameraPermission) {
            PermissionHelper.requestPermissions(this, req.requestCode, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"});
        } else if (needExternalStoragePermission) {
            PermissionHelper.requestPermission(this, req.requestCode, "android.permission.WRITE_EXTERNAL_STORAGE");
        } else {
            PermissionHelper.requestPermission(this, req.requestCode, "android.permission.CAMERA");
        }
    }

    private static void createWritableFile(File file) throws IOException {
        file.createNewFile();
        file.setWritable(true, false);
    }

    private void captureVideo(PendingRequests.Request req) {
        if (!this.cameraPermissionInManifest || PermissionHelper.hasPermission(this, "android.permission.CAMERA")) {
            Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
            if (Build.VERSION.SDK_INT > 7) {
                intent.putExtra("android.intent.extra.durationLimit", req.duration);
                intent.putExtra("android.intent.extra.videoQuality", req.quality);
            }
            this.f5cordova.startActivityForResult(this, intent, req.requestCode);
            return;
        }
        PermissionHelper.requestPermission(this, req.requestCode, "android.permission.CAMERA");
    }

    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        final PendingRequests.Request req = this.pendingRequests.get(requestCode);
        if (resultCode == -1) {
            this.f5cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    switch (req.action) {
                        case 0:
                            Capture.this.onAudioActivityResult(req, intent);
                            return;
                        case 1:
                            Capture.this.onImageActivityResult(req);
                            return;
                        case 2:
                            Capture.this.onVideoActivityResult(req, intent);
                            return;
                        default:
                            return;
                    }
                }
            });
        } else if (resultCode == 0) {
            if (req.results.length() > 0) {
                this.pendingRequests.resolveWithSuccess(req);
            } else {
                this.pendingRequests.resolveWithFailure(req, createErrorObject(3, "Canceled."));
            }
        } else if (req.results.length() > 0) {
            this.pendingRequests.resolveWithSuccess(req);
        } else {
            this.pendingRequests.resolveWithFailure(req, createErrorObject(3, "Did not complete!"));
        }
    }

    public void onAudioActivityResult(PendingRequests.Request req, Intent intent) {
        req.results.put(createMediaFile(intent.getData()));
        if (((long) req.results.length()) >= req.limit) {
            this.pendingRequests.resolveWithSuccess(req);
        } else {
            captureAudio(req);
        }
    }

    public void onImageActivityResult(PendingRequests.Request req) {
        req.results.put(createMediaFile(this.imageUri));
        checkForDuplicateImage();
        if (((long) req.results.length()) >= req.limit) {
            this.pendingRequests.resolveWithSuccess(req);
        } else {
            captureImage(req);
        }
    }

    public void onVideoActivityResult(PendingRequests.Request req, Intent intent) {
        Uri data = null;
        if (intent != null) {
            data = intent.getData();
        }
        if (data == null) {
            data = Uri.fromFile(new File(getTempDirectoryPath(), "Capture.avi"));
        }
        if (data == null) {
            this.pendingRequests.resolveWithFailure(req, createErrorObject(3, "Error: data is null"));
            return;
        }
        req.results.put(createMediaFile(data));
        if (((long) req.results.length()) >= req.limit) {
            this.pendingRequests.resolveWithSuccess(req);
        } else {
            captureVideo(req);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: org.apache.cordova.PluginManager} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.apache.cordova.PluginManager} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject createMediaFile(android.net.Uri r15) {
        /*
            r14 = this;
            org.apache.cordova.CordovaWebView r11 = r14.webView
            org.apache.cordova.CordovaResourceApi r11 = r11.getResourceApi()
            java.io.File r4 = r11.mapUriToFile(r15)
            org.json.JSONObject r6 = new org.json.JSONObject
            r6.<init>()
            org.apache.cordova.CordovaWebView r11 = r14.webView
            java.lang.Class r10 = r11.getClass()
            r7 = 0
            java.lang.String r11 = "getPluginManager"
            r12 = 0
            java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ NoSuchMethodException -> 0x00da, IllegalAccessException -> 0x00d7, InvocationTargetException -> 0x00d4 }
            java.lang.reflect.Method r5 = r10.getMethod(r11, r12)     // Catch:{ NoSuchMethodException -> 0x00da, IllegalAccessException -> 0x00d7, InvocationTargetException -> 0x00d4 }
            org.apache.cordova.CordovaWebView r11 = r14.webView     // Catch:{ NoSuchMethodException -> 0x00da, IllegalAccessException -> 0x00d7, InvocationTargetException -> 0x00d4 }
            r12 = 0
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch:{ NoSuchMethodException -> 0x00da, IllegalAccessException -> 0x00d7, InvocationTargetException -> 0x00d4 }
            java.lang.Object r11 = r5.invoke(r11, r12)     // Catch:{ NoSuchMethodException -> 0x00da, IllegalAccessException -> 0x00d7, InvocationTargetException -> 0x00d4 }
            r0 = r11
            org.apache.cordova.PluginManager r0 = (org.apache.cordova.PluginManager) r0     // Catch:{ NoSuchMethodException -> 0x00da, IllegalAccessException -> 0x00d7, InvocationTargetException -> 0x00d4 }
            r7 = r0
        L_0x002c:
            if (r7 != 0) goto L_0x003e
            java.lang.String r11 = "pluginManager"
            java.lang.reflect.Field r8 = r10.getField(r11)     // Catch:{ NoSuchFieldException -> 0x00d1, IllegalAccessException -> 0x00ce }
            org.apache.cordova.CordovaWebView r11 = r14.webView     // Catch:{ NoSuchFieldException -> 0x00d1, IllegalAccessException -> 0x00ce }
            java.lang.Object r11 = r8.get(r11)     // Catch:{ NoSuchFieldException -> 0x00d1, IllegalAccessException -> 0x00ce }
            r0 = r11
            org.apache.cordova.PluginManager r0 = (org.apache.cordova.PluginManager) r0     // Catch:{ NoSuchFieldException -> 0x00d1, IllegalAccessException -> 0x00ce }
            r7 = r0
        L_0x003e:
            java.lang.String r11 = "File"
            org.apache.cordova.CordovaPlugin r3 = r7.getPlugin(r11)
            org.apache.cordova.file.FileUtils r3 = (org.apache.cordova.file.FileUtils) r3
            java.lang.String r11 = r4.getAbsolutePath()
            org.apache.cordova.file.LocalFilesystemURL r9 = r3.filesystemURLforLocalPath(r11)
            java.lang.String r11 = "name"
            java.lang.String r12 = r4.getName()     // Catch:{ JSONException -> 0x00b9 }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r11 = "fullPath"
            android.net.Uri r12 = android.net.Uri.fromFile(r4)     // Catch:{ JSONException -> 0x00b9 }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00b9 }
            if (r9 == 0) goto L_0x006b
            java.lang.String r11 = "localURL"
            java.lang.String r12 = r9.toString()     // Catch:{ JSONException -> 0x00b9 }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00b9 }
        L_0x006b:
            java.io.File r11 = r4.getAbsoluteFile()     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r12 = ".3gp"
            boolean r11 = r11.endsWith(r12)     // Catch:{ JSONException -> 0x00b9 }
            if (r11 != 0) goto L_0x008b
            java.io.File r11 = r4.getAbsoluteFile()     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r12 = ".3gpp"
            boolean r11 = r11.endsWith(r12)     // Catch:{ JSONException -> 0x00b9 }
            if (r11 == 0) goto L_0x00be
        L_0x008b:
            java.lang.String r11 = r15.toString()     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r12 = "/audio/"
            boolean r11 = r11.contains(r12)     // Catch:{ JSONException -> 0x00b9 }
            if (r11 == 0) goto L_0x00b1
            java.lang.String r11 = "type"
            java.lang.String r12 = "audio/3gpp"
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00b9 }
        L_0x009e:
            java.lang.String r11 = "lastModifiedDate"
            long r12 = r4.lastModified()     // Catch:{ JSONException -> 0x00b9 }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r11 = "size"
            long r12 = r4.length()     // Catch:{ JSONException -> 0x00b9 }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00b9 }
        L_0x00b0:
            return r6
        L_0x00b1:
            java.lang.String r11 = "type"
            java.lang.String r12 = "video/3gpp"
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00b9 }
            goto L_0x009e
        L_0x00b9:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00b0
        L_0x00be:
            java.lang.String r11 = "type"
            android.net.Uri r12 = android.net.Uri.fromFile(r4)     // Catch:{ JSONException -> 0x00b9 }
            org.apache.cordova.CordovaInterface r13 = r14.f5cordova     // Catch:{ JSONException -> 0x00b9 }
            java.lang.String r12 = org.apache.cordova.mediacapture.FileHelper.getMimeType(r12, r13)     // Catch:{ JSONException -> 0x00b9 }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x00b9 }
            goto L_0x009e
        L_0x00ce:
            r11 = move-exception
            goto L_0x003e
        L_0x00d1:
            r11 = move-exception
            goto L_0x003e
        L_0x00d4:
            r11 = move-exception
            goto L_0x002c
        L_0x00d7:
            r11 = move-exception
            goto L_0x002c
        L_0x00da:
            r11 = move-exception
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.mediacapture.Capture.createMediaFile(android.net.Uri):org.json.JSONObject");
    }

    private JSONObject createErrorObject(int code, String message) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("code", code);
            obj.put(PushConstants.MESSAGE, message);
        } catch (JSONException e) {
        }
        return obj;
    }

    private Cursor queryImgDB(Uri contentStore) {
        return this.f5cordova.getActivity().getContentResolver().query(contentStore, new String[]{"_id"}, (String) null, (String[]) null, (String) null);
    }

    private void checkForDuplicateImage() {
        Uri contentStore = whichContentStore();
        Cursor cursor = queryImgDB(contentStore);
        if (cursor.getCount() - this.numPics == 2) {
            cursor.moveToLast();
            this.f5cordova.getActivity().getContentResolver().delete(Uri.parse(contentStore + "/" + (Integer.valueOf(cursor.getString(cursor.getColumnIndex("_id"))).intValue() - 1)), (String) null, (String[]) null);
        }
    }

    private Uri whichContentStore() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
    }

    private void executeRequest(PendingRequests.Request req) {
        switch (req.action) {
            case 0:
                captureAudio(req);
                return;
            case 1:
                captureImage(req);
                return;
            case 2:
                captureVideo(req);
                return;
            default:
                return;
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
        PendingRequests.Request req = this.pendingRequests.get(requestCode);
        if (req != null) {
            boolean success = true;
            int length = grantResults.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (grantResults[i] == -1) {
                    success = false;
                    break;
                } else {
                    i++;
                }
            }
            if (success) {
                executeRequest(req);
            } else {
                this.pendingRequests.resolveWithFailure(req, createErrorObject(4, "Permission denied."));
            }
        }
    }

    public Bundle onSaveInstanceState() {
        return this.pendingRequests.toBundle();
    }

    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) {
        this.pendingRequests.setLastSavedState(state, callbackContext);
    }
}
