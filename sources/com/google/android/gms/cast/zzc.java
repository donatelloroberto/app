package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzc implements Parcelable.Creator<AdBreakStatus> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new AdBreakStatus[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        String str = null;
        String str2 = null;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new AdBreakStatus(j3, j2, str2, str, j);
    }
}
