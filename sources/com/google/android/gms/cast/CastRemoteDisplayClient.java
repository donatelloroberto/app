package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.cast.zzdl;
import com.google.android.gms.internal.cast.zzdo;
import com.google.android.gms.internal.cast.zzei;
import com.google.android.gms.internal.cast.zzem;
import com.google.android.gms.tasks.Task;

@TargetApi(19)
public class CastRemoteDisplayClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API = new Api<>("CastRemoteDisplay.API", zzad, zzdl.zzzv);
    private static final Api.AbstractClientBuilder<zzei, Api.ApiOptions.NoOptions> zzad = new zzp();
    /* access modifiers changed from: private */
    public final zzdo zzbf = new zzdo("CastRemoteDisplay");
    /* access modifiers changed from: private */
    public VirtualDisplay zzbg;

    private static class zza extends zzem {
        private zza() {
        }

        public void zza(int i, int i2, Surface surface) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void onError(int i) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void onDisconnected() throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void zzd() throws RemoteException {
            throw new UnsupportedOperationException();
        }

        /* synthetic */ zza(zzp zzp) {
            this();
        }
    }

    CastRemoteDisplayClient(@NonNull Context context) {
        super(context, API, null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Display> startRemoteDisplay(@NonNull CastDevice castDevice, @NonNull String str, @CastRemoteDisplay.Configuration int i, @Nullable PendingIntent pendingIntent) {
        return doWrite(new zzr(this, i, pendingIntent, castDevice, str));
    }

    /* access modifiers changed from: private */
    public static int zza(int i, int i2) {
        return (Math.min(i, i2) * 320) / 1080;
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public final void zzc() {
        if (this.zzbg != null) {
            if (this.zzbg.getDisplay() != null) {
                this.zzbf.d(new StringBuilder(38).append("releasing virtual display: ").append(this.zzbg.getDisplay().getDisplayId()).toString(), new Object[0]);
            }
            this.zzbg.release();
            this.zzbg = null;
        }
    }

    public Task<Void> stopRemoteDisplay() {
        return doWrite(new zzt(this));
    }
}
