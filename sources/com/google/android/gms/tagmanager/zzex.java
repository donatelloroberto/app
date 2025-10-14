package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzdf;
import com.google.android.gms.internal.gtm.zzdi;
import com.google.android.gms.internal.gtm.zzi;
import com.google.android.gms.internal.gtm.zzop;
import com.google.android.gms.internal.gtm.zzor;
import com.google.android.gms.internal.gtm.zzov;
import com.google.android.gms.internal.gtm.zzoz;
import com.google.android.gms.internal.gtm.zzuv;
import com.google.android.gms.internal.gtm.zzuw;
import com.google.android.gms.tagmanager.zzeh;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;

final class zzex implements zzah {
    private final String zzaec;
    private zzdh<zzop> zzajf;
    private final ExecutorService zzajm = zzdf.zzgp().zzr(zzdi.zzadg);
    private final Context zzrm;

    zzex(Context context, String str) {
        this.zzrm = context;
        this.zzaec = str;
    }

    public final void zza(zzdh<zzop> zzdh) {
        this.zzajf = zzdh;
    }

    public final void zzhk() {
        this.zzajm.execute(new zzey(this));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzjd() {
        if (this.zzajf == null) {
            throw new IllegalStateException("Callback must be set before execute");
        }
        this.zzajf.zzhj();
        zzdi.zzab("Attempting to load resource from disk");
        if ((zzeh.zziy().zziz() == zzeh.zza.CONTAINER || zzeh.zziy().zziz() == zzeh.zza.CONTAINER_DEBUG) && this.zzaec.equals(zzeh.zziy().getContainerId())) {
            this.zzajf.zzs(zzcz.zzaht);
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(zzje());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzor.zza(fileInputStream, byteArrayOutputStream);
                zzop zzop = (zzop) zzuw.zza(new zzop(), byteArrayOutputStream.toByteArray());
                if (zzop.zzqk == null && zzop.zzauy == null) {
                    throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
                }
                this.zzajf.zze(zzop);
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    zzdi.zzac("Error closing stream for reading resource from disk");
                }
                zzdi.zzab("The Disk resource was successfully read.");
            } catch (IOException e2) {
                this.zzajf.zzs(zzcz.zzahu);
                zzdi.zzac("Failed to read the resource from disk");
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    zzdi.zzac("Error closing stream for reading resource from disk");
                }
            } catch (IllegalArgumentException e4) {
                this.zzajf.zzs(zzcz.zzahu);
                zzdi.zzac("Failed to read the resource from disk. The resource is inconsistent");
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    zzdi.zzac("Error closing stream for reading resource from disk");
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    zzdi.zzac("Error closing stream for reading resource from disk");
                }
                throw th;
            }
        } catch (FileNotFoundException e7) {
            zzdi.zzax("Failed to find the resource in the disk");
            this.zzajf.zzs(zzcz.zzaht);
        }
    }

    public final void zza(zzop zzop) {
        this.zzajm.execute(new zzez(this, zzop));
    }

    public final zzov zzt(int i) {
        try {
            InputStream openRawResource = this.zzrm.getResources().openRawResource(i);
            String resourceName = this.zzrm.getResources().getResourceName(i);
            zzdi.zzab(new StringBuilder(String.valueOf(resourceName).length() + 66).append("Attempting to load a container from the resource ID ").append(i).append(" (").append(resourceName).append(")").toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzor.zza(openRawResource, byteArrayOutputStream);
                zzov zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zzb(byteArrayOutputStream.toByteArray());
                }
                zzdi.zzab("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException e) {
                String resourceName2 = this.zzrm.getResources().getResourceName(i);
                zzdi.zzac(new StringBuilder(String.valueOf(resourceName2).length() + 67).append("Error reading the default container with resource ID ").append(i).append(" (").append(resourceName2).append(")").toString());
                return null;
            }
        } catch (Resources.NotFoundException e2) {
            zzdi.zzac(new StringBuilder(98).append("Failed to load the container. No default container resource found with the resource ID ").append(i).toString());
            return null;
        }
    }

    private static zzov zza(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzda.zzbf(byteArrayOutputStream.toString(WebRequest.CHARSET_UTF_8));
        } catch (UnsupportedEncodingException e) {
            zzdi.zzax("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException e2) {
            zzdi.zzac("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private static zzov zzb(byte[] bArr) {
        try {
            zzov zza = zzor.zza((zzi) zzuw.zza(new zzi(), bArr));
            if (zza == null) {
                return zza;
            }
            zzdi.zzab("The container was successfully loaded from the resource (using binary file)");
            return zza;
        } catch (zzuv e) {
            zzdi.zzav("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzoz e2) {
            zzdi.zzac("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    public final synchronized void release() {
        this.zzajm.shutdown();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final boolean zzb(zzop zzop) {
        File zzje = zzje();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zzje);
            try {
                byte[] bArr = new byte[zzop.zzpe()];
                zzuw.zza(zzop, bArr, 0, bArr.length);
                fileOutputStream.write(bArr);
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zzdi.zzac("error closing stream for writing resource to disk");
                }
                return true;
            } catch (IOException e2) {
                zzdi.zzac("Error writing resource to disk. Removing resource from disk.");
                zzje.delete();
                try {
                    fileOutputStream.close();
                    return false;
                } catch (IOException e3) {
                    zzdi.zzac("error closing stream for writing resource to disk");
                    return false;
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    zzdi.zzac("error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            zzdi.zzav("Error opening resource file for writing");
            return false;
        }
    }

    @VisibleForTesting
    private final File zzje() {
        String valueOf = String.valueOf("resource_");
        String valueOf2 = String.valueOf(this.zzaec);
        return new File(this.zzrm.getDir("google_tagmanager", 0), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }
}
