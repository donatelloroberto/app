package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.flags.Flag;
import com.google.android.gms.flags.FlagRegistry;
import com.google.android.gms.flags.Singletons;

public final class zzdd {
    public static final Flag<Boolean> zzzs = Flag.define(0, "gms:cast:remote_display_enabled", (Boolean) false);

    public static final void initialize(Context context) {
        Singletons.flagRegistry();
        FlagRegistry.initialize(context);
    }
}
