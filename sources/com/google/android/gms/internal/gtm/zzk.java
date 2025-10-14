package com.google.android.gms.internal.gtm;

import java.io.IOException;

public final class zzk extends zzuq<zzk> {
    public zzj[] zzqj = zzj.zzz();
    public zzi zzqk = null;
    public String zzql = "";

    public zzk() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzk)) {
            return false;
        }
        zzk zzk = (zzk) obj;
        if (!zzuu.equals((Object[]) this.zzqj, (Object[]) zzk.zzqj)) {
            return false;
        }
        if (this.zzqk == null) {
            if (zzk.zzqk != null) {
                return false;
            }
        } else if (!this.zzqk.equals(zzk.zzqk)) {
            return false;
        }
        if (this.zzql == null) {
            if (zzk.zzql != null) {
                return false;
            }
        } else if (!this.zzql.equals(zzk.zzql)) {
            return false;
        }
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            return this.zzbhb.equals(zzk.zzbhb);
        }
        if (zzk.zzbhb == null || zzk.zzbhb.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + zzuu.hashCode((Object[]) this.zzqj);
        zzi zzi = this.zzqk;
        int hashCode2 = ((this.zzql == null ? 0 : this.zzql.hashCode()) + (((zzi == null ? 0 : zzi.hashCode()) + (hashCode * 31)) * 31)) * 31;
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            i = this.zzbhb.hashCode();
        }
        return hashCode2 + i;
    }

    public final void zza(zzuo zzuo) throws IOException {
        if (this.zzqj != null && this.zzqj.length > 0) {
            for (zzj zzj : this.zzqj) {
                if (zzj != null) {
                    zzuo.zza(1, (zzuw) zzj);
                }
            }
        }
        if (this.zzqk != null) {
            zzuo.zza(2, (zzuw) this.zzqk);
        }
        if (this.zzql != null && !this.zzql.equals("")) {
            zzuo.zza(3, this.zzql);
        }
        super.zza(zzuo);
    }

    /* access modifiers changed from: protected */
    public final int zzy() {
        int zzy = super.zzy();
        if (this.zzqj != null && this.zzqj.length > 0) {
            for (zzj zzj : this.zzqj) {
                if (zzj != null) {
                    zzy += zzuo.zzb(1, (zzuw) zzj);
                }
            }
        }
        if (this.zzqk != null) {
            zzy += zzuo.zzb(2, (zzuw) this.zzqk);
        }
        if (this.zzql == null || this.zzql.equals("")) {
            return zzy;
        }
        return zzy + zzuo.zzb(3, this.zzql);
    }

    public final /* synthetic */ zzuw zza(zzun zzun) throws IOException {
        while (true) {
            int zzni = zzun.zzni();
            switch (zzni) {
                case 0:
                    break;
                case 10:
                    int zzb = zzuz.zzb(zzun, 10);
                    int length = this.zzqj == null ? 0 : this.zzqj.length;
                    zzj[] zzjArr = new zzj[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzqj, 0, zzjArr, 0, length);
                    }
                    while (length < zzjArr.length - 1) {
                        zzjArr[length] = new zzj();
                        zzun.zza((zzuw) zzjArr[length]);
                        zzun.zzni();
                        length++;
                    }
                    zzjArr[length] = new zzj();
                    zzun.zza((zzuw) zzjArr[length]);
                    this.zzqj = zzjArr;
                    continue;
                case 18:
                    if (this.zzqk == null) {
                        this.zzqk = new zzi();
                    }
                    zzun.zza((zzuw) this.zzqk);
                    continue;
                case 26:
                    this.zzql = zzun.readString();
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
