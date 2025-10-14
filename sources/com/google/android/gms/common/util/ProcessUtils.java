package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

@KeepForSdk
public class ProcessUtils {
    private static String zzhd = null;
    private static int zzhe = 0;

    private ProcessUtils() {
    }

    @KeepForSdk
    @Nullable
    public static String getMyProcessName() {
        if (zzhd == null) {
            if (zzhe == 0) {
                zzhe = Process.myPid();
            }
            zzhd = zzd(zzhe);
        }
        return zzhd;
    }

    @Nullable
    private static String zzd(int i) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2;
        String str = null;
        if (i > 0) {
            try {
                bufferedReader2 = zzj(new StringBuilder(25).append("/proc/").append(i).append("/cmdline").toString());
                try {
                    str = bufferedReader2.readLine().trim();
                    IOUtils.closeQuietly((Closeable) bufferedReader2);
                } catch (IOException e) {
                    IOUtils.closeQuietly((Closeable) bufferedReader2);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    IOUtils.closeQuietly((Closeable) bufferedReader);
                    throw th;
                }
            } catch (IOException e2) {
                bufferedReader2 = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                IOUtils.closeQuietly((Closeable) bufferedReader);
                throw th;
            }
        }
        return str;
    }

    private static BufferedReader zzj(String str) throws IOException {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
