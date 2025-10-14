package com.google.android.gms.dependencies;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u000f\u001a\u00020\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/google/android/gms/dependencies/Artifact;", "", "groupId", "", "artifactId", "(Ljava/lang/String;Ljava/lang/String;)V", "getArtifactId", "()Ljava/lang/String;", "getGroupId", "component1", "component2", "copy", "equals", "", "other", "getGradleRef", "hashCode", "", "toString", "Companion", "strict-version-matcher-plugin"}, k = 1, mv = {1, 1, 11})
/* compiled from: DataObjects.kt */
public final class Artifact {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private final String artifactId;
    @NotNull
    private final String groupId;

    @NotNull
    public static /* bridge */ /* synthetic */ Artifact copy$default(Artifact artifact, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = artifact.groupId;
        }
        if ((i & 2) != 0) {
            str2 = artifact.artifactId;
        }
        return artifact.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.groupId;
    }

    @NotNull
    public final String component2() {
        return this.artifactId;
    }

    @NotNull
    public final Artifact copy(@NotNull String groupId2, @NotNull String artifactId2) {
        Intrinsics.checkParameterIsNotNull(groupId2, "groupId");
        Intrinsics.checkParameterIsNotNull(artifactId2, "artifactId");
        return new Artifact(groupId2, artifactId2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Artifact) {
                Artifact artifact = (Artifact) obj;
                if (!Intrinsics.areEqual((Object) this.groupId, (Object) artifact.groupId) || !Intrinsics.areEqual((Object) this.artifactId, (Object) artifact.artifactId)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        String str = this.groupId;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.artifactId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Artifact(groupId=" + this.groupId + ", artifactId=" + this.artifactId + ")";
    }

    public Artifact(@NotNull String groupId2, @NotNull String artifactId2) {
        Intrinsics.checkParameterIsNotNull(groupId2, "groupId");
        Intrinsics.checkParameterIsNotNull(artifactId2, "artifactId");
        this.groupId = groupId2;
        this.artifactId = artifactId2;
    }

    @NotNull
    public final String getArtifactId() {
        return this.artifactId;
    }

    @NotNull
    public final String getGroupId() {
        return this.groupId;
    }

    @NotNull
    public final String getGradleRef() {
        return this.groupId + ':' + this.artifactId;
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/android/gms/dependencies/Artifact$Companion;", "", "()V", "fromGradleRef", "Lcom/google/android/gms/dependencies/Artifact;", "referenceString", "", "strict-version-matcher-plugin"}, k = 1, mv = {1, 1, 11})
    /* compiled from: DataObjects.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final Artifact fromGradleRef(@NotNull String referenceString) {
            Intrinsics.checkParameterIsNotNull(referenceString, "referenceString");
            List stringSplit = StringsKt.split$default((CharSequence) referenceString, new String[]{":"}, false, 0, 6, (Object) null);
            if (stringSplit.size() >= 2) {
                return new Artifact((String) stringSplit.get(0), (String) stringSplit.get(1));
            }
            throw new IllegalArgumentException("Invalid Gradle reference string: " + referenceString);
        }
    }
}
