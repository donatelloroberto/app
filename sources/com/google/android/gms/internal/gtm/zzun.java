package com.google.android.gms.internal.gtm;

import java.io.IOException;
import kotlin.jvm.internal.ByteCompanionObject;

public final class zzun {
    private final byte[] buffer;
    private int zzawf;
    private int zzawg = 64;
    private int zzawh = 67108864;
    private int zzawl;
    private int zzawn;
    private int zzawo = Integer.MAX_VALUE;
    private final int zzbgu;
    private final int zzbgv;
    private int zzbgw;
    private int zzbgx;
    private zzqe zzbgy;

    public static zzun zzk(byte[] bArr) {
        return zzj(bArr, 0, bArr.length);
    }

    public static zzun zzj(byte[] bArr, int i, int i2) {
        return new zzun(bArr, 0, i2);
    }

    public final int zzni() throws IOException {
        if (this.zzbgx == this.zzbgw) {
            this.zzawn = 0;
            return 0;
        }
        this.zzawn = zzoa();
        if (this.zzawn != 0) {
            return this.zzawn;
        }
        throw new zzuv("Protocol message contained an invalid tag (zero).");
    }

    public final void zzan(int i) throws zzuv {
        if (this.zzawn != i) {
            throw new zzuv("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzao(int i) throws IOException {
        int zzni;
        switch (i & 7) {
            case 0:
                zzoa();
                return true;
            case 1:
                zzof();
                zzof();
                zzof();
                zzof();
                zzof();
                zzof();
                zzof();
                zzof();
                return true;
            case 2:
                zzas(zzoa());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzoc();
                return true;
            default:
                throw new zzuv("Protocol message tag had invalid wire type.");
        }
        do {
            zzni = zzni();
            if (zzni == 0 || !zzao(zzni)) {
                zzan(((i >>> 3) << 3) | 4);
                return true;
            }
            zzni = zzni();
            zzan(((i >>> 3) << 3) | 4);
            return true;
        } while (!zzao(zzni));
        zzan(((i >>> 3) << 3) | 4);
        return true;
    }

    public final boolean zzno() throws IOException {
        return zzoa() != 0;
    }

    public final String readString() throws IOException {
        int zzoa = zzoa();
        if (zzoa < 0) {
            throw zzuv.zzsb();
        } else if (zzoa > this.zzbgw - this.zzbgx) {
            throw zzuv.zzsa();
        } else {
            String str = new String(this.buffer, this.zzbgx, zzoa, zzuu.UTF_8);
            this.zzbgx = zzoa + this.zzbgx;
            return str;
        }
    }

    public final void zza(zzuw zzuw, int i) throws IOException {
        if (this.zzawf >= this.zzawg) {
            throw zzuv.zzsd();
        }
        this.zzawf++;
        zzuw.zza(this);
        zzan((i << 3) | 4);
        this.zzawf--;
    }

    public final void zza(zzuw zzuw) throws IOException {
        int zzoa = zzoa();
        if (this.zzawf >= this.zzawg) {
            throw zzuv.zzsd();
        }
        int zzaq = zzaq(zzoa);
        this.zzawf++;
        zzuw.zza(this);
        zzan(0);
        this.zzawf--;
        zzar(zzaq);
    }

    public final int zzoa() throws IOException {
        byte zzof = zzof();
        if (zzof >= 0) {
            return zzof;
        }
        byte b = zzof & ByteCompanionObject.MAX_VALUE;
        byte zzof2 = zzof();
        if (zzof2 >= 0) {
            return b | (zzof2 << 7);
        }
        byte b2 = b | ((zzof2 & ByteCompanionObject.MAX_VALUE) << 7);
        byte zzof3 = zzof();
        if (zzof3 >= 0) {
            return b2 | (zzof3 << 14);
        }
        byte b3 = b2 | ((zzof3 & ByteCompanionObject.MAX_VALUE) << 14);
        byte zzof4 = zzof();
        if (zzof4 >= 0) {
            return b3 | (zzof4 << 21);
        }
        byte b4 = b3 | ((zzof4 & ByteCompanionObject.MAX_VALUE) << 21);
        byte zzof5 = zzof();
        byte b5 = b4 | (zzof5 << 28);
        if (zzof5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (zzof() >= 0) {
                return b5;
            }
        }
        throw zzuv.zzsc();
    }

    public final long zzob() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzof = zzof();
            j |= ((long) (zzof & ByteCompanionObject.MAX_VALUE)) << i;
            if ((zzof & ByteCompanionObject.MIN_VALUE) == 0) {
                return j;
            }
        }
        throw zzuv.zzsc();
    }

