package nl.xservices.plugins;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Base64;
import android.widget.Toast;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SocialSharing extends CordovaPlugin {
    private static final String ACTION_AVAILABLE_EVENT = "available";
    private static final String ACTION_CAN_SHARE_VIA = "canShareVia";
    private static final String ACTION_CAN_SHARE_VIA_EMAIL = "canShareViaEmail";
    private static final String ACTION_SHARE_EVENT = "share";
    private static final String ACTION_SHARE_VIA = "shareVia";
    private static final String ACTION_SHARE_VIA_EMAIL_EVENT = "shareViaEmail";
    private static final String ACTION_SHARE_VIA_FACEBOOK_EVENT = "shareViaFacebook";
    private static final String ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT = "shareViaFacebookWithPasteMessageHint";
    private static final String ACTION_SHARE_VIA_INSTAGRAM_EVENT = "shareViaInstagram";
    private static final String ACTION_SHARE_VIA_SMS_EVENT = "shareViaSMS";
    private static final String ACTION_SHARE_VIA_TWITTER_EVENT = "shareViaTwitter";
    private static final String ACTION_SHARE_VIA_WHATSAPP_EVENT = "shareViaWhatsApp";
    private static final String ACTION_SHARE_WITH_OPTIONS_EVENT = "shareWithOptions";
    private static final int ACTIVITY_CODE_SENDVIAEMAIL = 3;
    private static final int ACTIVITY_CODE_SENDVIAWHATSAPP = 4;
    private static final int ACTIVITY_CODE_SEND__BOOLRESULT = 1;
    private static final int ACTIVITY_CODE_SEND__OBJECT = 2;
    private static final Map<String, String> MIME_Map = new HashMap();
    private CallbackContext _callbackContext;
    /* access modifiers changed from: private */
    public String pasteMessage;

    private abstract class SocialSharingRunnable implements Runnable {
        public CallbackContext callbackContext;

        SocialSharingRunnable(CallbackContext cb) {
            this.callbackContext = cb;
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this._callbackContext = callbackContext;
        this.pasteMessage = null;
        if (ACTION_AVAILABLE_EVENT.equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            return true;
        } else if ("share".equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), (String) null, (String) null, false, true);
        } else if (ACTION_SHARE_WITH_OPTIONS_EVENT.equals(action)) {
            return shareWithOptions(callbackContext, args.getJSONObject(0));
        } else {
            if (ACTION_SHARE_VIA_TWITTER_EVENT.equals(action)) {
                return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "twitter", (String) null, false, true);
            } else if (ACTION_SHARE_VIA_FACEBOOK_EVENT.equals(action)) {
                return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.isNull(3) ? args.getJSONArray(2) : null, args.getString(3), "com.facebook.katana", (String) null, false, true, "com.facebook.composer.shareintent");
            } else if (ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT.equals(action)) {
                this.pasteMessage = args.getString(4);
                return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.isNull(3) ? args.getJSONArray(2) : null, args.getString(3), "com.facebook.katana", (String) null, false, true, "com.facebook.composer.shareintent");
            } else if (ACTION_SHARE_VIA_WHATSAPP_EVENT.equals(action)) {
                if (notEmpty(args.getString(4))) {
                    return shareViaWhatsAppDirectly(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4));
                } else if (notEmpty(args.getString(5))) {
                    return shareViaWhatsAppDirectly(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(5));
                } else {
                    return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "whatsapp", (String) null, false, true);
                }
            } else if (ACTION_SHARE_VIA_INSTAGRAM_EVENT.equals(action)) {
                if (notEmpty(args.getString(0))) {
                    copyHintToClipboard(args.getString(0), "Instagram paste message");
                }
                return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "instagram", (String) null, false, true);
            } else if (ACTION_CAN_SHARE_VIA.equals(action)) {
                return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4), (String) null, true, true);
            } else if (ACTION_CAN_SHARE_VIA_EMAIL.equals(action)) {
                if (isEmailAvailable()) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                    return true;
                }
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "not available"));
                return false;
            } else if (ACTION_SHARE_VIA.equals(action)) {
                return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4), (String) null, false, true);
            } else if (ACTION_SHARE_VIA_SMS_EVENT.equals(action)) {
                return invokeSMSIntent(callbackContext, args.getJSONObject(0), args.getString(1));
            } else {
                if (ACTION_SHARE_VIA_EMAIL_EVENT.equals(action)) {
                    return invokeEmailIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.isNull(3) ? null : args.getJSONArray(3), args.isNull(4) ? null : args.getJSONArray(4), args.isNull(5) ? null : args.getJSONArray(5));
                }
                callbackContext.error("socialSharing." + action + " is not a supported function. Did you mean '" + "share" + "'?");
                return false;
            }
        }
    }

    private boolean isEmailAvailable() {
        if (this.f5cordova.getActivity().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.fromParts(AdWebViewClient.MAILTO, "someone@domain.com", (String) null)), 0).size() > 0) {
            return true;
        }
        return false;
    }

    private boolean invokeEmailIntent(CallbackContext callbackContext, String message, String subject, JSONArray to, JSONArray cc, JSONArray bcc, JSONArray files) throws JSONException {
        final String str = message;
        final String str2 = subject;
        final JSONArray jSONArray = to;
        final JSONArray jSONArray2 = cc;
        final JSONArray jSONArray3 = bcc;
        final JSONArray jSONArray4 = files;
        this.f5cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) {
            public void run() {
                String dir;
                Intent draft = new Intent("android.intent.action.SENDTO");
                if (SocialSharing.notEmpty(str)) {
                    if (Pattern.compile(".*\\<[^>]+>.*", 32).matcher(str).matches()) {
                        draft.putExtra("android.intent.extra.TEXT", Html.fromHtml(str));
                        draft.setType(WebRequest.CONTENT_TYPE_HTML);
                    } else {
                        draft.putExtra("android.intent.extra.TEXT", str);
                        draft.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                    }
                }
                if (SocialSharing.notEmpty(str2)) {
                    draft.putExtra("android.intent.extra.SUBJECT", str2);
                }
                try {
                    if (jSONArray != null && jSONArray.length() > 0) {
                        draft.putExtra("android.intent.extra.EMAIL", SocialSharing.toStringArray(jSONArray));
                    }
                    if (jSONArray2 != null && jSONArray2.length() > 0) {
                        draft.putExtra("android.intent.extra.CC", SocialSharing.toStringArray(jSONArray2));
                    }
                    if (jSONArray3 != null && jSONArray3.length() > 0) {
                        draft.putExtra("android.intent.extra.BCC", SocialSharing.toStringArray(jSONArray3));
                    }
                    if (jSONArray4.length() > 0 && (dir = SocialSharing.this.getDownloadDir()) != null) {
                        ArrayList<Uri> fileUris = new ArrayList<>();
                        for (int i = 0; i < jSONArray4.length(); i++) {
                            Uri fileUri = FileProvider.getUriForFile(SocialSharing.this.webView.getContext(), SocialSharing.this.f5cordova.getActivity().getPackageName() + ".sharing.provider", new File(SocialSharing.this.getFileUriAndSetType(draft, dir, jSONArray4.getString(i), str2, i).getPath()));
                            if (fileUri != null) {
                                fileUris.add(fileUri);
                            }
                        }
                        if (!fileUris.isEmpty()) {
                            draft.putExtra("android.intent.extra.STREAM", fileUris);
                        }
                    }
                    draft.addFlags(268435456);
                    draft.setData(Uri.parse("mailto:"));
                    List<ResolveInfo> emailAppList = SocialSharing.this.f5cordova.getActivity().getPackageManager().queryIntentActivities(draft, 0);
                    List<LabeledIntent> labeledIntentList = new ArrayList<>();
                    for (ResolveInfo info : emailAppList) {
                        draft.setAction("android.intent.action.SEND_MULTIPLE");
                        draft.setType("application/octet-stream");
                        draft.setComponent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
                        labeledIntentList.add(new LabeledIntent(draft, info.activityInfo.packageName, info.loadLabel(SocialSharing.this.f5cordova.getActivity().getPackageManager()), info.icon));
                    }
                    final Intent emailAppLists = Intent.createChooser(labeledIntentList.remove(labeledIntentList.size() - 1), "Choose Email App");
                    emailAppLists.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) labeledIntentList.toArray(new LabeledIntent[labeledIntentList.size()]));
                    SocialSharing.this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            SocialSharing.this.f5cordova.startActivityForResult(this, emailAppLists, 3);
                        }
                    });
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public String getDownloadDir() throws IOException {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        String dir = this.webView.getContext().getExternalFilesDir((String) null) + "/socialsharing-downloads";
        createOrCleanDir(dir);
        return dir;
    }

    private boolean shareWithOptions(CallbackContext callbackContext, JSONObject jsonObject) {
        return doSendIntent(callbackContext, jsonObject.optString(PushConstants.MESSAGE, (String) null), jsonObject.optString(PushConstants.SUBJECT, (String) null), jsonObject.optJSONArray("files") == null ? new JSONArray() : jsonObject.optJSONArray("files"), jsonObject.optString(ImagesContract.URL, (String) null), jsonObject.optString("appPackageName", (String) null), jsonObject.optString("chooserTitle", (String) null), false, false);
    }

    private boolean doSendIntent(CallbackContext callbackContext, String msg, String subject, JSONArray files, String url, String appPackageName, String chooserTitle, boolean peek, boolean boolResult) {
        return doSendIntent(callbackContext, msg, subject, files, url, appPackageName, chooserTitle, peek, boolResult, (String) null);
    }

    private boolean doSendIntent(CallbackContext callbackContext, String msg, String subject, JSONArray files, String url, String appPackageName, String chooserTitle, boolean peek, boolean boolResult, String appName) {
        final CordovaInterface mycordova = this.f5cordova;
        final String str = msg;
        final JSONArray jSONArray = files;
        final String str2 = subject;
        final String str3 = url;
        final String str4 = appPackageName;
        final String str5 = appName;
        final boolean z = peek;
        final String str6 = chooserTitle;
        final boolean z2 = boolResult;
        this.f5cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) {
            /* JADX WARNING: Removed duplicated region for block: B:28:0x00f4  */
            /* JADX WARNING: Removed duplicated region for block: B:31:0x0107  */
            /* JADX WARNING: Removed duplicated region for block: B:36:0x012e  */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x0149  */
            /* JADX WARNING: Removed duplicated region for block: B:65:0x0203  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r22 = this;
                    r0 = r22
                    java.lang.String r14 = r3
                    r0 = r22
                    org.json.JSONArray r2 = r4
                    int r2 = r2.length()
                    r5 = 1
                    if (r2 <= r5) goto L_0x00d6
                    r12 = 1
                L_0x0010:
                    android.content.Intent r3 = new android.content.Intent
                    if (r12 == 0) goto L_0x00d9
                    java.lang.String r2 = "android.intent.action.SEND_MULTIPLE"
                L_0x0016:
                    r3.<init>(r2)
                    android.content.Intent r18 = new android.content.Intent
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this
                    org.apache.cordova.CordovaInterface r2 = r2.f5cordova
                    android.app.Activity r2 = r2.getActivity()
                    android.content.Context r2 = r2.getApplicationContext()
                    java.lang.Class<nl.xservices.plugins.ShareChooserPendingIntent> r5 = nl.xservices.plugins.ShareChooserPendingIntent.class
                    r0 = r18
                    r0.<init>(r2, r5)
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this
                    org.apache.cordova.CordovaInterface r2 = r2.f5cordova
                    android.app.Activity r2 = r2.getActivity()
                    android.content.Context r2 = r2.getApplicationContext()
                    r5 = 0
                    r6 = 134217728(0x8000000, float:3.85186E-34)
                    r0 = r18
                    android.app.PendingIntent r17 = android.app.PendingIntent.getBroadcast(r2, r5, r0, r6)
                    r2 = 524288(0x80000, float:7.34684E-40)
                    r3.addFlags(r2)
                    r0 = r22
                    org.json.JSONArray r2 = r4     // Catch:{ Exception -> 0x0195 }
                    int r2 = r2.length()     // Catch:{ Exception -> 0x0195 }
                    if (r2 <= 0) goto L_0x01aa
                    java.lang.String r2 = ""
                    r0 = r22
                    org.json.JSONArray r5 = r4     // Catch:{ Exception -> 0x0195 }
                    r6 = 0
                    java.lang.String r5 = r5.getString(r6)     // Catch:{ Exception -> 0x0195 }
                    boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x0195 }
                    if (r2 != 0) goto L_0x01aa
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this     // Catch:{ Exception -> 0x0195 }
                    java.lang.String r4 = r2.getDownloadDir()     // Catch:{ Exception -> 0x0195 }
                    if (r4 == 0) goto L_0x01a3
                    java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Exception -> 0x0195 }
                    r11.<init>()     // Catch:{ Exception -> 0x0195 }
                    r10 = 0
                    r7 = 0
                L_0x0078:
                    r0 = r22
                    org.json.JSONArray r2 = r4     // Catch:{ Exception -> 0x0195 }
                    int r2 = r2.length()     // Catch:{ Exception -> 0x0195 }
                    if (r7 >= r2) goto L_0x00dd
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this     // Catch:{ Exception -> 0x0195 }
                    r0 = r22
                    org.json.JSONArray r5 = r4     // Catch:{ Exception -> 0x0195 }
                    java.lang.String r5 = r5.getString(r7)     // Catch:{ Exception -> 0x0195 }
                    r0 = r22
                    java.lang.String r6 = r5     // Catch:{ Exception -> 0x0195 }
                    android.net.Uri r10 = r2.getFileUriAndSetType(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0195 }
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this     // Catch:{ Exception -> 0x0195 }
                    org.apache.cordova.CordovaWebView r2 = r2.webView     // Catch:{ Exception -> 0x0195 }
                    android.content.Context r2 = r2.getContext()     // Catch:{ Exception -> 0x0195 }
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0195 }
                    r5.<init>()     // Catch:{ Exception -> 0x0195 }
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r6 = nl.xservices.plugins.SocialSharing.this     // Catch:{ Exception -> 0x0195 }
                    org.apache.cordova.CordovaInterface r6 = r6.f5cordova     // Catch:{ Exception -> 0x0195 }
                    android.app.Activity r6 = r6.getActivity()     // Catch:{ Exception -> 0x0195 }
                    java.lang.String r6 = r6.getPackageName()     // Catch:{ Exception -> 0x0195 }
                    java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x0195 }
                    java.lang.String r6 = ".sharing.provider"
                    java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x0195 }
                    java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0195 }
                    java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x0195 }
                    java.lang.String r19 = r10.getPath()     // Catch:{ Exception -> 0x0195 }
                    r0 = r19
                    r6.<init>(r0)     // Catch:{ Exception -> 0x0195 }
                    android.net.Uri r10 = nl.xservices.plugins.FileProvider.getUriForFile(r2, r5, r6)     // Catch:{ Exception -> 0x0195 }
                    r11.add(r10)     // Catch:{ Exception -> 0x0195 }
                    int r7 = r7 + 1
                    goto L_0x0078
                L_0x00d6:
                    r12 = 0
                    goto L_0x0010
                L_0x00d9:
                    java.lang.String r2 = "android.intent.action.SEND"
                    goto L_0x0016
                L_0x00dd:
                    boolean r2 = r11.isEmpty()     // Catch:{ Exception -> 0x0195 }
                    if (r2 != 0) goto L_0x00ea
                    if (r12 == 0) goto L_0x018e
                    java.lang.String r2 = "android.intent.extra.STREAM"
                    r3.putExtra(r2, r11)     // Catch:{ Exception -> 0x0195 }
                L_0x00ea:
                    r0 = r22
                    java.lang.String r2 = r5
                    boolean r2 = nl.xservices.plugins.SocialSharing.notEmpty(r2)
                    if (r2 == 0) goto L_0x00fd
                    java.lang.String r2 = "android.intent.extra.SUBJECT"
                    r0 = r22
                    java.lang.String r5 = r5
                    r3.putExtra(r2, r5)
                L_0x00fd:
                    r0 = r22
                    java.lang.String r2 = r6
                    boolean r2 = nl.xservices.plugins.SocialSharing.notEmpty(r2)
                    if (r2 == 0) goto L_0x0128
                    boolean r2 = nl.xservices.plugins.SocialSharing.notEmpty(r14)
                    if (r2 == 0) goto L_0x01b1
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.StringBuilder r2 = r2.append(r14)
                    java.lang.String r5 = " "
                    java.lang.StringBuilder r2 = r2.append(r5)
                    r0 = r22
                    java.lang.String r5 = r6
                    java.lang.StringBuilder r2 = r2.append(r5)
                    java.lang.String r14 = r2.toString()
                L_0x0128:
                    boolean r2 = nl.xservices.plugins.SocialSharing.notEmpty(r14)
                    if (r2 == 0) goto L_0x013e
                    java.lang.String r2 = "android.intent.extra.TEXT"
                    r3.putExtra(r2, r14)
                    int r2 = android.os.Build.VERSION.SDK_INT
                    r5 = 21
                    if (r2 >= r5) goto L_0x013e
                    java.lang.String r2 = "sms_body"
                    r3.putExtra(r2, r14)
                L_0x013e:
                    r2 = 268435456(0x10000000, float:2.5243549E-29)
                    r3.addFlags(r2)
                    r0 = r22
                    java.lang.String r2 = r7
                    if (r2 == 0) goto L_0x0203
                    r0 = r22
                    java.lang.String r15 = r7
                    r16 = 0
                    java.lang.String r2 = "/"
                    boolean r2 = r15.contains(r2)
                    if (r2 == 0) goto L_0x0167
                    r0 = r22
                    java.lang.String r2 = r7
                    java.lang.String r5 = "/"
                    java.lang.String[] r13 = r2.split(r5)
                    r2 = 0
                    r15 = r13[r2]
                    r2 = 1
                    r16 = r13[r2]
                L_0x0167:
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this
                    r0 = r22
                    org.apache.cordova.CallbackContext r5 = r0.callbackContext
                    r0 = r22
                    java.lang.String r6 = r8
                    android.content.pm.ActivityInfo r8 = r2.getActivity(r5, r3, r15, r6)
                    if (r8 == 0) goto L_0x018d
                    r0 = r22
                    boolean r2 = r9
                    if (r2 == 0) goto L_0x01b7
                    r0 = r22
                    org.apache.cordova.CallbackContext r2 = r0.callbackContext
                    org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult
                    org.apache.cordova.PluginResult$Status r6 = org.apache.cordova.PluginResult.Status.OK
                    r5.<init>(r6)
                    r2.sendPluginResult(r5)
                L_0x018d:
                    return
                L_0x018e:
                    java.lang.String r2 = "android.intent.extra.STREAM"
                    r3.putExtra(r2, r10)     // Catch:{ Exception -> 0x0195 }
                    goto L_0x00ea
                L_0x0195:
                    r9 = move-exception
                    r0 = r22
                    org.apache.cordova.CallbackContext r2 = r0.callbackContext
                    java.lang.String r5 = r9.getMessage()
                    r2.error((java.lang.String) r5)
                    goto L_0x00ea
                L_0x01a3:
                    java.lang.String r2 = "text/plain"
                    r3.setType(r2)     // Catch:{ Exception -> 0x0195 }
                    goto L_0x00ea
                L_0x01aa:
                    java.lang.String r2 = "text/plain"
                    r3.setType(r2)     // Catch:{ Exception -> 0x0195 }
                    goto L_0x00ea
                L_0x01b1:
                    r0 = r22
                    java.lang.String r14 = r6
                    goto L_0x0128
                L_0x01b7:
                    java.lang.String r2 = "android.intent.category.LAUNCHER"
                    r3.addCategory(r2)
                    android.content.ComponentName r2 = new android.content.ComponentName
                    android.content.pm.ApplicationInfo r5 = r8.applicationInfo
                    java.lang.String r5 = r5.packageName
                    if (r16 == 0) goto L_0x01fe
                L_0x01c4:
                    r0 = r16
                    r2.<init>(r5, r0)
                    r3.setComponent(r2)
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this
                    org.apache.cordova.CordovaInterface r2 = r2.f5cordova
                    android.app.Activity r2 = r2.getActivity()
                    nl.xservices.plugins.SocialSharing$2$1 r5 = new nl.xservices.plugins.SocialSharing$2$1
                    r0 = r22
                    r5.<init>(r3)
                    r2.runOnUiThread(r5)
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this
                    java.lang.String r2 = r2.pasteMessage
                    if (r2 == 0) goto L_0x018d
                    java.util.Timer r2 = new java.util.Timer
                    r2.<init>()
                    nl.xservices.plugins.SocialSharing$2$2 r5 = new nl.xservices.plugins.SocialSharing$2$2
                    r0 = r22
                    r5.<init>()
                    r20 = 2000(0x7d0, double:9.88E-321)
                    r0 = r20
                    r2.schedule(r5, r0)
                    goto L_0x018d
                L_0x01fe:
                    java.lang.String r0 = r8.name
                    r16 = r0
                    goto L_0x01c4
                L_0x0203:
                    r0 = r22
                    boolean r2 = r9
                    if (r2 == 0) goto L_0x0219
                    r0 = r22
                    org.apache.cordova.CallbackContext r2 = r0.callbackContext
                    org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult
                    org.apache.cordova.PluginResult$Status r6 = org.apache.cordova.PluginResult.Status.OK
                    r5.<init>(r6)
                    r2.sendPluginResult(r5)
                    goto L_0x018d
                L_0x0219:
                    r0 = r22
                    nl.xservices.plugins.SocialSharing r2 = nl.xservices.plugins.SocialSharing.this
                    org.apache.cordova.CordovaInterface r2 = r2.f5cordova
                    android.app.Activity r2 = r2.getActivity()
                    nl.xservices.plugins.SocialSharing$2$3 r5 = new nl.xservices.plugins.SocialSharing$2$3
                    r0 = r22
                    r1 = r17
                    r5.<init>(r3, r1)
                    r2.runOnUiThread(r5)
                    goto L_0x018d
                */
                throw new UnsupportedOperationException("Method not decompiled: nl.xservices.plugins.SocialSharing.AnonymousClass2.run():void");
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void copyHintToClipboard(String msg, String label) {
        if (Build.VERSION.SDK_INT >= 11) {
            ((ClipboardManager) this.f5cordova.getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(label, msg));
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void showPasteMessage(String label) {
        if (Build.VERSION.SDK_INT >= 11) {
            Toast toast = Toast.makeText(this.webView.getContext(), label, 1);
            toast.setGravity(17, 0, 0);
            toast.show();
        }
    }

    /* access modifiers changed from: private */
    public Uri getFileUriAndSetType(Intent sendIntent, String dir, String image, String subject, int nthFile) throws IOException {
        String fileName;
        String localImage = image;
        if (image.endsWith("mp4") || image.endsWith("mov") || image.endsWith("3gp")) {
            sendIntent.setType("video/*");
        } else if (image.endsWith(HlsSegmentFormat.MP3)) {
            sendIntent.setType("audio/x-mpeg");
        } else {
            sendIntent.setType("image/*");
        }
        if (image.startsWith("http") || image.startsWith("www/")) {
            String filename = getFileName(image);
            localImage = "file://" + dir + "/" + filename.replaceAll("[^a-zA-Z0-9._-]", "");
            if (image.startsWith("http")) {
                URLConnection connection = new URL(image).openConnection();
                String disposition = connection.getHeaderField("Content-Disposition");
                if (disposition != null) {
                    Matcher matcher = Pattern.compile("filename=([^;]+)").matcher(disposition);
                    if (matcher.find()) {
                        filename = matcher.group(1).replaceAll("[^a-zA-Z0-9._-]", "");
                        if (filename.length() == 0) {
                            filename = "file";
                        }
                        localImage = "file://" + dir + "/" + filename;
                    }
                }
                saveFile(getBytes(connection.getInputStream()), dir, filename);
            } else {
                saveFile(getBytes(this.webView.getContext().getAssets().open(image)), dir, filename);
            }
        } else if (image.startsWith("data:")) {
            if (!image.contains(";base64,")) {
                sendIntent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                return null;
            }
            String encodedImg = image.substring(image.indexOf(";base64,") + 8);
            if (!image.contains("data:image/")) {
                sendIntent.setType(image.substring(image.indexOf("data:") + 5, image.indexOf(";base64")));
            }
            String imgExtension = image.substring(image.indexOf("/") + 1, image.indexOf(";base64"));
            if (notEmpty(subject)) {
                fileName = sanitizeFilename(subject) + (nthFile == 0 ? "" : "_" + nthFile) + "." + imgExtension;
            } else {
                fileName = "file" + (nthFile == 0 ? "" : "_" + nthFile) + "." + imgExtension;
            }
            saveFile(Base64.decode(encodedImg, 0), dir, fileName);
            localImage = "file://" + dir + "/" + fileName;
        } else if (image.startsWith("df:")) {
            if (!image.contains(";base64,")) {
                sendIntent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                return null;
            }
            String fileName2 = image.substring(image.indexOf("df:") + 3, image.indexOf(";data:"));
            String fileType = image.substring(image.indexOf(";data:") + 6, image.indexOf(";base64,"));
            String encodedImg2 = image.substring(image.indexOf(";base64,") + 8);
            sendIntent.setType(fileType);
            saveFile(Base64.decode(encodedImg2, 0), dir, sanitizeFilename(fileName2));
            localImage = "file://" + dir + "/" + sanitizeFilename(fileName2);
        } else if (!image.startsWith("file://")) {
            throw new IllegalArgumentException("URL_NOT_SUPPORTED");
        } else {
            sendIntent.setType(getMIMEType(image));
        }
        return Uri.parse(localImage);
    }

    private String getMIMEType(String fileName) {
        String fromMap;
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1 || (fromMap = MIME_Map.get(fileName.substring(dotIndex + 1, fileName.length()).toLowerCase())) == null) {
            return "*/*";
        }
        return fromMap;
    }

    static {
        MIME_Map.put("3gp", "video/3gpp");
        MIME_Map.put("apk", "application/vnd.android.package-archive");
        MIME_Map.put("asf", "video/x-ms-asf");
        MIME_Map.put("avi", "video/x-msvideo");
        MIME_Map.put("bin", "application/octet-stream");
        MIME_Map.put("bmp", "image/bmp");
        MIME_Map.put("c", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("class", "application/octet-stream");
        MIME_Map.put("conf", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("cpp", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("doc", "application/msword");
        MIME_Map.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        MIME_Map.put("xls", "application/vnd.ms-excel");
        MIME_Map.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        MIME_Map.put("exe", "application/octet-stream");
        MIME_Map.put("gif", "image/gif");
        MIME_Map.put("gtar", "application/x-gtar");
        MIME_Map.put("gz", "application/x-gzip");
        MIME_Map.put("h", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("htm", WebRequest.CONTENT_TYPE_HTML);
        MIME_Map.put("html", WebRequest.CONTENT_TYPE_HTML);
        MIME_Map.put("jar", "application/java-archive");
        MIME_Map.put("java", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("jpeg", "image/jpeg");
        MIME_Map.put("jpg", "image/*");
        MIME_Map.put("js", "application/x-javascript");
        MIME_Map.put("log", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("m3u", "audio/x-mpegurl");
        MIME_Map.put("m4a", "audio/mp4a-latm");
        MIME_Map.put("m4b", "audio/mp4a-latm");
        MIME_Map.put("m4p", "audio/mp4a-latm");
        MIME_Map.put("m4u", "video/vnd.mpegurl");
        MIME_Map.put("m4v", "video/x-m4v");
        MIME_Map.put("mov", "video/quicktime");
        MIME_Map.put("mp2", "audio/x-mpeg");
        MIME_Map.put(HlsSegmentFormat.MP3, "audio/x-mpeg");
        MIME_Map.put("mp4", "video/mp4");
        MIME_Map.put("mpc", "application/vnd.mpohun.certificate");
        MIME_Map.put("mpe", "video/mpeg");
        MIME_Map.put("mpeg", "video/mpeg");
        MIME_Map.put("mpg", "video/mpeg");
        MIME_Map.put("mpg4", "video/mp4");
        MIME_Map.put("mpga", "audio/mpeg");
        MIME_Map.put(NotificationCompat.CATEGORY_MESSAGE, "application/vnd.ms-outlook");
        MIME_Map.put("ogg", "audio/ogg");
        MIME_Map.put("pdf", "application/pdf");
        MIME_Map.put("png", "image/png");
        MIME_Map.put("pps", "application/vnd.ms-powerpoint");
        MIME_Map.put("ppt", "application/vnd.ms-powerpoint");
        MIME_Map.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        MIME_Map.put("prop", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("rc", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("rmvb", "audio/x-pn-realaudio");
        MIME_Map.put("rtf", "application/rtf");
        MIME_Map.put("sh", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("tar", "application/x-tar");
        MIME_Map.put("tgz", "application/x-compressed");
        MIME_Map.put("txt", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("wav", "audio/x-wav");
        MIME_Map.put("wma", "audio/x-ms-wma");
        MIME_Map.put("wmv", "audio/x-ms-wmv");
        MIME_Map.put("wps", "application/vnd.ms-works");
        MIME_Map.put("xml", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        MIME_Map.put("z", "application/x-compress");
        MIME_Map.put("zip", "application/x-zip-compressed");
        MIME_Map.put("", "*/*");
    }

    private boolean shareViaWhatsAppDirectly(CallbackContext callbackContext, String message, String subject, JSONArray files, String url, String number) {
        if (notEmpty(url)) {
            if (notEmpty(message)) {
                message = message + " " + url;
            } else {
                message = url;
            }
        }
        final String shareMessage = message;
        final String str = number;
        this.f5cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) {
            public void run() {
                final Intent intent = new Intent("android.intent.action.VIEW");
                try {
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + str + "&text=" + URLEncoder.encode(shareMessage, WebRequest.CHARSET_UTF_8)));
                    intent.addFlags(268435456);
                    SocialSharing.this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                SocialSharing.this.f5cordova.startActivityForResult(this, intent, 4);
                            } catch (Exception e) {
                                AnonymousClass3.this.callbackContext.error(e.getMessage());
                            }
                        }
                    });
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
            }
        });
        return true;
    }

    private boolean invokeSMSIntent(CallbackContext callbackContext, JSONObject options, String p_phonenumbers) {
        final String message = options.optString(PushConstants.MESSAGE);
        final String phonenumbers = getPhoneNumbersWithManufacturerSpecificSeparators(p_phonenumbers);
        this.f5cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext, (String) null, (String) null) {
            final /* synthetic */ String val$subject;

            {
                this.val$subject = r5;
            }

            public void run() {
                Intent intent;
                Uri fileUri;
                if (Build.VERSION.SDK_INT >= 19) {
                    intent = new Intent("android.intent.action.SENDTO");
                    intent.setData(Uri.parse("smsto:" + (SocialSharing.notEmpty(phonenumbers) ? phonenumbers : "")));
                } else {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setType("vnd.android-dir/mms-sms");
                    if (phonenumbers != null) {
                        intent.putExtra("address", phonenumbers);
                    }
                }
                intent.putExtra("sms_body", message);
                intent.putExtra("sms_subject", this.val$subject);
                try {
                    if (!(null == null || "".equals(null) || (fileUri = SocialSharing.this.getFileUriAndSetType(intent, SocialSharing.this.getDownloadDir(), null, this.val$subject, 0)) == null)) {
                        intent.putExtra("android.intent.extra.STREAM", fileUri);
                    }
                    intent.addFlags(268435456);
                    SocialSharing.this.f5cordova.startActivityForResult(this, intent, 0);
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
            }
        });
        return true;
    }

    private static String getPhoneNumbersWithManufacturerSpecificSeparators(String phonenumbers) {
        char separator;
        if (!notEmpty(phonenumbers)) {
            return null;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
            separator = ',';
        } else {
            separator = ';';
        }
        return phonenumbers.replace(';', separator).replace(',', separator);
    }

    /* access modifiers changed from: private */
    public ActivityInfo getActivity(CallbackContext callbackContext, Intent shareIntent, String appPackageName, String appName) {
        List<ResolveInfo> activityList = this.webView.getContext().getPackageManager().queryIntentActivities(shareIntent, 0);
        for (ResolveInfo app : activityList) {
            if (app.activityInfo.packageName.contains(appPackageName) && (appName == null || app.activityInfo.name.contains(appName))) {
                return app.activityInfo;
            }
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, getShareActivities(activityList)));
        return null;
    }

    private JSONArray getShareActivities(List<ResolveInfo> activityList) {
        List<String> packages = new ArrayList<>();
        for (ResolveInfo app : activityList) {
            packages.add(app.activityInfo.packageName);
        }
        return new JSONArray(packages);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        boolean z = true;
        super.onActivityResult(requestCode, resultCode, intent);
        if (this._callbackContext != null) {
            switch (requestCode) {
                case 1:
                    CallbackContext callbackContext = this._callbackContext;
                    PluginResult.Status status = PluginResult.Status.OK;
                    if (resultCode != -1) {
                        z = false;
                    }
                    callbackContext.sendPluginResult(new PluginResult(status, z));
                    return;
                case 2:
                    JSONObject json = new JSONObject();
                    if (resultCode != -1) {
                        z = false;
                    }
                    try {
                        json.put("completed", z);
                        json.put("app", ShareChooserPendingIntent.chosenComponent != null ? ShareChooserPendingIntent.chosenComponent : "");
                        this._callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, json));
                        return;
                    } catch (JSONException e) {
                        this._callbackContext.error(e.getMessage());
                        return;
                    }
                default:
                    this._callbackContext.success();
                    return;
            }
        }
    }

    private void createOrCleanDir(String downloadDir) throws IOException {
        File dir = new File(downloadDir);
        if (dir.exists()) {
            cleanupOldFiles(dir);
        } else if (!dir.mkdirs()) {
            throw new IOException("CREATE_DIRS_FAILED");
        }
    }

    private static String getFileName(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        Matcher m = Pattern.compile(".*/([^?#]+)?").matcher(url);
        if (m.find()) {
            return m.group(1);
        }
        return "file";
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[16384];
        while (true) {
            int nRead = is.read(data, 0, data.length);
            if (nRead != -1) {
                buffer.write(data, 0, nRead);
            } else {
                buffer.flush();
                return buffer.toByteArray();
            }
        }
    }

    private void saveFile(byte[] bytes, String dirName, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(new File(dirName), fileName));
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

    private void cleanupOldFiles(File dir) {
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }

    /* access modifiers changed from: private */
    public static boolean notEmpty(String what) {
        return what != null && !"".equals(what) && !"null".equalsIgnoreCase(what);
    }

    /* access modifiers changed from: private */
    public static String[] toStringArray(JSONArray jsonArray) throws JSONException {
        String[] result = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = jsonArray.getString(i);
        }
        return result;
    }

    public static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*?|<> ]", "_");
    }
}
