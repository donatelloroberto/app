package com.google.android.gms.internal.ads;

import android.webkit.ConsoleMessage;

final /* synthetic */ class zzaqv {
    static final /* synthetic */ int[] zzdbn = new int[ConsoleMessage.MessageLevel.values().length];

    static {
        try {
            zzdbn[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzdbn[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzdbn[ConsoleMessage.MessageLevel.LOG.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzdbn[ConsoleMessage.MessageLevel.TIP.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzdbn[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
