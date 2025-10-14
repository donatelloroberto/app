package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.ByteCompanionObject;

public final class zzayi implements zzatz {
    private final SecretKeySpec zzdmu;
    private final byte[] zzdmx;
    private final byte[] zzdmy;
    private final int zzdmz;

    public zzayi(byte[] bArr, int i) throws GeneralSecurityException {
        if (i == 12 || i == 16) {
            this.zzdmz = i;
            this.zzdmu = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
            instance.init(1, this.zzdmu);
            this.zzdmx = zzl(instance.doFinal(new byte[16]));
            this.zzdmy = zzl(this.zzdmx);
            return;
        }
        throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
    }

    private final byte[] zza(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2;
        byte[] bArr3 = new byte[16];
        bArr3[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(zzd(bArr3, this.zzdmx));
        }
        int i4 = 0;
        byte[] doFinal = cipher.doFinal(bArr3);
        while (i3 - i4 > 16) {
            for (int i5 = 0; i5 < 16; i5++) {
                doFinal[i5] = (byte) (doFinal[i5] ^ bArr[(i2 + i4) + i5]);
            }
            doFinal = cipher.doFinal(doFinal);
            i4 += 16;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i2 + i4, i2 + i3);
        if (copyOfRange.length == 16) {
            bArr2 = zzd(copyOfRange, this.zzdmx);
        } else {
            byte[] copyOf = Arrays.copyOf(this.zzdmy, 16);
            for (int i6 = 0; i6 < copyOfRange.length; i6++) {
                copyOf[i6] = (byte) (copyOf[i6] ^ copyOfRange[i6]);
            }
            copyOf[copyOfRange.length] = (byte) (copyOf[copyOfRange.length] ^ ByteCompanionObject.MIN_VALUE);
            bArr2 = copyOf;
        }
        return cipher.doFinal(zzd(doFinal, bArr2));
    }

    private static byte[] zzd(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private static byte[] zzl(byte[] bArr) {
        int i = 0;
        byte[] bArr2 = new byte[16];
        for (int i2 = 0; i2 < 15; i2++) {
            bArr2[i2] = (byte) ((bArr[i2] << 1) ^ ((bArr[i2 + 1] & 255) >>> 7));
        }
        int i3 = bArr[15] << 1;
        if ((bArr[0] & ByteCompanionObject.MIN_VALUE) != 0) {
            i = 135;
        }
        bArr2[15] = (byte) (i ^ i3);
        return bArr2;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > (Integer.MAX_VALUE - this.zzdmz) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArr3 = new byte[(this.zzdmz + bArr.length + 16)];
        byte[] zzbh = zzazl.zzbh(this.zzdmz);
        System.arraycopy(zzbh, 0, bArr3, 0, this.zzdmz);
        Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
        instance.init(1, this.zzdmu);
        byte[] zza = zza(instance, 0, zzbh, 0, zzbh.length);
        byte[] bArr4 = bArr2 == null ? new byte[0] : bArr2;
        byte[] zza2 = zza(instance, 1, bArr4, 0, bArr4.length);
        Cipher instance2 = Cipher.getInstance("AES/CTR/NOPADDING");
        instance2.init(1, this.zzdmu, new IvParameterSpec(zza));
        instance2.doFinal(bArr, 0, bArr.length, bArr3, this.zzdmz);
        byte[] zza3 = zza(instance, 2, bArr3, this.zzdmz, bArr.length);
        int length = bArr.length + this.zzdmz;
        for (int i = 0; i < 16; i++) {
            bArr3[length + i] = (byte) ((zza2[i] ^ zza[i]) ^ zza3[i]);
        }
        return bArr3;
    }
}
