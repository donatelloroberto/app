package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzb;
import com.google.android.gms.internal.gtm.zzl;
import java.util.Map;

final class zzh extends zzbq {
    private static final String ID = zza.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzadu = zzb.COMPONENT.toString();
    private static final String zzadv = zzb.CONVERSION_ID.toString();
    private final Context zzrm;

    public zzh(Context context) {
        super(ID, zzadv);
        this.zzrm = context;
    }

    public final boolean zzgw() {
        return true;
    }

    public final zzl zzb(Map<String, zzl> map) {
        zzl zzl = map.get(zzadv);
        if (zzl == null) {
            return zzgj.zzkc();
        }
        String zzc = zzgj.zzc(zzl);
        zzl zzl2 = map.get(zzadu);
        String zzc2 = zzl2 != null ? zzgj.zzc(zzl2) : null;
        Context context = this.zzrm;
        String str = zzcw.zzahk.get(zzc);
        if (str == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            if (sharedPreferences != null) {
                str = sharedPreferences.getString(zzc, "");
            } else {
                str = "";
            }
            zzcw.zzahk.put(zzc, str);
        }
        String zze = zzcw.zze(str, zzc2);
        if (zze != null) {
            return zzgj.zzi(zze);
        }
        return zzgj.zzkc();
    }
}
