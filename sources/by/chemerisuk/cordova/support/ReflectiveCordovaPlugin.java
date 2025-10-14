package by.chemerisuk.cordova.support;

import android.util.Pair;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReflectiveCordovaPlugin extends CordovaPlugin {
    /* access modifiers changed from: private */
    public static String TAG = "ReflectiveCordovaPlugin";
    private Map<String, Pair<Method, ExecutionThread>> pairs;

    public enum ExecutionThread {
        MAIN,
        UI,
        WORKER
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (this.pairs == null) {
            this.pairs = createCommandFactories();
        }
        Pair<Method, ExecutionThread> pair = this.pairs.get(action);
        if (pair == null) {
            return false;
        }
        Runnable command = createCommand((Method) pair.first, getMethodArgs(args, callbackContext), callbackContext);
        ExecutionThread executionThread = (ExecutionThread) pair.second;
        if (executionThread == ExecutionThread.WORKER) {
            this.f5cordova.getThreadPool().execute(command);
        } else if (executionThread == ExecutionThread.UI) {
            this.f5cordova.getActivity().runOnUiThread(command);
        } else {
            command.run();
        }
        return true;
    }

    private Runnable createCommand(final Method method, final Object[] methodArgs, final CallbackContext callbackContext) {
        return new Runnable() {
            public void run() {
                try {
                    method.invoke(ReflectiveCordovaPlugin.this, methodArgs);
                } catch (Throwable th) {
                    e = th;
                    if (e instanceof InvocationTargetException) {
                        e = ((InvocationTargetException) e).getTargetException();
                    }
                    LOG.e(ReflectiveCordovaPlugin.TAG, "Uncaught exception at " + getClass().getSimpleName() + "#" + method.getName(), e);
                    callbackContext.error(e.getMessage());
                }
            }
        };
    }

    private Map<String, Pair<Method, ExecutionThread>> createCommandFactories() {
        Map<String, Pair<Method, ExecutionThread>> result = new HashMap<>();
        for (Method method : getClass().getDeclaredMethods()) {
            CordovaMethod cordovaMethod = (CordovaMethod) method.getAnnotation(CordovaMethod.class);
            if (cordovaMethod != null) {
                String methodAction = cordovaMethod.action();
                if (methodAction.isEmpty()) {
                    methodAction = method.getName();
                }
                result.put(methodAction, new Pair(method, cordovaMethod.value()));
                method.setAccessible(true);
            }
        }
        return result;
    }

    private static Object[] getMethodArgs(JSONArray args, CallbackContext callbackContext) {
        int len = args.length();
        Object[] methodArgs = new Object[(len + 1)];
        for (int i = 0; i < len; i++) {
            Object argValue = args.opt(i);
            if (JSONObject.NULL.equals(argValue)) {
                argValue = null;
            }
            methodArgs[i] = argValue;
        }
        methodArgs[len] = callbackContext;
        return methodArgs;
    }
}
