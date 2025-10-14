package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;

@ShowFirstParty
@VisibleForTesting
public final class zzaa extends zzi<zzaa> {
    private String zzva;
    private int zzvb;
    private int zzvc;
    private String zzvd;
    private String zzve;
    private boolean zzvf;
    private boolean zzvg;

    public zzaa() {
        this(false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzaa(boolean r9) {
        /*
            r8 = this;
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r1 = 0
            java.util.UUID r2 = java.util.UUID.randomUUID()
            long r4 = r2.getLeastSignificantBits()
            long r4 = r4 & r6
            int r0 = (int) r4
            if (r0 == 0) goto L_0x0014
        L_0x0010:
            r8.<init>(r1, r0)
            return
        L_0x0014:
            long r2 = r2.getMostSignificantBits()
            long r2 = r2 & r6
            int r0 = (int) r2
            if (r0 != 0) goto L_0x0010
            java.lang.String r0 = "GAv4"
            java.lang.String r2 = "UUID.randomUUID() returned 0."
            android.util.Log.e(r0, r2)
            r0 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzaa.<init>(boolean):void");
    }

    @ShowFirstParty
    @VisibleForTesting
    private zzaa(boolean z, int i) {
        Preconditions.checkNotZero(i);
        this.zzvb = i;
        this.zzvg = false;
    }

    public final String zzca() {
        return this.zzva;
    }

    public final int zzcb() {
        return this.zzvb;
    }

    public final String zzcc() {
        return this.zzve;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("screenName", this.zzva);
        hashMap.put("interstitial", Boolean.valueOf(this.zzvf));
        hashMap.put("automatic", Boolean.valueOf(this.zzvg));
        hashMap.put("screenId", Integer.valueOf(this.zzvb));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzvc));
        hashMap.put("referrerScreenName", this.zzvd);
        hashMap.put("referrerUri", this.zzve);
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzaa zzaa = (zzaa) zzi;
        if (!TextUtils.isEmpty(this.zzva)) {
            zzaa.zzva = this.zzva;
        }
        if (this.zzvb != 0) {
            zzaa.zzvb = this.zzvb;
        }
        if (this.zzvc != 0) {
            zzaa.zzvc = this.zzvc;
        }
        if (!TextUtils.isEmpty(this.zzvd)) {
            zzaa.zzvd = this.zzvd;
        }
        if (!TextUtils.isEmpty(this.zzve)) {
            String str = this.zzve;
            if (TextUtils.isEmpty(str)) {
                zzaa.zzve = null;
            } else {
                zzaa.zzve = str;
            }
        }
        if (this.zzvf) {
            zzaa.zzvf = this.zzvf;
        }
        if (this.zzvg) {
            zzaa.zzvg = this.zzvg;
        }
    }
}
