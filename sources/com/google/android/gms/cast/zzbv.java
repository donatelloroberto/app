package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.cast.zzdc;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "RequestItemCreator")
@SafeParcelable.Reserved({1})
public final class zzbv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbv> CREATOR = new zzbu();
    @SafeParcelable.Field(getter = "getUrl", id = 2)
    private final String url;
    @SafeParcelable.Field(getter = "getProtocolType", id = 3)
    private final int zzhg;
    @SafeParcelable.Field(defaultValue = "0", getter = "getInitialTime", id = 4)
    private final int zzhh;
    @SafeParcelable.Field(getter = "getHlsSegmentFormat", id = 5)
    private final String zzn;

    @SafeParcelable.Constructor
    public zzbv(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @HlsSegmentFormat @SafeParcelable.Param(id = 5) String str2) {
        this.url = str;
        this.zzhg = i;
        this.zzhh = i2;
        this.zzn = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbv)) {
            return false;
        }
        zzbv zzbv = (zzbv) obj;
        if (!zzdc.zza(this.url, zzbv.url) || !zzdc.zza(Integer.valueOf(this.zzhg), Integer.valueOf(zzbv.zzhg)) || !zzdc.zza(Integer.valueOf(this.zzhh), Integer.valueOf(zzbv.zzhh)) || !zzdc.zza(zzbv.zzn, this.zzn)) {
            return false;
        }
        return true;
    }

    public final JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ImagesContract.URL, this.url);
        jSONObject.put("protocolType", this.zzhg);
        jSONObject.put("initialTime", this.zzhh);
        jSONObject.put("hlsSegmentFormat", this.zzn);
        return jSONObject;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzhg);
        SafeParcelWriter.writeInt(parcel, 4, this.zzhh);
        SafeParcelWriter.writeString(parcel, 5, this.zzn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @VisibleForTesting
    public final int hashCode() {
        return Objects.hashCode(this.url, Integer.valueOf(this.zzhg), Integer.valueOf(this.zzhh), this.zzn);
    }
}
