package com.google.android.gms.internal.gtm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzpi extends WeakReference<Throwable> {
    private final int zzavn;

    public zzpi(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, (ReferenceQueue) null);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzavn = System.identityHashCode(th);
    }

    public final int hashCode() {
        return this.zzavn;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzpi zzpi = (zzpi) obj;
        if (this.zzavn == zzpi.zzavn && get() == zzpi.get()) {
            return true;
        }
        return false;
    }
}
