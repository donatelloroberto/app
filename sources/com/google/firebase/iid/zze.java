package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

final /* synthetic */ class zze implements Runnable {
    private final zzd zzx;
    private final Intent zzy;

    zze(zzd zzd, Intent intent) {
        this.zzx = zzd;
        this.zzy = intent;
    }

    public final void run() {
        zzd zzd = this.zzx;
        String action = this.zzy.getAction();
        Log.w("EnhancedIntentService", new StringBuilder(String.valueOf(action).length() + 61).append("Service took too long to process intent: ").append(action).append(" App may get closed.").toString());
        zzd.finish();
    }
}
