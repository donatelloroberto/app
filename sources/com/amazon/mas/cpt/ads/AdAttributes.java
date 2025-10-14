package com.amazon.mas.cpt.ads;

import android.app.Activity;

public class AdAttributes {
    private final Activity activity;
    private Placement placement;

    public AdAttributes(Activity activity2) {
        this.activity = activity2;
    }

    public AdAttributes withPlacement(Placement placement2) {
        this.placement = placement2;
        return this;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public Placement getPlacement() {
        return this.placement;
    }
}
