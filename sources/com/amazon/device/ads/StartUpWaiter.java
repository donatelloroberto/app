package com.amazon.device.ads;

import android.util.SparseArray;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.Settings;
import com.amazon.device.ads.ThreadUtils;

abstract class StartUpWaiter implements Configuration.ConfigurationListener, Settings.SettingsListener {
    static final int CALLBACK_ON_MAIN_THREAD = 0;
    static final int DEFAULT = 1;
    private static final SparseArray<ThreadUtils.RunnableExecutor> executors = new SparseArray<>();
    private int callbackType = 1;
    private final Configuration configuration;
    private final Settings settings;

    /* access modifiers changed from: protected */
    public abstract void startUpFailed();

    /* access modifiers changed from: protected */
    public abstract void startUpReady();

    static {
        putRunnableExecutor(0, new ThreadUtils.MainThreadScheduler());
        putRunnableExecutor(1, new ThreadUtils.ThreadPoolScheduler());
    }

    public StartUpWaiter(Settings settings2, Configuration configuration2) {
        this.settings = settings2;
        this.configuration = configuration2;
    }

    public void start() {
        this.settings.listenForSettings(this);
    }

    public void startAndCallbackOnMainThread() {
        this.callbackType = 0;
        start();
    }

    public void settingsLoaded() {
        this.configuration.queueConfigurationListener(this);
    }

    public void onConfigurationReady() {
        onFinished(new Runnable() {
            public void run() {
                StartUpWaiter.this.startUpReady();
            }
        });
    }

    public void onConfigurationFailure() {
        onFinished(new Runnable() {
            public void run() {
                StartUpWaiter.this.startUpFailed();
            }
        });
    }

    private void onFinished(Runnable proc) {
        getExecutor(this.callbackType).execute(proc);
    }

    /* access modifiers changed from: package-private */
    public Settings getSettings() {
        return this.settings;
    }

    /* access modifiers changed from: package-private */
    public Configuration getConfiguration() {
        return this.configuration;
    }

    static ThreadUtils.RunnableExecutor getExecutor(int callbackType2) {
        return executors.get(callbackType2, executors.get(1));
    }

    static void putRunnableExecutor(int callbackType2, ThreadUtils.RunnableExecutor executor) {
        if (executor == null) {
            executors.remove(callbackType2);
        } else {
            executors.put(callbackType2, executor);
        }
    }
}
