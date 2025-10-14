package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzb;
import com.google.android.gms.internal.gtm.zzl;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class zzcx extends zzbq {
    private static final String ID = zza.JOINER.toString();
    private static final String zzags = zzb.ARG0.toString();
    private static final String zzahl = zzb.ITEM_SEPARATOR.toString();
    private static final String zzahm = zzb.KEY_VALUE_SEPARATOR.toString();
    private static final String zzahn = zzb.ESCAPE.toString();

    public zzcx() {
        super(ID, zzags);
    }

    public final boolean zzgw() {
        return true;
    }

    public final zzl zzb(Map<String, zzl> map) {
        String str;
        String str2;
        HashSet hashSet;
        zzl zzl = map.get(zzags);
        if (zzl == null) {
            return zzgj.zzkc();
        }
        zzl zzl2 = map.get(zzahl);
        if (zzl2 != null) {
            str = zzgj.zzc(zzl2);
        } else {
            str = "";
        }
        zzl zzl3 = map.get(zzahm);
        if (zzl3 != null) {
            str2 = zzgj.zzc(zzl3);
        } else {
            str2 = "=";
        }
        int i = zzcz.zzahp;
        zzl zzl4 = map.get(zzahn);
        if (zzl4 != null) {
            String zzc = zzgj.zzc(zzl4);
            if (ImagesContract.URL.equals(zzc)) {
                i = zzcz.zzahq;
                hashSet = null;
            } else if ("backslash".equals(zzc)) {
                int i2 = zzcz.zzahr;
                hashSet = new HashSet();
                zza(hashSet, str);
                zza(hashSet, str2);
                hashSet.remove('\\');
                i = i2;
            } else {
                String valueOf = String.valueOf(zzc);
                zzdi.zzav(valueOf.length() != 0 ? "Joiner: unsupported escape type: ".concat(valueOf) : new String("Joiner: unsupported escape type: "));
                return zzgj.zzkc();
            }
        } else {
            hashSet = null;
        }
        StringBuilder sb = new StringBuilder();
        switch (zzl.type) {
            case 2:
                boolean z = true;
                zzl[] zzlArr = zzl.zzqn;
                int length = zzlArr.length;
                int i3 = 0;
                while (i3 < length) {
                    zzl zzl5 = zzlArr[i3];
                    if (!z) {
                        sb.append(str);
                    }
                    zza(sb, zzgj.zzc(zzl5), i, hashSet);
                    i3++;
                    z = false;
                }
                break;
            case 3:
                for (int i4 = 0; i4 < zzl.zzqo.length; i4++) {
                    if (i4 > 0) {
                        sb.append(str);
                    }
                    String zzc2 = zzgj.zzc(zzl.zzqo[i4]);
                    String zzc3 = zzgj.zzc(zzl.zzqp[i4]);
                    zza(sb, zzc2, i, hashSet);
                    sb.append(str2);
                    zza(sb, zzc3, i, hashSet);
                }
                break;
            default:
                zza(sb, zzgj.zzc(zzl), i, hashSet);
                break;
        }
        return zzgj.zzi(sb.toString());
    }

    private static void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [int, java.lang.Integer] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(java.lang.StringBuilder r1, java.lang.String r2, java.lang.Integer r3, java.util.Set<java.lang.Character> r4) {
        /*
            java.lang.String r0 = zza(r2, r3, r4)
            r1.append(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcx.zza(java.lang.StringBuilder, java.lang.String, int, java.util.Set):void");
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [int, java.lang.Integer] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zza(java.lang.String r6, java.lang.Integer r7, java.util.Set<java.lang.Character> r8) {
        /*
            int[] r0 = com.google.android.gms.tagmanager.zzcy.zzaho
            int r1 = r7 + -1
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x000a;
                case 2: goto L_0x0016;
                default: goto L_0x0009;
            }
        L_0x0009:
            return r6
        L_0x000a:
            java.lang.String r6 = com.google.android.gms.tagmanager.zzgn.zzbs(r6)     // Catch:{ UnsupportedEncodingException -> 0x000f }
            goto L_0x0009
        L_0x000f:
            r0 = move-exception
            java.lang.String r1 = "Joiner: unsupported encoding"
            com.google.android.gms.tagmanager.zzdi.zza(r1, r0)
            goto L_0x0009
        L_0x0016:
            java.lang.String r0 = "\\"
            java.lang.String r1 = "\\\\"
            java.lang.String r0 = r6.replace(r0, r1)
            java.util.Iterator r2 = r8.iterator()
            r1 = r0
        L_0x0023:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x004f
            java.lang.Object r0 = r2.next()
            java.lang.Character r0 = (java.lang.Character) r0
            java.lang.String r3 = r0.toString()
            java.lang.String r4 = "\\"
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r5 = r0.length()
            if (r5 == 0) goto L_0x0049
            java.lang.String r0 = r4.concat(r0)
        L_0x0043:
            java.lang.String r0 = r1.replace(r3, r0)
            r1 = r0
            goto L_0x0023
        L_0x0049:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            goto L_0x0043
        L_0x004f:
            r6 = r1
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcx.zza(java.lang.String, int, java.util.Set):java.lang.String");
    }
}
