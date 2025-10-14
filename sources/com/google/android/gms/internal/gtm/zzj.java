package com.google.android.gms.internal.gtm;

import java.io.IOException;

public final class zzj extends zzuq<zzj> {
    private static volatile zzj[] zzqg;
    public String name = "";
    private zzl zzqh = null;
    public zzh zzqi = null;

    public static zzj[] zzz() {
        if (zzqg == null) {
            synchronized (zzuu.zzbhk) {
                if (zzqg == null) {
                    zzqg = new zzj[0];
                }
            }
        }
        return zzqg;
    }

    public zzj() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzj = (zzj) obj;
        if (this.name == null) {
            if (zzj.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzj.name)) {
            return false;
        }
        if (this.zzqh == null) {
            if (zzj.zzqh != null) {
                return false;
            }
        } else if (!this.zzqh.equals(zzj.zzqh)) {
            return false;
        }
        if (this.zzqi == null) {
            if (zzj.zzqi != null) {
                return false;
            }
        } else if (!this.zzqi.equals(zzj.zzqi)) {
            return false;
        }
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            return this.zzbhb.equals(zzj.zzbhb);
        }
        if (zzj.zzbhb == null || zzj.zzbhb.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        zzl zzl = this.zzqh;
        int i2 = hashCode * 31;
        int hashCode2 = zzl == null ? 0 : zzl.hashCode();
        zzh zzh = this.zzqi;
        int hashCode3 = ((zzh == null ? 0 : zzh.hashCode()) + ((hashCode2 + i2) * 31)) * 31;
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            i = this.zzbhb.hashCode();
        }
        return hashCode3 + i;
    }

    public final void zza(zzuo zzuo) throws IOException {
        if (this.name != null && !this.name.equals("")) {
            zzuo.zza(1, this.name);
        }
        if (this.zzqh != null) {
            zzuo.zza(2, (zzuw) this.zzqh);
        }
        if (this.zzqi != null) {
            zzuo.zza(3, (zzuw) this.zzqi);
        }
        super.zza(zzuo);
    }

    /* access modifiers changed from: protected */
    public final int zzy() {
        int zzy = super.zzy();
        if (this.name != null && !this.name.equals("")) {
            zzy += zzuo.zzb(1, this.name);
        }
        if (this.zzqh != null) {
            zzy += zzuo.zzb(2, (zzuw) this.zzqh);
        }
        if (this.zzqi != null) {
            return zzy + zzuo.zzb(3, (zzuw) this.zzqi);
        }
        return zzy;
    }

    public final /* synthetic */ zzuw zza(zzun zzun) throws IOException {
        while (true) {
            int zzni = zzun.zzni();
            switch (zzni) {
                case 0:
                    break;
                case 10:
                    this.name = zzun.readString();
                    continue;
                case 18:
                    if (this.zzqh == null) {
                        this.zzqh = new zzl();
                    }
                    zzun.zza((zzuw) this.zzqh);
                    continue;
                case 26:
                    if (this.zzqi == null) {
                        this.zzqi = new zzh();
                    }
                    zzun.zza((zzuw) this.zzqi);
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
