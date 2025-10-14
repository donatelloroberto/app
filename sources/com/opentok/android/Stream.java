package com.opentok.android;

import java.util.Date;

public final class Stream implements Comparable<Stream> {
    private Session session;
    private com.opentok.android.v3.Stream v3Stream;

    public enum StreamVideoType {
        StreamVideoTypeCamera(1),
        StreamVideoTypeScreen(2);
        
        private int videoType;

        private StreamVideoType(int type) {
            this.videoType = type;
        }

        public int getVideoType() {
            return this.videoType;
        }

        static StreamVideoType fromType(int typeId) {
            for (StreamVideoType type : values()) {
                if (type.getVideoType() == typeId) {
                    return type;
                }
            }
            throw new IllegalArgumentException("unknown type " + typeId);
        }
    }

    public String getStreamId() {
        return this.v3Stream.getStreamId();
    }

    public Date getCreationTime() {
        return this.v3Stream.getCreationTime();
    }

    public boolean hasVideo() {
        return this.v3Stream.hasVideo();
    }

    public boolean hasAudio() {
        return this.v3Stream.hasAudio();
    }

    public Connection getConnection() {
        return new Connection(this.v3Stream.getAssociatedConnection());
    }

    public String getName() {
        return this.v3Stream.getStreamName();
    }

    public int getVideoWidth() {
        return this.v3Stream.getWidth();
    }

    public int getVideoHeight() {
        return this.v3Stream.getHeight();
    }

    public Session getSession() {
        return this.session;
    }

    public StreamVideoType getStreamVideoType() {
        return new StreamVideoType[]{StreamVideoType.StreamVideoTypeCamera, StreamVideoType.StreamVideoTypeScreen}[this.v3Stream.getVideoType().ordinal()];
    }

    public boolean equals(Object rhs) {
        return this.v3Stream.equals(((Stream) rhs).v3Stream);
    }

    public int compareTo(Stream rhs) {
        return this.v3Stream.compareTo(rhs.v3Stream);
    }

    public String toString() {
        return String.format("streamId=%s", new Object[]{getStreamId()});
    }

    public int hashCode() {
        return this.v3Stream.hashCode();
    }

    Stream(com.opentok.android.v3.Stream v3Stream2, Session session2) {
        this.v3Stream = v3Stream2;
        this.session = session2;
    }

    /* access modifiers changed from: package-private */
    public com.opentok.android.v3.Stream getV3Stream() {
        return this.v3Stream;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
    }
}
