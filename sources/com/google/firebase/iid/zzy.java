package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.firebase_messaging.zzh;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

final class zzy {
    zzy() {
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzz zzb(Context context, String str) throws zzaa {
        zzz zzd = zzd(context, str);
        return zzd != null ? zzd : zzc(context, str);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzz zzc(Context context, String str) {
        zzz zzz = new zzz(zza.zzc(), System.currentTimeMillis());
        zzz zza = zza(context, str, zzz, true);
        if (zza == null || zza.equals(zzz)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Generated new key");
            }
            zza(context, str, zzz);
            return zzz;
        } else if (!Log.isLoggable("FirebaseInstanceId", 3)) {
            return zza;
        } else {
            Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
            return zza;
        }
    }

    static void zza(Context context) {
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    @Nullable
    private final zzz zzd(Context context, String str) throws zzaa {
        zzaa zzaa;
        try {
            zzz zze = zze(context, str);
            if (zze != null) {
                zza(context, str, zze);
                return zze;
            }
            zzaa = null;
            try {
                zzz zza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (zza != null) {
                    zza(context, str, zza, false);
                    return zza;
                }
                e = zzaa;
                if (e == null) {
                    return null;
                }
                throw e;
            } catch (zzaa e) {
                e = e;
            }
        } catch (zzaa e2) {
            zzaa = e2;
        }
    }

    private static KeyPair zzc(String str, String str2) throws zzaa {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 19).append("Invalid key stored ").append(valueOf).toString());
                throw new zzaa((Exception) e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzaa((Exception) e2);
        }
    }

