package com.opentok.android.v3.debug;

import com.opentok.android.v3.debug.OtLog;
import java.io.FileDescriptor;
import java.lang.reflect.Field;

class NativeLogger implements LogOutputInterface {
    private int osFd;

    private static native void write(int i, int i2, String str, String str2);

    public void debug(String tag, String message) {
        write(this.osFd, OtLog.Level.DEBUG.ordinal(), tag, message);
    }

    public void info(String tag, String message) {
        write(this.osFd, OtLog.Level.INFO.ordinal(), tag, message);
    }

    public void verbose(String tag, String message) {
        write(this.osFd, OtLog.Level.VERBOSE.ordinal(), tag, message);
    }

    public void warning(String tag, String message) {
        write(this.osFd, OtLog.Level.WARN.ordinal(), tag, message);
    }

    public void error(String tag, String message) {
        write(this.osFd, OtLog.Level.ERROR.ordinal(), tag, message);
    }

    public int outputFD() {
        return this.osFd;
    }

    NativeLogger(FileDescriptor fileDescriptor) {
        this.osFd = -1;
        this.osFd = 1;
        if (fileDescriptor != null) {
            try {
                Field field = fileDescriptor.getClass().getDeclaredField("descriptor");
                field.setAccessible(true);
                this.osFd = field.getInt(fileDescriptor);
                field.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
