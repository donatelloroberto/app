package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class ZTEHomeBadger implements Badger {
    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        Bundle extra = new Bundle();
        extra.putInt("app_badge_count", badgeCount);
        extra.putString("app_badge_component_name", componentName.flattenToString());
        if (Build.VERSION.SDK_INT >= 11) {
            context.getContentResolver().call(Uri.parse("content://com.android.launcher3.cornermark.unreadbadge"), "setAppUnreadCount", (String) null, extra);
        }
    }

    public List<String> getSupportLaunchers() {
        return new ArrayList(0);
    }
}
