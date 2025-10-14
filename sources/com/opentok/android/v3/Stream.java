package com.opentok.android.v3;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import com.opentok.android.v3.debug.LogInterface;
import com.opentok.android.v3.debug.OtLog;
import java.util.Date;

@Keep
public class Stream implements Comparable<Stream> {
    private static final LogInterface log = OtLog.LogToken("[Stream]");
    static final SparseArray<VideoType> videoTypeTbl = new SparseArray<VideoType>() {
        {
            append(1, VideoType.Camera);
            append(2, VideoType.Screen);
        }
    };
    private long nativeCtx;

    public enum VideoType {
        Camera,
        Screen
    }

    private native long finalizeNative(long j);

    private native Connection getAssociatedConnectionNative(long j);

    private native long getStreamCreationTimeNative(long j);

    private native int getStreamHeightNative(long j);

    private native String getStreamIdNative(long j);

    private native String getStreamNameNative(long j);

    private native int getStreamWidthNative(long j);

    private native int getVideoTypeNative(long j);

    private native boolean hasAudioNative(long j);

    private native boolean hasVideoNative(long j);

    private native long initNative(long j);

    private static native void registerNatives();

    public String getStreamId() {
        return getStreamIdNative(this.nativeCtx);
    }

    public String getStreamName() {
        return getStreamNameNative(this.nativeCtx);
    }

    public int getWidth() {
        return getStreamWidthNative(this.nativeCtx);
    }

    public int getHeight() {
        return getStreamHeightNative(this.nativeCtx);
    }

    public Date getCreationTime() {
        return new Date(getStreamCreationTimeNative(this.nativeCtx));
    }

    public boolean hasAudio() {
        return hasAudioNative(this.nativeCtx);
    }

    public boolean hasVideo() {
        return hasVideoNative(this.nativeCtx);
    }

    public VideoType getVideoType() {
        return videoTypeTbl.get(getVideoTypeNative(this.nativeCtx));
    }

    public Connection getAssociatedConnection() {
        return getAssociatedConnectionNative(this.nativeCtx);
    }

    public int compareTo(@NonNull Stream stream) {
        return hashCode() - stream.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Stream) && ((Stream) obj).getStreamId().equals(getStreamId());
    }

    public int hashCode() {
        return Utility.SDBMHash(getStreamId());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        log.d("finalize(...) called", new Object[0]);
        this.nativeCtx = finalizeNative(this.nativeCtx);
    }

    /* access modifiers changed from: package-private */
    public long getNativeCtx() {
        return this.nativeCtx;
    }

    Stream(long nativeHndl) {
        this.nativeCtx = initNative(nativeHndl);
        Utility.androidAssert(0 != nativeHndl);
    }

    static {
        registerNatives();
    }
}
