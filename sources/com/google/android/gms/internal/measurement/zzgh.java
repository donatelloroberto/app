package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zzgh extends zzhi {
    /* access modifiers changed from: private */
    public static final AtomicLong zzamg = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzalw;
    /* access modifiers changed from: private */
    public zzgl zzalx;
    /* access modifiers changed from: private */
    public zzgl zzaly;
    private final PriorityBlockingQueue<zzgk<?>> zzalz = new PriorityBlockingQueue<>();
    private final BlockingQueue<zzgk<?>> zzama = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzamb = new zzgj(this, "Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzamc = new zzgj(this, "Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzamd = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzame = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzamf;

    zzgh(zzgm zzgm) {
        super(zzgm);
    }

    private final void zza(zzgk<?> zzgk) {
        synchronized (this.zzamd) {
            this.zzalz.add(zzgk);
            if (this.zzalx == null) {
                this.zzalx = new zzgl(this, "Measurement Worker", this.zzalz);
                this.zzalx.setUncaughtExceptionHandler(this.zzamb);
                this.zzalx.start();
            } else {
                this.zzalx.zzju();
            }
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    public final <T> T zza(AtomicReference<T> atomicReference, long j, String str, Runnable runnable) {
        synchronized (atomicReference) {
            zzge().zzc(runnable);
            try {
                atomicReference.wait(15000);
            } catch (InterruptedException e) {
                zzfj zziv = zzgf().zziv();
                String valueOf = String.valueOf(str);
                zziv.log(valueOf.length() != 0 ? "Interrupted waiting for ".concat(valueOf) : new String("Interrupted waiting for "));
                return null;
            }
        }
        T t = atomicReference.get();
        if (t == null) {
            zzfj zziv2 = zzgf().zziv();
            String valueOf2 = String.valueOf(str);
            zziv2.log(valueOf2.length() != 0 ? "Timed out waiting for ".concat(valueOf2) : new String("Timed out waiting for "));
        }
        return t;
    }

    public final void zzab() {
        if (Thread.currentThread() != this.zzalx) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final <V> Future<V> zzb(Callable<V> callable) throws IllegalStateException {
        zzch();
        Preconditions.checkNotNull(callable);
        zzgk zzgk = new zzgk(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzalx) {
            if (!this.zzalz.isEmpty()) {
                zzgf().zziv().log("Callable skipped the worker queue.");
            }
            zzgk.run();
        } else {
            zza((zzgk<?>) zzgk);
        }
        return zzgk;
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final <V> Future<V> zzc(Callable<V> callable) throws IllegalStateException {
        zzch();
        Preconditions.checkNotNull(callable);
        zzgk zzgk = new zzgk(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzalx) {
            zzgk.run();
        } else {
            zza((zzgk<?>) zzgk);
        }
        return zzgk;
    }

    public final void zzc(Runnable runnable) throws IllegalStateException {
        zzch();
        Preconditions.checkNotNull(runnable);
        zza((zzgk<?>) new zzgk(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzd(Runnable runnable) throws IllegalStateException {
        zzch();
        Preconditions.checkNotNull(runnable);
        zzgk zzgk = new zzgk(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzamd) {
            this.zzama.add(zzgk);
            if (this.zzaly == null) {
                this.zzaly = new zzgl(this, "Measurement Network", this.zzama);
                this.zzaly.setUncaughtExceptionHandler(this.zzamc);
                this.zzaly.start();
            } else {
                this.zzaly.zzju();
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zzfr() {
        super.zzfr();
    }

    public final /* bridge */ /* synthetic */ void zzfs() {
        super.zzfs();
    }

    public final void zzft() {
        if (Thread.currentThread() != this.zzaly) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final /* bridge */ /* synthetic */ zzdu zzfu() {
        return super.zzfu();
    }

    public final /* bridge */ /* synthetic */ zzhl zzfv() {
        return super.zzfv();
    }

    public final /* bridge */ /* synthetic */ zzfc zzfw() {
        return super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzeq zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzij zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzig zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzfd zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzff zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzkc zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzji zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzgh zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfh zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzfs zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzeg zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzec zzgi() {
        return super.zzgi();
    }

    /* access modifiers changed from: protected */
    public final boolean zzhh() {
        return false;
    }

    public final boolean zzjr() {
        return Thread.currentThread() == this.zzalx;
    }

    /* access modifiers changed from: package-private */
    public final ExecutorService zzjs() {
        ExecutorService executorService;
        synchronized (this.zzamd) {
            if (this.zzalw == null) {
                this.zzalw = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.zzalw;
        }
        return executorService;
    }
}
