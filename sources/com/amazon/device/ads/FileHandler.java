package com.amazon.device.ads;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

abstract class FileHandler implements Closeable {
    private static final String LOGTAG = FileHandler.class.getSimpleName();
    File file;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    public abstract void close();

    /* access modifiers changed from: protected */
    public abstract Closeable getCloseableReaderWriter();

    /* access modifiers changed from: protected */
    public abstract Closeable getCloseableStream();

    public abstract boolean isOpen();

    FileHandler() {
    }

    public boolean setFile(File parent, String child) {
        return setFile(new File(parent, child));
    }

    public boolean setFile(String fileName) {
        return setFile(new File(fileName));
    }

    public boolean setFile(File file2) {
        if (!isFileSet()) {
            this.file = file2;
            return true;
        } else if (file2.getAbsolutePath().equals(this.file.getAbsolutePath())) {
            return true;
        } else {
            this.logger.e("Another file is already set in this FileOutputHandler. Close the other file before opening a new one.");
            return false;
        }
    }

    public boolean isFileSet() {
        return this.file != null;
    }

    public long getFileLength() {
        if (isFileSet()) {
            return this.file.length();
        }
        throw new IllegalStateException("A file has not been set, yet.");
    }

    public boolean doesFileExist() {
        if (isFileSet()) {
            return this.file.exists();
        }
        throw new IllegalStateException("A file has not been set, yet.");
    }

    /* access modifiers changed from: protected */
    public void closeCloseables() {
        Closeable readerWriter = getCloseableReaderWriter();
        if (readerWriter != null) {
            try {
                readerWriter.close();
            } catch (IOException e) {
                this.logger.e("Could not close the %s. %s", readerWriter.getClass().getSimpleName(), e.getMessage());
                closeStream();
            }
        } else {
            closeStream();
        }
    }

    private void closeStream() {
        Closeable stream = getCloseableStream();
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                this.logger.e("Could not close the stream. %s", e.getMessage());
            }
        }
    }
}
