package com.google.android.gms.internal.gtm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.HashMap;
import java.util.Map;

final class zzbb extends zzan {
    private boolean started;
    private final zzay zzxp;
    private final zzck zzxq;
    private final zzcj zzxr;
    private final zzat zzxs;
    private long zzxt = Long.MIN_VALUE;
    private final zzbs zzxu;
    private final zzbs zzxv;
    private final zzcv zzxw;
    private long zzxx;
    private boolean zzxy;

    protected zzbb(zzap zzap, zzar zzar) {
        super(zzap);
        Preconditions.checkNotNull(zzar);
        this.zzxr = new zzcj(zzap);
        this.zzxp = new zzay(zzap);
        this.zzxq = new zzck(zzap);
        this.zzxs = new zzat(zzap);
        this.zzxw = new zzcv(zzcn());
        this.zzxu = new zzbc(this, zzap);
        this.zzxv = new zzbd(this, zzap);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() {
        this.zzxp.zzag();
        this.zzxq.zzag();
        this.zzxs.zzag();
    }

    /* access modifiers changed from: package-private */
    public final void start() {
        zzdb();
        Preconditions.checkState(!this.started, "Analytics backend already started");
        this.started = true;
        zzcq().zza((Runnable) new zzbe(this));
    }

    private final boolean zzx(String str) {
        return Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    /* access modifiers changed from: protected */
    public final void zzdw() {
        zzdb();
        zzk.zzav();
        Context context = zzcm().getContext();
        if (!zzcp.zza(context)) {
            zzt("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzcq.zze(context)) {
            zzu("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zza(context)) {
            zzt("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzcv().zzfv();
        if (!zzx("android.permission.ACCESS_NETWORK_STATE")) {
            zzu("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzeg();
        }
        if (!zzx("android.permission.INTERNET")) {
            zzu("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzeg();
        }
        if (zzcq.zze(getContext())) {
            zzq("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzt("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzxy && !this.zzxp.isEmpty()) {
            zzdz();
        }
        zzec();
    }

    /* access modifiers changed from: private */
    public final void zzdx() {
        zzb((zzbw) new zzbf(this));
    }

    /* access modifiers changed from: package-private */
    public final void zzcl() {
        zzk.zzav();
        this.zzxx = zzcn().currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a A[LOOP:1: B:18:0x005a->B:17:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0040 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected() {
        /*
            r6 = this;
            com.google.android.gms.analytics.zzk.zzav()
            com.google.android.gms.analytics.zzk.zzav()
            r6.zzdb()
            boolean r0 = com.google.android.gms.internal.gtm.zzbq.zzen()
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService"
            r6.zzt(r0)
        L_0x0014:
            com.google.android.gms.internal.gtm.zzat r0 = r6.zzxs
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "Service not connected"
            r6.zzq(r0)
        L_0x0021:
            return
        L_0x0022:
            com.google.android.gms.internal.gtm.zzay r0 = r6.zzxp
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0021
            java.lang.String r0 = "Dispatching local hits to device AnalyticsService"
            r6.zzq(r0)
        L_0x002f:
            com.google.android.gms.internal.gtm.zzay r0 = r6.zzxp     // Catch:{ SQLiteException -> 0x0044 }
            int r1 = com.google.android.gms.internal.gtm.zzbq.zzer()     // Catch:{ SQLiteException -> 0x0044 }
            long r2 = (long) r1     // Catch:{ SQLiteException -> 0x0044 }
            java.util.List r1 = r0.zzd(r2)     // Catch:{ SQLiteException -> 0x0044 }
            boolean r0 = r1.isEmpty()     // Catch:{ SQLiteException -> 0x0044 }
            if (r0 == 0) goto L_0x005a
            r6.zzec()     // Catch:{ SQLiteException -> 0x0044 }
            goto L_0x0021
        L_0x0044:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from store"
            r6.zze(r1, r0)
            r6.zzee()
            goto L_0x0021
        L_0x004e:
            r1.remove(r0)
            com.google.android.gms.internal.gtm.zzay r2 = r6.zzxp     // Catch:{ SQLiteException -> 0x0073 }
            long r4 = r0.zzfg()     // Catch:{ SQLiteException -> 0x0073 }
            r2.zze(r4)     // Catch:{ SQLiteException -> 0x0073 }
        L_0x005a:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x002f
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.gtm.zzcd r0 = (com.google.android.gms.internal.gtm.zzcd) r0
            com.google.android.gms.internal.gtm.zzat r2 = r6.zzxs
            boolean r2 = r2.zzb((com.google.android.gms.internal.gtm.zzcd) r0)
            if (r2 != 0) goto L_0x004e
            r6.zzec()
            goto L_0x0021
        L_0x0073:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r6.zze(r1, r0)
            r6.zzee()
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzbb.onServiceConnected():void");
    }

    /* access modifiers changed from: private */
    public final void zzdy() {
        try {
            this.zzxp.zzdr();
            zzec();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzxv.zzh(86400000);
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzas zzas) {
        zzk.zzav();
        zzb("Sending first hit to property", zzas.zzdj());
        if (!zzcv().zzfw().zzj(zzbq.zzex())) {
            String zzfz = zzcv().zzfz();
            if (!TextUtils.isEmpty(zzfz)) {
                zzr zza = zzcz.zza(zzco(), zzfz);
                zzb("Found relevant installation campaign", zza);
                zza(zzas, zza);
            }
        }
    }

    public final void zzg(long j) {
        zzk.zzav();
        zzdb();
        if (j < 0) {
            j = 0;
        }
        this.zzxt = j;
        zzec();
    }

    private final void zzdz() {
        if (!this.zzxy && zzbq.zzen() && !this.zzxs.isConnected()) {
            if (this.zzxw.zzj(zzby.zzaan.get().longValue())) {
                this.zzxw.start();
                zzq("Connecting to service");
                if (this.zzxs.connect()) {
                    zzq("Connected to service");
                    this.zzxw.clear();
                    onServiceConnected();
                }
            }
        }
    }

    public final long zza(zzas zzas, boolean z) {
        String str;
        Preconditions.checkNotNull(zzas);
        zzdb();
        zzk.zzav();
        try {
            this.zzxp.beginTransaction();
            zzay zzay = this.zzxp;
            long zzdi = zzas.zzdi();
            String zzbt = zzas.zzbt();
            Preconditions.checkNotEmpty(zzbt);
            zzay.zzdb();
            zzk.zzav();
            int delete = zzay.getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(zzdi), zzbt});
            if (delete > 0) {
                zzay.zza("Deleted property records", Integer.valueOf(delete));
            }
            long zza = this.zzxp.zza(zzas.zzdi(), zzas.zzbt(), zzas.zzdj());
            zzas.zzb(1 + zza);
            zzay zzay2 = this.zzxp;
            Preconditions.checkNotNull(zzas);
            zzay2.zzdb();
            zzk.zzav();
            SQLiteDatabase writableDatabase = zzay2.getWritableDatabase();
            Map<String, String> zzdm = zzas.zzdm();
            Preconditions.checkNotNull(zzdm);
            Uri.Builder builder = new Uri.Builder();
            for (Map.Entry next : zzdm.entrySet()) {
                builder.appendQueryParameter((String) next.getKey(), (String) next.getValue());
            }
            String encodedQuery = builder.build().getEncodedQuery();
            if (encodedQuery == null) {
                str = "";
            } else {
                str = encodedQuery;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_uid", Long.valueOf(zzas.zzdi()));
            contentValues.put("cid", zzas.zzbt());
            contentValues.put("tid", zzas.zzdj());
            contentValues.put("adid", Integer.valueOf(zzas.zzdk() ? 1 : 0));
            contentValues.put("hits_count", Long.valueOf(zzas.zzdl()));
            contentValues.put("params", str);
            try {
                if (writableDatabase.insertWithOnConflict("properties", (String) null, contentValues, 5) == -1) {
                    zzay2.zzu("Failed to insert/update a property (got -1)");
                }
            } catch (SQLiteException e) {
                zzay2.zze("Error storing a property", e);
            }
            this.zzxp.setTransactionSuccessful();
            try {
                this.zzxp.endTransaction();
            } catch (SQLiteException e2) {
                zze("Failed to end transaction", e2);
            }
            return zza;
        } catch (SQLiteException e3) {
            zze("Failed to update Analytics property", e3);
            try {
                this.zzxp.endTransaction();
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
            }
            return -1;
        } catch (Throwable th) {
            try {
                this.zzxp.endTransaction();
            } catch (SQLiteException e5) {
                zze("Failed to end transaction", e5);
            }
            throw th;
        }
    }

    public final void zza(zzcd zzcd) {
        Pair<String, Long> zzgc;
        Preconditions.checkNotNull(zzcd);
        zzk.zzav();
        zzdb();
        if (this.zzxy) {
            zzr("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzcd);
        }
        if (TextUtils.isEmpty(zzcd.zzfl()) && (zzgc = zzcv().zzga().zzgc()) != null) {
            String str = (String) zzgc.first;
            String valueOf = String.valueOf((Long) zzgc.second);
            String sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
            HashMap hashMap = new HashMap(zzcd.zzdm());
            hashMap.put("_m", sb);
            zzcd = new zzcd(this, hashMap, zzcd.zzfh(), zzcd.zzfj(), zzcd.zzfg(), zzcd.zzff(), zzcd.zzfi());
        }
        zzdz();
        if (this.zzxs.zzb(zzcd)) {
            zzr("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzxp.zzc(zzcd);
            zzec();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zzco().zza(zzcd, "deliver: failed to insert hit to database");
        }
    }

    public final void zzch() {
        zzk.zzav();
        zzdb();
        zzq("Delete all hits from local store");
        try {
            zzay zzay = this.zzxp;
            zzk.zzav();
            zzay.zzdb();
            zzay.getWritableDatabase().delete("hits2", (String) null, (String[]) null);
            zzay zzay2 = this.zzxp;
            zzk.zzav();
            zzay2.zzdb();
            zzay2.getWritableDatabase().delete("properties", (String) null, (String[]) null);
            zzec();
        } catch (SQLiteException e) {
            zzd("Failed to delete hits from store", e);
        }
        zzdz();
        if (this.zzxs.zzdn()) {
            zzq("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e7, code lost:
        if (r12.zzxs.isConnected() == false) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e9, code lost:
        zzq("Service connected, sending hits to the service");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f2, code lost:
        if (r8.isEmpty() != false) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f4, code lost:
        r0 = r8.get(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0101, code lost:
        if (r12.zzxs.zzb(r0) == false) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0103, code lost:
        r4 = java.lang.Math.max(r4, r0.zzfg());
        r8.remove(r0);
        zzb("Hit sent do device AnalyticsService for delivery", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r12.zzxp.zze(r0.zzfg());
        r3.add(java.lang.Long.valueOf(r0.zzfg()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0128, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        zze("Failed to remove hit that was send for delivery", r0);
        zzee();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r12.zzxp.setTransactionSuccessful();
        r12.zzxp.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x013d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x013e, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzee();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0148, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x014f, code lost:
        if (r12.zzxq.zzfr() == false) goto L_0x017a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0151, code lost:
        r8 = r12.zzxq.zzb(r8);
        r9 = r8.iterator();
        r4 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0160, code lost:
        if (r9.hasNext() == false) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0162, code lost:
        r4 = java.lang.Math.max(r4, r9.next().longValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r12.zzxp.zza(r8);
        r3.addAll(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0179, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x017e, code lost:
        if (r3.isEmpty() == false) goto L_0x01b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r12.zzxp.setTransactionSuccessful();
        r12.zzxp.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x018c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x018d, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzee();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0197, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        zze("Failed to remove successfully uploaded hits", r0);
        zzee();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r12.zzxp.setTransactionSuccessful();
        r12.zzxp.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01ac, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01ad, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzee();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        r12.zzxp.setTransactionSuccessful();
        r12.zzxp.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01c4, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01c5, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzee();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzea() {
        /*
            r12 = this;
            r1 = 1
            r2 = 0
            com.google.android.gms.analytics.zzk.zzav()
            r12.zzdb()
            java.lang.String r0 = "Dispatching a batch of local hits"
            r12.zzq(r0)
            com.google.android.gms.internal.gtm.zzat r0 = r12.zzxs
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0028
            r0 = r1
        L_0x0016:
            com.google.android.gms.internal.gtm.zzck r3 = r12.zzxq
            boolean r3 = r3.zzfr()
            if (r3 != 0) goto L_0x002a
        L_0x001e:
            if (r0 == 0) goto L_0x002c
            if (r1 == 0) goto L_0x002c
            java.lang.String r0 = "No network or service available. Will retry later"
            r12.zzq(r0)
        L_0x0027:
            return r2
        L_0x0028:
            r0 = r2
            goto L_0x0016
        L_0x002a:
            r1 = r2
            goto L_0x001e
        L_0x002c:
            int r0 = com.google.android.gms.internal.gtm.zzbq.zzer()
            int r1 = com.google.android.gms.internal.gtm.zzbq.zzes()
            int r0 = java.lang.Math.max(r0, r1)
            long r6 = (long) r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
        L_0x0040:
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ all -> 0x01cf }
            r0.beginTransaction()     // Catch:{ all -> 0x01cf }
            r3.clear()     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x00c1 }
            java.util.List r8 = r0.zzd(r6)     // Catch:{ SQLiteException -> 0x00c1 }
            boolean r0 = r8.isEmpty()     // Catch:{ SQLiteException -> 0x00c1 }
            if (r0 == 0) goto L_0x0071
            java.lang.String r0 = "Store is empty, nothing to dispatch"
            r12.zzq(r0)     // Catch:{ SQLiteException -> 0x00c1 }
            r12.zzee()     // Catch:{ SQLiteException -> 0x00c1 }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x0067 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x0067 }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x0067 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x0067 }
            goto L_0x0027
        L_0x0067:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzee()
            goto L_0x0027
        L_0x0071:
            java.lang.String r0 = "Hits loaded from store. count"
            int r1 = r8.size()     // Catch:{ SQLiteException -> 0x00c1 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x00c1 }
            r12.zza(r0, r1)     // Catch:{ SQLiteException -> 0x00c1 }
            java.util.Iterator r1 = r8.iterator()     // Catch:{ all -> 0x01cf }
        L_0x0082:
            boolean r0 = r1.hasNext()     // Catch:{ all -> 0x01cf }
            if (r0 == 0) goto L_0x00e1
            java.lang.Object r0 = r1.next()     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzcd r0 = (com.google.android.gms.internal.gtm.zzcd) r0     // Catch:{ all -> 0x01cf }
            long r10 = r0.zzfg()     // Catch:{ all -> 0x01cf }
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0082
            java.lang.String r0 = "Database contains successfully uploaded hit"
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x01cf }
            int r3 = r8.size()     // Catch:{ all -> 0x01cf }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01cf }
            r12.zzd(r0, r1, r3)     // Catch:{ all -> 0x01cf }
            r12.zzee()     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x00b6 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x00b6 }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x00b6 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x00b6 }
            goto L_0x0027
        L_0x00b6:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzee()
            goto L_0x0027
        L_0x00c1:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from persisted store"
            r12.zzd(r1, r0)     // Catch:{ all -> 0x01cf }
            r12.zzee()     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x00d6 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x00d6 }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x00d6 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x00d6 }
            goto L_0x0027
        L_0x00d6:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzee()
            goto L_0x0027
        L_0x00e1:
            com.google.android.gms.internal.gtm.zzat r0 = r12.zzxs     // Catch:{ all -> 0x01cf }
            boolean r0 = r0.isConnected()     // Catch:{ all -> 0x01cf }
            if (r0 == 0) goto L_0x0148
            java.lang.String r0 = "Service connected, sending hits to the service"
            r12.zzq(r0)     // Catch:{ all -> 0x01cf }
        L_0x00ee:
            boolean r0 = r8.isEmpty()     // Catch:{ all -> 0x01cf }
            if (r0 != 0) goto L_0x0148
            r0 = 0
            java.lang.Object r0 = r8.get(r0)     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzcd r0 = (com.google.android.gms.internal.gtm.zzcd) r0     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzat r1 = r12.zzxs     // Catch:{ all -> 0x01cf }
            boolean r1 = r1.zzb((com.google.android.gms.internal.gtm.zzcd) r0)     // Catch:{ all -> 0x01cf }
            if (r1 == 0) goto L_0x0148
            long r10 = r0.zzfg()     // Catch:{ all -> 0x01cf }
            long r4 = java.lang.Math.max(r4, r10)     // Catch:{ all -> 0x01cf }
            r8.remove(r0)     // Catch:{ all -> 0x01cf }
            java.lang.String r1 = "Hit sent do device AnalyticsService for delivery"
            r12.zzb(r1, r0)     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzay r1 = r12.zzxp     // Catch:{ SQLiteException -> 0x0128 }
            long r10 = r0.zzfg()     // Catch:{ SQLiteException -> 0x0128 }
            r1.zze(r10)     // Catch:{ SQLiteException -> 0x0128 }
            long r0 = r0.zzfg()     // Catch:{ SQLiteException -> 0x0128 }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ SQLiteException -> 0x0128 }
            r3.add(r0)     // Catch:{ SQLiteException -> 0x0128 }
            goto L_0x00ee
        L_0x0128:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r12.zze(r1, r0)     // Catch:{ all -> 0x01cf }
            r12.zzee()     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x013d }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x013d }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x013d }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x013d }
            goto L_0x0027
        L_0x013d:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzee()
            goto L_0x0027
        L_0x0148:
            r0 = r4
            com.google.android.gms.internal.gtm.zzck r4 = r12.zzxq     // Catch:{ all -> 0x01cf }
            boolean r4 = r4.zzfr()     // Catch:{ all -> 0x01cf }
            if (r4 == 0) goto L_0x017a
            com.google.android.gms.internal.gtm.zzck r4 = r12.zzxq     // Catch:{ all -> 0x01cf }
            java.util.List r8 = r4.zzb((java.util.List<com.google.android.gms.internal.gtm.zzcd>) r8)     // Catch:{ all -> 0x01cf }
            java.util.Iterator r9 = r8.iterator()     // Catch:{ all -> 0x01cf }
            r4 = r0
        L_0x015c:
            boolean r0 = r9.hasNext()     // Catch:{ all -> 0x01cf }
            if (r0 == 0) goto L_0x0171
            java.lang.Object r0 = r9.next()     // Catch:{ all -> 0x01cf }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x01cf }
            long r0 = r0.longValue()     // Catch:{ all -> 0x01cf }
            long r4 = java.lang.Math.max(r4, r0)     // Catch:{ all -> 0x01cf }
            goto L_0x015c
        L_0x0171:
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x0197 }
            r0.zza((java.util.List<java.lang.Long>) r8)     // Catch:{ SQLiteException -> 0x0197 }
            r3.addAll(r8)     // Catch:{ SQLiteException -> 0x0197 }
            r0 = r4
        L_0x017a:
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x01cf }
            if (r4 == 0) goto L_0x01b7
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x018c }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x018c }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x018c }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x018c }
            goto L_0x0027
        L_0x018c:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzee()
            goto L_0x0027
        L_0x0197:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove successfully uploaded hits"
            r12.zze(r1, r0)     // Catch:{ all -> 0x01cf }
            r12.zzee()     // Catch:{ all -> 0x01cf }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x01ac }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01ac }
            com.google.android.gms.internal.gtm.zzay r0 = r12.zzxp     // Catch:{ SQLiteException -> 0x01ac }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x01ac }
            goto L_0x0027
        L_0x01ac:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzee()
            goto L_0x0027
        L_0x01b7:
            com.google.android.gms.internal.gtm.zzay r4 = r12.zzxp     // Catch:{ SQLiteException -> 0x01c4 }
            r4.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01c4 }
            com.google.android.gms.internal.gtm.zzay r4 = r12.zzxp     // Catch:{ SQLiteException -> 0x01c4 }
            r4.endTransaction()     // Catch:{ SQLiteException -> 0x01c4 }
            r4 = r0
            goto L_0x0040
        L_0x01c4:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzee()
            goto L_0x0027
        L_0x01cf:
            r0 = move-exception
            com.google.android.gms.internal.gtm.zzay r1 = r12.zzxp     // Catch:{ SQLiteException -> 0x01db }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01db }
            com.google.android.gms.internal.gtm.zzay r1 = r12.zzxp     // Catch:{ SQLiteException -> 0x01db }
            r1.endTransaction()     // Catch:{ SQLiteException -> 0x01db }
            throw r0
        L_0x01db:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzee()
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzbb.zzea():boolean");
    }

    public final void zzb(zzbw zzbw) {
        long j = this.zzxx;
        zzk.zzav();
        zzdb();
        long j2 = -1;
        long zzfx = zzcv().zzfx();
        if (zzfx != 0) {
            j2 = Math.abs(zzcn().currentTimeMillis() - zzfx);
        }
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(j2));
        zzdz();
        try {
            zzea();
            zzcv().zzfy();
            zzec();
            if (zzbw != null) {
                zzbw.zza((Throwable) null);
            }
            if (this.zzxx != j) {
                this.zzxr.zzfq();
            }
        } catch (Exception e) {
            zze("Local dispatch failed", e);
            zzcv().zzfy();
            zzec();
            if (zzbw != null) {
                zzbw.zza(e);
            }
        }
    }

    public final void zzeb() {
        zzk.zzav();
        zzdb();
        zzr("Sync dispatching local hits");
        long j = this.zzxx;
        zzdz();
        try {
            zzea();
            zzcv().zzfy();
            zzec();
            if (this.zzxx != j) {
                this.zzxr.zzfq();
            }
        } catch (Exception e) {
            zze("Sync local dispatch failed", e);
            zzec();
        }
    }

    private final long zzds() {
        zzk.zzav();
        zzdb();
        try {
            return this.zzxp.zzds();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    public final void zzec() {
        boolean z;
        long min;
        zzk.zzav();
        zzdb();
        if (!(!this.zzxy && zzef() > 0)) {
            this.zzxr.unregister();
            zzee();
        } else if (this.zzxp.isEmpty()) {
            this.zzxr.unregister();
            zzee();
        } else {
            if (!zzby.zzaai.get().booleanValue()) {
                this.zzxr.zzfo();
                z = this.zzxr.isConnected();
            } else {
                z = true;
            }
            if (z) {
                zzed();
                long zzef = zzef();
                long zzfx = zzcv().zzfx();
                if (zzfx != 0) {
                    min = zzef - Math.abs(zzcn().currentTimeMillis() - zzfx);
                    if (min <= 0) {
                        min = Math.min(zzbq.zzep(), zzef);
                    }
                } else {
                    min = Math.min(zzbq.zzep(), zzef);
                }
                zza("Dispatch scheduled (ms)", Long.valueOf(min));
                if (this.zzxu.zzez()) {
                    this.zzxu.zzi(Math.max(1, min + this.zzxu.zzey()));
                } else {
                    this.zzxu.zzh(min);
                }
            } else {
                zzee();
                zzed();
            }
        }
    }

    private final void zzed() {
        zzbv zzct = zzct();
        if (zzct.zzfc() && !zzct.zzez()) {
            long zzds = zzds();
            if (zzds != 0 && Math.abs(zzcn().currentTimeMillis() - zzds) <= zzby.zzzm.get().longValue()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzbq.zzeq()));
                zzct.zzfd();
            }
        }
    }

    private final void zzee() {
        if (this.zzxu.zzez()) {
            zzq("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzxu.cancel();
        zzbv zzct = zzct();
        if (zzct.zzez()) {
            zzct.cancel();
        }
    }

    private final long zzef() {
        if (this.zzxt != Long.MIN_VALUE) {
            return this.zzxt;
        }
        long longValue = zzby.zzzh.get().longValue();
        zzda zzcu = zzcu();
        zzcu.zzdb();
        if (!zzcu.zzacv) {
            return longValue;
        }
        zzda zzcu2 = zzcu();
        zzcu2.zzdb();
        return ((long) zzcu2.zzaax) * 1000;
    }

    public final void zzy(String str) {
        Preconditions.checkNotEmpty(str);
        zzk.zzav();
        zzr zza = zzcz.zza(zzco(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzfz = zzcv().zzfz();
        if (str.equals(zzfz)) {
            zzt("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzfz)) {
            zzd("Ignoring multiple install campaigns. original, new", zzfz, str);
        } else {
            zzcv().zzad(str);
            if (zzcv().zzfw().zzj(zzbq.zzex())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzas zza2 : this.zzxp.zzf(0)) {
                zza(zza2, zza);
            }
        }
    }

    private final void zza(zzas zzas, zzr zzr) {
        Preconditions.checkNotNull(zzas);
        Preconditions.checkNotNull(zzr);
        zza zza = new zza(zzcm());
        zza.zza(zzas.zzdj());
        zza.enableAdvertisingIdCollection(zzas.zzdk());
        zzg zzac = zza.zzac();
        zzz zzz = (zzz) zzac.zzb(zzz.class);
        zzz.zzl(PushConstants.PARSE_COM_DATA);
        zzz.zzb(true);
        zzac.zza((zzi) zzr);
        zzu zzu = (zzu) zzac.zzb(zzu.class);
        zzq zzq = (zzq) zzac.zzb(zzq.class);
        for (Map.Entry next : zzas.zzdm().entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if ("an".equals(str)) {
                zzq.setAppName(str2);
            } else if ("av".equals(str)) {
                zzq.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                zzq.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzq.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                zzz.setUserId(str2);
            } else {
                zzu.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzas.zzdj(), zzr);
        zzac.zza(zzcv().zzfv());
        zzac.zzam();
    }

    private final void zzeg() {
        zzdb();
        zzk.zzav();
        this.zzxy = true;
        this.zzxs.disconnect();
        zzec();
    }
}
