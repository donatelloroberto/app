package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.cast.zzdc;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "AdBreakClipInfoCreator")
@SafeParcelable.Reserved({1})
public class AdBreakClipInfo extends AbstractSafeParcelable {
    public static final long AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakClipInfo> CREATOR = new zza();
    @SafeParcelable.Field(getter = "getMimeType", id = 6)
    private final String mimeType;
    @SafeParcelable.Field(getter = "getId", id = 2)
    private final String zze;
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    private final String zzf;
    @SafeParcelable.Field(getter = "getDurationInMs", id = 4)
    private final long zzg;
    @SafeParcelable.Field(getter = "getContentUrl", id = 5)
    private final String zzh;
    @SafeParcelable.Field(getter = "getClickThroughUrl", id = 7)
    private final String zzi;
    @SafeParcelable.Field(getter = "getCustomDataAsString", id = 8)
    private String zzj;
    @SafeParcelable.Field(getter = "getContentId", id = 9)
    private String zzk;
    @SafeParcelable.Field(getter = "getImageUrl", id = 10)
    private String zzl;
    @SafeParcelable.Field(getter = "getWhenSkippableInMs", id = 11)
    private final long zzm;
    @HlsSegmentFormat
    @SafeParcelable.Field(getter = "getHlsSegmentFormat", id = 12)
    private final String zzn;
    @SafeParcelable.Field(getter = "getVastAdsRequest", id = 13)
    private final VastAdsRequest zzo;
    private JSONObject zzp;

