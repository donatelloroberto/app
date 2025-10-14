package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.cast.zzdc;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "AdBreakInfoCreator")
@SafeParcelable.Reserved({1})
public class AdBreakInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AdBreakInfo> CREATOR = new zzb();
    @SafeParcelable.Field(getter = "getId", id = 3)
    private final String zze;
    @SafeParcelable.Field(getter = "getDurationInMs", id = 4)
    private final long zzg;
    @SafeParcelable.Field(getter = "getPlaybackPositionInMs", id = 2)
    private final long zzq;
    @SafeParcelable.Field(getter = "isWatched", id = 5)
    private final boolean zzr;
    @SafeParcelable.Field(getter = "getBreakClipIds", id = 6)
    private String[] zzs;
    @SafeParcelable.Field(getter = "isEmbedded", id = 7)
    private final boolean zzt;

    @SafeParcelable.Constructor
    public AdBreakInfo(@SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) String[] strArr, @SafeParcelable.Param(id = 7) boolean z2) {
        this.zzq = j;
        this.zze = str;
        this.zzg = j2;
        this.zzr = z;
        this.zzs = strArr;
        this.zzt = z2;
    }

    public static class Builder {
        private String zze = null;
        private long zzg = 0;
        private long zzq = 0;
        private boolean zzr = false;
        private String[] zzs = null;
        private boolean zzt = false;

        public Builder(long j) {
            this.zzq = j;
        }

        public Builder setId(String str) {
            this.zze = str;
            return this;
        }

        public Builder setDurationInMs(long j) {
            this.zzg = j;
            return this;
        }

        public Builder setIsWatched(boolean z) {
            this.zzr = z;
            return this;
        }

        public Builder setIsEmbedded(boolean z) {
            this.zzt = z;
            return this;
        }

        public Builder setBreakClipIds(String[] strArr) {
            this.zzs = strArr;
            return this;
        }

        public AdBreakInfo build() {
            return new AdBreakInfo(this.zzq, this.zze, this.zzg, this.zzr, this.zzs, this.zzt);
        }
    }

    public long getPlaybackPositionInMs() {
        return this.zzq;
    }

    public String getId() {
        return this.zze;
    }

    public long getDurationInMs() {
        return this.zzg;
    }

    public boolean isWatched() {
        return this.zzr;
    }

    public boolean isEmbedded() {
        return this.zzt;
    }

    public String[] getBreakClipIds() {
        return this.zzs;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getPlaybackPositionInMs());
        SafeParcelWriter.writeString(parcel, 3, getId(), false);
        SafeParcelWriter.writeLong(parcel, 4, getDurationInMs());
        SafeParcelWriter.writeBoolean(parcel, 5, isWatched());
        SafeParcelWriter.writeStringArray(parcel, 6, getBreakClipIds(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, isEmbedded());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return this.zze.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakInfo)) {
            return false;
        }
        AdBreakInfo adBreakInfo = (AdBreakInfo) obj;
        if (zzdc.zza(this.zze, adBreakInfo.zze) && this.zzq == adBreakInfo.zzq && this.zzg == adBreakInfo.zzg && this.zzr == adBreakInfo.zzr && Arrays.equals(this.zzs, adBreakInfo.zzs) && this.zzt == adBreakInfo.zzt) {
            return true;
        }
        return false;
    }

    static AdBreakInfo zzb(JSONObject jSONObject) {
        String[] strArr;
        if (jSONObject == null) {
            return null;
        }
        if (!jSONObject.has(PushConstants.CHANNEL_ID) || !jSONObject.has("position")) {
            return null;
        }
        try {
            String string = jSONObject.getString(PushConstants.CHANNEL_ID);
            long j = (long) (((double) jSONObject.getLong("position")) * 1000.0d);
            boolean optBoolean = jSONObject.optBoolean("isWatched");
            long optLong = (long) (((double) jSONObject.optLong("duration")) * 1000.0d);
            JSONArray optJSONArray = jSONObject.optJSONArray("breakClipIds");
            if (optJSONArray != null) {
                strArr = new String[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    strArr[i] = optJSONArray.getString(i);
                }
            } else {
                strArr = null;
            }
            return new AdBreakInfo(j, string, optLong, optBoolean, strArr, jSONObject.optBoolean("isEmbedded"));
        } catch (JSONException e) {
            Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakInfo from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PushConstants.CHANNEL_ID, this.zze);
            jSONObject.put("position", ((double) this.zzq) / 1000.0d);
            jSONObject.put("isWatched", this.zzr);
            jSONObject.put("isEmbedded", this.zzt);
            jSONObject.put("duration", ((double) this.zzg) / 1000.0d);
            if (this.zzs != null) {
                JSONArray jSONArray = new JSONArray();
                for (String put : this.zzs) {
                    jSONArray.put(put);
                }
                jSONObject.put("breakClipIds", jSONArray);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
