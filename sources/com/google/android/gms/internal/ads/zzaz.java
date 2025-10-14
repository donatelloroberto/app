package com.google.android.gms.internal.ads;

public final class zzaz {
    public static int zzd(int i) {
        if ((i >= 0 && i <= 2) || (i >= 1000 && i <= 1000)) {
            return i;
        }
        throw new IllegalArgumentException(new StringBuilder(43).append(i).append(" is not a valid enum EnumBoolean").toString());
    }

    public static int zze(int i) {
        if (i >= 0 && i <= 2) {
            return i;
        }
        throw new IllegalArgumentException(new StringBuilder(41).append(i).append(" is not a valid enum ProtoName").toString());
    }

    public static int zzf(int i) {
        if (i >= 0 && i <= 3) {
            return i;
        }
        throw new IllegalArgumentException(new StringBuilder(48).append(i).append(" is not a valid enum EncryptionMethod").toString());
    }
}
