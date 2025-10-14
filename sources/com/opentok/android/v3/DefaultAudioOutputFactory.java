package com.opentok.android.v3;

import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.support.annotation.NonNull;
import com.opentok.android.v3.AudioDriver;
import com.opentok.android.v3.DefaultAudioDriver;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;

class DefaultAudioOutputFactory implements DefaultAudioDriver.AudioOutputFactory {
    private final int audioBufferSize;
    private final AudioDriver.AudioSettings settings;

    DefaultAudioOutputFactory(@NonNull AudioManager audioManager) {
        int sampleRate = AudioTrack.getNativeOutputSampleRate(1);
        int bufferSize = AudioTrack.getMinBufferSize(sampleRate, 4, 2);
        if (Build.VERSION.SDK_INT > 16) {
            try {
                sampleRate = Integer.parseInt(audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE"));
            } catch (Exception e) {
            }
        }
        this.audioBufferSize = bufferSize < 6000 ? bufferSize * 2 : bufferSize;
        this.settings = new AudioDriver.AudioSettings(sampleRate, 1, 2);
    }

    public OutputStream create() {
        return new AudioOutputStream(this.settings.samplingRate, this.audioBufferSize);
    }

    public AudioDriver.AudioSettings getSettings() {
        return this.settings;
    }

    private static class AudioOutputStream extends OutputStream {
        private AudioTrack track;

        AudioOutputStream(int sampleRate, int bufferSize) {
            this.track = new AudioTrack(0, sampleRate, 4, 2, bufferSize, 1);
            this.track.play();
        }

        public void write(int b) throws IOException {
            int size = this.track.write(new byte[]{(byte) b}, 0, 1);
            switch (size) {
                case -6:
                    throw new EOFException("AudioTrack Dead");
                case -3:
                case -2:
                case -1:
                    throw new IOException("AudioTrack Error: " + size);
                default:
                    return;
            }
        }

        public void write(@NonNull byte[] b, int off, int len) throws IOException {
            while (len > 0) {
                int size = this.track.write(b, off, len);
                switch (size) {
                    case -6:
                        throw new EOFException("AudioTrack Dead");
                    case -3:
                    case -2:
                    case -1:
                        throw new IOException("AudioTrack Error: " + size);
                    default:
                        len -= size;
                }
            }
        }

        public void flush() throws IOException {
            this.track.flush();
        }

        public void close() throws IOException {
            this.track.stop();
            this.track.release();
        }
    }
}
