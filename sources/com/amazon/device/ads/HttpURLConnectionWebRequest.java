package com.amazon.device.ads;

import com.amazon.device.ads.WebRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

class HttpURLConnectionWebRequest extends WebRequest {
    private static final String LOGTAG = HttpURLConnectionWebRequest.class.getSimpleName();
    private HttpURLConnection connection;

    HttpURLConnectionWebRequest() {
    }

    /* access modifiers changed from: protected */
    public WebRequest.WebResponse doHttpNetworkCall(URL url) throws WebRequest.WebRequestException {
        if (this.connection != null) {
            closeConnection();
        }
        try {
            this.connection = openConnection(url);
            setupRequestProperties(this.connection);
            try {
                this.connection.connect();
                return prepareResponse(this.connection);
            } catch (SocketTimeoutException e) {
                getLogger().e("Socket timed out while connecting to URL: %s", e.getMessage());
                throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_TIMEOUT, "Socket timed out while connecting to URL", e);
            } catch (Exception e2) {
                getLogger().e("Problem while connecting to URL: %s", e2.getMessage());
                throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Probem while connecting to URL", e2);
            }
        } catch (IOException e3) {
            getLogger().e("Problem while opening the URL connection: %s", e3.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while opening the URL connection", e3);
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    /* access modifiers changed from: protected */
    public void closeConnection() {
        if (this.connection != null) {
            this.connection.disconnect();
            this.connection = null;
        }
    }

    /* access modifiers changed from: protected */
    public void setupRequestProperties(HttpURLConnection connection2) throws WebRequest.WebRequestException {
        try {
            connection2.setRequestMethod(getHttpMethod().name());
            for (Map.Entry<String, String> header : this.headers.entrySet()) {
                if (header.getValue() != null && !header.getValue().equals("")) {
                    connection2.setRequestProperty(header.getKey(), header.getValue());
                }
            }
            connection2.setConnectTimeout(getTimeout());
            connection2.setReadTimeout(getTimeout());
            logUrl(connection2.getURL().toString());
            switch (getHttpMethod()) {
                case GET:
                    connection2.setDoOutput(false);
                    return;
                case POST:
                    connection2.setDoOutput(true);
                    writePostBody(connection2);
                    return;
                default:
                    return;
            }
        } catch (ProtocolException e) {
            getLogger().e("Invalid client protocol: %s", e.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.INVALID_CLIENT_PROTOCOL, "Invalid client protocol", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d2 A[SYNTHETIC, Splitter:B:34:0x00d2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writePostBody(java.net.HttpURLConnection r14) throws com.amazon.device.ads.WebRequest.WebRequestException {
        /*
            r13 = this;
            r12 = 1
            r11 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r13.requestBody
            if (r6 == 0) goto L_0x0044
            java.lang.String r6 = r13.requestBody
            r5.append(r6)
        L_0x0010:
            boolean r6 = r13.logRequestBodyEnabled
            if (r6 == 0) goto L_0x002b
            java.lang.String r6 = r13.getRequestBody()
            if (r6 == 0) goto L_0x002b
            com.amazon.device.ads.MobileAdsLogger r6 = r13.getLogger()
            java.lang.String r7 = "Request Body: %s"
            java.lang.Object[] r8 = new java.lang.Object[r12]
            java.lang.String r9 = r13.getRequestBody()
            r8[r11] = r9
            r6.d(r7, r8)
        L_0x002b:
            r2 = 0
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x00b1 }
            java.io.OutputStream r6 = r14.getOutputStream()     // Catch:{ IOException -> 0x00b1 }
            java.lang.String r7 = "UTF-8"
            r3.<init>(r6, r7)     // Catch:{ IOException -> 0x00b1 }
            java.lang.String r6 = r5.toString()     // Catch:{ IOException -> 0x00f5, all -> 0x00f2 }
            r3.write(r6)     // Catch:{ IOException -> 0x00f5, all -> 0x00f2 }
            if (r3 == 0) goto L_0x0043
            r3.close()     // Catch:{ IOException -> 0x0095 }
        L_0x0043:
            return
        L_0x0044:
            java.util.HashMap r6 = r13.postParameters
            if (r6 == 0) goto L_0x0010
            java.util.HashMap r6 = r13.postParameters
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x0010
            java.util.HashMap r6 = r13.postParameters
            java.util.Set r6 = r6.entrySet()
            java.util.Iterator r1 = r6.iterator()
        L_0x005a:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x008a
            java.lang.Object r4 = r1.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r6 = r4.getKey()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.StringBuilder r6 = r5.append(r6)
            java.lang.String r7 = "="
            java.lang.StringBuilder r7 = r6.append(r7)
            java.lang.Object r6 = r4.getValue()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r6 = com.amazon.device.ads.WebUtils.getURLEncodedString(r6)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r7 = "&"
            r6.append(r7)
            goto L_0x005a
        L_0x008a:
            java.lang.String r6 = "&"
            int r6 = r5.lastIndexOf(r6)
            r5.deleteCharAt(r6)
            goto L_0x0010
        L_0x0095:
            r0 = move-exception
            com.amazon.device.ads.MobileAdsLogger r6 = r13.getLogger()
            java.lang.String r7 = "Problem while closing output stream writer for request body: %s"
            java.lang.Object[] r8 = new java.lang.Object[r12]
            java.lang.String r9 = r0.getMessage()
            r8[r11] = r9
            r6.e(r7, r8)
            com.amazon.device.ads.WebRequest$WebRequestException r6 = new com.amazon.device.ads.WebRequest$WebRequestException
            com.amazon.device.ads.WebRequest$WebRequestStatus r7 = com.amazon.device.ads.WebRequest.WebRequestStatus.NETWORK_FAILURE
            java.lang.String r8 = "Problem while closing output stream writer for request body"
            r6.<init>(r7, r8, r0)
            throw r6
        L_0x00b1:
            r0 = move-exception
        L_0x00b2:
            com.amazon.device.ads.MobileAdsLogger r6 = r13.getLogger()     // Catch:{ all -> 0x00cf }
            java.lang.String r7 = "Problem while creating output steam for request body: %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x00cf }
            r9 = 0
            java.lang.String r10 = r0.getMessage()     // Catch:{ all -> 0x00cf }
            r8[r9] = r10     // Catch:{ all -> 0x00cf }
            r6.e(r7, r8)     // Catch:{ all -> 0x00cf }
            com.amazon.device.ads.WebRequest$WebRequestException r6 = new com.amazon.device.ads.WebRequest$WebRequestException     // Catch:{ all -> 0x00cf }
            com.amazon.device.ads.WebRequest$WebRequestStatus r7 = com.amazon.device.ads.WebRequest.WebRequestStatus.NETWORK_FAILURE     // Catch:{ all -> 0x00cf }
            java.lang.String r8 = "Problem while creating output steam for request body"
            r6.<init>(r7, r8, r0)     // Catch:{ all -> 0x00cf }
            throw r6     // Catch:{ all -> 0x00cf }
        L_0x00cf:
            r6 = move-exception
        L_0x00d0:
            if (r2 == 0) goto L_0x00d5
            r2.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00d5:
            throw r6
        L_0x00d6:
            r0 = move-exception
            com.amazon.device.ads.MobileAdsLogger r6 = r13.getLogger()
            java.lang.String r7 = "Problem while closing output stream writer for request body: %s"
            java.lang.Object[] r8 = new java.lang.Object[r12]
            java.lang.String r9 = r0.getMessage()
            r8[r11] = r9
            r6.e(r7, r8)
            com.amazon.device.ads.WebRequest$WebRequestException r6 = new com.amazon.device.ads.WebRequest$WebRequestException
            com.amazon.device.ads.WebRequest$WebRequestStatus r7 = com.amazon.device.ads.WebRequest.WebRequestStatus.NETWORK_FAILURE
            java.lang.String r8 = "Problem while closing output stream writer for request body"
            r6.<init>(r7, r8, r0)
            throw r6
        L_0x00f2:
            r6 = move-exception
            r2 = r3
            goto L_0x00d0
        L_0x00f5:
            r0 = move-exception
            r2 = r3
            goto L_0x00b2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.HttpURLConnectionWebRequest.writePostBody(java.net.HttpURLConnection):void");
    }

    /* access modifiers changed from: protected */
    public WebRequest.WebResponse prepareResponse(HttpURLConnection connection2) throws WebRequest.WebRequestException {
        WebRequest.WebResponse webResponse = new WebRequest.WebResponse();
        try {
            webResponse.setHttpStatusCode(connection2.getResponseCode());
            webResponse.setHttpStatus(connection2.getResponseMessage());
            if (webResponse.getHttpStatusCode() == 200) {
                try {
                    webResponse.setInputStream(connection2.getInputStream());
                } catch (IOException e) {
                    getLogger().e("IOException while reading the input stream from response: %s", e.getMessage());
                    throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "IOException while reading the input stream from response", e);
                }
            }
            return webResponse;
        } catch (SocketTimeoutException e2) {
            getLogger().e("Socket Timeout while getting the response status code: %s", e2.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_TIMEOUT, "Socket Timeout while getting the response status code", e2);
        } catch (IOException e3) {
            getLogger().e("IOException while getting the response status code: %s", e3.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "IOException while getting the response status code", e3);
        } catch (IndexOutOfBoundsException e4) {
            getLogger().e("IndexOutOfBoundsException while getting the response status code: %s", e4.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.MALFORMED_URL, "IndexOutOfBoundsException while getting the response status code", e4);
        }
    }

    /* access modifiers changed from: protected */
    public String getSubLogTag() {
        return LOGTAG;
    }
}
