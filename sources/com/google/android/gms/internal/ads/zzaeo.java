package com.google.android.gms.internal.ads;

public abstract class zzaeo extends zzek implements zzaen {
    public zzaeo() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v5, types: [com.google.android.gms.internal.ads.zzaet] */
    /* JADX WARNING: type inference failed for: r1v10, types: [com.google.android.gms.internal.ads.zzaet] */
    /* JADX WARNING: type inference failed for: r1v15, types: [com.google.android.gms.internal.ads.zzaeq] */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r1v20 */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
        /*
            r4 = this;
            r1 = 0
            switch(r5) {
                case 1: goto L_0x0006;
                case 2: goto L_0x001a;
                case 3: goto L_0x0004;
                case 4: goto L_0x0042;
                case 5: goto L_0x006a;
                default: goto L_0x0004;
            }
        L_0x0004:
            r0 = 0
        L_0x0005:
            return r0
        L_0x0006:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaef> r0 = com.google.android.gms.internal.ads.zzaef.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, r0)
            com.google.android.gms.internal.ads.zzaef r0 = (com.google.android.gms.internal.ads.zzaef) r0
            com.google.android.gms.internal.ads.zzaej r0 = r4.zzb(r0)
            r7.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r7, r0)
        L_0x0018:
            r0 = 1
            goto L_0x0005
        L_0x001a:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaef> r0 = com.google.android.gms.internal.ads.zzaef.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, r0)
            com.google.android.gms.internal.ads.zzaef r0 = (com.google.android.gms.internal.ads.zzaef) r0
            android.os.IBinder r2 = r6.readStrongBinder()
            if (r2 != 0) goto L_0x002f
        L_0x0028:
            r4.zza((com.google.android.gms.internal.ads.zzaef) r0, (com.google.android.gms.internal.ads.zzaeq) r1)
            r7.writeNoException()
            goto L_0x0018
        L_0x002f:
            java.lang.String r1 = "com.google.android.gms.ads.internal.request.IAdResponseListener"
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.ads.zzaeq
            if (r3 == 0) goto L_0x003c
            com.google.android.gms.internal.ads.zzaeq r1 = (com.google.android.gms.internal.ads.zzaeq) r1
            goto L_0x0028
        L_0x003c:
            com.google.android.gms.internal.ads.zzaes r1 = new com.google.android.gms.internal.ads.zzaes
            r1.<init>(r2)
            goto L_0x0028
        L_0x0042:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaey> r0 = com.google.android.gms.internal.ads.zzaey.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, r0)
            com.google.android.gms.internal.ads.zzaey r0 = (com.google.android.gms.internal.ads.zzaey) r0
            android.os.IBinder r2 = r6.readStrongBinder()
            if (r2 != 0) goto L_0x0057
        L_0x0050:
            r4.zza((com.google.android.gms.internal.ads.zzaey) r0, (com.google.android.gms.internal.ads.zzaet) r1)
            r7.writeNoException()
            goto L_0x0018
        L_0x0057:
            java.lang.String r1 = "com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener"
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.ads.zzaet
            if (r3 == 0) goto L_0x0064
            com.google.android.gms.internal.ads.zzaet r1 = (com.google.android.gms.internal.ads.zzaet) r1
            goto L_0x0050
        L_0x0064:
            com.google.android.gms.internal.ads.zzaeu r1 = new com.google.android.gms.internal.ads.zzaeu
            r1.<init>(r2)
            goto L_0x0050
        L_0x006a:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaey> r0 = com.google.android.gms.internal.ads.zzaey.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r6, r0)
            com.google.android.gms.internal.ads.zzaey r0 = (com.google.android.gms.internal.ads.zzaey) r0
            android.os.IBinder r2 = r6.readStrongBinder()
            if (r2 != 0) goto L_0x007f
        L_0x0078:
            r4.zzb(r0, r1)
            r7.writeNoException()
            goto L_0x0018
        L_0x007f:
            java.lang.String r1 = "com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener"
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.ads.zzaet
            if (r3 == 0) goto L_0x008c
            com.google.android.gms.internal.ads.zzaet r1 = (com.google.android.gms.internal.ads.zzaet) r1
            goto L_0x0078
        L_0x008c:
            com.google.android.gms.internal.ads.zzaeu r1 = new com.google.android.gms.internal.ads.zzaeu
            r1.<init>(r2)
            goto L_0x0078
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaeo.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
