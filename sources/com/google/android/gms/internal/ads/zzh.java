package com.google.android.gms.internal.ads;

import com.android.volley.DefaultRetryPolicy;

public final class zzh implements zzab {
    private int zzr;
    private int zzs;
    private final int zzt;
    private final float zzu;

    public zzh() {
        this(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, 1.0f);
    }

    private zzh(int i, int i2, float f) {
        this.zzr = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        this.zzt = 1;
        this.zzu = 1.0f;
    }

    public final void zza(zzae zzae) throws zzae {
        this.zzs++;
        this.zzr = (int) (((float) this.zzr) + (((float) this.zzr) * this.zzu));
        if (!(this.zzs <= this.zzt)) {
            throw zzae;
        }
    }

    public final int zzc() {
        return this.zzr;
    }

    public final int zzd() {
        return this.zzs;
    }
}
