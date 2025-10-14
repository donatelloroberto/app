package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzk;
import com.google.android.gms.internal.gtm.zzor;
import com.google.android.gms.internal.gtm.zzpc;
import com.google.android.gms.internal.gtm.zzpd;
import com.google.android.gms.internal.gtm.zzpe;
import com.google.android.gms.internal.gtm.zzuw;
import com.google.android.gms.tagmanager.zzeh;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

final class zzer implements Runnable {
    private final String zzaec;
    private volatile String zzafd;
    private final zzpd zzajd;
    private final String zzaje;
    private zzdh<zzk> zzajf;
    private volatile zzal zzajg;
    private volatile String zzajh;
    private final Context zzrm;

    public zzer(Context context, String str, zzal zzal) {
        this(context, str, new zzpd(), zzal);
    }

    @VisibleForTesting
    private zzer(Context context, String str, zzpd zzpd, zzal zzal) {
        this.zzrm = context;
        this.zzajd = zzpd;
        this.zzaec = str;
        this.zzajg = zzal;
        String valueOf = String.valueOf("/r?id=");
        String valueOf2 = String.valueOf(str);
        this.zzaje = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        this.zzafd = this.zzaje;
        this.zzajh = null;
    }

    public final void run() {
        boolean z;
        String str;
        if (this.zzajf == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.zzajf.zzhj();
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzrm.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzdi.zzab("...no network connectivity");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            this.zzajf.zzs(zzcz.zzaht);
            return;
        }
        zzdi.zzab("Start loading resource from network ...");
        String zzhq = this.zzajg.zzhq();
        String str2 = this.zzafd;
        String sb = new StringBuilder(String.valueOf(zzhq).length() + 12 + String.valueOf(str2).length()).append(zzhq).append(str2).append("&v=a65833898").toString();
        if (this.zzajh != null && !this.zzajh.trim().equals("")) {
            String valueOf = String.valueOf(sb);
            String str3 = this.zzajh;
            sb = new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(str3).length()).append(valueOf).append("&pv=").append(str3).toString();
        }
        if (zzeh.zziy().zziz().equals(zzeh.zza.CONTAINER_DEBUG)) {
            String valueOf2 = String.valueOf(sb);
            String valueOf3 = String.valueOf("&gtm_debug=x");
            str = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
        } else {
            str = sb;
        }
        zzpc zzmt = zzpd.zzmt();
        InputStream inputStream = null;
        try {
            inputStream = zzmt.zzcj(str);
        } catch (FileNotFoundException e) {
            String str4 = this.zzaec;
            zzdi.zzac(new StringBuilder(String.valueOf(str).length() + 79 + String.valueOf(str4).length()).append("No data is retrieved from the given url: ").append(str).append(". Make sure container_id: ").append(str4).append(" is correct.").toString());
            this.zzajf.zzs(zzcz.zzahv);
            zzmt.close();
            return;
        } catch (zzpe e2) {
            String valueOf4 = String.valueOf(str);
            zzdi.zzac(valueOf4.length() != 0 ? "Error when loading resource for url: ".concat(valueOf4) : new String("Error when loading resource for url: "));
            this.zzajf.zzs(zzcz.zzahw);
        } catch (IOException e3) {
            String message = e3.getMessage();
            zzdi.zzb(new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(message).length()).append("Error when loading resources from url: ").append(str).append(" ").append(message).toString(), e3);
            this.zzajf.zzs(zzcz.zzahu);
            zzmt.close();
            return;
        } catch (Throwable th) {
            zzmt.close();
            throw th;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            zzor.zza(inputStream, byteArrayOutputStream);
            zzk zzk = (zzk) zzuw.zza(new zzk(), byteArrayOutputStream.toByteArray());
            String valueOf5 = String.valueOf(zzk);
            zzdi.zzab(new StringBuilder(String.valueOf(valueOf5).length() + 43).append("Successfully loaded supplemented resource: ").append(valueOf5).toString());
            if (zzk.zzqk == null && zzk.zzqj.length == 0) {
                String valueOf6 = String.valueOf(this.zzaec);
                zzdi.zzab(valueOf6.length() != 0 ? "No change for container: ".concat(valueOf6) : new String("No change for container: "));
            }
            this.zzajf.zze(zzk);
            zzmt.close();
            zzdi.zzab("Load resource from network finished.");
        } catch (IOException e4) {
            String message2 = e4.getMessage();
            zzdi.zzb(new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(message2).length()).append("Error when parsing downloaded resources from url: ").append(str).append(" ").append(message2).toString(), e4);
            this.zzajf.zzs(zzcz.zzahv);
            zzmt.close();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzdh<zzk> zzdh) {
        this.zzajf = zzdh;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzap(String str) {
        if (str == null) {
            this.zzafd = this.zzaje;
            return;
        }
        String valueOf = String.valueOf(str);
        zzdi.zzax(valueOf.length() != 0 ? "Setting CTFE URL path: ".concat(valueOf) : new String("Setting CTFE URL path: "));
        this.zzafd = str;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzbi(String str) {
        String valueOf = String.valueOf(str);
        zzdi.zzax(valueOf.length() != 0 ? "Setting previous container version: ".concat(valueOf) : new String("Setting previous container version: "));
        this.zzajh = str;
    }
}
