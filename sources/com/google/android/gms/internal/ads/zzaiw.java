package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@TargetApi(21)
@ParametersAreNonnullByDefault
final class zzaiw {
    private static final Map<String, String> zzcnl;
    private final List<String> zzcnm;
    private final zzaii zzcnn;
    private final Context zzrt;

    static {
        HashMap hashMap = new HashMap();
        if (PlatformVersion.isAtLeastLollipop()) {
            hashMap.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
            hashMap.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        }
        zzcnl = hashMap;
    }

    zzaiw(Context context, List<String> list, zzaii zzaii) {
        this.zzrt = context;
        this.zzcnm = list;
        this.zzcnn = zzaii;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> zzc(java.lang.String[] r11) {
        /*
            r10 = this;
            r2 = 1
            r1 = 0
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            int r5 = r11.length
            r3 = r1
        L_0x0009:
            if (r3 >= r5) goto L_0x007f
            r6 = r11[r3]
            java.util.List<java.lang.String> r0 = r10.zzcnm
            java.util.Iterator r7 = r0.iterator()
        L_0x0013:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x006f
            java.lang.Object r0 = r7.next()
            java.lang.String r0 = (java.lang.String) r0
            boolean r8 = r0.equals(r6)
            if (r8 == 0) goto L_0x004d
            r0 = r2
        L_0x0026:
            if (r0 == 0) goto L_0x0079
            java.util.Map<java.lang.String, java.lang.String> r0 = zzcnl
            boolean r0 = r0.containsKey(r6)
            if (r0 == 0) goto L_0x0043
            com.google.android.gms.ads.internal.zzbv.zzek()
            android.content.Context r7 = r10.zzrt
            java.util.Map<java.lang.String, java.lang.String> r0 = zzcnl
            java.lang.Object r0 = r0.get(r6)
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = com.google.android.gms.internal.ads.zzakk.zzl(r7, r0)
            if (r0 == 0) goto L_0x0071
        L_0x0043:
            r0 = r2
        L_0x0044:
            if (r0 == 0) goto L_0x0073
            r4.add(r6)
        L_0x0049:
            int r0 = r3 + 1
            r3 = r0
            goto L_0x0009
        L_0x004d:
            java.lang.String r8 = "android.webkit.resource."
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r9 = r0.length()
            if (r9 == 0) goto L_0x0069
            java.lang.String r0 = r8.concat(r0)
        L_0x0061:
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x0013
            r0 = r2
            goto L_0x0026
        L_0x0069:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r8)
            goto L_0x0061
        L_0x006f:
            r0 = r1
            goto L_0x0026
        L_0x0071:
            r0 = r1
            goto L_0x0044
        L_0x0073:
            com.google.android.gms.internal.ads.zzaii r0 = r10.zzcnn
            r0.zzch(r6)
            goto L_0x0049
        L_0x0079:
            com.google.android.gms.internal.ads.zzaii r0 = r10.zzcnn
            r0.zzcg(r6)
            goto L_0x0049
        L_0x007f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaiw.zzc(java.lang.String[]):java.util.List");
    }
}
