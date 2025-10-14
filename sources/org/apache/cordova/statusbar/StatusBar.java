package org.apache.cordova.statusbar;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import com.adobe.phonegap.push.PushConstants;
import java.util.Arrays;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONException;

public class StatusBar extends CordovaPlugin {
    private static final String TAG = "StatusBar";

    public void initialize(final CordovaInterface cordova2, CordovaWebView webView) {
        LOG.v(TAG, "StatusBar: initialization");
        super.initialize(cordova2, webView);
        this.f5cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                cordova2.getActivity().getWindow().clearFlags(2048);
                StatusBar.this.setStatusBarBackgroundColor(StatusBar.this.preferences.getString("StatusBarBackgroundColor", "#000000"));
                StatusBar.this.setStatusBarStyle(StatusBar.this.preferences.getString("StatusBarStyle", "lightcontent"));
            }
        });
    }

    public boolean execute(String action, final CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        boolean statusBarVisible = false;
        LOG.v(TAG, "Executing action: " + action);
        final Window window = this.f5cordova.getActivity().getWindow();
        if ("_ready".equals(action)) {
            if ((window.getAttributes().flags & 1024) == 0) {
                statusBarVisible = true;
            }
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, statusBarVisible));
            return true;
        } else if ("show".equals(action)) {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (Build.VERSION.SDK_INT >= 19) {
                        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -1025 & -5);
                    }
                    window.clearFlags(1024);
                }
            });
            return true;
        } else if ("hide".equals(action)) {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (Build.VERSION.SDK_INT >= 19) {
                        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 1024 | 4);
                    }
                    window.addFlags(1024);
                }
            });
            return true;
        } else if ("backgroundColorByHexString".equals(action)) {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        StatusBar.this.setStatusBarBackgroundColor(args.getString(0));
                    } catch (JSONException e) {
                        LOG.e(StatusBar.TAG, "Invalid hexString argument, use f.i. '#777777'");
                    }
                }
            });
            return true;
        } else if ("overlaysWebView".equals(action)) {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            StatusBar.this.setStatusBarTransparent(args.getBoolean(0));
                        } catch (JSONException e) {
                            LOG.e(StatusBar.TAG, "Invalid boolean argument");
                        }
                    }
                });
                return true;
            } else if (args.getBoolean(0)) {
                return false;
            } else {
                return true;
            }
        } else if ("styleDefault".equals(action)) {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    StatusBar.this.setStatusBarStyle(PushConstants.SOUND_DEFAULT);
                }
            });
            return true;
        } else if ("styleLightContent".equals(action)) {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    StatusBar.this.setStatusBarStyle("lightcontent");
                }
            });
            return true;
        } else if ("styleBlackTranslucent".equals(action)) {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    StatusBar.this.setStatusBarStyle("blacktranslucent");
                }
            });
            return true;
        } else if (!"styleBlackOpaque".equals(action)) {
            return false;
        } else {
            this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    StatusBar.this.setStatusBarStyle("blackopaque");
                }
            });
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void setStatusBarBackgroundColor(String colorPref) {
        if (Build.VERSION.SDK_INT >= 21 && colorPref != null && !colorPref.isEmpty()) {
            Window window = this.f5cordova.getActivity().getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            try {
                window.getClass().getMethod("setStatusBarColor", new Class[]{Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(Color.parseColor(colorPref))});
            } catch (IllegalArgumentException e) {
                LOG.e(TAG, "Invalid hexString argument, use f.i. '#999999'");
            } catch (Exception e2) {
                LOG.w(TAG, "Method window.setStatusBarColor not found for SDK level " + Build.VERSION.SDK_INT);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setStatusBarTransparent(boolean transparent) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.f5cordova.getActivity().getWindow();
            if (transparent) {
                window.getDecorView().setSystemUiVisibility(1280);
                window.setStatusBarColor(0);
                return;
            }
            window.getDecorView().setSystemUiVisibility(256);
        }
    }

    /* access modifiers changed from: private */
    public void setStatusBarStyle(String style) {
        if (Build.VERSION.SDK_INT >= 23 && style != null && !style.isEmpty()) {
            View decorView = this.f5cordova.getActivity().getWindow().getDecorView();
            int uiOptions = decorView.getSystemUiVisibility();
            String[] lightContentStyles = {"lightcontent", "blacktranslucent", "blackopaque"};
            if (Arrays.asList(new String[]{PushConstants.SOUND_DEFAULT}).contains(style.toLowerCase())) {
                decorView.setSystemUiVisibility(uiOptions | 8192);
            } else if (Arrays.asList(lightContentStyles).contains(style.toLowerCase())) {
                decorView.setSystemUiVisibility(uiOptions & -8193);
            } else {
                LOG.e(TAG, "Invalid style, must be either 'default', 'lightcontent' or the deprecated 'blacktranslucent' and 'blackopaque'");
            }
        }
    }
}
