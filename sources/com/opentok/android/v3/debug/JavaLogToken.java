package com.opentok.android.v3.debug;

import com.opentok.android.v3.debug.OtLog;
import java.io.PrintWriter;
import java.io.StringWriter;

class JavaLogToken implements LogInterface {
    private int level = (OtLog.Level.ERROR.ordinal() + 1);
    private final LogOutputInterface logInterface;
    private String tag;

    public void setLevel(int level2) {
        this.level = level2;
    }

    public int level() {
        return this.level;
    }

    public void d(String message, Object... args) {
        if (OtLog.Level.DEBUG.ordinal() <= this.level) {
            this.logInterface.debug(this.tag, String.format(message, args));
        }
    }

    public void d(Throwable throwable, String message, Object... args) {
        if (OtLog.Level.DEBUG.ordinal() <= this.level) {
            d(this.tag, appendStackTraceString(message, throwable), args);
        }
    }

    public void i(String message, Object... args) {
        if (OtLog.Level.INFO.ordinal() <= this.level) {
            this.logInterface.info(this.tag, String.format(message, args));
        }
    }

    public void i(Throwable throwable, String message, Object... args) {
        if (OtLog.Level.INFO.ordinal() <= this.level) {
            i(this.tag, appendStackTraceString(message, throwable), args);
        }
    }

    public void v(String message, Object... args) {
        if (OtLog.Level.VERBOSE.ordinal() <= this.level) {
            this.logInterface.verbose(this.tag, String.format(message, args));
        }
    }

    public void v(Throwable throwable, String message, Object... args) {
        if (OtLog.Level.VERBOSE.ordinal() <= this.level) {
            v(this.tag, appendStackTraceString(message, throwable), args);
        }
    }

    public void w(String message, Object... args) {
        if (OtLog.Level.WARN.ordinal() <= this.level) {
            this.logInterface.warning(this.tag, String.format(message, args));
        }
    }

    public void w(Throwable throwable, String message, Object... args) {
        if (OtLog.Level.WARN.ordinal() <= this.level) {
            w(this.tag, appendStackTraceString(message, throwable), args);
        }
    }

    public void e(String message, Object... args) {
        if (OtLog.Level.ERROR.ordinal() <= this.level) {
            this.logInterface.error(this.tag, String.format(message, args));
        }
    }

    public void e(Throwable throwable, String message, Object... args) {
        if (OtLog.Level.ERROR.ordinal() <= this.level) {
            e(this.tag, appendStackTraceString(message, throwable), args);
        }
    }

    JavaLogToken(String name, int level2) {
        this.tag = name;
        this.logInterface = Log.getInstance().registerLog(name, this);
    }

    private static String appendStackTraceString(String message, Throwable throwable) {
        StringWriter sw = new StringWriter(256);
        PrintWriter pw = new PrintWriter(sw, false);
        throwable.printStackTrace(pw);
        pw.flush();
        if (message != null) {
            return message + "\n" + sw.toString();
        }
        return sw.toString();
    }
}
