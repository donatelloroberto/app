package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class zzfa extends zzo implements zzez {
    public zzfa() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzew) zzp.zza(parcel, zzew.CREATOR), (zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                zza((zzjz) zzp.zza(parcel, zzjz.CREATOR), (zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                zza((zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zza((zzew) zzp.zza(parcel, zzew.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zzb((zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                List<zzjz> zza = zza((zzdz) zzp.zza(parcel, zzdz.CREATOR), zzp.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 9:
                byte[] zza2 = zza((zzew) zzp.zza(parcel, zzew.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                break;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String zzc = zzc((zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                break;
            case 12:
                zza((zzee) zzp.zza(parcel, zzee.CREATOR), (zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                zzb((zzee) zzp.zza(parcel, zzee.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                List<zzjz> zza3 = zza(parcel.readString(), parcel.readString(), zzp.zza(parcel), (zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza3);
                break;
            case 15:
                List<zzjz> zza4 = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzp.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza4);
                break;
            case 16:
                List<zzee> zza5 = zza(parcel.readString(), parcel.readString(), (zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza5);
                break;
            case 17:
                List<zzee> zze = zze(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zze);
                break;
            case 18:
                zzd((zzdz) zzp.zza(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
