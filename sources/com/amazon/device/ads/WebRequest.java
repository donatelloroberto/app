package com.amazon.device.ads;

import com.amazon.device.ads.Metrics;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

abstract class WebRequest {
    private static final String CHARSET_KEY = "charset";
    public static final String CHARSET_UTF_16 = "UTF-16";
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE_CSS = "text/css";
    public static final String CONTENT_TYPE_HTML = "text/html";
    public static final String CONTENT_TYPE_JAVASCRIPT = "application/javascript";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_PLAIN_TEXT = "text/plain";
    public static final int DEFAULT_PORT = -1;
    public static final int DEFAULT_TIMEOUT = 20000;
    private static final String HEADER_ACCEPT_KEY = "Accept";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String LOGTAG = WebRequest.class.getSimpleName();
    String acceptContentType;
    String charset;
    String contentType;
    /* access modifiers changed from: private */
    public boolean disconnectEnabled;
    protected final HashMap<String, String> headers;
    private HttpMethod httpMethod;
    boolean logRequestBodyEnabled;
    boolean logResponseEnabled;
    private String logTag;
    protected boolean logUrlEnabled;
    private final MobileAdsLogger logger;
    private MetricsCollector metricsCollector;
    private String nonSecureHost;
    private String path;
    private int port;
    protected HashMap<String, String> postParameters;
    protected QueryStringParameters queryStringParameters;
    String requestBody;
    protected boolean secure;
    private String secureHost;
    protected Metrics.MetricType serviceCallLatencyMetric;
    private int timeout;
    private String urlString;

    public enum WebRequestStatus {
        NETWORK_FAILURE,
        NETWORK_TIMEOUT,
        MALFORMED_URL,
        INVALID_CLIENT_PROTOCOL,
        UNSUPPORTED_ENCODING
    }

    /* access modifiers changed from: protected */
    public abstract void closeConnection();

    /* access modifiers changed from: protected */
    public abstract WebResponse doHttpNetworkCall(URL url) throws WebRequestException;

    /* access modifiers changed from: protected */
    public abstract String getSubLogTag();

    WebRequest() {
        this.requestBody = null;
        this.acceptContentType = null;
        this.contentType = null;
        this.charset = null;
        this.urlString = null;
        this.secureHost = null;
        this.nonSecureHost = null;
        this.path = null;
        this.port = -1;
        this.httpMethod = HttpMethod.GET;
        this.timeout = 20000;
        this.logRequestBodyEnabled = false;
        this.logResponseEnabled = false;
        this.logUrlEnabled = false;
        this.secure = false;
        this.logTag = LOGTAG;
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(this.logTag);
        this.queryStringParameters = new QueryStringParameters();
        this.headers = new HashMap<>();
        this.postParameters = new HashMap<>();
        this.secure = DebugProperties.getInstance().getDebugPropertyAsBoolean(DebugProperties.DEBUG_TLS_ENABLED, Boolean.valueOf(Settings.getInstance().getBoolean("tlsEnabled", false))).booleanValue();
        this.disconnectEnabled = true;
    }

    public void convertToJSONPostRequest() {
        setHttpMethod(HttpMethod.POST);
        putHeader(HEADER_ACCEPT_KEY, CONTENT_TYPE_JSON);
        putHeader(HEADER_CONTENT_TYPE, "application/json; charset=UTF-8");
    }

    public WebResponse makeCall() throws WebRequestException {
        if (ThreadUtils.isOnMainThread()) {
            this.logger.e("The network request should not be performed on the main thread.");
        }
        setContentTypeHeaders();
        String urlString2 = getUrl();
        try {
            URL url = createURL(urlString2);
            writeMetricStart(this.serviceCallLatencyMetric);
            try {
                WebResponse response = doHttpNetworkCall(url);
                writeMetricStop(this.serviceCallLatencyMetric);
                if (this.logResponseEnabled) {
                    this.logger.d("Response: %s %s", Integer.valueOf(response.getHttpStatusCode()), response.getHttpStatus());
                }
                return response;
            } catch (WebRequestException e) {
                throw e;
            } catch (Throwable th) {
                writeMetricStop(this.serviceCallLatencyMetric);
                throw th;
            }
        } catch (MalformedURLException e2) {
            this.logger.e("Problem with URI syntax: %s", e2.getMessage());
            throw new WebRequestException(WebRequestStatus.MALFORMED_URL, "Could not construct URL from String " + urlString2, e2);
        }
    }

    public void enableLogUrl(boolean enabled) {
        this.logUrlEnabled = enabled;
    }

