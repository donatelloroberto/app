package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Random;

@ShowFirstParty
public final class zzai {
    private final String zzaec;
    private final Random zzafj;
    private final Context zzrm;

    public zzai(Context context, String str) {
        this(context, str, new Random());
    }

    @VisibleForTesting
    private zzai(Context context, String str, Random random) {
        this.zzrm = (Context) Preconditions.checkNotNull(context);
        this.zzaec = (String) Preconditions.checkNotNull(str);
        this.zzafj = random;
    }

    public final long zzhl() {
        return 43200000 + zza(7200000, 259200000);
    }

    public final long zzhm() {
        return 3600000 + zza(600000, 86400000);
    }

    private final long zza(long j, long j2) {
        SharedPreferences zzhp = zzhp();
        long max = Math.max(0, zzhp.getLong("FORBIDDEN_COUNT", 0));
        return (long) (((float) (((long) ((((float) max) / ((float) ((Math.max(0, zzhp.getLong("SUCCESSFUL_COUNT", 0)) + max) + 1))) * ((float) (j2 - j)))) + j)) * this.zzafj.nextFloat());
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzhn() {
        long min;
        SharedPreferences zzhp = zzhp();
        long j = zzhp.getLong("FORBIDDEN_COUNT", 0);
        long j2 = zzhp.getLong("SUCCESSFUL_COUNT", 0);
        SharedPreferences.Editor edit = zzhp.edit();
        if (j == 0) {
            min = 3;
        } else {
            min = Math.min(10, 1 + j);
        }
        long max = Math.max(0, Math.min(j2, 10 - min));
        edit.putLong("FORBIDDEN_COUNT", min);
        edit.putLong("SUCCESSFUL_COUNT", max);
        edit.apply();
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzho() {
        SharedPreferences zzhp = zzhp();
        long j = zzhp.getLong("SUCCESSFUL_COUNT", 0);
        long j2 = zzhp.getLong("FORBIDDEN_COUNT", 0);
        long min = Math.min(10, j + 1);
        long max = Math.max(0, Math.min(j2, 10 - min));
        SharedPreferences.Editor edit = zzhp.edit();
        edit.putLong("SUCCESSFUL_COUNT", min);
        edit.putLong("FORBIDDEN_COUNT", max);
        edit.apply();
    }

    private final SharedPreferences zzhp() {
        Context context = this.zzrm;
        String valueOf = String.valueOf("_gtmContainerRefreshPolicy_");
        String valueOf2 = String.valueOf(this.zzaec);
        return context.getSharedPreferences(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), 0);
    }
}
