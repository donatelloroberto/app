package com.amazon.device.ads;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

class LayoutFactory {

    enum LayoutType {
        RELATIVE_LAYOUT,
        LINEAR_LAYOUT,
        FRAME_LAYOUT
    }

    LayoutFactory() {
    }

    public ViewGroup createLayout(Context context, LayoutType layoutType, String contentDescription) {
        ViewGroup layout;
        switch (layoutType) {
            case RELATIVE_LAYOUT:
                layout = new RelativeLayout(context);
                break;
            case FRAME_LAYOUT:
                layout = new FrameLayout(context);
                break;
            default:
                layout = new LinearLayout(context);
                break;
        }
        layout.setContentDescription(contentDescription);
        return layout;
    }
}
