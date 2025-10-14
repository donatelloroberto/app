package com.amazon.mas.cpt.ads;

import com.amazon.cptplugins.SdkEvent;

public class Placement implements Comparable<Placement>, SdkEvent {
    private AdFit adFit;
    private Dock dock;
    private HorizontalAlign horizontalAlign;

    public Dock getDock() {
        return this.dock;
    }

    public void setDock(Dock dock2) {
        this.dock = dock2;
    }

    public Placement withDock(Dock dock2) {
        setDock(dock2);
        return this;
    }

    public HorizontalAlign getHorizontalAlign() {
        return this.horizontalAlign;
    }

    public void setHorizontalAlign(HorizontalAlign horizontalAlign2) {
        this.horizontalAlign = horizontalAlign2;
    }

    public Placement withHorizontalAlign(HorizontalAlign horizontalAlign2) {
        setHorizontalAlign(horizontalAlign2);
        return this;
    }

    public AdFit getAdFit() {
        return this.adFit;
    }

    public void setAdFit(AdFit adFit2) {
        this.adFit = adFit2;
    }

    public Placement withAdFit(AdFit adFit2) {
        setAdFit(adFit2);
        return this;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * ((31 * ((getDock() == null ? 0 : getDock().hashCode()) + 527)) + (getHorizontalAlign() == null ? 0 : getHorizontalAlign().hashCode()));
        if (getAdFit() != null) {
            i = getAdFit().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Placement)) {
            return false;
        }
        if (compareTo((Placement) o) != 0) {
            return false;
        }
        return true;
    }

    public int compareTo(Placement o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        Placement t = o;
        Dock o1 = getDock();
        Dock o2 = t.getDock();
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
        HorizontalAlign o12 = getHorizontalAlign();
        HorizontalAlign o22 = t.getHorizontalAlign();
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
        AdFit o13 = getAdFit();
        AdFit o23 = t.getAdFit();
        if (o13 != o23) {
            if (o13 == null) {
                return -1;
            }
            if (o23 == null) {
                return 1;
            }
            if (o13 instanceof Comparable) {
                int ret3 = o13.compareTo(o23);
                if (ret3 != 0) {
                    return ret3;
                }
            } else if (!o13.equals(o23)) {
                int hc13 = o13.hashCode();
                int hc23 = o23.hashCode();
                if (hc13 < hc23) {
                    return -1;
                }
                if (hc13 > hc23) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
