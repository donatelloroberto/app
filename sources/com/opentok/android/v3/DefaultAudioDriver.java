package com.opentok.android.v3;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.NonNull;
import com.opentok.android.v3.AudioDriver;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class DefaultAudioDriver extends BroadcastReceiver implements AudioDriverInterface, BluetoothProfile.ServiceListener, AudioManager.OnAudioFocusChangeListener, Thread.UncaughtExceptionHandler {
    private static final int ASTATE_BLUETOOTH = 2;
    private static final int ASTATE_HEADPHONES = 1;
    private static final int ASTATE_PHONE_SPEAKERS = 0;
    private static final int BSTATE_CONNECTED = 1;
    private static final int BSTATE_DISCONNECTED = 0;
    private static final String HEADSET_PLUG_STATE_KEY = "state";
    private static AudioInputFactory inputFactory;
    /* access modifiers changed from: private */
    public static final LogInterface log = OtLog.LogToken("[DefaultAudioDevice]");
    private static AudioOutputFactory outputFactory;
    private Context appContext;
    /* access modifiers changed from: private */
    public AudioDriver.AudioError audioErrorHandler;
    private AudioInputThread audioInputThread;
    private AudioManager audioManager;
    private AudioManagerMode audioMangerMode;
    private int audioOutputState;
    private AudioOutputThread audioOutputThread;
    private AudioDriver.OutputMode audioSpeakerState;
    private BluetoothAdapter bluetoothAdapter;
    private int bluetoothCxnState;
    private BluetoothProfile bluetoothProfile;
    InputStream cachedInputAudioBus;
    OutputStream cachedOutputAudioBus;
    int cachedStreamVol;
    private CaptureState captureState;
    private Handler mainThreadHandler;
    private RenderState renderState;

    interface AudioInputFactory {
        InputStream create();

        AudioDriver.AudioSettings getSettings();
    }

    interface AudioOutputFactory {
        OutputStream create();

        AudioDriver.AudioSettings getSettings();
    }

    private enum CaptureState {
        CAPTURE_DEAD,
        CAPTURE_INIT,
        CAPTURE_RUN,
        CAPTURE_STOP
    }

    private enum RenderState {
        RENDER_DEAD,
        RENDER_INIT,
        RENDER_RUN,
        RENDER_STOP
    }

    DefaultAudioDriver() {
    }

    public void initDriver(Context context, AudioDriver.AudioError errorObserver) {
        this.appContext = context;
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.captureState = CaptureState.CAPTURE_DEAD;
        this.renderState = RenderState.RENDER_DEAD;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.audioMangerMode = new AudioManagerMode(this.audioManager);
        this.audioErrorHandler = errorObserver;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.audioOutputState = 0;
        this.audioSpeakerState = AudioDriver.OutputMode.SpeakerPhone;
        this.cachedStreamVol = this.audioManager.getStreamVolume(0);
        synchronized (getClass()) {
            if (inputFactory == null) {
                inputFactory = new DefaultAudioInputFactory();
            }
            if (outputFactory == null) {
                outputFactory = new DefaultAudioOutputFactory(this.audioManager);
            }
        }
        this.audioMangerMode.acquireMode();
    }

    public void shutdownDriver() {
        this.mainThreadHandler = null;
        this.captureState = CaptureState.CAPTURE_DEAD;
        this.renderState = RenderState.RENDER_DEAD;
        this.audioMangerMode.releaseMode();
    }

    public void pause() {
        if (CaptureState.CAPTURE_RUN == this.captureState) {
            statelessStopCapturer();
        }
        if (RenderState.RENDER_RUN == this.renderState) {
            statelessStopRenderer();
        }
    }

    public void resume() {
        if (CaptureState.CAPTURE_RUN == this.captureState) {
            statelessStartCapturer(this.cachedOutputAudioBus);
        }
        if (RenderState.RENDER_RUN == this.renderState) {
            statelessStartRenderer(this.cachedInputAudioBus);
        }
    }

    public synchronized boolean setOutputMode(AudioDriver.OutputMode mode) {
        this.audioSpeakerState = mode;
        if (this.audioOutputState == 0) {
            this.audioManager.setSpeakerphoneOn(mode == AudioDriver.OutputMode.SpeakerPhone);
        }
        return true;
    }

    public boolean initCapturer() {
        if (CaptureState.CAPTURE_DEAD != this.captureState) {
            return true;
        }
        synchronized (getClass()) {
            this.audioInputThread = new AudioInputThread(inputFactory, this);
        }
        this.captureState = CaptureState.CAPTURE_INIT;
        return true;
    }

    public boolean startCapturer(@NonNull OutputStream audioBus) {
        switch (this.captureState) {
            case CAPTURE_INIT:
            case CAPTURE_STOP:
                statelessStartCapturer(audioBus);
                this.captureState = CaptureState.CAPTURE_RUN;
                return true;
            default:
                return true;
        }
    }

    public boolean stopCapturer() {
        switch (this.captureState) {
            case CAPTURE_RUN:
                statelessStopCapturer();
                this.captureState = CaptureState.CAPTURE_STOP;
                return true;
            default:
                return true;
        }
    }

    public boolean destroyCapturer() {
        switch (this.captureState) {
            case CAPTURE_INIT:
            case CAPTURE_STOP:
                this.audioInputThread = null;
                this.cachedOutputAudioBus = null;
                this.captureState = CaptureState.CAPTURE_DEAD;
                return true;
            default:
                return true;
        }
    }

    public int getEstimatedCaptureDelay() {
        switch (this.captureState) {
            case CAPTURE_RUN:
                return this.audioInputThread.getDelayEstimate();
            default:
                return 0;
        }
    }

    public AudioDriver.AudioSettings getCaptureSettings() {
        switch (this.captureState) {
            case CAPTURE_DEAD:
                return inputFactory.getSettings();
            default:
                return this.audioInputThread.getSettings();
        }
    }

    public boolean initRenderer() {
        if (RenderState.RENDER_DEAD != this.renderState) {
            return true;
        }
        synchronized (getClass()) {
            this.audioOutputThread = new AudioOutputThread(outputFactory, this);
        }
        this.renderState = RenderState.RENDER_INIT;
        return true;
    }

    public boolean startRenderer(@NonNull InputStream audioBus) {
        switch (this.renderState) {
            case RENDER_INIT:
            case RENDER_STOP:
                this.appContext.registerReceiver(this, new IntentFilter("android.intent.action.HEADSET_PLUG"));
                this.appContext.registerReceiver(this, new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED"));
                this.audioManager.requestAudioFocus(this, 0, 1);
                statelessStartRenderer(audioBus);
                this.renderState = RenderState.RENDER_RUN;
                break;
        }
        return true;
    }

    public boolean stopRenderer() {
        switch (this.renderState) {
            case RENDER_RUN:
                destroyBluetooth();
                this.appContext.unregisterReceiver(this);
                statelessStopRenderer();
                this.renderState = RenderState.RENDER_STOP;
                return true;
            default:
                return true;
        }
    }

    public boolean destroyRenderer() {
        switch (this.renderState) {
            case RENDER_INIT:
            case RENDER_STOP:
                this.renderState = RenderState.RENDER_DEAD;
                this.cachedInputAudioBus = null;
                return true;
            default:
                return true;
        }
    }

    public int getEstimatedRenderDelay() {
        switch (this.renderState) {
            case RENDER_RUN:
                return this.audioOutputThread.getDelayEstimate();
            default:
                return 0;
        }
    }

    public AudioDriver.AudioSettings getRenderSettings() {
        switch (this.renderState) {
            case RENDER_DEAD:
                return outputFactory.getSettings();
            default:
                return this.audioOutputThread.getSettings();
        }
    }

    public synchronized void onReceive(Context context, Intent intent) {
        boolean z = true;
        synchronized (this) {
            String action = intent.getAction();
            if (action != null) {
                if (!action.equals("android.intent.action.HEADSET_PLUG")) {
                    if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                        switch (intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1)) {
                            case 0:
                                if (1 == this.bluetoothCxnState) {
                                    this.bluetoothCxnState = 0;
                                    stopBluetoothSco();
                                    if (!this.audioManager.isWiredHeadsetOn()) {
                                        log.d("Bluetooth disconnected, switching to " + (this.audioSpeakerState == AudioDriver.OutputMode.SpeakerPhone ? "Speaker Phone" : "Headset"), new Object[0]);
                                        this.audioOutputState = 0;
                                        AudioManager audioManager2 = this.audioManager;
                                        if (this.audioSpeakerState != AudioDriver.OutputMode.SpeakerPhone) {
                                            z = false;
                                        }
                                        audioManager2.setSpeakerphoneOn(z);
                                        break;
                                    } else {
                                        log.d("Bluetooth disconnected, switching to headphones", new Object[0]);
                                        this.audioOutputState = 1;
                                        this.audioManager.setSpeakerphoneOn(false);
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                if (this.bluetoothCxnState == 0) {
                                    log.d("Bluetooth Connected, starting bluetooth", new Object[0]);
                                    this.bluetoothCxnState = 1;
                                    this.audioOutputState = 2;
                                    startBluetoothSco();
                                    this.audioManager.setSpeakerphoneOn(false);
                                    break;
                                }
                                break;
                        }
                    }
                } else if (intent.getIntExtra("state", 0) == 1) {
                    log.d("Headphones conected, switching to headphones", new Object[0]);
                    this.audioOutputState = 1;
                    this.audioManager.setSpeakerphoneOn(false);
                    this.audioManager.setBluetoothScoOn(false);
                } else if (1 == this.bluetoothCxnState) {
                    log.d("Headphones disconnected, switching to bluetooth", new Object[0]);
                    this.audioManager.setBluetoothScoOn(true);
                    this.audioOutputState = 2;
                } else {
                    log.d("Headphones disconnected, switching to speakers", new Object[0]);
                    this.audioOutputState = 0;
                    AudioManager audioManager3 = this.audioManager;
                    if (this.audioSpeakerState != AudioDriver.OutputMode.SpeakerPhone) {
                        z = false;
                    }
                    audioManager3.setSpeakerphoneOn(z);
                }
            }
        }
    }

    public void onServiceConnected(int profile, BluetoothProfile proxy) {
        if (1 == profile) {
            this.bluetoothProfile = proxy;
            List<BluetoothDevice> devices = proxy.getConnectedDevices();
            if (!devices.isEmpty() && 2 == proxy.getConnectionState(devices.get(0))) {
                Intent intent = new Intent("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                intent.putExtra("android.bluetooth.profile.extra.STATE", 2);
                onReceive(this.appContext, intent);
            }
        }
    }

    public void onServiceDisconnected(int profile) {
    }

    public synchronized void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case -3:
                log.d("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK", new Object[0]);
                this.cachedStreamVol = this.audioManager.getStreamVolume(0);
                this.audioManager.setStreamVolume(0, 0, 0);
                break;
            case -2:
            case -1:
                log.d("AudioManager.AUDIOFOCUS_LOSS || AudioManager.AUDIOFOCUS_LOSS_TRANSIENT", new Object[0]);
                if (CaptureState.CAPTURE_RUN == this.captureState) {
                    statelessStopCapturer();
                }
                if (RenderState.RENDER_RUN == this.renderState) {
                    statelessStopRenderer();
                    break;
                }
                break;
            case 1:
                log.d("AudioManager.AUDIOFOCUS_GAIN", new Object[0]);
                this.audioManager.setStreamVolume(0, this.cachedStreamVol, 0);
                if (CaptureState.CAPTURE_RUN == this.captureState) {
                    statelessStartCapturer(this.cachedOutputAudioBus);
                }
                if (RenderState.RENDER_RUN == this.renderState) {
                    statelessStartRenderer(this.cachedInputAudioBus);
                    break;
                }
                break;
        }
    }

    public void uncaughtException(Thread t, Throwable e) {
        onError(new Exception(e));
    }

    static void setAudioInputFactory(AudioInputFactory factory) {
        inputFactory = factory;
    }

    static void setAudioOutputFactory(AudioOutputFactory factory) {
        outputFactory = factory;
    }

    private synchronized void statelessStartCapturer(OutputStream audioBus) {
        log.d("statelessStartCapturer(...) entered", new Object[0]);
        this.cachedOutputAudioBus = audioBus;
        this.audioInputThread.start(audioBus);
        log.d("statelessStartCapturer(...) exit", new Object[0]);
    }

    private synchronized void statelessStopCapturer() {
        log.d("statelessStopCapturer(...) entered", new Object[0]);
        this.audioInputThread.exit();
        if (this.audioInputThread.isAlive()) {
            try {
                this.audioInputThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (getClass()) {
            this.audioInputThread = new AudioInputThread(inputFactory, this);
        }
        log.d("statelessStopCapturer(...) exit", new Object[0]);
        return;
    }

    private synchronized void statelessStartRenderer(InputStream audioBus) {
        this.cachedInputAudioBus = audioBus;
        setOutputMode(this.audioSpeakerState);
        initBluetooth();
        this.audioOutputThread.start(audioBus);
    }

    private synchronized void statelessStopRenderer() {
        this.audioOutputThread.exit();
        if (this.audioOutputThread.isAlive()) {
            try {
                this.audioOutputThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (getClass()) {
            this.audioOutputThread = new AudioOutputThread(outputFactory, this);
        }
        return;
    }

    private void onError(final Exception e) {
        this.mainThreadHandler.post(new Runnable() {
            public void run() {
                synchronized (DefaultAudioDriver.this.getClass()) {
                    if (DefaultAudioDriver.this.audioErrorHandler != null) {
                        DefaultAudioDriver.this.audioErrorHandler.onError(e);
                    }
                }
            }
        });
    }

    private synchronized void initBluetooth() {
        if (this.audioManager.isBluetoothScoAvailableOffCall() && this.bluetoothAdapter != null) {
            this.bluetoothAdapter.getProfileProxy(this.appContext, this, 1);
        }
    }

    private synchronized void destroyBluetooth() {
        if (!(this.bluetoothProfile == null || this.bluetoothAdapter == null)) {
            this.bluetoothAdapter.closeProfileProxy(1, this.bluetoothProfile);
        }
        Intent intent = new Intent("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.STATE", 0);
        onReceive(this.appContext, intent);
    }

    private synchronized void startBluetoothSco() {
        try {
            this.audioManager.setBluetoothScoOn(true);
            this.audioManager.startBluetoothSco();
        } catch (NullPointerException e) {
            log.d("Failed to start the BT SCO. In Android 5.0 calling [start|stop]BluetoothSco produces a NPE in some devices", new Object[0]);
        }
        return;
    }

    private synchronized void stopBluetoothSco() {
        try {
            this.audioManager.setBluetoothScoOn(false);
            this.audioManager.stopBluetoothSco();
        } catch (NullPointerException e) {
            log.d("Failed to start the BT SCO. In Android 5.0 calling [start|stop]BluetoothSco produces a NPE in some devices", new Object[0]);
        }
        return;
    }

    private static class AudioManagerMode {
        private final AudioManager audioManager;
        private int oldMode = 0;
        private int refCnt = 0;

        AudioManagerMode(AudioManager audioManager2) {
            this.audioManager = audioManager2;
        }

        /* access modifiers changed from: package-private */
        public void acquireMode() {
            int i = this.refCnt;
            this.refCnt = i + 1;
            if (i == 0) {
                this.oldMode = this.audioManager.getMode();
                this.audioManager.setMode(3);
            }
        }

        /* access modifiers changed from: package-private */
        public void releaseMode() {
            int i = this.refCnt - 1;
            this.refCnt = i;
            if (i == 0) {
                this.audioManager.setMode(this.oldMode);
            }
        }
    }

    private static class AudioInputThread extends Thread {
        private OutputStream audioBus;
        private AtomicInteger captureDelay = new AtomicInteger(0);
        private volatile boolean exit = false;
        private final AudioInputFactory inputFactory;

        AudioInputThread(AudioInputFactory factory, Thread.UncaughtExceptionHandler errorHandler) {
            super("AudioInputThread");
            setUncaughtExceptionHandler(errorHandler);
            this.inputFactory = factory;
        }

        /* access modifiers changed from: package-private */
        public void start(OutputStream output) {
            this.audioBus = output;
            start();
        }

        public void run() {
            InputStream inStrm = this.inputFactory.create();
            AudioDriver.AudioSettings settings = this.inputFactory.getSettings();
            int framesInBuf = (settings.samplingRate / 1000) * 10;
            int i = framesInBuf * settings.numberOfChannels;
            int numBytesInS = settings.numberOfChannels * settings.samplingRate * settings.bytesPerSample;
            byte[] buffer = new byte[(framesInBuf * settings.numberOfChannels * settings.bytesPerSample)];
            try {
                Process.setThreadPriority(-19);
            } catch (Exception e) {
                DefaultAudioDriver.log.d("failed to change audio output thread priority", new Object[0]);
            }
            while (!this.exit) {
                try {
                    int bytesRead = inStrm.read(buffer);
                    if (bytesRead > 0) {
                        this.audioBus.write(buffer, 0, bytesRead);
                        this.captureDelay.set((bytesRead * 1000) / numBytesInS);
                    } else if (bytesRead < 0) {
                        this.exit = true;
                        getUncaughtExceptionHandler().uncaughtException(this, new OpentokException(-1, "Audio Device Read Error: " + bytesRead));
                    }
                } catch (IOException e2) {
                    getUncaughtExceptionHandler().uncaughtException(this, e2);
                    return;
                }
            }
            inStrm.close();
        }

        /* access modifiers changed from: package-private */
        public AudioDriver.AudioSettings getSettings() {
            return this.inputFactory.getSettings();
        }

        /* access modifiers changed from: package-private */
        public int getDelayEstimate() {
            return this.captureDelay.get();
        }

        /* access modifiers changed from: package-private */
        public void exit() {
            this.exit = true;
        }
    }

    private static class AudioOutputThread extends Thread {
        private InputStream audioBus;
        private volatile boolean exit = false;
        private final AudioOutputFactory outputFactory;
        private AtomicInteger renderDelay = new AtomicInteger(0);

        AudioOutputThread(AudioOutputFactory factory, Thread.UncaughtExceptionHandler errorHandler) {
            super("AudioOutputThread");
            setUncaughtExceptionHandler(errorHandler);
            this.outputFactory = factory;
        }

        /* access modifiers changed from: package-private */
        public void start(InputStream input) {
            this.audioBus = input;
            start();
        }

        public void run() {
            OutputStream outStrm = this.outputFactory.create();
            AudioDriver.AudioSettings settings = this.outputFactory.getSettings();
            int numBytesInS = settings.samplingRate * settings.numberOfChannels * settings.bytesPerSample;
            byte[] buffer = new byte[((settings.samplingRate / 1000) * 10 * settings.numberOfChannels * settings.bytesPerSample)];
            try {
                Process.setThreadPriority(-19);
            } catch (Exception e) {
                DefaultAudioDriver.log.d("failed to change audio output thread priority", new Object[0]);
            }
            while (!this.exit) {
                try {
                    int bytesRead = this.audioBus.read(buffer);
                    if (bytesRead > 0) {
                        outStrm.write(buffer, 0, bytesRead);
                        this.renderDelay.set((bytesRead * 1000) / numBytesInS);
                    } else if (bytesRead < 0) {
                        this.exit = true;
                        getUncaughtExceptionHandler().uncaughtException(this, new OpentokException(-1, "Audio Bus Read Error: " + bytesRead));
                    }
                } catch (IOException e2) {
                    getUncaughtExceptionHandler().uncaughtException(this, e2);
                    return;
                }
            }
            outStrm.flush();
            outStrm.close();
        }

        /* access modifiers changed from: package-private */
        public AudioDriver.AudioSettings getSettings() {
            return this.outputFactory.getSettings();
        }

        /* access modifiers changed from: package-private */
        public void exit() {
            this.exit = true;
        }

        /* access modifiers changed from: package-private */
        public int getDelayEstimate() {
            return this.renderDelay.get();
        }
    }
}
