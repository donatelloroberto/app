package com.opentok.android.v3.debug;

import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Log implements DebugInterface {
    private static final String PROP_LOGGER_OUTPUT_CLASS = "debug.log.class";
    private static final String PROP_LOGGER_OUTPUT_FILE = "debug.log.file";
    private static final String PROP_LOGGER_OUTPUT_HOST = "debug.log.network.host";
    private static final String PROP_LOGGER_OUTPUT_PORT = "debug.log.network.port";
    private static Log instance = null;
    private int enableLevel = 0;
    private String enableRegex = "";
    private LogOutputWrapper logOutput = new LogOutputWrapper(new AdbLogger());
    private Map<String, LogTokenInterface> sections = new HashMap();

    private static native String[] getNativeSections();

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void setSettings(Properties properties) {
        String logOutputClass = properties.getProperty(PROP_LOGGER_OUTPUT_CLASS, AdbLogger.class.getName());
        String logFile = properties.getProperty(PROP_LOGGER_OUTPUT_FILE, FileLogger.LOG_FILE);
        String logHost = properties.getProperty(PROP_LOGGER_OUTPUT_HOST, NetworkLogger.HOST);
        String logPort = properties.getProperty(PROP_LOGGER_OUTPUT_PORT, NetworkLogger.PORT);
        try {
            if (logOutputClass.equals(AdbLogger.class.getCanonicalName())) {
                this.logOutput.setLogInterface(new AdbLogger());
            } else if (logOutputClass.equals(FileLogger.class.getCanonicalName())) {
                this.logOutput.setLogInterface(new FileLogger(logFile));
            } else if (logOutputClass.equals(NetworkLogger.class.getCanonicalName())) {
                this.logOutput.setLogInterface(new NetworkLogger(logHost, Integer.parseInt(logPort)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.logOutput.setLogInterface(new AdbLogger());
        }
        for (LogTokenInterface token : this.sections.values()) {
            if (token instanceof NativeLogToken) {
                ((NativeLogToken) token).updateFD();
            }
        }
    }

    public synchronized void enableLog(String section, int level) {
        this.enableRegex = section;
        this.enableLevel = level;
        for (String key : this.sections.keySet()) {
            if (key.matches(section)) {
                this.sections.get(key).setLevel(level);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public LogOutputInterface registerLog(String tag, LogTokenInterface token) {
        if (tag.matches(this.enableRegex)) {
            token.setLevel(this.enableLevel);
        }
        this.sections.put(tag, token);
        return new LogOutputWrapper(this.logOutput);
    }

    private Log() {
        setSettings(System.getProperties());
        for (String tag : getNativeSections()) {
            this.sections.put(tag, new NativeLogToken(tag, OtLog.Level.NONE.ordinal(), this.logOutput));
        }
    }

    private static class LogOutputWrapper implements LogOutputInterface {
        private LogOutputInterface logInterface;

        public void debug(String tag, String message) {
            this.logInterface.debug(tag, message);
        }

        public void info(String tag, String message) {
            this.logInterface.info(tag, message);
        }

        public void verbose(String tag, String message) {
            this.logInterface.verbose(tag, message);
        }

        public void warning(String tag, String message) {
            this.logInterface.warning(tag, message);
        }

        public void error(String tag, String message) {
            this.logInterface.error(tag, message);
        }

        public int outputFD() {
            return this.logInterface.outputFD();
        }

        LogOutputWrapper(LogOutputInterface output) {
            this.logInterface = output;
        }

        /* access modifiers changed from: package-private */
        public void setLogInterface(LogOutputInterface output) {
            this.logInterface = output;
        }
    }

    static {
        Loader.load();
    }
}
