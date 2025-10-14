package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzb;
import com.google.android.gms.internal.gtm.zzl;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzek extends zzbq {
    private static final String ID = zza.REGEX_GROUP.toString();
    private static final String zzaiz = zzb.ARG0.toString();
    private static final String zzaja = zzb.ARG1.toString();
    private static final String zzajb = zzb.IGNORE_CASE.toString();
    private static final String zzajc = zzb.GROUP.toString();

    public zzek() {
        super(ID, zzaiz, zzaja);
    }

    public final boolean zzgw() {
        return true;
    }

    public final zzl zzb(Map<String, zzl> map) {
        int i;
        zzl zzl = map.get(zzaiz);
        zzl zzl2 = map.get(zzaja);
        if (zzl == null || zzl == zzgj.zzkc() || zzl2 == null || zzl2 == zzgj.zzkc()) {
            return zzgj.zzkc();
        }
        int i2 = 64;
        if (zzgj.zzg(map.get(zzajb)).booleanValue()) {
            i2 = 66;
        }
        zzl zzl3 = map.get(zzajc);
        if (zzl3 != null) {
            Long zze = zzgj.zze(zzl3);
            if (zze == zzgj.zzjx()) {
                return zzgj.zzkc();
            }
            i = zze.intValue();
            if (i < 0) {
                return zzgj.zzkc();
            }
        } else {
            i = 1;
        }
        try {
            String zzc = zzgj.zzc(zzl);
            String str = null;
            Matcher matcher = Pattern.compile(zzgj.zzc(zzl2), i2).matcher(zzc);
            if (matcher.find() && matcher.groupCount() >= i) {
                str = matcher.group(i);
            }
            return str == null ? zzgj.zzkc() : zzgj.zzi(str);
        } catch (PatternSyntaxException e) {
            return zzgj.zzkc();
        }
    }
}
