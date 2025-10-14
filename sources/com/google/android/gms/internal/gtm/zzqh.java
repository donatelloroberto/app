package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.List;

final class zzqh implements zzsy {
    private int tag;
    private final zzqe zzawp;
    private int zzawq;
    private int zzawr = 0;

    public static zzqh zza(zzqe zzqe) {
        if (zzqe.zzawi != null) {
            return zzqe.zzawi;
        }
        return new zzqh(zzqe);
    }

    private zzqh(zzqe zzqe) {
        this.zzawp = (zzqe) zzre.zza(zzqe, "input");
        this.zzawp.zzawi = this;
    }

    public final int zzog() throws IOException {
        if (this.zzawr != 0) {
            this.tag = this.zzawr;
            this.zzawr = 0;
        } else {
            this.tag = this.zzawp.zzni();
        }
        if (this.tag == 0 || this.tag == this.zzawq) {
            return Integer.MAX_VALUE;
        }
        return this.tag >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzoh() throws IOException {
        if (this.zzawp.zzny() || this.tag == this.zzawq) {
            return false;
        }
        return this.zzawp.zzao(this.tag);
    }

    private final void zzat(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzrk.zzpt();
        }
    }

    public final double readDouble() throws IOException {
        zzat(1);
        return this.zzawp.readDouble();
    }

    public final float readFloat() throws IOException {
        zzat(5);
        return this.zzawp.readFloat();
    }

    public final long zznj() throws IOException {
        zzat(0);
        return this.zzawp.zznj();
    }

    public final long zznk() throws IOException {
        zzat(0);
        return this.zzawp.zznk();
    }

    public final int zznl() throws IOException {
        zzat(0);
        return this.zzawp.zznl();
    }

    public final long zznm() throws IOException {
        zzat(1);
        return this.zzawp.zznm();
    }

    public final int zznn() throws IOException {
        zzat(5);
        return this.zzawp.zznn();
    }

    public final boolean zzno() throws IOException {
        zzat(0);
        return this.zzawp.zzno();
    }

    public final String readString() throws IOException {
        zzat(2);
        return this.zzawp.readString();
    }

    public final String zznp() throws IOException {
        zzat(2);
        return this.zzawp.zznp();
    }

    public final <T> T zza(zzsz<T> zzsz, zzqp zzqp) throws IOException {
        zzat(2);
        return zzc(zzsz, zzqp);
    }

    public final <T> T zzb(zzsz<T> zzsz, zzqp zzqp) throws IOException {
        zzat(3);
        return zzd(zzsz, zzqp);
    }

    private final <T> T zzc(zzsz<T> zzsz, zzqp zzqp) throws IOException {
        int zznr = this.zzawp.zznr();
        if (this.zzawp.zzawf >= this.zzawp.zzawg) {
            throw zzrk.zzpu();
        }
        int zzaq = this.zzawp.zzaq(zznr);
        T newInstance = zzsz.newInstance();
        this.zzawp.zzawf++;
        zzsz.zza(newInstance, this, zzqp);
        zzsz.zzt(newInstance);
        this.zzawp.zzan(0);
        zzqe zzqe = this.zzawp;
        zzqe.zzawf--;
        this.zzawp.zzar(zzaq);
        return newInstance;
    }

