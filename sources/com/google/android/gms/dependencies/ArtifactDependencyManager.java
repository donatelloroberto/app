package com.google.android.gms.dependencies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.gradle.internal.impldep.com.google.common.annotations.VisibleForTesting;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\u0006\u0010\u0012\u001a\u00020\u0005R.\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00048\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/google/android/gms/dependencies/ArtifactDependencyManager;", "", "()V", "dependencies", "Ljava/util/HashMap;", "Lcom/google/android/gms/dependencies/Artifact;", "Ljava/util/ArrayList;", "Lcom/google/android/gms/dependencies/Dependency;", "dependencies$annotations", "getDependencies$strict_version_matcher_plugin", "()Ljava/util/HashMap;", "dependencyLock", "Ljava/lang/Object;", "addDependency", "", "dependency", "getDependencies", "", "artifact", "strict-version-matcher-plugin"}, k = 1, mv = {1, 1, 11})
/* compiled from: DataObjects.kt */
public final class ArtifactDependencyManager {
    @NotNull
    private final HashMap<Artifact, ArrayList<Dependency>> dependencies = new HashMap<>();
    private final Object dependencyLock = new Object();

    @VisibleForTesting
    public static /* synthetic */ void dependencies$annotations() {
    }

    @NotNull
    public final HashMap<Artifact, ArrayList<Dependency>> getDependencies$strict_version_matcher_plugin() {
        return this.dependencies;
    }

    public final void addDependency(@NotNull Dependency dependency) {
        Intrinsics.checkParameterIsNotNull(dependency, "dependency");
        synchronized (this.dependencyLock) {
            ArrayList depListForArtifact = this.dependencies.get(dependency.getToArtifact());
            if (depListForArtifact == null) {
                depListForArtifact = new ArrayList();
                this.dependencies.put(dependency.getToArtifact(), depListForArtifact);
            }
            depListForArtifact.add(dependency);
        }
    }

    @NotNull
    public final Collection<Dependency> getDependencies(@NotNull Artifact artifact) {
        Collection<Dependency> hashSet;
        Intrinsics.checkParameterIsNotNull(artifact, "artifact");
        synchronized (this.dependencyLock) {
            if (this.dependencies.get(artifact) == null) {
                hashSet = new HashSet<>();
            } else {
                hashSet = new HashSet<>(this.dependencies.get(artifact));
            }
        }
        return hashSet;
    }
}
