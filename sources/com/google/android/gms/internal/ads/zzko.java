package com.google.android.gms.internal.ads;

public abstract class zzko extends zzek implements zzkn {
    public zzko() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.google.android.gms.internal.ads.zzlg] */
    /* JADX WARNING: type inference failed for: r0v27, types: [com.google.android.gms.internal.ads.zzkh] */
    /* JADX WARNING: type inference failed for: r0v30 */
    /* JADX WARNING: type inference failed for: r0v31 */
    /* JADX WARNING: type inference failed for: r0v32 */
    /* JADX WARNING: type inference failed for: r0v33 */
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
                case 3: goto L_0x0032;
                case 4: goto L_0x0041;
                case 5: goto L_0x0050;
                case 6: goto L_0x006b;
                case 7: goto L_0x007a;
                case 8: goto L_0x009a;
                case 9: goto L_0x00b2;
                case 10: goto L_0x00c2;
                default: goto L_0x0004;
            }
        L_0x0004:
            r0 = 0
        L_0x0005:
            return r0
        L_0x0006:
            com.google.android.gms.internal.ads.zzkk r0 = r3.zzdh()
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, (android.os.IInterface) r0)
        L_0x0010:
            r0 = 1
            goto L_0x0005
        L_0x0012:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x001f
        L_0x0018:
            r3.zzb((com.google.android.gms.internal.ads.zzkh) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x001f:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdListener"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzkh
            if (r2 == 0) goto L_0x002c
            com.google.android.gms.internal.ads.zzkh r0 = (com.google.android.gms.internal.ads.zzkh) r0
            goto L_0x0018
        L_0x002c:
            com.google.android.gms.internal.ads.zzkj r0 = new com.google.android.gms.internal.ads.zzkj
            r0.<init>(r1)
            goto L_0x0018
        L_0x0032:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzqw r0 = com.google.android.gms.internal.ads.zzqx.zzl(r0)
            r3.zza((com.google.android.gms.internal.ads.zzqw) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0041:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzqz r0 = com.google.android.gms.internal.ads.zzra.zzm(r0)
            r3.zza((com.google.android.gms.internal.ads.zzqz) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0050:
            java.lang.String r0 = r5.readString()
            android.os.IBinder r1 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzrf r1 = com.google.android.gms.internal.ads.zzrg.zzo(r1)
            android.os.IBinder r2 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzrc r2 = com.google.android.gms.internal.ads.zzrd.zzn(r2)
            r3.zza(r0, r1, r2)
            r6.writeNoException()
            goto L_0x0010
        L_0x006b:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzpl> r0 = com.google.android.gms.internal.ads.zzpl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.internal.ads.zzpl r0 = (com.google.android.gms.internal.ads.zzpl) r0
            r3.zza((com.google.android.gms.internal.ads.zzpl) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x007a:
            android.os.IBinder r1 = r5.readStrongBinder()
            if (r1 != 0) goto L_0x0087
        L_0x0080:
            r3.zzb((com.google.android.gms.internal.ads.zzlg) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x0087:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.ICorrelationIdProvider"
            android.os.IInterface r0 = r1.queryLocalInterface(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.ads.zzlg
            if (r2 == 0) goto L_0x0094
            com.google.android.gms.internal.ads.zzlg r0 = (com.google.android.gms.internal.ads.zzlg) r0
            goto L_0x0080
        L_0x0094:
            com.google.android.gms.internal.ads.zzli r0 = new com.google.android.gms.internal.ads.zzli
            r0.<init>(r1)
            goto L_0x0080
        L_0x009a:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzri r1 = com.google.android.gms.internal.ads.zzrj.zzp(r0)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r0 = com.google.android.gms.internal.ads.zzjn.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.internal.ads.zzjn r0 = (com.google.android.gms.internal.ads.zzjn) r0
            r3.zza(r1, r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x00b2:
            android.os.Parcelable$Creator<com.google.android.gms.ads.formats.PublisherAdViewOptions> r0 = com.google.android.gms.ads.formats.PublisherAdViewOptions.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r0)
            com.google.android.gms.ads.formats.PublisherAdViewOptions r0 = (com.google.android.gms.ads.formats.PublisherAdViewOptions) r0
            r3.zza((com.google.android.gms.ads.formats.PublisherAdViewOptions) r0)
            r6.writeNoException()
            goto L_0x0010
        L_0x00c2:
            android.os.IBinder r0 = r5.readStrongBinder()
            com.google.android.gms.internal.ads.zzrl r0 = com.google.android.gms.internal.ads.zzrm.zzq(r0)
            r3.zza((com.google.android.gms.internal.ads.zzrl) r0)
            r6.writeNoException()
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzko.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
