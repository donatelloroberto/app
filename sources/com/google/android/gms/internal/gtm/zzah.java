package com.google.android.gms.internal.gtm;

final class zzah implements Runnable {
    private final /* synthetic */ zzae zzvw;
    private final /* synthetic */ String zzvy;
    private final /* synthetic */ Runnable zzvz;

    zzah(zzae zzae, String str, Runnable runnable) {
        this.zzvw = zzae;
        this.zzvy = str;
        this.zzvz = runnable;
    }

    public final void run() {
        this.zzvw.zzvu.zzy(this.zzvy);
        if (this.zzvz != null) {
            this.zzvz.run();
        }
    }
}
