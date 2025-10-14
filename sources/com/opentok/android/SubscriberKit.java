package com.opentok.android;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import com.opentok.android.OpentokError;
import com.opentok.android.OtLog;
import com.opentok.android.VideoUtils;
import com.opentok.android.v3.OpentokException;
import com.opentok.android.v3.Subscriber;
import com.opentok.impl.OpentokErrorImpl;

public class SubscriberKit {
    public static final float NO_PREFERRED_FRAMERATE = Float.MAX_VALUE;
    public static final VideoUtils.Size NO_PREFERRED_RESOLUTION = new VideoUtils.Size(Integer.MAX_VALUE, Integer.MAX_VALUE);
    public static final String VIDEO_REASON_CODEC_NOT_SUPPORTED = "codecNotSupported";
    public static final String VIDEO_REASON_PUBLISH_VIDEO = "publishVideo";
    public static final String VIDEO_REASON_QUALITY = "quality";
    public static final String VIDEO_REASON_SUBSCRIBE_TO_VIDEO = "subscribeToVideo";
    private static final OtLog.LogToken log = new OtLog.LogToken();
    protected AudioLevelListener audioLevelListener;
    protected AudioStatsListener audioStatsListener;
    private Handler handler;
    protected BaseVideoRenderer renderer;
    V3RendererWrapper rendererWrapper;
    protected Session session;
    protected Stream stream;
    protected StreamListener streamListener;
    protected SubscriberListener subscriberListener;
    private Subscriber.AudioLevelListener v3AudioLevelListener;
    private Subscriber.AudioStatsListener v3AudioStatsListener;
    private Subscriber.StreamListener v3StreamListener;
    private Subscriber v3Subscriber;
    private Subscriber.SubscriberListener v3SubscriberListener;
    private Subscriber.VideoListener v3VideoListener;
    private Subscriber.VideoStatsListener v3VideoStatsListener;
    protected VideoListener videoListener;
    protected VideoStatsListener videoStatsListener;

    public interface AudioLevelListener {
        void onAudioLevelUpdated(SubscriberKit subscriberKit, float f);
    }

    public interface AudioStatsListener {
        void onAudioStats(SubscriberKit subscriberKit, SubscriberAudioStats subscriberAudioStats);
    }

    public interface StreamListener {
        void onDisconnected(SubscriberKit subscriberKit);

        void onReconnected(SubscriberKit subscriberKit);
    }

    public static class SubscriberAudioStats {
        public int audioBytesReceived;
        public int audioPacketsLost;
        public int audioPacketsReceived;
        public double timeStamp;
    }

    public interface SubscriberListener {
        void onConnected(SubscriberKit subscriberKit);

        void onDisconnected(SubscriberKit subscriberKit);

        void onError(SubscriberKit subscriberKit, OpentokError opentokError);
    }

    public static class SubscriberVideoStats {
        public double timeStamp;
        public int videoBytesReceived;
        public int videoPacketsLost;
        public int videoPacketsReceived;
    }

    public interface VideoListener {
        void onVideoDataReceived(SubscriberKit subscriberKit);

        void onVideoDisableWarning(SubscriberKit subscriberKit);

        void onVideoDisableWarningLifted(SubscriberKit subscriberKit);

        void onVideoDisabled(SubscriberKit subscriberKit, String str);

        void onVideoEnabled(SubscriberKit subscriberKit, String str);
    }

    public interface VideoStatsListener {
        void onVideoStats(SubscriberKit subscriberKit, SubscriberVideoStats subscriberVideoStats);
    }

    public static class Builder {
        Context context;
        BaseVideoRenderer renderer;
        Stream stream;

        public Builder(Context context2, Stream stream2) {
            this.context = context2;
            this.stream = stream2;
        }

        public Builder renderer(BaseVideoRenderer renderer2) {
            this.renderer = renderer2;
            return this;
        }

        public SubscriberKit build() {
            return new SubscriberKit(this.context, this.stream, this.renderer);
        }
    }

    @Deprecated
    public SubscriberKit(Context context, Stream stream2) {
        this(context, stream2, (BaseVideoRenderer) null);
    }

