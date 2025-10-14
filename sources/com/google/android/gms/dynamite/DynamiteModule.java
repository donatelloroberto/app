package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class DynamiteModule {
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzb();
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzid;
    @GuardedBy("DynamiteModule.class")
    private static zzi zzie;
    @GuardedBy("DynamiteModule.class")
    private static zzk zzif;
    @GuardedBy("DynamiteModule.class")
    private static String zzig;
    @GuardedBy("DynamiteModule.class")
    private static int zzih = -1;
    private static final ThreadLocal<zza> zzii = new ThreadLocal<>();
    private static final VersionPolicy.zza zzij = new zza();
    private static final VersionPolicy zzik = new zzc();
    private static final VersionPolicy zzil = new zzg();
    private final Context zzim;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    public interface VersionPolicy {

        public interface zza {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        public static class zzb {
            public int zziq = 0;
            public int zzir = 0;
            public int zzis = 0;
        }

        zzb zza(Context context, String str, zza zza2) throws LoadingException;
    }

    private static class zza {
        public Cursor zzin;

        private zza() {
        }

        /* synthetic */ zza(zza zza) {
            this();
        }
    }

    @KeepForSdk
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        VersionPolicy.zzb zza2;
        zza zza3 = zzii.get();
        zza zza4 = new zza((zza) null);
        zzii.set(zza4);
        try {
            zza2 = versionPolicy.zza(context, str, zzij);
            Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza2.zziq).append(" and remote module ").append(str).append(":").append(zza2.zzir).toString());
            if (zza2.zzis == 0 || ((zza2.zzis == -1 && zza2.zziq == 0) || (zza2.zzis == 1 && zza2.zzir == 0))) {
                throw new LoadingException(new StringBuilder(91).append("No acceptable module found. Local version is ").append(zza2.zziq).append(" and remote version is ").append(zza2.zzir).append(".").toString(), (zza) null);
            } else if (zza2.zzis == -1) {
                DynamiteModule zze = zze(context, str);
                if (zza4.zzin != null) {
                    zza4.zzin.close();
                }
                zzii.set(zza3);
                return zze;
            } else if (zza2.zzis == 1) {
                DynamiteModule zza5 = zza(context, str, zza2.zzir);
                if (zza4.zzin != null) {
                    zza4.zzin.close();
                }
                zzii.set(zza3);
                return zza5;
            } else {
                throw new LoadingException(new StringBuilder(47).append("VersionPolicy returned invalid code:").append(zza2.zzis).toString(), (zza) null);
            }
        } catch (LoadingException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
            if (zza2.zziq == 0 || versionPolicy.zza(context, str, new zzb(zza2.zziq, 0)).zzis != -1) {
                throw new LoadingException("Remote load failed. No local fallback found.", e, (zza) null);
            }
            DynamiteModule zze2 = zze(context, str);
            if (zza4.zzin != null) {
                zza4.zzin.close();
            }
            zzii.set(zza3);
            return zze2;
        } catch (Throwable th) {
            if (zza4.zzin != null) {
                zza4.zzin.close();
            }
            zzii.set(zza3);
            throw th;
        }
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, zza zza) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zza zza) {
            this(str, th);
        }
    }

    private static class zzb implements VersionPolicy.zza {
        private final int zzio;
        private final int zzip = 0;

        public zzb(int i, int i2) {
            this.zzio = i;
        }

        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        public final int getLocalVersion(Context context, String str) {
            return this.zzio;
        }
    }

    @KeepForSdk
    public static int getLocalVersion(Context context, String str) {
        try {
            Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass(new StringBuilder(String.valueOf(str).length() + 61).append("com.google.android.gms.dynamite.descriptors.").append(str).append(".ModuleDescriptor").toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get((Object) null).equals(str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            Log.e("DynamiteModule", new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:37:0x0071=Splitter:B:37:0x0071, B:27:0x0043=Splitter:B:27:0x0043} */
    public static int zza(android.content.Context r7, java.lang.String r8, boolean r9) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r1 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r1)     // Catch:{ Throwable -> 0x0077 }
            java.lang.Boolean r0 = zzid     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0034
            android.content.Context r0 = r7.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x00a4, IllegalAccessException -> 0x00fd, NoSuchFieldException -> 0x00fb }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00a4, IllegalAccessException -> 0x00fd, NoSuchFieldException -> 0x00fb }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r2 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r2 = r2.getName()     // Catch:{ ClassNotFoundException -> 0x00a4, IllegalAccessException -> 0x00fd, NoSuchFieldException -> 0x00fb }
            java.lang.Class r2 = r0.loadClass(r2)     // Catch:{ ClassNotFoundException -> 0x00a4, IllegalAccessException -> 0x00fd, NoSuchFieldException -> 0x00fb }
            java.lang.String r0 = "sClassLoader"
            java.lang.reflect.Field r3 = r2.getDeclaredField(r0)     // Catch:{ ClassNotFoundException -> 0x00a4, IllegalAccessException -> 0x00fd, NoSuchFieldException -> 0x00fb }
            monitor-enter(r2)     // Catch:{ ClassNotFoundException -> 0x00a4, IllegalAccessException -> 0x00fd, NoSuchFieldException -> 0x00fb }
            r0 = 0
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x00a1 }
            java.lang.ClassLoader r0 = (java.lang.ClassLoader) r0     // Catch:{ all -> 0x00a1 }
            if (r0 == 0) goto L_0x0046
            java.lang.ClassLoader r3 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00a1 }
            if (r0 != r3) goto L_0x0040
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00a1 }
        L_0x0031:
            monitor-exit(r2)     // Catch:{ all -> 0x00a1 }
        L_0x0032:
            zzid = r0     // Catch:{ all -> 0x0074 }
        L_0x0034:
            monitor-exit(r1)     // Catch:{ all -> 0x0074 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x0077 }
            if (r0 == 0) goto L_0x00f2
            int r0 = zzc((android.content.Context) r7, (java.lang.String) r8, (boolean) r9)     // Catch:{ LoadingException -> 0x00cf }
        L_0x003f:
            return r0
        L_0x0040:
            zza(r0)     // Catch:{ LoadingException -> 0x00f8 }
        L_0x0043:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00a1 }
            goto L_0x0031
        L_0x0046:
            java.lang.String r0 = "com.google.android.gms"
            android.content.Context r4 = r7.getApplicationContext()     // Catch:{ all -> 0x00a1 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x00a1 }
            boolean r0 = r0.equals(r4)     // Catch:{ all -> 0x00a1 }
            if (r0 == 0) goto L_0x0061
            r0 = 0
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00a1 }
            r3.set(r0, r4)     // Catch:{ all -> 0x00a1 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00a1 }
            goto L_0x0031
        L_0x0061:
            int r0 = zzc((android.content.Context) r7, (java.lang.String) r8, (boolean) r9)     // Catch:{ LoadingException -> 0x0095 }
            java.lang.String r4 = zzig     // Catch:{ LoadingException -> 0x0095 }
            if (r4 == 0) goto L_0x0071
            java.lang.String r4 = zzig     // Catch:{ LoadingException -> 0x0095 }
            boolean r4 = r4.isEmpty()     // Catch:{ LoadingException -> 0x0095 }
            if (r4 == 0) goto L_0x007c
        L_0x0071:
            monitor-exit(r2)     // Catch:{ all -> 0x00a1 }
            monitor-exit(r1)     // Catch:{ all -> 0x0074 }
            goto L_0x003f
        L_0x0074:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0074 }
            throw r0     // Catch:{ Throwable -> 0x0077 }
        L_0x0077:
            r0 = move-exception
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r7, r0)
            throw r0
        L_0x007c:
            com.google.android.gms.dynamite.zzh r4 = new com.google.android.gms.dynamite.zzh     // Catch:{ LoadingException -> 0x0095 }
            java.lang.String r5 = zzig     // Catch:{ LoadingException -> 0x0095 }
            java.lang.ClassLoader r6 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x0095 }
            r4.<init>(r5, r6)     // Catch:{ LoadingException -> 0x0095 }
            zza(r4)     // Catch:{ LoadingException -> 0x0095 }
            r5 = 0
            r3.set(r5, r4)     // Catch:{ LoadingException -> 0x0095 }
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ LoadingException -> 0x0095 }
            zzid = r4     // Catch:{ LoadingException -> 0x0095 }
            monitor-exit(r2)     // Catch:{ all -> 0x00a1 }
            monitor-exit(r1)     // Catch:{ all -> 0x0074 }
            goto L_0x003f
        L_0x0095:
            r0 = move-exception
            r0 = 0
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00a1 }
            r3.set(r0, r4)     // Catch:{ all -> 0x00a1 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00a1 }
            goto L_0x0031
        L_0x00a1:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00a1 }
            throw r0     // Catch:{ ClassNotFoundException -> 0x00a4, IllegalAccessException -> 0x00fd, NoSuchFieldException -> 0x00fb }
        L_0x00a4:
            r0 = move-exception
        L_0x00a5:
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0074 }
            int r3 = r3.length()     // Catch:{ all -> 0x0074 }
            int r3 = r3 + 30
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r4.<init>(r3)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = "Failed to load module via V2: "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x0074 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0074 }
            android.util.Log.w(r2, r0)     // Catch:{ all -> 0x0074 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0074 }
            goto L_0x0032
        L_0x00cf:
            r0 = move-exception
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r2 = "Failed to retrieve remote module version: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0077 }
            int r3 = r0.length()     // Catch:{ Throwable -> 0x0077 }
            if (r3 == 0) goto L_0x00ec
            java.lang.String r0 = r2.concat(r0)     // Catch:{ Throwable -> 0x0077 }
        L_0x00e6:
            android.util.Log.w(r1, r0)     // Catch:{ Throwable -> 0x0077 }
            r0 = 0
            goto L_0x003f
        L_0x00ec:
            java.lang.String r0 = new java.lang.String     // Catch:{ Throwable -> 0x0077 }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0077 }
            goto L_0x00e6
        L_0x00f2:
            int r0 = zzb((android.content.Context) r7, (java.lang.String) r8, (boolean) r9)     // Catch:{ Throwable -> 0x0077 }
            goto L_0x003f
        L_0x00f8:
            r0 = move-exception
            goto L_0x0043
        L_0x00fb:
            r0 = move-exception
            goto L_0x00a5
        L_0x00fd:
            r0 = move-exception
            goto L_0x00a5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    private static int zzb(Context context, String str, boolean z) {
        zzi zzj = zzj(context);
        if (zzj == null) {
            return 0;
        }
        try {
            if (zzj.zzaj() >= 2) {
                return zzj.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzj.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzc(android.content.Context r7, java.lang.String r8, boolean r9) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r6 = 0
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            if (r9 == 0) goto L_0x006a
            java.lang.String r1 = "api_force_staging"
        L_0x0009:
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            int r2 = r2.length()     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            int r2 = r2 + 42
            java.lang.String r3 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            int r3 = r3.length()     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.String r2 = "content://com.google.android.gms.chimera/"
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.String r2 = "/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.StringBuilder r1 = r1.append(r8)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            if (r1 == 0) goto L_0x004d
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x005d }
            if (r0 != 0) goto L_0x006d
        L_0x004d:
            java.lang.String r0 = "DynamiteModule"
            java.lang.String r2 = "Failed to retrieve remote module version."
            android.util.Log.w(r0, r2)     // Catch:{ Exception -> 0x005d }
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x005d }
            java.lang.String r2 = "Failed to connect to dynamite module ContentResolver."
            r3 = 0
            r0.<init>((java.lang.String) r2, (com.google.android.gms.dynamite.zza) r3)     // Catch:{ Exception -> 0x005d }
            throw r0     // Catch:{ Exception -> 0x005d }
        L_0x005d:
            r0 = move-exception
        L_0x005e:
            boolean r2 = r0 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch:{ all -> 0x0063 }
            if (r2 == 0) goto L_0x00a7
            throw r0     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r0 = move-exception
        L_0x0064:
            if (r1 == 0) goto L_0x0069
            r1.close()
        L_0x0069:
            throw r0
        L_0x006a:
            java.lang.String r1 = "api"
            goto L_0x0009
        L_0x006d:
            r0 = 0
            int r2 = r1.getInt(r0)     // Catch:{ Exception -> 0x005d }
            if (r2 <= 0) goto L_0x009e
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r3 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r3)     // Catch:{ Exception -> 0x005d }
            r0 = 2
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x00a4 }
            zzig = r0     // Catch:{ all -> 0x00a4 }
            java.lang.String r0 = "loaderVersion"
            int r0 = r1.getColumnIndex(r0)     // Catch:{ all -> 0x00a4 }
            if (r0 < 0) goto L_0x008c
            int r0 = r1.getInt(r0)     // Catch:{ all -> 0x00a4 }
            zzih = r0     // Catch:{ all -> 0x00a4 }
        L_0x008c:
            monitor-exit(r3)     // Catch:{ all -> 0x00a4 }
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r0 = zzii     // Catch:{ Exception -> 0x005d }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x005d }
            com.google.android.gms.dynamite.DynamiteModule$zza r0 = (com.google.android.gms.dynamite.DynamiteModule.zza) r0     // Catch:{ Exception -> 0x005d }
            if (r0 == 0) goto L_0x009e
            android.database.Cursor r3 = r0.zzin     // Catch:{ Exception -> 0x005d }
            if (r3 != 0) goto L_0x009e
            r0.zzin = r1     // Catch:{ Exception -> 0x005d }
            r1 = r6
        L_0x009e:
            if (r1 == 0) goto L_0x00a3
            r1.close()
        L_0x00a3:
            return r2
        L_0x00a4:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00a4 }
            throw r0     // Catch:{ Exception -> 0x005d }
        L_0x00a7:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r2 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x0063 }
            java.lang.String r3 = "V2 version check failed"
            r4 = 0
            r2.<init>(r3, r0, r4)     // Catch:{ all -> 0x0063 }
            throw r2     // Catch:{ all -> 0x0063 }
        L_0x00b0:
            r0 = move-exception
            r1 = r6
            goto L_0x0064
        L_0x00b3:
            r0 = move-exception
            r1 = r6
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zze(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.dynamite.DynamiteModule zza(android.content.Context r3, java.lang.String r4, int r5) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r1 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r1)     // Catch:{ Throwable -> 0x0011 }
            java.lang.Boolean r0 = zzid     // Catch:{ all -> 0x0016 }
            monitor-exit(r1)     // Catch:{ all -> 0x0016 }
            if (r0 != 0) goto L_0x0019
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r1 = "Failed to determine which loading route to use."
            r2 = 0
            r0.<init>((java.lang.String) r1, (com.google.android.gms.dynamite.zza) r2)     // Catch:{ Throwable -> 0x0011 }
            throw r0     // Catch:{ Throwable -> 0x0011 }
        L_0x0011:
            r0 = move-exception
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r3, r0)
            throw r0
        L_0x0016:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0016 }
            throw r0     // Catch:{ Throwable -> 0x0011 }
        L_0x0019:
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x0011 }
            if (r0 == 0) goto L_0x0024
            com.google.android.gms.dynamite.DynamiteModule r0 = zzc((android.content.Context) r3, (java.lang.String) r4, (int) r5)     // Catch:{ Throwable -> 0x0011 }
        L_0x0023:
            return r0
        L_0x0024:
            com.google.android.gms.dynamite.DynamiteModule r0 = zzb((android.content.Context) r3, (java.lang.String) r4, (int) r5)     // Catch:{ Throwable -> 0x0011 }
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, int):com.google.android.gms.dynamite.DynamiteModule");
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException {
        IObjectWrapper zza2;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zzi zzj = zzj(context);
        if (zzj == null) {
            throw new LoadingException("Failed to create IDynamiteLoader.", (zza) null);
        }
        try {
            if (zzj.zzaj() >= 2) {
                zza2 = zzj.zzb(ObjectWrapper.wrap(context), str, i);
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                zza2 = zzj.zza(ObjectWrapper.wrap(context), str, i);
            }
            if (ObjectWrapper.unwrap(zza2) != null) {
                return new DynamiteModule((Context) ObjectWrapper.unwrap(zza2));
            }
            throw new LoadingException("Failed to load remote module.", (zza) null);
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, (zza) null);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.dynamite.zzi zzj(android.content.Context r7) {
        /*
            r3 = 0
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r4 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r4)
            com.google.android.gms.dynamite.zzi r1 = zzie     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x000c
            com.google.android.gms.dynamite.zzi r1 = zzie     // Catch:{ all -> 0x0039 }
            monitor-exit(r4)     // Catch:{ all -> 0x0039 }
        L_0x000b:
            return r1
        L_0x000c:
            com.google.android.gms.common.GoogleApiAvailabilityLight r1 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()     // Catch:{ all -> 0x0039 }
            int r1 = r1.isGooglePlayServicesAvailable(r7)     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0019
            monitor-exit(r4)     // Catch:{ all -> 0x0039 }
            r1 = r3
            goto L_0x000b
        L_0x0019:
            java.lang.String r1 = "com.google.android.gms"
            r2 = 3
            android.content.Context r1 = r7.createPackageContext(r1, r2)     // Catch:{ Exception -> 0x0052 }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ Exception -> 0x0052 }
            java.lang.String r2 = "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
            java.lang.Class r1 = r1.loadClass(r2)     // Catch:{ Exception -> 0x0052 }
            java.lang.Object r1 = r1.newInstance()     // Catch:{ Exception -> 0x0052 }
            android.os.IBinder r1 = (android.os.IBinder) r1     // Catch:{ Exception -> 0x0052 }
            if (r1 != 0) goto L_0x003c
            r1 = r3
        L_0x0033:
            if (r1 == 0) goto L_0x006c
            zzie = r1     // Catch:{ Exception -> 0x0052 }
            monitor-exit(r4)     // Catch:{ all -> 0x0039 }
            goto L_0x000b
        L_0x0039:
            r1 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0039 }
            throw r1
        L_0x003c:
            java.lang.String r2 = "com.google.android.gms.dynamite.IDynamiteLoader"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)     // Catch:{ Exception -> 0x0052 }
            boolean r5 = r2 instanceof com.google.android.gms.dynamite.zzi     // Catch:{ Exception -> 0x0052 }
            if (r5 == 0) goto L_0x004b
            r0 = r2
            com.google.android.gms.dynamite.zzi r0 = (com.google.android.gms.dynamite.zzi) r0     // Catch:{ Exception -> 0x0052 }
            r1 = r0
            goto L_0x0033
        L_0x004b:
            com.google.android.gms.dynamite.zzj r2 = new com.google.android.gms.dynamite.zzj     // Catch:{ Exception -> 0x0052 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0052 }
            r1 = r2
            goto L_0x0033
        L_0x0052:
            r1 = move-exception
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r5 = "Failed to load IDynamiteLoader from GmsCore: "
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0039 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0039 }
            int r6 = r1.length()     // Catch:{ all -> 0x0039 }
            if (r6 == 0) goto L_0x006f
            java.lang.String r1 = r5.concat(r1)     // Catch:{ all -> 0x0039 }
        L_0x0069:
            android.util.Log.e(r2, r1)     // Catch:{ all -> 0x0039 }
        L_0x006c:
            monitor-exit(r4)     // Catch:{ all -> 0x0039 }
            r1 = r3
            goto L_0x000b
        L_0x006f:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0039 }
            r1.<init>(r5)     // Catch:{ all -> 0x0039 }
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzj(android.content.Context):com.google.android.gms.dynamite.zzi");
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzim;
    }

    private static DynamiteModule zzc(Context context, String str, int i) throws LoadingException {
        zzk zzk;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            zzk = zzif;
        }
        if (zzk == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", (zza) null);
        }
        zza zza2 = zzii.get();
        if (zza2 == null || zza2.zzin == null) {
            throw new LoadingException("No result cursor", (zza) null);
        }
        Context zza3 = zza(context.getApplicationContext(), str, i, zza2.zzin, zzk);
        if (zza3 != null) {
            return new DynamiteModule(zza3);
        }
        throw new LoadingException("Failed to get module context", (zza) null);
    }

    private static Boolean zzai() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zzih >= 2);
        }
        return valueOf;
    }

    private static Context zza(Context context, String str, int i, Cursor cursor, zzk zzk) {
        IObjectWrapper zza2;
        try {
            ObjectWrapper.wrap(null);
            if (zzai().booleanValue()) {
                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                zza2 = zzk.zzb(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(cursor));
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                zza2 = zzk.zza(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(cursor));
            }
            return (Context) ObjectWrapper.unwrap(zza2);
        } catch (Exception e) {
            String valueOf = String.valueOf(e.toString());
            Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(valueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    @GuardedBy("DynamiteModule.class")
    private static void zza(ClassLoader classLoader) throws LoadingException {
        zzk zzl;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzl = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzk) {
                    zzl = (zzk) queryLocalInterface;
                } else {
                    zzl = new zzl(iBinder);
                }
            }
            zzif = zzl;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zza) null);
        }
    }

    @KeepForSdk
    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzim.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, (zza) null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzim = (Context) Preconditions.checkNotNull(context);
    }
}
