package com.opentok.android;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.opentok.android.OpentokError;
import com.opentok.android.OtLog;
import com.opentok.android.v3.Publisher;
import com.opentok.android.v3.Session;
import com.opentok.android.v3.Stream;
import com.opentok.impl.OpentokErrorImpl;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PublisherKit {
    private static final OtLog.LogToken log = new OtLog.LogToken();
    protected AudioLevelListener audioLevelListener;
    protected AudioStatsListener audioStatsListener;
    protected BaseVideoCapturer capturer;
    V3CapturerWrapper capturerWrapper;
    protected Context context;
    protected Handler handler;
    private boolean publishAudio;
    private boolean publishVideo;
    protected PublisherListener publisherListener;
    protected BaseVideoRenderer renderer;
    V3RendererWrapper rendererWrapper;
    protected Session session;
    private Publisher.AudioLevelListener v3AudioListener;
    private Publisher.AudioStatsListener v3AudioStatsListener;
    Publisher v3Publisher;
    Publisher.Builder v3PublisherBuilder;
    private Publisher.PublisherListener v3PublisherListener;
    private Publisher.VideoStatsListener v3VideoStatsListener;
    protected VideoStatsListener videoStatsListener;

    public interface AudioLevelListener {
        void onAudioLevelUpdated(PublisherKit publisherKit, float f);
    }

    public interface AudioStatsListener {
        void onAudioStats(PublisherKit publisherKit, PublisherAudioStats[] publisherAudioStatsArr);
    }

    public static class PublisherAudioStats {
        public long audioBytesSent;
        public long audioPacketsLost;
        public long audioPacketsSent;
        public String connectionId;
        public double startTime;
        public String subscriberId;
        public double timeStamp;
    }

    public interface PublisherListener {
        void onError(PublisherKit publisherKit, OpentokError opentokError);

        void onStreamCreated(PublisherKit publisherKit, Stream stream);

        void onStreamDestroyed(PublisherKit publisherKit, Stream stream);
    }

    public static class PublisherVideoStats {
        public String connectionId;
        public double startTime;
        public String subscriberId;
        public double timeStamp;
        public long videoBytesSent;
        public long videoPacketsLost;
        public long videoPacketsSent;
    }

    public interface VideoStatsListener {
        void onVideoStats(PublisherKit publisherKit, PublisherVideoStats[] publisherVideoStatsArr);
    }

    public enum PublisherKitVideoType {
        PublisherKitVideoTypeCamera(1),
        PublisherKitVideoTypeScreen(2);
        
        private int videoType;

        private PublisherKitVideoType(int type) {
            this.videoType = type;
        }

        public int getVideoType() {
            return this.videoType;
        }

        static PublisherKitVideoType fromType(int typeId) {
            for (PublisherKitVideoType type : values()) {
                if (type.getVideoType() == typeId) {
                    return type;
                }
            }
            throw new IllegalArgumentException("unknown type " + typeId);
        }
    }

    public static class Builder {
        int audioBitrate;
        boolean audioTrack = true;
        BaseVideoCapturer capturer = null;
        Context context;
        String name;
        BaseVideoRenderer renderer = null;
        boolean videoTrack = true;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder name(String name2) {
            this.name = name2;
            return this;
        }

        public Builder audioTrack(boolean enabled) {
            this.audioTrack = enabled;
            return this;
        }

        public Builder videoTrack(boolean enabled) {
            this.videoTrack = enabled;
            return this;
        }

        public Builder capturer(BaseVideoCapturer capturer2) {
            this.capturer = capturer2;
            return this;
        }

        public Builder renderer(BaseVideoRenderer renderer2) {
            this.renderer = renderer2;
            return this;
        }

        public Builder audioBitrate(int bitsPerSecond) {
            this.audioBitrate = bitsPerSecond;
            return this;
        }

        public PublisherKit build() {
            return new PublisherKit(this.context, this.name, this.audioTrack, this.audioBitrate, this.videoTrack, this.capturer, this.renderer);
        }
    }

    @Deprecated
    protected PublisherKit(Context context2, String name, boolean audioTrack, boolean videoTrack, BaseVideoCapturer capturer2, BaseVideoRenderer renderer2) {
        this(context2, name, audioTrack, 0, videoTrack, capturer2, renderer2);
    }

    protected PublisherKit(Context context2, String name, boolean audioTrack, int maxAudioBitRate, boolean videoTrack, BaseVideoCapturer capturer2, BaseVideoRenderer renderer2) {
        this.v3PublisherListener = new Publisher.PublisherListener() {
            public void onStreamCreated(Publisher v3Publisher, Stream v3Stream) {
                PublisherKit.this.onStreamCreated(PublisherKit.this.session.addStream(v3Stream));
            }

            public void onStreamDestroyed(Publisher v3Publisher, Stream v3Stream) {
                PublisherKit.this.onStreamDestroyed(PublisherKit.this.session.delStream(v3Stream));
            }

            public void onError(Publisher v3Publisher, Publisher.PublisherException pe) {
                PublisherKit.this.onError(new OpentokError(OpentokError.Domain.PublisherErrorDomain, pe.getErrorCode(), pe.getMessage()));
            }
        };
        this.v3AudioListener = new Publisher.AudioLevelListener() {
            public void onAudioLevelUpdated(Publisher publisher, float audioLevel) {
                PublisherKit.this.onAudioLevelUpdated(audioLevel);
            }
        };
        this.v3AudioStatsListener = new Publisher.AudioStatsListener() {
            public void onAudioStats(Publisher publisher, Publisher.AudioStats[] v3Stats) {
                PublisherAudioStats[] stats = new PublisherAudioStats[v3Stats.length];
                for (int i = 0; i < stats.length; i++) {
                    stats[i] = new PublisherAudioStats();
                    stats[i].connectionId = v3Stats[i].connectionId;
                    stats[i].subscriberId = v3Stats[i].subscriberId;
                    stats[i].audioBytesSent = v3Stats[i].audioBytesSent;
                    stats[i].audioPacketsSent = v3Stats[i].audioPacketsSent;
                    stats[i].audioPacketsLost = v3Stats[i].audioPacketsLost;
                    stats[i].timeStamp = v3Stats[i].timeStamp;
                    stats[i].startTime = v3Stats[i].startTime;
                }
                if (PublisherKit.this.audioStatsListener != null) {
                    PublisherKit.this.audioStatsListener.onAudioStats(PublisherKit.this, stats);
                }
            }
        };
        this.v3VideoStatsListener = new Publisher.VideoStatsListener() {
            public void onVideoStats(Publisher publisher, Publisher.VideoStats[] v3Stats) {
                PublisherVideoStats[] stats = new PublisherVideoStats[v3Stats.length];
                for (int i = 0; i < stats.length; i++) {
                    stats[i] = new PublisherVideoStats();
                    stats[i].connectionId = v3Stats[i].connectionId;
                    stats[i].subscriberId = v3Stats[i].subscriberId;
                    stats[i].videoBytesSent = v3Stats[i].videoBytesSent;
                    stats[i].videoPacketsSent = v3Stats[i].videoPacketsSent;
                    stats[i].videoPacketsLost = v3Stats[i].videoPacketsLost;
                    stats[i].timeStamp = v3Stats[i].timeStamp;
                    stats[i].startTime = v3Stats[i].startTime;
                }
                if (PublisherKit.this.videoStatsListener != null) {
                    PublisherKit.this.videoStatsListener.onVideoStats(PublisherKit.this, stats);
                }
            }
        };
        this.publishVideo = true;
        this.publishAudio = true;
        if (context2 == null) {
            throw new IllegalArgumentException("(Context context) is null, cannot create publisher object!");
        }
        this.context = context2;
        this.handler = new Handler(context2.getMainLooper());
        this.capturerWrapper = new V3CapturerWrapper(capturer2);
        this.rendererWrapper = new V3RendererWrapper(renderer2);
        this.v3Publisher = null;
        this.renderer = renderer2;
        this.capturer = capturer2;
        this.v3PublisherBuilder = new Publisher.Builder(context2).setPublisherName(name == null ? "" : name).setMaxAudioBitrate(maxAudioBitRate).setAudioTrackAvailable(audioTrack).setVideoTrackAvailable(videoTrack).setPublisherListener(this.v3PublisherListener).setAudioLevelListener(this.v3AudioListener).setAudioStatsListener(this.v3AudioStatsListener).setVideoStatsListener(this.v3VideoStatsListener).setCapturer(this.capturerWrapper).setRenderer(this.rendererWrapper);
    }

    @Deprecated
    public PublisherKit(Context context2, String name, boolean audioTrack, boolean videoTrack) {
        this(context2, name, audioTrack, 0, videoTrack, (BaseVideoCapturer) null, (BaseVideoRenderer) null);
    }

    @Deprecated
    public PublisherKit(Context context2) {
        this(context2, (String) null, true, 0, true, (BaseVideoCapturer) null, (BaseVideoRenderer) null);
    }

    @Deprecated
    public PublisherKit(Context context2, String name) {
        this(context2, name, true, 0, true, (BaseVideoCapturer) null, (BaseVideoRenderer) null);
    }

    public void onPause() {
        if (this.v3Publisher != null) {
            try {
                this.v3Publisher.pause();
            } catch (Publisher.PublisherException pe) {
                pe.printStackTrace();
            }
        }
    }

    public void onResume() {
        if (this.v3Publisher != null) {
            try {
                this.v3Publisher.resume();
            } catch (Publisher.PublisherException pe) {
                pe.printStackTrace();
            }
        }
    }

    public void destroy() {
        if (this.v3Publisher != null) {
            try {
                this.v3Publisher.close();
            } catch (Publisher.PublisherException pe) {
                throwError(new OpentokErrorImpl(OpentokError.Domain.PublisherErrorDomain, pe.getErrorCode()));
            } catch (Session.SessionException se) {
                throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
            } finally {
                this.v3Publisher = null;
            }
        }
    }

    public void setPublishVideo(boolean publishVideo2) {
        if (this.v3Publisher != null) {
            try {
                this.v3Publisher.enableVideoPublishing(publishVideo2);
            } catch (Publisher.PublisherException pe) {
                pe.printStackTrace();
            }
        } else {
            this.publishVideo = publishVideo2;
        }
    }

    public void setPublishAudio(boolean publishAudio2) {
        if (this.v3Publisher != null) {
            try {
                this.v3Publisher.enableAudioPublishing(publishAudio2);
            } catch (Publisher.PublisherException pe) {
                pe.printStackTrace();
            }
        } else {
            this.publishAudio = publishAudio2;
        }
    }

    @Deprecated
    public void setName(String name) {
        if (this.v3Publisher == null) {
            this.v3PublisherBuilder.setPublisherName(name);
        }
    }

    public void setPublisherVideoType(PublisherKitVideoType type) {
        if (this.v3Publisher != null) {
            setVideoTypeForce(type.getVideoType() - 1);
            return;
        }
        switch (type) {
            case PublisherKitVideoTypeCamera:
                this.v3PublisherBuilder.setVideoType(Stream.VideoType.Camera);
                return;
            case PublisherKitVideoTypeScreen:
                this.v3PublisherBuilder.setVideoType(Stream.VideoType.Screen);
                return;
            default:
                return;
        }
    }

    public String getName() {
        if (this.v3Publisher != null) {
            return this.v3Publisher.getName();
        }
        return this.v3PublisherBuilder.getPublisherName();
    }

    public boolean getPublishVideo() {
        if (this.v3Publisher != null) {
            return this.v3Publisher.isVideoPublishingEnabled();
        }
        return false;
    }

    public boolean getPublishAudio() {
        if (this.v3Publisher != null) {
            return this.v3Publisher.isAudioPublishingEnabled();
        }
        return false;
    }

    public Session getSession() {
        return this.session;
    }

    public Stream getStream() {
        if (this.v3Publisher == null || this.session == null) {
            return null;
        }
        return this.session.lookupStream(this.v3Publisher.getStream());
    }

    public PublisherKitVideoType getPublisherVideoType() {
        if (this.v3Publisher != null) {
            switch (this.v3Publisher.getVideoType()) {
                case Screen:
                    return PublisherKitVideoType.PublisherKitVideoTypeScreen;
                default:
                    return PublisherKitVideoType.PublisherKitVideoTypeCamera;
            }
        } else {
            switch (this.v3PublisherBuilder.getVideoType()) {
                case Screen:
                    return PublisherKitVideoType.PublisherKitVideoTypeScreen;
                default:
                    return PublisherKitVideoType.PublisherKitVideoTypeCamera;
            }
        }
    }

    public void setPublisherListener(PublisherListener listener) {
        this.publisherListener = listener;
    }

    public void setAudioLevelListener(AudioLevelListener listener) {
        this.audioLevelListener = listener;
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

    public BaseVideoCapturer getCapturer() {
        return this.capturerWrapper.getBaseVideoCapturer();
    }

    @Deprecated
    public void setCapturer(BaseVideoCapturer newCapturer) {
        this.capturerWrapper.setBaseVideoCapturer(newCapturer);
        this.capturer = newCapturer;
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

    public void setAudioFallbackEnabled(boolean enabled) {
        if (this.v3Publisher != null) {
            try {
                this.v3Publisher.enableAudioFallback(enabled);
            } catch (Publisher.PublisherException pe) {
                throwError(new OpentokError(OpentokError.Domain.PublisherErrorDomain, pe.getErrorCode(), pe.getMessage()));
            }
        } else {
            this.v3PublisherBuilder.enableAudioFallback(enabled);
        }
    }

    public boolean getAudioFallbackEnabled() {
        if (this.v3Publisher != null) {
            return this.v3Publisher.isAudioFallbackEnabled();
        }
        return this.v3PublisherBuilder.isAudioFallbackEnabled();
    }

    /* access modifiers changed from: package-private */
    public void throwError(final OpentokError error) {
        this.handler.post(new Runnable() {
            public void run() {
                PublisherKit.this.onError(error);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void buildIfNeeded() {
        if (this.v3Publisher == null) {
            try {
                this.v3Publisher = this.v3PublisherBuilder.Build();
                this.v3Publisher.enableAudioPublishing(this.publishAudio);
                this.v3Publisher.enableVideoPublishing(this.publishVideo);
            } catch (Publisher.PublisherException pe) {
                throwError(new OpentokErrorImpl(OpentokError.Domain.PublisherErrorDomain, pe.getErrorCode()));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void publish(Session session2) {
        buildIfNeeded();
        attachToSession(session2);
    }

    /* access modifiers changed from: package-private */
    public void unpublish() {
        destroy();
    }

    /* access modifiers changed from: package-private */
    public Publisher getV3Publisher() {
        return this.v3Publisher;
    }

    /* access modifiers changed from: protected */
    public void onStreamCreated(Stream stream) {
        if (this.publisherListener != null) {
            this.publisherListener.onStreamCreated(this, stream);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamDestroyed(Stream stream) {
        this.session = null;
        if (this.publisherListener != null) {
            this.publisherListener.onStreamDestroyed(this, stream);
        }
    }

    /* access modifiers changed from: protected */
    public void onError(OpentokError error) {
        if (this.publisherListener != null) {
            this.publisherListener.onError(this, error);
        }
    }

    /* access modifiers changed from: protected */
    public void onAudioLevelUpdated(float audioLevel) {
        if (this.audioLevelListener != null) {
            this.audioLevelListener.onAudioLevelUpdated(this, audioLevel);
        }
    }

    /* access modifiers changed from: protected */
    public void attachToSession(Session session2) {
        this.session = session2;
    }

    /* access modifiers changed from: protected */
    public void detachFromSession(Session session2) {
    }

    private void setVideoTypeForce(int type) {
        try {
            Method method = this.v3Publisher.getClass().getDeclaredMethod("setVideoTypeNative", new Class[]{Long.TYPE, Integer.TYPE});
            Field field = this.v3Publisher.getClass().getDeclaredField("nativeCtx");
            method.setAccessible(true);
            field.setAccessible(true);
            method.invoke(this.v3Publisher, new Object[]{field.get(this.v3Publisher), Integer.valueOf(type)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