    @Nullable
    private final zzz zze(Context context, String str) throws zzaa {
        File zzf = zzf(context, str);
        if (!zzf.exists()) {
            return null;
        }
        try {
            return zza(zzf);
        } catch (zzaa | IOException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(e);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 40).append("Failed to read key from file, retrying: ").append(valueOf).toString());
            }
            try {
                return zza(zzf);
            } catch (IOException e2) {
                String valueOf2 = String.valueOf(e2);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf2).length() + 45).append("IID file exists, but failed to read from it: ").append(valueOf2).toString());
                throw new zzaa((Exception) e2);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00dd, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00de, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e7, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e8, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ed, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ee, code lost:
        r2 = r1;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f1, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f2, code lost:
        r2 = r1;
        r3 = null;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.firebase.iid.zzz zza(android.content.Context r11, java.lang.String r12, com.google.firebase.iid.zzz r13, boolean r14) {
        /*
            r10 = this;
            r2 = 3
            r8 = 0
            r0 = 0
            java.lang.String r1 = "FirebaseInstanceId"
            boolean r1 = android.util.Log.isLoggable(r1, r2)
            if (r1 == 0) goto L_0x0013
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "Writing key to properties file"
            android.util.Log.d(r1, r2)
        L_0x0013:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            java.lang.String r1 = "pub"
            java.lang.String r3 = r13.zzv()
            r2.setProperty(r1, r3)
            java.lang.String r1 = "pri"
            java.lang.String r3 = r13.zzw()
            r2.setProperty(r1, r3)
            java.lang.String r1 = "cre"
            long r4 = r13.zzbw
            java.lang.String r3 = java.lang.String.valueOf(r4)
            r2.setProperty(r1, r3)
            java.io.File r1 = zzf(r11, r12)
            r1.createNewFile()     // Catch:{ IOException -> 0x00b2 }
            java.io.RandomAccessFile r4 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x00b2 }
            java.lang.String r3 = "rw"
            r4.<init>(r1, r3)     // Catch:{ IOException -> 0x00b2 }
            java.nio.channels.FileChannel r5 = r4.getChannel()     // Catch:{ Throwable -> 0x00e5, all -> 0x00ed }
            r5.lock()     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            if (r14 == 0) goto L_0x009a
            long r6 = r5.size()     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 <= 0) goto L_0x009a
            r6 = 0
            r5.position(r6)     // Catch:{ IOException -> 0x00f5, zzaa -> 0x006a }
            com.google.firebase.iid.zzz r13 = zza((java.nio.channels.FileChannel) r5)     // Catch:{ IOException -> 0x00f5, zzaa -> 0x006a }
            if (r5 == 0) goto L_0x0065
            r1 = 0
            zza((java.lang.Throwable) r1, (java.nio.channels.FileChannel) r5)     // Catch:{ Throwable -> 0x00e5, all -> 0x00ed }
        L_0x0065:
            r1 = 0
            zza((java.lang.Throwable) r1, (java.io.RandomAccessFile) r4)     // Catch:{ IOException -> 0x00b2 }
        L_0x0069:
            return r13
        L_0x006a:
            r1 = move-exception
        L_0x006b:
            java.lang.String r3 = "FirebaseInstanceId"
            r6 = 3
            boolean r3 = android.util.Log.isLoggable(r3, r6)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            if (r3 == 0) goto L_0x009a
            java.lang.String r3 = "FirebaseInstanceId"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            java.lang.String r6 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            int r6 = r6.length()     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            int r6 = r6 + 64
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            r7.<init>(r6)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            java.lang.String r6 = "Tried reading key pair before writing new one, but failed with: "
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            java.lang.StringBuilder r1 = r6.append(r1)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            android.util.Log.d(r3, r1)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
        L_0x009a:
            r6 = 0
            r5.position(r6)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            java.io.OutputStream r1 = java.nio.channels.Channels.newOutputStream(r5)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            r3 = 0
            r2.store(r1, r3)     // Catch:{ Throwable -> 0x00db, all -> 0x00f1 }
            if (r5 == 0) goto L_0x00ad
            r1 = 0
            zza((java.lang.Throwable) r1, (java.nio.channels.FileChannel) r5)     // Catch:{ Throwable -> 0x00e5, all -> 0x00ed }
        L_0x00ad:
            r1 = 0
            zza((java.lang.Throwable) r1, (java.io.RandomAccessFile) r4)     // Catch:{ IOException -> 0x00b2 }
            goto L_0x0069
        L_0x00b2:
            r1 = move-exception
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r3 = r3 + 21
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Failed to write key: "
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r2, r1)
            r13 = r0
            goto L_0x0069
        L_0x00db:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00dd }
        L_0x00dd:
            r2 = move-exception
            r3 = r1
        L_0x00df:
            if (r5 == 0) goto L_0x00e4
            zza((java.lang.Throwable) r3, (java.nio.channels.FileChannel) r5)     // Catch:{ Throwable -> 0x00e5, all -> 0x00ed }
        L_0x00e4:
            throw r2     // Catch:{ Throwable -> 0x00e5, all -> 0x00ed }
        L_0x00e5:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00e7 }
        L_0x00e7:
            r2 = move-exception
            r3 = r1
        L_0x00e9:
            zza((java.lang.Throwable) r3, (java.io.RandomAccessFile) r4)     // Catch:{ IOException -> 0x00b2 }
            throw r2     // Catch:{ IOException -> 0x00b2 }
        L_0x00ed:
            r1 = move-exception
            r2 = r1
            r3 = r0
            goto L_0x00e9
        L_0x00f1:
            r1 = move-exception
            r2 = r1
            r3 = r0
            goto L_0x00df
        L_0x00f5:
            r1 = move-exception
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzy.zza(android.content.Context, java.lang.String, com.google.firebase.iid.zzz, boolean):com.google.firebase.iid.zzz");
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static File zzf(Context context, String str) {
        String sb;
        if (TextUtils.isEmpty(str)) {
            sb = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes(WebRequest.CHARSET_UTF_8), 11);
                sb = new StringBuilder(String.valueOf(encodeToString).length() + 33).append("com.google.InstanceId_").append(encodeToString).append(".properties").toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), sb);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002f, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0030, code lost:
        r7 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0036, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0038, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0039, code lost:
        r2 = r0;
        r3 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.firebase.iid.zzz zza(java.io.File r10) throws com.google.firebase.iid.zzaa, java.io.IOException {
        /*
            r9 = this;
            r7 = 0
            java.io.FileInputStream r8 = new java.io.FileInputStream
            r8.<init>(r10)
            java.nio.channels.FileChannel r1 = r8.getChannel()     // Catch:{ Throwable -> 0x002d, all -> 0x0035 }
            r2 = 0
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r6 = 1
            r1.lock(r2, r4, r6)     // Catch:{ Throwable -> 0x0023, all -> 0x0038 }
            com.google.firebase.iid.zzz r0 = zza((java.nio.channels.FileChannel) r1)     // Catch:{ Throwable -> 0x0023, all -> 0x0038 }
            if (r1 == 0) goto L_0x001f
            r2 = 0
            zza((java.lang.Throwable) r2, (java.nio.channels.FileChannel) r1)     // Catch:{ Throwable -> 0x002d, all -> 0x0035 }
        L_0x001f:
            zza((java.lang.Throwable) r7, (java.io.FileInputStream) r8)
            return r0
        L_0x0023:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r2 = move-exception
            r3 = r0
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            zza((java.lang.Throwable) r3, (java.nio.channels.FileChannel) r1)     // Catch:{ Throwable -> 0x002d, all -> 0x0035 }
        L_0x002c:
            throw r2     // Catch:{ Throwable -> 0x002d, all -> 0x0035 }
        L_0x002d:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002f }
        L_0x002f:
            r1 = move-exception
            r7 = r0
        L_0x0031:
            zza((java.lang.Throwable) r7, (java.io.FileInputStream) r8)
            throw r1
        L_0x0035:
            r0 = move-exception
            r1 = r0
            goto L_0x0031
        L_0x0038:
            r0 = move-exception
            r2 = r0
            r3 = r7
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzy.zza(java.io.File):com.google.firebase.iid.zzz");
    }

    private static zzz zza(FileChannel fileChannel) throws zzaa, IOException {
        Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        String property = properties.getProperty("pub");
        String property2 = properties.getProperty("pri");
        if (property == null || property2 == null) {
            throw new zzaa("Invalid properties file");
        }
        try {
            return new zzz(zzc(property, property2), Long.parseLong(properties.getProperty("cre")));
        } catch (NumberFormatException e) {
            throw new zzaa((Exception) e);
        }
    }

    @Nullable
    private static zzz zza(SharedPreferences sharedPreferences, String str) throws zzaa {
        String string = sharedPreferences.getString(zzaw.zzd(str, "|P|"), (String) null);
        String string2 = sharedPreferences.getString(zzaw.zzd(str, "|K|"), (String) null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzz(zzc(string, string2), zzb(sharedPreferences, str));
    }

    private final void zza(Context context, String str, zzz zzz) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzz.equals(zza(sharedPreferences, str))) {
                return;
            }
        } catch (zzaa e) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(zzaw.zzd(str, "|P|"), zzz.zzv());
        edit.putString(zzaw.zzd(str, "|K|"), zzz.zzw());
        edit.putString(zzaw.zzd(str, "cre"), String.valueOf(zzz.zzbw));
        edit.commit();
    }

    private static long zzb(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzaw.zzd(str, "cre"), (String) null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    private static /* synthetic */ void zza(Throwable th, FileChannel fileChannel) {
        if (th != null) {
            try {
                fileChannel.close();
            } catch (Throwable th2) {
                zzh.zza(th, th2);
            }
        } else {
            fileChannel.close();
        }
    }

    private static /* synthetic */ void zza(Throwable th, RandomAccessFile randomAccessFile) {
        if (th != null) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                zzh.zza(th, th2);
            }
        } else {
            randomAccessFile.close();
        }
    }

    private static /* synthetic */ void zza(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                zzh.zza(th, th2);
            }
        } else {
            fileInputStream.close();
        }
    }
}
