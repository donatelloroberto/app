package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    private static final String[] zzafn = "gtm.lifetime".toString().split("\\.");
    private static final Pattern zzafo = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> zzafp;
    private final Map<String, Object> zzafq;
    private final ReentrantLock zzafr;
    private final LinkedList<Map<String, Object>> zzafs;
    private final zzc zzaft;
    /* access modifiers changed from: private */
    public final CountDownLatch zzafu;

    interface zzb {
        void zzc(Map<String, Object> map);
    }

    interface zzc {
        void zza(zzaq zzaq);

        void zza(List<zza> list, long j);

        void zzas(String str);
    }

    @VisibleForTesting
    DataLayer() {
        this(new zzao());
    }

    DataLayer(zzc zzc2) {
        this.zzaft = zzc2;
        this.zzafp = new ConcurrentHashMap<>();
        this.zzafq = new HashMap();
        this.zzafr = new ReentrantLock();
        this.zzafs = new LinkedList<>();
        this.zzafu = new CountDownLatch(1);
        this.zzaft.zza(new zzap(this));
    }

    static final class zza {
        public final String mKey;
        public final Object mValue;

        zza(String str, Object obj) {
            this.mKey = str;
            this.mValue = obj;
        }

        public final String toString() {
            String str = this.mKey;
            String obj = this.mValue.toString();
            return new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(obj).length()).append("Key: ").append(str).append(" value: ").append(obj).toString();
        }

        public final int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.mKey.hashCode()), Integer.valueOf(this.mValue.hashCode())});
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (!this.mKey.equals(zza.mKey) || !this.mValue.equals(zza.mValue)) {
                return false;
            }
            return true;
        }
    }

    public String toString() {
        String sb;
        synchronized (this.zzafq) {
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry next : this.zzafq.entrySet()) {
                sb2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{next.getKey(), next.getValue()}));
            }
            sb = sb2.toString();
        }
        return sb;
    }

    public void pushEvent(String str, Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    public void push(String str, Object obj) {
        push(zzg(str, obj));
    }

    public void push(Map<String, Object> map) {
        try {
            this.zzafu.await();
        } catch (InterruptedException e) {
            zzdi.zzac("DataLayer.push: unexpected InterruptedException");
        }
        zze(map);
    }

    /* access modifiers changed from: private */
    public final void zze(Map<String, Object> map) {
        Long zzar;
        this.zzafr.lock();
        try {
            this.zzafs.offer(map);
            if (this.zzafr.getHoldCount() == 1) {
                int i = 0;
                while (true) {
                    Map poll = this.zzafs.poll();
                    if (poll == null) {
                        break;
                    }
                    synchronized (this.zzafq) {
                        for (String str : poll.keySet()) {
                            zzb(zzg(str, poll.get(str)), this.zzafq);
                        }
                    }
                    for (zzb zzc2 : this.zzafp.keySet()) {
                        zzc2.zzc(poll);
                    }
                    int i2 = i + 1;
                    if (i2 > 500) {
                        this.zzafs.clear();
                        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                    }
                    i = i2;
                }
            }
            String[] strArr = zzafn;
            int length = strArr.length;
            int i3 = 0;
            Object obj = map;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                String str2 = strArr[i3];
                if (!(obj instanceof Map)) {
                    obj = null;
                    break;
                } else {
                    obj = ((Map) obj).get(str2);
                    i3++;
                }
            }
            if (obj == null) {
                zzar = null;
            } else {
                zzar = zzar(obj.toString());
            }
            if (zzar != null) {
                ArrayList arrayList = new ArrayList();
                zza(map, "", arrayList);
                this.zzaft.zza(arrayList, zzar.longValue());
            }
        } finally {
            this.zzafr.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaq(String str) {
        push(str, (Object) null);
        this.zzaft.zzas(str);
    }

    private final void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Map.Entry next : map.entrySet()) {
            String str2 = str.length() == 0 ? "" : ".";
            String str3 = (String) next.getKey();
            String sb = new StringBuilder(String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length()).append(str).append(str2).append(str3).toString();
            if (next.getValue() instanceof Map) {
                zza((Map) next.getValue(), sb, collection);
            } else if (!sb.equals("gtm.lifetime")) {
                collection.add(new zza(sb, next.getValue()));
            }
        }
    }

    @VisibleForTesting
    private static Long zzar(String str) {
        long j;
        String str2;
        String str3;
        Matcher matcher = zzafo.matcher(str);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                str3 = "unknown _lifetime: ".concat(valueOf);
            } else {
                str3 = new String("unknown _lifetime: ");
            }
            zzdi.zzaw(str3);
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException e) {
            String valueOf2 = String.valueOf(str);
            zzdi.zzac(valueOf2.length() != 0 ? "illegal number in _lifetime value: ".concat(valueOf2) : new String("illegal number in _lifetime value: "));
            j = 0;
        }
        if (j <= 0) {
            String valueOf3 = String.valueOf(str);
            zzdi.zzaw(valueOf3.length() != 0 ? "non-positive _lifetime: ".concat(valueOf3) : new String("non-positive _lifetime: "));
            return null;
        }
        String group = matcher.group(2);
        if (group.length() == 0) {
            return Long.valueOf(j);
        }
        switch (group.charAt(0)) {
            case 'd':
                return Long.valueOf(j * 1000 * 60 * 60 * 24);
            case 'h':
                return Long.valueOf(j * 1000 * 60 * 60);
            case 'm':
                return Long.valueOf(j * 1000 * 60);
            case 's':
                return Long.valueOf(j * 1000);
            default:
                String valueOf4 = String.valueOf(str);
                if (valueOf4.length() != 0) {
                    str2 = "unknown units in _lifetime: ".concat(valueOf4);
                } else {
                    str2 = new String("unknown units in _lifetime: ");
                }
                zzdi.zzac(str2);
                return null;
        }
    }

    public Object get(String str) {
        synchronized (this.zzafq) {
            Object obj = this.zzafq;
            for (String str2 : str.split("\\.")) {
                if (!(obj instanceof Map)) {
                    return null;
                }
                obj = ((Map) obj).get(str2);
                if (obj == null) {
                    return null;
                }
            }
            return obj;
        }
    }

    @VisibleForTesting
    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objArr.length) {
                return hashMap;
            }
            if (!(objArr[i2] instanceof String)) {
                String valueOf = String.valueOf(objArr[i2]);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 21).append("key is not a string: ").append(valueOf).toString());
            }
            hashMap.put(objArr[i2], objArr[i2 + 1]);
            i = i2 + 2;
        }
    }

    @VisibleForTesting
    public static List<Object> listOf(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzb zzb2) {
        this.zzafp.put(zzb2, 0);
    }

    static Map<String, Object> zzg(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        HashMap hashMap2 = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap3 = new HashMap();
            hashMap2.put(split[i], hashMap3);
            i++;
            hashMap2 = hashMap3;
        }
        hashMap2.put(split[split.length - 1], obj);
        return hashMap;
    }

    @VisibleForTesting
    private final void zzb(Map<String, Object> map, Map<String, Object> map2) {
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (obj instanceof List) {
                if (!(map2.get(next) instanceof List)) {
                    map2.put(next, new ArrayList());
                }
                zza((List<Object>) (List) obj, (List<Object>) (List) map2.get(next));
            } else if (obj instanceof Map) {
                if (!(map2.get(next) instanceof Map)) {
                    map2.put(next, new HashMap());
                }
                zzb((Map) obj, (Map) map2.get(next));
            } else {
                map2.put(next, obj);
            }
        }
    }

    @VisibleForTesting
    private final void zza(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add((Object) null);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                Object obj = list.get(i2);
                if (obj instanceof List) {
                    if (!(list2.get(i2) instanceof List)) {
                        list2.set(i2, new ArrayList());
                    }
                    zza((List<Object>) (List) obj, (List<Object>) (List) list2.get(i2));
                } else if (obj instanceof Map) {
                    if (!(list2.get(i2) instanceof Map)) {
                        list2.set(i2, new HashMap());
                    }
                    zzb((Map) obj, (Map) list2.get(i2));
                } else if (obj != OBJECT_NOT_PRESENT) {
                    list2.set(i2, obj);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }
}
