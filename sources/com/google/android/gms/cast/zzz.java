package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;

final class zzz implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;
    private final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzcm;

    zzz(CastRemoteDisplayLocalService castRemoteDisplayLocalService, CastRemoteDisplayLocalService.NotificationSettings notificationSettings) {
        this.zzci = castRemoteDisplayLocalService;
        this.zzcm = notificationSettings;
    }

    public final void run() {
        this.zzci.zza(this.zzcm);
    }
}
