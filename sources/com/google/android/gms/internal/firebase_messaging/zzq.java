package com.google.android.gms.internal.firebase_messaging;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONArray;
import org.json.JSONException;

public final class zzq {
    private final Bundle zzcl;
    final String zzeo;

    public zzq(String str, Bundle bundle) {
        this.zzeo = (String) Preconditions.checkNotNull(str);
        this.zzcl = (Bundle) Preconditions.checkNotNull(bundle);
    }

    public final String[] zzn(String str) {
        Object[] zzo = zzo(str);
        if (zzo == null) {
            return null;
        }
        String[] strArr = new String[zzo.length];
        for (int i = 0; i < zzo.length; i++) {
            strArr[i] = String.valueOf(zzo[i]);
        }
        return strArr;
    }

    public final String getString(String str) {
        return zzac.zza(this.zzcl, str);
    }

    private final Object[] zzo(String str) {
        Bundle bundle = this.zzcl;
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String zza = zzac.zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(zza);
            Object[] objArr = new String[jSONArray.length()];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = jSONArray.opt(i);
            }
            return objArr;
        } catch (JSONException e) {
            String str2 = this.zzeo;
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf("_loc_args");
            String substring = (valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).substring(6);
            Log.w(str2, new StringBuilder(String.valueOf(substring).length() + 41 + String.valueOf(zza).length()).append("Malformed ").append(substring).append(": ").append(zza).append("  Default value will be used.").toString());
            return null;
        }
    }

    public final String zzp(String str) {
        Bundle bundle = this.zzcl;
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        return zzac.zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public final String zzav() {
        return zza("gcm.n.sound2", "gcm.n.sound");
    }

    /* access modifiers changed from: package-private */
    @TargetApi(26)
    public final boolean zza(Resources resources, int i) {
        if (Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(resources.getDrawable(i, (Resources.Theme) null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            Log.e(this.zzeo, new StringBuilder(77).append("Adaptive icons cannot be used in notifications. Ignoring icon id: ").append(i).toString());
            return false;
        } catch (Resources.NotFoundException e) {
            Log.e(this.zzeo, new StringBuilder(66).append("Couldn't find resource ").append(i).append(", treating it as an invalid icon").toString());
            return false;
        }
    }

    @Nullable
    private final String zza(String... strArr) {
        for (String string : strArr) {
            String string2 = getString(string);
            if (!TextUtils.isEmpty(string2)) {
                return string2;
            }
        }
        return null;
    }

    @Nullable
    public final Uri zzaw() {
        String zza = zza("gcm.n.link_android", "gcm.n.link");
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        return Uri.parse(zza);
    }
}
