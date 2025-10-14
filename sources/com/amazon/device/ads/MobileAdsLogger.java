package com.amazon.device.ads;

import java.util.ArrayList;

class MobileAdsLogger implements Logger {
    private static final String DEFAULT_LOGTAG_PREFIX = "AmazonMobileAds";
    private static final int DEFAULT_MAX_LENGTH = 1000;
    static final String LOGGING_ENABLED = "loggingEnabled";
    private final DebugProperties debugProperties;
    private final Logger logger;
    private int maxLength;
    private final Settings settings;

    public enum Level {
        DEBUG,
        ERROR,
        INFO,
        VERBOSE,
        WARN
    }

    public MobileAdsLogger(Logger logger2) {
        this(logger2, DebugProperties.getInstance(), Settings.getInstance());
    }

    MobileAdsLogger(Logger logger2, DebugProperties debugProperties2, Settings settings2) {
        this.maxLength = 1000;
        this.logger = logger2.withLogTag(DEFAULT_LOGTAG_PREFIX);
        this.debugProperties = debugProperties2;
        this.settings = settings2;
    }

    public MobileAdsLogger withMaxLength(int maxLength2) {
        this.maxLength = maxLength2;
        return this;
    }

    public MobileAdsLogger withLogTag(String logTag) {
        this.logger.withLogTag("AmazonMobileAds " + logTag);
        return this;
    }

    public void enableLogging(boolean enabled) {
        this.settings.putTransientBoolean(LOGGING_ENABLED, enabled);
    }

    public boolean canLog() {
        if (this.logger == null) {
            return false;
        }
        return this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_LOGGING, Boolean.valueOf(this.settings.getBoolean(LOGGING_ENABLED, false))).booleanValue();
    }

    public final void enableLoggingWithSetterNotification(boolean enable) {
        if (!enable) {
            logSetterNotification("Debug logging", Boolean.valueOf(enable));
        }
        enableLogging(enable);
        if (enable) {
            logSetterNotification("Debug logging", Boolean.valueOf(enable));
            d("Amazon Mobile Ads API Version: %s", Version.getRawSDKVersion());
        }
    }

    public void logSetterNotification(String capitalizedSingularPropertyName, Object value) {
        if (canLog()) {
            if (value instanceof Boolean) {
                Object[] objArr = new Object[2];
                objArr[0] = capitalizedSingularPropertyName;
                objArr[1] = ((Boolean) value).booleanValue() ? "enabled" : "disabled";
                d("%s has been %s.", objArr);
                return;
            }
            d("%s has been set: %s", capitalizedSingularPropertyName, String.valueOf(value));
        }
    }

    public void i(String message, Object... params) {
        log(Level.INFO, message, params);
    }

    public void v(String message, Object... params) {
        log(Level.VERBOSE, message, params);
    }

    public void d(String message, Object... params) {
        log(Level.DEBUG, message, params);
    }

    public void w(String message, Object... params) {
        log(Level.WARN, message, params);
    }

    public void e(String message, Object... params) {
        log(Level.ERROR, message, params);
    }

    public void i(String message) {
        i(message, (Object[]) null);
    }

    public void v(String message) {
        v(message, (Object[]) null);
    }

    public void d(String message) {
        d(message, (Object[]) null);
    }

    public void w(String message) {
        w(message, (Object[]) null);
    }

    public void e(String message) {
        e(message, (Object[]) null);
    }

    public void log(Level level, String message, Object... params) {
        doLog(false, level, message, params);
    }

    public void forceLog(Level level, String message, Object... params) {
        doLog(true, level, message, params);
    }

    private void doLog(boolean force, Level level, String message, Object... params) {
        if (canLog() || force) {
            for (String data : formatAndSplit(message, params)) {
                switch (level) {
                    case DEBUG:
                        this.logger.d(data);
                        break;
                    case ERROR:
                        this.logger.e(data);
                        break;
                    case INFO:
                        this.logger.i(data);
                        break;
                    case VERBOSE:
                        this.logger.v(data);
                        break;
                    case WARN:
                        this.logger.w(data);
                        break;
                }
            }
        }
    }

    private Iterable<String> formatAndSplit(String msg, Object... params) {
        if (params != null && params.length > 0) {
            msg = String.format(msg, params);
        }
        return split(msg, this.maxLength);
    }

    private Iterable<String> split(String input, int maxLength2) {
        ArrayList<String> output = new ArrayList<>();
        if (!(input == null || input.length() == 0)) {
            int i = 0;
            while (i < input.length()) {
                output.add(input.substring(i, Math.min(input.length(), i + maxLength2)));
                i += maxLength2;
            }
        }
        return output;
    }
}
