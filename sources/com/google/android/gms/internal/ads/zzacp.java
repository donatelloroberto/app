package com.google.android.gms.internal.ads;

import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzacp implements zzacd<zzos> {
    private final boolean zzcbk;

    public zzacp(boolean z) {
        this.zzcbk = z;
    }

    public final /* synthetic */ zzpb zza(zzabv zzabv, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        View view = null;
        int i = 0;
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        zzanz<zzoj> zzg = zzabv.zzg(jSONObject);
        zzanz<zzaqw> zzc = zzabv.zzc(jSONObject, "video");
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            String string = jSONObject2.getString(AppMeasurement.Param.TYPE);
            if ("string".equals(string)) {
                simpleArrayMap2.put(jSONObject2.getString("name"), jSONObject2.getString("string_value"));
            } else if (PushConstants.IMAGE.equals(string)) {
                simpleArrayMap.put(jSONObject2.getString("name"), zzabv.zza(jSONObject2, "image_value", this.zzcbk));
            } else {
                String valueOf = String.valueOf(string);
                zzakb.zzdk(valueOf.length() != 0 ? "Unknown custom asset type: ".concat(valueOf) : new String("Unknown custom asset type: "));
            }
        }
        zzaqw zzc2 = zzabv.zzc(zzc);
        String string2 = jSONObject.getString("custom_template_id");
        SimpleArrayMap simpleArrayMap3 = new SimpleArrayMap();
        while (true) {
            int i3 = i;
            if (i3 >= simpleArrayMap.size()) {
                break;
            }
            simpleArrayMap3.put(simpleArrayMap.keyAt(i3), ((Future) simpleArrayMap.valueAt(i3)).get());
            i = i3 + 1;
        }
        zzoj zzoj = (zzoj) zzg.get();
        zzarl zztm = zzc2 != null ? zzc2.zztm() : null;
        if (zzc2 != null) {
            view = zzc2.getView();
        }
        return new zzos(string2, simpleArrayMap3, simpleArrayMap2, zzoj, zztm, view);
    }
}
