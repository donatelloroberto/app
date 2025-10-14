package com.google.android.gms.internal.gtm;

import java.nio.ByteBuffer;

final class zzuc extends zzub {
    zzuc() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: CFG modification limit reached, blocks count: 151 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        if (r0 >= -32) goto L_0x0031;
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (r3 >= r13) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        if (r0 < -62) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        r0 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        if (r11[r3] <= -65) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        r0 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        if (r0 >= -16) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0037, code lost:
        if (r3 < (r13 - 1)) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0039, code lost:
        r0 = com.google.android.gms.internal.gtm.zztz.zzg(r11, r3, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003e, code lost:
        r4 = r3 + 1;
        r3 = r11[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0042, code lost:
        if (r3 > -65) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0044, code lost:
        if (r0 != -32) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0046, code lost:
        if (r3 < -96) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004a, code lost:
        if (r0 != -19) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004c, code lost:
        if (r3 >= -96) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004e, code lost:
        r0 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0052, code lost:
        if (r11[r4] <= -65) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0054, code lost:
        r0 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0058, code lost:
        if (r3 < (r13 - 2)) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005a, code lost:
        r0 = com.google.android.gms.internal.gtm.zztz.zzg(r11, r3, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005f, code lost:
        r4 = r3 + 1;
        r3 = r11[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0063, code lost:
        if (r3 > -65) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x006c, code lost:
        if ((((r0 << 28) + (r3 + 112)) >> 30) != 0) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x006e, code lost:
        r3 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0072, code lost:
        if (r11[r4] > -65) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0074, code lost:
        r0 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0078, code lost:
        if (r11[r3] <= -65) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007a, code lost:
        r0 = -1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(int r10, byte[] r11, int r12, int r13) {
        /*
            r9 = this;
            r1 = 0
            r8 = -32
            r7 = -96
            r2 = -1
            r6 = -65
            r0 = r12
        L_0x0009:
            if (r0 >= r13) goto L_0x0012
            byte r3 = r11[r0]
            if (r3 < 0) goto L_0x0012
            int r0 = r0 + 1
            goto L_0x0009
        L_0x0012:
            if (r0 < r13) goto L_0x0017
            r0 = r1
        L_0x0015:
            return r0
        L_0x0016:
            r0 = r3
        L_0x0017:
            if (r0 < r13) goto L_0x001b
            r0 = r1
            goto L_0x0015
        L_0x001b:
            int r3 = r0 + 1
            byte r0 = r11[r0]
            if (r0 >= 0) goto L_0x0016
            if (r0 >= r8) goto L_0x0031
            if (r3 >= r13) goto L_0x0015
            r4 = -62
            if (r0 < r4) goto L_0x002f
            int r0 = r3 + 1
            byte r3 = r11[r3]
            if (r3 <= r6) goto L_0x0017
        L_0x002f:
            r0 = r2
            goto L_0x0015
        L_0x0031:
            r4 = -16
            if (r0 >= r4) goto L_0x0056
            int r4 = r13 + -1
            if (r3 < r4) goto L_0x003e
            int r0 = com.google.android.gms.internal.gtm.zztz.zzg(r11, r3, r13)
            goto L_0x0015
        L_0x003e:
            int r4 = r3 + 1
            byte r3 = r11[r3]
            if (r3 > r6) goto L_0x0054
            if (r0 != r8) goto L_0x0048
            if (r3 < r7) goto L_0x0054
        L_0x0048:
            r5 = -19
            if (r0 != r5) goto L_0x004e
            if (r3 >= r7) goto L_0x0054
        L_0x004e:
            int r0 = r4 + 1
            byte r3 = r11[r4]
            if (r3 <= r6) goto L_0x0017
        L_0x0054:
            r0 = r2
            goto L_0x0015
        L_0x0056:
            int r4 = r13 + -2
            if (r3 < r4) goto L_0x005f
            int r0 = com.google.android.gms.internal.gtm.zztz.zzg(r11, r3, r13)
            goto L_0x0015
        L_0x005f:
            int r4 = r3 + 1
            byte r3 = r11[r3]
            if (r3 > r6) goto L_0x007a
            int r0 = r0 << 28
            int r3 = r3 + 112
            int r0 = r0 + r3
            int r0 = r0 >> 30
            if (r0 != 0) goto L_0x007a
            int r3 = r4 + 1
            byte r0 = r11[r4]
            if (r0 > r6) goto L_0x007a
            int r0 = r3 + 1
            byte r3 = r11[r3]
            if (r3 <= r6) goto L_0x0017
        L_0x007a:
            r0 = r2
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzuc.zzb(int, byte[], int, int):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: CFG modification limit reached, blocks count: 145 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzh(byte[] r11, int r12, int r13) throws com.google.android.gms.internal.gtm.zzrk {
        /*
            r10 = this;
            r7 = 0
            r0 = r12 | r13
            int r1 = r11.length
            int r1 = r1 - r12
            int r1 = r1 - r13
            r0 = r0 | r1
            if (r0 >= 0) goto L_0x002d
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.String r1 = "buffer length=%d, index=%d, size=%d"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            int r3 = r11.length
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r7] = r3
            r3 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)
            r2[r3] = r4
            r3 = 2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r2[r3] = r4
            java.lang.String r1 = java.lang.String.format(r1, r2)
            r0.<init>(r1)
            throw r0
        L_0x002d:
            int r8 = r12 + r13
            char[] r4 = new char[r13]
            r5 = r7
            r1 = r12
        L_0x0033:
            if (r1 >= r8) goto L_0x00c6
            byte r2 = r11[r1]
            boolean r0 = com.google.android.gms.internal.gtm.zzua.zzd(r2)
            if (r0 == 0) goto L_0x00c6
            int r1 = r1 + 1
            int r0 = r5 + 1
            com.google.android.gms.internal.gtm.zzua.zza(r2, r4, r5)
            r5 = r0
            goto L_0x0033
        L_0x0046:
            int r2 = r1 + 1
            byte r3 = r11[r1]
            int r1 = r5 + 1
            com.google.android.gms.internal.gtm.zzua.zza(r0, r3, r4, r5)
            r5 = r1
        L_0x0050:
            if (r2 >= r8) goto L_0x00c0
            int r1 = r2 + 1
            byte r0 = r11[r2]
            boolean r2 = com.google.android.gms.internal.gtm.zzua.zzd(r0)
            if (r2 == 0) goto L_0x0075
            int r2 = r5 + 1
            com.google.android.gms.internal.gtm.zzua.zza(r0, r4, r5)
            r0 = r2
        L_0x0062:
            if (r1 >= r8) goto L_0x00bd
            byte r3 = r11[r1]
            boolean r2 = com.google.android.gms.internal.gtm.zzua.zzd(r3)
            if (r2 == 0) goto L_0x00bd
            int r1 = r1 + 1
            int r2 = r0 + 1
            com.google.android.gms.internal.gtm.zzua.zza(r3, r4, r0)
            r0 = r2
            goto L_0x0062
        L_0x0075:
            boolean r2 = com.google.android.gms.internal.gtm.zzua.zze(r0)
            if (r2 == 0) goto L_0x0082
            if (r1 < r8) goto L_0x0046
            com.google.android.gms.internal.gtm.zzrk r0 = com.google.android.gms.internal.gtm.zzrk.zzpw()
            throw r0
        L_0x0082:
            boolean r2 = com.google.android.gms.internal.gtm.zzua.zzf(r0)
            if (r2 == 0) goto L_0x00a0
            int r2 = r8 + -1
            if (r1 < r2) goto L_0x0091
            com.google.android.gms.internal.gtm.zzrk r0 = com.google.android.gms.internal.gtm.zzrk.zzpw()
            throw r0
        L_0x0091:
            int r3 = r1 + 1
            byte r6 = r11[r1]
            int r2 = r3 + 1
            byte r3 = r11[r3]
            int r1 = r5 + 1
            com.google.android.gms.internal.gtm.zzua.zza(r0, r6, r3, r4, r5)
            r5 = r1
            goto L_0x0050
        L_0x00a0:
            int r2 = r8 + -2
            if (r1 < r2) goto L_0x00a9
            com.google.android.gms.internal.gtm.zzrk r0 = com.google.android.gms.internal.gtm.zzrk.zzpw()
            throw r0
        L_0x00a9:
            int r2 = r1 + 1
            byte r1 = r11[r1]
            int r3 = r2 + 1
            byte r2 = r11[r2]
            int r6 = r3 + 1
            byte r3 = r11[r3]
            int r9 = r5 + 1
            com.google.android.gms.internal.gtm.zzua.zza(r0, r1, r2, r3, r4, r5)
            int r0 = r9 + 1
            r1 = r6
        L_0x00bd:
            r5 = r0
            r2 = r1
            goto L_0x0050
        L_0x00c0:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4, r7, r5)
            return r0
        L_0x00c6:
            r2 = r1
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzuc.zzh(byte[], int, int):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int length = charSequence.length();
        int i4 = 0;
        int i5 = i + i2;
        while (i4 < length && i4 + i < i5) {
            char charAt = charSequence.charAt(i4);
            if (charAt >= 128) {
                break;
            }
            bArr[i + i4] = (byte) charAt;
            i4++;
        }
        if (i4 == length) {
            return i + length;
        }
        int i6 = i + i4;
        while (i4 < length) {
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < 128 && i6 < i5) {
                i3 = i6 + 1;
                bArr[i6] = (byte) charAt2;
            } else if (charAt2 < 2048 && i6 <= i5 - 2) {
                int i7 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 6) | 960);
                i3 = i7 + 1;
                bArr[i7] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i6 <= i5 - 3) {
                int i8 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 12) | 480);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i3 = i9 + 1;
                bArr[i9] = (byte) ((charAt2 & '?') | 128);
            } else if (i6 <= i5 - 4) {
                if (i4 + 1 != charSequence.length()) {
                    i4++;
                    char charAt3 = charSequence.charAt(i4);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i10 = i6 + 1;
                        bArr[i6] = (byte) ((codePoint >>> 18) | 240);
                        int i11 = i10 + 1;
                        bArr[i10] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i3 = i12 + 1;
                        bArr[i12] = (byte) ((codePoint & 63) | 128);
                    }
                }
                throw new zzud(i4 - 1, length);
            } else if (55296 > charAt2 || charAt2 > 57343 || (i4 + 1 != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i4 + 1)))) {
                throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charAt2).append(" at index ").append(i6).toString());
            } else {
                throw new zzud(i4, length);
            }
            i4++;
            i6 = i3;
        }
        return i6;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzc(charSequence, byteBuffer);
    }
}
