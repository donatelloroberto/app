package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class zzso<T> implements zzsz<T> {
    private static final int[] zzbcw = new int[0];
    private static final Unsafe zzbcx = zztx.zzro();
    private final int[] zzbcy;
    private final Object[] zzbcz;
    private final int zzbda;
    private final int zzbdb;
    private final zzsk zzbdc;
    private final boolean zzbdd;
    private final boolean zzbde;
    private final boolean zzbdf;
    private final boolean zzbdg;
    private final int[] zzbdh;
    private final int zzbdi;
    private final int zzbdj;
    private final zzsr zzbdk;
    private final zzru zzbdl;
    private final zztr<?, ?> zzbdm;
    private final zzqq<?> zzbdn;
    private final zzsf zzbdo;

    private zzso(int[] iArr, Object[] objArr, int i, int i2, zzsk zzsk, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzsr zzsr, zzru zzru, zztr<?, ?> zztr, zzqq<?> zzqq, zzsf zzsf) {
        this.zzbcy = iArr;
        this.zzbcz = objArr;
        this.zzbda = i;
        this.zzbdb = i2;
        this.zzbde = zzsk instanceof zzrc;
        this.zzbdf = z;
        this.zzbdd = zzqq != null && zzqq.zze(zzsk);
        this.zzbdg = false;
        this.zzbdh = iArr2;
        this.zzbdi = i3;
        this.zzbdj = i4;
        this.zzbdk = zzsr;
        this.zzbdl = zzru;
        this.zzbdm = zztr;
        this.zzbdn = zzqq;
        this.zzbdc = zzsk;
        this.zzbdo = zzsf;
    }

    static <T> zzso<T> zza(Class<T> cls, zzsi zzsi, zzsr zzsr, zzru zzru, zztr<?, ?> zztr, zzqq<?> zzqq, zzsf zzsf) {
        char c;
        int i;
        int i2;
        int i3;
        int i4;
        char c2;
        char c3;
        char c4;
        int i5;
        char c5;
        int[] iArr;
        int i6;
        char c6;
        char c7;
        char charAt;
        char charAt2;
        char charAt3;
        char charAt4;
        char charAt5;
        char charAt6;
        char charAt7;
        char charAt8;
        int i7;
        int i8;
        char c8;
        int i9;
        int i10;
        char c9;
        int i11;
        int i12;
        int i13;
        char c10;
        int i14;
        char c11;
        int i15;
        Field zza;
        char charAt9;
        char c12;
        int i16;
        int i17;
        Field zza2;
        Field zza3;
        int i18;
        char charAt10;
        char charAt11;
        char charAt12;
        int i19;
        char charAt13;
        char charAt14;
        if (zzsi instanceof zzsx) {
            zzsx zzsx = (zzsx) zzsi;
            boolean z = zzsx.zzql() == zzrc.zze.zzbba;
            String zzqt = zzsx.zzqt();
            int length = zzqt.length();
            int i20 = 1;
            char charAt15 = zzqt.charAt(0);
            if (charAt15 >= 55296) {
                char c13 = charAt15 & 8191;
                int i21 = 13;
                while (true) {
                    i = i20 + 1;
                    charAt14 = zzqt.charAt(i20);
                    if (charAt14 < 55296) {
                        break;
                    }
                    c13 |= (charAt14 & 8191) << i21;
                    i21 += 13;
                    i20 = i;
                }
                c = (charAt14 << i21) | c13;
            } else {
                c = charAt15;
                i = 1;
            }
            int i22 = i + 1;
            char charAt16 = zzqt.charAt(i);
            if (charAt16 >= 55296) {
                char c14 = charAt16 & 8191;
                int i23 = 13;
                while (true) {
                    i19 = i22 + 1;
                    charAt13 = zzqt.charAt(i22);
                    if (charAt13 < 55296) {
                        break;
                    }
                    c14 |= (charAt13 & 8191) << i23;
                    i23 += 13;
                    i22 = i19;
                }
                charAt16 = (charAt13 << i23) | c14;
                i2 = i19;
            } else {
                i2 = i22;
            }
            if (charAt16 == 0) {
                c6 = 0;
                c2 = 0;
                c3 = 0;
                c4 = 0;
                c5 = 0;
                iArr = zzbcw;
                i6 = 0;
                c7 = 0;
            } else {
                int i24 = i3 + 1;
                char charAt17 = zzqt.charAt(i3);
                if (charAt17 >= 55296) {
                    char c15 = charAt17 & 8191;
                    int i25 = 13;
                    while (true) {
                        i4 = i24 + 1;
                        charAt8 = zzqt.charAt(i24);
                        if (charAt8 < 55296) {
                            break;
                        }
                        c15 |= (charAt8 & 8191) << i25;
                        i25 += 13;
                        i24 = i4;
                    }
                    charAt17 = (charAt8 << i25) | c15;
                } else {
                    i4 = i24;
                }
                int i26 = i4 + 1;
                char charAt18 = zzqt.charAt(i4);
                if (charAt18 >= 55296) {
                    char c16 = charAt18 & 8191;
                    int i27 = 13;
                    while (true) {
                        int i28 = i26;
                        i26 = i28 + 1;
                        charAt7 = zzqt.charAt(i28);
                        if (charAt7 < 55296) {
                            break;
                        }
                        c16 |= (charAt7 & 8191) << i27;
                        i27 += 13;
                    }
                    charAt18 = (charAt7 << i27) | c16;
                }
                int i29 = i26 + 1;
                char charAt19 = zzqt.charAt(i26);
                if (charAt19 >= 55296) {
                    char c17 = charAt19 & 8191;
                    int i30 = 13;
                    while (true) {
                        int i31 = i29;
                        i29 = i31 + 1;
                        charAt6 = zzqt.charAt(i31);
                        if (charAt6 < 55296) {
                            break;
                        }
                        c17 |= (charAt6 & 8191) << i30;
                        i30 += 13;
                    }
                    charAt19 = (charAt6 << i30) | c17;
                }
                int i32 = i29 + 1;
                char charAt20 = zzqt.charAt(i29);
                if (charAt20 >= 55296) {
                    char c18 = charAt20 & 8191;
                    int i33 = 13;
                    while (true) {
                        int i34 = i32;
                        i32 = i34 + 1;
                        charAt5 = zzqt.charAt(i34);
                        if (charAt5 < 55296) {
                            break;
                        }
                        c18 |= (charAt5 & 8191) << i33;
                        i33 += 13;
                    }
                    c2 = (charAt5 << i33) | c18;
                } else {
                    c2 = charAt20;
                }
                int i35 = i32 + 1;
                char charAt21 = zzqt.charAt(i32);
                if (charAt21 >= 55296) {
                    char c19 = charAt21 & 8191;
                    int i36 = 13;
                    while (true) {
                        int i37 = i35;
                        i35 = i37 + 1;
                        charAt4 = zzqt.charAt(i37);
                        if (charAt4 < 55296) {
                            break;
                        }
                        c19 |= (charAt4 & 8191) << i36;
                        i36 += 13;
                    }
                    c3 = (charAt4 << i36) | c19;
                } else {
                    c3 = charAt21;
                }
                int i38 = i35 + 1;
                char charAt22 = zzqt.charAt(i35);
                if (charAt22 >= 55296) {
                    char c20 = charAt22 & 8191;
                    int i39 = 13;
                    while (true) {
                        int i40 = i38;
                        i38 = i40 + 1;
                        charAt3 = zzqt.charAt(i40);
                        if (charAt3 < 55296) {
                            break;
                        }
                        c20 |= (charAt3 & 8191) << i39;
                        i39 += 13;
                    }
                    c4 = (charAt3 << i39) | c20;
                } else {
                    c4 = charAt22;
                }
                int i41 = i38 + 1;
                char charAt23 = zzqt.charAt(i38);
                if (charAt23 >= 55296) {
                    char c21 = charAt23 & 8191;
                    int i42 = 13;
                    while (true) {
                        i5 = i41 + 1;
                        charAt2 = zzqt.charAt(i41);
                        if (charAt2 < 55296) {
                            break;
                        }
                        c21 |= (charAt2 & 8191) << i42;
                        i42 += 13;
                        i41 = i5;
                    }
                    charAt23 = (charAt2 << i42) | c21;
                } else {
                    i5 = i41;
                }
                i3 = i5 + 1;
                char charAt24 = zzqt.charAt(i5);
                if (charAt24 >= 55296) {
                    char c22 = charAt24 & 8191;
                    int i43 = 13;
                    while (true) {
                        int i44 = i3;
                        i3 = i44 + 1;
                        charAt = zzqt.charAt(i44);
                        if (charAt < 55296) {
                            break;
                        }
                        c22 |= (charAt & 8191) << i43;
                        i43 += 13;
                    }
                    c5 = (charAt << i43) | c22;
                } else {
                    c5 = charAt24;
                }
                iArr = new int[(charAt23 + c5 + c4)];
                i6 = charAt18 + (charAt17 << 1);
                c6 = charAt19;
                c7 = charAt17;
            }
            Unsafe unsafe = zzbcx;
            Object[] zzqu = zzsx.zzqu();
            int i45 = 0;
            Class<?> cls2 = zzsx.zzqn().getClass();
            int[] iArr2 = new int[(c3 * 3)];
            Object[] objArr = new Object[(c3 << 1)];
            int i46 = c5 + c4;
            int i47 = 0;
            char c23 = c5;
            int i48 = i6;
            while (i3 < length) {
                int i49 = i3 + 1;
                char charAt25 = zzqt.charAt(i3);
                if (charAt25 >= 55296) {
                    char c24 = charAt25 & 8191;
                    int i50 = 13;
                    while (true) {
                        i8 = i49 + 1;
                        charAt12 = zzqt.charAt(i49);
                        if (charAt12 < 55296) {
                            break;
                        }
                        c24 |= (charAt12 & 8191) << i50;
                        i50 += 13;
                        i49 = i8;
                    }
                    i7 = (charAt12 << i50) | c24;
                } else {
                    i7 = charAt25;
                    i8 = i49;
                }
                int i51 = i8 + 1;
                char charAt26 = zzqt.charAt(i8);
                if (charAt26 >= 55296) {
                    char c25 = charAt26 & 8191;
                    int i52 = 13;
                    while (true) {
                        int i53 = i51;
                        i51 = i53 + 1;
                        charAt11 = zzqt.charAt(i53);
                        if (charAt11 < 55296) {
                            break;
                        }
                        c25 |= (charAt11 & 8191) << i52;
                        i52 += 13;
                    }
                    c8 = (charAt11 << i52) | c25;
                    i9 = i51;
                } else {
                    c8 = charAt26;
                    i9 = i51;
                }
                char c26 = c8 & 255;
                if ((c8 & 1024) != 0) {
                    iArr[i45] = i47;
                    i10 = i45 + 1;
                } else {
                    i10 = i45;
                }
                if (c26 >= '3') {
                    int i54 = i9 + 1;
                    char charAt27 = zzqt.charAt(i9);
                    if (charAt27 >= 55296) {
                        char c27 = charAt27 & 8191;
                        int i55 = 13;
                        while (true) {
                            i18 = i54 + 1;
                            charAt10 = zzqt.charAt(i54);
                            if (charAt10 < 55296) {
                                break;
                            }
                            c27 |= (charAt10 & 8191) << i55;
                            i55 += 13;
                            i54 = i18;
                        }
                        c12 = (charAt10 << i55) | c27;
                        i16 = i18;
                    } else {
                        c12 = charAt27;
                        i16 = i54;
                    }
                    int i56 = c26 - '3';
                    if (i56 == 9 || i56 == 17) {
                        objArr[((i47 / 3) << 1) + 1] = zzqu[i48];
                        i17 = i48 + 1;
                    } else if (i56 == 12 && (c & 1) == 1) {
                        objArr[((i47 / 3) << 1) + 1] = zzqu[i48];
                        i17 = i48 + 1;
                    } else {
                        i17 = i48;
                    }
                    int i57 = c12 << 1;
                    Object obj = zzqu[i57];
                    if (obj instanceof Field) {
                        zza2 = (Field) obj;
                    } else {
                        zza2 = zza(cls2, (String) obj);
                        zzqu[i57] = zza2;
                    }
                    i13 = (int) unsafe.objectFieldOffset(zza2);
                    int i58 = i57 + 1;
                    Object obj2 = zzqu[i58];
                    if (obj2 instanceof Field) {
                        zza3 = (Field) obj2;
                    } else {
                        zza3 = zza(cls2, (String) obj2);
                        zzqu[i58] = zza3;
                    }
                    i11 = (int) unsafe.objectFieldOffset(zza3);
                    i12 = 0;
                    c10 = c23;
                    i14 = i17;
                    i9 = i16;
                } else {
                    int i59 = i48 + 1;
                    Field zza4 = zza(cls2, (String) zzqu[i48]);
                    if (c26 == 9 || c26 == 17) {
                        objArr[((i47 / 3) << 1) + 1] = zza4.getType();
                        c9 = c23;
                    } else if (c26 == 27 || c26 == '1') {
                        objArr[((i47 / 3) << 1) + 1] = zzqu[i59];
                        c9 = c23;
                        i59++;
                    } else {
                        if (c26 == 12 || c26 == 30 || c26 == ',') {
                            if ((c & 1) == 1) {
                                objArr[((i47 / 3) << 1) + 1] = zzqu[i59];
                                c9 = c23;
                                i59++;
                            }
                        } else if (c26 == '2') {
                            int i60 = c23 + 1;
                            iArr[c23] = i47;
                            int i61 = i59 + 1;
                            objArr[(i47 / 3) << 1] = zzqu[i59];
                            if ((c8 & 2048) != 0) {
                                i59 = i61 + 1;
                                objArr[((i47 / 3) << 1) + 1] = zzqu[i61];
                                c9 = i60;
                            } else {
                                c9 = i60;
                                i59 = i61;
                            }
                        }
                        c9 = c23;
                    }
                    int objectFieldOffset = (int) unsafe.objectFieldOffset(zza4);
                    if ((c & 1) != 1 || c26 > 17) {
                        i11 = 0;
                        i12 = 0;
                    } else {
                        int i62 = i9 + 1;
                        char charAt28 = zzqt.charAt(i9);
                        if (charAt28 >= 55296) {
                            char c28 = charAt28 & 8191;
                            int i63 = 13;
                            while (true) {
                                i15 = i62 + 1;
                                charAt9 = zzqt.charAt(i62);
                                if (charAt9 < 55296) {
                                    break;
                                }
                                c28 |= (charAt9 & 8191) << i63;
                                i63 += 13;
                                i62 = i15;
                            }
                            c11 = (charAt9 << i63) | c28;
                        } else {
                            c11 = charAt28;
                            i15 = i62;
                        }
                        int i64 = (c11 / ' ') + (c7 << 1);
                        Object obj3 = zzqu[i64];
                        if (obj3 instanceof Field) {
                            zza = (Field) obj3;
                        } else {
                            zza = zza(cls2, (String) obj3);
                            zzqu[i64] = zza;
                        }
                        i11 = (int) unsafe.objectFieldOffset(zza);
                        i12 = c11 % ' ';
                        i9 = i15;
                    }
                    if (c26 < 18 || c26 > '1') {
                        i13 = objectFieldOffset;
                        c10 = c9;
                        i14 = i59;
                    } else {
                        iArr[i46] = objectFieldOffset;
                        i13 = objectFieldOffset;
                        i46++;
                        c10 = c9;
                        i14 = i59;
                    }
                }
                int i65 = i47 + 1;
                iArr2[i47] = i7;
                int i66 = i65 + 1;
                iArr2[i65] = ((c8 & 256) != 0 ? 268435456 : 0) | ((c8 & 512) != 0 ? 536870912 : 0) | (c26 << 20) | i13;
                iArr2[i66] = i11 | (i12 << 20);
                i47 = i66 + 1;
                c23 = c10;
                i45 = i10;
                i48 = i14;
                i3 = i9;
            }
            return new zzso<>(iArr2, objArr, c6, c2, zzsx.zzqn(), z, false, iArr, c5, c5 + c4, zzsr, zzru, zztr, zzqq, zzsf);
        }
        if (((zztm) zzsi).zzql() == zzrc.zze.zzbba) {
        }
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            throw new RuntimeException(new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length()).append("Field ").append(str).append(" for ").append(name).append(" not found. Known fields are ").append(arrays).toString());
        }
    }

    public final T newInstance() {
        return this.zzbdk.newInstance(this.zzbdc);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r12, T r13) {
        /*
            r11 = this;
            r1 = 1
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 0
            int[] r2 = r11.zzbcy
            int r4 = r2.length
            r3 = r0
        L_0x0009:
            if (r3 >= r4) goto L_0x01e0
            int r2 = r11.zzbr(r3)
            r5 = r2 & r10
            long r6 = (long) r5
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r2 = r2 & r5
            int r2 = r2 >>> 20
            switch(r2) {
                case 0: goto L_0x001e;
                case 1: goto L_0x003a;
                case 2: goto L_0x0054;
                case 3: goto L_0x0068;
                case 4: goto L_0x007c;
                case 5: goto L_0x008e;
                case 6: goto L_0x00a3;
                case 7: goto L_0x00b6;
                case 8: goto L_0x00c9;
                case 9: goto L_0x00e0;
                case 10: goto L_0x00f7;
                case 11: goto L_0x010e;
                case 12: goto L_0x0121;
                case 13: goto L_0x0134;
                case 14: goto L_0x0147;
                case 15: goto L_0x015c;
                case 16: goto L_0x016f;
                case 17: goto L_0x0184;
                case 18: goto L_0x019b;
                case 19: goto L_0x019b;
                case 20: goto L_0x019b;
                case 21: goto L_0x019b;
                case 22: goto L_0x019b;
                case 23: goto L_0x019b;
                case 24: goto L_0x019b;
                case 25: goto L_0x019b;
                case 26: goto L_0x019b;
                case 27: goto L_0x019b;
                case 28: goto L_0x019b;
                case 29: goto L_0x019b;
                case 30: goto L_0x019b;
                case 31: goto L_0x019b;
                case 32: goto L_0x019b;
                case 33: goto L_0x019b;
                case 34: goto L_0x019b;
                case 35: goto L_0x019b;
                case 36: goto L_0x019b;
                case 37: goto L_0x019b;
                case 38: goto L_0x019b;
                case 39: goto L_0x019b;
                case 40: goto L_0x019b;
                case 41: goto L_0x019b;
                case 42: goto L_0x019b;
                case 43: goto L_0x019b;
                case 44: goto L_0x019b;
                case 45: goto L_0x019b;
                case 46: goto L_0x019b;
                case 47: goto L_0x019b;
                case 48: goto L_0x019b;
                case 49: goto L_0x019b;
                case 50: goto L_0x01a9;
                case 51: goto L_0x01b7;
                case 52: goto L_0x01b7;
                case 53: goto L_0x01b7;
                case 54: goto L_0x01b7;
                case 55: goto L_0x01b7;
                case 56: goto L_0x01b7;
                case 57: goto L_0x01b7;
                case 58: goto L_0x01b7;
                case 59: goto L_0x01b7;
                case 60: goto L_0x01b7;
                case 61: goto L_0x01b7;
                case 62: goto L_0x01b7;
                case 63: goto L_0x01b7;
                case 64: goto L_0x01b7;
                case 65: goto L_0x01b7;
                case 66: goto L_0x01b7;
                case 67: goto L_0x01b7;
                case 68: goto L_0x01b7;
                default: goto L_0x001a;
            }
        L_0x001a:
            r2 = r1
        L_0x001b:
            if (r2 != 0) goto L_0x01db
        L_0x001d:
            return r0
        L_0x001e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0038
            double r8 = com.google.android.gms.internal.gtm.zztx.zzo(r12, r6)
            long r8 = java.lang.Double.doubleToLongBits(r8)
            double r6 = com.google.android.gms.internal.gtm.zztx.zzo(r13, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0038:
            r2 = r0
            goto L_0x001b
        L_0x003a:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0052
            float r2 = com.google.android.gms.internal.gtm.zztx.zzn(r12, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            float r5 = com.google.android.gms.internal.gtm.zztx.zzn(r13, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r2 == r5) goto L_0x001a
        L_0x0052:
            r2 = r0
            goto L_0x001b
        L_0x0054:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0066
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0066:
            r2 = r0
            goto L_0x001b
        L_0x0068:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x007a
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x007a:
            r2 = r0
            goto L_0x001b
        L_0x007c:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x008c
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.gtm.zztx.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x008c:
            r2 = r0
            goto L_0x001b
        L_0x008e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00a0
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x00a0:
            r2 = r0
            goto L_0x001b
        L_0x00a3:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00b3
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.gtm.zztx.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x00b3:
            r2 = r0
            goto L_0x001b
        L_0x00b6:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00c6
            boolean r2 = com.google.android.gms.internal.gtm.zztx.zzm(r12, r6)
            boolean r5 = com.google.android.gms.internal.gtm.zztx.zzm(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x00c6:
            r2 = r0
            goto L_0x001b
        L_0x00c9:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00dd
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.gtm.zztx.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.gtm.zztb.zze(r2, r5)
            if (r2 != 0) goto L_0x001a
        L_0x00dd:
            r2 = r0
            goto L_0x001b
        L_0x00e0:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00f4
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.gtm.zztx.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.gtm.zztb.zze(r2, r5)
            if (r2 != 0) goto L_0x001a
        L_0x00f4:
            r2 = r0
            goto L_0x001b
        L_0x00f7:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x010b
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.gtm.zztx.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.gtm.zztb.zze(r2, r5)
            if (r2 != 0) goto L_0x001a
        L_0x010b:
            r2 = r0
            goto L_0x001b
        L_0x010e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x011e
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.gtm.zztx.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x011e:
            r2 = r0
            goto L_0x001b
        L_0x0121:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0131
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.gtm.zztx.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x0131:
            r2 = r0
            goto L_0x001b
        L_0x0134:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0144
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.gtm.zztx.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x0144:
            r2 = r0
            goto L_0x001b
        L_0x0147:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0159
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0159:
            r2 = r0
            goto L_0x001b
        L_0x015c:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x016c
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r12, r6)
            int r5 = com.google.android.gms.internal.gtm.zztx.zzk(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x016c:
            r2 = r0
            goto L_0x001b
        L_0x016f:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0181
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r12, r6)
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0181:
            r2 = r0
            goto L_0x001b
        L_0x0184:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0198
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.gtm.zztx.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.gtm.zztb.zze(r2, r5)
            if (r2 != 0) goto L_0x001a
        L_0x0198:
            r2 = r0
            goto L_0x001b
        L_0x019b:
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.gtm.zztx.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.gtm.zztb.zze(r2, r5)
            goto L_0x001b
        L_0x01a9:
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.gtm.zztx.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.gtm.zztb.zze(r2, r5)
            goto L_0x001b
        L_0x01b7:
            int r2 = r11.zzbs(r3)
            r5 = r2 & r10
            long r8 = (long) r5
            int r5 = com.google.android.gms.internal.gtm.zztx.zzk(r12, r8)
            r2 = r2 & r10
            long r8 = (long) r2
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r13, r8)
            if (r5 != r2) goto L_0x01d8
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.gtm.zztx.zzp(r13, r6)
            boolean r2 = com.google.android.gms.internal.gtm.zztb.zze(r2, r5)
            if (r2 != 0) goto L_0x001a
        L_0x01d8:
            r2 = r0
            goto L_0x001b
        L_0x01db:
            int r2 = r3 + 3
            r3 = r2
            goto L_0x0009
        L_0x01e0:
            com.google.android.gms.internal.gtm.zztr<?, ?> r2 = r11.zzbdm
            java.lang.Object r2 = r2.zzag(r12)
            com.google.android.gms.internal.gtm.zztr<?, ?> r3 = r11.zzbdm
            java.lang.Object r3 = r3.zzag(r13)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x001d
            boolean r0 = r11.zzbdd
            if (r0 == 0) goto L_0x0208
            com.google.android.gms.internal.gtm.zzqq<?> r0 = r11.zzbdn
            com.google.android.gms.internal.gtm.zzqt r0 = r0.zzr(r12)
            com.google.android.gms.internal.gtm.zzqq<?> r1 = r11.zzbdn
            com.google.android.gms.internal.gtm.zzqt r1 = r1.zzr(r13)
            boolean r0 = r0.equals(r1)
            goto L_0x001d
        L_0x0208:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzso.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r10) {
        /*
            r9 = this;
            r1 = 37
            r0 = 0
            int[] r2 = r9.zzbcy
            int r4 = r2.length
            r3 = r0
            r2 = r0
        L_0x0008:
            if (r3 >= r4) goto L_0x0254
            int r0 = r9.zzbr(r3)
            int[] r5 = r9.zzbcy
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
            int r3 = r3 + 3
            r2 = r0
            goto L_0x0008
        L_0x0024:
            int r0 = r2 * 53
            double r6 = com.google.android.gms.internal.gtm.zztx.zzo(r10, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0034:
            int r0 = r2 * 53
            float r2 = com.google.android.gms.internal.gtm.zztx.zzn(r10, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0040:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x004c:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0058:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0060:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x006c:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0074:
            int r0 = r2 * 53
            boolean r2 = com.google.android.gms.internal.gtm.zztx.zzm(r10, r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzk(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0080:
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x008e:
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            if (r0 == 0) goto L_0x0276
            int r0 = r0.hashCode()
        L_0x0098:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x009c:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00a9:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00b2:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00bb:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00c4:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00d1:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.gtm.zztx.zzk(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00da:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00e7:
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            if (r0 == 0) goto L_0x0273
            int r0 = r0.hashCode()
        L_0x00f1:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00f6:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0103:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0110:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            double r6 = zzf(r10, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
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
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x014d:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzi(r10, r6)
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
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
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
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
            int r2 = com.google.android.gms.internal.gtm.zzre.zzk(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01a4:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01b9:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01cc:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
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
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
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
            int r2 = com.google.android.gms.internal.gtm.zzre.zzz(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0241:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0254:
            int r0 = r2 * 53
            com.google.android.gms.internal.gtm.zztr<?, ?> r1 = r9.zzbdm
            java.lang.Object r1 = r1.zzag(r10)
            int r1 = r1.hashCode()
            int r0 = r0 + r1
            boolean r1 = r9.zzbdd
            if (r1 == 0) goto L_0x0272
            int r0 = r0 * 53
            com.google.android.gms.internal.gtm.zzqq<?> r1 = r9.zzbdn
            com.google.android.gms.internal.gtm.zzqt r1 = r1.zzr(r10)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzso.hashCode(java.lang.Object):int");
    }

    public final void zzd(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzbcy.length; i += 3) {
            int zzbr = zzbr(i);
            long j = (long) (1048575 & zzbr);
            int i2 = this.zzbcy[i];
            switch ((zzbr & 267386880) >>> 20) {
                case 0:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzo(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 1:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzn(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 2:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzl(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 3:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzl(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 4:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zzb((Object) t, j, zztx.zzk(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 5:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzl(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 6:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zzb((Object) t, j, zztx.zzk(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 7:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzm(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 8:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzp(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzp(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 11:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zzb((Object) t, j, zztx.zzk(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 12:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zzb((Object) t, j, zztx.zzk(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 13:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zzb((Object) t, j, zztx.zzk(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 14:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzl(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 15:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zzb((Object) t, j, zztx.zzk(t2, j));
                        zzc(t, i);
                        break;
                    }
                case 16:
                    if (!zzb(t2, i)) {
                        break;
                    } else {
                        zztx.zza((Object) t, j, zztx.zzl(t2, j));
                        zzc(t, i);
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
                    this.zzbdl.zza(t, t2, j);
                    break;
                case 50:
                    zztb.zza(this.zzbdo, t, t2, j);
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
                        zztx.zza((Object) t, j, zztx.zzp(t2, j));
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
                        zztx.zza((Object) t, j, zztx.zzp(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (!this.zzbdf) {
            zztb.zza(this.zzbdm, t, t2);
            if (this.zzbdd) {
                zztb.zza(this.zzbdn, t, t2);
            }
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzbr = (long) (zzbr(i) & 1048575);
        if (zzb(t2, i)) {
            Object zzp = zztx.zzp(t, zzbr);
            Object zzp2 = zztx.zzp(t2, zzbr);
            if (zzp != null && zzp2 != null) {
                zztx.zza((Object) t, zzbr, zzre.zzb(zzp, zzp2));
                zzc(t, i);
            } else if (zzp2 != null) {
                zztx.zza((Object) t, zzbr, zzp2);
                zzc(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzbr = zzbr(i);
        int i2 = this.zzbcy[i];
        long j = (long) (zzbr & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zztx.zzp(t, j);
            Object zzp2 = zztx.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zztx.zza((Object) t, j, zzre.zzb(zzp, zzp2));
                zzb(t, i2, i);
            } else if (zzp2 != null) {
                zztx.zza((Object) t, j, zzp2);
                zzb(t, i2, i);
            }
        }
    }

    public final int zzad(T t) {
        int i;
        int i2;
        if (this.zzbdf) {
            Unsafe unsafe = zzbcx;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 >= this.zzbcy.length) {
                    return zza(this.zzbdm, t) + i3;
                }
                int zzbr = zzbr(i5);
                int i6 = (267386880 & zzbr) >>> 20;
                int i7 = this.zzbcy[i5];
                long j = (long) (zzbr & 1048575);
                if (i6 < zzqw.DOUBLE_LIST_PACKED.id() || i6 > zzqw.SINT64_LIST_PACKED.id()) {
                    i2 = 0;
                } else {
                    i2 = this.zzbcy[i5 + 2] & 1048575;
                }
                switch (i6) {
                    case 0:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzb(i7, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzb(i7, 0.0f);
                            break;
                        }
                    case 2:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzd(i7, zztx.zzl(t, j));
                            break;
                        }
                    case 3:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zze(i7, zztx.zzl(t, j));
                            break;
                        }
                    case 4:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzi(i7, zztx.zzk(t, j));
                            break;
                        }
                    case 5:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzg(i7, 0);
                            break;
                        }
                    case 6:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzl(i7, 0);
                            break;
                        }
                    case 7:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzc(i7, true);
                            break;
                        }
                    case 8:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            Object zzp = zztx.zzp(t, j);
                            if (!(zzp instanceof zzps)) {
                                i3 += zzqj.zzb(i7, (String) zzp);
                                break;
                            } else {
                                i3 += zzqj.zzc(i7, (zzps) zzp);
                                break;
                            }
                        }
                    case 9:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zztb.zzc(i7, zztx.zzp(t, j), zzbo(i5));
                            break;
                        }
                    case 10:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzc(i7, (zzps) zztx.zzp(t, j));
                            break;
                        }
                    case 11:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzj(i7, zztx.zzk(t, j));
                            break;
                        }
                    case 12:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzn(i7, zztx.zzk(t, j));
                            break;
                        }
                    case 13:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzm(i7, 0);
                            break;
                        }
                    case 14:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzh(i7, 0);
                            break;
                        }
                    case 15:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzk(i7, zztx.zzk(t, j));
                            break;
                        }
                    case 16:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzf(i7, zztx.zzl(t, j));
                            break;
                        }
                    case 17:
                        if (!zzb(t, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzc(i7, (zzsk) zztx.zzp(t, j), zzbo(i5));
                            break;
                        }
                    case 18:
                        i3 += zztb.zzw(i7, zze(t, j), false);
                        break;
                    case 19:
                        i3 += zztb.zzv(i7, zze(t, j), false);
                        break;
                    case 20:
                        i3 += zztb.zzo(i7, zze(t, j), false);
                        break;
                    case 21:
                        i3 += zztb.zzp(i7, zze(t, j), false);
                        break;
                    case 22:
                        i3 += zztb.zzs(i7, zze(t, j), false);
                        break;
                    case 23:
                        i3 += zztb.zzw(i7, zze(t, j), false);
                        break;
                    case 24:
                        i3 += zztb.zzv(i7, zze(t, j), false);
                        break;
                    case 25:
                        i3 += zztb.zzx(i7, zze(t, j), false);
                        break;
                    case 26:
                        i3 += zztb.zzc(i7, zze(t, j));
                        break;
                    case 27:
                        i3 += zztb.zzc(i7, (List<?>) zze(t, j), zzbo(i5));
                        break;
                    case 28:
                        i3 += zztb.zzd(i7, zze(t, j));
                        break;
                    case 29:
                        i3 += zztb.zzt(i7, zze(t, j), false);
                        break;
                    case 30:
                        i3 += zztb.zzr(i7, zze(t, j), false);
                        break;
                    case 31:
                        i3 += zztb.zzv(i7, zze(t, j), false);
                        break;
                    case 32:
                        i3 += zztb.zzw(i7, zze(t, j), false);
                        break;
                    case 33:
                        i3 += zztb.zzu(i7, zze(t, j), false);
                        break;
                    case 34:
                        i3 += zztb.zzq(i7, zze(t, j), false);
                        break;
                    case 35:
                        int zzae = zztb.zzae((List) unsafe.getObject(t, j));
                        if (zzae > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzae);
                            }
                            i3 += zzae + zzqj.zzbb(i7) + zzqj.zzbd(zzae);
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzad = zztb.zzad((List) unsafe.getObject(t, j));
                        if (zzad > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzad);
                            }
                            i3 += zzad + zzqj.zzbb(i7) + zzqj.zzbd(zzad);
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zzw = zztb.zzw((List) unsafe.getObject(t, j));
                        if (zzw > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzw);
                            }
                            i3 += zzw + zzqj.zzbb(i7) + zzqj.zzbd(zzw);
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzx = zztb.zzx((List) unsafe.getObject(t, j));
                        if (zzx > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzx);
                            }
                            i3 += zzx + zzqj.zzbb(i7) + zzqj.zzbd(zzx);
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zzaa = zztb.zzaa((List) unsafe.getObject(t, j));
                        if (zzaa > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzaa);
                            }
                            i3 += zzaa + zzqj.zzbb(i7) + zzqj.zzbd(zzaa);
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzae2 = zztb.zzae((List) unsafe.getObject(t, j));
                        if (zzae2 > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzae2);
                            }
                            i3 += zzae2 + zzqj.zzbb(i7) + zzqj.zzbd(zzae2);
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzad2 = zztb.zzad((List) unsafe.getObject(t, j));
                        if (zzad2 > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzad2);
                            }
                            i3 += zzad2 + zzqj.zzbb(i7) + zzqj.zzbd(zzad2);
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzaf = zztb.zzaf((List) unsafe.getObject(t, j));
                        if (zzaf > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzaf);
                            }
                            i3 += zzaf + zzqj.zzbb(i7) + zzqj.zzbd(zzaf);
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzab = zztb.zzab((List) unsafe.getObject(t, j));
                        if (zzab > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzab);
                            }
                            i3 += zzab + zzqj.zzbb(i7) + zzqj.zzbd(zzab);
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzz = zztb.zzz((List) unsafe.getObject(t, j));
                        if (zzz > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzz);
                            }
                            i3 += zzz + zzqj.zzbb(i7) + zzqj.zzbd(zzz);
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzad3 = zztb.zzad((List) unsafe.getObject(t, j));
                        if (zzad3 > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzad3);
                            }
                            i3 += zzad3 + zzqj.zzbb(i7) + zzqj.zzbd(zzad3);
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzae3 = zztb.zzae((List) unsafe.getObject(t, j));
                        if (zzae3 > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzae3);
                            }
                            i3 += zzae3 + zzqj.zzbb(i7) + zzqj.zzbd(zzae3);
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzac = zztb.zzac((List) unsafe.getObject(t, j));
                        if (zzac > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzac);
                            }
                            i3 += zzac + zzqj.zzbb(i7) + zzqj.zzbd(zzac);
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzy = zztb.zzy((List) unsafe.getObject(t, j));
                        if (zzy > 0) {
                            if (this.zzbdg) {
                                unsafe.putInt(t, (long) i2, zzy);
                            }
                            i3 += zzy + zzqj.zzbb(i7) + zzqj.zzbd(zzy);
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i3 += zztb.zzd(i7, zze(t, j), zzbo(i5));
                        break;
                    case 50:
                        i3 += this.zzbdo.zzb(i7, zztx.zzp(t, j), zzbp(i5));
                        break;
                    case 51:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzb(i7, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzb(i7, 0.0f);
                            break;
                        }
                    case 53:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzd(i7, zzi(t, j));
                            break;
                        }
                    case 54:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zze(i7, zzi(t, j));
                            break;
                        }
                    case 55:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzi(i7, zzh(t, j));
                            break;
                        }
                    case 56:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzg(i7, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzl(i7, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzc(i7, true);
                            break;
                        }
                    case 59:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            Object zzp2 = zztx.zzp(t, j);
                            if (!(zzp2 instanceof zzps)) {
                                i3 += zzqj.zzb(i7, (String) zzp2);
                                break;
                            } else {
                                i3 += zzqj.zzc(i7, (zzps) zzp2);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zztb.zzc(i7, zztx.zzp(t, j), zzbo(i5));
                            break;
                        }
                    case 61:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzc(i7, (zzps) zztx.zzp(t, j));
                            break;
                        }
                    case 62:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzj(i7, zzh(t, j));
                            break;
                        }
                    case 63:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzn(i7, zzh(t, j));
                            break;
                        }
                    case 64:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzm(i7, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzh(i7, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzk(i7, zzh(t, j));
                            break;
                        }
                    case 67:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzf(i7, zzi(t, j));
                            break;
                        }
                    case 68:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzqj.zzc(i7, (zzsk) zztx.zzp(t, j), zzbo(i5));
                            break;
                        }
                }
                i4 = i5 + 3;
            }
        } else {
            int i8 = 0;
            Unsafe unsafe2 = zzbcx;
            int i9 = -1;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                int i12 = i11;
                if (i12 < this.zzbcy.length) {
                    int zzbr2 = zzbr(i12);
                    int i13 = this.zzbcy[i12];
                    int i14 = (267386880 & zzbr2) >>> 20;
                    int i15 = 0;
                    if (i14 <= 17) {
                        i = this.zzbcy[i12 + 2];
                        int i16 = 1048575 & i;
                        int i17 = 1 << (i >>> 20);
                        if (i16 != i9) {
                            i10 = unsafe2.getInt(t, (long) i16);
                            i9 = i16;
                        }
                        i15 = i17;
                    } else if (!this.zzbdg || i14 < zzqw.DOUBLE_LIST_PACKED.id() || i14 > zzqw.SINT64_LIST_PACKED.id()) {
                        i = 0;
                    } else {
                        i = this.zzbcy[i12 + 2] & 1048575;
                    }
                    long j2 = (long) (1048575 & zzbr2);
                    switch (i14) {
                        case 0:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzb(i13, 0.0d);
                                break;
                            }
                        case 1:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzb(i13, 0.0f);
                                break;
                            }
                        case 2:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzd(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 3:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zze(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 4:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzi(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 5:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzg(i13, 0);
                                break;
                            }
                        case 6:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzl(i13, 0);
                                break;
                            }
                        case 7:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzc(i13, true);
                                break;
                            }
                        case 8:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                Object object = unsafe2.getObject(t, j2);
                                if (!(object instanceof zzps)) {
                                    i8 += zzqj.zzb(i13, (String) object);
                                    break;
                                } else {
                                    i8 += zzqj.zzc(i13, (zzps) object);
                                    break;
                                }
                            }
                        case 9:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zztb.zzc(i13, unsafe2.getObject(t, j2), zzbo(i12));
                                break;
                            }
                        case 10:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzc(i13, (zzps) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 11:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzj(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 12:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzn(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 13:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzm(i13, 0);
                                break;
                            }
                        case 14:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzh(i13, 0);
                                break;
                            }
                        case 15:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzk(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 16:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzf(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 17:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzqj.zzc(i13, (zzsk) unsafe2.getObject(t, j2), zzbo(i12));
                                break;
                            }
                        case 18:
                            i8 += zztb.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 19:
                            i8 += zztb.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 20:
                            i8 += zztb.zzo(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 21:
                            i8 += zztb.zzp(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 22:
                            i8 += zztb.zzs(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 23:
                            i8 += zztb.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 24:
                            i8 += zztb.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 25:
                            i8 += zztb.zzx(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 26:
                            i8 += zztb.zzc(i13, (List) unsafe2.getObject(t, j2));
                            break;
                        case 27:
                            i8 += zztb.zzc(i13, (List<?>) (List) unsafe2.getObject(t, j2), zzbo(i12));
                            break;
                        case 28:
                            i8 += zztb.zzd(i13, (List) unsafe2.getObject(t, j2));
                            break;
                        case 29:
                            i8 += zztb.zzt(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 30:
                            i8 += zztb.zzr(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 31:
                            i8 += zztb.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 32:
                            i8 += zztb.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 33:
                            i8 += zztb.zzu(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 34:
                            i8 += zztb.zzq(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 35:
                            int zzae4 = zztb.zzae((List) unsafe2.getObject(t, j2));
                            if (zzae4 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzae4);
                                }
                                i8 += zzae4 + zzqj.zzbb(i13) + zzqj.zzbd(zzae4);
                                break;
                            } else {
                                break;
                            }
                        case 36:
                            int zzad4 = zztb.zzad((List) unsafe2.getObject(t, j2));
                            if (zzad4 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzad4);
                                }
                                i8 += zzad4 + zzqj.zzbb(i13) + zzqj.zzbd(zzad4);
                                break;
                            } else {
                                break;
                            }
                        case 37:
                            int zzw2 = zztb.zzw((List) unsafe2.getObject(t, j2));
                            if (zzw2 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzw2);
                                }
                                i8 += zzw2 + zzqj.zzbb(i13) + zzqj.zzbd(zzw2);
                                break;
                            } else {
                                break;
                            }
                        case 38:
                            int zzx2 = zztb.zzx((List) unsafe2.getObject(t, j2));
                            if (zzx2 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzx2);
                                }
                                i8 += zzx2 + zzqj.zzbb(i13) + zzqj.zzbd(zzx2);
                                break;
                            } else {
                                break;
                            }
                        case 39:
                            int zzaa2 = zztb.zzaa((List) unsafe2.getObject(t, j2));
                            if (zzaa2 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzaa2);
                                }
                                i8 += zzaa2 + zzqj.zzbb(i13) + zzqj.zzbd(zzaa2);
                                break;
                            } else {
                                break;
                            }
                        case 40:
                            int zzae5 = zztb.zzae((List) unsafe2.getObject(t, j2));
                            if (zzae5 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzae5);
                                }
                                i8 += zzae5 + zzqj.zzbb(i13) + zzqj.zzbd(zzae5);
                                break;
                            } else {
                                break;
                            }
                        case 41:
                            int zzad5 = zztb.zzad((List) unsafe2.getObject(t, j2));
                            if (zzad5 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzad5);
                                }
                                i8 += zzad5 + zzqj.zzbb(i13) + zzqj.zzbd(zzad5);
                                break;
                            } else {
                                break;
                            }
                        case 42:
                            int zzaf2 = zztb.zzaf((List) unsafe2.getObject(t, j2));
                            if (zzaf2 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzaf2);
                                }
                                i8 += zzaf2 + zzqj.zzbb(i13) + zzqj.zzbd(zzaf2);
                                break;
                            } else {
                                break;
                            }
                        case 43:
                            int zzab2 = zztb.zzab((List) unsafe2.getObject(t, j2));
                            if (zzab2 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzab2);
                                }
                                i8 += zzab2 + zzqj.zzbb(i13) + zzqj.zzbd(zzab2);
                                break;
                            } else {
                                break;
                            }
                        case 44:
                            int zzz2 = zztb.zzz((List) unsafe2.getObject(t, j2));
                            if (zzz2 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzz2);
                                }
                                i8 += zzz2 + zzqj.zzbb(i13) + zzqj.zzbd(zzz2);
                                break;
                            } else {
                                break;
                            }
                        case 45:
                            int zzad6 = zztb.zzad((List) unsafe2.getObject(t, j2));
                            if (zzad6 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzad6);
                                }
                                i8 += zzad6 + zzqj.zzbb(i13) + zzqj.zzbd(zzad6);
                                break;
                            } else {
                                break;
                            }
                        case 46:
                            int zzae6 = zztb.zzae((List) unsafe2.getObject(t, j2));
                            if (zzae6 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzae6);
                                }
                                i8 += zzae6 + zzqj.zzbb(i13) + zzqj.zzbd(zzae6);
                                break;
                            } else {
                                break;
                            }
                        case 47:
                            int zzac2 = zztb.zzac((List) unsafe2.getObject(t, j2));
                            if (zzac2 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzac2);
                                }
                                i8 += zzac2 + zzqj.zzbb(i13) + zzqj.zzbd(zzac2);
                                break;
                            } else {
                                break;
                            }
                        case 48:
                            int zzy2 = zztb.zzy((List) unsafe2.getObject(t, j2));
                            if (zzy2 > 0) {
                                if (this.zzbdg) {
                                    unsafe2.putInt(t, (long) i, zzy2);
                                }
                                i8 += zzy2 + zzqj.zzbb(i13) + zzqj.zzbd(zzy2);
                                break;
                            } else {
                                break;
                            }
                        case 49:
                            i8 += zztb.zzd(i13, (List) unsafe2.getObject(t, j2), zzbo(i12));
                            break;
                        case 50:
                            i8 += this.zzbdo.zzb(i13, unsafe2.getObject(t, j2), zzbp(i12));
                            break;
                        case 51:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzb(i13, 0.0d);
                                break;
                            }
                        case 52:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzb(i13, 0.0f);
                                break;
                            }
                        case 53:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzd(i13, zzi(t, j2));
                                break;
                            }
                        case 54:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zze(i13, zzi(t, j2));
                                break;
                            }
                        case 55:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzi(i13, zzh(t, j2));
                                break;
                            }
                        case 56:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzg(i13, 0);
                                break;
                            }
                        case 57:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzl(i13, 0);
                                break;
                            }
                        case 58:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzc(i13, true);
                                break;
                            }
                        case 59:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                Object object2 = unsafe2.getObject(t, j2);
                                if (!(object2 instanceof zzps)) {
                                    i8 += zzqj.zzb(i13, (String) object2);
                                    break;
                                } else {
                                    i8 += zzqj.zzc(i13, (zzps) object2);
                                    break;
                                }
                            }
                        case 60:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zztb.zzc(i13, unsafe2.getObject(t, j2), zzbo(i12));
                                break;
                            }
                        case 61:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzc(i13, (zzps) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 62:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzj(i13, zzh(t, j2));
                                break;
                            }
                        case 63:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzn(i13, zzh(t, j2));
                                break;
                            }
                        case 64:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzm(i13, 0);
                                break;
                            }
                        case 65:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzh(i13, 0);
                                break;
                            }
                        case 66:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzk(i13, zzh(t, j2));
                                break;
                            }
                        case 67:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzf(i13, zzi(t, j2));
                                break;
                            }
                        case 68:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzqj.zzc(i13, (zzsk) unsafe2.getObject(t, j2), zzbo(i12));
                                break;
                            }
                    }
                    i11 = i12 + 3;
                } else {
                    int zza = i8 + zza(this.zzbdm, t);
                    if (!this.zzbdd) {
                        return zza;
                    }
                    zzqt<?> zzr = this.zzbdn.zzr(t);
                    int i18 = 0;
                    int i19 = 0;
                    while (true) {
                        int i20 = i19;
                        if (i20 < zzr.zzaxo.zzra()) {
                            Map.Entry<FieldDescriptorType, Object> zzbv = zzr.zzaxo.zzbv(i20);
                            i18 += zzqt.zzb((zzqv<?>) (zzqv) zzbv.getKey(), zzbv.getValue());
                            i19 = i20 + 1;
                        } else {
                            for (Map.Entry next : zzr.zzaxo.zzrb()) {
                                i18 += zzqt.zzb((zzqv<?>) (zzqv) next.getKey(), next.getValue());
                            }
                            return zza + i18;
                        }
                    }
                }
            }
        }
    }

    private static <UT, UB> int zza(zztr<UT, UB> zztr, T t) {
        return zztr.zzad(zztr.zzag(t));
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zztx.zzp(obj, j);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 547 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r11, com.google.android.gms.internal.gtm.zzum r12) throws java.io.IOException {
        /*
            r10 = this;
            int r0 = r12.zzol()
            int r1 = com.google.android.gms.internal.gtm.zzrc.zze.zzbbd
            if (r0 != r1) goto L_0x060c
            com.google.android.gms.internal.gtm.zztr<?, ?> r0 = r10.zzbdm
            zza(r0, r11, (com.google.android.gms.internal.gtm.zzum) r12)
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzbdd
            if (r2 == 0) goto L_0x002b
            com.google.android.gms.internal.gtm.zzqq<?> r2 = r10.zzbdn
            com.google.android.gms.internal.gtm.zzqt r2 = r2.zzr(r11)
            com.google.android.gms.internal.gtm.zztc<FieldDescriptorType, java.lang.Object> r3 = r2.zzaxo
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x002b
            java.util.Iterator r1 = r2.descendingIterator()
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x002b:
            int[] r2 = r10.zzbcy
            int r2 = r2.length
            int r2 = r2 + -3
            r3 = r2
        L_0x0031:
            if (r3 < 0) goto L_0x05f8
            int r4 = r10.zzbr(r3)
            int[] r2 = r10.zzbcy
            r5 = r2[r3]
            r2 = r0
        L_0x003c:
            if (r2 == 0) goto L_0x005b
            com.google.android.gms.internal.gtm.zzqq<?> r0 = r10.zzbdn
            int r0 = r0.zzb(r2)
            if (r0 <= r5) goto L_0x005b
            com.google.android.gms.internal.gtm.zzqq<?> r0 = r10.zzbdn
            r0.zza(r12, r2)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0059
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0057:
            r2 = r0
            goto L_0x003c
        L_0x0059:
            r0 = 0
            goto L_0x0057
        L_0x005b:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r4
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0067;
                case 1: goto L_0x007a;
                case 2: goto L_0x008d;
                case 3: goto L_0x00a0;
                case 4: goto L_0x00b3;
                case 5: goto L_0x00c6;
                case 6: goto L_0x00d9;
                case 7: goto L_0x00ed;
                case 8: goto L_0x0101;
                case 9: goto L_0x0115;
                case 10: goto L_0x012d;
                case 11: goto L_0x0143;
                case 12: goto L_0x0157;
                case 13: goto L_0x016b;
                case 14: goto L_0x017f;
                case 15: goto L_0x0193;
                case 16: goto L_0x01a7;
                case 17: goto L_0x01bb;
                case 18: goto L_0x01d3;
                case 19: goto L_0x01e8;
                case 20: goto L_0x01fd;
                case 21: goto L_0x0212;
                case 22: goto L_0x0227;
                case 23: goto L_0x023c;
                case 24: goto L_0x0251;
                case 25: goto L_0x0266;
                case 26: goto L_0x027b;
                case 27: goto L_0x028f;
                case 28: goto L_0x02a7;
                case 29: goto L_0x02bb;
                case 30: goto L_0x02d0;
                case 31: goto L_0x02e5;
                case 32: goto L_0x02fa;
                case 33: goto L_0x030f;
                case 34: goto L_0x0324;
                case 35: goto L_0x0339;
                case 36: goto L_0x034e;
                case 37: goto L_0x0363;
                case 38: goto L_0x0378;
                case 39: goto L_0x038d;
                case 40: goto L_0x03a2;
                case 41: goto L_0x03b7;
                case 42: goto L_0x03cc;
                case 43: goto L_0x03e1;
                case 44: goto L_0x03f6;
                case 45: goto L_0x040b;
                case 46: goto L_0x0420;
                case 47: goto L_0x0435;
                case 48: goto L_0x044a;
                case 49: goto L_0x045f;
                case 50: goto L_0x0477;
                case 51: goto L_0x0485;
                case 52: goto L_0x0499;
                case 53: goto L_0x04ad;
                case 54: goto L_0x04c1;
                case 55: goto L_0x04d5;
                case 56: goto L_0x04e9;
                case 57: goto L_0x04fd;
                case 58: goto L_0x0511;
                case 59: goto L_0x0525;
                case 60: goto L_0x0539;
                case 61: goto L_0x0551;
                case 62: goto L_0x0567;
                case 63: goto L_0x057b;
                case 64: goto L_0x058f;
                case 65: goto L_0x05a3;
                case 66: goto L_0x05b7;
                case 67: goto L_0x05cb;
                case 68: goto L_0x05df;
                default: goto L_0x0063;
            }
        L_0x0063:
            int r3 = r3 + -3
            r0 = r2
            goto L_0x0031
        L_0x0067:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            double r6 = com.google.android.gms.internal.gtm.zztx.zzo(r11, r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0063
        L_0x007a:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = com.google.android.gms.internal.gtm.zztx.zzn(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0063
        L_0x008d:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r6)
            r12.zzi(r5, r6)
            goto L_0x0063
        L_0x00a0:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0063
        L_0x00b3:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r6)
            r12.zze(r5, r0)
            goto L_0x0063
        L_0x00c6:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r6)
            r12.zzc(r5, r6)
            goto L_0x0063
        L_0x00d9:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r6)
            r12.zzh(r5, r0)
            goto L_0x0063
        L_0x00ed:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = com.google.android.gms.internal.gtm.zztx.zzm(r11, r6)
            r12.zzb((int) r5, (boolean) r0)
            goto L_0x0063
        L_0x0101:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzum) r12)
            goto L_0x0063
        L_0x0115:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            com.google.android.gms.internal.gtm.zzsz r4 = r10.zzbo(r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzsz) r4)
            goto L_0x0063
        L_0x012d:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            com.google.android.gms.internal.gtm.zzps r0 = (com.google.android.gms.internal.gtm.zzps) r0
            r12.zza((int) r5, (com.google.android.gms.internal.gtm.zzps) r0)
            goto L_0x0063
        L_0x0143:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r6)
            r12.zzf(r5, r0)
            goto L_0x0063
        L_0x0157:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r6)
            r12.zzp(r5, r0)
            goto L_0x0063
        L_0x016b:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r6)
            r12.zzo(r5, r0)
            goto L_0x0063
        L_0x017f:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r6)
            r12.zzj(r5, r6)
            goto L_0x0063
        L_0x0193:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r6)
            r12.zzg(r5, r0)
            goto L_0x0063
        L_0x01a7:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0063
        L_0x01bb:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            com.google.android.gms.internal.gtm.zzsz r4 = r10.zzbo(r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzsz) r4)
            goto L_0x0063
        L_0x01d3:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (boolean) r4)
            goto L_0x0063
        L_0x01e8:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (boolean) r4)
            goto L_0x0063
        L_0x01fd:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzc(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0212:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzd(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0227:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzh(r5, r0, r12, r4)
            goto L_0x0063
        L_0x023c:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzf(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0251:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzk(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0266:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzn(r5, r0, r12, r4)
            goto L_0x0063
        L_0x027b:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.gtm.zztb.zza((int) r5, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.gtm.zzum) r12)
            goto L_0x0063
        L_0x028f:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.gtm.zzsz r4 = r10.zzbo(r3)
            com.google.android.gms.internal.gtm.zztb.zza((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (com.google.android.gms.internal.gtm.zzsz) r4)
            goto L_0x0063
        L_0x02a7:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.gtm.zztb.zzb(r5, r0, r12)
            goto L_0x0063
        L_0x02bb:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzi(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02d0:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzm(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02e5:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzl(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02fa:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzg(r5, r0, r12, r4)
            goto L_0x0063
        L_0x030f:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zzj(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0324:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.gtm.zztb.zze(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0339:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (boolean) r4)
            goto L_0x0063
        L_0x034e:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (boolean) r4)
            goto L_0x0063
        L_0x0363:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzc(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0378:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzd(r5, r0, r12, r4)
            goto L_0x0063
        L_0x038d:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzh(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03a2:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzf(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03b7:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzk(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03cc:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzn(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03e1:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzi(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03f6:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzm(r5, r0, r12, r4)
            goto L_0x0063
        L_0x040b:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzl(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0420:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzg(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0435:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zzj(r5, r0, r12, r4)
            goto L_0x0063
        L_0x044a:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.gtm.zztb.zze(r5, r0, r12, r4)
            goto L_0x0063
        L_0x045f:
            int[] r0 = r10.zzbcy
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.gtm.zzsz r4 = r10.zzbo(r3)
            com.google.android.gms.internal.gtm.zztb.zzb((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (com.google.android.gms.internal.gtm.zzsz) r4)
            goto L_0x0063
        L_0x0477:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            r10.zza((com.google.android.gms.internal.gtm.zzum) r12, (int) r5, (java.lang.Object) r0, (int) r3)
            goto L_0x0063
        L_0x0485:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            double r6 = zzf(r11, r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0063
        L_0x0499:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = zzg(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0063
        L_0x04ad:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zzi(r5, r6)
            goto L_0x0063
        L_0x04c1:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0063
        L_0x04d5:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zze(r5, r0)
            goto L_0x0063
        L_0x04e9:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zzc(r5, r6)
            goto L_0x0063
        L_0x04fd:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzh(r5, r0)
            goto L_0x0063
        L_0x0511:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = zzj(r11, r6)
            r12.zzb((int) r5, (boolean) r0)
            goto L_0x0063
        L_0x0525:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzum) r12)
            goto L_0x0063
        L_0x0539:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            com.google.android.gms.internal.gtm.zzsz r4 = r10.zzbo(r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzsz) r4)
            goto L_0x0063
        L_0x0551:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            com.google.android.gms.internal.gtm.zzps r0 = (com.google.android.gms.internal.gtm.zzps) r0
            r12.zza((int) r5, (com.google.android.gms.internal.gtm.zzps) r0)
            goto L_0x0063
        L_0x0567:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzf(r5, r0)
            goto L_0x0063
        L_0x057b:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzp(r5, r0)
            goto L_0x0063
        L_0x058f:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzo(r5, r0)
            goto L_0x0063
        L_0x05a3:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zzj(r5, r6)
            goto L_0x0063
        L_0x05b7:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzh(r11, r6)
            r12.zzg(r5, r0)
            goto L_0x0063
        L_0x05cb:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzi(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0063
        L_0x05df:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r6)
            com.google.android.gms.internal.gtm.zzsz r4 = r10.zzbo(r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzsz) r4)
            goto L_0x0063
        L_0x05f7:
            r0 = 0
        L_0x05f8:
            if (r0 == 0) goto L_0x0c13
            com.google.android.gms.internal.gtm.zzqq<?> r2 = r10.zzbdn
            r2.zza(r12, r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x05f7
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x05f8
        L_0x060c:
            boolean r0 = r10.zzbdf
            if (r0 == 0) goto L_0x0c14
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzbdd
            if (r2 == 0) goto L_0x062e
            com.google.android.gms.internal.gtm.zzqq<?> r2 = r10.zzbdn
            com.google.android.gms.internal.gtm.zzqt r2 = r2.zzr(r11)
            com.google.android.gms.internal.gtm.zztc<FieldDescriptorType, java.lang.Object> r3 = r2.zzaxo
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x062e
            java.util.Iterator r1 = r2.iterator()
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x062e:
            int[] r2 = r10.zzbcy
            int r4 = r2.length
            r2 = 0
            r3 = r2
        L_0x0633:
            if (r3 >= r4) goto L_0x0bfa
            int r5 = r10.zzbr(r3)
            int[] r2 = r10.zzbcy
            r6 = r2[r3]
            r2 = r0
        L_0x063e:
            if (r2 == 0) goto L_0x065d
            com.google.android.gms.internal.gtm.zzqq<?> r0 = r10.zzbdn
            int r0 = r0.zzb(r2)
            if (r0 > r6) goto L_0x065d
            com.google.android.gms.internal.gtm.zzqq<?> r0 = r10.zzbdn
            r0.zza(r12, r2)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x065b
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0659:
            r2 = r0
            goto L_0x063e
        L_0x065b:
            r0 = 0
            goto L_0x0659
        L_0x065d:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0669;
                case 1: goto L_0x067c;
                case 2: goto L_0x068f;
                case 3: goto L_0x06a2;
                case 4: goto L_0x06b5;
                case 5: goto L_0x06c8;
                case 6: goto L_0x06db;
                case 7: goto L_0x06ef;
                case 8: goto L_0x0703;
                case 9: goto L_0x0717;
                case 10: goto L_0x072f;
                case 11: goto L_0x0745;
                case 12: goto L_0x0759;
                case 13: goto L_0x076d;
                case 14: goto L_0x0781;
                case 15: goto L_0x0795;
                case 16: goto L_0x07a9;
                case 17: goto L_0x07bd;
                case 18: goto L_0x07d5;
                case 19: goto L_0x07ea;
                case 20: goto L_0x07ff;
                case 21: goto L_0x0814;
                case 22: goto L_0x0829;
                case 23: goto L_0x083e;
                case 24: goto L_0x0853;
                case 25: goto L_0x0868;
                case 26: goto L_0x087d;
                case 27: goto L_0x0891;
                case 28: goto L_0x08a9;
                case 29: goto L_0x08bd;
                case 30: goto L_0x08d2;
                case 31: goto L_0x08e7;
                case 32: goto L_0x08fc;
                case 33: goto L_0x0911;
                case 34: goto L_0x0926;
                case 35: goto L_0x093b;
                case 36: goto L_0x0950;
                case 37: goto L_0x0965;
                case 38: goto L_0x097a;
                case 39: goto L_0x098f;
                case 40: goto L_0x09a4;
                case 41: goto L_0x09b9;
                case 42: goto L_0x09ce;
                case 43: goto L_0x09e3;
                case 44: goto L_0x09f8;
                case 45: goto L_0x0a0d;
                case 46: goto L_0x0a22;
                case 47: goto L_0x0a37;
                case 48: goto L_0x0a4c;
                case 49: goto L_0x0a61;
                case 50: goto L_0x0a79;
                case 51: goto L_0x0a87;
                case 52: goto L_0x0a9b;
                case 53: goto L_0x0aaf;
                case 54: goto L_0x0ac3;
                case 55: goto L_0x0ad7;
                case 56: goto L_0x0aeb;
                case 57: goto L_0x0aff;
                case 58: goto L_0x0b13;
                case 59: goto L_0x0b27;
                case 60: goto L_0x0b3b;
                case 61: goto L_0x0b53;
                case 62: goto L_0x0b69;
                case 63: goto L_0x0b7d;
                case 64: goto L_0x0b91;
                case 65: goto L_0x0ba5;
                case 66: goto L_0x0bb9;
                case 67: goto L_0x0bcd;
                case 68: goto L_0x0be1;
                default: goto L_0x0665;
            }
        L_0x0665:
            int r3 = r3 + 3
            r0 = r2
            goto L_0x0633
        L_0x0669:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            double r8 = com.google.android.gms.internal.gtm.zztx.zzo(r11, r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0665
        L_0x067c:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = com.google.android.gms.internal.gtm.zztx.zzn(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0665
        L_0x068f:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r8)
            r12.zzi(r6, r8)
            goto L_0x0665
        L_0x06a2:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0665
        L_0x06b5:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r8)
            r12.zze(r6, r0)
            goto L_0x0665
        L_0x06c8:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r8)
            r12.zzc(r6, r8)
            goto L_0x0665
        L_0x06db:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r8)
            r12.zzh(r6, r0)
            goto L_0x0665
        L_0x06ef:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = com.google.android.gms.internal.gtm.zztx.zzm(r11, r8)
            r12.zzb((int) r6, (boolean) r0)
            goto L_0x0665
        L_0x0703:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzum) r12)
            goto L_0x0665
        L_0x0717:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            com.google.android.gms.internal.gtm.zzsz r5 = r10.zzbo(r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzsz) r5)
            goto L_0x0665
        L_0x072f:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            com.google.android.gms.internal.gtm.zzps r0 = (com.google.android.gms.internal.gtm.zzps) r0
            r12.zza((int) r6, (com.google.android.gms.internal.gtm.zzps) r0)
            goto L_0x0665
        L_0x0745:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r8)
            r12.zzf(r6, r0)
            goto L_0x0665
        L_0x0759:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r8)
            r12.zzp(r6, r0)
            goto L_0x0665
        L_0x076d:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r8)
            r12.zzo(r6, r0)
            goto L_0x0665
        L_0x0781:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r8)
            r12.zzj(r6, r8)
            goto L_0x0665
        L_0x0795:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.gtm.zztx.zzk(r11, r8)
            r12.zzg(r6, r0)
            goto L_0x0665
        L_0x07a9:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.gtm.zztx.zzl(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0665
        L_0x07bd:
            boolean r0 = r10.zzb(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            com.google.android.gms.internal.gtm.zzsz r5 = r10.zzbo(r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzsz) r5)
            goto L_0x0665
        L_0x07d5:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (boolean) r5)
            goto L_0x0665
        L_0x07ea:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (boolean) r5)
            goto L_0x0665
        L_0x07ff:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzc(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0814:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzd(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0829:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzh(r6, r0, r12, r5)
            goto L_0x0665
        L_0x083e:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzf(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0853:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzk(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0868:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzn(r6, r0, r12, r5)
            goto L_0x0665
        L_0x087d:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.gtm.zztb.zza((int) r6, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.gtm.zzum) r12)
            goto L_0x0665
        L_0x0891:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.gtm.zzsz r5 = r10.zzbo(r3)
            com.google.android.gms.internal.gtm.zztb.zza((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (com.google.android.gms.internal.gtm.zzsz) r5)
            goto L_0x0665
        L_0x08a9:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.gtm.zztb.zzb(r6, r0, r12)
            goto L_0x0665
        L_0x08bd:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzi(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08d2:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzm(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08e7:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzl(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08fc:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzg(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0911:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zzj(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0926:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.gtm.zztb.zze(r6, r0, r12, r5)
            goto L_0x0665
        L_0x093b:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (boolean) r5)
            goto L_0x0665
        L_0x0950:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (boolean) r5)
            goto L_0x0665
        L_0x0965:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzc(r6, r0, r12, r5)
            goto L_0x0665
        L_0x097a:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzd(r6, r0, r12, r5)
            goto L_0x0665
        L_0x098f:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzh(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09a4:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzf(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09b9:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzk(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09ce:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzn(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09e3:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzi(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09f8:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzm(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a0d:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzl(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a22:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzg(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a37:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zzj(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a4c:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.gtm.zztb.zze(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a61:
            int[] r0 = r10.zzbcy
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.gtm.zzsz r5 = r10.zzbo(r3)
            com.google.android.gms.internal.gtm.zztb.zzb((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.gtm.zzum) r12, (com.google.android.gms.internal.gtm.zzsz) r5)
            goto L_0x0665
        L_0x0a79:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            r10.zza((com.google.android.gms.internal.gtm.zzum) r12, (int) r6, (java.lang.Object) r0, (int) r3)
            goto L_0x0665
        L_0x0a87:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            double r8 = zzf(r11, r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0665
        L_0x0a9b:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = zzg(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0665
        L_0x0aaf:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zzi(r6, r8)
            goto L_0x0665
        L_0x0ac3:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0665
        L_0x0ad7:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zze(r6, r0)
            goto L_0x0665
        L_0x0aeb:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zzc(r6, r8)
            goto L_0x0665
        L_0x0aff:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzh(r6, r0)
            goto L_0x0665
        L_0x0b13:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = zzj(r11, r8)
            r12.zzb((int) r6, (boolean) r0)
            goto L_0x0665
        L_0x0b27:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzum) r12)
            goto L_0x0665
        L_0x0b3b:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            com.google.android.gms.internal.gtm.zzsz r5 = r10.zzbo(r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzsz) r5)
            goto L_0x0665
        L_0x0b53:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            com.google.android.gms.internal.gtm.zzps r0 = (com.google.android.gms.internal.gtm.zzps) r0
            r12.zza((int) r6, (com.google.android.gms.internal.gtm.zzps) r0)
            goto L_0x0665
        L_0x0b69:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzf(r6, r0)
            goto L_0x0665
        L_0x0b7d:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzp(r6, r0)
            goto L_0x0665
        L_0x0b91:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzo(r6, r0)
            goto L_0x0665
        L_0x0ba5:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zzj(r6, r8)
            goto L_0x0665
        L_0x0bb9:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzh(r11, r8)
            r12.zzg(r6, r0)
            goto L_0x0665
        L_0x0bcd:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzi(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0665
        L_0x0be1:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r11, r8)
            com.google.android.gms.internal.gtm.zzsz r5 = r10.zzbo(r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.gtm.zzsz) r5)
            goto L_0x0665
        L_0x0bf9:
            r0 = 0
        L_0x0bfa:
            if (r0 == 0) goto L_0x0c0e
            com.google.android.gms.internal.gtm.zzqq<?> r2 = r10.zzbdn
            r2.zza(r12, r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0bf9
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x0bfa
        L_0x0c0e:
            com.google.android.gms.internal.gtm.zztr<?, ?> r0 = r10.zzbdm
            zza(r0, r11, (com.google.android.gms.internal.gtm.zzum) r12)
        L_0x0c13:
            return
        L_0x0c14:
            r10.zzb(r11, (com.google.android.gms.internal.gtm.zzum) r12)
            goto L_0x0c13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzso.zza(java.lang.Object, com.google.android.gms.internal.gtm.zzum):void");
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 387 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r21, com.google.android.gms.internal.gtm.zzum r22) throws java.io.IOException {
        /*
            r20 = this;
            r5 = 0
            r4 = 0
            r0 = r20
            boolean r6 = r0.zzbdd
            if (r6 == 0) goto L_0x0024
            r0 = r20
            com.google.android.gms.internal.gtm.zzqq<?> r6 = r0.zzbdn
            r0 = r21
            com.google.android.gms.internal.gtm.zzqt r6 = r6.zzr(r0)
            com.google.android.gms.internal.gtm.zztc<FieldDescriptorType, java.lang.Object> r7 = r6.zzaxo
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L_0x0024
            java.util.Iterator r5 = r6.iterator()
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
        L_0x0024:
            r8 = -1
            r6 = 0
            r0 = r20
            int[] r7 = r0.zzbcy
            int r13 = r7.length
            sun.misc.Unsafe r14 = zzbcx
            r7 = 0
            r12 = r7
            r9 = r4
        L_0x0030:
            if (r12 >= r13) goto L_0x06fd
            r0 = r20
            int r15 = r0.zzbr(r12)
            r0 = r20
            int[] r4 = r0.zzbcy
            r16 = r4[r12]
            r4 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r15
            int r17 = r4 >>> 20
            r4 = 0
            r0 = r20
            boolean r7 = r0.zzbdf
            if (r7 != 0) goto L_0x06f9
            r7 = 17
            r0 = r17
            if (r0 > r7) goto L_0x06f9
            r0 = r20
            int[] r4 = r0.zzbcy
            int r7 = r12 + 2
            r10 = r4[r7]
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r10 & r4
            if (r7 == r8) goto L_0x06f5
            long r0 = (long) r7
            r18 = r0
            r0 = r21
            r1 = r18
            int r4 = r14.getInt(r0, r1)
        L_0x006a:
            r6 = 1
            int r8 = r10 >>> 20
            int r6 = r6 << r8
            r10 = r6
            r11 = r4
            r8 = r7
        L_0x0071:
            if (r9 == 0) goto L_0x0098
            r0 = r20
            com.google.android.gms.internal.gtm.zzqq<?> r4 = r0.zzbdn
            int r4 = r4.zzb(r9)
            r0 = r16
            if (r4 > r0) goto L_0x0098
            r0 = r20
            com.google.android.gms.internal.gtm.zzqq<?> r4 = r0.zzbdn
            r0 = r22
            r4.zza(r0, r9)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x0096
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
        L_0x0094:
            r9 = r4
            goto L_0x0071
        L_0x0096:
            r4 = 0
            goto L_0x0094
        L_0x0098:
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r15
            long r6 = (long) r4
            switch(r17) {
                case 0: goto L_0x00a5;
                case 1: goto L_0x00b7;
                case 2: goto L_0x00c9;
                case 3: goto L_0x00db;
                case 4: goto L_0x00ed;
                case 5: goto L_0x00ff;
                case 6: goto L_0x0111;
                case 7: goto L_0x0124;
                case 8: goto L_0x0137;
                case 9: goto L_0x014a;
                case 10: goto L_0x0163;
                case 11: goto L_0x0178;
                case 12: goto L_0x018b;
                case 13: goto L_0x019e;
                case 14: goto L_0x01b1;
                case 15: goto L_0x01c4;
                case 16: goto L_0x01d7;
                case 17: goto L_0x01ea;
                case 18: goto L_0x0203;
                case 19: goto L_0x0219;
                case 20: goto L_0x022f;
                case 21: goto L_0x0245;
                case 22: goto L_0x025b;
                case 23: goto L_0x0271;
                case 24: goto L_0x0287;
                case 25: goto L_0x029d;
                case 26: goto L_0x02b3;
                case 27: goto L_0x02c8;
                case 28: goto L_0x02e3;
                case 29: goto L_0x02f8;
                case 30: goto L_0x030e;
                case 31: goto L_0x0324;
                case 32: goto L_0x033a;
                case 33: goto L_0x0350;
                case 34: goto L_0x0366;
                case 35: goto L_0x037c;
                case 36: goto L_0x0392;
                case 37: goto L_0x03a8;
                case 38: goto L_0x03be;
                case 39: goto L_0x03d4;
                case 40: goto L_0x03ea;
                case 41: goto L_0x0400;
                case 42: goto L_0x0416;
                case 43: goto L_0x042c;
                case 44: goto L_0x0442;
                case 45: goto L_0x0458;
                case 46: goto L_0x046e;
                case 47: goto L_0x0484;
                case 48: goto L_0x049a;
                case 49: goto L_0x04b0;
                case 50: goto L_0x04cb;
                case 51: goto L_0x04dc;
                case 52: goto L_0x04f7;
                case 53: goto L_0x0512;
                case 54: goto L_0x052d;
                case 55: goto L_0x0548;
                case 56: goto L_0x0563;
                case 57: goto L_0x057e;
                case 58: goto L_0x0599;
                case 59: goto L_0x05b4;
                case 60: goto L_0x05cf;
                case 61: goto L_0x05f0;
                case 62: goto L_0x060d;
                case 63: goto L_0x0628;
                case 64: goto L_0x0643;
                case 65: goto L_0x065e;
                case 66: goto L_0x0679;
                case 67: goto L_0x0694;
                case 68: goto L_0x06af;
                default: goto L_0x00a0;
            }
        L_0x00a0:
            int r4 = r12 + 3
            r12 = r4
            r6 = r11
            goto L_0x0030
        L_0x00a5:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            double r6 = com.google.android.gms.internal.gtm.zztx.zzo(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x00a0
        L_0x00b7:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            float r4 = com.google.android.gms.internal.gtm.zztx.zzn(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (float) r4)
            goto L_0x00a0
        L_0x00c9:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzi(r1, r6)
            goto L_0x00a0
        L_0x00db:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (long) r6)
            goto L_0x00a0
        L_0x00ed:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zze(r1, r4)
            goto L_0x00a0
        L_0x00ff:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc(r1, r6)
            goto L_0x00a0
        L_0x0111:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzh(r1, r4)
            goto L_0x00a0
        L_0x0124:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            boolean r4 = com.google.android.gms.internal.gtm.zztx.zzm(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (boolean) r4)
            goto L_0x00a0
        L_0x0137:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r16
            r1 = r22
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.gtm.zzum) r1)
            goto L_0x00a0
        L_0x014a:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.gtm.zzsz r6 = r0.zzbo(r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.gtm.zzsz) r6)
            goto L_0x00a0
        L_0x0163:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.gtm.zzps r4 = (com.google.android.gms.internal.gtm.zzps) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.gtm.zzps) r4)
            goto L_0x00a0
        L_0x0178:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzf(r1, r4)
            goto L_0x00a0
        L_0x018b:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzp(r1, r4)
            goto L_0x00a0
        L_0x019e:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzo(r1, r4)
            goto L_0x00a0
        L_0x01b1:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzj(r1, r6)
            goto L_0x00a0
        L_0x01c4:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzg(r1, r4)
            goto L_0x00a0
        L_0x01d7:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (long) r6)
            goto L_0x00a0
        L_0x01ea:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.gtm.zzsz r6 = r0.zzbo(r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.gtm.zzsz) r6)
            goto L_0x00a0
        L_0x0203:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.gtm.zzum) r0, (boolean) r6)
            goto L_0x00a0
        L_0x0219:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.gtm.zzum) r0, (boolean) r6)
            goto L_0x00a0
        L_0x022f:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzc(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0245:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzd(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x025b:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzh(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0271:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzf(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0287:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzk(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x029d:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzn(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x02b3:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zza((int) r10, (java.util.List<java.lang.String>) r4, (com.google.android.gms.internal.gtm.zzum) r0)
            goto L_0x00a0
        L_0x02c8:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.gtm.zzsz r6 = r0.zzbo(r12)
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zza((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.gtm.zzum) r0, (com.google.android.gms.internal.gtm.zzsz) r6)
            goto L_0x00a0
        L_0x02e3:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzb(r10, r4, r0)
            goto L_0x00a0
        L_0x02f8:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzi(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x030e:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzm(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0324:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzl(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x033a:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzg(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0350:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzj(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0366:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zze(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x037c:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.gtm.zzum) r0, (boolean) r6)
            goto L_0x00a0
        L_0x0392:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.gtm.zzum) r0, (boolean) r6)
            goto L_0x00a0
        L_0x03a8:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzc(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x03be:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzd(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x03d4:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzh(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x03ea:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzf(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0400:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzk(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0416:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzn(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x042c:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzi(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0442:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzm(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0458:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzl(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x046e:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzg(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x0484:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzj(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x049a:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zze(r10, r4, r0, r6)
            goto L_0x00a0
        L_0x04b0:
            r0 = r20
            int[] r4 = r0.zzbcy
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.gtm.zzsz r6 = r0.zzbo(r12)
            r0 = r22
            com.google.android.gms.internal.gtm.zztb.zzb((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.gtm.zzum) r0, (com.google.android.gms.internal.gtm.zzsz) r6)
            goto L_0x00a0
        L_0x04cb:
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            r1 = r22
            r2 = r16
            r0.zza((com.google.android.gms.internal.gtm.zzum) r1, (int) r2, (java.lang.Object) r4, (int) r12)
            goto L_0x00a0
        L_0x04dc:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            double r6 = zzf(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x00a0
        L_0x04f7:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            float r4 = zzg(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (float) r4)
            goto L_0x00a0
        L_0x0512:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzi(r1, r6)
            goto L_0x00a0
        L_0x052d:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (long) r6)
            goto L_0x00a0
        L_0x0548:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zze(r1, r4)
            goto L_0x00a0
        L_0x0563:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc(r1, r6)
            goto L_0x00a0
        L_0x057e:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzh(r1, r4)
            goto L_0x00a0
        L_0x0599:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            boolean r4 = zzj(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (boolean) r4)
            goto L_0x00a0
        L_0x05b4:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r16
            r1 = r22
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.gtm.zzum) r1)
            goto L_0x00a0
        L_0x05cf:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.gtm.zzsz r6 = r0.zzbo(r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.gtm.zzsz) r6)
            goto L_0x00a0
        L_0x05f0:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.gtm.zzps r4 = (com.google.android.gms.internal.gtm.zzps) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.gtm.zzps) r4)
            goto L_0x00a0
        L_0x060d:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzf(r1, r4)
            goto L_0x00a0
        L_0x0628:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzp(r1, r4)
            goto L_0x00a0
        L_0x0643:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzo(r1, r4)
            goto L_0x00a0
        L_0x065e:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzj(r1, r6)
            goto L_0x00a0
        L_0x0679:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            int r4 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzg(r1, r4)
            goto L_0x00a0
        L_0x0694:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            long r6 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (long) r6)
            goto L_0x00a0
        L_0x06af:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a0
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.gtm.zzsz r6 = r0.zzbo(r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.gtm.zzsz) r6)
            goto L_0x00a0
        L_0x06d0:
            r4 = 0
        L_0x06d1:
            if (r4 == 0) goto L_0x06e9
            r0 = r20
            com.google.android.gms.internal.gtm.zzqq<?> r6 = r0.zzbdn
            r0 = r22
            r6.zza(r0, r4)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x06d0
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x06d1
        L_0x06e9:
            r0 = r20
            com.google.android.gms.internal.gtm.zztr<?, ?> r4 = r0.zzbdm
            r0 = r21
            r1 = r22
            zza(r4, r0, (com.google.android.gms.internal.gtm.zzum) r1)
            return
        L_0x06f5:
            r4 = r6
            r7 = r8
            goto L_0x006a
        L_0x06f9:
            r10 = r4
            r11 = r6
            goto L_0x0071
        L_0x06fd:
            r4 = r9
            goto L_0x06d1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzso.zzb(java.lang.Object, com.google.android.gms.internal.gtm.zzum):void");
    }

    private final <K, V> void zza(zzum zzum, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzum.zza(i, this.zzbdo.zzac(zzbp(i2)), this.zzbdo.zzy(obj));
        }
    }

    private static <UT, UB> void zza(zztr<UT, UB> zztr, T t, zzum zzum) throws IOException {
        zztr.zza(zztr.zzag(t), zzum);
    }

    public final void zza(T t, zzsy zzsy, zzqp zzqp) throws IOException {
        Throwable th;
        Object obj;
        int i;
        Object obj2;
        Object obj3;
        Object obj4;
        Object zza;
        if (zzqp == null) {
            throw new NullPointerException();
        }
        zztr<?, ?> zztr = this.zzbdm;
        zzqq<?> zzqq = this.zzbdn;
        Object obj5 = null;
        zzqt<?> zzqt = null;
        while (true) {
            int zzog = zzsy.zzog();
            if (zzog < this.zzbda || zzog > this.zzbdb) {
                i = -1;
            } else {
                int i2 = 0;
                int length = (this.zzbcy.length / 3) - 1;
                while (true) {
                    if (i2 > length) {
                        i = -1;
                    } else {
                        int i3 = (length + i2) >>> 1;
                        i = i3 * 3;
                        int i4 = this.zzbcy[i];
                        if (zzog != i4) {
                            if (zzog < i4) {
                                length = i3 - 1;
                            } else {
                                i2 = i3 + 1;
                            }
                        }
                    }
                }
            }
            if (i >= 0) {
                int zzbr = zzbr(i);
                switch ((267386880 & zzbr) >>> 20) {
                    case 0:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.readDouble());
                        zzc(t, i);
                        continue;
                    case 1:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.readFloat());
                        zzc(t, i);
                        continue;
                    case 2:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zznk());
                        zzc(t, i);
                        continue;
                    case 3:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zznj());
                        zzc(t, i);
                        continue;
                    case 4:
                        zztx.zzb((Object) t, (long) (zzbr & 1048575), zzsy.zznl());
                        zzc(t, i);
                        continue;
                    case 5:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zznm());
                        zzc(t, i);
                        continue;
                    case 6:
                        zztx.zzb((Object) t, (long) (zzbr & 1048575), zzsy.zznn());
                        zzc(t, i);
                        continue;
                    case 7:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zzno());
                        zzc(t, i);
                        continue;
                    case 8:
                        zza((Object) t, zzbr, zzsy);
                        zzc(t, i);
                        continue;
                    case 9:
                        if (!zzb(t, i)) {
                            zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zza(zzbo(i), zzqp));
                            zzc(t, i);
                            break;
                        } else {
                            zztx.zza((Object) t, (long) (zzbr & 1048575), zzre.zzb(zztx.zzp(t, (long) (1048575 & zzbr)), zzsy.zza(zzbo(i), zzqp)));
                            continue;
                        }
                    case 10:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) zzsy.zznq());
                        zzc(t, i);
                        continue;
                    case 11:
                        zztx.zzb((Object) t, (long) (zzbr & 1048575), zzsy.zznr());
                        zzc(t, i);
                        continue;
                    case 12:
                        int zzns = zzsy.zzns();
                        zzrh zzbq = zzbq(i);
                        if (zzbq != null && !zzbq.zzb(zzns)) {
                            obj5 = zztb.zza(zzog, zzns, obj5, zztr);
                            break;
                        } else {
                            zztx.zzb((Object) t, (long) (zzbr & 1048575), zzns);
                            zzc(t, i);
                            continue;
                        }
                        break;
                    case 13:
                        zztx.zzb((Object) t, (long) (zzbr & 1048575), zzsy.zznt());
                        zzc(t, i);
                        continue;
                    case 14:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zznu());
                        zzc(t, i);
                        continue;
                    case 15:
                        zztx.zzb((Object) t, (long) (zzbr & 1048575), zzsy.zznv());
                        zzc(t, i);
                        continue;
                    case 16:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zznw());
                        zzc(t, i);
                        continue;
                    case 17:
                        if (!zzb(t, i)) {
                            zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zzb(zzbo(i), zzqp));
                            zzc(t, i);
                            break;
                        } else {
                            zztx.zza((Object) t, (long) (zzbr & 1048575), zzre.zzb(zztx.zzp(t, (long) (1048575 & zzbr)), zzsy.zzb(zzbo(i), zzqp)));
                            continue;
                        }
                    case 18:
                        zzsy.zzg(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 19:
                        zzsy.zzh(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 20:
                        zzsy.zzj(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 21:
                        zzsy.zzi(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 22:
                        zzsy.zzk(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 23:
                        zzsy.zzl(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 24:
                        zzsy.zzm(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 25:
                        zzsy.zzn(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 26:
                        if (!zzbt(zzbr)) {
                            zzsy.readStringList(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                            break;
                        } else {
                            zzsy.zzo(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                            continue;
                        }
                    case 27:
                        zzsy.zza(this.zzbdl.zza(t, (long) (zzbr & 1048575)), zzbo(i), zzqp);
                        continue;
                    case 28:
                        zzsy.zzp(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 29:
                        zzsy.zzq(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 30:
                        List zza2 = this.zzbdl.zza(t, (long) (zzbr & 1048575));
                        zzsy.zzr(zza2);
                        obj5 = zztb.zza(zzog, zza2, zzbq(i), obj5, zztr);
                        continue;
                    case 31:
                        zzsy.zzs(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 32:
                        zzsy.zzt(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 33:
                        zzsy.zzu(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 34:
                        zzsy.zzv(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 35:
                        zzsy.zzg(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 36:
                        zzsy.zzh(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 37:
                        zzsy.zzj(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 38:
                        zzsy.zzi(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 39:
                        zzsy.zzk(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 40:
                        zzsy.zzl(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 41:
                        zzsy.zzm(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 42:
                        zzsy.zzn(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 43:
                        zzsy.zzq(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 44:
                        List zza3 = this.zzbdl.zza(t, (long) (zzbr & 1048575));
                        zzsy.zzr(zza3);
                        obj5 = zztb.zza(zzog, zza3, zzbq(i), obj5, zztr);
                        continue;
                    case 45:
                        zzsy.zzs(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 46:
                        zzsy.zzt(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 47:
                        zzsy.zzu(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 48:
                        zzsy.zzv(this.zzbdl.zza(t, (long) (zzbr & 1048575)));
                        continue;
                    case 49:
                        zzsy.zzb(this.zzbdl.zza(t, (long) (zzbr & 1048575)), zzbo(i), zzqp);
                        continue;
                    case 50:
                        Object zzbp = zzbp(i);
                        long zzbr2 = (long) (zzbr(i) & 1048575);
                        Object zzp = zztx.zzp(t, zzbr2);
                        if (zzp == null) {
                            obj2 = this.zzbdo.zzab(zzbp);
                            zztx.zza((Object) t, zzbr2, obj2);
                        } else if (this.zzbdo.zzz(zzp)) {
                            obj2 = this.zzbdo.zzab(zzbp);
                            this.zzbdo.zzc(obj2, zzp);
                            zztx.zza((Object) t, zzbr2, obj2);
                        } else {
                            obj2 = zzp;
                        }
                        zzsy.zza(this.zzbdo.zzx(obj2), this.zzbdo.zzac(zzbp), zzqp);
                        continue;
                    case 51:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Double.valueOf(zzsy.readDouble()));
                        zzb(t, zzog, i);
                        continue;
                    case 52:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Float.valueOf(zzsy.readFloat()));
                        zzb(t, zzog, i);
                        continue;
                    case 53:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Long.valueOf(zzsy.zznk()));
                        zzb(t, zzog, i);
                        continue;
                    case 54:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Long.valueOf(zzsy.zznj()));
                        zzb(t, zzog, i);
                        continue;
                    case 55:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Integer.valueOf(zzsy.zznl()));
                        zzb(t, zzog, i);
                        continue;
                    case 56:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Long.valueOf(zzsy.zznm()));
                        zzb(t, zzog, i);
                        continue;
                    case 57:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Integer.valueOf(zzsy.zznn()));
                        zzb(t, zzog, i);
                        continue;
                    case 58:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Boolean.valueOf(zzsy.zzno()));
                        zzb(t, zzog, i);
                        continue;
                    case 59:
                        zza((Object) t, zzbr, zzsy);
                        zzb(t, zzog, i);
                        continue;
                    case 60:
                        if (zza(t, zzog, i)) {
                            zztx.zza((Object) t, (long) (zzbr & 1048575), zzre.zzb(zztx.zzp(t, (long) (1048575 & zzbr)), zzsy.zza(zzbo(i), zzqp)));
                        } else {
                            zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zza(zzbo(i), zzqp));
                            zzc(t, i);
                        }
                        zzb(t, zzog, i);
                        continue;
                    case 61:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) zzsy.zznq());
                        zzb(t, zzog, i);
                        continue;
                    case 62:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Integer.valueOf(zzsy.zznr()));
                        zzb(t, zzog, i);
                        continue;
                    case 63:
                        int zzns2 = zzsy.zzns();
                        zzrh zzbq2 = zzbq(i);
                        if (zzbq2 != null && !zzbq2.zzb(zzns2)) {
                            obj5 = zztb.zza(zzog, zzns2, obj5, zztr);
                            break;
                        } else {
                            zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Integer.valueOf(zzns2));
                            zzb(t, zzog, i);
                            continue;
                        }
                        break;
                    case 64:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Integer.valueOf(zzsy.zznt()));
                        zzb(t, zzog, i);
                        continue;
                    case 65:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Long.valueOf(zzsy.zznu()));
                        zzb(t, zzog, i);
                        continue;
                    case 66:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Integer.valueOf(zzsy.zznv()));
                        zzb(t, zzog, i);
                        continue;
                    case 67:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), (Object) Long.valueOf(zzsy.zznw()));
                        zzb(t, zzog, i);
                        continue;
                    case 68:
                        zztx.zza((Object) t, (long) (zzbr & 1048575), zzsy.zzb(zzbo(i), zzqp));
                        zzb(t, zzog, i);
                        continue;
                    default:
                        if (obj5 == null) {
                            try {
                                obj4 = zztr.zzri();
                            } catch (zzrl e) {
                                break;
                            }
                        } else {
                            obj4 = obj5;
                        }
                        try {
                            if (!zztr.zza(obj4, zzsy)) {
                                for (int i5 = this.zzbdi; i5 < this.zzbdj; i5++) {
                                    obj4 = zza((Object) t, this.zzbdh[i5], obj4, zztr);
                                }
                                if (obj4 != null) {
                                    zztr.zzg(t, obj4);
                                    return;
                                }
                                return;
                            }
                            obj5 = obj4;
                            continue;
                        } catch (zzrl e2) {
                            obj5 = obj4;
                            break;
                        }
                }
                try {
                    zztr.zza(zzsy);
                    if (obj5 == null) {
                        obj3 = zztr.zzah(t);
                    } else {
                        obj3 = obj5;
                    }
                    if (!zztr.zza(obj, zzsy)) {
                        for (int i6 = this.zzbdi; i6 < this.zzbdj; i6++) {
                            obj = zza((Object) t, this.zzbdh[i6], obj, zztr);
                        }
                        if (obj != null) {
                            zztr.zzg(t, obj);
                            return;
                        }
                        return;
                    }
                    obj5 = obj;
                } catch (Throwable th2) {
                    th = th2;
                    obj = obj5;
                    for (int i7 = this.zzbdi; i7 < this.zzbdj; i7++) {
                        obj = zza((Object) t, this.zzbdh[i7], obj, zztr);
                    }
                    if (obj != null) {
                        zztr.zzg(t, obj);
                    }
                    throw th;
                }
            } else if (zzog == Integer.MAX_VALUE) {
                for (int i8 = this.zzbdi; i8 < this.zzbdj; i8++) {
                    obj5 = zza((Object) t, this.zzbdh[i8], obj5, zztr);
                }
                if (obj5 != null) {
                    zztr.zzg(t, obj5);
                    return;
                }
                return;
            } else {
                if (!this.zzbdd) {
                    zza = null;
                } else {
                    zza = zzqq.zza(zzqp, this.zzbdc, zzog);
                }
                if (zza != null) {
                    if (zzqt == null) {
                        zzqt = zzqq.zzs(t);
                    }
                    obj5 = zzqq.zza(zzsy, zza, zzqp, zzqt, obj5, zztr);
                } else {
                    zztr.zza(zzsy);
                    if (obj5 == null) {
                        obj = zztr.zzah(t);
                    } else {
                        obj = obj5;
                    }
                    try {
                        if (!zztr.zza(obj, zzsy)) {
                            for (int i9 = this.zzbdi; i9 < this.zzbdj; i9++) {
                                obj = zza((Object) t, this.zzbdh[i9], obj, zztr);
                            }
                            if (obj != null) {
                                zztr.zzg(t, obj);
                                return;
                            }
                            return;
                        }
                        obj5 = obj;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
        }
    }

    private final zzsz zzbo(int i) {
        int i2 = (i / 3) << 1;
        zzsz zzsz = (zzsz) this.zzbcz[i2];
        if (zzsz != null) {
            return zzsz;
        }
        zzsz zzi = zzsw.zzqs().zzi((Class) this.zzbcz[i2 + 1]);
        this.zzbcz[i2] = zzi;
        return zzi;
    }

    private final Object zzbp(int i) {
        return this.zzbcz[(i / 3) << 1];
    }

    private final zzrh zzbq(int i) {
        return (zzrh) this.zzbcz[((i / 3) << 1) + 1];
    }

    public final void zzt(T t) {
        for (int i = this.zzbdi; i < this.zzbdj; i++) {
            long zzbr = (long) (zzbr(this.zzbdh[i]) & 1048575);
            Object zzp = zztx.zzp(t, zzbr);
            if (zzp != null) {
                zztx.zza((Object) t, zzbr, this.zzbdo.zzaa(zzp));
            }
        }
        int length = this.zzbdh.length;
        for (int i2 = this.zzbdj; i2 < length; i2++) {
            this.zzbdl.zzb(t, (long) this.zzbdh[i2]);
        }
        this.zzbdm.zzt(t);
        if (this.zzbdd) {
            this.zzbdn.zzt(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zztr<UT, UB> zztr) {
        zzrh zzbq;
        int i2 = this.zzbcy[i];
        Object zzp = zztx.zzp(obj, (long) (zzbr(i) & 1048575));
        if (zzp == null || (zzbq = zzbq(i)) == null) {
            return ub;
        }
        return zza(i, i2, this.zzbdo.zzx(zzp), zzbq, ub, zztr);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzrh zzrh, UB ub, zztr<UT, UB> zztr) {
        zzsd<?, ?> zzac = this.zzbdo.zzac(zzbp(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!zzrh.zzb(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zztr.zzri();
                }
                zzqa zzam = zzps.zzam(zzsc.zza(zzac, next.getKey(), next.getValue()));
                try {
                    zzsc.zza(zzam.zznh(), zzac, next.getKey(), next.getValue());
                    zztr.zza(ub, i2, zzam.zzng());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x0042 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x004d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzae(T r14) {
        /*
            r13 = this;
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r6 = 1
            r2 = 0
            r0 = -1
            r1 = r2
            r3 = r2
            r4 = r0
        L_0x0009:
            int r0 = r13.zzbdi
            if (r1 >= r0) goto L_0x00f5
            int[] r0 = r13.zzbdh
            r8 = r0[r1]
            int[] r0 = r13.zzbcy
            r9 = r0[r8]
            int r10 = r13.zzbr(r8)
            boolean r0 = r13.zzbdf
            if (r0 != 0) goto L_0x010b
            int[] r0 = r13.zzbcy
            int r5 = r8 + 2
            r0 = r0[r5]
            r7 = r0 & r12
            int r0 = r0 >>> 20
            int r0 = r6 << r0
            if (r7 == r4) goto L_0x0108
            sun.misc.Unsafe r3 = zzbcx
            long r4 = (long) r7
            int r3 = r3.getInt(r14, r4)
            r5 = r0
            r4 = r7
        L_0x0034:
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            r0 = r0 & r10
            if (r0 == 0) goto L_0x0043
            r0 = r6
        L_0x003a:
            if (r0 == 0) goto L_0x0045
            boolean r0 = r13.zza(r14, (int) r8, (int) r3, (int) r5)
            if (r0 != 0) goto L_0x0045
        L_0x0042:
            return r2
        L_0x0043:
            r0 = r2
            goto L_0x003a
        L_0x0045:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r10
            int r0 = r0 >>> 20
            switch(r0) {
                case 9: goto L_0x0051;
                case 17: goto L_0x0051;
                case 27: goto L_0x0062;
                case 49: goto L_0x0062;
                case 50: goto L_0x00a0;
                case 60: goto L_0x008f;
                case 68: goto L_0x008f;
                default: goto L_0x004d;
            }
        L_0x004d:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0009
        L_0x0051:
            boolean r0 = r13.zza(r14, (int) r8, (int) r3, (int) r5)
            if (r0 == 0) goto L_0x004d
            com.google.android.gms.internal.gtm.zzsz r0 = r13.zzbo(r8)
            boolean r0 = zza((java.lang.Object) r14, (int) r10, (com.google.android.gms.internal.gtm.zzsz) r0)
            if (r0 != 0) goto L_0x004d
            goto L_0x0042
        L_0x0062:
            r0 = r10 & r12
            long r10 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.gtm.zztx.zzp(r14, r10)
            java.util.List r0 = (java.util.List) r0
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto L_0x008d
            com.google.android.gms.internal.gtm.zzsz r7 = r13.zzbo(r8)
            r5 = r2
        L_0x0076:
            int r8 = r0.size()
            if (r5 >= r8) goto L_0x008d
            java.lang.Object r8 = r0.get(r5)
            boolean r8 = r7.zzae(r8)
            if (r8 != 0) goto L_0x008a
            r0 = r2
        L_0x0087:
            if (r0 != 0) goto L_0x004d
            goto L_0x0042
        L_0x008a:
            int r5 = r5 + 1
            goto L_0x0076
        L_0x008d:
            r0 = r6
            goto L_0x0087
        L_0x008f:
            boolean r0 = r13.zza(r14, (int) r9, (int) r8)
            if (r0 == 0) goto L_0x004d
            com.google.android.gms.internal.gtm.zzsz r0 = r13.zzbo(r8)
            boolean r0 = zza((java.lang.Object) r14, (int) r10, (com.google.android.gms.internal.gtm.zzsz) r0)
            if (r0 != 0) goto L_0x004d
            goto L_0x0042
        L_0x00a0:
            com.google.android.gms.internal.gtm.zzsf r0 = r13.zzbdo
            r5 = r10 & r12
            long r10 = (long) r5
            java.lang.Object r5 = com.google.android.gms.internal.gtm.zztx.zzp(r14, r10)
            java.util.Map r5 = r0.zzy(r5)
            boolean r0 = r5.isEmpty()
            if (r0 != 0) goto L_0x00f3
            java.lang.Object r0 = r13.zzbp(r8)
            com.google.android.gms.internal.gtm.zzsf r7 = r13.zzbdo
            com.google.android.gms.internal.gtm.zzsd r0 = r7.zzac(r0)
            com.google.android.gms.internal.gtm.zzug r0 = r0.zzbcr
            com.google.android.gms.internal.gtm.zzul r0 = r0.zzrs()
            com.google.android.gms.internal.gtm.zzul r7 = com.google.android.gms.internal.gtm.zzul.MESSAGE
            if (r0 != r7) goto L_0x00f3
            r0 = 0
            java.util.Collection r5 = r5.values()
            java.util.Iterator r5 = r5.iterator()
        L_0x00d0:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x00f3
            java.lang.Object r7 = r5.next()
            if (r0 != 0) goto L_0x00e8
            com.google.android.gms.internal.gtm.zzsw r0 = com.google.android.gms.internal.gtm.zzsw.zzqs()
            java.lang.Class r8 = r7.getClass()
            com.google.android.gms.internal.gtm.zzsz r0 = r0.zzi(r8)
        L_0x00e8:
            boolean r7 = r0.zzae(r7)
            if (r7 != 0) goto L_0x00d0
            r0 = r2
        L_0x00ef:
            if (r0 != 0) goto L_0x004d
            goto L_0x0042
        L_0x00f3:
            r0 = r6
            goto L_0x00ef
        L_0x00f5:
            boolean r0 = r13.zzbdd
            if (r0 == 0) goto L_0x0105
            com.google.android.gms.internal.gtm.zzqq<?> r0 = r13.zzbdn
            com.google.android.gms.internal.gtm.zzqt r0 = r0.zzr(r14)
            boolean r0 = r0.isInitialized()
            if (r0 == 0) goto L_0x0042
        L_0x0105:
            r2 = r6
            goto L_0x0042
        L_0x0108:
            r5 = r0
            goto L_0x0034
        L_0x010b:
            r5 = r2
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzso.zzae(java.lang.Object):boolean");
    }

    private static boolean zza(Object obj, int i, zzsz zzsz) {
        return zzsz.zzae(zztx.zzp(obj, (long) (1048575 & i)));
    }

    private static void zza(int i, Object obj, zzum zzum) throws IOException {
        if (obj instanceof String) {
            zzum.zza(i, (String) obj);
        } else {
            zzum.zza(i, (zzps) obj);
        }
    }

    private final void zza(Object obj, int i, zzsy zzsy) throws IOException {
        if (zzbt(i)) {
            zztx.zza(obj, (long) (i & 1048575), (Object) zzsy.zznp());
        } else if (this.zzbde) {
            zztx.zza(obj, (long) (i & 1048575), (Object) zzsy.readString());
        } else {
            zztx.zza(obj, (long) (i & 1048575), (Object) zzsy.zznq());
        }
    }

    private final int zzbr(int i) {
        return this.zzbcy[i + 1];
    }

    private final int zzbs(int i) {
        return this.zzbcy[i + 2];
    }

    private static boolean zzbt(int i) {
        return (536870912 & i) != 0;
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zztx.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zztx.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zztx.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zztx.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zztx.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzb(t, i) == zzb(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzbdf) {
            return zzb(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zzb(T t, int i) {
        if (this.zzbdf) {
            int zzbr = zzbr(i);
            long j = (long) (zzbr & 1048575);
            switch ((zzbr & 267386880) >>> 20) {
                case 0:
                    if (zztx.zzo(t, j) != 0.0d) {
                        return true;
                    }
                    return false;
                case 1:
                    return zztx.zzn(t, j) != 0.0f;
                case 2:
                    return zztx.zzl(t, j) != 0;
                case 3:
                    return zztx.zzl(t, j) != 0;
                case 4:
                    return zztx.zzk(t, j) != 0;
                case 5:
                    return zztx.zzl(t, j) != 0;
                case 6:
                    return zztx.zzk(t, j) != 0;
                case 7:
                    return zztx.zzm(t, j);
                case 8:
                    Object zzp = zztx.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzps) {
                        return !zzps.zzavx.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zztx.zzp(t, j) != null;
                case 10:
                    return !zzps.zzavx.equals(zztx.zzp(t, j));
                case 11:
                    return zztx.zzk(t, j) != 0;
                case 12:
                    return zztx.zzk(t, j) != 0;
                case 13:
                    return zztx.zzk(t, j) != 0;
                case 14:
                    return zztx.zzl(t, j) != 0;
                case 15:
                    return zztx.zzk(t, j) != 0;
                case 16:
                    return zztx.zzl(t, j) != 0;
                case 17:
                    return zztx.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzbs = zzbs(i);
            return (zztx.zzk(t, (long) (zzbs & 1048575)) & (1 << (zzbs >>> 20))) != 0;
        }
    }

    private final void zzc(T t, int i) {
        if (!this.zzbdf) {
            int zzbs = zzbs(i);
            long j = (long) (zzbs & 1048575);
            zztx.zzb((Object) t, j, zztx.zzk(t, j) | (1 << (zzbs >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zztx.zzk(t, (long) (zzbs(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zztx.zzb((Object) t, (long) (zzbs(i2) & 1048575), i);
    }
}
