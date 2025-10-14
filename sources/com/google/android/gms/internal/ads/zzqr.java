package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzqr extends NativeContentAd {
    private final VideoController zzasv = new VideoController();
    private final List<NativeAd.Image> zzbko = new ArrayList();
    private final NativeAd.AdChoicesInfo zzbkq;
    private final zzqo zzbkr;
    private final zzpz zzbks;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: com.google.android.gms.internal.ads.zzpy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: com.google.android.gms.internal.ads.zzpy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: com.google.android.gms.internal.ads.zzpy} */
    /* JADX WARNING: type inference failed for: r2v9, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzqr(com.google.android.gms.internal.ads.zzqo r7) {
        /*
            r6 = this;
            r3 = 0
            r6.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r6.zzbko = r1
            com.google.android.gms.ads.VideoController r1 = new com.google.android.gms.ads.VideoController
            r1.<init>()
            r6.zzasv = r1
            r6.zzbkr = r7
            com.google.android.gms.internal.ads.zzqo r1 = r6.zzbkr     // Catch:{ RemoteException -> 0x004d }
            java.util.List r1 = r1.getImages()     // Catch:{ RemoteException -> 0x004d }
            if (r1 == 0) goto L_0x0053
            java.util.Iterator r4 = r1.iterator()     // Catch:{ RemoteException -> 0x004d }
        L_0x0020:
            boolean r1 = r4.hasNext()     // Catch:{ RemoteException -> 0x004d }
            if (r1 == 0) goto L_0x0053
            java.lang.Object r1 = r4.next()     // Catch:{ RemoteException -> 0x004d }
            boolean r2 = r1 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x004d }
            if (r2 == 0) goto L_0x0080
            android.os.IBinder r1 = (android.os.IBinder) r1     // Catch:{ RemoteException -> 0x004d }
            if (r1 == 0) goto L_0x0080
            java.lang.String r2 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)     // Catch:{ RemoteException -> 0x004d }
            boolean r5 = r2 instanceof com.google.android.gms.internal.ads.zzpw     // Catch:{ RemoteException -> 0x004d }
            if (r5 == 0) goto L_0x0079
            r0 = r2
            com.google.android.gms.internal.ads.zzpw r0 = (com.google.android.gms.internal.ads.zzpw) r0     // Catch:{ RemoteException -> 0x004d }
            r1 = r0
        L_0x0040:
            if (r1 == 0) goto L_0x0020
            java.util.List<com.google.android.gms.ads.formats.NativeAd$Image> r2 = r6.zzbko     // Catch:{ RemoteException -> 0x004d }
            com.google.android.gms.internal.ads.zzpz r5 = new com.google.android.gms.internal.ads.zzpz     // Catch:{ RemoteException -> 0x004d }
            r5.<init>(r1)     // Catch:{ RemoteException -> 0x004d }
            r2.add(r5)     // Catch:{ RemoteException -> 0x004d }
            goto L_0x0020
        L_0x004d:
            r1 = move-exception
            java.lang.String r2 = ""
            com.google.android.gms.internal.ads.zzane.zzb(r2, r1)
        L_0x0053:
            com.google.android.gms.internal.ads.zzqo r1 = r6.zzbkr     // Catch:{ RemoteException -> 0x0084 }
            com.google.android.gms.internal.ads.zzpw r2 = r1.zzkg()     // Catch:{ RemoteException -> 0x0084 }
            if (r2 == 0) goto L_0x0082
            com.google.android.gms.internal.ads.zzpz r1 = new com.google.android.gms.internal.ads.zzpz     // Catch:{ RemoteException -> 0x0084 }
            r1.<init>(r2)     // Catch:{ RemoteException -> 0x0084 }
        L_0x0060:
            r6.zzbks = r1
            com.google.android.gms.internal.ads.zzqo r1 = r6.zzbkr     // Catch:{ RemoteException -> 0x008c }
            com.google.android.gms.internal.ads.zzps r1 = r1.zzkf()     // Catch:{ RemoteException -> 0x008c }
            if (r1 == 0) goto L_0x0076
            com.google.android.gms.internal.ads.zzpv r1 = new com.google.android.gms.internal.ads.zzpv     // Catch:{ RemoteException -> 0x008c }
            com.google.android.gms.internal.ads.zzqo r2 = r6.zzbkr     // Catch:{ RemoteException -> 0x008c }
            com.google.android.gms.internal.ads.zzps r2 = r2.zzkf()     // Catch:{ RemoteException -> 0x008c }
            r1.<init>(r2)     // Catch:{ RemoteException -> 0x008c }
            r3 = r1
        L_0x0076:
            r6.zzbkq = r3
            return
        L_0x0079:
            com.google.android.gms.internal.ads.zzpy r2 = new com.google.android.gms.internal.ads.zzpy     // Catch:{ RemoteException -> 0x004d }
            r2.<init>(r1)     // Catch:{ RemoteException -> 0x004d }
            r1 = r2
            goto L_0x0040
        L_0x0080:
            r1 = r3
            goto L_0x0040
        L_0x0082:
            r1 = r3
            goto L_0x0060
        L_0x0084:
            r1 = move-exception
            java.lang.String r2 = ""
            com.google.android.gms.internal.ads.zzane.zzb(r2, r1)
            r1 = r3
            goto L_0x0060
        L_0x008c:
            r1 = move-exception
            java.lang.String r2 = ""
            com.google.android.gms.internal.ads.zzane.zzb(r2, r1)
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqr.<init>(com.google.android.gms.internal.ads.zzqo):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: zzka */
    public final IObjectWrapper zzbe() {
        try {
            return this.zzbkr.zzka();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final void destroy() {
        try {
            this.zzbkr.destroy();
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzbkq;
    }

    public final CharSequence getAdvertiser() {
        try {
            return this.zzbkr.getAdvertiser();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getBody() {
        try {
            return this.zzbkr.getBody();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getCallToAction() {
        try {
            return this.zzbkr.getCallToAction();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            return this.zzbkr.getExtras();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getHeadline() {
        try {
            return this.zzbkr.getHeadline();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzbko;
    }

    public final NativeAd.Image getLogo() {
        return this.zzbks;
    }

    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzbkr.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzbkr.getVideoController() != null) {
                this.zzasv.zza(this.zzbkr.getVideoController());
            }
        } catch (RemoteException e) {
            zzane.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzasv;
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzbkr.performClick(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzbkr.recordImpression(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzbkr.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }
}
