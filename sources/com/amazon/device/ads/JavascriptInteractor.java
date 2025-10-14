package com.amazon.device.ads;

import android.webkit.JavascriptInterface;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

class JavascriptInteractor {
    private static final String LOGTAG = JavascriptInteractor.class.getSimpleName();
    private static String executorMethodName;
    private static final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private final Executor executor = new Executor(this);
    private final Map<String, JavascriptMethodExecutor> methodMap = new ConcurrentHashMap();

    public static String getExecutorMethodName() {
        if (executorMethodName == null) {
            Method[] methods = Executor.class.getDeclaredMethods();
            if (methods == null || methods.length != 1) {
                logger.e("Could not obtain the method name for javascript interfacing.");
            } else {
                executorMethodName = methods[0].getName();
            }
        }
        return executorMethodName;
    }

    public void addMethodExecutor(JavascriptMethodExecutor javascriptMethodExecutor) {
        if (this.methodMap.containsKey(javascriptMethodExecutor.getMethodName())) {
            throw new IllegalArgumentException("There is another executor with that method name already added.");
        }
        this.methodMap.put(javascriptMethodExecutor.getMethodName(), javascriptMethodExecutor);
    }

    public Executor getExecutor() {
        return this.executor;
    }

    /* access modifiers changed from: private */
    public JSONObject execute(String method, String javascriptObjectParameters) {
        JSONObject jsonObject = null;
        if (javascriptObjectParameters == null || javascriptObjectParameters.length() <= 2 || (jsonObject = JSONUtils.getJSONObjectFromString(javascriptObjectParameters)) != null) {
            return execute(method, jsonObject);
        }
        logger.w("The javascript object \"%s\" could not be parsed for method \"%s\".", javascriptObjectParameters, method);
        return null;
    }

    private JSONObject execute(String method, JSONObject jsonObject) {
        if (this.methodMap.containsKey(method)) {
            return this.methodMap.get(method).execute(jsonObject);
        }
        logger.w("The method %s was not recongized by this javascript interface.", method);
        return null;
    }

    public static class Executor {
        private final JavascriptInteractor interactor;
        private boolean proguardKeeper = false;

        public Executor(JavascriptInteractor interactor2) {
            this.interactor = interactor2;
            if (this.proguardKeeper) {
                execute((String) null, (String) null);
            }
        }

        @JavascriptInterface
        public String execute(String method, String javascriptObjectParameters) {
            JSONObject returnJSON = this.interactor.execute(method, javascriptObjectParameters);
            if (returnJSON == null) {
                return null;
            }
            return returnJSON.toString();
        }
    }

    public static abstract class JavascriptMethodExecutor {
        private final String methodName;

        /* access modifiers changed from: protected */
        public abstract JSONObject execute(JSONObject jSONObject);

        protected JavascriptMethodExecutor(String methodName2) {
            this.methodName = methodName2;
        }

        public String getMethodName() {
            return this.methodName;
        }
    }
}
