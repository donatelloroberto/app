package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.annotations.PublicApi;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.internal.DefaultIdTokenListenersCountChangedListener;
import com.google.firebase.internal.InternalTokenProvider;
import com.google.firebase.internal.InternalTokenResult;
import com.google.firebase.platforminfo.DefaultUserAgentPublisher;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;

@PublicApi
/* compiled from: com.google.firebase:firebase-common@@16.1.0 */
public class FirebaseApp {
    private static final List<String> API_INITIALIZERS = Arrays.asList(new String[]{AUTH_CLASSNAME, IID_CLASSNAME});
    private static final String AUTH_CLASSNAME = "com.google.firebase.auth.FirebaseAuth";
    private static final Set<String> CORE_CLASSES = Collections.emptySet();
    private static final String CRASH_CLASSNAME = "com.google.firebase.crash.FirebaseCrash";
    @VisibleForTesting
    static final String DATA_COLLECTION_DEFAULT_ENABLED = "firebase_data_collection_default_enabled";
    private static final List<String> DEFAULT_APP_API_INITITALIZERS = Collections.singletonList(CRASH_CLASSNAME);
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    private static final List<String> DEFAULT_CONTEXT_API_INITITALIZERS = Arrays.asList(new String[]{MEASUREMENT_CLASSNAME});
    private static final List<String> DIRECT_BOOT_COMPATIBLE_API_INITIALIZERS = Arrays.asList(new String[0]);
    private static final String FIREBASE_ANDROID = "fire-android";
    private static final String FIREBASE_APP_PREFS = "com.google.firebase.common.prefs:";
    private static final String FIREBASE_COMMON = "fire-core";
    private static final String IID_CLASSNAME = "com.google.firebase.iid.FirebaseInstanceId";
    @GuardedBy("LOCK")
    static final Map<String, FirebaseApp> INSTANCES = new ArrayMap();
    /* access modifiers changed from: private */
    public static final Object LOCK = new Object();
    private static final String LOG_TAG = "FirebaseApp";
    private static final String MEASUREMENT_CLASSNAME = "com.google.android.gms.measurement.AppMeasurement";
    private static final Executor UI_EXECUTOR = new UiExecutor();
    private final Context applicationContext;
    /* access modifiers changed from: private */
    public final AtomicBoolean automaticResourceManagementEnabled = new AtomicBoolean(false);
    private final List<BackgroundStateChangeListener> backgroundStateChangeListeners = new CopyOnWriteArrayList();
    private final ComponentRuntime componentRuntime;
    private final AtomicBoolean dataCollectionDefaultEnabled;
    private final AtomicBoolean deleted = new AtomicBoolean();
    private final List<IdTokenListener> idTokenListeners = new CopyOnWriteArrayList();
    private IdTokenListenersCountChangedListener idTokenListenersCountChangedListener;
    private final List<FirebaseAppLifecycleListener> lifecycleListeners = new CopyOnWriteArrayList();
    private final String name;
    private final FirebaseOptions options;
    private final Publisher publisher;
    private final SharedPreferences sharedPreferences;
    private InternalTokenProvider tokenProvider;

