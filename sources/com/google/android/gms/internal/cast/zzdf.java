package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzah;

public interface zzdf extends IInterface {
    void disconnect() throws RemoteException;

    void requestStatus() throws RemoteException;

    void zza(double d, double d2, boolean z) throws RemoteException;

    void zza(String str, String str2, long j) throws RemoteException;

    void zza(String str, String str2, zzah zzah) throws RemoteException;

    void zza(boolean z, double d, boolean z2) throws RemoteException;

    void zzb(String str, LaunchOptions launchOptions) throws RemoteException;

    void zzel() throws RemoteException;

    void zzj(String str) throws RemoteException;

    void zzt(String str) throws RemoteException;

    void zzu(String str) throws RemoteException;
}
