package com.google.android.gms.cast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.cast.zzdo;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaLiveSeekableRange {
    private static final zzdo zzbf = new zzdo("MediaLiveSeekableRange");
    private final long startTime;
    private final long zzdv;
    private final boolean zzdw;
    private final boolean zzdx;

    private MediaLiveSeekableRange(long j, long j2, boolean z, boolean z2) {
        this.startTime = Math.max(j, 0);
        this.zzdv = Math.max(j2, 0);
        this.zzdw = z;
        this.zzdx = z2;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.zzdv;
    }

    public boolean isMovingWindow() {
        return this.zzdw;
    }

    public boolean isLiveDone() {
        return this.zzdx;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.startTime), Long.valueOf(this.zzdv), Boolean.valueOf(this.zzdw), Boolean.valueOf(this.zzdx));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaLiveSeekableRange)) {
            return false;
        }
        MediaLiveSeekableRange mediaLiveSeekableRange = (MediaLiveSeekableRange) obj;
        if (this.startTime == mediaLiveSeekableRange.startTime && this.zzdv == mediaLiveSeekableRange.zzdv && this.zzdw == mediaLiveSeekableRange.zzdw && this.zzdx == mediaLiveSeekableRange.zzdx) {
            return true;
        }
        return false;
    }

    static MediaLiveSeekableRange zze(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        if (!jSONObject.has("start") || !jSONObject.has("end")) {
            return null;
        }
        try {
            return new MediaLiveSeekableRange((long) (jSONObject.getDouble("start") * 1000.0d), (long) (jSONObject.getDouble("end") * 1000.0d), jSONObject.optBoolean("isMovingWindow"), jSONObject.optBoolean("isLiveDone"));
        } catch (JSONException e) {
            zzdo zzdo = zzbf;
            String valueOf = String.valueOf(jSONObject);
            zzdo.e(new StringBuilder(String.valueOf(valueOf).length() + 43).append("Ignoring Malformed MediaLiveSeekableRange: ").append(valueOf).toString(), new Object[0]);
            return null;
        }
    }
}
