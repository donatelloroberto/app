package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseException;
import com.google.firebase.annotations.PublicApi;

@PublicApi
/* compiled from: com.google.firebase:firebase-common@@16.1.0 */
public class FirebaseAuthException extends FirebaseException {
    private final String errorCode;

    @PublicApi
    public FirebaseAuthException(@NonNull String errorCode2, @NonNull String detailMessage) {
        super(detailMessage);
        this.errorCode = Preconditions.checkNotEmpty(errorCode2);
    }

    @PublicApi
    @NonNull
    public String getErrorCode() {
        return this.errorCode;
    }
}
