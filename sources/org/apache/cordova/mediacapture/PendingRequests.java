package org.apache.cordova.mediacapture;

import android.os.Bundle;
import android.util.SparseArray;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PendingRequests {
    private static final String CURRENT_ID_KEY = "currentReqId";
    private static final String LOG_TAG = "PendingCaptureRequests";
    private static final String REQUEST_KEY_PREFIX = "request_";
    private int currentReqId = 0;
    private Bundle lastSavedState;
    private SparseArray<Request> requests = new SparseArray<>();
    private CallbackContext resumeContext;

    public synchronized Request createRequest(int action, JSONObject options, CallbackContext callbackContext) throws JSONException {
        Request req;
        req = new Request(action, options, callbackContext);
        this.requests.put(req.requestCode, req);
        return req;
    }

    public synchronized Request get(int requestCode) {
        Request r;
        if (this.lastSavedState == null || !this.lastSavedState.containsKey(REQUEST_KEY_PREFIX + requestCode)) {
            r = this.requests.get(requestCode);
        } else {
            r = new Request(this.lastSavedState.getBundle(REQUEST_KEY_PREFIX + requestCode), this.resumeContext, requestCode);
            this.requests.put(requestCode, r);
            this.lastSavedState = null;
            this.resumeContext = null;
        }
        return r;
    }

    public synchronized void resolveWithFailure(Request req, JSONObject error) {
        req.callbackContext.error(error);
        this.requests.remove(req.requestCode);
    }

    public synchronized void resolveWithSuccess(Request req) {
        req.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, req.results));
        this.requests.remove(req.requestCode);
    }

    /* access modifiers changed from: private */
    public synchronized int incrementCurrentReqId() {
        int i;
        i = this.currentReqId;
        this.currentReqId = i + 1;
        return i;
    }

    public synchronized void setLastSavedState(Bundle lastSavedState2, CallbackContext resumeContext2) {
        this.lastSavedState = lastSavedState2;
        this.resumeContext = resumeContext2;
        this.currentReqId = lastSavedState2.getInt(CURRENT_ID_KEY);
    }

    public synchronized Bundle toBundle() {
        Bundle bundle;
        bundle = new Bundle();
        bundle.putInt(CURRENT_ID_KEY, this.currentReqId);
        for (int i = 0; i < this.requests.size(); i++) {
            bundle.putBundle(REQUEST_KEY_PREFIX + this.requests.keyAt(i), this.requests.valueAt(i).toBundle());
        }
        if (this.requests.size() > 1) {
            LOG.w(LOG_TAG, "More than one media capture request pending on Activity destruction. Some requests will be dropped!");
        }
        return bundle;
    }

    public class Request {
        private static final String ACTION_KEY = "action";
        private static final String DURATION_KEY = "duration";
        private static final String LIMIT_KEY = "limit";
        private static final String QUALITY_KEY = "quality";
        private static final String RESULTS_KEY = "results";
        public int action;
        /* access modifiers changed from: private */
        public CallbackContext callbackContext;
        public int duration;
        public long limit;
        public int quality;
        public int requestCode;
        public JSONArray results;

        private Request(int action2, JSONObject options, CallbackContext callbackContext2) throws JSONException {
            this.limit = 1;
            this.duration = 0;
            this.quality = 1;
            this.results = new JSONArray();
            this.callbackContext = callbackContext2;
            this.action = action2;
            if (options != null) {
                this.limit = options.optLong(LIMIT_KEY, 1);
                this.duration = options.optInt(DURATION_KEY, 0);
                this.quality = options.optInt("quality", 1);
            }
            this.requestCode = PendingRequests.this.incrementCurrentReqId();
        }

        private Request(Bundle bundle, CallbackContext callbackContext2, int requestCode2) {
            this.limit = 1;
            this.duration = 0;
            this.quality = 1;
            this.results = new JSONArray();
            this.callbackContext = callbackContext2;
            this.requestCode = requestCode2;
            this.action = bundle.getInt(ACTION_KEY);
            this.limit = bundle.getLong(LIMIT_KEY);
            this.duration = bundle.getInt(DURATION_KEY);
            this.quality = bundle.getInt("quality");
            try {
                this.results = new JSONArray(bundle.getString(RESULTS_KEY));
            } catch (JSONException e) {
                LOG.e(PendingRequests.LOG_TAG, "Error parsing results for request from saved bundle", (Throwable) e);
            }
        }

        /* access modifiers changed from: private */
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(ACTION_KEY, this.action);
            bundle.putLong(LIMIT_KEY, this.limit);
            bundle.putInt(DURATION_KEY, this.duration);
            bundle.putInt("quality", this.quality);
            bundle.putString(RESULTS_KEY, this.results.toString());
            return bundle;
        }
    }
}
