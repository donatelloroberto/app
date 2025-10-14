package com.opentok.android.v3;

import android.content.Context;
import com.opentok.android.v3.loader.Loader;
import java.util.ArrayList;

public class MediaUtils {
    private static native int[] getSupportedVideoCodecsNative(Context context) throws OpentokException;

    private static native void registerNatives();

    public enum VideoCodecType {
        VIDEO_CODEC_VP8(1),
        VIDEO_CODEC_H264(2);
        
        private final int value;

        private VideoCodecType(int value2) {
            this.value = value2;
        }

        public int getValue() {
            return this.value;
        }

        public static VideoCodecType getValue(int value2) {
            for (VideoCodecType type : values()) {
                if (type.getValue() == value2) {
                    return type;
                }
            }
            return null;
        }
    }

    public class SupportedCodecs {
        public ArrayList<VideoCodecType> videoDecoderCodecs = new ArrayList<>();
        public ArrayList<VideoCodecType> videoEncoderCodecs = new ArrayList<>();

        public SupportedCodecs() {
        }
    }

    public static SupportedCodecs getSupportedCodecs(Context context) throws OpentokException {
        MediaUtils mediaUtils = new MediaUtils();
        mediaUtils.getClass();
        SupportedCodecs codecs = new SupportedCodecs();
        for (int codec : getSupportedVideoCodecsNative(context)) {
            VideoCodecType type = VideoCodecType.getValue(codec);
            codecs.videoEncoderCodecs.add(type);
            codecs.videoDecoderCodecs.add(type);
        }
        return codecs;
    }

    static {
        Loader.load();
        registerNatives();
    }
}
