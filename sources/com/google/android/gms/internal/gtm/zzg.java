package com.google.android.gms.internal.gtm;

import java.io.IOException;

public final class zzg {

    public static final class zza extends zzuq<zza> {
        public static final zzur<zzl, zza> zzpx = zzur.zza(11, zza.class, 810);
        private static final zza[] zzpy = new zza[0];
        public int[] zzpz = zzuz.zzbcw;
        public int[] zzqa = zzuz.zzbcw;
        public int[] zzqb = zzuz.zzbcw;
        private int zzqc = 0;
        public int[] zzqd = zzuz.zzbcw;
        public int zzqe = 0;
        private int zzqf = 0;

        public zza() {
            this.zzbhb = null;
            this.zzbhl = -1;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (!zzuu.equals(this.zzpz, zza.zzpz)) {
                return false;
            }
            if (!zzuu.equals(this.zzqa, zza.zzqa)) {
                return false;
            }
            if (!zzuu.equals(this.zzqb, zza.zzqb)) {
                return false;
            }
            if (this.zzqc != zza.zzqc) {
                return false;
            }
            if (!zzuu.equals(this.zzqd, zza.zzqd)) {
                return false;
            }
            if (this.zzqe != zza.zzqe) {
                return false;
            }
            if (this.zzqf != zza.zzqf) {
                return false;
            }
            if (this.zzbhb != null && !this.zzbhb.isEmpty()) {
                return this.zzbhb.equals(zza.zzbhb);
            }
            if (zza.zzbhb == null || zza.zzbhb.isEmpty()) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int i;
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzuu.hashCode(this.zzpz)) * 31) + zzuu.hashCode(this.zzqa)) * 31) + zzuu.hashCode(this.zzqb)) * 31) + this.zzqc) * 31) + zzuu.hashCode(this.zzqd)) * 31) + this.zzqe) * 31) + this.zzqf) * 31;
            if (this.zzbhb == null || this.zzbhb.isEmpty()) {
                i = 0;
            } else {
                i = this.zzbhb.hashCode();
            }
            return i + hashCode;
        }

        public final void zza(zzuo zzuo) throws IOException {
            if (this.zzpz != null && this.zzpz.length > 0) {
                for (int zze : this.zzpz) {
                    zzuo.zze(1, zze);
                }
            }
            if (this.zzqa != null && this.zzqa.length > 0) {
                for (int zze2 : this.zzqa) {
                    zzuo.zze(2, zze2);
                }
            }
            if (this.zzqb != null && this.zzqb.length > 0) {
                for (int zze3 : this.zzqb) {
                    zzuo.zze(3, zze3);
                }
            }
            if (this.zzqc != 0) {
                zzuo.zze(4, this.zzqc);
            }
            if (this.zzqd != null && this.zzqd.length > 0) {
                for (int zze4 : this.zzqd) {
                    zzuo.zze(5, zze4);
                }
            }
            if (this.zzqe != 0) {
                zzuo.zze(6, this.zzqe);
            }
            if (this.zzqf != 0) {
                zzuo.zze(7, this.zzqf);
            }
            super.zza(zzuo);
        }

        /* access modifiers changed from: protected */
        public final int zzy() {
            int i;
            int zzy = super.zzy();
            if (this.zzpz == null || this.zzpz.length <= 0) {
                i = zzy;
            } else {
                int i2 = 0;
                for (int zzbc : this.zzpz) {
                    i2 += zzuo.zzbc(zzbc);
                }
                i = zzy + i2 + (this.zzpz.length * 1);
            }
            if (this.zzqa != null && this.zzqa.length > 0) {
                int i3 = 0;
                for (int zzbc2 : this.zzqa) {
                    i3 += zzuo.zzbc(zzbc2);
                }
                i = i + i3 + (this.zzqa.length * 1);
            }
            if (this.zzqb != null && this.zzqb.length > 0) {
                int i4 = 0;
                for (int zzbc3 : this.zzqb) {
                    i4 += zzuo.zzbc(zzbc3);
                }
                i = i + i4 + (this.zzqb.length * 1);
            }
            if (this.zzqc != 0) {
                i += zzuo.zzi(4, this.zzqc);
            }
            if (this.zzqd != null && this.zzqd.length > 0) {
                int i5 = 0;
                for (int zzbc4 : this.zzqd) {
                    i5 += zzuo.zzbc(zzbc4);
                }
                i = i + i5 + (this.zzqd.length * 1);
            }
            if (this.zzqe != 0) {
                i += zzuo.zzi(6, this.zzqe);
            }
            if (this.zzqf != 0) {
                return i + zzuo.zzi(7, this.zzqf);
            }
            return i;
        }

        public final /* synthetic */ zzuw zza(zzun zzun) throws IOException {
            int length;
            int length2;
            int length3;
            int length4;
            while (true) {
                int zzni = zzun.zzni();
                switch (zzni) {
                    case 0:
                        break;
                    case 8:
                        int zzb = zzuz.zzb(zzun, 8);
                        if (this.zzpz == null) {
                            length4 = 0;
                        } else {
                            length4 = this.zzpz.length;
                        }
                        int[] iArr = new int[(zzb + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzpz, 0, iArr, 0, length4);
                        }
                        while (length4 < iArr.length - 1) {
                            iArr[length4] = zzun.zzoa();
                            zzun.zzni();
                            length4++;
                        }
                        iArr[length4] = zzun.zzoa();
                        this.zzpz = iArr;
                        continue;
                    case 10:
                        int zzaq = zzun.zzaq(zzun.zzoa());
                        int position = zzun.getPosition();
                        int i = 0;
                        while (zzun.zzrv() > 0) {
                            zzun.zzoa();
                            i++;
                        }
                        zzun.zzbz(position);
                        int length5 = this.zzpz == null ? 0 : this.zzpz.length;
                        int[] iArr2 = new int[(i + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzpz, 0, iArr2, 0, length5);
                        }
                        while (length5 < iArr2.length) {
                            iArr2[length5] = zzun.zzoa();
                            length5++;
                        }
                        this.zzpz = iArr2;
                        zzun.zzar(zzaq);
                        continue;
                    case 16:
                        int zzb2 = zzuz.zzb(zzun, 16);
                        if (this.zzqa == null) {
                            length3 = 0;
                        } else {
                            length3 = this.zzqa.length;
                        }
                        int[] iArr3 = new int[(zzb2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzqa, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = zzun.zzoa();
                            zzun.zzni();
                            length3++;
                        }
                        iArr3[length3] = zzun.zzoa();
                        this.zzqa = iArr3;
                        continue;
                    case 18:
                        int zzaq2 = zzun.zzaq(zzun.zzoa());
                        int position2 = zzun.getPosition();
                        int i2 = 0;
                        while (zzun.zzrv() > 0) {
                            zzun.zzoa();
                            i2++;
                        }
                        zzun.zzbz(position2);
                        int length6 = this.zzqa == null ? 0 : this.zzqa.length;
                        int[] iArr4 = new int[(i2 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzqa, 0, iArr4, 0, length6);
                        }
                        while (length6 < iArr4.length) {
                            iArr4[length6] = zzun.zzoa();
                            length6++;
                        }
                        this.zzqa = iArr4;
                        zzun.zzar(zzaq2);
                        continue;
                    case 24:
                        int zzb3 = zzuz.zzb(zzun, 24);
                        if (this.zzqb == null) {
                            length2 = 0;
                        } else {
                            length2 = this.zzqb.length;
                        }
                        int[] iArr5 = new int[(zzb3 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzqb, 0, iArr5, 0, length2);
                        }
                        while (length2 < iArr5.length - 1) {
                            iArr5[length2] = zzun.zzoa();
                            zzun.zzni();
                            length2++;
                        }
                        iArr5[length2] = zzun.zzoa();
                        this.zzqb = iArr5;
                        continue;
                    case 26:
                        int zzaq3 = zzun.zzaq(zzun.zzoa());
                        int position3 = zzun.getPosition();
                        int i3 = 0;
                        while (zzun.zzrv() > 0) {
                            zzun.zzoa();
                            i3++;
                        }
                        zzun.zzbz(position3);
                        int length7 = this.zzqb == null ? 0 : this.zzqb.length;
                        int[] iArr6 = new int[(i3 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzqb, 0, iArr6, 0, length7);
                        }
                        while (length7 < iArr6.length) {
                            iArr6[length7] = zzun.zzoa();
                            length7++;
                        }
                        this.zzqb = iArr6;
                        zzun.zzar(zzaq3);
                        continue;
                    case 32:
                        this.zzqc = zzun.zzoa();
                        continue;
                    case 40:
                        int zzb4 = zzuz.zzb(zzun, 40);
                        if (this.zzqd == null) {
                            length = 0;
                        } else {
                            length = this.zzqd.length;
                        }
                        int[] iArr7 = new int[(zzb4 + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzqd, 0, iArr7, 0, length);
                        }
                        while (length < iArr7.length - 1) {
                            iArr7[length] = zzun.zzoa();
                            zzun.zzni();
                            length++;
                        }
                        iArr7[length] = zzun.zzoa();
                        this.zzqd = iArr7;
                        continue;
                    case 42:
                        int zzaq4 = zzun.zzaq(zzun.zzoa());
                        int position4 = zzun.getPosition();
                        int i4 = 0;
                        while (zzun.zzrv() > 0) {
                            zzun.zzoa();
                            i4++;
                        }
                        zzun.zzbz(position4);
                        int length8 = this.zzqd == null ? 0 : this.zzqd.length;
                        int[] iArr8 = new int[(i4 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzqd, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = zzun.zzoa();
                            length8++;
                        }
                        this.zzqd = iArr8;
                        zzun.zzar(zzaq4);
                        continue;
                    case 48:
                        this.zzqe = zzun.zzoa();
                        continue;
                    case 56:
                        this.zzqf = zzun.zzoa();
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
}
