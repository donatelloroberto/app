package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzjs implements zzed {
    private static volatile zzjs zzaqj;
    private final zzgm zzacw;
    private zzgg zzaqk;
    private zzfl zzaql;
    private zzej zzaqm;
    private zzfq zzaqn;
    private zzjo zzaqo;
    private zzeb zzaqp;
    private final zzjy zzaqq;
    private boolean zzaqr;
    @VisibleForTesting
    private long zzaqs;
    private List<Runnable> zzaqt;
    private int zzaqu;
    private int zzaqv;
    private boolean zzaqw;
    private boolean zzaqx;
    private boolean zzaqy;
    private FileLock zzaqz;
    private FileChannel zzara;
    private List<Long> zzarb;
    private List<Long> zzarc;
    private long zzard;
    private boolean zzvo;

    class zza implements zzel {
        zzks zzarh;
        List<Long> zzari;
        List<zzkp> zzarj;
        private long zzark;

        private zza() {
        }

        /* synthetic */ zza(zzjs zzjs, zzjt zzjt) {
            this();
        }

        private static long zza(zzkp zzkp) {
            return ((zzkp.zzatn.longValue() / 1000) / 60) / 60;
        }

        public final boolean zza(long j, zzkp zzkp) {
            Preconditions.checkNotNull(zzkp);
            if (this.zzarj == null) {
                this.zzarj = new ArrayList();
            }
            if (this.zzari == null) {
                this.zzari = new ArrayList();
            }
            if (this.zzarj.size() > 0 && zza(this.zzarj.get(0)) != zza(zzkp)) {
                return false;
            }
            long zzvv = this.zzark + ((long) zzkp.zzvv());
            if (zzvv >= ((long) Math.max(0, zzey.zzagx.get().intValue()))) {
                return false;
            }
            this.zzark = zzvv;
            this.zzarj.add(zzkp);
            this.zzari.add(Long.valueOf(j));
            return this.zzarj.size() < Math.max(1, zzey.zzagy.get().intValue());
        }

        public final void zzb(zzks zzks) {
            Preconditions.checkNotNull(zzks);
            this.zzarh = zzks;
        }
    }

    private zzjs(zzjx zzjx) {
        this(zzjx, (zzgm) null);
    }

    private zzjs(zzjx zzjx, zzgm zzgm) {
        this.zzvo = false;
        Preconditions.checkNotNull(zzjx);
        this.zzacw = zzgm.zza(zzjx.zzqx, (String) null, (String) null);
        this.zzard = -1;
        zzjy zzjy = new zzjy(this);
        zzjy.zzm();
        this.zzaqq = zzjy;
        zzfl zzfl = new zzfl(this);
        zzfl.zzm();
        this.zzaql = zzfl;
        zzgg zzgg = new zzgg(this);
        zzgg.zzm();
        this.zzaqk = zzgg;
        this.zzacw.zzge().zzc((Runnable) new zzjt(this, zzjx));
    }

    @WorkerThread
    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzab();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzacw.zzgf().zzis().log("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read == 4) {
                allocate.flip();
                return allocate.getInt();
            } else if (read == -1) {
                return 0;
            } else {
                this.zzacw.zzgf().zziv().zzg("Unexpected data length. Bytes read", Integer.valueOf(read));
                return 0;
            }
        } catch (IOException e) {
            this.zzacw.zzgf().zzis().zzg("Failed to read from channel", e);
            return 0;
        }
    }

    private final zzdz zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j) {
        String str3;
        String str4 = "Unknown";
        String str5 = "Unknown";
        int i = Integer.MIN_VALUE;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            this.zzacw.zzgf().zzis().log("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str4 = packageManager.getInstallerPackageName(str);
        } catch (IllegalArgumentException e) {
            this.zzacw.zzgf().zzis().zzg("Error retrieving installer package name. appId", zzfh.zzbl(str));
        }
        if (str4 == null) {
            str4 = "manual_install";
        } else if ("com.android.vending".equals(str4)) {
            str4 = "";
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str);
                str3 = !TextUtils.isEmpty(applicationLabel) ? applicationLabel.toString() : "Unknown";
                try {
                    str5 = packageInfo.versionName;
                    i = packageInfo.versionCode;
                } catch (PackageManager.NameNotFoundException e2) {
                    this.zzacw.zzgf().zzis().zze("Error retrieving newly installed package info. appId, appName", zzfh.zzbl(str), str3);
                    return null;
                }
            }
            this.zzacw.zzgi();
            long j2 = 0;
            if (this.zzacw.zzgh().zzaz(str)) {
                j2 = j;
            }
            return new zzdz(str, str2, str5, (long) i, str4, 12451, this.zzacw.zzgc().zzd(context, str), (String) null, z, false, "", 0, j2, 0, z2, z3, false);
        } catch (PackageManager.NameNotFoundException e3) {
            str3 = "Unknown";
            this.zzacw.zzgf().zzis().zze("Error retrieving newly installed package info. appId, appName", zzfh.zzbl(str), str3);
            return null;
        }
    }

    private static void zza(zzjr zzjr) {
        if (zzjr == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzjr.isInitialized()) {
            String valueOf = String.valueOf(zzjr.getClass());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Component not initialized: ").append(valueOf).toString());
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzjx zzjx) {
        this.zzacw.zzge().zzab();
        zzej zzej = new zzej(this);
        zzej.zzm();
        this.zzaqm = zzej;
        this.zzacw.zzgh().zza(this.zzaqk);
        zzeb zzeb = new zzeb(this);
        zzeb.zzm();
        this.zzaqp = zzeb;
        zzjo zzjo = new zzjo(this);
        zzjo.zzm();
        this.zzaqo = zzjo;
        this.zzaqn = new zzfq(this);
        if (this.zzaqu != this.zzaqv) {
            this.zzacw.zzgf().zzis().zze("Not all upload components initialized", Integer.valueOf(this.zzaqu), Integer.valueOf(this.zzaqv));
        }
        this.zzvo = true;
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzab();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzacw.zzgf().zzis().log("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            this.zzacw.zzgf().zzis().zzg("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            this.zzacw.zzgf().zzis().zzg("Failed to write to channel", e);
            return false;
        }
    }

    private final boolean zza(String str, zzew zzew) {
        long longValue;
        zzkb zzkb;
        String string = zzew.zzafr.getString(FirebaseAnalytics.Param.CURRENCY);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzew.name)) {
            double doubleValue = zzew.zzafr.zzbg(FirebaseAnalytics.Param.VALUE).doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                doubleValue = ((double) zzew.zzafr.getLong(FirebaseAnalytics.Param.VALUE).longValue()) * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                this.zzacw.zzgf().zziv().zze("Data lost. Currency value is too big. appId", zzfh.zzbl(str), Double.valueOf(doubleValue));
                return false;
            }
            longValue = Math.round(doubleValue);
        } else {
            longValue = zzew.zzafr.getLong(FirebaseAnalytics.Param.VALUE).longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String upperCase = string.toUpperCase(Locale.US);
            if (upperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                String valueOf2 = String.valueOf(upperCase);
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                zzkb zzh = zzje().zzh(str, concat);
                if (zzh == null || !(zzh.value instanceof Long)) {
                    zzej zzje = zzje();
                    int zzb = this.zzacw.zzgh().zzb(str, zzey.zzaht) - 1;
                    Preconditions.checkNotEmpty(str);
                    zzje.zzab();
                    zzje.zzch();
                    try {
                        zzje.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                    } catch (SQLiteException e) {
                        zzje.zzgf().zzis().zze("Error pruning currencies. appId", zzfh.zzbl(str), e);
                    }
                    zzkb = new zzkb(str, zzew.origin, concat, this.zzacw.zzbt().currentTimeMillis(), Long.valueOf(longValue));
                } else {
                    zzkb = new zzkb(str, zzew.origin, concat, this.zzacw.zzbt().currentTimeMillis(), Long.valueOf(longValue + ((Long) zzh.value).longValue()));
                }
                if (!zzje().zza(zzkb)) {
                    this.zzacw.zzgf().zzis().zzd("Too many unique user properties are set. Ignoring user property. appId", zzfh.zzbl(str), this.zzacw.zzgb().zzbk(zzkb.name), zzkb.value);
                    this.zzacw.zzgc().zza(str, 9, (String) null, (String) null, 0);
                }
            }
        }
        return true;
    }

    private final zzko[] zza(String str, zzku[] zzkuArr, zzkp[] zzkpArr) {
        Preconditions.checkNotEmpty(str);
        return zzjd().zza(str, zzkpArr, zzkuArr);
    }

    @WorkerThread
    private final void zzab() {
        this.zzacw.zzge().zzab();
    }

    @WorkerThread
    private final void zzb(zzdy zzdy) {
        ArrayMap arrayMap;
        zzab();
        if (TextUtils.isEmpty(zzdy.getGmpAppId())) {
            zzb(zzdy.zzah(), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
            return;
        }
        String gmpAppId = zzdy.getGmpAppId();
        String appInstanceId = zzdy.getAppInstanceId();
        Uri.Builder builder = new Uri.Builder();
        Uri.Builder encodedAuthority = builder.scheme(zzey.zzagt.get()).encodedAuthority(zzey.zzagu.get());
        String valueOf = String.valueOf(gmpAppId);
        encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", appInstanceId).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", "12451");
        String uri = builder.build().toString();
        try {
            URL url = new URL(uri);
            this.zzacw.zzgf().zziz().zzg("Fetching remote configuration", zzdy.zzah());
            zzkm zzbt = zzkv().zzbt(zzdy.zzah());
            String zzbu = zzkv().zzbu(zzdy.zzah());
            if (zzbt == null || TextUtils.isEmpty(zzbu)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put("If-Modified-Since", zzbu);
                arrayMap = arrayMap2;
            }
            this.zzaqw = true;
            zzfl zzkw = zzkw();
            String zzah = zzdy.zzah();
            zzjv zzjv = new zzjv(this);
            zzkw.zzab();
            zzkw.zzch();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzjv);
            zzkw.zzge().zzd((Runnable) new zzfp(zzkw, zzah, url, (byte[]) null, arrayMap, zzjv));
        } catch (MalformedURLException e) {
            this.zzacw.zzgf().zzis().zze("Failed to parse config URL. Not fetching. appId", zzfh.zzbl(zzdy.zzah()), uri);
        }
    }

    @WorkerThread
    private final Boolean zzc(zzdy zzdy) {
        try {
            if (zzdy.zzgo() != -2147483648L) {
                if (zzdy.zzgo() == ((long) Wrappers.packageManager(this.zzacw.getContext()).getPackageInfo(zzdy.zzah(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzacw.getContext()).getPackageInfo(zzdy.zzah(), 0).versionName;
                if (zzdy.zzag() != null && zzdy.zzag().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @WorkerThread
    private final void zzc(zzew zzew, zzdz zzdz) {
        zzes zzac;
        zzer zzer;
        zzks zzks;
        boolean z;
        zzdy zzbb;
        Preconditions.checkNotNull(zzdz);
        Preconditions.checkNotEmpty(zzdz.packageName);
        long nanoTime = System.nanoTime();
        zzab();
        zzkz();
        String str = zzdz.packageName;
        if (this.zzacw.zzgc().zzd(zzew, zzdz)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
            } else if (zzkv().zzn(str, zzew.name)) {
                this.zzacw.zzgf().zziv().zze("Dropping blacklisted event. appId", zzfh.zzbl(str), this.zzacw.zzgb().zzbi(zzew.name));
                boolean z2 = zzkv().zzbx(str) || zzkv().zzby(str);
                if (!z2 && !"_err".equals(zzew.name)) {
                    this.zzacw.zzgc().zza(str, 11, "_ev", zzew.name, 0);
                }
                if (z2 && (zzbb = zzje().zzbb(str)) != null) {
                    if (Math.abs(this.zzacw.zzbt().currentTimeMillis() - Math.max(zzbb.zzgu(), zzbb.zzgt())) > zzey.zzaho.get().longValue()) {
                        this.zzacw.zzgf().zziy().log("Fetching config for blacklisted app");
                        zzb(zzbb);
                    }
                }
            } else {
                if (this.zzacw.zzgf().isLoggable(2)) {
                    this.zzacw.zzgf().zziz().zzg("Logging event", this.zzacw.zzgb().zzb(zzew));
                }
                zzje().beginTransaction();
                try {
                    zzg(zzdz);
                    if (("_iap".equals(zzew.name) || FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzew.name)) && !zza(str, zzew)) {
                        zzje().setTransactionSuccessful();
                        zzje().endTransaction();
                        return;
                    }
                    boolean zzcb = zzkc.zzcb(zzew.name);
                    boolean equals = "_err".equals(zzew.name);
                    zzek zza2 = zzje().zza(zzla(), str, true, zzcb, false, equals, false);
                    long intValue = zza2.zzaff - ((long) zzey.zzagz.get().intValue());
                    if (intValue > 0) {
                        if (intValue % 1000 == 1) {
                            this.zzacw.zzgf().zzis().zze("Data loss. Too many events logged. appId, count", zzfh.zzbl(str), Long.valueOf(zza2.zzaff));
                        }
                        zzje().setTransactionSuccessful();
                        zzje().endTransaction();
                        return;
                    }
                    if (zzcb) {
                        long intValue2 = zza2.zzafe - ((long) zzey.zzahb.get().intValue());
                        if (intValue2 > 0) {
                            if (intValue2 % 1000 == 1) {
                                this.zzacw.zzgf().zzis().zze("Data loss. Too many public events logged. appId, count", zzfh.zzbl(str), Long.valueOf(zza2.zzafe));
                            }
                            this.zzacw.zzgc().zza(str, 16, "_ev", zzew.name, 0);
                            zzje().setTransactionSuccessful();
                            zzje().endTransaction();
                            return;
                        }
                    }
                    if (equals) {
                        long max = zza2.zzafh - ((long) Math.max(0, Math.min(1000000, this.zzacw.zzgh().zzb(zzdz.packageName, zzey.zzaha))));
                        if (max > 0) {
                            if (max == 1) {
                                this.zzacw.zzgf().zzis().zze("Too many error events logged. appId, count", zzfh.zzbl(str), Long.valueOf(zza2.zzafh));
                            }
                            zzje().setTransactionSuccessful();
                            zzje().endTransaction();
                            return;
                        }
                    }
                    Bundle zzij = zzew.zzafr.zzij();
                    this.zzacw.zzgc().zza(zzij, "_o", (Object) zzew.origin);
                    if (this.zzacw.zzgc().zzci(str)) {
                        this.zzacw.zzgc().zza(zzij, "_dbg", (Object) 1L);
                        this.zzacw.zzgc().zza(zzij, "_r", (Object) 1L);
                    }
                    long zzbc = zzje().zzbc(str);
                    if (zzbc > 0) {
                        this.zzacw.zzgf().zziv().zze("Data lost. Too many events stored on disk, deleted. appId", zzfh.zzbl(str), Long.valueOf(zzbc));
                    }
                    zzer zzer2 = new zzer(this.zzacw, zzew.origin, str, zzew.name, zzew.zzagc, 0, zzij);
                    zzes zzf = zzje().zzf(str, zzer2.name);
                    if (zzf != null) {
                        zzer zza3 = zzer2.zza(this.zzacw, zzf.zzafu);
                        zzac = zzf.zzac(zza3.timestamp);
                        zzer = zza3;
                    } else if (zzje().zzbf(str) < 500 || !zzcb) {
                        zzac = new zzes(str, zzer2.name, 0, 0, zzer2.timestamp, 0, (Long) null, (Long) null, (Boolean) null);
                        zzer = zzer2;
                    } else {
                        this.zzacw.zzgf().zzis().zzd("Too many event names used, ignoring event. appId, name, supported count", zzfh.zzbl(str), this.zzacw.zzgb().zzbi(zzer2.name), 500);
                        this.zzacw.zzgc().zza(str, 8, (String) null, (String) null, 0);
                        zzje().endTransaction();
                        return;
                    }
                    zzje().zza(zzac);
                    zzab();
                    zzkz();
                    Preconditions.checkNotNull(zzer);
                    Preconditions.checkNotNull(zzdz);
                    Preconditions.checkNotEmpty(zzer.zzti);
                    Preconditions.checkArgument(zzer.zzti.equals(zzdz.packageName));
                    zzks = new zzks();
                    zzks.zzatt = 1;
                    zzks.zzaub = "android";
                    zzks.zzti = zzdz.packageName;
                    zzks.zzadt = zzdz.zzadt;
                    zzks.zzth = zzdz.zzth;
                    zzks.zzaun = zzdz.zzads == -2147483648L ? null : Integer.valueOf((int) zzdz.zzads);
                    zzks.zzauf = Long.valueOf(zzdz.zzadu);
                    zzks.zzadm = zzdz.zzadm;
                    zzks.zzauj = zzdz.zzadv == 0 ? null : Long.valueOf(zzdz.zzadv);
                    Pair<String, Boolean> zzbn = this.zzacw.zzgg().zzbn(zzdz.packageName);
                    if (zzbn == null || TextUtils.isEmpty((CharSequence) zzbn.first)) {
                        if (!this.zzacw.zzfx().zzf(this.zzacw.getContext()) && zzdz.zzadz) {
                            String string = Settings.Secure.getString(this.zzacw.getContext().getContentResolver(), "android_id");
                            if (string == null) {
                                this.zzacw.zzgf().zziv().zzg("null secure ID. appId", zzfh.zzbl(zzks.zzti));
                                string = "null";
                            } else if (string.isEmpty()) {
                                this.zzacw.zzgf().zziv().zzg("empty secure ID. appId", zzfh.zzbl(zzks.zzti));
                            }
                            zzks.zzauq = string;
                        }
                    } else if (zzdz.zzady) {
                        zzks.zzauh = (String) zzbn.first;
                        zzks.zzaui = (Boolean) zzbn.second;
                    }
                    this.zzacw.zzfx().zzch();
                    zzks.zzaud = Build.MODEL;
                    this.zzacw.zzfx().zzch();
                    zzks.zzauc = Build.VERSION.RELEASE;
                    zzks.zzaue = Integer.valueOf((int) this.zzacw.zzfx().zzig());
                    zzks.zzafo = this.zzacw.zzfx().zzih();
                    zzks.zzaug = null;
                    zzks.zzatw = null;
                    zzks.zzatx = null;
                    zzks.zzaty = null;
                    zzks.zzaus = Long.valueOf(zzdz.zzadx);
                    if (this.zzacw.isEnabled() && zzeg.zzho()) {
                        zzks.zzaut = null;
                    }
                    zzdy zzbb2 = zzje().zzbb(zzdz.packageName);
                    if (zzbb2 == null) {
                        zzbb2 = new zzdy(this.zzacw, zzdz.packageName);
                        zzbb2.zzak(this.zzacw.zzfw().zzio());
                        zzbb2.zzan(zzdz.zzado);
                        zzbb2.zzal(zzdz.zzadm);
                        zzbb2.zzam(this.zzacw.zzgg().zzbo(zzdz.packageName));
                        zzbb2.zzr(0);
                        zzbb2.zzm(0);
                        zzbb2.zzn(0);
                        zzbb2.setAppVersion(zzdz.zzth);
                        zzbb2.zzo(zzdz.zzads);
                        zzbb2.zzao(zzdz.zzadt);
                        zzbb2.zzp(zzdz.zzadu);
                        zzbb2.zzq(zzdz.zzadv);
                        zzbb2.setMeasurementEnabled(zzdz.zzadw);
                        zzbb2.zzaa(zzdz.zzadx);
                        zzje().zza(zzbb2);
                    }
                    zzks.zzadl = zzbb2.getAppInstanceId();
                    zzks.zzado = zzbb2.zzgl();
                    List<zzkb> zzba = zzje().zzba(zzdz.packageName);
                    zzks.zzatv = new zzku[zzba.size()];
                    for (int i = 0; i < zzba.size(); i++) {
                        zzku zzku = new zzku();
                        zzks.zzatv[i] = zzku;
                        zzku.name = zzba.get(i).name;
                        zzku.zzauz = Long.valueOf(zzba.get(i).zzarl);
                        zzjc().zza(zzku, zzba.get(i).value);
                    }
                    long zza4 = zzje().zza(zzks);
                    zzej zzje = zzje();
                    if (zzer.zzafr != null) {
                        Iterator<String> it = zzer.zzafr.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if ("_r".equals(it.next())) {
                                    z = true;
                                    break;
                                }
                            } else {
                                boolean zzo = zzkv().zzo(zzer.zzti, zzer.name);
                                zzek zza5 = zzje().zza(zzla(), zzer.zzti, false, false, false, false, false);
                                if (zzo) {
                                    if (zza5.zzafi < ((long) this.zzacw.zzgh().zzaq(zzer.zzti))) {
                                        z = true;
                                    }
                                }
                            }
                        }
                    }
                    z = false;
                    if (zzje.zza(zzer, zza4, z)) {
                        this.zzaqs = 0;
                    }
                    zzje().setTransactionSuccessful();
                    if (this.zzacw.zzgf().isLoggable(2)) {
                        this.zzacw.zzgf().zziz().zzg("Event recorded", this.zzacw.zzgb().zza(zzer));
                    }
                    zzje().endTransaction();
                    zzld();
                    this.zzacw.zzgf().zziz().zzg("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                } catch (IOException e) {
                    this.zzacw.zzgf().zzis().zze("Data loss. Failed to insert raw event metadata. appId", zzfh.zzbl(zzks.zzti), e);
                } catch (Throwable th) {
                    zzje().endTransaction();
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0389, code lost:
        if (com.google.android.gms.internal.measurement.zzkc.zzck(r12.name) != false) goto L_0x038b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0360 A[Catch:{ IOException -> 0x02c5, all -> 0x01c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0364 A[Catch:{ IOException -> 0x02c5, all -> 0x01c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x055f A[Catch:{ IOException -> 0x02c5, all -> 0x01c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0093 A[Catch:{ IOException -> 0x02c5, all -> 0x01c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0096 A[Catch:{ IOException -> 0x02c5, all -> 0x01c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:379:0x0c0f A[Catch:{ IOException -> 0x02c5, all -> 0x01c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x0c34  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzd(java.lang.String r31, long r32) {
        /*
            r30 = this;
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()
            r2.beginTransaction()
            com.google.android.gms.internal.measurement.zzjs$zza r21 = new com.google.android.gms.internal.measurement.zzjs$zza     // Catch:{ all -> 0x01c8 }
            r2 = 0
            r0 = r21
            r1 = r30
            r0.<init>(r1, r2)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzej r14 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r4 = 0
            r0 = r30
            long r0 = r0.zzard     // Catch:{ all -> 0x01c8 }
            r16 = r0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r21)     // Catch:{ all -> 0x01c8 }
            r14.zzab()     // Catch:{ all -> 0x01c8 }
            r14.zzch()     // Catch:{ all -> 0x01c8 }
            r3 = 0
            android.database.sqlite.SQLiteDatabase r2 = r14.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0c23 }
            r5 = 0
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteException -> 0x0c23 }
            if (r5 == 0) goto L_0x01d1
            r6 = -1
            int r5 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x016a
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = 0
            java.lang.String r7 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteException -> 0x0c23 }
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = 1
            java.lang.String r7 = java.lang.String.valueOf(r32)     // Catch:{ SQLiteException -> 0x0c23 }
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = r5
        L_0x0049:
            r8 = -1
            int r5 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x0177
            java.lang.String r5 = "rowid <= ? and "
        L_0x0051:
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0c23 }
            int r7 = r7.length()     // Catch:{ SQLiteException -> 0x0c23 }
            int r7 = r7 + 148
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0c23 }
            r8.<init>(r7)     // Catch:{ SQLiteException -> 0x0c23 }
            java.lang.String r7 = "select app_id, metadata_fingerprint from raw_events where "
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch:{ SQLiteException -> 0x0c23 }
            java.lang.StringBuilder r5 = r7.append(r5)     // Catch:{ SQLiteException -> 0x0c23 }
            java.lang.String r7 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ SQLiteException -> 0x0c23 }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLiteException -> 0x0c23 }
            android.database.Cursor r3 = r2.rawQuery(r5, r6)     // Catch:{ SQLiteException -> 0x0c23 }
            boolean r5 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0c23 }
            if (r5 != 0) goto L_0x017b
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01c8 }
        L_0x0083:
            r0 = r21
            java.util.List<com.google.android.gms.internal.measurement.zzkp> r2 = r0.zzarj     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0093
            r0 = r21
            java.util.List<com.google.android.gms.internal.measurement.zzkp> r2 = r0.zzarj     // Catch:{ all -> 0x01c8 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0364
        L_0x0093:
            r2 = 1
        L_0x0094:
            if (r2 != 0) goto L_0x0c0f
            r17 = 0
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r0 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            r22 = r0
            r0 = r21
            java.util.List<com.google.android.gms.internal.measurement.zzkp> r2 = r0.zzarj     // Catch:{ all -> 0x01c8 }
            int r2 = r2.size()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkp[] r2 = new com.google.android.gms.internal.measurement.zzkp[r2]     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzatu = r2     // Catch:{ all -> 0x01c8 }
            r13 = 0
            r14 = 0
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzeg r2 = r2.zzgh()     // Catch:{ all -> 0x01c8 }
            r0 = r22
            java.lang.String r3 = r0.zzti     // Catch:{ all -> 0x01c8 }
            boolean r18 = r2.zzau(r3)     // Catch:{ all -> 0x01c8 }
            r2 = 0
            r16 = r2
        L_0x00c2:
            r0 = r21
            java.util.List<com.google.android.gms.internal.measurement.zzkp> r2 = r0.zzarj     // Catch:{ all -> 0x01c8 }
            int r2 = r2.size()     // Catch:{ all -> 0x01c8 }
            r0 = r16
            if (r0 >= r2) goto L_0x05ee
            r0 = r21
            java.util.List<com.google.android.gms.internal.measurement.zzkp> r2 = r0.zzarj     // Catch:{ all -> 0x01c8 }
            r0 = r16
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x01c8 }
            r0 = r2
            com.google.android.gms.internal.measurement.zzkp r0 = (com.google.android.gms.internal.measurement.zzkp) r0     // Catch:{ all -> 0x01c8 }
            r12 = r0
            com.google.android.gms.internal.measurement.zzgg r2 = r30.zzkv()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r12.name     // Catch:{ all -> 0x01c8 }
            boolean r2 = r2.zzn(r3, r4)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x036a
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Dropping blacklisted raw event. appId"
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x01c8 }
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r5 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzff r5 = r5.zzgb()     // Catch:{ all -> 0x01c8 }
            java.lang.String r6 = r12.name     // Catch:{ all -> 0x01c8 }
            java.lang.String r5 = r5.zzbi(r6)     // Catch:{ all -> 0x01c8 }
            r2.zze(r3, r4, r5)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzgg r2 = r30.zzkv()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            boolean r2 = r2.zzbx(r3)     // Catch:{ all -> 0x01c8 }
            if (r2 != 0) goto L_0x0137
            com.google.android.gms.internal.measurement.zzgg r2 = r30.zzkv()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            boolean r2 = r2.zzby(r3)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0367
        L_0x0137:
            r2 = 1
        L_0x0138:
            if (r2 != 0) goto L_0x0c41
            java.lang.String r2 = "_err"
            java.lang.String r3 = r12.name     // Catch:{ all -> 0x01c8 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x01c8 }
            if (r2 != 0) goto L_0x0c41
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkc r2 = r2.zzgc()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            r4 = 11
            java.lang.String r5 = "_ev"
            java.lang.String r6 = r12.name     // Catch:{ all -> 0x01c8 }
            r7 = 0
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)     // Catch:{ all -> 0x01c8 }
            r2 = r14
            r4 = r13
            r5 = r17
        L_0x0160:
            int r6 = r16 + 1
            r16 = r6
            r14 = r2
            r13 = r4
            r17 = r5
            goto L_0x00c2
        L_0x016a:
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = 0
            java.lang.String r7 = java.lang.String.valueOf(r32)     // Catch:{ SQLiteException -> 0x0c23 }
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = r5
            goto L_0x0049
        L_0x0177:
            java.lang.String r5 = ""
            goto L_0x0051
        L_0x017b:
            r5 = 0
            java.lang.String r4 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x0c23 }
            r5 = 1
            java.lang.String r5 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x0c23 }
            r3.close()     // Catch:{ SQLiteException -> 0x0c23 }
            r13 = r5
            r11 = r3
            r12 = r4
        L_0x018b:
            java.lang.String r3 = "raw_events_metadata"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r5 = 0
            java.lang.String r6 = "metadata"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r7 = 0
            r6[r7] = r12     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r7 = 1
            r6[r7] = r13     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "2"
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            boolean r3 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            if (r3 != 0) goto L_0x023b
            com.google.android.gms.internal.measurement.zzfh r2 = r14.zzgf()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zzis()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            java.lang.String r3 = "Raw event metadata record is missing. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r12)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r2.zzg(r3, r4)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            if (r11 == 0) goto L_0x0083
            r11.close()     // Catch:{ all -> 0x01c8 }
            goto L_0x0083
        L_0x01c8:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzej r3 = r30.zzje()
            r3.endTransaction()
            throw r2
        L_0x01d1:
            r6 = -1
            int r5 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x0222
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = 0
            r7 = 0
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = 1
            java.lang.String r7 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteException -> 0x0c23 }
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = r5
        L_0x01e6:
            r8 = -1
            int r5 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x022b
            java.lang.String r5 = " and rowid <= ?"
        L_0x01ee:
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0c23 }
            int r7 = r7.length()     // Catch:{ SQLiteException -> 0x0c23 }
            int r7 = r7 + 84
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0c23 }
            r8.<init>(r7)     // Catch:{ SQLiteException -> 0x0c23 }
            java.lang.String r7 = "select metadata_fingerprint from raw_events where app_id = ?"
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch:{ SQLiteException -> 0x0c23 }
            java.lang.StringBuilder r5 = r7.append(r5)     // Catch:{ SQLiteException -> 0x0c23 }
            java.lang.String r7 = " order by rowid limit 1;"
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ SQLiteException -> 0x0c23 }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLiteException -> 0x0c23 }
            android.database.Cursor r3 = r2.rawQuery(r5, r6)     // Catch:{ SQLiteException -> 0x0c23 }
            boolean r5 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0c23 }
            if (r5 != 0) goto L_0x022e
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01c8 }
            goto L_0x0083
        L_0x0222:
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = 0
            r7 = 0
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x0c23 }
            r6 = r5
            goto L_0x01e6
        L_0x022b:
            java.lang.String r5 = ""
            goto L_0x01ee
        L_0x022e:
            r5 = 0
            java.lang.String r5 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x0c23 }
            r3.close()     // Catch:{ SQLiteException -> 0x0c23 }
            r13 = r5
            r11 = r3
            r12 = r4
            goto L_0x018b
        L_0x023b:
            r3 = 0
            byte[] r3 = r11.getBlob(r3)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r4 = 0
            int r5 = r3.length     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            com.google.android.gms.internal.measurement.zzabx r3 = com.google.android.gms.internal.measurement.zzabx.zza(r3, r4, r5)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            com.google.android.gms.internal.measurement.zzks r4 = new com.google.android.gms.internal.measurement.zzks     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r4.<init>()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r4.zzb(r3)     // Catch:{ IOException -> 0x02c5 }
            boolean r3 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            if (r3 == 0) goto L_0x0265
            com.google.android.gms.internal.measurement.zzfh r3 = r14.zzgf()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            com.google.android.gms.internal.measurement.zzfj r3 = r3.zziv()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            java.lang.String r5 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfh.zzbl(r12)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r3.zzg(r5, r6)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
        L_0x0265:
            r11.close()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r0 = r21
            r0.zzb(r4)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r4 = -1
            int r3 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x02de
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            r3 = 3
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r3 = 0
            r6[r3] = r12     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r3 = 1
            r6[r3] = r13     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r3 = 2
            java.lang.String r4 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r6[r3] = r4     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
        L_0x0285:
            java.lang.String r3 = "raw_events"
            r4 = 4
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r7 = 0
            java.lang.String r8 = "rowid"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r7 = 1
            java.lang.String r8 = "name"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r7 = 2
            java.lang.String r8 = "timestamp"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r7 = 3
            java.lang.String r8 = "data"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            r10 = 0
            android.database.Cursor r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0c26 }
            if (r2 != 0) goto L_0x0305
            com.google.android.gms.internal.measurement.zzfh r2 = r14.zzgf()     // Catch:{ SQLiteException -> 0x0c26 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ SQLiteException -> 0x0c26 }
            java.lang.String r4 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfh.zzbl(r12)     // Catch:{ SQLiteException -> 0x0c26 }
            r2.zzg(r4, r5)     // Catch:{ SQLiteException -> 0x0c26 }
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01c8 }
            goto L_0x0083
        L_0x02c5:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzfh r3 = r14.zzgf()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            com.google.android.gms.internal.measurement.zzfj r3 = r3.zzis()     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            java.lang.String r4 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfh.zzbl(r12)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r3.zze(r4, r5, r2)     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            if (r11 == 0) goto L_0x0083
            r11.close()     // Catch:{ all -> 0x01c8 }
            goto L_0x0083
        L_0x02de:
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r3 = 2
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r3 = 0
            r6[r3] = r12     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            r3 = 1
            r6[r3] = r13     // Catch:{ SQLiteException -> 0x02ea, all -> 0x0c1f }
            goto L_0x0285
        L_0x02ea:
            r2 = move-exception
            r3 = r11
            r4 = r12
        L_0x02ed:
            com.google.android.gms.internal.measurement.zzfh r5 = r14.zzgf()     // Catch:{ all -> 0x035d }
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zzis()     // Catch:{ all -> 0x035d }
            java.lang.String r6 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x035d }
            r5.zze(r6, r4, r2)     // Catch:{ all -> 0x035d }
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01c8 }
            goto L_0x0083
        L_0x0305:
            r2 = 0
            long r4 = r3.getLong(r2)     // Catch:{ SQLiteException -> 0x0c26 }
            r2 = 3
            byte[] r2 = r3.getBlob(r2)     // Catch:{ SQLiteException -> 0x0c26 }
            r6 = 0
            int r7 = r2.length     // Catch:{ SQLiteException -> 0x0c26 }
            com.google.android.gms.internal.measurement.zzabx r2 = com.google.android.gms.internal.measurement.zzabx.zza(r2, r6, r7)     // Catch:{ SQLiteException -> 0x0c26 }
            com.google.android.gms.internal.measurement.zzkp r6 = new com.google.android.gms.internal.measurement.zzkp     // Catch:{ SQLiteException -> 0x0c26 }
            r6.<init>()     // Catch:{ SQLiteException -> 0x0c26 }
            r6.zzb(r2)     // Catch:{ IOException -> 0x033e }
            r2 = 1
            java.lang.String r2 = r3.getString(r2)     // Catch:{ SQLiteException -> 0x0c26 }
            r6.name = r2     // Catch:{ SQLiteException -> 0x0c26 }
            r2 = 2
            long r8 = r3.getLong(r2)     // Catch:{ SQLiteException -> 0x0c26 }
            java.lang.Long r2 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x0c26 }
            r6.zzatn = r2     // Catch:{ SQLiteException -> 0x0c26 }
            r0 = r21
            boolean r2 = r0.zza(r4, r6)     // Catch:{ SQLiteException -> 0x0c26 }
            if (r2 != 0) goto L_0x0350
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01c8 }
            goto L_0x0083
        L_0x033e:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzfh r4 = r14.zzgf()     // Catch:{ SQLiteException -> 0x0c26 }
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zzis()     // Catch:{ SQLiteException -> 0x0c26 }
            java.lang.String r5 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfh.zzbl(r12)     // Catch:{ SQLiteException -> 0x0c26 }
            r4.zze(r5, r6, r2)     // Catch:{ SQLiteException -> 0x0c26 }
        L_0x0350:
            boolean r2 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0c26 }
            if (r2 != 0) goto L_0x0305
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01c8 }
            goto L_0x0083
        L_0x035d:
            r2 = move-exception
        L_0x035e:
            if (r3 == 0) goto L_0x0363
            r3.close()     // Catch:{ all -> 0x01c8 }
        L_0x0363:
            throw r2     // Catch:{ all -> 0x01c8 }
        L_0x0364:
            r2 = 0
            goto L_0x0094
        L_0x0367:
            r2 = 0
            goto L_0x0138
        L_0x036a:
            com.google.android.gms.internal.measurement.zzgg r2 = r30.zzkv()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r12.name     // Catch:{ all -> 0x01c8 }
            boolean r19 = r2.zzo(r3, r4)     // Catch:{ all -> 0x01c8 }
            if (r19 != 0) goto L_0x038b
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            r2.zzgc()     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = r12.name     // Catch:{ all -> 0x01c8 }
            boolean r2 = com.google.android.gms.internal.measurement.zzkc.zzck(r2)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x05ba
        L_0x038b:
            r4 = 0
            r3 = 0
            com.google.android.gms.internal.measurement.zzkq[] r2 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            if (r2 != 0) goto L_0x0396
            r2 = 0
            com.google.android.gms.internal.measurement.zzkq[] r2 = new com.google.android.gms.internal.measurement.zzkq[r2]     // Catch:{ all -> 0x01c8 }
            r12.zzatm = r2     // Catch:{ all -> 0x01c8 }
        L_0x0396:
            com.google.android.gms.internal.measurement.zzkq[] r6 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            int r7 = r6.length     // Catch:{ all -> 0x01c8 }
            r2 = 0
            r5 = r2
        L_0x039b:
            if (r5 >= r7) goto L_0x03cb
            r2 = r6[r5]     // Catch:{ all -> 0x01c8 }
            java.lang.String r8 = "_c"
            java.lang.String r9 = r2.name     // Catch:{ all -> 0x01c8 }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x01c8 }
            if (r8 == 0) goto L_0x03b7
            r8 = 1
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x01c8 }
            r2.zzatq = r4     // Catch:{ all -> 0x01c8 }
            r4 = 1
            r2 = r3
        L_0x03b3:
            int r5 = r5 + 1
            r3 = r2
            goto L_0x039b
        L_0x03b7:
            java.lang.String r8 = "_r"
            java.lang.String r9 = r2.name     // Catch:{ all -> 0x01c8 }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x01c8 }
            if (r8 == 0) goto L_0x0c3e
            r8 = 1
            java.lang.Long r3 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x01c8 }
            r2.zzatq = r3     // Catch:{ all -> 0x01c8 }
            r2 = 1
            goto L_0x03b3
        L_0x03cb:
            if (r4 != 0) goto L_0x0413
            if (r19 == 0) goto L_0x0413
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziz()     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "Marking event as conversion"
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r5 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzff r5 = r5.zzgb()     // Catch:{ all -> 0x01c8 }
            java.lang.String r6 = r12.name     // Catch:{ all -> 0x01c8 }
            java.lang.String r5 = r5.zzbi(r6)     // Catch:{ all -> 0x01c8 }
            r2.zzg(r4, r5)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r4 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            int r4 = r4.length     // Catch:{ all -> 0x01c8 }
            int r4 = r4 + 1
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r4)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = (com.google.android.gms.internal.measurement.zzkq[]) r2     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq r4 = new com.google.android.gms.internal.measurement.zzkq     // Catch:{ all -> 0x01c8 }
            r4.<init>()     // Catch:{ all -> 0x01c8 }
            java.lang.String r5 = "_c"
            r4.name = r5     // Catch:{ all -> 0x01c8 }
            r6 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01c8 }
            r4.zzatq = r5     // Catch:{ all -> 0x01c8 }
            int r5 = r2.length     // Catch:{ all -> 0x01c8 }
            int r5 = r5 + -1
            r2[r5] = r4     // Catch:{ all -> 0x01c8 }
            r12.zzatm = r2     // Catch:{ all -> 0x01c8 }
        L_0x0413:
            if (r3 != 0) goto L_0x0459
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziz()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Marking event as real-time"
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r4 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzff r4 = r4.zzgb()     // Catch:{ all -> 0x01c8 }
            java.lang.String r5 = r12.name     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzbi(r5)     // Catch:{ all -> 0x01c8 }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r3 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            int r3 = r3.length     // Catch:{ all -> 0x01c8 }
            int r3 = r3 + 1
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r3)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = (com.google.android.gms.internal.measurement.zzkq[]) r2     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq r3 = new com.google.android.gms.internal.measurement.zzkq     // Catch:{ all -> 0x01c8 }
            r3.<init>()     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "_r"
            r3.name = r4     // Catch:{ all -> 0x01c8 }
            r4 = 1
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x01c8 }
            r3.zzatq = r4     // Catch:{ all -> 0x01c8 }
            int r4 = r2.length     // Catch:{ all -> 0x01c8 }
            int r4 = r4 + -1
            r2[r4] = r3     // Catch:{ all -> 0x01c8 }
            r12.zzatm = r2     // Catch:{ all -> 0x01c8 }
        L_0x0459:
            r2 = 1
            com.google.android.gms.internal.measurement.zzej r3 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            long r4 = r30.zzla()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r6 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r6 = r6.zzti     // Catch:{ all -> 0x01c8 }
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 1
            com.google.android.gms.internal.measurement.zzek r3 = r3.zza(r4, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x01c8 }
            long r4 = r3.zzafi     // Catch:{ all -> 0x01c8 }
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r3 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzeg r3 = r3.zzgh()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r6 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r6 = r6.zzti     // Catch:{ all -> 0x01c8 }
            int r3 = r3.zzaq(r6)     // Catch:{ all -> 0x01c8 }
            long r6 = (long) r3     // Catch:{ all -> 0x01c8 }
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x0c3a
            r2 = 0
        L_0x048b:
            com.google.android.gms.internal.measurement.zzkq[] r3 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            int r3 = r3.length     // Catch:{ all -> 0x01c8 }
            if (r2 >= r3) goto L_0x04bc
            java.lang.String r3 = "_r"
            com.google.android.gms.internal.measurement.zzkq[] r4 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            r4 = r4[r2]     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.name     // Catch:{ all -> 0x01c8 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x01c8 }
            if (r3 == 0) goto L_0x052c
            com.google.android.gms.internal.measurement.zzkq[] r3 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            int r3 = r3.length     // Catch:{ all -> 0x01c8 }
            int r3 = r3 + -1
            com.google.android.gms.internal.measurement.zzkq[] r3 = new com.google.android.gms.internal.measurement.zzkq[r3]     // Catch:{ all -> 0x01c8 }
            if (r2 <= 0) goto L_0x04ae
            com.google.android.gms.internal.measurement.zzkq[] r4 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            r5 = 0
            r6 = 0
            java.lang.System.arraycopy(r4, r5, r3, r6, r2)     // Catch:{ all -> 0x01c8 }
        L_0x04ae:
            int r4 = r3.length     // Catch:{ all -> 0x01c8 }
            if (r2 >= r4) goto L_0x04ba
            com.google.android.gms.internal.measurement.zzkq[] r4 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            int r5 = r2 + 1
            int r6 = r3.length     // Catch:{ all -> 0x01c8 }
            int r6 = r6 - r2
            java.lang.System.arraycopy(r4, r5, r3, r2, r6)     // Catch:{ all -> 0x01c8 }
        L_0x04ba:
            r12.zzatm = r3     // Catch:{ all -> 0x01c8 }
        L_0x04bc:
            java.lang.String r2 = r12.name     // Catch:{ all -> 0x01c8 }
            boolean r2 = com.google.android.gms.internal.measurement.zzkc.zzcb(r2)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x05ba
            if (r19 == 0) goto L_0x05ba
            com.google.android.gms.internal.measurement.zzej r3 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            long r4 = r30.zzla()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r2 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r6 = r2.zzti     // Catch:{ all -> 0x01c8 }
            r7 = 0
            r8 = 0
            r9 = 1
            r10 = 0
            r11 = 0
            com.google.android.gms.internal.measurement.zzek r2 = r3.zza(r4, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x01c8 }
            long r2 = r2.zzafg     // Catch:{ all -> 0x01c8 }
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r4 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzeg r4 = r4.zzgh()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r5 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r5 = r5.zzti     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzey$zza<java.lang.Integer> r6 = com.google.android.gms.internal.measurement.zzey.zzahc     // Catch:{ all -> 0x01c8 }
            int r4 = r4.zzb(r5, r6)     // Catch:{ all -> 0x01c8 }
            long r4 = (long) r4     // Catch:{ all -> 0x01c8 }
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x05ba
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Too many conversions. Not logging as conversion. appId"
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x01c8 }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x01c8 }
            r4 = 0
            r3 = 0
            com.google.android.gms.internal.measurement.zzkq[] r6 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            int r7 = r6.length     // Catch:{ all -> 0x01c8 }
            r2 = 0
            r5 = r2
        L_0x051a:
            if (r5 >= r7) goto L_0x053d
            r2 = r6[r5]     // Catch:{ all -> 0x01c8 }
            java.lang.String r8 = "_c"
            java.lang.String r9 = r2.name     // Catch:{ all -> 0x01c8 }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x01c8 }
            if (r8 == 0) goto L_0x0530
        L_0x0528:
            int r5 = r5 + 1
            r3 = r2
            goto L_0x051a
        L_0x052c:
            int r2 = r2 + 1
            goto L_0x048b
        L_0x0530:
            java.lang.String r8 = "_err"
            java.lang.String r2 = r2.name     // Catch:{ all -> 0x01c8 }
            boolean r2 = r8.equals(r2)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0c37
            r4 = 1
            r2 = r3
            goto L_0x0528
        L_0x053d:
            if (r4 == 0) goto L_0x058e
            if (r3 == 0) goto L_0x058e
            com.google.android.gms.internal.measurement.zzkq[] r2 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            r4 = 1
            com.google.android.gms.internal.measurement.zzkq[] r4 = new com.google.android.gms.internal.measurement.zzkq[r4]     // Catch:{ all -> 0x01c8 }
            r5 = 0
            r4[r5] = r3     // Catch:{ all -> 0x01c8 }
            java.lang.Object[] r2 = com.google.android.gms.common.util.ArrayUtils.removeAll(r2, r4)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = (com.google.android.gms.internal.measurement.zzkq[]) r2     // Catch:{ all -> 0x01c8 }
            r12.zzatm = r2     // Catch:{ all -> 0x01c8 }
            r5 = r17
        L_0x0553:
            if (r18 == 0) goto L_0x0c34
            java.lang.String r2 = "_e"
            java.lang.String r3 = r12.name     // Catch:{ all -> 0x01c8 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0c34
            com.google.android.gms.internal.measurement.zzkq[] r2 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0568
            com.google.android.gms.internal.measurement.zzkq[] r2 = r12.zzatm     // Catch:{ all -> 0x01c8 }
            int r2 = r2.length     // Catch:{ all -> 0x01c8 }
            if (r2 != 0) goto L_0x05bd
        L_0x0568:
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Engagement event does not contain any parameters. appId"
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x01c8 }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x01c8 }
            r2 = r14
        L_0x0584:
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r6 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            int r4 = r13 + 1
            r6[r13] = r12     // Catch:{ all -> 0x01c8 }
            goto L_0x0160
        L_0x058e:
            if (r3 == 0) goto L_0x059f
            java.lang.String r2 = "_err"
            r3.name = r2     // Catch:{ all -> 0x01c8 }
            r4 = 10
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x01c8 }
            r3.zzatq = r2     // Catch:{ all -> 0x01c8 }
            r5 = r17
            goto L_0x0553
        L_0x059f:
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zzis()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Did not find conversion parameter. appId"
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x01c8 }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x01c8 }
        L_0x05ba:
            r5 = r17
            goto L_0x0553
        L_0x05bd:
            r30.zzjc()     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = "_et"
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzjy.zzb(r12, r2)     // Catch:{ all -> 0x01c8 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x01c8 }
            if (r2 != 0) goto L_0x05e7
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Engagement event does not include duration. appId"
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x01c8 }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x01c8 }
            r2 = r14
            goto L_0x0584
        L_0x05e7:
            long r2 = r2.longValue()     // Catch:{ all -> 0x01c8 }
            long r14 = r14 + r2
            r2 = r14
            goto L_0x0584
        L_0x05ee:
            r0 = r21
            java.util.List<com.google.android.gms.internal.measurement.zzkp> r2 = r0.zzarj     // Catch:{ all -> 0x01c8 }
            int r2 = r2.size()     // Catch:{ all -> 0x01c8 }
            if (r13 >= r2) goto L_0x0606
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r2 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r13)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkp[] r2 = (com.google.android.gms.internal.measurement.zzkp[]) r2     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzatu = r2     // Catch:{ all -> 0x01c8 }
        L_0x0606:
            if (r18 == 0) goto L_0x06c1
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r0 = r22
            java.lang.String r3 = r0.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "_lte"
            com.google.android.gms.internal.measurement.zzkb r8 = r2.zzh(r3, r4)     // Catch:{ all -> 0x01c8 }
            if (r8 == 0) goto L_0x061c
            java.lang.Object r2 = r8.value     // Catch:{ all -> 0x01c8 }
            if (r2 != 0) goto L_0x07a5
        L_0x061c:
            com.google.android.gms.internal.measurement.zzkb r2 = new com.google.android.gms.internal.measurement.zzkb     // Catch:{ all -> 0x01c8 }
            r0 = r22
            java.lang.String r3 = r0.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "auto"
            java.lang.String r5 = "_lte"
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r6 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.common.util.Clock r6 = r6.zzbt()     // Catch:{ all -> 0x01c8 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x01c8 }
            java.lang.Long r8 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x01c8 }
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x01c8 }
            r4 = r2
        L_0x063a:
            com.google.android.gms.internal.measurement.zzku r5 = new com.google.android.gms.internal.measurement.zzku     // Catch:{ all -> 0x01c8 }
            r5.<init>()     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = "_lte"
            r5.name = r2     // Catch:{ all -> 0x01c8 }
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.common.util.Clock r2 = r2.zzbt()     // Catch:{ all -> 0x01c8 }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x01c8 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x01c8 }
            r5.zzauz = r2     // Catch:{ all -> 0x01c8 }
            java.lang.Object r2 = r4.value     // Catch:{ all -> 0x01c8 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x01c8 }
            r5.zzatq = r2     // Catch:{ all -> 0x01c8 }
            r2 = 0
            r3 = 0
        L_0x065d:
            r0 = r22
            com.google.android.gms.internal.measurement.zzku[] r6 = r0.zzatv     // Catch:{ all -> 0x01c8 }
            int r6 = r6.length     // Catch:{ all -> 0x01c8 }
            if (r3 >= r6) goto L_0x067b
            java.lang.String r6 = "_lte"
            r0 = r22
            com.google.android.gms.internal.measurement.zzku[] r7 = r0.zzatv     // Catch:{ all -> 0x01c8 }
            r7 = r7[r3]     // Catch:{ all -> 0x01c8 }
            java.lang.String r7 = r7.name     // Catch:{ all -> 0x01c8 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x01c8 }
            if (r6 == 0) goto L_0x07ce
            r0 = r22
            com.google.android.gms.internal.measurement.zzku[] r2 = r0.zzatv     // Catch:{ all -> 0x01c8 }
            r2[r3] = r5     // Catch:{ all -> 0x01c8 }
            r2 = 1
        L_0x067b:
            if (r2 != 0) goto L_0x06a1
            r0 = r22
            com.google.android.gms.internal.measurement.zzku[] r2 = r0.zzatv     // Catch:{ all -> 0x01c8 }
            r0 = r22
            com.google.android.gms.internal.measurement.zzku[] r3 = r0.zzatv     // Catch:{ all -> 0x01c8 }
            int r3 = r3.length     // Catch:{ all -> 0x01c8 }
            int r3 = r3 + 1
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r3)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzku[] r2 = (com.google.android.gms.internal.measurement.zzku[]) r2     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzatv = r2     // Catch:{ all -> 0x01c8 }
            r0 = r22
            com.google.android.gms.internal.measurement.zzku[] r2 = r0.zzatv     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzku[] r3 = r3.zzatv     // Catch:{ all -> 0x01c8 }
            int r3 = r3.length     // Catch:{ all -> 0x01c8 }
            int r3 = r3 + -1
            r2[r3] = r5     // Catch:{ all -> 0x01c8 }
        L_0x06a1:
            r2 = 0
            int r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x06c1
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r2.zza((com.google.android.gms.internal.measurement.zzkb) r4)     // Catch:{ all -> 0x01c8 }
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziy()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Updated lifetime engagement user property with value. Value"
            java.lang.Object r4 = r4.value     // Catch:{ all -> 0x01c8 }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x01c8 }
        L_0x06c1:
            r0 = r22
            java.lang.String r2 = r0.zzti     // Catch:{ all -> 0x01c8 }
            r0 = r22
            com.google.android.gms.internal.measurement.zzku[] r3 = r0.zzatv     // Catch:{ all -> 0x01c8 }
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r4 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            r0 = r30
            com.google.android.gms.internal.measurement.zzko[] r2 = r0.zza(r2, r3, r4)     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzaum = r2     // Catch:{ all -> 0x01c8 }
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzeg r2 = r2.zzgh()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            boolean r2 = r2.zzat(r3)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0a14
            java.util.HashMap r23 = new java.util.HashMap     // Catch:{ all -> 0x01c8 }
            r23.<init>()     // Catch:{ all -> 0x01c8 }
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r2 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            int r2 = r2.length     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkp[] r0 = new com.google.android.gms.internal.measurement.zzkp[r2]     // Catch:{ all -> 0x01c8 }
            r24 = r0
            r18 = 0
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkc r2 = r2.zzgc()     // Catch:{ all -> 0x01c8 }
            java.security.SecureRandom r25 = r2.zzll()     // Catch:{ all -> 0x01c8 }
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r0 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            r26 = r0
            r0 = r26
            int r0 = r0.length     // Catch:{ all -> 0x01c8 }
            r27 = r0
            r2 = 0
            r19 = r2
        L_0x0715:
            r0 = r19
            r1 = r27
            if (r0 >= r1) goto L_0x09db
            r28 = r26[r19]     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.String r2 = r0.name     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "_ep"
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x07d2
            r30.zzjc()     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = "_en"
            r0 = r28
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzjy.zzb(r0, r2)     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x01c8 }
            r0 = r23
            java.lang.Object r3 = r0.get(r2)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r3 = (com.google.android.gms.internal.measurement.zzes) r3     // Catch:{ all -> 0x01c8 }
            if (r3 != 0) goto L_0x0753
            com.google.android.gms.internal.measurement.zzej r3 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r3 = r3.zzf(r4, r2)     // Catch:{ all -> 0x01c8 }
            r0 = r23
            r0.put(r2, r3)     // Catch:{ all -> 0x01c8 }
        L_0x0753:
            java.lang.Long r2 = r3.zzafw     // Catch:{ all -> 0x01c8 }
            if (r2 != 0) goto L_0x09d7
            java.lang.Long r2 = r3.zzafx     // Catch:{ all -> 0x01c8 }
            long r4 = r2.longValue()     // Catch:{ all -> 0x01c8 }
            r6 = 1
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0776
            r30.zzjc()     // Catch:{ all -> 0x01c8 }
            r0 = r28
            com.google.android.gms.internal.measurement.zzkq[] r2 = r0.zzatm     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "_sr"
            java.lang.Long r5 = r3.zzafx     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = com.google.android.gms.internal.measurement.zzjy.zza((com.google.android.gms.internal.measurement.zzkq[]) r2, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x01c8 }
            r0 = r28
            r0.zzatm = r2     // Catch:{ all -> 0x01c8 }
        L_0x0776:
            java.lang.Boolean r2 = r3.zzafy     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0799
            java.lang.Boolean r2 = r3.zzafy     // Catch:{ all -> 0x01c8 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0799
            r30.zzjc()     // Catch:{ all -> 0x01c8 }
            r0 = r28
            com.google.android.gms.internal.measurement.zzkq[] r2 = r0.zzatm     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "_efs"
            r4 = 1
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = com.google.android.gms.internal.measurement.zzjy.zza((com.google.android.gms.internal.measurement.zzkq[]) r2, (java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x01c8 }
            r0 = r28
            r0.zzatm = r2     // Catch:{ all -> 0x01c8 }
        L_0x0799:
            int r2 = r18 + 1
            r24[r18] = r28     // Catch:{ all -> 0x01c8 }
        L_0x079d:
            int r3 = r19 + 1
            r19 = r3
            r18 = r2
            goto L_0x0715
        L_0x07a5:
            com.google.android.gms.internal.measurement.zzkb r2 = new com.google.android.gms.internal.measurement.zzkb     // Catch:{ all -> 0x01c8 }
            r0 = r22
            java.lang.String r3 = r0.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "auto"
            java.lang.String r5 = "_lte"
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r6 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.common.util.Clock r6 = r6.zzbt()     // Catch:{ all -> 0x01c8 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x01c8 }
            java.lang.Object r8 = r8.value     // Catch:{ all -> 0x01c8 }
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ all -> 0x01c8 }
            long r8 = r8.longValue()     // Catch:{ all -> 0x01c8 }
            long r8 = r8 + r14
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x01c8 }
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x01c8 }
            r4 = r2
            goto L_0x063a
        L_0x07ce:
            int r3 = r3 + 1
            goto L_0x065d
        L_0x07d2:
            r2 = 1
            java.lang.String r4 = "_dbg"
            r6 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01c8 }
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x01c8 }
            if (r3 != 0) goto L_0x07e3
            if (r5 != 0) goto L_0x081a
        L_0x07e3:
            r3 = 0
        L_0x07e4:
            if (r3 != 0) goto L_0x0c30
            com.google.android.gms.internal.measurement.zzgg r2 = r30.zzkv()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x01c8 }
            int r2 = r2.zzp(r3, r4)     // Catch:{ all -> 0x01c8 }
            r20 = r2
        L_0x07fa:
            if (r20 > 0) goto L_0x0859
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Sample rate must be positive. event, rate"
            r0 = r28
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x01c8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r20)     // Catch:{ all -> 0x01c8 }
            r2.zze(r3, r4, r5)     // Catch:{ all -> 0x01c8 }
            int r2 = r18 + 1
            r24[r18] = r28     // Catch:{ all -> 0x01c8 }
            goto L_0x079d
        L_0x081a:
            r0 = r28
            com.google.android.gms.internal.measurement.zzkq[] r6 = r0.zzatm     // Catch:{ all -> 0x01c8 }
            int r7 = r6.length     // Catch:{ all -> 0x01c8 }
            r3 = 0
        L_0x0820:
            if (r3 >= r7) goto L_0x0857
            r8 = r6[r3]     // Catch:{ all -> 0x01c8 }
            java.lang.String r9 = r8.name     // Catch:{ all -> 0x01c8 }
            boolean r9 = r4.equals(r9)     // Catch:{ all -> 0x01c8 }
            if (r9 == 0) goto L_0x0854
            boolean r3 = r5 instanceof java.lang.Long     // Catch:{ all -> 0x01c8 }
            if (r3 == 0) goto L_0x0838
            java.lang.Long r3 = r8.zzatq     // Catch:{ all -> 0x01c8 }
            boolean r3 = r5.equals(r3)     // Catch:{ all -> 0x01c8 }
            if (r3 != 0) goto L_0x0850
        L_0x0838:
            boolean r3 = r5 instanceof java.lang.String     // Catch:{ all -> 0x01c8 }
            if (r3 == 0) goto L_0x0844
            java.lang.String r3 = r8.zzajo     // Catch:{ all -> 0x01c8 }
            boolean r3 = r5.equals(r3)     // Catch:{ all -> 0x01c8 }
            if (r3 != 0) goto L_0x0850
        L_0x0844:
            boolean r3 = r5 instanceof java.lang.Double     // Catch:{ all -> 0x01c8 }
            if (r3 == 0) goto L_0x0852
            java.lang.Double r3 = r8.zzaro     // Catch:{ all -> 0x01c8 }
            boolean r3 = r5.equals(r3)     // Catch:{ all -> 0x01c8 }
            if (r3 == 0) goto L_0x0852
        L_0x0850:
            r3 = 1
            goto L_0x07e4
        L_0x0852:
            r3 = 0
            goto L_0x07e4
        L_0x0854:
            int r3 = r3 + 1
            goto L_0x0820
        L_0x0857:
            r3 = 0
            goto L_0x07e4
        L_0x0859:
            r0 = r28
            java.lang.String r2 = r0.name     // Catch:{ all -> 0x01c8 }
            r0 = r23
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r2 = (com.google.android.gms.internal.measurement.zzes) r2     // Catch:{ all -> 0x01c8 }
            if (r2 != 0) goto L_0x0c2d
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r3 = r2.zzf(r3, r4)     // Catch:{ all -> 0x01c8 }
            if (r3 != 0) goto L_0x08b7
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Event being bundled has no eventAggregate. appId, eventName"
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.String r5 = r0.name     // Catch:{ all -> 0x01c8 }
            r2.zze(r3, r4, r5)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r3 = new com.google.android.gms.internal.measurement.zzes     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r2 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r2.zzti     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.String r5 = r0.name     // Catch:{ all -> 0x01c8 }
            r6 = 1
            r8 = 1
            r0 = r28
            java.lang.Long r2 = r0.zzatn     // Catch:{ all -> 0x01c8 }
            long r10 = r2.longValue()     // Catch:{ all -> 0x01c8 }
            r12 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r3.<init>(r4, r5, r6, r8, r10, r12, r14, r15, r16)     // Catch:{ all -> 0x01c8 }
        L_0x08b7:
            r30.zzjc()     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = "_eid"
            r0 = r28
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzjy.zzb(r0, r2)     // Catch:{ all -> 0x01c8 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x08f8
            r4 = 1
        L_0x08c7:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x01c8 }
            r5 = 1
            r0 = r20
            if (r0 != r5) goto L_0x08fa
            int r2 = r18 + 1
            r24[r18] = r28     // Catch:{ all -> 0x01c8 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01c8 }
            if (r4 == 0) goto L_0x079d
            java.lang.Long r4 = r3.zzafw     // Catch:{ all -> 0x01c8 }
            if (r4 != 0) goto L_0x08e6
            java.lang.Long r4 = r3.zzafx     // Catch:{ all -> 0x01c8 }
            if (r4 != 0) goto L_0x08e6
            java.lang.Boolean r4 = r3.zzafy     // Catch:{ all -> 0x01c8 }
            if (r4 == 0) goto L_0x079d
        L_0x08e6:
            r4 = 0
            r5 = 0
            r6 = 0
            com.google.android.gms.internal.measurement.zzes r3 = r3.zza(r4, r5, r6)     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x01c8 }
            r0 = r23
            r0.put(r4, r3)     // Catch:{ all -> 0x01c8 }
            goto L_0x079d
        L_0x08f8:
            r4 = 0
            goto L_0x08c7
        L_0x08fa:
            r0 = r25
            r1 = r20
            int r5 = r0.nextInt(r1)     // Catch:{ all -> 0x01c8 }
            if (r5 != 0) goto L_0x094a
            r30.zzjc()     // Catch:{ all -> 0x01c8 }
            r0 = r28
            com.google.android.gms.internal.measurement.zzkq[] r2 = r0.zzatm     // Catch:{ all -> 0x01c8 }
            java.lang.String r5 = "_sr"
            r0 = r20
            long r6 = (long) r0     // Catch:{ all -> 0x01c8 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = com.google.android.gms.internal.measurement.zzjy.zza((com.google.android.gms.internal.measurement.zzkq[]) r2, (java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x01c8 }
            r0 = r28
            r0.zzatm = r2     // Catch:{ all -> 0x01c8 }
            int r2 = r18 + 1
            r24[r18] = r28     // Catch:{ all -> 0x01c8 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01c8 }
            if (r4 == 0) goto L_0x0933
            r4 = 0
            r0 = r20
            long r6 = (long) r0     // Catch:{ all -> 0x01c8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01c8 }
            r6 = 0
            com.google.android.gms.internal.measurement.zzes r3 = r3.zza(r4, r5, r6)     // Catch:{ all -> 0x01c8 }
        L_0x0933:
            r0 = r28
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.Long r5 = r0.zzatn     // Catch:{ all -> 0x01c8 }
            long r6 = r5.longValue()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r3 = r3.zzad(r6)     // Catch:{ all -> 0x01c8 }
            r0 = r23
            r0.put(r4, r3)     // Catch:{ all -> 0x01c8 }
            goto L_0x079d
        L_0x094a:
            long r6 = r3.zzafv     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.Long r5 = r0.zzatn     // Catch:{ all -> 0x01c8 }
            long r8 = r5.longValue()     // Catch:{ all -> 0x01c8 }
            long r6 = r8 - r6
            long r6 = java.lang.Math.abs(r6)     // Catch:{ all -> 0x01c8 }
            r8 = 86400000(0x5265c00, double:4.2687272E-316)
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 < 0) goto L_0x09c2
            r30.zzjc()     // Catch:{ all -> 0x01c8 }
            r0 = r28
            com.google.android.gms.internal.measurement.zzkq[] r2 = r0.zzatm     // Catch:{ all -> 0x01c8 }
            java.lang.String r5 = "_efs"
            r6 = 1
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = com.google.android.gms.internal.measurement.zzjy.zza((com.google.android.gms.internal.measurement.zzkq[]) r2, (java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x01c8 }
            r0 = r28
            r0.zzatm = r2     // Catch:{ all -> 0x01c8 }
            r30.zzjc()     // Catch:{ all -> 0x01c8 }
            r0 = r28
            com.google.android.gms.internal.measurement.zzkq[] r2 = r0.zzatm     // Catch:{ all -> 0x01c8 }
            java.lang.String r5 = "_sr"
            r0 = r20
            long r6 = (long) r0     // Catch:{ all -> 0x01c8 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkq[] r2 = com.google.android.gms.internal.measurement.zzjy.zza((com.google.android.gms.internal.measurement.zzkq[]) r2, (java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x01c8 }
            r0 = r28
            r0.zzatm = r2     // Catch:{ all -> 0x01c8 }
            int r2 = r18 + 1
            r24[r18] = r28     // Catch:{ all -> 0x01c8 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01c8 }
            if (r4 == 0) goto L_0x09ab
            r4 = 0
            r0 = r20
            long r6 = (long) r0     // Catch:{ all -> 0x01c8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01c8 }
            r6 = 1
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r3 = r3.zza(r4, r5, r6)     // Catch:{ all -> 0x01c8 }
        L_0x09ab:
            r0 = r28
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x01c8 }
            r0 = r28
            java.lang.Long r5 = r0.zzatn     // Catch:{ all -> 0x01c8 }
            long r6 = r5.longValue()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r3 = r3.zzad(r6)     // Catch:{ all -> 0x01c8 }
            r0 = r23
            r0.put(r4, r3)     // Catch:{ all -> 0x01c8 }
            goto L_0x079d
        L_0x09c2:
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01c8 }
            if (r4 == 0) goto L_0x09d7
            r0 = r28
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x01c8 }
            r5 = 0
            r6 = 0
            com.google.android.gms.internal.measurement.zzes r2 = r3.zza(r2, r5, r6)     // Catch:{ all -> 0x01c8 }
            r0 = r23
            r0.put(r4, r2)     // Catch:{ all -> 0x01c8 }
        L_0x09d7:
            r2 = r18
            goto L_0x079d
        L_0x09db:
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r2 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            int r2 = r2.length     // Catch:{ all -> 0x01c8 }
            r0 = r18
            if (r0 >= r2) goto L_0x09f2
            r0 = r24
            r1 = r18
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r0, r1)     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkp[] r2 = (com.google.android.gms.internal.measurement.zzkp[]) r2     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzatu = r2     // Catch:{ all -> 0x01c8 }
        L_0x09f2:
            java.util.Set r2 = r23.entrySet()     // Catch:{ all -> 0x01c8 }
            java.util.Iterator r3 = r2.iterator()     // Catch:{ all -> 0x01c8 }
        L_0x09fa:
            boolean r2 = r3.hasNext()     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0a14
            java.lang.Object r2 = r3.next()     // Catch:{ all -> 0x01c8 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzej r4 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzes r2 = (com.google.android.gms.internal.measurement.zzes) r2     // Catch:{ all -> 0x01c8 }
            r4.zza((com.google.android.gms.internal.measurement.zzes) r2)     // Catch:{ all -> 0x01c8 }
            goto L_0x09fa
        L_0x0a14:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzatx = r2     // Catch:{ all -> 0x01c8 }
            r2 = -9223372036854775808
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzaty = r2     // Catch:{ all -> 0x01c8 }
            r2 = 0
        L_0x0a2c:
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r3 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            int r3 = r3.length     // Catch:{ all -> 0x01c8 }
            if (r2 >= r3) goto L_0x0a6c
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r3 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            r3 = r3[r2]     // Catch:{ all -> 0x01c8 }
            java.lang.Long r4 = r3.zzatn     // Catch:{ all -> 0x01c8 }
            long r4 = r4.longValue()     // Catch:{ all -> 0x01c8 }
            r0 = r22
            java.lang.Long r6 = r0.zzatx     // Catch:{ all -> 0x01c8 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x01c8 }
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x0a51
            java.lang.Long r4 = r3.zzatn     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzatx = r4     // Catch:{ all -> 0x01c8 }
        L_0x0a51:
            java.lang.Long r4 = r3.zzatn     // Catch:{ all -> 0x01c8 }
            long r4 = r4.longValue()     // Catch:{ all -> 0x01c8 }
            r0 = r22
            java.lang.Long r6 = r0.zzaty     // Catch:{ all -> 0x01c8 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x01c8 }
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x0a69
            java.lang.Long r3 = r3.zzatn     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzaty = r3     // Catch:{ all -> 0x01c8 }
        L_0x0a69:
            int r2 = r2 + 1
            goto L_0x0a2c
        L_0x0a6c:
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r2 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r6 = r2.zzti     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzdy r7 = r2.zzbb(r6)     // Catch:{ all -> 0x01c8 }
            if (r7 != 0) goto L_0x0b12
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zzis()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Bundling raw events w/o app info. appId"
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x01c8 }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x01c8 }
        L_0x0a97:
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r2 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            int r2 = r2.length     // Catch:{ all -> 0x01c8 }
            if (r2 <= 0) goto L_0x0ada
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            r2.zzgi()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzgg r2 = r30.zzkv()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r3 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = r3.zzti     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzkm r2 = r2.zzbt(r3)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0ab9
            java.lang.Long r3 = r2.zzatb     // Catch:{ all -> 0x01c8 }
            if (r3 != 0) goto L_0x0b9b
        L_0x0ab9:
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r2 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = r2.zzadm     // Catch:{ all -> 0x01c8 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x01c8 }
            if (r2 == 0) goto L_0x0b7e
            r2 = -1
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzaur = r2     // Catch:{ all -> 0x01c8 }
        L_0x0acf:
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r1 = r17
            r2.zza((com.google.android.gms.internal.measurement.zzks) r0, (boolean) r1)     // Catch:{ all -> 0x01c8 }
        L_0x0ada:
            com.google.android.gms.internal.measurement.zzej r4 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r0 = r21
            java.util.List<java.lang.Long> r5 = r0.zzari     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x01c8 }
            r4.zzab()     // Catch:{ all -> 0x01c8 }
            r4.zzch()     // Catch:{ all -> 0x01c8 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = "rowid in ("
            r7.<init>(r2)     // Catch:{ all -> 0x01c8 }
            r2 = 0
            r3 = r2
        L_0x0af4:
            int r2 = r5.size()     // Catch:{ all -> 0x01c8 }
            if (r3 >= r2) goto L_0x0ba3
            if (r3 == 0) goto L_0x0b01
            java.lang.String r2 = ","
            r7.append(r2)     // Catch:{ all -> 0x01c8 }
        L_0x0b01:
            java.lang.Object r2 = r5.get(r3)     // Catch:{ all -> 0x01c8 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x01c8 }
            long r8 = r2.longValue()     // Catch:{ all -> 0x01c8 }
            r7.append(r8)     // Catch:{ all -> 0x01c8 }
            int r2 = r3 + 1
            r3 = r2
            goto L_0x0af4
        L_0x0b12:
            r0 = r22
            com.google.android.gms.internal.measurement.zzkp[] r2 = r0.zzatu     // Catch:{ all -> 0x01c8 }
            int r2 = r2.length     // Catch:{ all -> 0x01c8 }
            if (r2 <= 0) goto L_0x0a97
            long r2 = r7.zzgn()     // Catch:{ all -> 0x01c8 }
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0b7a
            java.lang.Long r4 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x01c8 }
        L_0x0b27:
            r0 = r22
            r0.zzaua = r4     // Catch:{ all -> 0x01c8 }
            long r4 = r7.zzgm()     // Catch:{ all -> 0x01c8 }
            r8 = 0
            int r8 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x0c2a
        L_0x0b35:
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0b7c
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x01c8 }
        L_0x0b3f:
            r0 = r22
            r0.zzatz = r2     // Catch:{ all -> 0x01c8 }
            r7.zzgv()     // Catch:{ all -> 0x01c8 }
            long r2 = r7.zzgs()     // Catch:{ all -> 0x01c8 }
            int r2 = (int) r2     // Catch:{ all -> 0x01c8 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzauk = r2     // Catch:{ all -> 0x01c8 }
            r0 = r22
            java.lang.Long r2 = r0.zzatx     // Catch:{ all -> 0x01c8 }
            long r2 = r2.longValue()     // Catch:{ all -> 0x01c8 }
            r7.zzm(r2)     // Catch:{ all -> 0x01c8 }
            r0 = r22
            java.lang.Long r2 = r0.zzaty     // Catch:{ all -> 0x01c8 }
            long r2 = r2.longValue()     // Catch:{ all -> 0x01c8 }
            r7.zzn(r2)     // Catch:{ all -> 0x01c8 }
            java.lang.String r2 = r7.zzhd()     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzaek = r2     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r2.zza((com.google.android.gms.internal.measurement.zzdy) r7)     // Catch:{ all -> 0x01c8 }
            goto L_0x0a97
        L_0x0b7a:
            r4 = 0
            goto L_0x0b27
        L_0x0b7c:
            r2 = 0
            goto L_0x0b3f
        L_0x0b7e:
            r0 = r30
            com.google.android.gms.internal.measurement.zzgm r2 = r0.zzacw     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "Did not find measurement config or missing version info. appId"
            r0 = r21
            com.google.android.gms.internal.measurement.zzks r4 = r0.zzarh     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = r4.zzti     // Catch:{ all -> 0x01c8 }
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x01c8 }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x01c8 }
            goto L_0x0acf
        L_0x0b9b:
            java.lang.Long r2 = r2.zzatb     // Catch:{ all -> 0x01c8 }
            r0 = r22
            r0.zzaur = r2     // Catch:{ all -> 0x01c8 }
            goto L_0x0acf
        L_0x0ba3:
            java.lang.String r2 = ")"
            r7.append(r2)     // Catch:{ all -> 0x01c8 }
            android.database.sqlite.SQLiteDatabase r2 = r4.getWritableDatabase()     // Catch:{ all -> 0x01c8 }
            java.lang.String r3 = "raw_events"
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01c8 }
            r8 = 0
            int r2 = r2.delete(r3, r7, r8)     // Catch:{ all -> 0x01c8 }
            int r3 = r5.size()     // Catch:{ all -> 0x01c8 }
            if (r2 == r3) goto L_0x0bd6
            com.google.android.gms.internal.measurement.zzfh r3 = r4.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r3 = r3.zzis()     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x01c8 }
            int r5 = r5.size()     // Catch:{ all -> 0x01c8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x01c8 }
            r3.zze(r4, r2, r5)     // Catch:{ all -> 0x01c8 }
        L_0x0bd6:
            com.google.android.gms.internal.measurement.zzej r3 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            android.database.sqlite.SQLiteDatabase r2 = r3.getWritableDatabase()     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0bfc }
            r7 = 0
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x0bfc }
            r7 = 1
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x0bfc }
            r2.execSQL(r4, r5)     // Catch:{ SQLiteException -> 0x0bfc }
        L_0x0bec:
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()
            r2.endTransaction()
            r2 = 1
        L_0x0bfb:
            return r2
        L_0x0bfc:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzfh r3 = r3.zzgf()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzfj r3 = r3.zzis()     // Catch:{ all -> 0x01c8 }
            java.lang.String r4 = "Failed to remove unused event metadata. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfh.zzbl(r6)     // Catch:{ all -> 0x01c8 }
            r3.zze(r4, r5, r2)     // Catch:{ all -> 0x01c8 }
            goto L_0x0bec
        L_0x0c0f:
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()     // Catch:{ all -> 0x01c8 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x01c8 }
            com.google.android.gms.internal.measurement.zzej r2 = r30.zzje()
            r2.endTransaction()
            r2 = 0
            goto L_0x0bfb
        L_0x0c1f:
            r2 = move-exception
            r3 = r11
            goto L_0x035e
        L_0x0c23:
            r2 = move-exception
            goto L_0x02ed
        L_0x0c26:
            r2 = move-exception
            r4 = r12
            goto L_0x02ed
        L_0x0c2a:
            r2 = r4
            goto L_0x0b35
        L_0x0c2d:
            r3 = r2
            goto L_0x08b7
        L_0x0c30:
            r20 = r2
            goto L_0x07fa
        L_0x0c34:
            r2 = r14
            goto L_0x0584
        L_0x0c37:
            r2 = r3
            goto L_0x0528
        L_0x0c3a:
            r17 = r2
            goto L_0x04bc
        L_0x0c3e:
            r2 = r3
            goto L_0x03b3
        L_0x0c41:
            r2 = r14
            r4 = r13
            r5 = r17
            goto L_0x0160
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjs.zzd(java.lang.String, long):boolean");
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final zzdy zzg(zzdz zzdz) {
        boolean z = true;
        zzab();
        zzkz();
        Preconditions.checkNotNull(zzdz);
        Preconditions.checkNotEmpty(zzdz.packageName);
        zzdy zzbb = zzje().zzbb(zzdz.packageName);
        String zzbo = this.zzacw.zzgg().zzbo(zzdz.packageName);
        boolean z2 = false;
        if (zzbb == null) {
            zzbb = new zzdy(this.zzacw, zzdz.packageName);
            zzbb.zzak(this.zzacw.zzfw().zzio());
            zzbb.zzam(zzbo);
            z2 = true;
        } else if (!zzbo.equals(zzbb.zzgk())) {
            zzbb.zzam(zzbo);
            zzbb.zzak(this.zzacw.zzfw().zzio());
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzdz.zzadm) && !zzdz.zzadm.equals(zzbb.getGmpAppId())) {
            zzbb.zzal(zzdz.zzadm);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzdz.zzado) && !zzdz.zzado.equals(zzbb.zzgl())) {
            zzbb.zzan(zzdz.zzado);
            z2 = true;
        }
        if (!(zzdz.zzadu == 0 || zzdz.zzadu == zzbb.zzgq())) {
            zzbb.zzp(zzdz.zzadu);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzdz.zzth) && !zzdz.zzth.equals(zzbb.zzag())) {
            zzbb.setAppVersion(zzdz.zzth);
            z2 = true;
        }
        if (zzdz.zzads != zzbb.zzgo()) {
            zzbb.zzo(zzdz.zzads);
            z2 = true;
        }
        if (zzdz.zzadt != null && !zzdz.zzadt.equals(zzbb.zzgp())) {
            zzbb.zzao(zzdz.zzadt);
            z2 = true;
        }
        if (zzdz.zzadv != zzbb.zzgr()) {
            zzbb.zzq(zzdz.zzadv);
            z2 = true;
        }
        if (zzdz.zzadw != zzbb.isMeasurementEnabled()) {
            zzbb.setMeasurementEnabled(zzdz.zzadw);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzdz.zzaek) && !zzdz.zzaek.equals(zzbb.zzhc())) {
            zzbb.zzap(zzdz.zzaek);
            z2 = true;
        }
        if (zzdz.zzadx != zzbb.zzhe()) {
            zzbb.zzaa(zzdz.zzadx);
            z2 = true;
        }
        if (zzdz.zzady != zzbb.zzhf()) {
            zzbb.zzd(zzdz.zzady);
            z2 = true;
        }
        if (zzdz.zzadz != zzbb.zzhg()) {
            zzbb.zze(zzdz.zzadz);
        } else {
            z = z2;
        }
        if (z) {
            zzje().zza(zzbb);
        }
        return zzbb;
    }

    public static zzjs zzg(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzaqj == null) {
            synchronized (zzjs.class) {
                if (zzaqj == null) {
                    zzaqj = new zzjs(new zzjx(context));
                }
            }
        }
        return zzaqj;
    }

    private final zzgg zzkv() {
        zza((zzjr) this.zzaqk);
        return this.zzaqk;
    }

    private final zzfq zzkx() {
        if (this.zzaqn != null) {
            return this.zzaqn;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzjo zzky() {
        zza((zzjr) this.zzaqo);
        return this.zzaqo;
    }

    private final long zzla() {
        long currentTimeMillis = this.zzacw.zzbt().currentTimeMillis();
        zzfs zzgg = this.zzacw.zzgg();
        zzgg.zzch();
        zzgg.zzab();
        long j = zzgg.zzakh.get();
        if (j == 0) {
            j = 1 + ((long) zzgg.zzgc().zzll().nextInt(86400000));
            zzgg.zzakh.set(j);
        }
        return ((((j + currentTimeMillis) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzlc() {
        zzab();
        zzkz();
        return zzje().zzhw() || !TextUtils.isEmpty(zzje().zzhr());
    }

    @WorkerThread
    private final void zzld() {
        long max;
        long j;
        zzab();
        zzkz();
        if (zzlh()) {
            if (this.zzaqs > 0) {
                long abs = 3600000 - Math.abs(this.zzacw.zzbt().elapsedRealtime() - this.zzaqs);
                if (abs > 0) {
                    this.zzacw.zzgf().zziz().zzg("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    zzkx().unregister();
                    zzky().cancel();
                    return;
                }
                this.zzaqs = 0;
            }
            if (!this.zzacw.zzkd() || !zzlc()) {
                this.zzacw.zzgf().zziz().log("Nothing to upload or uploading impossible");
                zzkx().unregister();
                zzky().cancel();
                return;
            }
            long currentTimeMillis = this.zzacw.zzbt().currentTimeMillis();
            long max2 = Math.max(0, zzey.zzahp.get().longValue());
            boolean z = zzje().zzhx() || zzje().zzhs();
            if (z) {
                String zzhn = this.zzacw.zzgh().zzhn();
                max = (TextUtils.isEmpty(zzhn) || ".none.".equals(zzhn)) ? Math.max(0, zzey.zzahj.get().longValue()) : Math.max(0, zzey.zzahk.get().longValue());
            } else {
                max = Math.max(0, zzey.zzahi.get().longValue());
            }
            long j2 = this.zzacw.zzgg().zzakd.get();
            long j3 = this.zzacw.zzgg().zzake.get();
            long max3 = Math.max(zzje().zzhu(), zzje().zzhv());
            if (max3 == 0) {
                j = 0;
            } else {
                long abs2 = currentTimeMillis - Math.abs(max3 - currentTimeMillis);
                long abs3 = currentTimeMillis - Math.abs(j3 - currentTimeMillis);
                long max4 = Math.max(currentTimeMillis - Math.abs(j2 - currentTimeMillis), abs3);
                j = abs2 + max2;
                if (z && max4 > 0) {
                    j = Math.min(abs2, max4) + max;
                }
                if (!this.zzacw.zzgc().zza(max4, max)) {
                    j = max4 + max;
                }
                if (abs3 != 0 && abs3 >= abs2) {
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= Math.min(20, Math.max(0, zzey.zzahr.get().intValue()))) {
                            j = 0;
                            break;
                        }
                        j += (1 << i2) * Math.max(0, zzey.zzahq.get().longValue());
                        if (j > abs3) {
                            break;
                        }
                        i = i2 + 1;
                    }
                }
            }
            if (j == 0) {
                this.zzacw.zzgf().zziz().log("Next upload time is 0");
                zzkx().unregister();
                zzky().cancel();
            } else if (!zzkw().zzex()) {
                this.zzacw.zzgf().zziz().log("No network");
                zzkx().zzeu();
                zzky().cancel();
            } else {
                long j4 = this.zzacw.zzgg().zzakf.get();
                long max5 = Math.max(0, zzey.zzahg.get().longValue());
                long max6 = !this.zzacw.zzgc().zza(j4, max5) ? Math.max(j, max5 + j4) : j;
                zzkx().unregister();
                long currentTimeMillis2 = max6 - this.zzacw.zzbt().currentTimeMillis();
                if (currentTimeMillis2 <= 0) {
                    currentTimeMillis2 = Math.max(0, zzey.zzahl.get().longValue());
                    this.zzacw.zzgg().zzakd.set(this.zzacw.zzbt().currentTimeMillis());
                }
                this.zzacw.zzgf().zziz().zzg("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis2));
                zzky().zzh(currentTimeMillis2);
            }
        }
    }

    @WorkerThread
    private final void zzle() {
        zzab();
        if (this.zzaqw || this.zzaqx || this.zzaqy) {
            this.zzacw.zzgf().zziz().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzaqw), Boolean.valueOf(this.zzaqx), Boolean.valueOf(this.zzaqy));
            return;
        }
        this.zzacw.zzgf().zziz().log("Stopping uploading service(s)");
        if (this.zzaqt != null) {
            for (Runnable run : this.zzaqt) {
                run.run();
            }
            this.zzaqt.clear();
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zzlf() {
        zzab();
        try {
            this.zzara = new RandomAccessFile(new File(this.zzacw.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzaqz = this.zzara.tryLock();
            if (this.zzaqz != null) {
                this.zzacw.zzgf().zziz().log("Storage concurrent access okay");
                return true;
            }
            this.zzacw.zzgf().zzis().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            this.zzacw.zzgf().zzis().zzg("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            this.zzacw.zzgf().zzis().zzg("Failed to access storage lock file", e2);
        }
    }

    @WorkerThread
    private final boolean zzlh() {
        zzab();
        zzkz();
        return this.zzaqr;
    }

    public final Context getContext() {
        return this.zzacw.getContext();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void start() {
        this.zzacw.zzge().zzab();
        zzje().zzht();
        if (this.zzacw.zzgg().zzakd.get() == 0) {
            this.zzacw.zzgg().zzakd.set(this.zzacw.zzbt().currentTimeMillis());
        }
        zzld();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzej zzje;
        zzab();
        zzkz();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzaqx = false;
                zzle();
                throw th2;
            }
        }
        List<Long> list = this.zzarb;
        this.zzarb = null;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzacw.zzgg().zzakd.set(this.zzacw.zzbt().currentTimeMillis());
                this.zzacw.zzgg().zzake.set(0);
                zzld();
                this.zzacw.zzgf().zziz().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzje().beginTransaction();
                try {
                    for (Long next : list) {
                        try {
                            zzje = zzje();
                            long longValue = next.longValue();
                            zzje.zzab();
                            zzje.zzch();
                            if (zzje.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zzje.zzgf().zzis().zzg("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzarc == null || !this.zzarc.contains(next)) {
                                throw e2;
                            }
                        }
                    }
                    zzje().setTransactionSuccessful();
                    zzje().endTransaction();
                    this.zzarc = null;
                    if (!zzkw().zzex() || !zzlc()) {
                        this.zzard = -1;
                        zzld();
                    } else {
                        zzlb();
                    }
                    this.zzaqs = 0;
                } catch (Throwable th3) {
                    zzje().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzacw.zzgf().zzis().zzg("Database error while trying to delete uploaded bundles", e3);
                this.zzaqs = this.zzacw.zzbt().elapsedRealtime();
                this.zzacw.zzgf().zziz().zzg("Disable upload, time", Long.valueOf(this.zzaqs));
            }
        } else {
            this.zzacw.zzgf().zziz().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzacw.zzgg().zzake.set(this.zzacw.zzbt().currentTimeMillis());
            if (i == 503 || i == 429) {
                this.zzacw.zzgg().zzakf.set(this.zzacw.zzbt().currentTimeMillis());
            }
            if (this.zzacw.zzgh().zzaw(str)) {
                zzje().zzc(list);
            }
            zzld();
        }
        this.zzaqx = false;
        zzle();
    }

    @WorkerThread
    public final byte[] zza(@NonNull zzew zzew, @Size(min = 1) String str) {
        long j;
        zzku zzku;
        zzkz();
        zzab();
        this.zzacw.zzfr();
        Preconditions.checkNotNull(zzew);
        Preconditions.checkNotEmpty(str);
        zzkr zzkr = new zzkr();
        zzje().beginTransaction();
        try {
            zzdy zzbb = zzje().zzbb(str);
            if (zzbb == null) {
                this.zzacw.zzgf().zziy().zzg("Log and bundle not available. package_name", str);
                return new byte[0];
            } else if (!zzbb.isMeasurementEnabled()) {
                this.zzacw.zzgf().zziy().zzg("Log and bundle disabled. package_name", str);
                byte[] bArr = new byte[0];
                zzje().endTransaction();
                return bArr;
            } else {
                if (("_iap".equals(zzew.name) || FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzew.name)) && !zza(str, zzew)) {
                    this.zzacw.zzgf().zziv().zzg("Failed to handle purchase event at single event bundle creation. appId", zzfh.zzbl(str));
                }
                boolean zzau = this.zzacw.zzgh().zzau(str);
                Long l = 0L;
                if (zzau && "_e".equals(zzew.name)) {
                    if (zzew.zzafr == null || zzew.zzafr.size() == 0) {
                        this.zzacw.zzgf().zziv().zzg("The engagement event does not contain any parameters. appId", zzfh.zzbl(str));
                    } else if (zzew.zzafr.getLong("_et") == null) {
                        this.zzacw.zzgf().zziv().zzg("The engagement event does not include duration. appId", zzfh.zzbl(str));
                    } else {
                        l = zzew.zzafr.getLong("_et");
                    }
                }
                zzks zzks = new zzks();
                zzkr.zzatr = new zzks[]{zzks};
                zzks.zzatt = 1;
                zzks.zzaub = "android";
                zzks.zzti = zzbb.zzah();
                zzks.zzadt = zzbb.zzgp();
                zzks.zzth = zzbb.zzag();
                long zzgo = zzbb.zzgo();
                zzks.zzaun = zzgo == -2147483648L ? null : Integer.valueOf((int) zzgo);
                zzks.zzauf = Long.valueOf(zzbb.zzgq());
                zzks.zzadm = zzbb.getGmpAppId();
                zzks.zzauj = Long.valueOf(zzbb.zzgr());
                if (this.zzacw.isEnabled() && zzeg.zzho() && this.zzacw.zzgh().zzas(zzks.zzti)) {
                    zzks.zzaut = null;
                }
                Pair<String, Boolean> zzbn = this.zzacw.zzgg().zzbn(zzbb.zzah());
                if (zzbb.zzhf() && zzbn != null && !TextUtils.isEmpty((CharSequence) zzbn.first)) {
                    zzks.zzauh = (String) zzbn.first;
                    zzks.zzaui = (Boolean) zzbn.second;
                }
                this.zzacw.zzfx().zzch();
                zzks.zzaud = Build.MODEL;
                this.zzacw.zzfx().zzch();
                zzks.zzauc = Build.VERSION.RELEASE;
                zzks.zzaue = Integer.valueOf((int) this.zzacw.zzfx().zzig());
                zzks.zzafo = this.zzacw.zzfx().zzih();
                zzks.zzadl = zzbb.getAppInstanceId();
                zzks.zzado = zzbb.zzgl();
                List<zzkb> zzba = zzje().zzba(zzbb.zzah());
                zzks.zzatv = new zzku[zzba.size()];
                zzkb zzkb = null;
                if (zzau) {
                    zzkb zzh = zzje().zzh(zzks.zzti, "_lte");
                    zzkb = (zzh == null || zzh.value == null) ? new zzkb(zzks.zzti, "auto", "_lte", this.zzacw.zzbt().currentTimeMillis(), l) : l.longValue() > 0 ? new zzkb(zzks.zzti, "auto", "_lte", this.zzacw.zzbt().currentTimeMillis(), Long.valueOf(((Long) zzh.value).longValue() + l.longValue())) : zzh;
                }
                zzku zzku2 = null;
                int i = 0;
                while (i < zzba.size()) {
                    zzku zzku3 = new zzku();
                    zzks.zzatv[i] = zzku3;
                    zzku3.name = zzba.get(i).name;
                    zzku3.zzauz = Long.valueOf(zzba.get(i).zzarl);
                    zzjc().zza(zzku3, zzba.get(i).value);
                    if (!zzau || !"_lte".equals(zzku3.name)) {
                        zzku = zzku2;
                    } else {
                        zzku3.zzatq = (Long) zzkb.value;
                        zzku3.zzauz = Long.valueOf(this.zzacw.zzbt().currentTimeMillis());
                        zzku = zzku3;
                    }
                    i++;
                    zzku2 = zzku;
                }
                if (zzau && zzku2 == null) {
                    zzku zzku4 = new zzku();
                    zzku4.name = "_lte";
                    zzku4.zzauz = Long.valueOf(this.zzacw.zzbt().currentTimeMillis());
                    zzku4.zzatq = (Long) zzkb.value;
                    zzks.zzatv = (zzku[]) Arrays.copyOf(zzks.zzatv, zzks.zzatv.length + 1);
                    zzks.zzatv[zzks.zzatv.length - 1] = zzku4;
                }
                if (l.longValue() > 0) {
                    zzje().zza(zzkb);
                }
                Bundle zzij = zzew.zzafr.zzij();
                if ("_iap".equals(zzew.name)) {
                    zzij.putLong("_c", 1);
                    this.zzacw.zzgf().zziy().log("Marking in-app purchase as real-time");
                    zzij.putLong("_r", 1);
                }
                zzij.putString("_o", zzew.origin);
                if (this.zzacw.zzgc().zzci(zzks.zzti)) {
                    this.zzacw.zzgc().zza(zzij, "_dbg", (Object) 1L);
                    this.zzacw.zzgc().zza(zzij, "_r", (Object) 1L);
                }
                zzes zzf = zzje().zzf(str, zzew.name);
                if (zzf == null) {
                    zzje().zza(new zzes(str, zzew.name, 1, 0, zzew.zzagc, 0, (Long) null, (Long) null, (Boolean) null));
                    j = 0;
                } else {
                    j = zzf.zzafu;
                    zzje().zza(zzf.zzac(zzew.zzagc).zzii());
                }
                zzer zzer = new zzer(this.zzacw, zzew.origin, str, zzew.name, zzew.zzagc, j, zzij);
                zzkp zzkp = new zzkp();
                zzks.zzatu = new zzkp[]{zzkp};
                zzkp.zzatn = Long.valueOf(zzer.timestamp);
                zzkp.name = zzer.name;
                zzkp.zzato = Long.valueOf(zzer.zzafq);
                zzkp.zzatm = new zzkq[zzer.zzafr.size()];
                Iterator<String> it = zzer.zzafr.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String next = it.next();
                    zzkq zzkq = new zzkq();
                    zzkp.zzatm[i2] = zzkq;
                    zzkq.name = next;
                    zzjc().zza(zzkq, zzer.zzafr.get(next));
                    i2++;
                }
                zzks.zzaum = zza(zzbb.zzah(), zzks.zzatv, zzks.zzatu);
                zzks.zzatx = zzkp.zzatn;
                zzks.zzaty = zzkp.zzatn;
                long zzgn = zzbb.zzgn();
                zzks.zzaua = zzgn != 0 ? Long.valueOf(zzgn) : null;
                long zzgm = zzbb.zzgm();
                if (zzgm != 0) {
                    zzgn = zzgm;
                }
                zzks.zzatz = zzgn != 0 ? Long.valueOf(zzgn) : null;
                zzbb.zzgv();
                zzks.zzauk = Integer.valueOf((int) zzbb.zzgs());
                zzks.zzaug = 12451L;
                zzks.zzatw = Long.valueOf(this.zzacw.zzbt().currentTimeMillis());
                zzks.zzaul = Boolean.TRUE;
                zzbb.zzm(zzks.zzatx.longValue());
                zzbb.zzn(zzks.zzaty.longValue());
                zzje().zza(zzbb);
                zzje().setTransactionSuccessful();
                zzje().endTransaction();
                try {
                    byte[] bArr2 = new byte[zzkr.zzvv()];
                    zzaby zzb = zzaby.zzb(bArr2, 0, bArr2.length);
                    zzkr.zza(zzb);
                    zzb.zzvn();
                    return this.zzacw.zzgc().zza(bArr2);
                } catch (IOException e) {
                    this.zzacw.zzgf().zzis().zze("Data loss. Failed to bundle and serialize. appId", zzfh.zzbl(str), e);
                    return null;
                }
            }
        } finally {
            zzje().endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzee zzee, zzdz zzdz) {
        boolean z = true;
        Preconditions.checkNotNull(zzee);
        Preconditions.checkNotEmpty(zzee.packageName);
        Preconditions.checkNotNull(zzee.origin);
        Preconditions.checkNotNull(zzee.zzaeq);
        Preconditions.checkNotEmpty(zzee.zzaeq.name);
        zzab();
        zzkz();
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            zzee zzee2 = new zzee(zzee);
            zzee2.active = false;
            zzje().beginTransaction();
            try {
                zzee zzi = zzje().zzi(zzee2.packageName, zzee2.zzaeq.name);
                if (zzi != null && !zzi.origin.equals(zzee2.origin)) {
                    this.zzacw.zzgf().zziv().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzacw.zzgb().zzbk(zzee2.zzaeq.name), zzee2.origin, zzi.origin);
                }
                if (zzi != null && zzi.active) {
                    zzee2.origin = zzi.origin;
                    zzee2.creationTimestamp = zzi.creationTimestamp;
                    zzee2.triggerTimeout = zzi.triggerTimeout;
                    zzee2.triggerEventName = zzi.triggerEventName;
                    zzee2.zzaes = zzi.zzaes;
                    zzee2.active = zzi.active;
                    zzee2.zzaeq = new zzjz(zzee2.zzaeq.name, zzi.zzaeq.zzarl, zzee2.zzaeq.getValue(), zzi.zzaeq.origin);
                    z = false;
                } else if (TextUtils.isEmpty(zzee2.triggerEventName)) {
                    zzee2.zzaeq = new zzjz(zzee2.zzaeq.name, zzee2.creationTimestamp, zzee2.zzaeq.getValue(), zzee2.zzaeq.origin);
                    zzee2.active = true;
                } else {
                    z = false;
                }
                if (zzee2.active) {
                    zzjz zzjz = zzee2.zzaeq;
                    zzkb zzkb = new zzkb(zzee2.packageName, zzee2.origin, zzjz.name, zzjz.zzarl, zzjz.getValue());
                    if (zzje().zza(zzkb)) {
                        this.zzacw.zzgf().zziy().zzd("User property updated immediately", zzee2.packageName, this.zzacw.zzgb().zzbk(zzkb.name), zzkb.value);
                    } else {
                        this.zzacw.zzgf().zzis().zzd("(2)Too many active user properties, ignoring", zzfh.zzbl(zzee2.packageName), this.zzacw.zzgb().zzbk(zzkb.name), zzkb.value);
                    }
                    if (z && zzee2.zzaes != null) {
                        zzc(new zzew(zzee2.zzaes, zzee2.creationTimestamp), zzdz);
                    }
                }
                if (zzje().zza(zzee2)) {
                    this.zzacw.zzgf().zziy().zzd("Conditional property added", zzee2.packageName, this.zzacw.zzgb().zzbk(zzee2.zzaeq.name), zzee2.zzaeq.getValue());
                } else {
                    this.zzacw.zzgf().zzis().zzd("Too many conditional properties, ignoring", zzfh.zzbl(zzee2.packageName), this.zzacw.zzgb().zzbk(zzee2.zzaeq.name), zzee2.zzaeq.getValue());
                }
                zzje().setTransactionSuccessful();
            } finally {
                zzje().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzew zzew, zzdz zzdz) {
        List<zzee> zzb;
        List<zzee> zzb2;
        List<zzee> zzb3;
        Preconditions.checkNotNull(zzdz);
        Preconditions.checkNotEmpty(zzdz.packageName);
        zzab();
        zzkz();
        String str = zzdz.packageName;
        long j = zzew.zzagc;
        if (this.zzacw.zzgc().zzd(zzew, zzdz)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            zzje().beginTransaction();
            try {
                zzej zzje = zzje();
                Preconditions.checkNotEmpty(str);
                zzje.zzab();
                zzje.zzch();
                if (j < 0) {
                    zzje.zzgf().zziv().zze("Invalid time querying timed out conditional properties", zzfh.zzbl(str), Long.valueOf(j));
                    zzb = Collections.emptyList();
                } else {
                    zzb = zzje.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzee next : zzb) {
                    if (next != null) {
                        this.zzacw.zzgf().zziy().zzd("User property timed out", next.packageName, this.zzacw.zzgb().zzbk(next.zzaeq.name), next.zzaeq.getValue());
                        if (next.zzaer != null) {
                            zzc(new zzew(next.zzaer, j), zzdz);
                        }
                        zzje().zzj(str, next.zzaeq.name);
                    }
                }
                zzej zzje2 = zzje();
                Preconditions.checkNotEmpty(str);
                zzje2.zzab();
                zzje2.zzch();
                if (j < 0) {
                    zzje2.zzgf().zziv().zze("Invalid time querying expired conditional properties", zzfh.zzbl(str), Long.valueOf(j));
                    zzb2 = Collections.emptyList();
                } else {
                    zzb2 = zzje2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(zzb2.size());
                for (zzee next2 : zzb2) {
                    if (next2 != null) {
                        this.zzacw.zzgf().zziy().zzd("User property expired", next2.packageName, this.zzacw.zzgb().zzbk(next2.zzaeq.name), next2.zzaeq.getValue());
                        zzje().zzg(str, next2.zzaeq.name);
                        if (next2.zzaet != null) {
                            arrayList.add(next2.zzaet);
                        }
                        zzje().zzj(str, next2.zzaeq.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zzc(new zzew((zzew) obj, j), zzdz);
                }
                zzej zzje3 = zzje();
                String str2 = zzew.name;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zzje3.zzab();
                zzje3.zzch();
                if (j < 0) {
                    zzje3.zzgf().zziv().zzd("Invalid time querying triggered conditional properties", zzfh.zzbl(str), zzje3.zzgb().zzbi(str2), Long.valueOf(j));
                    zzb3 = Collections.emptyList();
                } else {
                    zzb3 = zzje3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(zzb3.size());
                for (zzee next3 : zzb3) {
                    if (next3 != null) {
                        zzjz zzjz = next3.zzaeq;
                        zzkb zzkb = new zzkb(next3.packageName, next3.origin, zzjz.name, j, zzjz.getValue());
                        if (zzje().zza(zzkb)) {
                            this.zzacw.zzgf().zziy().zzd("User property triggered", next3.packageName, this.zzacw.zzgb().zzbk(zzkb.name), zzkb.value);
                        } else {
                            this.zzacw.zzgf().zzis().zzd("Too many active user properties, ignoring", zzfh.zzbl(next3.packageName), this.zzacw.zzgb().zzbk(zzkb.name), zzkb.value);
                        }
                        if (next3.zzaes != null) {
                            arrayList3.add(next3.zzaes);
                        }
                        next3.zzaeq = new zzjz(zzkb);
                        next3.active = true;
                        zzje().zza(next3);
                    }
                }
                zzc(zzew, zzdz);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj2 = arrayList4.get(i2);
                    i2++;
                    zzc(new zzew((zzew) obj2, j), zzdz);
                }
                zzje().setTransactionSuccessful();
            } finally {
                zzje().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzjr zzjr) {
        this.zzaqu++;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzjz zzjz, zzdz zzdz) {
        int i = 0;
        zzab();
        zzkz();
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            int zzce = this.zzacw.zzgc().zzce(zzjz.name);
            if (zzce != 0) {
                this.zzacw.zzgc();
                String zza2 = zzkc.zza(zzjz.name, 24, true);
                if (zzjz.name != null) {
                    i = zzjz.name.length();
                }
                this.zzacw.zzgc().zza(zzdz.packageName, zzce, "_ev", zza2, i);
                return;
            }
            int zzi = this.zzacw.zzgc().zzi(zzjz.name, zzjz.getValue());
            if (zzi != 0) {
                this.zzacw.zzgc();
                String zza3 = zzkc.zza(zzjz.name, 24, true);
                Object value = zzjz.getValue();
                if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                    i = String.valueOf(value).length();
                }
                this.zzacw.zzgc().zza(zzdz.packageName, zzi, "_ev", zza3, i);
                return;
            }
            Object zzj = this.zzacw.zzgc().zzj(zzjz.name, zzjz.getValue());
            if (zzj != null) {
                zzkb zzkb = new zzkb(zzdz.packageName, zzjz.origin, zzjz.name, zzjz.zzarl, zzj);
                this.zzacw.zzgf().zziy().zze("Setting user property", this.zzacw.zzgb().zzbk(zzkb.name), zzj);
                zzje().beginTransaction();
                try {
                    zzg(zzdz);
                    boolean zza4 = zzje().zza(zzkb);
                    zzje().setTransactionSuccessful();
                    if (zza4) {
                        this.zzacw.zzgf().zziy().zze("User property set", this.zzacw.zzgb().zzbk(zzkb.name), zzkb.value);
                    } else {
                        this.zzacw.zzgf().zzis().zze("Too many unique user properties are set. Ignoring user property", this.zzacw.zzgb().zzbk(zzkb.name), zzkb.value);
                        this.zzacw.zzgc().zza(zzdz.packageName, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzje().endTransaction();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        boolean z = true;
        zzab();
        zzkz();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzaqw = false;
                zzle();
                throw th2;
            }
        }
        this.zzacw.zzgf().zziz().zzg("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zzje().beginTransaction();
        zzdy zzbb = zzje().zzbb(str);
        boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
        if (zzbb == null) {
            this.zzacw.zzgf().zziv().zzg("App does not exist in onConfigFetched. appId", zzfh.zzbl(str));
        } else if (z2 || i == 404) {
            List list = map != null ? map.get("Last-Modified") : null;
            String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
            if (i == 404 || i == 304) {
                if (zzkv().zzbt(str) == null && !zzkv().zza(str, (byte[]) null, (String) null)) {
                    zzje().endTransaction();
                    this.zzaqw = false;
                    zzle();
                    return;
                }
            } else if (!zzkv().zza(str, bArr, str2)) {
                zzje().endTransaction();
                this.zzaqw = false;
                zzle();
                return;
            }
            zzbb.zzs(this.zzacw.zzbt().currentTimeMillis());
            zzje().zza(zzbb);
            if (i == 404) {
                this.zzacw.zzgf().zziw().zzg("Config not found. Using empty config. appId", str);
            } else {
                this.zzacw.zzgf().zziz().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            }
            if (!zzkw().zzex() || !zzlc()) {
                zzld();
            } else {
                zzlb();
            }
        } else {
            zzbb.zzt(this.zzacw.zzbt().currentTimeMillis());
            zzje().zza(zzbb);
            this.zzacw.zzgf().zziz().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
            zzkv().zzbv(str);
            this.zzacw.zzgg().zzake.set(this.zzacw.zzbt().currentTimeMillis());
            if (!(i == 503 || i == 429)) {
                z = false;
            }
            if (z) {
                this.zzacw.zzgg().zzakf.set(this.zzacw.zzbt().currentTimeMillis());
            }
            zzld();
        }
        zzje().setTransactionSuccessful();
        zzje().endTransaction();
        this.zzaqw = false;
        zzle();
    }

    public final Clock zzbt() {
        return this.zzacw.zzbt();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzee zzee, zzdz zzdz) {
        Preconditions.checkNotNull(zzee);
        Preconditions.checkNotEmpty(zzee.packageName);
        Preconditions.checkNotNull(zzee.zzaeq);
        Preconditions.checkNotEmpty(zzee.zzaeq.name);
        zzab();
        zzkz();
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            zzje().beginTransaction();
            try {
                zzg(zzdz);
                zzee zzi = zzje().zzi(zzee.packageName, zzee.zzaeq.name);
                if (zzi != null) {
                    this.zzacw.zzgf().zziy().zze("Removing conditional user property", zzee.packageName, this.zzacw.zzgb().zzbk(zzee.zzaeq.name));
                    zzje().zzj(zzee.packageName, zzee.zzaeq.name);
                    if (zzi.active) {
                        zzje().zzg(zzee.packageName, zzee.zzaeq.name);
                    }
                    if (zzee.zzaet != null) {
                        Bundle bundle = null;
                        if (zzee.zzaet.zzafr != null) {
                            bundle = zzee.zzaet.zzafr.zzij();
                        }
                        zzc(this.zzacw.zzgc().zza(zzee.zzaet.name, bundle, zzi.origin, zzee.zzaet.zzagc, true, false), zzdz);
                    }
                } else {
                    this.zzacw.zzgf().zziv().zze("Conditional user property doesn't exist", zzfh.zzbl(zzee.packageName), this.zzacw.zzgb().zzbk(zzee.zzaeq.name));
                }
                zzje().setTransactionSuccessful();
            } finally {
                zzje().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzew zzew, String str) {
        zzdy zzbb = zzje().zzbb(str);
        if (zzbb == null || TextUtils.isEmpty(zzbb.zzag())) {
            this.zzacw.zzgf().zziy().zzg("No app data available; dropping event", str);
            return;
        }
        Boolean zzc = zzc(zzbb);
        if (zzc == null) {
            if (!"_ui".equals(zzew.name)) {
                this.zzacw.zzgf().zziv().zzg("Could not find package. appId", zzfh.zzbl(str));
            }
        } else if (!zzc.booleanValue()) {
            this.zzacw.zzgf().zzis().zzg("App version does not match; dropping event. appId", zzfh.zzbl(str));
            return;
        }
        zzew zzew2 = zzew;
        zzb(zzew2, new zzdz(str, zzbb.getGmpAppId(), zzbb.zzag(), zzbb.zzgo(), zzbb.zzgp(), zzbb.zzgq(), zzbb.zzgr(), (String) null, zzbb.isMeasurementEnabled(), false, zzbb.zzgl(), zzbb.zzhe(), 0, 0, zzbb.zzhf(), zzbb.zzhg(), false));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzjz zzjz, zzdz zzdz) {
        zzab();
        zzkz();
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            this.zzacw.zzgf().zziy().zzg("Removing user property", this.zzacw.zzgb().zzbk(zzjz.name));
            zzje().beginTransaction();
            try {
                zzg(zzdz);
                zzje().zzg(zzdz.packageName, zzjz.name);
                zzje().setTransactionSuccessful();
                this.zzacw.zzgf().zziy().zzg("User property removed", this.zzacw.zzgb().zzbk(zzjz.name));
            } finally {
                zzje().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzdz zzca(String str) {
        zzdy zzbb = zzje().zzbb(str);
        if (zzbb == null || TextUtils.isEmpty(zzbb.zzag())) {
            this.zzacw.zzgf().zziy().zzg("No app data available; dropping", str);
            return null;
        }
        Boolean zzc = zzc(zzbb);
        if (zzc == null || zzc.booleanValue()) {
            return new zzdz(str, zzbb.getGmpAppId(), zzbb.zzag(), zzbb.zzgo(), zzbb.zzgp(), zzbb.zzgq(), zzbb.zzgr(), (String) null, zzbb.isMeasurementEnabled(), false, zzbb.zzgl(), zzbb.zzhe(), 0, 0, zzbb.zzhf(), zzbb.zzhg(), false);
        }
        this.zzacw.zzgf().zzis().zzg("App version does not match; dropping. appId", zzfh.zzbl(str));
        return null;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zzd(zzdz zzdz) {
        if (this.zzarb != null) {
            this.zzarc = new ArrayList();
            this.zzarc.addAll(this.zzarb);
        }
        zzej zzje = zzje();
        String str = zzdz.packageName;
        Preconditions.checkNotEmpty(str);
        zzje.zzab();
        zzje.zzch();
        try {
            SQLiteDatabase writableDatabase = zzje.getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("main_event_params", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + 0 + writableDatabase.delete("events", "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr);
            if (delete > 0) {
                zzje.zzgf().zziz().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzje.zzgf().zzis().zze("Error resetting analytics data. appId, error", zzfh.zzbl(str), e);
        }
        zzdz zza2 = zza(this.zzacw.getContext(), zzdz.packageName, zzdz.zzadm, zzdz.zzadw, zzdz.zzady, zzdz.zzadz, zzdz.zzaem);
        if (!this.zzacw.zzgh().zzay(zzdz.packageName) || zzdz.zzadw) {
            zzf(zza2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzdz zzdz) {
        zzab();
        zzkz();
        Preconditions.checkNotEmpty(zzdz.packageName);
        zzg(zzdz);
    }

    @WorkerThread
    public final void zzf(zzdz zzdz) {
        int i;
        zzdy zzbb;
        ApplicationInfo applicationInfo;
        zzej zzje;
        String zzah;
        zzab();
        zzkz();
        Preconditions.checkNotNull(zzdz);
        Preconditions.checkNotEmpty(zzdz.packageName);
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            zzdy zzbb2 = zzje().zzbb(zzdz.packageName);
            if (zzbb2 != null && TextUtils.isEmpty(zzbb2.getGmpAppId()) && !TextUtils.isEmpty(zzdz.zzadm)) {
                zzbb2.zzs(0);
                zzje().zza(zzbb2);
                zzkv().zzbw(zzdz.packageName);
            }
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            long j = zzdz.zzaem;
            if (j == 0) {
                j = this.zzacw.zzbt().currentTimeMillis();
            }
            int i2 = zzdz.zzaen;
            if (i2 == 0 || i2 == 1) {
                i = i2;
            } else {
                this.zzacw.zzgf().zziv().zze("Incorrect app type, assuming installed app. appId, appType", zzfh.zzbl(zzdz.packageName), Integer.valueOf(i2));
                i = 0;
            }
            zzje().beginTransaction();
            try {
                zzbb = zzje().zzbb(zzdz.packageName);
                if (!(zzbb == null || zzbb.getGmpAppId() == null || zzbb.getGmpAppId().equals(zzdz.zzadm))) {
                    this.zzacw.zzgf().zziv().zzg("New GMP App Id passed in. Removing cached database data. appId", zzfh.zzbl(zzbb.zzah()));
                    zzje = zzje();
                    zzah = zzbb.zzah();
                    zzje.zzch();
                    zzje.zzab();
                    Preconditions.checkNotEmpty(zzah);
                    SQLiteDatabase writableDatabase = zzje.getWritableDatabase();
                    String[] strArr = {zzah};
                    int delete = writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("events", "app_id=?", strArr) + 0 + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr);
                    if (delete > 0) {
                        zzje.zzgf().zziz().zze("Deleted application data. app, records", zzah, Integer.valueOf(delete));
                    }
                    zzbb = null;
                }
            } catch (SQLiteException e) {
                zzje.zzgf().zzis().zze("Error deleting application data. appId, error", zzfh.zzbl(zzah), e);
            } catch (Throwable th) {
                zzje().endTransaction();
                throw th;
            }
            if (zzbb != null) {
                if (zzbb.zzgo() != -2147483648L) {
                    if (zzbb.zzgo() != zzdz.zzads) {
                        Bundle bundle = new Bundle();
                        bundle.putString("_pv", zzbb.zzag());
                        zzb(new zzew("_au", new zzet(bundle), "auto", j), zzdz);
                    }
                } else if (zzbb.zzag() != null && !zzbb.zzag().equals(zzdz.zzth)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("_pv", zzbb.zzag());
                    zzb(new zzew("_au", new zzet(bundle2), "auto", j), zzdz);
                }
            }
            zzg(zzdz);
            zzes zzes = null;
            if (i == 0) {
                zzes = zzje().zzf(zzdz.packageName, "_f");
            } else if (i == 1) {
                zzes = zzje().zzf(zzdz.packageName, "_v");
            }
            if (zzes == null) {
                long j2 = (1 + (j / 3600000)) * 3600000;
                if (i == 0) {
                    zzb(new zzjz("_fot", j, Long.valueOf(j2), "auto"), zzdz);
                    zzab();
                    zzkz();
                    Bundle bundle3 = new Bundle();
                    bundle3.putLong("_c", 1);
                    bundle3.putLong("_r", 1);
                    bundle3.putLong("_uwa", 0);
                    bundle3.putLong("_pfo", 0);
                    bundle3.putLong("_sys", 0);
                    bundle3.putLong("_sysu", 0);
                    if (this.zzacw.zzgh().zzay(zzdz.packageName) && zzdz.zzaeo) {
                        bundle3.putLong("_dac", 1);
                    }
                    if (this.zzacw.getContext().getPackageManager() == null) {
                        this.zzacw.zzgf().zzis().zzg("PackageManager is null, first open report might be inaccurate. appId", zzfh.zzbl(zzdz.packageName));
                    } else {
                        PackageInfo packageInfo = null;
                        try {
                            packageInfo = Wrappers.packageManager(this.zzacw.getContext()).getPackageInfo(zzdz.packageName, 0);
                        } catch (PackageManager.NameNotFoundException e2) {
                            this.zzacw.zzgf().zzis().zze("Package info is null, first open report might be inaccurate. appId", zzfh.zzbl(zzdz.packageName), e2);
                        }
                        if (packageInfo != null) {
                            if (packageInfo.firstInstallTime != 0) {
                                boolean z = false;
                                if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                    bundle3.putLong("_uwa", 1);
                                } else {
                                    z = true;
                                }
                                zzb(new zzjz("_fi", j, Long.valueOf(z ? 1 : 0), "auto"), zzdz);
                            }
                        }
                        try {
                            applicationInfo = Wrappers.packageManager(this.zzacw.getContext()).getApplicationInfo(zzdz.packageName, 0);
                        } catch (PackageManager.NameNotFoundException e3) {
                            this.zzacw.zzgf().zzis().zze("Application info is null, first open report might be inaccurate. appId", zzfh.zzbl(zzdz.packageName), e3);
                            applicationInfo = null;
                        }
                        if (applicationInfo != null) {
                            if ((applicationInfo.flags & 1) != 0) {
                                bundle3.putLong("_sys", 1);
                            }
                            if ((applicationInfo.flags & 128) != 0) {
                                bundle3.putLong("_sysu", 1);
                            }
                        }
                    }
                    zzej zzje2 = zzje();
                    String str = zzdz.packageName;
                    Preconditions.checkNotEmpty(str);
                    zzje2.zzab();
                    zzje2.zzch();
                    long zzm = zzje2.zzm(str, "first_open_count");
                    if (zzm >= 0) {
                        bundle3.putLong("_pfo", zzm);
                    }
                    zzb(new zzew("_f", new zzet(bundle3), "auto", j), zzdz);
                } else if (i == 1) {
                    zzb(new zzjz("_fvt", j, Long.valueOf(j2), "auto"), zzdz);
                    zzab();
                    zzkz();
                    Bundle bundle4 = new Bundle();
                    bundle4.putLong("_c", 1);
                    bundle4.putLong("_r", 1);
                    if (this.zzacw.zzgh().zzay(zzdz.packageName) && zzdz.zzaeo) {
                        bundle4.putLong("_dac", 1);
                    }
                    zzb(new zzew("_v", new zzet(bundle4), "auto", j), zzdz);
                }
                Bundle bundle5 = new Bundle();
                bundle5.putLong("_et", 1);
                zzb(new zzew("_e", new zzet(bundle5), "auto", j), zzdz);
            } else if (zzdz.zzael) {
                zzb(new zzew("_cd", new zzet(new Bundle()), "auto", j), zzdz);
            }
            zzje().setTransactionSuccessful();
            zzje().endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzg(Runnable runnable) {
        zzab();
        if (this.zzaqt == null) {
            this.zzaqt = new ArrayList();
        }
        this.zzaqt.add(runnable);
    }

    public final zzff zzgb() {
        return this.zzacw.zzgb();
    }

    public final zzkc zzgc() {
        return this.zzacw.zzgc();
    }

    public final zzgh zzge() {
        return this.zzacw.zzge();
    }

    public final zzfh zzgf() {
        return this.zzacw.zzgf();
    }

    public final zzeg zzgh() {
        return this.zzacw.zzgh();
    }

    public final zzec zzgi() {
        return this.zzacw.zzgi();
    }

    public final String zzh(zzdz zzdz) {
        try {
            return (String) this.zzacw.zzge().zzb(new zzjw(this, zzdz)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzacw.zzgf().zzis().zze("Failed to get app instance id. appId", zzfh.zzbl(zzdz.packageName), e);
            return null;
        }
    }

    public final zzjy zzjc() {
        zza((zzjr) this.zzaqq);
        return this.zzaqq;
    }

    public final zzeb zzjd() {
        zza((zzjr) this.zzaqp);
        return this.zzaqp;
    }

    public final zzej zzje() {
        zza((zzjr) this.zzaqm);
        return this.zzaqm;
    }

    public final zzfl zzkw() {
        zza((zzjr) this.zzaql);
        return this.zzaql;
    }

    /* access modifiers changed from: package-private */
    public final void zzkz() {
        if (!this.zzvo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x02f3  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x019f A[Catch:{ MalformedURLException -> 0x02b0, all -> 0x02c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01a8 A[Catch:{ MalformedURLException -> 0x02b0, all -> 0x02c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01fb A[Catch:{ MalformedURLException -> 0x02b0, all -> 0x02c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x020c A[Catch:{ MalformedURLException -> 0x02b0, all -> 0x02c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0232 A[Catch:{ MalformedURLException -> 0x02b0, all -> 0x02c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x023a A[Catch:{ MalformedURLException -> 0x02b0, all -> 0x02c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x025b A[Catch:{ MalformedURLException -> 0x02b0, all -> 0x02c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02a6  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x02a8 A[SYNTHETIC, Splitter:B:86:0x02a8] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzlb() {
        /*
            r14 = this;
            r14.zzab()
            r14.zzkz()
            r2 = 1
            r14.zzaqy = r2
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            r2.zzgi()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzij r2 = r2.zzfy()     // Catch:{ all -> 0x02c5 }
            java.lang.Boolean r2 = r2.zzko()     // Catch:{ all -> 0x02c5 }
            if (r2 != 0) goto L_0x0030
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziv()     // Catch:{ all -> 0x02c5 }
            java.lang.String r3 = "Upload data called on the client side before use of service was decided"
            r2.log(r3)     // Catch:{ all -> 0x02c5 }
            r2 = 0
            r14.zzaqy = r2
            r14.zzle()
        L_0x002f:
            return
        L_0x0030:
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x02c5 }
            if (r2 == 0) goto L_0x004c
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zzis()     // Catch:{ all -> 0x02c5 }
            java.lang.String r3 = "Upload called in the client side when service should be used"
            r2.log(r3)     // Catch:{ all -> 0x02c5 }
            r2 = 0
            r14.zzaqy = r2
            r14.zzle()
            goto L_0x002f
        L_0x004c:
            long r2 = r14.zzaqs     // Catch:{ all -> 0x02c5 }
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x005e
            r14.zzld()     // Catch:{ all -> 0x02c5 }
            r2 = 0
            r14.zzaqy = r2
            r14.zzle()
            goto L_0x002f
        L_0x005e:
            r14.zzab()     // Catch:{ all -> 0x02c5 }
            java.util.List<java.lang.Long> r2 = r14.zzarb     // Catch:{ all -> 0x02c5 }
            if (r2 == 0) goto L_0x007e
            r2 = 1
        L_0x0066:
            if (r2 == 0) goto L_0x0080
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziz()     // Catch:{ all -> 0x02c5 }
            java.lang.String r3 = "Uploading requested multiple times"
            r2.log(r3)     // Catch:{ all -> 0x02c5 }
            r2 = 0
            r14.zzaqy = r2
            r14.zzle()
            goto L_0x002f
        L_0x007e:
            r2 = 0
            goto L_0x0066
        L_0x0080:
            com.google.android.gms.internal.measurement.zzfl r2 = r14.zzkw()     // Catch:{ all -> 0x02c5 }
            boolean r2 = r2.zzex()     // Catch:{ all -> 0x02c5 }
            if (r2 != 0) goto L_0x00a3
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zziz()     // Catch:{ all -> 0x02c5 }
            java.lang.String r3 = "Network not connected, ignoring upload request"
            r2.log(r3)     // Catch:{ all -> 0x02c5 }
            r14.zzld()     // Catch:{ all -> 0x02c5 }
            r2 = 0
            r14.zzaqy = r2
            r14.zzle()
            goto L_0x002f
        L_0x00a3:
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.common.util.Clock r2 = r2.zzbt()     // Catch:{ all -> 0x02c5 }
            long r10 = r2.currentTimeMillis()     // Catch:{ all -> 0x02c5 }
            long r2 = com.google.android.gms.internal.measurement.zzeg.zzhm()     // Catch:{ all -> 0x02c5 }
            long r2 = r10 - r2
            r4 = 0
            r14.zzd(r4, r2)     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfs r2 = r2.zzgg()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfv r2 = r2.zzakd     // Catch:{ all -> 0x02c5 }
            long r2 = r2.get()     // Catch:{ all -> 0x02c5 }
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x00e2
            com.google.android.gms.internal.measurement.zzgm r4 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfh r4 = r4.zzgf()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zziy()     // Catch:{ all -> 0x02c5 }
            java.lang.String r5 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r2 = r10 - r2
            long r2 = java.lang.Math.abs(r2)     // Catch:{ all -> 0x02c5 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x02c5 }
            r4.zzg(r5, r2)     // Catch:{ all -> 0x02c5 }
        L_0x00e2:
            com.google.android.gms.internal.measurement.zzej r2 = r14.zzje()     // Catch:{ all -> 0x02c5 }
            java.lang.String r4 = r2.zzhr()     // Catch:{ all -> 0x02c5 }
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x02c5 }
            if (r2 != 0) goto L_0x02cd
            long r2 = r14.zzard     // Catch:{ all -> 0x02c5 }
            r6 = -1
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x0102
            com.google.android.gms.internal.measurement.zzej r2 = r14.zzje()     // Catch:{ all -> 0x02c5 }
            long r2 = r2.zzhy()     // Catch:{ all -> 0x02c5 }
            r14.zzard = r2     // Catch:{ all -> 0x02c5 }
        L_0x0102:
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzeg r2 = r2.zzgh()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzey$zza<java.lang.Integer> r3 = com.google.android.gms.internal.measurement.zzey.zzagv     // Catch:{ all -> 0x02c5 }
            int r2 = r2.zzb(r4, r3)     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzgm r3 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzeg r3 = r3.zzgh()     // Catch:{ all -> 0x02c5 }
            r5 = 0
            com.google.android.gms.internal.measurement.zzey$zza<java.lang.Integer> r6 = com.google.android.gms.internal.measurement.zzey.zzagw     // Catch:{ all -> 0x02c5 }
            int r3 = r3.zzb(r4, r6)     // Catch:{ all -> 0x02c5 }
            int r3 = java.lang.Math.max(r5, r3)     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzej r5 = r14.zzje()     // Catch:{ all -> 0x02c5 }
            java.util.List r3 = r5.zzb((java.lang.String) r4, (int) r2, (int) r3)     // Catch:{ all -> 0x02c5 }
            boolean r2 = r3.isEmpty()     // Catch:{ all -> 0x02c5 }
            if (r2 != 0) goto L_0x029e
            r5 = 0
            java.util.Iterator r6 = r3.iterator()     // Catch:{ all -> 0x02c5 }
        L_0x0132:
            boolean r2 = r6.hasNext()     // Catch:{ all -> 0x02c5 }
            if (r2 == 0) goto L_0x02f9
            java.lang.Object r2 = r6.next()     // Catch:{ all -> 0x02c5 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x02c5 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzks r2 = (com.google.android.gms.internal.measurement.zzks) r2     // Catch:{ all -> 0x02c5 }
            java.lang.String r7 = r2.zzauh     // Catch:{ all -> 0x02c5 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x02c5 }
            if (r7 != 0) goto L_0x0132
            java.lang.String r2 = r2.zzauh     // Catch:{ all -> 0x02c5 }
            r6 = r2
        L_0x014d:
            if (r6 == 0) goto L_0x02f6
            r2 = 0
            r5 = r2
        L_0x0151:
            int r2 = r3.size()     // Catch:{ all -> 0x02c5 }
            if (r5 >= r2) goto L_0x02f6
            java.lang.Object r2 = r3.get(r5)     // Catch:{ all -> 0x02c5 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x02c5 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzks r2 = (com.google.android.gms.internal.measurement.zzks) r2     // Catch:{ all -> 0x02c5 }
            java.lang.String r7 = r2.zzauh     // Catch:{ all -> 0x02c5 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x02c5 }
            if (r7 != 0) goto L_0x01f6
            java.lang.String r2 = r2.zzauh     // Catch:{ all -> 0x02c5 }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x02c5 }
            if (r2 != 0) goto L_0x01f6
            r2 = 0
            java.util.List r2 = r3.subList(r2, r5)     // Catch:{ all -> 0x02c5 }
            r6 = r2
        L_0x0177:
            com.google.android.gms.internal.measurement.zzkr r7 = new com.google.android.gms.internal.measurement.zzkr     // Catch:{ all -> 0x02c5 }
            r7.<init>()     // Catch:{ all -> 0x02c5 }
            int r2 = r6.size()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzks[] r2 = new com.google.android.gms.internal.measurement.zzks[r2]     // Catch:{ all -> 0x02c5 }
            r7.zzatr = r2     // Catch:{ all -> 0x02c5 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x02c5 }
            int r2 = r6.size()     // Catch:{ all -> 0x02c5 }
            r8.<init>(r2)     // Catch:{ all -> 0x02c5 }
            boolean r2 = com.google.android.gms.internal.measurement.zzeg.zzho()     // Catch:{ all -> 0x02c5 }
            if (r2 == 0) goto L_0x01fb
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzeg r2 = r2.zzgh()     // Catch:{ all -> 0x02c5 }
            boolean r2 = r2.zzas(r4)     // Catch:{ all -> 0x02c5 }
            if (r2 == 0) goto L_0x01fb
            r2 = 1
            r3 = r2
        L_0x01a1:
            r2 = 0
            r5 = r2
        L_0x01a3:
            com.google.android.gms.internal.measurement.zzks[] r2 = r7.zzatr     // Catch:{ all -> 0x02c5 }
            int r2 = r2.length     // Catch:{ all -> 0x02c5 }
            if (r5 >= r2) goto L_0x01fe
            com.google.android.gms.internal.measurement.zzks[] r9 = r7.zzatr     // Catch:{ all -> 0x02c5 }
            java.lang.Object r2 = r6.get(r5)     // Catch:{ all -> 0x02c5 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x02c5 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzks r2 = (com.google.android.gms.internal.measurement.zzks) r2     // Catch:{ all -> 0x02c5 }
            r9[r5] = r2     // Catch:{ all -> 0x02c5 }
            java.lang.Object r2 = r6.get(r5)     // Catch:{ all -> 0x02c5 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x02c5 }
            java.lang.Object r2 = r2.second     // Catch:{ all -> 0x02c5 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x02c5 }
            r8.add(r2)     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzks[] r2 = r7.zzatr     // Catch:{ all -> 0x02c5 }
            r2 = r2[r5]     // Catch:{ all -> 0x02c5 }
            r12 = 12451(0x30a3, double:6.1516E-320)
            java.lang.Long r9 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x02c5 }
            r2.zzaug = r9     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzks[] r2 = r7.zzatr     // Catch:{ all -> 0x02c5 }
            r2 = r2[r5]     // Catch:{ all -> 0x02c5 }
            java.lang.Long r9 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x02c5 }
            r2.zzatw = r9     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzks[] r2 = r7.zzatr     // Catch:{ all -> 0x02c5 }
            r2 = r2[r5]     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzgm r9 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            r9.zzgi()     // Catch:{ all -> 0x02c5 }
            r9 = 0
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ all -> 0x02c5 }
            r2.zzaul = r9     // Catch:{ all -> 0x02c5 }
            if (r3 != 0) goto L_0x01f2
            com.google.android.gms.internal.measurement.zzks[] r2 = r7.zzatr     // Catch:{ all -> 0x02c5 }
            r2 = r2[r5]     // Catch:{ all -> 0x02c5 }
            r9 = 0
            r2.zzaut = r9     // Catch:{ all -> 0x02c5 }
        L_0x01f2:
            int r2 = r5 + 1
            r5 = r2
            goto L_0x01a3
        L_0x01f6:
            int r2 = r5 + 1
            r5 = r2
            goto L_0x0151
        L_0x01fb:
            r2 = 0
            r3 = r2
            goto L_0x01a1
        L_0x01fe:
            r2 = 0
            com.google.android.gms.internal.measurement.zzgm r3 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfh r3 = r3.zzgf()     // Catch:{ all -> 0x02c5 }
            r5 = 2
            boolean r3 = r3.isLoggable(r5)     // Catch:{ all -> 0x02c5 }
            if (r3 == 0) goto L_0x02f3
            com.google.android.gms.internal.measurement.zzjy r2 = r14.zzjc()     // Catch:{ all -> 0x02c5 }
            java.lang.String r2 = r2.zzb(r7)     // Catch:{ all -> 0x02c5 }
            r3 = r2
        L_0x0215:
            com.google.android.gms.internal.measurement.zzjy r2 = r14.zzjc()     // Catch:{ all -> 0x02c5 }
            byte[] r6 = r2.zza((com.google.android.gms.internal.measurement.zzkr) r7)     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzey$zza<java.lang.String> r2 = com.google.android.gms.internal.measurement.zzey.zzahf     // Catch:{ all -> 0x02c5 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x02c5 }
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x02c5 }
            r9 = r0
            java.net.URL r5 = new java.net.URL     // Catch:{ MalformedURLException -> 0x02b0 }
            r5.<init>(r9)     // Catch:{ MalformedURLException -> 0x02b0 }
            boolean r2 = r8.isEmpty()     // Catch:{ MalformedURLException -> 0x02b0 }
            if (r2 != 0) goto L_0x02a6
            r2 = 1
        L_0x0233:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)     // Catch:{ MalformedURLException -> 0x02b0 }
            java.util.List<java.lang.Long> r2 = r14.zzarb     // Catch:{ MalformedURLException -> 0x02b0 }
            if (r2 == 0) goto L_0x02a8
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zzis()     // Catch:{ MalformedURLException -> 0x02b0 }
            java.lang.String r8 = "Set uploading progress before finishing the previous upload"
            r2.log(r8)     // Catch:{ MalformedURLException -> 0x02b0 }
        L_0x0249:
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzfs r2 = r2.zzgg()     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzfv r2 = r2.zzake     // Catch:{ MalformedURLException -> 0x02b0 }
            r2.set(r10)     // Catch:{ MalformedURLException -> 0x02b0 }
            java.lang.String r2 = "?"
            com.google.android.gms.internal.measurement.zzks[] r8 = r7.zzatr     // Catch:{ MalformedURLException -> 0x02b0 }
            int r8 = r8.length     // Catch:{ MalformedURLException -> 0x02b0 }
            if (r8 <= 0) goto L_0x0262
            com.google.android.gms.internal.measurement.zzks[] r2 = r7.zzatr     // Catch:{ MalformedURLException -> 0x02b0 }
            r7 = 0
            r2 = r2[r7]     // Catch:{ MalformedURLException -> 0x02b0 }
            java.lang.String r2 = r2.zzti     // Catch:{ MalformedURLException -> 0x02b0 }
        L_0x0262:
            com.google.android.gms.internal.measurement.zzgm r7 = r14.zzacw     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzfh r7 = r7.zzgf()     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzfj r7 = r7.zziz()     // Catch:{ MalformedURLException -> 0x02b0 }
            java.lang.String r8 = "Uploading data. app, uncompressed size, data"
            int r10 = r6.length     // Catch:{ MalformedURLException -> 0x02b0 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ MalformedURLException -> 0x02b0 }
            r7.zzd(r8, r2, r10, r3)     // Catch:{ MalformedURLException -> 0x02b0 }
            r2 = 1
            r14.zzaqx = r2     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzfl r3 = r14.zzkw()     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzju r8 = new com.google.android.gms.internal.measurement.zzju     // Catch:{ MalformedURLException -> 0x02b0 }
            r8.<init>(r14, r4)     // Catch:{ MalformedURLException -> 0x02b0 }
            r3.zzab()     // Catch:{ MalformedURLException -> 0x02b0 }
            r3.zzch()     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzgh r10 = r3.zzge()     // Catch:{ MalformedURLException -> 0x02b0 }
            com.google.android.gms.internal.measurement.zzfp r2 = new com.google.android.gms.internal.measurement.zzfp     // Catch:{ MalformedURLException -> 0x02b0 }
            r7 = 0
            r2.<init>(r3, r4, r5, r6, r7, r8)     // Catch:{ MalformedURLException -> 0x02b0 }
            r10.zzd((java.lang.Runnable) r2)     // Catch:{ MalformedURLException -> 0x02b0 }
        L_0x029e:
            r2 = 0
            r14.zzaqy = r2
            r14.zzle()
            goto L_0x002f
        L_0x02a6:
            r2 = 0
            goto L_0x0233
        L_0x02a8:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x02b0 }
            r2.<init>(r8)     // Catch:{ MalformedURLException -> 0x02b0 }
            r14.zzarb = r2     // Catch:{ MalformedURLException -> 0x02b0 }
            goto L_0x0249
        L_0x02b0:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzgm r2 = r14.zzacw     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfh r2 = r2.zzgf()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzfj r2 = r2.zzis()     // Catch:{ all -> 0x02c5 }
            java.lang.String r3 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfh.zzbl(r4)     // Catch:{ all -> 0x02c5 }
            r2.zze(r3, r4, r9)     // Catch:{ all -> 0x02c5 }
            goto L_0x029e
        L_0x02c5:
            r2 = move-exception
            r3 = 0
            r14.zzaqy = r3
            r14.zzle()
            throw r2
        L_0x02cd:
            r2 = -1
            r14.zzard = r2     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzej r2 = r14.zzje()     // Catch:{ all -> 0x02c5 }
            long r4 = com.google.android.gms.internal.measurement.zzeg.zzhm()     // Catch:{ all -> 0x02c5 }
            long r4 = r10 - r4
            java.lang.String r2 = r2.zzab(r4)     // Catch:{ all -> 0x02c5 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x02c5 }
            if (r3 != 0) goto L_0x029e
            com.google.android.gms.internal.measurement.zzej r3 = r14.zzje()     // Catch:{ all -> 0x02c5 }
            com.google.android.gms.internal.measurement.zzdy r2 = r3.zzbb(r2)     // Catch:{ all -> 0x02c5 }
            if (r2 == 0) goto L_0x029e
            r14.zzb((com.google.android.gms.internal.measurement.zzdy) r2)     // Catch:{ all -> 0x02c5 }
            goto L_0x029e
        L_0x02f3:
            r3 = r2
            goto L_0x0215
        L_0x02f6:
            r6 = r3
            goto L_0x0177
        L_0x02f9:
            r6 = r5
            goto L_0x014d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjs.zzlb():void");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzlg() {
        zzab();
        zzkz();
        if (!this.zzaqr) {
            this.zzacw.zzgf().zzix().log("This instance being marked as an uploader");
            zzab();
            zzkz();
            if (zzlh() && zzlf()) {
                int zza2 = zza(this.zzara);
                int zzip = this.zzacw.zzfw().zzip();
                zzab();
                if (zza2 > zzip) {
                    this.zzacw.zzgf().zzis().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzip));
                } else if (zza2 < zzip) {
                    if (zza(zzip, this.zzara)) {
                        this.zzacw.zzgf().zziz().zze("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzip));
                    } else {
                        this.zzacw.zzgf().zzis().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzip));
                    }
                }
            }
            this.zzaqr = true;
            zzld();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzli() {
        this.zzaqv++;
    }

    /* access modifiers changed from: package-private */
    public final zzgm zzlj() {
        return this.zzacw;
    }

    public final void zzm(boolean z) {
        zzld();
    }
}
