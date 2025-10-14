package com.google.android.gms.internal.ads;

import java.io.IOException;
import kotlin.jvm.internal.ByteCompanionObject;

final class zzbad {
    static int zza(int i, byte[] bArr, int i2, int i3, zzbae zzbae) throws zzbbu {
        if ((i >>> 3) == 0) {
            throw zzbbu.zzado();
        }
        switch (i & 7) {
            case 0:
                return zzb(bArr, i2, zzbae);
            case 1:
                return i2 + 8;
            case 2:
                return zza(bArr, i2, zzbae) + zzbae.zzdpl;
            case 3:
                int i4 = (i & -8) | 4;
                int i5 = 0;
                int i6 = i2;
                while (i6 < i3) {
                    i6 = zza(bArr, i6, zzbae);
                    i5 = zzbae.zzdpl;
                    if (i5 != i4) {
                        i6 = zza(i5, bArr, i6, i3, zzbae);
                    } else if (i6 > i3 && i5 == i4) {
                        return i6;
                    } else {
                        throw zzbbu.zzadr();
                    }
                }
                if (i6 > i3) {
                }
                throw zzbbu.zzadr();
            case 5:
                return i2 + 4;
            default:
                throw zzbbu.zzado();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbbt<?> zzbbt, zzbae zzbae) {
        zzbbp zzbbp = (zzbbp) zzbbt;
        int zza = zza(bArr, i2, zzbae);
        zzbbp.zzco(zzbae.zzdpl);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzbae);
            if (i != zzbae.zzdpl) {
                break;
            }
            zza = zza(bArr, zza2, zzbae);
            zzbbp.zzco(zzbae.zzdpl);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbef zzbef, zzbae zzbae) throws IOException {
        if ((i >>> 3) == 0) {
            throw zzbbu.zzado();
        }
        switch (i & 7) {
            case 0:
                int zzb = zzb(bArr, i2, zzbae);
                zzbef.zzb(i, Long.valueOf(zzbae.zzdpm));
                return zzb;
            case 1:
                zzbef.zzb(i, Long.valueOf(zzf(bArr, i2)));
                return i2 + 8;
            case 2:
                int zza = zza(bArr, i2, zzbae);
                int i4 = zzbae.zzdpl;
                if (i4 == 0) {
                    zzbef.zzb(i, zzbah.zzdpq);
                } else {
                    zzbef.zzb(i, zzbah.zzc(bArr, zza, i4));
                }
                return zza + i4;
            case 3:
                zzbef zzagd = zzbef.zzagd();
                int i5 = (i & -8) | 4;
                int i6 = 0;
                int i7 = i2;
                while (true) {
                    if (i7 < i3) {
                        int zza2 = zza(bArr, i7, zzbae);
                        i6 = zzbae.zzdpl;
                        if (i6 != i5) {
                            i7 = zza(i6, bArr, zza2, i3, zzagd, zzbae);
                        } else {
                            i7 = zza2;
                        }
                    }
                }
                if (i7 > i3 || i6 != i5) {
                    throw zzbbu.zzadr();
                }
                zzbef.zzb(i, zzagd);
                return i7;
            case 5:
                zzbef.zzb(i, Integer.valueOf(zze(bArr, i2)));
                return i2 + 4;
            default:
                throw zzbbu.zzado();
        }
    }

    static int zza(int i, byte[] bArr, int i2, zzbae zzbae) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzbae.zzdpl = i3 | (b << 7);
            return i4;
        }
        int i5 = ((b & ByteCompanionObject.MAX_VALUE) << 7) | i3;
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzbae.zzdpl = (b2 << 14) | i5;
            return i6;
        }
        int i7 = ((b2 & ByteCompanionObject.MAX_VALUE) << 14) | i5;
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzbae.zzdpl = i7 | (b3 << 21);
            return i8;
        }
        int i9 = ((b3 & ByteCompanionObject.MAX_VALUE) << 21) | i7;
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzbae.zzdpl = i9 | (b4 << 28);
            return i10;
        }
        int i11 = ((b4 & ByteCompanionObject.MAX_VALUE) << 28) | i9;
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzbae.zzdpl = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zza(byte[] bArr, int i, zzbae zzbae) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzbae);
        }
        zzbae.zzdpl = b;
        return i2;
    }

    static int zza(byte[] bArr, int i, zzbbt<?> zzbbt, zzbae zzbae) throws IOException {
        zzbbp zzbbp = (zzbbp) zzbbt;
        int zza = zza(bArr, i, zzbae);
        int i2 = zzbae.zzdpl + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzbae);
            zzbbp.zzco(zzbae.zzdpl);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzbbu.zzadl();
    }

    static int zzb(byte[] bArr, int i, zzbae zzbae) {
        int i2 = 7;
        int i3 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzbae.zzdpm = j;
        } else {
            long j2 = 127 & j;
            byte b = bArr[i3];
            long j3 = j2 | (((long) (b & ByteCompanionObject.MAX_VALUE)) << 7);
            i3++;
            while (b < 0) {
                b = bArr[i3];
                i2 += 7;
                j3 |= ((long) (b & ByteCompanionObject.MAX_VALUE)) << i2;
                i3++;
            }
            zzbae.zzdpm = j3;
        }
        return i3;
    }

    static int zzc(byte[] bArr, int i, zzbae zzbae) {
        int zza = zza(bArr, i, zzbae);
        int i2 = zzbae.zzdpl;
        if (i2 == 0) {
            zzbae.zzdpn = "";
            return zza;
        }
        zzbae.zzdpn = new String(bArr, zza, i2, zzbbq.UTF_8);
        return zza + i2;
    }

    static int zzd(byte[] bArr, int i, zzbae zzbae) throws IOException {
        int zza = zza(bArr, i, zzbae);
        int i2 = zzbae.zzdpl;
        if (i2 == 0) {
            zzbae.zzdpn = "";
            return zza;
        } else if (!zzbem.zzf(bArr, zza, zza + i2)) {
            throw zzbbu.zzads();
        } else {
            zzbae.zzdpn = new String(bArr, zza, i2, zzbbq.UTF_8);
            return zza + i2;
        }
    }

    static int zze(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    static int zze(byte[] bArr, int i, zzbae zzbae) {
        int zza = zza(bArr, i, zzbae);
        int i2 = zzbae.zzdpl;
        if (i2 == 0) {
            zzbae.zzdpn = zzbah.zzdpq;
            return zza;
        }
        zzbae.zzdpn = zzbah.zzc(bArr, zza, i2);
        return zza + i2;
    }

    static long zzf(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static double zzg(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzf(bArr, i));
    }

    static float zzh(byte[] bArr, int i) {
        return Float.intBitsToFloat(zze(bArr, i));
    }
}
