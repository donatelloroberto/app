package com.google.android.gms.internal.gtm;

final class zzcx extends zzam implements zzbp<zzcy> {
    private final zzcy zzacl = new zzcy();

    public zzcx(zzap zzap) {
        super(zzap);
    }

    public final void zzb(String str, String str2) {
        this.zzacl.zzacs.put(str, str2);
    }

    public final void zzc(String str, String str2) {
        if ("ga_trackingId".equals(str)) {
            this.zzacl.zzacm = str2;
        } else if ("ga_sampleFrequency".equals(str)) {
            try {
                this.zzacl.zzacn = Double.parseDouble(str2);
            } catch (NumberFormatException e) {
                zzc("Error parsing ga_sampleFrequency value", str2, e);
            }
        } else {
            zzd("string configuration name not recognized", str);
        }
    }

    public final void zza(String str, boolean z) {
        int i = 1;
        if ("ga_autoActivityTracking".equals(str)) {
            zzcy zzcy = this.zzacl;
            if (!z) {
                i = 0;
            }
            zzcy.zzacp = i;
        } else if ("ga_anonymizeIp".equals(str)) {
            zzcy zzcy2 = this.zzacl;
            if (!z) {
                i = 0;
            }
            zzcy2.zzacq = i;
        } else if ("ga_reportUncaughtExceptions".equals(str)) {
            zzcy zzcy3 = this.zzacl;
            if (!z) {
                i = 0;
            }
            zzcy3.zzacr = i;
        } else {
            zzd("bool configuration name not recognized", str);
        }
    }

    public final void zzb(String str, int i) {
        if ("ga_sessionTimeout".equals(str)) {
            this.zzacl.zzaco = i;
        } else {
            zzd("int configuration name not recognized", str);
        }
    }

    public final /* synthetic */ zzbn zzel() {
        return this.zzacl;
    }
}
