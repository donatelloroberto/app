package com.google.android.gms.cast;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.cast.zzdy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaQueueContainerMetadata {
    public static final int MEDIA_QUEUE_CONTAINER_TYPE_AUDIO_BOOK = 1;
    public static final int MEDIA_QUEUE_CONTAINER_TYPE_GENERIC = 0;
    private int zzej;
    private List<MediaMetadata> zzek;
    private List<WebImage> zzel;
    private double zzem;
    private String zzf;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQueueContainerType {
    }

    private MediaQueueContainerMetadata() {
        clear();
    }

    public static class Builder {
        private final MediaQueueContainerMetadata zzen = new MediaQueueContainerMetadata();

        public Builder setContainerType(int i) {
            this.zzen.zza(i);
            return this;
        }

        public Builder setTitle(@Nullable String str) {
            this.zzen.setTitle(str);
            return this;
        }

        public Builder setSections(@Nullable List<MediaMetadata> list) {
            this.zzen.zzd(list);
            return this;
        }

        public Builder setContainerImages(@Nullable List<WebImage> list) {
            this.zzen.zze(list);
            return this;
        }

        public Builder setContainerDuration(double d) {
            this.zzen.zza(d);
            return this;
        }

        public MediaQueueContainerMetadata build() {
            return new MediaQueueContainerMetadata();
        }

        public final Builder zzg(JSONObject jSONObject) {
            this.zzen.zzf(jSONObject);
            return this;
        }
    }

    private MediaQueueContainerMetadata(MediaQueueContainerMetadata mediaQueueContainerMetadata) {
        this.zzej = mediaQueueContainerMetadata.zzej;
        this.zzf = mediaQueueContainerMetadata.zzf;
        this.zzek = mediaQueueContainerMetadata.zzek;
        this.zzel = mediaQueueContainerMetadata.zzel;
        this.zzem = mediaQueueContainerMetadata.zzem;
    }

    private final void clear() {
        this.zzej = 0;
        this.zzf = null;
        this.zzek = null;
        this.zzel = null;
        this.zzem = 0.0d;
    }

    public int getContainerType() {
        return this.zzej;
    }

    /* access modifiers changed from: private */
    public final void zza(int i) {
        this.zzej = i;
    }

    @Nullable
    public String getTitle() {
        return this.zzf;
    }

    /* access modifiers changed from: private */
    public final void setTitle(@Nullable String str) {
        this.zzf = str;
    }

    @Nullable
    public List<MediaMetadata> getSections() {
        if (this.zzek == null) {
            return null;
        }
        return Collections.unmodifiableList(this.zzek);
    }

    /* access modifiers changed from: package-private */
    public final void zzd(@Nullable List<MediaMetadata> list) {
        this.zzek = list == null ? null : new ArrayList(list);
    }

    @Nullable
    public List<WebImage> getContainerImages() {
        if (this.zzel == null) {
            return null;
        }
        return Collections.unmodifiableList(this.zzel);
    }

    /* access modifiers changed from: private */
    public final void zze(@Nullable List<WebImage> list) {
        this.zzel = list == null ? null : new ArrayList(list);
    }

    public double getContainerDuration() {
        return this.zzem;
    }

    /* access modifiers changed from: private */
    public final void zza(double d) {
        this.zzem = d;
    }

    /* access modifiers changed from: private */
    public final void zzf(JSONObject jSONObject) {
        clear();
        if (jSONObject != null) {
            String optString = jSONObject.optString("containerType", "");
            char c = 65535;
            switch (optString.hashCode()) {
                case 6924225:
                    if (optString.equals("AUDIOBOOK_CONTAINER")) {
                        c = 1;
                        break;
                    }
                    break;
                case 828666841:
                    if (optString.equals("GENERIC_CONTAINER")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.zzej = 0;
                    break;
                case 1:
                    this.zzej = 1;
                    break;
            }
            this.zzf = jSONObject.optString(PushConstants.TITLE, (String) null);
            JSONArray optJSONArray = jSONObject.optJSONArray("sections");
            if (optJSONArray != null) {
                this.zzek = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        MediaMetadata mediaMetadata = new MediaMetadata();
                        mediaMetadata.zzf(optJSONObject);
                        this.zzek.add(mediaMetadata);
                    }
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("containerImages");
            if (optJSONArray2 != null) {
                this.zzel = new ArrayList();
                zzdy.zza(this.zzel, optJSONArray2);
            }
            this.zzem = jSONObject.optDouble("containerDuration", this.zzem);
        }
    }

    public final JSONObject toJson() {
        JSONArray zzg;
        JSONObject jSONObject = new JSONObject();
        try {
            switch (this.zzej) {
                case 0:
                    jSONObject.put("containerType", "GENERIC_CONTAINER");
                    break;
                case 1:
                    jSONObject.put("containerType", "AUDIOBOOK_CONTAINER");
                    break;
            }
            if (!TextUtils.isEmpty(this.zzf)) {
                jSONObject.put(PushConstants.TITLE, this.zzf);
            }
            if (this.zzek != null && !this.zzek.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (MediaMetadata json : this.zzek) {
                    jSONArray.put(json.toJson());
                }
                jSONObject.put("sections", jSONArray);
            }
            if (!(this.zzel == null || this.zzel.isEmpty() || (zzg = zzdy.zzg(this.zzel)) == null)) {
                jSONObject.put("containerImages", zzg);
            }
            jSONObject.put("containerDuration", this.zzem);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueContainerMetadata)) {
            return false;
        }
        MediaQueueContainerMetadata mediaQueueContainerMetadata = (MediaQueueContainerMetadata) obj;
        if (this.zzej != mediaQueueContainerMetadata.zzej || !TextUtils.equals(this.zzf, mediaQueueContainerMetadata.zzf) || !Objects.equal(this.zzek, mediaQueueContainerMetadata.zzek) || !Objects.equal(this.zzel, mediaQueueContainerMetadata.zzel) || this.zzem != mediaQueueContainerMetadata.zzem) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzej), this.zzf, this.zzek, this.zzel, Double.valueOf(this.zzem));
    }
}
