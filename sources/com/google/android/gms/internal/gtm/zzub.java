package com.google.android.gms.internal.gtm;

import java.nio.ByteBuffer;

abstract class zzub {
    zzub() {
    }

    /* access modifiers changed from: package-private */
    public abstract int zzb(int i, byte[] bArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract int zzb(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zzb(CharSequence charSequence, ByteBuffer byteBuffer);

    /* access modifiers changed from: package-private */
    public abstract String zzh(byte[] bArr, int i, int i2) throws zzrk;

    /* access modifiers changed from: package-private */
    public final boolean zzf(byte[] bArr, int i, int i2) {
        return zzb(0, bArr, i, i2) == 0;
    }

    static void zzc(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int length = charSequence.length();
        int position = byteBuffer.position();
        int i2 = 0;
        while (i2 < length) {
            try {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + i2, (byte) charAt);
                i2++;
            } catch (IndexOutOfBoundsException e) {
            }
        }
        if (i2 == length) {
            byteBuffer.position(position + i2);
            return;
        }
        position += i2;
        while (i2 < length) {
            char charAt2 = charSequence.charAt(i2);
            if (charAt2 < 128) {
                byteBuffer.put(position, (byte) charAt2);
            } else if (charAt2 < 2048) {
                i = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt2 >>> 6) | 192));
                    byteBuffer.put(i, (byte) ((charAt2 & '?') | 128));
                    position = i;
                } catch (IndexOutOfBoundsException e2) {
                    position = i;
                    int position2 = byteBuffer.position();
                    throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(i2)).append(" at index ").append(Math.max(i2, (position - byteBuffer.position()) + 1) + position2).toString());
                }
            } else if (charAt2 < 55296 || 57343 < charAt2) {
                int i3 = position + 1;
                byteBuffer.put(position, (byte) ((charAt2 >>> 12) | 224));
                position = i3 + 1;
                byteBuffer.put(i3, (byte) (((charAt2 >>> 6) & 63) | 128));
                byteBuffer.put(position, (byte) ((charAt2 & '?') | 128));
            } else {
                if (i2 + 1 != length) {
                    i2++;
                    char charAt3 = charSequence.charAt(i2);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i4 = position + 1;
                        try {
                            byteBuffer.put(position, (byte) ((codePoint >>> 18) | 240));
                            i = i4 + 1;
                            byteBuffer.put(i4, (byte) (((codePoint >>> 12) & 63) | 128));
                            position = i + 1;
                            byteBuffer.put(i, (byte) (((codePoint >>> 6) & 63) | 128));
                            byteBuffer.put(position, (byte) ((codePoint & 63) | 128));
                        } catch (IndexOutOfBoundsException e3) {
                            position = i4;
                            int position22 = byteBuffer.position();
                            throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(i2)).append(" at index ").append(Math.max(i2, (position - byteBuffer.position()) + 1) + position22).toString());
                        }
                    }
                }
                throw new zzud(i2, length);
            }
            i2++;
            position++;
        }
        byteBuffer.position(position);
    }
}
