package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzadh
@SafeParcelable.Class(creator = "VersionInfoParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzang extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzang> CREATOR = new zzanh();
    @SafeParcelable.Field(id = 3)
    public int zzcve;
    @SafeParcelable.Field(id = 4)
    public int zzcvf;
    @SafeParcelable.Field(id = 5)
    public boolean zzcvg;
    @SafeParcelable.Field(id = 6)
    public boolean zzcvh;
    @SafeParcelable.Field(id = 2)
    public String zzcw;

    public zzang(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzang(int i, int i2, boolean z, boolean z2) {
        this(12451000, i2, true, false, z2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzang(int r7, int r8, boolean r9, boolean r10, boolean r11) {
        /*
            r6 = this;
            if (r9 == 0) goto L_0x003e
            java.lang.String r0 = "0"
        L_0x0004:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            int r1 = r1.length()
            int r1 = r1 + 36
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "afma-sdk-a-v"
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.StringBuilder r1 = r1.append(r7)
            java.lang.String r2 = "."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r2 = "."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = r0.toString()
            r0 = r6
            r2 = r7
            r3 = r8
            r4 = r9
            r5 = r11
            r0.<init>((java.lang.String) r1, (int) r2, (int) r3, (boolean) r4, (boolean) r5)
            return
        L_0x003e:
            java.lang.String r0 = "1"
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzang.<init>(int, int, boolean, boolean, boolean):void");
    }

    @SafeParcelable.Constructor
    zzang(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) boolean z2) {
        this.zzcw = str;
        this.zzcve = i;
        this.zzcvf = i2;
        this.zzcvg = z;
        this.zzcvh = z2;
    }

    public static zzang zzsl() {
        return new zzang(12451009, 12451009, true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcw, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcve);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcvf);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcvg);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzcvh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
