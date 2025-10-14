package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzarx {
    private static final Pattern zzdeo = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*\\s*<!DOCTYPE(\\s)+html(|(\\s)+[^>]*)>", 2);
    private static final Pattern zzdep = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*?\\s*<!DOCTYPE[^>]*>", 2);

    public static String zzb(@NonNull String str, @Nullable String... strArr) {
        int i = 0;
        if (strArr.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Matcher matcher = zzdeo.matcher(str);
        if (matcher.find()) {
            int end = matcher.end();
            sb.append(str.substring(0, end));
            int length = strArr.length;
            while (i < length) {
                String str2 = strArr[i];
                if (str2 != null) {
                    sb.append(str2);
                }
                i++;
            }
            sb.append(str.substring(end));
        } else {
            if (!zzdep.matcher(str).find()) {
                int length2 = strArr.length;
                while (i < length2) {
                    String str3 = strArr[i];
                    if (str3 != null) {
                        sb.append(str3);
                    }
                    i++;
                }
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static String zzvp() {
        String str = (String) zzkb.zzik().zzd(zznk.zzawf);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", str);
            jSONObject.put("sdk", "Google Mobile Ads");
            jSONObject.put("sdkVersion", "12.4.51-000");
            StringBuilder sb = new StringBuilder();
            sb.append("<script>");
            sb.append("Object.defineProperty(window,'MRAID_ENV',{get:function(){return ").append(jSONObject.toString()).append("}});");
            sb.append("</script>");
            return sb.toString();
        } catch (JSONException e) {
            zzakb.zzc("Unable to build MRAID_ENV", e);
            return null;
        }
    }
}
