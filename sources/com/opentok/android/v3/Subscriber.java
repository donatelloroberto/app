package com.opentok.android.v3;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.v4.view.PointerIconCompat;
import android.util.SparseArray;
import android.view.View;
import com.google.android.gms.cast.CastStatusCodes;
import com.opentok.android.SubscriberKit;
import com.opentok.android.v3.AbstractRenderer;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;
import java.util.EnumSet;
import java.util.Iterator;

@Keep
public class Subscriber {
    /* access modifiers changed from: private */
    public static final SparseArray<String> VideoReasonTbl = new SparseArray<String>() {
        {
            append(1, SubscriberKit.VIDEO_REASON_PUBLISH_VIDEO);
            append(2, SubscriberKit.VIDEO_REASON_SUBSCRIBE_TO_VIDEO);
            append(3, SubscriberKit.VIDEO_REASON_QUALITY);
            append(4, SubscriberKit.VIDEO_REASON_CODEC_NOT_SUPPORTED);
        }
    };
    private static final LogInterface log = OtLog.LogToken("[Subscriber]");
    /* access modifiers changed from: private */
    public final AudioLevelListener audioLevelCb;
    /* access modifiers changed from: private */
    public final AudioStatsListener audioStatsCb;
    private final Handler mainThreadHandler;
    private long nativeCtx;
    /* access modifiers changed from: private */
    public final AbstractRenderer renderer;
    /* access modifiers changed from: private */
    public final StreamListener streamCb;
    /* access modifiers changed from: private */
    public final SubscriberListener subscriberCb;
    /* access modifiers changed from: private */
    public final VideoListener videoCb;
    /* access modifiers changed from: private */
    public final VideoStatsListener videoStatsCb;

    public interface AudioLevelListener {
        void onAudioLevelUpdated(Subscriber subscriber, float f);
    }

    public interface AudioStatsListener {
        void onAudioStats(Subscriber subscriber, AudioStats audioStats);
    }

    public interface StreamListener {
        void onDisconnected(Subscriber subscriber);

        void onReconnected(Subscriber subscriber);
    }

    public interface SubscriberListener {
        void onConnected(Subscriber subscriber);

        void onDisconnected(Subscriber subscriber);

        void onError(Subscriber subscriber, OpentokException opentokException);
    }

    public interface VideoListener {
        void onVideoDataReceived(Subscriber subscriber);

        void onVideoDisableWarning(Subscriber subscriber);

        void onVideoDisableWarningLifted(Subscriber subscriber);

        void onVideoDisabled(Subscriber subscriber, String str);

        void onVideoEnabled(Subscriber subscriber, String str);
    }

    public interface VideoStatsListener {
        void onVideoStats(Subscriber subscriber, VideoStats videoStats);
    }

    private native void closeNative(long j);

    private native long finalizeNative(long j);

    private native String getIdNative(long j);

    private native float getPreferredFrameRateNative(long j);

    private native Rect getPreferredResolutionNative(long j);

    private native Session getSessionNative(long j);

    private native Stream getStreamNative(long j);

    private native boolean getSubscribeAudioNative(long j);

    private native boolean getSubscribeVideoNative(long j);

    private native long initNative(Context context, long j);

    private static native void registerNatives();

    private native int setPreferredFrameRateNative(long j, float f);

    private native int setPreferredResolutionNative(long j, int i, int i2);

    private native int setSubscribeAudioNative(long j, boolean z);

    private native int setSubscribeVideoNative(long j, boolean z);

    public static class AudioStats {
        public final long audioBytesReceived;
        public final long audioPacketsLost;
        public final long audioPacketsReceived;
        public final double timeStamp;

        AudioStats(long aPktsLost, long aPktsReceived, long aBytesReceived, double tmStamp) {
            this.audioPacketsLost = aPktsLost;
            this.audioPacketsReceived = aPktsReceived;
            this.audioBytesReceived = aBytesReceived;
            this.timeStamp = tmStamp;
        }
    }

    public static class VideoStats {
        public final double timeStamp;
        public final long videoBytesReceived;
        public final long videoPacketsLost;
        public final long videoPacketsReceived;

        VideoStats(long vPktsLost, long vPktsReceived, long vBytesReceived, double tmStamp) {
            this.videoPacketsLost = vPktsLost;
            this.videoPacketsReceived = vPktsReceived;
            this.videoBytesReceived = vBytesReceived;
            this.timeStamp = tmStamp;
        }
    }

