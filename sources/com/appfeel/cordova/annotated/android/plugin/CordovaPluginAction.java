package com.appfeel.cordova.annotated.android.plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaPluginAction {
    /* access modifiers changed from: private */
    public static String TAG = "CordovaPluginAction";
    private ExecutionThread executionThread;
    /* access modifiers changed from: private */
    public boolean isAutofinish = false;
    /* access modifiers changed from: private */
    public Method method;

    CordovaPluginAction(Method method2, ExecutionThread executionThread2, boolean isAutofinish2) {
        this.method = method2;
        this.executionThread = executionThread2;
        this.isAutofinish = isAutofinish2;
    }

    private static Object[] getMethodArgs(JSONArray args, CallbackContext callbackContext) throws JSONException {
        int len = args.length();
        Object[] mArgs = new Object[(len + 1)];
        for (int i = 0; i < len; i++) {
            Object value = args.opt(i);
            if (JSONObject.NULL.equals(value)) {
                value = null;
            }
            mArgs[i] = value;
        }
        mArgs[len] = callbackContext;
        return mArgs;
    }

    private Runnable createRunnable(final Object[] mArgs, final AnnotatedCordovaPlugin caller, final CallbackContext callbackContext) {
        return new Runnable() {
            public void run() {
                try {
                    CordovaPluginAction.this.method.invoke(caller, mArgs);
                    if (!callbackContext.isFinished() && CordovaPluginAction.this.isAutofinish) {
                        callbackContext.success();
                    }
                } catch (Throwable th) {
                    e = th;
                    if (e instanceof InvocationTargetException) {
                        e = ((InvocationTargetException) e).getTargetException();
                    }
                    LOG.e(CordovaPluginAction.TAG, "Uncaught exception at " + getClass().getSimpleName() + "@" + CordovaPluginAction.this.method.getName(), e);
                    callbackContext.error(e.getMessage());
                }
            }
        };
    }

    public boolean execute(CordovaInterface cordova2, AnnotatedCordovaPlugin caller, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Runnable runnable = createRunnable(getMethodArgs(args, callbackContext), caller, callbackContext);
        if (this.executionThread == ExecutionThread.WORKER) {
            cordova2.getThreadPool().execute(runnable);
            return true;
        } else if (this.executionThread == ExecutionThread.UI) {
            cordova2.getActivity().runOnUiThread(runnable);
            return true;
        } else {
            runnable.run();
            return true;
        }
    }
}
