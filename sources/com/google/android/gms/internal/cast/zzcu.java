package com.google.android.gms.internal.cast;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class zzcu {
    protected final zzdo zzze;
    private final String zzzf;
    private zzdr zzzg;

    protected zzcu(String str, String str2, String str3) {
        zzdc.zzq(str);
        this.zzzf = str;
        this.zzze = new zzdo(str2);
        setSessionLabel(str3);
    }

    public final void setSessionLabel(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.zzze.zzv(str);
        }
    }

    public final String getNamespace() {
        return this.zzzf;
    }

    public final void zza(zzdr zzdr) {
        this.zzzg = zzdr;
        if (this.zzzg == null) {
            zzdz();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, long j, String str2) throws IllegalStateException {
        Object[] objArr = {str, null};
        this.zzzg.zza(this.zzzf, str, j, (String) null);
    }

    public void zzp(@NonNull String str) {
    }

    public void zza(long j, int i) {
    }

    /* access modifiers changed from: protected */
    public final long zzeg() {
        return this.zzzg.zzm();
    }

    public void zzdz() {
    }
}
