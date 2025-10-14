package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.gtm.zza;
import java.util.Map;

final class zzl extends zzbq {
    private static final String ID = zza.APP_VERSION_NAME.toString();
    private final Context zzrm;

    public zzl(Context context) {
        super(ID, new String[0]);
        this.zzrm = context;
    }

    public final boolean zzgw() {
        return true;
    }

    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        try {
            return zzgj.zzi(this.zzrm.getPackageManager().getPackageInfo(this.zzrm.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            String packageName = this.zzrm.getPackageName();
            String message = e.getMessage();
            zzdi.zzav(new StringBuilder(String.valueOf(packageName).length() + 25 + String.valueOf(message).length()).append("Package name ").append(packageName).append(" not found. ").append(message).toString());
            return zzgj.zzkc();
        }
    }
}
