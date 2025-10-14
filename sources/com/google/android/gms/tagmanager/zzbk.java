package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzb;
import com.google.android.gms.internal.gtm.zzl;
import java.util.Map;

final class zzbk extends zzbq {
    private static final String ID = zza.ENCODE.toString();
    private static final String zzags = zzb.ARG0.toString();
    private static final String zzagt = zzb.NO_PADDING.toString();
    private static final String zzagu = zzb.INPUT_FORMAT.toString();
    private static final String zzagv = zzb.OUTPUT_FORMAT.toString();

    public zzbk() {
        super(ID, zzags);
    }

    public final boolean zzgw() {
        return true;
    }

    public final zzl zzb(Map<String, zzl> map) {
        String zzc;
        int i;
        byte[] decode;
        String encodeToString;
        zzl zzl = map.get(zzags);
        if (zzl == null || zzl == zzgj.zzkc()) {
            return zzgj.zzkc();
        }
        String zzc2 = zzgj.zzc(zzl);
        zzl zzl2 = map.get(zzagu);
        String zzc3 = zzl2 == null ? PushConstants.STYLE_TEXT : zzgj.zzc(zzl2);
        zzl zzl3 = map.get(zzagv);
        if (zzl3 == null) {
            zzc = "base16";
        } else {
            zzc = zzgj.zzc(zzl3);
        }
        zzl zzl4 = map.get(zzagt);
        if (zzl4 == null || !zzgj.zzg(zzl4).booleanValue()) {
            i = 2;
        } else {
            i = 3;
        }
        try {
            if (PushConstants.STYLE_TEXT.equals(zzc3)) {
                decode = zzc2.getBytes();
            } else if ("base16".equals(zzc3)) {
                decode = zzo.decode(zzc2);
            } else if ("base64".equals(zzc3)) {
                decode = Base64.decode(zzc2, i);
            } else if ("base64url".equals(zzc3)) {
                decode = Base64.decode(zzc2, i | 8);
            } else {
                String valueOf = String.valueOf(zzc3);
                zzdi.zzav(valueOf.length() != 0 ? "Encode: unknown input format: ".concat(valueOf) : new String("Encode: unknown input format: "));
                return zzgj.zzkc();
            }
            if ("base16".equals(zzc)) {
                encodeToString = zzo.encode(decode);
            } else if ("base64".equals(zzc)) {
                encodeToString = Base64.encodeToString(decode, i);
            } else if ("base64url".equals(zzc)) {
                encodeToString = Base64.encodeToString(decode, i | 8);
            } else {
                String valueOf2 = String.valueOf(zzc);
                zzdi.zzav(valueOf2.length() != 0 ? "Encode: unknown output format: ".concat(valueOf2) : new String("Encode: unknown output format: "));
                return zzgj.zzkc();
            }
            return zzgj.zzi(encodeToString);
        } catch (IllegalArgumentException e) {
            zzdi.zzav("Encode: invalid input:");
            return zzgj.zzkc();
        }
    }
}
