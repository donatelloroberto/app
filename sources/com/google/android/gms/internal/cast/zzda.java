package com.google.android.gms.internal.cast;

final class zzda implements Runnable {
    private final /* synthetic */ zzct zzzm;
    private final /* synthetic */ zzcj zzzq;

    zzda(zzcv zzcv, zzct zzct, zzcj zzcj) {
        this.zzzm = zzct;
        this.zzzq = zzcj;
    }

    public final void run() {
        this.zzzm.zza(this.zzzq);
    }
}
