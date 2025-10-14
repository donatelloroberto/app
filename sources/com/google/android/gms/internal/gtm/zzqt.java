package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzqv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzqt<FieldDescriptorType extends zzqv<FieldDescriptorType>> {
    private static final zzqt zzaxq = new zzqt(true);
    private boolean zzaut;
    final zztc<FieldDescriptorType, Object> zzaxo = zztc.zzbu(16);
    private boolean zzaxp = false;

    private zzqt() {
    }

    private zzqt(boolean z) {
        zzmi();
    }

    public static <T extends zzqv<T>> zzqt<T> zzov() {
        return zzaxq;
    }

    public final void zzmi() {
        if (!this.zzaut) {
            this.zzaxo.zzmi();
            this.zzaut = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzaut;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqt)) {
            return false;
        }
        return this.zzaxo.equals(((zzqt) obj).zzaxo);
    }

    public final int hashCode() {
        return this.zzaxo.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzaxp) {
            return new zzrq(this.zzaxo.entrySet().iterator());
        }
        return this.zzaxo.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzaxp) {
            return new zzrq(this.zzaxo.zzrc().iterator());
        }
        return this.zzaxo.zzrc().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzaxo.get(fielddescriptortype);
        if (obj instanceof zzrn) {
            return zzrn.zzpy();
        }
        return obj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(FieldDescriptorType r7, java.lang.Object r8) {
        /*
            r6 = this;
            boolean r0 = r7.zzoz()
            if (r0 == 0) goto L_0x0034
            boolean r0 = r8 instanceof java.util.List
            if (r0 != 0) goto L_0x0012
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Wrong object type used with protocol message reflection."
            r0.<init>(r1)
            throw r0
        L_0x0012:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r8 = (java.util.List) r8
            r1.addAll(r8)
            r0 = r1
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r3 = r0.size()
            r2 = 0
        L_0x0024:
            if (r2 >= r3) goto L_0x003c
            java.lang.Object r4 = r0.get(r2)
            int r2 = r2 + 1
            com.google.android.gms.internal.gtm.zzug r5 = r7.zzox()
            zza((com.google.android.gms.internal.gtm.zzug) r5, (java.lang.Object) r4)
            goto L_0x0024
        L_0x0034:
            com.google.android.gms.internal.gtm.zzug r0 = r7.zzox()
            zza((com.google.android.gms.internal.gtm.zzug) r0, (java.lang.Object) r8)
            r1 = r8
        L_0x003c:
            boolean r0 = r1 instanceof com.google.android.gms.internal.gtm.zzrn
            if (r0 == 0) goto L_0x0043
            r0 = 1
            r6.zzaxp = r0
        L_0x0043:
            com.google.android.gms.internal.gtm.zztc<FieldDescriptorType, java.lang.Object> r0 = r6.zzaxo
            r0.put(r7, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzqt.zza(com.google.android.gms.internal.gtm.zzqv, java.lang.Object):void");
    }

    private static void zza(zzug zzug, Object obj) {
        boolean z = false;
        zzre.checkNotNull(obj);
        switch (zzqu.zzaxr[zzug.zzrs().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if ((obj instanceof zzps) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzrf)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzsk) || (obj instanceof zzrn)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzaxo.zzra(); i++) {
            if (!zzc(this.zzaxo.zzbv(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> zzc : this.zzaxo.zzrb()) {
            if (!zzc(zzc)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        zzqv zzqv = (zzqv) entry.getKey();
        if (zzqv.zzoy() == zzul.MESSAGE) {
            if (zzqv.zzoz()) {
                for (zzsk isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzsk) {
                    if (!((zzsk) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzrn) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzqt<FieldDescriptorType> zzqt) {
        for (int i = 0; i < zzqt.zzaxo.zzra(); i++) {
            zzd(zzqt.zzaxo.zzbv(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> zzd : zzqt.zzaxo.zzrb()) {
            zzd(zzd);
        }
    }

    private static Object zzu(Object obj) {
        if (obj instanceof zzsq) {
            return ((zzsq) obj).zzqo();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        Object zzpm;
        zzqv zzqv = (zzqv) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzrn) {
            value = zzrn.zzpy();
        }
        if (zzqv.zzoz()) {
            Object zza = zza(zzqv);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzu : (List) value) {
                ((List) zza).add(zzu(zzu));
            }
            this.zzaxo.put(zzqv, zza);
        } else if (zzqv.zzoy() == zzul.MESSAGE) {
            Object zza2 = zza(zzqv);
            if (zza2 == null) {
                this.zzaxo.put(zzqv, zzu(value));
                return;
            }
            if (zza2 instanceof zzsq) {
                zzpm = zzqv.zza((zzsq) zza2, (zzsq) value);
            } else {
                zzpm = zzqv.zza(((zzsk) zza2).zzpg(), (zzsk) value).zzpm();
            }
            this.zzaxo.put(zzqv, zzpm);
        } else {
            this.zzaxo.put(zzqv, zzu(value));
        }
    }

    static void zza(zzqj zzqj, zzug zzug, int i, Object obj) throws IOException {
        if (zzug == zzug.GROUP) {
            zzre.zzf((zzsk) obj);
            zzqj.zzd(i, 3);
            ((zzsk) obj).zzb(zzqj);
            zzqj.zzd(i, 4);
            return;
        }
        zzqj.zzd(i, zzug.zzrt());
        switch (zzqu.zzaws[zzug.ordinal()]) {
            case 1:
                zzqj.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                zzqj.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzqj.zzp(((Long) obj).longValue());
                return;
            case 4:
                zzqj.zzp(((Long) obj).longValue());
                return;
            case 5:
                zzqj.zzax(((Integer) obj).intValue());
                return;
            case 6:
                zzqj.zzr(((Long) obj).longValue());
                return;
            case 7:
                zzqj.zzba(((Integer) obj).intValue());
                return;
            case 8:
                zzqj.zzi(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzsk) obj).zzb(zzqj);
                return;
            case 10:
                zzqj.zzb((zzsk) obj);
                return;
            case 11:
                if (obj instanceof zzps) {
                    zzqj.zza((zzps) obj);
                    return;
                } else {
                    zzqj.zzcz((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzps) {
                    zzqj.zza((zzps) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzqj.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zzqj.zzay(((Integer) obj).intValue());
                return;
            case 14:
                zzqj.zzba(((Integer) obj).intValue());
                return;
            case 15:
                zzqj.zzr(((Long) obj).longValue());
                return;
            case 16:
                zzqj.zzaz(((Integer) obj).intValue());
                return;
            case 17:
                zzqj.zzq(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzrf) {
                    zzqj.zzax(((zzrf) obj).zzc());
                    return;
                } else {
                    zzqj.zzax(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzow() {
        int i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = i2;
            if (i3 >= this.zzaxo.zzra()) {
                break;
            }
            i2 = zze(this.zzaxo.zzbv(i3)) + i;
            i3++;
        }
        for (Map.Entry<FieldDescriptorType, Object> zze : this.zzaxo.zzrb()) {
            i += zze(zze);
        }
        return i;
    }

    private static int zze(Map.Entry<FieldDescriptorType, Object> entry) {
        zzqv zzqv = (zzqv) entry.getKey();
        Object value = entry.getValue();
        if (zzqv.zzoy() != zzul.MESSAGE || zzqv.zzoz() || zzqv.zzpa()) {
            return zzb((zzqv<?>) zzqv, value);
        }
        if (value instanceof zzrn) {
            return zzqj.zzb(((zzqv) entry.getKey()).zzc(), (zzrr) (zzrn) value);
        }
        return zzqj.zzd(((zzqv) entry.getKey()).zzc(), (zzsk) value);
    }

    static int zza(zzug zzug, int i, Object obj) {
        int i2;
        int zzbb = zzqj.zzbb(i);
        if (zzug == zzug.GROUP) {
            zzre.zzf((zzsk) obj);
            i2 = zzbb << 1;
        } else {
            i2 = zzbb;
        }
        return i2 + zzb(zzug, obj);
    }

    private static int zzb(zzug zzug, Object obj) {
        switch (zzqu.zzaws[zzug.ordinal()]) {
            case 1:
                return zzqj.zzc(((Double) obj).doubleValue());
            case 2:
                return zzqj.zzb(((Float) obj).floatValue());
            case 3:
                return zzqj.zzs(((Long) obj).longValue());
            case 4:
                return zzqj.zzt(((Long) obj).longValue());
            case 5:
                return zzqj.zzbc(((Integer) obj).intValue());
            case 6:
                return zzqj.zzv(((Long) obj).longValue());
            case 7:
                return zzqj.zzbf(((Integer) obj).intValue());
            case 8:
                return zzqj.zzj(((Boolean) obj).booleanValue());
            case 9:
                return zzqj.zzd((zzsk) obj);
            case 10:
                if (obj instanceof zzrn) {
                    return zzqj.zza((zzrr) (zzrn) obj);
                }
                return zzqj.zzc((zzsk) obj);
            case 11:
                if (obj instanceof zzps) {
                    return zzqj.zzb((zzps) obj);
                }
                return zzqj.zzda((String) obj);
            case 12:
                if (obj instanceof zzps) {
                    return zzqj.zzb((zzps) obj);
                }
                return zzqj.zzh((byte[]) obj);
            case 13:
                return zzqj.zzbd(((Integer) obj).intValue());
            case 14:
                return zzqj.zzbg(((Integer) obj).intValue());
            case 15:
                return zzqj.zzw(((Long) obj).longValue());
            case 16:
                return zzqj.zzbe(((Integer) obj).intValue());
            case 17:
                return zzqj.zzu(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzrf) {
                    return zzqj.zzbh(((zzrf) obj).zzc());
                }
                return zzqj.zzbh(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzb(zzqv<?> zzqv, Object obj) {
        int i = 0;
        zzug zzox = zzqv.zzox();
        int zzc = zzqv.zzc();
        if (!zzqv.zzoz()) {
            return zza(zzox, zzc, obj);
        }
        if (zzqv.zzpa()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzox, zzb);
            }
            return zzqj.zzbj(i) + zzqj.zzbb(zzc) + i;
        }
        for (Object zza : (List) obj) {
            i += zza(zzox, zzc, zza);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzqt zzqt = new zzqt();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zzaxo.zzra()) {
                break;
            }
            Map.Entry<FieldDescriptorType, Object> zzbv = this.zzaxo.zzbv(i2);
            zzqt.zza((zzqv) zzbv.getKey(), zzbv.getValue());
            i = i2 + 1;
        }
        for (Map.Entry next : this.zzaxo.zzrb()) {
            zzqt.zza((zzqv) next.getKey(), next.getValue());
        }
        zzqt.zzaxp = this.zzaxp;
        return zzqt;
    }
}