    public final int zzoc() throws IOException {
        return (zzof() & 255) | ((zzof() & 255) << 8) | ((zzof() & 255) << 16) | ((zzof() & 255) << 24);
    }

    private zzun(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzbgu = i;
        int i3 = i + i2;
        this.zzbgw = i3;
        this.zzbgv = i3;
        this.zzbgx = i;
    }

    private final zzqe zzru() throws IOException {
        if (this.zzbgy == null) {
            this.zzbgy = zzqe.zzd(this.buffer, this.zzbgu, this.zzbgv);
        }
        int zznz = this.zzbgy.zznz();
        int i = this.zzbgx - this.zzbgu;
        if (zznz > i) {
            throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", new Object[]{Integer.valueOf(zznz), Integer.valueOf(i)}));
        }
        this.zzbgy.zzas(i - zznz);
        this.zzbgy.zzap(this.zzawg - this.zzawf);
        return this.zzbgy;
    }

    public final <T extends zzrc<T, ?>> T zza(zzsu<T> zzsu) throws IOException {
        try {
            T t = (zzrc) zzru().zza(zzsu, zzqp.zzor());
            zzao(this.zzawn);
            return t;
        } catch (zzrk e) {
            throw new zzuv("", e);
        }
    }

    public final int zzaq(int i) throws zzuv {
        if (i < 0) {
            throw zzuv.zzsb();
        }
        int i2 = this.zzbgx + i;
        int i3 = this.zzawo;
        if (i2 > i3) {
            throw zzuv.zzsa();
        }
        this.zzawo = i2;
        zzoe();
        return i3;
    }

    private final void zzoe() {
        this.zzbgw += this.zzawl;
        int i = this.zzbgw;
        if (i > this.zzawo) {
            this.zzawl = i - this.zzawo;
            this.zzbgw -= this.zzawl;
            return;
        }
        this.zzawl = 0;
    }

    public final void zzar(int i) {
        this.zzawo = i;
        zzoe();
    }

    public final int zzrv() {
        if (this.zzawo == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzawo - this.zzbgx;
    }

    public final int getPosition() {
        return this.zzbgx - this.zzbgu;
    }

    public final byte[] zzt(int i, int i2) {
        if (i2 == 0) {
            return zzuz.zzbhw;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzbgu + i, bArr, 0, i2);
        return bArr;
    }

    public final void zzbz(int i) {
        zzu(i, this.zzawn);
    }

    /* access modifiers changed from: package-private */
    public final void zzu(int i, int i2) {
        if (i > this.zzbgx - this.zzbgu) {
            throw new IllegalArgumentException(new StringBuilder(50).append("Position ").append(i).append(" is beyond current ").append(this.zzbgx - this.zzbgu).toString());
        } else if (i < 0) {
            throw new IllegalArgumentException(new StringBuilder(24).append("Bad position ").append(i).toString());
        } else {
            this.zzbgx = this.zzbgu + i;
            this.zzawn = i2;
        }
    }

    private final byte zzof() throws IOException {
        if (this.zzbgx == this.zzbgw) {
            throw zzuv.zzsa();
        }
        byte[] bArr = this.buffer;
        int i = this.zzbgx;
        this.zzbgx = i + 1;
        return bArr[i];
    }

    private final void zzas(int i) throws IOException {
        if (i < 0) {
            throw zzuv.zzsb();
        } else if (this.zzbgx + i > this.zzawo) {
            zzas(this.zzawo - this.zzbgx);
            throw zzuv.zzsa();
        } else if (i <= this.zzbgw - this.zzbgx) {
            this.zzbgx += i;
        } else {
            throw zzuv.zzsa();
        }
    }
}
