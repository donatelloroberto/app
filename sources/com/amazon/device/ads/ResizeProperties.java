package com.amazon.device.ads;

import org.json.JSONObject;

class ResizeProperties {
    private final Boolean allowOffscreenDefault = Boolean.TRUE;
    private final String customClosePositionDefault = "top-right";
    private final JSONObject json = new JSONObject();

    public ResizeProperties() {
        JSONObject jSONObject = this.json;
        getClass();
        JSONUtils.put(jSONObject, "customClosePosition", "top-right");
        JSONUtils.put(this.json, "allowOffscreen", this.allowOffscreenDefault.booleanValue());
    }

    public int getWidth() {
        return JSONUtils.getIntegerFromJSON(this.json, "width", 0);
    }

    public void setWidth(int width) {
        JSONUtils.put(this.json, "width", width);
    }

    public int getHeight() {
        return JSONUtils.getIntegerFromJSON(this.json, "height", 0);
    }

    public void setHeight(int height) {
        JSONUtils.put(this.json, "height", height);
    }

    public int getOffsetX() {
        return JSONUtils.getIntegerFromJSON(this.json, "offsetX", 0);
    }

    public void setOffsetX(int offsetX) {
        JSONUtils.put(this.json, "offsetX", offsetX);
    }

    public int getOffsetY() {
        return JSONUtils.getIntegerFromJSON(this.json, "offsetY", 0);
    }

    public void setOffsetY(int offsetY) {
        JSONUtils.put(this.json, "offsetY", offsetY);
    }

    public String getCustomClosePosition() {
        JSONObject jSONObject = this.json;
        getClass();
        return JSONUtils.getStringFromJSON(jSONObject, "customClosePosition", "top-right");
    }

    public void setCustomClosePosition(String customClosePosition) {
        if (customClosePosition != null) {
            JSONUtils.put(this.json, "customClosePosition", customClosePosition);
        }
    }

    public Boolean getAllowOffscreen() {
        return Boolean.valueOf(JSONUtils.getBooleanFromJSON(this.json, "allowOffscreen", this.allowOffscreenDefault.booleanValue()));
    }

    public void setAllowOffscreen(Boolean allowOffscreen) {
        if (allowOffscreen != null) {
            JSONUtils.put(this.json, "allowOffscreen", allowOffscreen.booleanValue());
        }
    }

    public JSONObject toJSONObject() {
        return this.json;
    }
}
