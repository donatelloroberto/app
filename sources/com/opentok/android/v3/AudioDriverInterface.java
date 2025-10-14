package com.opentok.android.v3;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import com.opentok.android.v3.AudioDriver;
import java.io.InputStream;
import java.io.OutputStream;

@Keep
public interface AudioDriverInterface {
    boolean destroyCapturer();

    boolean destroyRenderer();

    AudioDriver.AudioSettings getCaptureSettings();

    int getEstimatedCaptureDelay();

    int getEstimatedRenderDelay();

    AudioDriver.AudioSettings getRenderSettings();

    boolean initCapturer();

    void initDriver(Context context, AudioDriver.AudioError audioError);

    boolean initRenderer();

    void pause();

    void resume();

    boolean setOutputMode(AudioDriver.OutputMode outputMode);

    void shutdownDriver();

    boolean startCapturer(@NonNull OutputStream outputStream);

    boolean startRenderer(@NonNull InputStream inputStream);

    boolean stopCapturer();

    boolean stopRenderer();
}
