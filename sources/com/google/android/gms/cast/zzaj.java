package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzaj implements Parcelable.Creator<MediaInfo> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaInfo[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        int i = 0;
        String str2 = null;
        MediaMetadata mediaMetadata = null;
        long j = 0;
        ArrayList<MediaTrack> arrayList = null;
        TextTrackStyle textTrackStyle = null;
        String str3 = null;
        ArrayList<AdBreakInfo> arrayList2 = null;
        ArrayList<AdBreakClipInfo> arrayList3 = null;
        String str4 = null;
        VastAdsRequest vastAdsRequest = null;
        long j2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    mediaMetadata = (MediaMetadata) SafeParcelReader.createParcelable(parcel, readHeader, MediaMetadata.CREATOR);
                    break;
                case 6:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 7:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, MediaTrack.CREATOR);
                    break;
                case 8:
                    textTrackStyle = (TextTrackStyle) SafeParcelReader.createParcelable(parcel, readHeader, TextTrackStyle.CREATOR);
                    break;
                case 9:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 10:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, AdBreakInfo.CREATOR);
                    break;
                case 11:
                    arrayList3 = SafeParcelReader.createTypedList(parcel, readHeader, AdBreakClipInfo.CREATOR);
                    break;
                case 12:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 13:
                    vastAdsRequest = (VastAdsRequest) SafeParcelReader.createParcelable(parcel, readHeader, VastAdsRequest.CREATOR);
                    break;
                case 14:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new MediaInfo(str, i, str2, mediaMetadata, j, arrayList, textTrackStyle, str3, arrayList2, arrayList3, str4, vastAdsRequest, j2);
    }
}
