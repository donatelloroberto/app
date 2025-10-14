package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzgc {
    private final zzgf zzalj;

    public zzgc(zzgf zzgf) {
        Preconditions.checkNotNull(zzgf);
        this.zzalj = zzgf;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        r1 = r1.getReceiverInfo(new android.content.ComponentName(r4, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zza(android.content.Context r4) {
        /*
            r0 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x001f }
            if (r1 != 0) goto L_0x000b
        L_0x000a:
            return r0
        L_0x000b:
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x001f }
            java.lang.String r3 = "com.google.android.gms.measurement.AppMeasurementReceiver"
            r2.<init>(r4, r3)     // Catch:{ NameNotFoundException -> 0x001f }
            r3 = 0
            android.content.pm.ActivityInfo r1 = r1.getReceiverInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x001f }
            if (r1 == 0) goto L_0x000a
            boolean r1 = r1.enabled     // Catch:{ NameNotFoundException -> 0x001f }
            if (r1 == 0) goto L_0x000a
            r0 = 1
            goto L_0x000a
        L_0x001f:
            r1 = move-exception
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgc.zza(android.content.Context):boolean");
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        zzgm zza = zzgm.zza(context, (String) null, (String) null);
        zzfh zzgf = zza.zzgf();
        if (intent == null) {
            zzgf.zziv().log("Receiver called with null intent");
            return;
        }
        zza.zzgi();
        String action = intent.getAction();
        zzgf.zziz().zzg("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzgf.zziz().log("Starting wakeful intent.");
            this.zzalj.doStartService(context, className);
        } else if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            try {
                zza.zzge().zzc((Runnable) new zzgd(this, zza, zzgf));
            } catch (Exception e) {
                zzgf.zziv().zzg("Install Referrer Reporter encountered a problem", e);
            }
            BroadcastReceiver.PendingResult doGoAsync = this.zzalj.doGoAsync();
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra == null) {
                zzgf.zziz().log("Install referrer extras are null");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                    return;
                }
                return;
            }
            zzgf.zzix().zzg("Install referrer extras are", stringExtra);
            if (!stringExtra.contains("?")) {
                String valueOf = String.valueOf(stringExtra);
                stringExtra = valueOf.length() != 0 ? "?".concat(valueOf) : new String("?");
            }
            Bundle zza2 = zza.zzgc().zza(Uri.parse(stringExtra));
            if (zza2 == null) {
                zzgf.zziz().log("No campaign defined in install referrer broadcast");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                    return;
                }
                return;
            }
            long longExtra = 1000 * intent.getLongExtra("referrer_timestamp_seconds", 0);
            if (longExtra == 0) {
                zzgf.zziv().log("Install referrer is missing timestamp");
            }
            zza.zzge().zzc((Runnable) new zzge(this, zza, longExtra, zza2, context, zzgf, doGoAsync));
        }
    }
}
