package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;

@KeepForSdk
public class InstantApps {
    private static Context zzht;
    private static Boolean zzhu;

    @KeepForSdk
    public static synchronized boolean isInstantApp(Context context) {
        boolean booleanValue;
        synchronized (InstantApps.class) {
            Context applicationContext = context.getApplicationContext();
            if (zzht == null || zzhu == null || zzht != applicationContext) {
                zzhu = null;
                if (PlatformVersion.isAtLeastO()) {
                    zzhu = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        zzhu = true;
                    } catch (ClassNotFoundException e) {
                        zzhu = false;
                    }
                }
                zzht = applicationContext;
                booleanValue = zzhu.booleanValue();
            } else {
                booleanValue = zzhu.booleanValue();
            }
        }
        return booleanValue;
    }
}
