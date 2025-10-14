package com.google.android.gms.cast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdl;
import com.google.android.gms.internal.cast.zzdx;
import com.google.android.gms.internal.cast.zzeh;

public final class CastRemoteDisplay {
    @Deprecated
    public static final Api<CastRemoteDisplayOptions> API = new Api<>("CastRemoteDisplay.API", zzad, zzdl.zzzu);
    public static final int CONFIGURATION_INTERACTIVE_NONREALTIME = 2;
    public static final int CONFIGURATION_INTERACTIVE_REALTIME = 1;
    public static final int CONFIGURATION_NONINTERACTIVE = 3;
    @Deprecated
    public static final CastRemoteDisplayApi CastRemoteDisplayApi = new zzdx(API);
    public static final String EXTRA_INT_SESSION_ENDED_STATUS_CODE = "extra_int_session_ended_status_code";
    private static final Api.AbstractClientBuilder<zzeh, CastRemoteDisplayOptions> zzad = new zzo();

    @Deprecated
    public interface CastRemoteDisplaySessionCallbacks {
        void onRemoteDisplayEnded(Status status);
    }

    @Deprecated
    public interface CastRemoteDisplaySessionResult extends Result {
        Display getPresentationDisplay();
    }

    public @interface Configuration {
    }

    public static final boolean isRemoteDisplaySdkSupported(Context context) {
        zzdd.initialize(context);
        return zzdd.zzzs.get().booleanValue();
    }

    private CastRemoteDisplay() {
    }

    public static CastRemoteDisplayClient getClient(@NonNull Context context) {
        return new CastRemoteDisplayClient(context);
    }

    @Deprecated
    public static final class CastRemoteDisplayOptions implements Api.ApiOptions.HasOptions {
        final CastDevice zzaj;
        final int zzbd;
        final CastRemoteDisplaySessionCallbacks zzbe;

        private CastRemoteDisplayOptions(Builder builder) {
            this.zzaj = builder.zzaj;
            this.zzbe = builder.zzbc;
            this.zzbd = builder.zzbd;
        }

        @Deprecated
        public static final class Builder {
            CastDevice zzaj;
            CastRemoteDisplaySessionCallbacks zzbc;
            int zzbd = 2;

            public Builder(CastDevice castDevice, CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks) {
                Preconditions.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                this.zzaj = castDevice;
                this.zzbc = castRemoteDisplaySessionCallbacks;
            }

            public final Builder setConfigPreset(@Configuration int i) {
                this.zzbd = i;
                return this;
            }

            public final CastRemoteDisplayOptions build() {
                return new CastRemoteDisplayOptions(this, (zzo) null);
            }
        }

        /* synthetic */ CastRemoteDisplayOptions(Builder builder, zzo zzo) {
            this(builder);
        }
    }
}
