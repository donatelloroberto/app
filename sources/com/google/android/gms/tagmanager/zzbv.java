package com.google.android.gms.tagmanager;

import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzb;
import com.google.android.gms.internal.gtm.zzl;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

final class zzbv extends zzbq {
    private static final String ID = zza.HASH.toString();
    private static final String zzags = zzb.ARG0.toString();
    private static final String zzagu = zzb.INPUT_FORMAT.toString();
    private static final String zzagx = zzb.ALGORITHM.toString();

    public zzbv() {
        super(ID, zzags);
    }

    public final boolean zzgw() {
        return true;
    }

    public final zzl zzb(Map<String, zzl> map) {
        String zzc;
        byte[] decode;
        zzl zzl = map.get(zzags);
        if (zzl == null || zzl == zzgj.zzkc()) {
            return zzgj.zzkc();
        }
        String zzc2 = zzgj.zzc(zzl);
        zzl zzl2 = map.get(zzagx);
        String zzc3 = zzl2 == null ? "MD5" : zzgj.zzc(zzl2);
        zzl zzl3 = map.get(zzagu);
        if (zzl3 == null) {
            zzc = PushConstants.STYLE_TEXT;
        } else {
            zzc = zzgj.zzc(zzl3);
        }
        if (PushConstants.STYLE_TEXT.equals(zzc)) {
            decode = zzc2.getBytes();
        } else if ("base16".equals(zzc)) {
            decode = zzo.decode(zzc2);
        } else {
            String valueOf = String.valueOf(zzc);
            zzdi.zzav(valueOf.length() != 0 ? "Hash: unknown input format: ".concat(valueOf) : new String("Hash: unknown input format: "));
            return zzgj.zzkc();
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(zzc3);
            instance.update(decode);
            return zzgj.zzi(zzo.encode(instance.digest()));
        } catch (NoSuchAlgorithmException e) {
            String valueOf2 = String.valueOf(zzc3);
            zzdi.zzav(valueOf2.length() != 0 ? "Hash: unknown algorithm: ".concat(valueOf2) : new String("Hash: unknown algorithm: "));
            return zzgj.zzkc();
        }
    }
}
