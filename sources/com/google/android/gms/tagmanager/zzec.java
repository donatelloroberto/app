package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzec implements zzfw {
    private final /* synthetic */ zzeb zzaik;

    zzec(zzeb zzeb) {
        this.zzaik = zzeb;
    }

    public final void zza(zzbw zzbw) {
        this.zzaik.zze(zzbw.zzih());
    }

    public final void zzb(zzbw zzbw) {
        this.zzaik.zze(zzbw.zzih());
        zzdi.zzab(new StringBuilder(57).append("Permanent failure dispatching hitId: ").append(zzbw.zzih()).toString());
    }

    public final void zzc(zzbw zzbw) {
        long zzii = zzbw.zzii();
        if (zzii == 0) {
            this.zzaik.zzb(zzbw.zzih(), this.zzaik.zzsd.currentTimeMillis());
        } else if (zzii + 14400000 < this.zzaik.zzsd.currentTimeMillis()) {
            this.zzaik.zze(zzbw.zzih());
            zzdi.zzab(new StringBuilder(47).append("Giving up on failed hitId: ").append(zzbw.zzih()).toString());
        }
    }
}
