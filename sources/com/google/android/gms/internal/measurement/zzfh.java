package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzfh extends zzhi {
    /* access modifiers changed from: private */
    public long zzadu = -1;
    /* access modifiers changed from: private */
    public char zzaiv = 0;
    @GuardedBy("this")
    private String zzaiw;
    private final zzfj zzaix = new zzfj(this, 6, false, false);
    private final zzfj zzaiy = new zzfj(this, 6, true, false);
    private final zzfj zzaiz = new zzfj(this, 6, false, true);
    private final zzfj zzaja = new zzfj(this, 5, false, false);
    private final zzfj zzajb = new zzfj(this, 5, true, false);
    private final zzfj zzajc = new zzfj(this, 5, false, true);
    private final zzfj zzajd = new zzfj(this, 4, false, false);
    private final zzfj zzaje = new zzfj(this, 3, false, false);
    private final zzfj zzajf = new zzfj(this, 2, false, false);

    zzfh(zzgm zzgm) {
        super(zzgm);
    }

    @VisibleForTesting
    private static String zza(boolean z, Object obj) {
        StackTraceElement stackTraceElement;
        String className;
        if (obj == null) {
            return "";
        }
        Object valueOf = obj instanceof Integer ? Long.valueOf((long) ((Integer) obj).intValue()) : obj;
        if (valueOf instanceof Long) {
            if (!z) {
                return String.valueOf(valueOf);
            }
            if (Math.abs(((Long) valueOf).longValue()) < 100) {
                return String.valueOf(valueOf);
            }
            String str = String.valueOf(valueOf).charAt(0) == '-' ? "-" : "";
            String valueOf2 = String.valueOf(Math.abs(((Long) valueOf).longValue()));
            return new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, (double) (valueOf2.length() - 1)))).append("...").append(str).append(Math.round(Math.pow(10.0d, (double) valueOf2.length()) - 1.0d)).toString();
        } else if (valueOf instanceof Boolean) {
            return String.valueOf(valueOf);
        } else {
            if (!(valueOf instanceof Throwable)) {
                return valueOf instanceof zzfk ? ((zzfk) valueOf).zzajo : z ? "-" : String.valueOf(valueOf);
            }
            Throwable th = (Throwable) valueOf;
            StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String zzbm = zzbm(AppMeasurement.class.getCanonicalName());
            String zzbm2 = zzbm(zzgm.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                stackTraceElement = stackTrace[i];
                if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null) {
                    String zzbm3 = zzbm(className);
                    if (zzbm3.equals(zzbm) || zzbm3.equals(zzbm2)) {
                        sb.append(": ");
                        sb.append(stackTraceElement);
                    }
                }
                i++;
            }
            sb.append(": ");
            sb.append(stackTraceElement);
            return sb.toString();
        }
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zza = zza(z, obj);
        String zza2 = zza(z, obj2);
        String zza3 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zza)) {
            sb.append(str2);
            sb.append(zza);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str2);
            sb.append(zza3);
        }
        return sb.toString();
    }

    protected static Object zzbl(String str) {
        if (str == null) {
            return null;
        }
        return new zzfk(str);
    }

    private static String zzbm(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    private final String zzja() {
        String str;
        synchronized (this) {
            if (this.zzaiw == null) {
                this.zzaiw = zzeg.zzhi();
            }
            str = this.zzaiw;
        }
        return str;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final boolean isLoggable(int i) {
        return Log.isLoggable(zzja(), i);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final void zza(int i, String str) {
        Log.println(i, zzja(), str);
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        int i2 = 0;
        if (!z && isLoggable(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzgh zzjx = this.zzacw.zzjx();
            if (zzjx == null) {
                zza(6, "Scheduler not set. Not logging error/warn");
            } else if (!zzjx.isInitialized()) {
                zza(6, "Scheduler not initialized. Not logging error/warn");
            } else {
                if (i >= 0) {
                    i2 = i;
                }
                if (i2 >= 9) {
                    i2 = 8;
                }
                zzjx.zzc((Runnable) new zzfi(this, i2, str, obj, obj2, obj3));
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
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
        return false;
    }

    public final zzfj zzis() {
        return this.zzaix;
    }

    public final zzfj zzit() {
        return this.zzaiy;
    }

    public final zzfj zziu() {
        return this.zzaiz;
    }

    public final zzfj zziv() {
        return this.zzaja;
    }

    public final zzfj zziw() {
        return this.zzajc;
    }

    public final zzfj zzix() {
        return this.zzajd;
    }

    public final zzfj zziy() {
        return this.zzaje;
    }

    public final zzfj zziz() {
        return this.zzajf;
    }

    public final String zzjb() {
        Pair<String, Long> zzfi = zzgg().zzakc.zzfi();
        if (zzfi == null || zzfi == zzfs.zzakb) {
            return null;
        }
        String valueOf = String.valueOf(zzfi.second);
        String str = (String) zzfi.first;
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
    }
}
