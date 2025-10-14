package com.opentok.android;

import android.content.Context;
import com.opentok.android.BaseAudioDevice;
import com.opentok.android.v3.AudioDriver;

public class AudioDeviceManager {
    public static void setAudioDevice(BaseAudioDevice device) throws IllegalStateException {
        if (V3AudioDriver.getBaseAudioDevice() != device) {
            if (V3AudioDriver.isActive()) {
                throw new IllegalStateException("AudioDevice can only be changed before initialization.");
            } else if (device != null) {
                V3AudioDriver.setBaseAudioDevice(device);
                V3AudioDriver.getBaseAudioDevice().setAudioBus(new BaseAudioDevice.AudioBus(V3AudioDriver.getBaseAudioDevice()));
            }
        }
    }

    public static BaseAudioDevice getAudioDevice() {
        return V3AudioDriver.getBaseAudioDevice();
    }

    static void initializeDefaultDevice(Context context) {
        if (V3AudioDriver.getBaseAudioDevice() == null) {
            OtLog.d("AUDIO_DEVICE creating default device", new Object[0]);
            V3AudioDriver.setBaseAudioDevice(new DefaultAudioDevice(context));
            V3AudioDriver.getBaseAudioDevice().setAudioBus(new BaseAudioDevice.AudioBus(V3AudioDriver.getBaseAudioDevice()));
        }
    }

    static {
        try {
            AudioDriver.setAudioDriver(V3AudioDriver.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
