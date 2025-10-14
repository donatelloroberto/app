package com.amazon.device.ads;

import java.util.Iterator;
import java.util.Set;

class AdData implements Iterable<AAXCreative> {
    private int adHeight;
    private int adWidth;
    private String creative;
    private Set<AAXCreative> creativeTypes;
    private long expirationTimeMs = -1;
    private boolean fetched;
    private String impPixelUrl;
    private String instrPixelUrl;
    private AdProperties properties;

    /* access modifiers changed from: protected */
    public String getCreative() {
        return this.creative;
    }

    /* access modifiers changed from: protected */
    public void setCreative(String creative2) {
        this.creative = creative2;
    }

    /* access modifiers changed from: protected */
    public AdProperties getProperties() {
        return this.properties;
    }

    /* access modifiers changed from: protected */
    public void setProperties(AdProperties properties2) {
        this.properties = properties2;
    }

    /* access modifiers changed from: protected */
    public Set<AAXCreative> getCreativeTypes() {
        return this.creativeTypes;
    }

    /* access modifiers changed from: protected */
    public void setCreativeTypes(Set<AAXCreative> creativeTypes2) {
        this.creativeTypes = creativeTypes2;
    }

    /* access modifiers changed from: protected */
    public String getInstrumentationPixelUrl() {
        return this.instrPixelUrl;
    }

    /* access modifiers changed from: protected */
    public void setInstrumentationPixelUrl(String instrPixelUrl2) {
        this.instrPixelUrl = instrPixelUrl2;
    }

    /* access modifiers changed from: protected */
    public String getImpressionPixelUrl() {
        return this.impPixelUrl;
    }

    /* access modifiers changed from: protected */
    public void setImpressionPixelUrl(String impPixelUrl2) {
        this.impPixelUrl = impPixelUrl2;
    }

    public boolean getIsFetched() {
        return this.fetched;
    }

    public void setFetched(boolean fetched2) {
        this.fetched = fetched2;
    }

    /* access modifiers changed from: protected */
    public void setHeight(int height) {
        this.adHeight = height;
    }

    public int getHeight() {
        return this.adHeight;
    }

    /* access modifiers changed from: protected */
    public void setWidth(int width) {
        this.adWidth = width;
    }

    public int getWidth() {
        return this.adWidth;
    }

    /* access modifiers changed from: protected */
    public void setExpirationTimeMillis(long expirationTimeMs2) {
        this.expirationTimeMs = expirationTimeMs2;
    }

    public boolean isExpired() {
        if (this.expirationTimeMs >= 0 && System.currentTimeMillis() > this.expirationTimeMs) {
            return true;
        }
        return false;
    }

    public long getTimeToExpire() {
        return this.expirationTimeMs - System.currentTimeMillis();
    }

    public Iterator<AAXCreative> iterator() {
        return this.creativeTypes.iterator();
    }
}
