package com.google.android.gms.cast;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.cast.MediaQueueContainerMetadata;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.cast.zzdv;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaQueueData {
    public static final int MEDIA_QUEUE_TYPE_ALBUM = 1;
    public static final int MEDIA_QUEUE_TYPE_AUDIO_BOOK = 3;
    public static final int MEDIA_QUEUE_TYPE_GENERIC = 0;
    public static final int MEDIA_QUEUE_TYPE_LIVE_TV = 8;
    public static final int MEDIA_QUEUE_TYPE_MOVIE = 9;
    public static final int MEDIA_QUEUE_TYPE_PLAYLIST = 2;
    public static final int MEDIA_QUEUE_TYPE_PODCAST_SERIES = 5;
    public static final int MEDIA_QUEUE_TYPE_RADIO_STATION = 4;
    public static final int MEDIA_QUEUE_TYPE_TV_SERIES = 6;
    public static final int MEDIA_QUEUE_TYPE_VIDEO_PLAYLIST = 7;
    private String name;
    private int repeatMode;
    private long startTime;
    private String zzdl;
    private MediaQueueContainerMetadata zzen;
    private String zzeo;
    private int zzep;
    private List<MediaQueueItem> zzeq;
    private int zzer;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQueueType {
    }

    private MediaQueueData() {
        clear();
    }

    public static class Builder {
        private final MediaQueueData zzdy = new MediaQueueData();

        public Builder setQueueId(@Nullable String str) {
            this.zzdy.zzh(str);
            return this;
        }

        public Builder setEntity(@Nullable String str) {
            this.zzdy.zzd(str);
            return this;
        }

        public Builder setQueueType(int i) {
            this.zzdy.zzb(i);
            return this;
        }

        public Builder setName(@Nullable String str) {
            this.zzdy.setName(str);
            return this;
        }

        public Builder setContainerMetadata(@Nullable MediaQueueContainerMetadata mediaQueueContainerMetadata) {
            this.zzdy.zza(mediaQueueContainerMetadata);
            return this;
        }

        public Builder setRepeatMode(int i) {
            this.zzdy.setRepeatMode(i);
            return this;
        }

        public Builder setItems(@Nullable List<MediaQueueItem> list) {
            this.zzdy.zzf(list);
            return this;
        }

        public Builder setStartIndex(int i) {
            this.zzdy.zzc(i);
            return this;
        }

        public Builder setStartTime(long j) {
            this.zzdy.setStartTime(j);
            return this;
        }

        public MediaQueueData build() {
            return new MediaQueueData();
        }

        public final Builder zzh(JSONObject jSONObject) {
            this.zzdy.zzf(jSONObject);
            return this;
        }
    }

    private MediaQueueData(MediaQueueData mediaQueueData) {
        this.zzeo = mediaQueueData.zzeo;
        this.zzdl = mediaQueueData.zzdl;
        this.zzep = mediaQueueData.zzep;
        this.name = mediaQueueData.name;
        this.zzen = mediaQueueData.zzen;
        this.repeatMode = mediaQueueData.repeatMode;
        this.zzeq = mediaQueueData.zzeq;
        this.zzer = mediaQueueData.zzer;
        this.startTime = mediaQueueData.startTime;
    }

    private final void clear() {
        this.zzeo = null;
        this.zzdl = null;
        this.zzep = 0;
        this.name = null;
        this.repeatMode = 0;
        this.zzeq = null;
        this.zzer = 0;
        this.startTime = -1;
    }

    @Nullable
    public String getQueueId() {
        return this.zzeo;
    }

    /* access modifiers changed from: private */
    public final void zzh(@Nullable String str) {
        this.zzeo = str;
    }

    @Nullable
    public String getEntity() {
        return this.zzdl;
    }

    /* access modifiers changed from: private */
    public final void zzd(@Nullable String str) {
        this.zzdl = str;
    }

    public int getQueueType() {
        return this.zzep;
    }

    /* access modifiers changed from: private */
    public final void zzb(int i) {
        this.zzep = i;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: private */
    public final void setName(@Nullable String str) {
        this.name = str;
    }

    @Nullable
    public MediaQueueContainerMetadata getContainerMetadata() {
        return this.zzen;
    }

    /* access modifiers changed from: private */
    public final void zza(@Nullable MediaQueueContainerMetadata mediaQueueContainerMetadata) {
        this.zzen = mediaQueueContainerMetadata;
    }

    public int getRepeatMode() {
        return this.repeatMode;
    }

    /* access modifiers changed from: private */
    public final void setRepeatMode(int i) {
        this.repeatMode = i;
    }

    @Nullable
    public List<MediaQueueItem> getItems() {
        if (this.zzeq == null) {
            return null;
        }
        return Collections.unmodifiableList(this.zzeq);
    }

    /* access modifiers changed from: private */
    public final void zzf(@Nullable List<MediaQueueItem> list) {
        this.zzeq = list == null ? null : new ArrayList(list);
    }

    public int getStartIndex() {
        return this.zzer;
    }

    /* access modifiers changed from: private */
    public final void zzc(int i) {
        this.zzer = i;
    }

    public long getStartTime() {
        return this.startTime;
    }

    /* access modifiers changed from: private */
    public final void setStartTime(long j) {
        this.startTime = j;
    }

    /* access modifiers changed from: private */
    public final void zzf(JSONObject jSONObject) {
        clear();
        if (jSONObject != null) {
            this.zzeo = jSONObject.optString(PushConstants.CHANNEL_ID, (String) null);
            this.zzdl = jSONObject.optString("entity", (String) null);
            String optString = jSONObject.optString("queueType");
            char c = 65535;
            switch (optString.hashCode()) {
                case -1803151310:
                    if (optString.equals("PODCAST_SERIES")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1758903120:
                    if (optString.equals("RADIO_STATION")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1632865838:
                    if (optString.equals("PLAYLIST")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1319760993:
                    if (optString.equals("AUDIOBOOK")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1088524588:
                    if (optString.equals("TV_SERIES")) {
                        c = 5;
                        break;
                    }
                    break;
                case 62359119:
                    if (optString.equals("ALBUM")) {
                        c = 0;
                        break;
                    }
                    break;
                case 73549584:
                    if (optString.equals("MOVIE")) {
                        c = 8;
                        break;
                    }
                    break;
                case 393100598:
                    if (optString.equals("VIDEO_PLAYLIST")) {
                        c = 6;
                        break;
                    }
                    break;
                case 902303413:
                    if (optString.equals("LIVE_TV")) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.zzep = 1;
                    break;
                case 1:
                    this.zzep = 2;
                    break;
                case 2:
                    this.zzep = 3;
                    break;
                case 3:
                    this.zzep = 4;
                    break;
                case 4:
                    this.zzep = 5;
                    break;
                case 5:
                    this.zzep = 6;
                    break;
                case 6:
                    this.zzep = 7;
                    break;
                case 7:
                    this.zzep = 8;
                    break;
                case 8:
                    this.zzep = 9;
                    break;
            }
            this.name = jSONObject.optString("name", (String) null);
            if (jSONObject.has("containerMetadata")) {
                this.zzen = new MediaQueueContainerMetadata.Builder().zzg(jSONObject.optJSONObject("containerMetadata")).build();
            }
            Integer zzw = zzdv.zzw(jSONObject.optString("repeatMode"));
            if (zzw != null) {
                this.repeatMode = zzw.intValue();
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("items");
            if (optJSONArray != null) {
                this.zzeq = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        try {
                            this.zzeq.add(new MediaQueueItem(optJSONObject));
                        } catch (JSONException e) {
                        }
                    }
                }
            }
            this.zzer = jSONObject.optInt("startIndex", this.zzer);
            if (jSONObject.has("startTime")) {
                this.startTime = (long) (jSONObject.optDouble("startTime", (double) this.startTime) * 1000.0d);
            }
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.zzeo)) {
                jSONObject.put(PushConstants.CHANNEL_ID, this.zzeo);
            }
            if (!TextUtils.isEmpty(this.zzdl)) {
                jSONObject.put("entity", this.zzdl);
            }
            switch (this.zzep) {
                case 1:
                    jSONObject.put("queueType", "ALBUM");
                    break;
                case 2:
                    jSONObject.put("queueType", "PLAYLIST");
                    break;
                case 3:
                    jSONObject.put("queueType", "AUDIOBOOK");
                    break;
                case 4:
                    jSONObject.put("queueType", "RADIO_STATION");
                    break;
                case 5:
                    jSONObject.put("queueType", "PODCAST_SERIES");
                    break;
                case 6:
                    jSONObject.put("queueType", "TV_SERIES");
                    break;
                case 7:
                    jSONObject.put("queueType", "VIDEO_PLAYLIST");
                    break;
                case 8:
                    jSONObject.put("queueType", "LIVE_TV");
                    break;
                case 9:
                    jSONObject.put("queueType", "MOVIE");
                    break;
            }
            if (!TextUtils.isEmpty(this.name)) {
                jSONObject.put("name", this.name);
            }
            if (this.zzen != null) {
                jSONObject.put("containerMetadata", this.zzen.toJson());
            }
            String zza = zzdv.zza(Integer.valueOf(this.repeatMode));
            if (zza != null) {
                jSONObject.put("repeatMode", zza);
            }
            if (this.zzeq != null && !this.zzeq.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (MediaQueueItem json : this.zzeq) {
                    jSONArray.put(json.toJson());
                }
                jSONObject.put("items", jSONArray);
            }
            jSONObject.put("startIndex", this.zzer);
            if (this.startTime != -1) {
                jSONObject.put("startTime", ((double) this.startTime) / 1000.0d);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueData)) {
            return false;
        }
        MediaQueueData mediaQueueData = (MediaQueueData) obj;
        if (!TextUtils.equals(this.zzeo, mediaQueueData.zzeo) || !TextUtils.equals(this.zzdl, mediaQueueData.zzdl) || this.zzep != mediaQueueData.zzep || !TextUtils.equals(this.name, mediaQueueData.name) || !Objects.equal(this.zzen, mediaQueueData.zzen) || this.repeatMode != mediaQueueData.repeatMode || !Objects.equal(this.zzeq, mediaQueueData.zzeq) || this.zzer != mediaQueueData.zzer || this.startTime != mediaQueueData.startTime) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzeo, this.zzdl, Integer.valueOf(this.zzep), this.name, this.zzen, Integer.valueOf(this.repeatMode), this.zzeq, Integer.valueOf(this.zzer), Long.valueOf(this.startTime));
    }
}
