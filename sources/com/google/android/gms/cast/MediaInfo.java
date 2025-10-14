package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdc;
import com.google.android.gms.internal.cast.zzdl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "MediaInfoCreator")
@SafeParcelable.Reserved({1})
public class MediaInfo extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaInfo> CREATOR = new zzaj();
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    public static final long UNKNOWN_DURATION = -1;
    public static final long UNKNOWN_START_ABSOLUTE_TIME = -1;
    @SafeParcelable.Field(getter = "getStreamType", id = 3)
    private int streamType;
    @SafeParcelable.Field(getter = "getContentType", id = 4)
    private String zzde;
    @SafeParcelable.Field(getter = "getMetadata", id = 5)
    private MediaMetadata zzdf;
    @SafeParcelable.Field(getter = "getStreamDuration", id = 6)
    private long zzdg;
    @SafeParcelable.Field(getter = "getMediaTracks", id = 7)
    private List<MediaTrack> zzdh;
    @SafeParcelable.Field(getter = "getTextTrackStyle", id = 8)
    private TextTrackStyle zzdi;
    @SafeParcelable.Field(getter = "getAdBreaks", id = 10)
    private List<AdBreakInfo> zzdj;
    @SafeParcelable.Field(getter = "getAdBreakClips", id = 11)
    private List<AdBreakClipInfo> zzdk;
    @SafeParcelable.Field(getter = "getEntity", id = 12)
    private String zzdl;
    @SafeParcelable.Field(getter = "getVmapAdsRequest", id = 13)
    private VastAdsRequest zzdm;
    @SafeParcelable.Field(getter = "getStartAbsoluteTime", id = 14)
    private long zzdn;
    @SafeParcelable.Field(id = 9)
    private String zzj;
    @SafeParcelable.Field(getter = "getContentId", id = 2)
    private final String zzk;
    private JSONObject zzp;

    @SafeParcelable.Constructor
    MediaInfo(@SafeParcelable.Param(id = 2) @NonNull String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) MediaMetadata mediaMetadata, @SafeParcelable.Param(id = 6) long j, @SafeParcelable.Param(id = 7) List<MediaTrack> list, @SafeParcelable.Param(id = 8) TextTrackStyle textTrackStyle, @SafeParcelable.Param(id = 9) String str3, @SafeParcelable.Param(id = 10) List<AdBreakInfo> list2, @SafeParcelable.Param(id = 11) List<AdBreakClipInfo> list3, @SafeParcelable.Param(id = 12) String str4, @SafeParcelable.Param(id = 13) VastAdsRequest vastAdsRequest, @SafeParcelable.Param(id = 14) long j2) {
        this.zzk = str;
        this.streamType = i;
        this.zzde = str2;
        this.zzdf = mediaMetadata;
        this.zzdg = j;
        this.zzdh = list;
        this.zzdi = textTrackStyle;
        this.zzj = str3;
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
        this.zzdj = list2;
        this.zzdk = list3;
        this.zzdl = str4;
        this.zzdm = vastAdsRequest;
        this.zzdn = j2;
    }

    @VisibleForTesting
    public static class Builder {
        private final MediaInfo zzdo;

        public Builder(String str) throws IllegalArgumentException {
            this.zzdo = new MediaInfo(str);
        }

        public Builder(String str, String str2) throws IllegalArgumentException {
            this.zzdo = new MediaInfo(str, str2);
        }

        public Builder setStreamType(int i) throws IllegalArgumentException {
            this.zzdo.setStreamType(i);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzdo.setContentType(str);
            return this;
        }

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            this.zzdo.zza(mediaMetadata);
            return this;
        }

        public Builder setStreamDuration(long j) throws IllegalArgumentException {
            this.zzdo.zza(j);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzdo.setCustomData(jSONObject);
            return this;
        }

        public Builder setMediaTracks(List<MediaTrack> list) {
            this.zzdo.zza(list);
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.zzdo.setTextTrackStyle(textTrackStyle);
            return this;
        }

        public Builder setEntity(String str) {
            this.zzdo.zzd(str);
            return this;
        }

        public Builder setVmapAdsRequest(VastAdsRequest vastAdsRequest) {
            this.zzdo.zza(vastAdsRequest);
            return this;
        }

        public Builder setAdBreaks(List<AdBreakInfo> list) {
            this.zzdo.zzb(list);
            return this;
        }

        public Builder setAdBreakClips(List<AdBreakClipInfo> list) {
            this.zzdo.zzc(list);
            return this;
        }

        public MediaInfo build() {
            return this.zzdo;
        }
    }

    MediaInfo(String str) throws IllegalArgumentException {
        this(str, -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, (String) null, (VastAdsRequest) null, -1);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(String str, String str2) throws IllegalArgumentException {
        this(str, -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, str2, (VastAdsRequest) null, -1);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(JSONObject jSONObject) throws JSONException {
        this(jSONObject.getString("contentId"), -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, (String) null, (VastAdsRequest) null, -1);
        String string = jSONObject.getString("streamType");
        if ("NONE".equals(string)) {
            this.streamType = 0;
        } else if ("BUFFERED".equals(string)) {
            this.streamType = 1;
        } else if ("LIVE".equals(string)) {
            this.streamType = 2;
        } else {
            this.streamType = -1;
        }
        this.zzde = jSONObject.optString("contentType", (String) null);
        if (jSONObject.has("metadata")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("metadata");
            this.zzdf = new MediaMetadata(jSONObject2.getInt("metadataType"));
            this.zzdf.zzf(jSONObject2);
        }
        this.zzdg = -1;
        if (jSONObject.has("duration") && !jSONObject.isNull("duration")) {
            double optDouble = jSONObject.optDouble("duration", 0.0d);
            if (!Double.isNaN(optDouble) && !Double.isInfinite(optDouble)) {
                this.zzdg = (long) (optDouble * 1000.0d);
            }
        }
        if (jSONObject.has("tracks")) {
            this.zzdh = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tracks");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.zzdh.add(new MediaTrack(jSONArray.getJSONObject(i)));
            }
        } else {
            this.zzdh = null;
        }
        if (jSONObject.has("textTrackStyle")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("textTrackStyle");
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            textTrackStyle.zzf(jSONObject3);
            this.zzdi = textTrackStyle;
        } else {
            this.zzdi = null;
        }
        zzd(jSONObject);
        this.zzp = jSONObject.optJSONObject("customData");
        if (jSONObject.has("entity")) {
            this.zzdl = jSONObject.getString("entity");
        }
        this.zzdm = VastAdsRequest.fromJson(jSONObject.optJSONObject("vmapAdsRequest"));
        if (zzdl.zzaaa && jSONObject.has("startAbsoluteTime") && !jSONObject.isNull("startAbsoluteTime")) {
            double optDouble2 = jSONObject.optDouble("startAbsoluteTime");
            if (!Double.isNaN(optDouble2) && !Double.isInfinite(optDouble2) && optDouble2 >= 0.0d) {
                this.zzdn = (long) (optDouble2 * 1000.0d);
            }
        }
    }

    public String getContentId() {
        return this.zzk;
    }

    /* access modifiers changed from: package-private */
    public final void setStreamType(int i) throws IllegalArgumentException {
        if (i < -1 || i > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.streamType = i;
    }

    public int getStreamType() {
        return this.streamType;
    }

    /* access modifiers changed from: package-private */
    public final void setContentType(String str) {
        this.zzde = str;
    }

    public String getContentType() {
        return this.zzde;
    }

    /* access modifiers changed from: package-private */
    public final void zza(MediaMetadata mediaMetadata) {
        this.zzdf = mediaMetadata;
    }

    public MediaMetadata getMetadata() {
        return this.zzdf;
    }

    @VisibleForTesting
    public final void zza(long j) throws IllegalArgumentException {
        if (j >= 0 || j == -1) {
            this.zzdg = j;
            return;
        }
        throw new IllegalArgumentException("Invalid stream duration");
    }

    public long getStreamDuration() {
        return this.zzdg;
    }

    /* access modifiers changed from: package-private */
    public final void zza(List<MediaTrack> list) {
        this.zzdh = list;
    }

    public List<MediaTrack> getMediaTracks() {
        return this.zzdh;
    }

    public void setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.zzdi = textTrackStyle;
    }

    public TextTrackStyle getTextTrackStyle() {
        return this.zzdi;
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public List<AdBreakInfo> getAdBreaks() {
        if (this.zzdj == null) {
            return null;
        }
        return Collections.unmodifiableList(this.zzdj);
    }

    public List<AdBreakClipInfo> getAdBreakClips() {
        if (this.zzdk == null) {
            return null;
        }
        return Collections.unmodifiableList(this.zzdk);
    }

    @VisibleForTesting
    public final void zzb(List<AdBreakInfo> list) {
        this.zzdj = list;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzc(List<AdBreakClipInfo> list) {
        this.zzdk = list;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject.has("breaks")) {
            JSONArray jSONArray = jSONObject.getJSONArray("breaks");
            this.zzdj = new ArrayList(jSONArray.length());
            int i2 = 0;
            while (true) {
                if (i2 < jSONArray.length()) {
                    AdBreakInfo zzb = AdBreakInfo.zzb(jSONArray.getJSONObject(i2));
                    if (zzb == null) {
                        this.zzdj.clear();
                        break;
                    } else {
                        this.zzdj.add(zzb);
                        i2++;
                    }
                } else {
                    break;
                }
            }
        }
        if (jSONObject.has("breakClips")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("breakClips");
            this.zzdk = new ArrayList(jSONArray2.length());
            while (i < jSONArray2.length()) {
                AdBreakClipInfo zza = AdBreakClipInfo.zza(jSONArray2.getJSONObject(i));
                if (zza != null) {
                    this.zzdk.add(zza);
                    i++;
                } else {
                    this.zzdk.clear();
                    return;
                }
            }
        }
    }

    public String getEntity() {
        return this.zzdl;
    }

    @VisibleForTesting
    public final void zzd(String str) {
        this.zzdl = str;
    }

    public VastAdsRequest getVmapAdsRequest() {
        return this.zzdm;
    }

    @VisibleForTesting
    public final void zza(VastAdsRequest vastAdsRequest) {
        this.zzdm = vastAdsRequest;
    }

    public long getStartAbsoluteTime() {
        return this.zzdn;
    }

    public final JSONObject toJson() {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", this.zzk);
            switch (this.streamType) {
                case 1:
                    str = "BUFFERED";
                    break;
                case 2:
                    str = "LIVE";
                    break;
                default:
                    str = "NONE";
                    break;
            }
            jSONObject.put("streamType", str);
            if (this.zzde != null) {
                jSONObject.put("contentType", this.zzde);
            }
            if (this.zzdf != null) {
                jSONObject.put("metadata", this.zzdf.toJson());
            }
            if (this.zzdg <= -1) {
                jSONObject.put("duration", JSONObject.NULL);
            } else {
                jSONObject.put("duration", ((double) this.zzdg) / 1000.0d);
            }
            if (this.zzdh != null) {
                JSONArray jSONArray = new JSONArray();
                for (MediaTrack json : this.zzdh) {
                    jSONArray.put(json.toJson());
                }
                jSONObject.put("tracks", jSONArray);
            }
            if (this.zzdi != null) {
                jSONObject.put("textTrackStyle", this.zzdi.toJson());
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
            if (this.zzdl != null) {
                jSONObject.put("entity", this.zzdl);
            }
            if (this.zzdj != null) {
                JSONArray jSONArray2 = new JSONArray();
                for (AdBreakInfo json2 : this.zzdj) {
                    jSONArray2.put(json2.toJson());
                }
                jSONObject.put("breaks", jSONArray2);
            }
            if (this.zzdk != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (AdBreakClipInfo json3 : this.zzdk) {
                    jSONArray3.put(json3.toJson());
                }
                jSONObject.put("breakClips", jSONArray3);
            }
            if (this.zzdm != null) {
                jSONObject.put("vmapAdsRequest", this.zzdm.toJson());
            }
            if (this.zzdn != -1) {
                jSONObject.put("startAbsoluteTime", ((double) this.zzdn) / 1000.0d);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        if ((this.zzp == null) != (mediaInfo.zzp == null)) {
            return false;
        }
        if ((this.zzp == null || mediaInfo.zzp == null || JsonUtils.areJsonValuesEquivalent(this.zzp, mediaInfo.zzp)) && zzdc.zza(this.zzk, mediaInfo.zzk) && this.streamType == mediaInfo.streamType && zzdc.zza(this.zzde, mediaInfo.zzde) && zzdc.zza(this.zzdf, mediaInfo.zzdf) && this.zzdg == mediaInfo.zzdg && zzdc.zza(this.zzdh, mediaInfo.zzdh) && zzdc.zza(this.zzdi, mediaInfo.zzdi) && zzdc.zza(this.zzdj, mediaInfo.zzdj) && zzdc.zza(this.zzdk, mediaInfo.zzdk) && zzdc.zza(this.zzdl, mediaInfo.zzdl) && zzdc.zza(this.zzdm, mediaInfo.zzdm) && this.zzdn == mediaInfo.zzdn) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzk, Integer.valueOf(this.streamType), this.zzde, this.zzdf, Long.valueOf(this.zzdg), String.valueOf(this.zzp), this.zzdh, this.zzdi, this.zzdj, this.zzdk, this.zzdl, this.zzdm, Long.valueOf(this.zzdn));
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getContentId(), false);
        SafeParcelWriter.writeInt(parcel, 3, getStreamType());
        SafeParcelWriter.writeString(parcel, 4, getContentType(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getMetadata(), i, false);
        SafeParcelWriter.writeLong(parcel, 6, getStreamDuration());
        SafeParcelWriter.writeTypedList(parcel, 7, getMediaTracks(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, getTextTrackStyle(), i, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.writeTypedList(parcel, 10, getAdBreaks(), false);
        SafeParcelWriter.writeTypedList(parcel, 11, getAdBreakClips(), false);
        SafeParcelWriter.writeString(parcel, 12, getEntity(), false);
        SafeParcelWriter.writeParcelable(parcel, 13, getVmapAdsRequest(), i, false);
        SafeParcelWriter.writeLong(parcel, 14, getStartAbsoluteTime());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
