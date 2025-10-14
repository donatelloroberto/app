package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

final class zzfl implements zzej {
    private final long zzabf;
    private final int zzabg;
    private double zzabh;
    private final Object zzabj;
    private long zzakm;
    private final Clock zzsd;

    private zzfl(int i, long j) {
        this.zzabj = new Object();
        this.zzabg = 60;
        this.zzabh = (double) this.zzabg;
        this.zzabf = 2000;
        this.zzsd = DefaultClock.getInstance();
    }

    public zzfl() {
        this(60, 2000);
    }

    public final boolean zzfm() {
        boolean z;
        synchronized (this.zzabj) {
            long currentTimeMillis = this.zzsd.currentTimeMillis();
            if (this.zzabh < ((double) this.zzabg)) {
                double d = ((double) (currentTimeMillis - this.zzakm)) / ((double) this.zzabf);
                if (d > 0.0d) {
                    this.zzabh = Math.min((double) this.zzabg, d + this.zzabh);
                }
            }
            this.zzakm = currentTimeMillis;
            if (this.zzabh >= 1.0d) {
                this.zzabh -= 1.0d;
                z = true;
            } else {
                zzdi.zzac("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
