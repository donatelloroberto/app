package com.opentok.android.v3;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.view.PointerIconCompat;
import android.util.SparseArray;
import android.view.View;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.ConnectionResult;
import com.opentok.android.v3.AbstractCapturer;
import com.opentok.android.v3.AbstractRenderer;
import com.opentok.android.v3.Session;
import com.opentok.android.v3.Stream;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;
import java.util.EnumSet;
import java.util.Iterator;

@Keep
public class Publisher {
    private static final LogInterface log = OtLog.LogToken("[publisher]");
    /* access modifiers changed from: private */
    public final AudioLevelListener audioLevelCb;
    /* access modifiers changed from: private */
    public final AudioStatsListener audioStatsCb;
    private final AbstractCapturer capturer;
    private AbstractCapturer.CapturerError capturerErrorHndlr = new AbstractCapturer.CapturerError() {
        public void onError(Exception e) {
            if (0 != Publisher.this.nativeCtx) {
                Publisher.this.onError(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Error", e);
            }
        }
    };
    private final Handler mainThreadHandler;
    /* access modifiers changed from: private */
    public long nativeCtx;
    /* access modifiers changed from: private */
    public final PublisherListener publisherCb;
    /* access modifiers changed from: private */
    public final AbstractRenderer renderer;
    /* access modifiers changed from: private */
    public final VideoStatsListener videoStatsCb;
    private boolean videoStreamStatePauseCache;

    public interface AudioLevelListener {
        void onAudioLevelUpdated(Publisher publisher, float f);
    }

    public interface AudioStatsListener {
        void onAudioStats(Publisher publisher, AudioStats[] audioStatsArr);
    }

    public interface PublisherListener {
        void onError(Publisher publisher, PublisherException publisherException);

        void onStreamCreated(Publisher publisher, Stream stream);

        void onStreamDestroyed(Publisher publisher, Stream stream);
    }

    public interface VideoStatsListener {
        void onVideoStats(Publisher publisher, VideoStats[] videoStatsArr);
    }

    private native void closeNative(long j);

    private native int enableAudioFallbackNative(long j, boolean z);

    private native long finalizeNative(long j);

    private native String getIdNative(long j);

    private native String getNameNative(long j);

    private native boolean getPublishAudioNative(long j);

    private native boolean getPublishVideoNative(long j);

    private native Stream getStreamNative(long j);

    private native int getVideoTypeNative(long j);

    private native long initNative(Context context, String str, boolean z, boolean z2);

    private native boolean isAudioFallbackEnabledNative(long j);

    private static native void registerNatives();

    private native int setMaxAudioBitrateNative(long j, int i);

    private native int setPublishAudioNative(long j, boolean z);

    private native int setPublishVideoNative(long j, boolean z);

    private native int setVideoTypeNative(long j, int i);

    public static class AudioStats {
        public final long audioBytesSent;
        public final long audioPacketsLost;
        public final long audioPacketsSent;
        public final String connectionId;
        public final double startTime;
        public final String subscriberId;
        public final double timeStamp;

        AudioStats(String aConnectionId, String aSubscriberId, long aPktsLost, long aPktsSent, long aBytesSent, double tmStamp, double stTime) {
            this.connectionId = aConnectionId;
            this.subscriberId = aSubscriberId;
            this.audioPacketsLost = aPktsLost;
            this.audioPacketsSent = aPktsSent;
            this.audioBytesSent = aBytesSent;
            this.timeStamp = tmStamp;
            this.startTime = stTime;
        }
    }

    public static class VideoStats {
        public final String connectionId;
        public final double startTime;
        public final String subscriberId;
        public final double timeStamp;
        public final long videoBytesSent;
        public final long videoPacketsLost;
        public final long videoPacketsSent;

        VideoStats(String vConnectionId, String vSubscriberId, long vPktsLost, long vPktsSent, long vBytesSent, double tmStamp, double stTime) {
            this.connectionId = vConnectionId;
            this.subscriberId = vSubscriberId;
            this.videoPacketsLost = vPktsLost;
            this.videoPacketsSent = vPktsSent;
            this.videoBytesSent = vBytesSent;
            this.timeStamp = tmStamp;
            this.startTime = stTime;
        }
    }

    public static class PublisherException extends OpentokException {

        public enum Error {
            UNKNOWN_ERROR(-1),
            SUCCESS(0),
            SESSION_DISCONNECTED(PointerIconCompat.TYPE_ALIAS),
            NULL_OR_INVALID_PARAMETER(PointerIconCompat.TYPE_COPY),
            UNABLE_TO_PUBLISH(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED),
            TIMED_OUT(1541),
            WEBRTC_ERROR(1610),
            INTERNAL_ERROR(CastStatusCodes.AUTHENTICATION_FAILED),
            VIDEO_CAPTURE_FAILED(PathInterpolatorCompat.MAX_NUM_POINTS),
            VIDEO_RENDER_FAILED(4000);
            
            private static final SparseArray<Error> reverseLookup = null;
            public final int errno;

            static {
                reverseLookup = new SparseArray<Error>() {
                    {
                        Iterator it = EnumSet.allOf(Error.class).iterator();
                        while (it.hasNext()) {
                            Error e = (Error) it.next();
                            append(e.errno, e);
                        }
                    }
                };
            }

            private Error(int code) {
                this.errno = code;
            }

            static Error getError(int error) {
                return reverseLookup.get(error);
            }
        }

        PublisherException() {
            this(Error.UNKNOWN_ERROR);
        }

        PublisherException(Error error) {
            this(error, error.name().replace('_', ' '));
        }

        PublisherException(Error error, String message) {
            this(error, message, (Exception) null);
        }

        PublisherException(Error error, String errorMsg, Exception e) {
            super(error.errno, errorMsg, e);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        PublisherException(int r4) {
            /*
                r3 = this;
                r2 = 95
                r1 = 32
                com.opentok.android.v3.Publisher$PublisherException$Error r0 = com.opentok.android.v3.Publisher.PublisherException.Error.getError(r4)
                if (r0 == 0) goto L_0x001a
                com.opentok.android.v3.Publisher$PublisherException$Error r0 = com.opentok.android.v3.Publisher.PublisherException.Error.getError(r4)
                java.lang.String r0 = r0.name()
                java.lang.String r0 = r0.replace(r2, r1)
            L_0x0016:
                r3.<init>(r4, r0)
                return
            L_0x001a:
                com.opentok.android.v3.Publisher$PublisherException$Error r0 = com.opentok.android.v3.Publisher.PublisherException.Error.UNKNOWN_ERROR
                java.lang.String r0 = r0.name()
                java.lang.String r0 = r0.replace(r2, r1)
                goto L_0x0016
            */
            throw new UnsupportedOperationException("Method not decompiled: com.opentok.android.v3.Publisher.PublisherException.<init>(int):void");
        }

        PublisherException(int error, String errorMsg) {
            super(error, errorMsg);
        }

        PublisherException(int error, String errorMsg, Exception e) {
            super(error, errorMsg, e);
        }
    }

    public static class Builder {
        private boolean audioFallback = true;
        private AudioLevelListener audioLevelListener;
        private AudioStatsListener audioStatsListener;
        private AbstractCapturer capturer = null;
        private Context context;
        private int maxAudioBitrate = 0;
        private String name = "unnamed-publisher";
        private boolean publishAudio = true;
        private boolean publishVideo = true;
        private PublisherListener publisherListener;
        private AbstractRenderer renderer = null;
        private VideoStatsListener videoStatsListener;
        private Stream.VideoType videoType = Stream.VideoType.Camera;

        public Builder(Context context2) {
            this.context = context2;
        }

        public String getPublisherName() {
            return this.name;
        }

        public boolean isAudioTrackAvailable() {
            return this.publishAudio;
        }

        public boolean isVideoTrackAvailable() {
            return this.publishVideo;
        }

        public AudioLevelListener getAudioLevelListener() {
            return this.audioLevelListener;
        }

        public AudioStatsListener getAudioStatsListener() {
            return this.audioStatsListener;
        }

        public VideoStatsListener getVideoStatsListener() {
            return this.videoStatsListener;
        }

        public AbstractRenderer getRenderer() {
            return this.renderer;
        }

        public AbstractCapturer getCapturer() {
            return this.capturer;
        }

        public int getMaxAudioBitrate() {
            return this.maxAudioBitrate;
        }

        public boolean isAudioFallbackEnabled() {
            return this.audioFallback;
        }

        public Stream.VideoType getVideoType() {
            return this.videoType;
        }

        public Builder setPublisherName(String name2) {
            this.name = name2;
            return this;
        }

        public Builder setAudioTrackAvailable(boolean hasAudio) {
            this.publishAudio = hasAudio;
            return this;
        }

        public Builder setVideoTrackAvailable(boolean hasVideo) {
            this.publishVideo = hasVideo;
            return this;
        }

        public Builder setPublisherListener(PublisherListener publisherListener2) {
            this.publisherListener = publisherListener2;
            return this;
        }

        public Builder setAudioLevelListener(AudioLevelListener audioLevelListener2) {
            this.audioLevelListener = audioLevelListener2;
            return this;
        }

        public Builder setAudioStatsListener(AudioStatsListener audioStatsListener2) {
            this.audioStatsListener = audioStatsListener2;
            return this;
        }

        public Builder setVideoStatsListener(VideoStatsListener videoStatsListener2) {
            this.videoStatsListener = videoStatsListener2;
            return this;
        }

        public Builder setRenderer(AbstractRenderer renderer2) {
            this.renderer = renderer2;
            return this;
        }

        public Builder setCapturer(AbstractCapturer capturer2) {
            this.capturer = capturer2;
            return this;
        }

        public Builder setMaxAudioBitrate(int bitrate) {
            this.maxAudioBitrate = bitrate;
            return this;
        }

        public Builder enableAudioFallback(boolean enable) {
            this.audioFallback = enable;
            return this;
        }

        public Builder setVideoType(Stream.VideoType videoType2) {
            this.videoType = videoType2;
            return this;
        }

        public Publisher Build() throws PublisherException {
            return new Publisher(this.context, this.name, this.publishAudio, this.publishVideo, this.maxAudioBitrate, this.audioFallback, this.videoType, this.renderer, this.capturer, this.publisherListener, this.audioLevelListener, this.audioStatsListener, this.videoStatsListener);
        }
    }

    public Stream getStream() {
        log.d("getStream(...) called", new Object[0]);
        return getStreamNative(this.nativeCtx);
    }

    public boolean isAudioPublishingEnabled() {
        log.d("getPublishAudio(...) called", new Object[0]);
        return getPublishAudioNative(this.nativeCtx);
    }

    public void enableAudioPublishing(boolean hasAudio) throws PublisherException {
        log.d("setPublishAudio(...) called", new Object[0]);
        int error = setPublishAudioNative(this.nativeCtx, hasAudio);
        if (error != 0) {
            throw new PublisherException(error);
        }
    }

    public boolean isVideoPublishingEnabled() {
        log.d("getPublishVideo(...) called", new Object[0]);
        return getPublishVideoNative(this.nativeCtx);
    }

    public void enableVideoPublishing(final boolean hasVideo) throws PublisherException {
        log.d("setPublishVideo(...) called", new Object[0]);
        int error = setPublishVideoNative(this.nativeCtx, hasVideo);
        if (error != 0) {
            throw new PublisherException(error);
        } else if (this.renderer != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Publisher.this.renderer.onVideoEnabled(hasVideo);
                }
            });
        }
    }

    public String getName() {
        log.d("getName(...) called", new Object[0]);
        return getNameNative(this.nativeCtx);
    }

    public String getId() {
        log.d("getId(...) called", new Object[0]);
        return getIdNative(this.nativeCtx);
    }

    public Stream.VideoType getVideoType() {
        log.d("getVideoType(...) called", new Object[0]);
        return Stream.videoTypeTbl.get(getVideoTypeNative(this.nativeCtx));
    }

    public AbstractCapturer getCapturer() {
        return this.capturer;
    }

    public AbstractRenderer getRenderer() {
        return this.renderer;
    }

    public void pause() throws PublisherException {
        log.d("pause(...) called", new Object[0]);
        if (this.renderer != null) {
            try {
                this.renderer.pause();
            } catch (Exception e) {
                throw new PublisherException(PublisherException.Error.VIDEO_RENDER_FAILED.errno, "Renderer Exception", e);
            }
        }
        if (this.capturer != null) {
            try {
                this.videoStreamStatePauseCache = getPublishVideoNative(this.nativeCtx);
                setPublishVideoNative(this.nativeCtx, false);
                this.capturer.pause();
            } catch (Exception e2) {
                throw new PublisherException(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Exception", e2);
            }
        }
    }

    public void resume() throws PublisherException {
        log.d("resume(...) called", new Object[0]);
        if (this.renderer != null) {
            try {
                this.renderer.resume();
            } catch (Exception e) {
                throw new PublisherException(PublisherException.Error.VIDEO_RENDER_FAILED.errno, "Renderer Exception", e);
            }
        }
        if (this.capturer != null) {
            try {
                setPublishVideoNative(this.nativeCtx, this.videoStreamStatePauseCache);
                this.capturer.resume();
            } catch (Exception e2) {
                throw new PublisherException(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Exception", e2);
            }
        }
    }

    public void cycleCamera() throws PublisherException {
        log.d("cycleCamera(...) called", new Object[0]);
        if (this.capturer != null) {
            try {
                ((AbstractCapturer.CaptureSwitch) this.capturer).cycleCamera();
            } catch (ClassCastException e) {
                throw new PublisherException(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Does not implement AbstractCapturer.CaptureSwitch", (Exception) e);
            } catch (Exception e2) {
                throw new PublisherException(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Exception", e2);
            }
        }
    }

    public void setStyle(AbstractRenderer.PresentationStyle style) {
        log.d("setStyle(...) called", new Object[0]);
        if (this.renderer != null) {
            this.renderer.setStyle(style);
        }
    }

    public View getView() {
        if (this.renderer != null) {
            return this.renderer.getView();
        }
        return null;
    }

    public boolean isAudioFallbackEnabled() {
        log.d("isAudioFallbackEnabled(...) called", new Object[0]);
        return isAudioFallbackEnabledNative(this.nativeCtx);
    }

    public void enableAudioFallback(boolean enable) throws PublisherException {
        log.d("enableAudioFallback(...) called", new Object[0]);
        int error = enableAudioFallbackNative(this.nativeCtx, enable);
        if (error != 0) {
            throw new PublisherException(error);
        }
    }

    public void close() throws PublisherException, Session.SessionException {
        log.d("close(...) called", new Object[0]);
        closeNative(this.nativeCtx);
        try {
            if (this.capturer != null) {
                if (this.capturer.isStarted()) {
                    this.capturer.stop();
                }
                if (this.capturer.isInitialized()) {
                    this.capturer.destroy();
                }
            }
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    if (Publisher.this.renderer != null) {
                        Publisher.this.renderer.onVideoEnabled(false);
                    }
                }
            });
        } catch (Exception e) {
            throw new PublisherException(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Exception", e);
        }
    }

    /* access modifiers changed from: package-private */
    public long getNativeHndl() {
        return this.nativeCtx;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v3, resolved type: com.opentok.android.v3.PassthroughRenderer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: com.opentok.android.v3.PassthroughRenderer} */
    /* JADX WARNING: type inference failed for: r16v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected Publisher(android.content.Context r9, java.lang.String r10, boolean r11, boolean r12, int r13, boolean r14, com.opentok.android.v3.Stream.VideoType r15, com.opentok.android.v3.AbstractRenderer r16, com.opentok.android.v3.AbstractCapturer r17, com.opentok.android.v3.Publisher.PublisherListener r18, com.opentok.android.v3.Publisher.AudioLevelListener r19, com.opentok.android.v3.Publisher.AudioStatsListener r20, com.opentok.android.v3.Publisher.VideoStatsListener r21) throws com.opentok.android.v3.Publisher.PublisherException {
        /*
            r8 = this;
            r8.<init>()
            com.opentok.android.v3.Publisher$9 r3 = new com.opentok.android.v3.Publisher$9
            r3.<init>()
            r8.capturerErrorHndlr = r3
            com.opentok.android.v3.debug.LogInterface r3 = log
            java.lang.String r4 = "Publisher(...) called"
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r3.d(r4, r5)
            if (r9 != 0) goto L_0x0020
            com.opentok.android.v3.Publisher$PublisherException r3 = new com.opentok.android.v3.Publisher$PublisherException
            com.opentok.android.v3.Publisher$PublisherException$Error r4 = com.opentok.android.v3.Publisher.PublisherException.Error.NULL_OR_INVALID_PARAMETER
            java.lang.String r5 = "Context is null"
            r3.<init>((com.opentok.android.v3.Publisher.PublisherException.Error) r4, (java.lang.String) r5)
            throw r3
        L_0x0020:
            com.opentok.android.v3.AudioDriver.setApplicationContext(r9)
            if (r16 == 0) goto L_0x0060
        L_0x0025:
            r0 = r16
            r8.renderer = r0
            if (r17 == 0) goto L_0x0068
        L_0x002b:
            r0 = r17
            r8.capturer = r0
            r0 = r18
            r8.publisherCb = r0
            r0 = r19
            r8.audioLevelCb = r0
            r0 = r20
            r8.audioStatsCb = r0
            r0 = r21
            r8.videoStatsCb = r0
            android.os.Handler r3 = new android.os.Handler
            android.os.Looper r4 = r9.getMainLooper()
            r3.<init>(r4)
            r8.mainThreadHandler = r3
            long r4 = r8.initNative(r9, r10, r11, r12)
            r8.nativeCtx = r4
            r4 = 0
            long r6 = r8.nativeCtx
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x0072
            com.opentok.android.v3.Publisher$PublisherException r3 = new com.opentok.android.v3.Publisher$PublisherException
            com.opentok.android.v3.Publisher$PublisherException$Error r4 = com.opentok.android.v3.Publisher.PublisherException.Error.INTERNAL_ERROR
            r3.<init>((com.opentok.android.v3.Publisher.PublisherException.Error) r4)
            throw r3
        L_0x0060:
            com.opentok.android.v3.PassthroughRenderer r16 = new com.opentok.android.v3.PassthroughRenderer
            r0 = r16
            r0.<init>(r9)
            goto L_0x0025
        L_0x0068:
            com.opentok.android.v3.DefaultVideoCapturer$Builder r3 = new com.opentok.android.v3.DefaultVideoCapturer$Builder
            r3.<init>(r9)
            com.opentok.android.v3.DefaultVideoCapturer r17 = r3.Build()
            goto L_0x002b
        L_0x0072:
            r2 = 0
            r8.enableAudioFallback(r14)
            long r4 = r8.nativeCtx
            int r3 = r15.ordinal()
            int r2 = r8.setVideoTypeNative(r4, r3)
            if (r2 == 0) goto L_0x0088
            com.opentok.android.v3.Publisher$PublisherException r3 = new com.opentok.android.v3.Publisher$PublisherException
            r3.<init>((int) r2)
            throw r3
        L_0x0088:
            if (r13 <= 0) goto L_0x009a
            long r4 = r8.nativeCtx
            int r3 = r13 / 1000
            int r2 = r8.setMaxAudioBitrateNative(r4, r3)
            if (r2 == 0) goto L_0x009a
            com.opentok.android.v3.Publisher$PublisherException r3 = new com.opentok.android.v3.Publisher$PublisherException
            r3.<init>((int) r2)
            throw r3
        L_0x009a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.opentok.android.v3.Publisher.<init>(android.content.Context, java.lang.String, boolean, boolean, int, boolean, com.opentok.android.v3.Stream$VideoType, com.opentok.android.v3.AbstractRenderer, com.opentok.android.v3.AbstractCapturer, com.opentok.android.v3.Publisher$PublisherListener, com.opentok.android.v3.Publisher$AudioLevelListener, com.opentok.android.v3.Publisher$AudioStatsListener, com.opentok.android.v3.Publisher$VideoStatsListener):void");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        log.d("finalize(...) called", new Object[0]);
        super.finalize();
        if (0 != this.nativeCtx) {
            this.nativeCtx = finalizeNative(this.nativeCtx);
        }
        log.d("finalize(...) exit", new Object[0]);
    }

    private void onStreamCreated(final Stream stream) {
        log.d("onStreamCreated(...) called", new Object[0]);
        if (this.publisherCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Publisher.this.publisherCb.onStreamCreated(Publisher.this, stream);
                }
            });
        }
    }

    private void onStreamDestroyed(final Stream stream) {
        log.d("onStreamDestroyed(...) called", new Object[0]);
        if (this.publisherCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Publisher.this.publisherCb.onStreamDestroyed(Publisher.this, stream);
                }
            });
        }
    }

    private void onFrame(long nativeFrame) {
        if (this.renderer != null) {
            try {
                this.renderer.onFrame(nativeFrame, this.capturer != null && this.capturer.isMirrorX());
            } catch (Exception e) {
                onError(PublisherException.Error.VIDEO_RENDER_FAILED.errno, "Renderer Exception", e);
            }
        }
    }

    private void onError(int errorCode, String errorMsg) {
        onError(errorCode, errorMsg, (Exception) null);
    }

    /* access modifiers changed from: private */
    public void onError(final int errorCode, final String errorMsg, final Exception e) {
        log.d("onError(...) called", new Object[0]);
        if (this.publisherCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Publisher.this.publisherCb.onError(Publisher.this, new PublisherException(errorCode, errorMsg, e));
                }
            });
        }
    }

    private void onAudioLevel(final float audioLevel) {
        if (this.audioLevelCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Publisher.this.audioLevelCb.onAudioLevelUpdated(Publisher.this, audioLevel);
                }
            });
        }
    }

    private void onVideoNetworkStats(final VideoStats[] videoStats) {
        if (this.videoStatsCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Publisher.this.videoStatsCb.onVideoStats(Publisher.this, videoStats);
                }
            });
        }
    }

    private void onAudioNetworkStats(final AudioStats[] audioStats) {
        if (this.audioStatsCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Publisher.this.audioStatsCb.onAudioStats(Publisher.this, audioStats);
                }
            });
        }
    }

    private boolean onCaptureInit(long captureNativeCtx) {
        AbstractCapturer.TexturePassthrough texturePassthrough;
        log.d("onCaptureInit(...) called", new Object[0]);
        if (this.capturer != null) {
            try {
                AbstractCapturer abstractCapturer = this.capturer;
                AbstractCapturer.CapturerError capturerError = this.capturerErrorHndlr;
                if (this.renderer instanceof AbstractCapturer.TexturePassthrough) {
                    texturePassthrough = (AbstractCapturer.TexturePassthrough) this.renderer;
                } else {
                    texturePassthrough = null;
                }
                return abstractCapturer.initCapturer(captureNativeCtx, capturerError, texturePassthrough);
            } catch (Exception e) {
                if (0 != this.nativeCtx) {
                    onError(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Exception", e);
                } else {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private boolean onCaptureDestroy(long captureNativeCtx) {
        log.d("onCaptureDestroy(...) called", new Object[0]);
        if (this.capturer == null) {
            return false;
        }
        try {
            this.capturer.unregisterCapturerError(this.capturerErrorHndlr);
            return this.capturer.destroy();
        } catch (Exception e) {
            if (0 != this.nativeCtx) {
                onError(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Exception", e);
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    private boolean onCaptureStart(long captureNativeCtx) {
        log.d("onCaptureStart(...) called", new Object[0]);
        if (this.capturer == null) {
            return false;
        }
        try {
            return this.capturer.start();
        } catch (Exception e) {
            if (0 != this.nativeCtx) {
                onError(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Exception", e);
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    private boolean onCaptureStop(long captureNativeCtx) {
        log.d("onCaptureStop(...) called", new Object[0]);
        if (this.capturer == null) {
            return false;
        }
        try {
            return this.capturer.stop();
        } catch (Exception e) {
            if (0 != this.nativeCtx) {
                onError(PublisherException.Error.VIDEO_CAPTURE_FAILED.errno, "Capturer Exception", e);
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    private void onCaptureSettings(long captureNativeCtx, long captureSettingsNativeHndl) {
        log.d("onCaptureSettings(...) called", new Object[0]);
        if (this.capturer != null) {
            this.capturer.captureSettings(captureSettingsNativeHndl);
        }
    }

    static {
        Loader.load();
        registerNatives();
    }
}
