package com.google.android.gms.ads.internal.gmsg;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaab;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarr;
import com.google.android.gms.internal.ads.zzars;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarz;
import com.google.android.gms.internal.ads.zzasb;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.internal.ads.zzjd;
import java.util.Map;

@zzadh
public final class zzad<T extends zzarr & zzars & zzarw & zzarz & zzasb> implements zzv<T> {
    private final Context mContext;
    private final zzjd zzapt;
    private final zzb zzbll;
    private final zzd zzblm;
    private final zzx zzbmw;
    private final zzaab zzbmx;
    private final zzci zzbna;
    private final zzt zzbnb;
    private final zzn zzbnc;
    private final zzaqw zzbnd = null;
    private final zzang zzzw;

    public zzad(Context context, zzang zzang, zzci zzci, zzt zzt, zzjd zzjd, zzb zzb, zzd zzd, zzn zzn, zzx zzx, zzaab zzaab) {
        this.mContext = context;
        this.zzzw = zzang;
        this.zzbna = zzci;
        this.zzbnb = zzt;
        this.zzapt = zzjd;
        this.zzbll = zzb;
        this.zzblm = zzd;
        this.zzbmw = zzx;
        this.zzbmx = zzaab;
        this.zzbnc = zzn;
    }

    @VisibleForTesting
    static String zza(Context context, zzci zzci, String str, View view, @Nullable Activity activity) {
        if (zzci == null) {
            return str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (zzci.zzc(parse)) {
                parse = zzci.zza(parse, context, view, activity);
            }
            return parse.toString();
        } catch (zzcj e) {
            return str;
        } catch (Exception e2) {
            zzbv.zzeo().zza(e2, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            return str;
        }
    }

    private static boolean zzg(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zzh(Map<String, String> map) {
        String str = map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrm();
            }
            if ("l".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrl();
            }
            if ("c".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrn();
            }
        }
        return -1;
    }

