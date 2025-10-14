package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzle extends zzek implements zzld {
    public zzle() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    public static zzld asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
        return queryLocalInterface instanceof zzld ? (zzld) queryLocalInterface : new zzlf(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzks createBannerAdManager = createBannerAdManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createBannerAdManager);
                break;
            case 2:
                zzks createInterstitialAdManager = createInterstitialAdManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createInterstitialAdManager);
                break;
            case 3:
                zzkn createAdLoaderBuilder = createAdLoaderBuilder(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createAdLoaderBuilder);
                break;
            case 4:
                zzlj mobileAdsSettingsManager = getMobileAdsSettingsManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) mobileAdsSettingsManager);
                break;
            case 5:
                zzqa createNativeAdViewDelegate = createNativeAdViewDelegate(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createNativeAdViewDelegate);
                break;
            case 6:
                zzagz createRewardedVideoAd = createRewardedVideoAd(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createRewardedVideoAd);
                break;
            case 7:
                zzaaz createInAppPurchaseManager = createInAppPurchaseManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createInAppPurchaseManager);
                break;
            case 8:
                zzaap createAdOverlay = createAdOverlay(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createAdOverlay);
                break;
            case 9:
                zzlj mobileAdsSettingsManagerWithClientJarVersion = getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) mobileAdsSettingsManagerWithClientJarVersion);
                break;
            case 10:
                zzks createSearchAdManager = createSearchAdManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createSearchAdManager);
                break;
            case 11:
                zzqf createNativeAdViewHolderDelegate = createNativeAdViewHolderDelegate(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface) createNativeAdViewHolderDelegate);
                break;
            default:
                return false;
        }
        return true;
    }
}
