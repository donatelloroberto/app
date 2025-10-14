package com.opentok.android;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.opentok.android.BaseAudioDevice;
import com.opentok.android.OtLog;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class DefaultAudioDevice extends BaseAudioDevice {
    private static final int DEFAULT_BUFFER_SIZE = 1760;
    private static final int DEFAULT_SAMPLES_PER_BUFFER = 440;
    private static final int DEFAULT_SAMPLE_RATE = 44100;
    private static final String HEADSET_PLUG_STATE_KEY = "state";
    private static final int NUM_CHANNELS_CAPTURING = 1;
    private static final int NUM_CHANNELS_RENDERING = 1;
    private static final int SAMPLE_SIZE_IN_BYTES = 2;
    private static final int STEREO_CHANNELS = 2;
    /* access modifiers changed from: private */
    public static final OtLog.LogToken log = new OtLog.LogToken();
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            Log.d("AUDIO_FOCUS", "focusChange: " + focusChange);
            switch (focusChange) {
                case -3:
                    DefaultAudioDevice.this.audioState.setLastStreamVolume(DefaultAudioDevice.this.audioManager.getStreamVolume(0));
                    DefaultAudioDevice.this.audioManager.setStreamVolume(0, 0, 0);
                    break;
                case -2:
                case -1:
                    break;
                case 0:
                    Log.d("AUDIO_FOCUS", "This is strange !!");
                    break;
                case 1:
                    switch (DefaultAudioDevice.this.audioState.getLastKnownFocusState()) {
                        case -3:
                            DefaultAudioDevice.this.audioManager.setStreamVolume(0, DefaultAudioDevice.this.audioState.getLastStreamVolume(), 0);
                            break;
                        case -2:
                        case -1:
                            break;
                        default:
                            Log.d("afChangeListener", "focusChange = " + focusChange);
                            break;
                    }
                    DefaultAudioDevice.this.setOutputType(DefaultAudioDevice.this.audioState.getLastOutputType());
                    DefaultAudioDevice.this.forceConnectBluetooth();
                    break;
                default:
                    Log.d("AUDIO_FOCUS", "focusChange: " + focusChange);
                    break;
            }
            DefaultAudioDevice.this.audioState.setLastOutputType(DefaultAudioDevice.this.getOutputType());
            DefaultAudioDevice.this.audioState.setLastKnownFocusState(focusChange);
        }
    };
    /* access modifiers changed from: private */
    public AudioManager audioManager;
    private AudioManagerMode audioManagerMode = new AudioManagerMode();
    private OutputType audioOutputType = OutputType.SPEAKER_PHONE;
    /* access modifiers changed from: private */
    public AudioRecord audioRecord;
    /* access modifiers changed from: private */
    public AudioState audioState = new AudioState();
    /* access modifiers changed from: private */
    public AudioTrack audioTrack;
    private BluetoothAdapter bluetoothAdapter;
    /* access modifiers changed from: private */
    public final Object bluetoothLock = new Object();
    /* access modifiers changed from: private */
    public BluetoothProfile bluetoothProfile;
    private final BluetoothProfile.ServiceListener bluetoothProfileListener = new BluetoothProfile.ServiceListener() {
        public void onServiceConnected(int type, BluetoothProfile profile) {
            if (1 == type) {
                BluetoothProfile unused = DefaultAudioDevice.this.bluetoothProfile = profile;
                List<BluetoothDevice> devices = profile.getConnectedDevices();
                DefaultAudioDevice.log.d("Service Proxy Connected", new Object[0]);
                if (!devices.isEmpty() && 2 == profile.getConnectionState(devices.get(0))) {
                    Intent intent = new Intent("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                    intent.putExtra("android.bluetooth.profile.extra.STATE", 2);
                    DefaultAudioDevice.this.btStatusReceiver.onReceive(DefaultAudioDevice.this.context, intent);
                }
            }
        }

        public void onServiceDisconnected(int type) {
            DefaultAudioDevice.log.d("Service Proxy Disconnected", new Object[0]);
        }
    };
    /* access modifiers changed from: private */
    public BluetoothState bluetoothState;
    /* access modifiers changed from: private */
    public final BroadcastReceiver btStatusReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                switch (intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1)) {
                    case 0:
                        DefaultAudioDevice.log.d("AUDIO_FOCUS: STATE_DISCONNECTED", new Object[0]);
                        synchronized (DefaultAudioDevice.this.bluetoothLock) {
                            if (BluetoothState.CONNECTED == DefaultAudioDevice.this.bluetoothState) {
                                DefaultAudioDevice.log.d("AUDIO_FOCUS : Bluetooth Headset: Disconnecting SCO", new Object[0]);
                                BluetoothState unused = DefaultAudioDevice.this.bluetoothState = BluetoothState.DISCONNECTED;
                                DefaultAudioDevice.this.audioManager.setBluetoothScoOn(false);
                                DefaultAudioDevice.this.stopBluetoothSco();
                                if (DefaultAudioDevice.this.audioManager.isWiredHeadsetOn()) {
                                    DefaultAudioDevice.this.setOutputType(OutputType.HEAD_PHONES);
                                    DefaultAudioDevice.this.audioManager.setSpeakerphoneOn(false);
                                } else {
                                    if (OutputType.SPEAKER_PHONE == DefaultAudioDevice.this.audioState.getLastOutputType()) {
                                        DefaultAudioDevice.this.setOutputType(OutputType.SPEAKER_PHONE);
                                        DefaultAudioDevice.this.audioManager.setSpeakerphoneOn(true);
                                    }
                                    if (OutputType.EAR_PIECE == DefaultAudioDevice.this.audioState.getLastOutputType()) {
                                        DefaultAudioDevice.this.setOutputType(OutputType.EAR_PIECE);
                                        DefaultAudioDevice.this.audioManager.setSpeakerphoneOn(false);
                                    }
                                }
                            }
                        }
                        return;
                    case 2:
                        DefaultAudioDevice.log.d("AUDIO_FOCUS : STATE_CONNECTED", new Object[0]);
                        synchronized (DefaultAudioDevice.this.bluetoothLock) {
                            if (BluetoothState.DISCONNECTED == DefaultAudioDevice.this.bluetoothState) {
                                DefaultAudioDevice.log.d("AUDIO_FOCUS: Bluetooth Headset: Connecting SCO", new Object[0]);
                                BluetoothState unused2 = DefaultAudioDevice.this.bluetoothState = BluetoothState.CONNECTED;
                                DefaultAudioDevice.this.audioState.setLastOutputType(DefaultAudioDevice.this.getOutputType());
                                DefaultAudioDevice.this.setOutputType(OutputType.BLUETOOTH);
                                DefaultAudioDevice.this.audioManager.setMode(3);
                                DefaultAudioDevice.this.audioManager.setBluetoothScoOn(true);
                                DefaultAudioDevice.this.startBluetoothSco();
                            }
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public int bufferedPlaySamples = 0;
    /* access modifiers changed from: private */
    public final Condition captureEvent = this.captureLock.newCondition();
    /* access modifiers changed from: private */
    public final ReentrantLock captureLock = new ReentrantLock(true);
    /* access modifiers changed from: private */
    public int captureSamplingRate = DEFAULT_SAMPLE_RATE;
    private BaseAudioDevice.AudioSettings captureSettings;
    Runnable captureThread = new Runnable() {
        public void run() {
            int samplesToRec = DefaultAudioDevice.this.captureSamplingRate / 100;
            try {
                Process.setThreadPriority(-19);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (!DefaultAudioDevice.this.shutdownCaptureThread) {
                DefaultAudioDevice.this.captureLock.lock();
                try {
                    if (!DefaultAudioDevice.this.isCapturing) {
                        DefaultAudioDevice.this.captureEvent.await();
                    } else if (DefaultAudioDevice.this.audioRecord == null) {
                        DefaultAudioDevice.this.captureLock.unlock();
                    } else {
                        int readBytes = DefaultAudioDevice.this.audioRecord.read(DefaultAudioDevice.this.tempBufRec, 0, (samplesToRec << 1) * 1);
                        if (readBytes >= 0) {
                            DefaultAudioDevice.this.recBuffer.rewind();
                            DefaultAudioDevice.this.recBuffer.put(DefaultAudioDevice.this.tempBufRec);
                            int samplesRead = (readBytes >> 1) / 1;
                            DefaultAudioDevice.this.captureLock.unlock();
                            DefaultAudioDevice.this.getAudioBus().writeCaptureData(DefaultAudioDevice.this.recBuffer, samplesRead);
                            int unused = DefaultAudioDevice.this.estimatedCaptureDelay = (samplesRead * 1000) / DefaultAudioDevice.this.captureSamplingRate;
                        } else {
                            switch (readBytes) {
                                case -3:
                                    throw new RuntimeException("Audio Capture Error: Invalid Operation (-3)");
                                case -2:
                                    throw new RuntimeException("Audio Capture Error: Bad Value (-2)");
                                default:
                                    throw new RuntimeException("Audio Capture Error(-1)");
                            }
                            BaseAudioDevice.publisherError(e);
                            return;
                        }
                    }
                } catch (Exception e2) {
                    BaseAudioDevice.publisherError(e2);
                    return;
                } finally {
                    DefaultAudioDevice.this.captureLock.unlock();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public Context context;
    private AcousticEchoCanceler echoCanceler;
    /* access modifiers changed from: private */
    public int estimatedCaptureDelay = 0;
    /* access modifiers changed from: private */
    public int estimatedRenderDelay = 0;
    private BroadcastReceiver headsetReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
                return;
            }
            if (intent.getIntExtra("state", 0) == 1) {
                DefaultAudioDevice.log.d("AUDIO_FOCUS: Headphones connected", new Object[0]);
                DefaultAudioDevice.this.audioState.setLastOutputType(DefaultAudioDevice.this.getOutputType());
                DefaultAudioDevice.this.setOutputType(OutputType.HEAD_PHONES);
                DefaultAudioDevice.this.audioManager.setSpeakerphoneOn(false);
                DefaultAudioDevice.this.audioManager.setBluetoothScoOn(false);
                return;
            }
            DefaultAudioDevice.log.d("AUDIO_FOCUS: Headphones disconnected", new Object[0]);
            if (DefaultAudioDevice.this.audioState.getLastOutputType() == OutputType.BLUETOOTH && BluetoothState.CONNECTED == DefaultAudioDevice.this.bluetoothState) {
                DefaultAudioDevice.this.audioManager.setBluetoothScoOn(true);
                DefaultAudioDevice.this.setOutputType(OutputType.BLUETOOTH);
                return;
            }
            if (DefaultAudioDevice.this.audioState.getLastOutputType() == OutputType.SPEAKER_PHONE) {
                DefaultAudioDevice.this.setOutputType(OutputType.SPEAKER_PHONE);
                DefaultAudioDevice.this.audioManager.setSpeakerphoneOn(true);
            }
            if (DefaultAudioDevice.this.audioState.getLastOutputType() == OutputType.EAR_PIECE) {
                DefaultAudioDevice.this.setOutputType(OutputType.EAR_PIECE);
                DefaultAudioDevice.this.audioManager.setSpeakerphoneOn(false);
            }
        }
    };
    /* access modifiers changed from: private */
    public volatile boolean isCapturing = false;
    /* access modifiers changed from: private */
    public volatile boolean isRendering = false;
    /* access modifiers changed from: private */
    public int lastPhoneState;
    private NoiseSuppressor noiseSuppressor;
    /* access modifiers changed from: private */
    public int outputSamplingRate = DEFAULT_SAMPLE_RATE;
    private PhoneStateListener phoneStateListener = new PhoneStateListener() {
        public void onCallStateChanged(int state, String incomingNumber) {
            Log.d("AUDIO_FOCUS", "Call State Changed");
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case 0:
                    Log.d("AUDIO_FOCUS", "CALL_STATE_IDLE");
                    if (DefaultAudioDevice.this.lastPhoneState == 2) {
                        DefaultAudioDevice.this.startRendererAndCapturer();
                        break;
                    }
                    break;
                case 1:
                    Log.d("AUDIO_FOCUS", "CALL_STATE_RINGING");
                    break;
                case 2:
                    Log.d("AUDIO_FOCUS", "CALL_STATE_OFFHOOK");
                    DefaultAudioDevice.this.stopRendererAndCapturer();
                    break;
                default:
                    Log.d("PhoneStateListener", "Unknown Phone State !");
                    break;
            }
            int unused = DefaultAudioDevice.this.lastPhoneState = state;
        }
    };
    private boolean phoneStateListenerRegistered;
    /* access modifiers changed from: private */
    public ByteBuffer playBuffer;
    /* access modifiers changed from: private */
    public int playPosition = 0;
    /* access modifiers changed from: private */
    public ByteBuffer recBuffer;
    private boolean receiverRegistered;
    /* access modifiers changed from: private */
    public final Condition renderEvent = this.rendererLock.newCondition();
    Runnable renderThread = new Runnable() {
        public void run() {
            int samplesToPlay = DefaultAudioDevice.this.samplesPerBuffer;
            try {
                Process.setThreadPriority(-19);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (!DefaultAudioDevice.this.shutdownRenderThread) {
                DefaultAudioDevice.this.rendererLock.lock();
                try {
                    if (!DefaultAudioDevice.this.isRendering) {
                        DefaultAudioDevice.this.renderEvent.await();
                    } else {
                        DefaultAudioDevice.this.rendererLock.unlock();
                        DefaultAudioDevice.this.playBuffer.clear();
                        int samplesRead = DefaultAudioDevice.this.getAudioBus().readRenderData(DefaultAudioDevice.this.playBuffer, samplesToPlay);
                        DefaultAudioDevice.this.rendererLock.lock();
                        if (DefaultAudioDevice.this.audioTrack == null || !DefaultAudioDevice.this.isRendering) {
                            DefaultAudioDevice.this.rendererLock.unlock();
                        } else {
                            int bytesRead = (samplesRead << 1) * 1;
                            DefaultAudioDevice.this.playBuffer.get(DefaultAudioDevice.this.tempBufPlay, 0, bytesRead);
                            int bytesWritten = DefaultAudioDevice.this.audioTrack.write(DefaultAudioDevice.this.tempBufPlay, 0, bytesRead);
                            if (bytesWritten > 0) {
                                int unused = DefaultAudioDevice.this.bufferedPlaySamples = DefaultAudioDevice.this.bufferedPlaySamples + ((bytesWritten >> 1) / 1);
                                int pos = DefaultAudioDevice.this.audioTrack.getPlaybackHeadPosition();
                                if (pos < DefaultAudioDevice.this.playPosition) {
                                    int unused2 = DefaultAudioDevice.this.playPosition = 0;
                                }
                                int unused3 = DefaultAudioDevice.this.bufferedPlaySamples = DefaultAudioDevice.this.bufferedPlaySamples - (pos - DefaultAudioDevice.this.playPosition);
                                int unused4 = DefaultAudioDevice.this.playPosition = pos;
                                int unused5 = DefaultAudioDevice.this.estimatedRenderDelay = (DefaultAudioDevice.this.bufferedPlaySamples * 1000) / DefaultAudioDevice.this.outputSamplingRate;
                                DefaultAudioDevice.this.rendererLock.unlock();
                            } else {
                                switch (bytesWritten) {
                                    case -3:
                                        throw new RuntimeException("Audio Renderer Error: Invalid Operation (-3)");
                                    case -2:
                                        throw new RuntimeException("Audio Renderer Error: Bad Value (-2)");
                                    default:
                                        throw new RuntimeException("Audio Renderer Error(-1)");
                                }
                                BaseAudioDevice.publisherError(e);
                                return;
                            }
                        }
                    }
                } catch (Exception e2) {
                    BaseAudioDevice.publisherError(e2);
                    return;
                } finally {
                    DefaultAudioDevice.this.rendererLock.unlock();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final ReentrantLock rendererLock = new ReentrantLock(true);
    private BaseAudioDevice.AudioSettings rendererSettings;
    /* access modifiers changed from: private */
    public int samplesPerBuffer = DEFAULT_SAMPLES_PER_BUFFER;
    private boolean scoReceiverRegistered;
    /* access modifiers changed from: private */
    public volatile boolean shutdownCaptureThread = false;
    /* access modifiers changed from: private */
    public volatile boolean shutdownRenderThread = false;
    private TelephonyManager telephonyManager;
    /* access modifiers changed from: private */
    public byte[] tempBufPlay;
    /* access modifiers changed from: private */
    public byte[] tempBufRec;
    private boolean wasCapturing = false;
    private boolean wasRendering = false;

    private enum BluetoothState {
        DISCONNECTED,
        CONNECTED
    }

    private enum OutputType {
        SPEAKER_PHONE,
        EAR_PIECE,
        HEAD_PHONES,
        BLUETOOTH
    }

    /* access modifiers changed from: private */
    public OutputType getOutputType() {
        return this.audioOutputType;
    }

    /* access modifiers changed from: private */
    public void setOutputType(OutputType type) {
        this.audioOutputType = type;
    }

    private static class AudioManagerMode {
        private int naquire = 0;
        private int oldMode = 0;

        AudioManagerMode() {
        }

        /* access modifiers changed from: package-private */
        public void acquireMode(AudioManager audioManager) {
            int i = this.naquire;
            this.naquire = i + 1;
            if (i == 0) {
                this.oldMode = audioManager.getMode();
                audioManager.setMode(3);
            }
        }

        /* access modifiers changed from: package-private */
        public void releaseMode(AudioManager audioManager) {
            int i = this.naquire - 1;
            this.naquire = i;
            if (i == 0) {
                audioManager.setMode(this.oldMode);
            }
        }
    }

    private static class AudioState {
        private int lastKnownFocusState;
        private OutputType lastOutputType;
        private int lastStreamVolume;

        private AudioState() {
            this.lastStreamVolume = 0;
            this.lastKnownFocusState = 0;
            this.lastOutputType = OutputType.SPEAKER_PHONE;
        }

        /* access modifiers changed from: package-private */
        public int getLastStreamVolume() {
            return this.lastStreamVolume;
        }

        /* access modifiers changed from: package-private */
        public void setLastStreamVolume(int lastStreamVolume2) {
            this.lastStreamVolume = lastStreamVolume2;
        }

        /* access modifiers changed from: package-private */
        public int getLastKnownFocusState() {
            return this.lastKnownFocusState;
        }

        /* access modifiers changed from: package-private */
        public void setLastKnownFocusState(int lastKnownFocusState2) {
            this.lastKnownFocusState = lastKnownFocusState2;
        }

        /* access modifiers changed from: package-private */
        public OutputType getLastOutputType() {
            return this.lastOutputType;
        }

        /* access modifiers changed from: package-private */
        public void setLastOutputType(OutputType lastOutputType2) {
            Log.d("AUDIO_FOCUS", "audioState mode set to " + lastOutputType2);
            this.lastOutputType = lastOutputType2;
        }
    }

    /* access modifiers changed from: private */
    public void startRendererAndCapturer() {
        if (this.wasRendering) {
            startRenderer();
        }
        if (this.wasCapturing) {
            startCapturer();
        }
    }

    /* access modifiers changed from: private */
    public void stopRendererAndCapturer() {
        if (this.isRendering) {
            stopRenderer();
            this.wasRendering = true;
        }
        if (this.isCapturing) {
            stopCapturer();
            this.wasCapturing = true;
        }
    }

    /* access modifiers changed from: private */
    public void forceConnectBluetooth() {
        Log.d("AUDIO_FOCUS", "Force Connect Bluetooth");
        synchronized (this.bluetoothLock) {
            if (getOutputType() == OutputType.BLUETOOTH) {
                this.bluetoothState = BluetoothState.DISCONNECTED;
                if (this.bluetoothAdapter != null) {
                    this.bluetoothAdapter.getProfileProxy(this.context, this.bluetoothProfileListener, 1);
                }
            }
        }
    }

    public DefaultAudioDevice(Context context2) {
        this.context = context2;
        try {
            this.recBuffer = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
        } catch (Exception e) {
            log.e(e.getMessage(), new Object[0]);
        }
        this.tempBufRec = new byte[DEFAULT_BUFFER_SIZE];
        this.audioManager = (AudioManager) context2.getSystemService("audio");
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.bluetoothProfile = null;
        int outputBufferSize = DEFAULT_BUFFER_SIZE;
        if (Build.VERSION.SDK_INT > 16) {
            try {
                this.outputSamplingRate = Integer.parseInt(this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE"));
                try {
                    this.samplesPerBuffer = Integer.parseInt(this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER"));
                    outputBufferSize = this.samplesPerBuffer * 2 * 1;
                } finally {
                    if (outputBufferSize == 0) {
                        this.samplesPerBuffer = DEFAULT_SAMPLES_PER_BUFFER;
                    }
                }
            } finally {
                if (this.outputSamplingRate == 0) {
                    this.outputSamplingRate = DEFAULT_SAMPLE_RATE;
                }
            }
        }
        try {
            this.playBuffer = ByteBuffer.allocateDirect(outputBufferSize);
        } catch (Exception e2) {
            log.e(e2.getMessage(), new Object[0]);
        }
        this.tempBufPlay = new byte[outputBufferSize];
        this.captureSettings = new BaseAudioDevice.AudioSettings(this.captureSamplingRate, 1);
        this.rendererSettings = new BaseAudioDevice.AudioSettings(this.outputSamplingRate, 1);
        try {
            this.telephonyManager = (TelephonyManager) context2.getSystemService("phone");
            registerPhoneStateListener();
        } catch (SecurityException e3) {
            e3.printStackTrace();
        }
        this.lastPhoneState = 0;
    }

    public boolean initCapturer() {
        this.audioManagerMode.acquireMode(this.audioManager);
        int recBufSize = AudioRecord.getMinBufferSize(this.captureSettings.getSampleRate(), 16, 2) * 2;
        if (this.noiseSuppressor != null) {
            this.noiseSuppressor.release();
            this.noiseSuppressor = null;
        }
        if (this.echoCanceler != null) {
            this.echoCanceler.release();
            this.echoCanceler = null;
        }
        if (this.audioRecord != null) {
            this.audioRecord.release();
            this.audioRecord = null;
        }
        try {
            this.audioRecord = new AudioRecord(7, this.captureSettings.getSampleRate(), 16, 2, recBufSize);
            if (NoiseSuppressor.isAvailable()) {
                this.noiseSuppressor = NoiseSuppressor.create(this.audioRecord.getAudioSessionId());
            }
            if (AcousticEchoCanceler.isAvailable()) {
                this.echoCanceler = AcousticEchoCanceler.create(this.audioRecord.getAudioSessionId());
            }
            if (this.audioRecord.getState() != 1) {
                throw new RuntimeException("Audio capture is not initialized " + this.captureSettings.getSampleRate());
            }
            this.shutdownCaptureThread = false;
            new Thread(this.captureThread).start();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean destroyCapturer() {
        this.captureLock.lock();
        if (this.echoCanceler != null) {
            this.echoCanceler.release();
            this.echoCanceler = null;
        }
        if (this.noiseSuppressor != null) {
            this.noiseSuppressor.release();
            this.noiseSuppressor = null;
        }
        this.audioRecord.release();
        this.audioRecord = null;
        this.shutdownCaptureThread = true;
        this.captureEvent.signal();
        this.captureLock.unlock();
        this.audioManagerMode.releaseMode(this.audioManager);
        return true;
    }

    public int getEstimatedCaptureDelay() {
        return this.estimatedCaptureDelay;
    }

    public boolean startCapturer() {
        Log.d("AUDIO_FOCUS", "Start Capturer");
        try {
            this.audioRecord.startRecording();
            this.captureLock.lock();
            this.isCapturing = true;
            this.captureEvent.signal();
            this.captureLock.unlock();
            return true;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean stopCapturer() {
        Log.d("AUDIO_FOCUS", "Stop Capturer");
        this.captureLock.lock();
        try {
            if (this.audioRecord.getRecordingState() == 3) {
                this.audioRecord.stop();
            }
            this.isCapturing = false;
            this.captureLock.unlock();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } catch (Throwable th) {
            this.isCapturing = false;
            this.captureLock.unlock();
            throw th;
        }
    }

    public boolean initRenderer() {
        int i;
        if (this.audioManager.requestAudioFocus(this.afChangeListener, 0, 1) == 1) {
            Log.d("AUDIO_FOCUS", "Audio Focus request GRANTED !");
            this.bluetoothState = BluetoothState.DISCONNECTED;
            this.audioManagerMode.acquireMode(this.audioManager);
            enableBluetoothEvents();
            int minPlayBufSize = AudioTrack.getMinBufferSize(this.rendererSettings.getSampleRate(), 4, 2);
            if (this.audioTrack != null) {
                this.audioTrack.release();
                this.audioTrack = null;
            }
            try {
                int sampleRate = this.rendererSettings.getSampleRate();
                if (minPlayBufSize >= 6000) {
                    i = minPlayBufSize;
                } else {
                    i = minPlayBufSize * 2;
                }
                this.audioTrack = new AudioTrack(0, sampleRate, 4, 2, i, 1);
                if (this.audioTrack.getState() != 1) {
                    throw new RuntimeException("Audio renderer not initialized " + this.rendererSettings.getSampleRate());
                }
                this.bufferedPlaySamples = 0;
                this.shutdownRenderThread = false;
                new Thread(this.renderThread).start();
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            Log.e("AUDIO_FOCUS", "Audio Focus request DENIED !");
            return false;
        }
    }

    private void destroyAudioTrack() {
        this.rendererLock.lock();
        this.audioTrack.release();
        this.audioTrack = null;
        this.shutdownRenderThread = true;
        this.renderEvent.signal();
        this.rendererLock.unlock();
    }

    public boolean destroyRenderer() {
        destroyAudioTrack();
        disableBluetoothEvents();
        unregisterHeadsetReceiver();
        this.audioManager.setSpeakerphoneOn(false);
        this.audioManagerMode.releaseMode(this.audioManager);
        this.audioManager.abandonAudioFocus(this.afChangeListener);
        return true;
    }

    public int getEstimatedRenderDelay() {
        return this.estimatedRenderDelay;
    }

    public boolean startRenderer() {
        Log.d("AUDIO_FOCUS", "Start Renderer");
        synchronized (this.bluetoothLock) {
            if (BluetoothState.CONNECTED != this.bluetoothState) {
                if (this.audioManager.isWiredHeadsetOn()) {
                    log.d("Turn off Speaker phone", new Object[0]);
                    this.audioManager.setSpeakerphoneOn(false);
                } else {
                    log.d("Turn on Speaker phone", new Object[0]);
                    if (getOutputType() == OutputType.SPEAKER_PHONE) {
                        this.audioManager.setSpeakerphoneOn(true);
                    }
                }
            }
        }
        try {
            this.audioTrack.play();
            this.rendererLock.lock();
            this.isRendering = true;
            this.renderEvent.signal();
            this.rendererLock.unlock();
            registerBtReceiver();
            registerHeadsetReceiver();
            return true;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean stopRenderer() {
        Log.d("AUDIO_FOCUS", "Stop Renderer");
        this.rendererLock.lock();
        try {
            if (this.audioTrack.getPlayState() == 3) {
                this.audioTrack.stop();
            }
            this.audioTrack.flush();
            this.isRendering = false;
            this.rendererLock.unlock();
            unregisterHeadsetReceiver();
            unregisterBtReceiver();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } catch (Throwable th) {
            this.isRendering = false;
            this.rendererLock.unlock();
            throw th;
        }
    }

    public BaseAudioDevice.AudioSettings getCaptureSettings() {
        return this.captureSettings;
    }

    public BaseAudioDevice.AudioSettings getRenderSettings() {
        return this.rendererSettings;
    }

    public boolean setOutputMode(BaseAudioDevice.OutputMode mode) {
        Log.d("AUDIO_FOCUS", "outputmode set to : " + mode);
        super.setOutputMode(mode);
        if (BaseAudioDevice.OutputMode.SpeakerPhone == mode) {
            setOutputType(OutputType.SPEAKER_PHONE);
            this.audioState.setLastOutputType(OutputType.SPEAKER_PHONE);
            this.audioManager.setSpeakerphoneOn(true);
        } else {
            setOutputType(OutputType.EAR_PIECE);
            this.audioState.setLastOutputType(OutputType.EAR_PIECE);
            this.audioManager.setSpeakerphoneOn(false);
        }
        return true;
    }

    private void registerPhoneStateListener() {
        if (!this.phoneStateListenerRegistered) {
            if (this.telephonyManager != null) {
                this.telephonyManager.listen(this.phoneStateListener, 32);
            }
            this.phoneStateListenerRegistered = true;
        }
    }

    private void registerHeadsetReceiver() {
        if (!this.receiverRegistered) {
            this.context.registerReceiver(this.headsetReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
            this.receiverRegistered = true;
        }
    }

    private void unregisterHeadsetReceiver() {
        if (this.receiverRegistered) {
            this.context.unregisterReceiver(this.headsetReceiver);
            this.receiverRegistered = false;
        }
    }

    private void registerBtReceiver() {
        if (!this.scoReceiverRegistered) {
            this.context.registerReceiver(this.btStatusReceiver, new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED"));
            this.scoReceiverRegistered = true;
        }
    }

    private void unregisterBtReceiver() {
        if (this.scoReceiverRegistered) {
            this.context.unregisterReceiver(this.btStatusReceiver);
            this.scoReceiverRegistered = false;
        }
    }

    public synchronized void onPause() {
        this.audioState.setLastOutputType(getOutputType());
        unregisterBtReceiver();
        unregisterHeadsetReceiver();
    }

    public synchronized void onResume() {
        registerBtReceiver();
        registerHeadsetReceiver();
        if (this.isRendering && this.audioState.getLastOutputType() == OutputType.SPEAKER_PHONE && !this.audioManager.isWiredHeadsetOn()) {
            Log.d("AUDIO_FOCUS", "onResume() - Set Speaker Phone ON True");
            this.audioManager.setSpeakerphoneOn(true);
        }
        setOutputType(this.audioState.getLastOutputType());
        forceConnectBluetooth();
    }

    private void enableBluetoothEvents() {
        if (this.audioManager.isBluetoothScoAvailableOffCall()) {
            registerBtReceiver();
            if (this.bluetoothAdapter != null) {
                this.bluetoothAdapter.getProfileProxy(this.context, this.bluetoothProfileListener, 1);
            }
        }
    }

    private void disableBluetoothEvents() {
        if (!(this.bluetoothProfile == null || this.bluetoothAdapter == null)) {
            this.bluetoothAdapter.closeProfileProxy(1, this.bluetoothProfile);
        }
        unregisterBtReceiver();
        Intent intent = new Intent("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.STATE", 0);
        this.btStatusReceiver.onReceive(this.context, intent);
    }

    /* access modifiers changed from: private */
    public void startBluetoothSco() {
        try {
            this.audioManager.startBluetoothSco();
        } catch (NullPointerException e) {
            log.d("Failed to start the BT SCO. In Android 5.0 calling [start|stop]BluetoothSco produces a NPE in some devices", new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void stopBluetoothSco() {
        try {
            this.audioManager.stopBluetoothSco();
        } catch (NullPointerException e) {
            log.d("Failed to start the BT SCO. In Android 5.0 calling [start|stop]BluetoothSco produces a NPE in some devices", new Object[0]);
        }
    }
}
