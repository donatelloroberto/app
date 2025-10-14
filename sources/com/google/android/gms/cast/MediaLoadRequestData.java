package com.google.android.gms.cast;

import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;
import org.json.JSONObject;

public class MediaLoadRequestData {
    public static final double PLAYBACK_RATE_MAX = 2.0d;
    public static final double PLAYBACK_RATE_MIN = 0.5d;
    private MediaInfo zzdo;
    private double zzdr;
    private long[] zzds;
    private String zzdt;
    private String zzdu;
    private MediaQueueData zzdy;
    private Boolean zzdz;
    private long zzea;
    private JSONObject zzp;

    private MediaLoadRequestData(MediaInfo mediaInfo, MediaQueueData mediaQueueData, Boolean bool, long j, double d, long[] jArr, JSONObject jSONObject, String str, String str2) {
        this.zzdo = mediaInfo;
        this.zzdy = mediaQueueData;
        this.zzdz = bool;
        this.zzea = j;
        this.zzdr = d;
        this.zzds = jArr;
        this.zzp = jSONObject;
        this.zzdt = str;
        this.zzdu = str2;
    }

    public static class Builder {
        private MediaInfo zzdo;
        private double zzdr = 1.0d;
        private long[] zzds = null;
        private String zzdt = null;
        private String zzdu = null;
        private MediaQueueData zzdy;
        private Boolean zzdz = true;
        private long zzea = -1;
        private JSONObject zzp = null;

        public Builder setMediaInfo(MediaInfo mediaInfo) {
            this.zzdo = mediaInfo;
            return this;
        }

        public Builder setQueueData(MediaQueueData mediaQueueData) {
            this.zzdy = mediaQueueData;
            return this;
        }

        public Builder setAutoplay(Boolean bool) {
            this.zzdz = bool;
            return this;
        }

        public Builder setCurrentTime(long j) {
            this.zzea = j;
            return this;
        }

        public Builder setPlaybackRate(double d) {
            if (Double.compare(d, 2.0d) > 0 || Double.compare(d, 0.5d) < 0) {
                throw new IllegalArgumentException("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
            }
            this.zzdr = d;
            return this;
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzds = jArr;
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzp = jSONObject;
            return this;
        }

        public Builder setCredentials(String str) {
            this.zzdt = str;
            return this;
        }

        public Builder setCredentialsType(String str) {
            this.zzdu = str;
            return this;
        }

        public MediaLoadRequestData build() {
            return new MediaLoadRequestData(this.zzdo, this.zzdy, this.zzdz, this.zzea, this.zzdr, this.zzds, this.zzp, this.zzdt, this.zzdu);
        }
    }

    public MediaInfo getMediaInfo() {
        return this.zzdo;
    }

    public MediaQueueData getQueueData() {
        return this.zzdy;
    }

    public Boolean getAutoplay() {
        return this.zzdz;
    }

    public long getCurrentTime() {
        return this.zzea;
    }

    public double getPlaybackRate() {
        return this.zzdr;
    }

    public long[] getActiveTrackIds() {
        return this.zzds;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public String getCredentials() {
        return this.zzdt;
    }

    public String getCredentialsType() {
        return this.zzdu;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaLoadRequestData)) {
            return false;
        }
        MediaLoadRequestData mediaLoadRequestData = (MediaLoadRequestData) obj;
        if (!Objects.equal(this.zzdo, mediaLoadRequestData.zzdo) || !Objects.equal(this.zzdy, mediaLoadRequestData.zzdy) || !Objects.equal(this.zzdz, mediaLoadRequestData.zzdz) || this.zzea != mediaLoadRequestData.zzea || this.zzdr != mediaLoadRequestData.zzdr || !Arrays.equals(this.zzds, mediaLoadRequestData.zzds) || !Objects.equal(this.zzp, mediaLoadRequestData.zzp) || !Objects.equal(this.zzdt, mediaLoadRequestData.zzdt) || !Objects.equal(this.zzdu, mediaLoadRequestData.zzdu)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzdo, this.zzdy, this.zzdz, Long.valueOf(this.zzea), Double.valueOf(this.zzdr), this.zzds, this.zzp, this.zzdt, this.zzdu);
    }
}
