package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzut implements Cloneable {
    private Object value;
    private zzur<?, ?> zzbhi;
    private List<zzuy> zzbhj = new ArrayList();

    zzut() {
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzuy zzuy) throws IOException {
        Object zzag;
        if (this.zzbhj != null) {
            this.zzbhj.add(zzuy);
            return;
        }
        if (this.value instanceof zzuw) {
            byte[] bArr = zzuy.zzawe;
            zzun zzj = zzun.zzj(bArr, 0, bArr.length);
            int zzoa = zzj.zzoa();
            if (zzoa != bArr.length - zzuo.zzbc(zzoa)) {
                throw zzuv.zzsa();
            }
            zzag = ((zzuw) this.value).zza(zzj);
        } else if (this.value instanceof zzuw[]) {
            zzuw[] zzuwArr = (zzuw[]) this.zzbhi.zzag(Collections.singletonList(zzuy));
            zzuw[] zzuwArr2 = (zzuw[]) this.value;
            zzag = (zzuw[]) Arrays.copyOf(zzuwArr2, zzuwArr2.length + zzuwArr.length);
            System.arraycopy(zzuwArr, 0, zzag, zzuwArr2.length, zzuwArr.length);
        } else if (this.value instanceof zzsk) {
            zzag = ((zzsk) this.value).zzpg().zza((zzsk) this.zzbhi.zzag(Collections.singletonList(zzuy))).zzpm();
        } else if (this.value instanceof zzsk[]) {
            zzsk[] zzskArr = (zzsk[]) this.zzbhi.zzag(Collections.singletonList(zzuy));
            zzsk[] zzskArr2 = (zzsk[]) this.value;
            zzag = (zzsk[]) Arrays.copyOf(zzskArr2, zzskArr2.length + zzskArr.length);
            System.arraycopy(zzskArr, 0, zzag, zzskArr2.length, zzskArr.length);
        } else {
            zzag = this.zzbhi.zzag(Collections.singletonList(zzuy));
        }
        this.zzbhi = this.zzbhi;
        this.value = zzag;
        this.zzbhj = null;
    }

    /* access modifiers changed from: package-private */
    public final <T> T zzb(zzur<?, T> zzur) {
        if (this.value == null) {
            this.zzbhi = zzur;
            this.value = zzur.zzag(this.zzbhj);
            this.zzbhj = null;
        } else if (!this.zzbhi.equals(zzur)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public final int zzy() {
        int i = 0;
        if (this.value != null) {
            zzur<?, ?> zzur = this.zzbhi;
            Object obj = this.value;
            if (!zzur.zzbhd) {
                return zzur.zzaj(obj);
            }
            int length = Array.getLength(obj);
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                Object obj2 = Array.get(obj, i3);
                if (obj2 != null) {
                    i2 += zzur.zzaj(obj2);
                }
            }
            return i2;
        }
        for (zzuy next : this.zzbhj) {
            i = next.zzawe.length + zzuo.zzbj(next.tag) + 0 + i;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzuo zzuo) throws IOException {
        if (this.value != null) {
            zzur<?, ?> zzur = this.zzbhi;
            Object obj = this.value;
            if (zzur.zzbhd) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzur.zza(obj2, zzuo);
                    }
                }
                return;
            }
            zzur.zza(obj, zzuo);
            return;
        }
        for (zzuy next : this.zzbhj) {
            zzuo.zzcb(next.tag);
            zzuo.zzm(next.zzawe);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzut)) {
            return false;
        }
        zzut zzut = (zzut) obj;
        if (this.value == null || zzut.value == null) {
            if (this.zzbhj != null && zzut.zzbhj != null) {
                return this.zzbhj.equals(zzut.zzbhj);
            }
            try {
                return Arrays.equals(toByteArray(), zzut.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzbhi != zzut.zzbhi) {
            return false;
        } else {
            if (!this.zzbhi.zzbhc.isArray()) {
                return this.value.equals(zzut.value);
            }
            if (this.value instanceof byte[]) {
                return Arrays.equals((byte[]) this.value, (byte[]) zzut.value);
            }
            if (this.value instanceof int[]) {
                return Arrays.equals((int[]) this.value, (int[]) zzut.value);
            }
            if (this.value instanceof long[]) {
                return Arrays.equals((long[]) this.value, (long[]) zzut.value);
            }
            if (this.value instanceof float[]) {
                return Arrays.equals((float[]) this.value, (float[]) zzut.value);
            }
            if (this.value instanceof double[]) {
                return Arrays.equals((double[]) this.value, (double[]) zzut.value);
            }
            if (this.value instanceof boolean[]) {
                return Arrays.equals((boolean[]) this.value, (boolean[]) zzut.value);
            }
            return Arrays.deepEquals((Object[]) this.value, (Object[]) zzut.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzy()];
        zza(zzuo.zzl(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzrz */
    public final zzut clone() {
        int i = 0;
        zzut zzut = new zzut();
        try {
            zzut.zzbhi = this.zzbhi;
            if (this.zzbhj == null) {
                zzut.zzbhj = null;
            } else {
                zzut.zzbhj.addAll(this.zzbhj);
            }
            if (this.value != null) {
                if (this.value instanceof zzuw) {
                    zzut.value = (zzuw) ((zzuw) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzut.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzut.value = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzut.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzut.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzut.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzut.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzut.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzuw[]) {
                    zzuw[] zzuwArr = (zzuw[]) this.value;
                    zzuw[] zzuwArr2 = new zzuw[zzuwArr.length];
                    zzut.value = zzuwArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= zzuwArr.length) {
                            break;
                        }
                        zzuwArr2[i3] = (zzuw) zzuwArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return zzut;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