    public static class SubscriberException extends OpentokException {

        public enum Error {
            UNKNOWN_ERROR(-1),
            SUCCESS(0),
            NULL_OR_INVALID_PARAMETER(PointerIconCompat.TYPE_COPY),
            TIMED_OUT(1542),
            SESSION_DISCONNECTED(1541),
            WEBRTC_ERROR(1600),
            SERVER_CANNOT_FIND_STREAM(1604),
            STREAM_LIMIT_EXCEEDED(1605),
            INTERNAL_ERROR(CastStatusCodes.AUTHENTICATION_FAILED),
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

        SubscriberException() {
            this(Error.UNKNOWN_ERROR);
        }

        SubscriberException(Error error) {
            this(error, error.name().replace('_', ' '));
        }

        SubscriberException(Error error, String message) {
            super(error.errno, message);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        SubscriberException(int r4) {
            /*
                r3 = this;
                r2 = 95
                r1 = 32
                com.opentok.android.v3.Subscriber$SubscriberException$Error r0 = com.opentok.android.v3.Subscriber.SubscriberException.Error.getError(r4)
                if (r0 == 0) goto L_0x001a
                com.opentok.android.v3.Subscriber$SubscriberException$Error r0 = com.opentok.android.v3.Subscriber.SubscriberException.Error.getError(r4)
                java.lang.String r0 = r0.name()
                java.lang.String r0 = r0.replace(r2, r1)
            L_0x0016:
                r3.<init>(r4, r0)
                return
            L_0x001a:
                com.opentok.android.v3.Subscriber$SubscriberException$Error r0 = com.opentok.android.v3.Subscriber.SubscriberException.Error.UNKNOWN_ERROR
                java.lang.String r0 = r0.name()
                java.lang.String r0 = r0.replace(r2, r1)
                goto L_0x0016
            */
            throw new UnsupportedOperationException("Method not decompiled: com.opentok.android.v3.Subscriber.SubscriberException.<init>(int):void");
        }

        SubscriberException(int error, String errorMsg) {
            super(error, errorMsg);
        }

        SubscriberException(int error, String errorMsg, Exception e) {
            super(error, errorMsg, e);
        }
    }

    public static class Builder {
        private AudioLevelListener audioLevelListener;
        private AudioStatsListener audioStatsListener;
        private Context context;
        private AbstractRenderer renderer = null;
        private Stream stream;
        private StreamListener streamListener;
        private boolean subscribeAudio = true;
        private boolean subscribeVideo = true;
        private SubscriberListener subscriberListener;
        private VideoListener videoListener;
        private VideoStatsListener videoStatsListener;

        public Builder(Context context2, Stream stream2) {
            this.context = context2;
            this.stream = stream2;
        }

        public Builder setSubscriberListener(SubscriberListener subscriberListener2) {
            this.subscriberListener = subscriberListener2;
            return this;
        }

        public Builder setAudioLevelListener(AudioLevelListener audioLevelListener2) {
            this.audioLevelListener = audioLevelListener2;
            return this;
        }

        public Builder setVideoListener(VideoListener videoListener2) {
            this.videoListener = videoListener2;
            return this;
        }

        public Builder setStreamListener(StreamListener streamListener2) {
            this.streamListener = streamListener2;
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

        public Builder setSubscribeAudio(boolean audio) {
            this.subscribeAudio = audio;
            return this;
        }

        public Builder setSubscribeVideo(boolean video) {
            this.subscribeVideo = video;
            return this;
        }

        public Subscriber Build() throws SubscriberException {
            return new Subscriber(this.context, this.stream, this.renderer, this.subscriberListener, this.audioLevelListener, this.videoListener, this.streamListener, this.audioStatsListener, this.videoStatsListener, this.subscribeAudio, this.subscribeVideo);
        }
    }

    public Stream getStream() {
        log.d("getStream(...) called", new Object[0]);
        return getStreamNative(this.nativeCtx);
    }

    public boolean isAudioSubscribed() {
        log.d("isAudioSubscribed(...) called", new Object[0]);
        return getSubscribeAudioNative(this.nativeCtx);
    }

    public boolean isVideoSubscribed() {
        log.d("isVideoSubscribed(...) called", new Object[0]);
        return getSubscribeVideoNative(this.nativeCtx);
    }

