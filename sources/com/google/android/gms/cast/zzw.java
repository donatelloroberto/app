package com.google.android.gms.cast;

final class zzw implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;
    private final /* synthetic */ boolean zzcj;

    zzw(CastRemoteDisplayLocalService castRemoteDisplayLocalService, boolean z) {
        this.zzci = castRemoteDisplayLocalService;
        this.zzcj = z;
    }

    public final void run() {
        this.zzci.zza(this.zzcj);
    }
}
