package com.opentok.android.v3.test;

import com.opentok.android.v3.Publisher;
import com.opentok.android.v3.Session;
import com.opentok.android.v3.Subscriber;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import com.opentok.android.v3.loader.Loader;

class Test implements TestInterface {
    private static Test instance = null;
    private static final LogInterface log = OtLog.LogToken("[test]");

    private static native void generateArbitraryErrorPublisherNative(Publisher publisher, int i);

    private static native void generateArbitraryErrorSubscriberNative(Subscriber subscriber, int i);

    private static native Session.Builder.IceServer[] getIceConfigNative(Session session);

    private static native void reconnectNative(Session session);

    private static native void registerNatives();

    private static native void setOTKitLogVerifyNative(boolean z);

    private static native void setPreferH264CodecNative(boolean z);

    private static native void setPublisherVGASimulcastModeNative(Publisher publisher, boolean z);

    private static native void setUseMediaCodecFactoriesNative(boolean z);

    public static TestInterface getInstance() {
        if (instance == null) {
            instance = new Test();
        }
        return instance;
    }

    public void simulateReconnect(Session session) {
        reconnectNative(session);
    }

    public void forceSetProxy(String host, int port) {
    }

    public Session.Builder.IceServer[] getIceServers(Session session) {
        return getIceConfigNative(session);
    }

    public void setUseMediaCodecFactories(boolean value) {
        log.d("setUseMediaCodecFactories(...) called", new Object[0]);
        setUseMediaCodecFactoriesNative(value);
    }

    public void setPreferH264Codec(boolean value) {
        log.d("setPreferH264Codec(...) called", new Object[0]);
        setPreferH264CodecNative(value);
    }

    public void generateArbitraryErrorPublisher(Publisher publisher, int errorcode) {
        generateArbitraryErrorPublisherNative(publisher, errorcode);
    }

    public void generateArbitraryErrorSubscriber(Subscriber subscriber, int errorcode) {
        generateArbitraryErrorSubscriberNative(subscriber, errorcode);
    }

    public void setPublisherVGASimulcastMode(Publisher publisher, boolean enable) {
        log.d("setPublisherVGASimulcastMode(...) called", new Object[0]);
        setPublisherVGASimulcastModeNative(publisher, enable);
    }

    public void setOTKitLogVerify(boolean value) {
        log.d("setOTKitLogVerify(...) called", new Object[0]);
        setOTKitLogVerifyNative(value);
    }

    Test() {
    }

    static {
        Loader.load();
        registerNatives();
    }
}
