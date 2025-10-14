package com.google.android.gms.internal.cast;

public final class zzdv {
    public static Integer zzw(String str) {
        if (str == null) {
            return null;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1118317585:
                if (str.equals("REPEAT_ALL_AND_SHUFFLE")) {
                    c = 3;
                    break;
                }
                break;
            case -962896020:
                if (str.equals("REPEAT_SINGLE")) {
                    c = 2;
                    break;
                }
                break;
            case 1645938909:
                if (str.equals("REPEAT_ALL")) {
                    c = 1;
                    break;
                }
                break;
            case 1645952171:
                if (str.equals("REPEAT_OFF")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return null;
        }
    }

    public static String zza(Integer num) {
        if (num == null) {
            return null;
        }
        switch (num.intValue()) {
            case 0:
                return "REPEAT_OFF";
            case 1:
                return "REPEAT_ALL";
            case 2:
                return "REPEAT_SINGLE";
            case 3:
                return "REPEAT_ALL_AND_SHUFFLE";
            default:
                return null;
        }
    }
}
