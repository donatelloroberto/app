package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzwy extends zzwu<Long> {
    zzwy(zzxe zzxe, String str, Long l) {
        super(zzxe, str, l, (zzwy) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzey */
    public final Long zzex(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException e) {
            String str2 = this.zzbns;
            Log.e("PhenotypeFlag", new StringBuilder(String.valueOf(str2).length() + 25 + String.valueOf(str).length()).append("Invalid long value for ").append(str2).append(": ").append(str).toString());
            return null;
        }
    }
}
