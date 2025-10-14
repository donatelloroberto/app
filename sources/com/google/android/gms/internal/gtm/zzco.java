package com.google.android.gms.internal.gtm;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.UUID;
import kotlin.jvm.internal.LongCompanionObject;

public final class zzco {
    private final String name;
    private final long zzabz;
    private final /* synthetic */ zzcm zzaca;

    private zzco(zzcm zzcm, String str, long j) {
        this.zzaca = zzcm;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j > 0);
        this.name = str;
        this.zzabz = j;
    }

    private final void zzgb() {
        long currentTimeMillis = this.zzaca.zzcn().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzaca.zzabv.edit();
        edit.remove(zzgf());
        edit.remove(zzgg());
        edit.putLong(zzge(), currentTimeMillis);
        edit.commit();
    }

    public final void zzae(String str) {
        if (zzgd() == 0) {
            zzgb();
        }
        if (str == null) {
            str = "";
        }
        synchronized (this) {
            long j = this.zzaca.zzabv.getLong(zzgf(), 0);
            if (j <= 0) {
                SharedPreferences.Editor edit = this.zzaca.zzabv.edit();
                edit.putString(zzgg(), str);
                edit.putLong(zzgf(), 1);
                edit.apply();
                return;
            }
            boolean z = (UUID.randomUUID().getLeastSignificantBits() & LongCompanionObject.MAX_VALUE) < LongCompanionObject.MAX_VALUE / (j + 1);
            SharedPreferences.Editor edit2 = this.zzaca.zzabv.edit();
            if (z) {
                edit2.putString(zzgg(), str);
            }
            edit2.putLong(zzgf(), j + 1);
            edit2.apply();
        }
    }

    public final Pair<String, Long> zzgc() {
        long abs;
        long zzgd = zzgd();
        if (zzgd == 0) {
            abs = 0;
        } else {
            abs = Math.abs(zzgd - this.zzaca.zzcn().currentTimeMillis());
        }
        if (abs < this.zzabz) {
            return null;
        }
        if (abs > (this.zzabz << 1)) {
            zzgb();
            return null;
        }
        String string = this.zzaca.zzabv.getString(zzgg(), (String) null);
        long j = this.zzaca.zzabv.getLong(zzgf(), 0);
        zzgb();
        if (string == null || j <= 0) {
            return null;
        }
        return new Pair<>(string, Long.valueOf(j));
    }

    private final long zzgd() {
        return this.zzaca.zzabv.getLong(zzge(), 0);
    }

    private final String zzge() {
        return String.valueOf(this.name).concat(":start");
    }

    private final String zzgf() {
        return String.valueOf(this.name).concat(":count");
    }

    @VisibleForTesting
    private final String zzgg() {
        return String.valueOf(this.name).concat(":value");
    }
}
