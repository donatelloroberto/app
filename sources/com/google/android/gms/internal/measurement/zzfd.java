package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzfd extends zzhi {
    private final zzfe zzaip = new zzfe(this, getContext(), "google_app_measurement_local.db");
    private boolean zzaiq;

    zzfd(zzgm zzgm) {
        super(zzgm);
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzaiq) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzaip.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzaiq = true;
        return null;
    }

    @WorkerThread
    private final boolean zza(int i, byte[] bArr) {
        zzfs();
        zzab();
        if (this.zzaiq) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppMeasurement.Param.TYPE, Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 5;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 < 5) {
                SQLiteDatabase sQLiteDatabase = null;
                Cursor cursor = null;
                try {
                    sQLiteDatabase = getWritableDatabase();
                    if (sQLiteDatabase == null) {
                        this.zzaiq = true;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        return false;
                    }
                    sQLiteDatabase.beginTransaction();
                    long j = 0;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("select count(1) from messages", (String[]) null);
                    if (rawQuery != null && rawQuery.moveToFirst()) {
                        j = rawQuery.getLong(0);
                    }
                    if (j >= 100000) {
                        zzgf().zzis().log("Data loss, local db full");
                        long j2 = (100000 - j) + 1;
                        long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j2)});
                        if (delete != j2) {
                            zzgf().zzis().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(delete), Long.valueOf(j2 - delete));
                        }
                    }
                    sQLiteDatabase.insertOrThrow("messages", (String) null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return true;
                } catch (SQLiteFullException e) {
                    zzgf().zzis().zzg("Error writing entry to local database", e);
                    this.zzaiq = true;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (SQLiteDatabaseLockedException e2) {
                    SystemClock.sleep((long) i2);
                    i2 += 20;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (SQLiteException e3) {
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    zzgf().zzis().zzg("Error writing entry to local database", e3);
                    this.zzaiq = true;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            } else {
                zzgf().zziv().log("Failed to write entry to local database");
                return false;
            }
            i3 = i4 + 1;
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzfs();
        zzab();
        try {
            int delete = getWritableDatabase().delete("messages", (String) null, (String[]) null) + 0;
            if (delete > 0) {
                zzgf().zziz().zzg("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzgf().zzis().zzg("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzew zzew) {
        Parcel obtain = Parcel.obtain();
        zzew.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzgf().zziv().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzjz zzjz) {
        Parcel obtain = Parcel.obtain();
        zzjz.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzgf().zziv().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final boolean zzc(zzee zzee) {
        zzgc();
        byte[] zza = zzkc.zza((Parcelable) zzee);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzgf().zziv().log("Conditional user property too long for local database. Sending directly to service");
        return false;
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

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x00b6 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ea A[SYNTHETIC, Splitter:B:66:0x00ea] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x013b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzp(int r14) {
        /*
            r13 = this;
            r13.zzab()
            r13.zzfs()
            boolean r0 = r13.zzaiq
            if (r0 == 0) goto L_0x000c
            r0 = 0
        L_0x000b:
            return r0
        L_0x000c:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            android.content.Context r0 = r13.getContext()
            java.lang.String r1 = "google_app_measurement_local.db"
            java.io.File r0 = r0.getDatabasePath(r1)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0023
            r0 = r10
            goto L_0x000b
        L_0x0023:
            r9 = 5
            r0 = 0
            r12 = r0
        L_0x0026:
            r0 = 5
            if (r12 >= r0) goto L_0x01d9
            r3 = 0
            r11 = 0
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x020a, SQLiteDatabaseLockedException -> 0x0201, SQLiteException -> 0x01f7, all -> 0x01e9 }
            if (r0 != 0) goto L_0x003b
            r1 = 1
            r13.zzaiq = r1     // Catch:{ SQLiteFullException -> 0x020f, SQLiteDatabaseLockedException -> 0x0205, SQLiteException -> 0x01fc, all -> 0x01ee }
            if (r0 == 0) goto L_0x0039
            r0.close()
        L_0x0039:
            r0 = 0
            goto L_0x000b
        L_0x003b:
            r0.beginTransaction()     // Catch:{ SQLiteFullException -> 0x020f, SQLiteDatabaseLockedException -> 0x0205, SQLiteException -> 0x01fc, all -> 0x01ee }
            java.lang.String r1 = "messages"
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteFullException -> 0x020f, SQLiteDatabaseLockedException -> 0x0205, SQLiteException -> 0x01fc, all -> 0x01ee }
            r3 = 0
            java.lang.String r4 = "rowid"
            r2[r3] = r4     // Catch:{ SQLiteFullException -> 0x020f, SQLiteDatabaseLockedException -> 0x0205, SQLiteException -> 0x01fc, all -> 0x01ee }
            r3 = 1
            java.lang.String r4 = "type"
            r2[r3] = r4     // Catch:{ SQLiteFullException -> 0x020f, SQLiteDatabaseLockedException -> 0x0205, SQLiteException -> 0x01fc, all -> 0x01ee }
            r3 = 2
            java.lang.String r4 = "entry"
            r2[r3] = r4     // Catch:{ SQLiteFullException -> 0x020f, SQLiteDatabaseLockedException -> 0x0205, SQLiteException -> 0x01fc, all -> 0x01ee }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid asc"
            r8 = 100
            java.lang.String r8 = java.lang.Integer.toString(r8)     // Catch:{ SQLiteFullException -> 0x020f, SQLiteDatabaseLockedException -> 0x0205, SQLiteException -> 0x01fc, all -> 0x01ee }
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteFullException -> 0x020f, SQLiteDatabaseLockedException -> 0x0205, SQLiteException -> 0x01fc, all -> 0x01ee }
            r4 = -1
        L_0x0064:
            boolean r1 = r2.moveToNext()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            if (r1 == 0) goto L_0x01a1
            r1 = 0
            long r4 = r2.getLong(r1)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r1 = 1
            int r1 = r2.getInt(r1)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r3 = 2
            byte[] r6 = r2.getBlob(r3)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            if (r1 != 0) goto L_0x010f
            android.os.Parcel r3 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r1 = 0
            int r7 = r6.length     // Catch:{ ParseException -> 0x00bc }
            r3.unmarshall(r6, r1, r7)     // Catch:{ ParseException -> 0x00bc }
            r1 = 0
            r3.setDataPosition(r1)     // Catch:{ ParseException -> 0x00bc }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzew> r1 = com.google.android.gms.internal.measurement.zzew.CREATOR     // Catch:{ ParseException -> 0x00bc }
            java.lang.Object r1 = r1.createFromParcel(r3)     // Catch:{ ParseException -> 0x00bc }
            com.google.android.gms.internal.measurement.zzew r1 = (com.google.android.gms.internal.measurement.zzew) r1     // Catch:{ ParseException -> 0x00bc }
            r3.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            if (r1 == 0) goto L_0x0064
            r10.add(r1)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            goto L_0x0064
        L_0x0099:
            r1 = move-exception
            r3 = r0
        L_0x009b:
            com.google.android.gms.internal.measurement.zzfh r0 = r13.zzgf()     // Catch:{ all -> 0x01f3 }
            com.google.android.gms.internal.measurement.zzfj r0 = r0.zzis()     // Catch:{ all -> 0x01f3 }
            java.lang.String r4 = "Error reading entries from local database"
            r0.zzg(r4, r1)     // Catch:{ all -> 0x01f3 }
            r0 = 1
            r13.zzaiq = r0     // Catch:{ all -> 0x01f3 }
            if (r2 == 0) goto L_0x00b0
            r2.close()
        L_0x00b0:
            if (r3 == 0) goto L_0x0214
            r3.close()
            r0 = r9
        L_0x00b6:
            int r1 = r12 + 1
            r12 = r1
            r9 = r0
            goto L_0x0026
        L_0x00bc:
            r1 = move-exception
            com.google.android.gms.internal.measurement.zzfh r1 = r13.zzgf()     // Catch:{ all -> 0x00e1 }
            com.google.android.gms.internal.measurement.zzfj r1 = r1.zzis()     // Catch:{ all -> 0x00e1 }
            java.lang.String r6 = "Failed to load event from local database"
            r1.log(r6)     // Catch:{ all -> 0x00e1 }
            r3.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            goto L_0x0064
        L_0x00ce:
            r1 = move-exception
            r3 = r0
        L_0x00d0:
            long r0 = (long) r9
            android.os.SystemClock.sleep(r0)     // Catch:{ all -> 0x01f3 }
            int r0 = r9 + 20
            if (r2 == 0) goto L_0x00db
            r2.close()
        L_0x00db:
            if (r3 == 0) goto L_0x00b6
            r3.close()
            goto L_0x00b6
        L_0x00e1:
            r1 = move-exception
            r3.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            throw r1     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
        L_0x00e6:
            r1 = move-exception
            r3 = r0
        L_0x00e8:
            if (r3 == 0) goto L_0x00f3
            boolean r0 = r3.inTransaction()     // Catch:{ all -> 0x01f3 }
            if (r0 == 0) goto L_0x00f3
            r3.endTransaction()     // Catch:{ all -> 0x01f3 }
        L_0x00f3:
            com.google.android.gms.internal.measurement.zzfh r0 = r13.zzgf()     // Catch:{ all -> 0x01f3 }
            com.google.android.gms.internal.measurement.zzfj r0 = r0.zzis()     // Catch:{ all -> 0x01f3 }
            java.lang.String r4 = "Error reading entries from local database"
            r0.zzg(r4, r1)     // Catch:{ all -> 0x01f3 }
            r0 = 1
            r13.zzaiq = r0     // Catch:{ all -> 0x01f3 }
            if (r2 == 0) goto L_0x0108
            r2.close()
        L_0x0108:
            if (r3 == 0) goto L_0x0214
            r3.close()
            r0 = r9
            goto L_0x00b6
        L_0x010f:
            r3 = 1
            if (r1 != r3) goto L_0x0157
            android.os.Parcel r7 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r3 = 0
            r1 = 0
            int r8 = r6.length     // Catch:{ ParseException -> 0x013f }
            r7.unmarshall(r6, r1, r8)     // Catch:{ ParseException -> 0x013f }
            r1 = 0
            r7.setDataPosition(r1)     // Catch:{ ParseException -> 0x013f }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzjz> r1 = com.google.android.gms.internal.measurement.zzjz.CREATOR     // Catch:{ ParseException -> 0x013f }
            java.lang.Object r1 = r1.createFromParcel(r7)     // Catch:{ ParseException -> 0x013f }
            com.google.android.gms.internal.measurement.zzjz r1 = (com.google.android.gms.internal.measurement.zzjz) r1     // Catch:{ ParseException -> 0x013f }
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
        L_0x012b:
            if (r1 == 0) goto L_0x0064
            r10.add(r1)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            goto L_0x0064
        L_0x0132:
            r1 = move-exception
            r3 = r0
        L_0x0134:
            if (r2 == 0) goto L_0x0139
            r2.close()
        L_0x0139:
            if (r3 == 0) goto L_0x013e
            r3.close()
        L_0x013e:
            throw r1
        L_0x013f:
            r1 = move-exception
            com.google.android.gms.internal.measurement.zzfh r1 = r13.zzgf()     // Catch:{ all -> 0x0152 }
            com.google.android.gms.internal.measurement.zzfj r1 = r1.zzis()     // Catch:{ all -> 0x0152 }
            java.lang.String r6 = "Failed to load user property from local database"
            r1.log(r6)     // Catch:{ all -> 0x0152 }
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r1 = r3
            goto L_0x012b
        L_0x0152:
            r1 = move-exception
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            throw r1     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
        L_0x0157:
            r3 = 2
            if (r1 != r3) goto L_0x0192
            android.os.Parcel r7 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r3 = 0
            r1 = 0
            int r8 = r6.length     // Catch:{ ParseException -> 0x017a }
            r7.unmarshall(r6, r1, r8)     // Catch:{ ParseException -> 0x017a }
            r1 = 0
            r7.setDataPosition(r1)     // Catch:{ ParseException -> 0x017a }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzee> r1 = com.google.android.gms.internal.measurement.zzee.CREATOR     // Catch:{ ParseException -> 0x017a }
            java.lang.Object r1 = r1.createFromParcel(r7)     // Catch:{ ParseException -> 0x017a }
            com.google.android.gms.internal.measurement.zzee r1 = (com.google.android.gms.internal.measurement.zzee) r1     // Catch:{ ParseException -> 0x017a }
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
        L_0x0173:
            if (r1 == 0) goto L_0x0064
            r10.add(r1)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            goto L_0x0064
        L_0x017a:
            r1 = move-exception
            com.google.android.gms.internal.measurement.zzfh r1 = r13.zzgf()     // Catch:{ all -> 0x018d }
            com.google.android.gms.internal.measurement.zzfj r1 = r1.zzis()     // Catch:{ all -> 0x018d }
            java.lang.String r6 = "Failed to load user property from local database"
            r1.log(r6)     // Catch:{ all -> 0x018d }
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r1 = r3
            goto L_0x0173
        L_0x018d:
            r1 = move-exception
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            throw r1     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
        L_0x0192:
            com.google.android.gms.internal.measurement.zzfh r1 = r13.zzgf()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            com.google.android.gms.internal.measurement.zzfj r1 = r1.zzis()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            java.lang.String r3 = "Unknown record type in local database"
            r1.log(r3)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            goto L_0x0064
        L_0x01a1:
            java.lang.String r1 = "messages"
            java.lang.String r3 = "rowid <= ?"
            r6 = 1
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r7 = 0
            java.lang.String r4 = java.lang.Long.toString(r4)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r6[r7] = r4     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            int r1 = r0.delete(r1, r3, r6)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            int r3 = r10.size()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            if (r1 >= r3) goto L_0x01c6
            com.google.android.gms.internal.measurement.zzfh r1 = r13.zzgf()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            com.google.android.gms.internal.measurement.zzfj r1 = r1.zzis()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            java.lang.String r3 = "Fewer entries removed from local database than expected"
            r1.log(r3)     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
        L_0x01c6:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            r0.endTransaction()     // Catch:{ SQLiteFullException -> 0x0099, SQLiteDatabaseLockedException -> 0x00ce, SQLiteException -> 0x00e6, all -> 0x0132 }
            if (r2 == 0) goto L_0x01d1
            r2.close()
        L_0x01d1:
            if (r0 == 0) goto L_0x01d6
            r0.close()
        L_0x01d6:
            r0 = r10
            goto L_0x000b
        L_0x01d9:
            com.google.android.gms.internal.measurement.zzfh r0 = r13.zzgf()
            com.google.android.gms.internal.measurement.zzfj r0 = r0.zziv()
            java.lang.String r1 = "Failed to read events from database in reasonable time"
            r0.log(r1)
            r0 = 0
            goto L_0x000b
        L_0x01e9:
            r0 = move-exception
            r1 = r0
            r2 = r11
            goto L_0x0134
        L_0x01ee:
            r1 = move-exception
            r2 = r11
            r3 = r0
            goto L_0x0134
        L_0x01f3:
            r0 = move-exception
            r1 = r0
            goto L_0x0134
        L_0x01f7:
            r0 = move-exception
            r1 = r0
            r2 = r11
            goto L_0x00e8
        L_0x01fc:
            r1 = move-exception
            r2 = r11
            r3 = r0
            goto L_0x00e8
        L_0x0201:
            r0 = move-exception
            r2 = r11
            goto L_0x00d0
        L_0x0205:
            r1 = move-exception
            r2 = r11
            r3 = r0
            goto L_0x00d0
        L_0x020a:
            r0 = move-exception
            r1 = r0
            r2 = r11
            goto L_0x009b
        L_0x020f:
            r1 = move-exception
            r2 = r11
            r3 = r0
            goto L_0x009b
        L_0x0214:
            r0 = r9
            goto L_0x00b6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfd.zzp(int):java.util.List");
    }
}
