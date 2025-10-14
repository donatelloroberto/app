package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzdf;
import com.google.android.gms.internal.gtm.zzdi;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

final class zzat implements DataLayer.zzc {
    /* access modifiers changed from: private */
    public static final String zzafx = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[]{"datalayer", "ID", "key", FirebaseAnalytics.Param.VALUE, "expires"});
    private final Executor zzafy;
    private zzax zzafz;
    private int zzaga;
    /* access modifiers changed from: private */
    public final Context zzrm;
    private Clock zzsd;

    public zzat(Context context) {
        this(context, DefaultClock.getInstance(), "google_tagmanager.db", CastStatusCodes.AUTHENTICATION_FAILED, zzdf.zzgp().zzr(zzdi.zzadg));
    }

    @VisibleForTesting
    private zzat(Context context, Clock clock, String str, int i, Executor executor) {
        this.zzrm = context;
        this.zzsd = clock;
        this.zzaga = CastStatusCodes.AUTHENTICATION_FAILED;
        this.zzafy = executor;
        this.zzafz = new zzax(this, this.zzrm, str);
    }

    public final void zza(List<DataLayer.zza> list, long j) {
        ArrayList arrayList = new ArrayList();
        for (DataLayer.zza next : list) {
            arrayList.add(new zzay(next.mKey, zzf(next.mValue)));
        }
        this.zzafy.execute(new zzau(this, arrayList, j));
    }

    public final void zza(zzaq zzaq) {
        this.zzafy.execute(new zzav(this, zzaq));
    }

    public final void zzas(String str) {
        this.zzafy.execute(new zzaw(this, str));
    }

    /* access modifiers changed from: private */
    public final List<DataLayer.zza> zzht() {
        try {
            zzl(this.zzsd.currentTimeMillis());
            List<zzay> zzhu = zzhu();
            ArrayList arrayList = new ArrayList();
            for (zzay next : zzhu) {
                arrayList.add(new DataLayer.zza(next.zzagg, zza(next.zzagh)));
            }
            return arrayList;
        } finally {
            zzhw();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0027 A[SYNTHETIC, Splitter:B:18:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0035 A[SYNTHETIC, Splitter:B:25:0x0035] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object zza(byte[] r5) {
        /*
            r0 = 0
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream
            r4.<init>(r5)
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0016, ClassNotFoundException -> 0x0023, all -> 0x0030 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0016, ClassNotFoundException -> 0x0023, all -> 0x0030 }
            java.lang.Object r0 = r1.readObject()     // Catch:{ IOException -> 0x0044, ClassNotFoundException -> 0x0042, all -> 0x003e }
            r1.close()     // Catch:{ IOException -> 0x0046 }
            r4.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0015:
            return r0
        L_0x0016:
            r1 = move-exception
            r1 = r0
        L_0x0018:
            if (r1 == 0) goto L_0x001d
            r1.close()     // Catch:{ IOException -> 0x0021 }
        L_0x001d:
            r4.close()     // Catch:{ IOException -> 0x0021 }
            goto L_0x0015
        L_0x0021:
            r1 = move-exception
            goto L_0x0015
        L_0x0023:
            r1 = move-exception
            r1 = r0
        L_0x0025:
            if (r1 == 0) goto L_0x002a
            r1.close()     // Catch:{ IOException -> 0x002e }
        L_0x002a:
            r4.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0015
        L_0x002e:
            r1 = move-exception
            goto L_0x0015
        L_0x0030:
            r1 = move-exception
            r2 = r1
            r3 = r0
        L_0x0033:
            if (r3 == 0) goto L_0x0038
            r3.close()     // Catch:{ IOException -> 0x003c }
        L_0x0038:
            r4.close()     // Catch:{ IOException -> 0x003c }
        L_0x003b:
            throw r2
        L_0x003c:
            r0 = move-exception
            goto L_0x003b
        L_0x003e:
            r0 = move-exception
            r2 = r0
            r3 = r1
            goto L_0x0033
        L_0x0042:
            r2 = move-exception
            goto L_0x0025
        L_0x0044:
            r2 = move-exception
            goto L_0x0018
        L_0x0046:
            r1 = move-exception
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zza(byte[]):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002b A[SYNTHETIC, Splitter:B:18:0x002b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] zzf(java.lang.Object r5) {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream
            r4.<init>()
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0019, all -> 0x0026 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0019, all -> 0x0026 }
            r1.writeObject(r5)     // Catch:{ IOException -> 0x0038, all -> 0x0034 }
            byte[] r0 = r4.toByteArray()     // Catch:{ IOException -> 0x0038, all -> 0x0034 }
            r1.close()     // Catch:{ IOException -> 0x003a }
            r4.close()     // Catch:{ IOException -> 0x003a }
        L_0x0018:
            return r0
        L_0x0019:
            r1 = move-exception
            r1 = r0
        L_0x001b:
            if (r1 == 0) goto L_0x0020
            r1.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0020:
            r4.close()     // Catch:{ IOException -> 0x0024 }
            goto L_0x0018
        L_0x0024:
            r1 = move-exception
            goto L_0x0018
        L_0x0026:
            r1 = move-exception
            r2 = r1
            r3 = r0
        L_0x0029:
            if (r3 == 0) goto L_0x002e
            r3.close()     // Catch:{ IOException -> 0x0032 }
        L_0x002e:
            r4.close()     // Catch:{ IOException -> 0x0032 }
        L_0x0031:
            throw r2
        L_0x0032:
            r0 = move-exception
            goto L_0x0031
        L_0x0034:
            r0 = move-exception
            r2 = r0
            r3 = r1
            goto L_0x0029
        L_0x0038:
            r2 = move-exception
            goto L_0x001b
        L_0x003a:
            r1 = move-exception
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzf(java.lang.Object):byte[]");
    }

    /* access modifiers changed from: private */
    public final synchronized void zzb(List<zzay> list, long j) {
        try {
            long currentTimeMillis = this.zzsd.currentTimeMillis();
            zzl(currentTimeMillis);
            int size = list.size() + (zzhv() - this.zzaga);
            if (size > 0) {
                List<String> zzu = zzu(size);
                zzdi.zzaw(new StringBuilder(64).append("DataLayer store full, deleting ").append(zzu.size()).append(" entries to make room.").toString());
                String[] strArr = (String[]) zzu.toArray(new String[0]);
                if (!(strArr == null || strArr.length == 0)) {
                    SQLiteDatabase zzau = zzau("Error opening database for deleteEntries.");
                    if (zzau != null) {
                        try {
                            zzau.delete("datalayer", String.format("%s in (%s)", new Object[]{"ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                        } catch (SQLiteException e) {
                            String valueOf = String.valueOf(Arrays.toString(strArr));
                            zzdi.zzac(valueOf.length() != 0 ? "Error deleting entries ".concat(valueOf) : new String("Error deleting entries "));
                        }
                    }
                }
            }
            long j2 = currentTimeMillis + j;
            SQLiteDatabase zzau2 = zzau("Error opening database for writeEntryToDatabase.");
            if (zzau2 != null) {
                for (zzay next : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("expires", Long.valueOf(j2));
                    contentValues.put("key", next.zzagg);
                    contentValues.put(FirebaseAnalytics.Param.VALUE, next.zzagh);
                    zzau2.insert("datalayer", (String) null, contentValues);
                }
            }
            zzhw();
        } catch (Throwable th) {
            zzhw();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    private final List<zzay> zzhu() {
        SQLiteDatabase zzau = zzau("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (zzau == null) {
            return arrayList;
        }
        Cursor query = zzau.query("datalayer", new String[]{"key", FirebaseAnalytics.Param.VALUE}, (String) null, (String[]) null, (String) null, (String) null, "ID", (String) null);
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzay(query.getString(0), query.getBlob(1)));
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        query.close();
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final void zzat(String str) {
        SQLiteDatabase zzau = zzau("Error opening database for clearKeysWithPrefix.");
        if (zzau != null) {
            try {
                zzdi.zzab(new StringBuilder(25).append("Cleared ").append(zzau.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")})).append(" items").toString());
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e);
                zzdi.zzac(new StringBuilder(String.valueOf(str).length() + 44 + String.valueOf(valueOf).length()).append("Error deleting entries with key prefix: ").append(str).append(" (").append(valueOf).append(").").toString());
            } finally {
                zzhw();
            }
        }
    }

    private final void zzl(long j) {
        SQLiteDatabase zzau = zzau("Error opening database for deleteOlderThan.");
        if (zzau != null) {
            try {
                zzdi.zzab(new StringBuilder(33).append("Deleted ").append(zzau.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)})).append(" expired items").toString());
            } catch (SQLiteException e) {
                zzdi.zzac("Error deleting old entries.");
            }
        }
    }

    private final List<String> zzu(int i) {
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            zzdi.zzac("Invalid maxEntries specified. Skipping.");
            return arrayList;
        }
        SQLiteDatabase zzau = zzau("Error opening database for peekEntryIds.");
        if (zzau == null) {
            return arrayList;
        }
        try {
            cursor = zzau.query("datalayer", new String[]{"ID"}, (String) null, (String[]) null, (String) null, (String) null, String.format("%s ASC", new Object[]{"ID"}), Integer.toString(i));
            try {
                if (cursor.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(cursor.getLong(0)));
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteException e) {
                e = e;
                try {
                    String valueOf = String.valueOf(e.getMessage());
                    zzdi.zzac(valueOf.length() != 0 ? "Error in peekEntries fetching entryIds: ".concat(valueOf) : new String("Error in peekEntries fetching entryIds: "));
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }

    private final int zzhv() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase zzau = zzau("Error opening database for getNumStoredEntries.");
        if (zzau != null) {
            try {
                Cursor rawQuery = zzau.rawQuery("SELECT COUNT(*) from datalayer", (String[]) null);
                if (rawQuery.moveToFirst()) {
                    i = (int) rawQuery.getLong(0);
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteException e) {
                zzdi.zzac("Error getting numStoredEntries");
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return i;
    }

    private final SQLiteDatabase zzau(String str) {
        try {
            return this.zzafz.getWritableDatabase();
        } catch (SQLiteException e) {
            zzdi.zzac(str);
            return null;
        }
    }

    private final void zzhw() {
        try {
            this.zzafz.close();
        } catch (SQLiteException e) {
        }
    }
}
