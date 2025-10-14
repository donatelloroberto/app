package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

final class zzan {
    final String zza;
    final long zzb;
    final long zzc;
    long zzca;
    final String zzcb;
    final long zzd;
    final long zze;
    final List<zzl> zzg;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzan(java.lang.String r17, com.google.android.gms.internal.ads.zzc r18) {
        /*
            r16 = this;
            r0 = r18
            java.lang.String r5 = r0.zza
            r0 = r18
            long r6 = r0.zzb
            r0 = r18
            long r8 = r0.zzc
            r0 = r18
            long r10 = r0.zzd
            r0 = r18
            long r12 = r0.zze
            r0 = r18
            java.util.List<com.google.android.gms.internal.ads.zzl> r2 = r0.zzg
            if (r2 == 0) goto L_0x0030
            r0 = r18
            java.util.List<com.google.android.gms.internal.ads.zzl> r14 = r0.zzg
        L_0x001e:
            r3 = r16
            r4 = r17
            r3.<init>(r4, r5, r6, r8, r10, r12, r14)
            r0 = r18
            byte[] r2 = r0.data
            int r2 = r2.length
            long r2 = (long) r2
            r0 = r16
            r0.zzca = r2
            return
        L_0x0030:
            r0 = r18
            java.util.Map<java.lang.String, java.lang.String> r2 = r0.zzf
            java.util.ArrayList r14 = new java.util.ArrayList
            int r3 = r2.size()
            r14.<init>(r3)
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r4 = r2.iterator()
        L_0x0045:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x001e
            java.lang.Object r2 = r4.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            com.google.android.gms.internal.ads.zzl r15 = new com.google.android.gms.internal.ads.zzl
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            r15.<init>(r3, r2)
            r14.add(r15)
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzan.<init>(java.lang.String, com.google.android.gms.internal.ads.zzc):void");
    }

    private zzan(String str, String str2, long j, long j2, long j3, long j4, List<zzl> list) {
        this.zzcb = str;
        this.zza = "".equals(str2) ? null : str2;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = j3;
        this.zze = j4;
        this.zzg = list;
    }

    static zzan zzc(zzao zzao) throws IOException {
        if (zzam.zzb((InputStream) zzao) == 538247942) {
            return new zzan(zzam.zza(zzao), zzam.zza(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzb(zzao));
        }
        throw new IOException();
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(OutputStream outputStream) {
        try {
            zzam.zza(outputStream, 538247942);
            zzam.zza(outputStream, this.zzcb);
            zzam.zza(outputStream, this.zza == null ? "" : this.zza);
            zzam.zza(outputStream, this.zzb);
            zzam.zza(outputStream, this.zzc);
            zzam.zza(outputStream, this.zzd);
            zzam.zza(outputStream, this.zze);
            List<zzl> list = this.zzg;
            if (list != null) {
                zzam.zza(outputStream, list.size());
                for (zzl next : list) {
                    zzam.zza(outputStream, next.getName());
                    zzam.zza(outputStream, next.getValue());
                }
            } else {
                zzam.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzaf.d("%s", e.toString());
            return false;
        }
    }
}
