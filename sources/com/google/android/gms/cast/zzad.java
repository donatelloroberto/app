package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzad implements Parcelable.Creator<zzae> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzae[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzac zzac = null;
        zzac zzac2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    zzac2 = (zzac) SafeParcelReader.createParcelable(parcel, readHeader, zzac.CREATOR);
                    break;
                case 3:
                    zzac = (zzac) SafeParcelReader.createParcelable(parcel, readHeader, zzac.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzae(zzac2, zzac);
    }
}
