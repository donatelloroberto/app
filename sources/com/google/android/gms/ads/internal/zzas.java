package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzasd;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzoj;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzpx;
import com.google.android.gms.internal.ads.zzxe;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzas {
    @VisibleForTesting
    static zzv<zzaqw> zza(@Nullable zzxz zzxz, @Nullable zzyc zzyc, zzac zzac) {
        return new zzax(zzxz, zzac, zzyc);
    }

    private static String zza(@Nullable Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzakb.zzdk("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        String valueOf2 = String.valueOf(encodeToString);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    @VisibleForTesting
    private static String zza(@Nullable zzpw zzpw) {
        if (zzpw == null) {
            zzakb.zzdk("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzpw.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzakb.zzdk("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzpw);
    }

    private static JSONObject zza(@Nullable Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (bundle.containsKey(next)) {
                if (PushConstants.IMAGE.equals(jSONObject2.getString(next))) {
                    Object obj = bundle.get(next);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(next, zza((Bitmap) obj));
                    } else {
                        zzakb.zzdk("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(next) instanceof Bitmap) {
                    zzakb.zzdk("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(next, String.valueOf(bundle.get(next)));
                }
            }
        }
        return jSONObject;
    }

    static final /* synthetic */ void zza(zzoo zzoo, String str, zzaqw zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzoo.getHeadline());
            jSONObject.put(PushConstants.BODY, zzoo.getBody());
            jSONObject.put("call_to_action", zzoo.getCallToAction());
            jSONObject.put(FirebaseAnalytics.Param.PRICE, zzoo.getPrice());
            jSONObject.put("star_rating", String.valueOf(zzoo.getStarRating()));
            jSONObject.put("store", zzoo.getStore());
            jSONObject.put(PushConstants.ICON, zza(zzoo.zzjz()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoo.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzoo.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "2");
            zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzakb.zzc("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void zza(zzoq zzoq, String str, zzaqw zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzoq.getHeadline());
            jSONObject.put(PushConstants.BODY, zzoq.getBody());
            jSONObject.put("call_to_action", zzoq.getCallToAction());
            jSONObject.put("advertiser", zzoq.getAdvertiser());
            jSONObject.put("logo", zza(zzoq.zzkg()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoq.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzoq.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "1");
            zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzakb.zzc("Exception occurred when loading assets", e);
        }
    }

    public static boolean zza(zzaqw zzaqw, zzxe zzxe, CountDownLatch countDownLatch) {
        boolean z;
        try {
            View view = zzaqw.getView();
            if (view == null) {
                zzakb.zzdk("AdWebView is null");
                z = false;
            } else {
                view.setVisibility(4);
                List<String> list = zzxe.zzbtw.zzbsi;
                if (list == null || list.isEmpty()) {
                    zzakb.zzdk("No template ids present in mediation response");
                    z = false;
                } else {
                    zzaqw.zza("/nativeExpressAssetsLoaded", (zzv<? super zzaqw>) new zzav(countDownLatch));
                    zzaqw.zza("/nativeExpressAssetsLoadingFailed", (zzv<? super zzaqw>) new zzaw(countDownLatch));
                    zzxz zzmo = zzxe.zzbtx.zzmo();
                    zzyc zzmp = zzxe.zzbtx.zzmp();
                    if (list.contains("2") && zzmo != null) {
                        zzaqw.zzuf().zza((zzasd) new zzat(new zzoo(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz(), zzmo.getCallToAction(), zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), (zzoj) null, zzmo.getExtras(), (zzlo) null, zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), (String) null), zzxe.zzbtw.zzbsh, zzaqw));
                    } else if (!list.contains("1") || zzmp == null) {
                        zzakb.zzdk("No matching template id and mapper");
                        z = false;
                    } else {
                        zzaqw.zzuf().zza((zzasd) new zzau(new zzoq(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg(), zzmp.getCallToAction(), zzmp.getAdvertiser(), (zzoj) null, zzmp.getExtras(), (zzlo) null, zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), (String) null), zzxe.zzbtw.zzbsh, zzaqw));
                    }
                    String str = zzxe.zzbtw.zzbsf;
                    String str2 = zzxe.zzbtw.zzbsg;
                    if (str2 != null) {
                        zzaqw.loadDataWithBaseURL(str2, str, WebRequest.CONTENT_TYPE_HTML, WebRequest.CHARSET_UTF_8, (String) null);
                    } else {
                        zzaqw.loadData(str, WebRequest.CONTENT_TYPE_HTML, WebRequest.CHARSET_UTF_8);
                    }
                    z = true;
                }
            }
        } catch (RemoteException e) {
            zzakb.zzc("Unable to invoke load assets", e);
            z = false;
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    private static String zzb(zzpw zzpw) {
        try {
            IObjectWrapper zzjy = zzpw.zzjy();
            if (zzjy == null) {
                zzakb.zzdk("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzjy);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzakb.zzdk("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException e) {
            zzakb.zzdk("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    @Nullable
    private static zzpw zzd(Object obj) {
        if (obj instanceof IBinder) {
            return zzpx.zzh((IBinder) obj);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void zzd(zzaqw zzaqw) {
        View.OnClickListener onClickListener = zzaqw.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(zzaqw.getView());
        }
    }

    @Nullable
    public static View zze(@Nullable zzajh zzajh) {
        if (zzajh == null) {
            zzakb.e("AdState is null");
            return null;
        } else if (zzf(zzajh) && zzajh.zzbyo != null) {
            return zzajh.zzbyo.getView();
        } else {
            try {
                IObjectWrapper view = zzajh.zzbtx != null ? zzajh.zzbtx.getView() : null;
                if (view != null) {
                    return (View) ObjectWrapper.unwrap(view);
                }
                zzakb.zzdk("View in mediation adapter is null.");
                return null;
            } catch (RemoteException e) {
                zzakb.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzf(@Nullable zzajh zzajh) {
        return (zzajh == null || !zzajh.zzceq || zzajh.zzbtw == null || zzajh.zzbtw.zzbsf == null) ? false : true;
    }
}
