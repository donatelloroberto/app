package com.google.firebase.auth;

import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.annotations.PublicApi;
import java.util.Map;

@PublicApi
/* compiled from: com.google.firebase:firebase-common@@16.1.0 */
public class GetTokenResult {
    private static final String AUTH_TIMESTAMP = "auth_time";
    private static final String EXPIRATION_TIMESTAMP = "exp";
    private static final String FIREBASE_KEY = "firebase";
    private static final String ISSUED_AT_TIMESTAMP = "iat";
    private static final String SIGN_IN_PROVIDER = "sign_in_provider";
    private Map<String, Object> claims;
    private String token;

    @KeepForSdk
    public GetTokenResult(String token2, Map<String, Object> claims2) {
        this.token = token2;
        this.claims = claims2;
    }

    @Nullable
    @PublicApi
    public String getToken() {
        return this.token;
    }

    @PublicApi
    public long getExpirationTimestamp() {
        return getLongFromClaimsSafely(EXPIRATION_TIMESTAMP);
    }

    @PublicApi
    public long getAuthTimestamp() {
        return getLongFromClaimsSafely(AUTH_TIMESTAMP);
    }

    @PublicApi
    public long getIssuedAtTimestamp() {
        return getLongFromClaimsSafely(ISSUED_AT_TIMESTAMP);
    }

    @Nullable
    @PublicApi
    public String getSignInProvider() {
        Map<String, Object> firebaseElem = (Map) this.claims.get(FIREBASE_KEY);
        if (firebaseElem != null) {
            return (String) firebaseElem.get(SIGN_IN_PROVIDER);
        }
        return null;
    }

    @PublicApi
    public Map<String, Object> getClaims() {
        return this.claims;
    }

    private long getLongFromClaimsSafely(String key) {
        Integer result = (Integer) this.claims.get(key);
        if (result == null) {
            return 0;
        }
        return result.longValue();
    }
}
