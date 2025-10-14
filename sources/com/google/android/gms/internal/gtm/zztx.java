package com.google.android.gms.internal.gtm;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zztx {
    private static final Logger logger = Logger.getLogger(zztx.class.getName());
    private static final Class<?> zzavt = zzpp.zznb();
    private static final boolean zzawt = zzrp();
    private static final Unsafe zzbcx = zzro();
    private static final boolean zzbet = zzn(Long.TYPE);
    private static final boolean zzbeu = zzn(Integer.TYPE);
    private static final zzd zzbev;
    private static final boolean zzbew = zzrq();
    static final long zzbex = ((long) zzl(byte[].class));
    private static final long zzbey = ((long) zzl(boolean[].class));
    private static final long zzbez = ((long) zzm(boolean[].class));
    private static final long zzbfa = ((long) zzl(int[].class));
    private static final long zzbfb = ((long) zzm(int[].class));
    private static final long zzbfc = ((long) zzl(long[].class));
    private static final long zzbfd = ((long) zzm(long[].class));
    private static final long zzbfe = ((long) zzl(float[].class));
    private static final long zzbff = ((long) zzm(float[].class));
    private static final long zzbfg = ((long) zzl(double[].class));
    private static final long zzbfh = ((long) zzm(double[].class));
    private static final long zzbfi = ((long) zzl(Object[].class));
    private static final long zzbfj = ((long) zzm(Object[].class));
    private static final long zzbfk;
    private static final int zzbfl = ((int) (zzbex & 7));
    static final boolean zzbfm;

    private zztx() {
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            if (zztx.zzbfm) {
                return zztx.zzq(obj, j);
            }
            return zztx.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zztx.zzbfm) {
                zztx.zza(obj, j, b);
            } else {
                zztx.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zztx.zzbfm) {
                return zztx.zzs(obj, j);
            }
            return zztx.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zztx.zzbfm) {
                zztx.zzb(obj, j, z);
            } else {
                zztx.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray(j2, bArr, (int) j, (int) j3);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            this.zzbfn.putByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            return this.zzbfn.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzbfn.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zzbfn.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzbfn.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zzbfn.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzbfn.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zzbfn.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzbfn.putDouble(obj, j, d);
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            this.zzbfn.copyMemory(bArr, zztx.zzbex + j, (Object) null, j2, j3);
        }
    }

    static boolean zzrm() {
        return zzawt;
    }

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte((int) (-1 & j), b);
        }

        public final byte zzy(Object obj, long j) {
            if (zztx.zzbfm) {
                return zztx.zzq(obj, j);
            }
            return zztx.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zztx.zzbfm) {
                zztx.zza(obj, j, b);
            } else {
                zztx.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zztx.zzbfm) {
                return zztx.zzs(obj, j);
            }
            return zztx.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zztx.zzbfm) {
                zztx.zzb(obj, j, z);
            } else {
                zztx.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray((int) (-1 & j2), bArr, (int) j, (int) j3);
        }
    }

    static abstract class zzd {
        Unsafe zzbfn;

        zzd(Unsafe unsafe) {
            this.zzbfn = unsafe;
        }

        public abstract void zza(long j, byte b);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zza(byte[] bArr, long j, long j2, long j3);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzbfn.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzbfn.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzbfn.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzbfn.putLong(obj, j, j2);
        }
    }

    static boolean zzrn() {
        return zzbew;
    }

    static <T> T zzk(Class<T> cls) {
        try {
            return zzbcx.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzl(Class<?> cls) {
        if (zzawt) {
            return zzbev.zzbfn.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzm(Class<?> cls) {
        if (zzawt) {
            return zzbev.zzbfn.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzbev.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzbev.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzbev.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzbev.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzbev.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzbev.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzbev.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzbev.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzbev.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzbev.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzbev.zzbfn.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzbev.zzbfn.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzbev.zzy(bArr, zzbex + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzbev.zze(bArr, zzbex + j, b);
    }

    static void zza(byte[] bArr, long j, long j2, long j3) {
        zzbev.zza(bArr, j, j2, j3);
    }

    static void zza(long j, byte b) {
        zzbev.zza(j, b);
    }

    static long zzb(ByteBuffer byteBuffer) {
        return zzbev.zzl(byteBuffer, zzbfk);
    }

    static Unsafe zzro() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzty());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzrp() {
        if (zzbcx == null) {
            return false;
        }
        try {
            Class<?> cls = zzbcx.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzpp.zzna()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzrq() {
        if (zzbcx == null) {
            return false;
        }
        try {
            Class<?> cls = zzbcx.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzrr() == null) {
                return false;
            }
            if (zzpp.zzna()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzn(Class<?> cls) {
        if (!zzpp.zzna()) {
            return false;
        }
        try {
            Class<?> cls2 = zzavt;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static Field zzrr() {
        Field zzb2;
        if (zzpp.zzna() && (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzb2;
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }

    static int zza(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int numberOfTrailingZeros;
        if (i < 0 || i2 < 0 || i3 < 0 || i + i3 > bArr.length || i2 + i3 > bArr2.length) {
            throw new IndexOutOfBoundsException();
        }
        int i4 = 0;
        if (zzawt) {
            int i5 = (zzbfl + i) & 7;
            while (i4 < i3 && (i5 & 7) != 0) {
                if (bArr[i + i4] != bArr2[i2 + i4]) {
                    return i4;
                }
                i4++;
                i5++;
            }
            int i6 = ((i3 - i4) & -8) + i4;
            int i7 = i4;
            while (i7 < i6) {
                long zzl = zzl(bArr, zzbex + ((long) i) + ((long) i7));
                long zzl2 = zzl(bArr2, zzbex + ((long) i2) + ((long) i7));
                if (zzl != zzl2) {
                    if (zzbfm) {
                        numberOfTrailingZeros = Long.numberOfLeadingZeros(zzl ^ zzl2);
                    } else {
                        numberOfTrailingZeros = Long.numberOfTrailingZeros(zzl ^ zzl2);
                    }
                    return (numberOfTrailingZeros >> 3) + i7;
                }
                i7 += 8;
            }
            i4 = i7;
        }
        while (i4 < i3) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return i4;
            }
            i4++;
        }
        return -1;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable th) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) (((-1 ^ j) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) ((3 & j) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        int i = ((((int) j) ^ -1) & 3) << 3;
        zzb(obj, j & -4, (zzk(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        int i = (((int) j) & 3) << 3;
        zzb(obj, j & -4, (zzk(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, (byte) (z ? 1 : 0));
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, (byte) (z ? 1 : 0));
    }

    static {
        long j;
        boolean z;
        zzd zzd2 = null;
        if (zzbcx != null) {
            if (!zzpp.zzna()) {
                zzd2 = new zzc(zzbcx);
            } else if (zzbet) {
                zzd2 = new zzb(zzbcx);
            } else if (zzbeu) {
                zzd2 = new zza(zzbcx);
            }
        }
        zzbev = zzd2;
        Field zzrr = zzrr();
        if (zzrr == null || zzbev == null) {
            j = -1;
        } else {
            j = zzbev.zzbfn.objectFieldOffset(zzrr);
        }
        zzbfk = j;
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            z = true;
        } else {
            z = false;
        }
        zzbfm = z;
    }
}
