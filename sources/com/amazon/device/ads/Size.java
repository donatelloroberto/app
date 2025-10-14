package com.amazon.device.ads;

import org.json.JSONObject;

class Size {
    private int height;
    private int width;

    public Size(int width2, int height2) {
        this.width = width2;
        this.height = height2;
    }

    public Size(String screenSize) {
        String[] dimensions;
        int width2 = 0;
        int height2 = 0;
        if (!(screenSize == null || (dimensions = screenSize.split("x")) == null || dimensions.length != 2)) {
            width2 = Math.max(parseInt(dimensions[0], 0), 0);
            height2 = Math.max(parseInt(dimensions[1], 0), 0);
        }
        this.width = width2;
        this.height = height2;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width2) {
        this.width = width2;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height2) {
        this.height = height2;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }

    private static int parseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        JSONUtils.put(json, "width", this.width);
        JSONUtils.put(json, "height", this.height);
        return json;
    }
}
