package com.google.android.gms.cast;

import com.google.android.gms.common.internal.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONObject;

public class MediaSeekOptions {
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    private final long zzey;
    private final int zzez;
    private final boolean zzfa;
    private final JSONObject zzp;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResumeState {
    }

    public long getPosition() {
        return this.zzey;
    }

    public static class Builder {
        private long zzey;
        private int zzez = 0;
        private boolean zzfa;
        private JSONObject zzp;

        public Builder setPosition(long j) {
            this.zzey = j;
            return this;
        }

        public Builder setResumeState(int i) {
            this.zzez = i;
            return this;
        }

        public Builder setIsSeekToInfinite(boolean z) {
            this.zzfa = z;
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzp = jSONObject;
            return this;
        }

        public MediaSeekOptions build() {
            return new MediaSeekOptions(this.zzey, this.zzez, this.zzfa, this.zzp);
        }
    }

    public int getResumeState() {
        return this.zzez;
    }

    public boolean isSeekToInfinite() {
        return this.zzfa;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSeekOptions)) {
            return false;
        }
        MediaSeekOptions mediaSeekOptions = (MediaSeekOptions) obj;
        if (this.zzey == mediaSeekOptions.zzey && this.zzez == mediaSeekOptions.zzez && this.zzfa == mediaSeekOptions.zzfa && Objects.equal(this.zzp, mediaSeekOptions.zzp)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzey), Integer.valueOf(this.zzez), Boolean.valueOf(this.zzfa), this.zzp);
    }

    private MediaSeekOptions(long j, int i, boolean z, JSONObject jSONObject) {
        this.zzey = j;
        this.zzez = i;
        this.zzfa = z;
        this.zzp = jSONObject;
    }
}
