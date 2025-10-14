package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzl;

final class zzfh {
    private zzdz<zzl> zzakf;
    private zzl zzakg;

    public zzfh(zzdz<zzl> zzdz, zzl zzl) {
        this.zzakf = zzdz;
        this.zzakg = zzl;
    }

    public final zzdz<zzl> zzjh() {
        return this.zzakf;
    }

    public final zzl zzji() {
        return this.zzakg;
    }

    public final int getSize() {
        return (this.zzakg == null ? 0 : this.zzakg.zzse()) + this.zzakf.getObject().zzse();
    }
}
