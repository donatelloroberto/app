package com.amazon.device.ads;

import com.amazon.device.ads.AdvertisingIdentifier;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.SISRegisterEventRequest;
import com.amazon.device.ads.SISRequestor;
import com.amazon.device.ads.ThreadUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

class SISRegistration {
    private static final String LOGTAG = SISRegistration.class.getSimpleName();
    protected static final long SIS_CHECKIN_INTERVAL = 86400000;
    private static final String SIS_LAST_CHECKIN_PREF_NAME = "amzn-ad-sis-last-checkin";
    private static final ThreadUtils.SingleThreadScheduler singleThreadScheduler = new ThreadUtils.SingleThreadScheduler();
    private final AdvertisingIdentifier advertisingIdentifier;
    private final AppEventRegistrationHandler appEventRegistrationHandler;
    private final Configuration configuration;
    private final DebugProperties debugProperties;
    private final ThreadUtils.RunnableExecutor executor;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Settings settings;
    private final SISRegisterEventRequest.SISRequestFactory sisRequestFactory;
    private final SISRequestor.SISRequestorFactory sisRequestorFactory;
    private final SystemTime systemTime;
    private final ThreadUtils.ThreadVerify threadVerify;

    public SISRegistration() {
        this(new SISRegisterEventRequest.SISRequestFactory(), new SISRequestor.SISRequestorFactory(), new AdvertisingIdentifier(), MobileAdsInfoStore.getInstance(), Configuration.getInstance(), Settings.getInstance(), AppEventRegistrationHandler.getInstance(), new SystemTime(), singleThreadScheduler, new ThreadUtils.ThreadVerify(), new MobileAdsLoggerFactory(), DebugProperties.getInstance());
    }

    SISRegistration(SISRegisterEventRequest.SISRequestFactory sisRequestFactory2, SISRequestor.SISRequestorFactory sisRequestorFactory2, AdvertisingIdentifier advertisingIdentifier2, MobileAdsInfoStore infoStore2, Configuration configuration2, Settings settings2, AppEventRegistrationHandler appEventRegistrationHandler2, SystemTime systemTime2, ThreadUtils.RunnableExecutor runnableExecutor, ThreadUtils.ThreadVerify threadVerify2, MobileAdsLoggerFactory loggerFactory, DebugProperties debugProperties2) {
        this.sisRequestFactory = sisRequestFactory2;
        this.sisRequestorFactory = sisRequestorFactory2;
        this.advertisingIdentifier = advertisingIdentifier2;
        this.infoStore = infoStore2;
        this.configuration = configuration2;
        this.settings = settings2;
        this.appEventRegistrationHandler = appEventRegistrationHandler2;
        this.systemTime = systemTime2;
        this.executor = runnableExecutor;
        this.threadVerify = threadVerify2;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.debugProperties = debugProperties2;
    }

    /* access modifiers changed from: private */
    public MobileAdsLogger getLogger() {
        return this.logger;
    }

    /* access modifiers changed from: protected */
    public boolean canRegister(long currentTime) {
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        if (exceededCheckinInterval(currentTime) || registrationInfo.shouldGetNewSISDeviceIdentifer() || registrationInfo.shouldGetNewSISRegistration() || this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_SHOULD_REGISTER_SIS, false).booleanValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUpdateDeviceInfo() {
        return this.infoStore.getRegistrationInfo().isRegisteredWithSIS();
    }

    public void registerApp() {
        this.executor.execute(new Runnable() {
            public void run() {
                SISRegistration.this.waitForConfigurationThenBeginRegistration();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void waitForConfigurationThenBeginRegistration() {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicBoolean canRegister = new AtomicBoolean(false);
        this.configuration.queueConfigurationListener(new Configuration.ConfigurationListener() {
            public void onConfigurationReady() {
                canRegister.set(true);
                latch.countDown();
            }

            public void onConfigurationFailure() {
                SISRegistration.this.getLogger().w("Configuration fetching failed so device registration will not proceed.");
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
        if (canRegister.get()) {
            registerAppWorker();
        }
    }

    /* access modifiers changed from: package-private */
    public void registerAppWorker() {
        long currentTime = this.systemTime.currentTimeMillis();
        if (this.advertisingIdentifier.getAdvertisingIdentifierInfo().canDo() && canRegister(currentTime)) {
            putLastSISCheckin(currentTime);
            if (shouldUpdateDeviceInfo()) {
                updateDeviceInfo(this.advertisingIdentifier);
            } else {
                register(this.advertisingIdentifier);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean exceededCheckinInterval(long now) {
        return now - getLastSISCheckin() > SIS_CHECKIN_INTERVAL;
    }

    /* access modifiers changed from: protected */
    public void register(AdvertisingIdentifier advertisingIdentifier2) {
        SISRequest generateDIDRequest = this.sisRequestFactory.createDeviceRequest(SISRegisterEventRequest.SISRequestType.GENERATE_DID).setAdvertisingIdentifier(advertisingIdentifier2);
        SISRequestorCallback sisRequestorCallback = new RegisterEventsSISRequestorCallback(this);
        this.sisRequestorFactory.createSISRequestor(sisRequestorCallback, generateDIDRequest).startCallSIS();
    }

    /* access modifiers changed from: protected */
    public void updateDeviceInfo(AdvertisingIdentifier advertisingIdentifier2) {
        SISRequest updateDeviceInfoRequest = this.sisRequestFactory.createDeviceRequest(SISRegisterEventRequest.SISRequestType.UPDATE_DEVICE_INFO).setAdvertisingIdentifier(advertisingIdentifier2);
        SISRequestorCallback sisRequestorCallback = new RegisterEventsSISRequestorCallback(this);
        this.sisRequestorFactory.createSISRequestor(sisRequestorCallback, updateDeviceInfoRequest).startCallSIS();
    }

    /* access modifiers changed from: protected */
    public long getLastSISCheckin() {
        return this.settings.getLong(SIS_LAST_CHECKIN_PREF_NAME, 0);
    }

    private void putLastSISCheckin(long currentTime) {
        this.settings.putLong(SIS_LAST_CHECKIN_PREF_NAME, currentTime);
    }

    /* access modifiers changed from: protected */
    public void registerEvents() {
        JSONArray appEvents;
        if (this.threadVerify.isOnMainThread()) {
            getLogger().e("Registering events must be done on a background thread.");
            return;
        }
        AdvertisingIdentifier.Info advertisingIdentifierInfo = this.advertisingIdentifier.getAdvertisingIdentifierInfo();
        if (advertisingIdentifierInfo.hasSISDeviceIdentifier() && (appEvents = this.appEventRegistrationHandler.getAppEventsJSONArray()) != null) {
            SISRegisterEventRequest registerEventRequest = this.sisRequestFactory.createRegisterEventRequest(advertisingIdentifierInfo, appEvents);
            this.sisRequestorFactory.createSISRequestor(registerEventRequest).startCallSIS();
        }
    }

    protected static class RegisterEventsSISRequestorCallback implements SISRequestorCallback {
        private final SISRegistration sisRegistration;

        public RegisterEventsSISRequestorCallback(SISRegistration sisRegistration2) {
            this.sisRegistration = sisRegistration2;
        }

        public void onSISCallComplete() {
            this.sisRegistration.registerEvents();
        }
    }
}
