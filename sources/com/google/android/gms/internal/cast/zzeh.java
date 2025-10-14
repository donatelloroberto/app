package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

@Deprecated
public final class zzeh extends GmsClient<zzel> implements IBinder.DeathRecipient {
    /* access modifiers changed from: private */
    public static final zzdo zzbf = new zzdo("CastRemoteDisplayClientImpl");
    /* access modifiers changed from: private */
    public CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzabr;
    private Bundle zzabs;
    private CastDevice zzaj;

    public zzeh(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, Bundle bundle, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 83, clientSettings, connectionCallbacks, onConnectionFailedListener);
        zzbf.d("instance created", new Object[0]);
        this.zzabr = castRemoteDisplaySessionCallbacks;
        this.zzaj = castDevice;
        this.zzabs = bundle;
    }

    public final void disconnect() {
        zzbf.d("disconnect", new Object[0]);
        this.zzabr = null;
        this.zzaj = null;
        try {
            ((zzel) getService()).disconnect();
        } catch (RemoteException | IllegalStateException e) {
        } finally {
            super.disconnect();
        }
    }

    public final void zza(zzej zzej, zzen zzen, String str) throws RemoteException {
        zzbf.d("startRemoteDisplay", new Object[0]);
        zzej zzej2 = zzej;
        ((zzel) getService()).zza(zzej2, (zzen) new zzek(this, zzen), this.zzaj.getDeviceId(), str, this.zzabs);
    }

    public final void zza(zzej zzej) throws RemoteException {
        zzbf.d("stopRemoteDisplay", new Object[0]);
        ((zzel) getService()).zza(zzej);
    }

    public final void binderDied() {
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.remote_display.service.START";
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        if (queryLocalInterface instanceof zzel) {
            return (zzel) queryLocalInterface;
        }
        return new zzeo(iBinder);
    }
}
