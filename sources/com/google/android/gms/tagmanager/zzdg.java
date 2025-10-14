package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzdg implements zzej {
    private final long zzabf = 900000;
    private final int zzabg = 5;
    private double zzabh = ((double) Math.min(1, 5));
    private long zzabi;
    private final Object zzabj = new Object();
    private final long zzaia = 5000;
    private final Clock zzsd;
    private final String zzup;

    public zzdg(int i, int i2, long j, long j2, String str, Clock clock) {
        this.zzup = str;
        this.zzsd = clock;
    }

    public final boolean zzfm() {
        boolean z = false;
        synchronized (this.zzabj) {
            long currentTimeMillis = this.zzsd.currentTimeMillis();
            if (currentTimeMillis - this.zzabi < this.zzaia) {
                String str = this.zzup;
                zzdi.zzac(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
            } else {
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
                    String str2 = this.zzup;
                    zzdi.zzac(new StringBuilder(String.valueOf(str2).length() + 34).append("Excessive ").append(str2).append(" detected; call ignored.").toString());
                }
            }
        }
        return z;
    }
}
