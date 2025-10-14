package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

final class zzbas extends zzbaq {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzdqd;
    private int zzdqe;
    private int zzdqf;
    private int zzdqg;
    private int zzdqh;

    private zzbas(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzdqh = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i + i2;
        this.pos = i;
        this.zzdqf = this.pos;
        this.zzdqd = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006a, code lost:
        if (r3[r2] < 0) goto L_0x006c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzacc() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L_0x006c
            byte[] r3 = r5.buffer
            int r2 = r0 + 1
            byte r0 = r3[r0]
            if (r0 < 0) goto L_0x0011
            r5.pos = r2
        L_0x0010:
            return r0
        L_0x0011:
            int r1 = r5.limit
            int r1 = r1 - r2
            r4 = 9
            if (r1 < r4) goto L_0x006c
            int r1 = r2 + 1
            byte r2 = r3[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0026
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
        L_0x0023:
            r5.pos = r1
            goto L_0x0010
        L_0x0026:
            int r2 = r1 + 1
            byte r1 = r3[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0033
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r2
            goto L_0x0023
        L_0x0033:
            int r1 = r2 + 1
            byte r2 = r3[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0041
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0023
        L_0x0041:
            int r2 = r1 + 1
            byte r1 = r3[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
            int r2 = r1 + 1
            byte r1 = r3[r1]
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
            int r2 = r1 + 1
            byte r1 = r3[r1]
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
        L_0x006c:
            long r0 = r5.zzabz()
            int r0 = (int) r0
            goto L_0x0010
        L_0x0072:
            r1 = r2
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbas.zzacc():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b2, code lost:
        if (((long) r4[r3]) < 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzacd() throws java.io.IOException {
        /*
            r10 = this;
            r8 = 0
            int r0 = r10.pos
            int r1 = r10.limit
            if (r1 == r0) goto L_0x00b4
            byte[] r4 = r10.buffer
            int r1 = r0 + 1
            byte r0 = r4[r0]
            if (r0 < 0) goto L_0x0014
            r10.pos = r1
            long r0 = (long) r0
        L_0x0013:
            return r0
        L_0x0014:
            int r2 = r10.limit
            int r2 = r2 - r1
            r3 = 9
            if (r2 < r3) goto L_0x00b4
            int r2 = r1 + 1
            byte r1 = r4[r1]
            int r1 = r1 << 7
            r0 = r0 ^ r1
            if (r0 >= 0) goto L_0x002a
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            long r0 = (long) r0
        L_0x0027:
            r10.pos = r2
            goto L_0x0013
        L_0x002a:
            int r3 = r2 + 1
            byte r1 = r4[r2]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0038
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r2 = r3
            goto L_0x0027
        L_0x0038:
            int r2 = r3 + 1
            byte r1 = r4[r3]
            int r1 = r1 << 21
            r0 = r0 ^ r1
            if (r0 >= 0) goto L_0x0047
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            long r0 = (long) r0
            goto L_0x0027
        L_0x0047:
            long r0 = (long) r0
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 28
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x005b
            r4 = 266354560(0xfe03f80, double:1.315966377E-315)
            long r0 = r0 ^ r4
            r2 = r3
            goto L_0x0027
        L_0x005b:
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r6 = (long) r3
            r3 = 35
            long r6 = r6 << r3
            long r0 = r0 ^ r6
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x006f
            r4 = -34093383808(0xfffffff80fe03f80, double:NaN)
            long r0 = r0 ^ r4
            goto L_0x0027
        L_0x006f:
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 42
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x0084
            r4 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            long r0 = r0 ^ r4
            r2 = r3
            goto L_0x0027
        L_0x0084:
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r6 = (long) r3
            r3 = 49
            long r6 = r6 << r3
            long r0 = r0 ^ r6
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0098
            r4 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            long r0 = r0 ^ r4
            goto L_0x0027
        L_0x0098:
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 56
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            r6 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x00ba
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r4 = (long) r3
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0027
        L_0x00b4:
            long r0 = r10.zzabz()
            goto L_0x0013
        L_0x00ba:
            r2 = r3
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbas.zzacd():long");
    }

    private final int zzace() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzbbu.zzadl();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private final long zzacf() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzbbu.zzadl();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private final void zzacg() {
        this.limit += this.zzdqe;
        int i = this.limit - this.zzdqf;
        if (i > this.zzdqh) {
            this.zzdqe = i - this.zzdqh;
            this.limit -= this.zzdqe;
            return;
        }
        this.zzdqe = 0;
    }

    private final byte zzach() throws IOException {
        if (this.pos == this.limit) {
            throw zzbbu.zzadl();
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzacf());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzace());
    }

    public final String readString() throws IOException {
        int zzacc = zzacc();
        if (zzacc > 0 && zzacc <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzacc, zzbbq.UTF_8);
            this.pos = zzacc + this.pos;
            return str;
        } else if (zzacc == 0) {
            return "";
        } else {
            if (zzacc < 0) {
                throw zzbbu.zzadm();
            }
            throw zzbbu.zzadl();
        }
    }

    public final int zzabk() throws IOException {
        if (zzaca()) {
            this.zzdqg = 0;
            return 0;
        }
        this.zzdqg = zzacc();
        if ((this.zzdqg >>> 3) != 0) {
            return this.zzdqg;
        }
        throw zzbbu.zzado();
    }

    public final long zzabl() throws IOException {
        return zzacd();
    }

    public final long zzabm() throws IOException {
        return zzacd();
    }

    public final int zzabn() throws IOException {
        return zzacc();
    }

    public final long zzabo() throws IOException {
        return zzacf();
    }

    public final int zzabp() throws IOException {
        return zzace();
    }

    public final boolean zzabq() throws IOException {
        return zzacd() != 0;
    }

    public final String zzabr() throws IOException {
        int zzacc = zzacc();
        if (zzacc <= 0 || zzacc > this.limit - this.pos) {
            if (zzacc == 0) {
                return "";
            }
            if (zzacc <= 0) {
                throw zzbbu.zzadm();
            }
            throw zzbbu.zzadl();
        } else if (!zzbem.zzf(this.buffer, this.pos, this.pos + zzacc)) {
            throw zzbbu.zzads();
        } else {
            int i = this.pos;
            this.pos += zzacc;
            return new String(this.buffer, i, zzacc, zzbbq.UTF_8);
        }
    }

    public final zzbah zzabs() throws IOException {
        byte[] bArr;
        int zzacc = zzacc();
        if (zzacc > 0 && zzacc <= this.limit - this.pos) {
            zzbah zzc = zzbah.zzc(this.buffer, this.pos, zzacc);
            this.pos = zzacc + this.pos;
            return zzc;
        } else if (zzacc == 0) {
            return zzbah.zzdpq;
        } else {
            if (zzacc > 0 && zzacc <= this.limit - this.pos) {
                int i = this.pos;
                this.pos = zzacc + this.pos;
                bArr = Arrays.copyOfRange(this.buffer, i, this.pos);
            } else if (zzacc > 0) {
                throw zzbbu.zzadl();
            } else if (zzacc == 0) {
                bArr = zzbbq.zzduq;
            } else {
                throw zzbbu.zzadm();
            }
            return zzbah.zzp(bArr);
        }
    }

    public final int zzabt() throws IOException {
        return zzacc();
    }

    public final int zzabu() throws IOException {
        return zzacc();
    }

    public final int zzabv() throws IOException {
        return zzace();
    }

    public final long zzabw() throws IOException {
        return zzacf();
    }

    public final int zzabx() throws IOException {
        return zzbu(zzacc());
    }

    public final long zzaby() throws IOException {
        return zzl(zzacd());
    }

    /* access modifiers changed from: package-private */
    public final long zzabz() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzach = zzach();
            j |= ((long) (zzach & ByteCompanionObject.MAX_VALUE)) << i;
            if ((zzach & ByteCompanionObject.MIN_VALUE) == 0) {
                return j;
            }
        }
        throw zzbbu.zzadn();
    }

    public final boolean zzaca() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzacb() {
        return this.pos - this.zzdqf;
    }

    public final void zzbp(int i) throws zzbbu {
        if (this.zzdqg != i) {
            throw zzbbu.zzadp();
        }
    }

    public final boolean zzbq(int i) throws IOException {
        int zzabk;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.limit - this.pos >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        if (bArr[i3] >= 0) {
                            return true;
                        }
                        i2++;
                    }
                    throw zzbbu.zzadn();
                }
                while (i2 < 10) {
                    if (zzach() >= 0) {
                        return true;
                    }
                    i2++;
                }
                throw zzbbu.zzadn();
            case 1:
                zzbt(8);
                return true;
            case 2:
                zzbt(zzacc());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzbt(4);
                return true;
            default:
                throw zzbbu.zzadq();
        }
        do {
            zzabk = zzabk();
            if (zzabk == 0 || !zzbq(zzabk)) {
                zzbp(((i >>> 3) << 3) | 4);
                return true;
            }
            zzabk = zzabk();
            zzbp(((i >>> 3) << 3) | 4);
            return true;
        } while (!zzbq(zzabk));
        zzbp(((i >>> 3) << 3) | 4);
        return true;
    }

    public final int zzbr(int i) throws zzbbu {
        if (i < 0) {
            throw zzbbu.zzadm();
        }
        int zzacb = zzacb() + i;
        int i2 = this.zzdqh;
        if (zzacb > i2) {
            throw zzbbu.zzadl();
        }
        this.zzdqh = zzacb;
        zzacg();
        return i2;
    }

    public final void zzbs(int i) {
        this.zzdqh = i;
        zzacg();
    }

    public final void zzbt(int i) throws IOException {
        if (i >= 0 && i <= this.limit - this.pos) {
            this.pos += i;
        } else if (i < 0) {
            throw zzbbu.zzadm();
        } else {
            throw zzbbu.zzadl();
        }
    }
}
