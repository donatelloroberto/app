package com.phonegap.plugins.videoplayer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class VideoPlayer extends CordovaPlugin {
    private static final String ASSETS = "file:///android_asset/";
    private static final String YOU_TUBE = "youtube.com";

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        PluginResult.Status status = PluginResult.Status.OK;
        try {
            if (action.equals("playVideo")) {
                playVideo(args.getString(0));
            } else {
                status = PluginResult.Status.INVALID_ACTION;
            }
            callbackContext.sendPluginResult(new PluginResult(status, ""));
            return true;
        } catch (JSONException e) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
            return true;
        } catch (IOException e2) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION));
            return true;
        }
    }

    private void playVideo(String url) throws IOException {
        Intent intent;
        if (url.contains("bit.ly/") || url.contains("goo.gl/") || url.contains("tinyurl.com/") || url.contains("youtu.be/")) {
            URLConnection con = new URL(url).openConnection();
            con.connect();
            InputStream is = con.getInputStream();
            url = con.getURL().toString();
            is.close();
        }
        Uri uri = Uri.parse(url);
        if (url.contains(YOU_TUBE)) {
            Uri uri2 = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));
            if (isYouTubeInstalled()) {
                intent = new Intent("android.intent.action.VIEW", uri2);
            } else {
                intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("market://details?id=com.google.android.youtube"));
            }
        } else if (url.contains(ASSETS)) {
            String filepath = url.replace(ASSETS, "");
            String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
            if (!new File(this.f5cordova.getActivity().getFilesDir() + "/" + filename).exists()) {
                copy(filepath, filename);
            }
            Uri uri3 = Uri.parse("file://" + this.f5cordova.getActivity().getFilesDir() + "/" + filename);
            intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uri3, "video/*");
        } else {
            intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uri, "video/*");
        }
        this.f5cordova.getActivity().startActivity(intent);
    }

    private void copy(String fileFrom, String fileTo) throws IOException {
        InputStream in = this.f5cordova.getActivity().getAssets().open(fileFrom);
        FileOutputStream out = this.f5cordova.getActivity().openFileOutput(fileTo, 1);
        byte[] buf = new byte[1024];
        while (true) {
            int len = in.read(buf);
            if (len > 0) {
                out.write(buf, 0, len);
            } else {
                in.close();
                out.close();
                return;
            }
        }
    }

    private boolean isYouTubeInstalled() {
        try {
            this.f5cordova.getActivity().getPackageManager().getPackageInfo("com.google.android.youtube", 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
