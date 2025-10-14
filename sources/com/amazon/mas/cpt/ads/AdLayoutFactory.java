package com.amazon.mas.cpt.ads;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdSize;

public class AdLayoutFactory implements AdFactory {
    public Ad createAd(AdAttributes adAttributes) {
        AdSize adSize;
        switch (adAttributes.getPlacement().getAdFit()) {
            case FIT_AD_SIZE:
                adSize = AdSize.SIZE_AUTO_NO_SCALE;
                break;
            default:
                adSize = AdSize.SIZE_AUTO;
                break;
        }
        switch (adAttributes.getPlacement().getHorizontalAlign()) {
            case LEFT:
                adSize = adSize.newGravity(3);
                break;
            case RIGHT:
                adSize = adSize.newGravity(5);
                break;
        }
        return new AdLayout(adAttributes.getActivity(), adSize);
    }
}
