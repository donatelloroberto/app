package com.opentok.android.v3.debug;

interface LogOutputInterface {
    void debug(String str, String str2);

    void error(String str, String str2);

    void info(String str, String str2);

    int outputFD();

    void verbose(String str, String str2);

    void warning(String str, String str2);
}
