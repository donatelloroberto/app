package com.amazon.device.ads;

import android.os.AsyncTask;
import com.amazon.device.ads.WebRequest;

/* compiled from: AdMetricsTasks */
class AdMetricsSubmitAaxTask extends AsyncTask<WebRequest, Void, Void> {
    private static final String LOGTAG = AdMetricsSubmitAaxTask.class.getSimpleName();
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    AdMetricsSubmitAaxTask() {
    }

    public Void doInBackground(WebRequest... webRequests) {
        for (WebRequest webRequest : webRequests) {
            try {
                webRequest.makeCall();
            } catch (WebRequest.WebRequestException e) {
                switch (e.getStatus()) {
                    case INVALID_CLIENT_PROTOCOL:
                        this.logger.e("Unable to submit metrics for ad due to an Invalid Client Protocol, msg: %s", e.getMessage());
                        continue;
                    case NETWORK_FAILURE:
                        this.logger.e("Unable to submit metrics for ad due to Network Failure, msg: %s", e.getMessage());
                        continue;
                    case MALFORMED_URL:
                        this.logger.e("Unable to submit metrics for ad due to a Malformed Pixel URL, msg: %s", e.getMessage());
                        break;
                    case UNSUPPORTED_ENCODING:
                        break;
                }
                this.logger.e("Unable to submit metrics for ad because of unsupported character encoding, msg: %s", e.getMessage());
            }
        }
        return null;
    }
}
