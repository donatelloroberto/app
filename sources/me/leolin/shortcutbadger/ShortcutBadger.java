package me.leolin.shortcutbadger;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.util.LinkedList;
import java.util.List;
import me.leolin.shortcutbadger.impl.AdwHomeBadger;
import me.leolin.shortcutbadger.impl.ApexHomeBadger;
import me.leolin.shortcutbadger.impl.AsusHomeBadger;
import me.leolin.shortcutbadger.impl.DefaultBadger;
import me.leolin.shortcutbadger.impl.EverythingMeHomeBadger;
import me.leolin.shortcutbadger.impl.HuaweiHomeBadger;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import me.leolin.shortcutbadger.impl.NovaHomeBadger;
import me.leolin.shortcutbadger.impl.OPPOHomeBader;
import me.leolin.shortcutbadger.impl.SamsungHomeBadger;
import me.leolin.shortcutbadger.impl.SonyHomeBadger;
import me.leolin.shortcutbadger.impl.VivoHomeBadger;
import me.leolin.shortcutbadger.impl.ZTEHomeBadger;
import me.leolin.shortcutbadger.impl.ZukHomeBadger;

public final class ShortcutBadger {
    private static final List<Class<? extends Badger>> BADGERS = new LinkedList();
    private static final String LOG_TAG = "ShortcutBadger";
    private static final int SUPPORTED_CHECK_ATTEMPTS = 3;
    private static ComponentName sComponentName;
    private static final Object sCounterSupportedLock = new Object();
    private static volatile Boolean sIsBadgeCounterSupported;
    private static Badger sShortcutBadger;

    static {
        BADGERS.add(AdwHomeBadger.class);
        BADGERS.add(ApexHomeBadger.class);
        BADGERS.add(DefaultBadger.class);
        BADGERS.add(NewHtcHomeBadger.class);
        BADGERS.add(NovaHomeBadger.class);
        BADGERS.add(SonyHomeBadger.class);
        BADGERS.add(AsusHomeBadger.class);
        BADGERS.add(HuaweiHomeBadger.class);
        BADGERS.add(OPPOHomeBader.class);
        BADGERS.add(SamsungHomeBadger.class);
        BADGERS.add(ZukHomeBadger.class);
        BADGERS.add(VivoHomeBadger.class);
        BADGERS.add(ZTEHomeBadger.class);
        BADGERS.add(EverythingMeHomeBadger.class);
    }

    public static boolean applyCount(Context context, int badgeCount) {
        try {
            applyCountOrThrow(context, badgeCount);
            return true;
        } catch (ShortcutBadgeException e) {
            if (Log.isLoggable(LOG_TAG, 3)) {
                Log.d(LOG_TAG, "Unable to execute badge", e);
            }
            return false;
        }
    }

    public static void applyCountOrThrow(Context context, int badgeCount) throws ShortcutBadgeException {
        if (sShortcutBadger != null || initBadger(context)) {
            try {
                sShortcutBadger.executeBadge(context, sComponentName, badgeCount);
            } catch (Exception e) {
                throw new ShortcutBadgeException("Unable to execute badge", e);
            }
        } else {
            throw new ShortcutBadgeException("No default launcher available");
        }
    }

    public static boolean removeCount(Context context) {
        return applyCount(context, 0);
    }

