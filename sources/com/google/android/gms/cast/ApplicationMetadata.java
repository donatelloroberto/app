package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.cast.zzdc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

@SafeParcelable.Class(creator = "ApplicationMetadataCreator")
@SafeParcelable.Reserved({1})
public class ApplicationMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new zzd();
    @SafeParcelable.Field(getter = "getName", id = 3)
    private String name;
    @SafeParcelable.Field(getter = "getSenderAppIdentifier", id = 6)
    private String zzaa;
    @SafeParcelable.Field(getter = "getSenderAppLaunchUrl", id = 7)
    private Uri zzab;
    @SafeParcelable.Field(getter = "getIconUrl", id = 8)
    @Nullable
    private String zzac;
    @SafeParcelable.Field(getter = "getApplicationId", id = 2)
    private String zzy;
    @SafeParcelable.Field(getter = "getSupportedNamespaces", id = 5)
    private List<String> zzz;

    @SafeParcelable.Constructor
    ApplicationMetadata(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) @Nullable List<WebImage> list, @SafeParcelable.Param(id = 5) List<String> list2, @SafeParcelable.Param(id = 6) String str3, @SafeParcelable.Param(id = 7) Uri uri, @SafeParcelable.Param(id = 8) @Nullable String str4) {
        this.zzy = str;
        this.name = str2;
        this.zzz = list2;
        this.zzaa = str3;
        this.zzab = uri;
        this.zzac = str4;
    }

    private ApplicationMetadata() {
        this.zzz = new ArrayList();
    }

    public String getApplicationId() {
        return this.zzy;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNamespaceSupported(String str) {
        return this.zzz != null && this.zzz.contains(str);
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzz);
    }

    public boolean areNamespacesSupported(List<String> list) {
        return this.zzz != null && this.zzz.containsAll(list);
    }

    public String getSenderAppIdentifier() {
        return this.zzaa;
    }

    public List<WebImage> getImages() {
        return null;
    }

    public String toString() {
        String str = this.zzy;
        String str2 = this.name;
        int size = this.zzz == null ? 0 : this.zzz.size();
        String str3 = this.zzaa;
        String valueOf = String.valueOf(this.zzab);
        String str4 = this.zzac;
        return new StringBuilder(String.valueOf(str).length() + 110 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(valueOf).length() + String.valueOf(str4).length()).append("applicationId: ").append(str).append(", name: ").append(str2).append(", namespaces.count: ").append(size).append(", senderAppIdentifier: ").append(str3).append(", senderAppLaunchUrl: ").append(valueOf).append(", iconUrl: ").append(str4).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getApplicationId(), false);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getImages(), false);
        SafeParcelWriter.writeStringList(parcel, 5, getSupportedNamespaces(), false);
        SafeParcelWriter.writeString(parcel, 6, getSenderAppIdentifier(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzab, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzac, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzy, this.name, this.zzz, this.zzaa, this.zzab, this.zzac);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        if (!zzdc.zza(this.zzy, applicationMetadata.zzy) || !zzdc.zza(this.name, applicationMetadata.name) || !zzdc.zza(this.zzz, applicationMetadata.zzz) || !zzdc.zza(this.zzaa, applicationMetadata.zzaa) || !zzdc.zza(this.zzab, applicationMetadata.zzab) || !zzdc.zza(this.zzac, applicationMetadata.zzac)) {
            return false;
        }
        return true;
    }
}
