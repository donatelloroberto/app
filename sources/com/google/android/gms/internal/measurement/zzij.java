package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
public final class zzij extends zzhi {
    /* access modifiers changed from: private */
    public final zzix zzapg;
    /* access modifiers changed from: private */
    public zzez zzaph;
    private volatile Boolean zzapi;
    private final zzeo zzapj;
    private final zzjn zzapk;
    private final List<Runnable> zzapl = new ArrayList();
    private final zzeo zzapm;

    protected zzij(zzgm zzgm) {
        super(zzgm);
        this.zzapk = new zzjn(zzgm.zzbt());
        this.zzapg = new zzix(this);
        this.zzapj = new zzik(this, zzgm);
        this.zzapm = new zzip(this, zzgm);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void onServiceDisconnected(ComponentName componentName) {
        zzab();
        if (this.zzaph != null) {
            this.zzaph = null;
            zzgf().zziz().zzg("Disconnected from device MeasurementService", componentName);
            zzab();
            zzdf();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzcu() {
        zzab();
        this.zzapk.start();
        this.zzapj.zzh(zzey.zzahv.get().longValue());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzcv() {
        zzab();
        if (isConnected()) {
            zzgf().zziz().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    private final void zzf(Runnable runnable) throws IllegalStateException {
        zzab();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzapl.size()) >= 1000) {
            zzgf().zzis().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzapl.add(runnable);
            this.zzapm.zzh(60000);
            zzdf();
        }
    }

    @Nullable
    @WorkerThread
    private final zzdz zzk(boolean z) {
        zzgi();
        return zzfw().zzbh(z ? zzgf().zzjb() : null);
    }

    private final boolean zzkn() {
        zzgi();
        return true;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzkp() {
        zzab();
        zzgf().zziz().zzg("Processing queued up service tasks", Integer.valueOf(this.zzapl.size()));
        for (Runnable run : this.zzapl) {
            try {
                run.run();
            } catch (Exception e) {
                zzgf().zzis().zzg("Task exception while flushing queue", e);
            }
        }
        this.zzapl.clear();
        this.zzapm.cancel();
    }

    @WorkerThread
    public final void disconnect() {
        zzab();
        zzch();
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzapg);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        this.zzaph = null;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final boolean isConnected() {
        zzab();
        zzch();
        return this.zzaph != null;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void resetAnalyticsData() {
        zzab();
        zzfs();
        zzch();
        zzdz zzk = zzk(false);
        if (zzkn()) {
            zzga().resetAnalyticsData();
        }
        zzf(new zzil(this, zzk));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzez zzez) {
        zzab();
        Preconditions.checkNotNull(zzez);
        this.zzaph = zzez;
        zzcu();
        zzkp();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzez zzez, AbstractSafeParcelable abstractSafeParcelable, zzdz zzdz) {
        List<AbstractSafeParcelable> zzp;
        zzab();
        zzfs();
        zzch();
        boolean zzkn = zzkn();
        int i = 100;
        for (int i2 = 0; i2 < 1001 && i == 100; i2++) {
            ArrayList arrayList = new ArrayList();
            if (!zzkn || (zzp = zzga().zzp(100)) == null) {
                i = 0;
            } else {
                arrayList.addAll(zzp);
                i = zzp.size();
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList2.get(i3);
                i3++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzew) {
                    try {
                        zzez.zza((zzew) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e) {
                        zzgf().zzis().zzg("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzjz) {
                    try {
                        zzez.zza((zzjz) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e2) {
                        zzgf().zzis().zzg("Failed to send attribute to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzee) {
                    try {
                        zzez.zza((zzee) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e3) {
                        zzgf().zzis().zzg("Failed to send conditional property to the service", e3);
                    }
                } else {
                    zzgf().zzis().log("Discarding data. Unrecognized parcel type.");
                }
            }
        }
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzab();
        zzch();
        zzf(new zzim(this, atomicReference, zzk(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzee>> atomicReference, String str, String str2, String str3) {
        zzab();
        zzch();
        zzf(new zzit(this, atomicReference, str, str2, str3, zzk(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzjz>> atomicReference, String str, String str2, String str3, boolean z) {
        zzab();
        zzch();
        zzf(new zziu(this, atomicReference, str, str2, str3, z, zzk(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzjz>> atomicReference, boolean z) {
        zzab();
        zzch();
        zzf(new zziw(this, atomicReference, zzk(false), z));
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzew zzew, String str) {
        Preconditions.checkNotNull(zzew);
        zzab();
        zzch();
        boolean zzkn = zzkn();
        zzf(new zzir(this, zzkn, zzkn && zzga().zza(zzew), zzew, zzk(true), str));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzif zzif) {
        zzab();
        zzch();
        zzf(new zzio(this, zzif));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzjz zzjz) {
        zzab();
        zzch();
        zzf(new zziv(this, zzkn() && zzga().zza(zzjz), zzjz, zzk(true)));
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzd(zzee zzee) {
        Preconditions.checkNotNull(zzee);
        zzab();
        zzch();
        zzgi();
        zzf(new zzis(this, true, zzga().zzc(zzee), new zzee(zzee), zzk(true), zzee));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzdf() {
        boolean z;
        boolean z2;
        boolean z3 = true;
        zzab();
        zzch();
        if (!isConnected()) {
            if (this.zzapi == null) {
                zzab();
                zzch();
                Boolean zzji = zzgg().zzji();
                if (zzji == null || !zzji.booleanValue()) {
                    zzgi();
                    if (zzfw().zziq() != 1) {
                        zzgf().zziz().log("Checking service availability");
                        int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(zzgc().getContext(), 12451000);
                        switch (isGooglePlayServicesAvailable) {
                            case 0:
                                zzgf().zziz().log("Service available");
                                z = true;
                                z2 = true;
                                break;
                            case 1:
                                zzgf().zziz().log("Service missing");
                                z = true;
                                z2 = false;
                                break;
                            case 2:
                                zzgf().zziy().log("Service container out of date");
                                if (zzgc().zzlm() >= 12600) {
                                    Boolean zzji2 = zzgg().zzji();
                                    z2 = zzji2 == null || zzji2.booleanValue();
                                    z = false;
                                    break;
                                } else {
                                    z = true;
                                    z2 = false;
                                    break;
                                }
                                break;
                            case 3:
                                zzgf().zziv().log("Service disabled");
                                z = false;
                                z2 = false;
                                break;
                            case 9:
                                zzgf().zziv().log("Service invalid");
                                z = false;
                                z2 = false;
                                break;
                            case 18:
                                zzgf().zziv().log("Service updating");
                                z = true;
                                z2 = true;
                                break;
                            default:
                                zzgf().zziv().zzg("Unexpected service status", Integer.valueOf(isGooglePlayServicesAvailable));
                                z = false;
                                z2 = false;
                                break;
                        }
                    } else {
                        z = true;
                        z2 = true;
                    }
                    if (z) {
                        zzgg().zzf(z2);
                    }
                } else {
                    z2 = true;
                }
                this.zzapi = Boolean.valueOf(z2);
            }
            if (this.zzapi.booleanValue()) {
                this.zzapg.zzkq();
                return;
            }
            zzgi();
            List<ResolveInfo> queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
            if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                z3 = false;
            }
            if (z3) {
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                Context context = getContext();
                zzgi();
                intent.setComponent(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                this.zzapg.zzc(intent);
                return;
            }
            zzgf().zzis().log("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
        }
    }

    public final /* bridge */ /* synthetic */ void zzfr() {
        super.zzfr();
    }

    public final /* bridge */ /* synthetic */ void zzfs() {
        super.zzfs();
    }

    public final /* bridge */ /* synthetic */ void zzft() {
        super.zzft();
    }

    public final /* bridge */ /* synthetic */ zzdu zzfu() {
        return super.zzfu();
    }

    public final /* bridge */ /* synthetic */ zzhl zzfv() {
        return super.zzfv();
    }

    public final /* bridge */ /* synthetic */ zzfc zzfw() {
        return super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzeq zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzij zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzig zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzfd zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzff zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzkc zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzji zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzgh zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfh zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzfs zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzeg zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzec zzgi() {
        return super.zzgi();
    }

    /* access modifiers changed from: protected */
    public final boolean zzhh() {
        return false;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzkj() {
        zzab();
        zzch();
        zzf(new zzin(this, zzk(true)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzkm() {
        zzab();
        zzch();
        zzf(new zziq(this, zzk(true)));
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzko() {
        return this.zzapi;
    }
}
