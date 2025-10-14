package com.amazon.device.ads;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

class Settings {
    private static final String LOGTAG = Settings.class.getSimpleName();
    private static final String PREFS_NAME = "AmazonMobileAds";
    public static final String SETTING_ENABLE_WEBVIEW_PAUSE_LOGIC = "shouldPauseWebViewTimersInWebViewRelatedActivities";
    protected static final String SETTING_TESTING_ENABLED = "testingEnabled";
    protected static final String SETTING_TLS_ENABLED = "tlsEnabled";
    private static Settings instance = new Settings();
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<String, Value> cache = new ConcurrentHashMap<>();
    private ArrayList<SettingsListener> listeners = new ArrayList<>();
    private final ReentrantLock listenersLock = new ReentrantLock();
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private final CountDownLatch settingsLoadedLatch = new CountDownLatch(1);
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public final ReentrantLock writeToSharedPreferencesLock = new ReentrantLock();

    public interface SettingsListener {
        void settingsLoaded();
    }

    protected Settings() {
    }

    public static Settings getInstance() {
        return instance;
    }

    /* access modifiers changed from: package-private */
    public void contextReceived(Context context) {
        if (context != null) {
            beginFetch(context);
        }
    }

    /* access modifiers changed from: package-private */
    public void beginFetch(final Context context) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                Settings.this.fetchSharedPreferences(context);
            }
        });
    }

    public boolean isSettingsLoaded() {
        return this.sharedPreferences != null;
    }

    public void listenForSettings(SettingsListener listener) {
        this.listenersLock.lock();
        if (isSettingsLoaded()) {
            listener.settingsLoaded();
        } else {
            this.listeners.add(listener);
        }
        this.listenersLock.unlock();
    }

    /* access modifiers changed from: package-private */
    public SharedPreferences getSharedPreferencesFromContext(Context context) {
        return context.getSharedPreferences(PREFS_NAME, 0);
    }

    /* access modifiers changed from: package-private */
    public SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    /* access modifiers changed from: package-private */
    public ConcurrentHashMap<String, Value> getCache() {
        return this.cache;
    }

    private void putSetting(String key, Value value) {
        if (value.value == null) {
            this.logger.w("Could not set null value for setting: %s", key);
            return;
        }
        putSettingWithNoFlush(key, value);
        if (!value.isTransientData && isSettingsLoaded()) {
            flush();
        }
    }

    private void putSettingWithNoFlush(String key, Value value) {
        if (value.value == null) {
            this.logger.w("Could not set null value for setting: %s", key);
            return;
        }
        this.cache.put(key, value);
    }

    /* access modifiers changed from: package-private */
    public void readSharedPreferencesIntoCache(SharedPreferences sharedPreferences2) {
        cacheAdditionalEntries(sharedPreferences2.getAll());
    }

    /* access modifiers changed from: package-private */
    public void cacheAdditionalEntries(Map<String, ?> entries) {
        for (Map.Entry<String, ?> entry : entries.entrySet()) {
            String key = entry.getKey();
            if (key != null && !this.cache.containsKey(key)) {
                Object value = entry.getValue();
                if (value != null) {
                    this.cache.put(key, new Value(value.getClass(), value));
                } else {
                    this.logger.w("Could not cache null value for SharedPreferences setting: %s", key);
                }
            }
        }
    }

    private void writeCacheToSharedPreferences() {
        writeCacheToSharedPreferences(this.sharedPreferences);
    }

    /* access modifiers changed from: package-private */
    public void writeCacheToSharedPreferences(final SharedPreferences sharedPreferences2) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                Settings.this.writeToSharedPreferencesLock.lock();
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.clear();
                for (Map.Entry<String, Value> entry : Settings.this.cache.entrySet()) {
                    Value value = entry.getValue();
                    if (!value.isTransientData) {
                        if (value.clazz == String.class) {
                            editor.putString(entry.getKey(), (String) value.value);
                        } else if (value.clazz == Long.class) {
                            editor.putLong(entry.getKey(), ((Long) value.value).longValue());
                        } else if (value.clazz == Integer.class) {
                            editor.putInt(entry.getKey(), ((Integer) value.value).intValue());
                        } else if (value.clazz == Boolean.class) {
                            editor.putBoolean(entry.getKey(), ((Boolean) value.value).booleanValue());
                        }
                    }
                }
                Settings.this.commit(editor);
                Settings.this.writeToSharedPreferencesLock.unlock();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void flush() {
        writeCacheToSharedPreferences();
    }

    public boolean containsKey(String key) {
        return this.cache.containsKey(key);
    }

    public String getString(String key, String defaultValue) {
        Value value = this.cache.get(key);
        return value == null ? defaultValue : (String) value.value;
    }

    /* access modifiers changed from: package-private */
    public void putString(String key, String value) {
        putSetting(key, new Value(String.class, value));
    }

    /* access modifiers changed from: package-private */
    public void putStringWithNoFlush(String key, String value) {
        putSettingWithNoFlush(key, new Value(String.class, value));
    }

    /* access modifiers changed from: package-private */
    public void putTransientString(String key, String value) {
        putSettingWithNoFlush(key, new TransientValue(String.class, value));
    }

    public String getWrittenString(String key, String defaultValue) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getString(key, defaultValue);
        }
        return defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        Value value = this.cache.get(key);
        return value == null ? defaultValue : ((Integer) value.value).intValue();
    }

    /* access modifiers changed from: package-private */
    public void putInt(String key, int value) {
        putSetting(key, new Value(Integer.class, Integer.valueOf(value)));
    }

    /* access modifiers changed from: package-private */
    public void putIntWithNoFlush(String key, int value) {
        putSettingWithNoFlush(key, new Value(Integer.class, Integer.valueOf(value)));
    }

    /* access modifiers changed from: package-private */
    public void putTransientInt(String key, int value) {
        putSettingWithNoFlush(key, new TransientValue(Integer.class, Integer.valueOf(value)));
    }

    public int getWrittenInt(String key, int defaultValue) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    public long getLong(String key, long defaultValue) {
        Value value = this.cache.get(key);
        return value == null ? defaultValue : ((Long) value.value).longValue();
    }

    /* access modifiers changed from: package-private */
    public void putLong(String key, long value) {
        putSetting(key, new Value(Long.class, Long.valueOf(value)));
    }

    /* access modifiers changed from: package-private */
    public void putLongWithNoFlush(String key, long value) {
        putSettingWithNoFlush(key, new Value(Long.class, Long.valueOf(value)));
    }

    /* access modifiers changed from: package-private */
    public void putTransientLong(String key, long value) {
        putSettingWithNoFlush(key, new TransientValue(Long.class, Long.valueOf(value)));
    }

    public long getWrittenLong(String key, long defaultValue) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getLong(key, defaultValue);
        }
        return defaultValue;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Boolean value = getBoolean(key, (Boolean) null);
        return value == null ? defaultValue : value.booleanValue();
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        Value value = this.cache.get(key);
        return value == null ? defaultValue : (Boolean) value.value;
    }

    /* access modifiers changed from: package-private */
    public void putBoolean(String key, boolean value) {
        putSetting(key, new Value(Boolean.class, Boolean.valueOf(value)));
    }

    /* access modifiers changed from: package-private */
    public void putBooleanWithNoFlush(String key, boolean value) {
        putSettingWithNoFlush(key, new Value(Boolean.class, Boolean.valueOf(value)));
    }

    /* access modifiers changed from: package-private */
    public void putTransientBoolean(String key, boolean value) {
        putSettingWithNoFlush(key, new TransientValue(Boolean.class, Boolean.valueOf(value)));
    }

    public boolean getWrittenBoolean(String key, boolean defaultValue) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    /* access modifiers changed from: package-private */
    public void remove(String key) {
        Value value = this.cache.remove(key);
        if (value != null && !value.isTransientData && isSettingsLoaded()) {
            flush();
        }
    }

    /* access modifiers changed from: package-private */
    public void removeWithNoFlush(String key) {
        this.cache.remove(key);
    }

    /* access modifiers changed from: private */
    public void commit(SharedPreferences.Editor editor) {
        if (ThreadUtils.isOnMainThread()) {
            this.logger.e("Committing settings must be executed on a background thread.");
        }
        if (AndroidTargetUtils.isAtLeastAndroidAPI(9)) {
            AndroidTargetUtils.editorApply(editor);
        } else {
            editor.commit();
        }
    }

    /* access modifiers changed from: package-private */
    public void notifySettingsListeners() {
        this.listenersLock.lock();
        Iterator i$ = this.listeners.iterator();
        while (i$.hasNext()) {
            i$.next().settingsLoaded();
        }
        this.listeners.clear();
        this.listeners = null;
        this.listenersLock.unlock();
    }

    class Value {
        public Class<?> clazz;
        public boolean isTransientData;
        public Object value;

        public Value(Class<?> clazz2, Object value2) {
            this.clazz = clazz2;
            this.value = value2;
        }
    }

    class TransientValue extends Value {
        public TransientValue(Class<?> clazz, Object value) {
            super(clazz, value);
            this.isTransientData = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void fetchSharedPreferences(Context context) {
        if (!isSettingsLoaded()) {
            SharedPreferences sharedPreferences2 = getSharedPreferencesFromContext(context);
            readSharedPreferencesIntoCache(sharedPreferences2);
            this.sharedPreferences = sharedPreferences2;
            writeCacheToSharedPreferences(sharedPreferences2);
        }
        this.settingsLoadedLatch.countDown();
        notifySettingsListeners();
    }
}
