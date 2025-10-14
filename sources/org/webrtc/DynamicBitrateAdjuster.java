package org.webrtc;

class DynamicBitrateAdjuster extends BaseBitrateAdjuster {
    private static final double BITRATE_ADJUSTMENT_MAX_SCALE = 4.0d;
    private static final double BITRATE_ADJUSTMENT_SEC = 3.0d;
    private static final int BITRATE_ADJUSTMENT_STEPS = 20;
    private static final double BITS_PER_BYTE = 8.0d;
    private int bitrateAdjustmentScaleExp = 0;
    private double deviationBytes = 0.0d;
    private double timeSinceLastAdjustmentMs = 0.0d;

    DynamicBitrateAdjuster() {
    }

    public void setTargets(int targetBitrateBps, int targetFps) {
        if (this.targetBitrateBps > 0 && targetBitrateBps < this.targetBitrateBps) {
            this.deviationBytes = (this.deviationBytes * ((double) targetBitrateBps)) / ((double) this.targetBitrateBps);
        }
        super.setTargets(targetBitrateBps, targetFps);
    }

    public void reportEncodedFrame(int size) {
        if (this.targetFps != 0) {
            this.deviationBytes += ((double) size) - ((((double) this.targetBitrateBps) / BITS_PER_BYTE) / ((double) this.targetFps));
            this.timeSinceLastAdjustmentMs += 1000.0d / ((double) this.targetFps);
            double deviationThresholdBytes = ((double) this.targetBitrateBps) / BITS_PER_BYTE;
            double deviationCap = BITRATE_ADJUSTMENT_SEC * deviationThresholdBytes;
            this.deviationBytes = Math.min(this.deviationBytes, deviationCap);
            this.deviationBytes = Math.max(this.deviationBytes, -deviationCap);
            if (this.timeSinceLastAdjustmentMs > 3000.0d) {
                if (this.deviationBytes > deviationThresholdBytes) {
                    this.bitrateAdjustmentScaleExp -= (int) ((this.deviationBytes / deviationThresholdBytes) + 0.5d);
                    this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
                    this.deviationBytes = deviationThresholdBytes;
                } else if (this.deviationBytes < (-deviationThresholdBytes)) {
                    this.bitrateAdjustmentScaleExp += (int) (((-this.deviationBytes) / deviationThresholdBytes) + 0.5d);
                    this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                    this.deviationBytes = -deviationThresholdBytes;
                }
                this.timeSinceLastAdjustmentMs = 0.0d;
            }
        }
    }

    public int getAdjustedBitrateBps() {
        return (int) (((double) this.targetBitrateBps) * Math.pow(BITRATE_ADJUSTMENT_MAX_SCALE, ((double) this.bitrateAdjustmentScaleExp) / 20.0d));
    }
}
