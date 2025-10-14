package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;

final class zzbt implements Cast.MessageReceivedCallback {
    private final /* synthetic */ zzbu zzwv;

    zzbt(zzbu zzbu) {
        this.zzwv = zzbu;
    }

    public final void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzwv.zzww.zzp(str2);
    }
}
