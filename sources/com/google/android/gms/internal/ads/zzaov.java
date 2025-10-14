package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import com.google.android.gms.ads.internal.zzbv;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzadh
@TargetApi(14)
public final class zzaov extends zzapg implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map<Integer, String> zzcwo = new HashMap();
    private final zzapx zzcwp;
    private final boolean zzcwq;
    private int zzcwr = 0;
    private int zzcws = 0;
    private MediaPlayer zzcwt;
    private Uri zzcwu;
    private int zzcwv;
    private int zzcww;
    private int zzcwx;
    private int zzcwy;
    private int zzcwz;
    private zzapu zzcxa;
    private boolean zzcxb;
    private int zzcxc;
    /* access modifiers changed from: private */
    public zzapf zzcxd;

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            zzcwo.put(-1004, "MEDIA_ERROR_IO");
            zzcwo.put(-1007, "MEDIA_ERROR_MALFORMED");
            zzcwo.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
            zzcwo.put(-110, "MEDIA_ERROR_TIMED_OUT");
            zzcwo.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzcwo.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzcwo.put(1, "MEDIA_ERROR_UNKNOWN");
        zzcwo.put(1, "MEDIA_INFO_UNKNOWN");
        zzcwo.put(700, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzcwo.put(701, "MEDIA_INFO_BUFFERING_START");
        zzcwo.put(702, "MEDIA_INFO_BUFFERING_END");
        zzcwo.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzcwo.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        zzcwo.put(802, "MEDIA_INFO_METADATA_UPDATE");
        if (Build.VERSION.SDK_INT >= 19) {
            zzcwo.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzcwo.put(902, "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzaov(Context context, boolean z, boolean z2, zzapv zzapv, zzapx zzapx) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzcwp = zzapx;
        this.zzcxb = z;
        this.zzcwq = z2;
        this.zzcwp.zzb(this);
    }

    private final void zza(float f) {
        if (this.zzcwt != null) {
            try {
                this.zzcwt.setVolume(f, f);
            } catch (IllegalStateException e) {
            }
        } else {
            zzakb.zzdk("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private final void zzag(int i) {
        if (i == 3) {
            this.zzcwp.zztt();
            this.zzcxl.zztt();
        } else if (this.zzcwr == 3) {
            this.zzcwp.zztu();
            this.zzcxl.zztu();
        }
        this.zzcwr = i;
    }

    private final void zzag(boolean z) {
        zzakb.v("AdMediaPlayerView release");
        if (this.zzcxa != null) {
            this.zzcxa.zzti();
            this.zzcxa = null;
        }
        if (this.zzcwt != null) {
            this.zzcwt.reset();
            this.zzcwt.release();
            this.zzcwt = null;
            zzag(0);
            if (z) {
                this.zzcws = 0;
                this.zzcws = 0;
            }
        }
    }

    private final void zzsq() {
        SurfaceTexture surfaceTexture;
        zzakb.v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture2 = getSurfaceTexture();
        if (this.zzcwu != null && surfaceTexture2 != null) {
            zzag(false);
            try {
                zzbv.zzfb();
                this.zzcwt = new MediaPlayer();
                this.zzcwt.setOnBufferingUpdateListener(this);
                this.zzcwt.setOnCompletionListener(this);
                this.zzcwt.setOnErrorListener(this);
                this.zzcwt.setOnInfoListener(this);
                this.zzcwt.setOnPreparedListener(this);
                this.zzcwt.setOnVideoSizeChangedListener(this);
                this.zzcwx = 0;
                if (this.zzcxb) {
                    this.zzcxa = new zzapu(getContext());
                    this.zzcxa.zza(surfaceTexture2, getWidth(), getHeight());
                    this.zzcxa.start();
                    surfaceTexture = this.zzcxa.zztj();
                    if (surfaceTexture == null) {
                        this.zzcxa.zzti();
                        this.zzcxa = null;
                    }
                    this.zzcwt.setDataSource(getContext(), this.zzcwu);
                    zzbv.zzfc();
                    this.zzcwt.setSurface(new Surface(surfaceTexture));
                    this.zzcwt.setAudioStreamType(3);
                    this.zzcwt.setScreenOnWhilePlaying(true);
                    this.zzcwt.prepareAsync();
                    zzag(1);
                }
                surfaceTexture = surfaceTexture2;
                this.zzcwt.setDataSource(getContext(), this.zzcwu);
                zzbv.zzfc();
                this.zzcwt.setSurface(new Surface(surfaceTexture));
                this.zzcwt.setAudioStreamType(3);
                this.zzcwt.setScreenOnWhilePlaying(true);
                this.zzcwt.prepareAsync();
                zzag(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzcwu);
                zzakb.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzcwt, 1, 0);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0034 A[LOOP:0: B:9:0x0034->B:14:0x004f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzsr() {
        /*
            r8 = this;
            boolean r0 = r8.zzcwq
            if (r0 != 0) goto L_0x0005
        L_0x0004:
            return
        L_0x0005:
            boolean r0 = r8.zzss()
            if (r0 == 0) goto L_0x0004
            android.media.MediaPlayer r0 = r8.zzcwt
            int r0 = r0.getCurrentPosition()
            if (r0 <= 0) goto L_0x0004
            int r0 = r8.zzcws
            r1 = 3
            if (r0 == r1) goto L_0x0004
            java.lang.String r0 = "AdMediaPlayerView nudging MediaPlayer"
            com.google.android.gms.internal.ads.zzakb.v(r0)
            r0 = 0
            r8.zza((float) r0)
            android.media.MediaPlayer r0 = r8.zzcwt
            r0.start()
            android.media.MediaPlayer r0 = r8.zzcwt
            int r0 = r0.getCurrentPosition()
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r2 = r1.currentTimeMillis()
        L_0x0034:
            boolean r1 = r8.zzss()
            if (r1 == 0) goto L_0x0051
            android.media.MediaPlayer r1 = r8.zzcwt
            int r1 = r1.getCurrentPosition()
            if (r1 != r0) goto L_0x0051
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r4 = r1.currentTimeMillis()
            long r4 = r4 - r2
            r6 = 250(0xfa, double:1.235E-321)
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x0034
        L_0x0051:
            android.media.MediaPlayer r0 = r8.zzcwt
            r0.pause()
            r8.zzst()
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaov.zzsr():void");
    }

    private final boolean zzss() {
        return (this.zzcwt == null || this.zzcwr == -1 || this.zzcwr == 0 || this.zzcwr == 1) ? false : true;
    }

    public final int getCurrentPosition() {
        if (zzss()) {
            return this.zzcwt.getCurrentPosition();
        }
        return 0;
    }

    public final int getDuration() {
        if (zzss()) {
            return this.zzcwt.getDuration();
        }
        return -1;
    }

    public final int getVideoHeight() {
        if (this.zzcwt != null) {
            return this.zzcwt.getVideoHeight();
        }
        return 0;
    }

    public final int getVideoWidth() {
        if (this.zzcwt != null) {
            return this.zzcwt.getVideoWidth();
        }
        return 0;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzcwx = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzakb.v("AdMediaPlayerView completion");
        zzag(5);
        this.zzcws = 5;
        zzakk.zzcrm.post(new zzaoy(this));
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzcwo.get(Integer.valueOf(i));
        String str2 = zzcwo.get(Integer.valueOf(i2));
        zzakb.zzdk(new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(str).append(":").append(str2).toString());
        zzag(-1);
        this.zzcws = -1;
        zzakk.zzcrm.post(new zzaoz(this, str, str2));
        return true;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzcwo.get(Integer.valueOf(i));
        String str2 = zzcwo.get(Integer.valueOf(i2));
        zzakb.v(new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(str).append(":").append(str2).toString());
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0090, code lost:
        if (r1 > r2) goto L_0x003f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            r3 = 1073741824(0x40000000, float:2.0)
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            int r0 = r7.zzcwv
            int r1 = getDefaultSize(r0, r8)
            int r0 = r7.zzcww
            int r0 = getDefaultSize(r0, r9)
            int r2 = r7.zzcwv
            if (r2 <= 0) goto L_0x0092
            int r2 = r7.zzcww
            if (r2 <= 0) goto L_0x0092
            com.google.android.gms.internal.ads.zzapu r2 = r7.zzcxa
            if (r2 != 0) goto L_0x0092
            int r4 = android.view.View.MeasureSpec.getMode(r8)
            int r2 = android.view.View.MeasureSpec.getSize(r8)
            int r5 = android.view.View.MeasureSpec.getMode(r9)
            int r0 = android.view.View.MeasureSpec.getSize(r9)
            if (r4 != r3) goto L_0x0078
            if (r5 != r3) goto L_0x0078
            int r1 = r7.zzcwv
            int r1 = r1 * r0
            int r3 = r7.zzcww
            int r3 = r3 * r2
            if (r1 >= r3) goto L_0x0069
            int r1 = r7.zzcwv
            int r1 = r1 * r0
            int r2 = r7.zzcww
            int r1 = r1 / r2
            r2 = r1
        L_0x003f:
            r7.setMeasuredDimension(r2, r0)
            com.google.android.gms.internal.ads.zzapu r1 = r7.zzcxa
            if (r1 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzapu r1 = r7.zzcxa
            r1.zzh(r2, r0)
        L_0x004b:
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 16
            if (r1 != r3) goto L_0x0068
            int r1 = r7.zzcwy
            if (r1 <= 0) goto L_0x0059
            int r1 = r7.zzcwy
            if (r1 != r2) goto L_0x0061
        L_0x0059:
            int r1 = r7.zzcwz
            if (r1 <= 0) goto L_0x0064
            int r1 = r7.zzcwz
            if (r1 == r0) goto L_0x0064
        L_0x0061:
            r7.zzsr()
        L_0x0064:
            r7.zzcwy = r2
            r7.zzcwz = r0
        L_0x0068:
            return
        L_0x0069:
            int r1 = r7.zzcwv
            int r1 = r1 * r0
            int r3 = r7.zzcww
            int r3 = r3 * r2
            if (r1 <= r3) goto L_0x003f
            int r0 = r7.zzcww
            int r0 = r0 * r2
            int r1 = r7.zzcwv
            int r0 = r0 / r1
            goto L_0x003f
        L_0x0078:
            if (r4 != r3) goto L_0x0086
            int r1 = r7.zzcww
            int r1 = r1 * r2
            int r3 = r7.zzcwv
            int r1 = r1 / r3
            if (r5 != r6) goto L_0x0084
            if (r1 > r0) goto L_0x003f
        L_0x0084:
            r0 = r1
            goto L_0x003f
        L_0x0086:
            if (r5 != r3) goto L_0x0094
            int r1 = r7.zzcwv
            int r1 = r1 * r0
            int r3 = r7.zzcww
            int r1 = r1 / r3
            if (r4 != r6) goto L_0x0092
            if (r1 > r2) goto L_0x003f
        L_0x0092:
            r2 = r1
            goto L_0x003f
        L_0x0094:
            int r1 = r7.zzcwv
            int r3 = r7.zzcww
            if (r5 != r6) goto L_0x00ad
            if (r3 <= r0) goto L_0x00ad
            int r1 = r7.zzcwv
            int r1 = r1 * r0
            int r3 = r7.zzcww
            int r1 = r1 / r3
        L_0x00a2:
            if (r4 != r6) goto L_0x0092
            if (r1 <= r2) goto L_0x0092
            int r0 = r7.zzcww
            int r0 = r0 * r2
            int r1 = r7.zzcwv
            int r0 = r0 / r1
            goto L_0x003f
        L_0x00ad:
            r0 = r3
            goto L_0x00a2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaov.onMeasure(int, int):void");
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzakb.v("AdMediaPlayerView prepared");
        zzag(2);
        this.zzcwp.zzsv();
        zzakk.zzcrm.post(new zzaox(this));
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        if (this.zzcxc != 0) {
            seekTo(this.zzcxc);
        }
        zzsr();
        int i = this.zzcwv;
        zzakb.zzdj(new StringBuilder(62).append("AdMediaPlayerView stream dimensions: ").append(i).append(" x ").append(this.zzcww).toString());
        if (this.zzcws == 3) {
            play();
        }
        zzst();
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzakb.v("AdMediaPlayerView surface created");
        zzsq();
        zzakk.zzcrm.post(new zzapa(this));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzakb.v("AdMediaPlayerView surface destroyed");
        if (this.zzcwt != null && this.zzcxc == 0) {
            this.zzcxc = this.zzcwt.getCurrentPosition();
        }
        if (this.zzcxa != null) {
            this.zzcxa.zzti();
        }
        zzakk.zzcrm.post(new zzapc(this));
        zzag(true);
        return true;
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        boolean z = true;
        zzakb.v("AdMediaPlayerView surface changed");
        boolean z2 = this.zzcws == 3;
        if (!(this.zzcwv == i && this.zzcww == i2)) {
            z = false;
        }
        if (this.zzcwt != null && z2 && z) {
            if (this.zzcxc != 0) {
                seekTo(this.zzcxc);
            }
            play();
        }
        if (this.zzcxa != null) {
            this.zzcxa.zzh(i, i2);
        }
        zzakk.zzcrm.post(new zzapb(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzcwp.zzc(this);
        this.zzcxk.zza(surfaceTexture, this.zzcxd);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        zzakb.v(new StringBuilder(57).append("AdMediaPlayerView size changed: ").append(i).append(" x ").append(i2).toString());
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        if (this.zzcwv != 0 && this.zzcww != 0) {
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        zzakb.v(new StringBuilder(58).append("AdMediaPlayerView window visibility changed to ").append(i).toString());
        zzakk.zzcrm.post(new zzaow(this, i));
        super.onWindowVisibilityChanged(i);
    }

    public final void pause() {
        zzakb.v("AdMediaPlayerView pause");
        if (zzss() && this.zzcwt.isPlaying()) {
            this.zzcwt.pause();
            zzag(4);
            zzakk.zzcrm.post(new zzape(this));
        }
        this.zzcws = 4;
    }

    public final void play() {
        zzakb.v("AdMediaPlayerView play");
        if (zzss()) {
            this.zzcwt.start();
            zzag(3);
            this.zzcxk.zzsw();
            zzakk.zzcrm.post(new zzapd(this));
        }
        this.zzcws = 3;
    }

    public final void seekTo(int i) {
        zzakb.v(new StringBuilder(34).append("AdMediaPlayerView seek ").append(i).toString());
        if (zzss()) {
            this.zzcwt.seekTo(i);
            this.zzcxc = 0;
            return;
        }
        this.zzcxc = i;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzhl zzd = zzhl.zzd(parse);
        if (zzd != null) {
            parse = Uri.parse(zzd.url);
        }
        this.zzcwu = parse;
        this.zzcxc = 0;
        zzsq();
        requestLayout();
        invalidate();
    }

    public final void stop() {
        zzakb.v("AdMediaPlayerView stop");
        if (this.zzcwt != null) {
            this.zzcwt.stop();
            this.zzcwt.release();
            this.zzcwt = null;
            zzag(0);
            this.zzcws = 0;
        }
        this.zzcwp.onStop();
    }

    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        return new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length()).append(name).append("@").append(hexString).toString();
    }

    public final void zza(float f, float f2) {
        if (this.zzcxa != null) {
            this.zzcxa.zzb(f, f2);
        }
    }

    public final void zza(zzapf zzapf) {
        this.zzcxd = zzapf;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzah(int i) {
        if (this.zzcxd != null) {
            this.zzcxd.onWindowVisibilityChanged(i);
        }
    }

    public final String zzsp() {
        String valueOf = String.valueOf(this.zzcxb ? " spherical" : "");
        return valueOf.length() != 0 ? "MediaPlayer".concat(valueOf) : new String("MediaPlayer");
    }

    public final void zzst() {
        zza(this.zzcxl.getVolume());
    }
}
