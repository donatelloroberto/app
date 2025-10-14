package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;

final class zzm implements Cast.ApplicationConnectionResult {
    private final /* synthetic */ Status zzam;

    zzm(Cast.zza zza, Status status) {
        this.zzam = status;
    }

    public final Status getStatus() {
        return this.zzam;
    }

    public final boolean getWasLaunched() {
        return false;
    }

    public final String getSessionId() {
        return null;
    }

    public final String getApplicationStatus() {
        return null;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return null;
    }
}
