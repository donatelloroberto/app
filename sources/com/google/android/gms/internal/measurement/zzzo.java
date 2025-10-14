package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzzq;
import java.util.Iterator;
import java.util.Map;

final class zzzo<FieldDescriptorType extends zzzq<FieldDescriptorType>> {
    private static final zzzo zzbse = new zzzo(true);
    private boolean zzbme;
    private final zzaba<FieldDescriptorType, Object> zzbsc = zzaba.zzag(16);
    private boolean zzbsd = false;

    private zzzo() {
    }

    private zzzo(boolean z) {
        if (!this.zzbme) {
            this.zzbsc.zzrp();
            this.zzbme = true;
        }
    }

    private static void zza(zzabr zzabr, Object obj) {
        boolean z = false;
        zzzt.checkNotNull(obj);
        switch (zzzp.zzbsf[zzabr.zzve().ordinal()]) {
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
                if ((obj instanceof zzyy) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzzu)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzaan) || (obj instanceof zzzw)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(FieldDescriptorType r7, java.lang.Object r8) {
        /*
            r6 = this;
            boolean r0 = r7.zztt()
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
            com.google.android.gms.internal.measurement.zzabr r5 = r7.zzts()
            zza((com.google.android.gms.internal.measurement.zzabr) r5, (java.lang.Object) r4)
            goto L_0x0024
        L_0x0034:
            com.google.android.gms.internal.measurement.zzabr r0 = r7.zzts()
            zza((com.google.android.gms.internal.measurement.zzabr) r0, (java.lang.Object) r8)
            r1 = r8
        L_0x003c:
            boolean r0 = r1 instanceof com.google.android.gms.internal.measurement.zzzw
            if (r0 == 0) goto L_0x0043
            r0 = 1
            r6.zzbsd = r0
        L_0x0043:
            com.google.android.gms.internal.measurement.zzaba<FieldDescriptorType, java.lang.Object> r0 = r6.zzbsc
            r0.put(r7, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzzo.zza(com.google.android.gms.internal.measurement.zzzq, java.lang.Object):void");
    }

    public static <T extends zzzq<T>> zzzo<T> zztr() {
        return zzbse;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzzo zzzo = new zzzo();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zzbsc.zzus()) {
                break;
            }
            Map.Entry<FieldDescriptorType, Object> zzah = this.zzbsc.zzah(i2);
            zzzo.zza((zzzq) zzah.getKey(), zzah.getValue());
            i = i2 + 1;
        }
        for (Map.Entry next : this.zzbsc.zzut()) {
            zzzo.zza((zzzq) next.getKey(), next.getValue());
        }
        zzzo.zzbsd = this.zzbsd;
        return zzzo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzo)) {
            return false;
        }
        return this.zzbsc.equals(((zzzo) obj).zzbsc);
    }

    public final int hashCode() {
        return this.zzbsc.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzbsd ? new zzzz(this.zzbsc.entrySet().iterator()) : this.zzbsc.entrySet().iterator();
    }
}
