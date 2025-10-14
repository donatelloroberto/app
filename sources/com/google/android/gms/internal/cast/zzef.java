package com.google.android.gms.internal.cast;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;

final class zzef implements CastRemoteDisplay.CastRemoteDisplaySessionResult {
    private final Display zzbz;
    private final Status zzhf;

    public zzef(Display display) {
        this.zzhf = Status.RESULT_SUCCESS;
        this.zzbz = display;
    }

    public zzef(Status status) {
        this.zzhf = status;
        this.zzbz = null;
    }

    public final Status getStatus() {
        return this.zzhf;
    }

    public final Display getPresentationDisplay() {
        return this.zzbz;
    }
}
