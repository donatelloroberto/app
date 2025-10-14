package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.util.Arrays;

final class zzbdj {
    /* access modifiers changed from: private */
    public final int flags;
    private final Object[] zzdwh;
    /* access modifiers changed from: private */
    public final int zzdwi;
    /* access modifiers changed from: private */
    public final int zzdwj;
    /* access modifiers changed from: private */
    public final int zzdwk;
    /* access modifiers changed from: private */
    public final int[] zzdwq;
    private final zzbdk zzdxf;
    private Class<?> zzdxg;
    /* access modifiers changed from: private */
    public final int zzdxh;
    private final int zzdxi;
    private final int zzdxj;
    /* access modifiers changed from: private */
    public final int zzdxk;
    /* access modifiers changed from: private */
    public final int zzdxl;
    /* access modifiers changed from: private */
    public final int zzdxm;
    private int zzdxn;
    private int zzdxo;
    private int zzdxp = Integer.MAX_VALUE;
    private int zzdxq = Integer.MIN_VALUE;
    private int zzdxr = 0;
    private int zzdxs = 0;
    private int zzdxt = 0;
    private int zzdxu = 0;
    private int zzdxv = 0;
    private int zzdxw;
    private int zzdxx;
    private int zzdxy;
    private int zzdxz;
    private int zzdya;
    private Field zzdyb;
    private Object zzdyc;
    private Object zzdyd;
    private Object zzdye;

    zzbdj(Class<?> cls, String str, Object[] objArr) {
        int[] iArr = null;
        this.zzdxg = cls;
        this.zzdxf = new zzbdk(str);
        this.zzdwh = objArr;
        this.flags = this.zzdxf.next();
        this.zzdxh = this.zzdxf.next();
        if (this.zzdxh == 0) {
            this.zzdxi = 0;
            this.zzdxj = 0;
            this.zzdwi = 0;
            this.zzdwj = 0;
            this.zzdxk = 0;
            this.zzdxl = 0;
            this.zzdwk = 0;
            this.zzdxm = 0;
            this.zzdwq = null;
            return;
        }
        this.zzdxi = this.zzdxf.next();
        this.zzdxj = this.zzdxf.next();
        this.zzdwi = this.zzdxf.next();
        this.zzdwj = this.zzdxf.next();
        this.zzdxl = this.zzdxf.next();
        this.zzdwk = this.zzdxf.next();
        this.zzdxk = this.zzdxf.next();
        this.zzdxm = this.zzdxf.next();
        int next = this.zzdxf.next();
        this.zzdwq = next != 0 ? new int[next] : iArr;
        this.zzdxn = (this.zzdxi << 1) + this.zzdxj;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            throw new RuntimeException(new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length()).append("Field ").append(str).append(" for ").append(name).append(" not found. Known fields are ").append(arrays).toString());
        }
    }

    private final Object zzaey() {
        Object[] objArr = this.zzdwh;
        int i = this.zzdxn;
        this.zzdxn = i + 1;
        return objArr[i];
    }

    private final boolean zzafa() {
        return (this.flags & 1) == 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean next() {
        boolean z = false;
        if (!this.zzdxf.hasNext()) {
            return false;
        }
        this.zzdxw = this.zzdxf.next();
        this.zzdxx = this.zzdxf.next();
        this.zzdxy = this.zzdxx & 255;
        if (this.zzdxw < this.zzdxp) {
            this.zzdxp = this.zzdxw;
        }
        if (this.zzdxw > this.zzdxq) {
            this.zzdxq = this.zzdxw;
        }
        if (this.zzdxy == zzbbj.MAP.id()) {
            this.zzdxr++;
        } else if (this.zzdxy >= zzbbj.DOUBLE_LIST.id() && this.zzdxy <= zzbbj.GROUP_LIST.id()) {
            this.zzdxs++;
        }
        this.zzdxv++;
        if (zzbdo.zze(this.zzdxp, this.zzdxw, this.zzdxv)) {
            this.zzdxu = this.zzdxw + 1;
            this.zzdxt = this.zzdxu - this.zzdxp;
        } else {
            this.zzdxt++;
        }
        if ((this.zzdxx & 1024) != 0) {
            int[] iArr = this.zzdwq;
            int i = this.zzdxo;
            this.zzdxo = i + 1;
            iArr[i] = this.zzdxw;
        }
        this.zzdyc = null;
        this.zzdyd = null;
        this.zzdye = null;
        if (zzafb()) {
            this.zzdxz = this.zzdxf.next();
            if (this.zzdxy == zzbbj.MESSAGE.id() + 51 || this.zzdxy == zzbbj.GROUP.id() + 51) {
                this.zzdyc = zzaey();
            } else if (this.zzdxy == zzbbj.ENUM.id() + 51 && zzafa()) {
                this.zzdyd = zzaey();
            }
        } else {
            this.zzdyb = zza(this.zzdxg, (String) zzaey());
            if (zzaff()) {
                this.zzdya = this.zzdxf.next();
            }
            if (this.zzdxy == zzbbj.MESSAGE.id() || this.zzdxy == zzbbj.GROUP.id()) {
                this.zzdyc = this.zzdyb.getType();
            } else if (this.zzdxy == zzbbj.MESSAGE_LIST.id() || this.zzdxy == zzbbj.GROUP_LIST.id()) {
                this.zzdyc = zzaey();
            } else if (this.zzdxy == zzbbj.ENUM.id() || this.zzdxy == zzbbj.ENUM_LIST.id() || this.zzdxy == zzbbj.ENUM_LIST_PACKED.id()) {
                if (zzafa()) {
                    this.zzdyd = zzaey();
                }
            } else if (this.zzdxy == zzbbj.MAP.id()) {
                this.zzdye = zzaey();
                if ((this.zzdxx & 2048) != 0) {
                    z = true;
                }
                if (z) {
                    this.zzdyd = zzaey();
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int zzaci() {
        return this.zzdxw;
    }

    /* access modifiers changed from: package-private */
    public final int zzaez() {
        return this.zzdxy;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzafb() {
        return this.zzdxy > zzbbj.MAP.id();
    }

    /* access modifiers changed from: package-private */
    public final Field zzafc() {
        int i = this.zzdxz << 1;
        Object obj = this.zzdwh[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzdxg, (String) obj);
        this.zzdwh[i] = zza;
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final Field zzafd() {
        int i = (this.zzdxz << 1) + 1;
        Object obj = this.zzdwh[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzdxg, (String) obj);
        this.zzdwh[i] = zza;
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final Field zzafe() {
        return this.zzdyb;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaff() {
        return zzafa() && this.zzdxy <= zzbbj.GROUP.id();
    }

    /* access modifiers changed from: package-private */
    public final Field zzafg() {
        int i = (this.zzdya / 32) + (this.zzdxi << 1);
        Object obj = this.zzdwh[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzdxg, (String) obj);
        this.zzdwh[i] = zza;
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final int zzafh() {
        return this.zzdya % 32;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzafi() {
        return (this.zzdxx & 256) != 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzafj() {
        return (this.zzdxx & 512) != 0;
    }

    /* access modifiers changed from: package-private */
    public final Object zzafk() {
        return this.zzdyc;
    }

    /* access modifiers changed from: package-private */
    public final Object zzafl() {
        return this.zzdyd;
    }

    /* access modifiers changed from: package-private */
    public final Object zzafm() {
        return this.zzdye;
    }
}
