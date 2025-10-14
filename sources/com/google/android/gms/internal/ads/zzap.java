package com.google.android.gms.internal.ads;

import com.adobe.phonegap.push.PushConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class zzap {
    public static zzc zzb(zzp zzp) {
        boolean z;
        long j;
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = zzp.zzab;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        boolean z2 = false;
        String str = map.get("Date");
        if (str != null) {
            j3 = zzf(str);
        }
        String str2 = map.get("Cache-Control");
        if (str2 != null) {
            String[] split = str2.split(",");
            int i = 0;
            z = false;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals(PushConstants.NO_CACHE) || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j4 = Long.parseLong(trim.substring(8));
                    } catch (Exception e) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    try {
                        j5 = Long.parseLong(trim.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    z = true;
                }
                i++;
                j5 = j5;
            }
            z2 = true;
        } else {
            z = false;
        }
        String str3 = map.get("Expires");
        long zzf = str3 != null ? zzf(str3) : 0;
        String str4 = map.get("Last-Modified");
        long zzf2 = str4 != null ? zzf(str4) : 0;
        String str5 = map.get("ETag");
        if (z2) {
            j2 = currentTimeMillis + (1000 * j4);
            j = z ? j2 : (1000 * j5) + j2;
        } else if (j3 <= 0 || zzf < j3) {
            j = 0;
            j2 = 0;
        } else {
            long j6 = currentTimeMillis + (zzf - j3);
            j = j6;
            j2 = j6;
        }
        zzc zzc = new zzc();
        zzc.data = zzp.data;
        zzc.zza = str5;
        zzc.zze = j2;
        zzc.zzd = j;
        zzc.zzb = j3;
        zzc.zzc = zzf2;
        zzc.zzf = map;
        zzc.zzg = zzp.allHeaders;
        return zzc;
    }

    static String zzb(long j) {
        return zzp().format(new Date(j));
    }

    private static long zzf(String str) {
        try {
            return zzp().parse(str).getTime();
        } catch (ParseException e) {
            zzaf.zza(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    private static SimpleDateFormat zzp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
}
