package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzcg {
    private final long zzabf;
    private final int zzabg;
    private double zzabh;
    private long zzabi;
    private final Object zzabj;
    private final Clock zzsd;
    private final String zzup;

    private zzcg(int i, long j, String str, Clock clock) {
        this.zzabj = new Object();
        this.zzabg = 60;
        this.zzabh = (double) this.zzabg;
        this.zzabf = 2000;
        this.zzup = str;
        this.zzsd = clock;
    }

    public zzcg(String str, Clock clock) {
        this(60, 2000, str, clock);
    }

    public final boolean zzfm() {
        boolean z;
        synchronized (this.zzabj) {
            long currentTimeMillis = this.zzsd.currentTimeMillis();
            if (this.zzabh < ((double) this.zzabg)) {
                double d = ((double) (currentTimeMillis - this.zzabi)) / ((double) this.zzabf);
                if (d > 0.0d) {
                    this.zzabh = Math.min((double) this.zzabg, d + this.zzabh);
                }
            }
            this.zzabi = currentTimeMillis;
            if (this.zzabh >= 1.0d) {
                this.zzabh -= 1.0d;
                z = true;
            } else {
                String str = this.zzup;
                zzch.zzac(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                z = false;
            }
        }
        return z;
    }
}
