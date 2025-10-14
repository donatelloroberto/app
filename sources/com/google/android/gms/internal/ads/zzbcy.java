package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class zzbcy<T> implements zzbdm<T> {
    private static final Unsafe zzdwf = zzbek.zzagh();
    private final int[] zzdwg;
    private final Object[] zzdwh;
    private final int zzdwi;
    private final int zzdwj;
    private final int zzdwk;
    private final zzbcu zzdwl;
    private final boolean zzdwm;
    private final boolean zzdwn;
    private final boolean zzdwo;
    private final boolean zzdwp;
    private final int[] zzdwq;
    private final int[] zzdwr;
    private final int[] zzdws;
    private final zzbdc zzdwt;
    private final zzbce zzdwu;
    private final zzbee<?, ?> zzdwv;
    private final zzbbd<?> zzdww;
    private final zzbcp zzdwx;

    private zzbcy(int[] iArr, Object[] objArr, int i, int i2, int i3, zzbcu zzbcu, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, zzbdc zzbdc, zzbce zzbce, zzbee<?, ?> zzbee, zzbbd<?> zzbbd, zzbcp zzbcp) {
        this.zzdwg = iArr;
        this.zzdwh = objArr;
        this.zzdwi = i;
        this.zzdwj = i2;
        this.zzdwk = i3;
        this.zzdwn = zzbcu instanceof zzbbo;
        this.zzdwo = z;
        this.zzdwm = zzbbd != null && zzbbd.zzh(zzbcu);
        this.zzdwp = false;
        this.zzdwq = iArr2;
        this.zzdwr = iArr3;
        this.zzdws = iArr4;
        this.zzdwt = zzbdc;
        this.zzdwu = zzbce;
        this.zzdwv = zzbee;
        this.zzdww = zzbbd;
        this.zzdwl = zzbcu;
        this.zzdwx = zzbcp;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzbae zzbae) throws IOException {
        return zzbad.zza(i, bArr, i2, i3, zzz(obj), zzbae);
    }

    private static int zza(zzbdm<?> zzbdm, int i, byte[] bArr, int i2, int i3, zzbbt<?> zzbbt, zzbae zzbae) throws IOException {
        int zza = zza((zzbdm) zzbdm, bArr, i2, i3, zzbae);
        zzbbt.add(zzbae.zzdpn);
        while (zza < i3) {
            int zza2 = zzbad.zza(bArr, zza, zzbae);
            if (i != zzbae.zzdpl) {
                break;
            }
            zza = zza((zzbdm) zzbdm, bArr, zza2, i3, zzbae);
            zzbbt.add(zzbae.zzdpn);
        }
        return zza;
    }

    private static int zza(zzbdm zzbdm, byte[] bArr, int i, int i2, int i3, zzbae zzbae) throws IOException {
        zzbcy zzbcy = (zzbcy) zzbdm;
        Object newInstance = zzbcy.newInstance();
        int zza = zzbcy.zza(newInstance, bArr, i, i2, i3, zzbae);
        zzbcy.zzo(newInstance);
        zzbae.zzdpn = newInstance;
        return zza;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(com.google.android.gms.internal.ads.zzbdm r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.ads.zzbae r11) throws java.io.IOException {
        /*
            int r3 = r9 + 1
            byte r0 = r8[r9]
            if (r0 >= 0) goto L_0x002c
            int r3 = com.google.android.gms.internal.ads.zzbad.zza((int) r0, (byte[]) r8, (int) r3, (com.google.android.gms.internal.ads.zzbae) r11)
            int r0 = r11.zzdpl
            r6 = r0
        L_0x000d:
            if (r6 < 0) goto L_0x0013
            int r0 = r10 - r3
            if (r6 <= r0) goto L_0x0018
        L_0x0013:
            com.google.android.gms.internal.ads.zzbbu r0 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r0
        L_0x0018:
            java.lang.Object r1 = r7.newInstance()
            int r4 = r3 + r6
            r0 = r7
            r2 = r8
            r5 = r11
            r0.zza(r1, r2, r3, r4, r5)
            r7.zzo(r1)
            r11.zzdpn = r1
            int r0 = r3 + r6
            return r0
        L_0x002c:
            r6 = r0
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(com.google.android.gms.internal.ads.zzbdm, byte[], int, int, com.google.android.gms.internal.ads.zzbae):int");
    }

    private static <UT, UB> int zza(zzbee<UT, UB> zzbee, T t) {
        return zzbee.zzy(zzbee.zzac(t));
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzbae zzbae) throws IOException {
        int zza;
        Unsafe unsafe = zzdwf;
        long j2 = (long) (this.zzdwg[i8 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzbad.zzg(bArr, i)));
                    zza = i + 8;
                    break;
                } else {
                    return i;
                }
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzbad.zzh(bArr, i)));
                    zza = i + 4;
                    break;
                } else {
                    return i;
                }
            case 53:
            case 54:
                if (i5 == 0) {
                    zza = zzbad.zzb(bArr, i, zzbae);
                    unsafe.putObject(t, j, Long.valueOf(zzbae.zzdpm));
                    break;
                } else {
                    return i;
                }
            case 55:
            case 62:
                if (i5 == 0) {
                    zza = zzbad.zza(bArr, i, zzbae);
                    unsafe.putObject(t, j, Integer.valueOf(zzbae.zzdpl));
                    break;
                } else {
                    return i;
                }
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzbad.zzf(bArr, i)));
                    zza = i + 8;
                    break;
                } else {
                    return i;
                }
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzbad.zze(bArr, i)));
                    zza = i + 4;
                    break;
                } else {
                    return i;
                }
            case 58:
                if (i5 == 0) {
                    int zzb = zzbad.zzb(bArr, i, zzbae);
                    unsafe.putObject(t, j, Boolean.valueOf(zzbae.zzdpm != 0));
                    zza = zzb;
                    break;
                } else {
                    return i;
                }
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int zza2 = zzbad.zza(bArr, i, zzbae);
                int i9 = zzbae.zzdpl;
                if (i9 == 0) {
                    unsafe.putObject(t, j, "");
                } else if ((536870912 & i6) == 0 || zzbem.zzf(bArr, zza2, zza2 + i9)) {
                    unsafe.putObject(t, j, new String(bArr, zza2, i9, zzbbq.UTF_8));
                    zza2 += i9;
                } else {
                    throw zzbbu.zzads();
                }
                unsafe.putInt(t, j2, i4);
                return zza2;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int zza3 = zza(zzcq(i8), bArr, i, i2, zzbae);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, zzbae.zzdpn);
                } else {
                    unsafe.putObject(t, j, zzbbq.zza(object, zzbae.zzdpn));
                }
                unsafe.putInt(t, j2, i4);
                return zza3;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                int zza4 = zzbad.zza(bArr, i, zzbae);
                int i10 = zzbae.zzdpl;
                if (i10 == 0) {
                    unsafe.putObject(t, j, zzbah.zzdpq);
                } else {
                    unsafe.putObject(t, j, zzbah.zzc(bArr, zza4, i10));
                    zza4 += i10;
                }
                unsafe.putInt(t, j2, i4);
                return zza4;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                zza = zzbad.zza(bArr, i, zzbae);
                int i11 = zzbae.zzdpl;
                zzbbs<?> zzcs = zzcs(i8);
                if (zzcs == null || zzcs.zzq(i11) != null) {
                    unsafe.putObject(t, j, Integer.valueOf(i11));
                    break;
                } else {
                    zzz(t).zzb(i3, Long.valueOf((long) i11));
                    return zza;
                }
            case 66:
                if (i5 == 0) {
                    zza = zzbad.zza(bArr, i, zzbae);
                    unsafe.putObject(t, j, Integer.valueOf(zzbaq.zzbu(zzbae.zzdpl)));
                    break;
                } else {
                    return i;
                }
            case 67:
                if (i5 == 0) {
                    zza = zzbad.zzb(bArr, i, zzbae);
                    unsafe.putObject(t, j, Long.valueOf(zzbaq.zzl(zzbae.zzdpm)));
                    break;
                } else {
                    return i;
                }
            case 68:
                if (i5 == 3) {
                    zza = zza(zzcq(i8), bArr, i, i2, (i3 & -8) | 4, zzbae);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 != null) {
                        unsafe.putObject(t, j, zzbbq.zza(object2, zzbae.zzdpn));
                        break;
                    } else {
                        unsafe.putObject(t, j, zzbae.zzdpn);
                        break;
                    }
                } else {
                    return i;
                }
            default:
                return i;
        }
        unsafe.putInt(t, j2, i4);
        return zza;
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzbae zzbae) throws IOException {
        zzbbt zzbbt;
        int zza;
        int i8;
        int i9;
        int i10;
        zzbbt zzbbt2 = (zzbbt) zzdwf.getObject(t, j2);
        if (!zzbbt2.zzaay()) {
            int size = zzbbt2.size();
            zzbbt = zzbbt2.zzbm(size == 0 ? 10 : size << 1);
            zzdwf.putObject(t, j2, zzbbt);
        } else {
            zzbbt = zzbbt2;
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzbay zzbay = (zzbay) zzbbt;
                    int zza2 = zzbad.zza(bArr, i, zzbae);
                    int i11 = zzbae.zzdpl + zza2;
                    while (zza2 < i11) {
                        zzbay.zzd(zzbad.zzg(bArr, zza2));
                        zza2 += 8;
                    }
                    if (zza2 == i11) {
                        return zza2;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 1) {
                    return i;
                } else {
                    zzbay zzbay2 = (zzbay) zzbbt;
                    zzbay2.zzd(zzbad.zzg(bArr, i));
                    int i12 = i + 8;
                    while (i12 < i2) {
                        int zza3 = zzbad.zza(bArr, i12, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i12;
                        }
                        zzbay2.zzd(zzbad.zzg(bArr, zza3));
                        i12 = zza3 + 8;
                    }
                    return i12;
                }
            case 19:
            case 36:
                if (i5 == 2) {
                    zzbbm zzbbm = (zzbbm) zzbbt;
                    int zza4 = zzbad.zza(bArr, i, zzbae);
                    int i13 = zzbae.zzdpl + zza4;
                    while (zza4 < i13) {
                        zzbbm.zzd(zzbad.zzh(bArr, zza4));
                        zza4 += 4;
                    }
                    if (zza4 == i13) {
                        return zza4;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 5) {
                    return i;
                } else {
                    zzbbm zzbbm2 = (zzbbm) zzbbt;
                    zzbbm2.zzd(zzbad.zzh(bArr, i));
                    int i14 = i + 4;
                    while (i14 < i2) {
                        int zza5 = zzbad.zza(bArr, i14, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i14;
                        }
                        zzbbm2.zzd(zzbad.zzh(bArr, zza5));
                        i14 = zza5 + 4;
                    }
                    return i14;
                }
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzbci zzbci = (zzbci) zzbbt;
                    int zza6 = zzbad.zza(bArr, i, zzbae);
                    int i15 = zzbae.zzdpl + zza6;
                    while (zza6 < i15) {
                        zza6 = zzbad.zzb(bArr, zza6, zzbae);
                        zzbci.zzw(zzbae.zzdpm);
                    }
                    if (zza6 == i15) {
                        return zza6;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzbci zzbci2 = (zzbci) zzbbt;
                    int zzb = zzbad.zzb(bArr, i, zzbae);
                    zzbci2.zzw(zzbae.zzdpm);
                    while (zzb < i2) {
                        int zza7 = zzbad.zza(bArr, zzb, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return zzb;
                        }
                        zzb = zzbad.zzb(bArr, zza7, zzbae);
                        zzbci2.zzw(zzbae.zzdpm);
                    }
                    return zzb;
                }
            case 22:
            case 29:
            case 39:
            case 43:
                return i5 == 2 ? zzbad.zza(bArr, i, (zzbbt<?>) zzbbt, zzbae) : i5 == 0 ? zzbad.zza(i3, bArr, i, i2, (zzbbt<?>) zzbbt, zzbae) : i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzbci zzbci3 = (zzbci) zzbbt;
                    int zza8 = zzbad.zza(bArr, i, zzbae);
                    int i16 = zzbae.zzdpl + zza8;
                    while (zza8 < i16) {
                        zzbci3.zzw(zzbad.zzf(bArr, zza8));
                        zza8 += 8;
                    }
                    if (zza8 == i16) {
                        return zza8;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 1) {
                    return i;
                } else {
                    zzbci zzbci4 = (zzbci) zzbbt;
                    zzbci4.zzw(zzbad.zzf(bArr, i));
                    int i17 = i + 8;
                    while (i17 < i2) {
                        int zza9 = zzbad.zza(bArr, i17, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i17;
                        }
                        zzbci4.zzw(zzbad.zzf(bArr, zza9));
                        i17 = zza9 + 8;
                    }
                    return i17;
                }
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzbbp zzbbp = (zzbbp) zzbbt;
                    int zza10 = zzbad.zza(bArr, i, zzbae);
                    int i18 = zzbae.zzdpl + zza10;
                    while (zza10 < i18) {
                        zzbbp.zzco(zzbad.zze(bArr, zza10));
                        zza10 += 4;
                    }
                    if (zza10 == i18) {
                        return zza10;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 5) {
                    return i;
                } else {
                    zzbbp zzbbp2 = (zzbbp) zzbbt;
                    zzbbp2.zzco(zzbad.zze(bArr, i));
                    int i19 = i + 4;
                    while (i19 < i2) {
                        int zza11 = zzbad.zza(bArr, i19, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i19;
                        }
                        zzbbp2.zzco(zzbad.zze(bArr, zza11));
                        i19 = zza11 + 4;
                    }
                    return i19;
                }
            case 25:
            case 42:
                if (i5 == 2) {
                    zzbaf zzbaf = (zzbaf) zzbbt;
                    int zza12 = zzbad.zza(bArr, i, zzbae);
                    int i20 = zza12 + zzbae.zzdpl;
                    while (zza12 < i20) {
                        zza12 = zzbad.zzb(bArr, zza12, zzbae);
                        zzbaf.addBoolean(zzbae.zzdpm != 0);
                    }
                    if (zza12 == i20) {
                        return zza12;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzbaf zzbaf2 = (zzbaf) zzbbt;
                    int zzb2 = zzbad.zzb(bArr, i, zzbae);
                    zzbaf2.addBoolean(zzbae.zzdpm != 0);
                    while (zzb2 < i2) {
                        int zza13 = zzbad.zza(bArr, zzb2, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return zzb2;
                        }
                        zzb2 = zzbad.zzb(bArr, zza13, zzbae);
                        zzbaf2.addBoolean(zzbae.zzdpm != 0);
                    }
                    return zzb2;
                }
            case 26:
                if (i5 != 2) {
                    return i;
                }
                if ((536870912 & j) == 0) {
                    int zza14 = zzbad.zza(bArr, i, zzbae);
                    int i21 = zzbae.zzdpl;
                    if (i21 == 0) {
                        zzbbt.add("");
                    } else {
                        zzbbt.add(new String(bArr, zza14, i21, zzbbq.UTF_8));
                        zza14 += i21;
                    }
                    while (i10 < i2) {
                        int zza15 = zzbad.zza(bArr, i10, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i10;
                        }
                        i10 = zzbad.zza(bArr, zza15, zzbae);
                        int i22 = zzbae.zzdpl;
                        if (i22 == 0) {
                            zzbbt.add("");
                        } else {
                            zzbbt.add(new String(bArr, i10, i22, zzbbq.UTF_8));
                            i10 += i22;
                        }
                    }
                    return i10;
                }
                int zza16 = zzbad.zza(bArr, i, zzbae);
                int i23 = zzbae.zzdpl;
                if (i23 == 0) {
                    zzbbt.add("");
                } else {
                    if (!zzbem.zzf(bArr, zza16, zza16 + i23)) {
                        throw zzbbu.zzads();
                    }
                    zzbbt.add(new String(bArr, zza16, i23, zzbbq.UTF_8));
                    zza16 += i23;
                }
                while (i9 < i2) {
                    int zza17 = zzbad.zza(bArr, i9, zzbae);
                    if (i3 != zzbae.zzdpl) {
                        return i9;
                    }
                    i9 = zzbad.zza(bArr, zza17, zzbae);
                    int i24 = zzbae.zzdpl;
                    if (i24 == 0) {
                        zzbbt.add("");
                    } else {
                        if (!zzbem.zzf(bArr, i9, i9 + i24)) {
                            throw zzbbu.zzads();
                        }
                        zzbbt.add(new String(bArr, i9, i24, zzbbq.UTF_8));
                        i9 += i24;
                    }
                }
                return i9;
            case 27:
                return i5 == 2 ? zza((zzbdm<?>) zzcq(i6), i3, bArr, i, i2, (zzbbt<?>) zzbbt, zzbae) : i;
            case 28:
                if (i5 != 2) {
                    return i;
                }
                int zza18 = zzbad.zza(bArr, i, zzbae);
                int i25 = zzbae.zzdpl;
                if (i25 == 0) {
                    zzbbt.add(zzbah.zzdpq);
                } else {
                    zzbbt.add(zzbah.zzc(bArr, zza18, i25));
                    zza18 += i25;
                }
                while (i8 < i2) {
                    int zza19 = zzbad.zza(bArr, i8, zzbae);
                    if (i3 != zzbae.zzdpl) {
                        return i8;
                    }
                    i8 = zzbad.zza(bArr, zza19, zzbae);
                    int i26 = zzbae.zzdpl;
                    if (i26 == 0) {
                        zzbbt.add(zzbah.zzdpq);
                    } else {
                        zzbbt.add(zzbah.zzc(bArr, i8, i26));
                        i8 += i26;
                    }
                }
                return i8;
            case 30:
            case 44:
                if (i5 == 2) {
                    zza = zzbad.zza(bArr, i, (zzbbt<?>) zzbbt, zzbae);
                } else if (i5 != 0) {
                    return i;
                } else {
                    zza = zzbad.zza(i3, bArr, i, i2, (zzbbt<?>) zzbbt, zzbae);
                }
                zzbef zzbef = ((zzbbo) t).zzdtt;
                if (zzbef == zzbef.zzagc()) {
                    zzbef = null;
                }
                zzbef zzbef2 = (zzbef) zzbdo.zza(i4, zzbbt, zzcs(i6), zzbef, this.zzdwv);
                if (zzbef2 == null) {
                    return zza;
                }
                ((zzbbo) t).zzdtt = zzbef2;
                return zza;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzbbp zzbbp3 = (zzbbp) zzbbt;
                    int zza20 = zzbad.zza(bArr, i, zzbae);
                    int i27 = zzbae.zzdpl + zza20;
                    while (zza20 < i27) {
                        zza20 = zzbad.zza(bArr, zza20, zzbae);
                        zzbbp3.zzco(zzbaq.zzbu(zzbae.zzdpl));
                    }
                    if (zza20 == i27) {
                        return zza20;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzbbp zzbbp4 = (zzbbp) zzbbt;
                    int zza21 = zzbad.zza(bArr, i, zzbae);
                    zzbbp4.zzco(zzbaq.zzbu(zzbae.zzdpl));
                    while (zza21 < i2) {
                        int zza22 = zzbad.zza(bArr, zza21, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return zza21;
                        }
                        zza21 = zzbad.zza(bArr, zza22, zzbae);
                        zzbbp4.zzco(zzbaq.zzbu(zzbae.zzdpl));
                    }
                    return zza21;
                }
            case 34:
            case 48:
                if (i5 == 2) {
                    zzbci zzbci5 = (zzbci) zzbbt;
                    int zza23 = zzbad.zza(bArr, i, zzbae);
                    int i28 = zzbae.zzdpl + zza23;
                    while (zza23 < i28) {
                        zza23 = zzbad.zzb(bArr, zza23, zzbae);
                        zzbci5.zzw(zzbaq.zzl(zzbae.zzdpm));
                    }
                    if (zza23 == i28) {
                        return zza23;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzbci zzbci6 = (zzbci) zzbbt;
                    int zzb3 = zzbad.zzb(bArr, i, zzbae);
                    zzbci6.zzw(zzbaq.zzl(zzbae.zzdpm));
                    while (zzb3 < i2) {
                        int zza24 = zzbad.zza(bArr, zzb3, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return zzb3;
                        }
                        zzb3 = zzbad.zzb(bArr, zza24, zzbae);
                        zzbci6.zzw(zzbaq.zzl(zzbae.zzdpm));
                    }
                    return zzb3;
                }
            case 49:
                if (i5 != 3) {
                    return i;
                }
                zzbdm zzcq = zzcq(i6);
                int i29 = (i3 & -8) | 4;
                int zza25 = zza(zzcq, bArr, i, i2, i29, zzbae);
                zzbbt.add(zzbae.zzdpn);
                while (zza25 < i2) {
                    int zza26 = zzbad.zza(bArr, zza25, zzbae);
                    if (i3 != zzbae.zzdpl) {
                        return zza25;
                    }
                    zza25 = zza(zzcq, bArr, zza26, i2, i29, zzbae);
                    zzbbt.add(zzbae.zzdpn);
                }
                return zza25;
            default:
                return i;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: byte} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r14, byte[] r15, int r16, int r17, int r18, int r19, long r20, com.google.android.gms.internal.ads.zzbae r22) throws java.io.IOException {
        /*
            r13 = this;
            sun.misc.Unsafe r4 = zzdwf
            r0 = r18
            java.lang.Object r5 = r13.zzcr(r0)
            r0 = r20
            java.lang.Object r3 = r4.getObject(r14, r0)
            com.google.android.gms.internal.ads.zzbcp r2 = r13.zzdwx
            boolean r2 = r2.zzu(r3)
            if (r2 == 0) goto L_0x00ba
            com.google.android.gms.internal.ads.zzbcp r2 = r13.zzdwx
            java.lang.Object r2 = r2.zzw(r5)
            com.google.android.gms.internal.ads.zzbcp r6 = r13.zzdwx
            r6.zzb(r2, r3)
            r0 = r20
            r4.putObject(r14, r0, r2)
        L_0x0026:
            com.google.android.gms.internal.ads.zzbcp r3 = r13.zzdwx
            com.google.android.gms.internal.ads.zzbcn r10 = r3.zzx(r5)
            com.google.android.gms.internal.ads.zzbcp r3 = r13.zzdwx
            java.util.Map r11 = r3.zzs(r2)
            r0 = r16
            r1 = r22
            int r4 = com.google.android.gms.internal.ads.zzbad.zza(r15, r0, r1)
            r0 = r22
            int r2 = r0.zzdpl
            if (r2 < 0) goto L_0x0044
            int r3 = r17 - r4
            if (r2 <= r3) goto L_0x0049
        L_0x0044:
            com.google.android.gms.internal.ads.zzbbu r2 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r2
        L_0x0049:
            int r12 = r4 + r2
            K r3 = r10.zzdvz
            V r2 = r10.zzdwb
            r8 = r2
            r9 = r3
        L_0x0051:
            if (r4 >= r12) goto L_0x00af
            int r3 = r4 + 1
            byte r2 = r15[r4]
            if (r2 >= 0) goto L_0x0063
            r0 = r22
            int r3 = com.google.android.gms.internal.ads.zzbad.zza((int) r2, (byte[]) r15, (int) r3, (com.google.android.gms.internal.ads.zzbae) r0)
            r0 = r22
            int r2 = r0.zzdpl
        L_0x0063:
            int r4 = r2 >>> 3
            r5 = r2 & 7
            switch(r4) {
                case 1: goto L_0x0074;
                case 2: goto L_0x008f;
                default: goto L_0x006a;
            }
        L_0x006a:
            r0 = r17
            r1 = r22
            int r2 = com.google.android.gms.internal.ads.zzbad.zza(r2, r15, r3, r0, r1)
            r4 = r2
            goto L_0x0051
        L_0x0074:
            com.google.android.gms.internal.ads.zzbes r4 = r10.zzdvy
            int r4 = r4.zzagm()
            if (r5 != r4) goto L_0x006a
            com.google.android.gms.internal.ads.zzbes r5 = r10.zzdvy
            r6 = 0
            r2 = r15
            r4 = r17
            r7 = r22
            int r3 = zza((byte[]) r2, (int) r3, (int) r4, (com.google.android.gms.internal.ads.zzbes) r5, (java.lang.Class<?>) r6, (com.google.android.gms.internal.ads.zzbae) r7)
            r0 = r22
            java.lang.Object r2 = r0.zzdpn
            r9 = r2
            r4 = r3
            goto L_0x0051
        L_0x008f:
            com.google.android.gms.internal.ads.zzbes r4 = r10.zzdwa
            int r4 = r4.zzagm()
            if (r5 != r4) goto L_0x006a
            com.google.android.gms.internal.ads.zzbes r5 = r10.zzdwa
            V r2 = r10.zzdwb
            java.lang.Class r6 = r2.getClass()
            r2 = r15
            r4 = r17
            r7 = r22
            int r3 = zza((byte[]) r2, (int) r3, (int) r4, (com.google.android.gms.internal.ads.zzbes) r5, (java.lang.Class<?>) r6, (com.google.android.gms.internal.ads.zzbae) r7)
            r0 = r22
            java.lang.Object r2 = r0.zzdpn
            r8 = r2
            r4 = r3
            goto L_0x0051
        L_0x00af:
            if (r4 == r12) goto L_0x00b6
            com.google.android.gms.internal.ads.zzbbu r2 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r2
        L_0x00b6:
            r11.put(r9, r8)
            return r12
        L_0x00ba:
            r2 = r3
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(java.lang.Object, byte[], int, int, int, int, long, com.google.android.gms.internal.ads.zzbae):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v47, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r17v3, types: [byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008d A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r41, byte[] r42, int r43, int r44, int r45, com.google.android.gms.internal.ads.zzbae r46) throws java.io.IOException {
        /*
            r40 = this;
            sun.misc.Unsafe r4 = zzdwf
            r39 = -1
            r38 = 0
            r17 = 0
            r19 = r43
        L_0x000a:
            r0 = r19
            r1 = r44
            if (r0 >= r1) goto L_0x032f
            int r10 = r19 + 1
            byte r17 = r42[r19]
            if (r17 >= 0) goto L_0x0026
            r0 = r17
            r1 = r42
            r2 = r46
            int r10 = com.google.android.gms.internal.ads.zzbad.zza((int) r0, (byte[]) r1, (int) r10, (com.google.android.gms.internal.ads.zzbae) r2)
            r0 = r46
            int r0 = r0.zzdpl
            r17 = r0
        L_0x0026:
            int r18 = r17 >>> 3
            r19 = r17 & 7
            r0 = r40
            r1 = r18
            int r20 = r0.zzcw(r1)
            r5 = -1
            r0 = r20
            if (r0 == r5) goto L_0x038a
            r0 = r40
            int[] r5 = r0.zzdwg
            int r6 = r20 + 1
            r32 = r5[r6]
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r5 = r5 & r32
            int r23 = r5 >>> 20
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r32
            long r6 = (long) r5
            r5 = 17
            r0 = r23
            if (r0 > r5) goto L_0x0275
            r0 = r40
            int[] r5 = r0.zzdwg
            int r8 = r20 + 2
            r5 = r5[r8]
            r8 = 1
            int r9 = r5 >>> 20
            int r14 = r8 << r9
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r8
            r0 = r39
            if (r5 == r0) goto L_0x007e
            r8 = -1
            r0 = r39
            if (r0 == r8) goto L_0x0075
            r0 = r39
            long r8 = (long) r0
            r0 = r41
            r1 = r38
            r4.putInt(r0, r8, r1)
        L_0x0075:
            long r8 = (long) r5
            r0 = r41
            int r38 = r4.getInt(r0, r8)
            r39 = r5
        L_0x007e:
            switch(r23) {
                case 0: goto L_0x00a1;
                case 1: goto L_0x00b7;
                case 2: goto L_0x00cd;
                case 3: goto L_0x00cd;
                case 4: goto L_0x00e4;
                case 5: goto L_0x00fb;
                case 6: goto L_0x0111;
                case 7: goto L_0x0127;
                case 8: goto L_0x0147;
                case 9: goto L_0x0172;
                case 10: goto L_0x01ae;
                case 11: goto L_0x00e4;
                case 12: goto L_0x01c8;
                case 13: goto L_0x0111;
                case 14: goto L_0x00fb;
                case 15: goto L_0x01ff;
                case 16: goto L_0x021a;
                case 17: goto L_0x0235;
                default: goto L_0x0081;
            }
        L_0x0081:
            r5 = r38
            r6 = r39
            r19 = r10
        L_0x0087:
            r0 = r17
            r1 = r45
            if (r0 != r1) goto L_0x008f
            if (r45 != 0) goto L_0x0333
        L_0x008f:
            r18 = r42
            r20 = r44
            r21 = r41
            r22 = r46
            int r19 = zza((int) r17, (byte[]) r18, (int) r19, (int) r20, (java.lang.Object) r21, (com.google.android.gms.internal.ads.zzbae) r22)
            r38 = r5
            r39 = r6
            goto L_0x000a
        L_0x00a1:
            r5 = 1
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            double r8 = com.google.android.gms.internal.ads.zzbad.zzg(r0, r10)
            r0 = r41
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r0, (long) r6, (double) r8)
            int r19 = r10 + 8
            r38 = r38 | r14
            goto L_0x000a
        L_0x00b7:
            r5 = 5
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            float r5 = com.google.android.gms.internal.ads.zzbad.zzh(r0, r10)
            r0 = r41
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r0, (long) r6, (float) r5)
            int r19 = r10 + 4
            r38 = r38 | r14
            goto L_0x000a
        L_0x00cd:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.ads.zzbad.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzdpm
            r5 = r41
            r4.putLong(r5, r6, r8)
            r38 = r38 | r14
            goto L_0x000a
        L_0x00e4:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.ads.zzbad.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zzdpl
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x00fb:
            r5 = 1
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            long r8 = com.google.android.gms.internal.ads.zzbad.zzf(r0, r10)
            r5 = r41
            r4.putLong(r5, r6, r8)
            int r19 = r10 + 8
            r38 = r38 | r14
            goto L_0x000a
        L_0x0111:
            r5 = 5
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            int r5 = com.google.android.gms.internal.ads.zzbad.zze(r0, r10)
            r0 = r41
            r4.putInt(r0, r6, r5)
            int r19 = r10 + 4
            r38 = r38 | r14
            goto L_0x000a
        L_0x0127:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.ads.zzbad.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzdpm
            r10 = 0
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x0145
            r5 = 1
        L_0x013c:
            r0 = r41
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r0, (long) r6, (boolean) r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x0145:
            r5 = 0
            goto L_0x013c
        L_0x0147:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r5 & r32
            if (r5 != 0) goto L_0x0169
            r0 = r42
            r1 = r46
            int r5 = com.google.android.gms.internal.ads.zzbad.zzc(r0, r10, r1)
        L_0x015a:
            r0 = r46
            java.lang.Object r8 = r0.zzdpn
            r0 = r41
            r4.putObject(r0, r6, r8)
            r38 = r38 | r14
            r19 = r5
            goto L_0x000a
        L_0x0169:
            r0 = r42
            r1 = r46
            int r5 = com.google.android.gms.internal.ads.zzbad.zzd(r0, r10, r1)
            goto L_0x015a
        L_0x0172:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.ads.zzbdm r5 = r0.zzcq(r1)
            r0 = r42
            r1 = r44
            r2 = r46
            int r19 = zza((com.google.android.gms.internal.ads.zzbdm) r5, (byte[]) r0, (int) r10, (int) r1, (com.google.android.gms.internal.ads.zzbae) r2)
            r5 = r38 & r14
            if (r5 != 0) goto L_0x019a
            r0 = r46
            java.lang.Object r5 = r0.zzdpn
            r0 = r41
            r4.putObject(r0, r6, r5)
        L_0x0196:
            r38 = r38 | r14
            goto L_0x000a
        L_0x019a:
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            r0 = r46
            java.lang.Object r8 = r0.zzdpn
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbbq.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r41
            r4.putObject(r0, r6, r5)
            goto L_0x0196
        L_0x01ae:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.ads.zzbad.zze(r0, r10, r1)
            r0 = r46
            java.lang.Object r5 = r0.zzdpn
            r0 = r41
            r4.putObject(r0, r6, r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x01c8:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.ads.zzbad.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zzdpl
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.ads.zzbbs r8 = r0.zzcs(r1)
            if (r8 == 0) goto L_0x01e6
            com.google.android.gms.internal.ads.zzbbr r8 = r8.zzq(r5)
            if (r8 == 0) goto L_0x01ef
        L_0x01e6:
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x01ef:
            com.google.android.gms.internal.ads.zzbef r6 = zzz(r41)
            long r8 = (long) r5
            java.lang.Long r5 = java.lang.Long.valueOf(r8)
            r0 = r17
            r6.zzb(r0, r5)
            goto L_0x000a
        L_0x01ff:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.ads.zzbad.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zzdpl
            int r5 = com.google.android.gms.internal.ads.zzbaq.zzbu(r5)
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x021a:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.ads.zzbad.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzdpm
            long r8 = com.google.android.gms.internal.ads.zzbaq.zzl(r8)
            r5 = r41
            r4.putLong(r5, r6, r8)
            r38 = r38 | r14
            goto L_0x000a
        L_0x0235:
            r5 = 3
            r0 = r19
            if (r0 != r5) goto L_0x0081
            int r5 = r18 << 3
            r12 = r5 | 4
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.ads.zzbdm r8 = r0.zzcq(r1)
            r9 = r42
            r11 = r44
            r13 = r46
            int r19 = zza((com.google.android.gms.internal.ads.zzbdm) r8, (byte[]) r9, (int) r10, (int) r11, (int) r12, (com.google.android.gms.internal.ads.zzbae) r13)
            r5 = r38 & r14
            if (r5 != 0) goto L_0x0261
            r0 = r46
            java.lang.Object r5 = r0.zzdpn
            r0 = r41
            r4.putObject(r0, r6, r5)
        L_0x025d:
            r38 = r38 | r14
            goto L_0x000a
        L_0x0261:
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            r0 = r46
            java.lang.Object r8 = r0.zzdpn
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbbq.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r41
            r4.putObject(r0, r6, r5)
            goto L_0x025d
        L_0x0275:
            r5 = 27
            r0 = r23
            if (r0 != r5) goto L_0x02b8
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x038a
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            com.google.android.gms.internal.ads.zzbbt r5 = (com.google.android.gms.internal.ads.zzbbt) r5
            boolean r8 = r5.zzaay()
            if (r8 != 0) goto L_0x0392
            int r8 = r5.size()
            if (r8 != 0) goto L_0x02b5
            r8 = 10
        L_0x0296:
            com.google.android.gms.internal.ads.zzbbt r12 = r5.zzbm(r8)
            r0 = r41
            r4.putObject(r0, r6, r12)
        L_0x029f:
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.ads.zzbdm r7 = r0.zzcq(r1)
            r8 = r17
            r9 = r42
            r11 = r44
            r13 = r46
            int r19 = zza((com.google.android.gms.internal.ads.zzbdm<?>) r7, (int) r8, (byte[]) r9, (int) r10, (int) r11, (com.google.android.gms.internal.ads.zzbbt<?>) r12, (com.google.android.gms.internal.ads.zzbae) r13)
            goto L_0x000a
        L_0x02b5:
            int r8 = r8 << 1
            goto L_0x0296
        L_0x02b8:
            r5 = 49
            r0 = r23
            if (r0 > r5) goto L_0x02de
            r0 = r32
            long r0 = (long) r0
            r21 = r0
            r12 = r40
            r13 = r41
            r14 = r42
            r15 = r10
            r16 = r44
            r24 = r6
            r26 = r46
            int r19 = r12.zza(r13, (byte[]) r14, (int) r15, (int) r16, (int) r17, (int) r18, (int) r19, (int) r20, (long) r21, (int) r23, (long) r24, (com.google.android.gms.internal.ads.zzbae) r26)
            r0 = r19
            if (r0 != r10) goto L_0x000a
            r5 = r38
            r6 = r39
            goto L_0x0087
        L_0x02de:
            r5 = 50
            r0 = r23
            if (r0 != r5) goto L_0x0309
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x038a
            r21 = r40
            r22 = r41
            r23 = r42
            r24 = r10
            r25 = r44
            r26 = r20
            r27 = r18
            r28 = r6
            r30 = r46
            int r19 = r21.zza(r22, r23, r24, r25, r26, r27, r28, r30)
            r0 = r19
            if (r0 != r10) goto L_0x000a
            r5 = r38
            r6 = r39
            goto L_0x0087
        L_0x0309:
            r24 = r40
            r25 = r41
            r26 = r42
            r27 = r10
            r28 = r44
            r29 = r17
            r30 = r18
            r31 = r19
            r33 = r23
            r34 = r6
            r36 = r20
            r37 = r46
            int r19 = r24.zza(r25, (byte[]) r26, (int) r27, (int) r28, (int) r29, (int) r30, (int) r31, (int) r32, (int) r33, (long) r34, (int) r36, (com.google.android.gms.internal.ads.zzbae) r37)
            r0 = r19
            if (r0 != r10) goto L_0x000a
            r5 = r38
            r6 = r39
            goto L_0x0087
        L_0x032f:
            r5 = r38
            r6 = r39
        L_0x0333:
            r7 = -1
            if (r6 == r7) goto L_0x033c
            long r6 = (long) r6
            r0 = r41
            r4.putInt(r0, r6, r5)
        L_0x033c:
            r0 = r40
            int[] r4 = r0.zzdwr
            if (r4 == 0) goto L_0x036b
            r6 = 0
            r0 = r40
            int[] r7 = r0.zzdwr
            int r8 = r7.length
            r4 = 0
            r5 = r4
        L_0x034a:
            if (r5 >= r8) goto L_0x0360
            r4 = r7[r5]
            r0 = r40
            com.google.android.gms.internal.ads.zzbee<?, ?> r9 = r0.zzdwv
            r0 = r40
            r1 = r41
            java.lang.Object r4 = r0.zza((java.lang.Object) r1, (int) r4, r6, r9)
            com.google.android.gms.internal.ads.zzbef r4 = (com.google.android.gms.internal.ads.zzbef) r4
            int r5 = r5 + 1
            r6 = r4
            goto L_0x034a
        L_0x0360:
            if (r6 == 0) goto L_0x036b
            r0 = r40
            com.google.android.gms.internal.ads.zzbee<?, ?> r4 = r0.zzdwv
            r0 = r41
            r4.zzf(r0, r6)
        L_0x036b:
            if (r45 != 0) goto L_0x0378
            r0 = r19
            r1 = r44
            if (r0 == r1) goto L_0x0389
            com.google.android.gms.internal.ads.zzbbu r4 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r4
        L_0x0378:
            r0 = r19
            r1 = r44
            if (r0 > r1) goto L_0x0384
            r0 = r17
            r1 = r45
            if (r0 == r1) goto L_0x0389
        L_0x0384:
            com.google.android.gms.internal.ads.zzbbu r4 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r4
        L_0x0389:
            return r19
        L_0x038a:
            r5 = r38
            r6 = r39
            r19 = r10
            goto L_0x0087
        L_0x0392:
            r12 = r5
            goto L_0x029f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.ads.zzbae):int");
    }

    private static int zza(byte[] bArr, int i, int i2, zzbes zzbes, Class<?> cls, zzbae zzbae) throws IOException {
        switch (zzbcz.zzdql[zzbes.ordinal()]) {
            case 1:
                int zzb = zzbad.zzb(bArr, i, zzbae);
                zzbae.zzdpn = Boolean.valueOf(zzbae.zzdpm != 0);
                return zzb;
            case 2:
                return zzbad.zze(bArr, i, zzbae);
            case 3:
                zzbae.zzdpn = Double.valueOf(zzbad.zzg(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzbae.zzdpn = Integer.valueOf(zzbad.zze(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzbae.zzdpn = Long.valueOf(zzbad.zzf(bArr, i));
                return i + 8;
            case 8:
                zzbae.zzdpn = Float.valueOf(zzbad.zzh(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zza = zzbad.zza(bArr, i, zzbae);
                zzbae.zzdpn = Integer.valueOf(zzbae.zzdpl);
                return zza;
            case 12:
            case 13:
                int zzb2 = zzbad.zzb(bArr, i, zzbae);
                zzbae.zzdpn = Long.valueOf(zzbae.zzdpm);
                return zzb2;
            case 14:
                return zza((zzbdm) zzbdg.zzaeo().zze(cls), bArr, i, i2, zzbae);
            case 15:
                int zza2 = zzbad.zza(bArr, i, zzbae);
                zzbae.zzdpn = Integer.valueOf(zzbaq.zzbu(zzbae.zzdpl));
                return zza2;
            case 16:
                int zzb3 = zzbad.zzb(bArr, i, zzbae);
                zzbae.zzdpn = Long.valueOf(zzbaq.zzl(zzbae.zzdpm));
                return zzb3;
            case 17:
                return zzbad.zzd(bArr, i, zzbae);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    static <T> zzbcy<T> zza(Class<T> cls, zzbcs zzbcs, zzbdc zzbdc, zzbce zzbce, zzbee<?, ?> zzbee, zzbbd<?> zzbbd, zzbcp zzbcp) {
        int zzaer;
        int zzaes;
        int zzaew;
        int zza;
        int i;
        int i2;
        if (zzbcs instanceof zzbdi) {
            zzbdi zzbdi = (zzbdi) zzbcs;
            boolean z = zzbdi.zzaeh() == zzbbo.zze.zzduj;
            if (zzbdi.getFieldCount() == 0) {
                zzaer = 0;
                zzaes = 0;
                zzaew = 0;
            } else {
                zzaer = zzbdi.zzaer();
                zzaes = zzbdi.zzaes();
                zzaew = zzbdi.zzaew();
            }
            int[] iArr = new int[(zzaew << 2)];
            Object[] objArr = new Object[(zzaew << 1)];
            int[] iArr2 = zzbdi.zzaet() > 0 ? new int[zzbdi.zzaet()] : null;
            int[] iArr3 = zzbdi.zzaeu() > 0 ? new int[zzbdi.zzaeu()] : null;
            int i3 = 0;
            int i4 = 0;
            zzbdj zzaeq = zzbdi.zzaeq();
            if (zzaeq.next()) {
                int zzaci = zzaeq.zzaci();
                int i5 = 0;
                while (true) {
                    if (zzaci >= zzbdi.zzaex() || i5 >= ((zzaci - zzaer) << 2)) {
                        if (zzaeq.zzafb()) {
                            zza = (int) zzbek.zza(zzaeq.zzafc());
                            i = (int) zzbek.zza(zzaeq.zzafd());
                            i2 = 0;
                        } else {
                            zza = (int) zzbek.zza(zzaeq.zzafe());
                            if (zzaeq.zzaff()) {
                                i = (int) zzbek.zza(zzaeq.zzafg());
                                i2 = zzaeq.zzafh();
                            } else {
                                i = 0;
                                i2 = 0;
                            }
                        }
                        iArr[i5] = zzaeq.zzaci();
                        iArr[i5 + 1] = zza | (zzaeq.zzafj() ? 536870912 : 0) | (zzaeq.zzafi() ? 268435456 : 0) | (zzaeq.zzaez() << 20);
                        iArr[i5 + 2] = i | (i2 << 20);
                        if (zzaeq.zzafm() != null) {
                            objArr[(i5 / 4) << 1] = zzaeq.zzafm();
                            if (zzaeq.zzafk() != null) {
                                objArr[((i5 / 4) << 1) + 1] = zzaeq.zzafk();
                            } else if (zzaeq.zzafl() != null) {
                                objArr[((i5 / 4) << 1) + 1] = zzaeq.zzafl();
                            }
                        } else if (zzaeq.zzafk() != null) {
                            objArr[((i5 / 4) << 1) + 1] = zzaeq.zzafk();
                        } else if (zzaeq.zzafl() != null) {
                            objArr[((i5 / 4) << 1) + 1] = zzaeq.zzafl();
                        }
                        int zzaez = zzaeq.zzaez();
                        if (zzaez == zzbbj.MAP.ordinal()) {
                            iArr2[i3] = i5;
                            i3++;
                        } else if (zzaez >= 18 && zzaez <= 49) {
                            iArr3[i4] = iArr[i5 + 1] & 1048575;
                            i4++;
                        }
                        if (!zzaeq.next()) {
                            break;
                        }
                        zzaci = zzaeq.zzaci();
                    } else {
                        for (int i6 = 0; i6 < 4; i6++) {
                            iArr[i5 + i6] = -1;
                        }
                    }
                    i5 += 4;
                }
            }
            return new zzbcy<>(iArr, objArr, zzaer, zzaes, zzbdi.zzaex(), zzbdi.zzaej(), z, false, zzbdi.zzaev(), iArr2, iArr3, zzbdc, zzbce, zzbee, zzbbd, zzbcp);
        }
        ((zzbdz) zzbcs).zzaeh();
        throw new NoSuchMethodError();
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzbbs<?> zzbbs, UB ub, zzbee<UT, UB> zzbee) {
        zzbcn<?, ?> zzx = this.zzdwx.zzx(zzcr(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (zzbbs.zzq(((Integer) next.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = zzbee.zzagb();
                }
                zzbam zzbo = zzbah.zzbo(zzbcm.zza(zzx, next.getKey(), next.getValue()));
                try {
                    zzbcm.zza(zzbo.zzabj(), zzx, next.getKey(), next.getValue());
                    zzbee.zza(ub, i2, zzbo.zzabi());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzbee<UT, UB> zzbee) {
        zzbbs<?> zzcs;
        int i2 = this.zzdwg[i];
        Object zzp = zzbek.zzp(obj, (long) (zzct(i) & 1048575));
        if (zzp == null || (zzcs = zzcs(i)) == null) {
            return ub;
        }
        return zza(i, i2, this.zzdwx.zzs(zzp), zzcs, ub, zzbee);
    }

    private static void zza(int i, Object obj, zzbey zzbey) throws IOException {
        if (obj instanceof String) {
            zzbey.zzf(i, (String) obj);
        } else {
            zzbey.zza(i, (zzbah) obj);
        }
    }

    private static <UT, UB> void zza(zzbee<UT, UB> zzbee, T t, zzbey zzbey) throws IOException {
        zzbee.zza(zzbee.zzac(t), zzbey);
    }

    private final <K, V> void zza(zzbey zzbey, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzbey.zza(i, this.zzdwx.zzx(zzcr(i2)), this.zzdwx.zzt(obj));
        }
    }

    private final void zza(Object obj, int i, zzbdl zzbdl) throws IOException {
        if (zzcv(i)) {
            zzbek.zza(obj, (long) (i & 1048575), (Object) zzbdl.zzabr());
        } else if (this.zzdwn) {
            zzbek.zza(obj, (long) (i & 1048575), (Object) zzbdl.readString());
        } else {
            zzbek.zza(obj, (long) (i & 1048575), (Object) zzbdl.zzabs());
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzct = (long) (zzct(i) & 1048575);
        if (zza(t2, i)) {
            Object zzp = zzbek.zzp(t, zzct);
            Object zzp2 = zzbek.zzp(t2, zzct);
            if (zzp != null && zzp2 != null) {
                zzbek.zza((Object) t, zzct, zzbbq.zza(zzp, zzp2));
                zzb(t, i);
            } else if (zzp2 != null) {
                zzbek.zza((Object) t, zzct, zzp2);
                zzb(t, i);
            }
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzdwo) {
            int zzct = zzct(i);
            long j = (long) (zzct & 1048575);
            switch ((zzct & 267386880) >>> 20) {
                case 0:
                    return zzbek.zzo(t, j) != 0.0d;
                case 1:
                    return zzbek.zzn(t, j) != 0.0f;
                case 2:
                    return zzbek.zzl(t, j) != 0;
                case 3:
                    return zzbek.zzl(t, j) != 0;
                case 4:
                    return zzbek.zzk(t, j) != 0;
                case 5:
                    return zzbek.zzl(t, j) != 0;
                case 6:
                    return zzbek.zzk(t, j) != 0;
                case 7:
                    return zzbek.zzm(t, j);
                case 8:
                    Object zzp = zzbek.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzbah) {
                        return !zzbah.zzdpq.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzbek.zzp(t, j) != null;
                case 10:
                    return !zzbah.zzdpq.equals(zzbek.zzp(t, j));
                case 11:
                    return zzbek.zzk(t, j) != 0;
                case 12:
                    return zzbek.zzk(t, j) != 0;
                case 13:
                    return zzbek.zzk(t, j) != 0;
                case 14:
                    return zzbek.zzl(t, j) != 0;
                case 15:
                    return zzbek.zzk(t, j) != 0;
                case 16:
                    return zzbek.zzl(t, j) != 0;
                case 17:
                    return zzbek.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzcu = zzcu(i);
            return (zzbek.zzk(t, (long) (zzcu & 1048575)) & (1 << (zzcu >>> 20))) != 0;
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzbek.zzk(t, (long) (zzcu(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzdwo ? zza(t, i) : (i2 & i3) != 0;
    }

    private static boolean zza(Object obj, int i, zzbdm zzbdm) {
        return zzbdm.zzaa(zzbek.zzp(obj, (long) (1048575 & i)));
    }

    private final void zzb(T t, int i) {
        if (!this.zzdwo) {
            int zzcu = zzcu(i);
            long j = (long) (zzcu & 1048575);
            zzbek.zzb((Object) t, j, zzbek.zzk(t, j) | (1 << (zzcu >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzbek.zzb((Object) t, (long) (zzcu(i2) & 1048575), i);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 387 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r21, com.google.android.gms.internal.ads.zzbey r22) throws java.io.IOException {
        /*
            r20 = this;
            r5 = 0
            r4 = 0
            r0 = r20
            boolean r6 = r0.zzdwm
            if (r6 == 0) goto L_0x0022
            r0 = r20
            com.google.android.gms.internal.ads.zzbbd<?> r6 = r0.zzdww
            r0 = r21
            com.google.android.gms.internal.ads.zzbbg r6 = r6.zzm(r0)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x0022
            java.util.Iterator r5 = r6.iterator()
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
        L_0x0022:
            r8 = -1
            r6 = 0
            r0 = r20
            int[] r7 = r0.zzdwg
            int r13 = r7.length
            sun.misc.Unsafe r14 = zzdwf
            r7 = 0
            r12 = r7
            r9 = r4
        L_0x002e:
            if (r12 >= r13) goto L_0x06fb
            r0 = r20
            int r15 = r0.zzct(r12)
            r0 = r20
            int[] r4 = r0.zzdwg
            r16 = r4[r12]
            r4 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r15
            int r17 = r4 >>> 20
            r4 = 0
            r0 = r20
            boolean r7 = r0.zzdwo
            if (r7 != 0) goto L_0x06f7
            r7 = 17
            r0 = r17
            if (r0 > r7) goto L_0x06f7
            r0 = r20
            int[] r4 = r0.zzdwg
            int r7 = r12 + 2
            r10 = r4[r7]
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r10 & r4
            if (r7 == r8) goto L_0x06f3
            long r0 = (long) r7
            r18 = r0
            r0 = r21
            r1 = r18
            int r4 = r14.getInt(r0, r1)
        L_0x0068:
            r6 = 1
            int r8 = r10 >>> 20
            int r6 = r6 << r8
            r10 = r6
            r11 = r4
            r8 = r7
        L_0x006f:
            if (r9 == 0) goto L_0x0096
            r0 = r20
            com.google.android.gms.internal.ads.zzbbd<?> r4 = r0.zzdww
            int r4 = r4.zza(r9)
            r0 = r16
            if (r4 > r0) goto L_0x0096
            r0 = r20
            com.google.android.gms.internal.ads.zzbbd<?> r4 = r0.zzdww
            r0 = r22
            r4.zza((com.google.android.gms.internal.ads.zzbey) r0, (java.util.Map.Entry<?, ?>) r9)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x0094
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
        L_0x0092:
            r9 = r4
            goto L_0x006f
        L_0x0094:
            r4 = 0
            goto L_0x0092
        L_0x0096:
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r15
            long r6 = (long) r4
            switch(r17) {
                case 0: goto L_0x00a3;
                case 1: goto L_0x00b5;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00d9;
                case 4: goto L_0x00eb;
                case 5: goto L_0x00fd;
                case 6: goto L_0x010f;
                case 7: goto L_0x0122;
                case 8: goto L_0x0135;
                case 9: goto L_0x0148;
                case 10: goto L_0x0161;
                case 11: goto L_0x0176;
                case 12: goto L_0x0189;
                case 13: goto L_0x019c;
                case 14: goto L_0x01af;
                case 15: goto L_0x01c2;
                case 16: goto L_0x01d5;
                case 17: goto L_0x01e8;
                case 18: goto L_0x0201;
                case 19: goto L_0x0217;
                case 20: goto L_0x022d;
                case 21: goto L_0x0243;
                case 22: goto L_0x0259;
                case 23: goto L_0x026f;
                case 24: goto L_0x0285;
                case 25: goto L_0x029b;
                case 26: goto L_0x02b1;
                case 27: goto L_0x02c6;
                case 28: goto L_0x02e1;
                case 29: goto L_0x02f6;
                case 30: goto L_0x030c;
                case 31: goto L_0x0322;
                case 32: goto L_0x0338;
                case 33: goto L_0x034e;
                case 34: goto L_0x0364;
                case 35: goto L_0x037a;
                case 36: goto L_0x0390;
                case 37: goto L_0x03a6;
                case 38: goto L_0x03bc;
                case 39: goto L_0x03d2;
                case 40: goto L_0x03e8;
                case 41: goto L_0x03fe;
                case 42: goto L_0x0414;
                case 43: goto L_0x042a;
                case 44: goto L_0x0440;
                case 45: goto L_0x0456;
                case 46: goto L_0x046c;
                case 47: goto L_0x0482;
                case 48: goto L_0x0498;
                case 49: goto L_0x04ae;
                case 50: goto L_0x04c9;
                case 51: goto L_0x04da;
                case 52: goto L_0x04f5;
                case 53: goto L_0x0510;
                case 54: goto L_0x052b;
                case 55: goto L_0x0546;
                case 56: goto L_0x0561;
                case 57: goto L_0x057c;
                case 58: goto L_0x0597;
                case 59: goto L_0x05b2;
                case 60: goto L_0x05cd;
                case 61: goto L_0x05ee;
                case 62: goto L_0x060b;
                case 63: goto L_0x0626;
                case 64: goto L_0x0641;
                case 65: goto L_0x065c;
                case 66: goto L_0x0677;
                case 67: goto L_0x0692;
                case 68: goto L_0x06ad;
                default: goto L_0x009e;
            }
        L_0x009e:
            int r4 = r12 + 4
            r12 = r4
            r6 = r11
            goto L_0x002e
        L_0x00a3:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            double r6 = com.google.android.gms.internal.ads.zzbek.zzo(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x009e
        L_0x00b5:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            float r4 = com.google.android.gms.internal.ads.zzbek.zzn(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (float) r4)
            goto L_0x009e
        L_0x00c7:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzi(r1, r6)
            goto L_0x009e
        L_0x00d9:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (long) r6)
            goto L_0x009e
        L_0x00eb:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzm(r1, r4)
            goto L_0x009e
        L_0x00fd:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc(r1, r6)
            goto L_0x009e
        L_0x010f:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzp(r1, r4)
            goto L_0x009e
        L_0x0122:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            boolean r4 = com.google.android.gms.internal.ads.zzbek.zzm(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzf((int) r1, (boolean) r4)
            goto L_0x009e
        L_0x0135:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r16
            r1 = r22
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.ads.zzbey) r1)
            goto L_0x009e
        L_0x0148:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.ads.zzbdm r6 = r0.zzcq(r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.ads.zzbdm) r6)
            goto L_0x009e
        L_0x0161:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.ads.zzbah r4 = (com.google.android.gms.internal.ads.zzbah) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.ads.zzbah) r4)
            goto L_0x009e
        L_0x0176:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzn(r1, r4)
            goto L_0x009e
        L_0x0189:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzx(r1, r4)
            goto L_0x009e
        L_0x019c:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzw(r1, r4)
            goto L_0x009e
        L_0x01af:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzj(r1, r6)
            goto L_0x009e
        L_0x01c2:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzo(r1, r4)
            goto L_0x009e
        L_0x01d5:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (long) r6)
            goto L_0x009e
        L_0x01e8:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.ads.zzbdm r6 = r0.zzcq(r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.ads.zzbdm) r6)
            goto L_0x009e
        L_0x0201:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.ads.zzbey) r0, (boolean) r6)
            goto L_0x009e
        L_0x0217:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.ads.zzbey) r0, (boolean) r6)
            goto L_0x009e
        L_0x022d:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzc(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0243:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzd(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0259:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzh(r10, r4, r0, r6)
            goto L_0x009e
        L_0x026f:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzf(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0285:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzk(r10, r4, r0, r6)
            goto L_0x009e
        L_0x029b:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzn(r10, r4, r0, r6)
            goto L_0x009e
        L_0x02b1:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zza((int) r10, (java.util.List<java.lang.String>) r4, (com.google.android.gms.internal.ads.zzbey) r0)
            goto L_0x009e
        L_0x02c6:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.ads.zzbdm r6 = r0.zzcq(r12)
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zza((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.ads.zzbey) r0, (com.google.android.gms.internal.ads.zzbdm) r6)
            goto L_0x009e
        L_0x02e1:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzb(r10, r4, r0)
            goto L_0x009e
        L_0x02f6:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzi(r10, r4, r0, r6)
            goto L_0x009e
        L_0x030c:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzm(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0322:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzl(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0338:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzg(r10, r4, r0, r6)
            goto L_0x009e
        L_0x034e:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzj(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0364:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zze(r10, r4, r0, r6)
            goto L_0x009e
        L_0x037a:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.ads.zzbey) r0, (boolean) r6)
            goto L_0x009e
        L_0x0390:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.ads.zzbey) r0, (boolean) r6)
            goto L_0x009e
        L_0x03a6:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzc(r10, r4, r0, r6)
            goto L_0x009e
        L_0x03bc:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzd(r10, r4, r0, r6)
            goto L_0x009e
        L_0x03d2:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzh(r10, r4, r0, r6)
            goto L_0x009e
        L_0x03e8:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzf(r10, r4, r0, r6)
            goto L_0x009e
        L_0x03fe:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzk(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0414:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzn(r10, r4, r0, r6)
            goto L_0x009e
        L_0x042a:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzi(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0440:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzm(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0456:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzl(r10, r4, r0, r6)
            goto L_0x009e
        L_0x046c:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzg(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0482:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzj(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0498:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zze(r10, r4, r0, r6)
            goto L_0x009e
        L_0x04ae:
            r0 = r20
            int[] r4 = r0.zzdwg
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.ads.zzbdm r6 = r0.zzcq(r12)
            r0 = r22
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.ads.zzbey) r0, (com.google.android.gms.internal.ads.zzbdm) r6)
            goto L_0x009e
        L_0x04c9:
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            r1 = r22
            r2 = r16
            r0.zza((com.google.android.gms.internal.ads.zzbey) r1, (int) r2, (java.lang.Object) r4, (int) r12)
            goto L_0x009e
        L_0x04da:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            double r6 = zzf(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x009e
        L_0x04f5:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            float r4 = zzg(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (float) r4)
            goto L_0x009e
        L_0x0510:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzi(r1, r6)
            goto L_0x009e
        L_0x052b:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (long) r6)
            goto L_0x009e
        L_0x0546:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzm(r1, r4)
            goto L_0x009e
        L_0x0561:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc(r1, r6)
            goto L_0x009e
        L_0x057c:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzp(r1, r4)
            goto L_0x009e
        L_0x0597:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            boolean r4 = zzj(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzf((int) r1, (boolean) r4)
            goto L_0x009e
        L_0x05b2:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r16
            r1 = r22
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.ads.zzbey) r1)
            goto L_0x009e
        L_0x05cd:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.ads.zzbdm r6 = r0.zzcq(r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.ads.zzbdm) r6)
            goto L_0x009e
        L_0x05ee:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.ads.zzbah r4 = (com.google.android.gms.internal.ads.zzbah) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.ads.zzbah) r4)
            goto L_0x009e
        L_0x060b:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzn(r1, r4)
            goto L_0x009e
        L_0x0626:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzx(r1, r4)
            goto L_0x009e
        L_0x0641:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzw(r1, r4)
            goto L_0x009e
        L_0x065c:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzj(r1, r6)
            goto L_0x009e
        L_0x0677:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzo(r1, r4)
            goto L_0x009e
        L_0x0692:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (long) r6)
            goto L_0x009e
        L_0x06ad:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.ads.zzbdm r6 = r0.zzcq(r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.ads.zzbdm) r6)
            goto L_0x009e
        L_0x06ce:
            r4 = 0
        L_0x06cf:
            if (r4 == 0) goto L_0x06e7
            r0 = r20
            com.google.android.gms.internal.ads.zzbbd<?> r6 = r0.zzdww
            r0 = r22
            r6.zza((com.google.android.gms.internal.ads.zzbey) r0, (java.util.Map.Entry<?, ?>) r4)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x06ce
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x06cf
        L_0x06e7:
            r0 = r20
            com.google.android.gms.internal.ads.zzbee<?, ?> r4 = r0.zzdwv
            r0 = r21
            r1 = r22
            zza(r4, r0, (com.google.android.gms.internal.ads.zzbey) r1)
            return
        L_0x06f3:
            r4 = r6
            r7 = r8
            goto L_0x0068
        L_0x06f7:
            r10 = r4
            r11 = r6
            goto L_0x006f
        L_0x06fb:
            r4 = r9
            goto L_0x06cf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zzb(java.lang.Object, com.google.android.gms.internal.ads.zzbey):void");
    }

    private final void zzb(T t, T t2, int i) {
        int zzct = zzct(i);
        int i2 = this.zzdwg[i];
        long j = (long) (zzct & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zzbek.zzp(t, j);
            Object zzp2 = zzbek.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzbek.zza((Object) t, j, zzbbq.zza(zzp, zzp2));
                zzb(t, i2, i);
            } else if (zzp2 != null) {
                zzbek.zza((Object) t, j, zzp2);
                zzb(t, i2, i);
            }
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final zzbdm zzcq(int i) {
        int i2 = (i / 4) << 1;
        zzbdm zzbdm = (zzbdm) this.zzdwh[i2];
        if (zzbdm != null) {
            return zzbdm;
        }
        zzbdm zze = zzbdg.zzaeo().zze((Class) this.zzdwh[i2 + 1]);
        this.zzdwh[i2] = zze;
        return zze;
    }

    private final Object zzcr(int i) {
        return this.zzdwh[(i / 4) << 1];
    }

    private final zzbbs<?> zzcs(int i) {
        return (zzbbs) this.zzdwh[((i / 4) << 1) + 1];
    }

    private final int zzct(int i) {
        return this.zzdwg[i + 1];
    }

    private final int zzcu(int i) {
        return this.zzdwg[i + 2];
    }

    private static boolean zzcv(int i) {
        return (536870912 & i) != 0;
    }

    private final int zzcw(int i) {
        if (i >= this.zzdwi) {
            if (i < this.zzdwk) {
                int i2 = (i - this.zzdwi) << 2;
                if (this.zzdwg[i2] == i) {
                    return i2;
                }
                return -1;
            } else if (i <= this.zzdwj) {
                int i3 = this.zzdwk - this.zzdwi;
                int length = (this.zzdwg.length / 4) - 1;
                while (i3 <= length) {
                    int i4 = (length + i3) >>> 1;
                    int i5 = i4 << 2;
                    int i6 = this.zzdwg[i5];
                    if (i == i6) {
                        return i5;
                    }
                    if (i < i6) {
                        length = i4 - 1;
                    } else {
                        i3 = i4 + 1;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzbek.zzp(obj, j);
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzbek.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzbek.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzbek.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzbek.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzbek.zzp(t, j)).booleanValue();
    }

    private static zzbef zzz(Object obj) {
        zzbef zzbef = ((zzbbo) obj).zzdtt;
        if (zzbef != zzbef.zzagc()) {
            return zzbef;
        }
        zzbef zzagd = zzbef.zzagd();
        ((zzbbo) obj).zzdtt = zzagd;
        return zzagd;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r12, T r13) {
        /*
            r11 = this;
            r1 = 1
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 0
            int[] r2 = r11.zzdwg
            int r4 = r2.length
            r3 = r0
        L_0x0009:
            if (r3 >= r4) goto L_0x01cf
            int r2 = r11.zzct(r3)
            r5 = r2 & r10
            long r6 = (long) r5
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r2 = r2 & r5
            int r2 = r2 >>> 20
            switch(r2) {
                case 0: goto L_0x001e;
                case 1: goto L_0x0032;
                case 2: goto L_0x0044;
                case 3: goto L_0x0058;
                case 4: goto L_0x006c;
                case 5: goto L_0x007e;
                case 6: goto L_0x0092;
                case 7: goto L_0x00a5;
                case 8: goto L_0x00b8;
                case 9: goto L_0x00cf;
                case 10: goto L_0x00e6;
                case 11: goto L_0x00fd;
                case 12: goto L_0x0110;
                case 13: goto L_0x0123;
                case 14: goto L_0x0136;
                case 15: goto L_0x014b;
                case 16: goto L_0x015e;
                case 17: goto L_0x0173;
                case 18: goto L_0x018a;
                case 19: goto L_0x018a;
                case 20: goto L_0x018a;
                case 21: goto L_0x018a;
                case 22: goto L_0x018a;
                case 23: goto L_0x018a;
                case 24: goto L_0x018a;
                case 25: goto L_0x018a;
                case 26: goto L_0x018a;
                case 27: goto L_0x018a;
                case 28: goto L_0x018a;
                case 29: goto L_0x018a;
                case 30: goto L_0x018a;
                case 31: goto L_0x018a;
                case 32: goto L_0x018a;
                case 33: goto L_0x018a;
                case 34: goto L_0x018a;
                case 35: goto L_0x018a;
                case 36: goto L_0x018a;
                case 37: goto L_0x018a;
                case 38: goto L_0x018a;
                case 39: goto L_0x018a;
                case 40: goto L_0x018a;
                case 41: goto L_0x018a;
                case 42: goto L_0x018a;
                case 43: goto L_0x018a;
                case 44: goto L_0x018a;
                case 45: goto L_0x018a;
                case 46: goto L_0x018a;
                case 47: goto L_0x018a;
                case 48: goto L_0x018a;
                case 49: goto L_0x018a;
                case 50: goto L_0x0198;
                case 51: goto L_0x01a6;
                case 52: goto L_0x01a6;
                case 53: goto L_0x01a6;
                case 54: goto L_0x01a6;
                case 55: goto L_0x01a6;
                case 56: goto L_0x01a6;
                case 57: goto L_0x01a6;
                case 58: goto L_0x01a6;
                case 59: goto L_0x01a6;
                case 60: goto L_0x01a6;
                case 61: goto L_0x01a6;
                case 62: goto L_0x01a6;
                case 63: goto L_0x01a6;
                case 64: goto L_0x01a6;
                case 65: goto L_0x01a6;
                case 66: goto L_0x01a6;
                case 67: goto L_0x01a6;
                case 68: goto L_0x01a6;
                default: goto L_0x001a;
            }
        L_0x001a:
            r2 = r1
        L_0x001b:
            if (r2 != 0) goto L_0x01ca
        L_0x001d:
            return r0
        L_0x001e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0030
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0030:
            r2 = r0
            goto L_0x001b
        L_0x0032:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0042
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x0042:
            r2 = r0
            goto L_0x001b
        L_0x0044:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0056
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0056:
            r2 = r0
            goto L_0x001b
        L_0x0058:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x006a
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x006a:
            r2 = r0
            goto L_0x001b
        L_0x006c:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x007c
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x007c:
            r2 = r0
            goto L_0x001b
        L_0x007e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0090
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0090:
            r2 = r0
            goto L_0x001b
        L_0x0092:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00a2
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x00a2:
            r2 = r0
            goto L_0x001b
        L_0x00a5:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00b5
            boolean r2 = com.google.android.gms.internal.ads.zzbek.zzm(r12, r6)
            boolean r5 = com.google.android.gms.internal.ads.zzbek.zzm(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x00b5:
            r2 = r0
            goto L_0x001b
        L_0x00b8:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00cc
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.ads.zzbdo.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00cc:
            r2 = r0
            goto L_0x001b
        L_0x00cf:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00e3
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.ads.zzbdo.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00e3:
            r2 = r0
            goto L_0x001b
        L_0x00e6:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00fa
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.ads.zzbdo.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00fa:
            r2 = r0
            goto L_0x001b
        L_0x00fd:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x010d
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x010d:
            r2 = r0
            goto L_0x001b
        L_0x0110:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0120
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x0120:
            r2 = r0
            goto L_0x001b
        L_0x0123:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0133
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x0133:
            r2 = r0
            goto L_0x001b
        L_0x0136:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0148
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0148:
            r2 = r0
            goto L_0x001b
        L_0x014b:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x015b
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x015b:
            r2 = r0
            goto L_0x001b
        L_0x015e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0170
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0170:
            r2 = r0
            goto L_0x001b
        L_0x0173:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0187
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.ads.zzbdo.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x0187:
            r2 = r0
            goto L_0x001b
        L_0x018a:
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.ads.zzbdo.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            goto L_0x001b
        L_0x0198:
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.ads.zzbdo.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            goto L_0x001b
        L_0x01a6:
            int r2 = r11.zzcu(r3)
            r5 = r2 & r10
            long r8 = (long) r5
            int r5 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r8)
            r2 = r2 & r10
            long r8 = (long) r2
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r8)
            if (r5 != r2) goto L_0x01c7
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.ads.zzbdo.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x01c7:
            r2 = r0
            goto L_0x001b
        L_0x01ca:
            int r2 = r3 + 4
            r3 = r2
            goto L_0x0009
        L_0x01cf:
            com.google.android.gms.internal.ads.zzbee<?, ?> r2 = r11.zzdwv
            java.lang.Object r2 = r2.zzac(r12)
            com.google.android.gms.internal.ads.zzbee<?, ?> r3 = r11.zzdwv
            java.lang.Object r3 = r3.zzac(r13)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x001d
            boolean r0 = r11.zzdwm
            if (r0 == 0) goto L_0x01f7
            com.google.android.gms.internal.ads.zzbbd<?> r0 = r11.zzdww
            com.google.android.gms.internal.ads.zzbbg r0 = r0.zzm(r12)
            com.google.android.gms.internal.ads.zzbbd<?> r1 = r11.zzdww
            com.google.android.gms.internal.ads.zzbbg r1 = r1.zzm(r13)
            boolean r0 = r0.equals(r1)
            goto L_0x001d
        L_0x01f7:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r10) {
        /*
            r9 = this;
            r1 = 37
            r0 = 0
            int[] r2 = r9.zzdwg
            int r4 = r2.length
            r3 = r0
            r2 = r0
        L_0x0008:
            if (r3 >= r4) goto L_0x0254
            int r0 = r9.zzct(r3)
            int[] r5 = r9.zzdwg
            r5 = r5[r3]
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r6 & r0
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r8
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0024;
                case 1: goto L_0x0034;
                case 2: goto L_0x0040;
                case 3: goto L_0x004c;
                case 4: goto L_0x0058;
                case 5: goto L_0x0060;
                case 6: goto L_0x006c;
                case 7: goto L_0x0074;
                case 8: goto L_0x0080;
                case 9: goto L_0x008e;
                case 10: goto L_0x009c;
                case 11: goto L_0x00a9;
                case 12: goto L_0x00b2;
                case 13: goto L_0x00bb;
                case 14: goto L_0x00c4;
                case 15: goto L_0x00d1;
                case 16: goto L_0x00da;
                case 17: goto L_0x00e7;
                case 18: goto L_0x00f6;
                case 19: goto L_0x00f6;
                case 20: goto L_0x00f6;
                case 21: goto L_0x00f6;
                case 22: goto L_0x00f6;
                case 23: goto L_0x00f6;
                case 24: goto L_0x00f6;
                case 25: goto L_0x00f6;
                case 26: goto L_0x00f6;
                case 27: goto L_0x00f6;
                case 28: goto L_0x00f6;
                case 29: goto L_0x00f6;
                case 30: goto L_0x00f6;
                case 31: goto L_0x00f6;
                case 32: goto L_0x00f6;
                case 33: goto L_0x00f6;
                case 34: goto L_0x00f6;
                case 35: goto L_0x00f6;
                case 36: goto L_0x00f6;
                case 37: goto L_0x00f6;
                case 38: goto L_0x00f6;
                case 39: goto L_0x00f6;
                case 40: goto L_0x00f6;
                case 41: goto L_0x00f6;
                case 42: goto L_0x00f6;
                case 43: goto L_0x00f6;
                case 44: goto L_0x00f6;
                case 45: goto L_0x00f6;
                case 46: goto L_0x00f6;
                case 47: goto L_0x00f6;
                case 48: goto L_0x00f6;
                case 49: goto L_0x00f6;
                case 50: goto L_0x0103;
                case 51: goto L_0x0110;
                case 52: goto L_0x0127;
                case 53: goto L_0x013a;
                case 54: goto L_0x014d;
                case 55: goto L_0x0160;
                case 56: goto L_0x016f;
                case 57: goto L_0x0182;
                case 58: goto L_0x0191;
                case 59: goto L_0x01a4;
                case 60: goto L_0x01b9;
                case 61: goto L_0x01cc;
                case 62: goto L_0x01df;
                case 63: goto L_0x01ee;
                case 64: goto L_0x01fd;
                case 65: goto L_0x020c;
                case 66: goto L_0x021f;
                case 67: goto L_0x022e;
                case 68: goto L_0x0241;
                default: goto L_0x001f;
            }
        L_0x001f:
            r0 = r2
        L_0x0020:
            int r3 = r3 + 4
            r2 = r0
            goto L_0x0008
        L_0x0024:
            int r0 = r2 * 53
            double r6 = com.google.android.gms.internal.ads.zzbek.zzo(r10, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0034:
            int r0 = r2 * 53
            float r2 = com.google.android.gms.internal.ads.zzbek.zzn(r10, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0040:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x004c:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0058:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0060:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x006c:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0074:
            int r0 = r2 * 53
            boolean r2 = com.google.android.gms.internal.ads.zzbek.zzm(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzar(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0080:
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x008e:
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            if (r0 == 0) goto L_0x0276
            int r0 = r0.hashCode()
        L_0x0098:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x009c:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00a9:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00b2:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00bb:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00c4:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00d1:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00da:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00e7:
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            if (r0 == 0) goto L_0x0273
            int r0 = r0.hashCode()
        L_0x00f1:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00f6:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0103:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0110:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            double r6 = zzf(r10, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0127:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            float r2 = zzg(r10, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x013a:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzi(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x014d:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzi(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0160:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzh(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x016f:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzi(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0182:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzh(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0191:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            boolean r2 = zzj(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzar(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01a4:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01b9:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01cc:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01df:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzh(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01ee:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzh(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01fd:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzh(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x020c:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzi(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x021f:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzh(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x022e:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzi(r10, r6)
            int r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0241:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0254:
            int r0 = r2 * 53
            com.google.android.gms.internal.ads.zzbee<?, ?> r1 = r9.zzdwv
            java.lang.Object r1 = r1.zzac(r10)
            int r1 = r1.hashCode()
            int r0 = r0 + r1
            boolean r1 = r9.zzdwm
            if (r1 == 0) goto L_0x0272
            int r0 = r0 * 53
            com.google.android.gms.internal.ads.zzbbd<?> r1 = r9.zzdww
            com.google.android.gms.internal.ads.zzbbg r1 = r1.zzm(r10)
            int r1 = r1.hashCode()
            int r0 = r0 + r1
        L_0x0272:
            return r0
        L_0x0273:
            r0 = r1
            goto L_0x00f1
        L_0x0276:
            r0 = r1
            goto L_0x0098
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.hashCode(java.lang.Object):int");
    }

    public final T newInstance() {
        return this.zzdwt.newInstance(this.zzdwl);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void zza(T r13, com.google.android.gms.internal.ads.zzbdl r14, com.google.android.gms.internal.ads.zzbbb r15) throws java.io.IOException {
        /*
            r12 = this;
            if (r15 != 0) goto L_0x0008
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>()
            throw r0
        L_0x0008:
            com.google.android.gms.internal.ads.zzbee<?, ?> r6 = r12.zzdwv
            com.google.android.gms.internal.ads.zzbbd<?> r0 = r12.zzdww
            r5 = 0
            r4 = 0
        L_0x000e:
            int r1 = r14.zzaci()     // Catch:{ all -> 0x00e9 }
            int r2 = r12.zzcw(r1)     // Catch:{ all -> 0x00e9 }
            if (r2 >= 0) goto L_0x0079
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x0036
            int[] r0 = r12.zzdwr
            if (r0 == 0) goto L_0x0030
            int[] r1 = r12.zzdwr
            int r2 = r1.length
            r0 = 0
        L_0x0025:
            if (r0 >= r2) goto L_0x0030
            r3 = r1[r0]
            java.lang.Object r5 = r12.zza((java.lang.Object) r13, (int) r3, r5, r6)
            int r0 = r0 + 1
            goto L_0x0025
        L_0x0030:
            if (r5 == 0) goto L_0x0035
            r6.zzf(r13, r5)
        L_0x0035:
            return
        L_0x0036:
            boolean r2 = r12.zzdwm     // Catch:{ all -> 0x00e9 }
            if (r2 != 0) goto L_0x004a
            r2 = 0
        L_0x003b:
            if (r2 == 0) goto L_0x0051
            if (r4 != 0) goto L_0x0043
            com.google.android.gms.internal.ads.zzbbg r4 = r0.zzn(r13)     // Catch:{ all -> 0x00e9 }
        L_0x0043:
            r1 = r14
            r3 = r15
            java.lang.Object r5 = r0.zza(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00e9 }
            goto L_0x000e
        L_0x004a:
            com.google.android.gms.internal.ads.zzbcu r2 = r12.zzdwl     // Catch:{ all -> 0x00e9 }
            java.lang.Object r2 = r0.zza(r15, r2, r1)     // Catch:{ all -> 0x00e9 }
            goto L_0x003b
        L_0x0051:
            r6.zza(r14)     // Catch:{ all -> 0x00e9 }
            if (r5 != 0) goto L_0x005a
            java.lang.Object r5 = r6.zzad(r13)     // Catch:{ all -> 0x00e9 }
        L_0x005a:
            boolean r1 = r6.zza(r5, (com.google.android.gms.internal.ads.zzbdl) r14)     // Catch:{ all -> 0x00e9 }
            if (r1 != 0) goto L_0x000e
            int[] r0 = r12.zzdwr
            if (r0 == 0) goto L_0x0073
            int[] r1 = r12.zzdwr
            int r2 = r1.length
            r0 = 0
        L_0x0068:
            if (r0 >= r2) goto L_0x0073
            r3 = r1[r0]
            java.lang.Object r5 = r12.zza((java.lang.Object) r13, (int) r3, r5, r6)
            int r0 = r0 + 1
            goto L_0x0068
        L_0x0073:
            if (r5 == 0) goto L_0x0035
            r6.zzf(r13, r5)
            goto L_0x0035
        L_0x0079:
            int r3 = r12.zzct(r2)     // Catch:{ all -> 0x00e9 }
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r7 = r7 & r3
            int r7 = r7 >>> 20
            switch(r7) {
                case 0: goto L_0x00a4;
                case 1: goto L_0x00d8;
                case 2: goto L_0x00fd;
                case 3: goto L_0x010e;
                case 4: goto L_0x011f;
                case 5: goto L_0x0130;
                case 6: goto L_0x0141;
                case 7: goto L_0x0152;
                case 8: goto L_0x0163;
                case 9: goto L_0x016b;
                case 10: goto L_0x01a5;
                case 11: goto L_0x01b6;
                case 12: goto L_0x01c7;
                case 13: goto L_0x01ea;
                case 14: goto L_0x01fb;
                case 15: goto L_0x020c;
                case 16: goto L_0x021d;
                case 17: goto L_0x022e;
                case 18: goto L_0x0268;
                case 19: goto L_0x0278;
                case 20: goto L_0x0288;
                case 21: goto L_0x0298;
                case 22: goto L_0x02a8;
                case 23: goto L_0x02b8;
                case 24: goto L_0x02c8;
                case 25: goto L_0x02d8;
                case 26: goto L_0x02e8;
                case 27: goto L_0x030e;
                case 28: goto L_0x0322;
                case 29: goto L_0x0332;
                case 30: goto L_0x0342;
                case 31: goto L_0x035a;
                case 32: goto L_0x036a;
                case 33: goto L_0x037a;
                case 34: goto L_0x038a;
                case 35: goto L_0x039a;
                case 36: goto L_0x03aa;
                case 37: goto L_0x03ba;
                case 38: goto L_0x03ca;
                case 39: goto L_0x03da;
                case 40: goto L_0x03ea;
                case 41: goto L_0x03fa;
                case 42: goto L_0x040a;
                case 43: goto L_0x041a;
                case 44: goto L_0x042a;
                case 45: goto L_0x0442;
                case 46: goto L_0x0452;
                case 47: goto L_0x0462;
                case 48: goto L_0x0472;
                case 49: goto L_0x0482;
                case 50: goto L_0x0496;
                case 51: goto L_0x04da;
                case 52: goto L_0x04ef;
                case 53: goto L_0x0504;
                case 54: goto L_0x0519;
                case 55: goto L_0x052e;
                case 56: goto L_0x0543;
                case 57: goto L_0x0558;
                case 58: goto L_0x056d;
                case 59: goto L_0x0582;
                case 60: goto L_0x058a;
                case 61: goto L_0x05c6;
                case 62: goto L_0x05d7;
                case 63: goto L_0x05ec;
                case 64: goto L_0x0613;
                case 65: goto L_0x0628;
                case 66: goto L_0x063d;
                case 67: goto L_0x0652;
                case 68: goto L_0x0667;
                default: goto L_0x0085;
            }
        L_0x0085:
            if (r5 != 0) goto L_0x008b
            java.lang.Object r5 = r6.zzagb()     // Catch:{ zzbbv -> 0x00b5 }
        L_0x008b:
            boolean r1 = r6.zza(r5, (com.google.android.gms.internal.ads.zzbdl) r14)     // Catch:{ zzbbv -> 0x00b5 }
            if (r1 != 0) goto L_0x000e
            int[] r0 = r12.zzdwr
            if (r0 == 0) goto L_0x067c
            int[] r1 = r12.zzdwr
            int r2 = r1.length
            r0 = 0
        L_0x0099:
            if (r0 >= r2) goto L_0x067c
            r3 = r1[r0]
            java.lang.Object r5 = r12.zza((java.lang.Object) r13, (int) r3, r5, r6)
            int r0 = r0 + 1
            goto L_0x0099
        L_0x00a4:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1
            double r10 = r14.readDouble()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (double) r10)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x00b5:
            r1 = move-exception
            r6.zza(r14)     // Catch:{ all -> 0x00e9 }
            if (r5 != 0) goto L_0x00bf
            java.lang.Object r5 = r6.zzad(r13)     // Catch:{ all -> 0x00e9 }
        L_0x00bf:
            boolean r1 = r6.zza(r5, (com.google.android.gms.internal.ads.zzbdl) r14)     // Catch:{ all -> 0x00e9 }
            if (r1 != 0) goto L_0x000e
            int[] r0 = r12.zzdwr
            if (r0 == 0) goto L_0x0683
            int[] r1 = r12.zzdwr
            int r2 = r1.length
            r0 = 0
        L_0x00cd:
            if (r0 >= r2) goto L_0x0683
            r3 = r1[r0]
            java.lang.Object r5 = r12.zza((java.lang.Object) r13, (int) r3, r5, r6)
            int r0 = r0 + 1
            goto L_0x00cd
        L_0x00d8:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1
            float r1 = r14.readFloat()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (float) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x00e9:
            r0 = move-exception
            int[] r1 = r12.zzdwr
            if (r1 == 0) goto L_0x068a
            int[] r2 = r12.zzdwr
            int r3 = r2.length
            r1 = 0
        L_0x00f2:
            if (r1 >= r3) goto L_0x068a
            r4 = r2[r1]
            java.lang.Object r5 = r12.zza((java.lang.Object) r13, (int) r4, r5, r6)
            int r1 = r1 + 1
            goto L_0x00f2
        L_0x00fd:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1
            long r10 = r14.zzabm()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (long) r10)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x010e:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzabl()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (long) r10)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x011f:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            int r1 = r14.zzabn()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zzb((java.lang.Object) r13, (long) r8, (int) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0130:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzabo()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (long) r10)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0141:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            int r1 = r14.zzabp()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zzb((java.lang.Object) r13, (long) r8, (int) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0152:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            boolean r1 = r14.zzabq()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (boolean) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0163:
            r12.zza((java.lang.Object) r13, (int) r3, (com.google.android.gms.internal.ads.zzbdl) r14)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x016b:
            boolean r1 = r12.zza(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            if (r1 == 0) goto L_0x0190
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r8)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbdm r2 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r2 = r14.zza(r2, r15)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbbq.zza((java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r2, (java.lang.Object) r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0190:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbdm r1 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r1 = r14.zza(r1, r15)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x01a5:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbah r1 = r14.zzabs()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x01b6:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            int r1 = r14.zzabt()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zzb((java.lang.Object) r13, (long) r8, (int) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x01c7:
            int r7 = r14.zzabu()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbbs r8 = r12.zzcs(r2)     // Catch:{ zzbbv -> 0x00b5 }
            if (r8 == 0) goto L_0x01d7
            com.google.android.gms.internal.ads.zzbbr r8 = r8.zzq(r7)     // Catch:{ zzbbv -> 0x00b5 }
            if (r8 == 0) goto L_0x01e4
        L_0x01d7:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zzb((java.lang.Object) r13, (long) r8, (int) r7)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x01e4:
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbdo.zza((int) r1, (int) r7, r5, r6)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x01ea:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            int r1 = r14.zzabv()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zzb((java.lang.Object) r13, (long) r8, (int) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x01fb:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzabw()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (long) r10)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x020c:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            int r1 = r14.zzabx()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zzb((java.lang.Object) r13, (long) r8, (int) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x021d:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzaby()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (long) r10)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x022e:
            boolean r1 = r12.zza(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            if (r1 == 0) goto L_0x0253
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r8)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbdm r2 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r2 = r14.zzb(r2, r15)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbbq.zza((java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r2, (java.lang.Object) r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0253:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbdm r1 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r1 = r14.zzb(r1, r15)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r1)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0268:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzp(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0278:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzq(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0288:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzs(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0298:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzr(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x02a8:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzt(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x02b8:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzu(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x02c8:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzv(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x02d8:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzw(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x02e8:
            boolean r1 = zzcv(r3)     // Catch:{ zzbbv -> 0x00b5 }
            if (r1 == 0) goto L_0x02fe
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzx(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x02fe:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.readStringList(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x030e:
            com.google.android.gms.internal.ads.zzbdm r1 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbce r7 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r2 = r7.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zza(r2, r1, (com.google.android.gms.internal.ads.zzbbb) r15)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0322:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzy(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0332:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzz(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0342:
            com.google.android.gms.internal.ads.zzbce r7 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r8
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r3 = r7.zza(r13, r8)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzaa(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbbs r2 = r12.zzcs(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbdo.zza(r1, r3, r2, r5, r6)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x035a:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzab(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x036a:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzac(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x037a:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzad(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x038a:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzae(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x039a:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzp(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x03aa:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzq(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x03ba:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzs(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x03ca:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzr(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x03da:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzt(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x03ea:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzu(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x03fa:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzv(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x040a:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzw(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x041a:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzz(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x042a:
            com.google.android.gms.internal.ads.zzbce r7 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r8
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r3 = r7.zza(r13, r8)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzaa(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbbs r2 = r12.zzcs(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbdo.zza(r1, r3, r2, r5, r6)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0442:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzab(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0452:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzac(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0462:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzad(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0472:
            com.google.android.gms.internal.ads.zzbce r1 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r3
            long r2 = (long) r2     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzae(r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0482:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r3
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbdm r1 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbce r2 = r12.zzdwu     // Catch:{ zzbbv -> 0x00b5 }
            java.util.List r2 = r2.zza(r13, r8)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zzb(r2, r1, r15)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0496:
            java.lang.Object r3 = r12.zzcr(r2)     // Catch:{ zzbbv -> 0x00b5 }
            int r1 = r12.zzct(r2)     // Catch:{ zzbbv -> 0x00b5 }
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r2
            long r8 = (long) r1     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r8)     // Catch:{ zzbbv -> 0x00b5 }
            if (r2 != 0) goto L_0x04c3
            com.google.android.gms.internal.ads.zzbcp r1 = r12.zzdwx     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r1 = r1.zzw(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r1)     // Catch:{ zzbbv -> 0x00b5 }
        L_0x04b2:
            com.google.android.gms.internal.ads.zzbcp r2 = r12.zzdwx     // Catch:{ zzbbv -> 0x00b5 }
            java.util.Map r1 = r2.zzs(r1)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbcp r2 = r12.zzdwx     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbcn r2 = r2.zzx(r3)     // Catch:{ zzbbv -> 0x00b5 }
            r14.zza(r1, r2, (com.google.android.gms.internal.ads.zzbbb) r15)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x04c3:
            com.google.android.gms.internal.ads.zzbcp r1 = r12.zzdwx     // Catch:{ zzbbv -> 0x00b5 }
            boolean r1 = r1.zzu(r2)     // Catch:{ zzbbv -> 0x00b5 }
            if (r1 == 0) goto L_0x0690
            com.google.android.gms.internal.ads.zzbcp r1 = r12.zzdwx     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r1 = r1.zzw(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbcp r7 = r12.zzdwx     // Catch:{ zzbbv -> 0x00b5 }
            r7.zzb(r1, r2)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r1)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x04b2
        L_0x04da:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            double r10 = r14.readDouble()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Double r3 = java.lang.Double.valueOf(r10)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x04ef:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            float r3 = r14.readFloat()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0504:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzabm()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Long r3 = java.lang.Long.valueOf(r10)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0519:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzabl()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Long r3 = java.lang.Long.valueOf(r10)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x052e:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            int r3 = r14.zzabn()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0543:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzabo()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Long r3 = java.lang.Long.valueOf(r10)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0558:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            int r3 = r14.zzabp()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x056d:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            boolean r3 = r14.zzabq()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0582:
            r12.zza((java.lang.Object) r13, (int) r3, (com.google.android.gms.internal.ads.zzbdl) r14)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x058a:
            boolean r7 = r12.zza(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            if (r7 == 0) goto L_0x05b2
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r7 & r3
            long r8 = (long) r7     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r7 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r8)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbdm r8 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r8 = r14.zza(r8, r15)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r7 = com.google.android.gms.internal.ads.zzbbq.zza((java.lang.Object) r7, (java.lang.Object) r8)     // Catch:{ zzbbv -> 0x00b5 }
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r8
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r7)     // Catch:{ zzbbv -> 0x00b5 }
        L_0x05ad:
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x05b2:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbdm r3 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r3 = r14.zza(r3, r15)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x05ad
        L_0x05c6:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbah r3 = r14.zzabs()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x05d7:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            int r3 = r14.zzabt()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x05ec:
            int r7 = r14.zzabu()     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbbs r8 = r12.zzcs(r2)     // Catch:{ zzbbv -> 0x00b5 }
            if (r8 == 0) goto L_0x05fc
            com.google.android.gms.internal.ads.zzbbr r8 = r8.zzq(r7)     // Catch:{ zzbbv -> 0x00b5 }
            if (r8 == 0) goto L_0x060d
        L_0x05fc:
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r8
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x060d:
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbdo.zza((int) r1, (int) r7, r5, r6)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0613:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            int r3 = r14.zzabv()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0628:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzabw()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Long r3 = java.lang.Long.valueOf(r10)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x063d:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            int r3 = r14.zzabx()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0652:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            long r10 = r14.zzaby()     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Long r3 = java.lang.Long.valueOf(r10)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x0667:
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r7
            long r8 = (long) r3     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbdm r3 = r12.zzcq(r2)     // Catch:{ zzbbv -> 0x00b5 }
            java.lang.Object r3 = r14.zzb(r3, r15)     // Catch:{ zzbbv -> 0x00b5 }
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r13, (long) r8, (java.lang.Object) r3)     // Catch:{ zzbbv -> 0x00b5 }
            r12.zzb(r13, (int) r1, (int) r2)     // Catch:{ zzbbv -> 0x00b5 }
            goto L_0x000e
        L_0x067c:
            if (r5 == 0) goto L_0x0035
            r6.zzf(r13, r5)
            goto L_0x0035
        L_0x0683:
            if (r5 == 0) goto L_0x0035
            r6.zzf(r13, r5)
            goto L_0x0035
        L_0x068a:
            if (r5 == 0) goto L_0x068f
            r6.zzf(r13, r5)
        L_0x068f:
            throw r0
        L_0x0690:
            r1 = r2
            goto L_0x04b2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbdl, com.google.android.gms.internal.ads.zzbbb):void");
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 547 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r11, com.google.android.gms.internal.ads.zzbey r12) throws java.io.IOException {
        /*
            r10 = this;
            int r0 = r12.zzacn()
            int r1 = com.google.android.gms.internal.ads.zzbbo.zze.zzdum
            if (r0 != r1) goto L_0x060a
            com.google.android.gms.internal.ads.zzbee<?, ?> r0 = r10.zzdwv
            zza(r0, r11, (com.google.android.gms.internal.ads.zzbey) r12)
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzdwm
            if (r2 == 0) goto L_0x0029
            com.google.android.gms.internal.ads.zzbbd<?> r2 = r10.zzdww
            com.google.android.gms.internal.ads.zzbbg r2 = r2.zzm(r11)
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x0029
            java.util.Iterator r1 = r2.descendingIterator()
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0029:
            int[] r2 = r10.zzdwg
            int r2 = r2.length
            int r2 = r2 + -4
            r3 = r2
        L_0x002f:
            if (r3 < 0) goto L_0x05f6
            int r4 = r10.zzct(r3)
            int[] r2 = r10.zzdwg
            r5 = r2[r3]
            r2 = r0
        L_0x003a:
            if (r2 == 0) goto L_0x0059
            com.google.android.gms.internal.ads.zzbbd<?> r0 = r10.zzdww
            int r0 = r0.zza(r2)
            if (r0 <= r5) goto L_0x0059
            com.google.android.gms.internal.ads.zzbbd<?> r0 = r10.zzdww
            r0.zza((com.google.android.gms.internal.ads.zzbey) r12, (java.util.Map.Entry<?, ?>) r2)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0057
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0055:
            r2 = r0
            goto L_0x003a
        L_0x0057:
            r0 = 0
            goto L_0x0055
        L_0x0059:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r4
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0065;
                case 1: goto L_0x0078;
                case 2: goto L_0x008b;
                case 3: goto L_0x009e;
                case 4: goto L_0x00b1;
                case 5: goto L_0x00c4;
                case 6: goto L_0x00d7;
                case 7: goto L_0x00eb;
                case 8: goto L_0x00ff;
                case 9: goto L_0x0113;
                case 10: goto L_0x012b;
                case 11: goto L_0x0141;
                case 12: goto L_0x0155;
                case 13: goto L_0x0169;
                case 14: goto L_0x017d;
                case 15: goto L_0x0191;
                case 16: goto L_0x01a5;
                case 17: goto L_0x01b9;
                case 18: goto L_0x01d1;
                case 19: goto L_0x01e6;
                case 20: goto L_0x01fb;
                case 21: goto L_0x0210;
                case 22: goto L_0x0225;
                case 23: goto L_0x023a;
                case 24: goto L_0x024f;
                case 25: goto L_0x0264;
                case 26: goto L_0x0279;
                case 27: goto L_0x028d;
                case 28: goto L_0x02a5;
                case 29: goto L_0x02b9;
                case 30: goto L_0x02ce;
                case 31: goto L_0x02e3;
                case 32: goto L_0x02f8;
                case 33: goto L_0x030d;
                case 34: goto L_0x0322;
                case 35: goto L_0x0337;
                case 36: goto L_0x034c;
                case 37: goto L_0x0361;
                case 38: goto L_0x0376;
                case 39: goto L_0x038b;
                case 40: goto L_0x03a0;
                case 41: goto L_0x03b5;
                case 42: goto L_0x03ca;
                case 43: goto L_0x03df;
                case 44: goto L_0x03f4;
                case 45: goto L_0x0409;
                case 46: goto L_0x041e;
                case 47: goto L_0x0433;
                case 48: goto L_0x0448;
                case 49: goto L_0x045d;
                case 50: goto L_0x0475;
                case 51: goto L_0x0483;
                case 52: goto L_0x0497;
                case 53: goto L_0x04ab;
                case 54: goto L_0x04bf;
                case 55: goto L_0x04d3;
                case 56: goto L_0x04e7;
                case 57: goto L_0x04fb;
                case 58: goto L_0x050f;
                case 59: goto L_0x0523;
                case 60: goto L_0x0537;
                case 61: goto L_0x054f;
                case 62: goto L_0x0565;
                case 63: goto L_0x0579;
                case 64: goto L_0x058d;
                case 65: goto L_0x05a1;
                case 66: goto L_0x05b5;
                case 67: goto L_0x05c9;
                case 68: goto L_0x05dd;
                default: goto L_0x0061;
            }
        L_0x0061:
            int r3 = r3 + -4
            r0 = r2
            goto L_0x002f
        L_0x0065:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            double r6 = com.google.android.gms.internal.ads.zzbek.zzo(r11, r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0061
        L_0x0078:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = com.google.android.gms.internal.ads.zzbek.zzn(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0061
        L_0x008b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)
            r12.zzi(r5, r6)
            goto L_0x0061
        L_0x009e:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0061
        L_0x00b1:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)
            r12.zzm(r5, r0)
            goto L_0x0061
        L_0x00c4:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)
            r12.zzc(r5, r6)
            goto L_0x0061
        L_0x00d7:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)
            r12.zzp(r5, r0)
            goto L_0x0061
        L_0x00eb:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = com.google.android.gms.internal.ads.zzbek.zzm(r11, r6)
            r12.zzf((int) r5, (boolean) r0)
            goto L_0x0061
        L_0x00ff:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbey) r12)
            goto L_0x0061
        L_0x0113:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            com.google.android.gms.internal.ads.zzbdm r4 = r10.zzcq(r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbdm) r4)
            goto L_0x0061
        L_0x012b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            com.google.android.gms.internal.ads.zzbah r0 = (com.google.android.gms.internal.ads.zzbah) r0
            r12.zza((int) r5, (com.google.android.gms.internal.ads.zzbah) r0)
            goto L_0x0061
        L_0x0141:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)
            r12.zzn(r5, r0)
            goto L_0x0061
        L_0x0155:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)
            r12.zzx(r5, r0)
            goto L_0x0061
        L_0x0169:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)
            r12.zzw(r5, r0)
            goto L_0x0061
        L_0x017d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)
            r12.zzj(r5, r6)
            goto L_0x0061
        L_0x0191:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)
            r12.zzo(r5, r0)
            goto L_0x0061
        L_0x01a5:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0061
        L_0x01b9:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            com.google.android.gms.internal.ads.zzbdm r4 = r10.zzcq(r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbdm) r4)
            goto L_0x0061
        L_0x01d1:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (boolean) r4)
            goto L_0x0061
        L_0x01e6:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (boolean) r4)
            goto L_0x0061
        L_0x01fb:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzc(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0210:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzd(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0225:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzh(r5, r0, r12, r4)
            goto L_0x0061
        L_0x023a:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzf(r5, r0, r12, r4)
            goto L_0x0061
        L_0x024f:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzk(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0264:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzn(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0279:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzbdo.zza((int) r5, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.ads.zzbey) r12)
            goto L_0x0061
        L_0x028d:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzbdm r4 = r10.zzcq(r3)
            com.google.android.gms.internal.ads.zzbdo.zza((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (com.google.android.gms.internal.ads.zzbdm) r4)
            goto L_0x0061
        L_0x02a5:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzbdo.zzb(r5, r0, r12)
            goto L_0x0061
        L_0x02b9:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzi(r5, r0, r12, r4)
            goto L_0x0061
        L_0x02ce:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzm(r5, r0, r12, r4)
            goto L_0x0061
        L_0x02e3:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzl(r5, r0, r12, r4)
            goto L_0x0061
        L_0x02f8:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzg(r5, r0, r12, r4)
            goto L_0x0061
        L_0x030d:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zzj(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0322:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.ads.zzbdo.zze(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0337:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (boolean) r4)
            goto L_0x0061
        L_0x034c:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (boolean) r4)
            goto L_0x0061
        L_0x0361:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzc(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0376:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzd(r5, r0, r12, r4)
            goto L_0x0061
        L_0x038b:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzh(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03a0:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzf(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03b5:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzk(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03ca:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzn(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03df:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzi(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03f4:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzm(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0409:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzl(r5, r0, r12, r4)
            goto L_0x0061
        L_0x041e:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzg(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0433:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zzj(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0448:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.ads.zzbdo.zze(r5, r0, r12, r4)
            goto L_0x0061
        L_0x045d:
            int[] r0 = r10.zzdwg
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzbdm r4 = r10.zzcq(r3)
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (com.google.android.gms.internal.ads.zzbdm) r4)
            goto L_0x0061
        L_0x0475:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            r10.zza((com.google.android.gms.internal.ads.zzbey) r12, (int) r5, (java.lang.Object) r0, (int) r3)
            goto L_0x0061
        L_0x0483:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            double r6 = zzf(r11, r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0061
        L_0x0497:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = zzg(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0061
        L_0x04ab:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zzi(r5, r6)
            goto L_0x0061
        L_0x04bf:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0061
        L_0x04d3:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzm(r5, r0)
            goto L_0x0061
        L_0x04e7:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zzc(r5, r6)
            goto L_0x0061
        L_0x04fb:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzp(r5, r0)
            goto L_0x0061
        L_0x050f:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = zzj(r11, r6)
            r12.zzf((int) r5, (boolean) r0)
            goto L_0x0061
        L_0x0523:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbey) r12)
            goto L_0x0061
        L_0x0537:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            com.google.android.gms.internal.ads.zzbdm r4 = r10.zzcq(r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbdm) r4)
            goto L_0x0061
        L_0x054f:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            com.google.android.gms.internal.ads.zzbah r0 = (com.google.android.gms.internal.ads.zzbah) r0
            r12.zza((int) r5, (com.google.android.gms.internal.ads.zzbah) r0)
            goto L_0x0061
        L_0x0565:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzn(r5, r0)
            goto L_0x0061
        L_0x0579:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzx(r5, r0)
            goto L_0x0061
        L_0x058d:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzw(r5, r0)
            goto L_0x0061
        L_0x05a1:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zzj(r5, r6)
            goto L_0x0061
        L_0x05b5:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzo(r5, r0)
            goto L_0x0061
        L_0x05c9:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0061
        L_0x05dd:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)
            com.google.android.gms.internal.ads.zzbdm r4 = r10.zzcq(r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbdm) r4)
            goto L_0x0061
        L_0x05f5:
            r0 = 0
        L_0x05f6:
            if (r0 == 0) goto L_0x0c0f
            com.google.android.gms.internal.ads.zzbbd<?> r2 = r10.zzdww
            r2.zza((com.google.android.gms.internal.ads.zzbey) r12, (java.util.Map.Entry<?, ?>) r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x05f5
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x05f6
        L_0x060a:
            boolean r0 = r10.zzdwo
            if (r0 == 0) goto L_0x0c10
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzdwm
            if (r2 == 0) goto L_0x062a
            com.google.android.gms.internal.ads.zzbbd<?> r2 = r10.zzdww
            com.google.android.gms.internal.ads.zzbbg r2 = r2.zzm(r11)
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x062a
            java.util.Iterator r1 = r2.iterator()
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x062a:
            int[] r2 = r10.zzdwg
            int r4 = r2.length
            r2 = 0
            r3 = r2
        L_0x062f:
            if (r3 >= r4) goto L_0x0bf6
            int r5 = r10.zzct(r3)
            int[] r2 = r10.zzdwg
            r6 = r2[r3]
            r2 = r0
        L_0x063a:
            if (r2 == 0) goto L_0x0659
            com.google.android.gms.internal.ads.zzbbd<?> r0 = r10.zzdww
            int r0 = r0.zza(r2)
            if (r0 > r6) goto L_0x0659
            com.google.android.gms.internal.ads.zzbbd<?> r0 = r10.zzdww
            r0.zza((com.google.android.gms.internal.ads.zzbey) r12, (java.util.Map.Entry<?, ?>) r2)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0657
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0655:
            r2 = r0
            goto L_0x063a
        L_0x0657:
            r0 = 0
            goto L_0x0655
        L_0x0659:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0665;
                case 1: goto L_0x0678;
                case 2: goto L_0x068b;
                case 3: goto L_0x069e;
                case 4: goto L_0x06b1;
                case 5: goto L_0x06c4;
                case 6: goto L_0x06d7;
                case 7: goto L_0x06eb;
                case 8: goto L_0x06ff;
                case 9: goto L_0x0713;
                case 10: goto L_0x072b;
                case 11: goto L_0x0741;
                case 12: goto L_0x0755;
                case 13: goto L_0x0769;
                case 14: goto L_0x077d;
                case 15: goto L_0x0791;
                case 16: goto L_0x07a5;
                case 17: goto L_0x07b9;
                case 18: goto L_0x07d1;
                case 19: goto L_0x07e6;
                case 20: goto L_0x07fb;
                case 21: goto L_0x0810;
                case 22: goto L_0x0825;
                case 23: goto L_0x083a;
                case 24: goto L_0x084f;
                case 25: goto L_0x0864;
                case 26: goto L_0x0879;
                case 27: goto L_0x088d;
                case 28: goto L_0x08a5;
                case 29: goto L_0x08b9;
                case 30: goto L_0x08ce;
                case 31: goto L_0x08e3;
                case 32: goto L_0x08f8;
                case 33: goto L_0x090d;
                case 34: goto L_0x0922;
                case 35: goto L_0x0937;
                case 36: goto L_0x094c;
                case 37: goto L_0x0961;
                case 38: goto L_0x0976;
                case 39: goto L_0x098b;
                case 40: goto L_0x09a0;
                case 41: goto L_0x09b5;
                case 42: goto L_0x09ca;
                case 43: goto L_0x09df;
                case 44: goto L_0x09f4;
                case 45: goto L_0x0a09;
                case 46: goto L_0x0a1e;
                case 47: goto L_0x0a33;
                case 48: goto L_0x0a48;
                case 49: goto L_0x0a5d;
                case 50: goto L_0x0a75;
                case 51: goto L_0x0a83;
                case 52: goto L_0x0a97;
                case 53: goto L_0x0aab;
                case 54: goto L_0x0abf;
                case 55: goto L_0x0ad3;
                case 56: goto L_0x0ae7;
                case 57: goto L_0x0afb;
                case 58: goto L_0x0b0f;
                case 59: goto L_0x0b23;
                case 60: goto L_0x0b37;
                case 61: goto L_0x0b4f;
                case 62: goto L_0x0b65;
                case 63: goto L_0x0b79;
                case 64: goto L_0x0b8d;
                case 65: goto L_0x0ba1;
                case 66: goto L_0x0bb5;
                case 67: goto L_0x0bc9;
                case 68: goto L_0x0bdd;
                default: goto L_0x0661;
            }
        L_0x0661:
            int r3 = r3 + 4
            r0 = r2
            goto L_0x062f
        L_0x0665:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            double r8 = com.google.android.gms.internal.ads.zzbek.zzo(r11, r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0661
        L_0x0678:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = com.google.android.gms.internal.ads.zzbek.zzn(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0661
        L_0x068b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r8)
            r12.zzi(r6, r8)
            goto L_0x0661
        L_0x069e:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0661
        L_0x06b1:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r8)
            r12.zzm(r6, r0)
            goto L_0x0661
        L_0x06c4:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r8)
            r12.zzc(r6, r8)
            goto L_0x0661
        L_0x06d7:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r8)
            r12.zzp(r6, r0)
            goto L_0x0661
        L_0x06eb:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = com.google.android.gms.internal.ads.zzbek.zzm(r11, r8)
            r12.zzf((int) r6, (boolean) r0)
            goto L_0x0661
        L_0x06ff:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbey) r12)
            goto L_0x0661
        L_0x0713:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            com.google.android.gms.internal.ads.zzbdm r5 = r10.zzcq(r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbdm) r5)
            goto L_0x0661
        L_0x072b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            com.google.android.gms.internal.ads.zzbah r0 = (com.google.android.gms.internal.ads.zzbah) r0
            r12.zza((int) r6, (com.google.android.gms.internal.ads.zzbah) r0)
            goto L_0x0661
        L_0x0741:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r8)
            r12.zzn(r6, r0)
            goto L_0x0661
        L_0x0755:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r8)
            r12.zzx(r6, r0)
            goto L_0x0661
        L_0x0769:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r8)
            r12.zzw(r6, r0)
            goto L_0x0661
        L_0x077d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r8)
            r12.zzj(r6, r8)
            goto L_0x0661
        L_0x0791:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.ads.zzbek.zzk(r11, r8)
            r12.zzo(r6, r0)
            goto L_0x0661
        L_0x07a5:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.ads.zzbek.zzl(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0661
        L_0x07b9:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            com.google.android.gms.internal.ads.zzbdm r5 = r10.zzcq(r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbdm) r5)
            goto L_0x0661
        L_0x07d1:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (boolean) r5)
            goto L_0x0661
        L_0x07e6:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (boolean) r5)
            goto L_0x0661
        L_0x07fb:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzc(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0810:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzd(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0825:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzh(r6, r0, r12, r5)
            goto L_0x0661
        L_0x083a:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzf(r6, r0, r12, r5)
            goto L_0x0661
        L_0x084f:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzk(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0864:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzn(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0879:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzbdo.zza((int) r6, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.ads.zzbey) r12)
            goto L_0x0661
        L_0x088d:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzbdm r5 = r10.zzcq(r3)
            com.google.android.gms.internal.ads.zzbdo.zza((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (com.google.android.gms.internal.ads.zzbdm) r5)
            goto L_0x0661
        L_0x08a5:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzbdo.zzb(r6, r0, r12)
            goto L_0x0661
        L_0x08b9:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzi(r6, r0, r12, r5)
            goto L_0x0661
        L_0x08ce:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzm(r6, r0, r12, r5)
            goto L_0x0661
        L_0x08e3:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzl(r6, r0, r12, r5)
            goto L_0x0661
        L_0x08f8:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzg(r6, r0, r12, r5)
            goto L_0x0661
        L_0x090d:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zzj(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0922:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.ads.zzbdo.zze(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0937:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (boolean) r5)
            goto L_0x0661
        L_0x094c:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (boolean) r5)
            goto L_0x0661
        L_0x0961:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzc(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0976:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzd(r6, r0, r12, r5)
            goto L_0x0661
        L_0x098b:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzh(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09a0:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzf(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09b5:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzk(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09ca:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzn(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09df:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzi(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09f4:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzm(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a09:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzl(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a1e:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzg(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a33:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zzj(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a48:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.ads.zzbdo.zze(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a5d:
            int[] r0 = r10.zzdwg
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzbdm r5 = r10.zzcq(r3)
            com.google.android.gms.internal.ads.zzbdo.zzb((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.ads.zzbey) r12, (com.google.android.gms.internal.ads.zzbdm) r5)
            goto L_0x0661
        L_0x0a75:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            r10.zza((com.google.android.gms.internal.ads.zzbey) r12, (int) r6, (java.lang.Object) r0, (int) r3)
            goto L_0x0661
        L_0x0a83:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            double r8 = zzf(r11, r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0661
        L_0x0a97:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = zzg(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0661
        L_0x0aab:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zzi(r6, r8)
            goto L_0x0661
        L_0x0abf:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0661
        L_0x0ad3:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzm(r6, r0)
            goto L_0x0661
        L_0x0ae7:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zzc(r6, r8)
            goto L_0x0661
        L_0x0afb:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzp(r6, r0)
            goto L_0x0661
        L_0x0b0f:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = zzj(r11, r8)
            r12.zzf((int) r6, (boolean) r0)
            goto L_0x0661
        L_0x0b23:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbey) r12)
            goto L_0x0661
        L_0x0b37:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            com.google.android.gms.internal.ads.zzbdm r5 = r10.zzcq(r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbdm) r5)
            goto L_0x0661
        L_0x0b4f:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            com.google.android.gms.internal.ads.zzbah r0 = (com.google.android.gms.internal.ads.zzbah) r0
            r12.zza((int) r6, (com.google.android.gms.internal.ads.zzbah) r0)
            goto L_0x0661
        L_0x0b65:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzn(r6, r0)
            goto L_0x0661
        L_0x0b79:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzx(r6, r0)
            goto L_0x0661
        L_0x0b8d:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzw(r6, r0)
            goto L_0x0661
        L_0x0ba1:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zzj(r6, r8)
            goto L_0x0661
        L_0x0bb5:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzo(r6, r0)
            goto L_0x0661
        L_0x0bc9:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0661
        L_0x0bdd:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r11, r8)
            com.google.android.gms.internal.ads.zzbdm r5 = r10.zzcq(r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.ads.zzbdm) r5)
            goto L_0x0661
        L_0x0bf5:
            r0 = 0
        L_0x0bf6:
            if (r0 == 0) goto L_0x0c0a
            com.google.android.gms.internal.ads.zzbbd<?> r2 = r10.zzdww
            r2.zza((com.google.android.gms.internal.ads.zzbey) r12, (java.util.Map.Entry<?, ?>) r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0bf5
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x0bf6
        L_0x0c0a:
            com.google.android.gms.internal.ads.zzbee<?, ?> r0 = r10.zzdwv
            zza(r0, r11, (com.google.android.gms.internal.ads.zzbey) r12)
        L_0x0c0f:
            return
        L_0x0c10:
            r10.zzb(r11, (com.google.android.gms.internal.ads.zzbey) r12)
            goto L_0x0c0f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbey):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r35, byte[] r36, int r37, int r38, com.google.android.gms.internal.ads.zzbae r39) throws java.io.IOException {
        /*
            r34 = this;
            r0 = r34
            boolean r4 = r0.zzdwo
            if (r4 == 0) goto L_0x024b
            sun.misc.Unsafe r4 = zzdwf
            r15 = r37
        L_0x000a:
            r0 = r38
            if (r15 >= r0) goto L_0x0242
            int r11 = r15 + 1
            byte r13 = r36[r15]
            if (r13 >= 0) goto L_0x0020
            r0 = r36
            r1 = r39
            int r11 = com.google.android.gms.internal.ads.zzbad.zza((int) r13, (byte[]) r0, (int) r11, (com.google.android.gms.internal.ads.zzbae) r1)
            r0 = r39
            int r13 = r0.zzdpl
        L_0x0020:
            int r14 = r13 >>> 3
            r15 = r13 & 7
            r0 = r34
            int r16 = r0.zzcw(r14)
            if (r16 < 0) goto L_0x025c
            r0 = r34
            int[] r5 = r0.zzdwg
            int r6 = r16 + 1
            r28 = r5[r6]
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r5 = r5 & r28
            int r19 = r5 >>> 20
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r28
            long r6 = (long) r5
            r5 = 17
            r0 = r19
            if (r0 > r5) goto L_0x019f
            switch(r19) {
                case 0: goto L_0x0057;
                case 1: goto L_0x0068;
                case 2: goto L_0x0079;
                case 3: goto L_0x0079;
                case 4: goto L_0x008e;
                case 5: goto L_0x00a3;
                case 6: goto L_0x00b5;
                case 7: goto L_0x00c7;
                case 8: goto L_0x00e5;
                case 9: goto L_0x010b;
                case 10: goto L_0x0142;
                case 11: goto L_0x008e;
                case 12: goto L_0x0158;
                case 13: goto L_0x00b5;
                case 14: goto L_0x00a3;
                case 15: goto L_0x016d;
                case 16: goto L_0x0186;
                default: goto L_0x0049;
            }
        L_0x0049:
            r15 = r11
        L_0x004a:
            r14 = r36
            r16 = r38
            r17 = r35
            r18 = r39
            int r15 = zza((int) r13, (byte[]) r14, (int) r15, (int) r16, (java.lang.Object) r17, (com.google.android.gms.internal.ads.zzbae) r18)
            goto L_0x000a
        L_0x0057:
            r5 = 1
            if (r15 != r5) goto L_0x025c
            r0 = r36
            double r8 = com.google.android.gms.internal.ads.zzbad.zzg(r0, r11)
            r0 = r35
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r0, (long) r6, (double) r8)
            int r15 = r11 + 8
            goto L_0x000a
        L_0x0068:
            r5 = 5
            if (r15 != r5) goto L_0x025c
            r0 = r36
            float r5 = com.google.android.gms.internal.ads.zzbad.zzh(r0, r11)
            r0 = r35
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r0, (long) r6, (float) r5)
            int r15 = r11 + 4
            goto L_0x000a
        L_0x0079:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.ads.zzbad.zzb(r0, r11, r1)
            r0 = r39
            long r8 = r0.zzdpm
            r5 = r35
            r4.putLong(r5, r6, r8)
            goto L_0x000a
        L_0x008e:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.ads.zzbad.zza(r0, r11, r1)
            r0 = r39
            int r5 = r0.zzdpl
            r0 = r35
            r4.putInt(r0, r6, r5)
            goto L_0x000a
        L_0x00a3:
            r5 = 1
            if (r15 != r5) goto L_0x025c
            r0 = r36
            long r8 = com.google.android.gms.internal.ads.zzbad.zzf(r0, r11)
            r5 = r35
            r4.putLong(r5, r6, r8)
            int r15 = r11 + 8
            goto L_0x000a
        L_0x00b5:
            r5 = 5
            if (r15 != r5) goto L_0x025c
            r0 = r36
            int r5 = com.google.android.gms.internal.ads.zzbad.zze(r0, r11)
            r0 = r35
            r4.putInt(r0, r6, r5)
            int r15 = r11 + 4
            goto L_0x000a
        L_0x00c7:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.ads.zzbad.zzb(r0, r11, r1)
            r0 = r39
            long r8 = r0.zzdpm
            r10 = 0
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x00e3
            r5 = 1
        L_0x00dc:
            r0 = r35
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r0, (long) r6, (boolean) r5)
            goto L_0x000a
        L_0x00e3:
            r5 = 0
            goto L_0x00dc
        L_0x00e5:
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r5 & r28
            if (r5 != 0) goto L_0x0102
            r0 = r36
            r1 = r39
            int r5 = com.google.android.gms.internal.ads.zzbad.zzc(r0, r11, r1)
        L_0x00f6:
            r0 = r39
            java.lang.Object r8 = r0.zzdpn
            r0 = r35
            r4.putObject(r0, r6, r8)
            r15 = r5
            goto L_0x000a
        L_0x0102:
            r0 = r36
            r1 = r39
            int r5 = com.google.android.gms.internal.ads.zzbad.zzd(r0, r11, r1)
            goto L_0x00f6
        L_0x010b:
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r0 = r34
            r1 = r16
            com.google.android.gms.internal.ads.zzbdm r5 = r0.zzcq(r1)
            r0 = r36
            r1 = r38
            r2 = r39
            int r15 = zza((com.google.android.gms.internal.ads.zzbdm) r5, (byte[]) r0, (int) r11, (int) r1, (com.google.android.gms.internal.ads.zzbae) r2)
            r0 = r35
            java.lang.Object r5 = r4.getObject(r0, r6)
            if (r5 != 0) goto L_0x0133
            r0 = r39
            java.lang.Object r5 = r0.zzdpn
            r0 = r35
            r4.putObject(r0, r6, r5)
            goto L_0x000a
        L_0x0133:
            r0 = r39
            java.lang.Object r8 = r0.zzdpn
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzbbq.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r35
            r4.putObject(r0, r6, r5)
            goto L_0x000a
        L_0x0142:
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.ads.zzbad.zze(r0, r11, r1)
            r0 = r39
            java.lang.Object r5 = r0.zzdpn
            r0 = r35
            r4.putObject(r0, r6, r5)
            goto L_0x000a
        L_0x0158:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.ads.zzbad.zza(r0, r11, r1)
            r0 = r39
            int r5 = r0.zzdpl
            r0 = r35
            r4.putInt(r0, r6, r5)
            goto L_0x000a
        L_0x016d:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.ads.zzbad.zza(r0, r11, r1)
            r0 = r39
            int r5 = r0.zzdpl
            int r5 = com.google.android.gms.internal.ads.zzbaq.zzbu(r5)
            r0 = r35
            r4.putInt(r0, r6, r5)
            goto L_0x000a
        L_0x0186:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.ads.zzbad.zzb(r0, r11, r1)
            r0 = r39
            long r8 = r0.zzdpm
            long r8 = com.google.android.gms.internal.ads.zzbaq.zzl(r8)
            r5 = r35
            r4.putLong(r5, r6, r8)
            goto L_0x000a
        L_0x019f:
            r5 = 27
            r0 = r19
            if (r0 != r5) goto L_0x01e0
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r0 = r35
            java.lang.Object r5 = r4.getObject(r0, r6)
            com.google.android.gms.internal.ads.zzbbt r5 = (com.google.android.gms.internal.ads.zzbbt) r5
            boolean r8 = r5.zzaay()
            if (r8 != 0) goto L_0x025f
            int r8 = r5.size()
            if (r8 != 0) goto L_0x01dd
            r8 = 10
        L_0x01be:
            com.google.android.gms.internal.ads.zzbbt r10 = r5.zzbm(r8)
            r0 = r35
            r4.putObject(r0, r6, r10)
        L_0x01c7:
            r0 = r34
            r1 = r16
            com.google.android.gms.internal.ads.zzbdm r5 = r0.zzcq(r1)
            r6 = r13
            r7 = r36
            r8 = r11
            r9 = r38
            r11 = r39
            int r15 = zza((com.google.android.gms.internal.ads.zzbdm<?>) r5, (int) r6, (byte[]) r7, (int) r8, (int) r9, (com.google.android.gms.internal.ads.zzbbt<?>) r10, (com.google.android.gms.internal.ads.zzbae) r11)
            goto L_0x000a
        L_0x01dd:
            int r8 = r8 << 1
            goto L_0x01be
        L_0x01e0:
            r5 = 49
            r0 = r19
            if (r0 > r5) goto L_0x01ff
            r0 = r28
            long r0 = (long) r0
            r17 = r0
            r8 = r34
            r9 = r35
            r10 = r36
            r12 = r38
            r20 = r6
            r22 = r39
            int r15 = r8.zza(r9, (byte[]) r10, (int) r11, (int) r12, (int) r13, (int) r14, (int) r15, (int) r16, (long) r17, (int) r19, (long) r20, (com.google.android.gms.internal.ads.zzbae) r22)
            if (r15 != r11) goto L_0x000a
            goto L_0x004a
        L_0x01ff:
            r5 = 50
            r0 = r19
            if (r0 != r5) goto L_0x0222
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r17 = r34
            r18 = r35
            r19 = r36
            r20 = r11
            r21 = r38
            r22 = r16
            r23 = r14
            r24 = r6
            r26 = r39
            int r15 = r17.zza(r18, r19, r20, r21, r22, r23, r24, r26)
            if (r15 != r11) goto L_0x000a
            goto L_0x004a
        L_0x0222:
            r20 = r34
            r21 = r35
            r22 = r36
            r23 = r11
            r24 = r38
            r25 = r13
            r26 = r14
            r27 = r15
            r29 = r19
            r30 = r6
            r32 = r16
            r33 = r39
            int r15 = r20.zza(r21, (byte[]) r22, (int) r23, (int) r24, (int) r25, (int) r26, (int) r27, (int) r28, (int) r29, (long) r30, (int) r32, (com.google.android.gms.internal.ads.zzbae) r33)
            if (r15 != r11) goto L_0x000a
            goto L_0x004a
        L_0x0242:
            r0 = r38
            if (r15 == r0) goto L_0x025b
            com.google.android.gms.internal.ads.zzbbu r4 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r4
        L_0x024b:
            r9 = 0
            r4 = r34
            r5 = r35
            r6 = r36
            r7 = r37
            r8 = r38
            r10 = r39
            r4.zza(r5, (byte[]) r6, (int) r7, (int) r8, (int) r9, (com.google.android.gms.internal.ads.zzbae) r10)
        L_0x025b:
            return
        L_0x025c:
            r15 = r11
            goto L_0x004a
        L_0x025f:
            r10 = r5
            goto L_0x01c7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzbae):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x004b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzaa(T r15) {
        /*
            r14 = this;
            int[] r0 = r14.zzdwq
            if (r0 == 0) goto L_0x0009
            int[] r0 = r14.zzdwq
            int r0 = r0.length
            if (r0 != 0) goto L_0x000b
        L_0x0009:
            r0 = 1
        L_0x000a:
            return r0
        L_0x000b:
            r4 = -1
            r2 = 0
            int[] r6 = r14.zzdwq
            int r7 = r6.length
            r0 = 0
            r5 = r0
        L_0x0012:
            if (r5 >= r7) goto L_0x010a
            r8 = r6[r5]
            int r9 = r14.zzcw(r8)
            int r10 = r14.zzct(r9)
            r0 = 0
            boolean r1 = r14.zzdwo
            if (r1 != 0) goto L_0x0120
            int[] r0 = r14.zzdwg
            int r1 = r9 + 2
            r0 = r0[r1]
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r0 & r1
            r1 = 1
            int r0 = r0 >>> 20
            int r0 = r1 << r0
            if (r3 == r4) goto L_0x0120
            sun.misc.Unsafe r1 = zzdwf
            long r12 = (long) r3
            int r2 = r1.getInt(r15, r12)
            r1 = r0
        L_0x003d:
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            r0 = r0 & r10
            if (r0 == 0) goto L_0x004d
            r0 = 1
        L_0x0043:
            if (r0 == 0) goto L_0x004f
            boolean r0 = r14.zza(r15, (int) r9, (int) r2, (int) r1)
            if (r0 != 0) goto L_0x004f
            r0 = 0
            goto L_0x000a
        L_0x004d:
            r0 = 0
            goto L_0x0043
        L_0x004f:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r10
            int r0 = r0 >>> 20
            switch(r0) {
                case 9: goto L_0x005c;
                case 17: goto L_0x005c;
                case 27: goto L_0x006e;
                case 49: goto L_0x006e;
                case 50: goto L_0x00b2;
                case 60: goto L_0x009f;
                case 68: goto L_0x009f;
                default: goto L_0x0057;
            }
        L_0x0057:
            int r0 = r5 + 1
            r5 = r0
            r4 = r3
            goto L_0x0012
        L_0x005c:
            boolean r0 = r14.zza(r15, (int) r9, (int) r2, (int) r1)
            if (r0 == 0) goto L_0x0057
            com.google.android.gms.internal.ads.zzbdm r0 = r14.zzcq(r9)
            boolean r0 = zza((java.lang.Object) r15, (int) r10, (com.google.android.gms.internal.ads.zzbdm) r0)
            if (r0 != 0) goto L_0x0057
            r0 = 0
            goto L_0x000a
        L_0x006e:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r10
            long r0 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbek.zzp(r15, r0)
            java.util.List r0 = (java.util.List) r0
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x009d
            com.google.android.gms.internal.ads.zzbdm r4 = r14.zzcq(r9)
            r1 = 0
        L_0x0084:
            int r8 = r0.size()
            if (r1 >= r8) goto L_0x009d
            java.lang.Object r8 = r0.get(r1)
            boolean r8 = r4.zzaa(r8)
            if (r8 != 0) goto L_0x009a
            r0 = 0
        L_0x0095:
            if (r0 != 0) goto L_0x0057
            r0 = 0
            goto L_0x000a
        L_0x009a:
            int r1 = r1 + 1
            goto L_0x0084
        L_0x009d:
            r0 = 1
            goto L_0x0095
        L_0x009f:
            boolean r0 = r14.zza(r15, (int) r8, (int) r9)
            if (r0 == 0) goto L_0x0057
            com.google.android.gms.internal.ads.zzbdm r0 = r14.zzcq(r9)
            boolean r0 = zza((java.lang.Object) r15, (int) r10, (com.google.android.gms.internal.ads.zzbdm) r0)
            if (r0 != 0) goto L_0x0057
            r0 = 0
            goto L_0x000a
        L_0x00b2:
            com.google.android.gms.internal.ads.zzbcp r0 = r14.zzdwx
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r10
            long r10 = (long) r1
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbek.zzp(r15, r10)
            java.util.Map r1 = r0.zzt(r1)
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x0108
            java.lang.Object r0 = r14.zzcr(r9)
            com.google.android.gms.internal.ads.zzbcp r4 = r14.zzdwx
            com.google.android.gms.internal.ads.zzbcn r0 = r4.zzx(r0)
            com.google.android.gms.internal.ads.zzbes r0 = r0.zzdwa
            com.google.android.gms.internal.ads.zzbex r0 = r0.zzagl()
            com.google.android.gms.internal.ads.zzbex r4 = com.google.android.gms.internal.ads.zzbex.MESSAGE
            if (r0 != r4) goto L_0x0108
            r0 = 0
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x00e4:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0108
            java.lang.Object r4 = r1.next()
            if (r0 != 0) goto L_0x00fc
            com.google.android.gms.internal.ads.zzbdg r0 = com.google.android.gms.internal.ads.zzbdg.zzaeo()
            java.lang.Class r8 = r4.getClass()
            com.google.android.gms.internal.ads.zzbdm r0 = r0.zze(r8)
        L_0x00fc:
            boolean r4 = r0.zzaa(r4)
            if (r4 != 0) goto L_0x00e4
            r0 = 0
        L_0x0103:
            if (r0 != 0) goto L_0x0057
            r0 = 0
            goto L_0x000a
        L_0x0108:
            r0 = 1
            goto L_0x0103
        L_0x010a:
            boolean r0 = r14.zzdwm
            if (r0 == 0) goto L_0x011d
            com.google.android.gms.internal.ads.zzbbd<?> r0 = r14.zzdww
            com.google.android.gms.internal.ads.zzbbg r0 = r0.zzm(r15)
            boolean r0 = r0.isInitialized()
            if (r0 != 0) goto L_0x011d
            r0 = 0
            goto L_0x000a
        L_0x011d:
            r0 = 1
            goto L_0x000a
        L_0x0120:
            r1 = r0
            r3 = r4
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zzaa(java.lang.Object):boolean");
    }

    public final void zzc(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzdwg.length; i += 4) {
            int zzct = zzct(i);
            long j = (long) (1048575 & zzct);
            int i2 = this.zzdwg[i];
            switch ((zzct & 267386880) >>> 20) {
                case 0:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzo(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 1:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzn(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 2:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 3:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 4:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 5:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 6:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 7:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzm(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 8:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzp(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzp(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 11:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 12:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 13:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 14:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 15:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 16:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 17:
                    zza(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzdwu.zza(t, t2, j);
                    break;
                case 50:
                    zzbdo.zza(this.zzdwx, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zza(t2, i2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzp(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zza(t2, i2, i)) {
                        break;
                    } else {
                        zzbek.zza((Object) t, j, zzbek.zzp(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (!this.zzdwo) {
            zzbdo.zza(this.zzdwv, t, t2);
            if (this.zzdwm) {
                zzbdo.zza(this.zzdww, t, t2);
            }
        }
    }

    public final void zzo(T t) {
        if (this.zzdwr != null) {
            for (int zzct : this.zzdwr) {
                long zzct2 = (long) (zzct(zzct) & 1048575);
                Object zzp = zzbek.zzp(t, zzct2);
                if (zzp != null) {
                    zzbek.zza((Object) t, zzct2, this.zzdwx.zzv(zzp));
                }
            }
        }
        if (this.zzdws != null) {
            for (int i : this.zzdws) {
                this.zzdwu.zzb(t, (long) i);
            }
        }
        this.zzdwv.zzo(t);
        if (this.zzdwm) {
            this.zzdww.zzo(t);
        }
    }

    public final int zzy(T t) {
        int i;
        if (this.zzdwo) {
            Unsafe unsafe = zzdwf;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= this.zzdwg.length) {
                    return zza(this.zzdwv, t) + i2;
                }
                int zzct = zzct(i4);
                int i5 = (267386880 & zzct) >>> 20;
                int i6 = this.zzdwg[i4];
                long j = (long) (zzct & 1048575);
                int i7 = (i5 < zzbbj.DOUBLE_LIST_PACKED.id() || i5 > zzbbj.SINT64_LIST_PACKED.id()) ? 0 : this.zzdwg[i4 + 2] & 1048575;
                switch (i5) {
                    case 0:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzb(i6, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzb(i6, 0.0f);
                            break;
                        }
                    case 2:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzd(i6, zzbek.zzl(t, j));
                            break;
                        }
                    case 3:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zze(i6, zzbek.zzl(t, j));
                            break;
                        }
                    case 4:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzq(i6, zzbek.zzk(t, j));
                            break;
                        }
                    case 5:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzg(i6, 0);
                            break;
                        }
                    case 6:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzt(i6, 0);
                            break;
                        }
                    case 7:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzg(i6, true);
                            break;
                        }
                    case 8:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            Object zzp = zzbek.zzp(t, j);
                            if (!(zzp instanceof zzbah)) {
                                i2 += zzbav.zzg(i6, (String) zzp);
                                break;
                            } else {
                                i2 += zzbav.zzc(i6, (zzbah) zzp);
                                break;
                            }
                        }
                    case 9:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbdo.zzc(i6, zzbek.zzp(t, j), zzcq(i4));
                            break;
                        }
                    case 10:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzc(i6, (zzbah) zzbek.zzp(t, j));
                            break;
                        }
                    case 11:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzr(i6, zzbek.zzk(t, j));
                            break;
                        }
                    case 12:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzv(i6, zzbek.zzk(t, j));
                            break;
                        }
                    case 13:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzu(i6, 0);
                            break;
                        }
                    case 14:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzh(i6, 0);
                            break;
                        }
                    case 15:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzs(i6, zzbek.zzk(t, j));
                            break;
                        }
                    case 16:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzf(i6, zzbek.zzl(t, j));
                            break;
                        }
                    case 17:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzc(i6, (zzbcu) zzbek.zzp(t, j), zzcq(i4));
                            break;
                        }
                    case 18:
                        i2 += zzbdo.zzw(i6, zze(t, j), false);
                        break;
                    case 19:
                        i2 += zzbdo.zzv(i6, zze(t, j), false);
                        break;
                    case 20:
                        i2 += zzbdo.zzo(i6, zze(t, j), false);
                        break;
                    case 21:
                        i2 += zzbdo.zzp(i6, zze(t, j), false);
                        break;
                    case 22:
                        i2 += zzbdo.zzs(i6, zze(t, j), false);
                        break;
                    case 23:
                        i2 += zzbdo.zzw(i6, zze(t, j), false);
                        break;
                    case 24:
                        i2 += zzbdo.zzv(i6, zze(t, j), false);
                        break;
                    case 25:
                        i2 += zzbdo.zzx(i6, zze(t, j), false);
                        break;
                    case 26:
                        i2 += zzbdo.zzc(i6, zze(t, j));
                        break;
                    case 27:
                        i2 += zzbdo.zzc(i6, (List<?>) zze(t, j), zzcq(i4));
                        break;
                    case 28:
                        i2 += zzbdo.zzd(i6, (List<zzbah>) zze(t, j));
                        break;
                    case 29:
                        i2 += zzbdo.zzt(i6, zze(t, j), false);
                        break;
                    case 30:
                        i2 += zzbdo.zzr(i6, zze(t, j), false);
                        break;
                    case 31:
                        i2 += zzbdo.zzv(i6, zze(t, j), false);
                        break;
                    case 32:
                        i2 += zzbdo.zzw(i6, zze(t, j), false);
                        break;
                    case 33:
                        i2 += zzbdo.zzu(i6, zze(t, j), false);
                        break;
                    case 34:
                        i2 += zzbdo.zzq(i6, zze(t, j), false);
                        break;
                    case 35:
                        int zzan = zzbdo.zzan((List) unsafe.getObject(t, j));
                        if (zzan > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzan);
                            }
                            i2 += zzan + zzbav.zzcd(i6) + zzbav.zzcf(zzan);
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzam = zzbdo.zzam((List) unsafe.getObject(t, j));
                        if (zzam > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzam);
                            }
                            i2 += zzam + zzbav.zzcd(i6) + zzbav.zzcf(zzam);
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zzaf = zzbdo.zzaf((List) unsafe.getObject(t, j));
                        if (zzaf > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzaf);
                            }
                            i2 += zzaf + zzbav.zzcd(i6) + zzbav.zzcf(zzaf);
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzag = zzbdo.zzag((List) unsafe.getObject(t, j));
                        if (zzag > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzag);
                            }
                            i2 += zzag + zzbav.zzcd(i6) + zzbav.zzcf(zzag);
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zzaj = zzbdo.zzaj((List) unsafe.getObject(t, j));
                        if (zzaj > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzaj);
                            }
                            i2 += zzaj + zzbav.zzcd(i6) + zzbav.zzcf(zzaj);
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzan2 = zzbdo.zzan((List) unsafe.getObject(t, j));
                        if (zzan2 > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzan2);
                            }
                            i2 += zzan2 + zzbav.zzcd(i6) + zzbav.zzcf(zzan2);
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzam2 = zzbdo.zzam((List) unsafe.getObject(t, j));
                        if (zzam2 > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzam2);
                            }
                            i2 += zzam2 + zzbav.zzcd(i6) + zzbav.zzcf(zzam2);
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzao = zzbdo.zzao((List) unsafe.getObject(t, j));
                        if (zzao > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzao);
                            }
                            i2 += zzao + zzbav.zzcd(i6) + zzbav.zzcf(zzao);
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzak = zzbdo.zzak((List) unsafe.getObject(t, j));
                        if (zzak > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzak);
                            }
                            i2 += zzak + zzbav.zzcd(i6) + zzbav.zzcf(zzak);
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzai = zzbdo.zzai((List) unsafe.getObject(t, j));
                        if (zzai > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzai);
                            }
                            i2 += zzai + zzbav.zzcd(i6) + zzbav.zzcf(zzai);
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzam3 = zzbdo.zzam((List) unsafe.getObject(t, j));
                        if (zzam3 > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzam3);
                            }
                            i2 += zzam3 + zzbav.zzcd(i6) + zzbav.zzcf(zzam3);
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzan3 = zzbdo.zzan((List) unsafe.getObject(t, j));
                        if (zzan3 > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzan3);
                            }
                            i2 += zzan3 + zzbav.zzcd(i6) + zzbav.zzcf(zzan3);
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzal = zzbdo.zzal((List) unsafe.getObject(t, j));
                        if (zzal > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzal);
                            }
                            i2 += zzal + zzbav.zzcd(i6) + zzbav.zzcf(zzal);
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzah = zzbdo.zzah((List) unsafe.getObject(t, j));
                        if (zzah > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i7, zzah);
                            }
                            i2 += zzah + zzbav.zzcd(i6) + zzbav.zzcf(zzah);
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i2 += zzbdo.zzd(i6, zze(t, j), zzcq(i4));
                        break;
                    case 50:
                        i2 += this.zzdwx.zzb(i6, zzbek.zzp(t, j), zzcr(i4));
                        break;
                    case 51:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzb(i6, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzb(i6, 0.0f);
                            break;
                        }
                    case 53:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzd(i6, zzi(t, j));
                            break;
                        }
                    case 54:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zze(i6, zzi(t, j));
                            break;
                        }
                    case 55:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzq(i6, zzh(t, j));
                            break;
                        }
                    case 56:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzg(i6, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzt(i6, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzg(i6, true);
                            break;
                        }
                    case 59:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            Object zzp2 = zzbek.zzp(t, j);
                            if (!(zzp2 instanceof zzbah)) {
                                i2 += zzbav.zzg(i6, (String) zzp2);
                                break;
                            } else {
                                i2 += zzbav.zzc(i6, (zzbah) zzp2);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbdo.zzc(i6, zzbek.zzp(t, j), zzcq(i4));
                            break;
                        }
                    case 61:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzc(i6, (zzbah) zzbek.zzp(t, j));
                            break;
                        }
                    case 62:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzr(i6, zzh(t, j));
                            break;
                        }
                    case 63:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzv(i6, zzh(t, j));
                            break;
                        }
                    case 64:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzu(i6, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzh(i6, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzs(i6, zzh(t, j));
                            break;
                        }
                    case 67:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzf(i6, zzi(t, j));
                            break;
                        }
                    case 68:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbav.zzc(i6, (zzbcu) zzbek.zzp(t, j), zzcq(i4));
                            break;
                        }
                }
                i3 = i4 + 4;
            }
        } else {
            int i8 = 0;
            Unsafe unsafe2 = zzdwf;
            int i9 = -1;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                int i12 = i11;
                if (i12 < this.zzdwg.length) {
                    int zzct2 = zzct(i12);
                    int i13 = this.zzdwg[i12];
                    int i14 = (267386880 & zzct2) >>> 20;
                    int i15 = 0;
                    if (i14 <= 17) {
                        i = this.zzdwg[i12 + 2];
                        int i16 = 1048575 & i;
                        int i17 = 1 << (i >>> 20);
                        if (i16 != i9) {
                            i10 = unsafe2.getInt(t, (long) i16);
                            i9 = i16;
                        }
                        i15 = i17;
                    } else {
                        i = (!this.zzdwp || i14 < zzbbj.DOUBLE_LIST_PACKED.id() || i14 > zzbbj.SINT64_LIST_PACKED.id()) ? 0 : this.zzdwg[i12 + 2] & 1048575;
                    }
                    long j2 = (long) (1048575 & zzct2);
                    switch (i14) {
                        case 0:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzb(i13, 0.0d);
                                break;
                            }
                        case 1:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzb(i13, 0.0f);
                                break;
                            }
                        case 2:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzd(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 3:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zze(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 4:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzq(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 5:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzg(i13, 0);
                                break;
                            }
                        case 6:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzt(i13, 0);
                                break;
                            }
                        case 7:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzg(i13, true);
                                break;
                            }
                        case 8:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                Object object = unsafe2.getObject(t, j2);
                                if (!(object instanceof zzbah)) {
                                    i8 += zzbav.zzg(i13, (String) object);
                                    break;
                                } else {
                                    i8 += zzbav.zzc(i13, (zzbah) object);
                                    break;
                                }
                            }
                        case 9:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbdo.zzc(i13, unsafe2.getObject(t, j2), zzcq(i12));
                                break;
                            }
                        case 10:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzc(i13, (zzbah) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 11:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzr(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 12:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzv(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 13:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzu(i13, 0);
                                break;
                            }
                        case 14:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzh(i13, 0);
                                break;
                            }
                        case 15:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzs(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 16:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzf(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 17:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbav.zzc(i13, (zzbcu) unsafe2.getObject(t, j2), zzcq(i12));
                                break;
                            }
                        case 18:
                            i8 += zzbdo.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 19:
                            i8 += zzbdo.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 20:
                            i8 += zzbdo.zzo(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 21:
                            i8 += zzbdo.zzp(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 22:
                            i8 += zzbdo.zzs(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 23:
                            i8 += zzbdo.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 24:
                            i8 += zzbdo.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 25:
                            i8 += zzbdo.zzx(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 26:
                            i8 += zzbdo.zzc(i13, (List) unsafe2.getObject(t, j2));
                            break;
                        case 27:
                            i8 += zzbdo.zzc(i13, (List<?>) (List) unsafe2.getObject(t, j2), zzcq(i12));
                            break;
                        case 28:
                            i8 += zzbdo.zzd(i13, (List<zzbah>) (List) unsafe2.getObject(t, j2));
                            break;
                        case 29:
                            i8 += zzbdo.zzt(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 30:
                            i8 += zzbdo.zzr(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 31:
                            i8 += zzbdo.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 32:
                            i8 += zzbdo.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 33:
                            i8 += zzbdo.zzu(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 34:
                            i8 += zzbdo.zzq(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 35:
                            int zzan4 = zzbdo.zzan((List) unsafe2.getObject(t, j2));
                            if (zzan4 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzan4);
                                }
                                i8 += zzan4 + zzbav.zzcd(i13) + zzbav.zzcf(zzan4);
                                break;
                            } else {
                                break;
                            }
                        case 36:
                            int zzam4 = zzbdo.zzam((List) unsafe2.getObject(t, j2));
                            if (zzam4 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzam4);
                                }
                                i8 += zzam4 + zzbav.zzcd(i13) + zzbav.zzcf(zzam4);
                                break;
                            } else {
                                break;
                            }
                        case 37:
                            int zzaf2 = zzbdo.zzaf((List) unsafe2.getObject(t, j2));
                            if (zzaf2 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzaf2);
                                }
                                i8 += zzaf2 + zzbav.zzcd(i13) + zzbav.zzcf(zzaf2);
                                break;
                            } else {
                                break;
                            }
                        case 38:
                            int zzag2 = zzbdo.zzag((List) unsafe2.getObject(t, j2));
                            if (zzag2 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzag2);
                                }
                                i8 += zzag2 + zzbav.zzcd(i13) + zzbav.zzcf(zzag2);
                                break;
                            } else {
                                break;
                            }
                        case 39:
                            int zzaj2 = zzbdo.zzaj((List) unsafe2.getObject(t, j2));
                            if (zzaj2 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzaj2);
                                }
                                i8 += zzaj2 + zzbav.zzcd(i13) + zzbav.zzcf(zzaj2);
                                break;
                            } else {
                                break;
                            }
                        case 40:
                            int zzan5 = zzbdo.zzan((List) unsafe2.getObject(t, j2));
                            if (zzan5 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzan5);
                                }
                                i8 += zzan5 + zzbav.zzcd(i13) + zzbav.zzcf(zzan5);
                                break;
                            } else {
                                break;
                            }
                        case 41:
                            int zzam5 = zzbdo.zzam((List) unsafe2.getObject(t, j2));
                            if (zzam5 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzam5);
                                }
                                i8 += zzam5 + zzbav.zzcd(i13) + zzbav.zzcf(zzam5);
                                break;
                            } else {
                                break;
                            }
                        case 42:
                            int zzao2 = zzbdo.zzao((List) unsafe2.getObject(t, j2));
                            if (zzao2 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzao2);
                                }
                                i8 += zzao2 + zzbav.zzcd(i13) + zzbav.zzcf(zzao2);
                                break;
                            } else {
                                break;
                            }
                        case 43:
                            int zzak2 = zzbdo.zzak((List) unsafe2.getObject(t, j2));
                            if (zzak2 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzak2);
                                }
                                i8 += zzak2 + zzbav.zzcd(i13) + zzbav.zzcf(zzak2);
                                break;
                            } else {
                                break;
                            }
                        case 44:
                            int zzai2 = zzbdo.zzai((List) unsafe2.getObject(t, j2));
                            if (zzai2 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzai2);
                                }
                                i8 += zzai2 + zzbav.zzcd(i13) + zzbav.zzcf(zzai2);
                                break;
                            } else {
                                break;
                            }
                        case 45:
                            int zzam6 = zzbdo.zzam((List) unsafe2.getObject(t, j2));
                            if (zzam6 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzam6);
                                }
                                i8 += zzam6 + zzbav.zzcd(i13) + zzbav.zzcf(zzam6);
                                break;
                            } else {
                                break;
                            }
                        case 46:
                            int zzan6 = zzbdo.zzan((List) unsafe2.getObject(t, j2));
                            if (zzan6 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzan6);
                                }
                                i8 += zzan6 + zzbav.zzcd(i13) + zzbav.zzcf(zzan6);
                                break;
                            } else {
                                break;
                            }
                        case 47:
                            int zzal2 = zzbdo.zzal((List) unsafe2.getObject(t, j2));
                            if (zzal2 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzal2);
                                }
                                i8 += zzal2 + zzbav.zzcd(i13) + zzbav.zzcf(zzal2);
                                break;
                            } else {
                                break;
                            }
                        case 48:
                            int zzah2 = zzbdo.zzah((List) unsafe2.getObject(t, j2));
                            if (zzah2 > 0) {
                                if (this.zzdwp) {
                                    unsafe2.putInt(t, (long) i, zzah2);
                                }
                                i8 += zzah2 + zzbav.zzcd(i13) + zzbav.zzcf(zzah2);
                                break;
                            } else {
                                break;
                            }
                        case 49:
                            i8 += zzbdo.zzd(i13, (List) unsafe2.getObject(t, j2), zzcq(i12));
                            break;
                        case 50:
                            i8 += this.zzdwx.zzb(i13, unsafe2.getObject(t, j2), zzcr(i12));
                            break;
                        case 51:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzb(i13, 0.0d);
                                break;
                            }
                        case 52:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzb(i13, 0.0f);
                                break;
                            }
                        case 53:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzd(i13, zzi(t, j2));
                                break;
                            }
                        case 54:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zze(i13, zzi(t, j2));
                                break;
                            }
                        case 55:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzq(i13, zzh(t, j2));
                                break;
                            }
                        case 56:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzg(i13, 0);
                                break;
                            }
                        case 57:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzt(i13, 0);
                                break;
                            }
                        case 58:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzg(i13, true);
                                break;
                            }
                        case 59:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                Object object2 = unsafe2.getObject(t, j2);
                                if (!(object2 instanceof zzbah)) {
                                    i8 += zzbav.zzg(i13, (String) object2);
                                    break;
                                } else {
                                    i8 += zzbav.zzc(i13, (zzbah) object2);
                                    break;
                                }
                            }
                        case 60:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbdo.zzc(i13, unsafe2.getObject(t, j2), zzcq(i12));
                                break;
                            }
                        case 61:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzc(i13, (zzbah) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 62:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzr(i13, zzh(t, j2));
                                break;
                            }
                        case 63:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzv(i13, zzh(t, j2));
                                break;
                            }
                        case 64:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzu(i13, 0);
                                break;
                            }
                        case 65:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzh(i13, 0);
                                break;
                            }
                        case 66:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzs(i13, zzh(t, j2));
                                break;
                            }
                        case 67:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzf(i13, zzi(t, j2));
                                break;
                            }
                        case 68:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbav.zzc(i13, (zzbcu) unsafe2.getObject(t, j2), zzcq(i12));
                                break;
                            }
                    }
                    i11 = i12 + 4;
                } else {
                    int zza = zza(this.zzdwv, t) + i8;
                    return this.zzdwm ? zza + this.zzdww.zzm(t).zzacw() : zza;
                }
            }
        }
    }
}
