package com.amazon.device.ads;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.amazon.device.ads.Configuration;
import com.google.firebase.analytics.FirebaseAnalytics;

class AdLocation {
    private static final int ARCMINUTE_PRECISION = 6;
    private static final String LOGTAG = AdLocation.class.getSimpleName();
    private static final float MAX_DISTANCE_IN_KILOMETERS = 3.0f;
    private final Configuration configuration;
    private final Context context;
    private final MobileAdsLogger logger;

    private enum LocationAwareness {
        LOCATION_AWARENESS_NORMAL,
        LOCATION_AWARENESS_TRUNCATED,
        LOCATION_AWARENESS_DISABLED
    }

    public AdLocation(Context context2) {
        this(context2, Configuration.getInstance());
    }

    AdLocation(Context context2, Configuration configuration2) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.context = context2;
        this.configuration = configuration2;
    }

    private static double roundToArcminutes(double coordinate) {
        return ((double) Math.round(coordinate * 60.0d)) / 60.0d;
    }

    private LocationAwareness getLocationAwareness() {
        if (this.configuration.getBoolean(Configuration.ConfigOption.TRUNCATE_LAT_LON)) {
            return LocationAwareness.LOCATION_AWARENESS_TRUNCATED;
        }
        return LocationAwareness.LOCATION_AWARENESS_NORMAL;
    }

    public Location getLocation() {
        Location result;
        LocationAwareness locationAwareness = getLocationAwareness();
        if (LocationAwareness.LOCATION_AWARENESS_DISABLED.equals(locationAwareness)) {
            return null;
        }
        LocationManager lm = (LocationManager) this.context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        Location gpsLocation = null;
        try {
            gpsLocation = lm.getLastKnownLocation("gps");
        } catch (SecurityException e) {
            this.logger.d("Failed to retrieve GPS location: No permissions to access GPS");
        } catch (IllegalArgumentException e2) {
            this.logger.d("Failed to retrieve GPS location: No GPS found");
        }
        Location networkLocation = null;
        try {
            networkLocation = lm.getLastKnownLocation("network");
        } catch (SecurityException e3) {
            this.logger.d("Failed to retrieve network location: No permissions to access network location");
        } catch (IllegalArgumentException e4) {
            this.logger.d("Failed to retrieve network location: No network provider found");
        }
        if (gpsLocation == null && networkLocation == null) {
            return null;
        }
        if (gpsLocation == null || networkLocation == null) {
            if (gpsLocation != null) {
                this.logger.d("Setting lat/long using GPS, not network");
                result = gpsLocation;
            } else {
                this.logger.d("Setting lat/long using network location, not GPS");
                result = networkLocation;
            }
        } else if (gpsLocation.distanceTo(networkLocation) / 1000.0f <= MAX_DISTANCE_IN_KILOMETERS) {
            if ((gpsLocation.hasAccuracy() ? gpsLocation.getAccuracy() : Float.MAX_VALUE) < (networkLocation.hasAccuracy() ? networkLocation.getAccuracy() : Float.MAX_VALUE)) {
                this.logger.d("Setting lat/long using GPS determined by distance");
                result = gpsLocation;
            } else {
                this.logger.d("Setting lat/long using network determined by distance");
                result = networkLocation;
            }
        } else if (gpsLocation.getTime() > networkLocation.getTime()) {
            this.logger.d("Setting lat/long using GPS");
            result = gpsLocation;
        } else {
            this.logger.d("Setting lat/long using network");
            result = networkLocation;
        }
        if (LocationAwareness.LOCATION_AWARENESS_TRUNCATED.equals(locationAwareness)) {
            result.setLatitude(((double) Math.round(Math.pow(10.0d, 6.0d) * roundToArcminutes(result.getLatitude()))) / Math.pow(10.0d, 6.0d));
            result.setLongitude(((double) Math.round(Math.pow(10.0d, 6.0d) * roundToArcminutes(result.getLongitude()))) / Math.pow(10.0d, 6.0d));
        }
        Location location = result;
        return result;
    }
}
