package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzib extends zzbfc<zzib> {
    public Integer zzalt = null;
    private Integer zzalu = null;
    private zzid zzalv = null;
    public zzie zzalw = null;
    private zzic[] zzalx = zzic.zzhr();
    private zzif zzaly = null;
    private zzio zzalz = null;
    private zzin zzama = null;
    private zzik zzamb = null;
    private zzil zzamc = null;
    private zziu[] zzamd = zziu.zzhu();

    public zzib() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zze */
    public final zzib zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 56:
                    int position = zzbez.getPosition();
                    try {
                        int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 9) {
                            throw new IllegalArgumentException(new StringBuilder(43).append(zzacc).append(" is not a valid enum AdInitiater").toString());
                        }
                        this.zzalt = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 64:
                    int position2 = zzbez.getPosition();
                    try {
                        this.zzalu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position2);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 74:
                    if (this.zzalv == null) {
                        this.zzalv = new zzid();
                    }
                    zzbez.zza(this.zzalv);
                    continue;
                case 82:
                    if (this.zzalw == null) {
                        this.zzalw = new zzie();
                    }
                    zzbez.zza(this.zzalw);
                    continue;
                case 90:
                    int zzb = zzbfl.zzb(zzbez, 90);
                    int length = this.zzalx == null ? 0 : this.zzalx.length;
                    zzic[] zzicArr = new zzic[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzalx, 0, zzicArr, 0, length);
                    }
                    while (length < zzicArr.length - 1) {
                        zzicArr[length] = new zzic();
                        zzbez.zza(zzicArr[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzicArr[length] = new zzic();
                    zzbez.zza(zzicArr[length]);
                    this.zzalx = zzicArr;
                    continue;
                case 98:
                    if (this.zzaly == null) {
                        this.zzaly = new zzif();
                    }
                    zzbez.zza(this.zzaly);
                    continue;
                case 106:
                    if (this.zzalz == null) {
                        this.zzalz = new zzio();
                    }
                    zzbez.zza(this.zzalz);
                    continue;
                case 114:
                    if (this.zzama == null) {
                        this.zzama = new zzin();
                    }
                    zzbez.zza(this.zzama);
                    continue;
                case 122:
                    if (this.zzamb == null) {
                        this.zzamb = new zzik();
                    }
                    zzbez.zza(this.zzamb);
                    continue;
                case 130:
                    if (this.zzamc == null) {
                        this.zzamc = new zzil();
                    }
                    zzbez.zza(this.zzamc);
                    continue;
                case 138:
                    int zzb2 = zzbfl.zzb(zzbez, 138);
                    int length2 = this.zzamd == null ? 0 : this.zzamd.length;
                    zziu[] zziuArr = new zziu[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzamd, 0, zziuArr, 0, length2);
                    }
                    while (length2 < zziuArr.length - 1) {
                        zziuArr[length2] = new zziu();
                        zzbez.zza(zziuArr[length2]);
                        zzbez.zzabk();
                        length2++;
                    }
                    zziuArr[length2] = new zziu();
                    zzbez.zza(zziuArr[length2]);
                    this.zzamd = zziuArr;
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
        if (this.zzalt != null) {
            zzbfa.zzm(7, this.zzalt.intValue());
        }
        if (this.zzalu != null) {
            zzbfa.zzm(8, this.zzalu.intValue());
        }
        if (this.zzalv != null) {
            zzbfa.zza(9, (zzbfi) this.zzalv);
        }
        if (this.zzalw != null) {
            zzbfa.zza(10, (zzbfi) this.zzalw);
        }
        if (this.zzalx != null && this.zzalx.length > 0) {
            for (zzic zzic : this.zzalx) {
                if (zzic != null) {
                    zzbfa.zza(11, (zzbfi) zzic);
                }
            }
        }
        if (this.zzaly != null) {
            zzbfa.zza(12, (zzbfi) this.zzaly);
        }
        if (this.zzalz != null) {
            zzbfa.zza(13, (zzbfi) this.zzalz);
        }
        if (this.zzama != null) {
            zzbfa.zza(14, (zzbfi) this.zzama);
        }
        if (this.zzamb != null) {
            zzbfa.zza(15, (zzbfi) this.zzamb);
        }
        if (this.zzamc != null) {
            zzbfa.zza(16, (zzbfi) this.zzamc);
        }
        if (this.zzamd != null && this.zzamd.length > 0) {
            for (zziu zziu : this.zzamd) {
                if (zziu != null) {
                    zzbfa.zza(17, (zzbfi) zziu);
                }
            }
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        if (this.zzalt != null) {
            zzr += zzbfa.zzq(7, this.zzalt.intValue());
        }
        if (this.zzalu != null) {
            zzr += zzbfa.zzq(8, this.zzalu.intValue());
        }
        if (this.zzalv != null) {
            zzr += zzbfa.zzb(9, (zzbfi) this.zzalv);
        }
        if (this.zzalw != null) {
            zzr += zzbfa.zzb(10, (zzbfi) this.zzalw);
        }
        if (this.zzalx != null && this.zzalx.length > 0) {
            int i = zzr;
            for (zzic zzic : this.zzalx) {
                if (zzic != null) {
                    i += zzbfa.zzb(11, (zzbfi) zzic);
                }
            }
            zzr = i;
        }
        if (this.zzaly != null) {
            zzr += zzbfa.zzb(12, (zzbfi) this.zzaly);
        }
        if (this.zzalz != null) {
            zzr += zzbfa.zzb(13, (zzbfi) this.zzalz);
        }
        if (this.zzama != null) {
            zzr += zzbfa.zzb(14, (zzbfi) this.zzama);
        }
        if (this.zzamb != null) {
            zzr += zzbfa.zzb(15, (zzbfi) this.zzamb);
        }
        if (this.zzamc != null) {
            zzr += zzbfa.zzb(16, (zzbfi) this.zzamc);
        }
        if (this.zzamd != null && this.zzamd.length > 0) {
            for (zziu zziu : this.zzamd) {
                if (zziu != null) {
                    zzr += zzbfa.zzb(17, (zzbfi) zziu);
                }
            }
        }
        return zzr;
    }
}
