package com.google.android.gms.internal.cast;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.cast.zzbv;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final class zzcn extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API = new Api<>("CastApi.API", zzad, CLIENT_KEY);
    private static final Api.ClientKey<zzcr> CLIENT_KEY = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzcr, Api.ApiOptions.NoOptions> zzad = new zzcq();

    public zzcn(@NonNull Context context) {
        super(context, API, null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<Void> zza(@NonNull String[] strArr, String str, List<zzbv> list) {
        return doWrite(new zzcp(this, strArr, str, (List) null));
    }
}
