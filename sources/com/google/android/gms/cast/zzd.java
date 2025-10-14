package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzd implements Parcelable.Creator<ApplicationMetadata> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new ApplicationMetadata[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        Uri uri = null;
        String str2 = null;
        ArrayList<String> arrayList = null;
        ArrayList<WebImage> arrayList2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, WebImage.CREATOR);
                    break;
                case 5:
                    arrayList = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 6:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 8:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ApplicationMetadata(str4, str3, arrayList2, arrayList, str2, uri, str);
    }
}
