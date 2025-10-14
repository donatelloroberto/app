package com.google.android.gms.cast;

final class zzu implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzu(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    public final void run() {
        this.zzci.zzb(new StringBuilder(59).append("onCreate after delay. The local service been started: ").append(this.zzci.zzcd).toString());
        if (!this.zzci.zzcd) {
            this.zzci.zzc("The local service has not been been started, stopping it");
            this.zzci.stopSelf();
        }
    }
}
