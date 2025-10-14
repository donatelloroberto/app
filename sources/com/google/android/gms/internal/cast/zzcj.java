package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ApplicationStatusCreator")
@SafeParcelable.Reserved({1})
public final class zzcj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcj> CREATOR = new zzcm();
    @SafeParcelable.Field(getter = "getApplicationStatusText", id = 2)
    private String zzyf;

    @SafeParcelable.Constructor
    zzcj(@SafeParcelable.Param(id = 2) String str) {
        this.zzyf = str;
    }

    public zzcj() {
        this((String) null);
    }

    public final String zzdy() {
        return this.zzyf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzyf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcj)) {
            return false;
        }
        return zzdc.zza(this.zzyf, ((zzcj) obj).zzyf);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzyf);
    }
}
