package com.google.android.gms.internal.gtm;

import java.io.IOException;

public final class zzl extends zzuq<zzl> {
    private static volatile zzl[] zzqm;
    public String string = "";
    public int type = 1;
    public zzl[] zzqn = zzaa();
    public zzl[] zzqo = zzaa();
    public zzl[] zzqp = zzaa();
    public String zzqq = "";
    public String zzqr = "";
    public long zzqs = 0;
    public boolean zzqt = false;
    public zzl[] zzqu = zzaa();
    public int[] zzqv = zzuz.zzbcw;
    public boolean zzqw = false;

    private static int zzc(int i) {
        if (i > 0 && i <= 17) {
            return i;
        }
        throw new IllegalArgumentException(new StringBuilder(40).append(i).append(" is not a valid enum Escaping").toString());
    }

    public static zzl[] zzaa() {
        if (zzqm == null) {
            synchronized (zzuu.zzbhk) {
                if (zzqm == null) {
                    zzqm = new zzl[0];
                }
            }
        }
        return zzqm;
    }

    public zzl() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzl)) {
            return false;
        }
        zzl zzl = (zzl) obj;
        if (this.type != zzl.type) {
            return false;
        }
        if (this.string == null) {
            if (zzl.string != null) {
                return false;
            }
        } else if (!this.string.equals(zzl.string)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzqn, (Object[]) zzl.zzqn)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzqo, (Object[]) zzl.zzqo)) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzqp, (Object[]) zzl.zzqp)) {
            return false;
        }
        if (this.zzqq == null) {
            if (zzl.zzqq != null) {
                return false;
            }
        } else if (!this.zzqq.equals(zzl.zzqq)) {
            return false;
        }
        if (this.zzqr == null) {
            if (zzl.zzqr != null) {
                return false;
            }
        } else if (!this.zzqr.equals(zzl.zzqr)) {
            return false;
        }
        if (this.zzqs != zzl.zzqs) {
            return false;
        }
        if (this.zzqt != zzl.zzqt) {
            return false;
        }
        if (!zzuu.equals((Object[]) this.zzqu, (Object[]) zzl.zzqu)) {
            return false;
        }
        if (!zzuu.equals(this.zzqv, zzl.zzqv)) {
            return false;
        }
        if (this.zzqw != zzl.zzqw) {
            return false;
        }
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            return this.zzbhb.equals(zzl.zzbhb);
        }
        if (zzl.zzbhb == null || zzl.zzbhb.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2 = 1231;
        int i3 = 0;
        int hashCode = ((((this.zzqr == null ? 0 : this.zzqr.hashCode()) + (((this.zzqq == null ? 0 : this.zzqq.hashCode()) + (((((((((this.string == null ? 0 : this.string.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31)) * 31) + zzuu.hashCode((Object[]) this.zzqn)) * 31) + zzuu.hashCode((Object[]) this.zzqo)) * 31) + zzuu.hashCode((Object[]) this.zzqp)) * 31)) * 31)) * 31) + ((int) (this.zzqs ^ (this.zzqs >>> 32)))) * 31;
        if (this.zzqt) {
            i = 1231;
        } else {
            i = 1237;
        }
        int hashCode2 = (((((i + hashCode) * 31) + zzuu.hashCode((Object[]) this.zzqu)) * 31) + zzuu.hashCode(this.zzqv)) * 31;
        if (!this.zzqw) {
            i2 = 1237;
        }
        int i4 = (hashCode2 + i2) * 31;
        if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
            i3 = this.zzbhb.hashCode();
        }
        return i4 + i3;
    }

    public final void zza(zzuo zzuo) throws IOException {
        zzuo.zze(1, this.type);
        if (this.string != null && !this.string.equals("")) {
            zzuo.zza(2, this.string);
        }
        if (this.zzqn != null && this.zzqn.length > 0) {
            for (zzl zzl : this.zzqn) {
                if (zzl != null) {
                    zzuo.zza(3, (zzuw) zzl);
                }
            }
        }
        if (this.zzqo != null && this.zzqo.length > 0) {
            for (zzl zzl2 : this.zzqo) {
                if (zzl2 != null) {
                    zzuo.zza(4, (zzuw) zzl2);
                }
            }
        }
        if (this.zzqp != null && this.zzqp.length > 0) {
            for (zzl zzl3 : this.zzqp) {
                if (zzl3 != null) {
                    zzuo.zza(5, (zzuw) zzl3);
                }
            }
        }
        if (this.zzqq != null && !this.zzqq.equals("")) {
            zzuo.zza(6, this.zzqq);
        }
        if (this.zzqr != null && !this.zzqr.equals("")) {
            zzuo.zza(7, this.zzqr);
        }
        if (this.zzqs != 0) {
            zzuo.zzi(8, this.zzqs);
        }
        if (this.zzqw) {
            zzuo.zzb(9, this.zzqw);
        }
        if (this.zzqv != null && this.zzqv.length > 0) {
            for (int zze : this.zzqv) {
                zzuo.zze(10, zze);
            }
        }
        if (this.zzqu != null && this.zzqu.length > 0) {
            for (zzl zzl4 : this.zzqu) {
                if (zzl4 != null) {
                    zzuo.zza(11, (zzuw) zzl4);
                }
            }
        }
        if (this.zzqt) {
            zzuo.zzb(12, this.zzqt);
        }
        super.zza(zzuo);
    }

    /* access modifiers changed from: protected */
    public final int zzy() {
        int zzy = super.zzy() + zzuo.zzi(1, this.type);
        if (this.string != null && !this.string.equals("")) {
            zzy += zzuo.zzb(2, this.string);
        }
        if (this.zzqn != null && this.zzqn.length > 0) {
            int i = zzy;
            for (zzl zzl : this.zzqn) {
                if (zzl != null) {
                    i += zzuo.zzb(3, (zzuw) zzl);
                }
            }
            zzy = i;
        }
        if (this.zzqo != null && this.zzqo.length > 0) {
            int i2 = zzy;
            for (zzl zzl2 : this.zzqo) {
                if (zzl2 != null) {
                    i2 += zzuo.zzb(4, (zzuw) zzl2);
                }
            }
            zzy = i2;
        }
        if (this.zzqp != null && this.zzqp.length > 0) {
            int i3 = zzy;
            for (zzl zzl3 : this.zzqp) {
                if (zzl3 != null) {
                    i3 += zzuo.zzb(5, (zzuw) zzl3);
                }
            }
            zzy = i3;
        }
        if (this.zzqq != null && !this.zzqq.equals("")) {
            zzy += zzuo.zzb(6, this.zzqq);
        }
        if (this.zzqr != null && !this.zzqr.equals("")) {
            zzy += zzuo.zzb(7, this.zzqr);
        }
        if (this.zzqs != 0) {
            zzy += zzuo.zzd(8, this.zzqs);
        }
        if (this.zzqw) {
            zzy += zzuo.zzbb(9) + 1;
        }
        if (this.zzqv != null && this.zzqv.length > 0) {
            int i4 = 0;
            for (int zzbc : this.zzqv) {
                i4 += zzuo.zzbc(zzbc);
            }
            zzy = zzy + i4 + (this.zzqv.length * 1);
        }
        if (this.zzqu != null && this.zzqu.length > 0) {
            for (zzl zzl4 : this.zzqu) {
                if (zzl4 != null) {
                    zzy += zzuo.zzb(11, (zzuw) zzl4);
                }
            }
        }
        if (this.zzqt) {
            return zzy + zzuo.zzbb(12) + 1;
        }
        return zzy;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzl zza(zzun zzun) throws IOException {
        int length;
        while (true) {
            int zzni = zzun.zzni();
            switch (zzni) {
                case 0:
                    break;
                case 8:
                    int position = zzun.getPosition();
                    try {
                        int zzoa = zzun.zzoa();
                        if (zzoa <= 0 || zzoa > 8) {
                            throw new IllegalArgumentException(new StringBuilder(36).append(zzoa).append(" is not a valid enum Type").toString());
                        }
                        this.type = zzoa;
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzun.zzbz(position);
                        zza(zzun, zzni);
                        break;
                    }
                case 18:
                    this.string = zzun.readString();
                    continue;
                case 26:
                    int zzb = zzuz.zzb(zzun, 26);
                    int length2 = this.zzqn == null ? 0 : this.zzqn.length;
                    zzl[] zzlArr = new zzl[(zzb + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzqn, 0, zzlArr, 0, length2);
                    }
                    while (length2 < zzlArr.length - 1) {
                        zzlArr[length2] = new zzl();
                        zzun.zza((zzuw) zzlArr[length2]);
                        zzun.zzni();
                        length2++;
                    }
                    zzlArr[length2] = new zzl();
                    zzun.zza((zzuw) zzlArr[length2]);
                    this.zzqn = zzlArr;
                    continue;
                case 34:
                    int zzb2 = zzuz.zzb(zzun, 34);
                    int length3 = this.zzqo == null ? 0 : this.zzqo.length;
                    zzl[] zzlArr2 = new zzl[(zzb2 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzqo, 0, zzlArr2, 0, length3);
                    }
                    while (length3 < zzlArr2.length - 1) {
                        zzlArr2[length3] = new zzl();
                        zzun.zza((zzuw) zzlArr2[length3]);
                        zzun.zzni();
                        length3++;
                    }
                    zzlArr2[length3] = new zzl();
                    zzun.zza((zzuw) zzlArr2[length3]);
                    this.zzqo = zzlArr2;
                    continue;
                case 42:
                    int zzb3 = zzuz.zzb(zzun, 42);
                    int length4 = this.zzqp == null ? 0 : this.zzqp.length;
                    zzl[] zzlArr3 = new zzl[(zzb3 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzqp, 0, zzlArr3, 0, length4);
                    }
                    while (length4 < zzlArr3.length - 1) {
                        zzlArr3[length4] = new zzl();
                        zzun.zza((zzuw) zzlArr3[length4]);
                        zzun.zzni();
                        length4++;
                    }
                    zzlArr3[length4] = new zzl();
                    zzun.zza((zzuw) zzlArr3[length4]);
                    this.zzqp = zzlArr3;
                    continue;
                case 50:
                    this.zzqq = zzun.readString();
                    continue;
                case 58:
                    this.zzqr = zzun.readString();
                    continue;
                case 64:
                    this.zzqs = zzun.zzob();
                    continue;
                case 72:
                    this.zzqw = zzun.zzno();
                    continue;
                case 80:
                    int zzb4 = zzuz.zzb(zzun, 80);
                    int[] iArr = new int[zzb4];
                    int i = 0;
                    for (int i2 = 0; i2 < zzb4; i2++) {
                        if (i2 != 0) {
                            zzun.zzni();
                        }
                        int position2 = zzun.getPosition();
                        try {
                            iArr[i] = zzc(zzun.zzoa());
                            i++;
                        } catch (IllegalArgumentException e2) {
                            zzun.zzbz(position2);
                            zza(zzun, zzni);
                        }
                    }
                    if (i != 0) {
                        int length5 = this.zzqv == null ? 0 : this.zzqv.length;
                        if (length5 != 0 || i != iArr.length) {
                            int[] iArr2 = new int[(length5 + i)];
                            if (length5 != 0) {
                                System.arraycopy(this.zzqv, 0, iArr2, 0, length5);
                            }
                            System.arraycopy(iArr, 0, iArr2, length5, i);
                            this.zzqv = iArr2;
                            break;
                        } else {
                            this.zzqv = iArr;
                            break;
                        }
                    } else {
                        continue;
                    }
                case 82:
                    int zzaq = zzun.zzaq(zzun.zzoa());
                    int position3 = zzun.getPosition();
                    int i3 = 0;
                    while (zzun.zzrv() > 0) {
                        try {
                            zzc(zzun.zzoa());
                            i3++;
                        } catch (IllegalArgumentException e3) {
                        }
                    }
                    if (i3 != 0) {
                        zzun.zzbz(position3);
                        if (this.zzqv == null) {
                            length = 0;
                        } else {
                            length = this.zzqv.length;
                        }
                        int[] iArr3 = new int[(i3 + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzqv, 0, iArr3, 0, length);
                        }
                        while (zzun.zzrv() > 0) {
                            int position4 = zzun.getPosition();
                            try {
                                iArr3[length] = zzc(zzun.zzoa());
                                length++;
                            } catch (IllegalArgumentException e4) {
                                zzun.zzbz(position4);
                                zza(zzun, 80);
                            }
                        }
                        this.zzqv = iArr3;
                    }
                    zzun.zzar(zzaq);
                    continue;
                case 90:
                    int zzb5 = zzuz.zzb(zzun, 90);
                    int length6 = this.zzqu == null ? 0 : this.zzqu.length;
                    zzl[] zzlArr4 = new zzl[(zzb5 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzqu, 0, zzlArr4, 0, length6);
                    }
                    while (length6 < zzlArr4.length - 1) {
                        zzlArr4[length6] = new zzl();
                        zzun.zza((zzuw) zzlArr4[length6]);
                        zzun.zzni();
                        length6++;
                    }
                    zzlArr4[length6] = new zzl();
                    zzun.zza((zzuw) zzlArr4[length6]);
                    this.zzqu = zzlArr4;
                    continue;
                case 96:
                    this.zzqt = zzun.zzno();
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
