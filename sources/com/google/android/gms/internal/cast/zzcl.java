package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

public abstract class zzcl<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzct> {
    public zzcl(GoogleApiClient googleApiClient) {
        super((Api<?>) Cast.API, googleApiClient);
    }

    public final void zzr(int i) {
        setResult(createFailedResult(new Status(CastStatusCodes.INVALID_REQUEST)));
    }
}
