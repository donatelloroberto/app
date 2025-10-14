package com.opentok.android.v3;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.Arrays;

@Keep
public class AudioDriver implements Thread.UncaughtExceptionHandler {
    private static WeakReference<Context> appContext;
    private static Class<? extends AudioDriverInterface> audioDriverClass = DefaultAudioDriver.class;
    /* access modifiers changed from: private */
    public static AudioError audioError;
    private static OutputMode cachedOutputMode = OutputMode.SpeakerPhone;
    private static final LogInterface log = OtLog.LogToken("[AudioDriver]");
    private static AudioDriver singleton;
    private AudioDriverInterface audioDriverInterface;
    private AudioError errorHandler = new AudioError() {
        public void onError(Exception e) {
            if (AudioDriver.audioError != null) {
                AudioDriver.audioError.onError(e);
            }
        }
    };

    public interface AudioError {
        void onError(Exception exc);
    }

    public enum OutputMode {
        SpeakerPhone,
        Handset
    }

    /* access modifiers changed from: private */
    public native int readSamplesNative(byte[] bArr, int i);

    /* access modifiers changed from: private */
    public native int readSamplesNative(byte[] bArr, int i, int i2);

    private static native void registerNatives();

    /* access modifiers changed from: private */
    public native int writeSamplesNative(byte[] bArr, int i);

    public static class AudioSettings {
        public final int bytesPerSample;
        public final int numberOfChannels;
        public final int samplingRate;

        public AudioSettings(int samplingRate2, int numberOfChannles, int bytesPerSample2) {
            this.samplingRate = samplingRate2;
            this.numberOfChannels = numberOfChannles;
            this.bytesPerSample = bytesPerSample2;
        }
    }

    public static void setAudioDriver(Class<? extends AudioDriverInterface> audioDriverInterfaceClass) throws IllegalAccessException, InstantiationException {
        log.d("setAudioDriver(...) called", new Object[0]);
        if (audioDriverInterfaceClass != null) {
            audioDriverClass = audioDriverInterfaceClass;
        } else {
            setAudioDriver(DefaultAudioDriver.class);
        }
    }

    public static void setAudioError(AudioError audioError2) {
        log.d("setAudioError(...) called", new Object[0]);
        audioError = audioError2;
    }

    public static boolean setOutputMode(OutputMode mode) {
        log.d("setOutputMode(...) called", new Object[0]);
        cachedOutputMode = mode;
        if (singleton == null || singleton.audioDriverInterface == null) {
            return true;
        }
        return singleton.audioDriverInterface.setOutputMode(cachedOutputMode);
    }

    public static AudioDriver getInstance() {
        return singleton;
    }

    public void pause() {
        log.d("pause() called", new Object[0]);
        this.audioDriverInterface.pause();
    }

    public void resume() {
        log.d("resume() called", new Object[0]);
        this.audioDriverInterface.resume();
    }

    public void uncaughtException(Thread t, Throwable e) {
        this.errorHandler.onError(new Exception(e));
    }

    static void setApplicationContext(Context context) {
        appContext = new WeakReference<>(context);
    }

    private AudioDriver() {
        log.d("AudioDriver() called", new Object[0]);
        try {
            Constructor<? extends AudioDriverInterface> ctr = audioDriverClass.getDeclaredConstructor(new Class[0]);
            ctr.setAccessible(true);
            singleton = this;
            this.audioDriverInterface = (AudioDriverInterface) ctr.newInstance(new Object[0]);
            this.audioDriverInterface.initDriver((Context) appContext.get(), this.errorHandler);
            this.audioDriverInterface.setOutputMode(cachedOutputMode);
            Thread.setDefaultUncaughtExceptionHandler(this);
        } catch (Exception e) {
            this.errorHandler.onError(e);
        }
    }

    private synchronized void shutdownDriver() {
        log.d("shutdownDriver() called", new Object[0]);
        try {
            this.audioDriverInterface.shutdownDriver();
            this.audioDriverInterface = null;
            singleton = null;
        } catch (Exception e) {
            this.errorHandler.onError(e);
        }
        return;
    }

    private synchronized boolean initCapture() {
        boolean z = false;
        synchronized (this) {
            log.d("initCapture() called", new Object[0]);
            try {
                z = this.audioDriverInterface.initCapturer();
            } catch (Exception e) {
                this.errorHandler.onError(e);
            }
        }
        return z;
    }

    private synchronized boolean destroyCapture() {
        boolean z = false;
        synchronized (this) {
            log.d("destroyCapture() called", new Object[0]);
            try {
                z = this.audioDriverInterface.destroyCapturer();
            } catch (Exception e) {
                this.errorHandler.onError(e);
            }
        }
        return z;
    }

    private synchronized boolean startCapture() {
        boolean z = false;
        synchronized (this) {
            log.d("startCapture() called", new Object[0]);
            try {
                z = this.audioDriverInterface.startCapturer(new AudioOutputStream(this.audioDriverInterface.getCaptureSettings()));
            } catch (Exception e) {
                this.errorHandler.onError(e);
            }
        }
        return z;
    }

