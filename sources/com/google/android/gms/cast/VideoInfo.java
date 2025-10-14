package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "VideoInfoCreator")
@SafeParcelable.Reserved({1})
public final class VideoInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VideoInfo> CREATOR = new zzby();
    public static final int HDR_TYPE_DV = 3;
    public static final int HDR_TYPE_HDR = 4;
    public static final int HDR_TYPE_HDR10 = 2;
    public static final int HDR_TYPE_SDR = 1;
    public static final int HDR_TYPE_UNKNOWN = 0;
    @SafeParcelable.Field(getter = "getHeight", id = 3)
    private int height;
    @SafeParcelable.Field(getter = "getWidth", id = 2)
    private int width;
    @SafeParcelable.Field(getter = "getHdrType", id = 4)
    private int zzho;

    @SafeParcelable.Constructor
    VideoInfo(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3) {
        this.width = i;
        this.height = i2;
        this.zzho = i3;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getHdrType() {
        return this.zzho;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getWidth());
        SafeParcelWriter.writeInt(parcel, 3, getHeight());
        SafeParcelWriter.writeInt(parcel, 4, getHdrType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoInfo)) {
            return false;
        }
        VideoInfo videoInfo = (VideoInfo) obj;
        if (this.height == videoInfo.getHeight() && this.width == videoInfo.getWidth() && this.zzho == videoInfo.getHdrType()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.height), Integer.valueOf(this.width), Integer.valueOf(this.zzho));
    }

    static VideoInfo zzk(JSONObject jSONObject) {
        int i = 2;
        if (jSONObject == null) {
            return null;
        }
        try {
            String string = jSONObject.getString("hdrType");
            char c = 65535;
            switch (string.hashCode()) {
                case 3218:
                    if (string.equals("dv")) {
                        c = 0;
                        break;
                    }
                    break;
                case 103158:
                    if (string.equals("hdr")) {
                        c = 2;
                        break;
                    }
                    break;
                case 113729:
                    if (string.equals("sdr")) {
                        c = 3;
                        break;
                    }
                    break;
                case 99136405:
                    if (string.equals("hdr10")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    i = 3;
                    break;
                case 1:
                    break;
                case 2:
                    i = 4;
                    break;
                case 3:
                    i = 1;
                    break;
                default:
                    Log.d("VideoInfo", String.format(Locale.ROOT, "Unknown HDR type: %s", new Object[]{string}));
                    i = 0;
                    break;
            }
            return new VideoInfo(jSONObject.getInt("width"), jSONObject.getInt("height"), i);
        } catch (JSONException e) {
            Log.d("VideoInfo", String.format(Locale.ROOT, "Error while creating a VideoInfo instance from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }
}
