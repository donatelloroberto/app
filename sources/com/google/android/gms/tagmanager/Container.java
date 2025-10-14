package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzi;
import com.google.android.gms.internal.gtm.zzj;
import com.google.android.gms.internal.gtm.zzk;
import com.google.android.gms.internal.gtm.zzor;
import com.google.android.gms.internal.gtm.zzov;
import com.google.android.gms.internal.gtm.zzoz;
import com.google.android.gms.tagmanager.zzeh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
public class Container {
    private final String zzaec;
    private final DataLayer zzaed;
    private zzfb zzaee;
    private Map<String, FunctionCallMacroCallback> zzaef = new HashMap();
    private Map<String, FunctionCallTagCallback> zzaeg = new HashMap();
    private volatile long zzaeh;
    private volatile String zzaei = "";
    private final Context zzrm;

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    class zza implements zzan {
        private zza() {
        }

        public final Object zza(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzal = Container.this.zzal(str);
            if (zzal == null) {
                return null;
            }
            return zzal.getValue(str, map);
        }
    }

    class zzb implements zzan {
        private zzb() {
        }

        public final Object zza(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzam = Container.this.zzam(str);
            if (zzam != null) {
                zzam.execute(str, map);
            }
            return zzgj.zzkb();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzov zzov) {
        this.zzrm = context;
        this.zzaed = dataLayer;
        this.zzaec = str;
        this.zzaeh = 0;
        zza(zzov);
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzk zzk) {
        this.zzrm = context;
        this.zzaed = dataLayer;
        this.zzaec = str;
        this.zzaeh = j;
        zzi zzi = zzk.zzqk;
        if (zzi == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzor.zza(zzi));
        } catch (zzoz e) {
            String valueOf = String.valueOf(zzi);
            String zzoz = e.toString();
            zzdi.zzav(new StringBuilder(String.valueOf(valueOf).length() + 46 + String.valueOf(zzoz).length()).append("Not loading resource: ").append(valueOf).append(" because it is invalid: ").append(zzoz).toString());
        }
        if (zzk.zzqj != null) {
            zzj[] zzjArr = zzk.zzqj;
            ArrayList arrayList = new ArrayList();
            for (zzj add : zzjArr) {
                arrayList.add(add);
            }
            zzhb().zze(arrayList);
        }
    }

    public String getContainerId() {
        return this.zzaec;
    }

    public boolean getBoolean(String str) {
        zzfb zzhb = zzhb();
        if (zzhb == null) {
            zzdi.zzav("getBoolean called for closed container.");
            return zzgj.zzjz().booleanValue();
        }
        try {
            return zzgj.zzg(zzhb.zzbj(str).getObject()).booleanValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdi.zzav(new StringBuilder(String.valueOf(message).length() + 66).append("Calling getBoolean() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgj.zzjz().booleanValue();
        }
    }

    public double getDouble(String str) {
        zzfb zzhb = zzhb();
        if (zzhb == null) {
            zzdi.zzav("getDouble called for closed container.");
            return zzgj.zzjy().doubleValue();
        }
        try {
            return zzgj.zzf(zzhb.zzbj(str).getObject()).doubleValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdi.zzav(new StringBuilder(String.valueOf(message).length() + 65).append("Calling getDouble() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgj.zzjy().doubleValue();
        }
    }

    public long getLong(String str) {
        zzfb zzhb = zzhb();
        if (zzhb == null) {
            zzdi.zzav("getLong called for closed container.");
            return zzgj.zzjx().longValue();
        }
        try {
            return zzgj.zze(zzhb.zzbj(str).getObject()).longValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdi.zzav(new StringBuilder(String.valueOf(message).length() + 63).append("Calling getLong() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgj.zzjx().longValue();
        }
    }

    public String getString(String str) {
        zzfb zzhb = zzhb();
        if (zzhb == null) {
            zzdi.zzav("getString called for closed container.");
            return zzgj.zzkb();
        }
        try {
            return zzgj.zzc(zzhb.zzbj(str).getObject());
        } catch (Exception e) {
            String message = e.getMessage();
            zzdi.zzav(new StringBuilder(String.valueOf(message).length() + 65).append("Calling getString() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgj.zzkb();
        }
    }

    public long getLastRefreshTime() {
        return this.zzaeh;
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.zzaef) {
            this.zzaef.put(str, functionCallMacroCallback);
        }
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.zzaef) {
            this.zzaef.remove(str);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final FunctionCallMacroCallback zzal(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzaef) {
            functionCallMacroCallback = this.zzaef.get(str);
        }
        return functionCallMacroCallback;
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.zzaeg) {
            this.zzaeg.put(str, functionCallTagCallback);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.zzaeg) {
            this.zzaeg.remove(str);
        }
    }

    @VisibleForTesting
    public final FunctionCallTagCallback zzam(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzaeg) {
            functionCallTagCallback = this.zzaeg.get(str);
        }
        return functionCallTagCallback;
    }

    @VisibleForTesting
    public final void zzan(String str) {
        zzhb().zzan(str);
    }

    @VisibleForTesting
    public final String zzha() {
        return this.zzaei;
    }

    private final void zza(zzov zzov) {
        this.zzaei = zzov.getVersion();
        String str = this.zzaei;
        zzeh.zziy().zziz().equals(zzeh.zza.CONTAINER_DEBUG);
        zzov zzov2 = zzov;
        zza(new zzfb(this.zzrm, zzov2, this.zzaed, new zza(), new zzb(), new zzdq()));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzaed.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzaec));
        }
    }

    private final synchronized void zza(zzfb zzfb) {
        this.zzaee = zzfb;
    }

    private final synchronized zzfb zzhb() {
        return this.zzaee;
    }

    /* access modifiers changed from: package-private */
    public final void release() {
        this.zzaee = null;
    }
}
