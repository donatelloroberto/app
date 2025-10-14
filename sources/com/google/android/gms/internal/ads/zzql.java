package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzql extends zzek implements zzqk {
    public zzql() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                IObjectWrapper zzka = zzka();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzka);
                break;
            case 3:
                String headline = getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 4:
                List images = getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            case 5:
                String body = getBody();
                parcel2.writeNoException();
                parcel2.writeString(body);
                break;
            case 6:
                zzpw zzjz = zzjz();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzjz);
                break;
            case 7:
                String callToAction = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(callToAction);
                break;
            case 8:
                double starRating = getStarRating();
                parcel2.writeNoException();
                parcel2.writeDouble(starRating);
                break;
            case 9:
                String store = getStore();
                parcel2.writeNoException();
                parcel2.writeString(store);
                break;
            case 10:
                String price = getPrice();
                parcel2.writeNoException();
                parcel2.writeString(price);
                break;
            case 11:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                zzel.zzb(parcel2, extras);
                break;
            case 12:
                destroy();
                parcel2.writeNoException();
                break;
            case 13:
                zzlo videoController = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) videoController);
                break;
            case 14:
                performClick((Bundle) zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 15:
                boolean recordImpression = recordImpression((Bundle) zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzel.zza(parcel2, recordImpression);
                break;
            case 16:
                reportTouchEvent((Bundle) zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 17:
                zzps zzkf = zzkf();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzkf);
                break;
            case 18:
                IObjectWrapper zzke = zzke();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzke);
                break;
            case 19:
                String mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            default:
                return false;
        }
        return true;
    }
}
