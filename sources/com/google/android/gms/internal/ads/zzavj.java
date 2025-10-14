package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzavo;
import com.google.android.gms.internal.ads.zzavs;
import com.google.android.gms.internal.ads.zzawe;
import com.google.android.gms.internal.ads.zzaxc;
import java.security.GeneralSecurityException;
import java.util.Arrays;

final class zzavj implements zzayn {
    private final String zzdic;
    private final int zzdid;
    private zzawe zzdie;
    private zzavo zzdif;
    private int zzdig;

    zzavj(zzaxn zzaxn) throws GeneralSecurityException {
        this.zzdic = zzaxn.zzyw();
        if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzawg zzt = zzawg.zzt(zzaxn.zzyx());
                this.zzdie = (zzawe) zzauo.zzb(zzaxn);
                this.zzdid = zzt.getKeySize();
            } catch (zzbbu e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzavq zzj = zzavq.zzj(zzaxn.zzyx());
                this.zzdif = (zzavo) zzauo.zzb(zzaxn);
                this.zzdig = zzj.zzwr().getKeySize();
                this.zzdid = zzj.zzws().getKeySize() + this.zzdig;
            } catch (zzbbu e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e2);
            }
        } else {
            String valueOf = String.valueOf(this.zzdic);
            throw new GeneralSecurityException(valueOf.length() != 0 ? "unsupported AEAD DEM key type: ".concat(valueOf) : new String("unsupported AEAD DEM key type: "));
        }
    }

    public final zzatz zzi(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzdid) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        } else if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            return (zzatz) zzauo.zzb(this.zzdic, (zzawe) ((zzawe.zza) zzawe.zzxk().zza(this.zzdie)).zzs(zzbah.zzc(bArr, 0, this.zzdid)).zzadi());
        } else if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zzdig);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zzdig, this.zzdid);
            zzavo.zza zzb = zzavo.zzwp().zzal(this.zzdif.getVersion()).zzb((zzavs) ((zzavs.zza) zzavs.zzww().zza(this.zzdif.zzwn())).zzm(zzbah.zzo(copyOfRange)).zzadi());
            return (zzatz) zzauo.zzb(this.zzdic, (zzavo) zzb.zzb((zzaxc) ((zzaxc.zza) zzaxc.zzyn().zza(this.zzdif.zzwo())).zzaf(zzbah.zzo(copyOfRange2)).zzadi()).zzadi());
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
    }

    public final int zzwm() {
        return this.zzdid;
    }
}
