package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NotificationCompat;
import android.support.v7.media.MediaRouter;
import android.text.TextUtils;
import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzdo;
import com.google.android.gms.internal.cast.zzep;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(19)
public abstract class CastRemoteDisplayLocalService extends Service {
    /* access modifiers changed from: private */
    public static final zzdo zzbf = new zzdo("CastRemoteDisplayLocalService");
    private static final int zzbp = R.id.cast_notification_id;
    /* access modifiers changed from: private */
    public static final Object zzbq = new Object();
    /* access modifiers changed from: private */
    public static AtomicBoolean zzbr = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static CastRemoteDisplayLocalService zzcg;
    private Handler handler;
    /* access modifiers changed from: private */
    public WeakReference<Callbacks> zzbs;
    private zzb zzbt;
    private NotificationSettings zzbu;
    private Notification zzbv;
    private boolean zzbw;
    private PendingIntent zzbx;
    /* access modifiers changed from: private */
    public CastDevice zzby;
    /* access modifiers changed from: private */
    public Display zzbz;
    /* access modifiers changed from: private */
    public Context zzca;
    /* access modifiers changed from: private */
    public ServiceConnection zzcb;
    private MediaRouter zzcc;
    /* access modifiers changed from: private */
    public boolean zzcd = false;
    private CastRemoteDisplayClient zzce;
    private final MediaRouter.Callback zzcf = new zzv(this);
    private final IBinder zzch = new zza();
    private String zzy;

    public interface Callbacks {
        void onRemoteDisplaySessionEnded(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onRemoteDisplaySessionError(Status status);

        void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onServiceCreated(CastRemoteDisplayLocalService castRemoteDisplayLocalService);
    }

    public static final class NotificationSettings {
        /* access modifiers changed from: private */
        public Notification zzbv;
        /* access modifiers changed from: private */
        public PendingIntent zzcp;
        /* access modifiers changed from: private */
        public String zzcq;
        /* access modifiers changed from: private */
        public String zzcr;

        private NotificationSettings() {
        }

        private NotificationSettings(NotificationSettings notificationSettings) {
            this.zzbv = notificationSettings.zzbv;
            this.zzcp = notificationSettings.zzcp;
            this.zzcq = notificationSettings.zzcq;
            this.zzcr = notificationSettings.zzcr;
        }

        public static final class Builder {
            private NotificationSettings zzcs = new NotificationSettings((zzv) null);

            public final Builder setNotification(Notification notification) {
                Notification unused = this.zzcs.zzbv = notification;
                return this;
            }

            public final Builder setNotificationPendingIntent(PendingIntent pendingIntent) {
                PendingIntent unused = this.zzcs.zzcp = pendingIntent;
                return this;
            }

            public final Builder setNotificationTitle(String str) {
                String unused = this.zzcs.zzcq = str;
                return this;
            }

            public final Builder setNotificationText(String str) {
                String unused = this.zzcs.zzcr = str;
                return this;
            }

            public final NotificationSettings build() {
                if (this.zzcs.zzbv != null) {
                    if (!TextUtils.isEmpty(this.zzcs.zzcq)) {
                        throw new IllegalArgumentException("notificationTitle requires using the default notification");
                    } else if (!TextUtils.isEmpty(this.zzcs.zzcr)) {
                        throw new IllegalArgumentException("notificationText requires using the default notification");
                    } else if (this.zzcs.zzcp != null) {
                        throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
                    }
                } else if (TextUtils.isEmpty(this.zzcs.zzcq) && TextUtils.isEmpty(this.zzcs.zzcr) && this.zzcs.zzcp == null) {
                    throw new IllegalArgumentException("At least an argument must be provided");
                }
                return this.zzcs;
            }
        }

        /* synthetic */ NotificationSettings(zzv zzv) {
            this();
        }

        /* synthetic */ NotificationSettings(NotificationSettings notificationSettings, zzv zzv) {
            this(notificationSettings);
        }
    }

    @VisibleForTesting
    class zza extends Binder {
        zza() {
        }
    }

