package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdc;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "MediaTrackCreator")
@SafeParcelable.Reserved({1})
public final class MediaTrack extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaTrack> CREATOR = new zzat();
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    @SafeParcelable.Field(getter = "getId", id = 2)
    private long id;
    @SafeParcelable.Field(getter = "getName", id = 6)
    private String name;
    @SafeParcelable.Field(getter = "getType", id = 3)
    private int type;
    @SafeParcelable.Field(getter = "getLanguage", id = 7)
    private String zzdd;
    @SafeParcelable.Field(getter = "getContentType", id = 5)
    private String zzde;
    @SafeParcelable.Field(getter = "getSubtype", id = 8)
    private int zzft;
    @SafeParcelable.Field(id = 9)
    private String zzj;
    @SafeParcelable.Field(getter = "getContentId", id = 4)
    private String zzk;
    private JSONObject zzp;

    @SafeParcelable.Constructor
    MediaTrack(@SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) String str2, @SafeParcelable.Param(id = 6) String str3, @SafeParcelable.Param(id = 7) String str4, @SafeParcelable.Param(id = 8) int i2, @SafeParcelable.Param(id = 9) String str5) {
        this.id = j;
        this.type = i;
        this.zzk = str;
        this.zzde = str2;
        this.name = str3;
        this.zzdd = str4;
        this.zzft = i2;
        this.zzj = str5;
        if (this.zzj != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException e) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
    }

    @VisibleForTesting
    public static class Builder {
        private final MediaTrack zzfs;

        public Builder(long j, int i) throws IllegalArgumentException {
            this.zzfs = new MediaTrack(j, i);
        }

        public Builder setContentId(String str) {
            this.zzfs.setContentId(str);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzfs.setContentType(str);
            return this;
        }

        public Builder setName(String str) {
            this.zzfs.setName(str);
            return this;
        }

        public Builder setLanguage(String str) {
            this.zzfs.setLanguage(str);
            return this;
        }

        public Builder setLanguage(Locale locale) {
            this.zzfs.setLanguage(zzdc.zza(locale));
            return this;
        }

        public Builder setSubtype(int i) throws IllegalArgumentException {
            this.zzfs.zze(i);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzfs.setCustomData(jSONObject);
            return this;
        }

        public MediaTrack build() {
            return this.zzfs;
        }
    }

    MediaTrack(JSONObject jSONObject) throws JSONException {
        this(0, 0, (String) null, (String) null, (String) null, (String) null, -1, (String) null);
        this.id = jSONObject.getLong("trackId");
        String string = jSONObject.getString(AppMeasurement.Param.TYPE);
        if ("TEXT".equals(string)) {
            this.type = 1;
        } else if ("AUDIO".equals(string)) {
            this.type = 2;
        } else if ("VIDEO".equals(string)) {
            this.type = 3;
        } else {
            String valueOf = String.valueOf(string);
            throw new JSONException(valueOf.length() != 0 ? "invalid type: ".concat(valueOf) : new String("invalid type: "));
        }
        this.zzk = jSONObject.optString("trackContentId", (String) null);
        this.zzde = jSONObject.optString("trackContentType", (String) null);
        this.name = jSONObject.optString("name", (String) null);
        this.zzdd = jSONObject.optString("language", (String) null);
        if (jSONObject.has("subtype")) {
            String string2 = jSONObject.getString("subtype");
            if ("SUBTITLES".equals(string2)) {
                this.zzft = 1;
            } else if ("CAPTIONS".equals(string2)) {
                this.zzft = 2;
            } else if ("DESCRIPTIONS".equals(string2)) {
                this.zzft = 3;
            } else if ("CHAPTERS".equals(string2)) {
                this.zzft = 4;
            } else if ("METADATA".equals(string2)) {
                this.zzft = 5;
            } else {
                this.zzft = -1;
            }
        } else {
            this.zzft = 0;
        }
        this.zzp = jSONObject.optJSONObject("customData");
    }

    MediaTrack(long j, int i) throws IllegalArgumentException {
        this(0, 0, (String) null, (String) null, (String) null, (String) null, -1, (String) null);
        this.id = j;
        if (i <= 0 || i > 3) {
            throw new IllegalArgumentException(new StringBuilder(24).append("invalid type ").append(i).toString());
        }
        this.type = i;
    }

    public final long getId() {
        return this.id;
    }

    public final int getType() {
        return this.type;
    }

    public final String getContentId() {
        return this.zzk;
    }

    public final void setContentId(String str) {
        this.zzk = str;
    }

    public final String getContentType() {
        return this.zzde;
    }

    public final void setContentType(String str) {
        this.zzde = str;
    }

    public final String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public final void setName(String str) {
        this.name = str;
    }

    public final String getLanguage() {
        return this.zzdd;
    }

    /* access modifiers changed from: package-private */
    public final void setLanguage(String str) {
        this.zzdd = str;
    }

    public final int getSubtype() {
        return this.zzft;
    }

    /* access modifiers changed from: package-private */
    public final void zze(int i) throws IllegalArgumentException {
        if (i < 0 || i > 5) {
            throw new IllegalArgumentException(new StringBuilder(27).append("invalid subtype ").append(i).toString());
        } else if (i == 0 || this.type == 1) {
            this.zzft = i;
        } else {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
        }
    }

    public final JSONObject getCustomData() {
        return this.zzp;
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackId", this.id);
            switch (this.type) {
                case 1:
                    jSONObject.put(AppMeasurement.Param.TYPE, "TEXT");
                    break;
                case 2:
                    jSONObject.put(AppMeasurement.Param.TYPE, "AUDIO");
                    break;
                case 3:
                    jSONObject.put(AppMeasurement.Param.TYPE, "VIDEO");
                    break;
            }
            if (this.zzk != null) {
                jSONObject.put("trackContentId", this.zzk);
            }
            if (this.zzde != null) {
                jSONObject.put("trackContentType", this.zzde);
            }
            if (this.name != null) {
                jSONObject.put("name", this.name);
            }
            if (!TextUtils.isEmpty(this.zzdd)) {
                jSONObject.put("language", this.zzdd);
            }
            switch (this.zzft) {
                case 1:
                    jSONObject.put("subtype", "SUBTITLES");
                    break;
                case 2:
                    jSONObject.put("subtype", "CAPTIONS");
                    break;
                case 3:
                    jSONObject.put("subtype", "DESCRIPTIONS");
                    break;
                case 4:
                    jSONObject.put("subtype", "CHAPTERS");
                    break;
                case 5:
                    jSONObject.put("subtype", "METADATA");
                    break;
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) obj;
        if ((this.zzp == null) != (mediaTrack.zzp == null)) {
            return false;
        }
        if ((this.zzp == null || mediaTrack.zzp == null || JsonUtils.areJsonValuesEquivalent(this.zzp, mediaTrack.zzp)) && this.id == mediaTrack.id && this.type == mediaTrack.type && zzdc.zza(this.zzk, mediaTrack.zzk) && zzdc.zza(this.zzde, mediaTrack.zzde) && zzdc.zza(this.name, mediaTrack.name) && zzdc.zza(this.zzdd, mediaTrack.zzdd) && this.zzft == mediaTrack.zzft) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.id), Integer.valueOf(this.type), this.zzk, this.zzde, this.name, this.zzdd, Integer.valueOf(this.zzft), String.valueOf(this.zzp));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getId());
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeString(parcel, 4, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 5, getContentType(), false);
        SafeParcelWriter.writeString(parcel, 6, getName(), false);
        SafeParcelWriter.writeString(parcel, 7, getLanguage(), false);
        SafeParcelWriter.writeInt(parcel, 8, getSubtype());
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