    private final void zzl(boolean z) {
        if (this.zzbmx != null) {
            this.zzbmx.zzm(z);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0121 A[SYNTHETIC, Splitter:B:43:0x0121] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(java.lang.Object r10, java.util.Map r11) {
        /*
            r9 = this;
            r5 = 1
            r4 = 0
            com.google.android.gms.internal.ads.zzarr r10 = (com.google.android.gms.internal.ads.zzarr) r10
            java.lang.String r1 = "u"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            android.content.Context r2 = r10.getContext()
            java.lang.String r3 = com.google.android.gms.internal.ads.zzajb.zzb((java.lang.String) r1, (android.content.Context) r2)
            java.lang.String r1 = "a"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x0024
            java.lang.String r1 = "Action missing from an open GMSG."
            com.google.android.gms.internal.ads.zzakb.zzdk(r1)
        L_0x0023:
            return
        L_0x0024:
            com.google.android.gms.ads.internal.zzx r2 = r9.zzbmw
            if (r2 == 0) goto L_0x0036
            com.google.android.gms.ads.internal.zzx r2 = r9.zzbmw
            boolean r2 = r2.zzcy()
            if (r2 != 0) goto L_0x0036
            com.google.android.gms.ads.internal.zzx r1 = r9.zzbmw
            r1.zzs(r3)
            goto L_0x0023
        L_0x0036:
            java.lang.String r2 = "expand"
            boolean r2 = r2.equalsIgnoreCase(r1)
            if (r2 == 0) goto L_0x005e
            r1 = r10
            com.google.android.gms.internal.ads.zzars r1 = (com.google.android.gms.internal.ads.zzars) r1
            boolean r1 = r1.zzuj()
            if (r1 == 0) goto L_0x004d
            java.lang.String r1 = "Cannot expand WebView that is already expanded."
            com.google.android.gms.internal.ads.zzakb.zzdk(r1)
            goto L_0x0023
        L_0x004d:
            r9.zzl(r4)
            com.google.android.gms.internal.ads.zzarw r10 = (com.google.android.gms.internal.ads.zzarw) r10
            boolean r1 = zzg(r11)
            int r2 = zzh(r11)
            r10.zza(r1, r2)
            goto L_0x0023
        L_0x005e:
            java.lang.String r2 = "webapp"
            boolean r2 = r2.equalsIgnoreCase(r1)
            if (r2 == 0) goto L_0x0097
            r9.zzl(r4)
            if (r3 == 0) goto L_0x0079
            com.google.android.gms.internal.ads.zzarw r10 = (com.google.android.gms.internal.ads.zzarw) r10
            boolean r1 = zzg(r11)
            int r2 = zzh(r11)
            r10.zza(r1, r2, r3)
            goto L_0x0023
        L_0x0079:
            com.google.android.gms.internal.ads.zzarw r10 = (com.google.android.gms.internal.ads.zzarw) r10
            boolean r3 = zzg(r11)
            int r4 = zzh(r11)
            java.lang.String r1 = "html"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "baseurl"
            java.lang.Object r2 = r11.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            r10.zza(r3, r4, r1, r2)
            goto L_0x0023
        L_0x0097:
            java.lang.String r2 = "app"
            boolean r1 = r2.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x00f3
            java.lang.String r2 = "true"
            java.lang.String r1 = "system_browser"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            boolean r1 = r2.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x00f3
            r9.zzl(r5)
            r10.getContext()
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto L_0x00c2
            java.lang.String r1 = "Destination url cannot be empty."
            com.google.android.gms.internal.ads.zzakb.zzdk(r1)
            goto L_0x0023
        L_0x00c2:
            com.google.android.gms.ads.internal.gmsg.zzae r2 = new com.google.android.gms.ads.internal.gmsg.zzae
            android.content.Context r3 = r10.getContext()
            r1 = r10
            com.google.android.gms.internal.ads.zzarz r1 = (com.google.android.gms.internal.ads.zzarz) r1
            com.google.android.gms.internal.ads.zzci r4 = r1.zzui()
            r1 = r10
            com.google.android.gms.internal.ads.zzasb r1 = (com.google.android.gms.internal.ads.zzasb) r1
            android.view.View r1 = r1.getView()
            r2.<init>(r3, r4, r1)
            android.content.Intent r1 = r2.zzi(r11)
            com.google.android.gms.internal.ads.zzarw r10 = (com.google.android.gms.internal.ads.zzarw) r10     // Catch:{ ActivityNotFoundException -> 0x00e9 }
            com.google.android.gms.ads.internal.overlay.zzc r2 = new com.google.android.gms.ads.internal.overlay.zzc     // Catch:{ ActivityNotFoundException -> 0x00e9 }
            r2.<init>(r1)     // Catch:{ ActivityNotFoundException -> 0x00e9 }
            r10.zza(r2)     // Catch:{ ActivityNotFoundException -> 0x00e9 }
            goto L_0x0023
        L_0x00e9:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.google.android.gms.internal.ads.zzakb.zzdk(r1)
            goto L_0x0023
        L_0x00f3:
            r9.zzl(r5)
            java.lang.String r1 = "intent_url"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            r2 = 0
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x0166
            r4 = 0
            android.content.Intent r1 = android.content.Intent.parseUri(r1, r4)     // Catch:{ URISyntaxException -> 0x0152 }
            r5 = r1
        L_0x010b:
            if (r5 == 0) goto L_0x0144
            android.net.Uri r1 = r5.getData()
            if (r1 == 0) goto L_0x0144
            android.net.Uri r2 = r5.getData()
            java.lang.String r4 = r2.toString()
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 != 0) goto L_0x019c
            android.content.Context r6 = r10.getContext()     // Catch:{ Exception -> 0x016e }
            r0 = r10
            com.google.android.gms.internal.ads.zzarz r0 = (com.google.android.gms.internal.ads.zzarz) r0     // Catch:{ Exception -> 0x016e }
            r1 = r0
            com.google.android.gms.internal.ads.zzci r7 = r1.zzui()     // Catch:{ Exception -> 0x016e }
            r0 = r10
            com.google.android.gms.internal.ads.zzasb r0 = (com.google.android.gms.internal.ads.zzasb) r0     // Catch:{ Exception -> 0x016e }
            r1 = r0
            android.view.View r1 = r1.getView()     // Catch:{ Exception -> 0x016e }
            android.app.Activity r8 = r10.zzto()     // Catch:{ Exception -> 0x016e }
            java.lang.String r1 = zza(r6, r7, r4, r1, r8)     // Catch:{ Exception -> 0x016e }
        L_0x013d:
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x017f }
        L_0x0141:
            r5.setData(r1)
        L_0x0144:
            if (r5 == 0) goto L_0x01a4
            com.google.android.gms.internal.ads.zzarw r10 = (com.google.android.gms.internal.ads.zzarw) r10
            com.google.android.gms.ads.internal.overlay.zzc r1 = new com.google.android.gms.ads.internal.overlay.zzc
            r1.<init>(r5)
            r10.zza(r1)
            goto L_0x0023
        L_0x0152:
            r4 = move-exception
            java.lang.String r5 = "Error parsing the url: "
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r6 = r1.length()
            if (r6 == 0) goto L_0x0168
            java.lang.String r1 = r5.concat(r1)
        L_0x0163:
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r4)
        L_0x0166:
            r5 = r2
            goto L_0x010b
        L_0x0168:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r5)
            goto L_0x0163
        L_0x016e:
            r1 = move-exception
            java.lang.String r6 = "Error occurred while adding signals."
            com.google.android.gms.internal.ads.zzakb.zzb(r6, r1)
            com.google.android.gms.internal.ads.zzajm r6 = com.google.android.gms.ads.internal.zzbv.zzeo()
            java.lang.String r7 = "OpenGmsgHandler.onGmsg"
            r6.zza(r1, r7)
            r1 = r4
            goto L_0x013d
        L_0x017f:
            r4 = move-exception
            java.lang.String r6 = "Error parsing the uri: "
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r7 = r1.length()
            if (r7 == 0) goto L_0x019e
            java.lang.String r1 = r6.concat(r1)
        L_0x0190:
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r4)
            com.google.android.gms.internal.ads.zzajm r1 = com.google.android.gms.ads.internal.zzbv.zzeo()
            java.lang.String r6 = "OpenGmsgHandler.onGmsg"
            r1.zza(r4, r6)
        L_0x019c:
            r1 = r2
            goto L_0x0141
        L_0x019e:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r6)
            goto L_0x0190
        L_0x01a4:
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 != 0) goto L_0x01c4
            android.content.Context r2 = r10.getContext()
            r1 = r10
            com.google.android.gms.internal.ads.zzarz r1 = (com.google.android.gms.internal.ads.zzarz) r1
            com.google.android.gms.internal.ads.zzci r4 = r1.zzui()
            r1 = r10
            com.google.android.gms.internal.ads.zzasb r1 = (com.google.android.gms.internal.ads.zzasb) r1
            android.view.View r1 = r1.getView()
            android.app.Activity r5 = r10.zzto()
            java.lang.String r3 = zza(r2, r4, r3, r1, r5)
        L_0x01c4:
            com.google.android.gms.internal.ads.zzarw r10 = (com.google.android.gms.internal.ads.zzarw) r10
            com.google.android.gms.ads.internal.overlay.zzc r1 = new com.google.android.gms.ads.internal.overlay.zzc
            java.lang.String r2 = "i"
            java.lang.Object r2 = r11.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r4 = "m"
            java.lang.Object r4 = r11.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "p"
            java.lang.Object r5 = r11.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r6 = "c"
            java.lang.Object r6 = r11.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = "f"
            java.lang.Object r7 = r11.get(r7)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = "e"
            java.lang.Object r8 = r11.get(r8)
            java.lang.String r8 = (java.lang.String) r8
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            r10.zza(r1)
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.gmsg.zzad.zza(java.lang.Object, java.util.Map):void");
    }
}
