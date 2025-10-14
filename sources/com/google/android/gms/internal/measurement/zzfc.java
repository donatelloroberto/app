package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

public final class zzfc extends zzhi {
    private String zzadm;
    private String zzadt;
    private long zzadx;
    private int zzaen;
    private int zzain;
    private long zzaio;
    private String zztg;
    private String zzth;
    private String zzti;

    zzfc(zzgm zzgm) {
        super(zzgm);
    }

    @WorkerThread
    private final String zzgl() {
        zzab();
        zzfs();
        if (zzgh().zzax(this.zzti) && !this.zzacw.isEnabled()) {
            return null;
        }
        try {
            return FirebaseInstanceId.getInstance().getId();
        } catch (IllegalStateException e) {
            zzgf().zziv().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    public final String getGmpAppId() {
        zzch();
        return this.zzadm;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: package-private */
    public final String zzah() {
        zzch();
        return this.zzti;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzdz zzbh(String str) {
        zzab();
        zzfs();
        String zzah = zzah();
        String gmpAppId = getGmpAppId();
        zzch();
        String str2 = this.zzth;
        long zzip = (long) zzip();
        zzch();
        String str3 = this.zzadt;
        zzch();
        zzab();
        if (this.zzaio == 0) {
            this.zzaio = this.zzacw.zzgc().zzd(getContext(), getContext().getPackageName());
        }
        long j = this.zzaio;
        boolean isEnabled = this.zzacw.isEnabled();
        boolean z = !zzgg().zzakw;
        String zzgl = zzgl();
        zzch();
        long j2 = this.zzadx;
        long zzkb = this.zzacw.zzkb();
        int zziq = zziq();
        zzeg zzgh = zzgh();
        zzgh.zzfs();
        Boolean zzar = zzgh.zzar("google_analytics_adid_collection_enabled");
        boolean booleanValue = Boolean.valueOf(zzar == null || zzar.booleanValue()).booleanValue();
        zzeg zzgh2 = zzgh();
        zzgh2.zzfs();
        Boolean zzar2 = zzgh2.zzar("google_analytics_ssaid_collection_enabled");
        return new zzdz(zzah, gmpAppId, str2, zzip, str3, 12451, j, str, isEnabled, z, zzgl, j2, zzkb, zziq, booleanValue, Boolean.valueOf(zzar2 == null || zzar2.booleanValue()).booleanValue(), zzgg().zzjl());
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final /* bridge */ /* synthetic */ void zzfr() {
        super.zzfr();
    }

    public final /* bridge */ /* synthetic */ void zzfs() {
        super.zzfs();
    }

    public final /* bridge */ /* synthetic */ void zzft() {
        super.zzft();
    }

    public final /* bridge */ /* synthetic */ zzdu zzfu() {
        return super.zzfu();
    }

    public final /* bridge */ /* synthetic */ zzhl zzfv() {
        return super.zzfv();
    }

    public final /* bridge */ /* synthetic */ zzfc zzfw() {
        return super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzeq zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzij zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzig zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzfd zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzff zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzkc zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzji zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzgh zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfh zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzfs zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzeg zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzec zzgi() {
        return super.zzgi();
    }

    /* access modifiers changed from: protected */
    public final boolean zzhh() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void zzin() {
        boolean z;
        int i = 1;
        String str = "unknown";
        String str2 = "Unknown";
        int i2 = Integer.MIN_VALUE;
        String str3 = "Unknown";
        String packageName = getContext().getPackageName();
        PackageManager packageManager = getContext().getPackageManager();
        if (packageManager == null) {
            zzgf().zzis().zzg("PackageManager is null, app identity information might be inaccurate. appId", zzfh.zzbl(packageName));
        } else {
            try {
                str = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                zzgf().zzis().zzg("Error retrieving app installer package name. appId", zzfh.zzbl(packageName));
            }
            if (str == null) {
                str = "manual_install";
            } else if ("com.android.vending".equals(str)) {
                str = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    if (!TextUtils.isEmpty(applicationLabel)) {
                        str3 = applicationLabel.toString();
                    }
                    str2 = packageInfo.versionName;
                    i2 = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                zzgf().zzis().zze("Error retrieving package info. appId, appName", zzfh.zzbl(packageName), str3);
            }
        }
        this.zzti = packageName;
        this.zzadt = str;
        this.zzth = str2;
        this.zzain = i2;
        this.zztg = str3;
        this.zzaio = 0;
        zzgi();
        Status initialize = GoogleServices.initialize(getContext());
        boolean z2 = initialize != null && initialize.isSuccess();
        if (!z2) {
            if (initialize == null) {
                zzgf().zzis().log("GoogleService failed to initialize (no status)");
            } else {
                zzgf().zzis().zze("GoogleService failed to initialize, status", Integer.valueOf(initialize.getStatusCode()), initialize.getStatusMessage());
            }
        }
        if (z2) {
            Boolean zzhk = zzgh().zzhk();
            if (zzgh().zzhj()) {
                zzgf().zzix().log("Collection disabled with firebase_analytics_collection_deactivated=1");
                z = false;
            } else if (zzhk != null && !zzhk.booleanValue()) {
                zzgf().zzix().log("Collection disabled with firebase_analytics_collection_enabled=0");
                z = false;
            } else if (zzhk != null || !GoogleServices.isMeasurementExplicitlyDisabled()) {
                zzgf().zziz().log("Collection enabled");
                z = true;
            } else {
                zzgf().zzix().log("Collection disabled with google_app_measurement_enable=0");
                z = false;
            }
        } else {
            z = false;
        }
        this.zzadm = "";
        this.zzadx = 0;
        zzgi();
        if (this.zzacw.zzka() != null) {
            this.zzadm = this.zzacw.zzka();
        } else {
            try {
                String googleAppId = GoogleServices.getGoogleAppId();
                if (TextUtils.isEmpty(googleAppId)) {
                    googleAppId = "";
                }
                this.zzadm = googleAppId;
                if (z) {
                    zzgf().zziz().zze("App package, google app id", this.zzti, this.zzadm);
                }
            } catch (IllegalStateException e3) {
                zzgf().zzis().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzfh.zzbl(packageName), e3);
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (!InstantApps.isInstantApp(getContext())) {
                i = 0;
            }
            this.zzaen = i;
            return;
        }
        this.zzaen = 0;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzio() {
        byte[] bArr = new byte[16];
        zzgc().zzll().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    public final int zzip() {
        zzch();
        return this.zzain;
    }

    /* access modifiers changed from: package-private */
    public final int zziq() {
        zzch();
        return this.zzaen;
    }
}
