package com.opentok.android.v3.debug;

public class DebugSystem {
    public static DebugInterface getDebugInterface() {
        return Log.getInstance();
    }
}