    protected SubscriberKit(Context context, Stream stream2, BaseVideoRenderer renderer2) {
        this.v3SubscriberListener = new Subscriber.SubscriberListener() {
            public void onConnected(Subscriber subscriber) {
                SubscriberKit.this.onConnected();
            }

            public void onDisconnected(Subscriber subscriber) {
                SubscriberKit.this.onDisconnected();
            }

            public void onError(Subscriber subscriber, OpentokException error) {
                SubscriberKit.this.onError(new OpentokError(OpentokError.Domain.SubscriberErrorDomain, error.getErrorCode(), error.getMessage()));
            }
        };
        this.v3AudioLevelListener = new Subscriber.AudioLevelListener() {
            public void onAudioLevelUpdated(Subscriber subscriber, float audioLevel) {
                SubscriberKit.this.onAudioLevelUpdated(audioLevel);
            }
        };
        this.v3AudioStatsListener = new Subscriber.AudioStatsListener() {
            public void onAudioStats(Subscriber subscriber, Subscriber.AudioStats v3Stats) {
                SubscriberAudioStats stats = new SubscriberAudioStats();
                stats.audioBytesReceived = (int) v3Stats.audioBytesReceived;
                stats.audioPacketsReceived = (int) v3Stats.audioPacketsReceived;
                stats.audioPacketsLost = (int) v3Stats.audioPacketsLost;
                stats.timeStamp = v3Stats.timeStamp;
                if (SubscriberKit.this.audioStatsListener != null) {
                    SubscriberKit.this.audioStatsListener.onAudioStats(SubscriberKit.this, stats);
                }
            }
        };
        this.v3VideoStatsListener = new Subscriber.VideoStatsListener() {
            public void onVideoStats(Subscriber subscriber, Subscriber.VideoStats v3Stats) {
                SubscriberVideoStats stats = new SubscriberVideoStats();
                stats.videoBytesReceived = (int) v3Stats.videoBytesReceived;
                stats.videoPacketsReceived = (int) v3Stats.videoPacketsReceived;
                stats.videoPacketsLost = (int) v3Stats.videoPacketsLost;
                stats.timeStamp = v3Stats.timeStamp;
                if (SubscriberKit.this.videoStatsListener != null) {
                    SubscriberKit.this.videoStatsListener.onVideoStats(SubscriberKit.this, stats);
                }
            }
        };
        this.v3VideoListener = new Subscriber.VideoListener() {
            public void onVideoDataReceived(Subscriber subscriber) {
                SubscriberKit.this.onVideoDataReceived();
            }

            public void onVideoDisabled(Subscriber subscriber, String reason) {
                SubscriberKit.this.onVideoDisabled(reason);
            }

            public void onVideoEnabled(Subscriber subscriber, String reason) {
                SubscriberKit.this.onVideoEnabled(reason);
            }

            public void onVideoDisableWarning(Subscriber subscriber) {
                SubscriberKit.this.onVideoDisableWarning();
            }

            public void onVideoDisableWarningLifted(Subscriber subscriber) {
                SubscriberKit.this.onVideoDisableWarningLifted();
            }
        };
        this.v3StreamListener = new Subscriber.StreamListener() {
            public void onReconnected(Subscriber subscriber) {
                SubscriberKit.this.onStreamReconnected();
            }

            public void onDisconnected(Subscriber subscriber) {
                SubscriberKit.this.onStreamDisconnected();
            }
        };
        try {
            this.session = stream2.getSession();
            this.handler = new Handler(context.getMainLooper());
            this.rendererWrapper = new V3RendererWrapper(renderer2);
            this.renderer = renderer2;
            this.stream = stream2;
            this.v3Subscriber = new Subscriber.Builder(context, stream2.getV3Stream()).setSubscriberListener(this.v3SubscriberListener).setAudioLevelListener(this.v3AudioLevelListener).setAudioStatsListener(this.v3AudioStatsListener).setVideoStatsListener(this.v3VideoStatsListener).setVideoListener(this.v3VideoListener).setStreamListener(this.v3StreamListener).setRenderer(this.rendererWrapper).Build();
        } catch (Subscriber.SubscriberException se) {
            se.printStackTrace();
        }
        AudioDeviceManager.initializeDefaultDevice(context);
    }

    public void destroy() {
        this.v3Subscriber.close();
    }

    public void setSubscriberListener(SubscriberListener listener) {
        this.subscriberListener = listener;
    }

    public void setVideoListener(VideoListener listener) {
        this.videoListener = listener;
    }

    public void setAudioLevelListener(AudioLevelListener listener) {
        this.audioLevelListener = listener;
    }

    public void setStreamListener(StreamListener listener) {
        this.streamListener = listener;
    }

    public Session getSession() {
        if (this.v3Subscriber.getSession() != null) {
            return this.session;
        }
        return null;
    }

    public Stream getStream() {
        return this.session.lookupStream(this.v3Subscriber.getStream());
    }

    public boolean getSubscribeToAudio() {
        return this.v3Subscriber.isAudioSubscribed();
    }

    public boolean getSubscribeToVideo() {
        return this.v3Subscriber.isVideoSubscribed();
    }

    public void setSubscribeToAudio(boolean subscribeToAudio) {
        try {
            this.v3Subscriber.setSubscribeAudio(subscribeToAudio);
        } catch (Subscriber.SubscriberException se) {
            se.printStackTrace();
        }
    }

