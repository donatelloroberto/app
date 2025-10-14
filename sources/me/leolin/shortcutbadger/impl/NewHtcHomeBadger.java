package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Collections;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

public class NewHtcHomeBadger implements Badger {
    public static final String COUNT = "count";
    public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
    public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";
    public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
    public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
    public static final String PACKAGENAME = "packagename";

    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        boolean intent1Success;
        boolean intentSuccess;
        Intent intent1 = new Intent(INTENT_SET_NOTIFICATION);
        intent1.putExtra(EXTRA_COMPONENT, componentName.flattenToShortString());
        intent1.putExtra(EXTRA_COUNT, badgeCount);
        Intent intent = new Intent(INTENT_UPDATE_SHORTCUT);
        intent.putExtra(PACKAGENAME, componentName.getPackageName());
        intent.putExtra("count", badgeCount);
        try {
            BroadcastHelper.sendIntentExplicitly(context, intent1);
            intent1Success = true;
        } catch (ShortcutBadgeException e) {
            intent1Success = false;
        }
        try {
            BroadcastHelper.sendIntentExplicitly(context, intent);
            intentSuccess = true;
        } catch (ShortcutBadgeException e2) {
            intentSuccess = false;
        }
        if (!intent1Success && !intentSuccess) {
            throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
        }
    }

    public List<String> getSupportLaunchers() {
        return Collections.singletonList("com.htc.launcher");
    }
}
