package com.opentok.android;

import com.opentok.android.OpentokError;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.WeakHashMap;

public abstract class BaseAudioDevice {
    private static WeakHashMap<Integer, PublisherKit> activePublisherLst = new WeakHashMap<>();
    private static WeakHashMap<Integer, SubscriberKit> activeSubscriberLst = new WeakHashMap<>();
    private AudioBus audioBus;
    /* access modifiers changed from: private */
    public OutputStream audioInputBus = null;
    /* access modifiers changed from: private */
    public InputStream audioOutputBus = null;
    private OutputMode outputMode = OutputMode.SpeakerPhone;

    public enum OutputMode {
        SpeakerPhone,
        Handset
    }

    public abstract boolean destroyCapturer();

    public abstract boolean destroyRenderer();

    public abstract AudioSettings getCaptureSettings();

    public abstract int getEstimatedCaptureDelay();

    public abstract int getEstimatedRenderDelay();

    public abstract AudioSettings getRenderSettings();

    public abstract boolean initCapturer();

    public abstract boolean initRenderer();

    public abstract void onPause();

    public abstract void onResume();

    public abstract boolean startCapturer();

    public abstract boolean startRenderer();

    public abstract boolean stopCapturer();

    public abstract boolean stopRenderer();

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        activeSubscriberLst.clear();
        activePublisherLst.clear();
        super.finalize();
    }

    public boolean setOutputMode(OutputMode mode) {
        this.outputMode = mode;
        return true;
    }

    public OutputMode getOutputMode() {
        return this.outputMode;
    }

    /* access modifiers changed from: package-private */
    public void setAudioBus(AudioBus audioBus2) {
        this.audioBus = audioBus2;
    }

    public AudioBus getAudioBus() {
        return this.audioBus;
    }

    public static class AudioSettings {
        int numChannels;
        int sampleRate;

        public AudioSettings(int sampleRate2, int numChannels2) {
            this.sampleRate = sampleRate2;
            this.numChannels = numChannels2;
        }

        public int getSampleRate() {
            return this.sampleRate;
        }

        public int getNumChannels() {
            return this.numChannels;
        }

        public void setSampleRate(int sampleRate2) {
            this.sampleRate = sampleRate2;
        }

        public void setNumChannels(int numChannels2) {
            this.numChannels = numChannels2;
        }
    }

    public static class AudioBus {
        private BaseAudioDevice device;
        private byte[] inputTransferBuffer;
        private byte[] outputTransferBuffer;

        private static native void array2BufferNative(Buffer buffer, byte[] bArr);

        private static native void buffer2ArrayNative(Buffer buffer, byte[] bArr);

        AudioBus(BaseAudioDevice device2) {
            this.device = device2;
        }

        public void writeCaptureData(ByteBuffer data, int numberOfSamples) {
            writeFromBuffer(data, numberOfSamples);
        }

        public void writeCaptureData(ShortBuffer data, int numberOfSamples) {
            writeFromBuffer(data, numberOfSamples);
        }

        public int readRenderData(ByteBuffer data, int numberOfSamples) {
            return readToBuffer(data, numberOfSamples);
        }

        public int readRenderData(ShortBuffer data, int numberOfSamples) {
            return readToBuffer(data, numberOfSamples);
        }

        private void writeFromBuffer(Buffer data, int numberOfSamples) {
            if (this.device.audioInputBus != null) {
                try {
                    if (!data.isDirect()) {
                        throw new RuntimeException("ByteBuffer should be allocated using allocateDirect method");
                    }
                    this.outputTransferBuffer = adjustArray(this.outputTransferBuffer, numberOfSamples * 2);
                    buffer2ArrayNative(data, this.outputTransferBuffer);
                    this.device.audioInputBus.write(this.outputTransferBuffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private int readToBuffer(Buffer data, int numberOfSamples) {
            if (this.device.audioOutputBus != null) {
                if (!data.isDirect()) {
                    throw new RuntimeException("ByteBuffer should be allocated using allocateDirect method");
                }
                try {
                    this.inputTransferBuffer = adjustArray(this.inputTransferBuffer, numberOfSamples * 2);
                    int nReadSamples = this.device.audioOutputBus.read(this.inputTransferBuffer) >> 1;
                    if (nReadSamples <= 0) {
                        return nReadSamples;
                    }
                    array2BufferNative(data, this.inputTransferBuffer);
                    return nReadSamples;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }

        private static byte[] adjustArray(byte[] array, int newSize) {
            return (array == null || array.length != newSize) ? new byte[newSize] : array;
        }
    }

    static void addPublisher(PublisherKit publisherkit) {
        activePublisherLst.put(Integer.valueOf(publisherkit.hashCode()), publisherkit);
    }

    static void addSubsciber(SubscriberKit subscriberKit) {
        activeSubscriberLst.put(Integer.valueOf(subscriberKit.hashCode()), subscriberKit);
    }

    static void publisherError(Exception exp) {
        for (PublisherKit sub : activePublisherLst.values()) {
            if (sub != null) {
                sub.throwError(new OpentokError(OpentokError.Domain.PublisherErrorDomain, OpentokError.ErrorCode.PublisherInternalError.getErrorCode(), exp.getMessage()));
            }
        }
    }

    static void subscriberError(Exception exp) {
        for (SubscriberKit sub : activeSubscriberLst.values()) {
            if (sub != null) {
                sub.throwError(new OpentokError(OpentokError.Domain.SubscriberErrorDomain, OpentokError.ErrorCode.SubscriberInternalError.getErrorCode(), exp.getMessage()));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setAudioInputBus(OutputStream outputStream) {
        this.audioInputBus = outputStream;
    }

    /* access modifiers changed from: package-private */
    public void setAudioOutputBus(InputStream inputStream) {
        this.audioOutputBus = inputStream;
    }
}
