package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.InputStream;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@SafeParcelable.Class(creator = "CacheEntryParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzhi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzhi> CREATOR = new zzhj();
    @Nullable
    @GuardedBy("this")
    @SafeParcelable.Field(getter = "getContentFileDescriptor", id = 2)
    private ParcelFileDescriptor zzaju;

    public zzhi() {
        this((ParcelFileDescriptor) null);
    }

    @SafeParcelable.Constructor
    public zzhi(@Nullable @SafeParcelable.Param(id = 2) ParcelFileDescriptor parcelFileDescriptor) {
        this.zzaju = parcelFileDescriptor;
    }

    private final synchronized ParcelFileDescriptor zzhk() {
        return this.zzaju;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, zzhk(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final synchronized boolean zzhi() {
        return this.zzaju != null;
    }

    @Nullable
    public final synchronized InputStream zzhj() {
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = null;
        synchronized (this) {
            if (this.zzaju != null) {
                autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.zzaju);
                this.zzaju = null;
            }
        }
        return autoCloseInputStream;
    }
}
