package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;

final class zzsx implements zzsi {
    private final int flags;
    private final String info;
    private final Object[] zzbcz;
    private final zzsk zzbdc;

    zzsx(zzsk zzsk, String str, Object[] objArr) {
        this.zzbdc = zzsk;
        this.info = str;
        this.zzbcz = objArr;
        int i = 1;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        char c = charAt & 8191;
        int i2 = 13;
        while (true) {
            int i3 = i + 1;
            char charAt2 = str.charAt(i);
            if (charAt2 >= 55296) {
                c |= (charAt2 & 8191) << i2;
                i2 += 13;
                i = i3;
            } else {
                this.flags = (charAt2 << i2) | c;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzqt() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzqu() {
        return this.zzbcz;
    }

    public final zzsk zzqn() {
        return this.zzbdc;
    }

    public final int zzql() {
        return (this.flags & 1) == 1 ? zzrc.zze.zzbaz : zzrc.zze.zzbba;
    }

    public final boolean zzqm() {
        return (this.flags & 2) == 2;
    }
}
