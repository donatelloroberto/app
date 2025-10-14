package nl.xservices.plugins;

import android.content.Intent;
import android.net.Uri;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Locale;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class LaunchMyApp extends CordovaPlugin {
    private static final String ACTION_CHECKINTENT = "checkIntent";
    private static final String ACTION_CLEARINTENT = "clearIntent";
    private static final String ACTION_GETLASTINTENT = "getLastIntent";
    private String lastIntentString = null;
    private boolean resetIntent;

    public void initialize(CordovaInterface cordova2, CordovaWebView webView) {
        boolean z = false;
        if (this.preferences.getBoolean("resetIntent", false) || this.preferences.getBoolean("CustomURLSchemePluginClearsAndroidIntent", false)) {
            z = true;
        }
        this.resetIntent = z;
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (ACTION_CLEARINTENT.equalsIgnoreCase(action)) {
            Intent intent = ((CordovaActivity) this.webView.getContext()).getIntent();
            if (this.resetIntent) {
                intent.setData((Uri) null);
            }
            return true;
        } else if (ACTION_CHECKINTENT.equalsIgnoreCase(action)) {
            Intent intent2 = ((CordovaActivity) this.webView.getContext()).getIntent();
            String intentString = intent2.getDataString();
            if (intentString == null || intent2.getScheme() == null) {
                callbackContext.error("App was not started via the launchmyapp URL scheme. Ignoring this errorcallback is the best approach.");
            } else {
                this.lastIntentString = intentString;
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, intent2.getDataString()));
            }
            return true;
        } else if (ACTION_GETLASTINTENT.equalsIgnoreCase(action)) {
            if (this.lastIntentString != null) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, this.lastIntentString));
            } else {
                callbackContext.error("No intent received so far.");
            }
            return true;
        } else {
            callbackContext.error("This plugin only responds to the checkIntent action.");
            return false;
        }
    }

    public void onNewIntent(Intent intent) {
        String intentString = intent.getDataString();
        if (intentString != null && intent.getScheme() != null) {
            if (this.resetIntent) {
                intent.setData((Uri) null);
            }
            try {
                StringWriter writer = new StringWriter(intentString.length() * 2);
                escapeJavaStyleString(writer, intentString, true, false);
                this.webView.loadUrl("javascript:handleOpenURL('" + URLEncoder.encode(writer.toString()) + "');");
            } catch (IOException e) {
            }
        }
    }

    private static void escapeJavaStyleString(Writer out, String str, boolean escapeSingleQuote, boolean escapeForwardSlash) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        } else if (str != null) {
            int sz = str.length();
            for (int i = 0; i < sz; i++) {
                char ch = str.charAt(i);
                if (ch <= 4095) {
                    if (ch <= 255) {
                        if (ch <= 127) {
                            if (ch >= ' ') {
                                switch (ch) {
                                    case '\"':
                                        out.write(92);
                                        out.write(34);
                                        break;
                                    case '\'':
                                        if (escapeSingleQuote) {
                                            out.write(92);
                                        }
                                        out.write(39);
                                        break;
                                    case '/':
                                        if (escapeForwardSlash) {
                                            out.write(92);
                                        }
                                        out.write(47);
                                        break;
                                    case '\\':
                                        out.write(92);
                                        out.write(92);
                                        break;
                                    default:
                                        out.write(ch);
                                        break;
                                }
                            } else {
                                switch (ch) {
                                    case 8:
                                        out.write(92);
                                        out.write(98);
                                        break;
                                    case 9:
                                        out.write(92);
                                        out.write(116);
                                        break;
                                    case 10:
                                        out.write(92);
                                        out.write(110);
                                        break;
                                    case 12:
                                        out.write(92);
                                        out.write(102);
                                        break;
                                    case 13:
                                        out.write(92);
                                        out.write(114);
                                        break;
                                    default:
                                        if (ch <= 15) {
                                            out.write("\\u000" + hex(ch));
                                            break;
                                        } else {
                                            out.write("\\u00" + hex(ch));
                                            break;
                                        }
                                }
                            }
                        } else {
                            out.write("\\u00" + hex(ch));
                        }
                    } else {
                        out.write("\\u0" + hex(ch));
                    }
                } else {
                    out.write("\\u" + hex(ch));
                }
            }
        }
    }

    private static String hex(char ch) {
        return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
    }
}
