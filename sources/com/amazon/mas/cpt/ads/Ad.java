package com.amazon.mas.cpt.ads;

import com.amazon.cptplugins.SdkEvent;

public class Ad implements Comparable<Ad>, SdkEvent {
    private AdType adType;
    private Long identifier;

    public AdType getAdType() {
        return this.adType;
    }

    public void setAdType(AdType adType2) {
        this.adType = adType2;
    }

    public Ad withAdType(AdType adType2) {
        setAdType(adType2);
        return this;
    }

    public Long getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(Long identifier2) {
        this.identifier = identifier2;
    }

    public Ad withIdentifier(Long identifier2) {
        setIdentifier(identifier2);
        return this;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * ((getAdType() == null ? 0 : getAdType().hashCode()) + 527);
        if (getIdentifier() != null) {
            i = getIdentifier().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Ad)) {
            return false;
        }
        if (compareTo((Ad) o) != 0) {
            return false;
        }
        return true;
    }

    public int compareTo(Ad o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        Ad t = o;
        AdType o1 = getAdType();
        AdType o2 = t.getAdType();
        if (o1 != o2) {
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            if (o1 instanceof Comparable) {
                int ret = o1.compareTo(o2);
                if (ret != 0) {
                    return ret;
                }
            } else if (!o1.equals(o2)) {
                int hc1 = o1.hashCode();
                int hc2 = o2.hashCode();
                if (hc1 < hc2) {
                    return -1;
                }
                if (hc1 > hc2) {
                    return 1;
                }
            }
        }
        Long o12 = getIdentifier();
        Long o22 = t.getIdentifier();
        if (o12 != o22) {
            if (o12 == null) {
                return -1;
            }
            if (o22 == null) {
                return 1;
            }
            if (o12 instanceof Comparable) {
                int ret2 = o12.compareTo(o22);
                if (ret2 != 0) {
                    return ret2;
                }
            } else if (!o12.equals(o22)) {
                int hc12 = o12.hashCode();
                int hc22 = o22.hashCode();
                if (hc12 < hc22) {
                    return -1;
                }
                if (hc12 > hc22) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
