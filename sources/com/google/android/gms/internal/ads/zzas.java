package com.google.android.gms.internal.ads;

import com.android.volley.toolbox.HttpClientStack;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

public final class zzas extends zzai {
    private final zzat zzci;
    private final SSLSocketFactory zzcj;

    public zzas() {
        this((zzat) null);
    }

    private zzas(zzat zzat) {
        this((zzat) null, (SSLSocketFactory) null);
    }

    private zzas(zzat zzat, SSLSocketFactory sSLSocketFactory) {
        this.zzci = null;
        this.zzcj = null;
    }

    private static InputStream zza(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException e) {
            return httpURLConnection.getErrorStream();
        }
    }

    private static List<zzl> zza(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            if (next.getKey() != null) {
                for (String zzl : (List) next.getValue()) {
                    arrayList.add(new zzl((String) next.getKey(), zzl));
                }
            }
        }
        return arrayList;
    }

    private static void zza(HttpURLConnection httpURLConnection, zzr<?> zzr) throws IOException, zza {
        byte[] zzg = zzr.zzg();
        if (zzg != null) {
            httpURLConnection.setDoOutput(true);
            String valueOf = String.valueOf(WebRequest.CHARSET_UTF_8);
            httpURLConnection.addRequestProperty("Content-Type", valueOf.length() != 0 ? "application/x-www-form-urlencoded; charset=".concat(valueOf) : new String("application/x-www-form-urlencoded; charset="));
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzg);
            dataOutputStream.close();
        }
    }

    public final zzaq zza(zzr<?> zzr, Map<String, String> map) throws IOException, zza {
        String str;
        String url = zzr.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(zzr.getHeaders());
        hashMap.putAll(map);
        if (this.zzci != null) {
            str = this.zzci.zzg(url);
            if (str == null) {
                String valueOf = String.valueOf(url);
                throw new IOException(valueOf.length() != 0 ? "URL blocked by rewriter: ".concat(valueOf) : new String("URL blocked by rewriter: "));
            }
        } else {
            str = url;
        }
        URL url2 = new URL(str);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int zzi = zzr.zzi();
        httpURLConnection.setConnectTimeout(zzi);
        httpURLConnection.setReadTimeout(zzi);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        "https".equals(url2.getProtocol());
        for (String str2 : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(str2, (String) hashMap.get(str2));
        }
        switch (zzr.getMethod()) {
            case -1:
                break;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                break;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                zza(httpURLConnection, zzr);
                break;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                zza(httpURLConnection, zzr);
                break;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                break;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                break;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                break;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                break;
            case 7:
                httpURLConnection.setRequestMethod(HttpClientStack.HttpPatch.METHOD_NAME);
                zza(httpURLConnection, zzr);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        return !(zzr.getMethod() != 4 && ((100 > responseCode || responseCode >= 200) && responseCode != 204 && responseCode != 304)) ? new zzaq(responseCode, zza((Map<String, List<String>>) httpURLConnection.getHeaderFields())) : new zzaq(responseCode, zza((Map<String, List<String>>) httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), zza(httpURLConnection));
    }
}
