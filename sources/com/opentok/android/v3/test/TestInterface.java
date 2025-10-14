package com.opentok.android.v3.test;

import com.opentok.android.v3.Publisher;
import com.opentok.android.v3.Session;
import com.opentok.android.v3.Subscriber;

public interface TestInterface {
    void forceSetProxy(String str, int i);

    void generateArbitraryErrorPublisher(Publisher publisher, int i);

    void generateArbitraryErrorSubscriber(Subscriber subscriber, int i);

    Session.Builder.IceServer[] getIceServers(Session session);

    void setOTKitLogVerify(boolean z);

    void setPreferH264Codec(boolean z);

    void setPublisherVGASimulcastMode(Publisher publisher, boolean z);

    void setUseMediaCodecFactories(boolean z);

    void simulateReconnect(Session session);
}
