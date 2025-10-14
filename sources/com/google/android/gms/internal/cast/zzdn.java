package com.google.android.gms.internal.cast;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakStatus;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLiveSeekableRange;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@VisibleForTesting
public final class zzdn extends zzco {
    public static final String NAMESPACE = zzdc.zzr("com.google.cast.media");
    private long zzaab;
    private MediaStatus zzaac;
    /* access modifiers changed from: private */
    public Long zzaad;
    private zzdp zzaae;
    @VisibleForTesting
    private final zzdt zzaaf = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaag = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaah = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaai = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaaj = new zzdt(10000);
    @VisibleForTesting
    private final zzdt zzaak = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaal = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaam = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaan = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaao = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaap = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaaq = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaar = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaas = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaat = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaau = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaav = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaaw = new zzdt(86400000);
    @VisibleForTesting
    private final zzdt zzaax = new zzdt(86400000);

    public zzdn(String str) {
        super(NAMESPACE, "MediaControlChannel", (String) null);
        zza(this.zzaaf);
        zza(this.zzaag);
        zza(this.zzaah);
        zza(this.zzaai);
        zza(this.zzaaj);
        zza(this.zzaak);
        zza(this.zzaal);
        zza(this.zzaam);
        zza(this.zzaan);
        zza(this.zzaao);
        zza(this.zzaap);
        zza(this.zzaaq);
        zza(this.zzaar);
        zza(this.zzaas);
        zza(this.zzaat);
        zza(this.zzaav);
        zza(this.zzaav);
        zza(this.zzaaw);
        zza(this.zzaax);
        zzem();
    }

    public final void zza(zzdp zzdp) {
        this.zzaae = zzdp;
    }

