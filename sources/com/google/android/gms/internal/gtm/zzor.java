package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzc;
import com.google.android.gms.internal.gtm.zzg;
import com.google.android.gms.tagmanager.zzdi;
import com.google.android.gms.tagmanager.zzgj;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class zzor {
    public static zzov zza(zzi zzi) throws zzoz {
        zzl[] zzlArr = new zzl[zzi.zzpj.length];
        for (int i = 0; i < zzi.zzpj.length; i++) {
            zza(i, zzi, zzlArr, (Set<Integer>) new HashSet(0));
        }
        zzow zzmn = zzov.zzmn();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < zzi.zzpm.length; i2++) {
            arrayList.add(zza(zzi.zzpm[i2], zzi, zzlArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < zzi.zzpn.length; i3++) {
            arrayList2.add(zza(zzi.zzpn[i3], zzi, zzlArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < zzi.zzpl.length; i4++) {
            zzot zza = zza(zzi.zzpl[i4], zzi, zzlArr, i4);
            zzmn.zzc(zza);
            arrayList3.add(zza);
        }
        for (zzc.zze zze : zzi.zzpo) {
            zzoy zzoy = new zzoy();
            for (Integer intValue : zze.zzn()) {
                zzoy.zzd((zzot) arrayList2.get(intValue.intValue()));
            }
            for (Integer intValue2 : zze.zzo()) {
                zzoy.zze((zzot) arrayList2.get(intValue2.intValue()));
            }
            for (Integer intValue3 : zze.zzp()) {
                zzoy.zzf((zzot) arrayList.get(intValue3.intValue()));
            }
            for (Integer intValue4 : zze.zzr()) {
                zzoy.zzct(zzi.zzpj[intValue4.intValue()].string);
            }
            for (Integer intValue5 : zze.zzq()) {
                zzoy.zzg((zzot) arrayList.get(intValue5.intValue()));
            }
            for (Integer intValue6 : zze.zzs()) {
                zzoy.zzcu(zzi.zzpj[intValue6.intValue()].string);
            }
            for (Integer intValue7 : zze.zzt()) {
                zzoy.zzh((zzot) arrayList3.get(intValue7.intValue()));
            }
            for (Integer intValue8 : zze.zzv()) {
                zzoy.zzcv(zzi.zzpj[intValue8.intValue()].string);
            }
            for (Integer intValue9 : zze.zzu()) {
                zzoy.zzi((zzot) arrayList3.get(intValue9.intValue()));
            }
            for (Integer intValue10 : zze.zzw()) {
                zzoy.zzcw(zzi.zzpj[intValue10.intValue()].string);
            }
            zzmn.zzb(zzoy.zzms());
        }
        zzmn.zzcs(zzi.version);
        zzmn.zzaf(zzi.zzpw);
        return zzmn.zzmp();
    }

    public static zzl zzk(zzl zzl) {
        zzl zzl2 = new zzl();
        zzl2.type = zzl.type;
        zzl2.zzqv = (int[]) zzl.zzqv.clone();
        if (zzl.zzqw) {
            zzl2.zzqw = zzl.zzqw;
        }
        return zzl2;
    }

    private static zzl zza(int i, zzi zzi, zzl[] zzlArr, Set<Integer> set) throws zzoz {
        if (set.contains(Integer.valueOf(i))) {
            String valueOf = String.valueOf(set);
            zzcf(new StringBuilder(String.valueOf(valueOf).length() + 90).append("Value cycle detected.  Current value reference: ").append(i).append(".  Previous value references: ").append(valueOf).append(".").toString());
        }
        zzl zzl = (zzl) zza(zzi.zzpj, i, "values");
        if (zzlArr[i] != null) {
            return zzlArr[i];
        }
        zzl zzl2 = null;
        set.add(Integer.valueOf(i));
        switch (zzl.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zzl2 = zzl;
                break;
            case 2:
                zzg.zza zzl3 = zzl(zzl);
                zzl2 = zzk(zzl);
                zzl2.zzqn = new zzl[zzl3.zzpz.length];
                int[] iArr = zzl3.zzpz;
                int length = iArr.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    zzl2.zzqn[i3] = zza(iArr[i2], zzi, zzlArr, set);
                    i2++;
                    i3++;
                }
                break;
            case 3:
                zzl2 = zzk(zzl);
                zzg.zza zzl4 = zzl(zzl);
                if (zzl4.zzqa.length != zzl4.zzqb.length) {
                    zzcf(new StringBuilder(58).append("Uneven map keys (").append(zzl4.zzqa.length).append(") and map values (").append(zzl4.zzqb.length).append(")").toString());
                }
                zzl2.zzqo = new zzl[zzl4.zzqa.length];
                zzl2.zzqp = new zzl[zzl4.zzqa.length];
                int[] iArr2 = zzl4.zzqa;
                int length2 = iArr2.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length2) {
                    zzl2.zzqo[i5] = zza(iArr2[i4], zzi, zzlArr, set);
                    i4++;
                    i5++;
                }
                int[] iArr3 = zzl4.zzqb;
                int length3 = iArr3.length;
                int i6 = 0;
                int i7 = 0;
                while (i6 < length3) {
                    zzl2.zzqp[i7] = zza(iArr3[i6], zzi, zzlArr, set);
                    i6++;
                    i7++;
                }
                break;
            case 4:
                zzl2 = zzk(zzl);
                zzl2.zzqq = zzgj.zzc(zza(zzl(zzl).zzqe, zzi, zzlArr, set));
                break;
            case 7:
                zzl2 = zzk(zzl);
                zzg.zza zzl5 = zzl(zzl);
                zzl2.zzqu = new zzl[zzl5.zzqd.length];
                int[] iArr4 = zzl5.zzqd;
                int length4 = iArr4.length;
                int i8 = 0;
                int i9 = 0;
                while (i8 < length4) {
                    zzl2.zzqu[i9] = zza(iArr4[i8], zzi, zzlArr, set);
                    i8++;
                    i9++;
                }
                break;
        }
        if (zzl2 == null) {
            String valueOf2 = String.valueOf(zzl);
            zzcf(new StringBuilder(String.valueOf(valueOf2).length() + 15).append("Invalid value: ").append(valueOf2).toString());
        }
        zzlArr[i] = zzl2;
        set.remove(Integer.valueOf(i));
        return zzl2;
    }

    private static zzg.zza zzl(zzl zzl) throws zzoz {
        if (((zzg.zza) zzl.zza(zzg.zza.zzpx)) == null) {
            String valueOf = String.valueOf(zzl);
            zzcf(new StringBuilder(String.valueOf(valueOf).length() + 54).append("Expected a ServingValue and didn't get one. Value is: ").append(valueOf).toString());
        }
        return (zzg.zza) zzl.zza(zzg.zza.zzpx);
    }

    private static void zzcf(String str) throws zzoz {
        zzdi.zzav(str);
        throw new zzoz(str);
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzoz {
        if (i < 0 || i >= tArr.length) {
            zzcf(new StringBuilder(String.valueOf(str).length() + 45).append("Index out of bounds detected: ").append(i).append(" in ").append(str).toString());
        }
        return tArr[i];
    }

    private static zzot zza(zzc.zzb zzb, zzi zzi, zzl[] zzlArr, int i) throws zzoz {
        zzou zzml = zzot.zzml();
        for (Integer intValue : zzb.zze()) {
            zzc.zzd zzd = (zzc.zzd) zza(zzi.zzpk, intValue.intValue(), "properties");
            String str = (String) zza(zzi.zzpi, zzd.zzl(), "keys");
            zzl zzl = (zzl) zza(zzlArr, zzd.getValue(), "values");
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzml.zzm(zzl);
            } else {
                zzml.zzb(str, zzl);
            }
        }
        return zzml.zzmm();
    }

    public static void zza(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
