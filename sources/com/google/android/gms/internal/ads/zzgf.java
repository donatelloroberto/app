package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgf {
    private final Object mLock = new Object();
    private int zzahm;
    private List<zzge> zzahn = new LinkedList();

    public final boolean zza(zzge zzge) {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzahn.contains(zzge);
        }
        return z;
    }

    public final boolean zzb(zzge zzge) {
        synchronized (this.mLock) {
            Iterator<zzge> it = this.zzahn.iterator();
            while (it.hasNext()) {
                zzge next = it.next();
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue() || zzbv.zzeo().zzqh().zzqu()) {
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue() && !zzbv.zzeo().zzqh().zzqw() && zzge != next && next.zzgp().equals(zzge.zzgp())) {
                        it.remove();
                        return true;
                    }
                } else if (zzge != next && next.getSignature().equals(zzge.getSignature())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzge zzge) {
        synchronized (this.mLock) {
            if (this.zzahn.size() >= 10) {
                zzakb.zzck(new StringBuilder(41).append("Queue is full, current size = ").append(this.zzahn.size()).toString());
                this.zzahn.remove(0);
            }
            int i = this.zzahm;
            this.zzahm = i + 1;
            zzge.zzo(i);
            this.zzahn.add(zzge);
        }
    }

    @Nullable
    public final zzge zzgv() {
        zzge zzge = null;
        synchronized (this.mLock) {
            if (this.zzahn.size() == 0) {
                zzakb.zzck("Queue empty");
                return null;
            } else if (this.zzahn.size() >= 2) {
                int i = Integer.MIN_VALUE;
                int i2 = 0;
                int i3 = 0;
                for (zzge next : this.zzahn) {
                    int score = next.getScore();
                    if (score > i) {
                        i2 = i3;
                    } else {
                        score = i;
                        next = zzge;
                    }
                    i3++;
                    i = score;
                    zzge = next;
                }
                this.zzahn.remove(i2);
                return zzge;
            } else {
                zzge zzge2 = this.zzahn.get(0);
                zzge2.zzgq();
                return zzge2;
            }
        }
    }
}