    private final <T> T zzd(zzsz<T> zzsz, zzqp zzqp) throws IOException {
        int i = this.zzawq;
        this.zzawq = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzsz.newInstance();
            zzsz.zza(newInstance, this, zzqp);
            zzsz.zzt(newInstance);
            if (this.tag == this.zzawq) {
                return newInstance;
            }
            throw zzrk.zzpv();
        } finally {
            this.zzawq = i;
        }
    }

    public final zzps zznq() throws IOException {
        zzat(2);
        return this.zzawp.zznq();
    }

    public final int zznr() throws IOException {
        zzat(0);
        return this.zzawp.zznr();
    }

    public final int zzns() throws IOException {
        zzat(0);
        return this.zzawp.zzns();
    }

    public final int zznt() throws IOException {
        zzat(5);
        return this.zzawp.zznt();
    }

    public final long zznu() throws IOException {
        zzat(1);
        return this.zzawp.zznu();
    }

    public final int zznv() throws IOException {
        zzat(0);
        return this.zzawp.zznv();
    }

    public final long zznw() throws IOException {
        zzat(0);
        return this.zzawp.zznw();
    }

    public final void zzg(List<Double> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzqm) {
            zzqm zzqm = (zzqm) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr();
                    zzau(zznr);
                    int zznz = zznr + this.zzawp.zznz();
                    do {
                        zzqm.zzd(this.zzawp.readDouble());
                    } while (this.zzawp.zznz() < zznz);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzqm.zzd(this.zzawp.readDouble());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr();
                zzau(zznr2);
                int zznz2 = zznr2 + this.zzawp.zznz();
                do {
                    list.add(Double.valueOf(this.zzawp.readDouble()));
                } while (this.zzawp.zznz() < zznz2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Double.valueOf(this.zzawp.readDouble()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzh(List<Float> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzqz) {
            zzqz zzqz = (zzqz) list;
            switch (this.tag & 7) {
                case 2:
                    int zznr = this.zzawp.zznr();
                    zzav(zznr);
                    int zznz = zznr + this.zzawp.zznz();
                    do {
                        zzqz.zzc(this.zzawp.readFloat());
                    } while (this.zzawp.zznz() < zznz);
                    return;
                case 5:
                    break;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzqz.zzc(this.zzawp.readFloat());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                int zznr2 = this.zzawp.zznr();
                zzav(zznr2);
                int zznz2 = zznr2 + this.zzawp.zznz();
                do {
                    list.add(Float.valueOf(this.zzawp.readFloat()));
                } while (this.zzawp.zznz() < zznz2);
                return;
            case 5:
                break;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Float.valueOf(this.zzawp.readFloat()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzi(List<Long> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzry) {
            zzry zzry = (zzry) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr() + this.zzawp.zznz();
                    do {
                        zzry.zzaa(this.zzawp.zznj());
                    } while (this.zzawp.zznz() < zznr);
                    zzaw(zznr);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzry.zzaa(this.zzawp.zznj());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr() + this.zzawp.zznz();
                do {
                    list.add(Long.valueOf(this.zzawp.zznj()));
                } while (this.zzawp.zznz() < zznr2);
                zzaw(zznr2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Long.valueOf(this.zzawp.zznj()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzj(List<Long> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzry) {
            zzry zzry = (zzry) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr() + this.zzawp.zznz();
                    do {
                        zzry.zzaa(this.zzawp.zznk());
                    } while (this.zzawp.zznz() < zznr);
                    zzaw(zznr);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzry.zzaa(this.zzawp.zznk());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr() + this.zzawp.zznz();
                do {
                    list.add(Long.valueOf(this.zzawp.zznk()));
                } while (this.zzawp.zznz() < zznr2);
                zzaw(zznr2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Long.valueOf(this.zzawp.zznk()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzk(List<Integer> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr() + this.zzawp.zznz();
                    do {
                        zzrd.zzbm(this.zzawp.zznl());
                    } while (this.zzawp.zznz() < zznr);
                    zzaw(zznr);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzrd.zzbm(this.zzawp.zznl());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr() + this.zzawp.zznz();
                do {
                    list.add(Integer.valueOf(this.zzawp.zznl()));
                } while (this.zzawp.zznz() < zznr2);
                zzaw(zznr2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Integer.valueOf(this.zzawp.zznl()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzl(List<Long> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzry) {
            zzry zzry = (zzry) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr();
                    zzau(zznr);
                    int zznz = zznr + this.zzawp.zznz();
                    do {
                        zzry.zzaa(this.zzawp.zznm());
                    } while (this.zzawp.zznz() < zznz);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzry.zzaa(this.zzawp.zznm());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr();
                zzau(zznr2);
                int zznz2 = zznr2 + this.zzawp.zznz();
                do {
                    list.add(Long.valueOf(this.zzawp.zznm()));
                } while (this.zzawp.zznz() < zznz2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Long.valueOf(this.zzawp.zznm()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzm(List<Integer> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            switch (this.tag & 7) {
                case 2:
                    int zznr = this.zzawp.zznr();
                    zzav(zznr);
                    int zznz = zznr + this.zzawp.zznz();
                    do {
                        zzrd.zzbm(this.zzawp.zznn());
                    } while (this.zzawp.zznz() < zznz);
                    return;
                case 5:
                    break;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzrd.zzbm(this.zzawp.zznn());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                int zznr2 = this.zzawp.zznr();
                zzav(zznr2);
                int zznz2 = zznr2 + this.zzawp.zznz();
                do {
                    list.add(Integer.valueOf(this.zzawp.zznn()));
                } while (this.zzawp.zznz() < zznz2);
                return;
            case 5:
                break;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Integer.valueOf(this.zzawp.zznn()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzn(List<Boolean> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzpq) {
            zzpq zzpq = (zzpq) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr() + this.zzawp.zznz();
                    do {
                        zzpq.addBoolean(this.zzawp.zzno());
                    } while (this.zzawp.zznz() < zznr);
                    zzaw(zznr);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzpq.addBoolean(this.zzawp.zzno());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr() + this.zzawp.zznz();
                do {
                    list.add(Boolean.valueOf(this.zzawp.zzno()));
                } while (this.zzawp.zznz() < zznr2);
                zzaw(zznr2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Boolean.valueOf(this.zzawp.zzno()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzo(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzni;
        int zzni2;
        if ((this.tag & 7) != 2) {
            throw zzrk.zzpt();
        } else if (!(list instanceof zzrt) || z) {
            do {
                list.add(z ? zznp() : readString());
                if (!this.zzawp.zzny()) {
                    zzni = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni == this.tag);
            this.zzawr = zzni;
        } else {
            zzrt zzrt = (zzrt) list;
            do {
                zzrt.zzc(zznq());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
        }
    }

    public final <T> void zza(List<T> list, zzsz<T> zzsz, zzqp zzqp) throws IOException {
        int zzni;
        if ((this.tag & 7) != 2) {
            throw zzrk.zzpt();
        }
        int i = this.tag;
        do {
            list.add(zzc(zzsz, zzqp));
            if (!this.zzawp.zzny() && this.zzawr == 0) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == i);
        this.zzawr = zzni;
    }

    public final <T> void zzb(List<T> list, zzsz<T> zzsz, zzqp zzqp) throws IOException {
        int zzni;
        if ((this.tag & 7) != 3) {
            throw zzrk.zzpt();
        }
        int i = this.tag;
        do {
            list.add(zzd(zzsz, zzqp));
            if (!this.zzawp.zzny() && this.zzawr == 0) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == i);
        this.zzawr = zzni;
    }

    public final void zzp(List<zzps> list) throws IOException {
        int zzni;
        if ((this.tag & 7) != 2) {
            throw zzrk.zzpt();
        }
        do {
            list.add(zznq());
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzq(List<Integer> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr() + this.zzawp.zznz();
                    do {
                        zzrd.zzbm(this.zzawp.zznr());
                    } while (this.zzawp.zznz() < zznr);
                    zzaw(zznr);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzrd.zzbm(this.zzawp.zznr());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr() + this.zzawp.zznz();
                do {
                    list.add(Integer.valueOf(this.zzawp.zznr()));
                } while (this.zzawp.zznz() < zznr2);
                zzaw(zznr2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Integer.valueOf(this.zzawp.zznr()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzr(List<Integer> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr() + this.zzawp.zznz();
                    do {
                        zzrd.zzbm(this.zzawp.zzns());
                    } while (this.zzawp.zznz() < zznr);
                    zzaw(zznr);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzrd.zzbm(this.zzawp.zzns());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr() + this.zzawp.zznz();
                do {
                    list.add(Integer.valueOf(this.zzawp.zzns()));
                } while (this.zzawp.zznz() < zznr2);
                zzaw(zznr2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Integer.valueOf(this.zzawp.zzns()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzs(List<Integer> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            switch (this.tag & 7) {
                case 2:
                    int zznr = this.zzawp.zznr();
                    zzav(zznr);
                    int zznz = zznr + this.zzawp.zznz();
                    do {
                        zzrd.zzbm(this.zzawp.zznt());
                    } while (this.zzawp.zznz() < zznz);
                    return;
                case 5:
                    break;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzrd.zzbm(this.zzawp.zznt());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                int zznr2 = this.zzawp.zznr();
                zzav(zznr2);
                int zznz2 = zznr2 + this.zzawp.zznz();
                do {
                    list.add(Integer.valueOf(this.zzawp.zznt()));
                } while (this.zzawp.zznz() < zznz2);
                return;
            case 5:
                break;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Integer.valueOf(this.zzawp.zznt()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzt(List<Long> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzry) {
            zzry zzry = (zzry) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr();
                    zzau(zznr);
                    int zznz = zznr + this.zzawp.zznz();
                    do {
                        zzry.zzaa(this.zzawp.zznu());
                    } while (this.zzawp.zznz() < zznz);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzry.zzaa(this.zzawp.zznu());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr();
                zzau(zznr2);
                int zznz2 = zznr2 + this.zzawp.zznz();
                do {
                    list.add(Long.valueOf(this.zzawp.zznu()));
                } while (this.zzawp.zznz() < zznz2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Long.valueOf(this.zzawp.zznu()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzu(List<Integer> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzrd) {
            zzrd zzrd = (zzrd) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr() + this.zzawp.zznz();
                    do {
                        zzrd.zzbm(this.zzawp.zznv());
                    } while (this.zzawp.zznz() < zznr);
                    zzaw(zznr);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzrd.zzbm(this.zzawp.zznv());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr() + this.zzawp.zznz();
                do {
                    list.add(Integer.valueOf(this.zzawp.zznv()));
                } while (this.zzawp.zznz() < zznr2);
                zzaw(zznr2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Integer.valueOf(this.zzawp.zznv()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    public final void zzv(List<Long> list) throws IOException {
        int zzni;
        int zzni2;
        if (list instanceof zzry) {
            zzry zzry = (zzry) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    int zznr = this.zzawp.zznr() + this.zzawp.zznz();
                    do {
                        zzry.zzaa(this.zzawp.zznw());
                    } while (this.zzawp.zznz() < zznr);
                    zzaw(zznr);
                    return;
                default:
                    throw zzrk.zzpt();
            }
            do {
                zzry.zzaa(this.zzawp.zznw());
                if (!this.zzawp.zzny()) {
                    zzni2 = this.zzawp.zzni();
                } else {
                    return;
                }
            } while (zzni2 == this.tag);
            this.zzawr = zzni2;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                int zznr2 = this.zzawp.zznr() + this.zzawp.zznz();
                do {
                    list.add(Long.valueOf(this.zzawp.zznw()));
                } while (this.zzawp.zznz() < zznr2);
                zzaw(zznr2);
                return;
            default:
                throw zzrk.zzpt();
        }
        do {
            list.add(Long.valueOf(this.zzawp.zznw()));
            if (!this.zzawp.zzny()) {
                zzni = this.zzawp.zzni();
            } else {
                return;
            }
        } while (zzni == this.tag);
        this.zzawr = zzni;
    }

    private static void zzau(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzrk.zzpv();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r7, com.google.android.gms.internal.gtm.zzsd<K, V> r8, com.google.android.gms.internal.gtm.zzqp r9) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 2
            r6.zzat(r0)
            com.google.android.gms.internal.gtm.zzqe r0 = r6.zzawp
            int r0 = r0.zznr()
            com.google.android.gms.internal.gtm.zzqe r1 = r6.zzawp
            int r2 = r1.zzaq(r0)
            K r1 = r8.zzbcq
            V r0 = r8.zzbcs
        L_0x0014:
            int r3 = r6.zzog()     // Catch:{ all -> 0x0045 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x0062
            com.google.android.gms.internal.gtm.zzqe r4 = r6.zzawp     // Catch:{ all -> 0x0045 }
            boolean r4 = r4.zzny()     // Catch:{ all -> 0x0045 }
            if (r4 != 0) goto L_0x0062
            switch(r3) {
                case 1: goto L_0x004c;
                case 2: goto L_0x0055;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r6.zzoh()     // Catch:{ zzrl -> 0x0036 }
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.gtm.zzrk r3 = new com.google.android.gms.internal.gtm.zzrk     // Catch:{ zzrl -> 0x0036 }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzrl -> 0x0036 }
            throw r3     // Catch:{ zzrl -> 0x0036 }
        L_0x0036:
            r3 = move-exception
            boolean r3 = r6.zzoh()     // Catch:{ all -> 0x0045 }
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.gtm.zzrk r0 = new com.google.android.gms.internal.gtm.zzrk     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "Unable to parse map entry."
            r0.<init>(r1)     // Catch:{ all -> 0x0045 }
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r0 = move-exception
            com.google.android.gms.internal.gtm.zzqe r1 = r6.zzawp
            r1.zzar(r2)
            throw r0
        L_0x004c:
            com.google.android.gms.internal.gtm.zzug r3 = r8.zzbcp     // Catch:{ zzrl -> 0x0036 }
            r4 = 0
            r5 = 0
            java.lang.Object r1 = r6.zza((com.google.android.gms.internal.gtm.zzug) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.gtm.zzqp) r5)     // Catch:{ zzrl -> 0x0036 }
            goto L_0x0014
        L_0x0055:
            com.google.android.gms.internal.gtm.zzug r3 = r8.zzbcr     // Catch:{ zzrl -> 0x0036 }
            V r4 = r8.zzbcs     // Catch:{ zzrl -> 0x0036 }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzrl -> 0x0036 }
            java.lang.Object r0 = r6.zza((com.google.android.gms.internal.gtm.zzug) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.gtm.zzqp) r9)     // Catch:{ zzrl -> 0x0036 }
            goto L_0x0014
        L_0x0062:
            r7.put(r1, r0)     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.gtm.zzqe r0 = r6.zzawp
            r0.zzar(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzqh.zza(java.util.Map, com.google.android.gms.internal.gtm.zzsd, com.google.android.gms.internal.gtm.zzqp):void");
    }

    private final Object zza(zzug zzug, Class<?> cls, zzqp zzqp) throws IOException {
        switch (zzqi.zzaws[zzug.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzno());
            case 2:
                return zznq();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzns());
            case 5:
                return Integer.valueOf(zznn());
            case 6:
                return Long.valueOf(zznm());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zznl());
            case 9:
                return Long.valueOf(zznk());
            case 10:
                zzat(2);
                return zzc(zzsw.zzqs().zzi(cls), zzqp);
            case 11:
                return Integer.valueOf(zznt());
            case 12:
                return Long.valueOf(zznu());
            case 13:
                return Integer.valueOf(zznv());
            case 14:
                return Long.valueOf(zznw());
            case 15:
                return zznp();
            case 16:
                return Integer.valueOf(zznr());
            case 17:
                return Long.valueOf(zznj());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzav(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzrk.zzpv();
        }
    }

    private final void zzaw(int i) throws IOException {
        if (this.zzawp.zznz() != i) {
            throw zzrk.zzpp();
        }
    }
}
