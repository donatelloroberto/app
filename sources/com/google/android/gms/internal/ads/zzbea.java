package com.google.android.gms.internal.ads;

final class zzbea {
    static String zzaq(zzbah zzbah) {
        zzbeb zzbeb = new zzbeb(zzbah);
        StringBuilder sb = new StringBuilder(zzbeb.size());
        for (int i = 0; i < zzbeb.size(); i++) {
            byte zzbn = zzbeb.zzbn(i);
            switch (zzbn) {
                case 7:
                    sb.append("\\a");
                    break;
                case 8:
                    sb.append("\\b");
                    break;
                case 9:
                    sb.append("\\t");
                    break;
                case 10:
                    sb.append("\\n");
                    break;
                case 11:
                    sb.append("\\v");
                    break;
                case 12:
                    sb.append("\\f");
                    break;
                case 13:
                    sb.append("\\r");
                    break;
                case 34:
                    sb.append("\\\"");
                    break;
                case 39:
                    sb.append("\\'");
                    break;
                case 92:
                    sb.append("\\\\");
                    break;
                default:
                    if (zzbn >= 32 && zzbn <= 126) {
                        sb.append((char) zzbn);
                        break;
                    } else {
                        sb.append('\\');
                        sb.append((char) (((zzbn >>> 6) & 3) + 48));
                        sb.append((char) (((zzbn >>> 3) & 7) + 48));
                        sb.append((char) ((zzbn & 7) + 48));
                        break;
                    }
            }
        }
        return sb.toString();
    }
}
