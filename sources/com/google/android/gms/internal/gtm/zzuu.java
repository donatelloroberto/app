package com.google.android.gms.internal.gtm;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class zzuu {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    protected static final Charset UTF_8 = Charset.forName(WebRequest.CHARSET_UTF_8);
    public static final Object zzbhk = new Object();

    public static boolean equals(int[] iArr, int[] iArr2) {
        if (iArr == null || iArr.length == 0) {
            return iArr2 == null || iArr2.length == 0;
        }
        return Arrays.equals(iArr, iArr2);
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        boolean z;
        int length = objArr == null ? 0 : objArr.length;
        int length2 = objArr2 == null ? 0 : objArr2.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= length || objArr[i2] != null) {
                int i3 = i;
                while (i3 < length2 && objArr2[i3] == null) {
                    i3++;
                }
                boolean z2 = i2 >= length;
                if (i3 >= length2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z2 && z) {
                    return true;
                }
                if (z2 != z || !objArr[i2].equals(objArr2[i3])) {
                    return false;
                }
                i = i3 + 1;
                i2++;
            } else {
                i2++;
            }
        }
    }

    public static int hashCode(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(iArr);
    }

    public static int hashCode(Object[] objArr) {
        int i;
        int length = objArr == null ? 0 : objArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Object obj = objArr[i2];
            if (obj != null) {
                i = obj.hashCode() + (i3 * 31);
            } else {
                i = i3;
            }
            i2++;
            i3 = i;
        }
        return i3;
    }

    public static void zza(zzuq zzuq, zzuq zzuq2) {
        if (zzuq.zzbhb != null) {
            zzuq2.zzbhb = (zzus) zzuq.zzbhb.clone();
        }
    }
}
