package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzaby {
    private final ByteBuffer zzbxf;

    private zzaby(ByteBuffer byteBuffer) {
        this.zzbxf = byteBuffer;
        this.zzbxf.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzaby(byte[] bArr, int i, int i2) {
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

    private final void zzan(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzap((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzap((int) j);
    }

    public static int zzao(int i) {
        if (i >= 0) {
            return zzas(i);
        }
        return 10;
    }

    public static int zzao(long j) {
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

    private final void zzap(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zzbxf.hasRemaining()) {
            throw new zzabz(this.zzbxf.position(), this.zzbxf.limit());
        }
        this.zzbxf.put(b);
    }

    public static int zzaq(int i) {
        return zzas(i << 3);
    }

    public static int zzas(int i) {
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

    public static int zzb(int i, zzacg zzacg) {
        int zzaq = zzaq(i);
        int zzvv = zzacg.zzvv();
        return zzaq + zzvv + zzas(zzvv);
    }

    public static zzaby zzb(byte[] bArr, int i, int i2) {
        return new zzaby(bArr, 0, i2);
    }

    public static int zzc(int i, long j) {
        return zzaq(i) + zzao(j);
    }

    public static int zzc(int i, String str) {
        return zzaq(i) + zzfk(str);
    }

    public static int zzf(int i, int i2) {
        return zzaq(i) + zzao(i2);
    }

    public static int zzfk(String str) {
        int zza = zza(str);
        return zza + zzas(zza);
    }

    public static zzaby zzj(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public final void zza(int i, double d) throws IOException {
        zzg(i, 1);
        long doubleToLongBits = Double.doubleToLongBits(d);
        if (this.zzbxf.remaining() < 8) {
            throw new zzabz(this.zzbxf.position(), this.zzbxf.limit());
        }
        this.zzbxf.putLong(doubleToLongBits);
    }

    public final void zza(int i, float f) throws IOException {
        zzg(i, 5);
        int floatToIntBits = Float.floatToIntBits(f);
        if (this.zzbxf.remaining() < 4) {
            throw new zzabz(this.zzbxf.position(), this.zzbxf.limit());
        }
        this.zzbxf.putInt(floatToIntBits);
    }

    public final void zza(int i, long j) throws IOException {
        zzg(i, 0);
        zzan(j);
    }

    public final void zza(int i, zzacg zzacg) throws IOException {
        zzg(i, 2);
        zzb(zzacg);
    }

    public final void zza(int i, boolean z) throws IOException {
        int i2 = 0;
        zzg(i, 0);
        if (z) {
            i2 = 1;
        }
        byte b = (byte) i2;
        if (!this.zzbxf.hasRemaining()) {
            throw new zzabz(this.zzbxf.position(), this.zzbxf.limit());
        }
        this.zzbxf.put(b);
    }

    public final void zzar(int i) throws IOException {
        while ((i & -128) != 0) {
            zzap((i & 127) | 128);
            i >>>= 7;
        }
        zzap(i);
    }

    public final void zzb(int i, long j) throws IOException {
        zzg(i, 0);
        zzan(j);
    }

    public final void zzb(int i, String str) throws IOException {
        zzg(i, 2);
        try {
            int zzas = zzas(str.length());
            if (zzas == zzas(str.length() * 3)) {
                int position = this.zzbxf.position();
                if (this.zzbxf.remaining() < zzas) {
                    throw new zzabz(zzas + position, this.zzbxf.limit());
                }
                this.zzbxf.position(position + zzas);
                zza((CharSequence) str, this.zzbxf);
                int position2 = this.zzbxf.position();
                this.zzbxf.position(position);
                zzar((position2 - position) - zzas);
                this.zzbxf.position(position2);
                return;
            }
            zzar(zza(str));
            zza((CharSequence) str, this.zzbxf);
        } catch (BufferOverflowException e) {
            zzabz zzabz = new zzabz(this.zzbxf.position(), this.zzbxf.limit());
            zzabz.initCause(e);
            throw zzabz;
        }
    }

    public final void zzb(zzacg zzacg) throws IOException {
        zzar(zzacg.zzvu());
        zzacg.zza(this);
    }

    public final void zze(int i, int i2) throws IOException {
        zzg(i, 0);
        if (i2 >= 0) {
            zzar(i2);
        } else {
            zzan((long) i2);
        }
    }

    public final void zzg(int i, int i2) throws IOException {
        zzar((i << 3) | i2);
    }

    public final void zzk(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzbxf.remaining() >= length) {
            this.zzbxf.put(bArr, 0, length);
            return;
        }
        throw new zzabz(this.zzbxf.position(), this.zzbxf.limit());
    }

    public final void zzvn() {
        if (this.zzbxf.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzbxf.remaining())}));
        }
    }
}
