package com.amazon.device.ads;

import org.json.JSONObject;

class Position {
    private Size size;
    private int x;
    private int y;

    public Position() {
        this.size = new Size(0, 0);
        this.x = 0;
        this.y = 0;
    }

    public Position(Size size2, int x2, int y2) {
        this.size = size2;
        this.x = x2;
        this.y = y2;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size2) {
        this.size = size2;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x2) {
        this.x = x2;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y2) {
        this.y = y2;
    }

    public JSONObject toJSONObject() {
        JSONObject json = this.size.toJSONObject();
        JSONUtils.put(json, "x", this.x);
        JSONUtils.put(json, "y", this.y);
        return json;
    }
}
