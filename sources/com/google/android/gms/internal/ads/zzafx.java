package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.text.TextUtils;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafx {
    private int mOrientation = -1;
    private String zzbhy;
    private boolean zzbtn = false;
    private final zzaef zzbuc;
    private List<String> zzcab;
    private String zzcae;
    private String zzchw;
    private String zzchx;
    private List<String> zzchy;
    private String zzchz;
    private String zzcia;
    private String zzcib;
    private List<String> zzcic;
    private List<String> zzcid;
    private long zzcie = -1;
    private boolean zzcif = false;
    private final long zzcig = -1;
    private long zzcih = -1;
    private boolean zzcii = false;
    private boolean zzcij = false;
    private boolean zzcik = false;
    private boolean zzcil = true;
    private boolean zzcim = true;
    private String zzcin = "";
    private boolean zzcio = false;
    private zzaig zzcip;
    private List<String> zzciq;
    private List<String> zzcir;
    private boolean zzcis = false;
    private boolean zzcit = false;
    private String zzciu;
    private List<String> zzciv;
    private boolean zzciw;
    private String zzcix;
    private zzaiq zzciy;
    private boolean zzciz;
    private boolean zzcja;
    private boolean zzcjb;
    private boolean zzcjc;
    private zzael zzxe;

    public zzafx(zzaef zzaef, String str) {
        this.zzchx = str;
        this.zzbuc = zzaef;
    }

    private static String zza(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static long zzb(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list != null && !list.isEmpty()) {
            String str2 = (String) list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException e) {
                zzakb.zzdk(new StringBuilder(String.valueOf(str).length() + 36 + String.valueOf(str2).length()).append("Could not parse float from ").append(str).append(" header: ").append(str2).toString());
            }
        }
        return -1;
    }

    private static List<String> zzc(Map<String, List<String>> map, String str) {
        String str2;
        List list = map.get(str);
        if (list == null || list.isEmpty() || (str2 = (String) list.get(0)) == null) {
            return null;
        }
        return Arrays.asList(str2.trim().split("\\s+"));
    }

    private static boolean zzd(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return Boolean.valueOf((String) list.get(0)).booleanValue();
    }

    public final zzaej zza(long j, boolean z) {
        return new zzaej(this.zzbuc, this.zzchx, this.zzbhy, this.zzchy, this.zzcic, this.zzcie, this.zzcif, -1, this.zzcab, this.zzcih, this.mOrientation, this.zzchw, j, this.zzcia, this.zzcib, this.zzcii, this.zzcij, this.zzcik, this.zzcil, false, this.zzcin, this.zzcio, this.zzbtn, this.zzcip, this.zzciq, this.zzcir, this.zzcis, this.zzxe, this.zzcit, this.zzciu, this.zzciv, this.zzciw, this.zzcix, this.zzciy, this.zzchz, this.zzcim, this.zzciz, this.zzcja, z ? 2 : 1, this.zzcjb, this.zzcid, this.zzcjc, this.zzcae);
    }

    public final void zza(String str, Map<String, List<String>> map, String str2) {
        this.zzbhy = str2;
        zzl(map);
    }

    public final void zzl(Map<String, List<String>> map) {
        this.zzchw = zza(map, "X-Afma-Ad-Size");
        this.zzcix = zza(map, "X-Afma-Ad-Slot-Size");
        List<String> zzc = zzc(map, "X-Afma-Click-Tracking-Urls");
        if (zzc != null) {
            this.zzchy = zzc;
        }
        this.zzchz = zza(map, "X-Afma-Debug-Signals");
        List list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.zzcia = (String) list.get(0);
        }
        List<String> zzc2 = zzc(map, "X-Afma-Tracking-Urls");
        if (zzc2 != null) {
            this.zzcic = zzc2;
        }
        List<String> zzc3 = zzc(map, "X-Afma-Downloaded-Impression-Urls");
        if (zzc3 != null) {
            this.zzcid = zzc3;
        }
        long zzb = zzb(map, "X-Afma-Interstitial-Timeout");
        if (zzb != -1) {
            this.zzcie = zzb;
        }
        this.zzcif |= zzd(map, "X-Afma-Mediation");
        List<String> zzc4 = zzc(map, "X-Afma-Manual-Tracking-Urls");
        if (zzc4 != null) {
            this.zzcab = zzc4;
        }
        long zzb2 = zzb(map, "X-Afma-Refresh-Rate");
        if (zzb2 != -1) {
            this.zzcih = zzb2;
        }
        List list2 = map.get("X-Afma-Orientation");
        if (list2 != null && !list2.isEmpty()) {
            String str = (String) list2.get(0);
            if (DeviceInfo.ORIENTATION_PORTRAIT.equalsIgnoreCase(str)) {
                this.mOrientation = zzbv.zzem().zzrm();
            } else if (DeviceInfo.ORIENTATION_LANDSCAPE.equalsIgnoreCase(str)) {
                this.mOrientation = zzbv.zzem().zzrl();
            }
        }
        this.zzcib = zza(map, "X-Afma-ActiveView");
        List list3 = map.get("X-Afma-Use-HTTPS");
        if (list3 != null && !list3.isEmpty()) {
            this.zzcik = Boolean.valueOf((String) list3.get(0)).booleanValue();
        }
        this.zzcii |= zzd(map, "X-Afma-Custom-Rendering-Allowed");
        this.zzcij = "native".equals(zza(map, "X-Afma-Ad-Format"));
        List list4 = map.get("X-Afma-Content-Url-Opted-Out");
        if (list4 != null && !list4.isEmpty()) {
            this.zzcil = Boolean.valueOf((String) list4.get(0)).booleanValue();
        }
        List list5 = map.get("X-Afma-Content-Vertical-Opted-Out");
        if (list5 != null && !list5.isEmpty()) {
            this.zzcim = Boolean.valueOf((String) list5.get(0)).booleanValue();
        }
        List list6 = map.get("X-Afma-Gws-Query-Id");
        if (list6 != null && !list6.isEmpty()) {
            this.zzcin = (String) list6.get(0);
        }
        String zza = zza(map, "X-Afma-Fluid");
        if (zza != null && zza.equals("height")) {
            this.zzcio = true;
        }
        this.zzbtn = "native_express".equals(zza(map, "X-Afma-Ad-Format"));
        this.zzcip = zzaig.zzce(zza(map, "X-Afma-Rewards"));
        if (this.zzciq == null) {
            this.zzciq = zzc(map, "X-Afma-Reward-Video-Start-Urls");
        }
        if (this.zzcir == null) {
            this.zzcir = zzc(map, "X-Afma-Reward-Video-Complete-Urls");
        }
        this.zzcis |= zzd(map, "X-Afma-Use-Displayed-Impression");
        this.zzcit |= zzd(map, "X-Afma-Auto-Collect-Location");
        this.zzciu = zza(map, "Set-Cookie");
        String zza2 = zza(map, "X-Afma-Auto-Protection-Configuration");
        if (zza2 == null || TextUtils.isEmpty(zza2)) {
            Uri.Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter(PushConstants.CHANNEL_ID, "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzcia)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzcia);
            }
            boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzaum)).booleanValue();
            String builder = buildUpon.toString();
            this.zzxe = new zzael(booleanValue, Arrays.asList(new String[]{new StringBuilder(String.valueOf(builder).length() + 31).append(builder).append("&navigationURL={NAVIGATION_URL}").toString()}));
        } else {
            try {
                this.zzxe = zzael.zzl(new JSONObject(zza2));
            } catch (JSONException e) {
                zzakb.zzc("Error parsing configuration JSON", e);
                this.zzxe = new zzael();
            }
        }
        List<String> zzc5 = zzc(map, "X-Afma-Remote-Ping-Urls");
        if (zzc5 != null) {
            this.zzciv = zzc5;
        }
        String zza3 = zza(map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(zza3)) {
            try {
                this.zzciy = zzaiq.zzo(new JSONObject(zza3));
            } catch (JSONException e2) {
                zzakb.zzc("Error parsing safe browsing header", e2);
            }
        }
        this.zzciw |= zzd(map, "X-Afma-Render-In-Browser");
        String zza4 = zza(map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(zza4)) {
            try {
                this.zzciz = new JSONObject(zza4).getBoolean("never_pool");
            } catch (JSONException e3) {
                zzakb.zzc("Error parsing interstitial pool header", e3);
            }
        }
        this.zzcja = zzd(map, "X-Afma-Custom-Close-Blocked");
        this.zzcjb = zzd(map, "X-Afma-Enable-Omid");
        this.zzcjc = zzd(map, "X-Afma-Disable-Closable-Area");
        this.zzcae = zza(map, "X-Afma-Omid-Settings");
    }
}
