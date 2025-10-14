package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzanv implements Runnable {
    private final zzaoj zzbnu;
    private final zzanz zzcvk;

    zzanv(zzaoj zzaoj, zzanz zzanz) {
        this.zzbnu = zzaoj;
        this.zzcvk = zzanz;
    }

    public final void run() {
        zzaoj zzaoj = this.zzbnu;
        try {
            zzaoj.set(this.zzcvk.get());
        } catch (ExecutionException e) {
            zzaoj.setException(e.getCause());
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            zzaoj.setException(e2);
        } catch (Exception e3) {
            zzaoj.setException(e3);
        }
    }
}
