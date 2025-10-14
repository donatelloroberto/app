package com.amazon.device.ads;

import org.json.JSONObject;

class ExpandProperties {
    private int height;
    private Boolean isModal = Boolean.TRUE;
    private Boolean useCustomClose = Boolean.FALSE;
    private int width;

    ExpandProperties() {
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

    public Boolean getUseCustomClose() {
        return this.useCustomClose;
    }

    public void setUseCustomClose(Boolean useCustomClose2) {
        this.useCustomClose = useCustomClose2;
    }

    public Boolean getIsModal() {
        return this.isModal;
    }

    public void setIsModal(Boolean isModal2) {
        this.isModal = isModal2;
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        JSONUtils.put(json, "width", this.width);
        JSONUtils.put(json, "height", this.height);
        JSONUtils.put(json, "useCustomClose", this.useCustomClose.booleanValue());
        JSONUtils.put(json, "isModal", this.isModal.booleanValue());
        return json;
    }
}
