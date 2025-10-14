package com.opentok.android.v3;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;
import android.support.annotation.NonNull;
import com.opentok.android.v3.AudioDriver;
import com.opentok.android.v3.DefaultAudioDriver;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

class DefaultAudioInputFactory implements DefaultAudioDriver.AudioInputFactory {
    private final AudioDriver.AudioSettings settings = new AudioDriver.AudioSettings(44100, 1, 2);

    DefaultAudioInputFactory() {
    }

    public InputStream create() {
        return new AudioInputStream();
    }

    public AudioDriver.AudioSettings getSettings() {
        return this.settings;
    }

    private static class AudioInputStream extends InputStream {
        static final int CHANNEL_TYPE = 16;
        static final int MIN_BUFFER_SZ = AudioRecord.getMinBufferSize(SAMPLE_RATE, 16, 2);
        static final int SAMPLE_RATE = 44100;
        private AcousticEchoCanceler echoCanceler;
        private NoiseSuppressor noiseSuppressor;
        private AudioRecord track = new AudioRecord(7, SAMPLE_RATE, 16, 2, MIN_BUFFER_SZ);

        AudioInputStream() {
            if (NoiseSuppressor.isAvailable()) {
                this.noiseSuppressor = NoiseSuppressor.create(this.track.getAudioSessionId());
            }
            if (AcousticEchoCanceler.isAvailable()) {
                this.echoCanceler = AcousticEchoCanceler.create(this.track.getAudioSessionId());
            }
            this.track.startRecording();
        }

        public int read() throws IOException {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1);
            int size = this.track.read(buffer, 1);
            switch (size) {
                case -6:
                    return -1;
                case -3:
                case -2:
                case -1:
                    throw new IOException("AudioRecord Error: " + size);
                default:
                    return buffer.get(0);
            }
        }

        public int read(@NonNull byte[] b, int off, int len) throws IOException {
            int size = this.track.read(b, off, len);
            switch (size) {
                case -6:
                    return -1;
                case -3:
                case -2:
                case -1:
                    throw new IOException("AudioRecord Error: " + size);
                default:
                    return size;
            }
        }

        public void close() throws IOException {
            this.track.stop();
            this.track.release();
            if (this.echoCanceler != null) {
                this.echoCanceler.release();
                this.echoCanceler = null;
            }
            if (this.noiseSuppressor != null) {
                this.noiseSuppressor.release();
                this.noiseSuppressor = null;
            }
        }
    }
}
