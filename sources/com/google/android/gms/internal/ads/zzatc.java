package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzatc {
    private static final String[] zzdbo = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzdbp = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};

    zzatc() {
    }

    private static void zzd(String str, String str2, String str3) {
        String str4;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazy)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString("code", str2);
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    str4 = parse.getHost();
                    bundle.putString("host", str4);
                }
            }
            str4 = "";
            bundle.putString("host", str4);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(@Nullable SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            zzd("ssl_err", (primaryError < 0 || primaryError >= zzdbp.length) ? String.valueOf(primaryError) : zzdbp[primaryError], sslError.getUrl());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(int i, String str) {
        zzd("http_err", (i >= 0 || (-i) + -1 >= zzdbo.length) ? String.valueOf(i) : zzdbo[(-i) - 1], str);
    }
}
