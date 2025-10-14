package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzapv {
    private final boolean zzczu;
    private final int zzczv;
    private final int zzczw;
    private final int zzczx;
    private final String zzczy;
    private final int zzczz;
    private final int zzdaa;
    private final int zzdab;
    private final boolean zzdac;

    public zzapv(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
            }
        } else {
            jSONObject = null;
        }
        jSONObject2 = jSONObject;
        this.zzczu = zza(jSONObject2, "aggressive_media_codec_release", zznk.zzavl);
        this.zzczv = zzb(jSONObject2, "byte_buffer_precache_limit", zznk.zzauv);
        this.zzczw = zzb(jSONObject2, "exo_cache_buffer_size", zznk.zzauz);
        this.zzczx = zzb(jSONObject2, "exo_connect_timeout_millis", zznk.zzaur);
        this.zzczy = zzc(jSONObject2, "exo_player_version", zznk.zzauq);
        this.zzczz = zzb(jSONObject2, "exo_read_timeout_millis", zznk.zzaus);
        this.zzdaa = zzb(jSONObject2, "load_check_interval_bytes", zznk.zzaut);
        this.zzdab = zzb(jSONObject2, "player_precache_limit", zznk.zzauu);
        this.zzdac = zza(jSONObject2, "use_cache_data_source", zznk.zzbdr);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.ads.zzna<java.lang.Boolean>, com.google.android.gms.internal.ads.zzna] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(org.json.JSONObject r1, java.lang.String r2, com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r3) {
        /*
            if (r1 == 0) goto L_0x0008
            boolean r0 = r1.getBoolean(r2)     // Catch:{ JSONException -> 0x0007 }
        L_0x0006:
            return r0
        L_0x0007:
            r0 = move-exception
        L_0x0008:
            com.google.android.gms.internal.ads.zzni r0 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r0.zzd(r3)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapv.zza(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzna):boolean");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.ads.zzna<java.lang.Integer>, com.google.android.gms.internal.ads.zzna] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzb(org.json.JSONObject r1, java.lang.String r2, com.google.android.gms.internal.ads.zzna<java.lang.Integer> r3) {
        /*
            if (r1 == 0) goto L_0x0008
            int r0 = r1.getInt(r2)     // Catch:{ JSONException -> 0x0007 }
        L_0x0006:
            return r0
        L_0x0007:
            r0 = move-exception
        L_0x0008:
            com.google.android.gms.internal.ads.zzni r0 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r0.zzd(r3)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapv.zzb(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzna):int");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.ads.zzna<java.lang.String>, com.google.android.gms.internal.ads.zzna] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzc(org.json.JSONObject r1, java.lang.String r2, com.google.android.gms.internal.ads.zzna<java.lang.String> r3) {
        /*
            if (r1 == 0) goto L_0x0008
            java.lang.String r0 = r1.getString(r2)     // Catch:{ JSONException -> 0x0007 }
        L_0x0006:
            return r0
        L_0x0007:
            r0 = move-exception
        L_0x0008:
            com.google.android.gms.internal.ads.zzni r0 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r0.zzd(r3)
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapv.zzc(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzna):java.lang.String");
    }
}
