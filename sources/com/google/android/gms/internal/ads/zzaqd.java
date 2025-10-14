package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaqd implements zzv<zzapw> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzarl zzarl;
        zzapw zzapw = (zzapw) obj;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbae)).booleanValue()) {
            zzarl zztm = zzapw.zztm();
            if (zztm == null) {
                try {
                    zzarl zzarl2 = new zzarl(zzapw, Float.parseFloat((String) map.get("duration")), "1".equals(map.get("customControlsAllowed")), "1".equals(map.get("clickToExpandAllowed")));
                    zzapw.zza(zzarl2);
                    zzarl = zzarl2;
                } catch (NullPointerException | NumberFormatException e) {
                    zzakb.zzb("Unable to parse videoMeta message.", e);
                    zzbv.zzeo().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            } else {
                zzarl = zztm;
            }
            boolean equals = "1".equals(map.get("muted"));
            float parseFloat = Float.parseFloat((String) map.get("currentTime"));
            int parseInt = Integer.parseInt((String) map.get("playbackState"));
            int i = (parseInt < 0 || 3 < parseInt) ? 0 : parseInt;
            String str = (String) map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzakb.isLoggable(3)) {
                zzakb.zzck(new StringBuilder(String.valueOf(str).length() + 79).append("Video Meta GMSG: isMuted : ").append(equals).append(" , playbackState : ").append(i).append(" , aspectRatio : ").append(str).toString());
            }
            zzarl.zza(parseFloat, i, equals, parseFloat2);
        }
    }
}
