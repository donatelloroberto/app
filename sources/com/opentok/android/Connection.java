package com.opentok.android;

import java.util.Date;

public final class Connection implements Comparable<Connection> {
    private com.opentok.android.v3.Connection v3Connection;

    public String getConnectionId() {
        return this.v3Connection.getId();
    }

    public String getData() {
        return this.v3Connection.getData();
    }

    public Date getCreationTime() {
        return this.v3Connection.getCreationTime();
    }

    public boolean equals(Object rhs) {
        return this.v3Connection.equals(((Connection) rhs).v3Connection);
    }

    public int compareTo(Connection rhs) {
        return this.v3Connection.compareTo(rhs.v3Connection);
    }

    public int hashCode() {
        return this.v3Connection.hashCode();
    }

    Connection(com.opentok.android.v3.Connection v3Connection2) {
        this.v3Connection = v3Connection2;
    }

    /* access modifiers changed from: package-private */
    public com.opentok.android.v3.Connection getV3Hndl() {
        return this.v3Connection;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
    }
}
