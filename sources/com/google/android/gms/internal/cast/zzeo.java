package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzeo extends zzb implements zzel {
    zzeo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
    }

    public final void disconnect() throws RemoteException {
        zzc(3, zza());
    }

    public final void zza(zzej zzej, zzen zzen, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzej);
        zzd.zza(zza, (IInterface) zzen);
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, (Parcelable) bundle);
        zzc(7, zza);
    }

    public final void zza(zzej zzej, PendingIntent pendingIntent, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzej);
        zzd.zza(zza, (Parcelable) pendingIntent);
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, (Parcelable) bundle);
        zzc(8, zza);
    }

    public final void zza(zzej zzej, int i) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzej);
        zza.writeInt(i);
        zzc(5, zza);
    }

    public final void zza(zzej zzej) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzej);
        zzc(6, zza);
    }
}
