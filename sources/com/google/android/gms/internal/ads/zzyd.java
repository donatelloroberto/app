package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzyd extends zzek implements zzyc {
    public zzyd() {
        super("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                String headline = getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 3:
                List images = getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            case 4:
                String body = getBody();
                parcel2.writeNoException();
                parcel2.writeString(body);
                break;
            case 5:
                zzpw zzkg = zzkg();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzkg);
                break;
            case 6:
                String callToAction = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(callToAction);
                break;
            case 7:
                String advertiser = getAdvertiser();
                parcel2.writeNoException();
                parcel2.writeString(advertiser);
                break;
            case 8:
                recordImpression();
                parcel2.writeNoException();
                break;
            case 9:
                zzj(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 10:
                zzk(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 11:
                boolean overrideImpressionRecording = getOverrideImpressionRecording();
                parcel2.writeNoException();
                zzel.zza(parcel2, overrideImpressionRecording);
                break;
            case 12:
                boolean overrideClickHandling = getOverrideClickHandling();
                parcel2.writeNoException();
                zzel.zza(parcel2, overrideClickHandling);
                break;
            case 13:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                zzel.zzb(parcel2, extras);
                break;
            case 14:
                zzl(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 15:
                IObjectWrapper zzmv = zzmv();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzmv);
                break;
            case 16:
                zzlo videoController = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) videoController);
                break;
            case 19:
                zzps zzkf = zzkf();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzkf);
                break;
            case 20:
                IObjectWrapper zzmw = zzmw();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzmw);
                break;
            case 21:
                IObjectWrapper zzke = zzke();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzke);
                break;
            case 22:
                zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
