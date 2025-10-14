package com.opentok.android.v3.debug;

public class OtLog {

    public enum Level {
        NONE,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        VERBOSE
    }

    public static LogInterface LogToken() {
        return new JavaLogToken(getCallerClassName(), Level.VERBOSE.ordinal());
    }

    public static LogInterface LogToken(Level level) {
        return new JavaLogToken(getCallerClassName(), level.ordinal());
    }

    public static LogInterface LogToken(String name) {
        return new JavaLogToken(name, Level.VERBOSE.ordinal());
    }

    public static LogInterface LogToken(String name, Level level) {
        return new JavaLogToken(name, level.ordinal());
    }

    private static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String otLogClsName = OtLog.class.getName();
        String otLogTokenClsName = OtLog.class.getName();
        boolean belowCurrentFrame = false;
        for (StackTraceElement ste : stElements) {
            if (!belowCurrentFrame && ste.getClassName().equals(otLogTokenClsName)) {
                belowCurrentFrame = true;
            } else if (belowCurrentFrame && !ste.getClassName().equals(otLogTokenClsName) && !ste.getClassName().equals(otLogClsName)) {
                try {
                    return Class.forName(ste.getClassName()).getSimpleName();
                } catch (ClassNotFoundException e) {
                    return ste.getClassName();
                }
            }
        }
        return otLogClsName;
    }
}
