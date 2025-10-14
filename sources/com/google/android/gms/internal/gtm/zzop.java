package com.google.android.gms.internal.gtm;

import java.io.IOException;

public final class zzop extends zzuq<zzop> {
    public long zzaux = 0;
    public zzk zzauy = null;
    public zzi zzqk = null;

    public zzop() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzop)) {
            return false;
        }
        zzop zzop = (zzop) obj;
        if (this.zzaux != zzop.zzaux) {
            return false;
        }
        if (this.zzqk == null) {
            if (zzop.zzqk != null) {
                return false;
            }
        } else if (!this.zzqk.equals(zzop.zzqk)) {
            return false;
        }
        if (this.zzauy == null) {
            if (zzop.zzauy != null) {
                return false;
            }
        } else if (!this.zzauy.equals(zzop.zzauy)) {
            return false;
        }
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            return this.zzbhb.equals(zzop.zzbhb);
        }
        if (zzop.zzbhb == null || zzop.zzbhb.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzaux ^ (this.zzaux >>> 32)));
        zzi zzi = this.zzqk;
        int i2 = hashCode * 31;
        int hashCode2 = zzi == null ? 0 : zzi.hashCode();
        zzk zzk = this.zzauy;
        int hashCode3 = ((zzk == null ? 0 : zzk.hashCode()) + ((hashCode2 + i2) * 31)) * 31;
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            i = this.zzbhb.hashCode();
        }
        return hashCode3 + i;
    }

    public final void zza(zzuo zzuo) throws IOException {
        zzuo.zzi(1, this.zzaux);
        if (this.zzqk != null) {
            zzuo.zza(2, (zzuw) this.zzqk);
        }
        if (this.zzauy != null) {
            zzuo.zza(3, (zzuw) this.zzauy);
        }
        super.zza(zzuo);
    }

    /* access modifiers changed from: protected */
    public final int zzy() {
        int zzy = super.zzy() + zzuo.zzd(1, this.zzaux);
        if (this.zzqk != null) {
            zzy += zzuo.zzb(2, (zzuw) this.zzqk);
        }
        if (this.zzauy != null) {
            return zzy + zzuo.zzb(3, (zzuw) this.zzauy);
        }
        return zzy;
    }

    public final /* synthetic */ zzuw zza(zzun zzun) throws IOException {
        while (true) {
            int zzni = zzun.zzni();
            switch (zzni) {
                case 0:
                    break;
                case 8:
                    this.zzaux = zzun.zzob();
                    continue;
                case 18:
                    if (this.zzqk == null) {
                        this.zzqk = new zzi();
                    }
                    zzun.zza((zzuw) this.zzqk);
                    continue;
                case 26:
                    if (this.zzauy == null) {
                        this.zzauy = new zzk();
                    }
                    zzun.zza((zzuw) this.zzauy);
                    continue;
                default:
                    if (!super.zza(zzun, zzni)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }
}