    public void enableLogRequestBody(boolean enabled) {
        this.logRequestBodyEnabled = enabled;
    }

    public void enableLogResponse(boolean enabled) {
        this.logResponseEnabled = enabled;
    }

    public void enableLog(boolean enabled) {
        enableLogUrl(enabled);
        enableLogRequestBody(enabled);
        enableLogResponse(enabled);
    }

    /* access modifiers changed from: protected */
    public void logUrl(String url) {
        if (this.logUrlEnabled) {
            this.logger.d("%s %s", getHttpMethod(), url);
        }
    }

    public String getQueryParameter(String name) {
        return this.queryStringParameters.get(name);
    }

    public void putUrlEncodedQueryParameter(String name, String value) {
        this.queryStringParameters.putUrlEncoded(name, value);
    }

    public String putUnencodedQueryParameter(String name, String value) {
        return this.queryStringParameters.putUnencoded(name, value);
    }

    public void setQueryStringParameters(QueryStringParameters queryStringParameters2) {
        this.queryStringParameters = queryStringParameters2;
    }

    public String getPostParameter(String name) {
        if (!StringUtils.isNullOrWhiteSpace(name)) {
            return this.postParameters.get(name);
        }
        throw new IllegalArgumentException("The name must not be null or empty string.");
    }

    public void putPostParameter(String name, String value) {
        if (StringUtils.isNullOrWhiteSpace(name)) {
            throw new IllegalArgumentException("The name must not be null or empty string.");
        } else if (value == null) {
            this.postParameters.remove(name);
        } else {
            this.postParameters.put(name, value);
        }
    }

    public String getHeader(String name) {
        if (!StringUtils.isNullOrWhiteSpace(name)) {
            return this.headers.get(name);
        }
        throw new IllegalArgumentException("The name must not be null or empty string.");
    }

    public void putHeader(String name, String value) {
        if (StringUtils.isNullOrWhiteSpace(name)) {
            throw new IllegalArgumentException("The name must not be null or empty string.");
        }
        this.headers.put(name, value);
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod2) {
        if (httpMethod2 == null) {
            throw new IllegalArgumentException("The httpMethod must not be null.");
        }
        this.httpMethod = httpMethod2;
    }

    public String getHost() {
        return this.secure ? this.secureHost : this.nonSecureHost;
    }

