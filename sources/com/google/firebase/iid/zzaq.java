package com.google.firebase.iid;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzaq {
    private final Executor executor;
    @GuardedBy("this")
    private final Map<Pair<String, String>, Task<InstanceIdResult>> zzcs = new ArrayMap();

    zzaq(Executor executor2) {
        this.executor = executor2;
    }

    /* access modifiers changed from: package-private */
    public final synchronized Task<InstanceIdResult> zza(String str, String str2, zzas zzas) {
        Task<TContinuationResult> task;
        Pair pair = new Pair(str, str2);
        task = this.zzcs.get(pair);
        if (task == null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(pair);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Making new request for: ").append(valueOf).toString());
            }
            task = zzas.zzs().continueWithTask(this.executor, new zzar(this, pair));
            this.zzcs.put(pair, task);
        } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf2 = String.valueOf(pair);
            Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf2).length() + 29).append("Joining ongoing request for: ").append(valueOf2).toString());
        }
        return task;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task zza(Pair pair, Task task) throws Exception {
        synchronized (this) {
            this.zzcs.remove(pair);
        }
        return task;
    }
}
