package com.google.firebase.messaging;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

final class zza {
    private final Context zzac;
    private final Bundle zzcl;

    public zza(Context context, Bundle bundle) {
        this.zzac = context.getApplicationContext();
        this.zzcl = bundle;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzar() {
        /*
            r11 = this;
            r1 = 1
            r2 = 0
            com.google.android.gms.internal.firebase_messaging.zzq r3 = new com.google.android.gms.internal.firebase_messaging.zzq
            java.lang.String r0 = "FirebaseMessaging"
            android.os.Bundle r4 = r11.zzcl
            r3.<init>(r0, r4)
            java.lang.String r0 = "1"
            java.lang.String r4 = "gcm.n.noui"
            java.lang.String r4 = r3.getString(r4)
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x001b
            r0 = r1
        L_0x001a:
            return r0
        L_0x001b:
            android.content.Context r0 = r11.zzac
            java.lang.String r4 = "keyguard"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.app.KeyguardManager r0 = (android.app.KeyguardManager) r0
            boolean r0 = r0.inKeyguardRestrictedInputMode()
            if (r0 != 0) goto L_0x006b
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastLollipop()
            if (r0 != 0) goto L_0x0036
            r4 = 10
            android.os.SystemClock.sleep(r4)
        L_0x0036:
            int r4 = android.os.Process.myPid()
            android.content.Context r0 = r11.zzac
            java.lang.String r5 = "activity"
            java.lang.Object r0 = r0.getSystemService(r5)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            java.util.List r0 = r0.getRunningAppProcesses()
            if (r0 == 0) goto L_0x006b
            java.util.Iterator r5 = r0.iterator()
        L_0x004e:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x006b
            java.lang.Object r0 = r5.next()
            android.app.ActivityManager$RunningAppProcessInfo r0 = (android.app.ActivityManager.RunningAppProcessInfo) r0
            int r6 = r0.pid
            if (r6 != r4) goto L_0x004e
            int r0 = r0.importance
            r4 = 100
            if (r0 != r4) goto L_0x0069
            r0 = r1
        L_0x0065:
            if (r0 == 0) goto L_0x006d
            r0 = r2
            goto L_0x001a
        L_0x0069:
            r0 = r2
            goto L_0x0065
        L_0x006b:
            r0 = r2
            goto L_0x0065
        L_0x006d:
            android.content.Context r0 = r11.zzac
            android.content.pm.PackageManager r4 = r0.getPackageManager()
            android.content.Context r0 = r11.zzac
            java.lang.String r5 = r0.getPackageName()
            android.content.Context r0 = r11.zzac
            android.content.pm.ApplicationInfo r6 = r0.getApplicationInfo()
            com.google.android.gms.internal.firebase_messaging.zzn r7 = new com.google.android.gms.internal.firebase_messaging.zzn
            android.content.Context r0 = r11.zzac
            r7.<init>(r0)
            com.google.android.gms.internal.firebase_messaging.zzo r8 = new com.google.android.gms.internal.firebase_messaging.zzo
            android.content.Context r0 = r11.zzac
            r8.<init>(r0)
            android.content.Context r0 = r11.zzac
            java.lang.String r9 = "notification"
            java.lang.Object r0 = r0.getSystemService(r9)
            android.app.NotificationManager r0 = (android.app.NotificationManager) r0
            com.google.android.gms.internal.firebase_messaging.zzu r9 = new com.google.android.gms.internal.firebase_messaging.zzu
            android.os.Bundle r10 = r11.zzcl
            r9.<init>(r10, r5)
            int r10 = r6.icon
            com.google.android.gms.internal.firebase_messaging.zzu r9 = r9.zzc((int) r10)
            java.lang.CharSequence r10 = r6.loadLabel(r4)
            com.google.android.gms.internal.firebase_messaging.zzu r9 = r9.zza((java.lang.CharSequence) r10)
            android.content.Intent r4 = r4.getLaunchIntentForPackage(r5)
            com.google.android.gms.internal.firebase_messaging.zzu r4 = r9.zzf((android.content.Intent) r4)
            com.google.android.gms.internal.firebase_messaging.zzu r4 = r4.zza((com.google.android.gms.internal.firebase_messaging.zzv) r7)
            com.google.android.gms.internal.firebase_messaging.zzu r4 = r4.zza((com.google.android.gms.internal.firebase_messaging.zzw) r8)
            com.google.firebase.messaging.zzb r5 = new com.google.firebase.messaging.zzb
            r5.<init>(r11, r0)
            com.google.android.gms.internal.firebase_messaging.zzu r0 = r4.zza((com.google.android.gms.internal.firebase_messaging.zzx) r5)
            java.lang.String r4 = "FCM-Notification"
            com.google.android.gms.internal.firebase_messaging.zzu r0 = r0.zzq(r4)
            android.os.Bundle r4 = r11.zzas()
            com.google.android.gms.internal.firebase_messaging.zzu r0 = r0.zzi((android.os.Bundle) r4)
            android.content.Context r4 = r11.zzac
            android.content.res.Resources r4 = r4.getResources()
            com.google.android.gms.internal.firebase_messaging.zzu r0 = r0.zza((android.content.res.Resources) r4)
            com.google.android.gms.internal.firebase_messaging.zzp r4 = new com.google.android.gms.internal.firebase_messaging.zzp
            android.content.Context r5 = r11.zzac
            android.os.Bundle r7 = r11.zzcl
            r4.<init>(r5, r7)
            com.google.android.gms.internal.firebase_messaging.zzu r0 = r0.zza((com.google.android.gms.internal.firebase_messaging.zzy) r4)
            int r4 = r6.targetSdkVersion
            com.google.android.gms.internal.firebase_messaging.zzu r0 = r0.zzd((int) r4)
            com.google.android.gms.internal.firebase_messaging.zzs r0 = r0.zzbe()
            com.google.android.gms.internal.firebase_messaging.zzaa r4 = new com.google.android.gms.internal.firebase_messaging.zzaa
            r4.<init>(r0, r3)
            com.google.android.gms.internal.firebase_messaging.zzr r0 = new com.google.android.gms.internal.firebase_messaging.zzr
            android.content.Context r3 = r11.zzac
            r0.<init>(r3, r4)
            com.google.android.gms.internal.firebase_messaging.zzab r3 = r0.zzax()
            java.lang.String r0 = "FirebaseMessaging"
            r4 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r4)
            if (r0 == 0) goto L_0x0114
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r4 = "Showing notification"
            android.util.Log.d(r0, r4)
        L_0x0114:
            android.content.Context r0 = r11.zzac
            java.lang.String r4 = "notification"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.app.NotificationManager r0 = (android.app.NotificationManager) r0
            java.lang.String r4 = r3.tag
            android.support.v4.app.NotificationCompat$Builder r3 = r3.zzfd
            android.app.Notification r3 = r3.build()
            r0.notify(r4, r2, r3)
            r0 = r1
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzar():boolean");
    }

    private final Bundle zzas() {
        try {
            Bundle bundle = this.zzac.getPackageManager().getApplicationInfo(this.zzac.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle;
            }
            return Bundle.EMPTY;
        } catch (PackageManager.NameNotFoundException e) {
            return Bundle.EMPTY;
        }
    }
}
