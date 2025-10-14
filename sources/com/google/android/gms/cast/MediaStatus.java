package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "MediaStatusCreator")
@SafeParcelable.Reserved({1})
@VisibleForTesting
public class MediaStatus extends AbstractSafeParcelable {
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_BACKWARD = 32;
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final Parcelable.Creator<MediaStatus> CREATOR = new zzas();
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_LOADING = 5;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    public static final int REPEAT_MODE_REPEAT_ALL = 1;
    public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
    public static final int REPEAT_MODE_REPEAT_OFF = 0;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;
    @SafeParcelable.Field(getter = "getMediaInfo", id = 2)
    @VisibleForTesting
    private MediaInfo zzdo;
    @SafeParcelable.Field(getter = "getPlaybackRate", id = 5)
    @VisibleForTesting
    private double zzdr;
    @SafeParcelable.Field(getter = "getActiveTrackIds", id = 12)
    @VisibleForTesting
    private long[] zzds;
    @VisibleForTesting
    private MediaQueueData zzdy;
    @SafeParcelable.Field(getter = "getMediaSessionId", id = 3)
    @VisibleForTesting
    private long zzfb;
    @SafeParcelable.Field(getter = "getCurrentItemId", id = 4)
    @VisibleForTesting
    private int zzfc;
    @SafeParcelable.Field(getter = "getPlayerState", id = 6)
    @VisibleForTesting
    private int zzfd;
    @SafeParcelable.Field(getter = "getIdleReason", id = 7)
    @VisibleForTesting
    private int zzfe;
    @SafeParcelable.Field(getter = "getStreamPosition", id = 8)
    @VisibleForTesting
    private long zzff;
    @SafeParcelable.Field(id = 9)
    private long zzfg;
    @SafeParcelable.Field(getter = "getStreamVolume", id = 10)
    @VisibleForTesting
    private double zzfh;
    @SafeParcelable.Field(getter = "isMute", id = 11)
    @VisibleForTesting
    private boolean zzfi;
    @SafeParcelable.Field(getter = "getLoadingItemId", id = 13)
    @VisibleForTesting
    private int zzfj;
    @SafeParcelable.Field(getter = "getPreloadedItemId", id = 14)
    @VisibleForTesting
    private int zzfk;
    @SafeParcelable.Field(id = 16)
    private int zzfl;
    @SafeParcelable.Field(id = 17)
    private final ArrayList<MediaQueueItem> zzfm;
    @SafeParcelable.Field(getter = "isPlayingAd", id = 18)
    @VisibleForTesting
    private boolean zzfn;
    @SafeParcelable.Field(getter = "getAdBreakStatus", id = 19)
    @VisibleForTesting
    private AdBreakStatus zzfo;
    @SafeParcelable.Field(getter = "getVideoInfo", id = 20)
    @VisibleForTesting
    private VideoInfo zzfp;
    @VisibleForTesting
    private MediaLiveSeekableRange zzfq;
    private final SparseArray<Integer> zzfr;
    @SafeParcelable.Field(id = 15)
    private String zzj;
    @VisibleForTesting
    private JSONObject zzp;

