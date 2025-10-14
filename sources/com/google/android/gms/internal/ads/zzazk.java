package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class zzazk {
    private static long zza(byte[] bArr, int i, int i2) {
        return (zzd(bArr, i) >> i2) & 67108863;
    }

    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) ((int) (255 & j));
            i2++;
            j >>= 8;
        }
    }

    private static long zzd(byte[] bArr, int i) {
        return ((long) ((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24))) & 4294967295L;
    }

    static byte[] zze(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long zza = 67108863 & zza(bArr, 0, 0);
        long zza2 = 67108611 & zza(bArr, 3, 2);
        long zza3 = 67092735 & zza(bArr, 6, 4);
        long zza4 = 66076671 & zza(bArr, 9, 6);
        long zza5 = 1048575 & zza(bArr, 12, 8);
        long j6 = zza2 * 5;
        long j7 = zza3 * 5;
        long j8 = zza4 * 5;
        long j9 = zza5 * 5;
        byte[] bArr3 = new byte[17];
        for (int i = 0; i < bArr2.length; i += 16) {
            int min = Math.min(16, bArr2.length - i);
            System.arraycopy(bArr2, i, bArr3, 0, min);
            bArr3[min] = 1;
            if (min != 16) {
                Arrays.fill(bArr3, min + 1, 17, (byte) 0);
            }
            long zza6 = j + zza(bArr3, 0, 0);
            long zza7 = j2 + zza(bArr3, 3, 2);
            long zza8 = j3 + zza(bArr3, 6, 4);
            long zza9 = j4 + zza(bArr3, 9, 6);
            long zza10 = j5 + (zza(bArr3, 12, 8) | ((long) (bArr3[16] << 24)));
            long j10 = (zza6 * zza) + (zza7 * j9) + (zza8 * j8) + (zza9 * j7) + (zza10 * j6);
            long j11 = (zza6 * zza2) + (zza7 * zza) + (zza8 * j9) + (zza9 * j8) + (zza10 * j7);
            long j12 = (zza6 * zza3) + (zza7 * zza2) + (zza8 * zza) + (zza9 * j9) + (zza10 * j8);
            long j13 = zza8 * zza3;
            long j14 = zza9 * zza2;
            long j15 = zza10 * zza;
            long j16 = j15 + j14 + j13 + (zza7 * zza4) + (zza6 * zza5);
            long j17 = j10 & 67108863;
            long j18 = (j10 >> 26) + j11;
            long j19 = j18 & 67108863;
            long j20 = j12 + (j18 >> 26);
            j3 = 67108863 & j20;
            long j21 = (zza6 * zza4) + (zza7 * zza3) + (zza8 * zza2) + (zza9 * zza) + (zza10 * j9) + (j20 >> 26);
            long j22 = j21 >> 26;
            j4 = j21 & 67108863;
            long j23 = j16 + j22;
            long j24 = j23 >> 26;
            j5 = j23 & 67108863;
            long j25 = j17 + (j24 * 5);
            j = 67108863 & j25;
            j2 = j19 + (j25 >> 26);
        }
        long j26 = (j2 >> 26) + j3;
        long j27 = j26 >> 26;
        long j28 = j26 & 67108863;
        long j29 = j4 + j27;
        long j30 = j29 >> 26;
        long j31 = j29 & 67108863;
        long j32 = j5 + j30;
        long j33 = j32 >> 26;
        long j34 = j32 & 67108863;
        long j35 = (j33 * 5) + j;
        long j36 = j35 >> 26;
        long j37 = j35 & 67108863;
        long j38 = (j2 & 67108863) + j36;
        long j39 = 5 + j37;
        long j40 = (j39 >> 26) + j38;
        long j41 = (j40 >> 26) + j28;
        long j42 = (j41 >> 26) + j31;
        long j43 = ((j42 >> 26) + j34) - 67108864;
        long j44 = j43 >> 63;
        long j45 = j37 & j44;
        long j46 = j38 & j44;
        long j47 = j28 & j44;
        long j48 = j31 & j44;
        long j49 = j34 & j44;
        long j50 = j44 ^ -1;
        long j51 = j46 | (j40 & 67108863 & j50);
        long j52 = j47 | (j41 & 67108863 & j50);
        long j53 = j48 | (j42 & 67108863 & j50);
        long j54 = (j49 | (j43 & j50)) << 8;
        long zzd = zzd(bArr, 16);
        long j55 = zzd + ((j45 | (j39 & 67108863 & j50) | (j51 << 26)) & 4294967295L);
        long j56 = 4294967295L & j55;
        long zzd2 = (j55 >> 32) + (((j51 >> 6) | (j52 << 20)) & 4294967295L) + zzd(bArr, 20);
        long j57 = 4294967295L & zzd2;
        long zzd3 = (((j52 >> 12) | (j53 << 14)) & 4294967295L) + zzd(bArr, 24) + (zzd2 >> 32);
        long j58 = 4294967295L & zzd3;
        byte[] bArr4 = new byte[16];
        zza(bArr4, j56, 0);
        zza(bArr4, j57, 4);
        zza(bArr4, j58, 8);
        zza(bArr4, ((zzd3 >> 32) + ((j54 | (j53 >> 18)) & 4294967295L) + zzd(bArr, 28)) & 4294967295L, 12);
        return bArr4;
    }
}
