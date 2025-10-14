package com.amazon.mas.cpt.ads;

import com.amazon.device.ads.AdTargetingOptions;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueuedAdCache extends AdCache {
    private final ConcurrentLinkedQueue<Long> createdAdIdentifiersQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Long> loadedAdIdentifiersQueue = new ConcurrentLinkedQueue<>();

    public QueuedAdCache(AdFactory adFactory) {
        super(adFactory);
    }

    /* access modifiers changed from: package-private */
    public void insertAd(AdWrapper adWrapper) {
        super.insertAd(adWrapper);
        this.createdAdIdentifiersQueue.add(Long.valueOf(adWrapper.getId()));
    }

    public boolean loadNextAd(AdTargetingOptions adTargetingOptions) {
        Long identifier = this.createdAdIdentifiersQueue.poll();
        if (identifier != null) {
            AdWrapper nextAdWrapper = getAdWrapper(identifier);
            if (nextAdWrapper.getSdkAd().loadAd(adTargetingOptions)) {
                adLoading(nextAdWrapper);
                return true;
            }
            this.createdAdIdentifiersQueue.add(identifier);
        }
        return false;
    }

    public boolean adLoaded(AdWrapper adWrapper) {
        if (!super.adLoaded(adWrapper)) {
            return false;
        }
        this.loadedAdIdentifiersQueue.add(Long.valueOf(adWrapper.getId()));
        return true;
    }

    public boolean adFailedToLoad(AdWrapper adWrapper) {
        if (!super.adFailedToLoad(adWrapper)) {
            return false;
        }
        this.createdAdIdentifiersQueue.add(Long.valueOf(adWrapper.getId()));
        return true;
    }

    public boolean adExpired(AdWrapper adWrapper) {
        return loadedAdFailed(adWrapper);
    }

    public boolean adClosed(AdWrapper adWrapper) {
        if (!super.adClosed(adWrapper)) {
            return false;
        }
        this.createdAdIdentifiersQueue.add(Long.valueOf(adWrapper.getId()));
        return true;
    }

    public void adShown(AdWrapper adWrapper) {
        Long nextIdentifier = this.loadedAdIdentifiersQueue.poll();
        if (nextIdentifier != null) {
            super.adShown(getAdWrapper(nextIdentifier));
        }
    }

    public void adFailedToShow(AdWrapper adWrapper) {
        loadedAdFailed(adWrapper);
    }

    private boolean loadedAdFailed(AdWrapper adWrapper) {
        if (!this.loadedAdIdentifiersQueue.remove(Long.valueOf(adWrapper.getId()))) {
            return false;
        }
        this.createdAdIdentifiersQueue.add(Long.valueOf(adWrapper.getId()));
        return true;
    }

    public AdWrapper getNextAdForShowing() {
        Long identifier = this.loadedAdIdentifiersQueue.peek();
        if (identifier != null) {
            return getAdWrapper(identifier);
        }
        return null;
    }

    public boolean isAnAdReadyToLoad() {
        return !this.createdAdIdentifiersQueue.isEmpty();
    }

    public boolean isAnAdReadyToShow() {
        return !this.loadedAdIdentifiersQueue.isEmpty();
    }
}
