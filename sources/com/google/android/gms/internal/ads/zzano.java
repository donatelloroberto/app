package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzano {
    public static <V> zzanz<V> zza(zzanz<V> zzanz, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzaoj zzaoj = new zzaoj();
        zza(zzaoj, zzanz);
        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new zzans(zzaoj), j, timeUnit);
        zza(zzanz, zzaoj);
        zzaoj.zza(new zzant(schedule), zzaoe.zzcvz);
        return zzaoj;
    }

    public static <A, B> zzanz<B> zza(zzanz<A> zzanz, zzanj<? super A, ? extends B> zzanj, Executor executor) {
        zzaoj zzaoj = new zzaoj();
        zzanz.zza(new zzanr(zzaoj, zzanj, zzanz), executor);
        zza(zzaoj, zzanz);
        return zzaoj;
    }

    public static <A, B> zzanz<B> zza(zzanz<A> zzanz, zzank<A, B> zzank, Executor executor) {
        zzaoj zzaoj = new zzaoj();
        zzanz.zza(new zzanq(zzaoj, zzank, zzanz), executor);
        zza(zzaoj, zzanz);
        return zzaoj;
    }

    public static <V, X extends Throwable> zzanz<V> zza(zzanz<? extends V> zzanz, Class<X> cls, zzanj<? super X, ? extends V> zzanj, Executor executor) {
        zzaoj zzaoj = new zzaoj();
        zza(zzaoj, zzanz);
        zzanz.zza(new zzanu(zzaoj, zzanz, cls, zzanj, executor), zzaoe.zzcvz);
        return zzaoj;
    }

    public static <T> T zza(Future<T> future, T t) {
        try {
            return future.get(((Long) zzkb.zzik().zzd(zznk.zzbam)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            future.cancel(true);
            zzakb.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zzb(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            future.cancel(true);
            zzakb.zzb("Error waiting for future.", e2);
            zzbv.zzeo().zzb(e2, "Futures.resolveFuture");
            return t;
        }
    }

    public static <T> T zza(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            future.cancel(true);
            zzakb.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zza(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            future.cancel(true);
            zzakb.zzb("Error waiting for future.", e2);
            zzbv.zzeo().zza(e2, "Futures.resolveFuture");
            return t;
        }
    }

    public static <V> void zza(zzanz<V> zzanz, zzanl<V> zzanl, Executor executor) {
        zzanz.zza(new zzanp(zzanl, zzanz), executor);
    }

    private static <V> void zza(zzanz<? extends V> zzanz, zzaoj<V> zzaoj) {
        zza(zzaoj, zzanz);
        zzanz.zza(new zzanv(zzaoj, zzanz), zzaoe.zzcvz);
    }

    private static <A, B> void zza(zzanz<A> zzanz, Future<B> future) {
        zzanz.zza(new zzanw(zzanz, future), zzaoe.zzcvz);
    }

    static final /* synthetic */ void zza(zzaoj zzaoj, zzanj zzanj, zzanz zzanz) {
        if (!zzaoj.isCancelled()) {
            try {
                zza(zzanj.zzc(zzanz.get()), zzaoj);
            } catch (CancellationException e) {
                zzaoj.cancel(true);
            } catch (ExecutionException e2) {
                zzaoj.setException(e2.getCause());
            } catch (InterruptedException e3) {
                Thread.currentThread().interrupt();
                zzaoj.setException(e3);
            } catch (Exception e4) {
                zzaoj.setException(e4);
            }
        }
    }

    static final /* synthetic */ void zza(zzaoj zzaoj, zzanz zzanz, Class cls, zzanj zzanj, Executor executor) {
        try {
            zzaoj.set(zzanz.get());
            return;
        } catch (ExecutionException e) {
            e = e.getCause();
        } catch (InterruptedException e2) {
            e = e2;
            Thread.currentThread().interrupt();
        } catch (Exception e3) {
            e = e3;
        }
        if (cls.isInstance(e)) {
            zza(zza(zzi(e), zzanj, executor), zzaoj);
        } else {
            zzaoj.setException(e);
        }
    }

    public static <T> zzanx<T> zzd(Throwable th) {
        return new zzanx<>(th);
    }

    public static <T> zzany<T> zzi(T t) {
        return new zzany<>(t);
    }
}
