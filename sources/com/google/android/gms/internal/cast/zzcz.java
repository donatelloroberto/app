package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;

final class zzcz implements Runnable {
    private final /* synthetic */ String zzae;
    private final /* synthetic */ zzct zzzm;
    private final /* synthetic */ String zzzp;

    zzcz(zzcv zzcv, zzct zzct, String str, String str2) {
        this.zzzm = zzct;
        this.zzae = str;
        this.zzzp = str2;
    }

    public final void run() {
        Cast.MessageReceivedCallback messageReceivedCallback;
        synchronized (this.zzzm.zzyk) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) this.zzzm.zzyk.get(this.zzae);
        }
        if (messageReceivedCallback != null) {
            messageReceivedCallback.onMessageReceived(this.zzzm.zzit, this.zzae, this.zzzp);
            return;
        }
        zzct.zzbf.d("Discarded message for unknown namespace '%s'", this.zzae);
    }
}
