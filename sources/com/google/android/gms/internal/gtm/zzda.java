package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.Locale;

public final class zzda extends zzan {
    private String zzaau;
    private String zzaav;
    protected int zzaax;
    private int zzacu;
    protected boolean zzacv;
    private boolean zzacw;
    private boolean zzacx;

    public zzda(zzap zzap) {
        super(zzap);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() {
        ApplicationInfo applicationInfo;
        int i;
        zzcc zzcc;
        boolean z;
        boolean z2;
        boolean z3;
        int i2;
        Context context = getContext();
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            zzd("PackageManager doesn't know about the app package", e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            zzt("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null && (i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource")) > 0 && (zzcc = (zzcc) new zzca(zzcm()).zzq(i)) != null) {
            zzq("Loading global XML config values");
            if (zzcc.zzaau != null) {
                String str = zzcc.zzaau;
                this.zzaau = str;
                zzb("XML config - app name", str);
            }
            if (zzcc.zzaav != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                String str2 = zzcc.zzaav;
                this.zzaav = str2;
                zzb("XML config - app version", str2);
            }
            if (zzcc.zzaaw != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                String lowerCase = zzcc.zzaaw.toLowerCase(Locale.US);
                if ("verbose".equals(lowerCase)) {
                    i2 = 0;
                } else if ("info".equals(lowerCase)) {
                    i2 = 1;
                } else if ("warning".equals(lowerCase)) {
                    i2 = 2;
                } else {
                    i2 = MediaRouteProviderProtocol.SERVICE_DATA_ERROR.equals(lowerCase) ? 3 : -1;
                }
                if (i2 >= 0) {
                    this.zzacu = i2;
                    zza("XML config - log level", Integer.valueOf(i2));
                }
            }
            if (zzcc.zzaax >= 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                int i3 = zzcc.zzaax;
                this.zzaax = i3;
                this.zzacv = true;
                zzb("XML config - dispatch period (sec)", Integer.valueOf(i3));
            }
            if (zzcc.zzaay != -1) {
                boolean z4 = zzcc.zzaay == 1;
                this.zzacx = z4;
                this.zzacw = true;
                zzb("XML config - dry run", Boolean.valueOf(z4));
            }
        }
    }

    public final String zzba() {
        zzdb();
        return this.zzaav;
    }

    public final String zzaz() {
        zzdb();
        return this.zzaau;
    }

    public final boolean zzgh() {
        zzdb();
        return false;
    }

    public final boolean zzgi() {
        zzdb();
        return this.zzacw;
    }

    public final boolean zzgj() {
        zzdb();
        return this.zzacx;
    }
}
