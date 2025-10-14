package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.cast.zzdc;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "AdBreakStatusCreator")
@SafeParcelable.Reserved({1})
public class AdBreakStatus extends AbstractSafeParcelable {
    public static final int AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakStatus> CREATOR = new zzc();
    @SafeParcelable.Field(getter = "getWhenSkippableInMs", id = 6)
    private final long zzm;
    @SafeParcelable.Field(getter = "getCurrentBreakTimeInMs", id = 2)
    private final long zzu;
    @SafeParcelable.Field(getter = "getCurrentBreakClipTimeInMs", id = 3)
    private final long zzv;
    @SafeParcelable.Field(getter = "getBreakId", id = 4)
    private final String zzw;
    @SafeParcelable.Field(getter = "getBreakClipId", id = 5)
    private final String zzx;

    @SafeParcelable.Constructor
    AdBreakStatus(@SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) String str2, @SafeParcelable.Param(id = 6) long j3) {
        this.zzu = j;
        this.zzv = j2;
        this.zzw = str;
        this.zzx = str2;
        this.zzm = j3;
    }

    public String getBreakId() {
        return this.zzw;
    }

    public String getBreakClipId() {
        return this.zzx;
    }

    public long getCurrentBreakTimeInMs() {
        return this.zzu;
    }

    public long getCurrentBreakClipTimeInMs() {
        return this.zzv;
    }

    public long getWhenSkippableInMs() {
        return this.zzm;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getCurrentBreakTimeInMs());
        SafeParcelWriter.writeLong(parcel, 3, getCurrentBreakClipTimeInMs());
        SafeParcelWriter.writeString(parcel, 4, getBreakId(), false);
        SafeParcelWriter.writeString(parcel, 5, getBreakClipId(), false);
        SafeParcelWriter.writeLong(parcel, 6, getWhenSkippableInMs());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzu), Long.valueOf(this.zzv), this.zzw, this.zzx, Long.valueOf(this.zzm));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakStatus)) {
            return false;
        }
        AdBreakStatus adBreakStatus = (AdBreakStatus) obj;
        if (this.zzu == adBreakStatus.zzu && this.zzv == adBreakStatus.zzv && zzdc.zza(this.zzw, adBreakStatus.zzw) && zzdc.zza(this.zzx, adBreakStatus.zzx) && this.zzm == adBreakStatus.zzm) {
            return true;
        }
        return false;
    }

    static AdBreakStatus zzc(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        if (!jSONObject.has("currentBreakTime") || !jSONObject.has("currentBreakClipTime")) {
            return null;
        }
        try {
            long j = (long) (((double) jSONObject.getLong("currentBreakTime")) * 1000.0d);
            long j2 = (long) (((double) jSONObject.getLong("currentBreakClipTime")) * 1000.0d);
            String optString = jSONObject.optString("breakId", (String) null);
            String optString2 = jSONObject.optString("breakClipId", (String) null);
            long optLong = jSONObject.optLong("whenSkippable", -1);
            if (optLong != -1) {
                optLong = (long) (((double) optLong) * 1000.0d);
            }
            return new AdBreakStatus(j, j2, optString, optString2, optLong);
        } catch (JSONException e) {
            Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }
}