    public static void removeCountOrThrow(Context context) throws ShortcutBadgeException {
        applyCountOrThrow(context, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0060, code lost:
        if (sIsBadgeCounterSupported != null) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0062, code lost:
        android.util.Log.w(LOG_TAG, "Badge counter seems not supported for this platform: " + r2);
        sIsBadgeCounterSupported = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isBadgeCounterSupported(android.content.Context r11) {
        /*
            r10 = 3
            java.lang.Boolean r3 = sIsBadgeCounterSupported
            if (r3 != 0) goto L_0x0082
            java.lang.Object r4 = sCounterSupportedLock
            monitor-enter(r4)
            java.lang.Boolean r3 = sIsBadgeCounterSupported     // Catch:{ all -> 0x0094 }
            if (r3 != 0) goto L_0x0081
            r2 = 0
            r1 = 0
        L_0x000e:
            if (r1 >= r10) goto L_0x005e
            java.lang.String r3 = "ShortcutBadger"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008e }
            r5.<init>()     // Catch:{ Exception -> 0x008e }
            java.lang.String r6 = "Checking if platform supports badge counters, attempt "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x008e }
            java.lang.String r6 = "%d/%d."
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x008e }
            r8 = 0
            int r9 = r1 + 1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x008e }
            r7[r8] = r9     // Catch:{ Exception -> 0x008e }
            r8 = 1
            r9 = 3
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x008e }
            r7[r8] = r9     // Catch:{ Exception -> 0x008e }
            java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch:{ Exception -> 0x008e }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x008e }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x008e }
            android.util.Log.i(r3, r5)     // Catch:{ Exception -> 0x008e }
            boolean r3 = initBadger(r11)     // Catch:{ Exception -> 0x008e }
            if (r3 == 0) goto L_0x0089
            me.leolin.shortcutbadger.Badger r3 = sShortcutBadger     // Catch:{ Exception -> 0x008e }
            android.content.ComponentName r5 = sComponentName     // Catch:{ Exception -> 0x008e }
            r6 = 0
            r3.executeBadge(r11, r5, r6)     // Catch:{ Exception -> 0x008e }
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x008e }
            sIsBadgeCounterSupported = r3     // Catch:{ Exception -> 0x008e }
            java.lang.String r3 = "ShortcutBadger"
            java.lang.String r5 = "Badge counter is supported in this platform."
            android.util.Log.i(r3, r5)     // Catch:{ Exception -> 0x008e }
        L_0x005e:
            java.lang.Boolean r3 = sIsBadgeCounterSupported     // Catch:{ all -> 0x0094 }
            if (r3 != 0) goto L_0x0081
            java.lang.String r3 = "ShortcutBadger"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            r5.<init>()     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "Badge counter seems not supported for this platform: "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r5 = r5.append(r2)     // Catch:{ all -> 0x0094 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0094 }
            android.util.Log.w(r3, r5)     // Catch:{ all -> 0x0094 }
            r3 = 0
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x0094 }
            sIsBadgeCounterSupported = r3     // Catch:{ all -> 0x0094 }
        L_0x0081:
            monitor-exit(r4)     // Catch:{ all -> 0x0094 }
        L_0x0082:
            java.lang.Boolean r3 = sIsBadgeCounterSupported
            boolean r3 = r3.booleanValue()
            return r3
        L_0x0089:
            java.lang.String r2 = "Failed to initialize the badge counter."
        L_0x008b:
            int r1 = r1 + 1
            goto L_0x000e
        L_0x008e:
            r0 = move-exception
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x0094 }
            goto L_0x008b
        L_0x0094:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0094 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: me.leolin.shortcutbadger.ShortcutBadger.isBadgeCounterSupported(android.content.Context):boolean");
    }

    public static void applyNotification(Context context, Notification notification, int badgeCount) {
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            try {
                Object extraNotification = notification.getClass().getDeclaredField("extraNotification").get(notification);
                extraNotification.getClass().getDeclaredMethod("setMessageCount", new Class[]{Integer.TYPE}).invoke(extraNotification, new Object[]{Integer.valueOf(badgeCount)});
            } catch (Exception e) {
                if (Log.isLoggable(LOG_TAG, 3)) {
                    Log.d(LOG_TAG, "Unable to execute badge", e);
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v28, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: me.leolin.shortcutbadger.Badger} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean initBadger(android.content.Context r11) {
        /*
            android.content.pm.PackageManager r8 = r11.getPackageManager()
            java.lang.String r9 = r11.getPackageName()
            android.content.Intent r4 = r8.getLaunchIntentForPackage(r9)
            if (r4 != 0) goto L_0x002c
            java.lang.String r8 = "ShortcutBadger"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Unable to find launch intent for package "
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r10 = r11.getPackageName()
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.util.Log.e(r8, r9)
            r8 = 0
        L_0x002b:
            return r8
        L_0x002c:
            android.content.ComponentName r8 = r4.getComponent()
            sComponentName = r8
            android.content.Intent r3 = new android.content.Intent
            java.lang.String r8 = "android.intent.action.MAIN"
            r3.<init>(r8)
            java.lang.String r8 = "android.intent.category.HOME"
            r3.addCategory(r8)
            android.content.pm.PackageManager r8 = r11.getPackageManager()
            r9 = 65536(0x10000, float:9.18355E-41)
            java.util.List r6 = r8.queryIntentActivities(r3, r9)
            java.util.Iterator r9 = r6.iterator()
        L_0x004c:
            boolean r8 = r9.hasNext()
            if (r8 == 0) goto L_0x0089
            java.lang.Object r5 = r9.next()
            android.content.pm.ResolveInfo r5 = (android.content.pm.ResolveInfo) r5
            android.content.pm.ActivityInfo r8 = r5.activityInfo
            java.lang.String r2 = r8.packageName
            java.util.List<java.lang.Class<? extends me.leolin.shortcutbadger.Badger>> r8 = BADGERS
            java.util.Iterator r10 = r8.iterator()
        L_0x0062:
            boolean r8 = r10.hasNext()
            if (r8 == 0) goto L_0x0085
            java.lang.Object r1 = r10.next()
            java.lang.Class r1 = (java.lang.Class) r1
            r7 = 0
            java.lang.Object r8 = r1.newInstance()     // Catch:{ Exception -> 0x00de }
            r0 = r8
            me.leolin.shortcutbadger.Badger r0 = (me.leolin.shortcutbadger.Badger) r0     // Catch:{ Exception -> 0x00de }
            r7 = r0
        L_0x0077:
            if (r7 == 0) goto L_0x0062
            java.util.List r8 = r7.getSupportLaunchers()
            boolean r8 = r8.contains(r2)
            if (r8 == 0) goto L_0x0062
            sShortcutBadger = r7
        L_0x0085:
            me.leolin.shortcutbadger.Badger r8 = sShortcutBadger
            if (r8 == 0) goto L_0x004c
        L_0x0089:
            me.leolin.shortcutbadger.Badger r8 = sShortcutBadger
            if (r8 != 0) goto L_0x009e
            java.lang.String r8 = android.os.Build.MANUFACTURER
            java.lang.String r9 = "ZUK"
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x00a0
            me.leolin.shortcutbadger.impl.ZukHomeBadger r8 = new me.leolin.shortcutbadger.impl.ZukHomeBadger
            r8.<init>()
            sShortcutBadger = r8
        L_0x009e:
            r8 = 1
            goto L_0x002b
        L_0x00a0:
            java.lang.String r8 = android.os.Build.MANUFACTURER
            java.lang.String r9 = "OPPO"
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x00b2
            me.leolin.shortcutbadger.impl.OPPOHomeBader r8 = new me.leolin.shortcutbadger.impl.OPPOHomeBader
            r8.<init>()
            sShortcutBadger = r8
            goto L_0x009e
        L_0x00b2:
            java.lang.String r8 = android.os.Build.MANUFACTURER
            java.lang.String r9 = "VIVO"
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x00c4
            me.leolin.shortcutbadger.impl.VivoHomeBadger r8 = new me.leolin.shortcutbadger.impl.VivoHomeBadger
            r8.<init>()
            sShortcutBadger = r8
            goto L_0x009e
        L_0x00c4:
            java.lang.String r8 = android.os.Build.MANUFACTURER
            java.lang.String r9 = "ZTE"
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x00d6
            me.leolin.shortcutbadger.impl.ZTEHomeBadger r8 = new me.leolin.shortcutbadger.impl.ZTEHomeBadger
            r8.<init>()
            sShortcutBadger = r8
            goto L_0x009e
        L_0x00d6:
            me.leolin.shortcutbadger.impl.DefaultBadger r8 = new me.leolin.shortcutbadger.impl.DefaultBadger
            r8.<init>()
            sShortcutBadger = r8
            goto L_0x009e
        L_0x00de:
            r8 = move-exception
            goto L_0x0077
        */
        throw new UnsupportedOperationException("Method not decompiled: me.leolin.shortcutbadger.ShortcutBadger.initBadger(android.content.Context):boolean");
    }

    private ShortcutBadger() {
    }
}
