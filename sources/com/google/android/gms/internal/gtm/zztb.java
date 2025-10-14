package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zztb {
    private static final Class<?> zzbdu = zzqy();
    private static final zztr<?, ?> zzbdv = zzl(false);
    private static final zztr<?, ?> zzbdw = zzl(true);
    private static final zztr<?, ?> zzbdx = new zztt();

    public static void zzj(Class<?> cls) {
        if (!zzrc.class.isAssignableFrom(cls) && zzbdu != null && !zzbdu.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzum zzum, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzum zzum) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzps> list, zzum zzum) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzum zzum, zzsz zzsz) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zza(i, list, zzsz);
        }
    }

    public static void zzb(int i, List<?> list, zzum zzum, zzsz zzsz) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzum.zzb(i, list, zzsz);
        }
    }

    static int zzw(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzry) {
            zzry zzry = (zzry) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzqj.zzs(zzry.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzqj.zzs(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzw(list) + (list.size() * zzqj.zzbb(i));
    }

    static int zzx(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzry) {
            zzry zzry = (zzry) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzqj.zzt(zzry.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzqj.zzt(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzqj.zzbb(i)) + zzx(list);
    }

    static int zzy(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzry) {
            zzry zzry = (zzry) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzqj.zzu(zzry.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzqj.zzu(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzqj.zzbb(i)) + zzy(list);
    }

    static int zzz(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzqj.zzbh(zzrd.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzqj.zzbh(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzqj.zzbb(i)) + zzz(list);
    }

    static int zzaa(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzqj.zzbc(zzrd.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzqj.zzbc(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzqj.zzbb(i)) + zzaa(list);
    }

    static int zzab(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzqj.zzbd(zzrd.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzqj.zzbd(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzqj.zzbb(i)) + zzab(list);
    }

    static int zzac(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzqj.zzbe(zzrd.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzqj.zzbe(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzqj.zzbb(i)) + zzac(list);
    }

    static int zzad(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzqj.zzl(i, 0) * size;
    }

    static int zzae(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzqj.zzg(i, 0);
    }

    static int zzaf(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzqj.zzc(i, true);
    }

    static int zzc(int i, List<?> list) {
        int zzda;
        int zzda2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzqj.zzbb(i) * size;
        if (list instanceof zzrt) {
            zzrt zzrt = (zzrt) list;
            int i2 = 0;
            while (i2 < size) {
                Object zzbn = zzrt.zzbn(i2);
                if (zzbn instanceof zzps) {
                    zzda2 = zzqj.zzb((zzps) zzbn);
                } else {
                    zzda2 = zzqj.zzda((String) zzbn);
                }
                i2++;
                zzbb = zzda2 + zzbb;
            }
            return zzbb;
        }
        int i3 = 0;
        while (i3 < size) {
            Object obj = list.get(i3);
            if (obj instanceof zzps) {
                zzda = zzqj.zzb((zzps) obj);
            } else {
                zzda = zzqj.zzda((String) obj);
            }
            i3++;
            zzbb = zzda + zzbb;
        }
        return zzbb;
    }

    static int zzc(int i, Object obj, zzsz zzsz) {
        if (obj instanceof zzrr) {
            return zzqj.zza(i, (zzrr) obj);
        }
        return zzqj.zzb(i, (zzsk) obj, zzsz);
    }

    static int zzc(int i, List<?> list, zzsz zzsz) {
        int zzb;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzqj.zzbb(i) * size;
        int i2 = 0;
        while (i2 < size) {
            Object obj = list.get(i2);
            if (obj instanceof zzrr) {
                zzb = zzqj.zza((zzrr) obj);
            } else {
                zzb = zzqj.zzb((zzsk) obj, zzsz);
            }
            i2++;
            zzbb = zzb + zzbb;
        }
        return zzbb;
    }

    static int zzd(int i, List<zzps> list) {
        int i2 = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzqj.zzbb(i) * size;
        while (true) {
            int i3 = i2;
            if (i3 >= list.size()) {
                return zzbb;
            }
            zzbb += zzqj.zzb(list.get(i3));
            i2 = i3 + 1;
        }
    }

    static int zzd(int i, List<zzsk> list, zzsz zzsz) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzqj.zzc(i, list.get(i3), zzsz);
        }
        return i2;
    }

    public static zztr<?, ?> zzqv() {
        return zzbdv;
    }

    public static zztr<?, ?> zzqw() {
        return zzbdw;
    }

    public static zztr<?, ?> zzqx() {
        return zzbdx;
    }

    private static zztr<?, ?> zzl(boolean z) {
        try {
            Class<?> zzqz = zzqz();
            if (zzqz == null) {
                return null;
            }
            return (zztr) zzqz.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzqy() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzqz() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzsf zzsf, T t, T t2, long j) {
        zztx.zza((Object) t, j, zzsf.zzc(zztx.zzp(t, j), zztx.zzp(t2, j)));
    }

    static <T, FT extends zzqv<FT>> void zza(zzqq<FT> zzqq, T t, T t2) {
        zzqt<FT> zzr = zzqq.zzr(t2);
        if (!zzr.zzaxo.isEmpty()) {
            zzqq.zzs(t).zza(zzr);
        }
    }

    static <T, UT, UB> void zza(zztr<UT, UB> zztr, T t, T t2) {
        zztr.zzf(t, zztr.zzh(zztr.zzag(t), zztr.zzag(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzrh zzrh, UB ub, zztr<UT, UB> zztr) {
        UB ub2;
        int i2;
        if (zzrh == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            int i4 = 0;
            ub2 = ub;
            while (i3 < size) {
                int intValue = list.get(i3).intValue();
                if (zzrh.zzb(intValue)) {
                    if (i3 != i4) {
                        list.set(i4, Integer.valueOf(intValue));
                    }
                    i2 = i4 + 1;
                } else {
                    ub2 = zza(i, intValue, ub2, zztr);
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
                if (!zzrh.zzb(intValue2)) {
                    ub = zza(i, intValue2, ub, zztr);
                    it.remove();
                }
            }
            ub2 = ub;
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zztr<UT, UB> zztr) {
        if (ub == null) {
            ub = zztr.zzri();
        }
        zztr.zza(ub, i, (long) i2);
        return ub;
    }
}
