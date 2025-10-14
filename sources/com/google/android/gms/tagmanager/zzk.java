package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzl;
import java.util.Map;

final class zzk extends zzbq {
    private static final String ID = zza.APP_VERSION.toString();
    private final Context zzrm;

    public zzk(Context context) {
        super(ID, new String[0]);
        this.zzrm = context;
    }

    public final boolean zzgw() {
        return true;
    }

    public final zzl zzb(Map<String, zzl> map) {
        try {
            return zzgj.zzi(Integer.valueOf(this.zzrm.getPackageManager().getPackageInfo(this.zzrm.getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            String packageName = this.zzrm.getPackageName();
            String message = e.getMessage();
            zzdi.zzav(new StringBuilder(String.valueOf(packageName).length() + 25 + String.valueOf(message).length()).append("Package name ").append(packageName).append(" not found. ").append(message).toString());
            return zzgj.zzkc();
        }
    }
}
