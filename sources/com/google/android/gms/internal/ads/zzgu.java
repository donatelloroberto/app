package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.MurmurHash3;
import java.io.UnsupportedEncodingException;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgu {
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007a, code lost:
        if (((r9 >= 65382 && r9 <= 65437) || (r9 >= 65441 && r9 <= 65500)) != false) goto L_0x007c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x009c  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] zzb(@android.support.annotation.Nullable java.lang.String r12, boolean r13) {
        /*
            r4 = 1
            r3 = 0
            if (r12 != 0) goto L_0x0006
            r0 = 0
        L_0x0005:
            return r0
        L_0x0006:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            char[] r7 = r12.toCharArray()
            int r8 = r12.length()
            r2 = r3
            r0 = r3
            r1 = r3
        L_0x0016:
            if (r1 >= r8) goto L_0x00db
            int r9 = java.lang.Character.codePointAt(r7, r1)
            int r10 = java.lang.Character.charCount(r9)
            boolean r5 = java.lang.Character.isLetter(r9)
            if (r5 == 0) goto L_0x009a
            java.lang.Character$UnicodeBlock r5 = java.lang.Character.UnicodeBlock.of(r9)
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.BOPOMOFO
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.BOPOMOFO_EXTENDED
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.HANGUL_JAMO
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.HANGUL_SYLLABLES
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.HIRAGANA
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.KATAKANA
            if (r5 == r11) goto L_0x0062
            java.lang.Character$UnicodeBlock r11 = java.lang.Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS
            if (r5 != r11) goto L_0x0096
        L_0x0062:
            r5 = r4
        L_0x0063:
            if (r5 != 0) goto L_0x007c
            r5 = 65382(0xff66, float:9.162E-41)
            if (r9 < r5) goto L_0x006f
            r5 = 65437(0xff9d, float:9.1697E-41)
            if (r9 <= r5) goto L_0x0079
        L_0x006f:
            r5 = 65441(0xffa1, float:9.1702E-41)
            if (r9 < r5) goto L_0x0098
            r5 = 65500(0xffdc, float:9.1785E-41)
            if (r9 > r5) goto L_0x0098
        L_0x0079:
            r5 = r4
        L_0x007a:
            if (r5 == 0) goto L_0x009a
        L_0x007c:
            r5 = r4
        L_0x007d:
            if (r5 == 0) goto L_0x009c
            if (r2 == 0) goto L_0x008b
            java.lang.String r2 = new java.lang.String
            int r5 = r1 - r0
            r2.<init>(r7, r0, r5)
            r6.add(r2)
        L_0x008b:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r7, r1, r10)
            r6.add(r2)
            r2 = r3
        L_0x0094:
            int r1 = r1 + r10
            goto L_0x0016
        L_0x0096:
            r5 = r3
            goto L_0x0063
        L_0x0098:
            r5 = r3
            goto L_0x007a
        L_0x009a:
            r5 = r3
            goto L_0x007d
        L_0x009c:
            boolean r5 = java.lang.Character.isLetterOrDigit(r9)
            if (r5 != 0) goto L_0x00b1
            int r5 = java.lang.Character.getType(r9)
            r11 = 6
            if (r5 == r11) goto L_0x00b1
            int r5 = java.lang.Character.getType(r9)
            r11 = 8
            if (r5 != r11) goto L_0x00b6
        L_0x00b1:
            if (r2 != 0) goto L_0x00b4
            r0 = r1
        L_0x00b4:
            r2 = r4
            goto L_0x0094
        L_0x00b6:
            if (r13 == 0) goto L_0x00cd
            int r5 = java.lang.Character.charCount(r9)
            if (r5 != r4) goto L_0x00cd
            char[] r5 = java.lang.Character.toChars(r9)
            char r5 = r5[r3]
            r9 = 39
            if (r5 != r9) goto L_0x00cd
            if (r2 != 0) goto L_0x00cb
            r0 = r1
        L_0x00cb:
            r2 = r4
            goto L_0x0094
        L_0x00cd:
            if (r2 == 0) goto L_0x0094
            java.lang.String r2 = new java.lang.String
            int r5 = r1 - r0
            r2.<init>(r7, r0, r5)
            r6.add(r2)
            r2 = r3
            goto L_0x0094
        L_0x00db:
            if (r2 == 0) goto L_0x00e6
            java.lang.String r2 = new java.lang.String
            int r1 = r1 - r0
            r2.<init>(r7, r0, r1)
            r6.add(r2)
        L_0x00e6:
            int r0 = r6.size()
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.Object[] r0 = r6.toArray(r0)
            java.lang.String[] r0 = (java.lang.String[]) r0
            goto L_0x0005
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgu.zzb(java.lang.String, boolean):java.lang.String[]");
    }

    public static int zzz(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes(WebRequest.CHARSET_UTF_8);
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        return MurmurHash3.murmurhash3_x86_32(bytes, 0, bytes.length, 0);
    }
}
