package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzcl;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzdn;
import com.google.android.gms.internal.cast.zzdp;
import com.google.android.gms.internal.cast.zzdr;
import com.google.android.gms.internal.cast.zzds;
import com.google.android.gms.internal.cast.zzdu;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

@SuppressLint({"MissingRemoteException"})
@Deprecated
public class RemoteMediaPlayer implements Cast.MessageReceivedCallback {
    public static final String NAMESPACE = zzdn.NAMESPACE;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    /* access modifiers changed from: private */
    public final Object lock;
    /* access modifiers changed from: private */
    public final zzdn zzfu;
    /* access modifiers changed from: private */
    public final zza zzfv;
    private OnPreloadStatusUpdatedListener zzfw;
    private OnQueueStatusUpdatedListener zzfx;
    private OnMetadataUpdatedListener zzfy;
    private OnStatusUpdatedListener zzfz;

    @Deprecated
    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();
    }

    @Deprecated
    public interface OnMetadataUpdatedListener {
        void onMetadataUpdated();
    }

    @Deprecated
    public interface OnPreloadStatusUpdatedListener {
        void onPreloadStatusUpdated();
    }

    @Deprecated
    public interface OnQueueStatusUpdatedListener {
        void onQueueStatusUpdated();
    }

    @Deprecated
    public interface OnStatusUpdatedListener {
        void onStatusUpdated();
    }

    public RemoteMediaPlayer() {
        this(new zzdn((String) null));
    }

    private class zza implements zzdr {
        private GoogleApiClient zzgx;
        private long zzgy = 0;

        public zza() {
        }

        public final void zza(GoogleApiClient googleApiClient) {
            this.zzgx = googleApiClient;
        }

        public final void zza(String str, String str2, long j, String str3) {
            if (this.zzgx == null) {
                throw new IllegalStateException("No GoogleApiClient available");
            }
            Cast.CastApi.sendMessage(this.zzgx, str, str2).setResultCallback(new zzbr(this, j));
        }

        public final long zzm() {
            long j = this.zzgy + 1;
            this.zzgy = j;
            return j;
        }
    }

    @VisibleForTesting
    private RemoteMediaPlayer(zzdn zzdn) {
        this.lock = new Object();
        this.zzfu = zzdn;
        this.zzfu.zza((zzdp) new zzav(this));
        this.zzfv = new zza();
        this.zzfu.zza(this.zzfv);
    }

    private static final class zzc implements MediaChannelResult {
        private final Status zzhf;
        private final JSONObject zzp;

        zzc(Status status, JSONObject jSONObject) {
            this.zzhf = status;
            this.zzp = jSONObject;
        }

        public final Status getStatus() {
            return this.zzhf;
        }

        public final JSONObject getCustomData() {
            return this.zzp;
        }
    }

    @VisibleForTesting
    abstract class zzb extends zzcl<MediaChannelResult> {
        zzdu zzgz;
        private final WeakReference<GoogleApiClient> zzha;

        zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.zzha = new WeakReference<>(googleApiClient);
            this.zzgz = new zzbt(this, RemoteMediaPlayer.this);
        }

        /* access modifiers changed from: package-private */
        public abstract void zzb(zzct zzct) throws zzds;

        /* access modifiers changed from: protected */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ void doExecute(com.google.android.gms.common.api.Api.AnyClient r4) throws android.os.RemoteException {
            /*
                r3 = this;
                com.google.android.gms.internal.cast.zzct r4 = (com.google.android.gms.internal.cast.zzct) r4
                com.google.android.gms.cast.RemoteMediaPlayer r0 = com.google.android.gms.cast.RemoteMediaPlayer.this
                java.lang.Object r1 = r0.lock
                monitor-enter(r1)
                java.lang.ref.WeakReference<com.google.android.gms.common.api.GoogleApiClient> r0 = r3.zzha     // Catch:{ all -> 0x003d }
                java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x003d }
                com.google.android.gms.common.api.GoogleApiClient r0 = (com.google.android.gms.common.api.GoogleApiClient) r0     // Catch:{ all -> 0x003d }
                if (r0 != 0) goto L_0x0025
                com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x003d }
                r2 = 2100(0x834, float:2.943E-42)
                r0.<init>(r2)     // Catch:{ all -> 0x003d }
                com.google.android.gms.common.api.Result r0 = r3.createFailedResult(r0)     // Catch:{ all -> 0x003d }
                com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x003d }
                r3.setResult(r0)     // Catch:{ all -> 0x003d }
                monitor-exit(r1)     // Catch:{ all -> 0x003d }
            L_0x0024:
                return
            L_0x0025:
                com.google.android.gms.cast.RemoteMediaPlayer r2 = com.google.android.gms.cast.RemoteMediaPlayer.this     // Catch:{ all -> 0x003d }
                com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzfv     // Catch:{ all -> 0x003d }
                r2.zza(r0)     // Catch:{ all -> 0x003d }
                r3.zzb(r4)     // Catch:{ IllegalArgumentException -> 0x0040, Throwable -> 0x0042 }
            L_0x0031:
                com.google.android.gms.cast.RemoteMediaPlayer r0 = com.google.android.gms.cast.RemoteMediaPlayer.this     // Catch:{ all -> 0x003d }
                com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzfv     // Catch:{ all -> 0x003d }
                r2 = 0
                r0.zza(r2)     // Catch:{ all -> 0x003d }
                monitor-exit(r1)     // Catch:{ all -> 0x003d }
                goto L_0x0024
            L_0x003d:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x003d }
                throw r0
            L_0x0040:
                r0 = move-exception
                throw r0     // Catch:{ all -> 0x003d }
            L_0x0042:
                r0 = move-exception
                com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x003d }
                r2 = 2100(0x834, float:2.943E-42)
                r0.<init>(r2)     // Catch:{ all -> 0x003d }
                com.google.android.gms.common.api.Result r0 = r3.createFailedResult(r0)     // Catch:{ all -> 0x003d }
                com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x003d }
                r3.setResult(r0)     // Catch:{ all -> 0x003d }
                goto L_0x0031
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.RemoteMediaPlayer.zzb.doExecute(com.google.android.gms.common.api.Api$AnyClient):void");
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzbs(this, status);
        }
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo) {
        return load(googleApiClient, mediaInfo, true, -1, (long[]) null, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z) {
        return load(googleApiClient, mediaInfo, z, -1, (long[]) null, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j) {
        return load(googleApiClient, mediaInfo, z, j, (long[]) null, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j, JSONObject jSONObject) {
        return load(googleApiClient, mediaInfo, z, j, (long[]) null, jSONObject);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbe(this, googleApiClient, mediaInfo, z, j, jArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient googleApiClient) {
        return pause(googleApiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbl(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient googleApiClient) {
        return stop(googleApiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbk(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient googleApiClient) {
        return play(googleApiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbn(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j) {
        return seek(googleApiClient, j, 0, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j, int i) {
        return seek(googleApiClient, j, i, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbm(this, googleApiClient, j, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient googleApiClient, double d) throws IllegalArgumentException {
        return setStreamVolume(googleApiClient, d, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient googleApiClient, double d, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzbp(this, googleApiClient, d, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient googleApiClient, boolean z) {
        return setStreamMute(googleApiClient, z, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient googleApiClient, boolean z, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbo(this, googleApiClient, z, jSONObject));
    }

    public PendingResult<MediaChannelResult> requestStatus(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzbq(this, googleApiClient));
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(GoogleApiClient googleApiClient, long[] jArr) {
        return googleApiClient.execute(new zzau(this, googleApiClient, jArr));
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(GoogleApiClient googleApiClient, TextTrackStyle textTrackStyle) {
        return googleApiClient.execute(new zzax(this, googleApiClient, textTrackStyle));
    }

    public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(googleApiClient, mediaQueueItemArr, i, i2, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzaw(this, googleApiClient, mediaQueueItemArr, i, i2, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueInsertItems(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzaz(this, googleApiClient, mediaQueueItemArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueAppendItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(googleApiClient, new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, JSONObject jSONObject) {
        return queueInsertAndPlayItem(googleApiClient, mediaQueueItem, i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        return googleApiClient.execute(new zzay(this, googleApiClient, mediaQueueItem, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueUpdateItems(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbb(this, googleApiClient, mediaQueueItemArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItems(GoogleApiClient googleApiClient, int[] iArr, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzba(this, googleApiClient, iArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueReorderItems(GoogleApiClient googleApiClient, int[] iArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzbd(this, googleApiClient, iArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queuePrev(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbc(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueNext(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbf(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueSetRepeatMode(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbh(this, googleApiClient, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItem(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbg(this, googleApiClient, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return queueJumpToItem(googleApiClient, i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient googleApiClient, int i, long j, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbj(this, googleApiClient, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbi(this, googleApiClient, i, i2, jSONObject));
    }

    /* access modifiers changed from: private */
    public final int zzf(int i) {
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return -1;
        }
        for (int i2 = 0; i2 < mediaStatus.getQueueItemCount(); i2++) {
            if (mediaStatus.getQueueItem(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public long getApproximateStreamPosition() {
        long approximateStreamPosition;
        synchronized (this.lock) {
            approximateStreamPosition = this.zzfu.getApproximateStreamPosition();
        }
        return approximateStreamPosition;
    }

    public long getStreamDuration() {
        long streamDuration;
        synchronized (this.lock) {
            streamDuration = this.zzfu.getStreamDuration();
        }
        return streamDuration;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus;
        synchronized (this.lock) {
            mediaStatus = this.zzfu.getMediaStatus();
        }
        return mediaStatus;
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo;
        synchronized (this.lock) {
            mediaInfo = this.zzfu.getMediaInfo();
        }
        return mediaInfo;
    }

    public void setOnStatusUpdatedListener(OnStatusUpdatedListener onStatusUpdatedListener) {
        this.zzfz = onStatusUpdatedListener;
    }

    /* access modifiers changed from: private */
    public final void onStatusUpdated() {
        if (this.zzfz != null) {
            this.zzfz.onStatusUpdated();
        }
    }

    public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener onMetadataUpdatedListener) {
        this.zzfy = onMetadataUpdatedListener;
    }

    /* access modifiers changed from: private */
    public final void onMetadataUpdated() {
        if (this.zzfy != null) {
            this.zzfy.onMetadataUpdated();
        }
    }

    public void setOnQueueStatusUpdatedListener(OnQueueStatusUpdatedListener onQueueStatusUpdatedListener) {
        this.zzfx = onQueueStatusUpdatedListener;
    }

    /* access modifiers changed from: private */
    public final void onQueueStatusUpdated() {
        if (this.zzfx != null) {
            this.zzfx.onQueueStatusUpdated();
        }
    }

    public void setOnPreloadStatusUpdatedListener(OnPreloadStatusUpdatedListener onPreloadStatusUpdatedListener) {
        this.zzfw = onPreloadStatusUpdatedListener;
    }

    /* access modifiers changed from: private */
    public final void onPreloadStatusUpdated() {
        if (this.zzfw != null) {
            this.zzfw.onPreloadStatusUpdated();
        }
    }

    public String getNamespace() {
        return this.zzfu.getNamespace();
    }

    public void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzfu.zzp(str2);
    }
}
