package com.google.firebase.platforminfo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-common@@16.1.0 */
public class GlobalLibraryVersionRegistrar {
    private static volatile GlobalLibraryVersionRegistrar INSTANCE;
    private final Set<LibraryVersion> infos = new HashSet();

    GlobalLibraryVersionRegistrar() {
    }

    public void registerVersion(String sdkName, String version) {
        synchronized (this.infos) {
            this.infos.add(LibraryVersion.create(sdkName, version));
        }
    }

    /* access modifiers changed from: package-private */
    public Set<LibraryVersion> getRegisteredVersions() {
        Set<LibraryVersion> unmodifiableSet;
        synchronized (this.infos) {
            unmodifiableSet = Collections.unmodifiableSet(this.infos);
        }
        return unmodifiableSet;
    }

    public static GlobalLibraryVersionRegistrar getInstance() {
        GlobalLibraryVersionRegistrar localRef = INSTANCE;
        if (localRef == null) {
            synchronized (GlobalLibraryVersionRegistrar.class) {
                try {
                    localRef = INSTANCE;
                    if (localRef == null) {
                        GlobalLibraryVersionRegistrar localRef2 = new GlobalLibraryVersionRegistrar();
                        try {
                            INSTANCE = localRef2;
                            localRef = localRef2;
                        } catch (Throwable th) {
                            th = th;
                            GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar = localRef2;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        }
        return localRef;
    }
}
