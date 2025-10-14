package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public abstract class zzbz<R extends Result> extends zzcl<R> {
    protected zzdu zzxb;

    public zzbz(zzbs zzbs) {
        super(zzbs.zzpl);
    }

    public abstract void execute();

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        execute();
    }
}
