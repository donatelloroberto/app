package com.google.android.gms.ads.internal;

import java.util.concurrent.Callable;

final class zzg implements Callable<String> {
    private final /* synthetic */ zzd zzwk;

    zzg(zzd zzd) {
        this.zzwk = zzd;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0014, code lost:
        r0 = com.google.android.gms.ads.internal.zzbv.zzem().zzax(r3.zzwk.zzvw.zzrt);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object call() throws java.lang.Exception {
        /*
            r3 = this;
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbdj
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r2.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x002b
            com.google.android.gms.internal.ads.zzakq r0 = com.google.android.gms.ads.internal.zzbv.zzem()
            com.google.android.gms.ads.internal.zzd r2 = r3.zzwk
            com.google.android.gms.ads.internal.zzbw r2 = r2.zzvw
            android.content.Context r2 = r2.zzrt
            android.webkit.CookieManager r0 = r0.zzax(r2)
            if (r0 == 0) goto L_0x002b
            java.lang.String r1 = "googleads.g.doubleclick.net"
            java.lang.String r0 = r0.getCookie(r1)
        L_0x002a:
            return r0
        L_0x002b:
            r0 = r1
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzg.call():java.lang.Object");
    }
}
