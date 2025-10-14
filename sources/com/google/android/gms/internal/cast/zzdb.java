package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.zzae;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "DeviceStatusCreator")
@SafeParcelable.Reserved({1})
public final class zzdb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdb> CREATOR = new zzde();
    @SafeParcelable.Field(getter = "getVolume", id = 2)
    private double zzfh;
    @SafeParcelable.Field(getter = "getMuteState", id = 3)
    private boolean zzfi;
    @SafeParcelable.Field(getter = "getEqualizerSettings", id = 7)
    private zzae zzyr;
    @SafeParcelable.Field(getter = "getActiveInputState", id = 4)
    private int zzys;
    @SafeParcelable.Field(getter = "getStandbyState", id = 6)
    private int zzyt;
    @SafeParcelable.Field(getter = "getStepInterval", id = 8)
    private double zzyz;
    @SafeParcelable.Field(getter = "getApplicationMetadata", id = 5)
    private ApplicationMetadata zzzi;

    @SafeParcelable.Constructor
    zzdb(@SafeParcelable.Param(id = 2) double d, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) ApplicationMetadata applicationMetadata, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) zzae zzae, @SafeParcelable.Param(id = 8) double d2) {
        this.zzfh = d;
        this.zzfi = z;
        this.zzys = i;
        this.zzzi = applicationMetadata;
        this.zzyt = i2;
        this.zzyr = zzae;
        this.zzyz = d2;
    }

    public zzdb() {
        this(Double.NaN, false, -1, (ApplicationMetadata) null, -1, (zzae) null, Double.NaN);
    }

    public final double getVolume() {
        return this.zzfh;
    }

    public final boolean zzei() {
        return this.zzfi;
    }

    public final int getActiveInputState() {
        return this.zzys;
    }

    public final int getStandbyState() {
        return this.zzyt;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzzi;
    }

    public final zzae zzej() {
        return this.zzyr;
    }

    public final double zzek() {
        return this.zzyz;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeDouble(parcel, 2, this.zzfh);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzfi);
        SafeParcelWriter.writeInt(parcel, 4, this.zzys);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzzi, i, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzyt);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzyr, i, false);
        SafeParcelWriter.writeDouble(parcel, 8, this.zzyz);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdb)) {
            return false;
        }
        zzdb zzdb = (zzdb) obj;
        if (this.zzfh == zzdb.zzfh && this.zzfi == zzdb.zzfi && this.zzys == zzdb.zzys && zzdc.zza(this.zzzi, zzdb.zzzi) && this.zzyt == zzdb.zzyt && zzdc.zza(this.zzyr, this.zzyr) && this.zzyz == zzdb.zzyz) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Double.valueOf(this.zzfh), Boolean.valueOf(this.zzfi), Integer.valueOf(this.zzys), this.zzzi, Integer.valueOf(this.zzyt), this.zzyr, Double.valueOf(this.zzyz));
    }
}
