package com.google.android.gms.internal.firebase_messaging;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class zzf extends Handler {
    private static volatile zzg zzf = null;

    public zzf() {
    }

    public zzf(Looper looper) {
        super(looper);
    }

    public zzf(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }

    public final void dispatchMessage(Message message) {
        super.dispatchMessage(message);
    }
}
