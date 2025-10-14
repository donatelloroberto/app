package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzcw implements Cast.ApplicationConnectionResult {
    private final Status zzhf;
    private final ApplicationMetadata zzzi;
    private final String zzzj;
    private final String zzzk;
    private final boolean zzzl;

    public zzcw(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        this.zzhf = status;
        this.zzzi = applicationMetadata;
        this.zzzj = str;
        this.zzzk = str2;
        this.zzzl = z;
    }

    public zzcw(Status status) {
        this(status, (ApplicationMetadata) null, (String) null, (String) null, false);
    }

    public final Status getStatus() {
        return this.zzhf;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzzi;
    }

    public final String getApplicationStatus() {
        return this.zzzj;
    }

    public final String getSessionId() {
        return this.zzzk;
    }

    public final boolean getWasLaunched() {
        return this.zzzl;
    }
}
