package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzl;
import java.util.Map;

final class zzem extends zzbq {
    private static final String ID = zza.RESOLUTION.toString();
    private final Context zzrm;

    public zzem(Context context) {
        super(ID, new String[0]);
        this.zzrm = context;
    }

    public final boolean zzgw() {
        return true;
    }

    public final zzl zzb(Map<String, zzl> map) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.zzrm.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        return zzgj.zzi(new StringBuilder(23).append(i).append("x").append(displayMetrics.heightPixels).toString());
    }
}
