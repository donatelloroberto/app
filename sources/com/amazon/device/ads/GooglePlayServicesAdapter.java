package com.amazon.device.ads;

import com.amazon.device.ads.GooglePlayServices;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

class GooglePlayServicesAdapter {
    private static final String LOGTAG = GooglePlayServicesAdapter.class.getSimpleName();
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    GooglePlayServicesAdapter() {
    }

    public GooglePlayServices.AdvertisingInfo getAdvertisingIdentifierInfo() {
        try {
            AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(MobileAdsInfoStore.getInstance().getApplicationContext());
            this.logger.v("The Google Play Services Advertising Identifier was successfully retrieved.");
            String advertisingId = info.getId();
            return new GooglePlayServices.AdvertisingInfo().setAdvertisingIdentifier(advertisingId).setLimitAdTrackingEnabled(info.isLimitAdTrackingEnabled());
        } catch (IllegalStateException e) {
            this.logger.e("The Google Play Services Advertising Id API was called from a non-background thread.");
            return new GooglePlayServices.AdvertisingInfo();
        } catch (IOException e2) {
            this.logger.e("Retrieving the Google Play Services Advertising Identifier caused an IOException.");
            return new GooglePlayServices.AdvertisingInfo();
        } catch (GooglePlayServicesNotAvailableException e3) {
            this.logger.v("Retrieving the Google Play Services Advertising Identifier caused a GooglePlayServicesNotAvailableException.");
            return GooglePlayServices.AdvertisingInfo.createNotAvailable();
        } catch (GooglePlayServicesRepairableException e4) {
            this.logger.v("Retrieving the Google Play Services Advertising Identifier caused a GooglePlayServicesRepairableException.");
            return new GooglePlayServices.AdvertisingInfo();
        }
    }
}
