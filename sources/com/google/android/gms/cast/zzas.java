package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzas implements Parcelable.Creator<MediaStatus> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaStatus[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        MediaInfo mediaInfo = null;
        long j = 0;
        int i = 0;
        double d = 0.0d;
        int i2 = 0;
        int i3 = 0;
        long j2 = 0;
        long j3 = 0;
        double d2 = 0.0d;
        boolean z = false;
        long[] jArr = null;
        int i4 = 0;
        int i5 = 0;
        String str = null;
        int i6 = 0;
        ArrayList<MediaQueueItem> arrayList = null;
        boolean z2 = false;
        AdBreakStatus adBreakStatus = null;
        VideoInfo videoInfo = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    mediaInfo = (MediaInfo) SafeParcelReader.createParcelable(parcel, readHeader, MediaInfo.CREATOR);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    d = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 8:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 9:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 10:
                    d2 = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                case 11:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 12:
                    jArr = SafeParcelReader.createLongArray(parcel, readHeader);
                    break;
                case 13:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 14:
                    i5 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 15:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 16:
                    i6 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 17:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, MediaQueueItem.CREATOR);
                    break;
                case 18:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 19:
                    adBreakStatus = (AdBreakStatus) SafeParcelReader.createParcelable(parcel, readHeader, AdBreakStatus.CREATOR);
                    break;
                case 20:
                    videoInfo = (VideoInfo) SafeParcelReader.createParcelable(parcel, readHeader, VideoInfo.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new MediaStatus(mediaInfo, j, i, d, i2, i3, j2, j3, d2, z, jArr, i4, i5, str, i6, arrayList, z2, adBreakStatus, videoInfo);
    }
}
