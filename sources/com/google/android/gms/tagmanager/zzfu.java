package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

final class zzfu implements zzbe {
    private final String zzabp;
    private final zzfx zzalb;
    private final zzfw zzalc;
    private final Context zzrm;

    @VisibleForTesting
    private zzfu(zzfx zzfx, Context context, zzfw zzfw) {
        String str = null;
        this.zzalb = zzfx;
        this.zzrm = context.getApplicationContext();
        this.zzalc = zzfw;
        String str2 = Build.VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        if (!(locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
                sb.append("-").append(locale.getCountry().toLowerCase());
            }
            str = sb.toString();
        }
        this.zzabp = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleTagManager", "4.00", str2, str, Build.MODEL, Build.ID});
    }

    @VisibleForTesting
    zzfu(Context context, zzfw zzfw) {
        this(new zzfv(), context, zzfw);
    }

    public final boolean zzhy() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzrm.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdi.zzab("...no network connectivity");
        return false;
    }

    public final void zzd(List<zzbw> list) {
        boolean z;
        HttpURLConnection zzc;
        int min = Math.min(list.size(), 40);
        boolean z2 = true;
        int i = 0;
        while (i < min) {
            zzbw zzbw = list.get(i);
            URL zzd = zzd(zzbw);
            if (zzd == null) {
                zzdi.zzac("No destination: discarding hit.");
                this.zzalc.zzb(zzbw);
                z = z2;
            } else {
                InputStream inputStream = null;
                try {
                    zzc = this.zzalb.zzc(zzd);
                    if (z2) {
                        zzdn.zzn(this.zzrm);
                        z2 = false;
                    }
                    zzc.setRequestProperty("User-Agent", this.zzabp);
                    int responseCode = zzc.getResponseCode();
                    inputStream = zzc.getInputStream();
                    if (responseCode != 200) {
                        zzdi.zzac(new StringBuilder(25).append("Bad response: ").append(responseCode).toString());
                        this.zzalc.zzc(zzbw);
                    } else {
                        this.zzalc.zza(zzbw);
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    zzc.disconnect();
                    z = z2;
                } catch (IOException e) {
                    boolean z3 = z2;
                    String valueOf = String.valueOf(e.getClass().getSimpleName());
                    zzdi.zzac(valueOf.length() != 0 ? "Exception sending hit: ".concat(valueOf) : new String("Exception sending hit: "));
                    zzdi.zzac(e.getMessage());
                    this.zzalc.zzc(zzbw);
                    z = z3;
                } catch (Throwable th) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    zzc.disconnect();
                    throw th;
                }
            }
            i++;
            z2 = z;
        }
    }

    @VisibleForTesting
    private static URL zzd(zzbw zzbw) {
        try {
            return new URL(zzbw.zzij());
        } catch (MalformedURLException e) {
            zzdi.zzav("Error trying to parse the GTM url.");
            return null;
        }
    }
}
