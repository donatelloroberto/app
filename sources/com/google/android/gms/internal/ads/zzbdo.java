package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzbdo {
    private static final Class<?> zzdyf = zzafq();
    private static final zzbee<?, ?> zzdyg = zzas(false);
    private static final zzbee<?, ?> zzdyh = zzas(true);
    private static final zzbee<?, ?> zzdyi = new zzbeg();

    static <UT, UB> UB zza(int i, int i2, UB ub, zzbee<UT, UB> zzbee) {
        if (ub == null) {
            ub = zzbee.zzagb();
        }
        zzbee.zza(ub, i, (long) i2);
        return ub;
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzbbs<?> zzbbs, UB ub, zzbee<UT, UB> zzbee) {
        UB ub2;
        int i2;
        if (zzbbs == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            int i4 = 0;
            ub2 = ub;
            while (i3 < size) {
                int intValue = list.get(i3).intValue();
                if (zzbbs.zzq(intValue) != null) {
                    if (i3 != i4) {
                        list.set(i4, Integer.valueOf(intValue));
                    }
                    i2 = i4 + 1;
                } else {
                    ub2 = zza(i, intValue, ub2, zzbee);
                    i2 = i4;
                }
                i3++;
                i4 = i2;
            }
            if (i4 != size) {
                list.subList(i4, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (zzbbs.zzq(intValue2) == null) {
                    ub = zza(i, intValue2, ub, zzbee);
                    it.remove();
                }
            }
            ub2 = ub;
        }
        return ub2;
    }

    public static void zza(int i, List<String> list, zzbey zzbey) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzbey zzbey, zzbdm zzbdm) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(i, list, zzbdm);
        }
    }

    public static void zza(int i, List<Double> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzg(i, list, z);
        }
    }

    static <T, FT extends zzbbi<FT>> void zza(zzbbd<FT> zzbbd, T t, T t2) {
        zzbbg<FT> zzm = zzbbd.zzm(t2);
        if (!zzm.isEmpty()) {
            zzbbd.zzn(t).zza(zzm);
        }
    }

    static <T> void zza(zzbcp zzbcp, T t, T t2, long j) {
        zzbek.zza((Object) t, j, zzbcp.zzb(zzbek.zzp(t, j), zzbek.zzp(t2, j)));
    }

    static <T, UT, UB> void zza(zzbee<UT, UB> zzbee, T t, T t2) {
        zzbee.zze(t, zzbee.zzg(zzbee.zzac(t), zzbee.zzac(t2)));
    }

    static int zzaf(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbav.zzp(zzbci.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbav.zzp(list.get(i4).longValue());
        }
        return i3;
    }

    public static zzbee<?, ?> zzafn() {
        return zzdyg;
    }

    public static zzbee<?, ?> zzafo() {
        return zzdyh;
    }

    public static zzbee<?, ?> zzafp() {
        return zzdyi;
    }

    private static Class<?> zzafq() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzafr() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static int zzag(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbav.zzq(zzbci.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbav.zzq(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzah(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbav.zzr(zzbci.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbav.zzr(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzai(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbav.zzcj(zzbbp.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbav.zzcj(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzaj(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbav.zzce(zzbbp.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbav.zzce(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzak(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbav.zzcf(zzbbp.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbav.zzcf(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzal(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbav.zzcg(zzbbp.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbav.zzcg(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzam(List<?> list) {
        return list.size() << 2;
    }

    static int zzan(List<?> list) {
        return list.size() << 3;
    }

    static int zzao(List<?> list) {
        return list.size();
    }

    private static zzbee<?, ?> zzas(boolean z) {
        try {
            Class<?> zzafr = zzafr();
            if (zzafr == null) {
                return null;
            }
            return (zzbee) zzafr.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    public static void zzb(int i, List<zzbah> list, zzbey zzbey) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzbey zzbey, zzbdm zzbdm) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(i, list, zzbdm);
        }
    }

    public static void zzb(int i, List<Float> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzf(i, list, z);
        }
    }

    static int zzc(int i, Object obj, zzbdm zzbdm) {
        return obj instanceof zzbcb ? zzbav.zza(i, (zzbcb) obj) : zzbav.zzb(i, (zzbcu) obj, zzbdm);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcd = zzbav.zzcd(i) * size;
        if (list instanceof zzbcd) {
            zzbcd zzbcd = (zzbcd) list;
            int i2 = 0;
            while (i2 < size) {
                Object zzcp = zzbcd.zzcp(i2);
                i2++;
                zzcd = (zzcp instanceof zzbah ? zzbav.zzao((zzbah) zzcp) : zzbav.zzeo((String) zzcp)) + zzcd;
            }
            return zzcd;
        }
        int i3 = 0;
        while (i3 < size) {
            Object obj = list.get(i3);
            i3++;
            zzcd = (obj instanceof zzbah ? zzbav.zzao((zzbah) obj) : zzbav.zzeo((String) obj)) + zzcd;
        }
        return zzcd;
    }

    static int zzc(int i, List<?> list, zzbdm zzbdm) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcd = zzbav.zzcd(i) * size;
        int i2 = 0;
        while (i2 < size) {
            Object obj = list.get(i2);
            i2++;
            zzcd = (obj instanceof zzbcb ? zzbav.zza((zzbcb) obj) : zzbav.zza((zzbcu) obj, zzbdm)) + zzcd;
        }
        return zzcd;
    }

    public static void zzc(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzc(i, list, z);
        }
    }

    static int zzd(int i, List<zzbah> list) {
        int i2 = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcd = zzbav.zzcd(i) * size;
        while (true) {
            int i3 = i2;
            if (i3 >= list.size()) {
                return zzcd;
            }
            zzcd += zzbav.zzao(list.get(i3));
            i2 = i3 + 1;
        }
    }

    static int zzd(int i, List<zzbcu> list, zzbdm zzbdm) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbav.zzc(i, list.get(i3), zzbdm);
        }
        return i2;
    }

    public static void zzd(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzd(i, list, z);
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void zze(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzn(i, list, z);
        }
    }

    public static boolean zze(int i, int i2, int i3) {
        return i2 < 40 || ((((long) i2) - ((long) i)) + 1) + 9 <= ((2 * ((long) i3)) + 3) + ((((long) i3) + 3) * 3);
    }

    public static void zzf(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zze(i, list, z);
        }
    }

    public static void zzf(Class<?> cls) {
        if (!zzbbo.class.isAssignableFrom(cls) && zzdyf != null && !zzdyf.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzg(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzi(i, list, z);
        }
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzaf(list) + (list.size() * zzbav.zzcd(i));
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzag(list);
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzah(list);
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzai(list);
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzaj(list);
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzak(list);
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzal(list);
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzbav.zzt(i, 0) * size;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzg(i, 0);
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzg(i, true);
    }
}
