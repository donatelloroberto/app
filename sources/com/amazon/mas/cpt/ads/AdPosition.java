package com.amazon.mas.cpt.ads;

import com.amazon.cptplugins.SdkEvent;

public class AdPosition implements Comparable<AdPosition>, SdkEvent {
    private Ad ad;
    private Integer height;
    private Integer width;
    private Integer xCoordinate;
    private Integer yCoordinate;

    public Ad getAd() {
        return this.ad;
    }

    public void setAd(Ad ad2) {
        this.ad = ad2;
    }

    public AdPosition withAd(Ad ad2) {
        setAd(ad2);
        return this;
    }

    public Integer getXCoordinate() {
        return this.xCoordinate;
    }

    public void setXCoordinate(Integer xCoordinate2) {
        this.xCoordinate = xCoordinate2;
    }

    public AdPosition withXCoordinate(Integer xCoordinate2) {
        setXCoordinate(xCoordinate2);
        return this;
    }

    public Integer getYCoordinate() {
        return this.yCoordinate;
    }

    public void setYCoordinate(Integer yCoordinate2) {
        this.yCoordinate = yCoordinate2;
    }

    public AdPosition withYCoordinate(Integer yCoordinate2) {
        setYCoordinate(yCoordinate2);
        return this;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width2) {
        this.width = width2;
    }

    public AdPosition withWidth(Integer width2) {
        setWidth(width2);
        return this;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height2) {
        this.height = height2;
    }

    public AdPosition withHeight(Integer height2) {
        setHeight(height2);
        return this;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * ((31 * ((31 * ((31 * ((getAd() == null ? 0 : getAd().hashCode()) + 527)) + (getXCoordinate() == null ? 0 : getXCoordinate().hashCode()))) + (getYCoordinate() == null ? 0 : getYCoordinate().hashCode()))) + (getWidth() == null ? 0 : getWidth().hashCode()));
        if (getHeight() != null) {
            i = getHeight().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AdPosition)) {
            return false;
        }
        if (compareTo((AdPosition) o) != 0) {
            return false;
        }
        return true;
    }

    public int compareTo(AdPosition o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        AdPosition t = o;
        Ad o1 = getAd();
        Ad o2 = t.getAd();
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
        Integer o12 = getXCoordinate();
        Integer o22 = t.getXCoordinate();
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
        Integer o13 = getYCoordinate();
        Integer o23 = t.getYCoordinate();
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
        Integer o14 = getWidth();
        Integer o24 = t.getWidth();
        if (o14 != o24) {
            if (o14 == null) {
                return -1;
            }
            if (o24 == null) {
                return 1;
            }
            if (o14 instanceof Comparable) {
                int ret4 = o14.compareTo(o24);
                if (ret4 != 0) {
                    return ret4;
                }
            } else if (!o14.equals(o24)) {
                int hc14 = o14.hashCode();
                int hc24 = o24.hashCode();
                if (hc14 < hc24) {
                    return -1;
                }
                if (hc14 > hc24) {
                    return 1;
                }
            }
        }
        Integer o15 = getHeight();
        Integer o25 = t.getHeight();
        if (o15 != o25) {
            if (o15 == null) {
                return -1;
            }
            if (o25 == null) {
                return 1;
            }
            if (o15 instanceof Comparable) {
                int ret5 = o15.compareTo(o25);
                if (ret5 != 0) {
                    return ret5;
                }
            } else if (!o15.equals(o25)) {
                int hc15 = o15.hashCode();
                int hc25 = o25.hashCode();
                if (hc15 < hc25) {
                    return -1;
                }
                if (hc15 > hc25) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
