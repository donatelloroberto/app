package com.opentok.android;

import android.content.Context;
import com.opentok.android.v3.MediaUtils;
import com.opentok.android.v3.OpentokException;
import java.util.ArrayList;
import java.util.Iterator;

public class MediaUtils {

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

    public static SupportedCodecs getSupportedCodecs(Context context) {
        MediaUtils mediaUtils = new MediaUtils();
        mediaUtils.getClass();
        SupportedCodecs codecs = new SupportedCodecs();
        try {
            Iterator<MediaUtils.VideoCodecType> it = com.opentok.android.v3.MediaUtils.getSupportedCodecs(context).videoEncoderCodecs.iterator();
            while (it.hasNext()) {
                VideoCodecType type = VideoCodecType.getValue(it.next().getValue());
                codecs.videoEncoderCodecs.add(type);
                codecs.videoDecoderCodecs.add(type);
            }
        } catch (OpentokException e) {
            e.printStackTrace();
        }
        return codecs;
    }
}
