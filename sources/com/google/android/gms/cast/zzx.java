package com.google.android.gms.cast;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;

final class zzx implements ServiceConnection {
    private final /* synthetic */ String zzag;
    private final /* synthetic */ CastDevice zzck;
    private final /* synthetic */ CastRemoteDisplayLocalService.Options zzcl;
    private final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzcm;
    private final /* synthetic */ Context zzcn;
    private final /* synthetic */ CastRemoteDisplayLocalService.Callbacks zzco;

    zzx(String str, CastDevice castDevice, CastRemoteDisplayLocalService.Options options, CastRemoteDisplayLocalService.NotificationSettings notificationSettings, Context context, CastRemoteDisplayLocalService.Callbacks callbacks) {
        this.zzag = str;
        this.zzck = castDevice;
        this.zzcl = options;
        this.zzcm = notificationSettings;
        this.zzcn = context;
        this.zzco = callbacks;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = CastRemoteDisplayLocalService.this;
        if (castRemoteDisplayLocalService != null) {
            if (castRemoteDisplayLocalService.zza(this.zzag, this.zzck, this.zzcl, this.zzcm, this.zzcn, this, this.zzco)) {
                return;
            }
        }
        CastRemoteDisplayLocalService.zzbf.e("Connected but unable to get the service instance", new Object[0]);
        this.zzco.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        CastRemoteDisplayLocalService.zzbr.set(false);
        try {
            this.zzcn.unbindService(this);
        } catch (IllegalArgumentException e) {
            CastRemoteDisplayLocalService.zzbf.d("No need to unbind service, already unbound", new Object[0]);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        CastRemoteDisplayLocalService.zzbf.d("onServiceDisconnected", new Object[0]);
        this.zzco.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_DISCONNECTED, "Service Disconnected"));
        CastRemoteDisplayLocalService.zzbr.set(false);
        try {
            this.zzcn.unbindService(this);
        } catch (IllegalArgumentException e) {
            CastRemoteDisplayLocalService.zzbf.d("No need to unbind service, already unbound", new Object[0]);
        }
    }
}
