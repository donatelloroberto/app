package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzfe extends SQLiteOpenHelper {
    private final /* synthetic */ zzfd zzair;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfe(zzfd zzfd, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzair = zzfd;
    }

    @WorkerThread
    public final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteDatabaseLockedException e) {
            throw e;
        } catch (SQLiteException e2) {
            this.zzair.zzgf().zzis().log("Opening the local database failed, dropping and recreating it");
            if (!this.zzair.getContext().getDatabasePath("google_app_measurement_local.db").delete()) {
                this.zzair.zzgf().zzis().zzg("Failed to delete corrupted local db file", "google_app_measurement_local.db");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e3) {
                this.zzair.zzgf().zzis().zzg("Failed to open local database. Events will bypass local storage", e3);
                return null;
            }
        }
    }

    @WorkerThread
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzen.zza(this.zzair.zzgf(), sQLiteDatabase);
    }

    @WorkerThread
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @WorkerThread
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        if (Build.VERSION.SDK_INT < 15) {
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[]) null);
                try {
                    rawQuery.moveToFirst();
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = rawQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        zzen.zza(this.zzair.zzgf(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", (String[]) null);
    }

    @WorkerThread
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
