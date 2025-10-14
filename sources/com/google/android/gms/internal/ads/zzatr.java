package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
final class zzatr implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private final String packageName;
    @VisibleForTesting
    private zzats zzdgz;
    private final String zzdha;
    private final LinkedBlockingQueue<zzba> zzdhb;
    private final HandlerThread zzdhc = new HandlerThread("GassClient");

    public zzatr(Context context, String str, String str2) {
        this.packageName = str;
        this.zzdha = str2;
        this.zzdhc.start();
        this.zzdgz = new zzats(context, this.zzdhc.getLooper(), this, this);
        this.zzdhb = new LinkedBlockingQueue<>();
        this.zzdgz.checkAvailabilityAndConnect();
    }

    private final void zznz() {
        if (this.zzdgz == null) {
            return;
        }
        if (this.zzdgz.isConnected() || this.zzdgz.isConnecting()) {
            this.zzdgz.disconnect();
        }
    }

    private final zzatx zzwb() {
        try {
            return this.zzdgz.zzwd();
        } catch (DeadObjectException | IllegalStateException e) {
            return null;
        }
    }

    @VisibleForTesting
    private static zzba zzwc() {
        zzba zzba = new zzba();
        zzba.zzdu = Long.valueOf(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID);
        return zzba;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0039, code lost:
        zznz();
        r4.zzdhc.quit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0038 A[ExcHandler:  FINALLY, Splitter:B:2:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r5) {
        /*
            r4 = this;
            com.google.android.gms.internal.ads.zzatx r0 = r4.zzwb()
            if (r0 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzatt r1 = new com.google.android.gms.internal.ads.zzatt     // Catch:{ Throwable -> 0x0025, all -> 0x0038 }
            java.lang.String r2 = r4.packageName     // Catch:{ Throwable -> 0x0025, all -> 0x0038 }
            java.lang.String r3 = r4.zzdha     // Catch:{ Throwable -> 0x0025, all -> 0x0038 }
            r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x0025, all -> 0x0038 }
            com.google.android.gms.internal.ads.zzatv r0 = r0.zza(r1)     // Catch:{ Throwable -> 0x0025, all -> 0x0038 }
            com.google.android.gms.internal.ads.zzba r0 = r0.zzwe()     // Catch:{ Throwable -> 0x0025, all -> 0x0038 }
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.ads.zzba> r1 = r4.zzdhb     // Catch:{ Throwable -> 0x0025, all -> 0x0038 }
            r1.put(r0)     // Catch:{ Throwable -> 0x0025, all -> 0x0038 }
            r4.zznz()
            android.os.HandlerThread r0 = r4.zzdhc
            r0.quit()
        L_0x0024:
            return
        L_0x0025:
            r0 = move-exception
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.ads.zzba> r0 = r4.zzdhb     // Catch:{ InterruptedException -> 0x0042, all -> 0x0038 }
            com.google.android.gms.internal.ads.zzba r1 = zzwc()     // Catch:{ InterruptedException -> 0x0042, all -> 0x0038 }
            r0.put(r1)     // Catch:{ InterruptedException -> 0x0042, all -> 0x0038 }
        L_0x002f:
            r4.zznz()
            android.os.HandlerThread r0 = r4.zzdhc
            r0.quit()
            goto L_0x0024
        L_0x0038:
            r0 = move-exception
            r4.zznz()
            android.os.HandlerThread r1 = r4.zzdhc
            r1.quit()
            throw r0
        L_0x0042:
            r0 = move-exception
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatr.onConnected(android.os.Bundle):void");
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            this.zzdhb.put(zzwc());
        } catch (InterruptedException e) {
        }
    }

    public final void onConnectionSuspended(int i) {
        try {
            this.zzdhb.put(zzwc());
        } catch (InterruptedException e) {
        }
    }

    public final zzba zzak(int i) {
        zzba zzba;
        try {
            zzba = this.zzdhb.poll(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzba = null;
        }
        return zzba == null ? zzwc() : zzba;
    }
}
