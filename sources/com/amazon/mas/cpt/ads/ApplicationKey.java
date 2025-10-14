package com.amazon.mas.cpt.ads;

import com.amazon.cptplugins.SdkEvent;

public class ApplicationKey implements Comparable<ApplicationKey>, SdkEvent {
    private String stringValue;

    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String stringValue2) {
        this.stringValue = stringValue2;
    }

    public ApplicationKey withStringValue(String stringValue2) {
        setStringValue(stringValue2);
        return this;
    }

    public int hashCode() {
        return (getStringValue() == null ? 0 : getStringValue().hashCode()) + 527;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ApplicationKey)) {
            return false;
        }
        if (compareTo((ApplicationKey) o) != 0) {
            return false;
        }
        return true;
    }

    public int compareTo(ApplicationKey o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        String o1 = getStringValue();
        String o2 = o.getStringValue();
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
