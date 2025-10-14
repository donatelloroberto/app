package com.google.android.gms.internal.cast;

import org.json.JSONObject;

final class zzbv extends zzbx {
    private final /* synthetic */ zzbs zzww;
    private final /* synthetic */ String zzwx;
    private final /* synthetic */ JSONObject zzwy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbv(zzbs zzbs, String str, JSONObject jSONObject) {
        super(zzbs);
        this.zzww = zzbs;
        this.zzwx = str;
        this.zzwy = jSONObject;
    }

    public final void execute() {
        this.zzww.zza(this.zzwx, 6, this.zzwy, this.zzxb);
    }
}
