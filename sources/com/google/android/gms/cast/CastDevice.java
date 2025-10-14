package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdc;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "CastDeviceCreator")
@SafeParcelable.Reserved({1})
public class CastDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_AUDIO_OUT = 4;
    public static final int CAPABILITY_MULTIZONE_GROUP = 32;
    public static final int CAPABILITY_VIDEO_IN = 2;
    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final Parcelable.Creator<CastDevice> CREATOR = new zzn();
    @SafeParcelable.Field(defaultValue = "-1", getter = "getStatus", id = 10)
    private int status;
    @SafeParcelable.Field(getter = "getDeviceIdRaw", id = 2)
    private String zzan;
    @SafeParcelable.Field(id = 3)
    private String zzao;
    private InetAddress zzap;
    @SafeParcelable.Field(getter = "getFriendlyName", id = 4)
    private String zzaq;
    @SafeParcelable.Field(getter = "getModelName", id = 5)
    private String zzar;
    @SafeParcelable.Field(getter = "getDeviceVersion", id = 6)
    private String zzas;
    @SafeParcelable.Field(getter = "getServicePort", id = 7)
    private int zzat;
    @SafeParcelable.Field(getter = "getIcons", id = 8)
    private List<WebImage> zzau;
    @SafeParcelable.Field(getter = "getCapabilities", id = 9)
    private int zzav;
    @SafeParcelable.Field(getter = "getServiceInstanceName", id = 11)
    private String zzaw;
    @SafeParcelable.Field(getter = "getReceiverMetricsId", id = 12)
    private String zzax;
    @SafeParcelable.Field(getter = "getRcnEnabledStatus", id = 13)
    private int zzay;
    @SafeParcelable.Field(getter = "getHotspotBssid", id = 14)
    private String zzaz;
    @SafeParcelable.Field(getter = "getIpLowestTwoBytes", id = 15)
    private byte[] zzba;
    @SafeParcelable.Field(getter = "getCloudDeviceId", id = 16)
    private String zzbb;

    @SafeParcelable.Constructor
    CastDevice(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 8) List<WebImage> list, @SafeParcelable.Param(id = 9) int i2, @SafeParcelable.Param(id = 10) int i3, @SafeParcelable.Param(id = 11) String str6, @SafeParcelable.Param(id = 12) String str7, @SafeParcelable.Param(id = 13) int i4, @SafeParcelable.Param(id = 14) String str8, @SafeParcelable.Param(id = 15) byte[] bArr, @SafeParcelable.Param(id = 16) String str9) {
        this.zzan = zza(str);
        this.zzao = zza(str2);
        if (!TextUtils.isEmpty(this.zzao)) {
            try {
                this.zzap = InetAddress.getByName(this.zzao);
            } catch (UnknownHostException e) {
                String str10 = this.zzao;
                String message = e.getMessage();
                Log.i("CastDevice", new StringBuilder(String.valueOf(str10).length() + 48 + String.valueOf(message).length()).append("Unable to convert host address (").append(str10).append(") to ipaddress: ").append(message).toString());
            }
        }
        this.zzaq = zza(str3);
        this.zzar = zza(str4);
        this.zzas = zza(str5);
        this.zzat = i;
        this.zzau = list == null ? new ArrayList<>() : list;
        this.zzav = i2;
        this.status = i3;
        this.zzaw = zza(str6);
        this.zzax = str7;
        this.zzay = i4;
        this.zzaz = str8;
        this.zzba = bArr;
        this.zzbb = str9;
    }

    public String getDeviceId() {
        if (this.zzan.startsWith("__cast_nearby__")) {
            return this.zzan.substring(16);
        }
        return this.zzan;
    }

    public boolean hasIPv4Address() {
        if (getInetAddress() == null || !(getInetAddress() instanceof Inet4Address)) {
            return false;
        }
        return true;
    }

    public boolean hasIPv6Address() {
        if (getInetAddress() == null || !(getInetAddress() instanceof Inet6Address)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public Inet4Address getIpAddress() {
        if (hasIPv4Address()) {
            return (Inet4Address) this.zzap;
        }
        return null;
    }

    public InetAddress getInetAddress() {
        return this.zzap;
    }

    public String getFriendlyName() {
        return this.zzaq;
    }

    public String getModelName() {
        return this.zzar;
    }

    public String getDeviceVersion() {
        return this.zzas;
    }

    public int getServicePort() {
        return this.zzat;
    }

    public List<WebImage> getIcons() {
        return Collections.unmodifiableList(this.zzau);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r1.getHeight() < r5) goto L_0x005a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.common.images.WebImage getIcon(int r9, int r10) {
        /*
            r8 = this;
            r7 = 0
            r0 = 0
            java.util.List<com.google.android.gms.common.images.WebImage> r1 = r8.zzau
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x000b
        L_0x000a:
            return r0
        L_0x000b:
            if (r9 <= 0) goto L_0x000f
            if (r10 > 0) goto L_0x0018
        L_0x000f:
            java.util.List<com.google.android.gms.common.images.WebImage> r0 = r8.zzau
            java.lang.Object r0 = r0.get(r7)
            com.google.android.gms.common.images.WebImage r0 = (com.google.android.gms.common.images.WebImage) r0
            goto L_0x000a
        L_0x0018:
            java.util.List<com.google.android.gms.common.images.WebImage> r1 = r8.zzau
            java.util.Iterator r3 = r1.iterator()
            r1 = r0
            r2 = r0
        L_0x0020:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x005c
            java.lang.Object r0 = r3.next()
            com.google.android.gms.common.images.WebImage r0 = (com.google.android.gms.common.images.WebImage) r0
            int r4 = r0.getWidth()
            int r5 = r0.getHeight()
            if (r4 < r9) goto L_0x0048
            if (r5 < r10) goto L_0x0048
            if (r2 == 0) goto L_0x0046
            int r6 = r2.getWidth()
            if (r6 <= r4) goto L_0x006e
            int r4 = r2.getHeight()
            if (r4 <= r5) goto L_0x006e
        L_0x0046:
            r2 = r0
            goto L_0x0020
        L_0x0048:
            if (r4 >= r9) goto L_0x006e
            if (r5 >= r10) goto L_0x006e
            if (r1 == 0) goto L_0x005a
            int r6 = r1.getWidth()
            if (r6 >= r4) goto L_0x006e
            int r4 = r1.getHeight()
            if (r4 >= r5) goto L_0x006e
        L_0x005a:
            r1 = r0
            goto L_0x0020
        L_0x005c:
            if (r2 == 0) goto L_0x0060
        L_0x005e:
            r0 = r2
            goto L_0x000a
        L_0x0060:
            if (r1 == 0) goto L_0x0064
            r2 = r1
            goto L_0x005e
        L_0x0064:
            java.util.List<com.google.android.gms.common.images.WebImage> r0 = r8.zzau
            java.lang.Object r0 = r0.get(r7)
            com.google.android.gms.common.images.WebImage r0 = (com.google.android.gms.common.images.WebImage) r0
            r2 = r0
            goto L_0x005e
        L_0x006e:
            r0 = r1
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastDevice.getIcon(int, int):com.google.android.gms.common.images.WebImage");
    }

    @VisibleForTesting
    public boolean hasIcons() {
        return !this.zzau.isEmpty();
    }

    public boolean hasCapability(int i) {
        return (this.zzav & i) == i;
    }

    public boolean hasCapabilities(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int hasCapability : iArr) {
            if (!hasCapability(hasCapability)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return String.format("\"%s\" (%s)", new Object[]{this.zzaq, this.zzan});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzan, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzao, false);
        SafeParcelWriter.writeString(parcel, 4, getFriendlyName(), false);
        SafeParcelWriter.writeString(parcel, 5, getModelName(), false);
        SafeParcelWriter.writeString(parcel, 6, getDeviceVersion(), false);
        SafeParcelWriter.writeInt(parcel, 7, getServicePort());
        SafeParcelWriter.writeTypedList(parcel, 8, getIcons(), false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzav);
        SafeParcelWriter.writeInt(parcel, 10, this.status);
        SafeParcelWriter.writeString(parcel, 11, this.zzaw, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzax, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzay);
        SafeParcelWriter.writeString(parcel, 14, this.zzaz, false);
        SafeParcelWriter.writeByteArray(parcel, 15, this.zzba, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzbb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        if (this.zzan == null) {
            if (castDevice.zzan != null) {
                return false;
            }
            return true;
        } else if (!zzdc.zza(this.zzan, castDevice.zzan) || !zzdc.zza(this.zzap, castDevice.zzap) || !zzdc.zza(this.zzar, castDevice.zzar) || !zzdc.zza(this.zzaq, castDevice.zzaq) || !zzdc.zza(this.zzas, castDevice.zzas) || this.zzat != castDevice.zzat || !zzdc.zza(this.zzau, castDevice.zzau) || this.zzav != castDevice.zzav || this.status != castDevice.status || !zzdc.zza(this.zzaw, castDevice.zzaw) || !zzdc.zza(Integer.valueOf(this.zzay), Integer.valueOf(castDevice.zzay)) || !zzdc.zza(this.zzaz, castDevice.zzaz) || !zzdc.zza(this.zzax, castDevice.zzax) || !zzdc.zza(this.zzas, castDevice.getDeviceVersion()) || this.zzat != castDevice.getServicePort() || (((this.zzba != null || castDevice.zzba != null) && !Arrays.equals(this.zzba, castDevice.zzba)) || !zzdc.zza(this.zzbb, castDevice.zzbb))) {
            return false;
        } else {
            return true;
        }
    }

    @VisibleForTesting
    public boolean isSameDevice(CastDevice castDevice) {
        if (castDevice == null) {
            return false;
        }
        if (!TextUtils.isEmpty(getDeviceId()) && !getDeviceId().startsWith("__cast_ble__") && !TextUtils.isEmpty(castDevice.getDeviceId()) && !castDevice.getDeviceId().startsWith("__cast_ble__")) {
            return zzdc.zza(getDeviceId(), castDevice.getDeviceId());
        }
        if (TextUtils.isEmpty(this.zzaz) || TextUtils.isEmpty(castDevice.zzaz)) {
            return false;
        }
        return zzdc.zza(this.zzaz, castDevice.zzaz);
    }

    public int hashCode() {
        if (this.zzan == null) {
            return 0;
        }
        return this.zzan.hashCode();
    }

    public void putInBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
        }
    }

    public static CastDevice getFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice) bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    public boolean isOnLocalNetwork() {
        return !this.zzan.startsWith("__cast_nearby__");
    }

    private static String zza(String str) {
        return str == null ? "" : str;
    }
}
