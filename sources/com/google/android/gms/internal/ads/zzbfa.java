package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzbfa {
    private final ByteBuffer zzebj;

    private zzbfa(ByteBuffer byteBuffer) {
        this.zzebj = byteBuffer;
        this.zzebj.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzbfa(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence) {
        int i;
        int i2 = 0;
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length && charSequence.charAt(i3) < 128) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 >= length) {
                i = i4;
                break;
            }
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i4 += (127 - charAt) >>> 31;
                i3++;
            } else {
                int length2 = charSequence.length();
                while (i3 < length2) {
                    char charAt2 = charSequence.charAt(i3);
                    if (charAt2 < 2048) {
                        i2 += (127 - charAt2) >>> 31;
                    } else {
                        i2 += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i3) < 65536) {
                                throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3).toString());
                            }
                            i3++;
                        }
                    }
                    i3++;
                }
                i = i4 + i2;
            }
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(((long) i) + 4294967296L).toString());
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        int i3 = 0;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byte[] array = byteBuffer.array();
                int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                int remaining = byteBuffer.remaining();
                int length = charSequence.length();
                int i4 = arrayOffset + remaining;
                while (i3 < length && i3 + arrayOffset < i4) {
                    char charAt = charSequence.charAt(i3);
                    if (charAt >= 128) {
                        break;
                    }
                    array[arrayOffset + i3] = (byte) charAt;
                    i3++;
                }
                if (i3 == length) {
                    i = arrayOffset + length;
                } else {
                    int i5 = arrayOffset + i3;
                    while (i3 < length) {
                        char charAt2 = charSequence.charAt(i3);
                        if (charAt2 < 128 && i5 < i4) {
                            i2 = i5 + 1;
                            array[i5] = (byte) charAt2;
                        } else if (charAt2 < 2048 && i5 <= i4 - 2) {
                            int i6 = i5 + 1;
                            array[i5] = (byte) ((charAt2 >>> 6) | 960);
                            i2 = i6 + 1;
                            array[i6] = (byte) ((charAt2 & '?') | 128);
                        } else if ((charAt2 < 55296 || 57343 < charAt2) && i5 <= i4 - 3) {
                            int i7 = i5 + 1;
                            array[i5] = (byte) ((charAt2 >>> 12) | 480);
                            int i8 = i7 + 1;
                            array[i7] = (byte) (((charAt2 >>> 6) & 63) | 128);
                            i2 = i8 + 1;
                            array[i8] = (byte) ((charAt2 & '?') | 128);
                        } else if (i5 <= i4 - 4) {
                            if (i3 + 1 != charSequence.length()) {
                                i3++;
                                char charAt3 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(charAt2, charAt3)) {
                                    int codePoint = Character.toCodePoint(charAt2, charAt3);
                                    int i9 = i5 + 1;
                                    array[i5] = (byte) ((codePoint >>> 18) | 240);
                                    int i10 = i9 + 1;
                                    array[i9] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i11 = i10 + 1;
                                    array[i10] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i2 = i11 + 1;
                                    array[i11] = (byte) ((codePoint & 63) | 128);
                                }
                            }
                            throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3 - 1).toString());
                        } else {
                            throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charAt2).append(" at index ").append(i5).toString());
                        }
                        i3++;
                        i5 = i2;
                    }
                    i = i5;
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            int length2 = charSequence.length();
            while (i3 < length2) {
                char charAt4 = charSequence.charAt(i3);
                if (charAt4 < 128) {
                    byteBuffer.put((byte) charAt4);
                } else if (charAt4 < 2048) {
                    byteBuffer.put((byte) ((charAt4 >>> 6) | 960));
                    byteBuffer.put((byte) ((charAt4 & '?') | 128));
                } else if (charAt4 < 55296 || 57343 < charAt4) {
                    byteBuffer.put((byte) ((charAt4 >>> 12) | 480));
                    byteBuffer.put((byte) (((charAt4 >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((charAt4 & '?') | 128));
                } else {
                    if (i3 + 1 != charSequence.length()) {
                        i3++;
                        char charAt5 = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(charAt4, charAt5)) {
                            int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                            byteBuffer.put((byte) ((codePoint2 >>> 18) | 240));
                            byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                        }
                    }
                    throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3 - 1).toString());
                }
                i3++;
            }
        }
    }

    public static int zzb(int i, zzbfi zzbfi) {
        int zzcd = zzcd(i);
        int zzacw = zzbfi.zzacw();
        return zzcd + zzacw + zzcl(zzacw);
    }

    public static int zzb(int i, byte[] bArr) {
        return zzcd(i) + zzv(bArr);
    }

    public static int zzcd(int i) {
        return zzcl(i << 3);
    }

    public static int zzce(int i) {
        if (i >= 0) {
            return zzcl(i);
        }
        return 10;
    }

    public static int zzcl(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzd(int i, long j) {
        return zzcd(i) + zzy(j);
    }

    private final void zzdd(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zzebj.hasRemaining()) {
            throw new zzbfb(this.zzebj.position(), this.zzebj.limit());
        }
        this.zzebj.put(b);
    }

    public static int zze(int i, long j) {
        return zzcd(i) + zzy(j);
    }

    public static int zzeo(String str) {
        int zza = zza(str);
        return zza + zzcl(zza);
    }

    public static int zzg(int i, String str) {
        return zzcd(i) + zzeo(str);
    }

    public static zzbfa zzj(byte[] bArr, int i, int i2) {
        return new zzbfa(bArr, 0, i2);
    }

    public static int zzq(int i, int i2) {
        return zzcd(i) + zzce(i2);
    }

    public static zzbfa zzu(byte[] bArr) {
        return zzj(bArr, 0, bArr.length);
    }

    public static int zzv(byte[] bArr) {
        return zzcl(bArr.length) + bArr.length;
    }

    private final void zzx(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzdd((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzdd((int) j);
    }

    public static int zzy(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        return (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public final void zza(int i, long j) throws IOException {
        zzl(i, 0);
        zzx(j);
    }

    public final void zza(int i, zzbfi zzbfi) throws IOException {
        zzl(i, 2);
        if (zzbfi.zzebt < 0) {
            zzbfi.zzacw();
        }
        zzde(zzbfi.zzebt);
        zzbfi.zza(this);
    }

    public final void zza(int i, byte[] bArr) throws IOException {
        zzl(i, 2);
        zzde(bArr.length);
        zzw(bArr);
    }

    public final void zzacl() {
        if (this.zzebj.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzebj.remaining())}));
        }
    }

    public final void zzde(int i) throws IOException {
        while ((i & -128) != 0) {
            zzdd((i & 127) | 128);
            i >>>= 7;
        }
        zzdd(i);
    }

    public final void zzf(int i, String str) throws IOException {
        zzl(i, 2);
        try {
            int zzcl = zzcl(str.length());
            if (zzcl == zzcl(str.length() * 3)) {
                int position = this.zzebj.position();
                if (this.zzebj.remaining() < zzcl) {
                    throw new zzbfb(zzcl + position, this.zzebj.limit());
                }
                this.zzebj.position(position + zzcl);
                zza((CharSequence) str, this.zzebj);
                int position2 = this.zzebj.position();
                this.zzebj.position(position);
                zzde((position2 - position) - zzcl);
                this.zzebj.position(position2);
                return;
            }
            zzde(zza(str));
            zza((CharSequence) str, this.zzebj);
        } catch (BufferOverflowException e) {
            zzbfb zzbfb = new zzbfb(this.zzebj.position(), this.zzebj.limit());
            zzbfb.initCause(e);
            throw zzbfb;
        }
    }

    public final void zzf(int i, boolean z) throws IOException {
        int i2 = 0;
        zzl(i, 0);
        if (z) {
            i2 = 1;
        }
        byte b = (byte) i2;
        if (!this.zzebj.hasRemaining()) {
            throw new zzbfb(this.zzebj.position(), this.zzebj.limit());
        }
        this.zzebj.put(b);
    }

    public final void zzi(int i, long j) throws IOException {
        zzl(i, 0);
        zzx(j);
    }

    public final void zzl(int i, int i2) throws IOException {
        zzde((i << 3) | i2);
    }

    public final void zzm(int i, int i2) throws IOException {
        zzl(i, 0);
        if (i2 >= 0) {
            zzde(i2);
        } else {
            zzx((long) i2);
        }
    }

    public final void zzw(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzebj.remaining() >= length) {
            this.zzebj.put(bArr, 0, length);
            return;
        }
        throw new zzbfb(this.zzebj.position(), this.zzebj.limit());
    }
}