    private static final class zzb extends BroadcastReceiver {
        private zzb() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT")) {
                CastRemoteDisplayLocalService.stopService();
            } else if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED")) {
                CastRemoteDisplayLocalService.zzb(false);
            }
        }

        /* synthetic */ zzb(zzv zzv) {
            this();
        }
    }

    public abstract void onCreatePresentation(Display display);

    public abstract void onDismissPresentation();

    public static class Options {
        @CastRemoteDisplay.Configuration
        private int zzbd = 2;

        public void setConfigPreset(@CastRemoteDisplay.Configuration int i) {
            this.zzbd = i;
        }

        @CastRemoteDisplay.Configuration
        public int getConfigPreset() {
            return this.zzbd;
        }
    }

    public IBinder onBind(Intent intent) {
        zzb("onBind");
        return this.zzch;
    }

    public void onCreate() {
        zzb("onCreate");
        super.onCreate();
        this.handler = new zzep(getMainLooper());
        this.handler.postDelayed(new zzu(this), 100);
        if (this.zzce == null) {
            this.zzce = CastRemoteDisplay.getClient(this);
        }
        if (PlatformVersion.isAtLeastO()) {
            NotificationChannel notificationChannel = new NotificationChannel("cast_remote_display_local_service", getString(R.string.cast_notification_default_channel_name), 2);
            notificationChannel.setShowBadge(false);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        zzb("onStartCommand");
        this.zzcd = true;
        return 2;
    }

    protected static void setDebugEnabled() {
        zzbf.zzl(true);
    }

    /* access modifiers changed from: protected */
    public Display getDisplay() {
        return this.zzbz;
    }

    public static CastRemoteDisplayLocalService getInstance() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        synchronized (zzbq) {
            castRemoteDisplayLocalService = zzcg;
        }
        return castRemoteDisplayLocalService;
    }

    public static void startService(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, NotificationSettings notificationSettings, Callbacks callbacks) {
        startServiceWithOptions(context, cls, str, castDevice, new Options(), notificationSettings, callbacks);
    }

    public static void startServiceWithOptions(@NonNull Context context, @NonNull Class<? extends CastRemoteDisplayLocalService> cls, @NonNull String str, @NonNull CastDevice castDevice, @NonNull Options options, @NonNull NotificationSettings notificationSettings, @NonNull Callbacks callbacks) {
        zzbf.d("Starting Service", new Object[0]);
        synchronized (zzbq) {
            if (zzcg != null) {
                zzbf.w("An existing service had not been stopped before starting one", new Object[0]);
                zzb(true);
            }
        }
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 128);
            if (serviceInfo == null || !serviceInfo.exported) {
                Preconditions.checkNotNull(context, "activityContext is required.");
                Preconditions.checkNotNull(cls, "serviceClass is required.");
                Preconditions.checkNotNull(str, "applicationId is required.");
                Preconditions.checkNotNull(castDevice, "device is required.");
                Preconditions.checkNotNull(options, "options is required.");
                Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
                Preconditions.checkNotNull(callbacks, "callbacks is required.");
                if (notificationSettings.zzbv == null && notificationSettings.zzcp == null) {
                    throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
                } else if (zzbr.getAndSet(true)) {
                    zzbf.e("Service is already being started, startService has been called twice", new Object[0]);
                } else {
                    Intent intent = new Intent(context, cls);
                    context.startService(intent);
                    context.bindService(intent, new zzx(str, castDevice, options, notificationSettings, context, callbacks), 64);
                }
            } else {
                throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?");
        }
    }

    /* access modifiers changed from: private */
    public final void zza(boolean z) {
        zzb("Stopping Service");
        Preconditions.checkMainThread("stopServiceInstanceInternal must be called on the main thread");
        if (!z && this.zzcc != null) {
            zzb("Setting default route");
            this.zzcc.selectRoute(this.zzcc.getDefaultRoute());
        }
        if (this.zzbt != null) {
            zzb("Unregistering notification receiver");
            unregisterReceiver(this.zzbt);
        }
        zzb("stopRemoteDisplaySession");
        zzb("stopRemoteDisplay");
        this.zzce.stopRemoteDisplay().addOnCompleteListener(new zzaa(this));
        if (this.zzbs.get() != null) {
            ((Callbacks) this.zzbs.get()).onRemoteDisplaySessionEnded(this);
        }
        onDismissPresentation();
        zzb("Stopping the remote display Service");
        stopForeground(true);
        stopSelf();
        if (this.zzcc != null) {
            Preconditions.checkMainThread("CastRemoteDisplayLocalService calls must be done on the main thread");
            zzb("removeMediaRouterCallback");
            this.zzcc.removeCallback(this.zzcf);
        }
        if (!(this.zzca == null || this.zzcb == null)) {
            try {
                this.zzca.unbindService(this.zzcb);
            } catch (IllegalArgumentException e) {
                zzb("No need to unbind service, already unbound");
            }
            this.zzcb = null;
            this.zzca = null;
        }
        this.zzy = null;
        this.zzbv = null;
        this.zzbz = null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (r0.handler == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        r0.handler.post(new com.google.android.gms.cast.zzw(r0, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r0.zza(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void zzb(boolean r4) {
        /*
            r3 = 0
            com.google.android.gms.internal.cast.zzdo r0 = zzbf
            java.lang.String r1 = "Stopping Service"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r0.d(r1, r2)
            java.util.concurrent.atomic.AtomicBoolean r0 = zzbr
            r0.set(r3)
            java.lang.Object r1 = zzbq
            monitor-enter(r1)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = zzcg     // Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0022
            com.google.android.gms.internal.cast.zzdo r0 = zzbf     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = "Service is already being stopped"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0041 }
            r0.e(r2, r3)     // Catch:{ all -> 0x0041 }
            monitor-exit(r1)     // Catch:{ all -> 0x0041 }
        L_0x0021:
            return
        L_0x0022:
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = zzcg     // Catch:{ all -> 0x0041 }
            r2 = 0
            zzcg = r2     // Catch:{ all -> 0x0041 }
            monitor-exit(r1)     // Catch:{ all -> 0x0041 }
            android.os.Handler r1 = r0.handler
            if (r1 == 0) goto L_0x0021
            android.os.Looper r1 = android.os.Looper.myLooper()
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            if (r1 == r2) goto L_0x0044
            android.os.Handler r1 = r0.handler
            com.google.android.gms.cast.zzw r2 = new com.google.android.gms.cast.zzw
            r2.<init>(r0, r4)
            r1.post(r2)
            goto L_0x0021
        L_0x0041:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0041 }
            throw r0
        L_0x0044:
            r0.zza((boolean) r4)
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastRemoteDisplayLocalService.zzb(boolean):void");
    }

    public static void stopService() {
        zzb(false);
    }

    public void updateNotificationSettings(NotificationSettings notificationSettings) {
        Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
        Preconditions.checkNotNull(this.handler, "Service is not ready yet.");
        this.handler.post(new zzz(this, notificationSettings));
    }

    /* access modifiers changed from: private */
    public final void zza(NotificationSettings notificationSettings) {
        Preconditions.checkMainThread("updateNotificationSettingsInternal must be called on the main thread");
        if (this.zzbu == null) {
            throw new IllegalStateException("No current notification settings to update");
        }
        if (!this.zzbw) {
            Preconditions.checkNotNull(notificationSettings.zzbv, "notification is required.");
            this.zzbv = notificationSettings.zzbv;
            Notification unused = this.zzbu.zzbv = this.zzbv;
        } else if (notificationSettings.zzbv != null) {
            throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
        } else {
            if (notificationSettings.zzcp != null) {
                PendingIntent unused2 = this.zzbu.zzcp = notificationSettings.zzcp;
            }
            if (!TextUtils.isEmpty(notificationSettings.zzcq)) {
                String unused3 = this.zzbu.zzcq = notificationSettings.zzcq;
            }
            if (!TextUtils.isEmpty(notificationSettings.zzcr)) {
                String unused4 = this.zzbu.zzcr = notificationSettings.zzcr;
            }
            this.zzbv = zzc(true);
        }
        startForeground(zzbp, this.zzbv);
    }

    /* access modifiers changed from: private */
    public final void zza(Display display) {
        this.zzbz = display;
        if (this.zzbw) {
            this.zzbv = zzc(true);
            startForeground(zzbp, this.zzbv);
        }
        if (this.zzbs.get() != null) {
            ((Callbacks) this.zzbs.get()).onRemoteDisplaySessionStarted(this);
        }
        onCreatePresentation(this.zzbz);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        if (r7.zzcc != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        r7.zzcc = android.support.v7.media.MediaRouter.getInstance(getApplicationContext());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
        r2 = new android.support.v7.media.MediaRouteSelector.Builder().addControlCategory(com.google.android.gms.cast.CastMediaControlIntent.categoryForCast(r7.zzy)).build();
        zzb("addMediaRouterCallback");
        r7.zzcc.addCallback(r2, r7.zzcf, 4);
        r7.zzbv = com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r11);
        r7.zzbt = new com.google.android.gms.cast.CastRemoteDisplayLocalService.zzb((com.google.android.gms.cast.zzv) null);
        r2 = new android.content.IntentFilter();
        r2.addAction("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
        r2.addAction("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
        registerReceiver(r7.zzbt, r2);
        r7.zzbu = new com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings(r11, (com.google.android.gms.cast.zzv) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x008e, code lost:
        if (com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r7.zzbu) != null) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0090, code lost:
        r7.zzbw = true;
        r7.zzbv = zzc(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0098, code lost:
        startForeground(zzbp, r7.zzbv);
        zzb("startRemoteDisplay");
        r2 = new android.content.Intent("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
        r2.setPackage(r7.zzca.getPackageName());
        r7.zzce.startRemoteDisplay(r9, r7.zzy, r10.getConfigPreset(), android.app.PendingIntent.getBroadcast(r7, 0, r2, 0)).addOnCompleteListener(new com.google.android.gms.cast.zzy(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00d2, code lost:
        if (r7.zzbs.get() == null) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00d4, code lost:
        ((com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks) r7.zzbs.get()).onServiceCreated(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e5, code lost:
        r7.zzbw = false;
        r7.zzbv = com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r7.zzbu);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        r7.zzbs = new java.lang.ref.WeakReference<>(r14);
        r7.zzy = r8;
        r7.zzby = r9;
        r7.zzca = r12;
        r7.zzcb = r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(java.lang.String r8, com.google.android.gms.cast.CastDevice r9, com.google.android.gms.cast.CastRemoteDisplayLocalService.Options r10, com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings r11, android.content.Context r12, android.content.ServiceConnection r13, com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks r14) {
        /*
            r7 = this;
            r6 = 0
            r1 = 1
            r0 = 0
            java.lang.String r2 = "startRemoteDisplaySession"
            r7.zzb((java.lang.String) r2)
            java.lang.String r2 = "Starting the Cast Remote Display must be done on the main thread"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r2)
            java.lang.Object r2 = zzbq
            monitor-enter(r2)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r3 = zzcg     // Catch:{ all -> 0x00e2 }
            if (r3 == 0) goto L_0x0020
            com.google.android.gms.internal.cast.zzdo r1 = zzbf     // Catch:{ all -> 0x00e2 }
            java.lang.String r3 = "An existing service had not been stopped before starting one"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00e2 }
            r1.w(r3, r4)     // Catch:{ all -> 0x00e2 }
            monitor-exit(r2)     // Catch:{ all -> 0x00e2 }
        L_0x001f:
            return r0
        L_0x0020:
            zzcg = r7     // Catch:{ all -> 0x00e2 }
            monitor-exit(r2)     // Catch:{ all -> 0x00e2 }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r14)
            r7.zzbs = r2
            r7.zzy = r8
            r7.zzby = r9
            r7.zzca = r12
            r7.zzcb = r13
            android.support.v7.media.MediaRouter r2 = r7.zzcc
            if (r2 != 0) goto L_0x0040
            android.content.Context r2 = r7.getApplicationContext()
            android.support.v7.media.MediaRouter r2 = android.support.v7.media.MediaRouter.getInstance(r2)
            r7.zzcc = r2
        L_0x0040:
            android.support.v7.media.MediaRouteSelector$Builder r2 = new android.support.v7.media.MediaRouteSelector$Builder
            r2.<init>()
            java.lang.String r3 = r7.zzy
            java.lang.String r3 = com.google.android.gms.cast.CastMediaControlIntent.categoryForCast((java.lang.String) r3)
            android.support.v7.media.MediaRouteSelector$Builder r2 = r2.addControlCategory(r3)
            android.support.v7.media.MediaRouteSelector r2 = r2.build()
            java.lang.String r3 = "addMediaRouterCallback"
            r7.zzb((java.lang.String) r3)
            android.support.v7.media.MediaRouter r3 = r7.zzcc
            android.support.v7.media.MediaRouter$Callback r4 = r7.zzcf
            r5 = 4
            r3.addCallback(r2, r4, r5)
            android.app.Notification r2 = r11.zzbv
            r7.zzbv = r2
            com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb r2 = new com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb
            r2.<init>(r6)
            r7.zzbt = r2
            android.content.IntentFilter r2 = new android.content.IntentFilter
            r2.<init>()
            java.lang.String r3 = "com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT"
            r2.addAction(r3)
            java.lang.String r3 = "com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED"
            r2.addAction(r3)
            com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb r3 = r7.zzbt
            r7.registerReceiver(r3, r2)
            com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings r2 = new com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings
            r2.<init>(r11, r6)
            r7.zzbu = r2
            com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings r2 = r7.zzbu
            android.app.Notification r2 = r2.zzbv
            if (r2 != 0) goto L_0x00e5
            r7.zzbw = r1
            android.app.Notification r2 = r7.zzc((boolean) r0)
            r7.zzbv = r2
        L_0x0098:
            int r2 = zzbp
            android.app.Notification r3 = r7.zzbv
            r7.startForeground(r2, r3)
            java.lang.String r2 = "startRemoteDisplay"
            r7.zzb((java.lang.String) r2)
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = "com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED"
            r2.<init>(r3)
            android.content.Context r3 = r7.zzca
            java.lang.String r3 = r3.getPackageName()
            r2.setPackage(r3)
            android.app.PendingIntent r0 = android.app.PendingIntent.getBroadcast(r7, r0, r2, r0)
            com.google.android.gms.cast.CastRemoteDisplayClient r2 = r7.zzce
            java.lang.String r3 = r7.zzy
            int r4 = r10.getConfigPreset()
            com.google.android.gms.tasks.Task r0 = r2.startRemoteDisplay(r9, r3, r4, r0)
            com.google.android.gms.cast.zzy r2 = new com.google.android.gms.cast.zzy
            r2.<init>(r7)
            r0.addOnCompleteListener(r2)
            java.lang.ref.WeakReference<com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks> r0 = r7.zzbs
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L_0x00df
            java.lang.ref.WeakReference<com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks> r0 = r7.zzbs
            java.lang.Object r0 = r0.get()
            com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks r0 = (com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks) r0
            r0.onServiceCreated(r7)
        L_0x00df:
            r0 = r1
            goto L_0x001f
        L_0x00e2:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00e2 }
            throw r0
        L_0x00e5:
            r7.zzbw = r0
            com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings r2 = r7.zzbu
            android.app.Notification r2 = r2.zzbv
            r7.zzbv = r2
            goto L_0x0098
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastRemoteDisplayLocalService.zza(java.lang.String, com.google.android.gms.cast.CastDevice, com.google.android.gms.cast.CastRemoteDisplayLocalService$Options, com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings, android.content.Context, android.content.ServiceConnection, com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks):boolean");
    }

    /* access modifiers changed from: private */
    public final void zze() {
        if (this.zzbs.get() != null) {
            ((Callbacks) this.zzbs.get()).onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        }
        stopService();
    }

    private final Notification zzc(boolean z) {
        int i;
        int i2;
        String str;
        String str2;
        zzb("createDefaultNotification");
        String zzc = this.zzbu.zzcq;
        String zzd = this.zzbu.zzcr;
        if (z) {
            i = R.string.cast_notification_connected_message;
            i2 = R.drawable.cast_ic_notification_on;
        } else {
            i = R.string.cast_notification_connecting_message;
            i2 = R.drawable.cast_ic_notification_connecting;
        }
        if (TextUtils.isEmpty(zzc)) {
            str = (String) getPackageManager().getApplicationLabel(getApplicationInfo());
        } else {
            str = zzc;
        }
        if (TextUtils.isEmpty(zzd)) {
            str2 = getString(i, new Object[]{this.zzby.getFriendlyName()});
        } else {
            str2 = zzd;
        }
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this, "cast_remote_display_local_service").setContentTitle(str).setContentText(str2).setContentIntent(this.zzbu.zzcp).setSmallIcon(i2).setOngoing(true);
        String string = getString(R.string.cast_notification_disconnect);
        if (this.zzbx == null) {
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intent.setPackage(this.zzca.getPackageName());
            this.zzbx = PendingIntent.getBroadcast(this, 0, intent, 134217728);
        }
        return ongoing.addAction(17301560, string, this.zzbx).build();
    }

    /* access modifiers changed from: private */
    public final void zzb(String str) {
        zzbf.d("[Instance: %s] %s", this, str);
    }

    /* access modifiers changed from: private */
    public final void zzc(String str) {
        zzbf.e("[Instance: %s] %s", this, str);
    }
}
