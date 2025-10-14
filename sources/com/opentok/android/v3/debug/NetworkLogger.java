package com.opentok.android.v3.debug;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.net.Socket;

public class NetworkLogger extends NativeLogger {
    public static final String HOST = "192.168.1.1";
    public static final String PORT = "8080";
    private static Socket socket;

    public /* bridge */ /* synthetic */ void debug(String str, String str2) {
        super.debug(str, str2);
    }

    public /* bridge */ /* synthetic */ void error(String str, String str2) {
        super.error(str, str2);
    }

    public /* bridge */ /* synthetic */ void info(String str, String str2) {
        super.info(str, str2);
    }

    public /* bridge */ /* synthetic */ int outputFD() {
        return super.outputFD();
    }

    public /* bridge */ /* synthetic */ void verbose(String str, String str2) {
        super.verbose(str, str2);
    }

    public /* bridge */ /* synthetic */ void warning(String str, String str2) {
        super.warning(str, str2);
    }

    NetworkLogger(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    private NetworkLogger(Socket socket2) {
        super(ParcelFileDescriptor.fromSocket(socket2).getFileDescriptor());
    }
}
