package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;

final class zzg implements DataLayer.zzb {
    private final Context zzrm;

    public zzg(Context context) {
        this.zzrm = context;
    }

    public final void zzc(Map<String, Object> map) {
        Object obj;
        String queryParameter;
        Object obj2;
        Object obj3 = map.get("gtm.url");
        if (obj3 != null || (obj2 = map.get("gtm")) == null || !(obj2 instanceof Map)) {
            obj = obj3;
        } else {
            obj = ((Map) obj2).get(ImagesContract.URL);
        }
        if (obj != null && (obj instanceof String) && (queryParameter = Uri.parse((String) obj).getQueryParameter("referrer")) != null) {
            zzcw.zzf(this.zzrm, queryParameter);
        }
    }
}
