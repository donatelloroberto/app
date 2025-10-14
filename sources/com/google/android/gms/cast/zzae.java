package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.cast.zzdc;

@ShowFirstParty
@SafeParcelable.Class(creator = "EqualizerSettingsCreator")
@SafeParcelable.Reserved({1})
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzae> CREATOR = new zzad();
    @SafeParcelable.Field(getter = "getLowShelf", id = 2)
    private final zzac zzcw;
    @SafeParcelable.Field(getter = "getHighShelf", id = 3)
    private final zzac zzcx;

    @SafeParcelable.Constructor
    public zzae(@SafeParcelable.Param(id = 2) zzac zzac, @SafeParcelable.Param(id = 3) zzac zzac2) {
        this.zzcw = zzac;
        this.zzcx = zzac2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcw, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzcx, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzae)) {
            return false;
        }
        zzae zzae = (zzae) obj;
        if (!zzdc.zza(this.zzcw, zzae.zzcw) || !zzdc.zza(this.zzcx, zzae.zzcx)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcw, this.zzcx);
    }
}
