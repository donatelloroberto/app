package com.google.android.gms.cast;

import org.json.JSONObject;

public class MediaLoadOptions {
    public static final double PLAYBACK_RATE_MAX = 2.0d;
    public static final double PLAYBACK_RATE_MIN = 0.5d;
    private boolean zzdp;
    private long zzdq;
    private double zzdr;
    private long[] zzds;
    private String zzdt;
    private String zzdu;
    private JSONObject zzp;

    private MediaLoadOptions(boolean z, long j, double d, long[] jArr, JSONObject jSONObject, String str, String str2) {
        this.zzdp = z;
        this.zzdq = j;
        this.zzdr = d;
        this.zzds = jArr;
        this.zzp = jSONObject;
        this.zzdt = str;
        this.zzdu = str2;
    }

    public static class Builder {
        private boolean zzdp = true;
        private long zzdq = -1;
        private double zzdr = 1.0d;
        private long[] zzds = null;
        private String zzdt = null;
        private String zzdu = null;
        private JSONObject zzp = null;

        public Builder setAutoplay(boolean z) {
            this.zzdp = z;
            return this;
        }

        public Builder setPlayPosition(long j) {
            this.zzdq = j;
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

        public MediaLoadOptions build() {
            return new MediaLoadOptions(this.zzdp, this.zzdq, this.zzdr, this.zzds, this.zzp, this.zzdt, this.zzdu);
        }
    }

    public boolean getAutoplay() {
        return this.zzdp;
    }

    public long getPlayPosition() {
        return this.zzdq;
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
}
