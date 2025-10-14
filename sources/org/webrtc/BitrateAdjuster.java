package org.webrtc;

interface BitrateAdjuster {
    int getAdjustedBitrateBps();

    int getAdjustedFramerate();

    void reportEncodedFrame(int i);

    void setTargets(int i, int i2);
}
