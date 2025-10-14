package com.google.firebase.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.1.0 */
public class InternalTokenResult {
    private String token;

    @KeepForSdk
    public InternalTokenResult(@Nullable String token2) {
        this.token = token2;
    }

    @Nullable
    @KeepForSdk
    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        return Objects.hashCode(this.token);
    }

    public boolean equals(Object o) {
        if (!(o instanceof InternalTokenResult)) {
            return false;
        }
        return Objects.equal(this.token, ((InternalTokenResult) o).token);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("token", this.token).toString();
    }
}
