package com.tokbox.cordova;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.adobe.phonegap.push.PushConstants;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.Connection;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenTokAndroidPlugin extends CordovaPlugin implements Session.SessionListener, Session.ConnectionListener, Session.ReconnectionListener, Session.ArchiveListener, Session.SignalListener, PublisherKit.PublisherListener, Session.StreamPropertiesListener {
    public static final String TAG = "OTPlugin";
    static CordovaInterface _cordova;
    static CordovaWebView _webView;
    public static final String[] perms = {"android.permission.INTERNET", "android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    static JSONObject viewList = new JSONObject();
    /* access modifiers changed from: private */
    public String apiKey;
    public HashMap<String, Connection> connectionCollection;
    protected Session mSession;
    public HashMap<String, CallbackContext> myEventListeners;
    public RunnablePublisher myPublisher;
    public CallbackContext permissionsCallback;
    public boolean publishCalled;
    public boolean sessionConnected;
    /* access modifiers changed from: private */
    public String sessionId;
    public HashMap<String, Stream> streamCollection;
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> streamHasAudio;
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> streamHasVideo;
    /* access modifiers changed from: private */
    public HashMap<String, JSONObject> streamVideoDimensions;
    public HashMap<String, RunnableSubscriber> subscriberCollection;

    public class RunnableUpdateViews implements Runnable {
        public ArrayList<RunnableUpdateViews> allStreamViews;
        public JSONArray mProperty;
        public View mView;

        public RunnableUpdateViews() {
        }

        public class CustomComparator implements Comparator<RunnableUpdateViews> {
            public CustomComparator() {
            }

            public int compare(RunnableUpdateViews object1, RunnableUpdateViews object2) {
                return object1.getZIndex() - object2.getZIndex();
            }
        }

        public void updateZIndices() {
            this.allStreamViews = new ArrayList<>();
            for (Map.Entry<String, RunnableSubscriber> entry : OpenTokAndroidPlugin.this.subscriberCollection.entrySet()) {
                this.allStreamViews.add(entry.getValue());
            }
            if (OpenTokAndroidPlugin.this.myPublisher != null) {
                this.allStreamViews.add(OpenTokAndroidPlugin.this.myPublisher);
            }
            Collections.sort(this.allStreamViews, new CustomComparator());
            Iterator<RunnableUpdateViews> it = this.allStreamViews.iterator();
            while (it.hasNext()) {
                RunnableUpdateViews viewContainer = it.next();
                if (Build.VERSION.SDK_INT >= 21) {
                    viewContainer.mView.setTranslationZ((float) viewContainer.getZIndex());
                }
                if (viewContainer.getZIndex() == 0) {
                    viewContainer.mView.bringToFront();
                }
            }
        }

        public int getZIndex() {
            try {
                return this.mProperty.getInt(5);
            } catch (Exception e) {
                return 0;
            }
        }

        @SuppressLint({"NewApi"})
        public void run() {
            int ratioIndex;
            try {
                Log.i(OpenTokAndroidPlugin.TAG, "updating view in ui runnable" + this.mProperty.toString());
                Log.i(OpenTokAndroidPlugin.TAG, "updating view in ui runnable " + this.mView.toString());
                if (this.mProperty.get(6) instanceof Number) {
                    ratioIndex = 6;
                } else if (this.mProperty.get(8) instanceof Number) {
                    ratioIndex = 8;
                } else {
                    ratioIndex = 9;
                }
                DisplayMetrics metrics = new DisplayMetrics();
                OpenTokAndroidPlugin.this.f5cordova.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                float widthRatio = ((float) this.mProperty.getDouble(ratioIndex)) * metrics.density;
                float heightRatio = ((float) this.mProperty.getDouble(ratioIndex + 1)) * metrics.density;
                this.mView.setY(((float) this.mProperty.getInt(1)) * heightRatio);
                this.mView.setX(((float) this.mProperty.getInt(2)) * widthRatio);
                ViewGroup.LayoutParams params = this.mView.getLayoutParams();
                params.height = (int) (((float) this.mProperty.getInt(4)) * heightRatio);
                params.width = (int) (((float) this.mProperty.getInt(3)) * widthRatio);
                this.mView.setLayoutParams(params);
                updateZIndices();
            } catch (Exception e) {
                Log.i(OpenTokAndroidPlugin.TAG, "error when trying to retrieve properties while resizing properties");
            }
        }
    }

    public class RunnablePublisher extends RunnableUpdateViews implements PublisherKit.PublisherListener, Publisher.CameraListener, PublisherKit.AudioLevelListener {
        public Publisher mPublisher;

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x011f, code lost:
            if (r19.compareStrings(r18.mProperty.getString(16), "352x288") != false) goto L_0x0121;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public RunnablePublisher(org.json.JSONArray r20) {
            /*
                r18 = this;
                r0 = r19
                r1 = r18
                com.tokbox.cordova.OpenTokAndroidPlugin.this = r0
                r18.<init>()
                r0 = r20
                r1 = r18
                r1.mProperty = r0
                r0 = r19
                org.apache.cordova.CordovaInterface r15 = r0.f5cordova
                android.app.Activity r15 = r15.getActivity()
                android.content.Context r15 = r15.getApplicationContext()
                java.lang.String r16 = "permissions"
                r17 = 0
                android.content.SharedPreferences r9 = r15.getSharedPreferences(r16, r17)
                android.content.SharedPreferences$Editor r7 = r9.edit()
                r7.clear()
                java.lang.String r15 = "opentok.publisher.accepted"
                r16 = 1
                r0 = r16
                r7.putBoolean(r15, r0)
                r7.commit()
                r3 = 1
                r14 = 1
                r4 = 1
                r10 = 1
                r11 = 1
                r2 = 40000(0x9c40, float:5.6052E-41)
                java.lang.String r12 = "Android-Cordova-Publisher"
                java.lang.String r8 = "FPS_15"
                java.lang.String r13 = "MEDIUM"
                java.lang.String r5 = "front"
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 0
                java.lang.String r12 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 12
                int r2 = r15.getInt(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c7 }
                r15.<init>()     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "FPS_"
                java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x01c7 }
                r0 = r18
                org.json.JSONArray r0 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = r0
                r17 = 15
                java.lang.String r16 = r16.getString(r17)     // Catch:{ Exception -> 0x01c7 }
                java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r8 = r15.toString()     // Catch:{ Exception -> 0x01c7 }
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 14
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "true"
                boolean r14 = r15.equals(r16)     // Catch:{ Exception -> 0x01c7 }
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 13
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "true"
                boolean r4 = r15.equals(r16)     // Catch:{ Exception -> 0x01c7 }
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 11
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "true"
                boolean r3 = r15.equals(r16)     // Catch:{ Exception -> 0x01c7 }
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 7
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "true"
                boolean r11 = r15.equals(r16)     // Catch:{ Exception -> 0x01c7 }
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 6
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "true"
                boolean r10 = r15.equals(r16)     // Catch:{ Exception -> 0x01c7 }
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 8
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "back"
                boolean r15 = r15.equals(r16)     // Catch:{ Exception -> 0x01c7 }
                if (r15 == 0) goto L_0x00dd
                java.lang.String r5 = "back"
            L_0x00dd:
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 16
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "1280x720"
                r0 = r19
                r1 = r16
                boolean r15 = r0.compareStrings(r15, r1)     // Catch:{ Exception -> 0x01c7 }
                if (r15 == 0) goto L_0x00f5
                java.lang.String r13 = "HIGH"
            L_0x00f5:
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 16
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "320x240"
                r0 = r19
                r1 = r16
                boolean r15 = r0.compareStrings(r15, r1)     // Catch:{ Exception -> 0x01c7 }
                if (r15 != 0) goto L_0x0121
                r0 = r18
                org.json.JSONArray r15 = r0.mProperty     // Catch:{ Exception -> 0x01c7 }
                r16 = 16
                java.lang.String r15 = r15.getString(r16)     // Catch:{ Exception -> 0x01c7 }
                java.lang.String r16 = "352x288"
                r0 = r19
                r1 = r16
                boolean r15 = r0.compareStrings(r15, r1)     // Catch:{ Exception -> 0x01c7 }
                if (r15 == 0) goto L_0x0123
            L_0x0121:
                java.lang.String r13 = "LOW"
            L_0x0123:
                java.lang.String r15 = "OTPlugin"
                java.lang.String r16 = "publisher properties sanitized"
                android.util.Log.i(r15, r16)     // Catch:{ Exception -> 0x01c7 }
            L_0x012a:
                com.opentok.android.Publisher$Builder r15 = new com.opentok.android.Publisher$Builder
                r0 = r19
                org.apache.cordova.CordovaInterface r0 = r0.f5cordova
                r16 = r0
                android.app.Activity r16 = r16.getActivity()
                android.content.Context r16 = r16.getApplicationContext()
                r15.<init>(r16)
                com.opentok.android.Publisher$Builder r15 = r15.videoTrack((boolean) r14)
                com.opentok.android.Publisher$Builder r15 = r15.audioTrack((boolean) r4)
                com.opentok.android.Publisher$Builder r15 = r15.name((java.lang.String) r12)
                com.opentok.android.Publisher$Builder r15 = r15.audioBitrate((int) r2)
                com.opentok.android.Publisher$CameraCaptureFrameRate r16 = com.opentok.android.Publisher.CameraCaptureFrameRate.valueOf(r8)
                com.opentok.android.Publisher$Builder r15 = r15.frameRate(r16)
                com.opentok.android.Publisher$CameraCaptureResolution r16 = com.opentok.android.Publisher.CameraCaptureResolution.valueOf(r13)
                com.opentok.android.Publisher$Builder r15 = r15.resolution(r16)
                com.tokbox.cordova.OpenTokCustomVideoRenderer r16 = new com.tokbox.cordova.OpenTokCustomVideoRenderer
                r0 = r19
                org.apache.cordova.CordovaInterface r0 = r0.f5cordova
                r17 = r0
                android.app.Activity r17 = r17.getActivity()
                android.content.Context r17 = r17.getApplicationContext()
                r16.<init>(r17)
                com.opentok.android.Publisher$Builder r15 = r15.renderer((com.opentok.android.BaseVideoRenderer) r16)
                com.opentok.android.Publisher r15 = r15.build()
                r0 = r18
                r0.mPublisher = r15
                r0 = r18
                com.opentok.android.Publisher r15 = r0.mPublisher
                r0 = r18
                r15.setCameraListener(r0)
                r0 = r18
                com.opentok.android.Publisher r15 = r0.mPublisher
                r0 = r18
                r15.setPublisherListener(r0)
                r0 = r18
                com.opentok.android.Publisher r15 = r0.mPublisher
                r0 = r18
                r15.setAudioLevelListener(r0)
                r0 = r18
                com.opentok.android.Publisher r15 = r0.mPublisher
                java.lang.String r16 = "STYLE_VIDEO_SCALE"
                java.lang.String r17 = "STYLE_VIDEO_FILL"
                r15.setStyle(r16, r17)
                r0 = r18
                com.opentok.android.Publisher r15 = r0.mPublisher
                r15.setAudioFallbackEnabled(r3)
                r0 = r18
                com.opentok.android.Publisher r15 = r0.mPublisher
                r15.setPublishVideo(r11)
                r0 = r18
                com.opentok.android.Publisher r15 = r0.mPublisher
                r15.setPublishAudio(r10)
                java.lang.String r15 = "back"
                boolean r15 = r5.equals(r15)
                if (r15 == 0) goto L_0x01c6
                r0 = r18
                com.opentok.android.Publisher r15 = r0.mPublisher
                r15.cycleCamera()
            L_0x01c6:
                return
            L_0x01c7:
                r6 = move-exception
                java.lang.String r15 = "OTPlugin"
                java.lang.String r16 = "Unable to set publisher properties"
                android.util.Log.i(r15, r16)
                goto L_0x012a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tokbox.cordova.OpenTokAndroidPlugin.RunnablePublisher.<init>(com.tokbox.cordova.OpenTokAndroidPlugin, org.json.JSONArray):void");
        }

        public void setPropertyFromArray(JSONArray args) {
            this.mProperty = args;
        }

        public void startPublishing() {
            OpenTokAndroidPlugin.this.mSession.publish(this.mPublisher);
            OpenTokAndroidPlugin.this.f5cordova.getActivity().runOnUiThread(this);
        }

        public void stopPublishing() {
            ((ViewGroup) OpenTokAndroidPlugin.this.webView.getView().getParent()).removeView(this.mView);
            if (this.mPublisher != null) {
                try {
                    OpenTokAndroidPlugin.this.mSession.unpublish(this.mPublisher);
                } catch (Exception e) {
                    Log.i(OpenTokAndroidPlugin.TAG, "Could not unpublish Publisher");
                }
            }
        }

        public void destroyPublisher() {
            ((ViewGroup) OpenTokAndroidPlugin.this.webView.getView().getParent()).removeView(this.mView);
            if (this.mPublisher != null) {
                this.mPublisher.destroy();
                this.mPublisher = null;
            }
        }

        public void getImgData(CallbackContext callbackContext) {
            ((OpenTokCustomVideoRenderer) this.mPublisher.getRenderer()).getSnapshot(callbackContext);
        }

        public void run() {
            if (this.mView == null) {
                this.mView = this.mPublisher.getView();
                ((ViewGroup) OpenTokAndroidPlugin.this.webView.getView().getParent()).addView(this.mView);
                if (Build.VERSION.SDK_INT >= 21) {
                    this.mView.setTranslationZ((float) getZIndex());
                }
            }
            super.run();
        }

        public void onError(PublisherKit arg0, OpentokError arg1) {
        }

        public void onStreamCreated(PublisherKit arg0, Stream arg1) {
            Log.i(OpenTokAndroidPlugin.TAG, "publisher stream received");
            OpenTokAndroidPlugin.this.streamCollection.put(arg1.getStreamId(), arg1);
            OpenTokAndroidPlugin.this.streamHasAudio.put(arg1.getStreamId(), Boolean.valueOf(arg1.hasAudio()));
            OpenTokAndroidPlugin.this.streamHasVideo.put(arg1.getStreamId(), Boolean.valueOf(arg1.hasVideo()));
            JSONObject videoDimensions = new JSONObject();
            try {
                videoDimensions.put("width", arg1.getVideoWidth());
                videoDimensions.put("height", arg1.getVideoHeight());
            } catch (JSONException e) {
            }
            OpenTokAndroidPlugin.this.streamVideoDimensions.put(arg1.getStreamId(), videoDimensions);
            OpenTokAndroidPlugin.this.triggerStreamEvent(arg1, "publisherEvents", "streamCreated");
        }

        public void onStreamDestroyed(PublisherKit arg0, Stream arg1) {
            OpenTokAndroidPlugin.this.streamCollection.remove(arg1.getStreamId());
            OpenTokAndroidPlugin.this.streamHasAudio.remove(arg1.getStreamId());
            OpenTokAndroidPlugin.this.streamHasVideo.remove(arg1.getStreamId());
            OpenTokAndroidPlugin.this.streamVideoDimensions.remove(arg1.getStreamId());
            OpenTokAndroidPlugin.this.triggerStreamEvent(arg1, "publisherEvents", "streamDestroyed");
        }

        public void onCameraChanged(Publisher arg0, int arg1) {
        }

        public void onCameraError(Publisher arg0, OpentokError arg1) {
        }

        public void onAudioLevelUpdated(PublisherKit publisher, float audioLevel) {
            JSONObject data = new JSONObject();
            try {
                data.put("audioLevel", (double) audioLevel);
                OpenTokAndroidPlugin.this.triggerJSEvent("publisherEvents", "audioLevelUpdated", data);
            } catch (JSONException e) {
            }
        }
    }

    public class RunnableSubscriber extends RunnableUpdateViews implements SubscriberKit.SubscriberListener, SubscriberKit.VideoListener, SubscriberKit.AudioLevelListener {
        public Stream mStream;
        public Subscriber mSubscriber;

        public RunnableSubscriber(JSONArray args, Stream stream) {
            super();
            this.mProperty = args;
            this.mStream = stream;
            OpenTokAndroidPlugin.this.logMessage("NEW SUBSCRIBER BEING CREATED");
            this.mSubscriber = new Subscriber.Builder(OpenTokAndroidPlugin.this.f5cordova.getActivity().getApplicationContext(), this.mStream).renderer((BaseVideoRenderer) new OpenTokCustomVideoRenderer(OpenTokAndroidPlugin.this.f5cordova.getActivity().getApplicationContext())).build();
            this.mSubscriber.setVideoListener(this);
            this.mSubscriber.setSubscriberListener(this);
            this.mSubscriber.setAudioLevelListener(this);
            this.mSubscriber.setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE, BaseVideoRenderer.STYLE_VIDEO_FILL);
            OpenTokAndroidPlugin.this.mSession.subscribe(this.mSubscriber);
            OpenTokAndroidPlugin.this.f5cordova.getActivity().runOnUiThread(this);
        }

        public void setPropertyFromArray(JSONArray args) {
            this.mProperty = args;
        }

        public void removeStreamView() {
            ((ViewGroup) OpenTokAndroidPlugin.this.webView.getView().getParent()).removeView(this.mView);
            if (this.mSubscriber != null) {
                try {
                    OpenTokAndroidPlugin.this.mSession.unsubscribe(this.mSubscriber);
                    this.mSubscriber.destroy();
                } catch (Exception e) {
                    Log.i(OpenTokAndroidPlugin.TAG, "Could not unsubscribe Subscriber");
                }
            }
        }

        public void run() {
            if (this.mView == null) {
                this.mView = this.mSubscriber.getView();
                ((ViewGroup) OpenTokAndroidPlugin.this.webView.getView().getParent()).addView(this.mView);
                if (Build.VERSION.SDK_INT >= 21) {
                    this.mView.setTranslationZ((float) getZIndex());
                }
                Log.i(OpenTokAndroidPlugin.TAG, "subscriber view is added to parent view!");
            }
            super.run();
        }

        public void getImgData(CallbackContext callbackContext) {
            ((OpenTokCustomVideoRenderer) this.mSubscriber.getRenderer()).getSnapshot(callbackContext);
        }

        public void onConnected(SubscriberKit arg0) {
            JSONObject eventData = new JSONObject();
            String streamId = arg0.getStream().getStreamId();
            try {
                eventData.put("streamId", streamId);
                OpenTokAndroidPlugin.this.triggerJSEvent("subscriberEvents", "connected", eventData);
                OpenTokAndroidPlugin.this.triggerJSEvent("sessionEvents", "subscribedToStream", eventData);
            } catch (JSONException e) {
                Log.e(OpenTokAndroidPlugin.TAG, "JSONException" + e.getMessage());
            }
            Log.i(OpenTokAndroidPlugin.TAG, "subscriber" + streamId + " is connected");
            run();
        }

        public void onDisconnected(SubscriberKit arg0) {
            JSONObject eventData = new JSONObject();
            String streamId = arg0.getStream().getStreamId();
            try {
                eventData.put("streamId", streamId);
                OpenTokAndroidPlugin.this.triggerJSEvent("subscriberEvents", "disconnected", eventData);
            } catch (JSONException e) {
                Log.e(OpenTokAndroidPlugin.TAG, "JSONException" + e.getMessage());
            }
            Log.i(OpenTokAndroidPlugin.TAG, "subscriber" + streamId + " is disconnected");
        }

        public void onError(SubscriberKit arg0, OpentokError arg1) {
            JSONObject eventData = new JSONObject();
            String streamId = arg0.getStream().getStreamId();
            try {
                eventData.put("errorCode", arg1.getErrorCode().getErrorCode());
                eventData.put("streamId", streamId);
                OpenTokAndroidPlugin.this.triggerJSEvent("sessionEvents", "subscribedToStream", eventData);
            } catch (JSONException e) {
                Log.e(OpenTokAndroidPlugin.TAG, "JSONException" + e.getMessage());
            }
            Log.e(OpenTokAndroidPlugin.TAG, "subscriber exception: " + arg1.getMessage() + ", stream id: " + arg0.getStream().getStreamId());
        }

        public void onVideoDataReceived(SubscriberKit arg0) {
            OpenTokAndroidPlugin.this.triggerJSEvent("subscriberEvents", "videoDataReceived", (Object) null);
        }

        public void onVideoDisabled(SubscriberKit arg0, String reason) {
            JSONObject data = new JSONObject();
            try {
                data.put("reason", reason);
                OpenTokAndroidPlugin.this.triggerJSEvent("subscriberEvents", "videoDisabled", data);
            } catch (JSONException e) {
            }
        }

        public void onVideoDisableWarning(SubscriberKit arg0) {
            OpenTokAndroidPlugin.this.triggerJSEvent("subscriberEvents", "videoDisableWarning", (Object) null);
        }

        public void onVideoDisableWarningLifted(SubscriberKit arg0) {
            OpenTokAndroidPlugin.this.triggerJSEvent("subscriberEvents", "videoDisableWarningLifted", (Object) null);
        }

        public void onVideoEnabled(SubscriberKit arg0, String reason) {
            JSONObject data = new JSONObject();
            try {
                data.put("reason", reason);
                OpenTokAndroidPlugin.this.triggerJSEvent("subscriberEvents", "videoEnabled", data);
            } catch (JSONException e) {
            }
        }

        public void onAudioLevelUpdated(SubscriberKit subscriber, float audioLevel) {
            JSONObject data = new JSONObject();
            try {
                data.put("audioLevel", (double) audioLevel);
                OpenTokAndroidPlugin.this.triggerJSEvent("subscriberEvents", "audioLevelUpdated", data);
            } catch (JSONException e) {
            }
        }

        public void subscribeToAudio(boolean value) {
            this.mSubscriber.setSubscribeToAudio(value);
        }

        public void subscribeToVideo(boolean value) {
            this.mSubscriber.setSubscribeToVideo(value);
        }
    }

    public void initialize(CordovaInterface cordova2, CordovaWebView webView) {
        _cordova = cordova2;
        _webView = webView;
        _webView.getView().setBackgroundColor(Color.argb(1, 0, 0, 0));
        Log.d(TAG, "Initialize Plugin");
        if (!viewList.has("mainView")) {
            try {
                viewList.put("mainView", webView);
                Log.d(TAG, "Found CordovaView ****** " + webView);
            } catch (JSONException e) {
                Log.e(TAG, "Critical error. Failed to retrieve Cordova's view");
            }
        }
        this.publishCalled = false;
        this.sessionConnected = false;
        this.myEventListeners = new HashMap<>();
        this.connectionCollection = new HashMap<>();
        this.streamCollection = new HashMap<>();
        this.subscriberCollection = new HashMap<>();
        this.streamHasAudio = new HashMap<>();
        this.streamHasVideo = new HashMap<>();
        this.streamVideoDimensions = new HashMap<>();
        super.initialize(cordova2, webView);
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.i(TAG, action);
        if (action.equals("initPublisher")) {
            this.myPublisher = new RunnablePublisher(args);
        } else if (action.equals("destroyPublisher")) {
            if (this.myPublisher != null) {
                this.myPublisher.destroyPublisher();
                this.myPublisher = null;
                callbackContext.success();
                return true;
            }
        } else if (action.equals("initSession")) {
            this.apiKey = args.getString(0);
            this.sessionId = args.getString(1);
            Log.i(TAG, "created new session with data: " + args.toString());
            this.mSession = new Session(this.f5cordova.getActivity().getApplicationContext(), this.apiKey, this.sessionId);
            this.mSession.setSessionListener(this);
            this.mSession.setConnectionListener(this);
            this.mSession.setReconnectionListener(this);
            this.mSession.setSignalListener(this);
            this.mSession.setStreamPropertiesListener(this);
            logOT((String) null);
        } else if (action.equals("setCameraPosition")) {
            this.myPublisher.mPublisher.cycleCamera();
        } else if (action.equals("publishAudio")) {
            boolean publishAudio = true;
            if (args.getString(0).equalsIgnoreCase("false")) {
                publishAudio = false;
            }
            Log.i(TAG, "setting publishAudio");
            this.myPublisher.mPublisher.setPublishAudio(publishAudio);
        } else if (action.equals(SubscriberKit.VIDEO_REASON_PUBLISH_VIDEO)) {
            boolean publishVideo = true;
            if (args.getString(0).equalsIgnoreCase("false")) {
                publishVideo = false;
            }
            Log.i(TAG, "setting publishVideo");
            this.myPublisher.mPublisher.setPublishVideo(publishVideo);
        } else if (action.equals("addEvent")) {
            Log.i(TAG, "adding new event - " + args.getString(0));
            this.myEventListeners.put(args.getString(0), callbackContext);
        } else if (action.equals("connect")) {
            Log.i(TAG, "connect command called");
            this.mSession.connect(args.getString(0));
            callbackContext.success();
        } else if (action.equals("disconnect")) {
            this.mSession.disconnect();
        } else if (action.equals("publish")) {
            if (this.sessionConnected) {
                if (Build.VERSION.SDK_INT >= 23) {
                    this.f5cordova.requestPermissions(this, 0, perms);
                    this.permissionsCallback = callbackContext;
                } else {
                    this.myPublisher.startPublishing();
                    Log.i(TAG, "publisher is publishing");
                }
            }
        } else if (action.equals("signal")) {
            Connection c = this.connectionCollection.get(args.getString(2));
            if (c == null) {
                this.mSession.sendSignal(args.getString(0), args.getString(1));
            } else {
                this.mSession.sendSignal(args.getString(0), args.getString(1), c);
            }
        } else if (action.equals("unpublish")) {
            Log.i(TAG, "unpublish command called");
            if (this.myPublisher != null) {
                this.myPublisher.stopPublishing();
                callbackContext.success();
                return true;
            }
        } else if (action.equals(PushConstants.UNSUBSCRIBE)) {
            Log.i(TAG, "unsubscribe command called");
            Log.i(TAG, "unsubscribe data: " + args.toString());
            RunnableSubscriber runsub = this.subscriberCollection.get(args.getString(0));
            if (runsub != null) {
                runsub.removeStreamView();
                callbackContext.success();
                return true;
            }
        } else if (action.equals(PushConstants.SUBSCRIBE)) {
            Log.i(TAG, "subscribe command called");
            Log.i(TAG, "subscribe data: " + args.toString());
            Stream stream = this.streamCollection.get(args.getString(0));
            this.subscriberCollection.put(stream.getStreamId(), new RunnableSubscriber(args, stream));
        } else if (action.equals("subscribeToAudio")) {
            RunnableSubscriber runsub2 = this.subscriberCollection.get(args.getString(0));
            String val = args.getString(1);
            if (runsub2 != null) {
                boolean subscribeAudio = true;
                if (val.equalsIgnoreCase("false")) {
                    subscribeAudio = false;
                }
                Log.i(TAG, "setting subscribeToAudio");
                runsub2.subscribeToAudio(subscribeAudio);
            }
        } else if (action.equals(SubscriberKit.VIDEO_REASON_SUBSCRIBE_TO_VIDEO)) {
            RunnableSubscriber runsub3 = this.subscriberCollection.get(args.getString(0));
            String val2 = args.getString(1);
            if (runsub3 != null) {
                boolean subscribeVideo = true;
                if (val2.equalsIgnoreCase("false")) {
                    subscribeVideo = false;
                }
                Log.i(TAG, "setting subscribeToVideo");
                runsub3.subscribeToVideo(subscribeVideo);
            }
        } else if (action.equals("updateView")) {
            if (!args.getString(0).equals("TBPublisher") || this.myPublisher == null || !this.sessionConnected) {
                RunnableSubscriber runsub4 = this.subscriberCollection.get(args.getString(0));
                if (runsub4 != null) {
                    runsub4.setPropertyFromArray(args);
                    this.f5cordova.getActivity().runOnUiThread(runsub4);
                }
            } else {
                Log.i(TAG, "updating view for publisher");
                this.myPublisher.setPropertyFromArray(args);
                this.f5cordova.getActivity().runOnUiThread(this.myPublisher);
            }
        } else if (action.equals("getImgData")) {
            if (!args.getString(0).equals("TBPublisher") || this.myPublisher == null || !this.sessionConnected) {
                final RunnableSubscriber runsub5 = this.subscriberCollection.get(args.getString(0));
                if (runsub5 != null) {
                    this.f5cordova.getThreadPool().execute(new Runnable() {
                        public void run() {
                            runsub5.getImgData(callbackContext);
                        }
                    });
                    runsub5.getImgData(callbackContext);
                    return true;
                }
            } else {
                this.f5cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        OpenTokAndroidPlugin.this.myPublisher.getImgData(callbackContext);
                    }
                });
                return true;
            }
        } else if (action.equals("exceptionHandler")) {
        }
        return true;
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] results) throws JSONException {
        Boolean permissionError = false;
        for (int permissionResult : results) {
            if (permissionResult == -1) {
                permissionError = true;
            }
        }
        if (permissionError.booleanValue()) {
            PluginResult callback = new PluginResult(PluginResult.Status.ERROR, "permission denied");
            callback.setKeepCallback(false);
            this.permissionsCallback.sendPluginResult(callback);
            return;
        }
        this.myPublisher.startPublishing();
        Log.i(TAG, "permission granted-publisher is publishing");
    }

    public void alertUser(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f5cordova.getActivity());
        builder.setMessage(message).setTitle("TokBox Message");
        AlertDialog create = builder.create();
    }

    public void onConnected(Session arg0) {
        logOT(arg0.getConnection().getConnectionId());
        Log.i(TAG, "session connected, triggering sessionConnected Event. My Cid is: " + this.mSession.getConnection().getConnectionId());
        this.sessionConnected = true;
        this.connectionCollection.put(this.mSession.getConnection().getConnectionId(), this.mSession.getConnection());
        JSONObject data = new JSONObject();
        try {
            data.put(NotificationCompat.CATEGORY_STATUS, "connected");
            data.put("connection", createDataFromConnection(this.mSession.getConnection()));
        } catch (JSONException e) {
        }
        triggerJSEvent("sessionEvents", "sessionConnected", data);
    }

    public void onDisconnected(Session arg0) {
        this.sessionConnected = false;
        if (this.myPublisher != null) {
            this.myPublisher.destroyPublisher();
            this.myPublisher = null;
        }
        for (Map.Entry<String, RunnableSubscriber> entry : this.subscriberCollection.entrySet()) {
            entry.getValue().removeStreamView();
        }
        this.subscriberCollection.clear();
        this.connectionCollection.clear();
        this.streamCollection.clear();
        JSONObject data = new JSONObject();
        try {
            data.put("reason", "clientDisconnected");
        } catch (JSONException e) {
        }
        triggerJSEvent("sessionEvents", "sessionDisconnected", data);
    }

    public void onReconnected(Session session) {
        Log.i(TAG, "session reconnected");
        triggerJSEvent("sessionEvents", "sessionReconnected", (Object) null);
    }

    public void onReconnecting(Session session) {
        Log.i(TAG, "session reconnecting");
        triggerJSEvent("sessionEvents", "sessionReconnecting", (Object) null);
    }

    public void onStreamDropped(Session arg0, Stream arg1) {
        Log.i(TAG, "session dropped stream");
        this.streamCollection.remove(arg1.getStreamId());
        this.streamHasAudio.remove(arg1.getStreamId());
        this.streamHasVideo.remove(arg1.getStreamId());
        this.streamVideoDimensions.remove(arg1.getStreamId());
        RunnableSubscriber subscriber = this.subscriberCollection.get(arg1.getStreamId());
        if (subscriber != null) {
            subscriber.removeStreamView();
            this.subscriberCollection.remove(arg1.getStreamId());
        }
        triggerStreamEvent(arg1, "sessionEvents", "streamDestroyed");
    }

    public void onStreamReceived(Session arg0, Stream arg1) {
        Log.i(TAG, "stream received");
        this.streamCollection.put(arg1.getStreamId(), arg1);
        this.streamHasAudio.put(arg1.getStreamId(), Boolean.valueOf(arg1.hasAudio()));
        this.streamHasVideo.put(arg1.getStreamId(), Boolean.valueOf(arg1.hasVideo()));
        JSONObject videoDimensions = new JSONObject();
        try {
            videoDimensions.put("width", arg1.getVideoWidth());
            videoDimensions.put("height", arg1.getVideoHeight());
        } catch (JSONException e) {
        }
        this.streamVideoDimensions.put(arg1.getStreamId(), videoDimensions);
        triggerStreamEvent(arg1, "sessionEvents", "streamCreated");
    }

    public void onError(Session arg0, OpentokError arg1) {
        Log.e(TAG, "session exception: " + arg1.getMessage());
        alertUser("session error " + arg1.getMessage());
    }

    public void onConnectionCreated(Session arg0, Connection arg1) {
        Log.i(TAG, "connectionCreated");
        this.connectionCollection.put(arg1.getConnectionId(), arg1);
        JSONObject data = new JSONObject();
        try {
            data.put("connection", createDataFromConnection(arg1));
        } catch (JSONException e) {
        }
        triggerJSEvent("sessionEvents", "connectionCreated", data);
    }

    public void onConnectionDestroyed(Session arg0, Connection arg1) {
        Log.i(TAG, "connection dropped: " + arg1.getConnectionId());
        this.connectionCollection.remove(arg1.getConnectionId());
        JSONObject data = new JSONObject();
        try {
            data.put("connection", createDataFromConnection(arg1));
        } catch (JSONException e) {
        }
        triggerJSEvent("sessionEvents", "connectionDestroyed", data);
    }

    public void onSignalReceived(Session arg0, String arg1, String arg2, Connection arg3) {
        JSONObject data = new JSONObject();
        Log.i(TAG, "signal type: " + arg1);
        Log.i(TAG, "signal data: " + arg2);
        try {
            data.put(AppMeasurement.Param.TYPE, arg1);
            data.put(PushConstants.PARSE_COM_DATA, arg2);
            if (arg3 != null) {
                data.put("connectionId", arg3.getConnectionId());
            }
            triggerJSEvent("sessionEvents", "signalReceived", data);
        } catch (JSONException e) {
        }
    }

    public void onArchiveStarted(Session session, String id, String name) {
        JSONObject data = new JSONObject();
        try {
            data.put(PushConstants.CHANNEL_ID, id);
            data.put("name", name);
            triggerJSEvent("sessionEvents", "archiveStarted", data);
        } catch (JSONException e) {
            Log.i(TAG, "archive started: " + id + " - " + name);
        }
    }

    public void onArchiveStopped(Session session, String id) {
        JSONObject data = new JSONObject();
        try {
            data.put(PushConstants.CHANNEL_ID, id);
            triggerJSEvent("sessionEvents", "archiveStopped", data);
        } catch (JSONException e) {
            Log.i(TAG, "archive stopped: " + id);
        }
    }

    public void onStreamHasAudioChanged(Session session, Stream stream, boolean newValue) {
        boolean oldValue = this.streamHasAudio.get(stream.getStreamId()).booleanValue();
        this.streamHasAudio.put(stream.getStreamId(), Boolean.valueOf(newValue));
        onStreamPropertyChanged("hasAudio", Boolean.valueOf(newValue), Boolean.valueOf(oldValue), stream);
    }

    public void onStreamHasVideoChanged(Session session, Stream stream, boolean newValue) {
        boolean oldValue = this.streamHasVideo.get(stream.getStreamId()).booleanValue();
        this.streamHasVideo.put(stream.getStreamId(), Boolean.valueOf(newValue));
        onStreamPropertyChanged("hasVideo", Boolean.valueOf(newValue), Boolean.valueOf(oldValue), stream);
    }

    public void onStreamVideoDimensionsChanged(Session session, Stream stream, int width, int height) {
        JSONObject oldValue = this.streamVideoDimensions.get(stream.getStreamId());
        JSONObject newValue = new JSONObject();
        try {
            newValue.put("width", width);
            newValue.put("height", height);
            this.streamVideoDimensions.put(stream.getStreamId(), newValue);
            onStreamPropertyChanged("videoDimensions", newValue, oldValue, stream);
        } catch (JSONException e) {
        }
    }

    public void onStreamPropertyChanged(String changedProperty, Object newValue, Object oldValue, Stream stream) {
        JSONObject data = new JSONObject();
        try {
            JSONObject streamData = createDataFromStream(stream);
            data.put("changedProperty", changedProperty);
            data.put("newValue", newValue);
            data.put("oldValue", oldValue);
            data.put("stream", streamData);
            triggerJSEvent("sessionEvents", "streamPropertyChanged", data);
        } catch (JSONException e) {
        }
    }

    public void logMessage(String a) {
        Log.i(TAG, a);
    }

    public boolean compareStrings(String a, String b) {
        if (a == null || b == null || !a.equalsIgnoreCase(b)) {
            return false;
        }
        return true;
    }

    public void triggerStreamEvent(Stream arg1, String eventType, String subEvent) {
        JSONObject data = new JSONObject();
        try {
            data.put("stream", createDataFromStream(arg1));
            triggerJSEvent(eventType, subEvent, data);
        } catch (JSONException e) {
        }
    }

    public void logOT(String connectionId) {
        final String str = connectionId;
        Volley.newRequestQueue(this.f5cordova.getActivity().getApplicationContext()).add(new StringRequest(1, "https://hlg.tokbox.com/prod/logging/ClientEvent", new Response.Listener<String>() {
            public void onResponse(String response) {
                Log.i(OpenTokAndroidPlugin.TAG, "Log Response: " + response);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.i(OpenTokAndroidPlugin.TAG, "Error logging");
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() {
                JSONObject payload = new JSONObject();
                try {
                    payload.put("platform", "Android");
                    payload.put("cp_version", "3.4.3");
                } catch (JSONException e) {
                    Log.i(OpenTokAndroidPlugin.TAG, "Error creating payload json object");
                }
                Map<String, String> params = new HashMap<>();
                params.put("payload_type", "info");
                params.put("partner_id", OpenTokAndroidPlugin.this.apiKey);
                params.put("payload", payload.toString());
                params.put(FirebaseAnalytics.Param.SOURCE, "https://github.com/opentok/cordova-plugin-opentok");
                params.put("build", WrapperSettings.SDK_VERSION);
                params.put("session_id", OpenTokAndroidPlugin.this.sessionId);
                if (str != null) {
                    params.put("action", "cp_on_connect");
                    params.put("connectionId", str);
                } else {
                    params.put("action", "cp_initialize");
                }
                return params;
            }
        });
    }

    public JSONObject createDataFromConnection(Connection arg1) {
        JSONObject connection = new JSONObject();
        try {
            connection.put("connectionId", arg1.getConnectionId());
            connection.put("creationTime", arg1.getCreationTime());
            connection.put(PushConstants.PARSE_COM_DATA, arg1.getData());
        } catch (JSONException e) {
        }
        return connection;
    }

    public JSONObject createDataFromStream(Stream arg1) {
        JSONObject stream = new JSONObject();
        try {
            Connection connection = arg1.getConnection();
            if (connection != null) {
                stream.put("connectionId", connection.getConnectionId());
                stream.put("connection", createDataFromConnection(connection));
            }
            stream.put("creationTime", arg1.getCreationTime());
            stream.put("fps", -999);
            stream.put("hasAudio", arg1.hasAudio());
            stream.put("hasVideo", arg1.hasVideo());
            JSONObject videoDimensions = new JSONObject();
            try {
                videoDimensions.put("width", arg1.getVideoWidth());
                videoDimensions.put("height", arg1.getVideoHeight());
            } catch (JSONException e) {
            }
            stream.put("videoDimensions", videoDimensions);
            String videoType = "custom";
            if (arg1.getStreamVideoType() == Stream.StreamVideoType.StreamVideoTypeCamera) {
                videoType = "camera";
            } else if (arg1.getStreamVideoType() == Stream.StreamVideoType.StreamVideoTypeScreen) {
                videoType = "screen";
            }
            stream.put("videoType", videoType);
            stream.put("name", arg1.getName());
            stream.put("streamId", arg1.getStreamId());
        } catch (Exception e2) {
        }
        return stream;
    }

    public void triggerJSEvent(String event, String type, Object data) {
        JSONObject message = new JSONObject();
        try {
            message.put("eventType", type);
            message.put(PushConstants.PARSE_COM_DATA, data);
        } catch (JSONException e) {
        }
        PluginResult myResult = new PluginResult(PluginResult.Status.OK, message);
        myResult.setKeepCallback(true);
        this.myEventListeners.get(event).sendPluginResult(myResult);
    }

    public void onError(PublisherKit arg0, OpentokError arg1) {
    }

    public void onStreamCreated(PublisherKit arg0, Stream arg1) {
    }

    public void onStreamDestroyed(PublisherKit arg0, Stream arg1) {
        if (this.myPublisher != null) {
            this.myPublisher.destroyPublisher();
            this.myPublisher = null;
        }
    }

    public void onStreamVideoTypeChanged(Session arg0, Stream arg1, Stream.StreamVideoType arg2) {
    }
}
