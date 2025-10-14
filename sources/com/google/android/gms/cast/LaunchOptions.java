package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdc;
import java.util.Locale;

@SafeParcelable.Class(creator = "LaunchOptionsCreator")
@SafeParcelable.Reserved({1})
public class LaunchOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LaunchOptions> CREATOR = new zzai();
    @SafeParcelable.Field(getter = "getRelaunchIfRunning", id = 2)
    private boolean zzdc;
    @SafeParcelable.Field(getter = "getLanguage", id = 3)
    private String zzdd;

    @SafeParcelable.Constructor
    LaunchOptions(@SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) String str) {
        this.zzdc = z;
        this.zzdd = str;
    }

    @VisibleForTesting
    public static final class Builder {
        private LaunchOptions zzdb = new LaunchOptions();

        public final Builder setRelaunchIfRunning(boolean z) {
            this.zzdb.setRelaunchIfRunning(z);
            return this;
        }

        public final Builder setLocale(Locale locale) {
            this.zzdb.setLanguage(zzdc.zza(locale));
            return this;
        }

        public final LaunchOptions build() {
            return this.zzdb;
        }
    }

    public LaunchOptions() {
        this(false, zzdc.zza(Locale.getDefault()));
    }

    public void setRelaunchIfRunning(boolean z) {
        this.zzdc = z;
    }

    public boolean getRelaunchIfRunning() {
        return this.zzdc;
    }

    public void setLanguage(String str) {
        this.zzdd = str;
    }

    public String getLanguage() {
        return this.zzdd;
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", new Object[]{Boolean.valueOf(this.zzdc), this.zzdd});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, getRelaunchIfRunning());
        SafeParcelWriter.writeString(parcel, 3, getLanguage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        if (this.zzdc != launchOptions.zzdc || !zzdc.zza(this.zzdd, launchOptions.zzdd)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzdc), this.zzdd);
    }
}
