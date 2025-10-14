package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class(creator = "JoinOptionsCreator")
@SafeParcelable.Reserved({1})
public final class zzah extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzah> CREATOR = new zzag();
    @SafeParcelable.Field(getter = "getConnectionType", id = 2)
    private int zzda;

    @SafeParcelable.Constructor
    zzah(@SafeParcelable.Param(id = 2) int i) {
        this.zzda = i;
    }

    public zzah() {
        this(0);
    }

    public final String toString() {
        String str;
        switch (this.zzda) {
            case 0:
                str = "STRONG";
                break;
            case 2:
                str = "INVISIBLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        return String.format("joinOptions(connectionType=%s)", new Object[]{str});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzda);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzah)) {
            return false;
        }
        if (this.zzda != ((zzah) obj).zzda) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzda));
    }
}
