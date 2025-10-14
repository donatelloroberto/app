package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.HashMap;

@ShowFirstParty
public final class zzy extends zzi<zzy> {
    public String zzuq;
    public boolean zzur;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put(PushConstants.CHANNEL_DESCRIPTION, this.zzuq);
        hashMap.put(AppMeasurement.Param.FATAL, Boolean.valueOf(this.zzur));
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzy zzy = (zzy) zzi;
        if (!TextUtils.isEmpty(this.zzuq)) {
            zzy.zzuq = this.zzuq;
        }
        if (this.zzur) {
            zzy.zzur = this.zzur;
        }
    }
}
