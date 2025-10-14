package com.google.android.gms.internal.cast;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public final class zzdc {
    private static final Pattern zzzr = Pattern.compile("urn:x-cast:[-A-Za-z0-9_]+(\\.[-A-Za-z0-9_]+)*");

    public static <T> boolean zza(T t, T t2) {
        return (t == null && t2 == null) || !(t == null || t2 == null || !t.equals(t2));
    }

    public static int zzb(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public static void zzq(String str) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Namespace cannot be null or empty");
        } else if (str.length() > 128) {
            throw new IllegalArgumentException("Invalid namespace length");
        } else if (!str.startsWith("urn:x-cast:")) {
            throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\"");
        } else if (str.length() == 11) {
            throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\" and have non-empty suffix");
        }
    }

    public static String zzr(String str) {
        String valueOf = String.valueOf("urn:x-cast:");
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public static String zzs(String str) {
        boolean z;
        if (zzzr.matcher(str).matches()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt < 'A' || charAt > 'Z') && ((charAt < 'a' || charAt > 'z') && !((charAt >= '0' && charAt <= '9') || charAt == '_' || charAt == '-'))) {
                z = false;
            } else {
                z = true;
            }
            if (z || charAt == '.' || charAt == ':') {
                sb.append(charAt);
            } else {
                sb.append(String.format("%%%04x", new Object[]{Integer.valueOf(charAt & 65535)}));
            }
        }
        return sb.toString();
    }

    public static String zza(Locale locale) {
        StringBuilder sb = new StringBuilder(20);
        sb.append(locale.getLanguage());
        String country = locale.getCountry();
        if (!TextUtils.isEmpty(country)) {
            sb.append('-').append(country);
        }
        String variant = locale.getVariant();
        if (!TextUtils.isEmpty(variant)) {
            sb.append('-').append(variant);
        }
        return sb.toString();
    }

    @NonNull
    public static int[] zza(@NonNull Collection<Integer> collection) {
        int[] iArr = new int[collection.size()];
        int i = 0;
        for (Integer intValue : collection) {
            iArr[i] = intValue.intValue();
            i++;
        }
        return iArr;
    }

    @NonNull
    public static List<Integer> zzg(@NonNull int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList;
    }
}
