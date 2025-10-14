package com.google.android.gms.internal.ads;

import android.os.Environment;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzhu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzhs {
    private final zzhx zzakc;
    @GuardedBy("this")
    private final zzii zzakd;
    private final boolean zzake;

    private zzhs() {
        this.zzake = false;
        this.zzakc = new zzhx();
        this.zzakd = new zzii();
        zzhn();
    }

    public zzhs(zzhx zzhx) {
        this.zzakc = zzhx;
        this.zzake = ((Boolean) zzkb.zzik().zzd(zznk.zzbeo)).booleanValue();
        this.zzakd = new zzii();
        zzhn();
    }

    private final synchronized void zzb(zzhu.zza.zzb zzb) {
        this.zzakd.zzanl = zzho();
        this.zzakc.zzd(zzbfi.zzb(this.zzakd)).zzs(zzb.zzhq()).zzbd();
        String valueOf = String.valueOf(Integer.toString(zzb.zzhq(), 10));
        zzakb.v(valueOf.length() != 0 ? "Logging Event with event code : ".concat(valueOf) : new String("Logging Event with event code : "));
    }

    private final synchronized void zzc(zzhu.zza.zzb zzb) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(externalStorageDirectory, "clearcut_events.txt"), true);
                try {
                    fileOutputStream.write(zzd(zzb).getBytes());
                    fileOutputStream.write(10);
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        zzakb.v("Could not close Clearcut output stream.");
                    }
                } catch (IOException e2) {
                    zzakb.v("Could not write Clearcut to file.");
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        zzakb.v("Could not close Clearcut output stream.");
                    }
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        zzakb.v("Could not close Clearcut output stream.");
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                zzakb.v("Could not find file for Clearcut");
            }
        }
        return;
    }

    private final synchronized String zzd(zzhu.zza.zzb zzb) {
        return String.format("id=%s,timestamp=%s,event=%s", new Object[]{this.zzakd.zzanh, Long.valueOf(zzbv.zzer().elapsedRealtime()), Integer.valueOf(zzb.zzhq())});
    }

    public static zzhs zzhm() {
        return new zzhs();
    }

    private final synchronized void zzhn() {
        this.zzakd.zzanp = new zzib();
        this.zzakd.zzanp.zzalw = new zzie();
        this.zzakd.zzanm = new zzig();
    }

    private static long[] zzho() {
        List<String> zzjc = zznk.zzjc();
        ArrayList arrayList = new ArrayList();
        for (String split : zzjc) {
            for (String valueOf : split.split(",")) {
                try {
                    arrayList.add(Long.valueOf(valueOf));
                } catch (NumberFormatException e) {
                    zzakb.v("Experiment ID is not a number");
                }
            }
        }
        long[] jArr = new long[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            jArr[i2] = ((Long) obj).longValue();
            i2++;
        }
        return jArr;
    }

    public final synchronized void zza(zzht zzht) {
        if (this.zzake) {
            try {
                zzht.zza(this.zzakd);
            } catch (NullPointerException e) {
                zzbv.zzeo().zza(e, "AdMobClearcutLogger.modify");
            }
        }
        return;
    }

    public final synchronized void zza(zzhu.zza.zzb zzb) {
        if (this.zzake) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbep)).booleanValue()) {
                zzc(zzb);
            } else {
                zzb(zzb);
            }
        }
    }
}
