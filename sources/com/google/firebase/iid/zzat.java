package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzl;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;

final class zzat {
    private static int zzcj = 0;
    private static PendingIntent zzcv;
    private final Context zzac;
    private final zzan zzas;
    @GuardedBy("responseCallbacks")
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzcw = new SimpleArrayMap<>();
    private Messenger zzcx;
    private Messenger zzcy;
    private zzl zzcz;

    public zzat(Context context, zzan zzan) {
        this.zzac = context;
        this.zzas = zzan;
        this.zzcx = new Messenger(new zzau(this, Looper.getMainLooper()));
    }

    /* access modifiers changed from: private */
    public final void zzb(Message message) {
        String str;
        String str2;
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("FirebaseInstanceId", "Dropping invalid message");
            return;
        }
        Intent intent = (Intent) message.obj;
        intent.setExtrasClassLoader(new zzl.zza());
        if (intent.hasExtra("google.messenger")) {
            Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
            if (parcelableExtra instanceof zzl) {
                this.zzcz = (zzl) parcelableExtra;
            }
            if (parcelableExtra instanceof Messenger) {
                this.zzcy = (Messenger) parcelableExtra;
            }
        }
        Intent intent2 = (Intent) message.obj;
        String action = intent2.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            String stringExtra = intent2.getStringExtra("registration_id");
            if (stringExtra == null) {
                stringExtra = intent2.getStringExtra("unregistered");
            }
            if (stringExtra == null) {
                String stringExtra2 = intent2.getStringExtra(MediaRouteProviderProtocol.SERVICE_DATA_ERROR);
                if (stringExtra2 == null) {
                    String valueOf = String.valueOf(intent2.getExtras());
                    Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 49).append("Unexpected response, no error or registration id ").append(valueOf).toString());
                    return;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf2 = String.valueOf(stringExtra2);
                    Log.d("FirebaseInstanceId", valueOf2.length() != 0 ? "Received InstanceID error ".concat(valueOf2) : new String("Received InstanceID error "));
                }
                if (stringExtra2.startsWith("|")) {
                    String[] split = stringExtra2.split("\\|");
                    if (split.length <= 2 || !"ID".equals(split[1])) {
                        String valueOf3 = String.valueOf(stringExtra2);
                        if (valueOf3.length() != 0) {
                            str = "Unexpected structured response ".concat(valueOf3);
                        } else {
                            str = new String("Unexpected structured response ");
                        }
                        Log.w("FirebaseInstanceId", str);
                        return;
                    }
                    String str3 = split[2];
                    String str4 = split[3];
                    if (str4.startsWith(":")) {
                        str4 = str4.substring(1);
                    }
                    zza(str3, intent2.putExtra(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, str4).getExtras());
                    return;
                }
                synchronized (this.zzcw) {
                    for (int i = 0; i < this.zzcw.size(); i++) {
                        zza(this.zzcw.keyAt(i), intent2.getExtras());
                    }
                }
                return;
            }
            Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
            if (matcher.matches()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                Bundle extras = intent2.getExtras();
                extras.putString("registration_id", group2);
                zza(group, extras);
            } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf4 = String.valueOf(stringExtra);
                Log.d("FirebaseInstanceId", valueOf4.length() != 0 ? "Unexpected response string: ".concat(valueOf4) : new String("Unexpected response string: "));
            }
        } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf5 = String.valueOf(action);
            if (valueOf5.length() != 0) {
                str2 = "Unexpected response action: ".concat(valueOf5);
            } else {
                str2 = new String("Unexpected response action: ");
            }
            Log.d("FirebaseInstanceId", str2);
        }
    }

    private static synchronized void zza(Context context, Intent intent) {
        synchronized (zzat.class) {
            if (zzcv == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzcv = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", zzcv);
        }
    }

    private final void zza(String str, Bundle bundle) {
        synchronized (this.zzcw) {
            TaskCompletionSource remove = this.zzcw.remove(str);
            if (remove == null) {
                String valueOf = String.valueOf(str);
                Log.w("FirebaseInstanceId", valueOf.length() != 0 ? "Missing callback for ".concat(valueOf) : new String("Missing callback for "));
                return;
            }
            remove.setResult(bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzc(Bundle bundle) throws IOException {
        if (this.zzas.zzaf() < 12000000) {
            return zzd(bundle);
        }
        try {
            return (Bundle) Tasks.await(zzab.zzc(this.zzac).zzb(1, bundle));
        } catch (InterruptedException | ExecutionException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(e);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 22).append("Error making request: ").append(valueOf).toString());
            }
            if (!(e.getCause() instanceof zzal) || ((zzal) e.getCause()).getErrorCode() != 4) {
                return null;
            }
            return zzd(bundle);
        }
    }

    private final Bundle zzd(Bundle bundle) throws IOException {
        Bundle zze = zze(bundle);
        if (zze == null || !zze.containsKey("google.messenger")) {
            return zze;
        }
        Bundle zze2 = zze(bundle);
        if (zze2 == null || !zze2.containsKey("google.messenger")) {
            return zze2;
        }
        return null;
    }

    private static synchronized String zzah() {
        String num;
        synchronized (zzat.class) {
            int i = zzcj;
            zzcj = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private final android.os.Bundle zze(android.os.Bundle r10) throws java.io.IOException {
        /*
            r9 = this;
            r8 = 3
            r7 = 2
            java.lang.String r1 = zzah()
            com.google.android.gms.tasks.TaskCompletionSource r0 = new com.google.android.gms.tasks.TaskCompletionSource
            r0.<init>()
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r2 = r9.zzcw
            monitor-enter(r2)
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r3 = r9.zzcw     // Catch:{ all -> 0x0024 }
            r3.put(r1, r0)     // Catch:{ all -> 0x0024 }
            monitor-exit(r2)     // Catch:{ all -> 0x0024 }
            com.google.firebase.iid.zzan r2 = r9.zzas
            int r2 = r2.zzac()
            if (r2 != 0) goto L_0x0027
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "MISSING_INSTANCEID_SERVICE"
            r0.<init>(r1)
            throw r0
        L_0x0024:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0024 }
            throw r0
        L_0x0027:
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            java.lang.String r3 = "com.google.android.gms"
            r2.setPackage(r3)
            com.google.firebase.iid.zzan r3 = r9.zzas
            int r3 = r3.zzac()
            if (r3 != r7) goto L_0x00d8
            java.lang.String r3 = "com.google.iid.TOKEN_REQUEST"
            r2.setAction(r3)
        L_0x003e:
            r2.putExtras(r10)
            android.content.Context r3 = r9.zzac
            zza((android.content.Context) r3, (android.content.Intent) r2)
            java.lang.String r3 = "kid"
            java.lang.String r4 = java.lang.String.valueOf(r1)
            int r4 = r4.length()
            int r4 = r4 + 5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "|ID|"
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r5 = "|"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.putExtra(r3, r4)
            java.lang.String r3 = "FirebaseInstanceId"
            boolean r3 = android.util.Log.isLoggable(r3, r8)
            if (r3 == 0) goto L_0x00a2
            java.lang.String r3 = "FirebaseInstanceId"
            android.os.Bundle r4 = r2.getExtras()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r5 = java.lang.String.valueOf(r4)
            int r5 = r5.length()
            int r5 = r5 + 8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Sending "
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
        L_0x00a2:
            java.lang.String r3 = "google.messenger"
            android.os.Messenger r4 = r9.zzcx
            r2.putExtra(r3, r4)
            android.os.Messenger r3 = r9.zzcy
            if (r3 != 0) goto L_0x00b1
            com.google.firebase.iid.zzl r3 = r9.zzcz
            if (r3 == 0) goto L_0x00f5
        L_0x00b1:
            android.os.Message r3 = android.os.Message.obtain()
            r3.obj = r2
            android.os.Messenger r4 = r9.zzcy     // Catch:{ RemoteException -> 0x00e5 }
            if (r4 == 0) goto L_0x00df
            android.os.Messenger r4 = r9.zzcy     // Catch:{ RemoteException -> 0x00e5 }
            r4.send(r3)     // Catch:{ RemoteException -> 0x00e5 }
        L_0x00c0:
            com.google.android.gms.tasks.Task r0 = r0.getTask()     // Catch:{ InterruptedException -> 0x010c, TimeoutException -> 0x0131, ExecutionException -> 0x0127 }
            r2 = 30000(0x7530, double:1.4822E-319)
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x010c, TimeoutException -> 0x0131, ExecutionException -> 0x0127 }
            java.lang.Object r0 = com.google.android.gms.tasks.Tasks.await(r0, r2, r4)     // Catch:{ InterruptedException -> 0x010c, TimeoutException -> 0x0131, ExecutionException -> 0x0127 }
            android.os.Bundle r0 = (android.os.Bundle) r0     // Catch:{ InterruptedException -> 0x010c, TimeoutException -> 0x0131, ExecutionException -> 0x0127 }
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r2 = r9.zzcw
            monitor-enter(r2)
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r3 = r9.zzcw     // Catch:{ all -> 0x0109 }
            r3.remove(r1)     // Catch:{ all -> 0x0109 }
            monitor-exit(r2)     // Catch:{ all -> 0x0109 }
            return r0
        L_0x00d8:
            java.lang.String r3 = "com.google.android.c2dm.intent.REGISTER"
            r2.setAction(r3)
            goto L_0x003e
        L_0x00df:
            com.google.firebase.iid.zzl r4 = r9.zzcz     // Catch:{ RemoteException -> 0x00e5 }
            r4.send(r3)     // Catch:{ RemoteException -> 0x00e5 }
            goto L_0x00c0
        L_0x00e5:
            r3 = move-exception
            java.lang.String r3 = "FirebaseInstanceId"
            boolean r3 = android.util.Log.isLoggable(r3, r8)
            if (r3 == 0) goto L_0x00f5
            java.lang.String r3 = "FirebaseInstanceId"
            java.lang.String r4 = "Messenger failed, fallback to startService"
            android.util.Log.d(r3, r4)
        L_0x00f5:
            com.google.firebase.iid.zzan r3 = r9.zzas
            int r3 = r3.zzac()
            if (r3 != r7) goto L_0x0103
            android.content.Context r3 = r9.zzac
            r3.sendBroadcast(r2)
            goto L_0x00c0
        L_0x0103:
            android.content.Context r3 = r9.zzac
            r3.startService(r2)
            goto L_0x00c0
        L_0x0109:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0109 }
            throw r0
        L_0x010c:
            r0 = move-exception
        L_0x010d:
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r2 = "No response"
            android.util.Log.w(r0, r2)     // Catch:{ all -> 0x011c }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x011c }
            java.lang.String r2 = "TIMEOUT"
            r0.<init>(r2)     // Catch:{ all -> 0x011c }
            throw r0     // Catch:{ all -> 0x011c }
        L_0x011c:
            r0 = move-exception
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r2 = r9.zzcw
            monitor-enter(r2)
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.TaskCompletionSource<android.os.Bundle>> r3 = r9.zzcw     // Catch:{ all -> 0x012e }
            r3.remove(r1)     // Catch:{ all -> 0x012e }
            monitor-exit(r2)     // Catch:{ all -> 0x012e }
            throw r0
        L_0x0127:
            r0 = move-exception
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x011c }
            r2.<init>(r0)     // Catch:{ all -> 0x011c }
            throw r2     // Catch:{ all -> 0x011c }
        L_0x012e:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x012e }
            throw r0
        L_0x0131:
            r0 = move-exception
            goto L_0x010d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzat.zze(android.os.Bundle):android.os.Bundle");
    }
}
