package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzzk extends zzek implements zzzj {
    public zzzk() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzzj zzt(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        return queryLocalInterface instanceof zzzj ? (zzzj) queryLocalInterface : new zzzl(iBinder);
    }

    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r5v4, types: [com.google.android.gms.internal.ads.zzzh] */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: type inference failed for: r5v8, types: [com.google.android.gms.internal.ads.zzzf] */
    /* JADX WARNING: type inference failed for: r5v10 */
    /* JADX WARNING: type inference failed for: r5v11 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
        /*
            r8 = this;
            r5 = 0
            switch(r9) {
                case 1: goto L_0x0006;
                case 2: goto L_0x003c;
                case 3: goto L_0x0047;
                case 4: goto L_0x0052;
                case 5: goto L_0x009c;
                case 6: goto L_0x00a7;
                case 7: goto L_0x00ea;
                default: goto L_0x0004;
            }
        L_0x0004:
            r0 = 0
        L_0x0005:
            return r0
        L_0x0006:
            android.os.IBinder r0 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            java.lang.String r3 = r10.readString()
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r10, r0)
            android.os.Bundle r0 = (android.os.Bundle) r0
            android.os.IBinder r4 = r10.readStrongBinder()
            if (r4 != 0) goto L_0x0029
            r1 = r5
        L_0x0021:
            r8.zza(r2, r3, r0, r1)
            r11.writeNoException()
        L_0x0027:
            r0 = 1
            goto L_0x0005
        L_0x0029:
            java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback"
            android.os.IInterface r1 = r4.queryLocalInterface(r1)
            boolean r5 = r1 instanceof com.google.android.gms.internal.ads.zzzm
            if (r5 == 0) goto L_0x0036
            com.google.android.gms.internal.ads.zzzm r1 = (com.google.android.gms.internal.ads.zzzm) r1
            goto L_0x0021
        L_0x0036:
            com.google.android.gms.internal.ads.zzzn r1 = new com.google.android.gms.internal.ads.zzzn
            r1.<init>(r4)
            goto L_0x0021
        L_0x003c:
            com.google.android.gms.internal.ads.zzzt r0 = r8.zznc()
            r11.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r11, r0)
            goto L_0x0027
        L_0x0047:
            com.google.android.gms.internal.ads.zzzt r0 = r8.zznd()
            r11.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r11, r0)
            goto L_0x0027
        L_0x0052:
            byte[] r1 = r10.createByteArray()
            java.lang.String r2 = r10.readString()
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r10, r0)
            android.os.Bundle r3 = (android.os.Bundle) r3
            android.os.IBinder r0 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r6 = r10.readStrongBinder()
            if (r6 != 0) goto L_0x0088
        L_0x0070:
            android.os.IBinder r0 = r10.readStrongBinder()
            com.google.android.gms.internal.ads.zzxt r6 = com.google.android.gms.internal.ads.zzxu.zzs(r0)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r0 = com.google.android.gms.internal.ads.zzjn.CREATOR
            android.os.Parcelable r7 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r10, r0)
            com.google.android.gms.internal.ads.zzjn r7 = (com.google.android.gms.internal.ads.zzjn) r7
            r0 = r8
            r0.zza(r1, r2, r3, r4, r5, r6, r7)
            r11.writeNoException()
            goto L_0x0027
        L_0x0088:
            java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback"
            android.os.IInterface r0 = r6.queryLocalInterface(r0)
            boolean r5 = r0 instanceof com.google.android.gms.internal.ads.zzzf
            if (r5 == 0) goto L_0x0096
            com.google.android.gms.internal.ads.zzzf r0 = (com.google.android.gms.internal.ads.zzzf) r0
            r5 = r0
            goto L_0x0070
        L_0x0096:
            com.google.android.gms.internal.ads.zzzg r5 = new com.google.android.gms.internal.ads.zzzg
            r5.<init>(r6)
            goto L_0x0070
        L_0x009c:
            com.google.android.gms.internal.ads.zzlo r0 = r8.getVideoController()
            r11.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, (android.os.IInterface) r0)
            goto L_0x0027
        L_0x00a7:
            byte[] r1 = r10.createByteArray()
            java.lang.String r2 = r10.readString()
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r10, r0)
            android.os.Bundle r3 = (android.os.Bundle) r3
            android.os.IBinder r0 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r6 = r10.readStrongBinder()
            if (r6 != 0) goto L_0x00d6
        L_0x00c5:
            android.os.IBinder r0 = r10.readStrongBinder()
            com.google.android.gms.internal.ads.zzxt r6 = com.google.android.gms.internal.ads.zzxu.zzs(r0)
            r0 = r8
            r0.zza(r1, r2, r3, r4, r5, r6)
            r11.writeNoException()
            goto L_0x0027
        L_0x00d6:
            java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback"
            android.os.IInterface r0 = r6.queryLocalInterface(r0)
            boolean r5 = r0 instanceof com.google.android.gms.internal.ads.zzzh
            if (r5 == 0) goto L_0x00e4
            com.google.android.gms.internal.ads.zzzh r0 = (com.google.android.gms.internal.ads.zzzh) r0
            r5 = r0
            goto L_0x00c5
        L_0x00e4:
            com.google.android.gms.internal.ads.zzzi r5 = new com.google.android.gms.internal.ads.zzzi
            r5.<init>(r6)
            goto L_0x00c5
        L_0x00ea:
            r8.showInterstitial()
            r11.writeNoException()
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzzk.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
