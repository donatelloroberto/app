package com.google.android.gms.internal.cast;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
final class zzcv extends zzdk {
    private final Handler handler;
    private final AtomicReference<zzct> zzzh;

    public zzcv(zzct zzct) {
        this.zzzh = new AtomicReference<>(zzct);
        this.handler = new zzep(zzct.getLooper());
    }

    public final zzct zzeh() {
        zzct andSet = this.zzzh.getAndSet((Object) null);
        if (andSet == null) {
            return null;
        }
        andSet.zzeb();
        return andSet;
    }

    public final boolean isDisposed() {
        return this.zzzh.get() == null;
    }

    public final void zzu(int i) {
        zzct zzeh = zzeh();
        if (zzeh != null) {
            zzct.zzbf.d("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i));
            if (i != 0) {
                zzeh.triggerConnectionSuspended(2);
            }
        }
    }

    public final void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            ApplicationMetadata unused = zzct.zzyj = applicationMetadata;
            String unused2 = zzct.zzyv = applicationMetadata.getApplicationId();
            String unused3 = zzct.zzyw = str2;
            String unused4 = zzct.zzyn = str;
            synchronized (zzct.zzzc) {
                if (zzct.zzza != null) {
                    zzct.zzza.setResult(new zzcw(new Status(0), applicationMetadata, str, str2, z));
                    BaseImplementation.ResultHolder unused5 = zzct.zzza = null;
                }
            }
        }
    }

    public final void zzi(int i) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            zzct.zzs(i);
        }
    }

    public final void zzv(int i) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            zzct.zzt(i);
        }
    }

    public final void zzw(int i) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            zzct.zzt(i);
        }
    }

    public final void onApplicationDisconnected(int i) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            String unused = zzct.zzyv = null;
            String unused2 = zzct.zzyw = null;
            zzct.zzt(i);
            if (zzct.zzak != null) {
                this.handler.post(new zzcy(this, zzct, i));
            }
        }
    }

    public final void zza(String str, double d, boolean z) {
        zzct.zzbf.d("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    public final void zzb(zzdb zzdb) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            zzct.zzbf.d("onDeviceStatusChanged", new Object[0]);
            this.handler.post(new zzcx(this, zzct, zzdb));
        }
    }

    public final void zzb(zzcj zzcj) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            zzct.zzbf.d("onApplicationStatusChanged", new Object[0]);
            this.handler.post(new zzda(this, zzct, zzcj));
        }
    }

    public final void zzb(String str, String str2) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            zzct.zzbf.d("Receive (type=text, ns=%s) %s", str, str2);
            this.handler.post(new zzcz(this, zzct, str, str2));
        }
    }

    public final void zza(String str, byte[] bArr) {
        if (this.zzzh.get() != null) {
            zzct.zzbf.d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
        }
    }

    public final void zza(String str, long j, int i) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            zzct.zzb(j, i);
        }
    }

    public final void zza(String str, long j) {
        zzct zzct = this.zzzh.get();
        if (zzct != null) {
            zzct.zzb(j, 0);
        }
    }
}