    @SafeParcelable.Constructor
    MediaStatus(@SafeParcelable.Param(id = 2) MediaInfo mediaInfo, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) double d, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) int i3, @SafeParcelable.Param(id = 8) long j2, @SafeParcelable.Param(id = 9) long j3, @SafeParcelable.Param(id = 10) double d2, @SafeParcelable.Param(id = 11) boolean z, @SafeParcelable.Param(id = 12) long[] jArr, @SafeParcelable.Param(id = 13) int i4, @SafeParcelable.Param(id = 14) int i5, @SafeParcelable.Param(id = 15) String str, @SafeParcelable.Param(id = 16) int i6, @SafeParcelable.Param(id = 17) List<MediaQueueItem> list, @SafeParcelable.Param(id = 18) boolean z2, @SafeParcelable.Param(id = 19) AdBreakStatus adBreakStatus, @SafeParcelable.Param(id = 20) VideoInfo videoInfo) {
        this.zzfm = new ArrayList<>();
        this.zzfr = new SparseArray<>();
        this.zzdo = mediaInfo;
        this.zzfb = j;
        this.zzfc = i;
        this.zzdr = d;
        this.zzfd = i2;
        this.zzfe = i3;
        this.zzff = j2;
        this.zzfg = j3;
        this.zzfh = d2;
        this.zzfi = z;
        this.zzds = jArr;
        this.zzfj = i4;
        this.zzfk = i5;
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
        this.zzfl = i6;
        if (list != null && !list.isEmpty()) {
            zza((MediaQueueItem[]) list.toArray(new MediaQueueItem[list.size()]));
        }
        this.zzfn = z2;
        this.zzfo = adBreakStatus;
        this.zzfp = videoInfo;
    }

    public MediaStatus(JSONObject jSONObject) throws JSONException {
        this((MediaInfo) null, 0, 0, 0.0d, 0, 0, 0, 0, 0.0d, false, (long[]) null, 0, 0, (String) null, 0, (List<MediaQueueItem>) null, false, (AdBreakStatus) null, (VideoInfo) null);
        zza(jSONObject, 0);
    }

    public final long zzk() {
        return this.zzfb;
    }

    public int getPlayerState() {
        return this.zzfd;
    }

    public int getIdleReason() {
        return this.zzfe;
    }

    public double getPlaybackRate() {
        return this.zzdr;
    }

    public MediaInfo getMediaInfo() {
        return this.zzdo;
    }

    public long getStreamPosition() {
        return this.zzff;
    }

    public boolean isMediaCommandSupported(long j) {
        return (this.zzfg & j) != 0;
    }

    public double getStreamVolume() {
        return this.zzfh;
    }

    public boolean isMute() {
        return this.zzfi;
    }

    public long[] getActiveTrackIds() {
        return this.zzds;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public int getCurrentItemId() {
        return this.zzfc;
    }

    public int getLoadingItemId() {
        return this.zzfj;
    }

    public int getPreloadedItemId() {
        return this.zzfk;
    }

    public int getQueueRepeatMode() {
        return this.zzfl;
    }

    public List<MediaQueueItem> getQueueItems() {
        return this.zzfm;
    }

    public int getQueueItemCount() {
        return this.zzfm.size();
    }

    public MediaQueueItem getQueueItemById(int i) {
        return getItemById(i);
    }

    public MediaQueueItem getQueueItem(int i) {
        return getItemByIndex(i);
    }

    public boolean isPlayingAd() {
        return this.zzfn;
    }

    public final void zzf(boolean z) {
        this.zzfn = z;
    }

    public AdBreakStatus getAdBreakStatus() {
        return this.zzfo;
    }

    public VideoInfo getVideoInfo() {
        return this.zzfp;
    }

    @Nullable
    public MediaLiveSeekableRange getLiveSeekableRange() {
        return this.zzfq;
    }

    @Nullable
    public MediaQueueData getQueueData() {
        return this.zzdy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:121:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(org.json.JSONObject r13, int r14) throws org.json.JSONException {
        /*
            r12 = this;
            org.json.JSONObject r4 = zzj(r13)
            r0 = 0
            java.lang.String r1 = "mediaSessionId"
            long r2 = r4.getLong(r1)
            long r6 = r12.zzfb
            int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x0014
            r12.zzfb = r2
            r0 = 1
        L_0x0014:
            java.lang.String r1 = "playerState"
            boolean r1 = r4.has(r1)
            if (r1 == 0) goto L_0x0057
            r1 = 0
            java.lang.String r2 = "playerState"
            java.lang.String r2 = r4.getString(r2)
            java.lang.String r3 = "IDLE"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x00fa
            r1 = 1
        L_0x002c:
            int r2 = r12.zzfd
            if (r1 == r2) goto L_0x03ac
            r12.zzfd = r1
            r2 = r0 | 2
        L_0x0034:
            r0 = 1
            if (r1 != r0) goto L_0x03a9
            java.lang.String r0 = "idleReason"
            boolean r0 = r4.has(r0)
            if (r0 == 0) goto L_0x03a9
            r0 = 0
            java.lang.String r1 = "idleReason"
            java.lang.String r1 = r4.getString(r1)
            java.lang.String r3 = "CANCELLED"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0126
            r0 = 2
        L_0x004f:
            int r1 = r12.zzfe
            if (r0 == r1) goto L_0x03a9
            r12.zzfe = r0
            r0 = r2 | 2
        L_0x0057:
            java.lang.String r1 = "playbackRate"
            boolean r1 = r4.has(r1)
            if (r1 == 0) goto L_0x006f
            java.lang.String r1 = "playbackRate"
            double r2 = r4.getDouble(r1)
            double r6 = r12.zzdr
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 == 0) goto L_0x006f
            r12.zzdr = r2
            r0 = r0 | 2
        L_0x006f:
            java.lang.String r1 = "currentTime"
            boolean r1 = r4.has(r1)
            if (r1 == 0) goto L_0x0090
            java.lang.String r1 = "currentTime"
            double r2 = r4.getDouble(r1)
            r6 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r2 = r2 * r6
            long r2 = (long) r2
            long r6 = r12.zzff
            int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x008e
            r12.zzff = r2
            r0 = r0 | 2
        L_0x008e:
            r0 = r0 | 128(0x80, float:1.794E-43)
        L_0x0090:
            java.lang.String r1 = "supportedMediaCommands"
            boolean r1 = r4.has(r1)
            if (r1 == 0) goto L_0x00a8
            java.lang.String r1 = "supportedMediaCommands"
            long r2 = r4.getLong(r1)
            long r6 = r12.zzfg
            int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x00a8
            r12.zzfg = r2
            r0 = r0 | 2
        L_0x00a8:
            java.lang.String r1 = "volume"
            boolean r1 = r4.has(r1)
            if (r1 == 0) goto L_0x00d8
            r1 = r14 & 1
            if (r1 != 0) goto L_0x00d8
            java.lang.String r1 = "volume"
            org.json.JSONObject r1 = r4.getJSONObject(r1)
            java.lang.String r2 = "level"
            double r2 = r1.getDouble(r2)
            double r6 = r12.zzfh
            int r5 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x00ca
            r12.zzfh = r2
            r0 = r0 | 2
        L_0x00ca:
            java.lang.String r2 = "muted"
            boolean r1 = r1.getBoolean(r2)
            boolean r2 = r12.zzfi
            if (r1 == r2) goto L_0x00d8
            r12.zzfi = r1
            r0 = r0 | 2
        L_0x00d8:
            r2 = 0
            r1 = 0
            java.lang.String r3 = "activeTrackIds"
            boolean r3 = r4.has(r3)
            if (r3 == 0) goto L_0x0255
            java.lang.String r1 = "activeTrackIds"
            org.json.JSONArray r5 = r4.getJSONArray(r1)
            int r6 = r5.length()
            long[] r1 = new long[r6]
            r3 = 0
        L_0x00ef:
            if (r3 >= r6) goto L_0x0147
            long r8 = r5.getLong(r3)
            r1[r3] = r8
            int r3 = r3 + 1
            goto L_0x00ef
        L_0x00fa:
            java.lang.String r3 = "PLAYING"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0105
            r1 = 2
            goto L_0x002c
        L_0x0105:
            java.lang.String r3 = "PAUSED"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0110
            r1 = 3
            goto L_0x002c
        L_0x0110:
            java.lang.String r3 = "BUFFERING"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x011b
            r1 = 4
            goto L_0x002c
        L_0x011b:
            java.lang.String r3 = "LOADING"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x002c
            r1 = 5
            goto L_0x002c
        L_0x0126:
            java.lang.String r3 = "INTERRUPTED"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0131
            r0 = 3
            goto L_0x004f
        L_0x0131:
            java.lang.String r3 = "FINISHED"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x013c
            r0 = 1
            goto L_0x004f
        L_0x013c:
            java.lang.String r3 = "ERROR"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x004f
            r0 = 4
            goto L_0x004f
        L_0x0147:
            long[] r3 = r12.zzds
            if (r3 != 0) goto L_0x023a
            r2 = 1
        L_0x014c:
            if (r2 == 0) goto L_0x0150
            r12.zzds = r1
        L_0x0150:
            if (r2 == 0) goto L_0x0156
            r12.zzds = r1
            r0 = r0 | 2
        L_0x0156:
            java.lang.String r1 = "customData"
            boolean r1 = r4.has(r1)
            if (r1 == 0) goto L_0x016b
            java.lang.String r1 = "customData"
            org.json.JSONObject r1 = r4.getJSONObject(r1)
            r12.zzp = r1
            r1 = 0
            r12.zzj = r1
            r0 = r0 | 2
        L_0x016b:
            java.lang.String r1 = "media"
            boolean r1 = r4.has(r1)
            if (r1 == 0) goto L_0x019c
            java.lang.String r1 = "media"
            org.json.JSONObject r1 = r4.getJSONObject(r1)
            com.google.android.gms.cast.MediaInfo r2 = new com.google.android.gms.cast.MediaInfo
            r2.<init>((org.json.JSONObject) r1)
            com.google.android.gms.cast.MediaInfo r3 = r12.zzdo
            if (r3 == 0) goto L_0x018e
            com.google.android.gms.cast.MediaInfo r3 = r12.zzdo
            if (r3 == 0) goto L_0x0192
            com.google.android.gms.cast.MediaInfo r3 = r12.zzdo
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0192
        L_0x018e:
            r12.zzdo = r2
            r0 = r0 | 2
        L_0x0192:
            java.lang.String r2 = "metadata"
            boolean r1 = r1.has(r2)
            if (r1 == 0) goto L_0x019c
            r0 = r0 | 4
        L_0x019c:
            java.lang.String r1 = "currentItemId"
            boolean r1 = r4.has(r1)
            if (r1 == 0) goto L_0x01b2
            java.lang.String r1 = "currentItemId"
            int r1 = r4.getInt(r1)
            int r2 = r12.zzfc
            if (r2 == r1) goto L_0x01b2
            r12.zzfc = r1
            r0 = r0 | 2
        L_0x01b2:
            java.lang.String r1 = "preloadedItemId"
            r2 = 0
            int r1 = r4.optInt(r1, r2)
            int r2 = r12.zzfk
            if (r2 == r1) goto L_0x01c1
            r12.zzfk = r1
            r0 = r0 | 16
        L_0x01c1:
            java.lang.String r1 = "loadingItemId"
            r2 = 0
            int r1 = r4.optInt(r1, r2)
            int r2 = r12.zzfj
            if (r2 == r1) goto L_0x03a6
            r12.zzfj = r1
            r0 = r0 | 2
            r1 = r0
        L_0x01d1:
            com.google.android.gms.cast.MediaInfo r0 = r12.zzdo
            if (r0 != 0) goto L_0x025c
            r0 = -1
        L_0x01d6:
            int r2 = r12.zzfd
            int r3 = r12.zzfe
            int r5 = r12.zzfj
            boolean r0 = zza(r2, r3, r5, r0)
            if (r0 != 0) goto L_0x0371
            r2 = 0
            java.lang.String r0 = "repeatMode"
            boolean r0 = r4.has(r0)
            if (r0 == 0) goto L_0x03a3
            java.lang.String r0 = "repeatMode"
            java.lang.String r0 = r4.getString(r0)
            java.lang.Integer r0 = com.google.android.gms.internal.cast.zzdv.zzw(r0)
            if (r0 != 0) goto L_0x0264
            int r0 = r12.zzfl
        L_0x01f9:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r3 = r12.zzfl
            int r5 = r0.intValue()
            if (r3 == r5) goto L_0x03a3
            int r0 = r0.intValue()
            r12.zzfl = r0
            r0 = 1
        L_0x020c:
            java.lang.String r2 = "items"
            boolean r2 = r4.has(r2)
            if (r2 == 0) goto L_0x02d3
            java.lang.String r2 = "items"
            org.json.JSONArray r5 = r4.getJSONArray(r2)
            int r6 = r5.length()
            android.util.SparseArray r7 = new android.util.SparseArray
            r7.<init>()
            r2 = 0
        L_0x0224:
            if (r2 >= r6) goto L_0x0269
            org.json.JSONObject r3 = r5.getJSONObject(r2)
            java.lang.String r8 = "itemId"
            int r3 = r3.getInt(r8)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r7.put(r2, r3)
            int r2 = r2 + 1
            goto L_0x0224
        L_0x023a:
            long[] r3 = r12.zzds
            int r3 = r3.length
            if (r3 == r6) goto L_0x0242
            r2 = 1
            goto L_0x014c
        L_0x0242:
            r3 = 0
        L_0x0243:
            if (r3 >= r6) goto L_0x014c
            long[] r5 = r12.zzds
            r8 = r5[r3]
            r10 = r1[r3]
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x0252
            r2 = 1
            goto L_0x014c
        L_0x0252:
            int r3 = r3 + 1
            goto L_0x0243
        L_0x0255:
            long[] r3 = r12.zzds
            if (r3 == 0) goto L_0x0150
            r2 = 1
            goto L_0x0150
        L_0x025c:
            com.google.android.gms.cast.MediaInfo r0 = r12.zzdo
            int r0 = r0.getStreamType()
            goto L_0x01d6
        L_0x0264:
            int r0 = r0.intValue()
            goto L_0x01f9
        L_0x0269:
            com.google.android.gms.cast.MediaQueueItem[] r8 = new com.google.android.gms.cast.MediaQueueItem[r6]
            r3 = 0
            r2 = r0
        L_0x026d:
            if (r3 >= r6) goto L_0x02c7
            java.lang.Object r0 = r7.get(r3)
            java.lang.Integer r0 = (java.lang.Integer) r0
            org.json.JSONObject r9 = r5.getJSONObject(r3)
            int r10 = r0.intValue()
            com.google.android.gms.cast.MediaQueueItem r10 = r12.getItemById(r10)
            if (r10 == 0) goto L_0x029d
            boolean r9 = r10.zzi(r9)
            r2 = r2 | r9
            r8[r3] = r10
            int r0 = r0.intValue()
            java.lang.Integer r0 = r12.getIndexById(r0)
            int r0 = r0.intValue()
            if (r3 == r0) goto L_0x02c5
            r0 = 1
        L_0x0299:
            int r3 = r3 + 1
            r2 = r0
            goto L_0x026d
        L_0x029d:
            r2 = 1
            int r0 = r0.intValue()
            int r10 = r12.zzfc
            if (r0 != r10) goto L_0x02be
            com.google.android.gms.cast.MediaInfo r0 = r12.zzdo
            if (r0 == 0) goto L_0x02be
            com.google.android.gms.cast.MediaQueueItem$Builder r0 = new com.google.android.gms.cast.MediaQueueItem$Builder
            com.google.android.gms.cast.MediaInfo r10 = r12.zzdo
            r0.<init>((com.google.android.gms.cast.MediaInfo) r10)
            com.google.android.gms.cast.MediaQueueItem r0 = r0.build()
            r8[r3] = r0
            r0 = r8[r3]
            r0.zzi(r9)
            r0 = r2
            goto L_0x0299
        L_0x02be:
            com.google.android.gms.cast.MediaQueueItem r0 = new com.google.android.gms.cast.MediaQueueItem
            r0.<init>((org.json.JSONObject) r9)
            r8[r3] = r0
        L_0x02c5:
            r0 = r2
            goto L_0x0299
        L_0x02c7:
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r0 = r12.zzfm
            int r0 = r0.size()
            if (r0 == r6) goto L_0x03a0
            r0 = 1
        L_0x02d0:
            r12.zza(r8)
        L_0x02d3:
            if (r0 == 0) goto L_0x02d7
            r1 = r1 | 8
        L_0x02d7:
            java.lang.String r0 = "breakStatus"
            org.json.JSONObject r0 = r4.optJSONObject(r0)
            com.google.android.gms.cast.AdBreakStatus r2 = com.google.android.gms.cast.AdBreakStatus.zzc(r0)
            com.google.android.gms.cast.AdBreakStatus r0 = r12.zzfo
            if (r0 != 0) goto L_0x02e7
            if (r2 != 0) goto L_0x02f3
        L_0x02e7:
            com.google.android.gms.cast.AdBreakStatus r0 = r12.zzfo
            if (r0 == 0) goto L_0x02fc
            com.google.android.gms.cast.AdBreakStatus r0 = r12.zzfo
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x02fc
        L_0x02f3:
            if (r2 == 0) goto L_0x0393
            r0 = 1
        L_0x02f6:
            r12.zzfn = r0
            r12.zzfo = r2
            r1 = r1 | 32
        L_0x02fc:
            java.lang.String r0 = "videoInfo"
            org.json.JSONObject r0 = r4.optJSONObject(r0)
            com.google.android.gms.cast.VideoInfo r0 = com.google.android.gms.cast.VideoInfo.zzk(r0)
            com.google.android.gms.cast.VideoInfo r2 = r12.zzfp
            if (r2 != 0) goto L_0x030c
            if (r0 != 0) goto L_0x0318
        L_0x030c:
            com.google.android.gms.cast.VideoInfo r2 = r12.zzfp
            if (r2 == 0) goto L_0x031c
            com.google.android.gms.cast.VideoInfo r2 = r12.zzfp
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x031c
        L_0x0318:
            r12.zzfp = r0
            r1 = r1 | 64
        L_0x031c:
            java.lang.String r0 = "breakInfo"
            boolean r0 = r4.has(r0)
            if (r0 == 0) goto L_0x0335
            com.google.android.gms.cast.MediaInfo r0 = r12.zzdo
            if (r0 == 0) goto L_0x0335
            com.google.android.gms.cast.MediaInfo r0 = r12.zzdo
            java.lang.String r2 = "breakInfo"
            org.json.JSONObject r2 = r4.getJSONObject(r2)
            r0.zzd((org.json.JSONObject) r2)
            r1 = r1 | 2
        L_0x0335:
            boolean r0 = com.google.android.gms.internal.cast.zzdl.zzaaa
            if (r0 == 0) goto L_0x0356
            java.lang.String r0 = "queueData"
            boolean r0 = r4.has(r0)
            if (r0 == 0) goto L_0x0356
            com.google.android.gms.cast.MediaQueueData$Builder r0 = new com.google.android.gms.cast.MediaQueueData$Builder
            r0.<init>()
            java.lang.String r2 = "queueData"
            org.json.JSONObject r2 = r4.getJSONObject(r2)
            com.google.android.gms.cast.MediaQueueData$Builder r0 = r0.zzh(r2)
            com.google.android.gms.cast.MediaQueueData r0 = r0.build()
            r12.zzdy = r0
        L_0x0356:
            boolean r0 = com.google.android.gms.internal.cast.zzdl.zzaaa
            if (r0 == 0) goto L_0x0370
            java.lang.String r0 = "liveSeekableRange"
            boolean r0 = r4.has(r0)
            if (r0 == 0) goto L_0x0396
            java.lang.String r0 = "liveSeekableRange"
            org.json.JSONObject r0 = r4.optJSONObject(r0)
            com.google.android.gms.cast.MediaLiveSeekableRange r0 = com.google.android.gms.cast.MediaLiveSeekableRange.zze(r0)
            r12.zzfq = r0
            r1 = r1 | 2
        L_0x0370:
            return r1
        L_0x0371:
            r0 = 0
            r12.zzfc = r0
            r0 = 0
            r12.zzfj = r0
            r0 = 0
            r12.zzfk = r0
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r0 = r12.zzfm
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x02d7
            r0 = 0
            r12.zzfl = r0
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r0 = r12.zzfm
            r0.clear()
            android.util.SparseArray<java.lang.Integer> r0 = r12.zzfr
            r0.clear()
            r1 = r1 | 8
            goto L_0x02d7
        L_0x0393:
            r0 = 0
            goto L_0x02f6
        L_0x0396:
            com.google.android.gms.cast.MediaLiveSeekableRange r0 = r12.zzfq
            if (r0 == 0) goto L_0x039c
            r1 = r1 | 2
        L_0x039c:
            r0 = 0
            r12.zzfq = r0
            goto L_0x0370
        L_0x03a0:
            r0 = r2
            goto L_0x02d0
        L_0x03a3:
            r0 = r2
            goto L_0x020c
        L_0x03a6:
            r1 = r0
            goto L_0x01d1
        L_0x03a9:
            r0 = r2
            goto L_0x0057
        L_0x03ac:
            r2 = r0
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaStatus.zza(org.json.JSONObject, int):int");
    }

    private static JSONObject zzj(@NonNull JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("extendedStatus");
        if (optJSONObject == null) {
            return jSONObject;
        }
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                arrayList.add(keys.next());
            }
            JSONObject jSONObject2 = new JSONObject(jSONObject, (String[]) arrayList.toArray(new String[0]));
            Iterator<String> keys2 = optJSONObject.keys();
            while (keys2.hasNext()) {
                String next = keys2.next();
                jSONObject2.put(next, optJSONObject.get(next));
            }
            jSONObject2.remove("extendedStatus");
            return jSONObject2;
        } catch (JSONException e) {
            return jSONObject;
        }
    }

    public final boolean zzl() {
        return zza(this.zzfd, this.zzfe, this.zzfj, this.zzdo == null ? -1 : this.zzdo.getStreamType());
    }

    private static boolean zza(int i, int i2, int i3, int i4) {
        if (i != 1) {
            return false;
        }
        switch (i2) {
            case 1:
            case 3:
                if (i3 == 0) {
                    return true;
                }
                return false;
            case 2:
                if (i4 != 2) {
                    return true;
                }
                return false;
            default:
                return true;
        }
    }

    public MediaQueueItem getItemById(int i) {
        Integer num = this.zzfr.get(i);
        if (num == null) {
            return null;
        }
        return this.zzfm.get(num.intValue());
    }

    public MediaQueueItem getItemByIndex(int i) {
        if (i < 0 || i >= this.zzfm.size()) {
            return null;
        }
        return this.zzfm.get(i);
    }

    public Integer getIndexById(int i) {
        return this.zzfr.get(i);
    }

    private final void zza(MediaQueueItem[] mediaQueueItemArr) {
        this.zzfm.clear();
        this.zzfr.clear();
        for (int i = 0; i < mediaQueueItemArr.length; i++) {
            MediaQueueItem mediaQueueItem = mediaQueueItemArr[i];
            this.zzfm.add(mediaQueueItem);
            this.zzfr.put(mediaQueueItem.getItemId(), Integer.valueOf(i));
        }
    }

    public boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        boolean z3 = this.zzp == null;
        if (mediaStatus.zzp == null) {
            z = true;
        } else {
            z = false;
        }
        if (z3 != z || this.zzfb != mediaStatus.zzfb || this.zzfc != mediaStatus.zzfc || this.zzdr != mediaStatus.zzdr || this.zzfd != mediaStatus.zzfd || this.zzfe != mediaStatus.zzfe || this.zzff != mediaStatus.zzff || this.zzfh != mediaStatus.zzfh || this.zzfi != mediaStatus.zzfi || this.zzfj != mediaStatus.zzfj || this.zzfk != mediaStatus.zzfk || this.zzfl != mediaStatus.zzfl || !Arrays.equals(this.zzds, mediaStatus.zzds) || !zzdc.zza(Long.valueOf(this.zzfg), Long.valueOf(mediaStatus.zzfg)) || !zzdc.zza(this.zzfm, mediaStatus.zzfm) || !zzdc.zza(this.zzdo, mediaStatus.zzdo)) {
            return false;
        }
        if (this.zzp == null || mediaStatus.zzp == null || JsonUtils.areJsonValuesEquivalent(this.zzp, mediaStatus.zzp)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2 || this.zzfn != mediaStatus.isPlayingAd() || !zzdc.zza(this.zzfo, mediaStatus.zzfo) || !zzdc.zza(this.zzfp, mediaStatus.zzfp) || !zzdc.zza(this.zzfq, mediaStatus.zzfq) || !Objects.equal(this.zzdy, mediaStatus.zzdy)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzdo, Long.valueOf(this.zzfb), Integer.valueOf(this.zzfc), Double.valueOf(this.zzdr), Integer.valueOf(this.zzfd), Integer.valueOf(this.zzfe), Long.valueOf(this.zzff), Long.valueOf(this.zzfg), Double.valueOf(this.zzfh), Boolean.valueOf(this.zzfi), Integer.valueOf(Arrays.hashCode(this.zzds)), Integer.valueOf(this.zzfj), Integer.valueOf(this.zzfk), String.valueOf(this.zzp), Integer.valueOf(this.zzfl), this.zzfm, Boolean.valueOf(this.zzfn), this.zzfo, this.zzfp, this.zzfq, this.zzdy);
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMediaInfo(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzfb);
        SafeParcelWriter.writeInt(parcel, 4, getCurrentItemId());
        SafeParcelWriter.writeDouble(parcel, 5, getPlaybackRate());
        SafeParcelWriter.writeInt(parcel, 6, getPlayerState());
        SafeParcelWriter.writeInt(parcel, 7, getIdleReason());
        SafeParcelWriter.writeLong(parcel, 8, getStreamPosition());
        SafeParcelWriter.writeLong(parcel, 9, this.zzfg);
        SafeParcelWriter.writeDouble(parcel, 10, getStreamVolume());
        SafeParcelWriter.writeBoolean(parcel, 11, isMute());
        SafeParcelWriter.writeLongArray(parcel, 12, getActiveTrackIds(), false);
        SafeParcelWriter.writeInt(parcel, 13, getLoadingItemId());
        SafeParcelWriter.writeInt(parcel, 14, getPreloadedItemId());
        SafeParcelWriter.writeString(parcel, 15, this.zzj, false);
        SafeParcelWriter.writeInt(parcel, 16, this.zzfl);
        SafeParcelWriter.writeTypedList(parcel, 17, this.zzfm, false);
        SafeParcelWriter.writeBoolean(parcel, 18, isPlayingAd());
        SafeParcelWriter.writeParcelable(parcel, 19, getAdBreakStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 20, getVideoInfo(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public AdBreakInfo getCurrentAdBreak() {
        if (this.zzfo == null || this.zzdo == null) {
            return null;
        }
        String breakId = this.zzfo.getBreakId();
        if (TextUtils.isEmpty(breakId)) {
            return null;
        }
        List<AdBreakInfo> adBreaks = this.zzdo.getAdBreaks();
        if (adBreaks == null || adBreaks.isEmpty()) {
            return null;
        }
        for (AdBreakInfo next : adBreaks) {
            if (breakId.equals(next.getId())) {
                return next;
            }
        }
        return null;
    }

    public AdBreakClipInfo getCurrentAdBreakClip() {
        if (this.zzfo == null || this.zzdo == null) {
            return null;
        }
        String breakClipId = this.zzfo.getBreakClipId();
        if (TextUtils.isEmpty(breakClipId)) {
            return null;
        }
        List<AdBreakClipInfo> adBreakClips = this.zzdo.getAdBreakClips();
        if (adBreakClips == null || adBreakClips.isEmpty()) {
            return null;
        }
        for (AdBreakClipInfo next : adBreakClips) {
            if (breakClipId.equals(next.getId())) {
                return next;
            }
        }
        return null;
    }
}
