package com.google.android.gms.cast;

import com.google.android.gms.internal.cast.zzdp;

final class zzav implements zzdp {
    private final /* synthetic */ RemoteMediaPlayer zzgb;

    zzav(RemoteMediaPlayer remoteMediaPlayer) {
        this.zzgb = remoteMediaPlayer;
    }

    public final void onStatusUpdated() {
        this.zzgb.onStatusUpdated();
    }

    public final void onMetadataUpdated() {
        this.zzgb.onMetadataUpdated();
    }

    public final void onQueueStatusUpdated() {
        this.zzgb.onQueueStatusUpdated();
    }

    public final void onPreloadStatusUpdated() {
        this.zzgb.onPreloadStatusUpdated();
    }

    public final void onAdBreakStatusUpdated() {
    }

    public final void zza(int[] iArr) {
    }

    public final void zza(int[] iArr, int i) {
    }

    public final void zzb(int[] iArr) {
    }

    public final void zzc(int[] iArr) {
    }

    public final void zzb(MediaQueueItem[] mediaQueueItemArr) {
    }
}
