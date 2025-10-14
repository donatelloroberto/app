package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzadh
@SafeParcelable.Class(creator = "AdSizeParcelCreator")
@SafeParcelable.Reserved({1})
public class zzjn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzjn> CREATOR = new zzjo();
    @SafeParcelable.Field(id = 3)
    public final int height;
    @SafeParcelable.Field(id = 4)
    public final int heightPixels;
    @SafeParcelable.Field(id = 6)
    public final int width;
    @SafeParcelable.Field(id = 7)
    public final int widthPixels;
    @SafeParcelable.Field(id = 2)
    public final String zzarb;
    @SafeParcelable.Field(id = 5)
    public final boolean zzarc;
    @SafeParcelable.Field(id = 8)
    public final zzjn[] zzard;
    @SafeParcelable.Field(id = 9)
    public final boolean zzare;
    @SafeParcelable.Field(id = 10)
    public final boolean zzarf;
    @SafeParcelable.Field(id = 11)
    public boolean zzarg;

    public zzjn() {
        this("interstitial_mb", 0, 0, true, 0, 0, (zzjn[]) null, false, false, false);
    }

    public zzjn(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzjn(android.content.Context r13, com.google.android.gms.ads.AdSize[] r14) {
        /*
            r12 = this;
            r1 = 1
            r2 = 0
            r12.<init>()
            r6 = r14[r2]
            r12.zzarc = r2
            boolean r0 = r6.isFluid()
            r12.zzarf = r0
            boolean r0 = r12.zzarf
            if (r0 == 0) goto L_0x00bc
            com.google.android.gms.ads.AdSize r0 = com.google.android.gms.ads.AdSize.BANNER
            int r0 = r0.getWidth()
            r12.width = r0
            com.google.android.gms.ads.AdSize r0 = com.google.android.gms.ads.AdSize.BANNER
            int r0 = r0.getHeight()
            r12.height = r0
        L_0x0023:
            int r0 = r12.width
            r3 = -1
            if (r0 != r3) goto L_0x00ca
            r0 = r1
        L_0x0029:
            int r3 = r12.height
            r4 = -2
            if (r3 != r4) goto L_0x00cd
            r3 = r1
        L_0x002f:
            android.content.res.Resources r4 = r13.getResources()
            android.util.DisplayMetrics r7 = r4.getDisplayMetrics()
            if (r0 == 0) goto L_0x00d5
            com.google.android.gms.internal.ads.zzkb.zzif()
            boolean r4 = com.google.android.gms.internal.ads.zzamu.zzbi(r13)
            if (r4 == 0) goto L_0x00d0
            com.google.android.gms.internal.ads.zzkb.zzif()
            boolean r4 = com.google.android.gms.internal.ads.zzamu.zzbj(r13)
            if (r4 == 0) goto L_0x00d0
            int r4 = r7.widthPixels
            com.google.android.gms.internal.ads.zzkb.zzif()
            int r5 = com.google.android.gms.internal.ads.zzamu.zzbk(r13)
            int r4 = r4 - r5
            r12.widthPixels = r4
        L_0x0057:
            int r4 = r12.widthPixels
            float r4 = (float) r4
            float r5 = r7.density
            float r4 = r4 / r5
            double r8 = (double) r4
            int r4 = (int) r8
            int r5 = (int) r8
            double r10 = (double) r5
            double r8 = r8 - r10
            r10 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x006d
            int r4 = r4 + 1
        L_0x006d:
            r5 = r4
        L_0x006e:
            if (r3 == 0) goto L_0x00e4
            int r4 = zzd(r7)
        L_0x0074:
            com.google.android.gms.internal.ads.zzkb.zzif()
            int r7 = com.google.android.gms.internal.ads.zzamu.zza((android.util.DisplayMetrics) r7, (int) r4)
            r12.heightPixels = r7
            if (r0 != 0) goto L_0x0081
            if (r3 == 0) goto L_0x00e7
        L_0x0081:
            r0 = 26
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.StringBuilder r0 = r3.append(r5)
            java.lang.String r3 = "x"
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r3 = "_as"
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            r12.zzarb = r0
        L_0x00a2:
            int r0 = r14.length
            if (r0 <= r1) goto L_0x00f7
            int r0 = r14.length
            com.google.android.gms.internal.ads.zzjn[] r0 = new com.google.android.gms.internal.ads.zzjn[r0]
            r12.zzard = r0
            r0 = r2
        L_0x00ab:
            int r1 = r14.length
            if (r0 >= r1) goto L_0x00fa
            com.google.android.gms.internal.ads.zzjn[] r1 = r12.zzard
            com.google.android.gms.internal.ads.zzjn r3 = new com.google.android.gms.internal.ads.zzjn
            r4 = r14[r0]
            r3.<init>((android.content.Context) r13, (com.google.android.gms.ads.AdSize) r4)
            r1[r0] = r3
            int r0 = r0 + 1
            goto L_0x00ab
        L_0x00bc:
            int r0 = r6.getWidth()
            r12.width = r0
            int r0 = r6.getHeight()
            r12.height = r0
            goto L_0x0023
        L_0x00ca:
            r0 = r2
            goto L_0x0029
        L_0x00cd:
            r3 = r2
            goto L_0x002f
        L_0x00d0:
            int r4 = r7.widthPixels
            r12.widthPixels = r4
            goto L_0x0057
        L_0x00d5:
            int r4 = r12.width
            com.google.android.gms.internal.ads.zzkb.zzif()
            int r5 = r12.width
            int r5 = com.google.android.gms.internal.ads.zzamu.zza((android.util.DisplayMetrics) r7, (int) r5)
            r12.widthPixels = r5
            r5 = r4
            goto L_0x006e
        L_0x00e4:
            int r4 = r12.height
            goto L_0x0074
        L_0x00e7:
            boolean r0 = r12.zzarf
            if (r0 == 0) goto L_0x00f0
            java.lang.String r0 = "320x50_mb"
            r12.zzarb = r0
            goto L_0x00a2
        L_0x00f0:
            java.lang.String r0 = r6.toString()
            r12.zzarb = r0
            goto L_0x00a2
        L_0x00f7:
            r0 = 0
            r12.zzard = r0
        L_0x00fa:
            r12.zzare = r2
            r12.zzarg = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjn.<init>(android.content.Context, com.google.android.gms.ads.AdSize[]):void");
    }

    public zzjn(zzjn zzjn, zzjn[] zzjnArr) {
        this(zzjn.zzarb, zzjn.height, zzjn.heightPixels, zzjn.zzarc, zzjn.width, zzjn.widthPixels, zzjnArr, zzjn.zzare, zzjn.zzarf, zzjn.zzarg);
    }

    @SafeParcelable.Constructor
    zzjn(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) int i3, @SafeParcelable.Param(id = 7) int i4, @SafeParcelable.Param(id = 8) zzjn[] zzjnArr, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) boolean z3, @SafeParcelable.Param(id = 11) boolean z4) {
        this.zzarb = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzarc = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzard = zzjnArr;
        this.zzare = z2;
        this.zzarf = z3;
        this.zzarg = z4;
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics) {
        return (int) (((float) zzd(displayMetrics)) * displayMetrics.density);
    }

    private static int zzd(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public static zzjn zzf(Context context) {
        return new zzjn("320x50_mb", 0, 0, false, 0, 0, (zzjn[]) null, true, false, false);
    }

    public static zzjn zzhx() {
        return new zzjn("reward_mb", 0, 0, true, 0, 0, (zzjn[]) null, false, false, false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzarb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzarc);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.writeInt(parcel, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzard, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzare);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzarf);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzarg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final AdSize zzhy() {
        return zzb.zza(this.width, this.height, this.zzarb);
    }
}
