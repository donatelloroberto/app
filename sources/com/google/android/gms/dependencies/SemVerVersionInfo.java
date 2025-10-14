package com.google.android.gms.dependencies;

import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/google/android/gms/dependencies/SemVerVersionInfo;", "", "major", "", "minor", "patch", "(III)V", "getMajor", "()I", "getMinor", "getPatch", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "strict-version-matcher-plugin"}, k = 1, mv = {1, 1, 11})
/* compiled from: Versions.kt */
public final class SemVerVersionInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int major;
    private final int minor;
    private final int patch;

    @NotNull
    public static /* bridge */ /* synthetic */ SemVerVersionInfo copy$default(SemVerVersionInfo semVerVersionInfo, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = semVerVersionInfo.major;
        }
        if ((i4 & 2) != 0) {
            i2 = semVerVersionInfo.minor;
        }
        if ((i4 & 4) != 0) {
            i3 = semVerVersionInfo.patch;
        }
        return semVerVersionInfo.copy(i, i2, i3);
    }

    public final int component1() {
        return this.major;
    }

    public final int component2() {
        return this.minor;
    }

    public final int component3() {
        return this.patch;
    }

    @NotNull
    public final SemVerVersionInfo copy(int major2, int minor2, int patch2) {
        return new SemVerVersionInfo(major2, minor2, patch2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof SemVerVersionInfo)) {
                return false;
            }
            SemVerVersionInfo semVerVersionInfo = (SemVerVersionInfo) obj;
            if (!(this.major == semVerVersionInfo.major)) {
                return false;
            }
            if (!(this.minor == semVerVersionInfo.minor)) {
                return false;
            }
            if (!(this.patch == semVerVersionInfo.patch)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.major) * 31) + Integer.hashCode(this.minor)) * 31) + Integer.hashCode(this.patch);
    }

    public String toString() {
        return "SemVerVersionInfo(major=" + this.major + ", minor=" + this.minor + ", patch=" + this.patch + ")";
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/android/gms/dependencies/SemVerVersionInfo$Companion;", "", "()V", "parseString", "Lcom/google/android/gms/dependencies/SemVerVersionInfo;", "versionString", "", "strict-version-matcher-plugin"}, k = 1, mv = {1, 1, 11})
    /* compiled from: Versions.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final SemVerVersionInfo parseString(@NotNull String versionString) {
            Intrinsics.checkParameterIsNotNull(versionString, "versionString");
            List parts = StringsKt.split$default((CharSequence) StringsKt.trim((CharSequence) versionString).toString(), new String[]{"."}, false, 0, 6, (Object) null);
            if (parts.size() != 3) {
                throw new IllegalArgumentException("versionString didn't have 3 parts divided by periods.");
            }
            Integer major = Integer.valueOf((String) parts.get(0));
            Integer minor = Integer.valueOf((String) parts.get(1));
            String patchString = (String) parts.get(2);
            int dashIndex = StringsKt.indexOf$default((CharSequence) patchString, "-", 0, false, 6, (Object) null);
            if (dashIndex != -1) {
                if (patchString == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                patchString = patchString.substring(0, dashIndex);
                Intrinsics.checkExpressionValueIsNotNull(patchString, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
            Integer patch = Integer.valueOf(patchString);
            Intrinsics.checkExpressionValueIsNotNull(major, "major");
            int intValue = major.intValue();
            Intrinsics.checkExpressionValueIsNotNull(minor, "minor");
            int intValue2 = minor.intValue();
            Intrinsics.checkExpressionValueIsNotNull(patch, "patch");
            return new SemVerVersionInfo(intValue, intValue2, patch.intValue());
        }
    }

    public SemVerVersionInfo(int major2, int minor2, int patch2) {
        this.major = major2;
        this.minor = minor2;
        this.patch = patch2;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }
}
