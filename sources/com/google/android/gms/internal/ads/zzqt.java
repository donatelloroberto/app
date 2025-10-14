package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzqt extends zzek implements zzqs {
    public zzqt() {
        super("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    public static zzqs zzk(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        return queryLocalInterface instanceof zzqs ? (zzqs) queryLocalInterface : new zzqu(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                String zzao = zzao(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(zzao);
                break;
            case 2:
                zzpw zzap = zzap(parcel.readString());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzap);
                break;
            case 3:
                List<String> availableAssetNames = getAvailableAssetNames();
                parcel2.writeNoException();
                parcel2.writeStringList(availableAssetNames);
                break;
            case 4:
                String customTemplateId = getCustomTemplateId();
                parcel2.writeNoException();
                parcel2.writeString(customTemplateId);
                break;
            case 5:
                performClick(parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                recordImpression();
                parcel2.writeNoException();
                break;
            case 7:
                zzlo videoController = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) videoController);
                break;
            case 8:
                destroy();
                parcel2.writeNoException();
                break;
            case 9:
                IObjectWrapper zzkh = zzkh();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzkh);
                break;
            case 10:
                boolean zzh = zzh(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, zzh);
                break;
            case 11:
                IObjectWrapper zzka = zzka();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzka);
                break;
            default:
                return false;
        }
        return true;
    }
}