    @SafeParcelable.Constructor
    AdBreakClipInfo(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) long j, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) String str4, @SafeParcelable.Param(id = 7) String str5, @SafeParcelable.Param(id = 8) String str6, @SafeParcelable.Param(id = 9) String str7, @SafeParcelable.Param(id = 10) String str8, @SafeParcelable.Param(id = 11) long j2, @HlsSegmentFormat @SafeParcelable.Param(id = 12) String str9, @SafeParcelable.Param(id = 13) VastAdsRequest vastAdsRequest) {
        this.zze = str;
        this.zzf = str2;
        this.zzg = j;
        this.zzh = str3;
        this.mimeType = str4;
        this.zzi = str5;
        this.zzj = str6;
        this.zzk = str7;
        this.zzl = str8;
        this.zzm = j2;
        this.zzn = str9;
        this.zzo = vastAdsRequest;
        if (!TextUtils.isEmpty(this.zzj)) {
            try {
                this.zzp = new JSONObject(str6);
            } catch (JSONException e) {
                Log.w("AdBreakClipInfo", String.format(Locale.ROOT, "Error creating AdBreakClipInfo: %s", new Object[]{e.getMessage()}));
                this.zzj = null;
                this.zzp = new JSONObject();
            }
        } else {
            this.zzp = new JSONObject();
        }
    }

    public static class Builder {
        private String mimeType = null;
        private String zze = null;
        private String zzf = null;
        private long zzg = 0;
        private String zzh = null;
        private String zzi = null;
        private String zzj = null;
        private String zzk = null;
        private String zzl = null;
        private long zzm = -1;
        @HlsSegmentFormat
        private String zzn;
        private VastAdsRequest zzo = null;

        public Builder(String str) {
            this.zze = str;
        }

        public Builder setTitle(String str) {
            this.zzf = str;
            return this;
        }

        public Builder setDurationInMs(long j) {
            this.zzg = j;
            return this;
        }

        public Builder setContentUrl(String str) {
            this.zzh = str;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder setClickThroughUrl(String str) {
            this.zzi = str;
            return this;
        }

        public Builder setCustomDataJsonString(String str) {
            this.zzj = str;
            return this;
        }

        public Builder setContentId(String str) {
            this.zzk = str;
            return this;
        }

        public Builder setImageUrl(String str) {
            this.zzl = str;
            return this;
        }

        public Builder setWhenSkippableInMs(long j) {
            this.zzm = j;
            return this;
        }

        public Builder setHlsSegmentFormat(String str) {
            this.zzn = str;
            return this;
        }

        public Builder setVastAdsRequest(VastAdsRequest vastAdsRequest) {
            this.zzo = vastAdsRequest;
            return this;
        }

        public AdBreakClipInfo build() {
            return new AdBreakClipInfo(this.zze, this.zzf, this.zzg, this.zzh, this.mimeType, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo);
        }
    }

    public String getId() {
        return this.zze;
    }

    public String getTitle() {
        return this.zzf;
    }

    public long getDurationInMs() {
        return this.zzg;
    }

    public String getContentUrl() {
        return this.zzh;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getClickThroughUrl() {
        return this.zzi;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public String getContentId() {
        return this.zzk;
    }

    public String getImageUrl() {
        return this.zzl;
    }

    public long getWhenSkippableInMs() {
        return this.zzm;
    }

    public String getHlsSegmentFormat() {
        return this.zzn;
    }

    public VastAdsRequest getVastAdsRequest() {
        return this.zzo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeLong(parcel, 4, getDurationInMs());
        SafeParcelWriter.writeString(parcel, 5, getContentUrl(), false);
        SafeParcelWriter.writeString(parcel, 6, getMimeType(), false);
        SafeParcelWriter.writeString(parcel, 7, getClickThroughUrl(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zzj, false);
        SafeParcelWriter.writeString(parcel, 9, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 10, getImageUrl(), false);
        SafeParcelWriter.writeLong(parcel, 11, getWhenSkippableInMs());
        SafeParcelWriter.writeString(parcel, 12, getHlsSegmentFormat(), false);
        SafeParcelWriter.writeParcelable(parcel, 13, getVastAdsRequest(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(this.zze, this.zzf, Long.valueOf(this.zzg), this.zzh, this.mimeType, this.zzi, this.zzj, this.zzk, this.zzl, Long.valueOf(this.zzm), this.zzn, this.zzo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakClipInfo)) {
            return false;
        }
        AdBreakClipInfo adBreakClipInfo = (AdBreakClipInfo) obj;
        if (!zzdc.zza(this.zze, adBreakClipInfo.zze) || !zzdc.zza(this.zzf, adBreakClipInfo.zzf) || this.zzg != adBreakClipInfo.zzg || !zzdc.zza(this.zzh, adBreakClipInfo.zzh) || !zzdc.zza(this.mimeType, adBreakClipInfo.mimeType) || !zzdc.zza(this.zzi, adBreakClipInfo.zzi) || !zzdc.zza(this.zzj, adBreakClipInfo.zzj) || !zzdc.zza(this.zzk, adBreakClipInfo.zzk) || !zzdc.zza(this.zzl, adBreakClipInfo.zzl) || this.zzm != adBreakClipInfo.zzm || !zzdc.zza(this.zzn, adBreakClipInfo.zzn) || !zzdc.zza(this.zzo, adBreakClipInfo.zzo)) {
            return false;
        }
        return true;
    }

    static AdBreakClipInfo zza(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has(PushConstants.CHANNEL_ID)) {
            return null;
        }
        try {
            String string = jSONObject.getString(PushConstants.CHANNEL_ID);
            long optLong = (long) (((double) jSONObject.optLong("duration")) * 1000.0d);
            String optString = jSONObject.optString("clickThroughUrl", (String) null);
            String optString2 = jSONObject.optString("contentUrl", (String) null);
            String optString3 = jSONObject.optString("mimeType", (String) null);
            if (optString3 == null) {
                optString3 = jSONObject.optString("contentType", (String) null);
            }
            String optString4 = jSONObject.optString(PushConstants.TITLE, (String) null);
            JSONObject optJSONObject = jSONObject.optJSONObject("customData");
            String optString5 = jSONObject.optString("contentId", (String) null);
            String optString6 = jSONObject.optString("posterUrl", (String) null);
            long j = -1;
            if (jSONObject.has("whenSkippable")) {
                j = (long) (((double) ((Integer) jSONObject.get("whenSkippable")).intValue()) * 1000.0d);
            }
            return new AdBreakClipInfo(string, optString4, optLong, optString2, optString3, optString, (optJSONObject == null || optJSONObject.length() == 0) ? null : optJSONObject.toString(), optString5, optString6, j, jSONObject.optString("hlsSegmentFormat", (String) null), VastAdsRequest.fromJson(jSONObject.optJSONObject("vastAdsRequest")));
        } catch (JSONException e) {
            Log.d("AdBreakClipInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PushConstants.CHANNEL_ID, this.zze);
            jSONObject.put("duration", ((double) this.zzg) / 1000.0d);
            if (this.zzm != -1) {
                jSONObject.put("whenSkippable", ((double) this.zzm) / 1000.0d);
            }
            if (this.zzk != null) {
                jSONObject.put("contentId", this.zzk);
            }
            if (this.mimeType != null) {
                jSONObject.put("contentType", this.mimeType);
            }
            if (this.zzf != null) {
                jSONObject.put(PushConstants.TITLE, this.zzf);
            }
            if (this.zzh != null) {
                jSONObject.put("contentUrl", this.zzh);
            }
            if (this.zzi != null) {
                jSONObject.put("clickThroughUrl", this.zzi);
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
            if (this.zzl != null) {
                jSONObject.put("posterUrl", this.zzl);
            }
            if (this.zzn != null) {
                jSONObject.put("hlsSegmentFormat", this.zzn);
            }
            if (this.zzo != null) {
                jSONObject.put("vastAdsRequest", this.zzo.toJson());
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
