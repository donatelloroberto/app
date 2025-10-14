package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.cast.zzdc;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "VastAdsRequestCreator")
@SafeParcelable.Reserved({1})
public class VastAdsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VastAdsRequest> CREATOR = new zzbx();
    @SafeParcelable.Field(getter = "getAdTagUrl", id = 2)
    private final String zzhm;
    @SafeParcelable.Field(getter = "getAdsResponse", id = 3)
    private final String zzhn;

    @SafeParcelable.Constructor
    VastAdsRequest(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2) {
        this.zzhm = str;
        this.zzhn = str2;
    }

    public static class Builder {
        private String zzhm = null;
        private String zzhn = null;

        public Builder setAdTagUrl(String str) {
            this.zzhm = str;
            return this;
        }

        public Builder setAdsResponse(String str) {
            this.zzhn = str;
            return this;
        }

        public VastAdsRequest build() {
            return new VastAdsRequest(this.zzhm, this.zzhn);
        }
    }

    public String getAdTagUrl() {
        return this.zzhm;
    }

    public String getAdsResponse() {
        return this.zzhn;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAdTagUrl(), false);
        SafeParcelWriter.writeString(parcel, 3, getAdsResponse(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzhm, this.zzhn);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VastAdsRequest)) {
            return false;
        }
        VastAdsRequest vastAdsRequest = (VastAdsRequest) obj;
        if (!zzdc.zza(this.zzhm, vastAdsRequest.zzhm) || !zzdc.zza(this.zzhn, vastAdsRequest.zzhn)) {
            return false;
        }
        return true;
    }

    public static VastAdsRequest fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new VastAdsRequest(jSONObject.optString("adTagUrl", (String) null), jSONObject.optString("adsResponse", (String) null));
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.zzhm != null) {
                jSONObject.put("adTagUrl", this.zzhm);
            }
            if (this.zzhn != null) {
                jSONObject.put("adsResponse", this.zzhn);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
