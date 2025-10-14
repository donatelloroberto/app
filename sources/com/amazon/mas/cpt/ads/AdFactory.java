package com.amazon.mas.cpt.ads;

import com.amazon.device.ads.Ad;

public interface AdFactory {
    Ad createAd(AdAttributes adAttributes);
}
