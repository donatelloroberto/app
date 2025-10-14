package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class NovaHomeBadger implements Badger {
    private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
    private static final String COUNT = "count";
    private static final String TAG = "tag";

    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TAG, componentName.getPackageName() + "/" + componentName.getClassName());
        contentValues.put("count", Integer.valueOf(badgeCount));
        context.getContentResolver().insert(Uri.parse(CONTENT_URI), contentValues);
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.teslacoilsw.launcher"});
    }
}
