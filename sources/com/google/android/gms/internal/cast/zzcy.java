package com.google.android.gms.internal.cast;

final class zzcy implements Runnable {
    private final /* synthetic */ zzct zzzm;
    private final /* synthetic */ int zzzo;

    zzcy(zzcv zzcv, zzct zzct, int i) {
        this.zzzm = zzct;
        this.zzzo = i;
    }

    public final void run() {
        this.zzzm.zzak.onApplicationDisconnected(this.zzzo);
    }
}
