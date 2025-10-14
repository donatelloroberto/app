package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class zzay {
    final String zzagg;
    final byte[] zzagh;

    zzay(String str, byte[] bArr) {
        this.zzagg = str;
        this.zzagh = bArr;
    }

    public final String toString() {
        String str = this.zzagg;
        return new StringBuilder(String.valueOf(str).length() + 54).append("KeyAndSerialized: key = ").append(str).append(" serialized hash = ").append(Arrays.hashCode(this.zzagh)).toString();
    }
}
