package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;

public final class zzdo {
    private static boolean zzaay = false;
    private final String mTag;
    private final boolean zzaaz;
    private boolean zzaba;
    private boolean zzabb;
    private String zzabc;

    private zzdo(String str, boolean z) {
        boolean z2;
        Preconditions.checkNotEmpty(str, "The log tag cannot be null or empty.");
        this.mTag = str;
        if (str.length() <= 23) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.zzaaz = z2;
        this.zzaba = false;
        this.zzabb = false;
    }

    public zzdo(String str) {
        this(str, false);
    }

    public final void zzv(String str) {
        String format;
        if (TextUtils.isEmpty(str)) {
            format = null;
        } else {
            format = String.format("[%s] ", new Object[]{str});
        }
        this.zzabc = format;
    }

    private final boolean zzen() {
        return this.zzaba || (this.zzaaz && Log.isLoggable(this.mTag, 3));
    }

    public final void zzl(boolean z) {
        this.zzaba = true;
    }

    public final void d(String str, Object... objArr) {
        if (zzen()) {
            Log.d(this.mTag, zza(str, objArr));
        }
    }

    public final void zza(Throwable th, String str, Object... objArr) {
        if (zzen()) {
            Log.d(this.mTag, zza(str, objArr), th);
        }
    }

    public final void i(String str, Object... objArr) {
        Log.i(this.mTag, zza(str, objArr));
    }

    public final void w(String str, Object... objArr) {
        Log.w(this.mTag, zza(str, objArr));
    }

    public final void zzb(Throwable th, String str, Object... objArr) {
        Log.w(this.mTag, zza(str, objArr), th);
    }

    public final void e(String str, Object... objArr) {
        Log.e(this.mTag, zza(str, objArr));
    }

    public final void zzc(Throwable th, String str, Object... objArr) {
        Log.e(this.mTag, zza(str, objArr), th);
    }

    private final String zza(String str, Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(Locale.ROOT, str, objArr);
        }
        if (TextUtils.isEmpty(this.zzabc)) {
            return str;
        }
        String valueOf = String.valueOf(this.zzabc);
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }
}
