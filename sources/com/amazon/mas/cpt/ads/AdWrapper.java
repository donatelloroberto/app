package com.amazon.mas.cpt.ads;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.InterstitialAd;
import java.util.concurrent.atomic.AtomicLong;

public class AdWrapper {
    private static AtomicLong nextId = new AtomicLong(0);
    private Placement placement;
    private final Ad pluginAd = makeAd(this.sdkAd);
    private final Ad sdkAd;

    public AdWrapper(Ad sdkAd2) {
        this.sdkAd = sdkAd2;
    }

    public AdWrapper withPlacement(Placement placement2) {
        this.placement = placement2;
        return this;
    }

    private Ad makeAd(Ad sdkAd2) {
        long id = nextId.incrementAndGet();
        Ad adOut = new Ad();
        adOut.setIdentifier(Long.valueOf(id));
        adOut.setAdType(getAdType(sdkAd2));
        return adOut;
    }

    private AdType getAdType(Ad sdkAd2) {
        if (sdkAd2 instanceof InterstitialAd) {
            return AdType.INTERSTITIAL;
        }
        if (sdkAd2 instanceof AdLayout) {
            return AdType.FLOATING;
        }
        return null;
    }

    public Ad getSdkAd() {
        return this.sdkAd;
    }

    public Ad getPluginAd() {
        return this.pluginAd;
    }

    public long getId() {
        return this.pluginAd.getIdentifier().longValue();
    }

    public Placement getPlacement() {
        return this.placement;
    }

    public int hashCode() {
        return (this.sdkAd == null ? 0 : this.sdkAd.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AdWrapper other = (AdWrapper) obj;
        if (this.sdkAd == null) {
            if (other.sdkAd != null) {
                return false;
            }
            return true;
        } else if (!this.sdkAd.equals(other.sdkAd)) {
            return false;
        } else {
            return true;
        }
    }
}
