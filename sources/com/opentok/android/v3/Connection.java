package com.opentok.android.v3;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import java.util.Date;

@Keep
public class Connection implements Comparable<Connection> {
    private static final LogInterface log = OtLog.LogToken("[Connection]");
    private long nativeCtx;

    private native long finalizeNative(long j);

    private native long getCreationTimeNative(long j);

    private native String getDataNative(long j);

    private native String getIdNative(long j);

    private native String getSessionIdNative(long j);

    private native long initNative(long j);

    private static native void registerNatives();

    public String getId() {
        return getIdNative(this.nativeCtx);
    }

    public Date getCreationTime() {
        return new Date(getCreationTimeNative(this.nativeCtx));
    }

    public String getData() {
        return getDataNative(this.nativeCtx);
    }

    public String getSessionId() {
        return getSessionIdNative(this.nativeCtx);
    }

    public int compareTo(@NonNull Connection connection) {
        return hashCode() - connection.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Connection) && ((Connection) obj).getId().equals(getId());
    }

    public int hashCode() {
        return Utility.SDBMHash(getId());
    }

    /* access modifiers changed from: package-private */
    public long getNativeHndl() {
        return this.nativeCtx;
    }

    Connection(long nativeHndl) {
        this.nativeCtx = initNative(nativeHndl);
        Utility.androidAssert(0 != this.nativeCtx);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        log.d("finalize(...) called", new Object[0]);
        this.nativeCtx = finalizeNative(this.nativeCtx);
    }

    static {
        registerNatives();
    }
}
