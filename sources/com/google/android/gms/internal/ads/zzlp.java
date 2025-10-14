package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzlp extends zzek implements zzlo {
    public zzlp() {
        super("com.google.android.gms.ads.internal.client.IVideoController");
    }

    public static zzlo zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        return queryLocalInterface instanceof zzlo ? (zzlo) queryLocalInterface : new zzlq(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzlr zzlt;
        switch (i) {
            case 1:
                play();
                parcel2.writeNoException();
                break;
            case 2:
                pause();
                parcel2.writeNoException();
                break;
            case 3:
                mute(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            case 4:
                boolean isMuted = isMuted();
                parcel2.writeNoException();
                zzel.zza(parcel2, isMuted);
                break;
            case 5:
                int playbackState = getPlaybackState();
                parcel2.writeNoException();
                parcel2.writeInt(playbackState);
                break;
            case 6:
                float zzim = zzim();
                parcel2.writeNoException();
                parcel2.writeFloat(zzim);
                break;
            case 7:
                float zzin = zzin();
                parcel2.writeNoException();
                parcel2.writeFloat(zzin);
                break;
            case 8:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzlt = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    zzlt = queryLocalInterface instanceof zzlr ? (zzlr) queryLocalInterface : new zzlt(readStrongBinder);
                }
                zza(zzlt);
                parcel2.writeNoException();
                break;
            case 9:
                float aspectRatio = getAspectRatio();
                parcel2.writeNoException();
                parcel2.writeFloat(aspectRatio);
                break;
            case 10:
                boolean isCustomControlsEnabled = isCustomControlsEnabled();
                parcel2.writeNoException();
                zzel.zza(parcel2, isCustomControlsEnabled);
                break;
            case 11:
                zzlr zzio = zzio();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) zzio);
                break;
            case 12:
                boolean isClickToExpandEnabled = isClickToExpandEnabled();
                parcel2.writeNoException();
                zzel.zza(parcel2, isClickToExpandEnabled);
                break;
            default:
                return false;
        }
        return true;
    }
}
