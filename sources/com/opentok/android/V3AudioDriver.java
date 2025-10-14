package com.opentok.android;

import android.content.Context;
import com.opentok.android.BaseAudioDevice;
import com.opentok.android.v3.AudioDriver;
import com.opentok.android.v3.AudioDriverInterface;
import java.io.InputStream;
import java.io.OutputStream;

class V3AudioDriver implements AudioDriverInterface {
    private static BaseAudioDevice baseAudioDevice = null;
    private static boolean isActive = false;

    V3AudioDriver() {
    }

    public void initDriver(Context context, AudioDriver.AudioError errorObserver) {
        isActive = true;
    }

    public void shutdownDriver() {
        isActive = false;
    }

    public void pause() {
        if (baseAudioDevice != null) {
            baseAudioDevice.onPause();
        }
    }

    public void resume() {
        if (baseAudioDevice != null) {
            baseAudioDevice.onResume();
        }
    }

    public boolean setOutputMode(AudioDriver.OutputMode mode) {
        return true;
    }

    public boolean initCapturer() {
        if (baseAudioDevice != null) {
            return baseAudioDevice.initCapturer();
        }
        return false;
    }

    public boolean startCapturer(OutputStream audioBus) {
        if (baseAudioDevice == null) {
            return false;
        }
        baseAudioDevice.setAudioInputBus(audioBus);
        return baseAudioDevice.startCapturer();
    }

    public boolean stopCapturer() {
        if (baseAudioDevice != null) {
            return baseAudioDevice.stopCapturer();
        }
        return false;
    }

    public boolean destroyCapturer() {
        if (baseAudioDevice != null) {
            return baseAudioDevice.destroyCapturer();
        }
        return false;
    }

    public int getEstimatedCaptureDelay() {
        if (baseAudioDevice != null) {
            return baseAudioDevice.getEstimatedCaptureDelay();
        }
        return 0;
    }

    public AudioDriver.AudioSettings getCaptureSettings() {
        if (baseAudioDevice == null) {
            return null;
        }
        BaseAudioDevice.AudioSettings as = baseAudioDevice.getCaptureSettings();
        return new AudioDriver.AudioSettings(as.sampleRate, as.numChannels, 2);
    }

    public boolean initRenderer() {
        if (baseAudioDevice != null) {
            return baseAudioDevice.initRenderer();
        }
        return false;
    }

    public boolean startRenderer(InputStream audioBus) {
        if (baseAudioDevice == null) {
            return false;
        }
        baseAudioDevice.setAudioOutputBus(audioBus);
        return baseAudioDevice.startRenderer();
    }

    public boolean stopRenderer() {
        if (baseAudioDevice != null) {
            return baseAudioDevice.stopRenderer();
        }
        return false;
    }

    public boolean destroyRenderer() {
        if (baseAudioDevice != null) {
            return baseAudioDevice.destroyRenderer();
        }
        return false;
    }

    public int getEstimatedRenderDelay() {
        if (baseAudioDevice != null) {
            return baseAudioDevice.getEstimatedRenderDelay();
        }
        return 0;
    }

    public AudioDriver.AudioSettings getRenderSettings() {
        if (baseAudioDevice == null) {
            return null;
        }
        BaseAudioDevice.AudioSettings as = baseAudioDevice.getRenderSettings();
        return new AudioDriver.AudioSettings(as.sampleRate, as.numChannels, 2);
    }

    static BaseAudioDevice getBaseAudioDevice() {
        return baseAudioDevice;
    }

    static void setBaseAudioDevice(BaseAudioDevice audioDevice) {
        baseAudioDevice = audioDevice;
    }

    static boolean isActive() {
        return isActive;
    }
}
