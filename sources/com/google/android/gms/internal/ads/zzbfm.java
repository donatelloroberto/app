package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfm extends zzbfc<zzbfm> {
    public String url = null;
    public Integer zzamf = null;
    private Integer zzecg = null;
    public String zzech = null;
    private String zzeci = null;
    public zzbfn zzecj = null;
    public zzbfu[] zzeck = zzbfu.zzagu();
    public String zzecl = null;
    public zzbft zzecm = null;
    private Boolean zzecn = null;
    private String[] zzeco = zzbfl.zzecd;
    private String zzecp = null;
    private Boolean zzecq = null;
    private Boolean zzecr = null;
    private byte[] zzecs = null;
    public zzbfv zzect = null;
    public String[] zzecu = zzbfl.zzecd;
    public String[] zzecv = zzbfl.zzecd;

    public zzbfm() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaa */
    public final zzbfm zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.url = zzbez.readString();
                    continue;
                case 18:
                    this.zzech = zzbez.readString();
                    continue;
                case 26:
                    this.zzeci = zzbez.readString();
                    continue;
                case 34:
                    int zzb = zzbfl.zzb(zzbez, 34);
                    int length = this.zzeck == null ? 0 : this.zzeck.length;
                    zzbfu[] zzbfuArr = new zzbfu[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzeck, 0, zzbfuArr, 0, length);
                    }
                    while (length < zzbfuArr.length - 1) {
                        zzbfuArr[length] = new zzbfu();
                        zzbez.zza(zzbfuArr[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzbfuArr[length] = new zzbfu();
                    zzbez.zza(zzbfuArr[length]);
                    this.zzeck = zzbfuArr;
                    continue;
                case 40:
                    this.zzecn = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 50:
                    int zzb2 = zzbfl.zzb(zzbez, 50);
                    int length2 = this.zzeco == null ? 0 : this.zzeco.length;
                    String[] strArr = new String[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzeco, 0, strArr, 0, length2);
                    }
                    while (length2 < strArr.length - 1) {
                        strArr[length2] = zzbez.readString();
                        zzbez.zzabk();
                        length2++;
                    }
                    strArr[length2] = zzbez.readString();
                    this.zzeco = strArr;
                    continue;
                case 58:
                    this.zzecp = zzbez.readString();
                    continue;
                case 64:
                    this.zzecq = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 72:
                    this.zzecr = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 80:
                    int position = zzbez.getPosition();
                    try {
                        int zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 9) {
                            throw new IllegalArgumentException(new StringBuilder(42).append(zzabn).append(" is not a valid enum ReportType").toString());
                        }
                        this.zzamf = Integer.valueOf(zzabn);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 88:
                    int position2 = zzbez.getPosition();
                    try {
                        int zzabn2 = zzbez.zzabn();
                        if (zzabn2 < 0 || zzabn2 > 4) {
                            throw new IllegalArgumentException(new StringBuilder(39).append(zzabn2).append(" is not a valid enum Verdict").toString());
                        }
                        this.zzecg = Integer.valueOf(zzabn2);
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position2);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 98:
                    if (this.zzecj == null) {
                        this.zzecj = new zzbfn();
                    }
                    zzbez.zza(this.zzecj);
                    continue;
                case 106:
                    this.zzecl = zzbez.readString();
                    continue;
                case 114:
                    if (this.zzecm == null) {
                        this.zzecm = new zzbft();
                    }
                    zzbez.zza(this.zzecm);
                    continue;
                case 122:
                    this.zzecs = zzbez.readBytes();
                    continue;
                case 138:
                    if (this.zzect == null) {
                        this.zzect = new zzbfv();
                    }
                    zzbez.zza(this.zzect);
                    continue;
                case 162:
                    int zzb3 = zzbfl.zzb(zzbez, 162);
                    int length3 = this.zzecu == null ? 0 : this.zzecu.length;
                    String[] strArr2 = new String[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzecu, 0, strArr2, 0, length3);
                    }
                    while (length3 < strArr2.length - 1) {
                        strArr2[length3] = zzbez.readString();
                        zzbez.zzabk();
                        length3++;
                    }
                    strArr2[length3] = zzbez.readString();
                    this.zzecu = strArr2;
                    continue;
                case 170:
                    int zzb4 = zzbfl.zzb(zzbez, 170);
                    int length4 = this.zzecv == null ? 0 : this.zzecv.length;
                    String[] strArr3 = new String[(zzb4 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzecv, 0, strArr3, 0, length4);
                    }
                    while (length4 < strArr3.length - 1) {
                        strArr3[length4] = zzbez.readString();
                        zzbez.zzabk();
                        length4++;
                    }
                    strArr3[length4] = zzbez.readString();
                    this.zzecv = strArr3;
                    continue;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.url != null) {
            zzbfa.zzf(1, this.url);
        }
        if (this.zzech != null) {
            zzbfa.zzf(2, this.zzech);
        }
        if (this.zzeci != null) {
            zzbfa.zzf(3, this.zzeci);
        }
        if (this.zzeck != null && this.zzeck.length > 0) {
            for (zzbfu zzbfu : this.zzeck) {
                if (zzbfu != null) {
                    zzbfa.zza(4, (zzbfi) zzbfu);
                }
            }
        }
        if (this.zzecn != null) {
            zzbfa.zzf(5, this.zzecn.booleanValue());
        }
        if (this.zzeco != null && this.zzeco.length > 0) {
            for (String str : this.zzeco) {
                if (str != null) {
                    zzbfa.zzf(6, str);
                }
            }
        }
        if (this.zzecp != null) {
            zzbfa.zzf(7, this.zzecp);
        }
        if (this.zzecq != null) {
            zzbfa.zzf(8, this.zzecq.booleanValue());
        }
        if (this.zzecr != null) {
            zzbfa.zzf(9, this.zzecr.booleanValue());
        }
        if (this.zzamf != null) {
            zzbfa.zzm(10, this.zzamf.intValue());
        }
        if (this.zzecg != null) {
            zzbfa.zzm(11, this.zzecg.intValue());
        }
        if (this.zzecj != null) {
            zzbfa.zza(12, (zzbfi) this.zzecj);
        }
        if (this.zzecl != null) {
            zzbfa.zzf(13, this.zzecl);
        }
        if (this.zzecm != null) {
            zzbfa.zza(14, (zzbfi) this.zzecm);
        }
        if (this.zzecs != null) {
            zzbfa.zza(15, this.zzecs);
        }
        if (this.zzect != null) {
            zzbfa.zza(17, (zzbfi) this.zzect);
        }
        if (this.zzecu != null && this.zzecu.length > 0) {
            for (String str2 : this.zzecu) {
                if (str2 != null) {
                    zzbfa.zzf(20, str2);
                }
            }
        }
        if (this.zzecv != null && this.zzecv.length > 0) {
            for (String str3 : this.zzecv) {
                if (str3 != null) {
                    zzbfa.zzf(21, str3);
                }
            }
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.url != null) {
            zzr += zzbfa.zzg(1, this.url);
        }
        if (this.zzech != null) {
            zzr += zzbfa.zzg(2, this.zzech);
        }
        if (this.zzeci != null) {
            zzr += zzbfa.zzg(3, this.zzeci);
        }
        if (this.zzeck != null && this.zzeck.length > 0) {
            int i = zzr;
            for (zzbfu zzbfu : this.zzeck) {
                if (zzbfu != null) {
                    i += zzbfa.zzb(4, (zzbfi) zzbfu);
                }
            }
            zzr = i;
        }
        if (this.zzecn != null) {
            this.zzecn.booleanValue();
            zzr += zzbfa.zzcd(5) + 1;
        }
        if (this.zzeco != null && this.zzeco.length > 0) {
            int i2 = 0;
            int i3 = 0;
            for (String str : this.zzeco) {
                if (str != null) {
                    i3++;
                    i2 += zzbfa.zzeo(str);
                }
            }
            zzr = zzr + i2 + (i3 * 1);
        }
        if (this.zzecp != null) {
            zzr += zzbfa.zzg(7, this.zzecp);
        }
        if (this.zzecq != null) {
            this.zzecq.booleanValue();
            zzr += zzbfa.zzcd(8) + 1;
        }
        if (this.zzecr != null) {
            this.zzecr.booleanValue();
            zzr += zzbfa.zzcd(9) + 1;
        }
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(10, this.zzamf.intValue());
        }
        if (this.zzecg != null) {
            zzr += zzbfa.zzq(11, this.zzecg.intValue());
        }
        if (this.zzecj != null) {
            zzr += zzbfa.zzb(12, (zzbfi) this.zzecj);
        }
        if (this.zzecl != null) {
            zzr += zzbfa.zzg(13, this.zzecl);
        }
        if (this.zzecm != null) {
            zzr += zzbfa.zzb(14, (zzbfi) this.zzecm);
        }
        if (this.zzecs != null) {
            zzr += zzbfa.zzb(15, this.zzecs);
        }
        if (this.zzect != null) {
            zzr += zzbfa.zzb(17, (zzbfi) this.zzect);
        }
        if (this.zzecu != null && this.zzecu.length > 0) {
            int i4 = 0;
            int i5 = 0;
            for (String str2 : this.zzecu) {
                if (str2 != null) {
                    i5++;
                    i4 += zzbfa.zzeo(str2);
                }
            }
            zzr = zzr + i4 + (i5 * 2);
        }
        if (this.zzecv == null || this.zzecv.length <= 0) {
            return zzr;
        }
        int i6 = 0;
        int i7 = 0;
        for (String str3 : this.zzecv) {
            if (str3 != null) {
                i7++;
                i6 += zzbfa.zzeo(str3);
            }
        }
        return zzr + i6 + (i7 * 2);
    }
}
