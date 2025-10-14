package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzaka extends zzajx {
    private Context mContext;

    zzaka(Context context) {
        this.mContext = context;
    }

    public final void onStop() {
    }

    public final void zzdn() {
        boolean z;
        try {
            z = AdvertisingIdClient.getIsAdIdFakeForDebugLogging(this.mContext);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            zzakb.zzb("Fail to get isAdIdFakeForDebugLogging", e);
            z = false;
        }
        zzamy.zzaf(z);
        zzakb.zzdk(new StringBuilder(43).append("Update ad debug logging enablement as ").append(z).toString());
    }
}
