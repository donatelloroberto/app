package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzkt extends zzek implements zzks {
    public zzkt() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzks zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return queryLocalInterface instanceof zzks ? (zzks) queryLocalInterface : new zzku(iBinder);
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.google.android.gms.internal.ads.zzkx] */
    /* JADX WARNING: type inference failed for: r0v28, types: [com.google.android.gms.internal.ads.zzlg] */
    /* JADX WARNING: type inference failed for: r0v33, types: [com.google.android.gms.internal.ads.zzke] */
    /* JADX WARNING: type inference failed for: r0v49, types: [com.google.android.gms.internal.ads.zzla] */
    /* JADX WARNING: type inference failed for: r0v54, types: [com.google.android.gms.internal.ads.zzkh] */
    /* JADX WARNING: type inference failed for: r0v62 */
    /* JADX WARNING: type inference failed for: r0v63 */
    /* JADX WARNING: type inference failed for: r0v64 */
    /* JADX WARNING: type inference failed for: r0v65 */
    /* JADX WARNING: type inference failed for: r0v66 */
    /* JADX WARNING: type inference failed for: r0v67 */
    /* JADX WARNING: type inference failed for: r0v68 */
    /* JADX WARNING: type inference failed for: r0v69 */
    /* JADX WARNING: type inference failed for: r0v70 */
    /* JADX WARNING: type inference failed for: r0v71 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 0
            switch(r4) {
                case 1: goto L_0x0006;
                case 2: goto L_0x0012;
                case 3: goto L_0x0019;
                case 4: goto L_0x0024;
                case 5: goto L_0x0037;
                case 6: goto L_0x003e;
                case 7: goto L_0x0045;
                case 8: goto L_0x0065;
                case 9: goto L_0x0085;
                case 10: goto L_0x008c;
                case 11: goto L_0x0094;
                case 12: goto L_0x009c;
                case 13: goto L_0x00a8;
                case 14: goto L_0x00b8;
                case 15: goto L_0x00c8;
                case 16: goto L_0x0004;
                case 17: goto L_0x0004;
                case 18: goto L_0x00dc;
                case 19: goto L_0x00e8;
                case 20: goto L_0x00f8;
                case 21: goto L_0x0119;
                case 22: goto L_0x013a;
                case 23: goto L_0x0146;
                case 24: goto L_0x0152;
                case 25: goto L_0x0162;
                case 26: goto L_0x016e;
                case 27: goto L_0x0004;
                case 28: goto L_0x0004;
                case 29: goto L_0x017a;
                case 30: goto L_0x018a;
                case 31: goto L_0x019a;
                case 32: goto L_0x01a6;
                case 33: goto L_0x01b2;
                case 34: goto L_0x01be;
                case 35: goto L_0x01ca;
                case 36: goto L_0x01d6;
                case 37: goto L_0x01f7;
                default: goto L_0x0004;
            }
        L_0x0004:
            r0 = 0
        L_0x0005:
            return r0
        L_0x0006:
            com.google.android.gms.dynamic.IObjectWrapper r0 = r3.zzbj()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (android.os.IInterface) r0)
        L_0x0010:
            r0 = 1
            goto L_0x0005
        L_0x0012:
            r3.destroy()
            r6.writeNoException()
            goto L_0x0010
        L_0x0019:
            boolean r0 = r3.isReady()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (boolean) r0)
            goto L_0x0010
        L_0x0024:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r0 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.internal.ads.zzjj r0 = (com.google.android.gms.internal.ads.zzjj) r0
            boolean r0 = r3.zzb(r0)
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (boolean) r0)
            goto L_0x0010
        L_0x0037:
            r3.pause()
            r6.writeNoException()
            goto L_0x0010
        L_0x003e:
            r3.resume()
            r6.writeNoException()
            goto L_0x0010
        L_0x0045:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x0052
        L_0x004b:
            r3.zza((com.google.android.gms.internal.ads.zzkh) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0052:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdListener"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzkh
            if (r2 == 0) goto L_0x005f
            com.google.android.gms.internal.ads.zzkh r0 = (com.google.android.gms.internal.ads.zzkh) r0
            goto L_0x004b
        L_0x005f:
            com.google.android.gms.internal.ads.zzkj r0 = new com.google.android.gms.internal.ads.zzkj
            r0.<init>(r1)
            goto L_0x004b
        L_0x0065:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x0072
        L_0x006b:
            r3.zza((com.google.android.gms.internal.ads.zzla) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0072:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAppEventListener"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzla
            if (r2 == 0) goto L_0x007f
            com.google.android.gms.internal.ads.zzla r0 = (com.google.android.gms.internal.ads.zzla) r0
            goto L_0x006b
        L_0x007f:
            com.google.android.gms.internal.ads.zzlc r0 = new com.google.android.gms.internal.ads.zzlc
            r0.<init>(r1)
            goto L_0x006b
        L_0x0085:
            r3.showInterstitial()
            r6.writeNoException()
            goto L_0x0010
        L_0x008c:
            r3.stopLoading()
            r6.writeNoException()
            goto L_0x0010
        L_0x0094:
            r3.zzbm()
            r6.writeNoException()
            goto L_0x0010
        L_0x009c:
            com.google.android.gms.internal.ads.zzjn r0 = r3.zzbk()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r6, r0)
            goto L_0x0010
        L_0x00a8:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r0 = com.google.android.gms.internal.ads.zzjn.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.internal.ads.zzjn r0 = (com.google.android.gms.internal.ads.zzjn) r0
            r3.zza((com.google.android.gms.internal.ads.zzjn) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x00b8:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzaaw r0 = com.google.android.gms.internal.ads.zzaax.zzv(r0)
            r3.zza((com.google.android.gms.internal.ads.zzaaw) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x00c8:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzabc r0 = com.google.android.gms.internal.ads.zzabd.zzx(r0)
            java.lang.String r1 = r5.readString()
            r3.zza(r0, r1)
            r6.writeNoException()
            goto L_0x0010
        L_0x00dc:
            java.lang.String r0 = r3.getMediationAdapterClassName()
            r6.writeNoException()
            r6.writeString(r0)
            goto L_0x0010
        L_0x00e8:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzod r0 = com.google.android.gms.internal.ads.zzoe.zzf(r0)
            r3.zza((com.google.android.gms.internal.ads.zzod) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x00f8:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x0106
        L_0x00fe:
            r3.zza((com.google.android.gms.internal.ads.zzke) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0106:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdClickListener"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzke
            if (r2 == 0) goto L_0x0113
            com.google.android.gms.internal.ads.zzke r0 = (com.google.android.gms.internal.ads.zzke) r0
            goto L_0x00fe
        L_0x0113:
            com.google.android.gms.internal.ads.zzkg r0 = new com.google.android.gms.internal.ads.zzkg
            r0.<init>(r1)
            goto L_0x00fe
        L_0x0119:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x0127
        L_0x011f:
            r3.zza((com.google.android.gms.internal.ads.zzlg) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0127:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.ICorrelationIdProvider"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzlg
            if (r2 == 0) goto L_0x0134
            com.google.android.gms.internal.ads.zzlg r0 = (com.google.android.gms.internal.ads.zzlg) r0
            goto L_0x011f
        L_0x0134:
            com.google.android.gms.internal.ads.zzli r0 = new com.google.android.gms.internal.ads.zzli
            r0.<init>(r1)
            goto L_0x011f
        L_0x013a:
            boolean r0 = com.google.android.gms.internal.ads.zzel.zza(r5)
            r3.setManualImpressionsEnabled(r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0146:
            boolean r0 = r3.isLoading()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (boolean) r0)
            goto L_0x0010
        L_0x0152:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzahe r0 = com.google.android.gms.internal.ads.zzahf.zzz(r0)
            r3.zza((com.google.android.gms.internal.ads.zzahe) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0162:
            java.lang.String r0 = r5.readString()
            r3.setUserId(r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x016e:
            com.google.android.gms.internal.ads.zzlo r0 = r3.getVideoController()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (android.os.IInterface) r0)
            goto L_0x0010
        L_0x017a:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzmu> r0 = com.google.android.gms.internal.ads.zzmu.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.internal.ads.zzmu r0 = (com.google.android.gms.internal.ads.zzmu) r0
            r3.zza((com.google.android.gms.internal.ads.zzmu) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x018a:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzlu> r0 = com.google.android.gms.internal.ads.zzlu.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.internal.ads.zzlu r0 = (com.google.android.gms.internal.ads.zzlu) r0
            r3.zza((com.google.android.gms.internal.ads.zzlu) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x019a:
            java.lang.String r0 = r3.getAdUnitId()
            r6.writeNoException()
            r6.writeString(r0)
            goto L_0x0010
        L_0x01a6:
            com.google.android.gms.internal.ads.zzla r0 = r3.zzbw()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (android.os.IInterface) r0)
            goto L_0x0010
        L_0x01b2:
            com.google.android.gms.internal.ads.zzkh r0 = r3.zzbx()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (android.os.IInterface) r0)
            goto L_0x0010
        L_0x01be:
            boolean r0 = com.google.android.gms.internal.ads.zzel.zza(r5)
            r3.setImmersiveMode(r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x01ca:
            java.lang.String r0 = r3.zzck()
            r6.writeNoException()
            r6.writeString(r0)
            goto L_0x0010
        L_0x01d6:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x01e4
        L_0x01dc:
            r3.zza((com.google.android.gms.internal.ads.zzkx) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x01e4:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdMetadataListener"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzkx
            if (r2 == 0) goto L_0x01f1
            com.google.android.gms.internal.ads.zzkx r0 = (com.google.android.gms.internal.ads.zzkx) r0
            goto L_0x01dc
        L_0x01f1:
            com.google.android.gms.internal.ads.zzkz r0 = new com.google.android.gms.internal.ads.zzkz
            r0.<init>(r1)
            goto L_0x01dc
        L_0x01f7:
            android.os.Bundle r0 = r3.zzba()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r6, r0)
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkt.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
