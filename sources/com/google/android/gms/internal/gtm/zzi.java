package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzc;
import java.io.IOException;

public final class zzi extends zzuq<zzi> {
    public String version = "";
    private String[] zzph = zzuz.zzbhu;
    public String[] zzpi = zzuz.zzbhu;
    public zzl[] zzpj = zzl.zzaa();
    public zzc.zzd[] zzpk = new zzc.zzd[0];
    public zzc.zzb[] zzpl = new zzc.zzb[0];
    public zzc.zzb[] zzpm = new zzc.zzb[0];
    public zzc.zzb[] zzpn = new zzc.zzb[0];
    public zzc.zze[] zzpo = new zzc.zze[0];
    private String zzpp = "";
    private String zzpq = "";
    private String zzpr = "0";
    private zzc.zza zzps = null;
    private float zzpt = 0.0f;
    private boolean zzpu = false;
    private String[] zzpv = zzuz.zzbhu;
    public int zzpw = 0;

    public zzi() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        if (!zzuu.equals((Object[]) this.zzph, (Object[]) zzi.zzph)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpi, (Object[]) zzi.zzpi)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpj, (Object[]) zzi.zzpj)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpk, (Object[]) zzi.zzpk)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpl, (Object[]) zzi.zzpl)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpm, (Object[]) zzi.zzpm)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpn, (Object[]) zzi.zzpn)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpo, (Object[]) zzi.zzpo)) {
            return false;
        }
        if (this.zzpp == null) {
            if (zzi.zzpp != null) {
                return false;
            }
        } else if (!this.zzpp.equals(zzi.zzpp)) {
            return false;
        }
        if (this.zzpq == null) {
            if (zzi.zzpq != null) {
                return false;
            }
        } else if (!this.zzpq.equals(zzi.zzpq)) {
            return false;
        }
        if (this.zzpr == null) {
            if (zzi.zzpr != null) {
                return false;
            }
        } else if (!this.zzpr.equals(zzi.zzpr)) {
            return false;
        }
        if (this.version == null) {
            if (zzi.version != null) {
                return false;
            }
        } else if (!this.version.equals(zzi.version)) {
            return false;
        }
        if (this.zzps == null) {
            if (zzi.zzps != null) {
                return false;
            }
        } else if (!this.zzps.equals(zzi.zzps)) {
            return false;
        }
        if (Float.floatToIntBits(this.zzpt) != Float.floatToIntBits(zzi.zzpt)) {
            return false;
        }
        if (this.zzpu != zzi.zzpu) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzpv, (Object[]) zzi.zzpv)) {
            return false;
        }
        if (this.zzpw != zzi.zzpw) {
            return false;
        }
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            return this.zzbhb.equals(zzi.zzbhb);
        }
        if (zzi.zzbhb == null || zzi.zzbhb.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.version == null ? 0 : this.version.hashCode()) + (((this.zzpr == null ? 0 : this.zzpr.hashCode()) + (((this.zzpq == null ? 0 : this.zzpq.hashCode()) + (((this.zzpp == null ? 0 : this.zzpp.hashCode()) + ((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzuu.hashCode((Object[]) this.zzph)) * 31) + zzuu.hashCode((Object[]) this.zzpi)) * 31) + zzuu.hashCode((Object[]) this.zzpj)) * 31) + zzuu.hashCode((Object[]) this.zzpk)) * 31) + zzuu.hashCode((Object[]) this.zzpl)) * 31) + zzuu.hashCode((Object[]) this.zzpm)) * 31) + zzuu.hashCode((Object[]) this.zzpn)) * 31) + zzuu.hashCode((Object[]) this.zzpo)) * 31)) * 31)) * 31)) * 31);
        zzc.zza zza = this.zzps;
        int hashCode2 = ((((((this.zzpu ? 1231 : 1237) + (((((zza == null ? 0 : zza.hashCode()) + (hashCode * 31)) * 31) + Float.floatToIntBits(this.zzpt)) * 31)) * 31) + zzuu.hashCode((Object[]) this.zzpv)) * 31) + this.zzpw) * 31;
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            i = this.zzbhb.hashCode();
        }
        return hashCode2 + i;
    }

    public final void zza(zzuo zzuo) throws IOException {
        if (this.zzpi != null && this.zzpi.length > 0) {
            for (String str : this.zzpi) {
                if (str != null) {
                    zzuo.zza(1, str);
                }
            }
        }
        if (this.zzpj != null && this.zzpj.length > 0) {
            for (zzl zzl : this.zzpj) {
                if (zzl != null) {
                    zzuo.zza(2, (zzuw) zzl);
                }
            }
        }
        if (this.zzpk != null && this.zzpk.length > 0) {
            for (zzc.zzd zzd : this.zzpk) {
                if (zzd != null) {
                    zzuo.zze(3, (zzsk) zzd);
                }
            }
        }
        if (this.zzpl != null && this.zzpl.length > 0) {
            for (zzc.zzb zzb : this.zzpl) {
                if (zzb != null) {
                    zzuo.zze(4, (zzsk) zzb);
                }
            }
        }
        if (this.zzpm != null && this.zzpm.length > 0) {
            for (zzc.zzb zzb2 : this.zzpm) {
                if (zzb2 != null) {
                    zzuo.zze(5, (zzsk) zzb2);
                }
            }
        }
        if (this.zzpn != null && this.zzpn.length > 0) {
            for (zzc.zzb zzb3 : this.zzpn) {
                if (zzb3 != null) {
                    zzuo.zze(6, (zzsk) zzb3);
                }
            }
        }
        if (this.zzpo != null && this.zzpo.length > 0) {
            for (zzc.zze zze : this.zzpo) {
                if (zze != null) {
                    zzuo.zze(7, (zzsk) zze);
                }
            }
        }
        if (this.zzpp != null && !this.zzpp.equals("")) {
            zzuo.zza(9, this.zzpp);
        }
        if (this.zzpq != null && !this.zzpq.equals("")) {
            zzuo.zza(10, this.zzpq);
        }
        if (this.zzpr != null && !this.zzpr.equals("0")) {
            zzuo.zza(12, this.zzpr);
        }
        if (this.version != null && !this.version.equals("")) {
            zzuo.zza(13, this.version);
        }
        if (this.zzps != null) {
            zzuo.zze(14, (zzsk) this.zzps);
        }
        if (Float.floatToIntBits(this.zzpt) != Float.floatToIntBits(0.0f)) {
            float f = this.zzpt;
            zzuo.zzd(15, 5);
            zzuo.zzcc(Float.floatToIntBits(f));
        }
        if (this.zzpv != null && this.zzpv.length > 0) {
            for (String str2 : this.zzpv) {
                if (str2 != null) {
                    zzuo.zza(16, str2);
                }
            }
        }
        if (this.zzpw != 0) {
            zzuo.zze(17, this.zzpw);
        }
        if (this.zzpu) {
            zzuo.zzb(18, this.zzpu);
        }
        if (this.zzph != null && this.zzph.length > 0) {
            for (String str3 : this.zzph) {
                if (str3 != null) {
                    zzuo.zza(19, str3);
                }
            }
        }
        super.zza(zzuo);
    }

    /* access modifiers changed from: protected */
    public final int zzy() {
        int i;
        int zzy = super.zzy();
        if (this.zzpi == null || this.zzpi.length <= 0) {
            i = zzy;
        } else {
            int i2 = 0;
            int i3 = 0;
            for (String str : this.zzpi) {
                if (str != null) {
                    i3++;
                    i2 += zzuo.zzda(str);
                }
            }
            i = zzy + i2 + (i3 * 1);
        }
        if (this.zzpj != null && this.zzpj.length > 0) {
            int i4 = i;
            for (zzl zzl : this.zzpj) {
                if (zzl != null) {
                    i4 += zzuo.zzb(2, (zzuw) zzl);
                }
            }
            i = i4;
        }
        if (this.zzpk != null && this.zzpk.length > 0) {
            int i5 = i;
            for (zzc.zzd zzd : this.zzpk) {
                if (zzd != null) {
                    i5 += zzqj.zzc(3, (zzsk) zzd);
                }
            }
            i = i5;
        }
        if (this.zzpl != null && this.zzpl.length > 0) {
            int i6 = i;
            for (zzc.zzb zzb : this.zzpl) {
                if (zzb != null) {
                    i6 += zzqj.zzc(4, (zzsk) zzb);
                }
            }
            i = i6;
        }
        if (this.zzpm != null && this.zzpm.length > 0) {
            int i7 = i;
            for (zzc.zzb zzb2 : this.zzpm) {
                if (zzb2 != null) {
                    i7 += zzqj.zzc(5, (zzsk) zzb2);
                }
            }
            i = i7;
        }
        if (this.zzpn != null && this.zzpn.length > 0) {
            int i8 = i;
            for (zzc.zzb zzb3 : this.zzpn) {
                if (zzb3 != null) {
                    i8 += zzqj.zzc(6, (zzsk) zzb3);
                }
            }
            i = i8;
        }
        if (this.zzpo != null && this.zzpo.length > 0) {
            int i9 = i;
            for (zzc.zze zze : this.zzpo) {
                if (zze != null) {
                    i9 += zzqj.zzc(7, (zzsk) zze);
                }
            }
            i = i9;
        }
        if (this.zzpp != null && !this.zzpp.equals("")) {
            i += zzuo.zzb(9, this.zzpp);
        }
        if (this.zzpq != null && !this.zzpq.equals("")) {
            i += zzuo.zzb(10, this.zzpq);
        }
        if (this.zzpr != null && !this.zzpr.equals("0")) {
            i += zzuo.zzb(12, this.zzpr);
        }
        if (this.version != null && !this.version.equals("")) {
            i += zzuo.zzb(13, this.version);
        }
        if (this.zzps != null) {
            i += zzqj.zzc(14, (zzsk) this.zzps);
        }
        if (Float.floatToIntBits(this.zzpt) != Float.floatToIntBits(0.0f)) {
            i += zzuo.zzbb(15) + 4;
        }
        if (this.zzpv != null && this.zzpv.length > 0) {
            int i10 = 0;
            int i11 = 0;
            for (String str2 : this.zzpv) {
                if (str2 != null) {
                    i11++;
                    i10 += zzuo.zzda(str2);
                }
            }
            i = i + i10 + (i11 * 2);
        }
        if (this.zzpw != 0) {
            i += zzuo.zzi(17, this.zzpw);
        }
        if (this.zzpu) {
            i += zzuo.zzbb(18) + 1;
        }
        if (this.zzph == null || this.zzph.length <= 0) {
            return i;
        }
        int i12 = 0;
        int i13 = 0;
        for (String str3 : this.zzph) {
            if (str3 != null) {
                i13++;
                i12 += zzuo.zzda(str3);
            }
        }
        return i + i12 + (i13 * 2);
    }

    public final /* synthetic */ zzuw zza(zzun zzun) throws IOException {
        while (true) {
            int zzni = zzun.zzni();
            switch (zzni) {
                case 0:
                    break;
                case 10:
                    int zzb = zzuz.zzb(zzun, 10);
                    int length = this.zzpi == null ? 0 : this.zzpi.length;
                    String[] strArr = new String[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzpi, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzun.readString();
                        zzun.zzni();
                        length++;
                    }
                    strArr[length] = zzun.readString();
                    this.zzpi = strArr;
                    continue;
                case 18:
                    int zzb2 = zzuz.zzb(zzun, 18);
                    int length2 = this.zzpj == null ? 0 : this.zzpj.length;
                    zzl[] zzlArr = new zzl[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzpj, 0, zzlArr, 0, length2);
                    }
                    while (length2 < zzlArr.length - 1) {
                        zzlArr[length2] = new zzl();
                        zzun.zza((zzuw) zzlArr[length2]);
                        zzun.zzni();
                        length2++;
                    }
                    zzlArr[length2] = new zzl();
                    zzun.zza((zzuw) zzlArr[length2]);
                    this.zzpj = zzlArr;
                    continue;
                case 26:
                    int zzb3 = zzuz.zzb(zzun, 26);
                    int length3 = this.zzpk == null ? 0 : this.zzpk.length;
                    zzc.zzd[] zzdArr = new zzc.zzd[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzpk, 0, zzdArr, 0, length3);
                    }
                    while (true) {
                        int i = length3;
                        if (i < zzdArr.length - 1) {
                            zzdArr[i] = (zzc.zzd) zzun.zza(zzc.zzd.zza());
                            zzun.zzni();
                            length3 = i + 1;
                        } else {
                            zzdArr[i] = (zzc.zzd) zzun.zza(zzc.zzd.zza());
                            this.zzpk = zzdArr;
                            continue;
                        }
                    }
                case 34:
                    int zzb4 = zzuz.zzb(zzun, 34);
                    int length4 = this.zzpl == null ? 0 : this.zzpl.length;
                    zzc.zzb[] zzbArr = new zzc.zzb[(zzb4 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzpl, 0, zzbArr, 0, length4);
                    }
                    while (true) {
                        int i2 = length4;
                        if (i2 < zzbArr.length - 1) {
                            zzbArr[i2] = (zzc.zzb) zzun.zza(zzc.zzb.zza());
                            zzun.zzni();
                            length4 = i2 + 1;
                        } else {
                            zzbArr[i2] = (zzc.zzb) zzun.zza(zzc.zzb.zza());
                            this.zzpl = zzbArr;
                            continue;
                        }
                    }
                case 42:
                    int zzb5 = zzuz.zzb(zzun, 42);
                    int length5 = this.zzpm == null ? 0 : this.zzpm.length;
                    zzc.zzb[] zzbArr2 = new zzc.zzb[(zzb5 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzpm, 0, zzbArr2, 0, length5);
                    }
                    while (true) {
                        int i3 = length5;
                        if (i3 < zzbArr2.length - 1) {
                            zzbArr2[i3] = (zzc.zzb) zzun.zza(zzc.zzb.zza());
                            zzun.zzni();
                            length5 = i3 + 1;
                        } else {
                            zzbArr2[i3] = (zzc.zzb) zzun.zza(zzc.zzb.zza());
                            this.zzpm = zzbArr2;
                            continue;
                        }
                    }
                case 50:
                    int zzb6 = zzuz.zzb(zzun, 50);
                    int length6 = this.zzpn == null ? 0 : this.zzpn.length;
                    zzc.zzb[] zzbArr3 = new zzc.zzb[(zzb6 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzpn, 0, zzbArr3, 0, length6);
                    }
                    while (true) {
                        int i4 = length6;
                        if (i4 < zzbArr3.length - 1) {
                            zzbArr3[i4] = (zzc.zzb) zzun.zza(zzc.zzb.zza());
                            zzun.zzni();
                            length6 = i4 + 1;
                        } else {
                            zzbArr3[i4] = (zzc.zzb) zzun.zza(zzc.zzb.zza());
                            this.zzpn = zzbArr3;
                            continue;
                        }
                    }
                case 58:
                    int zzb7 = zzuz.zzb(zzun, 58);
                    int length7 = this.zzpo == null ? 0 : this.zzpo.length;
                    zzc.zze[] zzeArr = new zzc.zze[(zzb7 + length7)];
                    if (length7 != 0) {
                        System.arraycopy(this.zzpo, 0, zzeArr, 0, length7);
                    }
                    while (true) {
                        int i5 = length7;
                        if (i5 < zzeArr.length - 1) {
                            zzeArr[i5] = (zzc.zze) zzun.zza(zzc.zze.zza());
                            zzun.zzni();
                            length7 = i5 + 1;
                        } else {
                            zzeArr[i5] = (zzc.zze) zzun.zza(zzc.zze.zza());
                            this.zzpo = zzeArr;
                            continue;
                        }
                    }
                case 74:
                    this.zzpp = zzun.readString();
                    continue;
                case 82:
                    this.zzpq = zzun.readString();
                    continue;
                case 98:
                    this.zzpr = zzun.readString();
                    continue;
                case 106:
                    this.version = zzun.readString();
                    continue;
                case 114:
                    zzc.zza zza = (zzc.zza) zzun.zza(zzc.zza.zza());
                    if (this.zzps != null) {
                        zza = (zzc.zza) ((zzrc) ((zzc.zza.C0002zza) ((zzc.zza.C0002zza) this.zzps.zzpd()).zza(zza)).zzpm());
                    }
                    this.zzps = zza;
                    continue;
                case 125:
                    this.zzpt = Float.intBitsToFloat(zzun.zzoc());
                    continue;
                case 130:
                    int zzb8 = zzuz.zzb(zzun, 130);
                    int length8 = this.zzpv == null ? 0 : this.zzpv.length;
                    String[] strArr2 = new String[(zzb8 + length8)];
                    if (length8 != 0) {
                        System.arraycopy(this.zzpv, 0, strArr2, 0, length8);
                    }
                    while (length8 < strArr2.length - 1) {
                        strArr2[length8] = zzun.readString();
                        zzun.zzni();
                        length8++;
                    }
                    strArr2[length8] = zzun.readString();
                    this.zzpv = strArr2;
                    continue;
                case 136:
                    this.zzpw = zzun.zzoa();
                    continue;
                case 144:
                    this.zzpu = zzun.zzno();
                    continue;
                case 154:
                    int zzb9 = zzuz.zzb(zzun, 154);
                    int length9 = this.zzph == null ? 0 : this.zzph.length;
                    String[] strArr3 = new String[(zzb9 + length9)];
                    if (length9 != 0) {
                        System.arraycopy(this.zzph, 0, strArr3, 0, length9);
                    }
                    while (length9 < strArr3.length - 1) {
                        strArr3[length9] = zzun.readString();
                        zzun.zzni();
                        length9++;
                    }
                    strArr3[length9] = zzun.readString();
                    this.zzph = strArr3;
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
