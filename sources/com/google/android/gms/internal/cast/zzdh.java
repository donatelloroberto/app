package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

public interface zzdh extends IInterface {
    void onApplicationDisconnected(int i) throws RemoteException;

    void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException;

    void zza(String str, double d, boolean z) throws RemoteException;

    void zza(String str, long j) throws RemoteException;

    void zza(String str, long j, int i) throws RemoteException;

    void zza(String str, byte[] bArr) throws RemoteException;

    void zzb(zzcj zzcj) throws RemoteException;

    void zzb(zzdb zzdb) throws RemoteException;

    void zzb(String str, String str2) throws RemoteException;

    void zzi(int i) throws RemoteException;

    void zzu(int i) throws RemoteException;

    void zzv(int i) throws RemoteException;

    void zzw(int i) throws RemoteException;
}
