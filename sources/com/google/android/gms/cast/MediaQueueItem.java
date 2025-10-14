package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdc;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "MediaQueueItemCreator")
@SafeParcelable.Reserved({1})
public class MediaQueueItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaQueueItem> CREATOR = new zzaq();
    public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
    public static final int INVALID_ITEM_ID = 0;
    @SafeParcelable.Field(getter = "getAutoplay", id = 4)
    private boolean zzdp;
    @SafeParcelable.Field(getter = "getActiveTrackIds", id = 8)
    private long[] zzds;
    @SafeParcelable.Field(getter = "getMedia", id = 2)
    private MediaInfo zzes;
    @SafeParcelable.Field(getter = "getItemId", id = 3)
    private int zzet;
    @SafeParcelable.Field(getter = "getStartTime", id = 5)
    private double zzeu;
    @SafeParcelable.Field(getter = "getPlaybackDuration", id = 6)
    private double zzev;
    @SafeParcelable.Field(getter = "getPreloadTime", id = 7)
    private double zzew;
    @SafeParcelable.Field(id = 9)
    private String zzj;
    private JSONObject zzp;

    @SafeParcelable.Constructor
    MediaQueueItem(@SafeParcelable.Param(id = 2) MediaInfo mediaInfo, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) double d, @SafeParcelable.Param(id = 6) double d2, @SafeParcelable.Param(id = 7) double d3, @SafeParcelable.Param(id = 8) long[] jArr, @SafeParcelable.Param(id = 9) String str) {
        this.zzeu = Double.NaN;
        this.zzes = mediaInfo;
        this.zzet = i;
        this.zzdp = z;
        this.zzeu = d;
        this.zzev = d2;
        this.zzew = d3;
        this.zzds = jArr;
        this.zzj = str;
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
        private final MediaQueueItem zzex;

        public Builder(MediaInfo mediaInfo) throws IllegalArgumentException {
            this.zzex = new MediaQueueItem(mediaInfo);
        }

        public Builder(JSONObject jSONObject) throws JSONException {
            this.zzex = new MediaQueueItem(jSONObject);
        }

        public Builder(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
            this.zzex = new MediaQueueItem();
        }

        public Builder clearItemId() {
            this.zzex.zzd(0);
            return this;
        }

        public Builder setAutoplay(boolean z) {
            this.zzex.zze(z);
            return this;
        }

        public Builder setStartTime(double d) throws IllegalArgumentException {
            this.zzex.zzb(d);
            return this;
        }

        public Builder setPlaybackDuration(double d) {
            this.zzex.zzc(d);
            return this;
        }

        public Builder setPreloadTime(double d) throws IllegalArgumentException {
            this.zzex.zzd(d);
            return this;
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzex.zza(jArr);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzex.setCustomData(jSONObject);
            return this;
        }

        public MediaQueueItem build() {
            this.zzex.zzj();
            return this.zzex;
        }
    }

    private MediaQueueItem(MediaInfo mediaInfo) throws IllegalArgumentException {
        this(mediaInfo, 0, true, Double.NaN, Double.POSITIVE_INFINITY, 0.0d, (long[]) null, (String) null);
        if (mediaInfo == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
    }

    MediaQueueItem(JSONObject jSONObject) throws JSONException {
        this((MediaInfo) null, 0, true, Double.NaN, Double.POSITIVE_INFINITY, 0.0d, (long[]) null, (String) null);
        zzi(jSONObject);
    }

    private MediaQueueItem(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
        this(mediaQueueItem.getMedia(), mediaQueueItem.getItemId(), mediaQueueItem.getAutoplay(), mediaQueueItem.getStartTime(), mediaQueueItem.getPlaybackDuration(), mediaQueueItem.getPreloadTime(), mediaQueueItem.getActiveTrackIds(), (String) null);
        if (this.zzes == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
        this.zzp = mediaQueueItem.getCustomData();
    }

    public final boolean zzi(JSONObject jSONObject) throws JSONException {
        boolean z;
        boolean z2;
        boolean z3;
        int i;
        boolean z4 = false;
        if (jSONObject.has("media")) {
            this.zzes = new MediaInfo(jSONObject.getJSONObject("media"));
            z = true;
        } else {
            z = false;
        }
        if (jSONObject.has("itemId") && this.zzet != (i = jSONObject.getInt("itemId"))) {
            this.zzet = i;
            z = true;
        }
        if (jSONObject.has("autoplay") && this.zzdp != (z3 = jSONObject.getBoolean("autoplay"))) {
            this.zzdp = z3;
            z = true;
        }
        double optDouble = jSONObject.optDouble("startTime");
        if (Double.isNaN(optDouble) != Double.isNaN(this.zzeu)) {
            z2 = true;
        } else if (Double.isNaN(optDouble) || Math.abs(optDouble - this.zzeu) <= 1.0E-7d) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2) {
            this.zzeu = optDouble;
            z = true;
        }
        if (jSONObject.has("playbackDuration")) {
            double d = jSONObject.getDouble("playbackDuration");
            if (Math.abs(d - this.zzev) > 1.0E-7d) {
                this.zzev = d;
                z = true;
            }
        }
        if (jSONObject.has("preloadTime")) {
            double d2 = jSONObject.getDouble("preloadTime");
            if (Math.abs(d2 - this.zzew) > 1.0E-7d) {
                this.zzew = d2;
                z = true;
            }
        }
        long[] jArr = null;
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            jArr = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr[i2] = jSONArray.getLong(i2);
            }
            if (this.zzds == null) {
                z4 = true;
            } else if (this.zzds.length != length) {
                z4 = true;
            } else {
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    } else if (this.zzds[i3] != jArr[i3]) {
                        z4 = true;
                        break;
                    } else {
                        i3++;
                    }
                }
            }
        }
        if (z4) {
            this.zzds = jArr;
            z = true;
        }
        if (!jSONObject.has("customData")) {
            return z;
        }
        this.zzp = jSONObject.getJSONObject("customData");
        return true;
    }

    public MediaInfo getMedia() {
        return this.zzes;
    }

    public int getItemId() {
        return this.zzet;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(int i) {
        this.zzet = 0;
    }

    public boolean getAutoplay() {
        return this.zzdp;
    }

    /* access modifiers changed from: package-private */
    public final void zze(boolean z) {
        this.zzdp = z;
    }

    public double getStartTime() {
        return this.zzeu;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d >= 0.0d) {
            this.zzeu = d;
            return;
        }
        throw new IllegalArgumentException("startTime cannot be negative.");
    }

    public double getPlaybackDuration() {
        return this.zzev;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(double d) throws IllegalArgumentException {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        }
        this.zzev = d;
    }

    public double getPreloadTime() {
        return this.zzew;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or NaN.");
        }
        this.zzew = d;
    }

    public long[] getActiveTrackIds() {
        return this.zzds;
    }

    /* access modifiers changed from: package-private */
    public final void zza(long[] jArr) {
        this.zzds = jArr;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public final void zzj() throws IllegalArgumentException {
        if (this.zzes == null) {
            throw new IllegalArgumentException("media cannot be null.");
        } else if (!Double.isNaN(this.zzeu) && this.zzeu < 0.0d) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        } else if (Double.isNaN(this.zzev)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        } else if (Double.isNaN(this.zzew) || this.zzew < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("media", this.zzes.toJson());
            if (this.zzet != 0) {
                jSONObject.put("itemId", this.zzet);
            }
            jSONObject.put("autoplay", this.zzdp);
            if (!Double.isNaN(this.zzeu)) {
                jSONObject.put("startTime", this.zzeu);
            }
            if (this.zzev != Double.POSITIVE_INFINITY) {
                jSONObject.put("playbackDuration", this.zzev);
            }
            jSONObject.put("preloadTime", this.zzew);
            if (this.zzds != null) {
                JSONArray jSONArray = new JSONArray();
                for (long put : this.zzds) {
                    jSONArray.put(put);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueItem)) {
            return false;
        }
        MediaQueueItem mediaQueueItem = (MediaQueueItem) obj;
        if ((this.zzp == null) != (mediaQueueItem.zzp == null)) {
            return false;
        }
        if ((this.zzp != null && mediaQueueItem.zzp != null && !JsonUtils.areJsonValuesEquivalent(this.zzp, mediaQueueItem.zzp)) || !zzdc.zza(this.zzes, mediaQueueItem.zzes) || this.zzet != mediaQueueItem.zzet || this.zzdp != mediaQueueItem.zzdp) {
            return false;
        }
        if (((!Double.isNaN(this.zzeu) || !Double.isNaN(mediaQueueItem.zzeu)) && this.zzeu != mediaQueueItem.zzeu) || this.zzev != mediaQueueItem.zzev || this.zzew != mediaQueueItem.zzew || !Arrays.equals(this.zzds, mediaQueueItem.zzds)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzes, Integer.valueOf(this.zzet), Boolean.valueOf(this.zzdp), Double.valueOf(this.zzeu), Double.valueOf(this.zzev), Double.valueOf(this.zzew), Integer.valueOf(Arrays.hashCode(this.zzds)), String.valueOf(this.zzp));
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMedia(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getItemId());
        SafeParcelWriter.writeBoolean(parcel, 4, getAutoplay());
        SafeParcelWriter.writeDouble(parcel, 5, getStartTime());
        SafeParcelWriter.writeDouble(parcel, 6, getPlaybackDuration());
        SafeParcelWriter.writeDouble(parcel, 7, getPreloadTime());
        SafeParcelWriter.writeLongArray(parcel, 8, getActiveTrackIds(), false);
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