    public final long zza(@NonNull zzdu zzdu, @NonNull MediaLoadRequestData mediaLoadRequestData) throws IllegalStateException, IllegalArgumentException {
        if (mediaLoadRequestData.getMediaInfo() == null && mediaLoadRequestData.getQueueData() == null) {
            throw new IllegalArgumentException("MediaInfo and MediaQueueData should not be both null");
        }
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "LOAD");
            if (mediaLoadRequestData.getMediaInfo() != null) {
                jSONObject.put("media", mediaLoadRequestData.getMediaInfo().toJson());
            }
            if (mediaLoadRequestData.getQueueData() != null) {
                jSONObject.put("queueData", mediaLoadRequestData.getQueueData().toJson());
            }
            if (mediaLoadRequestData.getAutoplay() != null) {
                jSONObject.put("autoplay", mediaLoadRequestData.getAutoplay());
            }
            if (mediaLoadRequestData.getCurrentTime() != -1) {
                jSONObject.put("currentTime", ((double) mediaLoadRequestData.getCurrentTime()) / 1000.0d);
            }
            jSONObject.put("playbackRate", mediaLoadRequestData.getPlaybackRate());
            if (mediaLoadRequestData.getCredentials() != null) {
                jSONObject.put("credentials", mediaLoadRequestData.getCredentials());
            }
            if (mediaLoadRequestData.getCredentialsType() != null) {
                jSONObject.put("credentialsType", mediaLoadRequestData.getCredentialsType());
            }
            long[] activeTrackIds = mediaLoadRequestData.getActiveTrackIds();
            if (activeTrackIds != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < activeTrackIds.length; i++) {
                    jSONArray.put(i, activeTrackIds[i]);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            JSONObject customData = mediaLoadRequestData.getCustomData();
            if (customData != null) {
                jSONObject.put("customData", customData);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaaf.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, JSONObject jSONObject) throws IllegalStateException, zzds {
        JSONObject jSONObject2 = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject2.put("requestId", zzeg);
            jSONObject2.put(AppMeasurement.Param.TYPE, "PAUSE");
            jSONObject2.put("mediaSessionId", zzk());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject2.toString(), zzeg, (String) null);
        this.zzaag.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zzb(zzdu zzdu, JSONObject jSONObject) throws IllegalStateException, zzds {
        JSONObject jSONObject2 = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject2.put("requestId", zzeg);
            jSONObject2.put(AppMeasurement.Param.TYPE, "STOP");
            jSONObject2.put("mediaSessionId", zzk());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject2.toString(), zzeg, (String) null);
        this.zzaai.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zzc(zzdu zzdu, JSONObject jSONObject) throws IllegalStateException, zzds {
        JSONObject jSONObject2 = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject2.put("requestId", zzeg);
            jSONObject2.put(AppMeasurement.Param.TYPE, "PLAY");
            jSONObject2.put("mediaSessionId", zzk());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject2.toString(), zzeg, (String) null);
        this.zzaah.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, MediaSeekOptions mediaSeekOptions) throws IllegalStateException, zzds {
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        long position = mediaSeekOptions.isSeekToInfinite() ? 4294967296000L : mediaSeekOptions.getPosition();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "SEEK");
            jSONObject.put("mediaSessionId", zzk());
            jSONObject.put("currentTime", ((double) position) / 1000.0d);
            if (mediaSeekOptions.getResumeState() == 1) {
                jSONObject.put("resumeState", "PLAYBACK_START");
            } else if (mediaSeekOptions.getResumeState() == 2) {
                jSONObject.put("resumeState", "PLAYBACK_PAUSE");
            }
            if (mediaSeekOptions.getCustomData() != null) {
                jSONObject.put("customData", mediaSeekOptions.getCustomData());
            }
        } catch (JSONException e) {
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaad = Long.valueOf(position);
        this.zzaaj.zza(zzeg, (zzdu) new zzdq(this, zzdu));
        return zzeg;
    }

    public final long zza(zzdu zzdu) throws IllegalStateException, zzds {
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "SKIP_AD");
            jSONObject.put("mediaSessionId", zzk());
        } catch (JSONException e) {
            this.zzze.w(String.format(Locale.ROOT, "Error creating SkipAd message: %s", new Object[]{e.getMessage()}), new Object[0]);
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaax.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, double d, JSONObject jSONObject) throws IllegalStateException, zzds, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException(new StringBuilder(41).append("Volume cannot be ").append(d).toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject2.put("requestId", zzeg);
            jSONObject2.put(AppMeasurement.Param.TYPE, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzk());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(FirebaseAnalytics.Param.LEVEL, d);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject2.toString(), zzeg, (String) null);
        this.zzaak.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, boolean z, JSONObject jSONObject) throws IllegalStateException, zzds {
        JSONObject jSONObject2 = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject2.put("requestId", zzeg);
            jSONObject2.put(AppMeasurement.Param.TYPE, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzk());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject2.toString(), zzeg, (String) null);
        this.zzaal.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zzb(zzdu zzdu, double d, JSONObject jSONObject) throws IllegalStateException, zzds {
        if (this.zzaac == null) {
            throw new zzds();
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject2.put("requestId", zzeg);
            jSONObject2.put(AppMeasurement.Param.TYPE, "SET_PLAYBACK_RATE");
            jSONObject2.put("playbackRate", d);
            jSONObject2.put("mediaSessionId", this.zzaac.zzk());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject2.toString(), zzeg, (String) null);
        this.zzaaw.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zzb(zzdu zzdu) throws IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "GET_STATUS");
            if (this.zzaac != null) {
                jSONObject.put("mediaSessionId", this.zzaac.zzk());
            }
        } catch (JSONException e) {
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaam.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, long[] jArr) throws IllegalStateException, zzds {
        if (jArr == null) {
            throw new IllegalArgumentException("trackIds cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", zzk());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < jArr.length; i++) {
                jSONArray.put(i, jArr[i]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException e) {
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaan.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, TextTrackStyle textTrackStyle) throws IllegalStateException, zzds {
        if (textTrackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "EDIT_TRACKS_INFO");
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.toJson());
            }
            jSONObject.put("mediaSessionId", zzk());
        } catch (JSONException e) {
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaao.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long getApproximateStreamPosition() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null) {
            return 0;
        }
        if (this.zzaad != null) {
            if (this.zzaad.equals(4294967296000L)) {
                if (this.zzaac.getLiveSeekableRange() != null) {
                    return Math.min(this.zzaad.longValue(), getApproximateLiveSeekableRangeEnd());
                }
                if (getStreamDuration() >= 0) {
                    return Math.min(this.zzaad.longValue(), getStreamDuration());
                }
            }
            return this.zzaad.longValue();
        } else if (this.zzaab == 0) {
            return 0;
        } else {
            double playbackRate = this.zzaac.getPlaybackRate();
            long streamPosition = this.zzaac.getStreamPosition();
            int playerState = this.zzaac.getPlayerState();
            if (playbackRate == 0.0d || playerState != 2) {
                return streamPosition;
            }
            return zza(playbackRate, streamPosition, mediaInfo.getStreamDuration());
        }
    }

    public final long getApproximateLiveSeekableRangeStart() {
        MediaLiveSeekableRange liveSeekableRange;
        if (this.zzaac == null || (liveSeekableRange = this.zzaac.getLiveSeekableRange()) == null) {
            return 0;
        }
        long startTime = liveSeekableRange.getStartTime();
        if (liveSeekableRange.isMovingWindow()) {
            startTime = zza(1.0d, startTime, -1);
        }
        if (liveSeekableRange.isLiveDone()) {
            return Math.min(startTime, liveSeekableRange.getEndTime());
        }
        return startTime;
    }

    public final long getApproximateLiveSeekableRangeEnd() {
        MediaLiveSeekableRange liveSeekableRange;
        if (this.zzaac == null || (liveSeekableRange = this.zzaac.getLiveSeekableRange()) == null) {
            return 0;
        }
        long endTime = liveSeekableRange.getEndTime();
        if (!liveSeekableRange.isLiveDone()) {
            return zza(1.0d, endTime, -1);
        }
        return endTime;
    }

    public final long getApproximateAdBreakClipPositionMs() {
        AdBreakStatus adBreakStatus;
        AdBreakClipInfo currentAdBreakClip;
        double d = 0.0d;
        if (this.zzaab == 0 || this.zzaac == null || (adBreakStatus = this.zzaac.getAdBreakStatus()) == null || (currentAdBreakClip = this.zzaac.getCurrentAdBreakClip()) == null) {
            return 0;
        }
        if (this.zzaac.getPlaybackRate() == 0.0d && this.zzaac.getPlayerState() == 2) {
            d = 1.0d;
        }
        return zza(d, adBreakStatus.getCurrentBreakClipTimeInMs(), currentAdBreakClip.getDurationInMs());
    }

    private final long zza(double d, long j, long j2) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.zzaab;
        if (elapsedRealtime < 0) {
            elapsedRealtime = 0;
        }
        if (elapsedRealtime == 0) {
            return j;
        }
        long j3 = ((long) (((double) elapsedRealtime) * d)) + j;
        if (j2 <= 0 || j3 <= j2) {
            if (j3 < 0) {
                j2 = 0;
            } else {
                j2 = j3;
            }
        }
        return j2;
    }

    public final long getStreamDuration() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo != null) {
            return mediaInfo.getStreamDuration();
        }
        return 0;
    }

    public final MediaStatus getMediaStatus() {
        return this.zzaac;
    }

    public final MediaInfo getMediaInfo() {
        if (this.zzaac == null) {
            return null;
        }
        return this.zzaac.getMediaInfo();
    }

    public final long zza(zzdu zzdu, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException {
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("items must not be null or empty.");
        } else if (i < 0 || i >= mediaQueueItemArr.length) {
            throw new IllegalArgumentException(new StringBuilder(31).append("Invalid startIndex: ").append(i).toString());
        } else if (j == -1 || j >= 0) {
            JSONObject jSONObject2 = new JSONObject();
            long zzeg = zzeg();
            this.zzaaf.zza(zzeg, zzdu);
            try {
                jSONObject2.put("requestId", zzeg);
                jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_LOAD");
                JSONArray jSONArray = new JSONArray();
                for (int i3 = 0; i3 < mediaQueueItemArr.length; i3++) {
                    jSONArray.put(i3, mediaQueueItemArr[i3].toJson());
                }
                jSONObject2.put("items", jSONArray);
                String zza = zzdv.zza(Integer.valueOf(i2));
                if (zza == null) {
                    throw new IllegalArgumentException(new StringBuilder(32).append("Invalid repeat mode: ").append(i2).toString());
                }
                jSONObject2.put("repeatMode", zza);
                jSONObject2.put("startIndex", i);
                if (j != -1) {
                    jSONObject2.put("currentTime", ((double) j) / 1000.0d);
                }
                if (jSONObject != null) {
                    jSONObject2.put("customData", jSONObject);
                }
                zza(jSONObject2.toString(), zzeg, (String) null);
                return zzeg;
            } catch (JSONException e) {
            }
        } else {
            throw new IllegalArgumentException(new StringBuilder(54).append("playPosition can not be negative: ").append(j).toString());
        }
    }

    public final long zza(zzdu zzdu, MediaQueueItem[] mediaQueueItemArr, int i, int i2, int i3, long j, JSONObject jSONObject) throws IllegalStateException, zzds, IllegalArgumentException {
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("itemsToInsert must not be null or empty.");
        } else if (i3 != -1 && (i3 < 0 || i3 >= mediaQueueItemArr.length)) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "currentItemIndexInItemsToInsert %d out of range [0, %d).", new Object[]{Integer.valueOf(i3), Integer.valueOf(mediaQueueItemArr.length)}));
        } else if (j == -1 || j >= 0) {
            JSONObject jSONObject2 = new JSONObject();
            long zzeg = zzeg();
            try {
                jSONObject2.put("requestId", zzeg);
                jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_INSERT");
                jSONObject2.put("mediaSessionId", zzk());
                JSONArray jSONArray = new JSONArray();
                for (int i4 = 0; i4 < mediaQueueItemArr.length; i4++) {
                    jSONArray.put(i4, mediaQueueItemArr[i4].toJson());
                }
                jSONObject2.put("items", jSONArray);
                if (i != 0) {
                    jSONObject2.put("insertBefore", i);
                }
                if (i3 != -1) {
                    jSONObject2.put("currentItemIndex", i3);
                }
                if (j != -1) {
                    jSONObject2.put("currentTime", ((double) j) / 1000.0d);
                }
                if (jSONObject != null) {
                    jSONObject2.put("customData", jSONObject);
                }
            } catch (JSONException e) {
            }
            zza(jSONObject2.toString(), zzeg, (String) null);
            this.zzaap.zza(zzeg, zzdu);
            return zzeg;
        } else {
            throw new IllegalArgumentException(new StringBuilder(54).append("playPosition can not be negative: ").append(j).toString());
        }
    }

    public final long zza(zzdu zzdu, int i, long j, MediaQueueItem[] mediaQueueItemArr, int i2, Integer num, JSONObject jSONObject) throws IllegalArgumentException, IllegalStateException, zzds {
        if (j == -1 || j >= 0) {
            JSONObject jSONObject2 = new JSONObject();
            long zzeg = zzeg();
            try {
                jSONObject2.put("requestId", zzeg);
                jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_UPDATE");
                jSONObject2.put("mediaSessionId", zzk());
                if (i != 0) {
                    jSONObject2.put("currentItemId", i);
                }
                if (i2 != 0) {
                    jSONObject2.put("jump", i2);
                }
                if (mediaQueueItemArr != null && mediaQueueItemArr.length > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (int i3 = 0; i3 < mediaQueueItemArr.length; i3++) {
                        jSONArray.put(i3, mediaQueueItemArr[i3].toJson());
                    }
                    jSONObject2.put("items", jSONArray);
                }
                String zza = zzdv.zza(num);
                if (zza != null) {
                    jSONObject2.put("repeatMode", zza);
                }
                if (j != -1) {
                    jSONObject2.put("currentTime", ((double) j) / 1000.0d);
                }
                if (jSONObject != null) {
                    jSONObject2.put("customData", jSONObject);
                }
            } catch (JSONException e) {
            }
            zza(jSONObject2.toString(), zzeg, (String) null);
            this.zzaaq.zza(zzeg, zzdu);
            return zzeg;
        }
        throw new IllegalArgumentException(new StringBuilder(53).append("playPosition cannot be negative: ").append(j).toString());
    }

    public final long zza(zzdu zzdu, int[] iArr, JSONObject jSONObject) throws IllegalStateException, zzds, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToRemove must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject2.put("requestId", zzeg);
            jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_REMOVE");
            jSONObject2.put("mediaSessionId", zzk());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < iArr.length; i++) {
                jSONArray.put(i, iArr[i]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject2.toString(), zzeg, (String) null);
        this.zzaar.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, int[] iArr, int i, JSONObject jSONObject) throws IllegalStateException, zzds, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToReorder must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject2.put("requestId", zzeg);
            jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_REORDER");
            jSONObject2.put("mediaSessionId", zzk());
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                jSONArray.put(i2, iArr[i2]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject2.toString(), zzeg, (String) null);
        this.zzaas.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zzc(zzdu zzdu) throws zzds, IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "QUEUE_GET_ITEM_IDS");
            jSONObject.put("mediaSessionId", zzk());
        } catch (JSONException e) {
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaat.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, int i, int i2, int i3) throws zzds, IllegalArgumentException {
        if ((i2 <= 0 || i3 != 0) && (i2 != 0 || i3 <= 0)) {
            throw new IllegalArgumentException("Exactly one of nextCount and prevCount must be positive and the other must be zero");
        }
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "QUEUE_GET_ITEM_RANGE");
            jSONObject.put("mediaSessionId", zzk());
            jSONObject.put("itemId", i);
            if (i2 > 0) {
                jSONObject.put("nextCount", i2);
            }
            if (i3 > 0) {
                jSONObject.put("prevCount", i3);
            }
        } catch (JSONException e) {
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaav.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zza(zzdu zzdu, int[] iArr) throws zzds, IllegalArgumentException {
        JSONObject jSONObject = new JSONObject();
        long zzeg = zzeg();
        try {
            jSONObject.put("requestId", zzeg);
            jSONObject.put(AppMeasurement.Param.TYPE, "QUEUE_GET_ITEMS");
            jSONObject.put("mediaSessionId", zzk());
            JSONArray jSONArray = new JSONArray();
            for (int put : iArr) {
                jSONArray.put(put);
            }
            jSONObject.put("itemIds", jSONArray);
        } catch (JSONException e) {
        }
        zza(jSONObject.toString(), zzeg, (String) null);
        this.zzaau.zza(zzeg, zzdu);
        return zzeg;
    }

    public final long zzb(String str, List<zzbv> list) throws IllegalStateException {
        long zzeg = zzeg();
        zza(zza(str, list, zzeg), zzeg, (String) null);
        return zzeg;
    }

    private static String zza(String str, List<zzbv> list, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestId", j);
            jSONObject.put(AppMeasurement.Param.TYPE, "PRECACHE");
            if (str != null) {
                jSONObject.put("precacheData", str);
            }
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    jSONArray.put(i, list.get(i).toJson());
                }
                jSONObject.put("requestItems", jSONArray);
            }
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public final void zzp(String str) {
        int[] zzb;
        int i;
        int i2;
        this.zzze.d("message received: %s", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(AppMeasurement.Param.TYPE);
            long optLong = jSONObject.optLong("requestId", -1);
            char c = 65535;
            switch (string.hashCode()) {
                case -1830647528:
                    if (string.equals("LOAD_CANCELLED")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1790231854:
                    if (string.equals("QUEUE_ITEMS")) {
                        c = 7;
                        break;
                    }
                    break;
                case -1125000185:
                    if (string.equals("INVALID_REQUEST")) {
                        c = 4;
                        break;
                    }
                    break;
                case -262628938:
                    if (string.equals("LOAD_FAILED")) {
                        c = 2;
                        break;
                    }
                    break;
                case 154411710:
                    if (string.equals("QUEUE_CHANGE")) {
                        c = 6;
                        break;
                    }
                    break;
                case 431600379:
                    if (string.equals("INVALID_PLAYER_STATE")) {
                        c = 1;
                        break;
                    }
                    break;
                case 823510221:
                    if (string.equals("MEDIA_STATUS")) {
                        c = 0;
                        break;
                    }
                    break;
                case 2107149050:
                    if (string.equals("QUEUE_ITEM_IDS")) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    JSONArray jSONArray = jSONObject.getJSONArray(NotificationCompat.CATEGORY_STATUS);
                    if (jSONArray.length() > 0) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
                        boolean test = this.zzaaf.test(optLong);
                        if ((this.zzaak.zzeo() && !this.zzaak.test(optLong)) || (this.zzaal.zzeo() && !this.zzaal.test(optLong))) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (test || this.zzaac == null) {
                            this.zzaac = new MediaStatus(jSONObject2);
                            this.zzaab = SystemClock.elapsedRealtime();
                            i2 = 127;
                        } else {
                            i2 = this.zzaac.zza(jSONObject2, i);
                        }
                        if ((i2 & 1) != 0) {
                            this.zzaab = SystemClock.elapsedRealtime();
                            onStatusUpdated();
                        }
                        if ((i2 & 2) != 0) {
                            this.zzaab = SystemClock.elapsedRealtime();
                            onStatusUpdated();
                        }
                        if ((i2 & 128) != 0) {
                            this.zzaab = SystemClock.elapsedRealtime();
                        }
                        if ((i2 & 4) != 0) {
                            onMetadataUpdated();
                        }
                        if ((i2 & 8) != 0) {
                            onQueueStatusUpdated();
                        }
                        if ((i2 & 16) != 0) {
                            onPreloadStatusUpdated();
                        }
                        if ((i2 & 32) != 0) {
                            this.zzaab = SystemClock.elapsedRealtime();
                            if (this.zzaae != null) {
                                this.zzaae.onAdBreakStatusUpdated();
                            }
                        }
                        if ((i2 & 64) != 0) {
                            this.zzaab = SystemClock.elapsedRealtime();
                            onStatusUpdated();
                        }
                    } else {
                        this.zzaac = null;
                        onStatusUpdated();
                        onMetadataUpdated();
                        onQueueStatusUpdated();
                        onPreloadStatusUpdated();
                    }
                    for (zzdt zzc : zzea()) {
                        zzc.zzc(optLong, 0, (Object) null);
                    }
                    return;
                case 1:
                    this.zzze.w("received unexpected error: Invalid Player State.", new Object[0]);
                    JSONObject optJSONObject = jSONObject.optJSONObject("customData");
                    for (zzdt zzc2 : zzea()) {
                        zzc2.zzc(optLong, 2100, optJSONObject);
                    }
                    return;
                case 2:
                    this.zzaaf.zzc(optLong, 2100, jSONObject.optJSONObject("customData"));
                    return;
                case 3:
                    this.zzaaf.zzc(optLong, RemoteMediaPlayer.STATUS_CANCELED, jSONObject.optJSONObject("customData"));
                    return;
                case 4:
                    this.zzze.w("received unexpected error: Invalid Request.", new Object[0]);
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("customData");
                    for (zzdt zzc3 : zzea()) {
                        zzc3.zzc(optLong, 2100, optJSONObject2);
                    }
                    return;
                case 5:
                    this.zzaat.zzc(optLong, 0, (Object) null);
                    if (this.zzaae != null && (zzb = zzb(jSONObject.getJSONArray("itemIds"))) != null) {
                        this.zzaae.zza(zzb);
                        return;
                    }
                    return;
                case 6:
                    this.zzaav.zzc(optLong, 0, (Object) null);
                    if (this.zzaae != null) {
                        String string2 = jSONObject.getString("changeType");
                        int[] zzb2 = zzb(jSONObject.getJSONArray("itemIds"));
                        int optInt = jSONObject.optInt("insertBefore", 0);
                        if (zzb2 != null) {
                            char c2 = 65535;
                            switch (string2.hashCode()) {
                                case -2130463047:
                                    if (string2.equals("INSERT")) {
                                        c2 = 0;
                                        break;
                                    }
                                    break;
                                case -1881281404:
                                    if (string2.equals("REMOVE")) {
                                        c2 = 2;
                                        break;
                                    }
                                    break;
                                case -1785516855:
                                    if (string2.equals("UPDATE")) {
                                        c2 = 3;
                                        break;
                                    }
                                    break;
                                case 1122976047:
                                    if (string2.equals("ITEMS_CHANGE")) {
                                        c2 = 1;
                                        break;
                                    }
                                    break;
                                case 1395699694:
                                    if (string2.equals("NO_CHANGE")) {
                                        c2 = 4;
                                        break;
                                    }
                                    break;
                            }
                            switch (c2) {
                                case 0:
                                    this.zzaae.zza(zzb2, optInt);
                                    return;
                                case 1:
                                    this.zzaae.zzb(zzb2);
                                    return;
                                case 2:
                                    this.zzaae.zzc(zzb2);
                                    return;
                                case 3:
                                    this.zzaae.zza(zzb2);
                                    return;
                                default:
                                    return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                case 7:
                    this.zzaau.zzc(optLong, 0, (Object) null);
                    if (this.zzaae != null) {
                        JSONArray jSONArray2 = jSONObject.getJSONArray("items");
                        MediaQueueItem[] mediaQueueItemArr = new MediaQueueItem[jSONArray2.length()];
                        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                            mediaQueueItemArr[i3] = new MediaQueueItem.Builder(jSONArray2.getJSONObject(i3)).build();
                        }
                        this.zzaae.zzb(mediaQueueItemArr);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (JSONException e) {
            this.zzze.w("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
        this.zzze.w("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
    }

    private static int[] zzb(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            iArr[i] = jSONArray.getInt(i);
        }
        return iArr;
    }

    private final long zzk() throws zzds {
        if (this.zzaac != null) {
            return this.zzaac.zzk();
        }
        throw new zzds();
    }

    private final void onStatusUpdated() {
        if (this.zzaae != null) {
            this.zzaae.onStatusUpdated();
        }
    }

    private final void onMetadataUpdated() {
        if (this.zzaae != null) {
            this.zzaae.onMetadataUpdated();
        }
    }

    private final void onQueueStatusUpdated() {
        if (this.zzaae != null) {
            this.zzaae.onQueueStatusUpdated();
        }
    }

    private final void onPreloadStatusUpdated() {
        if (this.zzaae != null) {
            this.zzaae.onPreloadStatusUpdated();
        }
    }

    private final void zzem() {
        this.zzaab = 0;
        this.zzaac = null;
        for (zzdt zzx : zzea()) {
            zzx.zzx(CastStatusCodes.CANCELED);
        }
    }

    public final void zzdz() {
        super.zzdz();
        zzem();
    }

    public final void zza(long j, int i) {
        for (zzdt zzc : zzea()) {
            zzc.zzc(j, i, (Object) null);
        }
    }
}
