package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

public class zzci extends zzan {
    private static zzci zzabl;

    public zzci(zzap zzap) {
        super(zzap);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() {
        synchronized (zzci.class) {
            zzabl = this;
        }
    }

    public static zzci zzfn() {
        return zzabl;
    }

    public final void zza(zzcd zzcd, String str) {
        String zzcd2 = zzcd != null ? zzcd.toString() : "no hit data";
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? "Discarding hit. ".concat(valueOf) : new String("Discarding hit. "), zzcd2);
    }

    public final void zza(Map<String, String> map, String str) {
        String str2;
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry next : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append((String) next.getKey());
                sb.append('=');
                sb.append((String) next.getValue());
            }
            str2 = sb.toString();
        } else {
            str2 = "no hit data";
        }
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? "Discarding hit. ".concat(valueOf) : new String("Discarding hit. "), str2);
    }

    public final synchronized void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        int i2;
        char c;
        int i3 = 0;
        synchronized (this) {
            Preconditions.checkNotNull(str);
            if (i >= 0) {
                i3 = i;
            }
            if (i3 >= 9) {
                i2 = 8;
            } else {
                i2 = i3;
            }
            if (zzcp().zzem()) {
                c = 'C';
            } else {
                c = 'c';
            }
            char charAt = "01VDIWEA?".charAt(i2);
            String str2 = zzao.VERSION;
            String zzc = zzc(str, zzd(obj), zzd(obj2), zzd(obj3));
            String sb = new StringBuilder(String.valueOf(str2).length() + 4 + String.valueOf(zzc).length()).append("3").append(charAt).append(c).append(str2).append(":").append(zzc).toString();
            if (sb.length() > 1024) {
                sb = sb.substring(0, 1024);
            }
            zzcm zzdf = zzcm().zzdf();
            if (zzdf != null) {
                zzdf.zzga().zzae(sb);
            }
        }
    }

    @VisibleForTesting
    private static String zzd(Object obj) {
        Object obj2;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            obj2 = Long.valueOf((long) ((Integer) obj).intValue());
        } else {
            obj2 = obj;
        }
        if (obj2 instanceof Long) {
            if (Math.abs(((Long) obj2).longValue()) < 100) {
                return String.valueOf(obj2);
            }
            String str = String.valueOf(obj2).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(((Long) obj2).longValue()));
            return str + Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1))) + "..." + str + Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
        } else if (obj2 instanceof Boolean) {
            return String.valueOf(obj2);
        } else {
            if (obj2 instanceof Throwable) {
                return obj2.getClass().getCanonicalName();
            }
            return "-";
        }
    }
}
