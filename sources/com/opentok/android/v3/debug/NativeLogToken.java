package com.opentok.android.v3.debug;

import com.opentok.android.v3.loader.Loader;

class NativeLogToken implements LogTokenInterface {
    private long nativeHndl;
    private LogOutputInterface output;

    private native int getLogLevelNative(long j);

    private native long getTokenNative(String str);

    private static native void registerNatives();

    private native void setLogFileHandleNative(long j, int i);

    private native void setLogLevelNative(long j, int i);

    public void setLevel(int level) {
        setLogLevelNative(this.nativeHndl, level);
    }

    public int level() {
        return getLogLevelNative(this.nativeHndl);
    }

    public void updateFD() {
        setLogFileHandleNative(this.nativeHndl, this.output.outputFD());
    }

    NativeLogToken(String name, int level, LogOutputInterface logInterface) {
        this.nativeHndl = getTokenNative(name);
        setLogLevelNative(this.nativeHndl, level);
        setLogFileHandleNative(this.nativeHndl, logInterface.outputFD());
        this.output = logInterface;
    }

    static {
        Loader.load();
        registerNatives();
    }
}
