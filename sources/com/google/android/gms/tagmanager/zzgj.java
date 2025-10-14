package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
public final class zzgj {
    private static final Object zzalo = null;
    private static Long zzalp = new Long(0);
    private static Double zzalq = new Double(0.0d);
    private static zzgi zzalr = zzgi.zzm(0);
    private static String zzals = new String("");
    private static Boolean zzalt = new Boolean(false);
    private static List<Object> zzalu = new ArrayList(0);
    private static Map<Object, Object> zzalv = new HashMap();
    private static zzl zzalw = zzi(zzals);

    public static Object zzjw() {
        return null;
    }

    public static Long zzjx() {
        return zzalp;
    }

    public static Double zzjy() {
        return zzalq;
    }

    public static Boolean zzjz() {
        return zzalt;
    }

    public static zzgi zzka() {
        return zzalr;
    }

    public static String zzkb() {
        return zzals;
    }

    public static zzl zzkc() {
        return zzalw;
    }

    private static String zzh(Object obj) {
        return obj == null ? zzals : obj.toString();
    }

    public static String zzc(zzl zzl) {
        return zzh(zzh(zzl));
    }

    public static zzgi zzd(zzl zzl) {
        Object zzh = zzh(zzl);
        if (zzh instanceof zzgi) {
            return (zzgi) zzh;
        }
        if (zzk(zzh)) {
            return zzgi.zzm(zzl(zzh));
        }
        if (zzj(zzh)) {
            return zzgi.zza(Double.valueOf(getDouble(zzh)));
        }
        return zzbq(zzh(zzh));
    }

    public static Long zze(zzl zzl) {
        Object zzh = zzh(zzl);
        if (zzk(zzh)) {
            return Long.valueOf(zzl(zzh));
        }
        zzgi zzbq = zzbq(zzh(zzh));
        return zzbq == zzalr ? zzalp : Long.valueOf(zzbq.longValue());
    }

    public static Double zzf(zzl zzl) {
        Object zzh = zzh(zzl);
        if (zzj(zzh)) {
            return Double.valueOf(getDouble(zzh));
        }
        zzgi zzbq = zzbq(zzh(zzh));
        return zzbq == zzalr ? zzalq : Double.valueOf(zzbq.doubleValue());
    }

    public static Boolean zzg(zzl zzl) {
        Object zzh = zzh(zzl);
        if (zzh instanceof Boolean) {
            return (Boolean) zzh;
        }
        String zzh2 = zzh(zzh);
        if ("true".equalsIgnoreCase(zzh2)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(zzh2)) {
            return Boolean.FALSE;
        }
        return zzalt;
    }

    public static zzl zzi(Object obj) {
        boolean z;
        boolean z2;
        boolean z3 = false;
        zzl zzl = new zzl();
        if (obj instanceof zzl) {
            return (zzl) obj;
        }
        if (obj instanceof String) {
            zzl.type = 1;
            zzl.string = (String) obj;
        } else if (obj instanceof List) {
            zzl.type = 2;
            List<Object> list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            boolean z4 = false;
            for (Object zzi : list) {
                zzl zzi2 = zzi(zzi);
                if (zzi2 == zzalw) {
                    return zzalw;
                }
                if (z4 || zzi2.zzqw) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                arrayList.add(zzi2);
                z4 = z2;
            }
            zzl.zzqn = (zzl[]) arrayList.toArray(new zzl[0]);
            z3 = z4;
        } else if (obj instanceof Map) {
            zzl.type = 3;
            Set<Map.Entry> entrySet = ((Map) obj).entrySet();
            ArrayList arrayList2 = new ArrayList(entrySet.size());
            ArrayList arrayList3 = new ArrayList(entrySet.size());
            boolean z5 = false;
            for (Map.Entry entry : entrySet) {
                zzl zzi3 = zzi(entry.getKey());
                zzl zzi4 = zzi(entry.getValue());
                if (zzi3 == zzalw || zzi4 == zzalw) {
                    return zzalw;
                }
                if (z5 || zzi3.zzqw || zzi4.zzqw) {
                    z = true;
                } else {
                    z = false;
                }
                arrayList2.add(zzi3);
                arrayList3.add(zzi4);
                z5 = z;
            }
            zzl.zzqo = (zzl[]) arrayList2.toArray(new zzl[0]);
            zzl.zzqp = (zzl[]) arrayList3.toArray(new zzl[0]);
            z3 = z5;
        } else if (zzj(obj)) {
            zzl.type = 1;
            zzl.string = obj.toString();
        } else if (zzk(obj)) {
            zzl.type = 6;
            zzl.zzqs = zzl(obj);
        } else if (obj instanceof Boolean) {
            zzl.type = 8;
            zzl.zzqt = ((Boolean) obj).booleanValue();
        } else {
            String valueOf = String.valueOf(obj == null ? "null" : obj.getClass().toString());
            zzdi.zzav(valueOf.length() != 0 ? "Converting to Value from unknown object type: ".concat(valueOf) : new String("Converting to Value from unknown object type: "));
            return zzalw;
        }
        zzl.zzqw = z3;
        return zzl;
    }

