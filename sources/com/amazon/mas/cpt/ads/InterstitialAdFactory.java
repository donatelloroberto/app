package com.amazon.mas.cpt.ads;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.InterstitialAd;

public class InterstitialAdFactory implements AdFactory {
    public Ad createAd(AdAttributes adAttributes) {
        return new InterstitialAd(adAttributes.getActivity());
    }
}
