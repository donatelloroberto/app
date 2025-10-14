package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzc;
import java.io.IOException;

public final class zzh extends zzuq<zzh> {
    public zzl[] zzpe = zzl.zzaa();
    public zzl[] zzpf = zzl.zzaa();
    public zzc.C0003zzc[] zzpg = new zzc.C0003zzc[0];

    public zzh() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh zzh = (zzh) obj;
        if (!zzuu.equals((Object[]) this.zzpe, (Object[]) zzh.zzpe)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpf, (Object[]) zzh.zzpf)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpg, (Object[]) zzh.zzpg)) {
            return false;
        }
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            return this.zzbhb.equals(zzh.zzbhb);
        }
        if (zzh.zzbhb == null || zzh.zzbhb.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + zzuu.hashCode((Object[]) this.zzpe)) * 31) + zzuu.hashCode((Object[]) this.zzpf)) * 31) + zzuu.hashCode((Object[]) this.zzpg)) * 31;
        if (this.zzbhb == null || this.zzbhb.isEmpty()) {
            i = 0;
        } else {
            i = this.zzbhb.hashCode();
        }
        return i + hashCode;
    }

    public final void zza(zzuo zzuo) throws IOException {
        if (this.zzpe != null && this.zzpe.length > 0) {
            for (zzl zzl : this.zzpe) {
                if (zzl != null) {
                    zzuo.zza(1, (zzuw) zzl);
                }
            }
        }
        if (this.zzpf != null && this.zzpf.length > 0) {
            for (zzl zzl2 : this.zzpf) {
                if (zzl2 != null) {
                    zzuo.zza(2, (zzuw) zzl2);
                }
            }
        }
        if (this.zzpg != null && this.zzpg.length > 0) {
            for (zzc.C0003zzc zzc : this.zzpg) {
                if (zzc != null) {
                    zzuo.zze(3, (zzsk) zzc);
                }
            }
        }
        super.zza(zzuo);
    }

    /* access modifiers changed from: protected */
    public final int zzy() {
        int zzy = super.zzy();
        if (this.zzpe != null && this.zzpe.length > 0) {
            for (zzl zzl : this.zzpe) {
                if (zzl != null) {
                    zzy += zzuo.zzb(1, (zzuw) zzl);
                }
            }
        }
        if (this.zzpf != null && this.zzpf.length > 0) {
            for (zzl zzl2 : this.zzpf) {
                if (zzl2 != null) {
                    zzy += zzuo.zzb(2, (zzuw) zzl2);
                }
            }
        }
        if (this.zzpg != null && this.zzpg.length > 0) {
            for (zzc.C0003zzc zzc : this.zzpg) {
                if (zzc != null) {
                    zzy += zzqj.zzc(3, (zzsk) zzc);
                }
            }
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
                    int zzb = zzuz.zzb(zzun, 10);
                    int length = this.zzpe == null ? 0 : this.zzpe.length;
                    zzl[] zzlArr = new zzl[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzpe, 0, zzlArr, 0, length);
                    }
                    while (length < zzlArr.length - 1) {
                        zzlArr[length] = new zzl();
                        zzun.zza((zzuw) zzlArr[length]);
                        zzun.zzni();
                        length++;
                    }
                    zzlArr[length] = new zzl();
                    zzun.zza((zzuw) zzlArr[length]);
                    this.zzpe = zzlArr;
                    continue;
                case 18:
                    int zzb2 = zzuz.zzb(zzun, 18);
                    int length2 = this.zzpf == null ? 0 : this.zzpf.length;
                    zzl[] zzlArr2 = new zzl[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzpf, 0, zzlArr2, 0, length2);
                    }
                    while (length2 < zzlArr2.length - 1) {
                        zzlArr2[length2] = new zzl();
                        zzun.zza((zzuw) zzlArr2[length2]);
                        zzun.zzni();
                        length2++;
                    }
                    zzlArr2[length2] = new zzl();
                    zzun.zza((zzuw) zzlArr2[length2]);
                    this.zzpf = zzlArr2;
                    continue;
                case 26:
                    int zzb3 = zzuz.zzb(zzun, 26);
                    int length3 = this.zzpg == null ? 0 : this.zzpg.length;
                    zzc.C0003zzc[] zzcArr = new zzc.C0003zzc[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzpg, 0, zzcArr, 0, length3);
                    }
                    while (true) {
                        int i = length3;
                        if (i < zzcArr.length - 1) {
                            zzcArr[i] = (zzc.C0003zzc) zzun.zza(zzc.C0003zzc.zza());
                            zzun.zzni();
                            length3 = i + 1;
                        } else {
                            zzcArr[i] = (zzc.C0003zzc) zzun.zza(zzc.C0003zzc.zza());
                            this.zzpg = zzcArr;
                            continue;
                        }
                    }
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
