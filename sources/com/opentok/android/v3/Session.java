package com.opentok.android.v3;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.v4.view.PointerIconCompat;
import android.util.SparseArray;
import com.google.android.gms.cast.CastStatusCodes;
import com.opentok.android.v3.Publisher;
import com.opentok.android.v3.Stream;
import com.opentok.android.v3.Subscriber;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

@Keep
public class Session {
    /* access modifiers changed from: private */
    public static final LogInterface log = OtLog.LogToken("[Session]");
    private URL apiUrl;
    /* access modifiers changed from: private */
    public final ArchiveListener archiveCb;
    /* access modifiers changed from: private */
    public final ConnectionListener connectionCb;
    private final Handler mainThreadHandler;
    private long nativeCtx;
    /* access modifiers changed from: private */
    public ArrayList<Publisher> publisherLst;
    /* access modifiers changed from: private */
    public final ReconnectionListener reconnectionCb;
    /* access modifiers changed from: private */
    public final SessionListener sessionCb;
    /* access modifiers changed from: private */
    public final SignalListener signalCb;
    /* access modifiers changed from: private */
    public final StreamPropertiesListener streamCb;
    /* access modifiers changed from: private */
    public ArrayList<Subscriber> subscriberLst;

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

        void onError(Session session, OpentokException opentokException);

        void onStreamDropped(Session session, Stream stream);

