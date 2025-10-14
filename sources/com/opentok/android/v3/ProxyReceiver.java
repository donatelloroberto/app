package com.opentok.android.v3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;

class ProxyReceiver extends BroadcastReceiver {
    private Context applicaitonCtx;

    private static native void registerNatives();

    private static native void setProxyNative(String str, int i);

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
            updateProxyInfo();
        }
    }

    protected ProxyReceiver(Context ctx) {
        updateProxyInfo();
        ctx.getApplicationContext().registerReceiver(this, new IntentFilter("android.intent.action.PROXY_CHANGE"));
        this.applicaitonCtx = ctx.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public void close() {
        this.applicaitonCtx.unregisterReceiver(this);
    }

    private void updateProxyInfo() {
        for (Proxy proxy : ProxySelector.getDefault().select(URI.create(BuildConfig.API_URL))) {
            try {
                InetSocketAddress inetAddress = (InetSocketAddress) proxy.address();
                if (!(inetAddress == null || inetAddress.getHostName() == null)) {
                    setProxyNative(inetAddress.getHostName(), inetAddress.getPort());
                    return;
                }
            } catch (ClassCastException e) {
            }
        }
    }

    static {
        registerNatives();
    }
}