    public void setHost(String host) {
        if (StringUtils.isNullOrWhiteSpace(host)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.secureHost = host;
        this.nonSecureHost = host;
    }

    public void setSecureHost(String secureHost2) {
        if (StringUtils.isNullOrWhiteSpace(secureHost2)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.secureHost = secureHost2;
    }

    public void setNonSecureHost(String nonSecureHost2) {
        if (StringUtils.isNullOrWhiteSpace(nonSecureHost2)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.nonSecureHost = nonSecureHost2;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port2) {
        this.port = port2;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path2) {
        this.path = path2;
    }

    public boolean getUseSecure() {
        return this.secure;
    }

    public void setUseSecure(boolean secure2) {
        this.secure = secure2;
    }

    public void setUrlString(String urlString2) {
        if (urlString2 != null && this.secure && urlString2.startsWith("http:")) {
            urlString2 = urlString2.replaceFirst("http", "https");
        }
        this.urlString = urlString2;
    }

    public String getUrlString() {
        return this.urlString;
    }

    public void setRequestBodyString(String requestBody2) {
        this.requestBody = requestBody2;
    }

    public String getRequestBodyString() {
        return this.requestBody;
    }

    public String getRequestBody() {
        if (getRequestBodyString() != null) {
            return getRequestBodyString();
        }
        if (this.postParameters.isEmpty()) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : this.postParameters.entrySet()) {
            builder.append(entry.getKey()).append('=').append(entry.getValue()).append(";\n");
        }
        return builder.toString();
    }

    public void setAcceptContentType(String acceptContentType2) {
        this.acceptContentType = this.contentType;
    }

    public String getAcceptContentType() {
        return this.acceptContentType;
    }

    public void setContentType(String contentType2) {
        this.contentType = contentType2;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setCharset(String charset2) {
        this.charset = charset2;
    }

    public String getCharset() {
        return this.charset;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout2) {
        this.timeout = timeout2;
    }

    public void setMetricsCollector(MetricsCollector metricsCollector2) {
        this.metricsCollector = metricsCollector2;
    }

    public void setServiceCallLatencyMetric(Metrics.MetricType metric) {
        this.serviceCallLatencyMetric = metric;
    }

    public void setAdditionalQueryParamsString(String paramsString) {
        this.queryStringParameters.setRawAppendage(paramsString);
    }

    public void setExternalLogTag(String externalLogTag) {
        if (externalLogTag == null) {
            this.logTag = LOGTAG + " " + getSubLogTag();
        } else {
            this.logTag = externalLogTag + " " + LOGTAG + " " + getSubLogTag();
        }
        this.logger.withLogTag(this.logTag);
    }

    public boolean getDisconnectEnabled() {
        return this.disconnectEnabled;
    }

    public void setDisconnectEnabled(boolean disconnectEnabled2) {
        this.disconnectEnabled = disconnectEnabled2;
    }

    /* access modifiers changed from: protected */
    public MobileAdsLogger getLogger() {
        return this.logger;
    }

    /* access modifiers changed from: private */
    public String getLogTag() {
        return this.logTag;
    }

    /* access modifiers changed from: protected */
    public void setContentTypeHeaders() {
        if (this.acceptContentType != null) {
            putHeader(HEADER_ACCEPT_KEY, this.contentType);
        }
        if (this.contentType != null) {
            String contentType2 = this.contentType;
            if (this.charset != null) {
                contentType2 = contentType2 + "; charset=" + this.charset;
            }
            putHeader(HEADER_CONTENT_TYPE, contentType2);
        }
    }

    /* access modifiers changed from: protected */
    public void writeMetricStart(Metrics.MetricType metric) {
        if (metric != null && this.metricsCollector != null) {
            this.metricsCollector.startMetric(metric);
        }
    }

    /* access modifiers changed from: protected */
    public void writeMetricStop(Metrics.MetricType metric) {
        if (metric != null && this.metricsCollector != null) {
            this.metricsCollector.stopMetric(metric);
        }
    }

    /* access modifiers changed from: protected */
    public URI createUri() throws URISyntaxException, MalformedURLException {
        return new URL(getUrlString()).toURI();
    }

    /* access modifiers changed from: protected */
    public URI createURI(String url) throws MalformedURLException, URISyntaxException {
        return createURI(createURL(url));
    }

    /* access modifiers changed from: protected */
    public URI createURI(URL url) throws URISyntaxException {
        return url.toURI();
    }

    /* access modifiers changed from: protected */
    public URL createURL(String urlString2) throws MalformedURLException {
        return new URL(urlString2);
    }

    /* access modifiers changed from: protected */
    public void appendQuery(StringBuilder builder) {
        this.queryStringParameters.append(builder);
    }

    /* access modifiers changed from: protected */
    public String getScheme() {
        if (getUseSecure()) {
            return "https";
        }
        return "http";
    }

    public String toString() {
        return getUrl();
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        if (this.urlString != null) {
            return this.urlString;
        }
        StringBuilder builder = new StringBuilder(getScheme());
        builder.append("://");
        builder.append(getHost());
        if (getPort() != -1) {
            builder.append(":");
            builder.append(getPort());
        }
        builder.append(getPath());
        appendQuery(builder);
        return builder.toString();
    }

    public enum HttpMethod {
        GET("GET"),
        POST("POST");
        
        private final String methodString;

        private HttpMethod(String methodString2) {
            this.methodString = methodString2;
        }

        public String toString() {
            return this.methodString;
        }
    }

    public class WebResponse {
        private String httpStatus;
        private int httpStatusCode;
        private WebRequestInputStream inputStream;

        protected WebResponse() {
        }

        /* access modifiers changed from: protected */
        public void setInputStream(InputStream inputStream2) {
            this.inputStream = new WebRequestInputStream(inputStream2);
        }

        public ResponseReader getResponseReader() {
            ResponseReader reader = new ResponseReader(this.inputStream);
            reader.enableLog(WebRequest.this.logResponseEnabled);
            reader.setExternalLogTag(WebRequest.this.getLogTag());
            return reader;
        }

        public int getHttpStatusCode() {
            return this.httpStatusCode;
        }

        /* access modifiers changed from: protected */
        public void setHttpStatusCode(int httpStatusCode2) {
            this.httpStatusCode = httpStatusCode2;
        }

        public boolean isHttpStatusCodeOK() {
            return getHttpStatusCode() == 200;
        }

        public String getHttpStatus() {
            return this.httpStatus;
        }

        /* access modifiers changed from: protected */
        public void setHttpStatus(String httpStatus2) {
            this.httpStatus = httpStatus2;
        }
    }

    public class WebRequestException extends Exception {
        private static final long serialVersionUID = -4980265484926465548L;
        private final WebRequestStatus status;

        protected WebRequestException(WebRequestStatus status2, String message, Throwable cause) {
            super(message, cause);
            this.status = status2;
        }

        protected WebRequestException(WebRequest webRequest, WebRequestStatus status2, String message) {
            this(status2, message, (Throwable) null);
        }

        public WebRequestStatus getStatus() {
            return this.status;
        }
    }

    public static class WebRequestFactory {
        private final AndroidBuildInfo androidBuildInfo;

        public WebRequestFactory() {
            this(new AndroidBuildInfo());
        }

        WebRequestFactory(AndroidBuildInfo androidBuildInfo2) {
            this.androidBuildInfo = androidBuildInfo2;
        }

        public WebRequest createWebRequest() {
            if (AndroidTargetUtils.isAtOrBelowAndroidAPI(this.androidBuildInfo, 7)) {
                return new HttpClientWebRequest();
            }
            return new HttpURLConnectionWebRequest();
        }

        public WebRequest createJSONGetWebRequest() {
            WebRequest request = createWebRequest();
            request.setHttpMethod(HttpMethod.GET);
            request.putHeader(WebRequest.HEADER_ACCEPT_KEY, WebRequest.CONTENT_TYPE_JSON);
            return request;
        }

        public WebRequest createJSONPostWebRequest() {
            WebRequest request = createWebRequest();
            request.convertToJSONPostRequest();
            return request;
        }
    }

    static class QueryStringParameters {
        private final HashMap<String, String> params = new HashMap<>();
        private String rawAppendage;

        QueryStringParameters() {
        }

        /* access modifiers changed from: package-private */
        public int size() {
            return this.params.size();
        }

        /* access modifiers changed from: package-private */
        public void setRawAppendage(String rawAppendage2) {
            this.rawAppendage = rawAppendage2;
        }

        /* access modifiers changed from: package-private */
        public void putUrlEncoded(String param, boolean value) {
            putUrlEncoded(param, Boolean.toString(value));
        }

        /* access modifiers changed from: package-private */
        public void putUrlEncodedIfNotNullOrEmpty(String param, String value) {
            putUrlEncodedIfTrue(param, value, !StringUtils.isNullOrEmpty(value));
        }

        /* access modifiers changed from: package-private */
        public void putUrlEncodedIfTrue(String param, String value, boolean check) {
            if (check) {
                putUrlEncoded(param, value);
            }
        }

        /* access modifiers changed from: package-private */
        public String get(String name) {
            if (!StringUtils.isNullOrWhiteSpace(name)) {
                return this.params.get(name);
            }
            throw new IllegalArgumentException("The name must not be null or empty string.");
        }

        /* access modifiers changed from: package-private */
        public void putUrlEncoded(String name, String value) {
            if (StringUtils.isNullOrWhiteSpace(name)) {
                throw new IllegalArgumentException("The name must not be null or empty string.");
            } else if (value == null) {
                this.params.remove(name);
            } else {
                this.params.put(name, value);
            }
        }

        /* access modifiers changed from: package-private */
        public String putUnencoded(String name, String value) {
            WebUtils2 webUtils = new WebUtils2();
            String encodedName = webUtils.getURLEncodedString(name);
            putUrlEncoded(encodedName, webUtils.getURLEncodedString(value));
            return encodedName;
        }

        /* access modifiers changed from: package-private */
        public void append(StringBuilder builder) {
            if (size() != 0 || !StringUtils.isNullOrEmpty(this.rawAppendage)) {
                builder.append("?");
                boolean first = true;
                for (Map.Entry<String, String> param : this.params.entrySet()) {
                    if (first) {
                        first = false;
                    } else {
                        builder.append("&");
                    }
                    builder.append(param.getKey());
                    builder.append("=");
                    builder.append(param.getValue());
                }
                if (this.rawAppendage != null && !this.rawAppendage.equals("")) {
                    if (size() != 0) {
                        builder.append("&");
                    }
                    builder.append(this.rawAppendage);
                }
            }
        }
    }

    class WebRequestInputStream extends InputStream {
        private final InputStream decoratedStream;

        public WebRequestInputStream(InputStream decoratedStream2) {
            this.decoratedStream = decoratedStream2;
        }

        public int read() throws IOException {
            return this.decoratedStream.read();
        }

        public void close() throws IOException {
            this.decoratedStream.close();
            if (WebRequest.this.disconnectEnabled) {
                WebRequest.this.closeConnection();
            }
        }
    }
}
