package acidhax.cordova.chromecast;

import android.os.Bundle;
import android.support.v7.media.MediaRouter;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.images.WebImage;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChromecastSession extends Cast.Listener implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, RemoteMediaPlayer.OnMetadataUpdatedListener, RemoteMediaPlayer.OnStatusUpdatedListener, Cast.MessageReceivedCallback {
    private volatile String appId;
    /* access modifiers changed from: private */
    public volatile List<WebImage> appImages;
    private ChromecastMediaController chromecastMediaController;
    private ResultCallback<RemoteMediaPlayer.MediaChannelResult> connectRemoteMediaPlayerCallback = new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {
        public void onResult(RemoteMediaPlayer.MediaChannelResult result) {
            if (result.getStatus().isSuccess()) {
                ChromecastSession.this.onMediaUpdatedListener.onMediaUpdated(true, ChromecastSession.this.createMediaObject());
            } else {
                System.out.println("Failed to request status.");
            }
        }
    };

    /* renamed from: cordova  reason: collision with root package name */
    private CordovaInterface f3cordova = null;
    private CastDevice device = null;
    /* access modifiers changed from: private */
    public volatile String displayName;
    /* access modifiers changed from: private */
    public boolean isConnected = false;
    private ResultCallback<Cast.ApplicationConnectionResult> joinApplicationResultCallback = new ResultCallback<Cast.ApplicationConnectionResult>() {
        public void onResult(Cast.ApplicationConnectionResult result) {
            Status status = result.getStatus();
            if (status.isSuccess()) {
                try {
                    ApplicationMetadata metadata = result.getApplicationMetadata();
                    String unused = ChromecastSession.this.sessionId = result.getSessionId();
                    String unused2 = ChromecastSession.this.displayName = metadata.getName();
                    List unused3 = ChromecastSession.this.appImages = metadata.getImages();
                    ChromecastSession.this.joinSessionCallback.onSuccess(ChromecastSession.this);
                    ChromecastSession.this.connectRemoteMediaPlayer();
                    boolean unused4 = ChromecastSession.this.isConnected = true;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } else {
                ChromecastSession.this.joinSessionCallback.onError(status.toString());
                boolean unused5 = ChromecastSession.this.isConnected = false;
            }
        }
    };
    private boolean joinInsteadOfConnecting = false;
    /* access modifiers changed from: private */
    public ChromecastSessionCallback joinSessionCallback;
    private volatile String lastSessionId = null;
    private ResultCallback<Cast.ApplicationConnectionResult> launchApplicationResultCallback = new ResultCallback<Cast.ApplicationConnectionResult>() {
        public void onResult(Cast.ApplicationConnectionResult result) {
            ApplicationMetadata metadata = result.getApplicationMetadata();
            String unused = ChromecastSession.this.sessionId = result.getSessionId();
            String unused2 = ChromecastSession.this.displayName = metadata.getName();
            List unused3 = ChromecastSession.this.appImages = metadata.getImages();
            if (result.getStatus().isSuccess()) {
                try {
                    ChromecastSession.this.launchCallback.onSuccess(ChromecastSession.this);
                    ChromecastSession.this.connectRemoteMediaPlayer();
                    boolean unused4 = ChromecastSession.this.isConnected = true;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } else {
                boolean unused5 = ChromecastSession.this.isConnected = false;
            }
        }
    };
    /* access modifiers changed from: private */
    public ChromecastSessionCallback launchCallback;
    private volatile GoogleApiClient mApiClient = null;
    private volatile RemoteMediaPlayer mRemoteMediaPlayer;
    private HashSet<String> messageNamespaces = new HashSet<>();
    /* access modifiers changed from: private */
    public ChromecastOnMediaUpdatedListener onMediaUpdatedListener;
    private ChromecastOnSessionUpdatedListener onSessionUpdatedListener;
    private MediaRouter.RouteInfo routeInfo = null;
    /* access modifiers changed from: private */
    public volatile String sessionId = null;

    public ChromecastSession(MediaRouter.RouteInfo routeInfo2, CordovaInterface cordovaInterface, ChromecastOnMediaUpdatedListener onMediaUpdatedListener2, ChromecastOnSessionUpdatedListener onSessionUpdatedListener2) {
        this.f3cordova = cordovaInterface;
        this.onMediaUpdatedListener = onMediaUpdatedListener2;
        this.onSessionUpdatedListener = onSessionUpdatedListener2;
        this.routeInfo = routeInfo2;
        this.device = CastDevice.getFromBundle(this.routeInfo.getExtras());
        this.mRemoteMediaPlayer = new RemoteMediaPlayer();
        this.mRemoteMediaPlayer.setOnMetadataUpdatedListener(this);
        this.mRemoteMediaPlayer.setOnStatusUpdatedListener(this);
        this.chromecastMediaController = new ChromecastMediaController(this.mRemoteMediaPlayer);
    }

    public void launch(String appId2, ChromecastSessionCallback launchCallback2) {
        this.appId = appId2;
        this.launchCallback = launchCallback2;
        connectToDevice();
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public void addMessageListener(String namespace) {
        if (!this.messageNamespaces.contains(namespace)) {
            try {
                Cast.CastApi.setMessageReceivedCallbacks(this.mApiClient, namespace, this);
                this.messageNamespaces.add(namespace);
            } catch (Exception e) {
            }
        }
    }

    public void sendMessage(String namespace, String message, final ChromecastSessionCallback callback) {
        try {
            Cast.CastApi.sendMessage(this.mApiClient, namespace, message).setResultCallback(new ResultCallback<Status>() {
                public void onResult(Status result) {
                    if (!result.isSuccess()) {
                        callback.onSuccess();
                    } else {
                        callback.onError(result.toString());
                    }
                }
            });
        } catch (Exception e) {
            callback.onError(e.getMessage());
        }
    }

    public void join(String appId2, String sessionId2, ChromecastSessionCallback joinSessionCallback2) {
        this.appId = appId2;
        this.joinSessionCallback = joinSessionCallback2;
        this.joinInsteadOfConnecting = true;
        this.lastSessionId = sessionId2;
        connectToDevice();
    }

    public void kill(ChromecastSessionCallback callback) {
        try {
            Cast.CastApi.stopApplication(this.mApiClient);
            this.mApiClient.disconnect();
        } catch (Exception e) {
        }
        callback.onSuccess();
    }

    public void leave(ChromecastSessionCallback callback) {
        try {
            Cast.CastApi.leaveApplication(this.mApiClient);
        } catch (Exception e) {
        }
        callback.onSuccess();
    }

    public boolean loadMedia(String contentId, String contentType, long duration, String streamType, boolean autoPlay, double currentTime, JSONObject metadata, ChromecastSessionCallback callback) {
        try {
            final ChromecastSessionCallback chromecastSessionCallback = callback;
            this.mRemoteMediaPlayer.load(this.mApiClient, this.chromecastMediaController.createLoadUrlRequest(contentId, contentType, duration, streamType, metadata), autoPlay, (long) (1000.0d * currentTime)).setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {
                public void onResult(RemoteMediaPlayer.MediaChannelResult result) {
                    if (result.getStatus().isSuccess()) {
                        System.out.println("Media loaded successfully");
                        ChromecastSession.this.onMediaUpdatedListener.onMediaLoaded(ChromecastSession.this.createMediaObject());
                        chromecastSessionCallback.onSuccess(ChromecastSession.this.createMediaObject());
                        return;
                    }
                    chromecastSessionCallback.onError("session_error");
                }
            });
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("Problem occurred with media during loading");
            callback.onError("session_error");
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            callback.onError("session_error");
            System.out.println("Problem opening media during loading");
            return false;
        }
    }

    public void mediaPlay(ChromecastSessionCallback callback) {
        this.chromecastMediaController.play(this.mApiClient, callback);
    }

    public void mediaPause(ChromecastSessionCallback callback) {
        this.chromecastMediaController.pause(this.mApiClient, callback);
    }

    public void mediaSeek(long seekPosition, String resumeState, ChromecastSessionCallback callback) {
        this.chromecastMediaController.seek(seekPosition, resumeState, this.mApiClient, callback);
    }

    public void mediaSetVolume(double level, ChromecastSessionCallback callback) {
        this.chromecastMediaController.setVolume(level, this.mApiClient, callback);
    }

    public void mediaSetMuted(boolean muted, ChromecastSessionCallback callback) {
        this.chromecastMediaController.setMuted(muted, this.mApiClient, callback);
    }

    public void mediaStop(ChromecastSessionCallback callback) {
        this.chromecastMediaController.stop(this.mApiClient, callback);
    }

    public void setVolume(double volume, ChromecastSessionCallback callback) {
        try {
            Cast.CastApi.setVolume(this.mApiClient, volume);
            callback.onSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
        }
    }

    public void setMute(boolean muted, ChromecastSessionCallback callback) {
        try {
            Cast.CastApi.setMute(this.mApiClient, muted);
            callback.onSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
        }
    }

    private void connectToDevice() {
        try {
            this.mApiClient = new GoogleApiClient.Builder(this.f3cordova.getActivity().getApplicationContext()).addApi(Cast.API, Cast.CastOptions.builder(this.device, this).build()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
            this.mApiClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void launchApplication() {
        Cast.CastApi.launchApplication(this.mApiClient, this.appId, false).setResultCallback(this.launchApplicationResultCallback);
    }

    private void joinApplication() {
        Cast.CastApi.joinApplication(this.mApiClient, this.appId, this.lastSessionId).setResultCallback(this.joinApplicationResultCallback);
    }

    /* access modifiers changed from: private */
    public void connectRemoteMediaPlayer() throws IllegalStateException, IOException {
        Cast.CastApi.setMessageReceivedCallbacks(this.mApiClient, this.mRemoteMediaPlayer.getNamespace(), this.mRemoteMediaPlayer);
        this.mRemoteMediaPlayer.requestStatus(this.mApiClient).setResultCallback(this.connectRemoteMediaPlayerCallback);
    }

    public JSONObject createSessionObject() {
        JSONObject out = new JSONObject();
        try {
            out.put("appId", this.appId);
            out.put("media", createMediaObject());
            if (this.appImages != null) {
                JSONArray appImages2 = new JSONArray();
                for (WebImage o : this.appImages) {
                    appImages2.put(o.toString());
                }
            }
            out.put("appImages", this.appImages);
            out.put("sessionId", this.sessionId);
            out.put("displayName", this.displayName);
            JSONObject receiver = new JSONObject();
            receiver.put("friendlyName", this.device.getFriendlyName());
            receiver.put("label", this.device.getDeviceId());
            JSONObject volume = new JSONObject();
            try {
                volume.put(FirebaseAnalytics.Param.LEVEL, Cast.CastApi.getVolume(this.mApiClient));
                volume.put("muted", Cast.CastApi.isMute(this.mApiClient));
            } catch (Exception e) {
            }
            receiver.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, volume);
            out.put("receiver", receiver);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return out;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject createMediaObject() {
        /*
            r10 = this;
            r8 = 4652007308841189376(0x408f400000000000, double:1000.0)
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            com.google.android.gms.cast.RemoteMediaPlayer r5 = r10.mRemoteMediaPlayer
            com.google.android.gms.cast.MediaStatus r1 = r5.getMediaStatus()
            if (r1 != 0) goto L_0x0018
        L_0x0017:
            return r3
        L_0x0018:
            com.google.android.gms.cast.MediaInfo r0 = r1.getMediaInfo()
            java.lang.String r5 = "media"
            r3.put(r5, r2)     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "mediaSessionId"
            r6 = 1
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "sessionId"
            java.lang.String r6 = r10.sessionId     // Catch:{ JSONException -> 0x009a }
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "currentTime"
            long r6 = r1.getStreamPosition()     // Catch:{ JSONException -> 0x009a }
            double r6 = (double) r6     // Catch:{ JSONException -> 0x009a }
            double r6 = r6 / r8
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "playbackRate"
            double r6 = r1.getPlaybackRate()     // Catch:{ JSONException -> 0x009a }
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "customData"
            org.json.JSONObject r6 = r1.getCustomData()     // Catch:{ JSONException -> 0x009a }
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            int r5 = r1.getPlayerState()     // Catch:{ JSONException -> 0x009a }
            switch(r5) {
                case 0: goto L_0x00b5;
                case 1: goto L_0x009d;
                case 2: goto L_0x00ad;
                case 3: goto L_0x00a5;
                case 4: goto L_0x0092;
                default: goto L_0x0052;
            }     // Catch:{ JSONException -> 0x009a }
        L_0x0052:
            int r5 = r1.getIdleReason()     // Catch:{ JSONException -> 0x009a }
            switch(r5) {
                case 0: goto L_0x00de;
                case 1: goto L_0x00cd;
                case 2: goto L_0x00bd;
                case 3: goto L_0x00d5;
                case 4: goto L_0x00c5;
                default: goto L_0x0059;
            }     // Catch:{ JSONException -> 0x009a }
        L_0x0059:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x009a }
            r4.<init>()     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "level"
            double r6 = r1.getStreamVolume()     // Catch:{ JSONException -> 0x009a }
            r4.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "muted"
            boolean r6 = r1.isMute()     // Catch:{ JSONException -> 0x009a }
            r4.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "volume"
            r3.put(r5, r4)     // Catch:{ JSONException -> 0x009a }
            java.lang.String r5 = "duration"
            long r6 = r0.getStreamDuration()     // Catch:{ Exception -> 0x0090 }
            double r6 = (double) r6     // Catch:{ Exception -> 0x0090 }
            double r6 = r6 / r8
            r2.put(r5, r6)     // Catch:{ Exception -> 0x0090 }
            int r5 = r0.getStreamType()     // Catch:{ Exception -> 0x0090 }
            switch(r5) {
                case 0: goto L_0x0088;
                case 1: goto L_0x00e7;
                case 2: goto L_0x00f0;
                default: goto L_0x0087;
            }     // Catch:{ Exception -> 0x0090 }
        L_0x0087:
            goto L_0x0017
        L_0x0088:
            java.lang.String r5 = "streamType"
            java.lang.String r6 = "other"
            r2.put(r5, r6)     // Catch:{ Exception -> 0x0090 }
            goto L_0x0017
        L_0x0090:
            r5 = move-exception
            goto L_0x0017
        L_0x0092:
            java.lang.String r5 = "playerState"
            java.lang.String r6 = "BUFFERING"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0052
        L_0x009a:
            r5 = move-exception
            goto L_0x0017
        L_0x009d:
            java.lang.String r5 = "playerState"
            java.lang.String r6 = "IDLE"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0052
        L_0x00a5:
            java.lang.String r5 = "playerState"
            java.lang.String r6 = "PAUSED"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0052
        L_0x00ad:
            java.lang.String r5 = "playerState"
            java.lang.String r6 = "PLAYING"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0052
        L_0x00b5:
            java.lang.String r5 = "playerState"
            java.lang.String r6 = "UNKNOWN"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0052
        L_0x00bd:
            java.lang.String r5 = "idleReason"
            java.lang.String r6 = "canceled"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0059
        L_0x00c5:
            java.lang.String r5 = "idleReason"
            java.lang.String r6 = "error"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0059
        L_0x00cd:
            java.lang.String r5 = "idleReason"
            java.lang.String r6 = "finished"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0059
        L_0x00d5:
            java.lang.String r5 = "idleReason"
            java.lang.String r6 = "iterrupted"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0059
        L_0x00de:
            java.lang.String r5 = "idleReason"
            java.lang.String r6 = "none"
            r3.put(r5, r6)     // Catch:{ JSONException -> 0x009a }
            goto L_0x0059
        L_0x00e7:
            java.lang.String r5 = "streamType"
            java.lang.String r6 = "buffered"
            r2.put(r5, r6)     // Catch:{ Exception -> 0x0090 }
            goto L_0x0017
        L_0x00f0:
            java.lang.String r5 = "streamType"
            java.lang.String r6 = "live"
            r2.put(r5, r6)     // Catch:{ Exception -> 0x0090 }
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: acidhax.cordova.chromecast.ChromecastSession.createMediaObject():org.json.JSONObject");
    }

    public void onConnected(Bundle connectionHint) {
        if (this.joinInsteadOfConnecting) {
            joinApplication();
        } else {
            launchApplication();
        }
    }

    public void onConnectionSuspended(int cause) {
        if (this.onSessionUpdatedListener != null) {
            this.isConnected = false;
            this.onSessionUpdatedListener.onSessionUpdated(false, createSessionObject());
        }
    }

    public void onConnectionFailed(ConnectionResult result) {
        if (this.launchCallback != null) {
            this.isConnected = false;
            this.launchCallback.onError("channel_error");
        }
    }

    public void onApplicationStatusChanged() {
        if (this.onSessionUpdatedListener != null) {
            this.isConnected = true;
            this.onSessionUpdatedListener.onSessionUpdated(true, createSessionObject());
        }
    }

    public void onVolumeChanged() {
        if (this.onSessionUpdatedListener != null) {
            this.onSessionUpdatedListener.onSessionUpdated(true, createSessionObject());
        }
    }

    public void onApplicationDisconnected(int errorCode) {
        if (this.onSessionUpdatedListener != null) {
            this.isConnected = false;
            this.onSessionUpdatedListener.onSessionUpdated(false, createSessionObject());
        }
    }

    public void onMetadataUpdated() {
        if (this.onMediaUpdatedListener != null) {
            this.onMediaUpdatedListener.onMediaUpdated(true, createMediaObject());
        }
    }

    public void onStatusUpdated() {
        if (this.onMediaUpdatedListener != null) {
            this.onMediaUpdatedListener.onMediaUpdated(true, createMediaObject());
        }
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void onMessageReceived(CastDevice castDevice, String namespace, String message) {
        if (this.onSessionUpdatedListener != null) {
            this.onSessionUpdatedListener.onMessage(this, namespace, message);
        }
    }
}
