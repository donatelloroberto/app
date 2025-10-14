package com.google.android.gms.internal.gtm;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class zzbh extends zzan {
    private volatile String zzut;
    private Future<String> zzyh;

    protected zzbh(zzap zzap) {
        super(zzap);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() {
    }

    public final String zzeh() {
        String str;
        zzdb();
        synchronized (this) {
            if (this.zzut == null) {
                this.zzyh = zzcq().zza(new zzbi(this));
            }
            if (this.zzyh != null) {
                try {
                    this.zzut = this.zzyh.get();
                } catch (InterruptedException e) {
                    zzd("ClientId loading or generation was interrupted", e);
                    this.zzut = "0";
                } catch (ExecutionException e2) {
                    zze("Failed to load or generate client id", e2);
                    this.zzut = "0";
                }
                if (this.zzut == null) {
                    this.zzut = "0";
                }
                zza("Loaded clientId", this.zzut);
                this.zzyh = null;
            }
            str = this.zzut;
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public final String zzei() {
        synchronized (this) {
            this.zzut = null;
            this.zzyh = zzcq().zza(new zzbj(this));
        }
        return zzeh();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final String zzej() {
        String zzd = zzd(zzcq().getContext());
        if (zzd == null) {
            return zzek();
        }
        return zzd;
    }

    /* access modifiers changed from: private */
    @VisibleForTesting
    public final String zzek() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
        try {
            if (!zzb(zzcq().getContext(), lowerCase)) {
                return "0";
            }
            return lowerCase;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return "0";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0075 A[SYNTHETIC, Splitter:B:34:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008e A[SYNTHETIC, Splitter:B:44:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x009d A[SYNTHETIC, Splitter:B:51:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzd(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r1 = "ClientId should be loaded from worker thread"
            com.google.android.gms.common.internal.Preconditions.checkNotMainThread(r1)
            java.lang.String r1 = "gaClientId"
            java.io.FileInputStream r2 = r7.openFileInput(r1)     // Catch:{ FileNotFoundException -> 0x0071, IOException -> 0x0080, all -> 0x0099 }
            r1 = 36
            byte[] r3 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            r1 = 0
            r4 = 36
            int r4 = r2.read(r3, r1, r4)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            int r1 = r2.available()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            if (r1 <= 0) goto L_0x0037
            java.lang.String r1 = "clientId file seems corrupted, deleting it."
            r6.zzt(r1)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ IOException -> 0x0030 }
        L_0x002f:
            return r0
        L_0x0030:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x002f
        L_0x0037:
            r1 = 14
            if (r4 >= r1) goto L_0x0055
            java.lang.String r1 = "clientId file is empty, deleting it."
            r6.zzt(r1)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x002f
        L_0x004e:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x002f
        L_0x0055:
            r2.close()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            java.lang.String r1 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            r5 = 0
            r1.<init>(r3, r5, r4)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            java.lang.String r3 = "Read client id from disk"
            r6.zza(r3, r1)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
            if (r2 == 0) goto L_0x0068
            r2.close()     // Catch:{ IOException -> 0x006a }
        L_0x0068:
            r0 = r1
            goto L_0x002f
        L_0x006a:
            r0 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r0)
            goto L_0x0068
        L_0x0071:
            r1 = move-exception
            r1 = r0
        L_0x0073:
            if (r1 == 0) goto L_0x002f
            r1.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x002f
        L_0x0079:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x002f
        L_0x0080:
            r1 = move-exception
            r2 = r0
        L_0x0082:
            java.lang.String r3 = "Error reading client id file, deleting it"
            r6.zze(r3, r1)     // Catch:{ all -> 0x00a8 }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ all -> 0x00a8 }
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x002f
        L_0x0092:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x002f
        L_0x0099:
            r1 = move-exception
            r2 = r0
        L_0x009b:
            if (r2 == 0) goto L_0x00a0
            r2.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x00a0:
            throw r1
        L_0x00a1:
            r0 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r0)
            goto L_0x00a0
        L_0x00a8:
            r0 = move-exception
            r1 = r0
            goto L_0x009b
        L_0x00ab:
            r1 = move-exception
            goto L_0x0082
        L_0x00ad:
            r1 = move-exception
            r1 = r2
            goto L_0x0073
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzbh.zzd(android.content.Context):java.lang.String");
    }

    private final boolean zzb(Context context, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotMainThread("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStream = null;
        try {
            zza("Storing clientId", str);
            fileOutputStream = context.openFileOutput("gaClientId", 0);
            fileOutputStream.write(str.getBytes());
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zze("Failed to close clientId writing stream", e);
                }
            }
            return true;
        } catch (FileNotFoundException e2) {
            zze("Error creating clientId file", e2);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (IOException e3) {
                zze("Failed to close clientId writing stream", e3);
                return false;
            }
        } catch (IOException e4) {
            zze("Error writing to clientId file", e4);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (IOException e5) {
                zze("Failed to close clientId writing stream", e5);
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    zze("Failed to close clientId writing stream", e6);
                }
            }
            throw th;
        }
    }
}
