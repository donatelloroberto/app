package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzi;
import com.google.android.gms.internal.gtm.zzk;
import com.google.android.gms.internal.gtm.zzop;
import com.google.android.gms.internal.gtm.zzoq;
import com.google.android.gms.internal.gtm.zzov;
import com.google.android.gms.tagmanager.zzeh;

@ShowFirstParty
public final class zzy extends BasePendingResult<ContainerHolder> {
    private final String zzaec;
    /* access modifiers changed from: private */
    public long zzaeh;
    private final Looper zzaek;
    private final TagManager zzaer;
    private final zzaf zzaeu;
    /* access modifiers changed from: private */
    public final zzej zzaev;
    private final int zzaew;
    /* access modifiers changed from: private */
    public final zzai zzaex;
    private zzah zzaey;
    private zzoq zzaez;
    /* access modifiers changed from: private */
    public volatile zzv zzafa;
    /* access modifiers changed from: private */
    public volatile boolean zzafb;
    /* access modifiers changed from: private */
    public zzk zzafc;
    private String zzafd;
    private zzag zzafe;
    private zzac zzaff;
    private final Context zzrm;
    /* access modifiers changed from: private */
    public final Clock zzsd;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzy(android.content.Context r19, com.google.android.gms.tagmanager.TagManager r20, android.os.Looper r21, java.lang.String r22, int r23, com.google.android.gms.tagmanager.zzal r24) {
        /*
            r18 = this;
            com.google.android.gms.tagmanager.zzex r4 = new com.google.android.gms.tagmanager.zzex
            r0 = r19
            r1 = r22
            r4.<init>(r0, r1)
            com.google.android.gms.tagmanager.zzes r16 = new com.google.android.gms.tagmanager.zzes
            r0 = r16
            r1 = r19
            r2 = r22
            r3 = r24
            r0.<init>(r1, r2, r3)
            com.google.android.gms.internal.gtm.zzoq r14 = new com.google.android.gms.internal.gtm.zzoq
            r0 = r19
            r14.<init>(r0)
            com.google.android.gms.common.util.Clock r15 = com.google.android.gms.common.util.DefaultClock.getInstance()
            com.google.android.gms.tagmanager.zzdg r5 = new com.google.android.gms.tagmanager.zzdg
            r6 = 1
            r7 = 5
            r8 = 900000(0xdbba0, double:4.44659E-318)
            r10 = 5000(0x1388, double:2.4703E-320)
            java.lang.String r12 = "refreshing"
            com.google.android.gms.common.util.Clock r13 = com.google.android.gms.common.util.DefaultClock.getInstance()
            r5.<init>(r6, r7, r8, r10, r12, r13)
            com.google.android.gms.tagmanager.zzai r17 = new com.google.android.gms.tagmanager.zzai
            r0 = r17
            r1 = r19
            r2 = r22
            r0.<init>(r1, r2)
            r6 = r18
            r7 = r19
            r8 = r20
            r9 = r21
            r10 = r22
            r11 = r23
            r12 = r4
            r13 = r16
            r16 = r5
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            r0 = r18
            com.google.android.gms.internal.gtm.zzoq r4 = r0.zzaez
            java.lang.String r5 = r24.zzhq()
            r4.zzcr(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzy.<init>(android.content.Context, com.google.android.gms.tagmanager.TagManager, android.os.Looper, java.lang.String, int, com.google.android.gms.tagmanager.zzal):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    private zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzah zzah, zzag zzag, zzoq zzoq, Clock clock, zzej zzej, zzai zzai) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.zzrm = context;
        this.zzaer = tagManager;
        this.zzaek = looper == null ? Looper.getMainLooper() : looper;
        this.zzaec = str;
        this.zzaew = i;
        this.zzaey = zzah;
        this.zzafe = zzag;
        this.zzaez = zzoq;
        this.zzaeu = new zzaf(this, (zzz) null);
        this.zzafc = new zzk();
        this.zzsd = clock;
        this.zzaev = zzej;
        this.zzaex = zzai;
        if (zzhi()) {
            zzao(zzeh.zziy().zzja());
        }
    }

    public final void zzhf() {
        zzov zzt = this.zzaey.zzt(this.zzaew);
        if (zzt != null) {
            setResult(new zzv(this.zzaer, this.zzaek, new Container(this.zzrm, this.zzaer.getDataLayer(), this.zzaec, 0, zzt), new zzaa(this)));
        } else {
            zzdi.zzav("Default was requested, but no default container was found");
            setResult(createFailedResult(new Status(10, "Default was requested, but no default container was found", (PendingIntent) null)));
        }
        this.zzafe = null;
        this.zzaey = null;
    }

    public final void zzhg() {
        zzd(false);
    }

    public final void zzhh() {
        zzd(true);
    }

    private final void zzd(boolean z) {
        this.zzaey.zza((zzdh<zzop>) new zzad(this, (zzz) null));
        this.zzafe.zza(new zzae(this, (zzz) null));
        zzov zzt = this.zzaey.zzt(this.zzaew);
        if (zzt != null) {
            this.zzafa = new zzv(this.zzaer, this.zzaek, new Container(this.zzrm, this.zzaer.getDataLayer(), this.zzaec, 0, zzt), this.zzaeu);
        }
        this.zzaff = new zzab(this, z);
        if (zzhi()) {
            this.zzafe.zza(0, "");
        } else {
            this.zzaey.zzhk();
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zza(zzk zzk, long j, boolean z) {
        if (z) {
            boolean z2 = this.zzafb;
        }
        if (!isReady() || this.zzafa != null) {
            this.zzafc = zzk;
            this.zzaeh = j;
            long zzhl = this.zzaex.zzhl();
            zzk(Math.max(0, Math.min(zzhl, (this.zzaeh + zzhl) - this.zzsd.currentTimeMillis())));
            Container container = new Container(this.zzrm, this.zzaer.getDataLayer(), this.zzaec, j, zzk);
            if (this.zzafa == null) {
                this.zzafa = new zzv(this.zzaer, this.zzaek, container, this.zzaeu);
            } else {
                this.zzafa.zza(container);
            }
            if (!isReady() && this.zzaff.zzb(container)) {
                setResult(this.zzafa);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final synchronized void zzao(String str) {
        this.zzafd = str;
        if (this.zzafe != null) {
            this.zzafe.zzap(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized String zzhc() {
        return this.zzafd;
    }

    /* access modifiers changed from: private */
    public final synchronized void zzk(long j) {
        if (this.zzafe == null) {
            zzdi.zzac("Refresh requested, but no network load scheduler.");
        } else {
            this.zzafe.zza(j, this.zzafc.zzql);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public final ContainerHolder createFailedResult(Status status) {
        if (this.zzafa != null) {
            return this.zzafa;
        }
        if (status == Status.RESULT_TIMEOUT) {
            zzdi.zzav("timer expired: setting result to failure");
        }
        return new zzv(status);
    }

    /* access modifiers changed from: private */
    public final boolean zzhi() {
        zzeh zziy = zzeh.zziy();
        return (zziy.zziz() == zzeh.zza.CONTAINER || zziy.zziz() == zzeh.zza.CONTAINER_DEBUG) && this.zzaec.equals(zziy.getContainerId());
    }

    /* access modifiers changed from: private */
    public final synchronized void zza(zzk zzk) {
        if (this.zzaey != null) {
            zzop zzop = new zzop();
            zzop.zzaux = this.zzaeh;
            zzop.zzqk = new zzi();
            zzop.zzauy = zzk;
            this.zzaey.zza(zzop);
        }
    }
}
