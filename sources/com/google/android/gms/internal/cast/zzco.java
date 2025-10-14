package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@VisibleForTesting
public class zzco extends zzcu {
    private final List<zzdt> zzyg = Collections.synchronizedList(new ArrayList());

    public zzco(String str, String str2, String str3) {
        super(str, str2, (String) null);
    }

    public void zzdz() {
        synchronized (this.zzyg) {
            for (zzdt zzx : this.zzyg) {
                zzx.zzx(CastStatusCodes.CANCELED);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final List<zzdt> zzea() {
        return this.zzyg;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdt zzdt) {
        this.zzyg.add(zzdt);
    }
}
