package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

public abstract class zzdk extends zza implements zzdh {
    public zzdk() {
        super("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzu(parcel.readInt());
                break;
            case 2:
                zza((ApplicationMetadata) zzd.zza(parcel, ApplicationMetadata.CREATOR), parcel.readString(), parcel.readString(), zzd.zza(parcel));
                break;
            case 3:
                zzi(parcel.readInt());
                break;
            case 4:
                zza(parcel.readString(), parcel.readDouble(), zzd.zza(parcel));
                break;
            case 5:
                zzb(parcel.readString(), parcel.readString());
                break;
            case 6:
                zza(parcel.readString(), parcel.createByteArray());
                break;
            case 7:
                zzw(parcel.readInt());
                break;
            case 8:
                zzv(parcel.readInt());
                break;
            case 9:
                onApplicationDisconnected(parcel.readInt());
                break;
            case 10:
                zza(parcel.readString(), parcel.readLong(), parcel.readInt());
                break;
            case 11:
                zza(parcel.readString(), parcel.readLong());
                break;
            case 12:
                zzb((zzcj) zzd.zza(parcel, zzcj.CREATOR));
                break;
            case 13:
                zzb((zzdb) zzd.zza(parcel, zzdb.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
