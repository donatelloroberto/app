package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public final class zzdy {
    private static final String[] zzabl = {"Z", "+hh", "+hhmm", "+hh:mm"};
    private static final String zzabm;
    private static final zzdo zzbf = new zzdo("MetadataUtils");

    public static void zza(List<WebImage> list, JSONArray jSONArray) {
        try {
            list.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    list.add(new WebImage(jSONArray.getJSONObject(i)));
                } catch (IllegalArgumentException e) {
                }
            }
        } catch (JSONException e2) {
        }
    }

    public static JSONArray zzg(List<WebImage> list) {
        if (list == null && list.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (WebImage json : list) {
            jSONArray.put(json.toJson());
        }
        return jSONArray;
    }

    public static String zza(Calendar calendar) {
        if (calendar == null) {
            zzbf.d("Calendar object cannot be null", new Object[0]);
            return null;
        }
        String str = zzabm;
        if (calendar.get(11) == 0 && calendar.get(12) == 0 && calendar.get(13) == 0) {
            str = "yyyyMMdd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        String format = simpleDateFormat.format(calendar.getTime());
        if (format.endsWith("+0000")) {
            return format.replace("+0000", zzabl[0]);
        }
        return format;
    }

    public static Calendar zzx(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbf.d("Input string is empty or null", new Object[0]);
            return null;
        }
        String zzy = zzy(str);
        if (TextUtils.isEmpty(zzy)) {
            zzbf.d("Invalid date format", new Object[0]);
            return null;
        }
        String zzz = zzz(str);
        String str2 = "yyyyMMdd";
        if (!TextUtils.isEmpty(zzz)) {
            String valueOf = String.valueOf(zzy);
            zzy = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(zzz).length()).append(valueOf).append("T").append(zzz).toString();
            if (zzz.length() == 6) {
                str2 = "yyyyMMdd'T'HHmmss";
            } else {
                str2 = zzabm;
            }
        }
        Calendar instance = GregorianCalendar.getInstance();
        try {
            instance.setTime(new SimpleDateFormat(str2).parse(zzy));
            return instance;
        } catch (ParseException e) {
            zzbf.d("Error parsing string: %s", e.getMessage());
            return null;
        }
    }

    private static String zzy(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbf.d("Input string is empty or null", new Object[0]);
            return null;
        }
        try {
            return str.substring(0, 8);
        } catch (IndexOutOfBoundsException e) {
            zzbf.i("Error extracting the date: %s", e.getMessage());
            return null;
        }
    }

    private static String zzz(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            zzbf.d("string is empty or null", new Object[0]);
            return null;
        }
        int indexOf = str.indexOf(84);
        int i = indexOf + 1;
        if (indexOf != 8) {
            zzbf.d("T delimeter is not found", new Object[0]);
            return null;
        }
        try {
            String substring = str.substring(i);
            if (substring.length() == 6) {
                return substring;
            }
            switch (substring.charAt(6)) {
                case '+':
                case '-':
                    int length = substring.length();
                    if (length == zzabl[1].length() + 6 || length == zzabl[2].length() + 6 || length == zzabl[3].length() + 6) {
                        z = true;
                    }
                    if (z) {
                        return substring.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
                    }
                    return null;
                case 'Z':
                    if (substring.length() != zzabl[0].length() + 6) {
                        return null;
                    }
                    String valueOf = String.valueOf(substring.substring(0, substring.length() - 1));
                    String valueOf2 = String.valueOf("+0000");
                    return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                default:
                    return null;
            }
        } catch (IndexOutOfBoundsException e) {
            zzbf.d("Error extracting the time substring: %s", e.getMessage());
            return null;
        }
    }

    static {
        String valueOf = String.valueOf("yyyyMMdd'T'HHmmss");
        String valueOf2 = String.valueOf(zzabl[0]);
        zzabm = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }
}