    public void setSubscribeAudio(boolean audio) throws SubscriberException {
        log.d("setSubscribeAudio(...) called", new Object[0]);
        int error = setSubscribeAudioNative(this.nativeCtx, audio);
        if (error != 0) {
            throw new SubscriberException(error);
        }
    }

    public void setSubscribeVideo(final boolean video) throws SubscriberException {
        log.d("setSubscribeVideo(...) called", new Object[0]);
        int error = setSubscribeVideoNative(this.nativeCtx, video);
        if (error != 0) {
            throw new SubscriberException(error);
        } else if (this.renderer != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.renderer.onVideoEnabled(video);
                }
            });
        }
    }

    public Session getSession() {
        log.d("getSession(...) called", new Object[0]);
        return getSessionNative(this.nativeCtx);
    }

    public Rect getPreferredResolution() {
        log.d("getPreferredResolution(...) called", new Object[0]);
        return getPreferredResolutionNative(this.nativeCtx);
    }

    public void setPreferredResolution(Rect dimension) throws SubscriberException {
        log.d("setPreferredResolution(...) called", new Object[0]);
        int error = setPreferredResolutionNative(this.nativeCtx, dimension.width(), dimension.height());
        if (error != 0) {
            throw new SubscriberException(error);
        }
    }

    public float getPreferredFrameRate() {
        log.d("getPreferredFrameRate(...) called", new Object[0]);
        return getPreferredFrameRateNative(this.nativeCtx);
    }

    public void setPreferredFrameRate(float frameRate) throws SubscriberException {
        log.d("setPreferredFrameRate(...) called", new Object[0]);
        int error = setPreferredFrameRateNative(this.nativeCtx, frameRate);
        if (error != 0) {
            throw new SubscriberException(error);
        }
    }

    public void setStyle(AbstractRenderer.PresentationStyle style) {
        if (this.renderer != null) {
            this.renderer.setStyle(style);
        }
    }

    public String getId() {
        log.d("getId(...) called", new Object[0]);
        return getIdNative(this.nativeCtx);
    }

    public View getView() {
        if (this.renderer != null) {
            return this.renderer.getView();
        }
        return null;
    }

    public void pause() throws SubscriberException {
        if (this.renderer != null) {
            try {
                this.renderer.pause();
            } catch (Exception e) {
                throw new SubscriberException(SubscriberException.Error.VIDEO_RENDER_FAILED.errno, "Renderer Exception", e);
            }
        }
    }

    public void resume() throws SubscriberException {
        if (this.renderer != null) {
            try {
                this.renderer.resume();
            } catch (Exception e) {
                throw new SubscriberException(SubscriberException.Error.VIDEO_RENDER_FAILED.errno, "Renderer Exception", e);
            }
        }
    }

    public AbstractRenderer getRenderer() {
        return this.renderer;
    }

    public void close() {
        closeNative(this.nativeCtx);
    }

    /* access modifiers changed from: package-private */
    public long getNativeHndl() {
        return this.nativeCtx;
    }

    protected Subscriber(Context ctx, Stream stream, AbstractRenderer videoRenderer, SubscriberListener subscriberListener, AudioLevelListener audioLevelListener, VideoListener videoListener, StreamListener streamListener, AudioStatsListener audioStatsListener, VideoStatsListener videoStatsListener, boolean subscribeAudio, boolean subscribeVideo) throws SubscriberException {
        log.d("Subscriber(...) called", new Object[0]);
        if (ctx == null || stream == null) {
            throw new SubscriberException(SubscriberException.Error.NULL_OR_INVALID_PARAMETER, "Context or Stream is null");
        }
        AudioDriver.setApplicationContext(ctx);
        this.renderer = videoRenderer == null ? new DefaultVideoRenderer(ctx) : videoRenderer;
        this.subscriberCb = subscriberListener;
        this.audioLevelCb = audioLevelListener;
        this.videoCb = videoListener;
        this.streamCb = streamListener;
        this.audioStatsCb = audioStatsListener;
        this.videoStatsCb = videoStatsListener;
        this.mainThreadHandler = new Handler(ctx.getMainLooper());
        this.nativeCtx = initNative(ctx, stream.getNativeCtx());
        if (0 == this.nativeCtx) {
            throw new SubscriberException(SubscriberException.Error.INTERNAL_ERROR);
        }
        if (!subscribeAudio) {
            setSubscribeAudioNative(this.nativeCtx, subscribeAudio);
        }
        if (!subscribeVideo) {
            setSubscribeVideoNative(this.nativeCtx, subscribeVideo);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        log.d("finalize(...) called", new Object[0]);
        if (0 != this.nativeCtx) {
            this.nativeCtx = finalizeNative(this.nativeCtx);
        }
    }

    private void onConnected() {
        log.d("onConnected(...) called", new Object[0]);
        this.mainThreadHandler.post(new Runnable() {
            public void run() {
                if (Subscriber.this.subscriberCb != null) {
                    Subscriber.this.subscriberCb.onConnected(Subscriber.this);
                }
                if (Subscriber.this.renderer != null) {
                    Subscriber.this.renderer.onVideoEnabled(Subscriber.this.isVideoSubscribed());
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onDisconnected() {
        log.d("onDisconnected(...) called", new Object[0]);
        if (this.subscriberCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.subscriberCb.onDisconnected(Subscriber.this);
                }
            });
        }
    }

    private void onError(String errorMsg, int errorCode) {
        onError(errorMsg, errorCode, (Exception) null);
    }

    private void onError(final String errorMsg, final int errorCode, final Exception e) {
        log.d("onError(...) called", new Object[0]);
        if (this.subscriberCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.subscriberCb.onError(Subscriber.this, new SubscriberException(errorCode, errorMsg, e));
                }
            });
        }
    }

