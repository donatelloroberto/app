package org.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;

public class HardwareVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "HardwareVideoDecoderFactory";

    public VideoDecoder createDecoder(String codecType) {
        VideoCodecType type = VideoCodecType.valueOf(codecType);
        MediaCodecInfo info = findCodecForType(type);
        if (info == null) {
            return null;
        }
        return new HardwareVideoDecoder(info.getName(), type, MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, info.getCapabilitiesForType(type.mimeType())).intValue());
    }

    private MediaCodecInfo findCodecForType(VideoCodecType type) {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo info = null;
            try {
                info = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.e(TAG, "Cannot retrieve encoder codec info", e);
            }
            if (info != null && !info.isEncoder() && isSupportedCodec(info, type)) {
                return info;
            }
        }
        return null;
    }

    private boolean isSupportedCodec(MediaCodecInfo info, VideoCodecType type) {
        if (MediaCodecUtils.codecSupportsType(info, type) && MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, info.getCapabilitiesForType(type.mimeType())) != null) {
            return isHardwareSupported(info, type);
        }
        return false;
    }

    private boolean isHardwareSupported(MediaCodecInfo info, VideoCodecType type) {
        String name = info.getName();
        switch (type) {
            case VP8:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Intel.") || name.startsWith("OMX.Exynos.") || name.startsWith("OMX.Nvidia.")) {
                    return true;
                }
                return false;
            case VP9:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Exynos.")) {
                    return true;
                }
                return false;
            case H264:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Intel.") || name.startsWith("OMX.Exynos.")) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
