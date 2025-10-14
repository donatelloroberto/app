package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@VisibleForTesting
final class zzaz extends SQLiteOpenHelper {
    private final /* synthetic */ zzay zzxo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaz(zzay zzay, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzxo = zzay;
    }

    public final SQLiteDatabase getWritableDatabase() {
        if (!this.zzxo.zzxn.zzj(3600000)) {
            throw new SQLiteException("Database open failed");
        }
        try {
            return super.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzxo.zzxn.start();
            this.zzxo.zzu("Opening the database failed, dropping the table and recreating it");
            this.zzxo.getContext().getDatabasePath(zzay.zzdt()).delete();
            try {
                SQLiteDatabase writableDatabase = super.getWritableDatabase();
                this.zzxo.zzxn.clear();
                return writableDatabase;
            } catch (SQLiteException e2) {
                this.zzxo.zze("Failed to open freshly created database", e2);
                throw e2;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(android.database.sqlite.SQLiteDatabase r11, java.lang.String r12) {
        /*
            r10 = this;
            r8 = 0
            r9 = 0
            java.lang.String r1 = "SQLITE_MASTER"
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
            r0 = 0
            java.lang.String r3 = "name"
            r2[r0] = r3     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
            java.lang.String r3 = "name=?"
            r0 = 1
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
            r0 = 0
            r4[r0] = r12     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r11
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0040 }
            if (r1 == 0) goto L_0x0025
            r1.close()
        L_0x0025:
            return r0
        L_0x0026:
            r0 = move-exception
            r1 = r9
        L_0x0028:
            com.google.android.gms.internal.gtm.zzay r2 = r10.zzxo     // Catch:{ all -> 0x003d }
            java.lang.String r3 = "Error querying for table"
            r2.zzc(r3, r12, r0)     // Catch:{ all -> 0x003d }
            if (r1 == 0) goto L_0x0034
            r1.close()
        L_0x0034:
            r0 = r8
            goto L_0x0025
        L_0x0036:
            r0 = move-exception
        L_0x0037:
            if (r9 == 0) goto L_0x003c
            r9.close()
        L_0x003c:
            throw r0
        L_0x003d:
            r0 = move-exception
            r9 = r1
            goto L_0x0037
        L_0x0040:
            r0 = move-exception
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzaz.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), (String[]) null);
        try {
            String[] columnNames = rawQuery.getColumnNames();
            for (String add : columnNames) {
                hashSet.add(add);
            }
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        boolean z;
        if (Build.VERSION.SDK_INT < 15) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[]) null);
            try {
                rawQuery.moveToFirst();
            } finally {
                rawQuery.close();
            }
        }
        if (!zza(sQLiteDatabase, "hits2")) {
            sQLiteDatabase.execSQL(zzay.zzxj);
        } else {
            Set<String> zzb = zzb(sQLiteDatabase, "hits2");
            String[] strArr = {"hit_id", "hit_string", "hit_time", "hit_url"};
            for (int i = 0; i < 4; i++) {
                String str = strArr[i];
                if (!zzb.remove(str)) {
                    String valueOf = String.valueOf(str);
                    throw new SQLiteException(valueOf.length() != 0 ? "Database hits2 is missing required column: ".concat(valueOf) : new String("Database hits2 is missing required column: "));
                }
            }
            if (!zzb.remove("hit_app_id")) {
                z = true;
            } else {
                z = false;
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database hits2 has extra columns");
            } else if (z) {
                sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
            }
        }
        if (!zza(sQLiteDatabase, "properties")) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
            return;
        }
        Set<String> zzb2 = zzb(sQLiteDatabase, "properties");
        String[] strArr2 = {"app_uid", "cid", "tid", "params", "adid", "hits_count"};
        for (int i2 = 0; i2 < 6; i2++) {
            String str2 = strArr2[i2];
            if (!zzb2.remove(str2)) {
                String valueOf2 = String.valueOf(str2);
                throw new SQLiteException(valueOf2.length() != 0 ? "Database properties is missing required column: ".concat(valueOf2) : new String("Database properties is missing required column: "));
            }
        }
        if (!zzb2.isEmpty()) {
            throw new SQLiteException("Database properties table has extra columns");
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        String path = sQLiteDatabase.getPath();
        if (zzbx.version() >= 9) {
            File file = new File(path);
            file.setReadable(false, false);
            file.setWritable(false, false);
            file.setReadable(true, true);
            file.setWritable(true, true);
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
