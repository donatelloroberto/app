package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzah;

public final class zzdi extends zzb implements zzdf {
    zzdi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastDeviceController");
    }

    public final void disconnect() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzel() throws RemoteException {
        zzc(4, zza());
    }

    public final void zzj(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(5, zza);
    }

    public final void requestStatus() throws RemoteException {
        zzc(6, zza());
    }

    public final void zza(double d, double d2, boolean z) throws RemoteException {
        Parcel zza = zza();
        zza.writeDouble(d);
        zza.writeDouble(d2);
        zzd.writeBoolean(zza, z);
        zzc(7, zza);
    }

    public final void zza(boolean z, double d, boolean z2) throws RemoteException {
        Parcel zza = zza();
        zzd.writeBoolean(zza, z);
        zza.writeDouble(d);
        zzd.writeBoolean(zza, z2);
        zzc(8, zza);
    }

    public final void zza(String str, String str2, long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zza.writeLong(j);
        zzc(9, zza);
    }

    public final void zzt(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(11, zza);
    }

    public final void zzu(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(12, zza);
    }

    public final void zzb(String str, LaunchOptions launchOptions) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, (Parcelable) launchOptions);
        zzc(13, zza);
    }

    public final void zza(String str, String str2, zzah zzah) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, (Parcelable) zzah);
        zzc(14, zza);
    }
}
