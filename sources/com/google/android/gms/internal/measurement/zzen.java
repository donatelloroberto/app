package com.google.android.gms.internal.measurement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzen {
    static void zza(zzfh zzfh, SQLiteDatabase sQLiteDatabase) {
        if (zzfh == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzfh.zziv().log("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzfh.zziv().log("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzfh.zziv().log("Failed to turn on database read permission for owner");
        }
        if (!file.setWritable(true, true)) {
            zzfh.zziv().log("Failed to turn on database write permission for owner");
        }
    }

    @WorkerThread
    static void zza(zzfh zzfh, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzfh == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(zzfh, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        if (zzfh == null) {
            try {
                throw new IllegalArgumentException("Monitor must not be null");
            } catch (SQLiteException e) {
                zzfh.zzis().zzg("Failed to verify columns on table that was just created", str);
                throw e;
            }
        } else {
            Set<String> zzb = zzb(sQLiteDatabase, str);
            for (String str4 : str3.split(",")) {
                if (!zzb.remove(str4)) {
                    throw new SQLiteException(new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str4).length()).append("Table ").append(str).append(" is missing required column: ").append(str4).toString());
                }
            }
            if (strArr != null) {
                for (int i = 0; i < strArr.length; i += 2) {
                    if (!zzb.remove(strArr[i])) {
                        sQLiteDatabase.execSQL(strArr[i + 1]);
                    }
                }
            }
            if (!zzb.isEmpty()) {
                zzfh.zziv().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(com.google.android.gms.internal.measurement.zzfh r10, android.database.sqlite.SQLiteDatabase r11, java.lang.String r12) {
        /*
            r8 = 0
            r9 = 0
            if (r10 != 0) goto L_0x000c
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Monitor must not be null"
            r0.<init>(r1)
            throw r0
        L_0x000c:
            java.lang.String r1 = "SQLITE_MASTER"
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            r0 = 0
            java.lang.String r3 = "name"
            r2[r0] = r3     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            java.lang.String r3 = "name=?"
            r0 = 1
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            r0 = 0
            r4[r0] = r12     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r11
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x004c }
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return r0
        L_0x0030:
            r0 = move-exception
            r1 = r9
        L_0x0032:
            com.google.android.gms.internal.measurement.zzfj r2 = r10.zziv()     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = "Error querying for table"
            r2.zze(r3, r12, r0)     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0040
            r1.close()
        L_0x0040:
            r0 = r8
            goto L_0x002f
        L_0x0042:
            r0 = move-exception
        L_0x0043:
            if (r9 == 0) goto L_0x0048
            r9.close()
        L_0x0048:
            throw r0
        L_0x0049:
            r0 = move-exception
            r9 = r1
            goto L_0x0043
        L_0x004c:
            r0 = move-exception
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzen.zza(com.google.android.gms.internal.measurement.zzfh, android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    @WorkerThread
    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), (String[]) null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }
}
