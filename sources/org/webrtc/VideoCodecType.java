package org.webrtc;

enum VideoCodecType {
    VP8("video/x-vnd.on2.vp8"),
    VP9("video/x-vnd.on2.vp9"),
    H264("video/avc");
    
    private final String mimeType;

    private VideoCodecType(String mimeType2) {
        this.mimeType = mimeType2;
    }

    /* access modifiers changed from: package-private */
    public String mimeType() {
        return this.mimeType;
    }
}
