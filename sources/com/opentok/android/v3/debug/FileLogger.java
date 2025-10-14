package com.opentok.android.v3.debug;

import android.os.Environment;
import android.support.annotation.NonNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class FileLogger extends NativeLogger {
    public static final String LOG_FILE = "opentok-log.txt";
    private FileOutputStream fileOutputStream;

    FileLogger(@NonNull String fileName) throws IOException {
        this(setup(fileName));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private FileLogger(FileOutputStream stream) throws IOException {
        super(stream != null ? stream.getFD() : null);
        this.fileOutputStream = stream;
    }

    private static FileOutputStream setup(String fileName) {
        try {
            File logFile = new File(Environment.getExternalStorageDirectory() + "/" + fileName);
            if (logFile.exists()) {
                logFile.delete();
            }
            return new FileOutputStream(logFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
