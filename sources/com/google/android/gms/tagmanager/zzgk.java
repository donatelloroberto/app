package com.google.android.gms.tagmanager;

import android.content.Context;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zza;
import com.google.android.gms.internal.gtm.zzb;
import com.google.android.gms.internal.gtm.zzl;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShowFirstParty
@VisibleForTesting
public final class zzgk extends zzgh {
    private static final String ID = zza.UNIVERSAL_ANALYTICS.toString();
    private static final String zzalx = zzb.ACCOUNT.toString();
    private static final String zzaly = zzb.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzalz = zzb.ENABLE_ECOMMERCE.toString();
    private static final String zzama = zzb.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzamb = zzb.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzamc = zzb.ANALYTICS_FIELDS.toString();
    private static final String zzamd = zzb.TRACK_TRANSACTION.toString();
    private static final String zzame = zzb.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzamf = zzb.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzamg = Arrays.asList(new String[]{ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND});
    private static final Pattern zzamh = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzami = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzamj;
    private static Map<String, String> zzamk;
    private final DataLayer zzaed;
    private final Set<String> zzaml;
    private final zzgf zzamm;

    public zzgk(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzgf(context));
    }

    @VisibleForTesting
    private zzgk(Context context, DataLayer dataLayer, zzgf zzgf) {
        super(ID, new String[0]);
        this.zzaed = dataLayer;
        this.zzamm = zzgf;
        this.zzaml = new HashSet();
        this.zzaml.add("");
        this.zzaml.add("0");
        this.zzaml.add("false");
    }

    private static boolean zzc(Map<String, zzl> map, String str) {
        zzl zzl = map.get(str);
        if (zzl == null) {
            return false;
        }
        return zzgj.zzg(zzl).booleanValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x0182  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(java.util.Map<java.lang.String, com.google.android.gms.internal.gtm.zzl> r13) {
        /*
            r12 = this;
            r6 = 0
            r4 = 0
            com.google.android.gms.tagmanager.zzgf r2 = r12.zzamm
            java.lang.String r3 = "_GTM_DEFAULT_TRACKER_"
            com.google.android.gms.analytics.Tracker r7 = r2.zzbm(r3)
            java.lang.String r2 = "collect_adid"
            boolean r2 = zzc(r13, r2)
            r7.enableAdvertisingIdCollection(r2)
            java.lang.String r2 = zzalz
            boolean r2 = zzc(r13, r2)
            if (r2 == 0) goto L_0x02c8
            com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder r8 = new com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder
            r8.<init>()
            java.lang.String r2 = zzamc
            java.lang.Object r2 = r13.get(r2)
            com.google.android.gms.internal.gtm.zzl r2 = (com.google.android.gms.internal.gtm.zzl) r2
            java.util.Map r3 = r12.zzj(r2)
            r8.setAll(r3)
            java.lang.String r2 = zzama
            boolean r2 = zzc(r13, r2)
            if (r2 == 0) goto L_0x00a6
            com.google.android.gms.tagmanager.DataLayer r2 = r12.zzaed
            java.lang.String r5 = "ecommerce"
            java.lang.Object r2 = r2.get(r5)
            boolean r5 = r2 instanceof java.util.Map
            if (r5 == 0) goto L_0x0491
            java.util.Map r2 = (java.util.Map) r2
        L_0x0045:
            r5 = r2
        L_0x0046:
            if (r5 == 0) goto L_0x029a
            java.lang.String r2 = "&cu"
            java.lang.Object r2 = r3.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L_0x005a
            java.lang.String r2 = "currencyCode"
            java.lang.Object r2 = r5.get(r2)
            java.lang.String r2 = (java.lang.String) r2
        L_0x005a:
            if (r2 == 0) goto L_0x0061
            java.lang.String r3 = "&cu"
            r8.set(r3, r2)
        L_0x0061:
            java.lang.String r2 = "impressions"
            java.lang.Object r2 = r5.get(r2)
            boolean r3 = r2 instanceof java.util.List
            if (r3 == 0) goto L_0x00c0
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r3 = r2.iterator()
        L_0x0071:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x00c0
            java.lang.Object r2 = r3.next()
            java.util.Map r2 = (java.util.Map) r2
            com.google.android.gms.analytics.ecommerce.Product r9 = zzf(r2)     // Catch:{ RuntimeException -> 0x008d }
            java.lang.String r10 = "list"
            java.lang.Object r2 = r2.get(r10)     // Catch:{ RuntimeException -> 0x008d }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ RuntimeException -> 0x008d }
            r8.addImpression(r9, r2)     // Catch:{ RuntimeException -> 0x008d }
            goto L_0x0071
        L_0x008d:
            r2 = move-exception
            java.lang.String r9 = "Failed to extract a product from DataLayer. "
            java.lang.String r2 = r2.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r10 = r2.length()
            if (r10 == 0) goto L_0x00ba
            java.lang.String r2 = r9.concat(r2)
        L_0x00a2:
            com.google.android.gms.tagmanager.zzdi.zzav(r2)
            goto L_0x0071
        L_0x00a6:
            java.lang.String r2 = zzamb
            java.lang.Object r2 = r13.get(r2)
            com.google.android.gms.internal.gtm.zzl r2 = (com.google.android.gms.internal.gtm.zzl) r2
            java.lang.Object r2 = com.google.android.gms.tagmanager.zzgj.zzh((com.google.android.gms.internal.gtm.zzl) r2)
            boolean r5 = r2 instanceof java.util.Map
            if (r5 == 0) goto L_0x048e
            java.util.Map r2 = (java.util.Map) r2
            r5 = r2
            goto L_0x0046
        L_0x00ba:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r9)
            goto L_0x00a2
        L_0x00c0:
            java.lang.String r2 = "promoClick"
            boolean r2 = r5.containsKey(r2)
            if (r2 == 0) goto L_0x0151
            java.lang.String r2 = "promoClick"
            java.lang.Object r2 = r5.get(r2)
            java.util.Map r2 = (java.util.Map) r2
            java.lang.String r3 = "promotions"
            java.lang.Object r2 = r2.get(r3)
            java.util.List r2 = (java.util.List) r2
        L_0x00d8:
            r4 = 1
            if (r2 == 0) goto L_0x01e2
            java.util.Iterator r9 = r2.iterator()
        L_0x00df:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x0171
            java.lang.Object r2 = r9.next()
            java.util.Map r2 = (java.util.Map) r2
            com.google.android.gms.analytics.ecommerce.Promotion r10 = new com.google.android.gms.analytics.ecommerce.Promotion     // Catch:{ RuntimeException -> 0x0138 }
            r10.<init>()     // Catch:{ RuntimeException -> 0x0138 }
            java.lang.String r3 = "id"
            java.lang.Object r3 = r2.get(r3)     // Catch:{ RuntimeException -> 0x0138 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ RuntimeException -> 0x0138 }
            if (r3 == 0) goto L_0x0101
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0138 }
            r10.setId(r3)     // Catch:{ RuntimeException -> 0x0138 }
        L_0x0101:
            java.lang.String r3 = "name"
            java.lang.Object r3 = r2.get(r3)     // Catch:{ RuntimeException -> 0x0138 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ RuntimeException -> 0x0138 }
            if (r3 == 0) goto L_0x0112
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0138 }
            r10.setName(r3)     // Catch:{ RuntimeException -> 0x0138 }
        L_0x0112:
            java.lang.String r3 = "creative"
            java.lang.Object r3 = r2.get(r3)     // Catch:{ RuntimeException -> 0x0138 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ RuntimeException -> 0x0138 }
            if (r3 == 0) goto L_0x0123
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ RuntimeException -> 0x0138 }
            r10.setCreative(r3)     // Catch:{ RuntimeException -> 0x0138 }
        L_0x0123:
            java.lang.String r3 = "position"
            java.lang.Object r2 = r2.get(r3)     // Catch:{ RuntimeException -> 0x0138 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ RuntimeException -> 0x0138 }
            if (r2 == 0) goto L_0x0134
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ RuntimeException -> 0x0138 }
            r10.setPosition(r2)     // Catch:{ RuntimeException -> 0x0138 }
        L_0x0134:
            r8.addPromotion(r10)     // Catch:{ RuntimeException -> 0x0138 }
            goto L_0x00df
        L_0x0138:
            r2 = move-exception
            java.lang.String r3 = "Failed to extract a promotion from DataLayer. "
            java.lang.String r2 = r2.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r10 = r2.length()
            if (r10 == 0) goto L_0x016b
            java.lang.String r2 = r3.concat(r2)
        L_0x014d:
            com.google.android.gms.tagmanager.zzdi.zzav(r2)
            goto L_0x00df
        L_0x0151:
            java.lang.String r2 = "promoView"
            boolean r2 = r5.containsKey(r2)
            if (r2 == 0) goto L_0x048b
            java.lang.String r2 = "promoView"
            java.lang.Object r2 = r5.get(r2)
            java.util.Map r2 = (java.util.Map) r2
            java.lang.String r3 = "promotions"
            java.lang.Object r2 = r2.get(r3)
            java.util.List r2 = (java.util.List) r2
            goto L_0x00d8
        L_0x016b:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r3)
            goto L_0x014d
        L_0x0171:
            java.lang.String r2 = "promoClick"
            boolean r2 = r5.containsKey(r2)
            if (r2 == 0) goto L_0x01db
            java.lang.String r2 = "&promoa"
            java.lang.String r3 = "click"
            r8.set(r2, r3)
        L_0x0180:
            if (r6 == 0) goto L_0x029a
            java.util.List<java.lang.String> r2 = zzamg
            java.util.Iterator r3 = r2.iterator()
        L_0x0188:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x029a
            java.lang.Object r2 = r3.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r4 = r5.containsKey(r2)
            if (r4 == 0) goto L_0x0188
            java.lang.Object r3 = r5.get(r2)
            java.util.Map r3 = (java.util.Map) r3
            java.lang.String r4 = "products"
            java.lang.Object r4 = r3.get(r4)
            java.util.List r4 = (java.util.List) r4
            if (r4 == 0) goto L_0x01ea
            java.util.Iterator r5 = r4.iterator()
        L_0x01ae:
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x01ea
            java.lang.Object r4 = r5.next()
            java.util.Map r4 = (java.util.Map) r4
            com.google.android.gms.analytics.ecommerce.Product r4 = zzf(r4)     // Catch:{ RuntimeException -> 0x01c2 }
            r8.addProduct(r4)     // Catch:{ RuntimeException -> 0x01c2 }
            goto L_0x01ae
        L_0x01c2:
            r4 = move-exception
            java.lang.String r6 = "Failed to extract a product from DataLayer. "
            java.lang.String r4 = r4.getMessage()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r9 = r4.length()
            if (r9 == 0) goto L_0x01e4
            java.lang.String r4 = r6.concat(r4)
        L_0x01d7:
            com.google.android.gms.tagmanager.zzdi.zzav(r4)
            goto L_0x01ae
        L_0x01db:
            java.lang.String r2 = "&promoa"
            java.lang.String r3 = "view"
            r8.set(r2, r3)
        L_0x01e2:
            r6 = r4
            goto L_0x0180
        L_0x01e4:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r6)
            goto L_0x01d7
        L_0x01ea:
            java.lang.String r4 = "actionField"
            boolean r4 = r3.containsKey(r4)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r4 == 0) goto L_0x02a2
            java.lang.String r4 = "actionField"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ RuntimeException -> 0x02a9 }
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ RuntimeException -> 0x02a9 }
            com.google.android.gms.analytics.ecommerce.ProductAction r4 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x02a9 }
            r4.<init>(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            java.lang.String r2 = "id"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x020e
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setTransactionId(r2)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x020e:
            java.lang.String r2 = "affiliation"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x021d
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setTransactionAffiliation(r2)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x021d:
            java.lang.String r2 = "coupon"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x022c
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setTransactionCouponCode(r2)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x022c:
            java.lang.String r2 = "list"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x023b
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setProductActionList(r2)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x023b:
            java.lang.String r2 = "option"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x024a
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setCheckoutOptions(r2)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x024a:
            java.lang.String r2 = "revenue"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x025d
            java.lang.Double r2 = zzm(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            double r10 = r2.doubleValue()     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setTransactionRevenue(r10)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x025d:
            java.lang.String r2 = "tax"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x0270
            java.lang.Double r2 = zzm(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            double r10 = r2.doubleValue()     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setTransactionTax(r10)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x0270:
            java.lang.String r2 = "shipping"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x0283
            java.lang.Double r2 = zzm(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            double r10 = r2.doubleValue()     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setTransactionShipping(r10)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x0283:
            java.lang.String r2 = "step"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            if (r2 == 0) goto L_0x0296
            java.lang.Integer r2 = zzn(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            int r2 = r2.intValue()     // Catch:{ RuntimeException -> 0x02a9 }
            r4.setCheckoutStep(r2)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x0296:
            r2 = r4
        L_0x0297:
            r8.setProductAction(r2)     // Catch:{ RuntimeException -> 0x02a9 }
        L_0x029a:
            java.util.Map r2 = r8.build()
            r7.send(r2)
        L_0x02a1:
            return
        L_0x02a2:
            com.google.android.gms.analytics.ecommerce.ProductAction r3 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x02a9 }
            r3.<init>(r2)     // Catch:{ RuntimeException -> 0x02a9 }
            r2 = r3
            goto L_0x0297
        L_0x02a9:
            r2 = move-exception
            java.lang.String r3 = "Failed to extract a product action from DataLayer. "
            java.lang.String r2 = r2.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r4 = r2.length()
            if (r4 == 0) goto L_0x02c2
            java.lang.String r2 = r3.concat(r2)
        L_0x02be:
            com.google.android.gms.tagmanager.zzdi.zzav(r2)
            goto L_0x029a
        L_0x02c2:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r3)
            goto L_0x02be
        L_0x02c8:
            java.lang.String r2 = zzaly
            boolean r2 = zzc(r13, r2)
            if (r2 == 0) goto L_0x02e0
            java.lang.String r2 = zzamc
            java.lang.Object r2 = r13.get(r2)
            com.google.android.gms.internal.gtm.zzl r2 = (com.google.android.gms.internal.gtm.zzl) r2
            java.util.Map r2 = r12.zzj(r2)
            r7.send(r2)
            goto L_0x02a1
        L_0x02e0:
            java.lang.String r2 = zzamd
            boolean r2 = zzc(r13, r2)
            if (r2 == 0) goto L_0x0484
            java.lang.String r2 = "transactionId"
            java.lang.String r8 = r12.zzbr(r2)
            if (r8 != 0) goto L_0x02f6
            java.lang.String r2 = "Cannot find transactionId in data layer."
            com.google.android.gms.tagmanager.zzdi.zzav(r2)
            goto L_0x02a1
        L_0x02f6:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.String r2 = zzamc     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r2 = r13.get(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            com.google.android.gms.internal.gtm.zzl r2 = (com.google.android.gms.internal.gtm.zzl) r2     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.util.Map r9 = r12.zzj(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = "&t"
            java.lang.String r3 = "transaction"
            r9.put(r2, r3)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = zzame     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r2 = r13.get(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            com.google.android.gms.internal.gtm.zzl r2 = (com.google.android.gms.internal.gtm.zzl) r2     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 == 0) goto L_0x034c
            java.util.Map r2 = zzi(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x031c:
            java.util.Set r2 = r2.entrySet()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.util.Iterator r10 = r2.iterator()     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x0324:
            boolean r2 = r10.hasNext()     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 == 0) goto L_0x0384
            java.lang.Object r2 = r10.next()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r3 = r2.getValue()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r2 = r2.getKey()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = r12.zzbr(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            zzd(r9, r3, r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            goto L_0x0324
        L_0x0344:
            r2 = move-exception
            java.lang.String r3 = "Unable to send transaction"
            com.google.android.gms.tagmanager.zzdi.zza(r3, r2)
            goto L_0x02a1
        L_0x034c:
            java.util.Map<java.lang.String, java.lang.String> r2 = zzamj     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 != 0) goto L_0x0381
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ IllegalArgumentException -> 0x0344 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "transactionId"
            java.lang.String r10 = "&ti"
            r2.put(r3, r10)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "transactionAffiliation"
            java.lang.String r10 = "&ta"
            r2.put(r3, r10)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "transactionTax"
            java.lang.String r10 = "&tt"
            r2.put(r3, r10)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "transactionShipping"
            java.lang.String r10 = "&ts"
            r2.put(r3, r10)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "transactionTotal"
            java.lang.String r10 = "&tr"
            r2.put(r3, r10)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "transactionCurrency"
            java.lang.String r10 = "&cu"
            r2.put(r3, r10)     // Catch:{ IllegalArgumentException -> 0x0344 }
            zzamj = r2     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x0381:
            java.util.Map<java.lang.String, java.lang.String> r2 = zzamj     // Catch:{ IllegalArgumentException -> 0x0344 }
            goto L_0x031c
        L_0x0384:
            r5.add(r9)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = "transactionProducts"
            com.google.android.gms.tagmanager.DataLayer r3 = r12.zzaed     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r3 != 0) goto L_0x03b4
        L_0x0391:
            if (r4 == 0) goto L_0x046d
            java.util.Iterator r9 = r4.iterator()     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x0397:
            boolean r2 = r9.hasNext()     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 == 0) goto L_0x046d
            java.lang.Object r2 = r9.next()     // Catch:{ IllegalArgumentException -> 0x0344 }
            r0 = r2
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ IllegalArgumentException -> 0x0344 }
            r4 = r0
            java.lang.String r2 = "name"
            java.lang.Object r2 = r4.get(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 != 0) goto L_0x03e2
            java.lang.String r2 = "Unable to send transaction item hit due to missing 'name' field."
            com.google.android.gms.tagmanager.zzdi.zzav(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            goto L_0x02a1
        L_0x03b4:
            boolean r2 = r3 instanceof java.util.List     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 != 0) goto L_0x03c0
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "transactionProducts should be of type List."
            r2.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x0344 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x03c0:
            r0 = r3
            java.util.List r0 = (java.util.List) r0     // Catch:{ IllegalArgumentException -> 0x0344 }
            r2 = r0
            java.util.Iterator r2 = r2.iterator()     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x03c8:
            boolean r4 = r2.hasNext()     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r4 == 0) goto L_0x03de
            java.lang.Object r4 = r2.next()     // Catch:{ IllegalArgumentException -> 0x0344 }
            boolean r4 = r4 instanceof java.util.Map     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r4 != 0) goto L_0x03c8
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "Each element of transactionProducts should be of type Map."
            r2.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x0344 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x03de:
            java.util.List r3 = (java.util.List) r3     // Catch:{ IllegalArgumentException -> 0x0344 }
            r4 = r3
            goto L_0x0391
        L_0x03e2:
            java.lang.String r2 = zzamc     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r2 = r13.get(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            com.google.android.gms.internal.gtm.zzl r2 = (com.google.android.gms.internal.gtm.zzl) r2     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.util.Map r10 = r12.zzj(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = "&t"
            java.lang.String r3 = "item"
            r10.put(r2, r3)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = "&ti"
            r10.put(r2, r8)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = zzamf     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r2 = r13.get(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            com.google.android.gms.internal.gtm.zzl r2 = (com.google.android.gms.internal.gtm.zzl) r2     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 == 0) goto L_0x0430
            java.util.Map r2 = zzi(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x0408:
            java.util.Set r2 = r2.entrySet()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.util.Iterator r11 = r2.iterator()     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x0410:
            boolean r2 = r11.hasNext()     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 == 0) goto L_0x0468
            java.lang.Object r2 = r11.next()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r3 = r2.getValue()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r2 = r2.getKey()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.Object r2 = r4.get(r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IllegalArgumentException -> 0x0344 }
            zzd(r10, r3, r2)     // Catch:{ IllegalArgumentException -> 0x0344 }
            goto L_0x0410
        L_0x0430:
            java.util.Map<java.lang.String, java.lang.String> r2 = zzamk     // Catch:{ IllegalArgumentException -> 0x0344 }
            if (r2 != 0) goto L_0x0465
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ IllegalArgumentException -> 0x0344 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "name"
            java.lang.String r11 = "&in"
            r2.put(r3, r11)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "sku"
            java.lang.String r11 = "&ic"
            r2.put(r3, r11)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "category"
            java.lang.String r11 = "&iv"
            r2.put(r3, r11)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "price"
            java.lang.String r11 = "&ip"
            r2.put(r3, r11)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "quantity"
            java.lang.String r11 = "&iq"
            r2.put(r3, r11)     // Catch:{ IllegalArgumentException -> 0x0344 }
            java.lang.String r3 = "currency"
            java.lang.String r11 = "&cu"
            r2.put(r3, r11)     // Catch:{ IllegalArgumentException -> 0x0344 }
            zzamk = r2     // Catch:{ IllegalArgumentException -> 0x0344 }
        L_0x0465:
            java.util.Map<java.lang.String, java.lang.String> r2 = zzamk     // Catch:{ IllegalArgumentException -> 0x0344 }
            goto L_0x0408
        L_0x0468:
            r5.add(r10)     // Catch:{ IllegalArgumentException -> 0x0344 }
            goto L_0x0397
        L_0x046d:
            r0 = r5
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ IllegalArgumentException -> 0x0344 }
            r2 = r0
            int r5 = r2.size()     // Catch:{ IllegalArgumentException -> 0x0344 }
            r4 = r6
        L_0x0476:
            if (r4 >= r5) goto L_0x02a1
            java.lang.Object r3 = r2.get(r4)     // Catch:{ IllegalArgumentException -> 0x0344 }
            int r4 = r4 + 1
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ IllegalArgumentException -> 0x0344 }
            r7.send(r3)     // Catch:{ IllegalArgumentException -> 0x0344 }
            goto L_0x0476
        L_0x0484:
            java.lang.String r2 = "Ignoring unknown tag."
            com.google.android.gms.tagmanager.zzdi.zzac(r2)
            goto L_0x02a1
        L_0x048b:
            r2 = r4
            goto L_0x00d8
        L_0x048e:
            r5 = r4
            goto L_0x0046
        L_0x0491:
            r2 = r4
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzgk.zzd(java.util.Map):void");
    }

    private final String zzbr(String str) {
        Object obj = this.zzaed.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private static void zzd(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private static Product zzf(Map<String, Object> map) {
        Product product = new Product();
        Object obj = map.get(PushConstants.CHANNEL_ID);
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get("name");
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get("category");
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get(FirebaseAnalytics.Param.COUPON);
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get("position");
        if (obj7 != null) {
            product.setPosition(zzn(obj7).intValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.Param.PRICE);
        if (obj8 != null) {
            product.setPrice(zzm(obj8).doubleValue());
        }
        Object obj9 = map.get(FirebaseAnalytics.Param.QUANTITY);
        if (obj9 != null) {
            product.setQuantity(zzn(obj9).intValue());
        }
        for (String next : map.keySet()) {
            Matcher matcher = zzamh.matcher(next);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(next)));
                } catch (NumberFormatException e) {
                    String valueOf = String.valueOf(next);
                    zzdi.zzac(valueOf.length() != 0 ? "illegal number in custom dimension value: ".concat(valueOf) : new String("illegal number in custom dimension value: "));
                }
            } else {
                Matcher matcher2 = zzami.matcher(next);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzn(map.get(next)).intValue());
                    } catch (NumberFormatException e2) {
                        String valueOf2 = String.valueOf(next);
                        zzdi.zzac(valueOf2.length() != 0 ? "illegal number in custom metric value: ".concat(valueOf2) : new String("illegal number in custom metric value: "));
                    }
                }
            }
        }
        return product;
    }

    private static Map<String, String> zzi(zzl zzl) {
        Object zzh = zzgj.zzh(zzl);
        if (!(zzh instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) zzh).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private final Map<String, String> zzj(zzl zzl) {
        if (zzl == null) {
            return new HashMap();
        }
        Map<String, String> zzi = zzi(zzl);
        if (zzi == null) {
            return new HashMap();
        }
        String str = zzi.get("&aip");
        if (str != null && this.zzaml.contains(str.toLowerCase())) {
            zzi.remove("&aip");
        }
        return zzi;
    }

    private static Double zzm(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf) : new String("Cannot convert the object to Double: "));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf2) : new String("Cannot convert the object to Double: "));
        }
    }

    private static Integer zzn(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf) : new String("Cannot convert the object to Integer: "));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf2) : new String("Cannot convert the object to Integer: "));
        }
    }

    public final /* bridge */ /* synthetic */ zzl zzb(Map map) {
        return super.zzb(map);
    }

    public final /* bridge */ /* synthetic */ boolean zzgw() {
        return super.zzgw();
    }

    public final /* bridge */ /* synthetic */ Set zzig() {
        return super.zzig();
    }

    public final /* bridge */ /* synthetic */ String zzif() {
        return super.zzif();
    }
}
