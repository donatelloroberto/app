package com.amazon.device.ads;

import com.amazon.device.ads.WebRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

class HttpClientWebRequest extends WebRequest {
    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
    private static final String LOGTAG = HttpClientWebRequest.class.getSimpleName();
    private HttpClient client;

    HttpClientWebRequest() {
    }

    /* access modifiers changed from: protected */
    public WebRequest.WebResponse doHttpNetworkCall(URL url) throws WebRequest.WebRequestException {
        HttpRequestBase httpRequest = createHttpRequest(url);
        HttpParams httpParams = createHttpParams();
        if (this.client != null) {
            closeConnection();
        }
        this.client = new DefaultHttpClient(httpParams);
        try {
            return parseResponse(this.client.execute(httpRequest));
        } catch (ClientProtocolException e) {
            getLogger().e("Invalid client protocol: %s", e.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.INVALID_CLIENT_PROTOCOL, "Invalid client protocol", e);
        } catch (IOException e2) {
            getLogger().e("IOException during client execution: %s", e2.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "IOException during client execution", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void closeConnection() {
        if (this.client != null) {
            this.client.getConnectionManager().closeIdleConnections(0, TimeUnit.MILLISECONDS);
            this.client.getConnectionManager().closeExpiredConnections();
            this.client = null;
        }
    }

    /* access modifiers changed from: protected */
    public HttpRequestBase createHttpRequest(URL url) throws WebRequest.WebRequestException {
        HttpPost httpRequest = null;
        try {
            URI uri = createURI(url);
            switch (getHttpMethod()) {
                case GET:
                    httpRequest = new HttpGet(uri);
                    break;
                case POST:
                    httpRequest = new HttpPost(uri);
                    prepareRequestBody(httpRequest);
                    break;
            }
            for (Map.Entry<String, String> header : this.headers.entrySet()) {
                if (header.getValue() != null && !header.getValue().equals("")) {
                    httpRequest.addHeader(header.getKey(), header.getValue());
                }
            }
            logUrl(uri.toString());
            if (this.logRequestBodyEnabled && getRequestBody() != null) {
                getLogger().d("Request Body: %s", getRequestBody());
            }
            return httpRequest;
        } catch (URISyntaxException e) {
            getLogger().e("Problem with URI syntax: %s", e.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.MALFORMED_URL, "Problem with URI syntax", e);
        }
    }

    private void prepareRequestBody(HttpPost httpPost) throws WebRequest.WebRequestException {
        String charset = this.charset;
        if (charset == null) {
            charset = WebRequest.CHARSET_UTF_8;
        }
        String contentType = this.contentType;
        if (contentType == null) {
            contentType = WebRequest.CONTENT_TYPE_PLAIN_TEXT;
        }
        if (this.requestBody != null) {
            prepareStringRequestBody(httpPost, contentType, charset);
        } else {
            prepareFormRequestBody(httpPost, charset);
        }
    }

    private void prepareStringRequestBody(HttpPost requestBase, String contentType, String charset) throws WebRequest.WebRequestException {
        try {
            StringEntity entity = new StringEntity(this.requestBody, charset);
            entity.setContentType(contentType);
            requestBase.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            getLogger().e("Unsupported character encoding used while creating the request. ", e.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.UNSUPPORTED_ENCODING, "Unsupported character encoding used while creating the request.", e);
        }
    }

    private void prepareFormRequestBody(HttpPost httpPost, String charset) throws WebRequest.WebRequestException {
        List<NameValuePair> postParams = new ArrayList<>();
        for (Map.Entry<String, String> param : this.postParameters.entrySet()) {
            postParams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(postParams, WebRequest.CHARSET_UTF_8));
        } catch (UnsupportedEncodingException e) {
            getLogger().e("Unsupported character encoding used while creating the request: %s", e.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.UNSUPPORTED_ENCODING, "Unsupported character encoding used while creating the request", e);
        }
    }

    /* access modifiers changed from: protected */
    public HttpParams createHttpParams() {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, getTimeout());
        HttpConnectionParams.setSoTimeout(httpParams, getTimeout());
        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
        return httpParams;
    }

    /* access modifiers changed from: protected */
    public WebRequest.WebResponse parseResponse(HttpResponse response) throws WebRequest.WebRequestException {
        HttpEntity entity;
        WebRequest.WebResponse webResponse = new WebRequest.WebResponse();
        webResponse.setHttpStatusCode(response.getStatusLine().getStatusCode());
        webResponse.setHttpStatus(response.getStatusLine().getReasonPhrase());
        if (!(webResponse.getHttpStatusCode() != 200 || (entity = response.getEntity()) == null || entity.getContentLength() == 0)) {
            try {
                webResponse.setInputStream(entity.getContent());
            } catch (IOException e) {
                getLogger().e("IOException while reading the input stream from response: %s", e.getMessage());
                throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "IOException while reading the input stream from response", e);
            }
        }
        return webResponse;
    }

    /* access modifiers changed from: protected */
    public String getSubLogTag() {
        return LOGTAG;
    }
}
