package com.google.android.gms.internal.cast;

final class zzdq implements zzdu {
    private final /* synthetic */ zzdu zzabd;
    private final /* synthetic */ zzdn zzabe;

    zzdq(zzdn zzdn, zzdu zzdu) {
        this.zzabe = zzdn;
        this.zzabd = zzdu;
    }

    public final void zza(long j, int i, Object obj) {
        Long unused = this.zzabe.zzaad = null;
        if (this.zzabd != null) {
            this.zzabd.zza(j, i, obj);
        }
    }

    public final void zzb(long j) {
        if (this.zzabd != null) {
            this.zzabd.zzb(j);
        }
    }
}
