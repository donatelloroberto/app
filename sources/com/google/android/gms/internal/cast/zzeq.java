package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzeq extends zza implements zzen {
    public zzeq() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplaySessionCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzy(parcel.readInt());
        return true;
    }
}
