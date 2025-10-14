package com.opentok.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.opentok.android.OpentokError;
import com.opentok.android.OtLog;
import com.opentok.android.Stream;
import com.opentok.android.v3.Connection;
import com.opentok.android.v3.OpentokException;
import com.opentok.android.v3.Publisher;
import com.opentok.android.v3.Session;
import com.opentok.android.v3.Stream;
import com.opentok.android.v3.Subscriber;
import com.opentok.impl.OpentokErrorImpl;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class Session {
    private static final String INTENT_ACTION = (Session.class.getPackage().getName() + ".log.event");
    /* access modifiers changed from: private */
    public static final OtLog.LogToken log = new OtLog.LogToken("[session]", true);
    private HashMap<String, Stream> activeStreams;
    protected String apiKey;
    protected ArchiveListener archiveListener;
    protected Connection connection;
    protected boolean connectionEventsSuppressed;
    protected ConnectionListener connectionListener;
    private Context context;
    private Handler handler;
    protected boolean ipWhitelist;
    private boolean isLoggingReceiverRegistered;
    private BroadcastReceiver loggingReceiver;
    protected ReconnectionListener reconnectionListener;
    protected String sessionId;
    protected SessionListener sessionListener;
    private boolean shouldRegisterLoggingReceiver;
    protected SignalListener signalListener;
    protected StreamPropertiesListener streamPropertiesListener;
    private Session.ArchiveListener v3ArchiveObserver;
    private Session.ConnectionListener v3ConnectionObserver;
    private Session.ReconnectionListener v3ReconnectionObserver;
    private Session.SessionListener v3SessionObserver;
    private Session.SignalListener v3SignalObserver;
    private Session.StreamPropertiesListener v3StreamPropertiesObserver;
    /* access modifiers changed from: private */
    public com.opentok.android.v3.Session v3session;

    public interface ArchiveListener {
        void onArchiveStarted(Session session, String str, String str2);

        void onArchiveStopped(Session session, String str);
    }

    public interface ConnectionListener {
        void onConnectionCreated(Session session, Connection connection);

        void onConnectionDestroyed(Session session, Connection connection);
    }

    public interface ReconnectionListener {
        void onReconnected(Session session);

        void onReconnecting(Session session);
    }

    public interface SessionListener {
        void onConnected(Session session);

        void onDisconnected(Session session);

        void onError(Session session, OpentokError opentokError);

        void onStreamDropped(Session session, Stream stream);

        void onStreamReceived(Session session, Stream stream);
    }

    @Deprecated
    public interface SessionOptionsProvider {
        @Deprecated
        boolean isHwDecodingSupported();
    }

    public interface SignalListener {
        void onSignalReceived(Session session, String str, String str2, Connection connection);
    }

    public interface StreamPropertiesListener {
        void onStreamHasAudioChanged(Session session, Stream stream, boolean z);

        void onStreamHasVideoChanged(Session session, Stream stream, boolean z);

        void onStreamVideoDimensionsChanged(Session session, Stream stream, int i, int i2);

        void onStreamVideoTypeChanged(Session session, Stream stream, Stream.StreamVideoType streamVideoType);
    }

    static {
        Log.i("OpenTok Android SDK", "*********************** OPENTOK ANDROID SDK 2.15.3 ********************************");
        Log.i("OpenTok Android SDK", "**** Lib built on 20190117151249");
        Log.i("OpenTok Android SDK", "**** OpenTok Android SDK : d86d4fc8373d99ee992495bcd499cd0d0b077609");
        Log.i("OpenTok Android SDK", "**** Copyright 2018 TokBox, Inc.");
        Log.i("OpenTok Android SDK", "**** Licensed under the Apache License, Version 2.0");
        Log.i("OpenTok Android SDK", "************************************************************************");
    }

    public static class Capabilities {
        public boolean canPublish;
        public boolean canSubscribe;

        public String toString() {
            StringBuilder str = new StringBuilder("[\n");
            for (Field f : getClass().getFields()) {
                try {
                    str.append(String.format("\t%s = %b\n", new Object[]{f.getName(), f.get(this)}));
                } catch (IllegalAccessException e) {
                    Session.log.e("Error converting Capabilities to String", new Object[0]);
                }
            }
            str.append(']');
            return str.toString();
        }
    }

    public static abstract class SessionOptions {
        private final Map<String, Boolean> cam2EnableList = new HashMap<String, Boolean>() {
            {
                put("nexus 4", true);
                put("nexus 5", true);
                put("nexus 5x", true);
                put("nexus 6", true);
                put("nexus 6p", true);
                put("nexus 7", true);
                put("nexus 10", true);
                put("pixel", true);
                put("gt-i9300", true);
                put("samsung-sm-g925a", true);
                put("samsung-sm-g935a", true);
                put("samsung-sm-t817a", true);
                put("sm-g900h", true);
                put("sm-j106h", true);
                put("lgus991", true);
                put("lg-h810", true);
                put("lg-k430", true);
                put("xt1058", true);
                put("aquaris e5", true);
                put("c6602", true);
            }
        };

        @Deprecated
        public boolean isHwDecodingSupported() {
            return false;
        }

        public boolean isCamera2Capable() {
            return this.cam2EnableList.containsKey(Build.MODEL.toLowerCase(Locale.ROOT));
        }

        public boolean useTextureViews() {
            return false;
        }
    }

    protected static class ConfigurableSessionOptions extends SessionOptions {
        private boolean hwDecCapable = false;

        ConfigurableSessionOptions(boolean hwDecCapable2) {
            this.hwDecCapable = hwDecCapable2;
        }

        public boolean isHwDecodingSupported() {
            return this.hwDecCapable;
        }
    }

    public static class Builder {
        String apiKey;
        URL apiUrl;
        boolean connectionEventsSuppressed = false;
        Context context;
        IceServer[] iceServers = new IceServer[0];
        boolean ipWhitelist = false;
        String sessionId;
        SessionOptions sessionOptions = new SessionOptions() {
        };
        TransportPolicy turnRouting = TransportPolicy.All;
        IncludeServers turnServerConfig = IncludeServers.All;

        public enum IncludeServers {
            Custom(0),
            All(1);
            
            /* access modifiers changed from: private */
            public int val;

            private IncludeServers(int val2) {
                this.val = val2;
            }
        }

        public enum TransportPolicy {
            All(0),
            Relay(1);
            
            /* access modifiers changed from: private */
            public int val;

            private TransportPolicy(int val2) {
                this.val = val2;
            }
        }

        public static class IceServer {
            public String credential = "";
            public String url = "";
            public String user = "";

            public IceServer(String url2, String user2, String credential2) {
                this.url = url2;
                this.user = user2;
                this.credential = credential2;
            }
        }

        public Builder(Context context2, String apiKey2, String sessionId2) {
            this.context = context2;
            this.apiKey = apiKey2;
            this.sessionId = sessionId2;
        }

        public Builder connectionEventsSuppressed(Boolean enabled) {
            this.connectionEventsSuppressed = enabled.booleanValue();
            return this;
        }

        public Builder sessionOptions(SessionOptions sessionOptions2) {
            this.sessionOptions = sessionOptions2;
            return this;
        }

        public Builder setCustomIceServers(List<IceServer> serverList, IncludeServers config) {
            this.iceServers = (IceServer[]) serverList.toArray(new IceServer[serverList.size()]);
            this.turnServerConfig = config;
            return this;
        }

        public Builder setIceRouting(TransportPolicy routing) {
            this.turnRouting = routing;
            return this;
        }

        public Builder setApiUrl(URL apiUrl2) {
            this.apiUrl = apiUrl2;
            return this;
        }

        public Builder setIpWhitelist(boolean enabled) {
            this.ipWhitelist = enabled;
            return this;
        }

        public Session build() {
            return new Session(this.context, this.apiKey, this.sessionId, this.connectionEventsSuppressed, this.sessionOptions, this.turnRouting, this.turnServerConfig, this.iceServers, this.apiUrl, this.ipWhitelist);
        }
    }

    @Deprecated
    public Session(Context context2, String apiKey2, String sessionId2) {
        this(context2, apiKey2, sessionId2, (SessionOptions) new SessionOptions() {
        });
    }

    @Deprecated
    public Session(Context context2, String apiKey2, String sessionId2, SessionOptionsProvider optionsProvider) {
        this(context2, apiKey2, sessionId2, (SessionOptions) new ConfigurableSessionOptions(optionsProvider.isHwDecodingSupported()));
    }

    @Deprecated
    public Session(Context context2, String apiKey2, String sessionId2, SessionOptions sessionOptions) {
        this(context2, apiKey2, sessionId2, false, sessionOptions, Builder.TransportPolicy.All, Builder.IncludeServers.All, new Builder.IceServer[0], (URL) null, false);
    }

    protected Session(Context context2, String apiKey2, String sessionId2, boolean connectionEventsSuppressed2, SessionOptions sessionOptions, Builder.TransportPolicy routing, Builder.IncludeServers turnConfig, Builder.IceServer[] iceServers, URL apiUrl, boolean ipWhitelist2) {
        this.v3SessionObserver = new Session.SessionListener() {
            public void onConnected(com.opentok.android.v3.Session session) {
                if (Build.VERSION.SDK_INT >= 19) {
                    try {
                        JSONObject logMsg = new JSONObject();
                        JSONArray encoderMsg = new JSONArray();
                        JSONArray decoderMSg = new JSONArray();
                        logMsg.put("encoders", encoderMsg);
                        logMsg.put("decoders", decoderMSg);
                        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
                            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
                            if (info != null && info.isEncoder()) {
                                for (String mimeType : info.getSupportedTypes()) {
                                    if (mimeType.equals("video/avc")) {
                                        MediaCodecInfo.CodecCapabilities capabilities = info.getCapabilitiesForType(mimeType);
                                        JSONObject codecInfoMsg = new JSONObject();
                                        codecInfoMsg.put("codec", info.getName());
                                        codecInfoMsg.put("color_format", Arrays.toString(capabilities.colorFormats));
                                        encoderMsg.put(codecInfoMsg);
                                    }
                                }
                            } else if (info != null) {
                                for (String mimeType2 : info.getSupportedTypes()) {
                                    if (mimeType2.equals("video/avc")) {
                                        MediaCodecInfo.CodecCapabilities capabilities2 = info.getCapabilitiesForType(mimeType2);
                                        JSONObject codecInfoMsg2 = new JSONObject();
                                        codecInfoMsg2.put("codec", info.getName());
                                        codecInfoMsg2.put("color_format", Arrays.toString(capabilities2.colorFormats));
                                        decoderMSg.put(codecInfoMsg2);
                                    }
                                }
                            }
                        }
                        Session.this.logCustomMsg("codec-avail", logMsg.toString());
                        Session.log.i(logMsg.toString(), new Object[0]);
                    } catch (Exception e) {
                        Session.log.w("Failed to analyze codec list: " + e.getMessage(), new Object[0]);
                        e.printStackTrace();
                    }
                }
                Session.this.connection = new Connection(Session.this.v3session.getConnection());
                Session.this.onConnected();
            }

            public void onDisconnected(com.opentok.android.v3.Session session) {
                Session.this.onDisconnected();
                Session.this.connection = null;
            }

            public void onStreamReceived(com.opentok.android.v3.Session session, com.opentok.android.v3.Stream v3Stream) {
                Session.this.onStreamReceived(Session.this.addStream(v3Stream));
            }

            public void onStreamDropped(com.opentok.android.v3.Session v3Session, com.opentok.android.v3.Stream v3Stream) {
                Session.this.onStreamDropped(Session.this.delStream(v3Stream));
            }

            public void onError(com.opentok.android.v3.Session session, OpentokException error) {
                Session.this.onError(new OpentokError(OpentokError.Domain.SessionErrorDomain, error.getErrorCode(), error.getMessage()));
            }
        };
        this.v3ReconnectionObserver = new Session.ReconnectionListener() {
            public void onReconnecting(com.opentok.android.v3.Session session) {
                Session.this.onReconnecting();
            }

            public void onReconnected(com.opentok.android.v3.Session session) {
                Session.this.onReconnected();
            }
        };
        this.v3SignalObserver = new Session.SignalListener() {
            public void onSignalReceived(com.opentok.android.v3.Session v3Session, String type, String data, Connection v3Connection) {
                Session.this.onSignalReceived(type, data, v3Connection == null ? null : new Connection(v3Connection));
            }
        };
        this.v3ConnectionObserver = new Session.ConnectionListener() {
            public void onConnectionCreated(com.opentok.android.v3.Session v3Session, Connection v3Connection) {
                Session.this.onConnectionCreated(new Connection(v3Connection));
            }

            public void onConnectionDestroyed(com.opentok.android.v3.Session v3Session, Connection v3Connection) {
                Session.this.onConnectionDestroyed(new Connection(v3Connection));
            }
        };
        this.v3StreamPropertiesObserver = new Session.StreamPropertiesListener() {
            public void onStreamHasAudioChanged(com.opentok.android.v3.Session v3Session, com.opentok.android.v3.Stream v3Stream, boolean hasAudio) {
                Session.this.onStreamHasAudioChanged(Session.this.lookupStream(v3Stream), hasAudio ? 1 : 0);
            }

            public void onStreamHasVideoChanged(com.opentok.android.v3.Session v3Session, com.opentok.android.v3.Stream v3Stream, boolean hasVideo) {
                Session.this.onStreamHasVideoChanged(Session.this.lookupStream(v3Stream), hasVideo ? 1 : 0);
            }

            public void onStreamVideoDimensionsChanged(com.opentok.android.v3.Session v3Session, com.opentok.android.v3.Stream v3Stream, int width, int height) {
                Session.this.onStreamVideoDimensionsChanged(Session.this.lookupStream(v3Stream), width, height);
            }

            public void onStreamVideoTypeChanged(com.opentok.android.v3.Session v3Session, com.opentok.android.v3.Stream v3Stream, Stream.VideoType videoType) {
                Session.this.onStreamVideoTypeChanged(Session.this.lookupStream(v3Stream), Stream.StreamVideoType.fromType(videoType.ordinal()));
            }
        };
        this.v3ArchiveObserver = new Session.ArchiveListener() {
            public void onArchiveStarted(com.opentok.android.v3.Session v3Session, String id, String name) {
                Session.this.onArchiveStarted(id, name);
            }

            public void onArchiveStopped(com.opentok.android.v3.Session v3Session, String id) {
                Session.this.onArchiveStopped(id);
            }
        };
        this.loggingReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String logParam;
                if (intent.getExtras() != null && intent.hasExtra("event") && (logParam = intent.getExtras().getString("event")) != null) {
                    Session.this.logAdHocAction(logParam);
                }
            }
        };
        this.apiKey = apiKey2;
        this.context = context2;
        this.sessionId = sessionId2;
        this.activeStreams = new HashMap<>();
        this.handler = new Handler(context2.getMainLooper());
        this.connectionEventsSuppressed = connectionEventsSuppressed2;
        this.ipWhitelist = ipWhitelist2;
        VideoCaptureFactory.enableCamera2api(Build.VERSION.SDK_INT >= 21 && sessionOptions.isCamera2Capable());
        VideoRenderFactory.useTextureViews(sessionOptions.useTextureViews());
        Session.Builder.TransportPolicy[] policyMap = {Session.Builder.TransportPolicy.All, Session.Builder.TransportPolicy.Relay};
        Session.Builder.IncludeServers[] includeServersMap = {Session.Builder.IncludeServers.Custom, Session.Builder.IncludeServers.All};
        ArrayList<Session.Builder.IceServer> iceServerArrayLst = new ArrayList<>();
        for (Builder.IceServer iceServer : iceServers) {
            iceServerArrayLst.add(new Session.Builder.IceServer(iceServer.url, iceServer.user, iceServer.credential));
        }
        try {
            this.v3session = new Session.Builder(context2, apiKey2, sessionId2).setSessionListener(this.v3SessionObserver).setReconnectionListener(this.v3ReconnectionObserver).setConnectionListener(this.v3ConnectionObserver).setSignalListener(this.v3SignalObserver).setStreamListener(this.v3StreamPropertiesObserver).setArchiveListener(this.v3ArchiveObserver).connectionEventsSuppressed(Boolean.valueOf(connectionEventsSuppressed2)).setApiUrl(apiUrl == null ? new URL(WrapperSettings.API_URL) : apiUrl).setIceRouting(policyMap[routing.val]).setCustomIceServers(iceServerArrayLst, includeServersMap[turnConfig.val]).setIpWhitelist(ipWhitelist2).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AudioDeviceManager.initializeDefaultDevice(context2);
    }

    public Connection getConnection() {
        return new Connection(this.v3session.getConnection());
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionListener(SessionListener listener) {
        this.sessionListener = listener;
    }

    public void setConnectionListener(ConnectionListener listener) {
        this.connectionListener = listener;
    }

    public void setStreamPropertiesListener(StreamPropertiesListener listener) {
        this.streamPropertiesListener = listener;
    }

    public void setSignalListener(SignalListener listener) {
        this.signalListener = listener;
    }

    public void setArchiveListener(ArchiveListener listener) {
        this.archiveListener = listener;
    }

    public void setReconnectionListener(ReconnectionListener listener) {
        this.reconnectionListener = listener;
    }

    public void connect(String token) {
        if (this.sessionId == null || this.sessionId.isEmpty()) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, OpentokError.ErrorCode.InvalidSessionId.getErrorCode()));
            return;
        }
        try {
            if (this.apiKey == null) {
                throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, OpentokError.ErrorCode.AuthorizationFailure.getErrorCode()));
            } else {
                this.v3session.connect(token);
            }
        } catch (Session.SessionException se) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
        }
    }

    public void disconnect() {
        try {
            this.v3session.disconnect();
        } catch (Session.SessionException se) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
        }
    }

    public void onPause() {
        try {
            this.v3session.pause();
        } catch (Publisher.PublisherException pe) {
            pe.printStackTrace();
        } catch (Subscriber.SubscriberException se) {
            se.printStackTrace();
        }
        unregisterLoggingEventsReceiver();
    }

    public void onResume() {
        try {
            this.v3session.resume();
        } catch (Publisher.PublisherException pe) {
            pe.printStackTrace();
        } catch (Subscriber.SubscriberException se) {
            se.printStackTrace();
        }
        registerLoggingEventsReceiver();
    }

    public void publish(PublisherKit publisher) {
        if (publisher != null) {
            if (publisher.getSession() == null) {
                try {
                    publisher.publish(this);
                    this.v3session.publish(publisher.getV3Publisher());
                } catch (Session.SessionException se) {
                    publisher.unpublish();
                    publisher.detachFromSession(this);
                    publisher.destroy();
                    publisher.throwError(new OpentokErrorImpl(OpentokError.Domain.PublisherErrorDomain, se.getErrorCode()));
                }
            } else {
                throwError(new OpentokErrorImpl(OpentokError.Domain.PublisherErrorDomain, OpentokError.ErrorCode.SessionNullOrInvalidParameter.getErrorCode()));
            }
        }
    }

    public void unpublish(PublisherKit publisher) {
        if (publisher == null) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, OpentokError.ErrorCode.SessionNullOrInvalidParameter.getErrorCode()));
        } else if (publisher.getV3Publisher() == null) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, OpentokError.ErrorCode.UnknownPublisherInstance.getErrorCode()));
        } else {
            try {
                this.v3session.unpublish(publisher.getV3Publisher());
            } catch (Session.SessionException se) {
                if (se.getErrorCode() == Session.SessionException.Error.PUBLISHER_NOT_FOUND.errno) {
                    throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, OpentokError.ErrorCode.UnknownPublisherInstance.getErrorCode()));
                } else {
                    throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
                }
            } finally {
                publisher.unpublish();
                publisher.detachFromSession(this);
            }
        }
    }

    public void subscribe(SubscriberKit subscriber) {
        try {
            this.v3session.subscribe(subscriber.getV3Subscriber());
            if (Stream.StreamVideoType.StreamVideoTypeScreen == subscriber.getStream().getStreamVideoType()) {
                subscriber.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE, BaseVideoRenderer.STYLE_VIDEO_FIT);
            }
            subscriber.attachToSession(this);
        } catch (Session.SessionException se) {
            subscriber.throwError(new OpentokErrorImpl(OpentokError.Domain.SubscriberErrorDomain, se.getErrorCode()));
        }
    }

    public void unsubscribe(SubscriberKit subscriber) {
        try {
            this.v3session.unsubscribe(subscriber.getV3Subscriber());
            subscriber.detachFromSession(this);
        } catch (Session.SessionException se) {
            if (Session.SessionException.Error.SUBSCRIBER_NOT_FOUND.errno == se.getErrorCode()) {
                throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, OpentokError.ErrorCode.UnknownSubscriberInstance.getErrorCode()));
            } else {
                throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
            }
        }
    }

    public void sendSignal(String type, String data) {
        try {
            this.v3session.sendSignal(type, data);
        } catch (Session.SessionException se) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
        }
    }

    public void sendSignal(String type, String data, boolean retryAfterReconnect) {
        try {
            this.v3session.sendSignal(type, data, retryAfterReconnect);
        } catch (Session.SessionException se) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
        }
    }

    public void sendSignal(String type, String data, Connection connection2) {
        if (connection2 == null) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, OpentokError.ErrorCode.ConnectionFailed.getErrorCode()));
            return;
        }
        try {
            this.v3session.sendSignal(type, data, connection2.getV3Hndl());
        } catch (Session.SessionException se) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
        }
    }

    public void sendSignal(String type, String data, Connection connection2, boolean retryAfterReconnect) {
        if (connection2 == null) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, OpentokError.ErrorCode.ConnectionFailed.getErrorCode()));
            return;
        }
        try {
            this.v3session.sendSignal(type, data, connection2.getV3Hndl(), retryAfterReconnect);
        } catch (Session.SessionException se) {
            throwError(new OpentokErrorImpl(OpentokError.Domain.SessionErrorDomain, se.getErrorCode()));
        }
    }

    public Capabilities getCapabilities() {
        Session.Capabilities sessionCaps = this.v3session.getCapabilities();
        Capabilities capabilities = new Capabilities();
        capabilities.canPublish = sessionCaps.canPublish;
        capabilities.canSubscribe = sessionCaps.canPublish;
        return capabilities;
    }

    public String reportIssue() {
        try {
            return this.v3session.reportIssue("");
        } catch (Session.SessionException se) {
            se.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void throwError(final OpentokError error) {
        if (this.sessionListener != null) {
            this.handler.post(new Runnable() {
                public void run() {
                    synchronized (this) {
                        Session.this.sessionListener.onError(Session.this, error);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public Stream addStream(com.opentok.android.v3.Stream v3Stream) {
        Stream newStream = new Stream(v3Stream, this);
        this.activeStreams.put(v3Stream.getStreamId(), newStream);
        return newStream;
    }

    /* access modifiers changed from: package-private */
    public Stream delStream(com.opentok.android.v3.Stream v3Stream) {
        Stream stream = lookupStream(v3Stream);
        this.activeStreams.remove(v3Stream.getStreamId());
        return stream;
    }

    /* access modifiers changed from: package-private */
    public Stream lookupStream(com.opentok.android.v3.Stream v3Stream) {
        if (v3Stream != null) {
            return this.activeStreams.get(v3Stream.getStreamId());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public com.opentok.android.v3.Session getV3session() {
        return this.v3session;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
    }

    /* access modifiers changed from: private */
    public void logAdHocAction(String action) {
        try {
            Method method = this.v3session.getClass().getDeclaredMethod("logAdHocAction", new Class[]{String.class});
            method.setAccessible(true);
            method.invoke(this.v3session, new Object[]{action});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void logCustomMsg(String event, String payload) {
        try {
            Method method = this.v3session.getClass().getDeclaredMethod("logCustomClientEvent", new Class[]{String.class, String.class});
            method.setAccessible(true);
            method.invoke(this.v3session, new Object[]{event, payload});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enableLoggingEventsReceiver() {
        this.shouldRegisterLoggingReceiver = true;
        registerLoggingEventsReceiver();
    }

    private void disableLoggingEventsReceiver() {
        this.shouldRegisterLoggingReceiver = false;
        unregisterLoggingEventsReceiver();
    }

    private void registerLoggingEventsReceiver() {
        if (!this.isLoggingReceiverRegistered && this.shouldRegisterLoggingReceiver) {
            this.context.registerReceiver(this.loggingReceiver, new IntentFilter(INTENT_ACTION));
            this.isLoggingReceiverRegistered = true;
        }
    }

    private void unregisterLoggingEventsReceiver() {
        if (this.isLoggingReceiverRegistered) {
            try {
                this.context.unregisterReceiver(this.loggingReceiver);
                this.isLoggingReceiverRegistered = false;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onArchiveStarted(String id, String name) {
        if (this.archiveListener != null) {
            this.archiveListener.onArchiveStarted(this, id, name);
        }
    }

    /* access modifiers changed from: protected */
    public void onArchiveStopped(String id) {
        if (this.archiveListener != null) {
            this.archiveListener.onArchiveStopped(this, id);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamHasAudioChanged(Stream stream, int hasAudio) {
        if (this.streamPropertiesListener != null) {
            this.streamPropertiesListener.onStreamHasAudioChanged(this, stream, hasAudio != 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamHasVideoChanged(Stream stream, int hasVideo) {
        if (this.streamPropertiesListener != null) {
            this.streamPropertiesListener.onStreamHasVideoChanged(this, stream, hasVideo != 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamVideoDimensionsChanged(Stream stream, int width, int height) {
        if (this.streamPropertiesListener != null) {
            this.streamPropertiesListener.onStreamVideoDimensionsChanged(this, stream, width, height);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamVideoTypeChanged(Stream stream, Stream.StreamVideoType videoType) {
        if (this.streamPropertiesListener != null) {
            this.streamPropertiesListener.onStreamVideoTypeChanged(this, stream, videoType);
        }
    }

    /* access modifiers changed from: protected */
    public void onConnectionCreated(Connection connection2) {
        if (this.connectionListener != null) {
            this.connectionListener.onConnectionCreated(this, connection2);
        }
    }

    /* access modifiers changed from: protected */
    public void onConnectionDestroyed(Connection connection2) {
        if (this.connectionListener != null) {
            this.connectionListener.onConnectionDestroyed(this, connection2);
        }
    }

    public void onSignalReceived(String type, String data, Connection connection2) {
        if (this.signalListener != null) {
            this.signalListener.onSignalReceived(this, type, data, connection2);
        }
    }

    /* access modifiers changed from: protected */
    public void onReconnecting() {
        if (this.reconnectionListener != null) {
            this.reconnectionListener.onReconnecting(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onReconnected() {
        if (this.reconnectionListener != null) {
            this.reconnectionListener.onReconnected(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onConnected() {
        if (this.sessionListener != null) {
            this.sessionListener.onConnected(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDisconnected() {
        synchronized (this) {
            if (this.sessionListener != null) {
                this.sessionListener.onDisconnected(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamReceived(Stream stream) {
        if (this.sessionListener != null) {
            this.sessionListener.onStreamReceived(this, stream);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamDropped(Stream stream) {
        if (this.sessionListener != null) {
            this.sessionListener.onStreamDropped(this, stream);
        }
    }

    /* access modifiers changed from: protected */
    public void onError(OpentokError error) {
        if (this.sessionListener != null) {
            this.sessionListener.onError(this, error);
        }
    }
}
