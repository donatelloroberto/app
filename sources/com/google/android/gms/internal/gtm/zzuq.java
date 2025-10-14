package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzuq;
import java.io.IOException;

public abstract class zzuq<M extends zzuq<M>> extends zzuw {
    protected zzus zzbhb;

    /* access modifiers changed from: protected */
    public int zzy() {
        int i = 0;
        if (this.zzbhb == null) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i2 >= this.zzbhb.size()) {
                return i3;
            }
            i = this.zzbhb.zzce(i2).zzy() + i3;
            i2++;
        }
    }

    public void zza(zzuo zzuo) throws IOException {
        if (this.zzbhb != null) {
            for (int i = 0; i < this.zzbhb.size(); i++) {
                this.zzbhb.zzce(i).zza(zzuo);
            }
        }
    }

    public final <T> T zza(zzur<M, T> zzur) {
        zzut zzcd;
        if (this.zzbhb == null || (zzcd = this.zzbhb.zzcd(zzur.tag >>> 3)) == null) {
            return null;
        }
        return zzcd.zzb(zzur);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzun zzun, int i) throws IOException {
        int position = zzun.getPosition();
        if (!zzun.zzao(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzuy zzuy = new zzuy(i, zzun.zzt(position, zzun.getPosition() - position));
        zzut zzut = null;
        if (this.zzbhb == null) {
            this.zzbhb = new zzus();
        } else {
            zzut = this.zzbhb.zzcd(i2);
        }
        if (zzut == null) {
            zzut = new zzut();
            this.zzbhb.zza(i2, zzut);
        }
        zzut.zza(zzuy);
        return true;
    }

    public final /* synthetic */ zzuw zzry() throws CloneNotSupportedException {
        return (zzuq) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzuq zzuq = (zzuq) super.clone();
        zzuu.zza(this, zzuq);
        return zzuq;
    }
}
