package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@SafeParcelable.Class(creator = "AdResponseParcelCreator")
@ParametersAreNonnullByDefault
public final class zzaej extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaej> CREATOR = new zzaek();
    @SafeParcelable.Field(id = 5)
    public final int errorCode;
    @SafeParcelable.Field(id = 12)
    public final int orientation;
    @SafeParcelable.Field(id = 1)
    private final int versionCode;
    @SafeParcelable.Field(id = 30)
    public final String zzamj;
    @SafeParcelable.Field(id = 23)
    public final boolean zzare;
    @SafeParcelable.Field(id = 31)
    public final boolean zzarf;
    @SafeParcelable.Field(id = 32)
    public final boolean zzarg;
    @SafeParcelable.Field(id = 4)
    public final List<String> zzbsn;
    @SafeParcelable.Field(id = 6)
    public final List<String> zzbso;
    @SafeParcelable.Field(id = 52)
    public final List<String> zzbsp;
    @SafeParcelable.Field(id = 40)
    public final List<String> zzbsr;
    @SafeParcelable.Field(id = 42)
    public final boolean zzbss;
    @SafeParcelable.Field(id = 11)
    public final long zzbsu;
    private zzaef zzbuc;
    @SafeParcelable.Field(id = 2)
    public final String zzbyq;
    @SafeParcelable.Field(id = 24)
    public final boolean zzcdd;
    @SafeParcelable.Field(id = 38)
    public final boolean zzcdr;
    @Nullable
    @SafeParcelable.Field(id = 39)
    public String zzcds;
    @SafeParcelable.Field(id = 47)
    public final boolean zzced;
    @SafeParcelable.Field(id = 3)
    public String zzceo;
    @SafeParcelable.Field(id = 7)
    public final long zzcep;
    @SafeParcelable.Field(id = 8)
    public final boolean zzceq;
    @SafeParcelable.Field(id = 9)
    public final long zzcer;
    @SafeParcelable.Field(id = 10)
    public final List<String> zzces;
    @SafeParcelable.Field(id = 13)
    public final String zzcet;
    @SafeParcelable.Field(id = 14)
    public final long zzceu;
    @SafeParcelable.Field(id = 15)
    public final String zzcev;
    @SafeParcelable.Field(id = 18)
    public final boolean zzcew;
    @SafeParcelable.Field(id = 19)
    public final String zzcex;
    @SafeParcelable.Field(id = 21)
    public final String zzcey;
    @SafeParcelable.Field(id = 22)
    public final boolean zzcez;
    @SafeParcelable.Field(id = 25)
    public final boolean zzcfa;
    @SafeParcelable.Field(id = 26)
    public final boolean zzcfb;
    @SafeParcelable.Field(id = 28)
    private zzaev zzcfc;
    @SafeParcelable.Field(id = 29)
    public String zzcfd;
    @Nullable
    @SafeParcelable.Field(id = 33)
    public final zzaig zzcfe;
    @Nullable
    @SafeParcelable.Field(id = 34)
    public final List<String> zzcff;
    @Nullable
    @SafeParcelable.Field(id = 35)
    public final List<String> zzcfg;
    @SafeParcelable.Field(id = 36)
    public final boolean zzcfh;
    @Nullable
    @SafeParcelable.Field(id = 37)
    public final zzael zzcfi;
    @Nullable
    @SafeParcelable.Field(id = 43)
    public final String zzcfj;
    @Nullable
    @SafeParcelable.Field(id = 44)
    public final zzaiq zzcfk;
    @Nullable
    @SafeParcelable.Field(id = 45)
    public final String zzcfl;
    @SafeParcelable.Field(id = 46)
    public final boolean zzcfm;
    @SafeParcelable.Field(id = 48)
    private Bundle zzcfn;
    @SafeParcelable.Field(id = 50)
    public final int zzcfo;
    @SafeParcelable.Field(id = 51)
    public final boolean zzcfp;
    @Nullable
    @SafeParcelable.Field(id = 54)
    public final String zzcfq;
    @SafeParcelable.Field(id = 49)
    public final boolean zzzl;
    @SafeParcelable.Field(id = 53)
    public final boolean zzzm;

    public zzaej(int i) {
        this(19, (String) null, (String) null, (List<String>) null, i, (List<String>) null, -1, false, -1, (List<String>) null, -1, -1, (String) null, -1, (String) null, false, (String) null, (String) null, false, false, false, true, false, (zzaev) null, (String) null, (String) null, false, false, (zzaig) null, (List<String>) null, (List<String>) null, false, (zzael) null, false, (String) null, (List<String>) null, false, (String) null, (zzaiq) null, (String) null, true, false, (Bundle) null, false, 0, false, (List<String>) null, false, (String) null);
    }

    public zzaej(int i, long j) {
        this(19, (String) null, (String) null, (List<String>) null, i, (List<String>) null, -1, false, -1, (List<String>) null, j, -1, (String) null, -1, (String) null, false, (String) null, (String) null, false, false, false, true, false, (zzaev) null, (String) null, (String) null, false, false, (zzaig) null, (List<String>) null, (List<String>) null, false, (zzael) null, false, (String) null, (List<String>) null, false, (String) null, (zzaiq) null, (String) null, true, false, (Bundle) null, false, 0, false, (List<String>) null, false, (String) null);
    }

    @SafeParcelable.Constructor
    zzaej(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) List<String> list, @SafeParcelable.Param(id = 5) int i2, @SafeParcelable.Param(id = 6) List<String> list2, @SafeParcelable.Param(id = 7) long j, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) long j2, @SafeParcelable.Param(id = 10) List<String> list3, @SafeParcelable.Param(id = 11) long j3, @SafeParcelable.Param(id = 12) int i3, @SafeParcelable.Param(id = 13) String str3, @SafeParcelable.Param(id = 14) long j4, @SafeParcelable.Param(id = 15) String str4, @SafeParcelable.Param(id = 18) boolean z2, @SafeParcelable.Param(id = 19) String str5, @SafeParcelable.Param(id = 21) String str6, @SafeParcelable.Param(id = 22) boolean z3, @SafeParcelable.Param(id = 23) boolean z4, @SafeParcelable.Param(id = 24) boolean z5, @SafeParcelable.Param(id = 25) boolean z6, @SafeParcelable.Param(id = 26) boolean z7, @SafeParcelable.Param(id = 28) zzaev zzaev, @SafeParcelable.Param(id = 29) String str7, @SafeParcelable.Param(id = 30) String str8, @SafeParcelable.Param(id = 31) boolean z8, @SafeParcelable.Param(id = 32) boolean z9, @SafeParcelable.Param(id = 33) zzaig zzaig, @SafeParcelable.Param(id = 34) List<String> list4, @SafeParcelable.Param(id = 35) List<String> list5, @SafeParcelable.Param(id = 36) boolean z10, @SafeParcelable.Param(id = 37) zzael zzael, @SafeParcelable.Param(id = 38) boolean z11, @SafeParcelable.Param(id = 39) String str9, @SafeParcelable.Param(id = 40) List<String> list6, @SafeParcelable.Param(id = 42) boolean z12, @SafeParcelable.Param(id = 43) String str10, @SafeParcelable.Param(id = 44) zzaiq zzaiq, @SafeParcelable.Param(id = 45) String str11, @SafeParcelable.Param(id = 46) boolean z13, @SafeParcelable.Param(id = 47) boolean z14, @SafeParcelable.Param(id = 48) Bundle bundle, @SafeParcelable.Param(id = 49) boolean z15, @SafeParcelable.Param(id = 50) int i4, @SafeParcelable.Param(id = 51) boolean z16, @SafeParcelable.Param(id = 52) List<String> list7, @SafeParcelable.Param(id = 53) boolean z17, @SafeParcelable.Param(id = 54) String str12) {
        zzafj zzafj;
        this.versionCode = i;
        this.zzbyq = str;
        this.zzceo = str2;
        this.zzbsn = list != null ? Collections.unmodifiableList(list) : null;
        this.errorCode = i2;
        this.zzbso = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.zzcep = j;
        this.zzceq = z;
        this.zzcer = j2;
        this.zzces = list3 != null ? Collections.unmodifiableList(list3) : null;
        this.zzbsu = j3;
        this.orientation = i3;
        this.zzcet = str3;
        this.zzceu = j4;
        this.zzcev = str4;
        this.zzcew = z2;
        this.zzcex = str5;
        this.zzcey = str6;
        this.zzcez = z3;
        this.zzare = z4;
        this.zzcdd = z5;
        this.zzcfa = z6;
        this.zzcfm = z13;
        this.zzcfb = z7;
        this.zzcfc = zzaev;
        this.zzcfd = str7;
        this.zzamj = str8;
        if (this.zzceo == null && this.zzcfc != null && (zzafj = (zzafj) this.zzcfc.zza(zzafj.CREATOR)) != null && !TextUtils.isEmpty(zzafj.zzbgu)) {
            this.zzceo = zzafj.zzbgu;
        }
        this.zzarf = z8;
        this.zzarg = z9;
        this.zzcfe = zzaig;
        this.zzcff = list4;
        this.zzcfg = list5;
        this.zzcfh = z10;
        this.zzcfi = zzael;
        this.zzcdr = z11;
        this.zzcds = str9;
        this.zzbsr = list6;
        this.zzbss = z12;
        this.zzcfj = str10;
        this.zzcfk = zzaiq;
        this.zzcfl = str11;
        this.zzced = z14;
        this.zzcfn = bundle;
        this.zzzl = z15;
        this.zzcfo = i4;
        this.zzcfp = z16;
        this.zzbsp = list7 != null ? Collections.unmodifiableList(list7) : null;
        this.zzzm = z17;
        this.zzcfq = str12;
    }

    public zzaej(zzaef zzaef, String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, String str5, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str6, boolean z7, boolean z8, zzaig zzaig, List<String> list4, List<String> list5, boolean z9, zzael zzael, boolean z10, String str7, List<String> list6, boolean z11, String str8, zzaiq zzaiq, String str9, boolean z12, boolean z13, boolean z14, int i2, boolean z15, List<String> list7, boolean z16, String str10) {
        this(19, str, str2, list, -2, list2, j, z, -1, list3, j3, i, str3, j4, str4, false, (String) null, str5, z2, z3, z4, z5, false, (zzaev) null, (String) null, str6, z7, z8, zzaig, list4, list5, z9, zzael, z10, str7, list6, z11, str8, zzaiq, str9, z12, z13, (Bundle) null, z14, i2, z15, list7, z16, str10);
        this.zzbuc = zzaef;
    }

    public zzaej(zzaef zzaef, String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, boolean z2, String str5, String str6, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str7, boolean z8, boolean z9, zzaig zzaig, List<String> list4, List<String> list5, boolean z10, zzael zzael, boolean z11, String str8, List<String> list6, boolean z12, String str9, zzaiq zzaiq, String str10, boolean z13, boolean z14, boolean z15, int i2, boolean z16, List<String> list7, boolean z17, String str11) {
        this(19, str, str2, list, -2, list2, j, z, j2, list3, j3, i, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, (zzaev) null, (String) null, str7, z8, z9, zzaig, list4, list5, z10, zzael, z11, str8, list6, z12, str9, zzaiq, str10, z13, z14, (Bundle) null, z15, 0, z16, list7, z17, str11);
        this.zzbuc = zzaef;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (this.zzbuc != null && this.zzbuc.versionCode >= 9 && !TextUtils.isEmpty(this.zzceo)) {
            this.zzcfc = new zzaev((SafeParcelable) new zzafj(this.zzceo));
            this.zzceo = null;
        }
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.zzbyq, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzceo, false);
        SafeParcelWriter.writeStringList(parcel, 4, this.zzbsn, false);
        SafeParcelWriter.writeInt(parcel, 5, this.errorCode);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzbso, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzcep);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzceq);
        SafeParcelWriter.writeLong(parcel, 9, this.zzcer);
        SafeParcelWriter.writeStringList(parcel, 10, this.zzces, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzbsu);
        SafeParcelWriter.writeInt(parcel, 12, this.orientation);
        SafeParcelWriter.writeString(parcel, 13, this.zzcet, false);
        SafeParcelWriter.writeLong(parcel, 14, this.zzceu);
        SafeParcelWriter.writeString(parcel, 15, this.zzcev, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzcew);
        SafeParcelWriter.writeString(parcel, 19, this.zzcex, false);
        SafeParcelWriter.writeString(parcel, 21, this.zzcey, false);
        SafeParcelWriter.writeBoolean(parcel, 22, this.zzcez);
        SafeParcelWriter.writeBoolean(parcel, 23, this.zzare);
        SafeParcelWriter.writeBoolean(parcel, 24, this.zzcdd);
        SafeParcelWriter.writeBoolean(parcel, 25, this.zzcfa);
        SafeParcelWriter.writeBoolean(parcel, 26, this.zzcfb);
        SafeParcelWriter.writeParcelable(parcel, 28, this.zzcfc, i, false);
        SafeParcelWriter.writeString(parcel, 29, this.zzcfd, false);
        SafeParcelWriter.writeString(parcel, 30, this.zzamj, false);
        SafeParcelWriter.writeBoolean(parcel, 31, this.zzarf);
        SafeParcelWriter.writeBoolean(parcel, 32, this.zzarg);
        SafeParcelWriter.writeParcelable(parcel, 33, this.zzcfe, i, false);
        SafeParcelWriter.writeStringList(parcel, 34, this.zzcff, false);
        SafeParcelWriter.writeStringList(parcel, 35, this.zzcfg, false);
        SafeParcelWriter.writeBoolean(parcel, 36, this.zzcfh);
        SafeParcelWriter.writeParcelable(parcel, 37, this.zzcfi, i, false);
        SafeParcelWriter.writeBoolean(parcel, 38, this.zzcdr);
        SafeParcelWriter.writeString(parcel, 39, this.zzcds, false);
        SafeParcelWriter.writeStringList(parcel, 40, this.zzbsr, false);
        SafeParcelWriter.writeBoolean(parcel, 42, this.zzbss);
        SafeParcelWriter.writeString(parcel, 43, this.zzcfj, false);
        SafeParcelWriter.writeParcelable(parcel, 44, this.zzcfk, i, false);
        SafeParcelWriter.writeString(parcel, 45, this.zzcfl, false);
        SafeParcelWriter.writeBoolean(parcel, 46, this.zzcfm);
        SafeParcelWriter.writeBoolean(parcel, 47, this.zzced);
        SafeParcelWriter.writeBundle(parcel, 48, this.zzcfn, false);
        SafeParcelWriter.writeBoolean(parcel, 49, this.zzzl);
        SafeParcelWriter.writeInt(parcel, 50, this.zzcfo);
        SafeParcelWriter.writeBoolean(parcel, 51, this.zzcfp);
        SafeParcelWriter.writeStringList(parcel, 52, this.zzbsp, false);
        SafeParcelWriter.writeBoolean(parcel, 53, this.zzzm);
        SafeParcelWriter.writeString(parcel, 54, this.zzcfq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
