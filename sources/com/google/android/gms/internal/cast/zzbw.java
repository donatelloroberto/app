package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.CastStatusCodes;
import org.json.JSONObject;

final class zzbw extends zzbx {
    private final /* synthetic */ zzbs zzww;
    private final /* synthetic */ String zzwx;
    private final /* synthetic */ JSONObject zzwy;
    private final /* synthetic */ int zzwz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbw(zzbs zzbs, int i, String str, JSONObject jSONObject) {
        super(zzbs);
        this.zzww = zzbs;
        this.zzwz = i;
        this.zzwx = str;
        this.zzwy = jSONObject;
    }

    public final void execute() {
        int i;
        switch (this.zzwz) {
            case 2:
                i = 5;
                break;
            case 3:
                i = 1;
                break;
            case 4:
                i = 2;
                break;
            case 5:
                i = 3;
                break;
            case 6:
                i = 4;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            this.zzxb.zza(-1, CastStatusCodes.INVALID_REQUEST, (Object) null);
            zzbs.zzbf.w("sendPlayerRequest for unsupported playerState: %d", Integer.valueOf(this.zzwz));
            return;
        }
        this.zzww.zza(this.zzwx, i, this.zzwy, this.zzxb);
    }
}