    public void setSubscribeToVideo(boolean subscribeToVideo) {
        try {
            this.v3Subscriber.setSubscribeVideo(subscribeToVideo);
        } catch (Subscriber.SubscriberException se) {
            se.printStackTrace();
        }
    }

    public void setStyle(String key, String value) {
        if (this.rendererWrapper.getBaseRenderer() != null) {
            this.rendererWrapper.getBaseRenderer().setStyle(key, value);
        }
    }

    @Deprecated
    public void setRenderer(BaseVideoRenderer renderer2) {
        this.rendererWrapper.setBaseRenderer(renderer2);
        this.renderer = renderer2;
    }

    public BaseVideoRenderer getRenderer() {
        return this.rendererWrapper.getBaseRenderer();
    }

    public View getView() {
        if (getRenderer() != null) {
            return this.rendererWrapper.getBaseRenderer().getView();
        }
        return null;
    }

    public void setVideoStatsListener(VideoStatsListener listener) {
        this.videoStatsListener = listener;
    }

    public void setAudioStatsListener(AudioStatsListener listener) {
        this.audioStatsListener = listener;
    }

    public void setPreferredResolution(VideoUtils.Size preferredResolution) {
        try {
            this.v3Subscriber.setPreferredResolution(preferredResolution.equals(NO_PREFERRED_RESOLUTION) ? new Rect(0, 0, 0, 0) : new Rect(0, 0, preferredResolution.width, preferredResolution.height));
        } catch (Subscriber.SubscriberException se) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SubscriberErrorDomain, se.getErrorCode()));
        }
    }

    public void setPreferredFrameRate(float preferredFrameRate) {
        try {
            Subscriber subscriber = this.v3Subscriber;
            if (preferredFrameRate == Float.MAX_VALUE) {
                preferredFrameRate = 0.0f;
            }
            subscriber.setPreferredFrameRate(preferredFrameRate);
        } catch (Subscriber.SubscriberException se) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SubscriberErrorDomain, se.getErrorCode()));
        }
    }

    public VideoUtils.Size getPreferredResolution() {
        Rect size = this.v3Subscriber.getPreferredResolution();
        return size != null ? new VideoUtils.Size(size.right - size.left, size.bottom - size.top) : NO_PREFERRED_RESOLUTION;
    }

    public float getPreferredFrameRate() {
        return this.v3Subscriber.getPreferredFrameRate();
    }

    /* access modifiers changed from: package-private */
    public void error(SubscriberKit subscriber, int errorCode, String msg) {
        throwError(new OpentokError(OpentokError.Domain.SubscriberErrorDomain, errorCode, msg));
    }

    /* access modifiers changed from: package-private */
    public void throwError(final OpentokError error) {
        this.handler.post(new Runnable() {
            public void run() {
                SubscriberKit.this.onError(error);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public Subscriber getV3Subscriber() {
        return this.v3Subscriber;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
    }

    /* access modifiers changed from: protected */
    public void onConnected() {
        if (this.subscriberListener != null) {
            this.subscriberListener.onConnected(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDisconnected() {
        log.i("Subscriber with streamId: %s is disconnected", getStream().getStreamId());
        if (this.subscriberListener != null) {
            this.subscriberListener.onDisconnected(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onError(OpentokError error) {
        if (this.subscriberListener != null) {
            this.subscriberListener.onError(this, error);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamDisconnected() {
        log.i("Stream: %s is disconnected", getStream().getStreamId());
        if (this.streamListener != null) {
            this.streamListener.onDisconnected(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamReconnected() {
        log.i("Stream: %s is reconnected", getStream().getStreamId());
        if (this.streamListener != null) {
            this.streamListener.onReconnected(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onVideoDisabled(String reason) {
        if (this.videoListener != null) {
            this.videoListener.onVideoDisabled(this, reason);
        }
    }

    /* access modifiers changed from: protected */
    public void onVideoEnabled(String reason) {
        if (this.videoListener != null) {
            this.videoListener.onVideoEnabled(this, reason);
        }
    }

    /* access modifiers changed from: protected */
    public void onVideoDisableWarning() {
        if (this.videoListener != null) {
            this.videoListener.onVideoDisableWarning(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onVideoDisableWarningLifted() {
        if (this.videoListener != null) {
            this.videoListener.onVideoDisableWarningLifted(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onVideoDataReceived() {
        if (this.videoListener != null) {
            this.videoListener.onVideoDataReceived(this);
        }
    }

    /* access modifiers changed from: protected */
    public void attachToSession(Session session2) {
    }

    /* access modifiers changed from: protected */
    public void detachFromSession(Session session2) {
    }

    /* access modifiers changed from: package-private */
    public void onAudioLevelUpdated(float audioLevel) {
        if (this.audioLevelListener != null) {
            this.audioLevelListener.onAudioLevelUpdated(this, audioLevel);
        }
    }
}
