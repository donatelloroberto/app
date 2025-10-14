package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Api;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public final class zzdl {
    public static boolean zzaaa = true;
    public static final Api.ClientKey<zzct> zzzt = new Api.ClientKey<>();
    public static final Api.ClientKey<zzeh> zzzu = new Api.ClientKey<>();
    public static final Api.ClientKey<zzei> zzzv = new Api.ClientKey<>();
    private static final Api.ClientKey<Object> zzzw = new Api.ClientKey<>();
    private static final Api.ClientKey<Object> zzzx = new Api.ClientKey<>();
    private static final Charset zzzy;
    private static final String zzzz = zzdc.zzr("com.google.cast.multizone");

    static {
        Charset charset = null;
        try {
            charset = Charset.forName(WebRequest.CHARSET_UTF_8);
        } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
        }
        zzzy = charset;
    }
}
