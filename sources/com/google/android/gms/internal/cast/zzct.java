package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzae;
import com.google.android.gms.cast.zzah;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public final class zzct extends GmsClient<zzdf> {
    /* access modifiers changed from: private */
    public static final zzdo zzbf = new zzdo("CastClientImpl");
    /* access modifiers changed from: private */
    public static final Object zzzc = new Object();
    private static final Object zzzd = new Object();
    private final Bundle extras;
    /* access modifiers changed from: private */
    public final Cast.Listener zzak;
    private double zzfh;
    private boolean zzfi;
    /* access modifiers changed from: private */
    public final CastDevice zzit;
    /* access modifiers changed from: private */
    public ApplicationMetadata zzyj;
    /* access modifiers changed from: private */
    public final Map<String, Cast.MessageReceivedCallback> zzyk = new HashMap();
    private final long zzyl;
    private zzcv zzym;
    /* access modifiers changed from: private */
    public String zzyn;
    private boolean zzyo;
    private boolean zzyp;
    private boolean zzyq;
    private zzae zzyr;
    private int zzys;
    private int zzyt;
    private final AtomicLong zzyu = new AtomicLong(0);
    /* access modifiers changed from: private */
    public String zzyv;
    /* access modifiers changed from: private */
    public String zzyw;
    private Bundle zzyx;
    private final Map<Long, BaseImplementation.ResultHolder<Status>> zzyy = new HashMap();
    private double zzyz;
    /* access modifiers changed from: private */
    public BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> zzza;
    private BaseImplementation.ResultHolder<Status> zzzb;

    public zzct(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, long j, Cast.Listener listener, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 10, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzit = castDevice;
        this.zzak = listener;
        this.zzyl = j;
        this.extras = bundle;
        zzeb();
        this.zzyz = zzee();
    }

    /* access modifiers changed from: private */
    public final void zzeb() {
        this.zzyq = false;
        this.zzys = -1;
        this.zzyt = -1;
        this.zzyj = null;
        this.zzyn = null;
        this.zzfh = 0.0d;
        this.zzyz = zzee();
        this.zzfi = false;
        this.zzyr = null;
    }

    /* access modifiers changed from: protected */
    public final void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        zzbf.d("in onPostInitHandler; statusCode=%d", Integer.valueOf(i));
        if (i == 0 || i == 1001) {
            this.zzyq = true;
            this.zzyo = true;
            this.zzyp = true;
        } else {
            this.zzyq = false;
        }
        if (i == 1001) {
            this.zzyx = new Bundle();
            this.zzyx.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i = 0;
        }
        super.onPostInitHandler(i, iBinder, bundle, i2);
    }

    public final void disconnect() {
        zzbf.d("disconnect(); ServiceListener=%s, isConnected=%b", this.zzym, Boolean.valueOf(isConnected()));
        zzcv zzcv = this.zzym;
        this.zzym = null;
        if (zzcv == null || zzcv.zzeh() == null) {
            zzbf.d("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        zzec();
        try {
            ((zzdf) getService()).disconnect();
        } catch (RemoteException | IllegalStateException e) {
            zzbf.zza(e, "Error while disconnecting the controller interface: %s", e.getMessage());
        } finally {
            super.disconnect();
        }
    }

    public final Bundle getConnectionHint() {
        if (this.zzyx == null) {
            return super.getConnectionHint();
        }
        Bundle bundle = this.zzyx;
        this.zzyx = null;
        return bundle;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    /* access modifiers changed from: protected */
    @NonNull
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    /* access modifiers changed from: protected */
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        zzbf.d("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzyv, this.zzyw);
        this.zzit.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzyl);
        if (this.extras != null) {
            bundle.putAll(this.extras);
        }
        this.zzym = new zzcv(this);
        bundle.putParcelable(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, new BinderWrapper(this.zzym.asBinder()));
        if (this.zzyv != null) {
            bundle.putString("last_application_id", this.zzyv);
            if (this.zzyw != null) {
                bundle.putString("last_session_id", this.zzyw);
            }
        }
        return bundle;
    }

    public final void zza(String str, String str2, BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        } else if (str2.length() > 524288) {
            zzbf.w("Message send failed. Message exceeds maximum size", new Object[0]);
            throw new IllegalArgumentException("Message exceeds maximum size");
        } else {
            zzdc.zzq(str);
            long incrementAndGet = this.zzyu.incrementAndGet();
            try {
                this.zzyy.put(Long.valueOf(incrementAndGet), resultHolder);
                zzdf zzdf = (zzdf) getService();
                if (zzed()) {
                    zzdf.zza(str, str2, incrementAndGet);
                } else {
                    zzb(incrementAndGet, (int) CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
                }
            } catch (Throwable th) {
                this.zzyy.remove(Long.valueOf(incrementAndGet));
                throw th;
            }
        }
    }

    public final void zza(String str, LaunchOptions launchOptions, BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) throws IllegalStateException, RemoteException {
        zza(resultHolder);
        zzdf zzdf = (zzdf) getService();
        if (zzed()) {
            zzdf.zzb(str, launchOptions);
        } else {
            zzs(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, String str2, zzah zzah, BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) throws IllegalStateException, RemoteException {
        zza(resultHolder);
        if (zzah == null) {
            zzah = new zzah();
        }
        zzdf zzdf = (zzdf) getService();
        if (zzed()) {
            zzdf.zza(str, str2, zzah);
        } else {
            zzs(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    private final void zza(BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) {
        synchronized (zzzc) {
            if (this.zzza != null) {
                this.zzza.setResult(new zzcw(new Status(CastStatusCodes.CANCELED)));
            }
            this.zzza = resultHolder;
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException {
        zzc(resultHolder);
        zzdf zzdf = (zzdf) getService();
        if (zzed()) {
            zzdf.zzel();
        } else {
            zzt(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException {
        zzc(resultHolder);
        zzdf zzdf = (zzdf) getService();
        if (zzed()) {
            zzdf.zzj(str);
        } else {
            zzt(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    private final void zzc(BaseImplementation.ResultHolder<Status> resultHolder) {
        synchronized (zzzd) {
            if (this.zzzb != null) {
                resultHolder.setResult(new Status(CastStatusCodes.INVALID_REQUEST));
            } else {
                this.zzzb = resultHolder;
            }
        }
    }

    public final void requestStatus() throws IllegalStateException, RemoteException {
        zzdf zzdf = (zzdf) getService();
        if (zzed()) {
            zzdf.requestStatus();
        }
    }

    public final void setVolume(double d) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException(new StringBuilder(41).append("Volume cannot be ").append(d).toString());
        }
        zzdf zzdf = (zzdf) getService();
        if (zzed()) {
            zzdf.zza(d, this.zzfh, this.zzfi);
        }
    }

    public final void setMute(boolean z) throws IllegalStateException, RemoteException {
        zzdf zzdf = (zzdf) getService();
        if (zzed()) {
            zzdf.zza(z, this.zzfh, this.zzfi);
        }
    }

    public final double getVolume() throws IllegalStateException {
        checkConnected();
        return this.zzfh;
    }

    public final boolean isMute() throws IllegalStateException {
        checkConnected();
        return this.zzfi;
    }

    public final int getActiveInputState() throws IllegalStateException {
        checkConnected();
        return this.zzys;
    }

    public final int getStandbyState() throws IllegalStateException {
        checkConnected();
        return this.zzyt;
    }

    public final void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalArgumentException, IllegalStateException, RemoteException {
        zzdc.zzq(str);
        removeMessageReceivedCallbacks(str);
        if (messageReceivedCallback != null) {
            synchronized (this.zzyk) {
                this.zzyk.put(str, messageReceivedCallback);
            }
            zzdf zzdf = (zzdf) getService();
            if (zzed()) {
                zzdf.zzt(str);
            }
        }
    }

    public final void removeMessageReceivedCallbacks(String str) throws IllegalArgumentException, RemoteException {
        Cast.MessageReceivedCallback remove;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        synchronized (this.zzyk) {
            remove = this.zzyk.remove(str);
        }
        if (remove != null) {
            try {
                ((zzdf) getService()).zzu(str);
            } catch (IllegalStateException e) {
                zzbf.zza(e, "Error unregistering namespace (%s): %s", str, e.getMessage());
            }
        }
    }

    public final ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        checkConnected();
        return this.zzyj;
    }

    public final String getApplicationStatus() throws IllegalStateException {
        checkConnected();
        return this.zzyn;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        zzec();
    }

    private final void zzec() {
        zzbf.d("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzyk) {
            this.zzyk.clear();
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzdb zzdb) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        ApplicationMetadata applicationMetadata = zzdb.getApplicationMetadata();
        if (!zzdc.zza(applicationMetadata, this.zzyj)) {
            this.zzyj = applicationMetadata;
            this.zzak.onApplicationMetadataChanged(this.zzyj);
        }
        double volume = zzdb.getVolume();
        if (Double.isNaN(volume) || Math.abs(volume - this.zzfh) <= 1.0E-7d) {
            z = false;
        } else {
            this.zzfh = volume;
            z = true;
        }
        boolean zzei = zzdb.zzei();
        if (zzei != this.zzfi) {
            this.zzfi = zzei;
            z = true;
        }
        double zzek = zzdb.zzek();
        if (!Double.isNaN(zzek)) {
            this.zzyz = zzek;
        }
        zzbf.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzyp));
        if (this.zzak != null && (z || this.zzyp)) {
            this.zzak.onVolumeChanged();
        }
        int activeInputState = zzdb.getActiveInputState();
        if (activeInputState != this.zzys) {
            this.zzys = activeInputState;
            z2 = true;
        } else {
            z2 = false;
        }
        zzbf.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(this.zzyp));
        if (this.zzak != null && (z2 || this.zzyp)) {
            this.zzak.onActiveInputStateChanged(this.zzys);
        }
        int standbyState = zzdb.getStandbyState();
        if (standbyState != this.zzyt) {
            this.zzyt = standbyState;
            z3 = true;
        } else {
            z3 = false;
        }
        zzbf.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z3), Boolean.valueOf(this.zzyp));
        if (this.zzak != null && (z3 || this.zzyp)) {
            this.zzak.onStandbyStateChanged(this.zzyt);
        }
        if (!zzdc.zza(this.zzyr, zzdb.zzej())) {
            this.zzyr = zzdb.zzej();
        } else {
            z4 = false;
        }
        if (this.zzak == null || !z4) {
        }
        this.zzyp = false;
    }

    /* access modifiers changed from: private */
    public final void zza(zzcj zzcj) {
        boolean z;
        String zzdy = zzcj.zzdy();
        if (!zzdc.zza(zzdy, this.zzyn)) {
            this.zzyn = zzdy;
            z = true;
        } else {
            z = false;
        }
        zzbf.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzyo));
        if (this.zzak != null && (z || this.zzyo)) {
            this.zzak.onApplicationStatusChanged();
        }
        this.zzyo = false;
    }

    @VisibleForTesting
    private final boolean zzed() {
        return this.zzyq && this.zzym != null && !this.zzym.isDisposed();
    }

    @VisibleForTesting
    private final double zzee() {
        if (this.zzit.hasCapability(2048)) {
            return 0.02d;
        }
        if (!this.zzit.hasCapability(4) || this.zzit.hasCapability(1)) {
            return 0.05d;
        }
        if ("Chromecast Audio".equals(this.zzit.getModelName())) {
            return 0.05d;
        }
        return 0.02d;
    }

    /* access modifiers changed from: private */
    public final void zzb(long j, int i) {
        BaseImplementation.ResultHolder remove;
        synchronized (this.zzyy) {
            remove = this.zzyy.remove(Long.valueOf(j));
        }
        if (remove != null) {
            remove.setResult(new Status(i));
        }
    }

    public final void zzs(int i) {
        synchronized (zzzc) {
            if (this.zzza != null) {
                this.zzza.setResult(new zzcw(new Status(i)));
                this.zzza = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzt(int i) {
        synchronized (zzzd) {
            if (this.zzzb != null) {
                this.zzzb.setResult(new Status(i));
                this.zzzb = null;
            }
        }
    }

    public final int getMinApkVersion() {
        return 12800000;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if (queryLocalInterface instanceof zzdf) {
            return (zzdf) queryLocalInterface;
        }
        return new zzdi(iBinder);
    }
}
