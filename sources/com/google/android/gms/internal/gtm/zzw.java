package com.google.android.gms.internal.gtm;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ShowFirstParty
public final class zzw extends zzi<zzw> {
    private ProductAction zzrx;
    private final Map<String, List<Product>> zzry = new HashMap();
    private final List<Promotion> zzrz = new ArrayList();
    private final List<Product> zzsa = new ArrayList();

    public final ProductAction zzbn() {
        return this.zzrx;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zzsa.isEmpty()) {
            hashMap.put("products", this.zzsa);
        }
        if (!this.zzrz.isEmpty()) {
            hashMap.put("promotions", this.zzrz);
        }
        if (!this.zzry.isEmpty()) {
            hashMap.put("impressions", this.zzry);
        }
        hashMap.put("productAction", this.zzrx);
        return zza((Object) hashMap);
    }

    public final List<Product> zzbo() {
        return Collections.unmodifiableList(this.zzsa);
    }

    public final Map<String, List<Product>> zzbp() {
        return this.zzry;
    }

    public final List<Promotion> zzbq() {
        return Collections.unmodifiableList(this.zzrz);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        String str;
        zzw zzw = (zzw) zzi;
        zzw.zzsa.addAll(this.zzsa);
        zzw.zzrz.addAll(this.zzrz);
        for (Map.Entry next : this.zzry.entrySet()) {
            String str2 = (String) next.getKey();
            for (Product product : (List) next.getValue()) {
                if (product != null) {
                    if (str2 == null) {
                        str = "";
                    } else {
                        str = str2;
                    }
                    if (!zzw.zzry.containsKey(str)) {
                        zzw.zzry.put(str, new ArrayList());
                    }
                    zzw.zzry.get(str).add(product);
                }
            }
        }
        if (this.zzrx != null) {
            zzw.zzrx = this.zzrx;
        }
    }
}
