package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzak<T> {
    final int what;
    final int zzcj;
    final TaskCompletionSource<T> zzck = new TaskCompletionSource<>();
    final Bundle zzcl;

    zzak(int i, int i2, Bundle bundle) {
        this.zzcj = i;
        this.what = i2;
        this.zzcl = bundle;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean zzab();

    /* access modifiers changed from: package-private */
    public abstract void zzb(Bundle bundle);

    /* access modifiers changed from: package-private */
    public final void finish(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(t);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(valueOf2).length()).append("Finishing ").append(valueOf).append(" with ").append(valueOf2).toString());
        }
        this.zzck.setResult(t);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzal zzal) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzal);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length()).append("Failing ").append(valueOf).append(" with ").append(valueOf2).toString());
        }
        this.zzck.setException(zzal);
    }

    public String toString() {
        int i = this.what;
        int i2 = this.zzcj;
        return new StringBuilder(55).append("Request { what=").append(i).append(" id=").append(i2).append(" oneWay=").append(zzab()).append("}").toString();
    }
}