    private synchronized boolean stopCapture() {
        boolean z = false;
        synchronized (this) {
            log.d("stopCapture() called", new Object[0]);
            try {
                z = this.audioDriverInterface.stopCapturer();
            } catch (Exception e) {
                this.errorHandler.onError(e);
            }
        }
        return z;
    }

    private AudioSettings getCaptureSettings() {
        log.d("getCaptureSettings() called", new Object[0]);
        try {
            return this.audioDriverInterface.getCaptureSettings();
        } catch (Exception e) {
            this.errorHandler.onError(e);
            return null;
        }
    }

    private int getEstimatedCaptureDelay() {
        try {
            return this.audioDriverInterface.getEstimatedCaptureDelay();
        } catch (Exception e) {
            this.errorHandler.onError(e);
            return 0;
        }
    }

    private synchronized boolean initRender() {
        boolean z = false;
        synchronized (this) {
            log.d("initRender() called", new Object[0]);
            try {
                z = this.audioDriverInterface.initRenderer();
            } catch (Exception e) {
                this.errorHandler.onError(e);
            }
        }
        return z;
    }

    private synchronized boolean destroyRender() {
        boolean z = false;
        synchronized (this) {
            log.d("destroyRender() called", new Object[0]);
            try {
                z = this.audioDriverInterface.destroyRenderer();
            } catch (Exception e) {
                this.errorHandler.onError(e);
            }
        }
        return z;
    }

    private synchronized boolean startRender() {
        boolean z = false;
        synchronized (this) {
            log.d("startRender() called", new Object[0]);
            try {
                z = this.audioDriverInterface.startRenderer(new AudioInput(this.audioDriverInterface.getRenderSettings()));
            } catch (Exception e) {
                this.errorHandler.onError(e);
            }
        }
        return z;
    }

    private synchronized boolean stopRender() {
        boolean z = false;
        synchronized (this) {
            log.d("stopRender() called", new Object[0]);
            try {
                z = this.audioDriverInterface.stopRenderer();
            } catch (Exception e) {
                this.errorHandler.onError(e);
            }
        }
        return z;
    }

    private AudioSettings getRenderSettings() {
        log.d("getRenderSettings() called", new Object[0]);
        try {
            return this.audioDriverInterface.getRenderSettings();
        } catch (Exception e) {
            this.errorHandler.onError(e);
            return null;
        }
    }

    private int getEstimatedRenderDelay() {
        try {
            return this.audioDriverInterface.getEstimatedRenderDelay();
        } catch (Exception e) {
            this.errorHandler.onError(e);
            return 0;
        }
    }

    private class AudioOutputStream extends OutputStream {
        private int frameShiftFactor;

        AudioOutputStream(AudioSettings settings) {
            this.frameShiftFactor = 31 - Integer.numberOfLeadingZeros(settings.bytesPerSample * settings.numberOfChannels);
        }

        public void write(int b) throws IOException {
            int unused = AudioDriver.this.writeSamplesNative(ByteBuffer.allocate(4).putInt(b).array(), 1);
        }

        public void write(@NonNull byte[] b, int off, int len) throws IOException {
            int error = AudioDriver.this.writeSamplesNative(Arrays.copyOfRange(b, off, off + len), len >> this.frameShiftFactor);
            if (error != 0) {
                throw new IOException("AudioOutputStream Error: " + error);
            }
        }

        public void write(@NonNull byte[] b) throws IOException {
            int error = AudioDriver.this.writeSamplesNative(b, b.length >> this.frameShiftFactor);
            if (error != 0) {
                throw new IOException("AudioOutputStream Error: " + error);
            }
        }

        public void close() throws IOException {
            throw new IOException("Close not supported");
        }
    }

    private class AudioInput extends InputStream {
        private int frameShiftFactor;

        AudioInput(AudioSettings settings) {
            this.frameShiftFactor = 31 - Integer.numberOfLeadingZeros(settings.bytesPerSample * settings.numberOfChannels);
        }

        public int read() throws IOException {
            ByteBuffer tmp = ByteBuffer.allocate(4);
            int unused = AudioDriver.this.readSamplesNative(tmp.array(), 1);
            return tmp.getInt();
        }

        public int read(@NonNull byte[] b, int off, int len) throws IOException {
            return AudioDriver.this.readSamplesNative(b, off, len >> this.frameShiftFactor) << this.frameShiftFactor;
        }

        public int read(@NonNull byte[] b) throws IOException {
            return AudioDriver.this.readSamplesNative(b, b.length >> this.frameShiftFactor) << this.frameShiftFactor;
        }

        public void close() throws IOException {
            throw new IOException("Close not supported");
        }
    }

    static {
        Loader.load();
        registerNatives();
    }
}
