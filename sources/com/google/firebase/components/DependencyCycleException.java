package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.1.0 */
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> componentsInCycle;

    @KeepForSdk
    public DependencyCycleException(List<Component<?>> componentsInCycle2) {
        super("Dependency cycle detected: " + Arrays.toString(componentsInCycle2.toArray()));
        this.componentsInCycle = componentsInCycle2;
    }

    @KeepForSdk
    public List<Component<?>> getComponentsInCycle() {
        return this.componentsInCycle;
    }
}
