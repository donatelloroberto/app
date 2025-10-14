package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.zzae;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzde implements Parcelable.Creator<zzdb> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdb[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        double d = 0.0d;
        zzae zzae = null;
        int i = 0;
        ApplicationMetadata applicationMetadata = null;
        int i2 = 0;
        boolean z = false;
        double d2 = 0.0d;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    d2 = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                case 3:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    applicationMetadata = (ApplicationMetadata) SafeParcelReader.createParcelable(parcel, readHeader, ApplicationMetadata.CREATOR);
                    break;
                case 6:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    zzae = (zzae) SafeParcelReader.createParcelable(parcel, readHeader, zzae.CREATOR);
                    break;
                case 8:
                    d = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzdb(d2, z, i2, applicationMetadata, i, zzae, d);
    }
}
