package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzgq {
    @Nullable
    private static MessageDigest zzaix = null;
    protected Object mLock = new Object();

    /* access modifiers changed from: protected */
    @Nullable
    public final MessageDigest zzhg() {
        MessageDigest messageDigest;
        synchronized (this.mLock) {
            if (zzaix != null) {
                messageDigest = zzaix;
            } else {
                for (int i = 0; i < 2; i++) {
                    try {
                        zzaix = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                    }
                }
                messageDigest = zzaix;
            }
        }
        return messageDigest;
    }

    /* access modifiers changed from: package-private */
    public abstract byte[] zzx(String str);
}
