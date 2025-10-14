package com.google.android.gms.internal.measurement;

final class zzabi {
    static String zza(zzyy zzyy) {
        zzabj zzabj = new zzabj(zzyy);
        StringBuilder sb = new StringBuilder(zzabj.size());
        for (int i = 0; i < zzabj.size(); i++) {
            byte zzae = zzabj.zzae(i);
            switch (zzae) {
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
                    if (zzae >= 32 && zzae <= 126) {
                        sb.append((char) zzae);
                        break;
                    } else {
                        sb.append('\\');
                        sb.append((char) (((zzae >>> 6) & 3) + 48));
                        sb.append((char) (((zzae >>> 3) & 7) + 48));
                        sb.append((char) ((zzae & 7) + 48));
                        break;
                    }
            }
        }
        return sb.toString();
    }
}
