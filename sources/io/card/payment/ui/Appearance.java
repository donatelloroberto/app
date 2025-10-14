package io.card.payment.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;

public class Appearance {
    public static final Drawable ACTIONBAR_BACKGROUND = new ColorDrawable(Color.parseColor("#717074"));
    public static final int BUTTON_PRIMARY_DISABLED_COLOR = Color.parseColor("#c5ddeb");
    public static final int BUTTON_PRIMARY_FOCUS_COLOR = PAL_BLUE_COLOR_OPACITY_66;
    public static final int BUTTON_PRIMARY_NORMAL_COLOR = PAL_BLUE_COLOR;
    public static final int BUTTON_PRIMARY_PRESSED_COLOR = PAY_BLUE_COLOR;
    public static final int BUTTON_SECONDARY_DISABLED_COLOR = Color.parseColor("#f5f5f5");
    public static final int BUTTON_SECONDARY_FOCUS_COLOR = Color.parseColor("#aa717074");
    public static final int BUTTON_SECONDARY_NORMAL_COLOR = Color.parseColor("#717074");
    public static final int BUTTON_SECONDARY_PRESSED_COLOR = Color.parseColor("#5a5a5d");
    public static final int[] BUTTON_STATE_DISABLED = {-16842910};
    public static final int[] BUTTON_STATE_FOCUSED = {16842908};
    public static final int[] BUTTON_STATE_NORMAL = {16842910};
    public static final int[] BUTTON_STATE_PRESSED = {16842919, 16842910};
    public static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#f5f5f5");
    public static final int PAL_BLUE_COLOR = Color.parseColor("#009CDE");
    public static final int PAL_BLUE_COLOR_OPACITY_66 = Color.parseColor("#aa009CDE");
    public static final int PAY_BLUE_COLOR = Color.parseColor("#003087");
    public static final int TEXT_COLOR_ERROR = Color.parseColor("#b32317");
    public static final int TEXT_COLOR_LABEL = TEXT_COLOR_LIGHT;
    public static final int TEXT_COLOR_LIGHT = Color.parseColor("#515151");
    public static final Typeface TYPEFACE_BUTTON = typefaceLight();

    public static Drawable buttonBackgroundPrimary(Context context) {
        StateListDrawable d = new StateListDrawable();
        d.addState(BUTTON_STATE_PRESSED, new ColorDrawable(BUTTON_PRIMARY_PRESSED_COLOR));
        d.addState(BUTTON_STATE_DISABLED, new ColorDrawable(BUTTON_PRIMARY_DISABLED_COLOR));
        d.addState(BUTTON_STATE_FOCUSED, buttonBackgroundPrimaryFocused(context));
        d.addState(BUTTON_STATE_NORMAL, buttonBackgroundPrimaryNormal(context));
        return d;
    }

    private static float getFocusBorderWidthPixels(Context context) {
        return (ViewUtil.typedDimensionValueToPixels("4dip", context) / 2.0f) * context.getResources().getDisplayMetrics().density;
    }

    private static Drawable buttonBackgroundPrimaryNormal(Context context) {
        return buttonNormal(BUTTON_PRIMARY_NORMAL_COLOR, getFocusBorderWidthPixels(context));
    }

    private static Drawable buttonBackgroundPrimaryFocused(Context context) {
        return buttonFocused(BUTTON_PRIMARY_NORMAL_COLOR, BUTTON_PRIMARY_FOCUS_COLOR, getFocusBorderWidthPixels(context));
    }

    public static Drawable buttonBackgroundSecondary(Context context) {
        StateListDrawable d = new StateListDrawable();
        d.addState(BUTTON_STATE_PRESSED, new ColorDrawable(BUTTON_SECONDARY_PRESSED_COLOR));
        d.addState(BUTTON_STATE_DISABLED, new ColorDrawable(BUTTON_SECONDARY_DISABLED_COLOR));
        d.addState(BUTTON_STATE_FOCUSED, buttonBackgroundSecondaryFocused(context));
        d.addState(BUTTON_STATE_NORMAL, buttonBackgroundSecondaryNormal(context));
        return d;
    }

    private static Drawable buttonBackgroundSecondaryNormal(Context context) {
        return buttonNormal(BUTTON_SECONDARY_NORMAL_COLOR, getFocusBorderWidthPixels(context));
    }

    private static Drawable buttonBackgroundSecondaryFocused(Context context) {
        return buttonFocused(BUTTON_SECONDARY_NORMAL_COLOR, BUTTON_SECONDARY_FOCUS_COLOR, getFocusBorderWidthPixels(context));
    }

    private static Drawable buttonNormal(int color, float width) {
        ShapeDrawable s = new ShapeDrawable(new RectShape());
        s.getPaint().setStrokeWidth(2.0f * width);
        s.getPaint().setStyle(Paint.Style.STROKE);
        s.getPaint().setColor(DEFAULT_BACKGROUND_COLOR);
        return new LayerDrawable(new Drawable[]{new ColorDrawable(color), s});
    }

    private static Drawable buttonFocused(int backgroundColor, int focusBoxColor, float scaledBorderWidth) {
        ShapeDrawable s = new ShapeDrawable(new RectShape());
        s.getPaint().setStrokeWidth(2.0f * scaledBorderWidth);
        s.getPaint().setStyle(Paint.Style.STROKE);
        s.getPaint().setColor(DEFAULT_BACKGROUND_COLOR);
        ShapeDrawable s2 = new ShapeDrawable(new RectShape());
        s2.getPaint().setStrokeWidth(scaledBorderWidth);
        s2.getPaint().setStyle(Paint.Style.STROKE);
        s2.getPaint().setColor(focusBoxColor);
        return new LayerDrawable(new Drawable[]{new ColorDrawable(backgroundColor), s, s2});
    }

    private static Typeface typefaceLight() {
        return Typeface.create("sans-serif-light", 0);
    }
}
