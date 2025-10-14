package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzeb extends zzjr {
    zzeb(zzjs zzjs) {
        super(zzjs);
    }

    private final Boolean zza(double d, zzki zzki) {
        try {
            return zza(new BigDecimal(d), zzki, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(long j, zzki zzki) {
        try {
            return zza(new BigDecimal(j), zzki, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    zzgf().zziv().zzg("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private final Boolean zza(String str, zzki zzki) {
        if (!zzkc.zzcj(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzki, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzkk zzkk) {
        List arrayList;
        String str2 = null;
        Preconditions.checkNotNull(zzkk);
        if (str == null || zzkk.zzast == null || zzkk.zzast.intValue() == 0) {
            return null;
        }
        if (zzkk.zzast.intValue() == 6) {
            if (zzkk.zzasw == null || zzkk.zzasw.length == 0) {
                return null;
            }
        } else if (zzkk.zzasu == null) {
            return null;
        }
        int intValue = zzkk.zzast.intValue();
        boolean z = zzkk.zzasv != null && zzkk.zzasv.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzkk.zzasu : zzkk.zzasu.toUpperCase(Locale.ENGLISH);
        if (zzkk.zzasw == null) {
            arrayList = null;
        } else {
            String[] strArr = zzkk.zzasw;
            if (z) {
                arrayList = Arrays.asList(strArr);
            } else {
                arrayList = new ArrayList();
                for (String upperCase2 : strArr) {
                    arrayList.add(upperCase2.toUpperCase(Locale.ENGLISH));
                }
            }
        }
        if (intValue == 1) {
            str2 = upperCase;
        }
        return zza(str, intValue, z, upperCase, arrayList, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007d, code lost:
        if (r5 != null) goto L_0x007f;
     */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r10, com.google.android.gms.internal.measurement.zzki r11, double r12) {
        /*
            r8 = 4
            r7 = -1
            r1 = 0
            r0 = 1
            r2 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)
            java.lang.Integer r3 = r11.zzasl
            if (r3 == 0) goto L_0x0014
            java.lang.Integer r3 = r11.zzasl
            int r3 = r3.intValue()
            if (r3 != 0) goto L_0x0016
        L_0x0014:
            r0 = r2
        L_0x0015:
            return r0
        L_0x0016:
            java.lang.Integer r3 = r11.zzasl
            int r3 = r3.intValue()
            if (r3 != r8) goto L_0x0028
            java.lang.String r3 = r11.zzaso
            if (r3 == 0) goto L_0x0026
            java.lang.String r3 = r11.zzasp
            if (r3 != 0) goto L_0x002e
        L_0x0026:
            r0 = r2
            goto L_0x0015
        L_0x0028:
            java.lang.String r3 = r11.zzasn
            if (r3 != 0) goto L_0x002e
            r0 = r2
            goto L_0x0015
        L_0x002e:
            java.lang.Integer r3 = r11.zzasl
            int r6 = r3.intValue()
            java.lang.Integer r3 = r11.zzasl
            int r3 = r3.intValue()
            if (r3 != r8) goto L_0x0066
            java.lang.String r3 = r11.zzaso
            boolean r3 = com.google.android.gms.internal.measurement.zzkc.zzcj(r3)
            if (r3 == 0) goto L_0x004c
            java.lang.String r3 = r11.zzasp
            boolean r3 = com.google.android.gms.internal.measurement.zzkc.zzcj(r3)
            if (r3 != 0) goto L_0x004e
        L_0x004c:
            r0 = r2
            goto L_0x0015
        L_0x004e:
            java.math.BigDecimal r4 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0063 }
            java.lang.String r3 = r11.zzaso     // Catch:{ NumberFormatException -> 0x0063 }
            r4.<init>(r3)     // Catch:{ NumberFormatException -> 0x0063 }
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0063 }
            java.lang.String r5 = r11.zzasp     // Catch:{ NumberFormatException -> 0x0063 }
            r3.<init>(r5)     // Catch:{ NumberFormatException -> 0x0063 }
            r5 = r2
        L_0x005d:
            if (r6 != r8) goto L_0x007d
            if (r4 != 0) goto L_0x007f
            r0 = r2
            goto L_0x0015
        L_0x0063:
            r0 = move-exception
            r0 = r2
            goto L_0x0015
        L_0x0066:
            java.lang.String r3 = r11.zzasn
            boolean r3 = com.google.android.gms.internal.measurement.zzkc.zzcj(r3)
            if (r3 != 0) goto L_0x0070
            r0 = r2
            goto L_0x0015
        L_0x0070:
            java.math.BigDecimal r5 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x007a }
            java.lang.String r3 = r11.zzasn     // Catch:{ NumberFormatException -> 0x007a }
            r5.<init>(r3)     // Catch:{ NumberFormatException -> 0x007a }
            r3 = r2
            r4 = r2
            goto L_0x005d
        L_0x007a:
            r0 = move-exception
            r0 = r2
            goto L_0x0015
        L_0x007d:
            if (r5 == 0) goto L_0x0082
        L_0x007f:
            switch(r6) {
                case 1: goto L_0x0084;
                case 2: goto L_0x0091;
                case 3: goto L_0x009f;
                case 4: goto L_0x00ed;
                default: goto L_0x0082;
            }
        L_0x0082:
            r0 = r2
            goto L_0x0015
        L_0x0084:
            int r2 = r10.compareTo(r5)
            if (r2 != r7) goto L_0x008f
        L_0x008a:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x008f:
            r0 = r1
            goto L_0x008a
        L_0x0091:
            int r2 = r10.compareTo(r5)
            if (r2 != r0) goto L_0x009d
        L_0x0097:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x009d:
            r0 = r1
            goto L_0x0097
        L_0x009f:
            r2 = 0
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x00df
            java.math.BigDecimal r2 = new java.math.BigDecimal
            r2.<init>(r12)
            java.math.BigDecimal r3 = new java.math.BigDecimal
            r4 = 2
            r3.<init>(r4)
            java.math.BigDecimal r2 = r2.multiply(r3)
            java.math.BigDecimal r2 = r5.subtract(r2)
            int r2 = r10.compareTo(r2)
            if (r2 != r0) goto L_0x00dd
            java.math.BigDecimal r2 = new java.math.BigDecimal
            r2.<init>(r12)
            java.math.BigDecimal r3 = new java.math.BigDecimal
            r4 = 2
            r3.<init>(r4)
            java.math.BigDecimal r2 = r2.multiply(r3)
            java.math.BigDecimal r2 = r5.add(r2)
            int r2 = r10.compareTo(r2)
            if (r2 != r7) goto L_0x00dd
        L_0x00d7:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x00dd:
            r0 = r1
            goto L_0x00d7
        L_0x00df:
            int r2 = r10.compareTo(r5)
            if (r2 != 0) goto L_0x00eb
        L_0x00e5:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x00eb:
            r0 = r1
            goto L_0x00e5
        L_0x00ed:
            int r2 = r10.compareTo(r4)
            if (r2 == r7) goto L_0x00ff
            int r2 = r10.compareTo(r3)
            if (r2 == r0) goto L_0x00ff
        L_0x00f9:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x00ff:
            r0 = r1
            goto L_0x00f9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeb.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzki, double):java.lang.Boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v123, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: java.lang.String} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0464  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x046d  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x0769  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x076c  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0bc1  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0344  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzko[] zza(java.lang.String r37, com.google.android.gms.internal.measurement.zzkp[] r38, com.google.android.gms.internal.measurement.zzku[] r39) {
        /*
            r36 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r37)
            java.util.HashSet r26 = new java.util.HashSet
            r26.<init>()
            android.support.v4.util.ArrayMap r27 = new android.support.v4.util.ArrayMap
            r27.<init>()
            android.support.v4.util.ArrayMap r28 = new android.support.v4.util.ArrayMap
            r28.<init>()
            android.support.v4.util.ArrayMap r29 = new android.support.v4.util.ArrayMap
            r29.<init>()
            com.google.android.gms.internal.measurement.zzej r4 = r36.zzje()
            r0 = r37
            java.util.Map r8 = r4.zzbe(r0)
            if (r8 == 0) goto L_0x00e1
            java.util.Set r4 = r8.keySet()
            java.util.Iterator r9 = r4.iterator()
        L_0x002b:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L_0x00e1
            java.lang.Object r4 = r9.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r10 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            java.lang.Object r4 = r8.get(r4)
            com.google.android.gms.internal.measurement.zzkt r4 = (com.google.android.gms.internal.measurement.zzkt) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r10)
            r0 = r28
            java.lang.Object r5 = r0.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)
            r0 = r29
            java.lang.Object r6 = r0.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            if (r5 != 0) goto L_0x007b
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)
            r0 = r28
            r0.put(r6, r5)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r10)
            r0 = r29
            r0.put(r7, r6)
        L_0x007b:
            r7 = 0
        L_0x007c:
            long[] r11 = r4.zzauw
            int r11 = r11.length
            int r11 = r11 << 6
            if (r7 >= r11) goto L_0x00b1
            long[] r11 = r4.zzauw
            boolean r11 = com.google.android.gms.internal.measurement.zzkc.zza((long[]) r11, (int) r7)
            if (r11 == 0) goto L_0x00ae
            com.google.android.gms.internal.measurement.zzfh r11 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r11 = r11.zziz()
            java.lang.String r12 = "Filter already evaluated. audience ID, filter ID"
            java.lang.Integer r13 = java.lang.Integer.valueOf(r10)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r7)
            r11.zze(r12, r13, r14)
            r6.set(r7)
            long[] r11 = r4.zzaux
            boolean r11 = com.google.android.gms.internal.measurement.zzkc.zza((long[]) r11, (int) r7)
            if (r11 == 0) goto L_0x00ae
            r5.set(r7)
        L_0x00ae:
            int r7 = r7 + 1
            goto L_0x007c
        L_0x00b1:
            com.google.android.gms.internal.measurement.zzko r7 = new com.google.android.gms.internal.measurement.zzko
            r7.<init>()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r0 = r27
            r0.put(r10, r7)
            r10 = 0
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r7.zzatk = r10
            r7.zzatj = r4
            com.google.android.gms.internal.measurement.zzkt r4 = new com.google.android.gms.internal.measurement.zzkt
            r4.<init>()
            r7.zzati = r4
            com.google.android.gms.internal.measurement.zzkt r4 = r7.zzati
            long[] r5 = com.google.android.gms.internal.measurement.zzkc.zza((java.util.BitSet) r5)
            r4.zzaux = r5
            com.google.android.gms.internal.measurement.zzkt r4 = r7.zzati
            long[] r5 = com.google.android.gms.internal.measurement.zzkc.zza((java.util.BitSet) r6)
            r4.zzauw = r5
            goto L_0x002b
        L_0x00e1:
            if (r38 == 0) goto L_0x0786
            r10 = 0
            r8 = 0
            r5 = 0
            android.support.v4.util.ArrayMap r30 = new android.support.v4.util.ArrayMap
            r30.<init>()
            r0 = r38
            int r0 = r0.length
            r31 = r0
            r4 = 0
            r25 = r4
        L_0x00f4:
            r0 = r25
            r1 = r31
            if (r0 >= r1) goto L_0x0786
            r14 = r38[r25]
            java.lang.String r11 = r14.name
            com.google.android.gms.internal.measurement.zzkq[] r12 = r14.zzatm
            com.google.android.gms.internal.measurement.zzeg r4 = r36.zzgh()
            com.google.android.gms.internal.measurement.zzey$zza<java.lang.Boolean> r6 = com.google.android.gms.internal.measurement.zzey.zzaic
            r0 = r37
            boolean r4 = r4.zzd(r0, r6)
            if (r4 == 0) goto L_0x0ba6
            r36.zzjc()
            java.lang.String r4 = "_eid"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzjy.zzb(r14, r4)
            java.lang.Long r7 = (java.lang.Long) r7
            if (r7 == 0) goto L_0x014e
            r4 = 1
            r6 = r4
        L_0x011d:
            if (r6 == 0) goto L_0x0151
            java.lang.String r4 = "_ep"
            boolean r4 = r11.equals(r4)
            if (r4 == 0) goto L_0x0151
            r4 = 1
        L_0x0128:
            if (r4 == 0) goto L_0x02f4
            r36.zzjc()
            java.lang.String r4 = "_en"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzjy.zzb(r14, r4)
            r11 = r4
            java.lang.String r11 = (java.lang.String) r11
            boolean r4 = android.text.TextUtils.isEmpty(r11)
            if (r4 == 0) goto L_0x0153
            com.google.android.gms.internal.measurement.zzfh r4 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zzis()
            java.lang.String r6 = "Extra parameter without an event name. eventId"
            r4.zzg(r6, r7)
        L_0x0149:
            int r4 = r25 + 1
            r25 = r4
            goto L_0x00f4
        L_0x014e:
            r4 = 0
            r6 = r4
            goto L_0x011d
        L_0x0151:
            r4 = 0
            goto L_0x0128
        L_0x0153:
            if (r10 == 0) goto L_0x0163
            if (r5 == 0) goto L_0x0163
            long r16 = r7.longValue()
            long r18 = r5.longValue()
            int r4 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r4 == 0) goto L_0x0bb2
        L_0x0163:
            com.google.android.gms.internal.measurement.zzej r4 = r36.zzje()
            r0 = r37
            android.util.Pair r6 = r4.zza((java.lang.String) r0, (java.lang.Long) r7)
            if (r6 == 0) goto L_0x0173
            java.lang.Object r4 = r6.first
            if (r4 != 0) goto L_0x0181
        L_0x0173:
            com.google.android.gms.internal.measurement.zzfh r4 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zzis()
            java.lang.String r6 = "Extra parameter without existing main event. eventName, eventId"
            r4.zze(r6, r11, r7)
            goto L_0x0149
        L_0x0181:
            java.lang.Object r4 = r6.first
            com.google.android.gms.internal.measurement.zzkp r4 = (com.google.android.gms.internal.measurement.zzkp) r4
            java.lang.Object r5 = r6.second
            java.lang.Long r5 = (java.lang.Long) r5
            long r8 = r5.longValue()
            r36.zzjc()
            java.lang.String r5 = "_eid"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzjy.zzb(r4, r5)
            java.lang.Long r5 = (java.lang.Long) r5
            r13 = r5
            r10 = r4
        L_0x019a:
            r4 = 1
            long r8 = r8 - r4
            r4 = 0
            int r4 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x0201
            com.google.android.gms.internal.measurement.zzej r5 = r36.zzje()
            r5.zzab()
            com.google.android.gms.internal.measurement.zzfh r4 = r5.zzgf()
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zziz()
            java.lang.String r6 = "Clearing complex main event info. appId"
            r0 = r37
            r4.zzg(r6, r0)
            android.database.sqlite.SQLiteDatabase r4 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x01f2 }
            java.lang.String r6 = "delete from main_event_params where app_id=?"
            r7 = 1
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x01f2 }
            r15 = 0
            r7[r15] = r37     // Catch:{ SQLiteException -> 0x01f2 }
            r4.execSQL(r6, r7)     // Catch:{ SQLiteException -> 0x01f2 }
        L_0x01c8:
            com.google.android.gms.internal.measurement.zzkq[] r4 = r10.zzatm
            int r4 = r4.length
            int r5 = r12.length
            int r4 = r4 + r5
            com.google.android.gms.internal.measurement.zzkq[] r6 = new com.google.android.gms.internal.measurement.zzkq[r4]
            r5 = 0
            com.google.android.gms.internal.measurement.zzkq[] r15 = r10.zzatm
            int r0 = r15.length
            r16 = r0
            r4 = 0
            r7 = r4
        L_0x01d7:
            r0 = r16
            if (r7 >= r0) goto L_0x020b
            r17 = r15[r7]
            r36.zzjc()
            r0 = r17
            java.lang.String r4 = r0.name
            com.google.android.gms.internal.measurement.zzkq r4 = com.google.android.gms.internal.measurement.zzjy.zza((com.google.android.gms.internal.measurement.zzkp) r14, (java.lang.String) r4)
            if (r4 != 0) goto L_0x0bc4
            int r4 = r5 + 1
            r6[r5] = r17
        L_0x01ee:
            int r7 = r7 + 1
            r5 = r4
            goto L_0x01d7
        L_0x01f2:
            r4 = move-exception
            com.google.android.gms.internal.measurement.zzfh r5 = r5.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zzis()
            java.lang.String r6 = "Error clearing complex main event"
            r5.zzg(r6, r4)
            goto L_0x01c8
        L_0x0201:
            com.google.android.gms.internal.measurement.zzej r5 = r36.zzje()
            r6 = r37
            r5.zza(r6, r7, r8, r10)
            goto L_0x01c8
        L_0x020b:
            if (r5 <= 0) goto L_0x02db
            int r15 = r12.length
            r4 = 0
        L_0x020f:
            if (r4 >= r15) goto L_0x021b
            r16 = r12[r4]
            int r7 = r5 + 1
            r6[r5] = r16
            int r4 = r4 + 1
            r5 = r7
            goto L_0x020f
        L_0x021b:
            int r4 = r6.length
            if (r5 != r4) goto L_0x02d3
            r4 = r6
        L_0x021f:
            r19 = r4
            r20 = r11
            r21 = r13
            r22 = r8
            r24 = r10
        L_0x0229:
            com.google.android.gms.internal.measurement.zzej r4 = r36.zzje()
            java.lang.String r5 = r14.name
            r0 = r37
            com.google.android.gms.internal.measurement.zzes r4 = r4.zzf(r0, r5)
            if (r4 != 0) goto L_0x0344
            com.google.android.gms.internal.measurement.zzfh r4 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zziv()
            java.lang.String r5 = "Event aggregate wasn't created during raw event logging. appId, event"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfh.zzbl(r37)
            com.google.android.gms.internal.measurement.zzff r7 = r36.zzgb()
            r0 = r20
            java.lang.String r7 = r7.zzbi(r0)
            r4.zze(r5, r6, r7)
            com.google.android.gms.internal.measurement.zzes r5 = new com.google.android.gms.internal.measurement.zzes
            java.lang.String r7 = r14.name
            r8 = 1
            r10 = 1
            java.lang.Long r4 = r14.zzatn
            long r12 = r4.longValue()
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r6 = r37
            r5.<init>(r6, r7, r8, r10, r12, r14, r16, r17, r18)
        L_0x026d:
            com.google.android.gms.internal.measurement.zzej r4 = r36.zzje()
            r4.zza((com.google.android.gms.internal.measurement.zzes) r5)
            long r12 = r5.zzafs
            r0 = r30
            r1 = r20
            java.lang.Object r4 = r0.get(r1)
            java.util.Map r4 = (java.util.Map) r4
            if (r4 != 0) goto L_0x0bc1
            com.google.android.gms.internal.measurement.zzej r4 = r36.zzje()
            r0 = r37
            r1 = r20
            java.util.Map r4 = r4.zzk(r0, r1)
            if (r4 != 0) goto L_0x0295
            android.support.v4.util.ArrayMap r4 = new android.support.v4.util.ArrayMap
            r4.<init>()
        L_0x0295:
            r0 = r30
            r1 = r20
            r0.put(r1, r4)
            r7 = r4
        L_0x029d:
            java.util.Set r4 = r7.keySet()
            java.util.Iterator r11 = r4.iterator()
        L_0x02a5:
            boolean r4 = r11.hasNext()
            if (r4 == 0) goto L_0x0bb9
            java.lang.Object r4 = r11.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r14 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r0 = r26
            boolean r4 = r0.contains(r4)
            if (r4 == 0) goto L_0x034a
            com.google.android.gms.internal.measurement.zzfh r4 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zziz()
            java.lang.String r5 = "Skipping failed audience ID"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r14)
            r4.zzg(r5, r6)
            goto L_0x02a5
        L_0x02d3:
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r6, r5)
            com.google.android.gms.internal.measurement.zzkq[] r4 = (com.google.android.gms.internal.measurement.zzkq[]) r4
            goto L_0x021f
        L_0x02db:
            com.google.android.gms.internal.measurement.zzfh r4 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zziv()
            java.lang.String r5 = "No unique parameters in main event. eventName"
            r4.zzg(r5, r11)
            r19 = r12
            r20 = r11
            r21 = r13
            r22 = r8
            r24 = r10
            goto L_0x0229
        L_0x02f4:
            if (r6 == 0) goto L_0x0ba6
            r36.zzjc()
            java.lang.String r5 = "_epc"
            r8 = 0
            java.lang.Long r4 = java.lang.Long.valueOf(r8)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzjy.zzb(r14, r5)
            if (r5 != 0) goto L_0x032c
        L_0x0307:
            java.lang.Long r4 = (java.lang.Long) r4
            long r8 = r4.longValue()
            r4 = 0
            int r4 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x032e
            com.google.android.gms.internal.measurement.zzfh r4 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zziv()
            java.lang.String r5 = "Complex event with zero extra param count. eventName"
            r4.zzg(r5, r11)
            r19 = r12
            r20 = r11
            r21 = r7
            r22 = r8
            r24 = r14
            goto L_0x0229
        L_0x032c:
            r4 = r5
            goto L_0x0307
        L_0x032e:
            com.google.android.gms.internal.measurement.zzej r5 = r36.zzje()
            r6 = r37
            r10 = r14
            r5.zza(r6, r7, r8, r10)
            r19 = r12
            r20 = r11
            r21 = r7
            r22 = r8
            r24 = r14
            goto L_0x0229
        L_0x0344:
            com.google.android.gms.internal.measurement.zzes r5 = r4.zzii()
            goto L_0x026d
        L_0x034a:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r0 = r27
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzko r4 = (com.google.android.gms.internal.measurement.zzko) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r14)
            r0 = r28
            java.lang.Object r5 = r0.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r14)
            r0 = r29
            java.lang.Object r6 = r0.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            if (r4 != 0) goto L_0x0bb5
            com.google.android.gms.internal.measurement.zzko r4 = new com.google.android.gms.internal.measurement.zzko
            r4.<init>()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r14)
            r0 = r27
            r0.put(r5, r4)
            r5 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r4.zzatk = r5
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r0 = r28
            r0.put(r4, r5)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r0 = r29
            r0.put(r4, r6)
            r8 = r6
            r9 = r5
        L_0x03a3:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            java.lang.Object r4 = r7.get(r4)
            java.util.List r4 = (java.util.List) r4
            java.util.Iterator r15 = r4.iterator()
        L_0x03b1:
            boolean r4 = r15.hasNext()
            if (r4 == 0) goto L_0x02a5
            java.lang.Object r4 = r15.next()
            com.google.android.gms.internal.measurement.zzkg r4 = (com.google.android.gms.internal.measurement.zzkg) r4
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            r6 = 2
            boolean r5 = r5.isLoggable(r6)
            if (r5 == 0) goto L_0x0402
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziz()
            java.lang.String r6 = "Evaluating filter. audience, filter, event"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r14)
            java.lang.Integer r0 = r4.zzasb
            r16 = r0
            com.google.android.gms.internal.measurement.zzff r17 = r36.zzgb()
            java.lang.String r0 = r4.zzasc
            r18 = r0
            java.lang.String r17 = r17.zzbi(r18)
            r0 = r16
            r1 = r17
            r5.zzd(r6, r10, r0, r1)
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziz()
            java.lang.String r6 = "Filter definition"
            com.google.android.gms.internal.measurement.zzjy r10 = r36.zzjc()
            java.lang.String r10 = r10.zza((com.google.android.gms.internal.measurement.zzkg) r4)
            r5.zzg(r6, r10)
        L_0x0402:
            java.lang.Integer r5 = r4.zzasb
            if (r5 == 0) goto L_0x0410
            java.lang.Integer r5 = r4.zzasb
            int r5 = r5.intValue()
            r6 = 256(0x100, float:3.59E-43)
            if (r5 <= r6) goto L_0x0428
        L_0x0410:
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "Invalid event filter ID. appId, id"
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfh.zzbl(r37)
            java.lang.Integer r4 = r4.zzasb
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r5.zze(r6, r10, r4)
            goto L_0x03b1
        L_0x0428:
            java.lang.Integer r5 = r4.zzasb
            int r5 = r5.intValue()
            boolean r5 = r9.get(r5)
            if (r5 == 0) goto L_0x0449
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziz()
            java.lang.String r6 = "Event filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r14)
            java.lang.Integer r4 = r4.zzasb
            r5.zze(r6, r10, r4)
            goto L_0x03b1
        L_0x0449:
            com.google.android.gms.internal.measurement.zzki r5 = r4.zzasf
            if (r5 == 0) goto L_0x0484
            com.google.android.gms.internal.measurement.zzki r5 = r4.zzasf
            r0 = r36
            java.lang.Boolean r5 = r0.zza((long) r12, (com.google.android.gms.internal.measurement.zzki) r5)
            if (r5 != 0) goto L_0x0478
            r5 = 0
        L_0x0458:
            com.google.android.gms.internal.measurement.zzfh r6 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r10 = r6.zziz()
            java.lang.String r16 = "Event filter result"
            if (r5 != 0) goto L_0x0769
            java.lang.String r6 = "null"
        L_0x0466:
            r0 = r16
            r10.zzg(r0, r6)
            if (r5 != 0) goto L_0x076c
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r0 = r26
            r0.add(r4)
            goto L_0x03b1
        L_0x0478:
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L_0x0484
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto L_0x0458
        L_0x0484:
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            com.google.android.gms.internal.measurement.zzkh[] r10 = r4.zzasd
            int r0 = r10.length
            r16 = r0
            r5 = 0
        L_0x048f:
            r0 = r16
            if (r5 >= r0) goto L_0x04c8
            r17 = r10[r5]
            r0 = r17
            java.lang.String r0 = r0.zzask
            r18 = r0
            boolean r18 = android.text.TextUtils.isEmpty(r18)
            if (r18 == 0) goto L_0x04ba
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "null or empty param name in filter. event"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            r5.zzg(r6, r10)
            r5 = 0
            goto L_0x0458
        L_0x04ba:
            r0 = r17
            java.lang.String r0 = r0.zzask
            r17 = r0
            r0 = r17
            r6.add(r0)
            int r5 = r5 + 1
            goto L_0x048f
        L_0x04c8:
            android.support.v4.util.ArrayMap r16 = new android.support.v4.util.ArrayMap
            r16.<init>()
            r0 = r19
            int r10 = r0.length
            r5 = 0
        L_0x04d1:
            if (r5 >= r10) goto L_0x0569
            r17 = r19[r5]
            r0 = r17
            java.lang.String r0 = r0.name
            r18 = r0
            r0 = r18
            boolean r18 = r6.contains(r0)
            if (r18 == 0) goto L_0x0500
            r0 = r17
            java.lang.Long r0 = r0.zzatq
            r18 = r0
            if (r18 == 0) goto L_0x0503
            r0 = r17
            java.lang.String r0 = r0.name
            r18 = r0
            r0 = r17
            java.lang.Long r0 = r0.zzatq
            r17 = r0
            r0 = r16
            r1 = r18
            r2 = r17
            r0.put(r1, r2)
        L_0x0500:
            int r5 = r5 + 1
            goto L_0x04d1
        L_0x0503:
            r0 = r17
            java.lang.Double r0 = r0.zzaro
            r18 = r0
            if (r18 == 0) goto L_0x0521
            r0 = r17
            java.lang.String r0 = r0.name
            r18 = r0
            r0 = r17
            java.lang.Double r0 = r0.zzaro
            r17 = r0
            r0 = r16
            r1 = r18
            r2 = r17
            r0.put(r1, r2)
            goto L_0x0500
        L_0x0521:
            r0 = r17
            java.lang.String r0 = r0.zzajo
            r18 = r0
            if (r18 == 0) goto L_0x053f
            r0 = r17
            java.lang.String r0 = r0.name
            r18 = r0
            r0 = r17
            java.lang.String r0 = r0.zzajo
            r17 = r0
            r0 = r16
            r1 = r18
            r2 = r17
            r0.put(r1, r2)
            goto L_0x0500
        L_0x053f:
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "Unknown value for param. event, param"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            com.google.android.gms.internal.measurement.zzff r16 = r36.zzgb()
            r0 = r17
            java.lang.String r0 = r0.name
            r17 = r0
            java.lang.String r16 = r16.zzbj(r17)
            r0 = r16
            r5.zze(r6, r10, r0)
            r5 = 0
            goto L_0x0458
        L_0x0569:
            com.google.android.gms.internal.measurement.zzkh[] r0 = r4.zzasd
            r17 = r0
            r0 = r17
            int r0 = r0.length
            r18 = r0
            r5 = 0
            r10 = r5
        L_0x0574:
            r0 = r18
            if (r10 >= r0) goto L_0x0762
            r32 = r17[r10]
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            r0 = r32
            java.lang.Boolean r6 = r0.zzasj
            boolean r33 = r5.equals(r6)
            r0 = r32
            java.lang.String r0 = r0.zzask
            r34 = r0
            boolean r5 = android.text.TextUtils.isEmpty(r34)
            if (r5 == 0) goto L_0x05aa
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "Event has empty param name. event"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            r5.zzg(r6, r10)
            r5 = 0
            goto L_0x0458
        L_0x05aa:
            r0 = r16
            r1 = r34
            java.lang.Object r5 = r0.get(r1)
            boolean r6 = r5 instanceof java.lang.Long
            if (r6 == 0) goto L_0x060f
            r0 = r32
            com.google.android.gms.internal.measurement.zzki r6 = r0.zzasi
            if (r6 != 0) goto L_0x05e4
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "No number filter for long param. event, param"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            com.google.android.gms.internal.measurement.zzff r16 = r36.zzgb()
            r0 = r16
            r1 = r34
            java.lang.String r16 = r0.zzbj(r1)
            r0 = r16
            r5.zze(r6, r10, r0)
            r5 = 0
            goto L_0x0458
        L_0x05e4:
            java.lang.Long r5 = (java.lang.Long) r5
            long r34 = r5.longValue()
            r0 = r32
            com.google.android.gms.internal.measurement.zzki r5 = r0.zzasi
            r0 = r36
            r1 = r34
            java.lang.Boolean r5 = r0.zza((long) r1, (com.google.android.gms.internal.measurement.zzki) r5)
            if (r5 != 0) goto L_0x05fb
            r5 = 0
            goto L_0x0458
        L_0x05fb:
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L_0x060d
            r5 = 1
        L_0x0602:
            r5 = r5 ^ r33
            if (r5 == 0) goto L_0x075d
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto L_0x0458
        L_0x060d:
            r5 = 0
            goto L_0x0602
        L_0x060f:
            boolean r6 = r5 instanceof java.lang.Double
            if (r6 == 0) goto L_0x066c
            r0 = r32
            com.google.android.gms.internal.measurement.zzki r6 = r0.zzasi
            if (r6 != 0) goto L_0x0641
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "No number filter for double param. event, param"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            com.google.android.gms.internal.measurement.zzff r16 = r36.zzgb()
            r0 = r16
            r1 = r34
            java.lang.String r16 = r0.zzbj(r1)
            r0 = r16
            r5.zze(r6, r10, r0)
            r5 = 0
            goto L_0x0458
        L_0x0641:
            java.lang.Double r5 = (java.lang.Double) r5
            double r34 = r5.doubleValue()
            r0 = r32
            com.google.android.gms.internal.measurement.zzki r5 = r0.zzasi
            r0 = r36
            r1 = r34
            java.lang.Boolean r5 = r0.zza((double) r1, (com.google.android.gms.internal.measurement.zzki) r5)
            if (r5 != 0) goto L_0x0658
            r5 = 0
            goto L_0x0458
        L_0x0658:
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L_0x066a
            r5 = 1
        L_0x065f:
            r5 = r5 ^ r33
            if (r5 == 0) goto L_0x075d
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto L_0x0458
        L_0x066a:
            r5 = 0
            goto L_0x065f
        L_0x066c:
            boolean r6 = r5 instanceof java.lang.String
            if (r6 == 0) goto L_0x0707
            r0 = r32
            com.google.android.gms.internal.measurement.zzkk r6 = r0.zzash
            if (r6 == 0) goto L_0x0687
            java.lang.String r5 = (java.lang.String) r5
            r0 = r32
            com.google.android.gms.internal.measurement.zzkk r6 = r0.zzash
            r0 = r36
            java.lang.Boolean r5 = r0.zza((java.lang.String) r5, (com.google.android.gms.internal.measurement.zzkk) r6)
        L_0x0682:
            if (r5 != 0) goto L_0x06f3
            r5 = 0
            goto L_0x0458
        L_0x0687:
            r0 = r32
            com.google.android.gms.internal.measurement.zzki r6 = r0.zzasi
            if (r6 == 0) goto L_0x06cb
            r6 = r5
            java.lang.String r6 = (java.lang.String) r6
            boolean r6 = com.google.android.gms.internal.measurement.zzkc.zzcj(r6)
            if (r6 == 0) goto L_0x06a3
            java.lang.String r5 = (java.lang.String) r5
            r0 = r32
            com.google.android.gms.internal.measurement.zzki r6 = r0.zzasi
            r0 = r36
            java.lang.Boolean r5 = r0.zza((java.lang.String) r5, (com.google.android.gms.internal.measurement.zzki) r6)
            goto L_0x0682
        L_0x06a3:
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "Invalid param value for number filter. event, param"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            com.google.android.gms.internal.measurement.zzff r16 = r36.zzgb()
            r0 = r16
            r1 = r34
            java.lang.String r16 = r0.zzbj(r1)
            r0 = r16
            r5.zze(r6, r10, r0)
            r5 = 0
            goto L_0x0458
        L_0x06cb:
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "No filter for String param. event, param"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            com.google.android.gms.internal.measurement.zzff r16 = r36.zzgb()
            r0 = r16
            r1 = r34
            java.lang.String r16 = r0.zzbj(r1)
            r0 = r16
            r5.zze(r6, r10, r0)
            r5 = 0
            goto L_0x0458
        L_0x06f3:
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L_0x0705
            r5 = 1
        L_0x06fa:
            r5 = r5 ^ r33
            if (r5 == 0) goto L_0x075d
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto L_0x0458
        L_0x0705:
            r5 = 0
            goto L_0x06fa
        L_0x0707:
            if (r5 != 0) goto L_0x0735
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziz()
            java.lang.String r6 = "Missing param for filter. event, param"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            com.google.android.gms.internal.measurement.zzff r16 = r36.zzgb()
            r0 = r16
            r1 = r34
            java.lang.String r16 = r0.zzbj(r1)
            r0 = r16
            r5.zze(r6, r10, r0)
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto L_0x0458
        L_0x0735:
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "Unknown param type. event, param"
            com.google.android.gms.internal.measurement.zzff r10 = r36.zzgb()
            r0 = r20
            java.lang.String r10 = r10.zzbi(r0)
            com.google.android.gms.internal.measurement.zzff r16 = r36.zzgb()
            r0 = r16
            r1 = r34
            java.lang.String r16 = r0.zzbj(r1)
            r0 = r16
            r5.zze(r6, r10, r0)
            r5 = 0
            goto L_0x0458
        L_0x075d:
            int r5 = r10 + 1
            r10 = r5
            goto L_0x0574
        L_0x0762:
            r5 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto L_0x0458
        L_0x0769:
            r6 = r5
            goto L_0x0466
        L_0x076c:
            java.lang.Integer r6 = r4.zzasb
            int r6 = r6.intValue()
            r8.set(r6)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x03b1
            java.lang.Integer r4 = r4.zzasb
            int r4 = r4.intValue()
            r9.set(r4)
            goto L_0x03b1
        L_0x0786:
            if (r39 == 0) goto L_0x0a92
            android.support.v4.util.ArrayMap r11 = new android.support.v4.util.ArrayMap
            r11.<init>()
            r0 = r39
            int r12 = r0.length
            r4 = 0
            r10 = r4
        L_0x0792:
            if (r10 >= r12) goto L_0x0a92
            r13 = r39[r10]
            java.lang.String r4 = r13.name
            java.lang.Object r4 = r11.get(r4)
            java.util.Map r4 = (java.util.Map) r4
            if (r4 != 0) goto L_0x0ba3
            com.google.android.gms.internal.measurement.zzej r4 = r36.zzje()
            java.lang.String r5 = r13.name
            r0 = r37
            java.util.Map r4 = r4.zzl(r0, r5)
            if (r4 != 0) goto L_0x07b3
            android.support.v4.util.ArrayMap r4 = new android.support.v4.util.ArrayMap
            r4.<init>()
        L_0x07b3:
            java.lang.String r5 = r13.name
            r11.put(r5, r4)
            r7 = r4
        L_0x07b9:
            java.util.Set r4 = r7.keySet()
            java.util.Iterator r14 = r4.iterator()
        L_0x07c1:
            boolean r4 = r14.hasNext()
            if (r4 == 0) goto L_0x0a8d
            java.lang.Object r4 = r14.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r15 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            r0 = r26
            boolean r4 = r0.contains(r4)
            if (r4 == 0) goto L_0x07ef
            com.google.android.gms.internal.measurement.zzfh r4 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zziz()
            java.lang.String r5 = "Skipping failed audience ID"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r15)
            r4.zzg(r5, r6)
            goto L_0x07c1
        L_0x07ef:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            r0 = r27
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzko r4 = (com.google.android.gms.internal.measurement.zzko) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r15)
            r0 = r28
            java.lang.Object r5 = r0.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r15)
            r0 = r29
            java.lang.Object r6 = r0.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            if (r4 != 0) goto L_0x0846
            com.google.android.gms.internal.measurement.zzko r4 = new com.google.android.gms.internal.measurement.zzko
            r4.<init>()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r15)
            r0 = r27
            r0.put(r5, r4)
            r5 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r4.zzatk = r5
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            r0 = r28
            r0.put(r4, r5)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            r0 = r29
            r0.put(r4, r6)
        L_0x0846:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            java.lang.Object r4 = r7.get(r4)
            java.util.List r4 = (java.util.List) r4
            java.util.Iterator r16 = r4.iterator()
        L_0x0854:
            boolean r4 = r16.hasNext()
            if (r4 == 0) goto L_0x07c1
            java.lang.Object r4 = r16.next()
            com.google.android.gms.internal.measurement.zzkj r4 = (com.google.android.gms.internal.measurement.zzkj) r4
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            r9 = 2
            boolean r8 = r8.isLoggable(r9)
            if (r8 == 0) goto L_0x08ab
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziz()
            java.lang.String r9 = "Evaluating filter. audience, filter, property"
            java.lang.Integer r17 = java.lang.Integer.valueOf(r15)
            java.lang.Integer r0 = r4.zzasb
            r18 = r0
            com.google.android.gms.internal.measurement.zzff r19 = r36.zzgb()
            java.lang.String r0 = r4.zzasr
            r20 = r0
            java.lang.String r19 = r19.zzbk(r20)
            r0 = r17
            r1 = r18
            r2 = r19
            r8.zzd(r9, r0, r1, r2)
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziz()
            java.lang.String r9 = "Filter definition"
            com.google.android.gms.internal.measurement.zzjy r17 = r36.zzjc()
            r0 = r17
            java.lang.String r17 = r0.zza((com.google.android.gms.internal.measurement.zzkj) r4)
            r0 = r17
            r8.zzg(r9, r0)
        L_0x08ab:
            java.lang.Integer r8 = r4.zzasb
            if (r8 == 0) goto L_0x08b9
            java.lang.Integer r8 = r4.zzasb
            int r8 = r8.intValue()
            r9 = 256(0x100, float:3.59E-43)
            if (r8 <= r9) goto L_0x08db
        L_0x08b9:
            com.google.android.gms.internal.measurement.zzfh r5 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zziv()
            java.lang.String r6 = "Invalid property filter ID. appId, id"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfh.zzbl(r37)
            java.lang.Integer r4 = r4.zzasb
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r5.zze(r6, r8, r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            r0 = r26
            r0.add(r4)
            goto L_0x07c1
        L_0x08db:
            java.lang.Integer r8 = r4.zzasb
            int r8 = r8.intValue()
            boolean r8 = r5.get(r8)
            if (r8 == 0) goto L_0x08fe
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziz()
            java.lang.String r9 = "Property filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r17 = java.lang.Integer.valueOf(r15)
            java.lang.Integer r4 = r4.zzasb
            r0 = r17
            r8.zze(r9, r0, r4)
            goto L_0x0854
        L_0x08fe:
            com.google.android.gms.internal.measurement.zzkh r8 = r4.zzass
            if (r8 != 0) goto L_0x0940
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziv()
            java.lang.String r9 = "Missing property filter. property"
            com.google.android.gms.internal.measurement.zzff r17 = r36.zzgb()
            java.lang.String r0 = r13.name
            r18 = r0
            java.lang.String r17 = r17.zzbk(r18)
            r0 = r17
            r8.zzg(r9, r0)
            r8 = 0
        L_0x091e:
            com.google.android.gms.internal.measurement.zzfh r9 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r17 = r9.zziz()
            java.lang.String r18 = "Property filter result"
            if (r8 != 0) goto L_0x0a70
            java.lang.String r9 = "null"
        L_0x092c:
            r0 = r17
            r1 = r18
            r0.zzg(r1, r9)
            if (r8 != 0) goto L_0x0a73
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            r0 = r26
            r0.add(r4)
            goto L_0x0854
        L_0x0940:
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            java.lang.Boolean r0 = r8.zzasj
            r17 = r0
            r0 = r17
            boolean r9 = r9.equals(r0)
            java.lang.Long r0 = r13.zzatq
            r17 = r0
            if (r17 == 0) goto L_0x098c
            com.google.android.gms.internal.measurement.zzki r0 = r8.zzasi
            r17 = r0
            if (r17 != 0) goto L_0x0975
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziv()
            java.lang.String r9 = "No number filter for long property. property"
            com.google.android.gms.internal.measurement.zzff r17 = r36.zzgb()
            java.lang.String r0 = r13.name
            r18 = r0
            java.lang.String r17 = r17.zzbk(r18)
            r0 = r17
            r8.zzg(r9, r0)
            r8 = 0
            goto L_0x091e
        L_0x0975:
            java.lang.Long r0 = r13.zzatq
            r17 = r0
            long r18 = r17.longValue()
            com.google.android.gms.internal.measurement.zzki r8 = r8.zzasi
            r0 = r36
            r1 = r18
            java.lang.Boolean r8 = r0.zza((long) r1, (com.google.android.gms.internal.measurement.zzki) r8)
            java.lang.Boolean r8 = zza((java.lang.Boolean) r8, (boolean) r9)
            goto L_0x091e
        L_0x098c:
            java.lang.Double r0 = r13.zzaro
            r17 = r0
            if (r17 == 0) goto L_0x09ce
            com.google.android.gms.internal.measurement.zzki r0 = r8.zzasi
            r17 = r0
            if (r17 != 0) goto L_0x09b6
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziv()
            java.lang.String r9 = "No number filter for double property. property"
            com.google.android.gms.internal.measurement.zzff r17 = r36.zzgb()
            java.lang.String r0 = r13.name
            r18 = r0
            java.lang.String r17 = r17.zzbk(r18)
            r0 = r17
            r8.zzg(r9, r0)
            r8 = 0
            goto L_0x091e
        L_0x09b6:
            java.lang.Double r0 = r13.zzaro
            r17 = r0
            double r18 = r17.doubleValue()
            com.google.android.gms.internal.measurement.zzki r8 = r8.zzasi
            r0 = r36
            r1 = r18
            java.lang.Boolean r8 = r0.zza((double) r1, (com.google.android.gms.internal.measurement.zzki) r8)
            java.lang.Boolean r8 = zza((java.lang.Boolean) r8, (boolean) r9)
            goto L_0x091e
        L_0x09ce:
            java.lang.String r0 = r13.zzajo
            r17 = r0
            if (r17 == 0) goto L_0x0a52
            com.google.android.gms.internal.measurement.zzkk r0 = r8.zzash
            r17 = r0
            if (r17 != 0) goto L_0x0a3e
            com.google.android.gms.internal.measurement.zzki r0 = r8.zzasi
            r17 = r0
            if (r17 != 0) goto L_0x09fe
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziv()
            java.lang.String r9 = "No string or number filter defined. property"
            com.google.android.gms.internal.measurement.zzff r17 = r36.zzgb()
            java.lang.String r0 = r13.name
            r18 = r0
            java.lang.String r17 = r17.zzbk(r18)
            r0 = r17
            r8.zzg(r9, r0)
        L_0x09fb:
            r8 = 0
            goto L_0x091e
        L_0x09fe:
            java.lang.String r0 = r13.zzajo
            r17 = r0
            boolean r17 = com.google.android.gms.internal.measurement.zzkc.zzcj(r17)
            if (r17 == 0) goto L_0x0a1c
            java.lang.String r0 = r13.zzajo
            r17 = r0
            com.google.android.gms.internal.measurement.zzki r8 = r8.zzasi
            r0 = r36
            r1 = r17
            java.lang.Boolean r8 = r0.zza((java.lang.String) r1, (com.google.android.gms.internal.measurement.zzki) r8)
            java.lang.Boolean r8 = zza((java.lang.Boolean) r8, (boolean) r9)
            goto L_0x091e
        L_0x0a1c:
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziv()
            java.lang.String r9 = "Invalid user property value for Numeric number filter. property, value"
            com.google.android.gms.internal.measurement.zzff r17 = r36.zzgb()
            java.lang.String r0 = r13.name
            r18 = r0
            java.lang.String r17 = r17.zzbk(r18)
            java.lang.String r0 = r13.zzajo
            r18 = r0
            r0 = r17
            r1 = r18
            r8.zze(r9, r0, r1)
            goto L_0x09fb
        L_0x0a3e:
            java.lang.String r0 = r13.zzajo
            r17 = r0
            com.google.android.gms.internal.measurement.zzkk r8 = r8.zzash
            r0 = r36
            r1 = r17
            java.lang.Boolean r8 = r0.zza((java.lang.String) r1, (com.google.android.gms.internal.measurement.zzkk) r8)
            java.lang.Boolean r8 = zza((java.lang.Boolean) r8, (boolean) r9)
            goto L_0x091e
        L_0x0a52:
            com.google.android.gms.internal.measurement.zzfh r8 = r36.zzgf()
            com.google.android.gms.internal.measurement.zzfj r8 = r8.zziv()
            java.lang.String r9 = "User property has no value, property"
            com.google.android.gms.internal.measurement.zzff r17 = r36.zzgb()
            java.lang.String r0 = r13.name
            r18 = r0
            java.lang.String r17 = r17.zzbk(r18)
            r0 = r17
            r8.zzg(r9, r0)
            r8 = 0
            goto L_0x091e
        L_0x0a70:
            r9 = r8
            goto L_0x092c
        L_0x0a73:
            java.lang.Integer r9 = r4.zzasb
            int r9 = r9.intValue()
            r6.set(r9)
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0854
            java.lang.Integer r4 = r4.zzasb
            int r4 = r4.intValue()
            r5.set(r4)
            goto L_0x0854
        L_0x0a8d:
            int r4 = r10 + 1
            r10 = r4
            goto L_0x0792
        L_0x0a92:
            int r4 = r28.size()
            com.google.android.gms.internal.measurement.zzko[] r8 = new com.google.android.gms.internal.measurement.zzko[r4]
            r4 = 0
            java.util.Set r5 = r28.keySet()
            java.util.Iterator r9 = r5.iterator()
            r5 = r4
        L_0x0aa2:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L_0x0b99
            java.lang.Object r4 = r9.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r10 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r0 = r26
            boolean r4 = r0.contains(r4)
            if (r4 != 0) goto L_0x0aa2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r0 = r27
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzko r4 = (com.google.android.gms.internal.measurement.zzko) r4
            if (r4 != 0) goto L_0x0ba0
            com.google.android.gms.internal.measurement.zzko r4 = new com.google.android.gms.internal.measurement.zzko
            r4.<init>()
            r7 = r4
        L_0x0ad2:
            int r6 = r5 + 1
            r8[r5] = r7
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r7.zzarx = r4
            com.google.android.gms.internal.measurement.zzkt r4 = new com.google.android.gms.internal.measurement.zzkt
            r4.<init>()
            r7.zzati = r4
            com.google.android.gms.internal.measurement.zzkt r5 = r7.zzati
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r0 = r28
            java.lang.Object r4 = r0.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            long[] r4 = com.google.android.gms.internal.measurement.zzkc.zza((java.util.BitSet) r4)
            r5.zzaux = r4
            com.google.android.gms.internal.measurement.zzkt r5 = r7.zzati
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r0 = r29
            java.lang.Object r4 = r0.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            long[] r4 = com.google.android.gms.internal.measurement.zzkc.zza((java.util.BitSet) r4)
            r5.zzauw = r4
            com.google.android.gms.internal.measurement.zzej r5 = r36.zzje()
            com.google.android.gms.internal.measurement.zzkt r4 = r7.zzati
            r5.zzch()
            r5.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r37)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            int r7 = r4.zzvv()     // Catch:{ IOException -> 0x0b6f }
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x0b6f }
            r11 = 0
            int r12 = r7.length     // Catch:{ IOException -> 0x0b6f }
            com.google.android.gms.internal.measurement.zzaby r11 = com.google.android.gms.internal.measurement.zzaby.zzb(r7, r11, r12)     // Catch:{ IOException -> 0x0b6f }
            r4.zza(r11)     // Catch:{ IOException -> 0x0b6f }
            r11.zzvn()     // Catch:{ IOException -> 0x0b6f }
            android.content.ContentValues r4 = new android.content.ContentValues
            r4.<init>()
            java.lang.String r11 = "app_id"
            r0 = r37
            r4.put(r11, r0)
            java.lang.String r11 = "audience_id"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r4.put(r11, r10)
            java.lang.String r10 = "current_results"
            r4.put(r10, r7)
            android.database.sqlite.SQLiteDatabase r7 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0b84 }
            java.lang.String r10 = "audience_filter_values"
            r11 = 0
            r12 = 5
            long r10 = r7.insertWithOnConflict(r10, r11, r4, r12)     // Catch:{ SQLiteException -> 0x0b84 }
            r12 = -1
            int r4 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r4 != 0) goto L_0x0b6c
            com.google.android.gms.internal.measurement.zzfh r4 = r5.zzgf()     // Catch:{ SQLiteException -> 0x0b84 }
            com.google.android.gms.internal.measurement.zzfj r4 = r4.zzis()     // Catch:{ SQLiteException -> 0x0b84 }
            java.lang.String r7 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfh.zzbl(r37)     // Catch:{ SQLiteException -> 0x0b84 }
            r4.zzg(r7, r10)     // Catch:{ SQLiteException -> 0x0b84 }
        L_0x0b6c:
            r5 = r6
            goto L_0x0aa2
        L_0x0b6f:
            r4 = move-exception
            com.google.android.gms.internal.measurement.zzfh r5 = r5.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zzis()
            java.lang.String r7 = "Configuration loss. Failed to serialize filter results. appId"
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfh.zzbl(r37)
            r5.zze(r7, r10, r4)
            r5 = r6
            goto L_0x0aa2
        L_0x0b84:
            r4 = move-exception
            com.google.android.gms.internal.measurement.zzfh r5 = r5.zzgf()
            com.google.android.gms.internal.measurement.zzfj r5 = r5.zzis()
            java.lang.String r7 = "Error storing filter results. appId"
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfh.zzbl(r37)
            r5.zze(r7, r10, r4)
            r5 = r6
            goto L_0x0aa2
        L_0x0b99:
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r8, r5)
            com.google.android.gms.internal.measurement.zzko[] r4 = (com.google.android.gms.internal.measurement.zzko[]) r4
            return r4
        L_0x0ba0:
            r7 = r4
            goto L_0x0ad2
        L_0x0ba3:
            r7 = r4
            goto L_0x07b9
        L_0x0ba6:
            r19 = r12
            r20 = r11
            r21 = r5
            r22 = r8
            r24 = r10
            goto L_0x0229
        L_0x0bb2:
            r13 = r5
            goto L_0x019a
        L_0x0bb5:
            r8 = r6
            r9 = r5
            goto L_0x03a3
        L_0x0bb9:
            r5 = r21
            r8 = r22
            r10 = r24
            goto L_0x0149
        L_0x0bc1:
            r7 = r4
            goto L_0x029d
        L_0x0bc4:
            r4 = r5
            goto L_0x01ee
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeb.zza(java.lang.String, com.google.android.gms.internal.measurement.zzkp[], com.google.android.gms.internal.measurement.zzku[]):com.google.android.gms.internal.measurement.zzko[]");
    }

    /* access modifiers changed from: protected */
    public final boolean zzhh() {
        return false;
    }
}
