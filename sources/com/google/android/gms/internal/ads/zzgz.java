package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgz extends zzgq {
    private MessageDigest zzaje;
    private final int zzajh;
    private final int zzaji;

    public zzgz(int i) {
        int i2 = i / 8;
        this.zzajh = i % 8 > 0 ? i2 + 1 : i2;
        this.zzaji = i;
    }

    public final byte[] zzx(String str) {
        byte[] bArr;
        synchronized (this.mLock) {
            this.zzaje = zzhg();
            if (this.zzaje == null) {
                bArr = new byte[0];
            } else {
                this.zzaje.reset();
                this.zzaje.update(str.getBytes(Charset.forName(WebRequest.CHARSET_UTF_8)));
                byte[] digest = this.zzaje.digest();
                bArr = new byte[(digest.length > this.zzajh ? this.zzajh : digest.length)];
                System.arraycopy(digest, 0, bArr, 0, bArr.length);
                if (this.zzaji % 8 > 0) {
                    long j = 0;
                    for (int i = 0; i < bArr.length; i++) {
                        if (i > 0) {
                            j <<= 8;
                        }
                        j += (long) (bArr[i] & 255);
                    }
                    long j2 = j >>> (8 - (this.zzaji % 8));
                    for (int i2 = this.zzajh - 1; i2 >= 0; i2--) {
                        bArr[i2] = (byte) ((int) (255 & j2));
                        j2 >>>= 8;
                    }
                }
            }
        }
        return bArr;
    }
}
