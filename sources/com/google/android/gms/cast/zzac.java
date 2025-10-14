package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class(creator = "EqualizerBandSettingsCreator")
@SafeParcelable.Reserved({1})
public final class zzac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzac> CREATOR = new zzab();
    @SafeParcelable.Field(getter = "getFrequency", id = 2)
    private final float zzct;
    @SafeParcelable.Field(getter = "getQFactor", id = 3)
    private final float zzcu;
    @SafeParcelable.Field(getter = "getGainDb", id = 4)
    private final float zzcv;

    @SafeParcelable.Constructor
    public zzac(@SafeParcelable.Param(id = 2) float f, @SafeParcelable.Param(id = 3) float f2, @SafeParcelable.Param(id = 4) float f3) {
        this.zzct = f;
        this.zzcu = f2;
        this.zzcv = f3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zzct);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzcu);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzcv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzac)) {
            return false;
        }
        zzac zzac = (zzac) obj;
        if (this.zzct == zzac.zzct && this.zzcu == zzac.zzcu && this.zzcv == zzac.zzcv) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzct), Float.valueOf(this.zzcu), Float.valueOf(this.zzcv));
    }
}
