package com.opentok.android.v3.debug;

import java.io.FileDescriptor;

class AdbLogger extends NativeLogger {
    AdbLogger() {
        super((FileDescriptor) null);
    }
}
