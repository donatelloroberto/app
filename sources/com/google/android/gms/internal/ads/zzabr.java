package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@zzadh
public final class zzabr extends zzabh {
    /* access modifiers changed from: private */
    public final zzaqw zzbnd;
    private zzwy zzbtj;
    @VisibleForTesting
    private zzww zzbzq;
    protected zzxe zzbzr;
    /* access modifiers changed from: private */
    public boolean zzbzs;
    private final zznx zzvr;
    private zzxn zzwh;

    zzabr(Context context, zzaji zzaji, zzxn zzxn, zzabm zzabm, zznx zznx, zzaqw zzaqw) {
        super(context, zzaji, zzabm);
        this.zzwh = zzxn;
        this.zzbtj = zzaji.zzcod;
        this.zzvr = zznx;
        this.zzbnd = zzaqw;
    }

    public final void onStop() {
        synchronized (this.zzbzh) {
            super.onStop();
            if (this.zzbzq != null) {
                this.zzbzq.cancel();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final zzajh zzaa(int i) {
        String str;
        int i2;
        zzaef zzaef = this.zzbze.zzcgs;
        zzjj zzjj = zzaef.zzccv;
        zzaqw zzaqw = this.zzbnd;
        List<String> list = this.zzbzf.zzbsn;
        List<String> list2 = this.zzbzf.zzbso;
        List<String> list3 = this.zzbzf.zzces;
        int i3 = this.zzbzf.orientation;
        long j = this.zzbzf.zzbsu;
        String str2 = zzaef.zzccy;
        boolean z = this.zzbzf.zzceq;
        zzwx zzwx = this.zzbzr != null ? this.zzbzr.zzbtw : null;
        zzxq zzxq = this.zzbzr != null ? this.zzbzr.zzbtx : null;
        String name = this.zzbzr != null ? this.zzbzr.zzbty : AdMobAdapter.class.getName();
        zzwy zzwy = this.zzbtj;
        zzxa zzxa = this.zzbzr != null ? this.zzbzr.zzbtz : null;
        long j2 = this.zzbzf.zzcer;
        zzjn zzjn = this.zzbze.zzacv;
        long j3 = this.zzbzf.zzcep;
        long j4 = this.zzbze.zzcoh;
        long j5 = this.zzbzf.zzceu;
        String str3 = this.zzbzf.zzcev;
        JSONObject jSONObject = this.zzbze.zzcob;
        zzaig zzaig = this.zzbzf.zzcfe;
        List<String> list4 = this.zzbzf.zzcff;
        List<String> list5 = this.zzbzf.zzcfg;
        boolean z2 = this.zzbtj != null ? this.zzbtj.zzbsz : false;
        zzael zzael = this.zzbzf.zzcfi;
        if (this.zzbzq != null) {
            List<zzxe> zzme = this.zzbzq.zzme();
            if (zzme == null) {
                str = "".toString();
            } else {
                String str4 = "";
                for (zzxe next : zzme) {
                    if (!(next == null || next.zzbtw == null || TextUtils.isEmpty(next.zzbtw.zzbru))) {
                        String valueOf = String.valueOf(str4);
                        String str5 = next.zzbtw.zzbru;
                        switch (next.zzbtv) {
                            case -1:
                                i2 = 4;
                                break;
                            case 0:
                                i2 = 0;
                                break;
                            case 1:
                                i2 = 1;
                                break;
                            case 3:
                                i2 = 2;
                                break;
                            case 4:
                                i2 = 3;
                                break;
                            case 5:
                                i2 = 5;
                                break;
                            default:
                                i2 = 6;
                                break;
                        }
                        String sb = new StringBuilder(String.valueOf(str5).length() + 33).append(str5).append(".").append(i2).append(".").append(next.zzbub).toString();
                        str4 = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(sb).length()).append(valueOf).append(sb).append("_").toString();
                    }
                }
                str = str4.substring(0, Math.max(0, str4.length() - 1));
            }
        } else {
            str = null;
        }
        return new zzajh(zzjj, zzaqw, list, i, list2, list3, i3, j, str2, z, zzwx, zzxq, name, zzwy, zzxa, j2, zzjn, j3, j4, j5, str3, jSONObject, (zzpb) null, zzaig, list4, list5, z2, zzael, str, this.zzbzf.zzbsr, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbzf.zzzl, this.zzbze.zzcor, this.zzbzf.zzcfp, this.zzbzf.zzbsp, this.zzbzf.zzzm, this.zzbzf.zzcfq);
    }

    /* access modifiers changed from: protected */
    public final void zze(long j) throws zzabk {
        zzww zzxk;
        Bundle bundle;
        synchronized (this.zzbzh) {
            if (this.zzbtj.zzbsx != -1) {
                zzxk = new zzxh(this.mContext, this.zzbze.zzcgs, this.zzwh, this.zzbtj, this.zzbzf.zzare, this.zzbzf.zzarg, this.zzbzf.zzcfj, j, ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), 2, this.zzbze.zzcor);
            } else {
                zzxk = new zzxk(this.mContext, this.zzbze.zzcgs, this.zzwh, this.zzbtj, this.zzbzf.zzare, this.zzbzf.zzarg, this.zzbzf.zzcfj, j, ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), this.zzvr, this.zzbze.zzcor);
            }
            this.zzbzq = zzxk;
        }
        ArrayList arrayList = new ArrayList(this.zzbtj.zzbsm);
        boolean z = false;
        Bundle bundle2 = this.zzbze.zzcgs.zzccv.zzaqg;
        if (!(bundle2 == null || (bundle = bundle2.getBundle("com.google.ads.mediation.admob.AdMobAdapter")) == null)) {
            z = bundle.getBoolean("_skipMediation");
        }
        if (z) {
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (!((zzwx) listIterator.next()).zzbrt.contains("com.google.ads.mediation.admob.AdMobAdapter")) {
                    listIterator.remove();
                }
            }
        }
        this.zzbzr = this.zzbzq.zzh(arrayList);
        switch (this.zzbzr.zzbtv) {
            case 0:
                if (this.zzbzr.zzbtw != null && this.zzbzr.zzbtw.zzbsf != null) {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    zzakk.zzcrm.post(new zzabs(this, countDownLatch));
                    try {
                        countDownLatch.await(10, TimeUnit.SECONDS);
                        synchronized (this.zzbzh) {
                            if (!this.zzbzs) {
                                throw new zzabk("View could not be prepared", 0);
                            } else if (this.zzbnd.isDestroyed()) {
                                throw new zzabk("Assets not loaded, web view is destroyed", 0);
                            }
                        }
                        return;
                    } catch (InterruptedException e) {
                        String valueOf = String.valueOf(e);
                        throw new zzabk(new StringBuilder(String.valueOf(valueOf).length() + 38).append("Interrupted while waiting for latch : ").append(valueOf).toString(), 0);
                    }
                } else {
                    return;
                }
            case 1:
                throw new zzabk("No fill from any mediation ad networks.", 3);
            default:
                throw new zzabk(new StringBuilder(40).append("Unexpected mediation result: ").append(this.zzbzr.zzbtv).toString(), 0);
        }
    }
}
