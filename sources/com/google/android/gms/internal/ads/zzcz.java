package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzcz {
    private static final String TAG = zzcz.class.getSimpleName();
    private volatile boolean zzqt = false;
    protected Context zzrt;
    private ExecutorService zzru;
    private DexClassLoader zzrv;
    private zzck zzrw;
    private byte[] zzrx;
    private volatile AdvertisingIdClient zzry = null;
    private Future zzrz = null;
    private boolean zzsa;
    /* access modifiers changed from: private */
    public volatile zzba zzsb = null;
    private Future zzsc = null;
    private zzcc zzsd;
    private boolean zzse = false;
    private boolean zzsf = false;
    private Map<Pair<String, String>, zzeg> zzsg;
    private boolean zzsh = false;
    /* access modifiers changed from: private */
    public boolean zzsi = true;
    private boolean zzsj = false;

    final class zza extends BroadcastReceiver {
        private zza() {
        }

        /* synthetic */ zza(zzcz zzcz, zzda zzda) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                boolean unused = zzcz.this.zzsi = true;
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                boolean unused2 = zzcz.this.zzsi = false;
            }
        }
    }

    private zzcz(Context context) {
        boolean z = true;
        Context applicationContext = context.getApplicationContext();
        this.zzsa = applicationContext == null ? false : z;
        this.zzrt = this.zzsa ? applicationContext : context;
        this.zzsg = new HashMap();
    }

    public static zzcz zza(Context context, String str, String str2, boolean z) {
        File file;
        File file2;
        boolean z2 = true;
        zzcz zzcz = new zzcz(context);
        try {
            zzcz.zzru = Executors.newCachedThreadPool(new zzda());
            zzcz.zzqt = z;
            if (z) {
                zzcz.zzrz = zzcz.zzru.submit(new zzdb(zzcz));
            }
            zzcz.zzru.execute(new zzdd(zzcz));
            try {
                GoogleApiAvailabilityLight instance = GoogleApiAvailabilityLight.getInstance();
                zzcz.zzse = instance.getApkVersion(zzcz.zzrt) > 0;
                if (instance.isGooglePlayServicesAvailable(zzcz.zzrt) != 0) {
                    z2 = false;
                }
                zzcz.zzsf = z2;
            } catch (Throwable th) {
            }
            zzcz.zza(0, true);
            if (zzdg.isMainThread()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbaz)).booleanValue()) {
                    throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
                }
            }
            zzcz.zzrw = new zzck((SecureRandom) null);
            zzcz.zzrx = zzcz.zzrw.zzl(str);
            File cacheDir = zzcz.zzrt.getCacheDir();
            if (cacheDir == null && (cacheDir = zzcz.zzrt.getDir("dex", 0)) == null) {
                throw new zzcw();
            }
            file = cacheDir;
            file2 = new File(String.format("%s/%s.jar", new Object[]{file, "1521499837408"}));
            if (!file2.exists()) {
                byte[] zza2 = zzcz.zzrw.zza(zzcz.zzrx, str2);
                file2.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(zza2, 0, zza2.length);
                fileOutputStream.close();
            }
            zzcz.zzb(file, "1521499837408");
            zzcz.zzrv = new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), (String) null, zzcz.zzrt.getClassLoader());
            zzb(file2);
            zzcz.zza(file, "1521499837408");
            zzm(String.format("%s/%s.dex", new Object[]{file, "1521499837408"}));
            if (!zzcz.zzsj) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                zzcz.zzrt.registerReceiver(new zza(zzcz, (zzda) null), intentFilter);
                zzcz.zzsj = true;
            }
            zzcz.zzsd = new zzcc(zzcz);
            zzcz.zzsh = true;
            return zzcz;
        } catch (zzcl e) {
            throw new zzcw(e);
        } catch (FileNotFoundException e2) {
            throw new zzcw(e2);
        } catch (IOException e3) {
            throw new zzcw(e3);
        } catch (zzcl e4) {
            throw new zzcw(e4);
        } catch (NullPointerException e5) {
            throw new zzcw(e5);
        } catch (zzcw e6) {
        } catch (Throwable th2) {
            zzb(file2);
            zzcz.zza(file, "1521499837408");
            zzm(String.format("%s/%s.dex", new Object[]{file, "1521499837408"}));
            throw th2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x009c A[SYNTHETIC, Splitter:B:27:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a1 A[SYNTHETIC, Splitter:B:30:0x00a1] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00af A[SYNTHETIC, Splitter:B:36:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b4 A[SYNTHETIC, Splitter:B:39:0x00b4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.io.File r9, java.lang.String r10) {
        /*
            r8 = this;
            r2 = 0
            r7 = 2
            r6 = 1
            r4 = 0
            java.io.File r3 = new java.io.File
            java.lang.String r0 = "%s/%s.tmp"
            java.lang.Object[] r1 = new java.lang.Object[r7]
            r1[r4] = r9
            r1[r6] = r10
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r3.<init>(r0)
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x001c
        L_0x001b:
            return
        L_0x001c:
            java.io.File r5 = new java.io.File
            java.lang.String r0 = "%s/%s.dex"
            java.lang.Object[] r1 = new java.lang.Object[r7]
            r1[r4] = r9
            r1[r6] = r10
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r5.<init>(r0)
            boolean r0 = r5.exists()
            if (r0 == 0) goto L_0x001b
            long r0 = r5.length()
            r6 = 0
            int r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x001b
            int r0 = (int) r0
            byte[] r0 = new byte[r0]
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00d6, NoSuchAlgorithmException -> 0x0097, zzcl -> 0x00df, all -> 0x00a9 }
            r1.<init>(r5)     // Catch:{ IOException -> 0x00d6, NoSuchAlgorithmException -> 0x0097, zzcl -> 0x00df, all -> 0x00a9 }
            int r4 = r1.read(r0)     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            if (r4 > 0) goto L_0x0052
            r1.close()     // Catch:{ IOException -> 0x00bb }
        L_0x004e:
            zzb(r5)
            goto L_0x001b
        L_0x0052:
            com.google.android.gms.internal.ads.zzbe r4 = new com.google.android.gms.internal.ads.zzbe     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            r4.<init>()     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            byte[] r6 = r6.getBytes()     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            r4.zzgs = r6     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            byte[] r6 = r10.getBytes()     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            r4.zzgr = r6     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzck r6 = r8.zzrw     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            byte[] r7 = r8.zzrx     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            java.lang.String r0 = r6.zzb(r7, r0)     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            r4.data = r0     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            byte[] r0 = com.google.android.gms.internal.ads.zzbk.zzb(r0)     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            r4.zzgq = r0     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            r3.createNewFile()     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x00da, NoSuchAlgorithmException -> 0x00d1, zzcl -> 0x00e3, all -> 0x00c9 }
            byte[] r2 = com.google.android.gms.internal.ads.zzbfi.zzb(r4)     // Catch:{ IOException -> 0x00dd, NoSuchAlgorithmException -> 0x00d4, zzcl -> 0x00e6, all -> 0x00cd }
            r3 = 0
            int r4 = r2.length     // Catch:{ IOException -> 0x00dd, NoSuchAlgorithmException -> 0x00d4, zzcl -> 0x00e6, all -> 0x00cd }
            r0.write(r2, r3, r4)     // Catch:{ IOException -> 0x00dd, NoSuchAlgorithmException -> 0x00d4, zzcl -> 0x00e6, all -> 0x00cd }
            r0.close()     // Catch:{ IOException -> 0x00dd, NoSuchAlgorithmException -> 0x00d4, zzcl -> 0x00e6, all -> 0x00cd }
            r1.close()     // Catch:{ IOException -> 0x00bd }
        L_0x0090:
            r0.close()     // Catch:{ IOException -> 0x00bf }
        L_0x0093:
            zzb(r5)
            goto L_0x001b
        L_0x0097:
            r0 = move-exception
            r0 = r2
            r1 = r2
        L_0x009a:
            if (r1 == 0) goto L_0x009f
            r1.close()     // Catch:{ IOException -> 0x00c1 }
        L_0x009f:
            if (r0 == 0) goto L_0x00a4
            r0.close()     // Catch:{ IOException -> 0x00c3 }
        L_0x00a4:
            zzb(r5)
            goto L_0x001b
        L_0x00a9:
            r0 = move-exception
            r3 = r0
            r4 = r2
            r1 = r2
        L_0x00ad:
            if (r1 == 0) goto L_0x00b2
            r1.close()     // Catch:{ IOException -> 0x00c5 }
        L_0x00b2:
            if (r4 == 0) goto L_0x00b7
            r4.close()     // Catch:{ IOException -> 0x00c7 }
        L_0x00b7:
            zzb(r5)
            throw r3
        L_0x00bb:
            r0 = move-exception
            goto L_0x004e
        L_0x00bd:
            r1 = move-exception
            goto L_0x0090
        L_0x00bf:
            r0 = move-exception
            goto L_0x0093
        L_0x00c1:
            r1 = move-exception
            goto L_0x009f
        L_0x00c3:
            r0 = move-exception
            goto L_0x00a4
        L_0x00c5:
            r0 = move-exception
            goto L_0x00b2
        L_0x00c7:
            r0 = move-exception
            goto L_0x00b7
        L_0x00c9:
            r0 = move-exception
            r3 = r0
            r4 = r2
            goto L_0x00ad
        L_0x00cd:
            r2 = move-exception
            r3 = r2
            r4 = r0
            goto L_0x00ad
        L_0x00d1:
            r0 = move-exception
            r0 = r2
            goto L_0x009a
        L_0x00d4:
            r2 = move-exception
            goto L_0x009a
        L_0x00d6:
            r0 = move-exception
            r0 = r2
            r1 = r2
            goto L_0x009a
        L_0x00da:
            r0 = move-exception
            r0 = r2
            goto L_0x009a
        L_0x00dd:
            r2 = move-exception
            goto L_0x009a
        L_0x00df:
            r0 = move-exception
            r0 = r2
            r1 = r2
            goto L_0x009a
        L_0x00e3:
            r0 = move-exception
            r0 = r2
            goto L_0x009a
        L_0x00e6:
            r2 = move-exception
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcz.zza(java.io.File, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public static boolean zza(int i, zzba zzba) {
        if (i < 4) {
            if (zzba == null) {
                return true;
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbc)).booleanValue() && (zzba.zzcx == null || zzba.zzcx.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
                return true;
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbd)).booleanValue() && (zzba.zzfn == null || zzba.zzfn.zzgl == null || zzba.zzfn.zzgl.longValue() == -2)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final void zzal() {
        try {
            if (this.zzry == null && this.zzsa) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzrt);
                advertisingIdClient.start();
                this.zzry = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e) {
            this.zzry = null;
        }
    }

    @VisibleForTesting
    private final zzba zzam() {
        try {
            return zzatq.zzl(this.zzrt, this.zzrt.getPackageName(), Integer.toString(this.zzrt.getPackageManager().getPackageInfo(this.zzrt.getPackageName(), 0).versionCode));
        } catch (Throwable th) {
            return null;
        }
    }

    private static void zzb(File file) {
        if (!file.exists()) {
            Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
            return;
        }
        file.delete();
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c9 A[SYNTHETIC, Splitter:B:42:0x00c9] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ce A[SYNTHETIC, Splitter:B:45:0x00ce] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00da A[SYNTHETIC, Splitter:B:51:0x00da] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00df A[SYNTHETIC, Splitter:B:54:0x00df] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzb(java.io.File r13, java.lang.String r14) {
        /*
            r12 = this;
            r3 = 0
            r7 = 2
            r1 = 1
            r2 = 0
            java.io.File r5 = new java.io.File
            java.lang.String r0 = "%s/%s.tmp"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r4[r2] = r13
            r4[r1] = r14
            java.lang.String r0 = java.lang.String.format(r0, r4)
            r5.<init>(r0)
            boolean r0 = r5.exists()
            if (r0 != 0) goto L_0x001d
            r0 = r2
        L_0x001c:
            return r0
        L_0x001d:
            java.io.File r6 = new java.io.File
            java.lang.String r0 = "%s/%s.dex"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r4[r2] = r13
            r4[r1] = r14
            java.lang.String r0 = java.lang.String.format(r0, r4)
            r6.<init>(r0)
            boolean r0 = r6.exists()
            if (r0 == 0) goto L_0x0036
            r0 = r2
            goto L_0x001c
        L_0x0036:
            long r8 = r5.length()     // Catch:{ IOException -> 0x0102, NoSuchAlgorithmException -> 0x00c4, zzcl -> 0x010d, all -> 0x00d4 }
            r10 = 0
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 > 0) goto L_0x0045
            zzb(r5)     // Catch:{ IOException -> 0x0102, NoSuchAlgorithmException -> 0x00c4, zzcl -> 0x010d, all -> 0x00d4 }
            r0 = r2
            goto L_0x001c
        L_0x0045:
            int r0 = (int) r8     // Catch:{ IOException -> 0x0102, NoSuchAlgorithmException -> 0x00c4, zzcl -> 0x010d, all -> 0x00d4 }
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0102, NoSuchAlgorithmException -> 0x00c4, zzcl -> 0x010d, all -> 0x00d4 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0102, NoSuchAlgorithmException -> 0x00c4, zzcl -> 0x010d, all -> 0x00d4 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0102, NoSuchAlgorithmException -> 0x00c4, zzcl -> 0x010d, all -> 0x00d4 }
            int r7 = r4.read(r0)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            if (r7 > 0) goto L_0x0062
            java.lang.String r0 = TAG     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            java.lang.String r1 = "Cannot read the cache data."
            android.util.Log.d(r0, r1)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            zzb(r5)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            r4.close()     // Catch:{ IOException -> 0x00e3 }
        L_0x0060:
            r0 = r2
            goto L_0x001c
        L_0x0062:
            com.google.android.gms.internal.ads.zzbe r7 = new com.google.android.gms.internal.ads.zzbe     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            r7.<init>()     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            com.google.android.gms.internal.ads.zzbfi r0 = com.google.android.gms.internal.ads.zzbfi.zza(r7, r0)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            com.google.android.gms.internal.ads.zzbe r0 = (com.google.android.gms.internal.ads.zzbe) r0     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            java.lang.String r7 = new java.lang.String     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            byte[] r8 = r0.zzgr     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            boolean r7 = r14.equals(r7)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            if (r7 == 0) goto L_0x0096
            byte[] r7 = r0.zzgq     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            byte[] r8 = r0.data     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            byte[] r8 = com.google.android.gms.internal.ads.zzbk.zzb(r8)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            boolean r7 = java.util.Arrays.equals(r7, r8)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            if (r7 == 0) goto L_0x0096
            byte[] r7 = r0.zzgs     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            java.lang.String r8 = android.os.Build.VERSION.SDK     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            byte[] r8 = r8.getBytes()     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            boolean r7 = java.util.Arrays.equals(r7, r8)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            if (r7 != 0) goto L_0x009f
        L_0x0096:
            zzb(r5)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            r4.close()     // Catch:{ IOException -> 0x00e6 }
        L_0x009c:
            r0 = r2
            goto L_0x001c
        L_0x009f:
            com.google.android.gms.internal.ads.zzck r5 = r12.zzrw     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            byte[] r7 = r12.zzrx     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            java.lang.String r8 = new java.lang.String     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            byte[] r0 = r0.data     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            r8.<init>(r0)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            byte[] r5 = r5.zza(r7, r8)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            r6.createNewFile()     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            r0.<init>(r6)     // Catch:{ IOException -> 0x0106, NoSuchAlgorithmException -> 0x00fb, zzcl -> 0x0111, all -> 0x00f4 }
            r3 = 0
            int r6 = r5.length     // Catch:{ IOException -> 0x010a, NoSuchAlgorithmException -> 0x00ff, zzcl -> 0x0115, all -> 0x00f8 }
            r0.write(r5, r3, r6)     // Catch:{ IOException -> 0x010a, NoSuchAlgorithmException -> 0x00ff, zzcl -> 0x0115, all -> 0x00f8 }
            r4.close()     // Catch:{ IOException -> 0x00e8 }
        L_0x00be:
            r0.close()     // Catch:{ IOException -> 0x00ea }
        L_0x00c1:
            r0 = r1
            goto L_0x001c
        L_0x00c4:
            r0 = move-exception
            r0 = r3
            r1 = r3
        L_0x00c7:
            if (r1 == 0) goto L_0x00cc
            r1.close()     // Catch:{ IOException -> 0x00ec }
        L_0x00cc:
            if (r0 == 0) goto L_0x00d1
            r0.close()     // Catch:{ IOException -> 0x00ee }
        L_0x00d1:
            r0 = r2
            goto L_0x001c
        L_0x00d4:
            r0 = move-exception
            r1 = r0
            r2 = r3
            r4 = r3
        L_0x00d8:
            if (r4 == 0) goto L_0x00dd
            r4.close()     // Catch:{ IOException -> 0x00f0 }
        L_0x00dd:
            if (r2 == 0) goto L_0x00e2
            r2.close()     // Catch:{ IOException -> 0x00f2 }
        L_0x00e2:
            throw r1
        L_0x00e3:
            r0 = move-exception
            goto L_0x0060
        L_0x00e6:
            r0 = move-exception
            goto L_0x009c
        L_0x00e8:
            r2 = move-exception
            goto L_0x00be
        L_0x00ea:
            r0 = move-exception
            goto L_0x00c1
        L_0x00ec:
            r1 = move-exception
            goto L_0x00cc
        L_0x00ee:
            r0 = move-exception
            goto L_0x00d1
        L_0x00f0:
            r0 = move-exception
            goto L_0x00dd
        L_0x00f2:
            r0 = move-exception
            goto L_0x00e2
        L_0x00f4:
            r0 = move-exception
            r1 = r0
            r2 = r3
            goto L_0x00d8
        L_0x00f8:
            r1 = move-exception
            r2 = r0
            goto L_0x00d8
        L_0x00fb:
            r0 = move-exception
            r0 = r3
            r1 = r4
            goto L_0x00c7
        L_0x00ff:
            r1 = move-exception
            r1 = r4
            goto L_0x00c7
        L_0x0102:
            r0 = move-exception
            r0 = r3
            r1 = r3
            goto L_0x00c7
        L_0x0106:
            r0 = move-exception
            r0 = r3
            r1 = r4
            goto L_0x00c7
        L_0x010a:
            r1 = move-exception
            r1 = r4
            goto L_0x00c7
        L_0x010d:
            r0 = move-exception
            r0 = r3
            r1 = r3
            goto L_0x00c7
        L_0x0111:
            r0 = move-exception
            r0 = r3
            r1 = r4
            goto L_0x00c7
        L_0x0115:
            r1 = move-exception
            r1 = r4
            goto L_0x00c7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcz.zzb(java.io.File, java.lang.String):boolean");
    }

    private static void zzm(String str) {
        zzb(new File(str));
    }

    public final Context getContext() {
        return this.zzrt;
    }

    public final boolean isInitialized() {
        return this.zzsh;
    }

    public final Method zza(String str, String str2) {
        zzeg zzeg = this.zzsg.get(new Pair(str, str2));
        if (zzeg == null) {
            return null;
        }
        return zzeg.zzaw();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zza(int i, boolean z) {
        if (this.zzsf) {
            Future<?> submit = this.zzru.submit(new zzdc(this, i, z));
            if (i == 0) {
                this.zzsc = submit;
            }
        }
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zzsg.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzsg.put(new Pair(str, str2), new zzeg(this, str, str2, clsArr));
        return true;
    }

    public final ExecutorService zzab() {
        return this.zzru;
    }

    public final DexClassLoader zzac() {
        return this.zzrv;
    }

    public final zzck zzad() {
        return this.zzrw;
    }

    public final byte[] zzae() {
        return this.zzrx;
    }

    public final boolean zzaf() {
        return this.zzse;
    }

    public final zzcc zzag() {
        return this.zzsd;
    }

    public final boolean zzah() {
        return this.zzsf;
    }

    public final boolean zzai() {
        return this.zzsi;
    }

    public final zzba zzaj() {
        return this.zzsb;
    }

    public final Future zzak() {
        return this.zzsc;
    }

    public final AdvertisingIdClient zzan() {
        if (!this.zzqt) {
            return null;
        }
        if (this.zzry != null) {
            return this.zzry;
        }
        if (this.zzrz != null) {
            try {
                this.zzrz.get(2000, TimeUnit.MILLISECONDS);
                this.zzrz = null;
            } catch (InterruptedException | ExecutionException e) {
            } catch (TimeoutException e2) {
                this.zzrz.cancel(true);
            }
        }
        return this.zzry;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final zzba zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep((long) (i * 1000));
            } catch (InterruptedException e) {
            }
        }
        return zzam();
    }

    public final int zzx() {
        if (this.zzsd != null) {
            return zzcc.zzx();
        }
        return Integer.MIN_VALUE;
    }
}
