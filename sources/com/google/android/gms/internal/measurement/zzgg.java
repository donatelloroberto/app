package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.Map;

public final class zzgg extends zzjr implements zzei {
    @VisibleForTesting
    private static int zzalo = SupportMenu.USER_MASK;
    @VisibleForTesting
    private static int zzalp = 2;
    private final Map<String, Map<String, String>> zzalq = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzalr = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzals = new ArrayMap();
    private final Map<String, zzkm> zzalt = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzalu = new ArrayMap();
    private final Map<String, String> zzalv = new ArrayMap();

    zzgg(zzjs zzjs) {
        super(zzjs);
    }

    @WorkerThread
    private final zzkm zza(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzkm();
        }
        zzabx zza = zzabx.zza(bArr, 0, bArr.length);
        zzkm zzkm = new zzkm();
        try {
            zzkm.zzb(zza);
            zzgf().zziz().zze("Parsed config. version, gmp_app_id", zzkm.zzatb, zzkm.zzadm);
            return zzkm;
        } catch (IOException e) {
            zzgf().zziv().zze("Unable to merge remote config. appId", zzfh.zzbl(str), e);
            return new zzkm();
        }
    }

    private static Map<String, String> zza(zzkm zzkm) {
        ArrayMap arrayMap = new ArrayMap();
        if (!(zzkm == null || zzkm.zzatd == null)) {
            for (zzkn zzkn : zzkm.zzatd) {
                if (zzkn != null) {
                    arrayMap.put(zzkn.zzny, zzkn.value);
                }
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzkm zzkm) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (!(zzkm == null || zzkm.zzate == null)) {
            for (zzkl zzkl : zzkm.zzate) {
                if (TextUtils.isEmpty(zzkl.name)) {
                    zzgf().zziv().log("EventConfig contained null event name");
                } else {
                    String zzaj = AppMeasurement.Event.zzaj(zzkl.name);
                    if (!TextUtils.isEmpty(zzaj)) {
                        zzkl.name = zzaj;
                    }
                    arrayMap.put(zzkl.name, zzkl.zzasy);
                    arrayMap2.put(zzkl.name, zzkl.zzasz);
                    if (zzkl.zzata != null) {
                        if (zzkl.zzata.intValue() < zzalp || zzkl.zzata.intValue() > zzalo) {
                            zzgf().zziv().zze("Invalid sampling rate. Event name, sample rate", zzkl.name, zzkl.zzata);
                        } else {
                            arrayMap3.put(zzkl.name, zzkl.zzata);
                        }
                    }
                }
            }
        }
        this.zzalr.put(str, arrayMap);
        this.zzals.put(str, arrayMap2);
        this.zzalu.put(str, arrayMap3);
    }

    @WorkerThread
    private final void zzbs(String str) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        if (this.zzalt.get(str) == null) {
            byte[] zzbd = zzje().zzbd(str);
            if (zzbd == null) {
                this.zzalq.put(str, (Object) null);
                this.zzalr.put(str, (Object) null);
                this.zzals.put(str, (Object) null);
                this.zzalt.put(str, (Object) null);
                this.zzalv.put(str, (Object) null);
                this.zzalu.put(str, (Object) null);
                return;
            }
            zzkm zza = zza(str, zzbd);
            this.zzalq.put(str, zza(zza));
            zza(str, zza);
            this.zzalt.put(str, zza);
            this.zzalv.put(str, (Object) null);
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final boolean zza(String str, byte[] bArr, String str2) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        zzkm zza = zza(str, bArr);
        if (zza == null) {
            return false;
        }
        zza(str, zza);
        this.zzalt.put(str, zza);
        this.zzalv.put(str, str2);
        this.zzalq.put(str, zza(zza));
        zzeb zzjd = zzjd();
        zzkf[] zzkfArr = zza.zzatf;
        Preconditions.checkNotNull(zzkfArr);
        for (zzkf zzkf : zzkfArr) {
            for (zzkg zzkg : zzkf.zzarz) {
                String zzaj = AppMeasurement.Event.zzaj(zzkg.zzasc);
                if (zzaj != null) {
                    zzkg.zzasc = zzaj;
                }
                for (zzkh zzkh : zzkg.zzasd) {
                    String zzaj2 = AppMeasurement.Param.zzaj(zzkh.zzask);
                    if (zzaj2 != null) {
                        zzkh.zzask = zzaj2;
                    }
                }
            }
            for (zzkj zzkj : zzkf.zzary) {
                String zzaj3 = AppMeasurement.UserProperty.zzaj(zzkj.zzasr);
                if (zzaj3 != null) {
                    zzkj.zzasr = zzaj3;
                }
            }
        }
        zzjd.zzje().zza(str, zzkfArr);
        try {
            zza.zzatf = null;
            byte[] bArr2 = new byte[zza.zzvv()];
            zza.zza(zzaby.zzb(bArr2, 0, bArr2.length));
            bArr = bArr2;
        } catch (IOException e) {
            zzgf().zziv().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzfh.zzbl(str), e);
        }
        zzej zzje = zzje();
        Preconditions.checkNotEmpty(str);
        zzje.zzab();
        zzje.zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) zzje.getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzje.zzgf().zzis().zzg("Failed to update remote config (got 0). appId", zzfh.zzbl(str));
            }
        } catch (SQLiteException e2) {
            zzje.zzgf().zzis().zze("Error storing remote config. appId", zzfh.zzbl(str), e2);
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final zzkm zzbt(String str) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        zzbs(str);
        return this.zzalt.get(str);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final String zzbu(String str) {
        zzab();
        return this.zzalv.get(str);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzbv(String str) {
        zzab();
        this.zzalv.put(str, (Object) null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzbw(String str) {
        zzab();
        this.zzalt.remove(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzbx(String str) {
        return "1".equals(zze(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzby(String str) {
        return "1".equals(zze(str, "measurement.upload.blacklist_public"));
    }

    @WorkerThread
    public final String zze(String str, String str2) {
        zzab();
        zzbs(str);
        Map map = this.zzalq.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
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
        return false;
    }

    public final /* bridge */ /* synthetic */ zzjy zzjc() {
        return super.zzjc();
    }

    public final /* bridge */ /* synthetic */ zzeb zzjd() {
        return super.zzjd();
    }

    public final /* bridge */ /* synthetic */ zzej zzje() {
        return super.zzje();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzn(String str, String str2) {
        zzab();
        zzbs(str);
        if (zzbx(str) && zzkc.zzch(str2)) {
            return true;
        }
        if (zzby(str) && zzkc.zzcb(str2)) {
            return true;
        }
        Map map = this.zzalr.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzo(String str, String str2) {
        zzab();
        zzbs(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        Map map = this.zzals.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final int zzp(String str, String str2) {
        zzab();
        zzbs(str);
        Map map = this.zzalu.get(str);
        if (map == null) {
            return 1;
        }
        Integer num = (Integer) map.get(str2);
        if (num == null) {
            return 1;
        }
        return num.intValue();
    }
}