        void onStreamReceived(Session session, Stream stream);
    }

    public interface SignalListener {
        void onSignalReceived(Session session, String str, String str2, Connection connection);
    }

    public interface StreamPropertiesListener {
        void onStreamHasAudioChanged(Session session, Stream stream, boolean z);

        void onStreamHasVideoChanged(Session session, Stream stream, boolean z);

        void onStreamVideoDimensionsChanged(Session session, Stream stream, int i, int i2);

        void onStreamVideoTypeChanged(Session session, Stream stream, Stream.VideoType videoType);
    }

    private native int connectNative(long j, String str, String str2, int i, boolean z, String str3);

    private native int disconnectNative(long j);

    private native long finalizeNative(long j);

    private native Capabilities getCapabilitiesNative(long j);

    private native long getConnectionNative(long j);

    private native String[] getSupportedVideoCodecsNative(long j);

    private native long initNative(Context context, String str, String str2, boolean z, int i, int i2, Builder.IceServer[] iceServerArr, boolean z2);

    private native void logAdHocActionNative(long j, String str) throws SessionException;

    private native void logCustomClientEventNative(long j, String str, String str2);

    private native int publishNative(long j, long j2);

    private static native void registerNatives();

    private native String reportIssueNative(long j, String str) throws SessionException;

    private native int sendSignalNative(long j, String str, String str2, boolean z);

    private native int sendSignalToCxnNative(long j, String str, String str2, long j2, boolean z);

    private native int subscribeNative(long j, long j2);

    private native int unpublishNative(long j, long j2);

    private native int unsubscribeNative(long j, long j2);

    public static class Capabilities {
        public final boolean canPublish;
        public final boolean canSubscribe;

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

        Capabilities(boolean canPublish2, boolean canSubscribe2) {
            this.canPublish = canPublish2;
            this.canSubscribe = canSubscribe2;
        }
    }

    public static class SessionException extends OpentokException {

        public enum Error {
            UNKNOWN_ERROR(-1),
            SUCCESS(0),
            AUTHORIZATION_FAILURE(1004),
            INVALID_SESSION(1005),
            CONNECTION_FAILED(PointerIconCompat.TYPE_CELL),
            NOT_CONNECTED(PointerIconCompat.TYPE_ALIAS),
            NULL_OR_INVALID_PARAMETER(PointerIconCompat.TYPE_COPY),
            ILLEGAL_STATE(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW),
            STATE_FAILED(PointerIconCompat.TYPE_GRAB),
            CONNECTION_TIMED_OUT(PointerIconCompat.TYPE_GRABBING),
            CONNECTION_DROPPED(1022),
            CONNECTION_REFUSED(1023),
            BLOCKED_COUNTRY(1026),
            CONNECTION_LIMIT_EXCEEDED(1027),
            SUBSCRIBER_NOT_FOUND(1112),
            PUBLISHER_NOT_FOUND(1113),
            SIGNAL_DATA_TOO_LONG(1413),
            SIGNAL_TYPE_TOO_LONG(1414),
            INVALID_SIGNAL_TYPE(1461),
            NO_MESSAGING_SERVER(1503),
            UNABLE_TO_FORCE_DISCONNECT(1520),
            UNABLE_TO_FORCE_UNPUBLISH(1530),
            FORCE_UNPUBLISH_OR_INVALID_STREAM(1535),
            SESSION_DISCONNECTED(1541),
            INTERNAL_ERROR(CastStatusCodes.AUTHENTICATION_FAILED),
            UNEXPECTED_GET_SESSION_INFO_REPONSE(CastStatusCodes.INVALID_REQUEST),
            UKNOWN_PUBLISHER_INSTANCE(CastStatusCodes.NOT_ALLOWED),
            UNKNOWN_SUBSCRIBER_INSTANCE(CastStatusCodes.APPLICATION_NOT_FOUND);
            
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

        SessionException(Error error) {
            this(error, error.name().replace('_', ' '));
        }

        SessionException(Error error, String message) {
            super(error.errno, message);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        SessionException(int r4) {
            /*
                r3 = this;
                r2 = 95
                r1 = 32
                com.opentok.android.v3.Session$SessionException$Error r0 = com.opentok.android.v3.Session.SessionException.Error.getError(r4)
                if (r0 == 0) goto L_0x001a
                com.opentok.android.v3.Session$SessionException$Error r0 = com.opentok.android.v3.Session.SessionException.Error.getError(r4)
                java.lang.String r0 = r0.name()
                java.lang.String r0 = r0.replace(r2, r1)
            L_0x0016:
                r3.<init>(r4, r0)
                return
            L_0x001a:
                com.opentok.android.v3.Session$SessionException$Error r0 = com.opentok.android.v3.Session.SessionException.Error.UNKNOWN_ERROR
                java.lang.String r0 = r0.name()
                java.lang.String r0 = r0.replace(r2, r1)
                goto L_0x0016
            */
            throw new UnsupportedOperationException("Method not decompiled: com.opentok.android.v3.Session.SessionException.<init>(int):void");
        }

        SessionException(int error, String message) {
            super(error, message);
        }
    }

    public static class Builder {
        private String apiKey;
        private ArchiveListener archiveCb;
        private ConnectionListener connectionCb;
        private boolean connectionEventsSuppressed = false;
        private Context context;
        private IceServer[] iceServers;
        private boolean ipWhitelist = false;
        private ReconnectionListener reconnectionCb;
        private SessionListener sessionCb;
        private String sessionId;
        private SignalListener signalCb;
        private StreamPropertiesListener streamCb;
        private TransportPolicy turnRouting = TransportPolicy.All;
        private IncludeServers turnServerConfig = IncludeServers.All;
        private URL url;

        public enum IncludeServers {
            All,
            Custom
        }

        public enum TransportPolicy {
            All,
            Relay
        }

        public static class IceServer {
            public final String credential;
            public final String url;
            public final String user;

            public IceServer(String url2, String user2, String credential2) {
                this.url = url2;
                this.user = user2;
                this.credential = credential2;
            }

            public boolean equals(Object obj) {
                return (obj instanceof IceServer) && ((IceServer) obj).url.equals(this.url) && ((IceServer) obj).user.equals(this.user) && ((IceServer) obj).credential.equals(this.credential);
            }
        }

        public Builder(Context context2, String apiKey2, String sessionId2) {
            try {
                this.context = context2;
                this.apiKey = apiKey2;
                this.sessionId = sessionId2;
                this.connectionEventsSuppressed = false;
                this.sessionCb = null;
                this.connectionCb = null;
                this.streamCb = null;
                this.signalCb = null;
                this.reconnectionCb = null;
                this.archiveCb = null;
                this.url = new URL(BuildConfig.API_URL);
                this.turnRouting = TransportPolicy.All;
                this.iceServers = new IceServer[0];
                this.turnServerConfig = IncludeServers.All;
                this.ipWhitelist = false;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        public Builder connectionEventsSuppressed(Boolean enabled) {
            this.connectionEventsSuppressed = enabled.booleanValue();
            return this;
        }

        public Builder setSessionListener(SessionListener listener) {
            this.sessionCb = listener;
            return this;
        }

        public Builder setConnectionListener(ConnectionListener listener) {
            this.connectionCb = listener;
            return this;
        }

        public Builder setStreamListener(StreamPropertiesListener listener) {
            this.streamCb = listener;
            return this;
        }

        public Builder setSignalListener(SignalListener listener) {
            this.signalCb = listener;
            return this;
        }

        public Builder setReconnectionListener(ReconnectionListener listener) {
            this.reconnectionCb = listener;
            return this;
        }

        public Builder setArchiveListener(ArchiveListener listener) {
            this.archiveCb = listener;
            return this;
        }

        public Builder setApiUrl(URL url2) {
            this.url = url2;
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

        public Builder setIpWhitelist(boolean enabled) {
            this.ipWhitelist = enabled;
            return this;
        }

        public Session build() throws OpentokException {
            return new Session(this.context, this.apiKey, this.sessionId, this.connectionEventsSuppressed, this.sessionCb, this.connectionCb, this.streamCb, this.signalCb, this.reconnectionCb, this.archiveCb, this.url, this.turnRouting, this.turnServerConfig, this.iceServers, this.ipWhitelist);
        }
    }

    public void connect(String token) throws SessionException {
        log.d("Connect(...) called", new Object[0]);
        if (token == null) {
            throw new SessionException(SessionException.Error.NULL_OR_INVALID_PARAMETER, "token argument is null");
        }
        int error = connectNative(this.nativeCtx, this.apiUrl.getHost(), this.apiUrl.getPath(), -1 == this.apiUrl.getPort() ? this.apiUrl.getDefaultPort() : this.apiUrl.getPort(), this.apiUrl.getProtocol().equals("https"), token);
        if (error != 0) {
            throw new SessionException(error);
        }
    }

    public void disconnect() throws SessionException {
        log.d("disconnect(...) called", new Object[0]);
        int error = disconnectNative(this.nativeCtx);
        if (error != 0) {
            throw new SessionException(error);
        }
    }

    public Connection getConnection() {
        log.d("getConnection(...) called", new Object[0]);
        return new Connection(getConnectionNative(this.nativeCtx));
    }

    public void sendSignal(String type, String signal) throws SessionException {
        log.d("sendSignal(...) called", new Object[0]);
        int error = sendSignalNative(this.nativeCtx, type, signal, true);
        if (error != 0) {
            throw new SessionException(error);
        }
    }

    public void sendSignal(String type, String signal, boolean retryAfterReconnect) throws SessionException {
        log.d("sendSignal(...) called", new Object[0]);
        int error = sendSignalNative(this.nativeCtx, type, signal, retryAfterReconnect);
        if (error != 0) {
            throw new SessionException(error);
        }
    }

    public void sendSignal(String type, String signal, Connection connection) throws SessionException {
        if (connection == null) {
            throw new SessionException(SessionException.Error.NULL_OR_INVALID_PARAMETER, "connection argument is null");
        }
        log.d("sendSignal(...) called", new Object[0]);
        int error = sendSignalToCxnNative(this.nativeCtx, type, signal, connection.getNativeHndl(), true);
        if (error != 0) {
            throw new SessionException(error);
        }
    }

    public void sendSignal(String type, String signal, Connection connection, boolean retryAfterReconnect) throws SessionException {
        if (connection == null) {
            throw new SessionException(SessionException.Error.NULL_OR_INVALID_PARAMETER, "connection argument is null");
        }
        log.d("sendSignal(...) called", new Object[0]);
        int error = sendSignalToCxnNative(this.nativeCtx, type, signal, connection.getNativeHndl(), retryAfterReconnect);
        if (error != 0) {
            throw new SessionException(error);
        }
    }

    public void publish(Publisher publisher) throws SessionException {
        log.d("publish(...) called", new Object[0]);
        if (publisher == null) {
            throw new SessionException(SessionException.Error.NULL_OR_INVALID_PARAMETER, "publisher argument is null");
        }
        int error = publishNative(this.nativeCtx, publisher.getNativeHndl());
        if (error != 0) {
            throw new SessionException(error);
        }
        this.publisherLst.add(publisher);
    }

    public void unpublish(Publisher publisher) throws SessionException {
        log.d("unpublish(...) called", new Object[0]);
        if (publisher == null) {
            throw new SessionException(SessionException.Error.NULL_OR_INVALID_PARAMETER, "publisher argument is null");
        }
        int error = unpublishNative(this.nativeCtx, publisher.getNativeHndl());
        if (error != 0) {
            throw new SessionException(error);
        }
        this.publisherLst.remove(publisher);
    }

    public void subscribe(Subscriber subscriber) throws SessionException {
        log.d("subscribe(...) called", new Object[0]);
        if (subscriber == null) {
            throw new SessionException(SessionException.Error.NULL_OR_INVALID_PARAMETER, "subscriber argument is null");
        }
        int error = subscribeNative(this.nativeCtx, subscriber.getNativeHndl());
        if (error != 0) {
            throw new SessionException(error);
        }
        this.subscriberLst.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) throws SessionException {
        log.d("unsubscribe(...) called", new Object[0]);
        if (subscriber == null) {
            throw new SessionException(SessionException.Error.NULL_OR_INVALID_PARAMETER, "subscriber argument is null");
        }
        int error = unsubscribeNative(this.nativeCtx, subscriber.getNativeHndl());
        if (error != 0) {
            throw new SessionException(error);
        }
        this.subscriberLst.remove(subscriber);
        subscriber.onDisconnected();
    }

    public Capabilities getCapabilities() {
        log.d("getCapabilities(...) called", new Object[0]);
        return getCapabilitiesNative(this.nativeCtx);
    }

    public String reportIssue(String issueDescription) throws SessionException {
        log.d("reportIssue(...) called", new Object[0]);
        return reportIssueNative(this.nativeCtx, issueDescription);
    }

    public void pause() throws Subscriber.SubscriberException, Publisher.PublisherException {
        log.d("pause(...) called", new Object[0]);
        Iterator<Subscriber> it = this.subscriberLst.iterator();
        while (it.hasNext()) {
            it.next().pause();
        }
        Iterator<Publisher> it2 = this.publisherLst.iterator();
        while (it2.hasNext()) {
            it2.next().pause();
        }
        if (AudioDriver.getInstance() != null) {
            AudioDriver.getInstance().pause();
        }
    }

    public void resume() throws Subscriber.SubscriberException, Publisher.PublisherException {
        log.d("resume(...) called", new Object[0]);
        Iterator<Subscriber> it = this.subscriberLst.iterator();
        while (it.hasNext()) {
            it.next().resume();
        }
        Iterator<Publisher> it2 = this.publisherLst.iterator();
        while (it2.hasNext()) {
            it2.next().resume();
        }
        if (AudioDriver.getInstance() != null) {
            AudioDriver.getInstance().resume();
        }
    }

    public ArrayList<String> getVideoEncoderSupportedCodecs() {
        return new ArrayList<>(Arrays.asList(getSupportedVideoCodecsNative(this.nativeCtx)));
    }

    public ArrayList<String> getVideoDecoderSupportedCodecs() {
        return new ArrayList<>(Arrays.asList(getSupportedVideoCodecsNative(this.nativeCtx)));
    }

    Session(Context context, String apiKey, String sessionId, boolean connectionEventsSuppressed, SessionListener sessionListener, ConnectionListener connectionListener, StreamPropertiesListener streamListener, SignalListener signalListener, ReconnectionListener reconnectionListener, ArchiveListener archiveListener, URL url, Builder.TransportPolicy routing, Builder.IncludeServers turnConfig, Builder.IceServer[] iceServers, boolean ipWhitelist) throws SessionException {
        log.d("Session(...) called", new Object[0]);
        if (context == null || apiKey == null || sessionId == null) {
            throw new SessionException(SessionException.Error.NULL_OR_INVALID_PARAMETER, "context or apiKey or session Id argument(s) is null");
        }
        this.sessionCb = sessionListener;
        this.connectionCb = connectionListener;
        this.streamCb = streamListener;
        this.signalCb = signalListener;
        this.reconnectionCb = reconnectionListener;
        this.archiveCb = archiveListener;
        this.mainThreadHandler = new Handler(context.getMainLooper());
        this.publisherLst = new ArrayList<>();
        this.subscriberLst = new ArrayList<>();
        this.apiUrl = url;
        this.nativeCtx = initNative(context, apiKey, sessionId, connectionEventsSuppressed, routing.ordinal(), turnConfig.ordinal(), iceServers, ipWhitelist);
        if (0 == this.nativeCtx) {
            throw new SessionException(SessionException.Error.INTERNAL_ERROR);
        }
    }

    /* access modifiers changed from: package-private */
    public void logAdHocAction(String action) throws SessionException {
        if (0 != this.nativeCtx) {
            logAdHocActionNative(this.nativeCtx, action);
        }
    }

    /* access modifiers changed from: package-private */
    public void logCustomClientEvent(String eventName, String eventData) throws SessionException {
        if (0 != this.nativeCtx) {
            logCustomClientEventNative(this.nativeCtx, eventName, eventData);
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
        if (this.sessionCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.sessionCb.onConnected(Session.this);
                }
            });
        }
    }

    private void onDisconnected() {
        log.d("onDisconnected(...) called", new Object[0]);
        if (this.sessionCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.sessionCb.onDisconnected(Session.this);
                }
            });
        }
    }

    private void onStreamReceived(final Stream stream) {
        log.d("onStreamReceived(...) called", new Object[0]);
        if (this.sessionCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.sessionCb.onStreamReceived(Session.this, stream);
                }
            });
        }
    }

    private void onStreamDropped(final Stream stream) {
        log.d("onStreamDropped(...) called", new Object[0]);
        if (this.sessionCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.sessionCb.onStreamDropped(Session.this, stream);
                }
            });
        }
    }

    private void onError(final String errorMsg, final int errorCode) {
        log.d("onError(...) called", new Object[0]);
        if (this.sessionCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.sessionCb.onError(Session.this, new SessionException(errorCode, errorMsg));
                }
            });
        }
    }

    private void onConnectionCreated(final Connection connection) {
        log.d("onConnectionCreated(...) called", new Object[0]);
        if (this.connectionCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.connectionCb.onConnectionCreated(Session.this, connection);
                }
            });
        }
    }

    private void onConnectionDropped(final Connection connection) {
        log.d("onConnectionDropped(...) called", new Object[0]);
        this.mainThreadHandler.post(new Runnable() {
            public void run() {
                if (Session.this.connectionCb != null) {
                    Session.this.connectionCb.onConnectionDestroyed(Session.this, connection);
                }
            }
        });
    }

    private void onStreamAudioStateChange(final Stream stream, final boolean hasAudio) {
        log.d("onStreamAudioStateChange(...) called", new Object[0]);
        if (this.streamCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.streamCb.onStreamHasAudioChanged(Session.this, stream, hasAudio);
                }
            });
        }
    }

    private void onStreamVideoStateChange(final Stream stream, final boolean hasVideo) {
        log.d("onStreamVideoStateChange(...) called", new Object[0]);
        if (this.streamCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.streamCb.onStreamHasVideoChanged(Session.this, stream, hasVideo);
                }
            });
        }
    }

    private void onStreamVideoSizeChange(final Stream stream, final int width, final int height) {
        log.d("onStreamVideoSizeChange(...) called", new Object[0]);
        if (this.streamCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.streamCb.onStreamVideoDimensionsChanged(Session.this, stream, width, height);
                }
            });
        }
    }

    private void onStreamVideoTypeChanged(final Stream stream, final int videoType) {
        log.d("onStreamVideoTypeChanged(...) called", new Object[0]);
        if (this.streamCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.streamCb.onStreamVideoTypeChanged(Session.this, stream, Stream.videoTypeTbl.get(videoType));
                }
            });
        }
    }

    private void onSignalReceived(final String type, final String data, final Connection connection) {
        log.d("onSignalReceived(...) called", new Object[0]);
        if (this.signalCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.signalCb.onSignalReceived(Session.this, type, data, connection);
                }
            });
        }
    }

    private void onReconnecting() {
        log.d("onReconnecting(...) called", new Object[0]);
        if (this.reconnectionCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.reconnectionCb.onReconnecting(Session.this);
                }
            });
        }
    }

    private void onReconnected() {
        log.d("onReconnected(...) called", new Object[0]);
        if (this.reconnectionCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.reconnectionCb.onReconnected(Session.this);
                }
            });
        }
    }

    private void onArchiveStarted(final String id, final String name) {
        log.d("onArchiveStarted(...) called", new Object[0]);
        if (this.archiveCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.archiveCb.onArchiveStarted(Session.this, id, name);
                }
            });
        }
    }

    private void onArchiveStopped(final String id) {
        log.d("onArchiveStopped(...) called", new Object[0]);
        if (this.archiveCb != null) {
            this.mainThreadHandler.post(new Runnable() {
                public void run() {
                    Session.this.archiveCb.onArchiveStopped(Session.this, id);
                }
            });
        }
    }

    private void onSubscriberClosed(final Subscriber subscriber) {
        this.mainThreadHandler.post(new Runnable() {
            public void run() {
                subscriber.onDisconnected();
                Session.this.subscriberLst.remove(subscriber);
            }
        });
    }

    private void onPublisherClosed(final Publisher publisher) {
        this.mainThreadHandler.post(new Runnable() {
            public void run() {
                Session.this.publisherLst.remove(publisher);
            }
        });
    }

    static {
        Loader.load();
        registerNatives();
    }
}
