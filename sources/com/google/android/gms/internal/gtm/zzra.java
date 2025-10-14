package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzqp;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class zzra<T extends zzqp> {
    private static final Logger logger = Logger.getLogger(zzqj.class.getName());
    private static String zzbai = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";

    zzra() {
    }

    /* access modifiers changed from: protected */
    public abstract T zzpb();

    static <T extends zzqp> T zzd(Class<T> cls) {
        String format;
        String str;
        ClassLoader classLoader = zzra.class.getClassLoader();
        if (cls.equals(zzqp.class)) {
            format = zzbai;
        } else if (!cls.getPackage().equals(zzra.class.getPackage())) {
            throw new IllegalArgumentException(cls.getName());
        } else {
            format = String.format("%s.BlazeGenerated%sLoader", new Object[]{cls.getPackage().getName(), cls.getSimpleName()});
        }
        try {
            return (zzqp) cls.cast(((zzra) Class.forName(format, true, classLoader).getConstructor(new Class[0]).newInstance(new Object[0])).zzpb());
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException(e3);
        } catch (InvocationTargetException e4) {
            throw new IllegalStateException(e4);
        } catch (ClassNotFoundException e5) {
            Iterator<S> it = ServiceLoader.load(zzra.class, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add((zzqp) cls.cast(((zzra) it.next()).zzpb()));
                } catch (ServiceConfigurationError e6) {
                    Logger logger2 = logger;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(cls.getSimpleName());
                    if (valueOf.length() != 0) {
                        str = "Unable to load ".concat(valueOf);
                    } else {
                        str = new String("Unable to load ");
                    }
                    logger2.logp(level, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", str, e6);
                }
            }
            if (arrayList.size() == 1) {
                return (zzqp) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (zzqp) cls.getMethod("combine", new Class[]{Collection.class}).invoke((Object) null, new Object[]{arrayList});
            } catch (NoSuchMethodException e7) {
                throw new IllegalStateException(e7);
            } catch (IllegalAccessException e8) {
                throw new IllegalStateException(e8);
            } catch (InvocationTargetException e9) {
                throw new IllegalStateException(e9);
            }
        }
    }
}
