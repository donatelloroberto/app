package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.zzbv;
import com.google.android.gms.common.api.internal.IStatusCallback;
import java.util.List;

public final class zzdm extends zzb implements zzdj {
    zzdm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastService");
    }

    public final void zza(IStatusCallback iStatusCallback, String[] strArr, String str, List<zzbv> list) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) iStatusCallback);
        zza.writeStringArray(strArr);
        zza.writeString(str);
        zza.writeTypedList(list);
        zzc(2, zza);
    }
}
