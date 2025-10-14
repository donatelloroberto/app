package com.amazon.mas.cpt.ads;

import com.amazon.cptplugins.SdkEvent;

public class AdPair implements Comparable<AdPair>, SdkEvent {
    private Ad adOne;
    private Ad adTwo;

    public Ad getAdOne() {
        return this.adOne;
    }

    public void setAdOne(Ad adOne2) {
        this.adOne = adOne2;
    }

    public AdPair withAdOne(Ad adOne2) {
        setAdOne(adOne2);
        return this;
    }

    public Ad getAdTwo() {
        return this.adTwo;
    }

    public void setAdTwo(Ad adTwo2) {
        this.adTwo = adTwo2;
    }

    public AdPair withAdTwo(Ad adTwo2) {
        setAdTwo(adTwo2);
        return this;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * ((getAdOne() == null ? 0 : getAdOne().hashCode()) + 527);
        if (getAdTwo() != null) {
            i = getAdTwo().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AdPair)) {
            return false;
        }
        if (compareTo((AdPair) o) != 0) {
            return false;
        }
        return true;
    }

    public int compareTo(AdPair o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        AdPair t = o;
        Ad o1 = getAdOne();
        Ad o2 = t.getAdOne();
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
        Ad o12 = getAdTwo();
        Ad o22 = t.getAdTwo();
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
