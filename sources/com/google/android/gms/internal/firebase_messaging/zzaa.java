package com.google.android.gms.internal.firebase_messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Arrays;
import java.util.Iterator;
import java.util.MissingFormatArgumentException;

public final class zzaa extends zzz {
    private final zzs zzfb;
    private final zzq zzfc;

    public zzaa(zzs zzs, zzq zzq) {
        this.zzfb = zzs;
        this.zzfc = zzq;
    }

    @Nullable
    public final CharSequence zzbf() {
        String zzs = zzs("gcm.n.body");
        if (!TextUtils.isEmpty(zzs)) {
            return zzs;
        }
        String zzt = zzt("gcm.n.body");
        if (TextUtils.isEmpty(zzt)) {
            return null;
        }
        return zzt;
    }

    @Nullable
    public final CharSequence getTitle() {
        String zzs = zzs("gcm.n.title");
        if (!TextUtils.isEmpty(zzs)) {
            return zzs;
        }
        String zzt = zzt("gcm.n.title");
        if (!TextUtils.isEmpty(zzt)) {
            return zzt;
        }
        CharSequence appLabel = this.zzfb.getAppLabel();
        return TextUtils.isEmpty(appLabel) ? "" : appLabel;
    }

    @Nullable
    public final String getTag() {
        String zzs = zzs("gcm.n.tag");
        if (!TextUtils.isEmpty(zzs)) {
            return zzs;
        }
        String zzbd = this.zzfb.zzbd();
        return new StringBuilder(String.valueOf(zzbd).length() + 21).append(zzbd).append(":").append(SystemClock.uptimeMillis()).toString();
    }

    @Nullable
    public final String getChannelId() {
        if (!PlatformVersion.isAtLeastO() || this.zzfb.zzbc() < 26) {
            return null;
        }
        String zzs = zzs("gcm.n.android_channel_id");
        if (this.zzfb.zzl(zzs)) {
            return zzs;
        }
        if (!TextUtils.isEmpty("com.google.firebase.messaging.default_notification_channel_id")) {
            String string = this.zzfb.zzaz().getString("com.google.firebase.messaging.default_notification_channel_id");
            if (this.zzfb.zzl(string)) {
                return string;
            }
        }
        String zzat = this.zzfb.zzat();
        if (this.zzfb.zzl(zzat)) {
            return zzat;
        }
        return null;
    }

    @Nullable
    public final Integer zzbg() {
        int i;
        Integer zzb;
        Integer zzb2;
        String zzs = zzs("gcm.n.color");
        if (!TextUtils.isEmpty(zzs) && (zzb2 = zzb(zzs)) != null) {
            return zzb2;
        }
        if (TextUtils.isEmpty("com.google.firebase.messaging.default_notification_color") || (i = this.zzfb.zzaz().getInt("com.google.firebase.messaging.default_notification_color", 0)) == 0 || (zzb = this.zzfb.zzb(i)) == null) {
            return null;
        }
        return zzb;
    }

    private static Integer zzb(CharSequence charSequence) {
        try {
            return Integer.valueOf(Color.parseColor(String.valueOf(charSequence)));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Nullable
    public final PendingIntent zzbh() {
        Intent zzba;
        String zzs = zzs("gcm.n.click_action");
        if (!TextUtils.isEmpty(zzs)) {
            zzba = new Intent(zzs).setPackage(this.zzfb.getPackageName()).setFlags(268435456);
        } else {
            String zzs2 = zzs("gcm.n.link_android");
            if (!TextUtils.isEmpty(zzs2)) {
                zzba = zzr(zzs2);
            } else {
                String zzs3 = zzs("gcm.n.link");
                if (!TextUtils.isEmpty(zzs3)) {
                    zzba = zzr(zzs3);
                } else {
                    zzba = this.zzfb.zzba();
                }
            }
        }
        if (zzba == null) {
            return null;
        }
        zzba.addFlags(67108864);
        Bundle bundle = new Bundle(this.zzfb.getData());
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && (str.startsWith("google.c.") || str.startsWith(PushConstants.GCM_N) || str.startsWith("gcm.notification."))) {
                it.remove();
            }
        }
        zzba.putExtras(bundle);
        return this.zzfb.zze(zzba);
    }

