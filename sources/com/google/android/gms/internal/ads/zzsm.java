package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzsm implements zzm {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("mLock")
    public zzsf zzbnl;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zzbnm;

    public zzsm(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzbnl != null) {
                this.zzbnl.disconnect();
                this.zzbnl = null;
                Binder.flushPendingCommands();
            }
        }
    }

    private final Future<ParcelFileDescriptor> zzb(zzsg zzsg) {
        zzsn zzsn = new zzsn(this);
        zzso zzso = new zzso(this, zzsn, zzsg);
        zzsr zzsr = new zzsr(this, zzsn);
        synchronized (this.mLock) {
            this.zzbnl = new zzsf(this.mContext, zzbv.zzez().zzsa(), zzso, zzsr);
            this.zzbnl.checkAvailabilityAndConnect();
        }
        return zzsn;
    }

    /* JADX INFO: finally extract failed */
    public final zzp zzc(zzr<?> zzr) throws zzae {
        zzp zzp;
        zzsg zzh = zzsg.zzh(zzr);
        long intValue = (long) ((Integer) zzkb.zzik().zzd(zznk.zzbdx)).intValue();
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        try {
            zzsi zzsi = (zzsi) new zzaev(zzb(zzh).get(intValue, TimeUnit.MILLISECONDS)).zza(zzsi.CREATOR);
            if (zzsi.zzbnj) {
                throw new zzae(zzsi.zzbnk);
            }
            if (zzsi.zzbnh.length != zzsi.zzbni.length) {
                zzp = null;
            } else {
                HashMap hashMap = new HashMap();
                for (int i = 0; i < zzsi.zzbnh.length; i++) {
                    hashMap.put(zzsi.zzbnh[i], zzsi.zzbni[i]);
                }
                zzp = new zzp(zzsi.statusCode, zzsi.data, (Map<String, String>) hashMap, zzsi.zzac, zzsi.zzad);
            }
            zzakb.v(new StringBuilder(52).append("Http assets remote cache took ").append(zzbv.zzer().elapsedRealtime() - elapsedRealtime).append("ms").toString());
            return zzp;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzakb.v(new StringBuilder(52).append("Http assets remote cache took ").append(zzbv.zzer().elapsedRealtime() - elapsedRealtime).append("ms").toString());
            return null;
        } catch (Throwable th) {
            zzakb.v(new StringBuilder(52).append("Http assets remote cache took ").append(zzbv.zzer().elapsedRealtime() - elapsedRealtime).append("ms").toString());
            throw th;
        }
    }
}
