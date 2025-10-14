package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzru extends UnifiedNativeAd {
    private final zzrr zzbkw;
    private final List<NativeAd.Image> zzbkx = new ArrayList();
    private final zzpz zzbky;
    private final VideoController zzbkz = new VideoController();
    private final NativeAd.AdChoicesInfo zzbla;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: com.google.android.gms.internal.ads.zzpy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: com.google.android.gms.internal.ads.zzpy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: com.google.android.gms.internal.ads.zzpy} */
    /* JADX WARNING: type inference failed for: r2v9, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzru(com.google.android.gms.internal.ads.zzrr r7) {
        /*
            r6 = this;
            r3 = 0
            r6.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r6.zzbkx = r1
            com.google.android.gms.ads.VideoController r1 = new com.google.android.gms.ads.VideoController
            r1.<init>()
            r6.zzbkz = r1
            r6.zzbkw = r7
            com.google.android.gms.internal.ads.zzrr r1 = r6.zzbkw     // Catch:{ RemoteException -> 0x004d }
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
            java.util.List<com.google.android.gms.ads.formats.NativeAd$Image> r2 = r6.zzbkx     // Catch:{ RemoteException -> 0x004d }
            com.google.android.gms.internal.ads.zzpz r5 = new com.google.android.gms.internal.ads.zzpz     // Catch:{ RemoteException -> 0x004d }
            r5.<init>(r1)     // Catch:{ RemoteException -> 0x004d }
            r2.add(r5)     // Catch:{ RemoteException -> 0x004d }
            goto L_0x0020
        L_0x004d:
            r1 = move-exception
            java.lang.String r2 = ""
            com.google.android.gms.internal.ads.zzane.zzb(r2, r1)
        L_0x0053:
            com.google.android.gms.internal.ads.zzrr r1 = r6.zzbkw     // Catch:{ RemoteException -> 0x0084 }
            com.google.android.gms.internal.ads.zzpw r2 = r1.zzjz()     // Catch:{ RemoteException -> 0x0084 }
            if (r2 == 0) goto L_0x0082
            com.google.android.gms.internal.ads.zzpz r1 = new com.google.android.gms.internal.ads.zzpz     // Catch:{ RemoteException -> 0x0084 }
            r1.<init>(r2)     // Catch:{ RemoteException -> 0x0084 }
        L_0x0060:
            r6.zzbky = r1
            com.google.android.gms.internal.ads.zzrr r1 = r6.zzbkw     // Catch:{ RemoteException -> 0x008c }
            com.google.android.gms.internal.ads.zzps r1 = r1.zzkf()     // Catch:{ RemoteException -> 0x008c }
            if (r1 == 0) goto L_0x0076
            com.google.android.gms.internal.ads.zzpv r1 = new com.google.android.gms.internal.ads.zzpv     // Catch:{ RemoteException -> 0x008c }
            com.google.android.gms.internal.ads.zzrr r2 = r6.zzbkw     // Catch:{ RemoteException -> 0x008c }
            com.google.android.gms.internal.ads.zzps r2 = r2.zzkf()     // Catch:{ RemoteException -> 0x008c }
            r1.<init>(r2)     // Catch:{ RemoteException -> 0x008c }
            r3 = r1
        L_0x0076:
            r6.zzbla = r3
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzru.<init>(com.google.android.gms.internal.ads.zzrr):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: zzka */
    public final IObjectWrapper zzbe() {
        try {
            return this.zzbkw.zzka();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final void cancelUnconfirmedClick() {
        try {
            this.zzbkw.cancelUnconfirmedClick();
        } catch (RemoteException e) {
            zzane.zzb("Failed to cancelUnconfirmedClick", e);
        }
    }

    public final void destroy() {
        try {
            this.zzbkw.destroy();
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzbla;
    }

    public final String getAdvertiser() {
        try {
            return this.zzbkw.getAdvertiser();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getBody() {
        try {
            return this.zzbkw.getBody();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getCallToAction() {
        try {
            return this.zzbkw.getCallToAction();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            Bundle extras = this.zzbkw.getExtras();
            if (extras != null) {
                return extras;
            }
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
        return new Bundle();
    }

    public final String getHeadline() {
        try {
            return this.zzbkw.getHeadline();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final NativeAd.Image getIcon() {
        return this.zzbky;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzbkx;
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzbkw.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getPrice() {
        try {
            return this.zzbkw.getPrice();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Double getStarRating() {
        try {
            double starRating = this.zzbkw.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getStore() {
        try {
            return this.zzbkw.getStore();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzbkw.getVideoController() != null) {
                this.zzbkz.zza(this.zzbkw.getVideoController());
            }
        } catch (RemoteException e) {
            zzane.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzbkz;
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzbkw.performClick(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzbkw.recordImpression(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzbkw.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final void setUnconfirmedClickListener(UnifiedNativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zzbkw.zza(new zzse(unconfirmedClickListener));
        } catch (RemoteException e) {
            zzane.zzb("Failed to setUnconfirmedClickListener", e);
        }
    }

    public final Object zzbh() {
        try {
            IObjectWrapper zzke = this.zzbkw.zzke();
            if (zzke != null) {
                return ObjectWrapper.unwrap(zzke);
            }
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
        return null;
    }
}
