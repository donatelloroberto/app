package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzcl;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzdl;
import java.io.IOException;

public final class Cast {
    public static final int ACTIVE_INPUT_STATE_NO = 0;
    public static final int ACTIVE_INPUT_STATE_UNKNOWN = -1;
    public static final int ACTIVE_INPUT_STATE_YES = 1;
    public static final Api<CastOptions> API = new Api<>("Cast.API", zzad, zzdl.zzzt);
    public static final CastApi CastApi = new CastApi.zza();
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    public static final int STANDBY_STATE_NO = 0;
    public static final int STANDBY_STATE_UNKNOWN = -1;
    public static final int STANDBY_STATE_YES = 1;
    @VisibleForTesting
    private static final Api.AbstractClientBuilder<zzct, CastOptions> zzad = new zze();

    public interface ApplicationConnectionResult extends Result {
        ApplicationMetadata getApplicationMetadata();

        String getApplicationStatus();

        String getSessionId();

        boolean getWasLaunched();
    }

    @Deprecated
    public interface CastApi {

        public static final class zza implements CastApi {
            public final void requestStatus(GoogleApiClient googleApiClient) throws IOException, IllegalStateException {
                try {
                    ((zzct) googleApiClient.getClient(zzdl.zzzt)).requestStatus();
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public final PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2) {
                return googleApiClient.execute(new zzf(this, googleApiClient, str, str2));
            }

            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str) {
                return googleApiClient.execute(new zzh(this, googleApiClient, str));
            }

            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) {
                return googleApiClient.execute(new zzg(this, googleApiClient, str, launchOptions));
            }

            @Deprecated
            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z) {
                return launchApplication(googleApiClient, str, new LaunchOptions.Builder().setRelaunchIfRunning(z).build());
            }

            @ShowFirstParty
            private final PendingResult<ApplicationConnectionResult> zza(GoogleApiClient googleApiClient, String str, String str2, zzah zzah) {
                return googleApiClient.execute(new zzj(this, googleApiClient, str, str2, (zzah) null));
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2) {
                return zza(googleApiClient, str, str2, (zzah) null);
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str) {
                return zza(googleApiClient, str, (String) null, (zzah) null);
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient) {
                return zza(googleApiClient, (String) null, (String) null, (zzah) null);
            }

            public final PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient) {
                return googleApiClient.execute(new zzi(this, googleApiClient));
            }

            public final PendingResult<Status> stopApplication(GoogleApiClient googleApiClient) {
                return googleApiClient.execute(new zzl(this, googleApiClient));
            }

            public final PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str) {
                return googleApiClient.execute(new zzk(this, googleApiClient, str));
            }

            public final void setVolume(GoogleApiClient googleApiClient, double d) throws IOException, IllegalArgumentException, IllegalStateException {
                try {
                    ((zzct) googleApiClient.getClient(zzdl.zzzt)).setVolume(d);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public final double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzct) googleApiClient.getClient(zzdl.zzzt)).getVolume();
            }

            public final void setMute(GoogleApiClient googleApiClient, boolean z) throws IOException, IllegalStateException {
                try {
                    ((zzct) googleApiClient.getClient(zzdl.zzzt)).setMute(z);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public final boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzct) googleApiClient.getClient(zzdl.zzzt)).isMute();
            }

            public final int getActiveInputState(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzct) googleApiClient.getClient(zzdl.zzzt)).getActiveInputState();
            }

            public final int getStandbyState(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzct) googleApiClient.getClient(zzdl.zzzt)).getStandbyState();
            }

            public final ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzct) googleApiClient.getClient(zzdl.zzzt)).getApplicationMetadata();
            }

            public final String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzct) googleApiClient.getClient(zzdl.zzzt)).getApplicationStatus();
            }

            public final void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException {
                try {
                    ((zzct) googleApiClient.getClient(zzdl.zzzt)).setMessageReceivedCallbacks(str, messageReceivedCallback);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public final void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException {
                try {
                    ((zzct) googleApiClient.getClient(zzdl.zzzt)).removeMessageReceivedCallbacks(str);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }
        }

        int getActiveInputState(GoogleApiClient googleApiClient) throws IllegalStateException;

        ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException;

        String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException;

        int getStandbyState(GoogleApiClient googleApiClient) throws IllegalStateException;

        double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException;

        boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException;

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions);

        @Deprecated
        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z);

        PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient);

        void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException;

        void requestStatus(GoogleApiClient googleApiClient) throws IOException, IllegalStateException;

        PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2);

        void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException;

        void setMute(GoogleApiClient googleApiClient, boolean z) throws IOException, IllegalStateException;

        void setVolume(GoogleApiClient googleApiClient, double d) throws IOException, IllegalArgumentException, IllegalStateException;

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient);

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str);
    }

    public static class Listener {
        public void onApplicationStatusChanged() {
        }

        public void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
        }

        public void onApplicationDisconnected(int i) {
        }

        public void onActiveInputStateChanged(int i) {
        }

        public void onStandbyStateChanged(int i) {
        }

        public void onVolumeChanged() {
        }
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice castDevice, String str, String str2);
    }

    private Cast() {
    }

    @VisibleForTesting
    static abstract class zza extends zzcl<ApplicationConnectionResult> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zza */
        public void doExecute(zzct zzct) throws RemoteException {
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzm(this, status);
        }
    }

    public static final class CastOptions implements Api.ApiOptions.HasOptions {
        final Bundle extras;
        final CastDevice zzaj;
        final Listener zzak;
        /* access modifiers changed from: private */
        public final int zzal;

        private CastOptions(Builder builder) {
            this.zzaj = builder.zzaj;
            this.zzak = builder.zzak;
            this.zzal = builder.zzal;
            this.extras = builder.extras;
        }

        public static final class Builder {
            /* access modifiers changed from: private */
            public Bundle extras;
            CastDevice zzaj;
            Listener zzak;
            /* access modifiers changed from: private */
            public int zzal = 0;

            public Builder(CastDevice castDevice, Listener listener) {
                Preconditions.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                Preconditions.checkNotNull(listener, "CastListener parameter cannot be null");
                this.zzaj = castDevice;
                this.zzak = listener;
            }

            public final Builder setVerboseLoggingEnabled(boolean z) {
                if (z) {
                    this.zzal |= 1;
                } else {
                    this.zzal &= -2;
                }
                return this;
            }

            public final Builder zza(Bundle bundle) {
                this.extras = bundle;
                return this;
            }

            public final CastOptions build() {
                return new CastOptions(this, (zze) null);
            }
        }

        @Deprecated
        public static Builder builder(CastDevice castDevice, Listener listener) {
            return new Builder(castDevice, listener);
        }

        /* synthetic */ CastOptions(Builder builder, zze zze) {
            this(builder);
        }
    }
}
