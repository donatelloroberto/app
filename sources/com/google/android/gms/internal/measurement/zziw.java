package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zziw implements Runnable {
    private final /* synthetic */ zzdz zzano;
    private final /* synthetic */ boolean zzaoj;
    private final /* synthetic */ zzij zzapn;
    private final /* synthetic */ AtomicReference zzapo;

    zziw(zzij zzij, AtomicReference atomicReference, zzdz zzdz, boolean z) {
        this.zzapn = zzij;
        this.zzapo = atomicReference;
        this.zzano = zzdz;
        this.zzaoj = z;
    }

    /* JADX INFO: finally extract failed */
    public final void run() {
        synchronized (this.zzapo) {
            try {
                zzez zzd = this.zzapn.zzaph;
                if (zzd == null) {
                    this.zzapn.zzgf().zzis().log("Failed to get user properties");
                    this.zzapo.notify();
                    return;
                }
                this.zzapo.set(zzd.zza(this.zzano, this.zzaoj));
                this.zzapn.zzcu();
                this.zzapo.notify();
            } catch (RemoteException e) {
                this.zzapn.zzgf().zzis().zzg("Failed to get user properties", e);
                this.zzapo.notify();
            } catch (Throwable th) {
                this.zzapo.notify();
                throw th;
            }
        }
    }
}
