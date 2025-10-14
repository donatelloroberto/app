package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzaha extends zzek implements zzagz {
    public zzaha() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    public static zzagz zzy(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        return queryLocalInterface instanceof zzagz ? (zzagz) queryLocalInterface : new zzahb(iBinder);
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.google.android.gms.internal.ads.zzagx] */
    /* JADX WARNING: type inference failed for: r0v23, types: [com.google.android.gms.internal.ads.zzahe] */
    /* JADX WARNING: type inference failed for: r0v28 */
    /* JADX WARNING: type inference failed for: r0v29 */
    /* JADX WARNING: type inference failed for: r0v30 */
    /* JADX WARNING: type inference failed for: r0v31 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 0
            switch(r4) {
                case 1: goto L_0x0006;
                case 2: goto L_0x0016;
                case 3: goto L_0x001d;
                case 4: goto L_0x0004;
                case 5: goto L_0x003d;
                case 6: goto L_0x0048;
                case 7: goto L_0x004f;
                case 8: goto L_0x0056;
                case 9: goto L_0x005d;
                case 10: goto L_0x006c;
                case 11: goto L_0x007b;
                case 12: goto L_0x008a;
                case 13: goto L_0x0095;
                case 14: goto L_0x00a1;
                case 15: goto L_0x00b1;
                case 16: goto L_0x00bd;
                case 17: goto L_0x0004;
                case 18: goto L_0x0004;
                case 19: goto L_0x0004;
                case 20: goto L_0x0004;
                case 21: goto L_0x0004;
                case 22: goto L_0x0004;
                case 23: goto L_0x0004;
                case 24: goto L_0x0004;
                case 25: goto L_0x0004;
                case 26: goto L_0x0004;
                case 27: goto L_0x0004;
                case 28: goto L_0x0004;
                case 29: goto L_0x0004;
                case 30: goto L_0x0004;
                case 31: goto L_0x0004;
                case 32: goto L_0x0004;
                case 33: goto L_0x0004;
                case 34: goto L_0x00de;
                default: goto L_0x0004;
            }
        L_0x0004:
            r0 = 0
        L_0x0005:
            return r0
        L_0x0006:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzahk> r0 = com.google.android.gms.internal.ads.zzahk.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.internal.ads.zzahk r0 = (com.google.android.gms.internal.ads.zzahk) r0
            r3.zza((com.google.android.gms.internal.ads.zzahk) r0)
            r6.writeNoException()
        L_0x0014:
            r0 = 1
            goto L_0x0005
        L_0x0016:
            r3.show()
            r6.writeNoException()
            goto L_0x0014
        L_0x001d:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x002a
        L_0x0023:
            r3.zza((com.google.android.gms.internal.ads.zzahe) r0)
            r6.writeNoException()
            goto L_0x0014
        L_0x002a:
            java.lang.String r0 = "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzahe
            if (r2 == 0) goto L_0x0037
            com.google.android.gms.internal.ads.zzahe r0 = (com.google.android.gms.internal.ads.zzahe) r0
            goto L_0x0023
        L_0x0037:
            com.google.android.gms.internal.ads.zzahg r0 = new com.google.android.gms.internal.ads.zzahg
            r0.<init>(r1)
            goto L_0x0023
        L_0x003d:
            boolean r0 = r3.isLoaded()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (boolean) r0)
            goto L_0x0014
        L_0x0048:
            r3.pause()
            r6.writeNoException()
            goto L_0x0014
        L_0x004f:
            r3.resume()
            r6.writeNoException()
            goto L_0x0014
        L_0x0056:
            r3.destroy()
            r6.writeNoException()
            goto L_0x0014
        L_0x005d:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            r3.zzd(r0)
            r6.writeNoException()
            goto L_0x0014
        L_0x006c:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            r3.zze(r0)
            r6.writeNoException()
            goto L_0x0014
        L_0x007b:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            r3.zzf(r0)
            r6.writeNoException()
            goto L_0x0014
        L_0x008a:
            java.lang.String r0 = r3.getMediationAdapterClassName()
            r6.writeNoException()
            r6.writeString(r0)
            goto L_0x0014
        L_0x0095:
            java.lang.String r0 = r5.readString()
            r3.setUserId(r0)
            r6.writeNoException()
            goto L_0x0014
        L_0x00a1:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzkx r0 = com.google.android.gms.internal.ads.zzky.zzc(r0)
            r3.zza((com.google.android.gms.internal.ads.zzkx) r0)
            r6.writeNoException()
            goto L_0x0014
        L_0x00b1:
            android.os.Bundle r0 = r3.zzba()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r6, r0)
            goto L_0x0014
        L_0x00bd:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x00cb
        L_0x00c3:
            r3.zza((com.google.android.gms.internal.ads.zzagx) r0)
            r6.writeNoException()
            goto L_0x0014
        L_0x00cb:
            java.lang.String r0 = "com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzagx
            if (r2 == 0) goto L_0x00d8
            com.google.android.gms.internal.ads.zzagx r0 = (com.google.android.gms.internal.ads.zzagx) r0
            goto L_0x00c3
        L_0x00d8:
            com.google.android.gms.internal.ads.zzagy r0 = new com.google.android.gms.internal.ads.zzagy
            r0.<init>(r1)
            goto L_0x00c3
        L_0x00de:
            boolean r0 = com.google.android.gms.internal.ads.zzel.zza(r5)
            r3.setImmersiveMode(r0)
            r6.writeNoException()
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaha.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
