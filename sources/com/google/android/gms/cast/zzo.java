package com.google.android.gms.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.cast.zzeh;

final class zzo extends Api.AbstractClientBuilder<zzeh, CastRemoteDisplay.CastRemoteDisplayOptions> {
    zzo() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        CastRemoteDisplay.CastRemoteDisplayOptions castRemoteDisplayOptions = (CastRemoteDisplay.CastRemoteDisplayOptions) obj;
        Bundle bundle = new Bundle();
        bundle.putInt("configuration", castRemoteDisplayOptions.zzbd);
        return new zzeh(context, looper, clientSettings, castRemoteDisplayOptions.zzaj, bundle, castRemoteDisplayOptions.zzbe, connectionCallbacks, onConnectionFailedListener);
    }
}
