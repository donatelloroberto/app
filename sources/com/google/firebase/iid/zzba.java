package com.google.firebase.iid;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

final class zzba {
    @GuardedBy("itself")
    private final zzaw zzao;
    @GuardedBy("this")
    private int zzdq = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdr = new ArrayMap();

    zzba(zzaw zzaw) {
        this.zzao = zzaw;
    }

    /* access modifiers changed from: package-private */
    public final synchronized Task<Void> zza(String str) {
        String zzak;
        TaskCompletionSource taskCompletionSource;
        int length;
        synchronized (this.zzao) {
            zzak = this.zzao.zzak();
            this.zzao.zzf(new StringBuilder(String.valueOf(zzak).length() + 1 + String.valueOf(str).length()).append(zzak).append(",").append(str).toString());
        }
        taskCompletionSource = new TaskCompletionSource();
        Map<Integer, TaskCompletionSource<Void>> map = this.zzdr;
        if (TextUtils.isEmpty(zzak)) {
            length = 0;
        } else {
            length = zzak.split(",").length - 1;
        }
        map.put(Integer.valueOf(length + this.zzdq), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzap() {
        return zzaq() != null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (zza(r4, r1) != false) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0 = r3.zzdr.remove(java.lang.Integer.valueOf(r3.zzdq));
        zzk(r1);
        r3.zzdq++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003b, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003c, code lost:
        if (r0 == null) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        r0.setResult(null);
     */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzc(com.google.firebase.iid.FirebaseInstanceId r4) {
        /*
            r3 = this;
        L_0x0000:
            monitor-enter(r3)
            java.lang.String r1 = r3.zzaq()     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x0017
            boolean r0 = com.google.firebase.iid.FirebaseInstanceId.zzm()     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x0014
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r1 = "topic sync succeeded"
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x0020 }
        L_0x0014:
            r0 = 1
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
        L_0x0016:
            return r0
        L_0x0017:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            boolean r0 = zza(r4, r1)
            if (r0 != 0) goto L_0x0023
            r0 = 0
            goto L_0x0016
        L_0x0020:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            throw r0
        L_0x0023:
            monitor-enter(r3)
            java.util.Map<java.lang.Integer, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>> r0 = r3.zzdr     // Catch:{ all -> 0x0043 }
            int r2 = r3.zzdq     // Catch:{ all -> 0x0043 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r0.remove(r2)     // Catch:{ all -> 0x0043 }
            com.google.android.gms.tasks.TaskCompletionSource r0 = (com.google.android.gms.tasks.TaskCompletionSource) r0     // Catch:{ all -> 0x0043 }
            r3.zzk(r1)     // Catch:{ all -> 0x0043 }
            int r1 = r3.zzdq     // Catch:{ all -> 0x0043 }
            int r1 = r1 + 1
            r3.zzdq = r1     // Catch:{ all -> 0x0043 }
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0000
            r1 = 0
            r0.setResult(r1)
            goto L_0x0000
        L_0x0043:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzba.zzc(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }

    @Nullable
    @GuardedBy("this")
    private final String zzaq() {
        String zzak;
        synchronized (this.zzao) {
            zzak = this.zzao.zzak();
        }
        if (!TextUtils.isEmpty(zzak)) {
            String[] split = zzak.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
        }
        return null;
    }

    private final synchronized boolean zzk(String str) {
        boolean z;
        synchronized (this.zzao) {
            String zzak = this.zzao.zzak();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (zzak.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                String valueOf3 = String.valueOf(",");
                String valueOf4 = String.valueOf(str);
                this.zzao.zzf(zzak.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length()));
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006e  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(com.google.firebase.iid.FirebaseInstanceId r6, java.lang.String r7) {
        /*
            r0 = 1
            r1 = 0
            java.lang.String r2 = "!"
            java.lang.String[] r2 = r7.split(r2)
            int r3 = r2.length
            r4 = 2
            if (r3 != r4) goto L_0x001b
            r3 = r2[r1]
            r4 = r2[r0]
            r2 = -1
            int r5 = r3.hashCode()     // Catch:{ IOException -> 0x0041 }
            switch(r5) {
                case 83: goto L_0x001c;
                case 84: goto L_0x0018;
                case 85: goto L_0x0026;
                default: goto L_0x0018;
            }     // Catch:{ IOException -> 0x0041 }
        L_0x0018:
            switch(r2) {
                case 0: goto L_0x0030;
                case 1: goto L_0x005d;
                default: goto L_0x001b;
            }     // Catch:{ IOException -> 0x0041 }
        L_0x001b:
            return r0
        L_0x001c:
            java.lang.String r5 = "S"
            boolean r3 = r3.equals(r5)     // Catch:{ IOException -> 0x0041 }
            if (r3 == 0) goto L_0x0018
            r2 = r1
            goto L_0x0018
        L_0x0026:
            java.lang.String r5 = "U"
            boolean r3 = r3.equals(r5)     // Catch:{ IOException -> 0x0041 }
            if (r3 == 0) goto L_0x0018
            r2 = r0
            goto L_0x0018
        L_0x0030:
            r6.zzb((java.lang.String) r4)     // Catch:{ IOException -> 0x0041 }
            boolean r2 = com.google.firebase.iid.FirebaseInstanceId.zzm()     // Catch:{ IOException -> 0x0041 }
            if (r2 == 0) goto L_0x001b
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r3 = "subscribe operation succeeded"
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0041 }
            goto L_0x001b
        L_0x0041:
            r0 = move-exception
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r3 = "Topic sync failed: "
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r4 = r0.length()
            if (r4 == 0) goto L_0x006e
            java.lang.String r0 = r3.concat(r0)
        L_0x0058:
            android.util.Log.e(r2, r0)
            r0 = r1
            goto L_0x001b
        L_0x005d:
            r6.zzc(r4)     // Catch:{ IOException -> 0x0041 }
            boolean r2 = com.google.firebase.iid.FirebaseInstanceId.zzm()     // Catch:{ IOException -> 0x0041 }
            if (r2 == 0) goto L_0x001b
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r3 = "unsubscribe operation succeeded"
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0041 }
            goto L_0x001b
        L_0x006e:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
            goto L_0x0058
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzba.zza(com.google.firebase.iid.FirebaseInstanceId, java.lang.String):boolean");
    }
}
