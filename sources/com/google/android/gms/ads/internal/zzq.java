package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzoj;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzov;
import com.google.android.gms.internal.ads.zzox;
import com.google.android.gms.internal.ads.zzoy;
import com.google.android.gms.internal.ads.zzoz;
import com.google.android.gms.internal.ads.zzpa;
import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzxa;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.internal.ads.zzyf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzq extends zzd implements zzpa {
    private boolean zzvm;
    /* access modifiers changed from: private */
    public zzajh zzwr;
    private boolean zzws = false;

    public zzq(Context context, zzw zzw, zzjn zzjn, String str, zzxn zzxn, zzang zzang) {
        super(context, zzjn, str, zzxn, zzang, zzw);
    }

    private static zzajh zza(zzaji zzaji, int i) {
        return new zzajh(zzaji.zzcgs.zzccv, (zzaqw) null, zzaji.zzcos.zzbsn, i, zzaji.zzcos.zzbso, zzaji.zzcos.zzces, zzaji.zzcos.orientation, zzaji.zzcos.zzbsu, zzaji.zzcgs.zzccy, zzaji.zzcos.zzceq, (zzwx) null, (zzxq) null, (String) null, zzaji.zzcod, (zzxa) null, zzaji.zzcos.zzcer, zzaji.zzacv, zzaji.zzcos.zzcep, zzaji.zzcoh, zzaji.zzcoi, zzaji.zzcos.zzcev, zzaji.zzcob, (zzpb) null, zzaji.zzcos.zzcfe, zzaji.zzcos.zzcff, zzaji.zzcos.zzcff, zzaji.zzcos.zzcfh, zzaji.zzcos.zzcfi, (String) null, zzaji.zzcos.zzbsr, zzaji.zzcos.zzcfl, zzaji.zzcoq, zzaji.zzcos.zzzl, zzaji.zzcor, zzaji.zzcos.zzcfp, zzaji.zzcos.zzbsp, zzaji.zzcos.zzzm, zzaji.zzcos.zzcfq);
    }

    private final void zza(zzov zzov) {
        zzakk.zzcrm.post(new zzs(this, zzov));
    }

    private final boolean zzb(zzajh zzajh, zzajh zzajh2) {
        zzd((List<String>) null);
        if (!this.zzvw.zzfo()) {
            zzakb.zzdk("Native ad does not have custom rendering mode.");
            zzi(0);
            return false;
        }
        try {
            zzyf zzmu = zzajh2.zzbtx != null ? zzajh2.zzbtx.zzmu() : null;
            zzxz zzmo = zzajh2.zzbtx != null ? zzajh2.zzbtx.zzmo() : null;
            zzyc zzmp = zzajh2.zzbtx != null ? zzajh2.zzbtx.zzmp() : null;
            zzqs zzmt = zzajh2.zzbtx != null ? zzajh2.zzbtx.zzmt() : null;
            String zzc = zzc(zzajh2);
            if (zzmu == null || this.zzvw.zzadg == null) {
                if (zzmo != null) {
                    if (this.zzvw.zzadg != null) {
                        zzov zzov = new zzov(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz() != null ? zzmo.zzjz() : null, zzmo.getCallToAction(), (String) null, zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), (zzoj) null, zzmo.getVideoController(), zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), zzc, zzmo.getExtras());
                        zzov.zzb((zzoz) new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmo, (zzpb) zzov));
                        zza(zzov);
                    }
                }
                if (zzmo != null) {
                    if (this.zzvw.zzade != null) {
                        zzoo zzoo = new zzoo(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz() != null ? zzmo.zzjz() : null, zzmo.getCallToAction(), zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), (zzoj) null, zzmo.getExtras(), zzmo.getVideoController(), zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), zzc);
                        zzoo.zzb((zzoz) new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmo, (zzpb) zzoo));
                        zzakk.zzcrm.post(new zzt(this, zzoo));
                    }
                }
                if (zzmp != null && this.zzvw.zzadg != null) {
                    zzov zzov2 = new zzov(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg() != null ? zzmp.zzkg() : null, zzmp.getCallToAction(), zzmp.getAdvertiser(), -1.0d, (String) null, (String) null, (zzoj) null, zzmp.getVideoController(), zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), zzc, zzmp.getExtras());
                    zzov2.zzb((zzoz) new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmp, (zzpb) zzov2));
                    zza(zzov2);
                } else if (zzmp != null && this.zzvw.zzadf != null) {
                    zzoq zzoq = new zzoq(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg() != null ? zzmp.zzkg() : null, zzmp.getCallToAction(), zzmp.getAdvertiser(), (zzoj) null, zzmp.getExtras(), zzmp.getVideoController(), zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), zzc);
                    zzoq.zzb((zzoz) new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmp, (zzpb) zzoq));
                    zzakk.zzcrm.post(new zzu(this, zzoq));
                } else if (zzmt == null || this.zzvw.zzadi == null || this.zzvw.zzadi.get(zzmt.getCustomTemplateId()) == null) {
                    zzakb.zzdk("No matching mapper/listener for retrieved native ad template.");
                    zzi(0);
                    return false;
                } else {
                    zzakk.zzcrm.post(new zzv(this, zzmt));
                }
            } else {
                zzov zzov3 = new zzov(zzmu.getHeadline(), zzmu.getImages(), zzmu.getBody(), zzmu.zzjz() != null ? zzmu.zzjz() : null, zzmu.getCallToAction(), zzmu.getAdvertiser(), zzmu.getStarRating(), zzmu.getStore(), zzmu.getPrice(), (zzoj) null, zzmu.getVideoController(), zzmu.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmu.zzmw()) : null, zzmu.zzke(), zzc, zzmu.getExtras());
                zzov3.zzb((zzoz) new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmu, (zzpb) zzov3));
                zza(zzov3);
            }
            return super.zza(zzajh, zzajh2);
        } catch (RemoteException e) {
            zzakb.zzd("#007 Could not call remote method.", e);
            zzi(0);
            return false;
        }
    }

    private final boolean zzc(zzajh zzajh, zzajh zzajh2) {
        View zze = zzas.zze(zzajh2);
        if (zze == null) {
            return false;
        }
        View nextView = this.zzvw.zzacs.getNextView();
        if (nextView != null) {
            if (nextView instanceof zzaqw) {
                ((zzaqw) nextView).destroy();
            }
            this.zzvw.zzacs.removeView(nextView);
        }
        if (!zzas.zzf(zzajh2)) {
            try {
                zzg(zze);
            } catch (Throwable th) {
                zzbv.zzeo().zza(th, "AdLoaderManager.swapBannerViews");
                zzakb.zzc("Could not add mediation view to view hierarchy.", th);
                return false;
            }
        }
        if (this.zzvw.zzacs.getChildCount() > 1) {
            this.zzvw.zzacs.showNext();
        }
        if (zzajh != null) {
            View nextView2 = this.zzvw.zzacs.getNextView();
            if (nextView2 != null) {
                this.zzvw.zzacs.removeView(nextView2);
            }
            this.zzvw.zzfn();
        }
        this.zzvw.zzacs.setMinimumWidth(zzbk().widthPixels);
        this.zzvw.zzacs.setMinimumHeight(zzbk().heightPixels);
        this.zzvw.zzacs.requestLayout();
        this.zzvw.zzacs.setVisibility(0);
        return true;
    }

    @Nullable
    private final zzwy zzcw() {
        if (this.zzvw.zzacw == null || !this.zzvw.zzacw.zzceq) {
            return null;
        }
        return this.zzvw.zzacw.zzcod;
    }

    @Nullable
    public final zzlo getVideoController() {
        return null;
    }

    public final void pause() {
        if (!this.zzws) {
            throw new IllegalStateException("Native Ad does not support pause().");
        }
        super.pause();
    }

    public final void resume() {
        if (!this.zzws) {
            throw new IllegalStateException("Native Ad does not support resume().");
        }
        super.resume();
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is not supported by AdLoaderManager.");
    }

    public final void zza(zzaji zzaji, zznx zznx) {
        this.zzwr = null;
        if (zzaji.errorCode != -2) {
            this.zzwr = zza(zzaji, zzaji.errorCode);
        } else if (!zzaji.zzcos.zzceq) {
            zzakb.zzdk("partialAdState is not mediation");
            this.zzwr = zza(zzaji, 0);
        }
        if (this.zzwr != null) {
            zzakk.zzcrm.post(new zzr(this));
            return;
        }
        if (zzaji.zzacv != null) {
            this.zzvw.zzacv = zzaji.zzacv;
        }
        this.zzvw.zzadv = 0;
        zzbw zzbw = this.zzvw;
        zzbv.zzej();
        zzbw.zzacu = zzabl.zza(this.zzvw.zzrt, this, zzaji, this.zzvw.zzacq, (zzaqw) null, this.zzwh, this, zznx);
    }

    public final void zza(zzod zzod) {
        throw new IllegalStateException("CustomRendering is not supported by AdLoaderManager.");
    }

    public final void zza(zzox zzox) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable) null);
    }

    public final void zza(zzoz zzoz) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable) null);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(@Nullable zzajh zzajh, zzajh zzajh2) {
        boolean z;
        if (!this.zzvw.zzfo()) {
            throw new IllegalStateException("AdLoader API does not support custom rendering.");
        } else if (!zzajh2.zzceq) {
            zzi(0);
            zzakb.zzdk("newState is not mediation.");
            return false;
        } else {
            if (zzajh2.zzbtw != null && zzajh2.zzbtw.zzmf()) {
                if (this.zzvw.zzfo() && this.zzvw.zzacs != null) {
                    this.zzvw.zzacs.zzfr().zzdb(zzajh2.zzcev);
                }
                if (!super.zza(zzajh, zzajh2)) {
                    z = false;
                } else if (!this.zzvw.zzfo() || zzc(zzajh, zzajh2)) {
                    if (!this.zzvw.zzfp()) {
                        super.zza(zzajh2, false);
                    }
                    z = true;
                } else {
                    zzi(0);
                    z = false;
                }
                if (!z) {
                    return false;
                }
                this.zzws = true;
            } else if (zzajh2.zzbtw == null || !zzajh2.zzbtw.zzmg()) {
                zzi(0);
                zzakb.zzdk("Response is neither banner nor native.");
                return false;
            } else if (!zzb(zzajh, zzajh2)) {
                return false;
            }
            zze(new ArrayList(Arrays.asList(new Integer[]{2})));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzjj zzjj, zzajh zzajh, boolean z) {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void zzb(@Nullable IObjectWrapper iObjectWrapper) {
        Object unwrap = iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null;
        if (unwrap instanceof zzoz) {
            ((zzoz) unwrap).zzkl();
        }
        super.zzb(this.zzvw.zzacw, false);
    }

    public final boolean zzb(zzjj zzjj) {
        if (this.zzvw.zzadn != null && this.zzvw.zzadn.size() == 1 && this.zzvw.zzadn.get(0).intValue() == 2) {
            zzakb.e("Requesting only banner Ad from AdLoader or calling loadAd on returned banner is not yet supported");
            zzi(0);
            return false;
        } else if (this.zzvw.zzadm == null) {
            return super.zzb(zzjj);
        } else {
            if (zzjj.zzaqb != this.zzvm) {
                zzjj = new zzjj(zzjj.versionCode, zzjj.zzapw, zzjj.extras, zzjj.zzapx, zzjj.zzapy, zzjj.zzapz, zzjj.zzaqa, zzjj.zzaqb || this.zzvm, zzjj.zzaqc, zzjj.zzaqd, zzjj.zzaqe, zzjj.zzaqf, zzjj.zzaqg, zzjj.zzaqh, zzjj.zzaqi, zzjj.zzaqj, zzjj.zzaqk, zzjj.zzaql);
            }
            return super.zzb(zzjj);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbq() {
        super.zzbq();
        zzajh zzajh = this.zzvw.zzacw;
        if (zzajh != null && zzajh.zzbtw != null && zzajh.zzbtw.zzmf() && this.zzvw.zzadm != null) {
            try {
                this.zzvw.zzadm.zza(this, ObjectWrapper.wrap(this.zzvw.zzrt));
                super.zzb(this.zzvw.zzacw, false);
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzce() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty) || this.zzvw.zzacw.zzbtw == null || !this.zzvw.zzacw.zzbtw.zzmg()) {
            super.zzce();
        } else {
            zzbs();
        }
    }

    public final void zzcj() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty) || this.zzvw.zzacw.zzbtw == null || !this.zzvw.zzacw.zzbtw.zzmg()) {
            super.zzcj();
        } else {
            zzbr();
        }
    }

    public final void zzcr() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable) null);
    }

    public final void zzcs() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable) null);
    }

    public final void zzct() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable) null);
    }

    public final boolean zzcu() {
        if (zzcw() != null) {
            return zzcw().zzbta;
        }
        return false;
    }

    public final boolean zzcv() {
        if (zzcw() != null) {
            return zzcw().zzbtb;
        }
        return false;
    }

    public final void zzd(@Nullable List<String> list) {
        Preconditions.checkMainThread("setNativeTemplates must be called on the main UI thread.");
        this.zzvw.zzads = list;
    }

    public final void zze(List<Integer> list) {
        Preconditions.checkMainThread("setAllowedAdTypes must be called on the main UI thread.");
        this.zzvw.zzadn = list;
    }

    public final void zzi(View view) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable) null);
    }

    @Nullable
    public final zzrc zzr(String str) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        return this.zzvw.zzadh.get(str);
    }
}
