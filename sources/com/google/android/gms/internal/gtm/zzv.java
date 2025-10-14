package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;

@ShowFirstParty
@VisibleForTesting
public final class zzv extends zzi<zzv> {
    private String zzuj;
    public int zzuk;
    public int zzul;
    public int zzum;
    public int zzun;
    public int zzuo;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("language", this.zzuj);
        hashMap.put("screenColors", Integer.valueOf(this.zzuk));
        hashMap.put("screenWidth", Integer.valueOf(this.zzul));
        hashMap.put("screenHeight", Integer.valueOf(this.zzum));
        hashMap.put("viewportWidth", Integer.valueOf(this.zzun));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzuo));
        return zza((Object) hashMap);
    }

    public final String getLanguage() {
        return this.zzuj;
    }

    public final void setLanguage(String str) {
        this.zzuj = str;
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzv zzv = (zzv) zzi;
        if (this.zzuk != 0) {
            zzv.zzuk = this.zzuk;
        }
        if (this.zzul != 0) {
            zzv.zzul = this.zzul;
        }
        if (this.zzum != 0) {
            zzv.zzum = this.zzum;
        }
        if (this.zzun != 0) {
            zzv.zzun = this.zzun;
        }
        if (this.zzuo != 0) {
            zzv.zzuo = this.zzuo;
        }
        if (!TextUtils.isEmpty(this.zzuj)) {
            zzv.zzuj = this.zzuj;
        }
    }
}
