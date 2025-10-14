package com.google.android.gms.internal.cast;

final class zzcx implements Runnable {
    private final /* synthetic */ zzct zzzm;
    private final /* synthetic */ zzdb zzzn;

    zzcx(zzcv zzcv, zzct zzct, zzdb zzdb) {
        this.zzzm = zzct;
        this.zzzn = zzdb;
    }

    public final void run() {
        this.zzzm.zza(this.zzzn);
    }
}
