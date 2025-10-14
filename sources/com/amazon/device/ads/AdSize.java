package com.amazon.device.ads;

import android.support.v7.widget.helper.ItemTouchHelper;

public class AdSize {
    private static final String LOGTAG = AdSize.class.getSimpleName();
    public static final AdSize SIZE_1024x50 = new AdSize(1024, 50);
    public static final AdSize SIZE_300x250 = new AdSize(300, (int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    public static final AdSize SIZE_300x50 = new AdSize(300, 50);
    public static final AdSize SIZE_320x50 = new AdSize(320, 50);
    public static final AdSize SIZE_600x90 = new AdSize(600, 90);
    public static final AdSize SIZE_728x90 = new AdSize(728, 90);
    public static final AdSize SIZE_AUTO = new AdSize(SizeType.AUTO);
    public static final AdSize SIZE_AUTO_NO_SCALE = new AdSize(SizeType.AUTO, Scaling.NO_UPSCALE);
    static final AdSize SIZE_INTERSTITIAL = new AdSize(SizeType.INTERSTITIAL, Modality.MODAL);
    static final AdSize SIZE_MODELESS_INTERSTITIAL = new AdSize(SizeType.INTERSTITIAL);
    private int gravity;
    private int height;
    private final MobileAdsLogger logger;
    private Modality modality;
    private Scaling scaling;
    private SizeType type;
    private int width;

    private enum Modality {
        MODAL,
        MODELESS
    }

    private enum Scaling {
        CAN_UPSCALE,
        NO_UPSCALE
    }

    enum SizeType {
        EXPLICIT,
        AUTO,
        INTERSTITIAL
    }

    public AdSize(int width2, int height2) {
        this.gravity = 17;
        this.type = SizeType.EXPLICIT;
        this.modality = Modality.MODELESS;
        this.scaling = Scaling.CAN_UPSCALE;
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        initialize(width2, height2);
    }

    AdSize(String width2, String height2) {
        this.gravity = 17;
        this.type = SizeType.EXPLICIT;
        this.modality = Modality.MODELESS;
        this.scaling = Scaling.CAN_UPSCALE;
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        initialize(NumberUtils.parseInt(width2, 0), NumberUtils.parseInt(height2, 0));
    }

    AdSize(SizeType type2) {
        this.gravity = 17;
        this.type = SizeType.EXPLICIT;
        this.modality = Modality.MODELESS;
        this.scaling = Scaling.CAN_UPSCALE;
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.type = type2;
    }

    AdSize(SizeType type2, Modality modality2) {
        this(type2);
        this.modality = modality2;
    }

    AdSize(SizeType type2, Scaling scaling2) {
        this(type2);
        this.scaling = scaling2;
    }

    private AdSize deepClone() {
        AdSize adSize = new AdSize(this.type);
        adSize.width = this.width;
        adSize.height = this.height;
        adSize.gravity = this.gravity;
        adSize.modality = this.modality;
        adSize.scaling = this.scaling;
        return adSize;
    }

    private void initialize(int width2, int height2) {
        if (width2 <= 0 || height2 <= 0) {
            this.logger.e("The width and height must be positive integers.");
            throw new IllegalArgumentException("The width and height must be positive integers.");
        }
        this.width = width2;
        this.height = height2;
        this.type = SizeType.EXPLICIT;
    }

    public AdSize newGravity(int gravity2) {
        AdSize adSize = deepClone();
        adSize.gravity = gravity2;
        return adSize;
    }

    public int getGravity() {
        return this.gravity;
    }

    public String toString() {
        switch (this.type) {
            case EXPLICIT:
                return getAsSizeString(this.width, this.height);
            case AUTO:
                return "auto";
            case INTERSTITIAL:
                return "interstitial";
            default:
                return null;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof AdSize) {
            AdSize other = (AdSize) obj;
            if (this.type.equals(other.type)) {
                if (!this.type.equals(SizeType.EXPLICIT)) {
                    return true;
                }
                if (this.width == other.width && this.height == other.height) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isAuto() {
        return this.type == SizeType.AUTO;
    }

    /* access modifiers changed from: package-private */
    public boolean isModal() {
        return Modality.MODAL.equals(this.modality);
    }

    /* access modifiers changed from: package-private */
    public SizeType getSizeType() {
        return this.type;
    }

    public boolean canUpscale() {
        return Scaling.CAN_UPSCALE.equals(this.scaling);
    }

    static String getAsSizeString(int w, int h) {
        return Integer.toString(w) + "x" + Integer.toString(h);
    }
}
