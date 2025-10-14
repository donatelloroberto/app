package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

@zzadh
public final class zzgv extends zzgq {
    private MessageDigest zzaje;

    public final byte[] zzx(String str) {
        byte[] bArr;
        byte[] bArr2;
        String[] split = str.split(" ");
        if (split.length == 1) {
            int zzz = zzgu.zzz(split[0]);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(zzz);
            bArr = allocate.array();
        } else if (split.length < 5) {
            byte[] bArr3 = new byte[(split.length << 1)];
            for (int i = 0; i < split.length; i++) {
                int zzz2 = zzgu.zzz(split[i]);
                int i2 = (zzz2 >> 16) ^ (65535 & zzz2);
                byte[] bArr4 = {(byte) i2, (byte) (i2 >> 8)};
                bArr3[i << 1] = bArr4[0];
                bArr3[(i << 1) + 1] = bArr4[1];
            }
            bArr = bArr3;
        } else {
            bArr = new byte[split.length];
            for (int i3 = 0; i3 < split.length; i3++) {
                int zzz3 = zzgu.zzz(split[i3]);
                bArr[i3] = (byte) ((zzz3 >> 24) ^ (((zzz3 & 255) ^ ((zzz3 >> 8) & 255)) ^ ((zzz3 >> 16) & 255)));
            }
        }
        this.zzaje = zzhg();
        synchronized (this.mLock) {
            if (this.zzaje == null) {
                bArr2 = new byte[0];
            } else {
                this.zzaje.reset();
                this.zzaje.update(bArr);
                byte[] digest = this.zzaje.digest();
                bArr2 = new byte[(digest.length > 4 ? 4 : digest.length)];
                System.arraycopy(digest, 0, bArr2, 0, bArr2.length);
            }
        }
        return bArr2;
    }
}
