package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.google.firebase.analytics.FirebaseAnalytics;

public final class zza {
    private static final IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzgt;
    private static float zzgu = Float.NaN;

    @TargetApi(20)
    public static int zzg(Context context) {
        boolean z;
        boolean isScreenOn;
        int i;
        int i2 = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, filter);
        if (((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0) {
            z = true;
        } else {
            z = false;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            isScreenOn = powerManager.isInteractive();
        } else {
            isScreenOn = powerManager.isScreenOn();
        }
        if (isScreenOn) {
            i = 1;
        } else {
            i = 0;
        }
        int i3 = i << 1;
        if (!z) {
            i2 = 0;
        }
        return i3 | i2;
    }

    public static synchronized float zzh(Context context) {
        float f;
        synchronized (zza.class) {
            if (SystemClock.elapsedRealtime() - zzgt >= 60000 || Float.isNaN(zzgu)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, filter);
                if (registerReceiver != null) {
                    zzgu = ((float) registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzgt = SystemClock.elapsedRealtime();
                f = zzgu;
            } else {
                f = zzgu;
            }
        }
        return f;
    }
}
