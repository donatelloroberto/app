package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzb;
import com.google.android.gms.internal.gtm.zzl;
import java.util.Map;
import java.util.Set;

@ShowFirstParty
@VisibleForTesting
public abstract class zzef extends zzbq {
    private static final String zzags = zzb.ARG0.toString();
    private static final String zzaio = zzb.ARG1.toString();

    public zzef(String str) {
        super(str, zzags, zzaio);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzl zzl, zzl zzl2, Map<String, zzl> map);

    public final zzl zzb(Map<String, zzl> map) {
        for (zzl zzl : map.values()) {
            if (zzl == zzgj.zzkc()) {
                return zzgj.zzi(false);
            }
        }
        zzl zzl2 = map.get(zzags);
        zzl zzl3 = map.get(zzaio);
        return zzgj.zzi(Boolean.valueOf((zzl2 == null || zzl3 == null) ? false : zza(zzl2, zzl3, map)));
    }

    public final boolean zzgw() {
        return true;
    }

    public final /* bridge */ /* synthetic */ Set zzig() {
        return super.zzig();
    }

    public final /* bridge */ /* synthetic */ String zzif() {
        return super.zzif();
    }
}
