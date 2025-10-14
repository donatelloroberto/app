package com.google.android.gms.tagmanager;

import android.net.Uri;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@ShowFirstParty
@VisibleForTesting
class zzeh {
    private static zzeh zzaip;
    private volatile String zzaec = null;
    private volatile zza zzaiq = zza.NONE;
    private volatile String zzair = null;
    private volatile String zzais = null;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzeh() {
    }

    @ShowFirstParty
    static zzeh zziy() {
        zzeh zzeh;
        synchronized (zzeh.class) {
            if (zzaip == null) {
                zzaip = new zzeh();
            }
            zzeh = zzaip;
        }
        return zzeh;
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zza(Uri uri) {
        String str;
        boolean z = true;
        synchronized (this) {
            try {
                String decode = URLDecoder.decode(uri.toString(), WebRequest.CHARSET_UTF_8);
                if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                    String valueOf = String.valueOf(decode);
                    if (valueOf.length() != 0) {
                        str = "Container preview url: ".concat(valueOf);
                    } else {
                        str = new String("Container preview url: ");
                    }
                    zzdi.zzab(str);
                    if (decode.matches(".*?&gtm_debug=x$")) {
                        this.zzaiq = zza.CONTAINER_DEBUG;
                    } else {
                        this.zzaiq = zza.CONTAINER;
                    }
                    this.zzais = uri.getQuery().replace("&gtm_debug=x", "");
                    if (this.zzaiq == zza.CONTAINER || this.zzaiq == zza.CONTAINER_DEBUG) {
                        String valueOf2 = String.valueOf("/r?");
                        String valueOf3 = String.valueOf(this.zzais);
                        this.zzair = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
                    }
                    this.zzaec = zzbh(this.zzais);
                } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    String valueOf4 = String.valueOf(decode);
                    zzdi.zzac(valueOf4.length() != 0 ? "Invalid preview uri: ".concat(valueOf4) : new String("Invalid preview uri: "));
                    z = false;
                } else if (zzbh(uri.getQuery()).equals(this.zzaec)) {
                    String valueOf5 = String.valueOf(this.zzaec);
                    zzdi.zzab(valueOf5.length() != 0 ? "Exit preview mode for container: ".concat(valueOf5) : new String("Exit preview mode for container: "));
                    this.zzaiq = zza.NONE;
                    this.zzair = null;
                } else {
                    z = false;
                }
            } catch (UnsupportedEncodingException e) {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final zza zziz() {
        return this.zzaiq;
    }

    /* access modifiers changed from: package-private */
    public final String zzja() {
        return this.zzair;
    }

    /* access modifiers changed from: package-private */
    public final String getContainerId() {
        return this.zzaec;
    }

    private static String zzbh(String str) {
        return str.split("&")[0].split("=")[1];
    }
}
