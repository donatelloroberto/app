package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated
public final class zzdx implements CastRemoteDisplayApi {
    /* access modifiers changed from: private */
    public static final zzdo zzbf = new zzdo("CastRemoteDisplayApiImpl");
    /* access modifiers changed from: private */
    public Api<?> zzabj;
    /* access modifiers changed from: private */
    public final zzen zzabk = new zzea(this);
    /* access modifiers changed from: private */
    public VirtualDisplay zzbg;

    public zzdx(Api api) {
        this.zzabj = api;
    }

    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> startRemoteDisplay(GoogleApiClient googleApiClient, String str) {
        zzbf.d("startRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzdz(this, googleApiClient, str));
    }

    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> stopRemoteDisplay(GoogleApiClient googleApiClient) {
        zzbf.d("stopRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzec(this, googleApiClient));
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public final void zzc() {
        if (this.zzbg != null) {
            if (this.zzbg.getDisplay() != null) {
                zzbf.d(new StringBuilder(38).append("releasing virtual display: ").append(this.zzbg.getDisplay().getDisplayId()).toString(), new Object[0]);
            }
            this.zzbg.release();
            this.zzbg = null;
        }
    }
}
