package io.card.payment.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtil {
    private static final Map<String, Integer> DIMENSION_STRING_CONSTANT = initDimensionStringConstantMap();
    static Pattern DIMENSION_VALUE_PATTERN = Pattern.compile("^\\s*(\\d+(\\.\\d+)*)\\s*([a-zA-Z]+)\\s*$");
    static HashMap<String, Float> pxDimensionLookupTable = new HashMap<>();

    @TargetApi(16)
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    static Map<String, Integer> initDimensionStringConstantMap() {
        Map<String, Integer> m = new HashMap<>();
        m.put("px", 0);
        m.put("dip", 1);
        m.put("dp", 1);
        m.put("sp", 2);
        m.put("pt", 3);
        m.put("in", 4);
        m.put("mm", 5);
        return Collections.unmodifiableMap(m);
    }

    public static int typedDimensionValueToPixelsInt(String dimensionValueString, Context context) {
        if (dimensionValueString == null) {
            return 0;
        }
        return (int) typedDimensionValueToPixels(dimensionValueString, context);
    }

    @SuppressLint({"DefaultLocale"})
    public static float typedDimensionValueToPixels(String dimensionValueString, Context context) {
        if (dimensionValueString == null) {
            return 0.0f;
        }
        String dimensionValueString2 = dimensionValueString.toLowerCase();
        if (pxDimensionLookupTable.containsKey(dimensionValueString2)) {
            return pxDimensionLookupTable.get(dimensionValueString2).floatValue();
        }
        Matcher m = DIMENSION_VALUE_PATTERN.matcher(dimensionValueString2);
        if (!m.matches()) {
            throw new NumberFormatException();
        }
        float value = Float.parseFloat(m.group(1));
        Integer unit = DIMENSION_STRING_CONSTANT.get(m.group(3).toLowerCase());
        if (unit == null) {
            unit = 1;
        }
        float ret = TypedValue.applyDimension(unit.intValue(), value, context.getResources().getDisplayMetrics());
        pxDimensionLookupTable.put(dimensionValueString2, Float.valueOf(ret));
        return ret;
    }

    public static void setPadding(View view, String left, String top, String right, String bottom) {
        Context context = view.getContext();
        view.setPadding(typedDimensionValueToPixelsInt(left, context), typedDimensionValueToPixelsInt(top, context), typedDimensionValueToPixelsInt(right, context), typedDimensionValueToPixelsInt(bottom, context));
    }

    public static void setMargins(View view, String left, String top, String right, String bottom) {
        Context context = view.getContext();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) params).setMargins(typedDimensionValueToPixelsInt(left, context), typedDimensionValueToPixelsInt(top, context), typedDimensionValueToPixelsInt(right, context), typedDimensionValueToPixelsInt(bottom, context));
        }
    }

    public static void setDimensions(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
    }

    public static void styleAsButton(Button button, boolean primary, Context context, boolean useApplicationTheme) {
        Drawable buttonBackgroundSecondary;
        setDimensions(button, -1, -2);
        button.setFocusable(true);
        setPadding(button, "10dip", "0dip", "10dip", "0dip");
        if (!useApplicationTheme) {
            if (primary) {
                buttonBackgroundSecondary = Appearance.buttonBackgroundPrimary(context);
            } else {
                buttonBackgroundSecondary = Appearance.buttonBackgroundSecondary(context);
            }
            setBackground(button, buttonBackgroundSecondary);
            button.setGravity(17);
            button.setMinimumHeight(typedDimensionValueToPixelsInt("54dip", context));
            button.setTextColor(-1);
            button.setTextSize(20.0f);
            button.setTypeface(Appearance.TYPEFACE_BUTTON);
        }
    }
}
