package com.amazon.mas.cpt.ads;

import com.amazon.cptplugins.SdkEvent;

public class AdShown implements Comparable<AdShown>, SdkEvent {
    private Boolean booleanValue;

    public Boolean getBooleanValue() {
        return this.booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue2) {
        this.booleanValue = booleanValue2;
    }

    public AdShown withBooleanValue(Boolean booleanValue2) {
        setBooleanValue(booleanValue2);
        return this;
    }

    public int hashCode() {
        return (getBooleanValue() == null ? 0 : getBooleanValue().hashCode()) + 527;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AdShown)) {
            return false;
        }
        if (compareTo((AdShown) o) != 0) {
            return false;
        }
        return true;
    }

    public int compareTo(AdShown o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        Boolean o1 = getBooleanValue();
        Boolean o2 = o.getBooleanValue();
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
        return 0;
    }
}
