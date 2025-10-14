package com.amazon.mas.cpt.ads;

import android.app.Activity;
import com.amazon.device.ads.Ad;
import java.util.HashMap;
import java.util.HashSet;

public abstract class AdCache {
    private final AdFactory adFactory;
    private final HashMap<Ad, AdWrapper> adToAdWrapper = new HashMap<>();
    private final HashMap<Long, AdWrapper> identifierToAd = new HashMap<>();
    private final HashSet<Long> loadingAdIdentifiers = new HashSet<>();
    private final HashSet<Long> showingAdIdentifiers = new HashSet<>();

    public abstract boolean isAnAdReadyToLoad();

    public abstract boolean isAnAdReadyToShow();

    public AdCache(AdFactory adFactory2) {
        this.adFactory = adFactory2;
    }

    public AdWrapper createAd(Activity activity, Placement placement) {
        AdWrapper adWrapper = new AdWrapper(this.adFactory.createAd(new AdAttributes(activity).withPlacement(placement))).withPlacement(placement);
        insertAd(adWrapper);
        return adWrapper;
    }

    /* access modifiers changed from: package-private */
    public void insertAd(AdWrapper adWrapper) {
        this.adToAdWrapper.put(adWrapper.getSdkAd(), adWrapper);
        this.identifierToAd.put(Long.valueOf(adWrapper.getId()), adWrapper);
    }

    /* access modifiers changed from: package-private */
    public void adLoading(AdWrapper adWrapper) {
        this.loadingAdIdentifiers.add(Long.valueOf(adWrapper.getId()));
    }

    /* access modifiers changed from: package-private */
    public void adShowing(AdWrapper adWrapper) {
        this.showingAdIdentifiers.add(Long.valueOf(adWrapper.getId()));
    }

    public boolean adLoaded(AdWrapper adWrapper) {
        return adFinishedLoading(adWrapper);
    }

    public boolean adFailedToLoad(AdWrapper adWrapper) {
        return adFinishedLoading(adWrapper);
    }

    private boolean adFinishedLoading(AdWrapper adWrapper) {
        return this.loadingAdIdentifiers.remove(Long.valueOf(adWrapper.getId()));
    }

    public boolean adExpired(AdWrapper adWrapper) {
        return false;
    }

    public void adShown(AdWrapper adWrapper) {
        this.showingAdIdentifiers.add(Long.valueOf(adWrapper.getId()));
    }

    public boolean adClosed(AdWrapper adWrapper) {
        return this.showingAdIdentifiers.remove(Long.valueOf(adWrapper.getId()));
    }

    public AdWrapper getAdWrapper(Ad sdkAd) {
        return this.adToAdWrapper.get(sdkAd);
    }

    public AdWrapper getAdWrapper(Long identifier) {
        return this.identifierToAd.get(identifier);
    }

    public boolean isAdShowing(AdWrapper adWrapper) {
        return this.showingAdIdentifiers.contains(Long.valueOf(adWrapper.getId()));
    }
}
