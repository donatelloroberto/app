package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;

@zzadh
public final class zzzy extends zzaal {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzbgp;
    private String zzbvs = zzbu(PushConstants.CHANNEL_DESCRIPTION);
    private long zzbvt = zzbv("start_ticks");
    private long zzbvu = zzbv("end_ticks");
    private String zzbvv = zzbu("summary");
    private String zzbvw = zzbu(FirebaseAnalytics.Param.LOCATION);

    public zzzy(zzaqw zzaqw, Map<String, String> map) {
        super(zzaqw, "createCalendarEvent");
        this.zzbgp = map;
        this.mContext = zzaqw.zzto();
    }

    private final String zzbu(String str) {
        return TextUtils.isEmpty(this.zzbgp.get(str)) ? "" : this.zzbgp.get(str);
    }

    private final long zzbv(String str) {
        String str2 = this.zzbgp.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    public final Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra(PushConstants.TITLE, this.zzbvs);
        data.putExtra("eventLocation", this.zzbvw);
        data.putExtra(PushConstants.CHANNEL_DESCRIPTION, this.zzbvv);
        if (this.zzbvt > -1) {
            data.putExtra("beginTime", this.zzbvt);
        }
        if (this.zzbvu > -1) {
            data.putExtra("endTime", this.zzbvu);
        }
        data.setFlags(268435456);
        return data;
    }

    public final void execute() {
        if (this.mContext == null) {
            zzbw("Activity context is not available.");
            return;
        }
        zzbv.zzek();
        if (!zzakk.zzao(this.mContext).zziz()) {
            zzbw("This feature is not available on the device.");
            return;
        }
        zzbv.zzek();
        AlertDialog.Builder zzan = zzakk.zzan(this.mContext);
        Resources resources = zzbv.zzeo().getResources();
        zzan.setTitle(resources != null ? resources.getString(R.string.s5) : "Create calendar event");
        zzan.setMessage(resources != null ? resources.getString(R.string.s6) : "Allow Ad to create a calendar event?");
        zzan.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzzz(this));
        zzan.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzaaa(this));
        zzan.create().show();
    }
}
