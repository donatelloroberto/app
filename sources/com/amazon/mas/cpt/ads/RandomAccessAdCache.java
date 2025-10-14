package com.amazon.mas.cpt.ads;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdTargetingOptions;
import java.util.HashSet;

public class RandomAccessAdCache extends AdCache {
    private final HashSet<Long> createdAdIdentifiers = new HashSet<>();
    private final HashSet<Long> loadedAdIdentifiers = new HashSet<>();

    public RandomAccessAdCache(AdFactory adFactory) {
        super(adFactory);
    }

    /* access modifiers changed from: package-private */
    public void insertAd(AdWrapper adWrapper) {
        super.insertAd(adWrapper);
        this.createdAdIdentifiers.add(Long.valueOf(adWrapper.getId()));
    }

    public boolean adLoaded(AdWrapper adWrapper) {
        if (!super.adLoaded(adWrapper)) {
            return false;
        }
        this.loadedAdIdentifiers.add(Long.valueOf(adWrapper.getId()));
        return true;
    }

    public boolean adFailedToLoad(AdWrapper adWrapper) {
        if (!super.adFailedToLoad(adWrapper)) {
            return false;
        }
        this.createdAdIdentifiers.add(Long.valueOf(adWrapper.getId()));
        return true;
    }

    public boolean adClosed(AdWrapper adWrapper) {
        if (!super.adClosed(adWrapper)) {
            return false;
        }
        this.createdAdIdentifiers.add(Long.valueOf(adWrapper.getId()));
        return true;
    }

    public void adShown(AdWrapper adWrapper) {
        if (this.loadedAdIdentifiers.remove(Long.valueOf(adWrapper.getId()))) {
            super.adShown(adWrapper);
        }
    }

    public boolean loadAd(AdTargetingOptions adTargetingOptions, AdWrapper adWrapper) {
        boolean adIsShowing = isAdShowing(adWrapper);
        if (this.createdAdIdentifiers.remove(Long.valueOf(adWrapper.getId())) || adIsShowing) {
            if (adWrapper.getSdkAd().loadAd(adTargetingOptions)) {
                if (adIsShowing) {
                    adClosed(adWrapper);
                }
                adLoading(adWrapper);
                return true;
            } else if (!adIsShowing) {
                this.createdAdIdentifiers.add(Long.valueOf(adWrapper.getId()));
            }
        }
        return false;
    }

    public AdWrapper getAdForShowing(Ad ad) {
        AdWrapper adWrapper = getAdWrapper(ad);
        if (adWrapper == null || !this.loadedAdIdentifiers.contains(Long.valueOf(adWrapper.getId()))) {
            return null;
        }
        return adWrapper;
    }

    public boolean isAnAdReadyToLoad() {
        return !this.createdAdIdentifiers.isEmpty();
    }

    public boolean isAnAdReadyToShow() {
        return !this.loadedAdIdentifiers.isEmpty();
    }
}