    public static zzl zzbp(String str) {
        zzl zzl = new zzl();
        zzl.type = 5;
        zzl.zzqr = str;
        return zzl;
    }

    private static boolean zzj(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof zzgi) && ((zzgi) obj).zzju());
    }

    private static double getDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzdi.zzav("getDouble received non-Number");
        return 0.0d;
    }

    private static boolean zzk(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof zzgi) && ((zzgi) obj).zzjv());
    }

    private static long zzl(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzdi.zzav("getInt64 received non-Number");
        return 0;
    }

    private static zzgi zzbq(String str) {
        try {
            return zzgi.zzbo(str);
        } catch (NumberFormatException e) {
            zzdi.zzav(new StringBuilder(String.valueOf(str).length() + 33).append("Failed to convert '").append(str).append("' to a number.").toString());
            return zzalr;
        }
    }

    public static Object zzh(zzl zzl) {
        int i = 0;
        if (zzl == null) {
            return null;
        }
        switch (zzl.type) {
            case 1:
                return zzl.string;
            case 2:
                ArrayList arrayList = new ArrayList(zzl.zzqn.length);
                zzl[] zzlArr = zzl.zzqn;
                int length = zzlArr.length;
                while (i < length) {
                    Object zzh = zzh(zzlArr[i]);
                    if (zzh == null) {
                        return null;
                    }
                    arrayList.add(zzh);
                    i++;
                }
                return arrayList;
            case 3:
                if (zzl.zzqo.length != zzl.zzqp.length) {
                    String valueOf = String.valueOf(zzl.toString());
                    zzdi.zzav(valueOf.length() != 0 ? "Converting an invalid value to object: ".concat(valueOf) : new String("Converting an invalid value to object: "));
                    return null;
                }
                HashMap hashMap = new HashMap(zzl.zzqp.length);
                while (i < zzl.zzqo.length) {
                    Object zzh2 = zzh(zzl.zzqo[i]);
                    Object zzh3 = zzh(zzl.zzqp[i]);
                    if (zzh2 == null || zzh3 == null) {
                        return null;
                    }
                    hashMap.put(zzh2, zzh3);
                    i++;
                }
                return hashMap;
            case 4:
                zzdi.zzav("Trying to convert a macro reference to object");
                return null;
            case 5:
                zzdi.zzav("Trying to convert a function id to object");
                return null;
            case 6:
                return Long.valueOf(zzl.zzqs);
            case 7:
                StringBuilder sb = new StringBuilder();
                zzl[] zzlArr2 = zzl.zzqu;
                int length2 = zzlArr2.length;
                while (i < length2) {
                    String zzh4 = zzh(zzh(zzlArr2[i]));
                    if (zzh4 == zzals) {
                        return null;
                    }
                    sb.append(zzh4);
                    i++;
                }
                return sb.toString();
            case 8:
                return Boolean.valueOf(zzl.zzqt);
            default:
                zzdi.zzav(new StringBuilder(46).append("Failed to convert a value of type: ").append(zzl.type).toString());
                return null;
        }
    }
}
