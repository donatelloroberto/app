package me.leolin.shortcutbadger.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import java.util.Collections;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.impl.IntentConstants;

public class BroadcastHelper {
    public static List<ResolveInfo> resolveBroadcast(Context context, Intent intent) {
        List<ResolveInfo> receivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        return receivers != null ? receivers : Collections.emptyList();
    }

    public static void sendIntentExplicitly(Context context, Intent intent) throws ShortcutBadgeException {
        List<ResolveInfo> resolveInfos = resolveBroadcast(context, intent);
        if (resolveInfos.size() == 0) {
            throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
        }
        for (ResolveInfo info : resolveInfos) {
            Intent actualIntent = new Intent(intent);
            if (info != null) {
                actualIntent.setPackage(info.resolvePackageName);
                context.sendBroadcast(actualIntent);
            }
        }
    }

    public static void sendDefaultIntentExplicitly(Context context, Intent intent) throws ShortcutBadgeException {
        boolean oreoIntentSuccess = false;
        if (Build.VERSION.SDK_INT >= 26) {
            Intent oreoIntent = new Intent(intent);
            oreoIntent.setAction(IntentConstants.DEFAULT_OREO_INTENT_ACTION);
            try {
                sendIntentExplicitly(context, oreoIntent);
                oreoIntentSuccess = true;
            } catch (ShortcutBadgeException e) {
                oreoIntentSuccess = false;
            }
        }
        if (!oreoIntentSuccess) {
            sendIntentExplicitly(context, intent);
        }
    }
}
