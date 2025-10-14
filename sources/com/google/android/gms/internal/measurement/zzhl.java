package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzhl extends zzhi {
    @VisibleForTesting
    protected zzie zzanz;
    private AppMeasurement.EventInterceptor zzaoa;
    private final Set<AppMeasurement.OnEventListener> zzaob = new CopyOnWriteArraySet();
    private boolean zzaoc;
    private final AtomicReference<String> zzaod = new AtomicReference<>();
    @VisibleForTesting
    protected boolean zzaoe = true;

    protected zzhl(zzgm zzgm) {
        super(zzgm);
    }

    private final void zza(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzgc().zzce(str) != 0) {
            zzgf().zzis().zzg("Invalid conditional user property name", zzgb().zzbk(str));
        } else if (zzgc().zzi(str, obj) != 0) {
            zzgf().zzis().zze("Invalid conditional user property value", zzgb().zzbk(str), obj);
        } else {
            Object zzj = zzgc().zzj(str, obj);
            if (zzj == null) {
                zzgf().zzis().zze("Unable to normalize conditional user property value", zzgb().zzbk(str), obj);
                return;
            }
            conditionalUserProperty.mValue = zzj;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                long j2 = conditionalUserProperty.mTimeToLive;
                if (j2 > 15552000000L || j2 < 1) {
                    zzgf().zzis().zze("Invalid conditional user property time to live", zzgb().zzbk(str), Long.valueOf(j2));
                } else {
                    zzge().zzc((Runnable) new zzhs(this, conditionalUserProperty));
                }
            } else {
                zzgf().zzis().zze("Invalid conditional user property timeout", zzgb().zzbk(str), Long.valueOf(j));
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        int i;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(bundle);
        zzab();
        zzch();
        if (!this.zzacw.isEnabled()) {
            zzgf().zziy().log("Event not sent since app measurement is disabled");
            return;
        }
        if (!this.zzaoc) {
            this.zzaoc = true;
            try {
                try {
                    Class.forName("com.google.android.gms.tagmanager.TagManagerService").getDeclaredMethod("initialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{getContext()});
                } catch (Exception e) {
                    zzgf().zziv().zzg("Failed to invoke Tag Manager's initialize() method", e);
                }
            } catch (ClassNotFoundException e2) {
                zzgf().zzix().log("Tag Manager is not found and thus will not be used");
            }
        }
        if (z3) {
            zzgi();
            if (!"_iap".equals(str2)) {
                zzkc zzgc = this.zzacw.zzgc();
                int i2 = !zzgc.zzq("event", str2) ? 2 : !zzgc.zza("event", AppMeasurement.Event.zzacx, str2) ? 13 : !zzgc.zza("event", 40, str2) ? 2 : 0;
                if (i2 != 0) {
                    zzgf().zziu().zzg("Invalid public event name. Event will not be logged (FE)", zzgb().zzbi(str2));
                    this.zzacw.zzgc();
                    this.zzacw.zzgc().zza(i2, "_ev", zzkc.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
            }
        }
        zzgi();
        zzif zzkk = zzfz().zzkk();
        if (zzkk != null && !bundle.containsKey("_sc")) {
            zzkk.zzaou = true;
        }
        zzig.zza(zzkk, bundle, z && z3);
        boolean equals = "am".equals(str);
        boolean zzch = zzkc.zzch(str2);
        if (z && this.zzaoa != null && !zzch && !equals) {
            zzgf().zziy().zze("Passing event to registered event handler (FE)", zzgb().zzbi(str2), zzgb().zzb(bundle));
            this.zzaoa.interceptEvent(str, str2, bundle, j);
        } else if (this.zzacw.zzkd()) {
            int zzcc = zzgc().zzcc(str2);
            if (zzcc != 0) {
                zzgf().zziu().zzg("Invalid event name. Event will not be logged (FE)", zzgb().zzbi(str2));
                zzgc();
                this.zzacw.zzgc().zza(str3, zzcc, "_ev", zzkc.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                return;
            }
            List listOf = CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"});
            Bundle zza = zzgc().zza(str2, bundle, (List<String>) listOf, z3, true);
            zzif zzif = (zza == null || !zza.containsKey("_sc") || !zza.containsKey("_si")) ? null : new zzif(zza.getString("_sn"), zza.getString("_sc"), Long.valueOf(zza.getLong("_si")).longValue());
            zzif zzif2 = zzif == null ? zzkk : zzif;
            ArrayList arrayList = new ArrayList();
            arrayList.add(zza);
            long nextLong = zzgc().zzll().nextLong();
            int i3 = 0;
            String[] strArr = (String[]) zza.keySet().toArray(new String[bundle.size()]);
            Arrays.sort(strArr);
            int length = strArr.length;
            int i4 = 0;
            while (i4 < length) {
                String str4 = strArr[i4];
                Object obj = zza.get(str4);
                zzgc();
                Bundle[] zze = zzkc.zze(obj);
                if (zze != null) {
                    zza.putInt(str4, zze.length);
                    int i5 = 0;
                    while (true) {
                        int i6 = i5;
                        if (i6 >= zze.length) {
                            break;
                        }
                        Bundle bundle2 = zze[i6];
                        zzig.zza(zzif2, bundle2, true);
                        Bundle zza2 = zzgc().zza("_ep", bundle2, (List<String>) listOf, z3, false);
                        zza2.putString("_en", str2);
                        zza2.putLong("_eid", nextLong);
                        zza2.putString("_gn", str4);
                        zza2.putInt("_ll", zze.length);
                        zza2.putInt("_i", i6);
                        arrayList.add(zza2);
                        i5 = i6 + 1;
                    }
                    i = zze.length + i3;
                } else {
                    i = i3;
                }
                i4++;
                i3 = i;
            }
            if (i3 != 0) {
                zza.putLong("_eid", nextLong);
                zza.putInt("_epc", i3);
            }
            int i7 = 0;
            while (true) {
                int i8 = i7;
                if (i8 >= arrayList.size()) {
                    break;
                }
                Bundle bundle3 = (Bundle) arrayList.get(i8);
                String str5 = i8 != 0 ? "_ep" : str2;
                bundle3.putString("_o", str);
                Bundle zzd = z2 ? zzgc().zzd(bundle3) : bundle3;
                zzgf().zziy().zze("Logging event (FE)", zzgb().zzbi(str2), zzgb().zzb(zzd));
                zzfy().zzb(new zzew(str5, new zzet(zzd), str, j), str3);
                if (!equals) {
                    for (AppMeasurement.OnEventListener onEvent : this.zzaob) {
                        onEvent.onEvent(str, str2, new Bundle(zzd), j);
                    }
                }
                i7 = i8 + 1;
            }
            zzgi();
            if (zzfz().zzkk() != null && AppMeasurement.Event.APP_EXCEPTION.equals(str2)) {
                zzgd().zzl(true);
            }
        }
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzge().zzc((Runnable) new zzhn(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzb(str, str2, zzbt().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzfs();
        zzch();
        if (!this.zzacw.isEnabled()) {
            zzgf().zziy().log("User property not set since app measurement is disabled");
        } else if (this.zzacw.zzkd()) {
            zzgf().zziy().zze("Setting user property (FE)", zzgb().zzbi(str2), obj);
            zzfy().zzb(new zzjz(str2, j, obj, str));
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzge().zzc((Runnable) new zzht(this, conditionalUserProperty));
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzge().zzjr()) {
            zzgf().zzis().log("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzec.isMainThread()) {
            zzgf().zzis().log("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacw.zzge().zzc((Runnable) new zzhv(this, atomicReference, str, str2, str3, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgf().zziv().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzjz> list = (List) atomicReference.get();
            if (list == null) {
                zzgf().zziv().log("Timed out waiting for get user properties");
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzjz zzjz : list) {
                arrayMap.put(zzjz.name, zzjz.getValue());
            }
            return arrayMap;
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzb(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzab();
        zzch();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        if (!this.zzacw.isEnabled()) {
            zzgf().zziy().log("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzjz zzjz = new zzjz(conditionalUserProperty.mName, conditionalUserProperty.mTriggeredTimestamp, conditionalUserProperty.mValue, conditionalUserProperty.mOrigin);
        try {
            zzew zza = zzgc().zza(conditionalUserProperty.mTriggeredEventName, conditionalUserProperty.mTriggeredEventParams, conditionalUserProperty.mOrigin, 0, true, false);
            zzfy().zzd(new zzee(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzjz, conditionalUserProperty.mCreationTimestamp, false, conditionalUserProperty.mTriggerEventName, zzgc().zza(conditionalUserProperty.mTimedOutEventName, conditionalUserProperty.mTimedOutEventParams, conditionalUserProperty.mOrigin, 0, true, false), conditionalUserProperty.mTriggerTimeout, zza, conditionalUserProperty.mTimeToLive, zzgc().zza(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, 0, true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2;
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = new Bundle(bundle);
            for (String str4 : bundle2.keySet()) {
                Object obj = bundle2.get(str4);
                if (obj instanceof Bundle) {
                    bundle2.putBundle(str4, new Bundle((Bundle) obj));
                } else if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= parcelableArr.length) {
                            break;
                        }
                        if (parcelableArr[i2] instanceof Bundle) {
                            parcelableArr[i2] = new Bundle((Bundle) parcelableArr[i2]);
                        }
                        i = i2 + 1;
                    }
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        if (i4 >= arrayList.size()) {
                            break;
                        }
                        Object obj2 = arrayList.get(i4);
                        if (obj2 instanceof Bundle) {
                            arrayList.set(i4, new Bundle((Bundle) obj2));
                        }
                        i3 = i4 + 1;
                    }
                }
            }
        }
        zzge().zzc((Runnable) new zzid(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzc(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzab();
        zzch();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        if (!this.zzacw.isEnabled()) {
            zzgf().zziy().log("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        zzjz zzjz = new zzjz(conditionalUserProperty.mName, 0, (Object) null, (String) null);
        try {
            zzfy().zzd(new zzee(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzjz, conditionalUserProperty.mCreationTimestamp, conditionalUserProperty.mActive, conditionalUserProperty.mTriggerEventName, (zzew) null, conditionalUserProperty.mTriggerTimeout, (zzew) null, conditionalUserProperty.mTimeToLive, zzgc().zza(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, conditionalUserProperty.mCreationTimestamp, true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    @VisibleForTesting
    private final List<AppMeasurement.ConditionalUserProperty> zzf(String str, String str2, String str3) {
        if (zzge().zzjr()) {
            zzgf().zzis().log("Cannot get conditional user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzec.isMainThread()) {
            zzgf().zzis().log("Cannot get conditional user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacw.zzge().zzc((Runnable) new zzhu(this, atomicReference, str, str2, str3));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgf().zziv().zze("Interrupted waiting for get conditional user properties", str, e);
                }
            }
            List<zzee> list = (List) atomicReference.get();
            if (list == null) {
                zzgf().zziv().zzg("Timed out waiting for get conditional user properties", str);
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (zzee zzee : list) {
                AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
                conditionalUserProperty.mAppId = zzee.packageName;
                conditionalUserProperty.mOrigin = zzee.origin;
                conditionalUserProperty.mCreationTimestamp = zzee.creationTimestamp;
                conditionalUserProperty.mName = zzee.zzaeq.name;
                conditionalUserProperty.mValue = zzee.zzaeq.getValue();
                conditionalUserProperty.mActive = zzee.active;
                conditionalUserProperty.mTriggerEventName = zzee.triggerEventName;
                if (zzee.zzaer != null) {
                    conditionalUserProperty.mTimedOutEventName = zzee.zzaer.name;
                    if (zzee.zzaer.zzafr != null) {
                        conditionalUserProperty.mTimedOutEventParams = zzee.zzaer.zzafr.zzij();
                    }
                }
                conditionalUserProperty.mTriggerTimeout = zzee.triggerTimeout;
                if (zzee.zzaes != null) {
                    conditionalUserProperty.mTriggeredEventName = zzee.zzaes.name;
                    if (zzee.zzaes.zzafr != null) {
                        conditionalUserProperty.mTriggeredEventParams = zzee.zzaes.zzafr.zzij();
                    }
                }
                conditionalUserProperty.mTriggeredTimestamp = zzee.zzaeq.zzarl;
                conditionalUserProperty.mTimeToLive = zzee.timeToLive;
                if (zzee.zzaet != null) {
                    conditionalUserProperty.mExpiredEventName = zzee.zzaet.name;
                    if (zzee.zzaet.zzafr != null) {
                        conditionalUserProperty.mExpiredEventParams = zzee.zzaet.zzafr.zzij();
                    }
                }
                arrayList.add(conditionalUserProperty);
            }
            return arrayList;
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzi(boolean z) {
        zzab();
        zzfs();
        zzch();
        zzgf().zziy().zzg("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzgg().setMeasurementEnabled(z);
        if (!zzgh().zzay(zzfw().zzah())) {
            zzfy().zzkm();
        } else if (!this.zzacw.isEnabled() || !this.zzaoe) {
            zzfy().zzkm();
        } else {
            zzgf().zziy().log("Recording app launch after enabling measurement for the first time (FE)");
            zzkj();
        }
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzfs();
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zzfr();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String zzjh = zzgg().zzjh();
            return zzjh != null ? Tasks.forResult(zzjh) : Tasks.call(zzge().zzjs(), new zzhp(this));
        } catch (Exception e) {
            zzgf().zziv().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzfs();
        return zzf((String) null, str, str2);
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzfr();
        return zzf(str, str2, str3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzfs();
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zzfr();
        return zzb(str, str2, str3, z);
    }

    public final void logEvent(String str, String str2, Bundle bundle) {
        zzfs();
        zza(str, str2, bundle, true, this.zzaoa == null || zzkc.zzch(str2), false, (String) null);
    }

    public final void registerOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzfs();
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzaob.add(onEventListener)) {
            zzgf().zziv().log("OnEventListener already registered");
        }
    }

    public final void resetAnalyticsData() {
        zzge().zzc((Runnable) new zzhr(this, zzbt().currentTimeMillis()));
    }

    public final void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        zzfs();
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = new AppMeasurement.ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzgf().zziv().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mAppId);
        zzfr();
        zza(new AppMeasurement.ConditionalUserProperty(conditionalUserProperty));
    }

    @WorkerThread
    public final void setEventInterceptor(AppMeasurement.EventInterceptor eventInterceptor) {
        zzab();
        zzfs();
        zzch();
        if (!(eventInterceptor == null || eventInterceptor == this.zzaoa)) {
            Preconditions.checkState(this.zzaoa == null, "EventInterceptor already set.");
        }
        this.zzaoa = eventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) {
        zzch();
        zzfs();
        zzge().zzc((Runnable) new zzia(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzfs();
        zzge().zzc((Runnable) new zzib(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzfs();
        zzge().zzc((Runnable) new zzic(this, j));
    }

    public final void setUserProperty(String str, String str2, Object obj) {
        int i = 0;
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = zzbt().currentTimeMillis();
        int zzce = zzgc().zzce(str2);
        if (zzce != 0) {
            zzgc();
            String zza = zzkc.zza(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzacw.zzgc().zza(zzce, "_ev", zza, i);
        } else if (obj != null) {
            int zzi = zzgc().zzi(str2, obj);
            if (zzi != 0) {
                zzgc();
                String zza2 = zzkc.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzacw.zzgc().zza(zzi, "_ev", zza2, i);
                return;
            }
            Object zzj = zzgc().zzj(str2, obj);
            if (zzj != null) {
                zza(str, str2, currentTimeMillis, zzj);
            }
        } else {
            zza(str, str2, currentTimeMillis, (Object) null);
        }
    }

    public final void unregisterOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzfs();
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzaob.remove(onEventListener)) {
            zzgf().zziv().log("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, String str2, Bundle bundle) {
        zzfs();
        zzab();
        zza(str, str2, zzbt().currentTimeMillis(), bundle, true, this.zzaoa == null || zzkc.zzch(str2), false, (String) null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zzfs();
        zzb(str, str2, j, bundle, false, true, true, (String) null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zzfs();
        zza(str, str2, bundle, true, this.zzaoa == null || zzkc.zzch(str2), true, (String) null);
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final String zzae(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzge().zzc((Runnable) new zzhq(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException e) {
                zzgf().zziv().log("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    /* access modifiers changed from: package-private */
    public final void zzbq(@Nullable String str) {
        this.zzaod.set(str);
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
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

    public final String zzhq() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzge().zza(atomicReference, 15000, "String test flag value", new zzhw(this, atomicReference));
    }

    public final List<zzjz> zzj(boolean z) {
        zzfs();
        zzch();
        zzgf().zziy().log("Fetching user attributes (FE)");
        if (zzge().zzjr()) {
            zzgf().zzis().log("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzec.isMainThread()) {
            zzgf().zzis().log("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacw.zzge().zzc((Runnable) new zzho(this, atomicReference, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgf().zziv().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzjz> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzgf().zziv().log("Timed out waiting for get user properties");
            return Collections.emptyList();
        }
    }

    @Nullable
    public final String zzjh() {
        zzfs();
        return this.zzaod.get();
    }

    public final Boolean zzkf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzge().zza(atomicReference, 15000, "boolean test flag value", new zzhm(this, atomicReference));
    }

    public final Long zzkg() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzge().zza(atomicReference, 15000, "long test flag value", new zzhx(this, atomicReference));
    }

    public final Integer zzkh() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzge().zza(atomicReference, 15000, "int test flag value", new zzhy(this, atomicReference));
    }

    public final Double zzki() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzge().zza(atomicReference, 15000, "double test flag value", new zzhz(this, atomicReference));
    }

    @WorkerThread
    public final void zzkj() {
        zzab();
        zzfs();
        zzch();
        if (this.zzacw.zzkd()) {
            zzfy().zzkj();
            this.zzaoe = false;
            String zzjk = zzgg().zzjk();
            if (!TextUtils.isEmpty(zzjk)) {
                zzfx().zzch();
                if (!zzjk.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzjk);
                    logEvent("auto", "_ou", bundle);
                }
            }
        }
    }
}