    @KeepForSdk
    /* compiled from: com.google.firebase:firebase-common@@16.1.0 */
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z);
    }

    @KeepForSdk
    @Deprecated
    /* compiled from: com.google.firebase:firebase-common@@16.1.0 */
    public interface IdTokenListener {
        @KeepForSdk
        void onIdTokenChanged(@NonNull InternalTokenResult internalTokenResult);
    }

    @KeepForSdk
    @Deprecated
    /* compiled from: com.google.firebase:firebase-common@@16.1.0 */
    public interface IdTokenListenersCountChangedListener {
        @KeepForSdk
        void onListenerCountChanged(int i);
    }

    @PublicApi
    @NonNull
    public Context getApplicationContext() {
        checkNotDeleted();
        return this.applicationContext;
    }

    @PublicApi
    @NonNull
    public String getName() {
        checkNotDeleted();
        return this.name;
    }

    @PublicApi
    @NonNull
    public FirebaseOptions getOptions() {
        checkNotDeleted();
        return this.options;
    }

    public boolean equals(Object o) {
        if (!(o instanceof FirebaseApp)) {
            return false;
        }
        return this.name.equals(((FirebaseApp) o).getName());
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("options", this.options).toString();
    }

    @PublicApi
    public static List<FirebaseApp> getApps(Context context) {
        ArrayList arrayList;
        synchronized (LOCK) {
            arrayList = new ArrayList(INSTANCES.values());
        }
        return arrayList;
    }

    @PublicApi
    @NonNull
    public static FirebaseApp getInstance() {
        FirebaseApp defaultApp;
        synchronized (LOCK) {
            defaultApp = INSTANCES.get(DEFAULT_APP_NAME);
            if (defaultApp == null) {
                throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
            }
        }
        return defaultApp;
    }

    @PublicApi
    @NonNull
    public static FirebaseApp getInstance(@NonNull String name2) {
        FirebaseApp firebaseApp;
        String availableAppNamesMessage;
        synchronized (LOCK) {
            firebaseApp = INSTANCES.get(normalize(name2));
            if (firebaseApp == null) {
                List<String> availableAppNames = getAllAppNames();
                if (availableAppNames.isEmpty()) {
                    availableAppNamesMessage = "";
                } else {
                    availableAppNamesMessage = "Available app names: " + TextUtils.join(", ", availableAppNames);
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{name2, availableAppNamesMessage}));
            }
        }
        return firebaseApp;
    }

    @Nullable
    @PublicApi
    public static FirebaseApp initializeApp(@NonNull Context context) {
        FirebaseApp initializeApp;
        synchronized (LOCK) {
            if (INSTANCES.containsKey(DEFAULT_APP_NAME)) {
                initializeApp = getInstance();
            } else {
                FirebaseOptions firebaseOptions = FirebaseOptions.fromResource(context);
                if (firebaseOptions == null) {
                    Log.d(LOG_TAG, "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
                    initializeApp = null;
                } else {
                    initializeApp = initializeApp(context, firebaseOptions);
                }
            }
        }
        return initializeApp;
    }

    @PublicApi
    @NonNull
    public static FirebaseApp initializeApp(@NonNull Context context, @NonNull FirebaseOptions options2) {
        return initializeApp(context, options2, DEFAULT_APP_NAME);
    }

    @PublicApi
    @NonNull
    public static FirebaseApp initializeApp(@NonNull Context context, @NonNull FirebaseOptions options2, @NonNull String name2) {
        Context applicationContext2;
        FirebaseApp firebaseApp;
        GlobalBackgroundStateListener.ensureBackgroundStateListenerRegistered(context);
        String normalizedName = normalize(name2);
        if (context.getApplicationContext() == null) {
            applicationContext2 = context;
        } else {
            applicationContext2 = context.getApplicationContext();
        }
        synchronized (LOCK) {
            Preconditions.checkState(!INSTANCES.containsKey(normalizedName), "FirebaseApp name " + normalizedName + " already exists!");
            Preconditions.checkNotNull(applicationContext2, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(applicationContext2, normalizedName, options2);
            INSTANCES.put(normalizedName, firebaseApp);
        }
        firebaseApp.initializeAllApis();
        return firebaseApp;
    }

    @KeepForSdk
    @Deprecated
    public void setTokenProvider(@NonNull InternalTokenProvider tokenProvider2) {
        this.tokenProvider = (InternalTokenProvider) Preconditions.checkNotNull(tokenProvider2);
    }

    @KeepForSdk
    @Deprecated
    public void setIdTokenListenersCountChangedListener(@NonNull IdTokenListenersCountChangedListener listener) {
        this.idTokenListenersCountChangedListener = (IdTokenListenersCountChangedListener) Preconditions.checkNotNull(listener);
        this.idTokenListenersCountChangedListener.onListenerCountChanged(this.idTokenListeners.size());
    }

    @Nullable
    @KeepForSdk
    @Deprecated
    public String getUid() throws FirebaseApiNotAvailableException {
        checkNotDeleted();
        if (this.tokenProvider != null) {
            return this.tokenProvider.getUid();
        }
        throw new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.");
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public Task<GetTokenResult> getToken(boolean forceRefresh) {
        checkNotDeleted();
        if (this.tokenProvider == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode."));
        }
        return this.tokenProvider.getAccessToken(forceRefresh);
    }

    @PublicApi
    public void delete() {
        if (this.deleted.compareAndSet(false, true)) {
            synchronized (LOCK) {
                INSTANCES.remove(this.name);
            }
            notifyOnAppDeleted();
        }
    }

    @KeepForSdk
    public <T> T get(Class<T> anInterface) {
        checkNotDeleted();
        return this.componentRuntime.get(anInterface);
    }

    @PublicApi
    public void setAutomaticResourceManagementEnabled(boolean enabled) {
        boolean z;
        checkNotDeleted();
        AtomicBoolean atomicBoolean = this.automaticResourceManagementEnabled;
        if (!enabled) {
            z = true;
        } else {
            z = false;
        }
        if (atomicBoolean.compareAndSet(z, enabled)) {
            boolean inBackground = BackgroundDetector.getInstance().isInBackground();
            if (enabled && inBackground) {
                notifyBackgroundStateChangeListeners(true);
            } else if (!enabled && inBackground) {
                notifyBackgroundStateChangeListeners(false);
            }
        }
    }

    @KeepForSdk
    public boolean isDataCollectionDefaultEnabled() {
        checkNotDeleted();
        return this.dataCollectionDefaultEnabled.get();
    }

    @KeepForSdk
    public void setDataCollectionDefaultEnabled(boolean enabled) {
        checkNotDeleted();
        if (this.dataCollectionDefaultEnabled.compareAndSet(!enabled, enabled)) {
            this.sharedPreferences.edit().putBoolean(DATA_COLLECTION_DEFAULT_ENABLED, enabled).commit();
            this.publisher.publish(new Event(DataCollectionDefaultChange.class, new DataCollectionDefaultChange(enabled)));
        }
    }

    protected FirebaseApp(Context applicationContext2, String name2, FirebaseOptions options2) {
        this.applicationContext = (Context) Preconditions.checkNotNull(applicationContext2);
        this.name = Preconditions.checkNotEmpty(name2);
        this.options = (FirebaseOptions) Preconditions.checkNotNull(options2);
        this.idTokenListenersCountChangedListener = new DefaultIdTokenListenersCountChangedListener();
        this.sharedPreferences = applicationContext2.getSharedPreferences(getSharedPrefsName(name2), 0);
        this.dataCollectionDefaultEnabled = new AtomicBoolean(readAutoDataCollectionEnabled());
        List<ComponentRegistrar> registrars = ComponentDiscovery.forContext(applicationContext2).discover();
        this.componentRuntime = new ComponentRuntime(UI_EXECUTOR, registrars, Component.of(applicationContext2, Context.class, new Class[0]), Component.of(this, FirebaseApp.class, new Class[0]), Component.of(options2, FirebaseOptions.class, new Class[0]), LibraryVersionComponent.create(FIREBASE_ANDROID, ""), LibraryVersionComponent.create(FIREBASE_COMMON, BuildConfig.VERSION_NAME), DefaultUserAgentPublisher.component());
        this.publisher = (Publisher) this.componentRuntime.get(Publisher.class);
    }

    private static String getSharedPrefsName(String appName) {
        return FIREBASE_APP_PREFS + appName;
    }

    private boolean readAutoDataCollectionEnabled() {
        ApplicationInfo applicationInfo;
        if (this.sharedPreferences.contains(DATA_COLLECTION_DEFAULT_ENABLED)) {
            return this.sharedPreferences.getBoolean(DATA_COLLECTION_DEFAULT_ENABLED, true);
        }
        try {
            PackageManager packageManager = this.applicationContext.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(this.applicationContext.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(DATA_COLLECTION_DEFAULT_ENABLED)) {
                return true;
            }
            return applicationInfo.metaData.getBoolean(DATA_COLLECTION_DEFAULT_ENABLED);
        } catch (PackageManager.NameNotFoundException e) {
            return true;
        }
    }

    private void checkNotDeleted() {
        Preconditions.checkState(!this.deleted.get(), "FirebaseApp was deleted");
    }

    @KeepForSdk
    @Deprecated
    public List<IdTokenListener> getListeners() {
        checkNotDeleted();
        return this.idTokenListeners;
    }

    @VisibleForTesting
    @KeepForSdk
    public boolean isDefaultApp() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    @UiThread
    @KeepForSdk
    @Deprecated
    public void notifyIdTokenListeners(@NonNull InternalTokenResult tokenResult) {
        Log.d(LOG_TAG, "Notifying auth state listeners.");
        int size = 0;
        for (IdTokenListener listener : this.idTokenListeners) {
            listener.onIdTokenChanged(tokenResult);
            size++;
        }
        Log.d(LOG_TAG, String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(size)}));
    }

    /* access modifiers changed from: private */
    public void notifyBackgroundStateChangeListeners(boolean background) {
        Log.d(LOG_TAG, "Notifying background state change listeners.");
        for (BackgroundStateChangeListener listener : this.backgroundStateChangeListeners) {
            listener.onBackgroundStateChanged(background);
        }
    }

    @KeepForSdk
    @Deprecated
    public void addIdTokenListener(@NonNull IdTokenListener listener) {
        checkNotDeleted();
        Preconditions.checkNotNull(listener);
        this.idTokenListeners.add(listener);
        this.idTokenListenersCountChangedListener.onListenerCountChanged(this.idTokenListeners.size());
    }

    @KeepForSdk
    @Deprecated
    public void removeIdTokenListener(@NonNull IdTokenListener listenerToRemove) {
        checkNotDeleted();
        Preconditions.checkNotNull(listenerToRemove);
        this.idTokenListeners.remove(listenerToRemove);
        this.idTokenListenersCountChangedListener.onListenerCountChanged(this.idTokenListeners.size());
    }

    @KeepForSdk
    public void addBackgroundStateChangeListener(BackgroundStateChangeListener listener) {
        checkNotDeleted();
        if (this.automaticResourceManagementEnabled.get() && BackgroundDetector.getInstance().isInBackground()) {
            listener.onBackgroundStateChanged(true);
        }
        this.backgroundStateChangeListeners.add(listener);
    }

    @KeepForSdk
    public void removeBackgroundStateChangeListener(BackgroundStateChangeListener listener) {
        checkNotDeleted();
        this.backgroundStateChangeListeners.remove(listener);
    }

    @KeepForSdk
    public String getPersistenceKey() {
        return Base64Utils.encodeUrlSafeNoPadding(getName().getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(getOptions().getApplicationId().getBytes(Charset.defaultCharset()));
    }

    @KeepForSdk
    public void addLifecycleEventListener(@NonNull FirebaseAppLifecycleListener listener) {
        checkNotDeleted();
        Preconditions.checkNotNull(listener);
        this.lifecycleListeners.add(listener);
    }

    @KeepForSdk
    public void removeLifecycleEventListener(@NonNull FirebaseAppLifecycleListener listener) {
        checkNotDeleted();
        Preconditions.checkNotNull(listener);
        this.lifecycleListeners.remove(listener);
    }

    private void notifyOnAppDeleted() {
        for (FirebaseAppLifecycleListener listener : this.lifecycleListeners) {
            listener.onDeleted(this.name, this.options);
        }
    }

    @VisibleForTesting
    public static void clearInstancesForTest() {
        synchronized (LOCK) {
            INSTANCES.clear();
        }
    }

    @KeepForSdk
    public static String getPersistenceKey(String name2, FirebaseOptions options2) {
        return Base64Utils.encodeUrlSafeNoPadding(name2.getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(options2.getApplicationId().getBytes(Charset.defaultCharset()));
    }

    private static List<String> getAllAppNames() {
        List<String> allAppNames = new ArrayList<>();
        synchronized (LOCK) {
            for (FirebaseApp app : INSTANCES.values()) {
                allAppNames.add(app.getName());
            }
        }
        Collections.sort(allAppNames);
        return allAppNames;
    }

    /* access modifiers changed from: private */
    public void initializeAllApis() {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.applicationContext);
        if (isDeviceProtectedStorage) {
            UserUnlockReceiver.ensureReceiverRegistered(this.applicationContext);
        } else {
            this.componentRuntime.initializeEagerComponents(isDefaultApp());
        }
        initializeApis(FirebaseApp.class, this, API_INITIALIZERS, isDeviceProtectedStorage);
        if (isDefaultApp()) {
            initializeApis(FirebaseApp.class, this, DEFAULT_APP_API_INITITALIZERS, isDeviceProtectedStorage);
            initializeApis(Context.class, this.applicationContext, DEFAULT_CONTEXT_API_INITITALIZERS, isDeviceProtectedStorage);
        }
    }

    private <T> void initializeApis(Class<T> parameterClass, T parameter, Iterable<String> apiInitClasses, boolean isDeviceProtectedStorage) {
        for (String apiInitClass : apiInitClasses) {
            if (isDeviceProtectedStorage) {
                try {
                    if (!DIRECT_BOOT_COMPATIBLE_API_INITIALIZERS.contains(apiInitClass)) {
                    }
                } catch (ClassNotFoundException e) {
                    if (CORE_CLASSES.contains(apiInitClass)) {
                        throw new IllegalStateException(apiInitClass + " is missing, but is required. Check if it has been removed by Proguard.");
                    }
                    Log.d(LOG_TAG, apiInitClass + " is not linked. Skipping initialization.");
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException(apiInitClass + "#getInstance has been removed by Proguard. Add keep rule to prevent it.");
                } catch (InvocationTargetException e3) {
                    Log.wtf(LOG_TAG, "Firebase API initialization failure.", e3);
                } catch (IllegalAccessException e4) {
                    Log.wtf(LOG_TAG, "Failed to initialize " + apiInitClass, e4);
                }
            }
            Method initMethod = Class.forName(apiInitClass).getMethod("getInstance", new Class[]{parameterClass});
            int initMethodModifiers = initMethod.getModifiers();
            if (Modifier.isPublic(initMethodModifiers) && Modifier.isStatic(initMethodModifiers)) {
                initMethod.invoke((Object) null, new Object[]{parameter});
            }
        }
    }

    private static String normalize(@NonNull String name2) {
        return name2.trim();
    }

    @TargetApi(24)
    /* compiled from: com.google.firebase:firebase-common@@16.1.0 */
    private static class UserUnlockReceiver extends BroadcastReceiver {
        private static AtomicReference<UserUnlockReceiver> INSTANCE = new AtomicReference<>();
        private final Context applicationContext;

        public UserUnlockReceiver(Context applicationContext2) {
            this.applicationContext = applicationContext2;
        }

        /* access modifiers changed from: private */
        public static void ensureReceiverRegistered(Context applicationContext2) {
            if (INSTANCE.get() == null) {
                UserUnlockReceiver receiver = new UserUnlockReceiver(applicationContext2);
                if (INSTANCE.compareAndSet((Object) null, receiver)) {
                    applicationContext2.registerReceiver(receiver, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.LOCK) {
                for (FirebaseApp app : FirebaseApp.INSTANCES.values()) {
                    app.initializeAllApis();
                }
            }
            unregister();
        }

        public void unregister() {
            this.applicationContext.unregisterReceiver(this);
        }
    }

    @TargetApi(14)
    /* compiled from: com.google.firebase:firebase-common@@16.1.0 */
    private static class GlobalBackgroundStateListener implements BackgroundDetector.BackgroundStateChangeListener {
        private static AtomicReference<GlobalBackgroundStateListener> INSTANCE = new AtomicReference<>();

        private GlobalBackgroundStateListener() {
        }

        /* access modifiers changed from: private */
        public static void ensureBackgroundStateListenerRegistered(Context context) {
            if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
                Application application = (Application) context.getApplicationContext();
                if (INSTANCE.get() == null) {
                    GlobalBackgroundStateListener listener = new GlobalBackgroundStateListener();
                    if (INSTANCE.compareAndSet((Object) null, listener)) {
                        BackgroundDetector.initialize(application);
                        BackgroundDetector.getInstance().addListener(listener);
                    }
                }
            }
        }

        public void onBackgroundStateChanged(boolean background) {
            synchronized (FirebaseApp.LOCK) {
                Iterator it = new ArrayList(FirebaseApp.INSTANCES.values()).iterator();
                while (it.hasNext()) {
                    FirebaseApp app = (FirebaseApp) it.next();
                    if (app.automaticResourceManagementEnabled.get()) {
                        app.notifyBackgroundStateChangeListeners(background);
                    }
                }
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-common@@16.1.0 */
    private static class UiExecutor implements Executor {
        private static final Handler HANDLER = new Handler(Looper.getMainLooper());

        private UiExecutor() {
        }

        public void execute(@NonNull Runnable command) {
            HANDLER.post(command);
        }
    }
}
