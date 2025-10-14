package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzl;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

final class zzgn {
    static zzdz<zzl> zza(zzdz<zzl> zzdz, int... iArr) {
        zzdz<zzl> zza;
        int length = iArr.length;
        int i = 0;
        zzdz<zzl> zzdz2 = zzdz;
        while (i < length) {
            int i2 = iArr[i];
            if (zzgj.zzh(zzdz2.getObject()) instanceof String) {
                switch (i2) {
                    case 12:
                        zza = zza(zzdz2);
                        break;
                    default:
                        zzdi.zzav(new StringBuilder(39).append("Unsupported Value Escaping: ").append(i2).toString());
                        zza = zzdz2;
                        break;
                }
            } else {
                zzdi.zzav("Escaping can only be applied to strings.");
                zza = zzdz2;
            }
            i++;
            zzdz2 = zza;
        }
        return zzdz2;
    }

    static String zzbs(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, WebRequest.CHARSET_UTF_8).replaceAll("\\+", "%20");
    }

    private static zzdz<zzl> zza(zzdz<zzl> zzdz) {
        try {
            return new zzdz<>(zzgj.zzi(zzbs(zzgj.zzc(zzdz.getObject()))), zzdz.zziu());
        } catch (UnsupportedEncodingException e) {
            zzdi.zza("Escape URI: unsupported encoding", e);
            return zzdz;
        }
    }
}