    private void onStreamDisconnected() {
        log.d("onStreamDisconnected(...) called", new Object[0]);
        if (this.streamCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.streamCb.onDisconnected(Subscriber.this);
                }
            });
        }
    }

    private void onStreamReconnected() {
        log.d("onStreamReconnected(...) called", new Object[0]);
        if (this.streamCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.streamCb.onReconnected(Subscriber.this);
                }
            });
        }
    }

    private void onFrame(long nativeFrame) {
        if (this.renderer != null) {
            try {
                this.renderer.onFrame(nativeFrame, false);
            } catch (Exception e) {
                onError("Renderer Exception", SubscriberException.Error.VIDEO_RENDER_FAILED.errno, e);
            }
        }
    }

    private void onVideoDisabled(final int reason) {
        log.d("onVideoDisabled(...) called", new Object[0]);
        this.mainThreadHandler.post(new Runnable() {
            public void run() {
                if (Subscriber.this.videoCb != null) {
                    Subscriber.this.videoCb.onVideoDisabled(Subscriber.this, (String) Subscriber.VideoReasonTbl.get(reason));
                }
                if (Subscriber.this.renderer != null) {
                    Subscriber.this.renderer.onVideoEnabled(false);
                }
            }
        });
    }

    private void onVideoEnabled(final int reason) {
        log.d("onVideoEnabled(...) called", new Object[0]);
        this.mainThreadHandler.post(new Runnable() {
            public void run() {
                if (Subscriber.this.videoCb != null) {
                    Subscriber.this.videoCb.onVideoEnabled(Subscriber.this, (String) Subscriber.VideoReasonTbl.get(reason));
                }
                if (Subscriber.this.renderer != null) {
                    Subscriber.this.renderer.onVideoEnabled(true);
                }
            }
        });
    }

    private void onVideoDisableWarning() {
        log.d("onVideoDisableWarning(...) called", new Object[0]);
        if (this.videoCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.videoCb.onVideoDisableWarning(Subscriber.this);
                }
            });
        }
    }

    private void onVideoDisableWarningLifted() {
        log.d("onVideoDisableWarningLifted(...) called", new Object[0]);
        if (this.videoCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.videoCb.onVideoDisableWarningLifted(Subscriber.this);
                }
            });
        }
    }

    private void onVideoDataReceived() {
        if (this.videoCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.videoCb.onVideoDataReceived(Subscriber.this);
                }
            });
        }
    }

    private void onAudioNetworkStats(final AudioStats audioStats) {
        if (this.audioStatsCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.audioStatsCb.onAudioStats(Subscriber.this, audioStats);
                }
            });
        }
    }

    private void onAudioLevel(final float audio_level) {
        if (this.audioLevelCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.audioLevelCb.onAudioLevelUpdated(Subscriber.this, audio_level);
                }
            });
        }
    }

    private void onVideoNetworkStats(final VideoStats videoStats) {
        if (this.videoStatsCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Subscriber.this.videoStatsCb.onVideoStats(Subscriber.this, videoStats);
                }
            });
        }
    }

    static {
        Loader.load();
        registerNatives();
    }
}
