package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

final class zzfs implements Handler.Callback {
    private final /* synthetic */ zzfr zzala;

    zzfs(zzfr zzfr) {
        this.zzala = zzfr;
    }

    public final boolean handleMessage(Message message) {
        if (1 == message.what && zzfn.zzakn.equals(message.obj)) {
            this.zzala.zzakz.dispatch();
            if (!this.zzala.zzakz.isPowerSaveMode()) {
                this.zzala.zzh((long) this.zzala.zzakz.zzakr);
            }
        }
        return true;
    }
}
