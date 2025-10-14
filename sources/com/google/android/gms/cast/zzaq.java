package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzaq implements Parcelable.Creator<MediaQueueItem> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaQueueItem[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        MediaInfo mediaInfo = null;
        int i = 0;
        boolean z = false;
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        long[] jArr = null;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    mediaInfo = (MediaInfo) SafeParcelReader.createParcelable(parcel, readHeader, MediaInfo.CREATOR);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 5:
                    d = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                case 6:
                    d2 = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                case 7:
                    d3 = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                case 8:
                    jArr = SafeParcelReader.createLongArray(parcel, readHeader);
                    break;
                case 9:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new MediaQueueItem(mediaInfo, i, z, d, d2, d3, jArr, str);
    }
}
