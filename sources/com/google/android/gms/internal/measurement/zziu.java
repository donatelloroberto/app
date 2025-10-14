package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

final class zziu implements Runnable {
    private final /* synthetic */ zzdz zzano;
    private final /* synthetic */ String zzanr;
    private final /* synthetic */ String zzans;
    private final /* synthetic */ String zzant;
    private final /* synthetic */ boolean zzaoj;
    private final /* synthetic */ zzij zzapn;
    private final /* synthetic */ AtomicReference zzapo;

    zziu(zzij zzij, AtomicReference atomicReference, String str, String str2, String str3, boolean z, zzdz zzdz) {
        this.zzapn = zzij;
        this.zzapo = atomicReference;
        this.zzant = str;
        this.zzanr = str2;
        this.zzans = str3;
        this.zzaoj = z;
        this.zzano = zzdz;
    }

    /* JADX INFO: finally extract failed */
    public final void run() {
        synchronized (this.zzapo) {
            try {
                zzez zzd = this.zzapn.zzaph;
                if (zzd == null) {
                    this.zzapn.zzgf().zzis().zzd("Failed to get user properties", zzfh.zzbl(this.zzant), this.zzanr, this.zzans);
                    this.zzapo.set(Collections.emptyList());
                    this.zzapo.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.zzant)) {
                    this.zzapo.set(zzd.zza(this.zzanr, this.zzans, this.zzaoj, this.zzano));
                } else {
                    this.zzapo.set(zzd.zza(this.zzant, this.zzanr, this.zzans, this.zzaoj));
                }
                this.zzapn.zzcu();
                this.zzapo.notify();
            } catch (RemoteException e) {
                this.zzapn.zzgf().zzis().zzd("Failed to get user properties", zzfh.zzbl(this.zzant), this.zzanr, e);
                this.zzapo.set(Collections.emptyList());
                this.zzapo.notify();
            } catch (Throwable th) {
                this.zzapo.notify();
                throw th;
            }
        }
    }
}
