package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.firebase_messaging.zzf;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

final class zzad implements ServiceConnection {
    @GuardedBy("this")
    int state;
    final Messenger zzcb;
    zzai zzcc;
    @GuardedBy("this")
    final Queue<zzak<?>> zzcd;
    @GuardedBy("this")
    final SparseArray<zzak<?>> zzce;
    final /* synthetic */ zzab zzcf;

    private zzad(zzab zzab) {
        this.zzcf = zzab;
        this.state = 0;
        this.zzcb = new Messenger(new zzf(Looper.getMainLooper(), new zzae(this)));
        this.zzcd = new ArrayDeque();
        this.zzce = new SparseArray<>();
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzb(zzak zzak) {
        boolean z = false;
        boolean z2 = true;
        synchronized (this) {
            switch (this.state) {
                case 0:
                    this.zzcd.add(zzak);
                    if (this.state == 0) {
                        z = true;
                    }
                    Preconditions.checkState(z);
                    if (Log.isLoggable("MessengerIpcClient", 2)) {
                        Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                    }
                    this.state = 1;
                    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent.setPackage("com.google.android.gms");
                    if (ConnectionTracker.getInstance().bindService(this.zzcf.zzac, intent, this, 1)) {
                        this.zzcf.zzby.schedule(new zzaf(this), 30, TimeUnit.SECONDS);
                        break;
                    } else {
                        zza(0, "Unable to bind to service");
                        break;
                    }
                case 1:
                    this.zzcd.add(zzak);
                    break;
                case 2:
                    this.zzcd.add(zzak);
                    zzy();
                    break;
                case 3:
                case 4:
                    z2 = false;
                    break;
                default:
                    throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.state).toString());
            }
        }
        return z2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", new StringBuilder(41).append("Received response to request: ").append(i).toString());
        }
        synchronized (this) {
            zzak zzak = this.zzce.get(i);
            if (zzak == null) {
                Log.w("MessengerIpcClient", new StringBuilder(50).append("Received response for unknown request: ").append(i).toString());
            } else {
                this.zzce.remove(i);
                zzz();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    zzak.zza(new zzal(4, "Not supported by GmsCore"));
                } else {
                    zzak.zzb(data);
                }
            }
        }
        return true;
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zza(0, "Null service connection");
        } else {
            try {
                this.zzcc = new zzai(iBinder);
                this.state = 2;
                zzy();
            } catch (RemoteException e) {
                zza(0, e.getMessage());
            }
        }
        return;
    }

    private final void zzy() {
        this.zzcf.zzby.execute(new zzag(this));
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zza(2, "Service disconnected");
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        switch (this.state) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                ConnectionTracker.getInstance().unbindService(this.zzcf.zzac, this);
                zzal zzal = new zzal(i, str);
                for (zzak zza : this.zzcd) {
                    zza.zza(zzal);
                }
                this.zzcd.clear();
                for (int i2 = 0; i2 < this.zzce.size(); i2++) {
                    this.zzce.valueAt(i2).zza(zzal);
                }
                this.zzce.clear();
                break;
            case 3:
                this.state = 4;
                break;
            case 4:
                break;
            default:
                throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.state).toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzz() {
        if (this.state == 2 && this.zzcd.isEmpty() && this.zzce.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker.getInstance().unbindService(this.zzcf.zzac, this);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzaa() {
        if (this.state == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(int i) {
        zzak zzak = this.zzce.get(i);
        if (zzak != null) {
            Log.w("MessengerIpcClient", new StringBuilder(31).append("Timing out request: ").append(i).toString());
            this.zzce.remove(i);
            zzak.zza(new zzal(3, "Timed out waiting for response"));
            zzz();
        }
    }
}