    @Nullable
    public final PendingIntent zzbi() {
        return this.zzfb.zzau();
    }

    private final Intent zzr(String str) {
        return new Intent("android.intent.action.VIEW").setPackage(this.zzfb.getPackageName()).setData(Uri.parse(str));
    }

    @Nullable
    public final Integer zzbj() {
        String zzs = zzs("gcm.n.icon");
        if (!TextUtils.isEmpty(zzs)) {
            String[] strArr = {PushConstants.DRAWABLE, "mipmap"};
            for (int i = 0; i < 2; i++) {
                int identifier = this.zzfb.zzay().getIdentifier(zzs, strArr[i], this.zzfb.getPackageName());
                if (zze(identifier)) {
                    return Integer.valueOf(identifier);
                }
            }
            Log.w(this.zzfc.zzeo, new StringBuilder(String.valueOf(zzs).length() + 61).append("Icon resource ").append(zzs).append(" not found. Notification will use default icon.").toString());
        }
        if (!TextUtils.isEmpty("com.google.firebase.messaging.default_notification_icon")) {
            int i2 = this.zzfb.zzaz().getInt("com.google.firebase.messaging.default_notification_icon", 0);
            if (zze(i2)) {
                return Integer.valueOf(i2);
            }
        }
        int zzbb = this.zzfb.zzbb();
        if (zze(zzbb)) {
            return Integer.valueOf(zzbb);
        }
        return 17301651;
    }

    private final boolean zze(int i) {
        return i != 0 && this.zzfc.zza(this.zzfb.zzay(), i);
    }

    @Nullable
    public final Uri getSound() {
        Uri defaultUri;
        String zzs = zzs("gcm.n.sound2");
        if (TextUtils.isEmpty(zzs)) {
            zzs = zzs("gcm.n.sound");
        }
        if (TextUtils.isEmpty(zzs)) {
            return null;
        }
        if (TextUtils.isEmpty(zzs)) {
            defaultUri = null;
        } else {
            if (!PushConstants.SOUND_DEFAULT.equals(zzs)) {
                if (this.zzfb.zzay().getIdentifier(zzs, "raw", this.zzfb.getPackageName()) != 0) {
                    String packageName = this.zzfb.getPackageName();
                    defaultUri = Uri.parse(new StringBuilder(String.valueOf(packageName).length() + 24 + String.valueOf(zzs).length()).append("android.resource://").append(packageName).append("/raw/").append(zzs).toString());
                }
            }
            defaultUri = RingtoneManager.getDefaultUri(2);
        }
        return defaultUri == null ? RingtoneManager.getDefaultUri(2) : defaultUri;
    }

    private final String zzs(String str) {
        String string = this.zzfb.getData().getString(str);
        if (string != null) {
            return string;
        }
        return this.zzfb.getData().getString(str.replace(PushConstants.GCM_N, "gcm.notification."));
    }

    private final String zzt(String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        String zzs = zzs(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(zzs)) {
            return null;
        }
        int identifier = this.zzfb.zzay().getIdentifier(zzs, "string", this.zzfb.getPackageName());
        if (identifier == 0) {
            String str2 = this.zzfc.zzeo;
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf("_loc_key");
            String substring = (valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).substring(6);
            Log.w(str2, new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(str).length()).append(substring).append(" resource not found: ").append(str).append(" Default value will be used.").toString());
            return null;
        }
        String[] zzn = this.zzfc.zzn(str);
        if (zzn == null) {
            return this.zzfb.zzay().getString(identifier);
        }
        try {
            return this.zzfb.zzay().getString(identifier, zzn);
        } catch (MissingFormatArgumentException e) {
            String str3 = this.zzfc.zzeo;
            String arrays = Arrays.toString(zzn);
            Log.w(str3, new StringBuilder(String.valueOf(str).length() + 58 + String.valueOf(arrays).length()).append("Missing format argument for ").append(str).append(": ").append(arrays).append(" Default value will be used.").toString(), e);
            return null;
        }
    }
}
