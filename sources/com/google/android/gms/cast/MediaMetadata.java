package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.adobe.phonegap.push.PushConstants;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.cast.zzdy;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "MediaMetadataCreator")
@SafeParcelable.Reserved({1})
public class MediaMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaMetadata> CREATOR = new zzam();
    public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
    public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
    public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
    public static final String KEY_BOOK_TITLE = "com.google.android.gms.cast.metadata.BOOK_TITLE";
    public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
    public static final String KEY_CHAPTER_NUMBER = "com.google.android.gms.cast.metadata.CHAPTER_NUMBER";
    public static final String KEY_CHAPTER_TITLE = "com.google.android.gms.cast.metadata.CHAPTER_TITLE";
    public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
    public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
    public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
    public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
    public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
    public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
    public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
    public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
    public static final String KEY_QUEUE_ITEM_ID = "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID";
    public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
    public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
    public static final String KEY_SECTION_DURATION = "com.google.android.gms.cast.metadata.SECTION_DURATION";
    public static final String KEY_SECTION_START_ABSOLUTE_TIME = "com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME";
    public static final String KEY_SECTION_START_TIME_IN_CONTAINER = "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER";
    public static final String KEY_SECTION_START_TIME_IN_MEDIA = "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA";
    public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
    public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
    public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
    public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
    public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
    public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
    public static final int MEDIA_TYPE_AUDIOBOOK_CHAPTER = 5;
    public static final int MEDIA_TYPE_GENERIC = 0;
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_USER = 100;
    private static final String[] zzeb = {null, "String", "int", "double", "ISO-8601 date String"};
    private static final zza zzec = new zza().zza(KEY_CREATION_DATE, "creationDateTime", 4).zza(KEY_RELEASE_DATE, "releaseDate", 4).zza(KEY_BROADCAST_DATE, "originalAirdate", 4).zza(KEY_TITLE, PushConstants.TITLE, 1).zza(KEY_SUBTITLE, "subtitle", 1).zza(KEY_ARTIST, "artist", 1).zza(KEY_ALBUM_ARTIST, "albumArtist", 1).zza(KEY_ALBUM_TITLE, "albumName", 1).zza(KEY_COMPOSER, "composer", 1).zza(KEY_DISC_NUMBER, "discNumber", 2).zza(KEY_TRACK_NUMBER, "trackNumber", 2).zza(KEY_SEASON_NUMBER, "season", 2).zza(KEY_EPISODE_NUMBER, "episode", 2).zza(KEY_SERIES_TITLE, "seriesTitle", 1).zza(KEY_STUDIO, "studio", 1).zza(KEY_WIDTH, "width", 2).zza(KEY_HEIGHT, "height", 2).zza(KEY_LOCATION_NAME, FirebaseAnalytics.Param.LOCATION, 1).zza(KEY_LOCATION_LATITUDE, "latitude", 3).zza(KEY_LOCATION_LONGITUDE, "longitude", 3).zza(KEY_SECTION_DURATION, "sectionDuration", 5).zza(KEY_SECTION_START_TIME_IN_MEDIA, "sectionStartTimeInMedia", 5).zza(KEY_SECTION_START_ABSOLUTE_TIME, "sectionStartAbsoluteTime", 5).zza(KEY_SECTION_START_TIME_IN_CONTAINER, "sectionStartTimeInContainer", 5).zza(KEY_QUEUE_ITEM_ID, "queueItemId", 2).zza(KEY_BOOK_TITLE, "bookTitle", 1).zza(KEY_CHAPTER_NUMBER, "chapterNumber", 2).zza(KEY_CHAPTER_TITLE, "chapterTitle", 1);
    @SafeParcelable.Field(getter = "getImages", id = 2)
    private final List<WebImage> zzed;
    @SafeParcelable.Field(id = 3)
    private final Bundle zzee;
    @SafeParcelable.Field(getter = "getMediaType", id = 4)
    private int zzef;

    @SafeParcelable.Constructor
    MediaMetadata(@SafeParcelable.Param(id = 2) List<WebImage> list, @SafeParcelable.Param(id = 3) Bundle bundle, @SafeParcelable.Param(id = 4) int i) {
        this.zzed = list;
        this.zzee = bundle;
        this.zzef = i;
    }

    private static class zza {
        private final Map<String, String> zzeg = new HashMap();
        private final Map<String, String> zzeh = new HashMap();
        private final Map<String, Integer> zzei = new HashMap();

        public final zza zza(String str, String str2, int i) {
            this.zzeg.put(str, str2);
            this.zzeh.put(str2, str);
            this.zzei.put(str, Integer.valueOf(i));
            return this;
        }

        public final String zze(String str) {
            return this.zzeg.get(str);
        }

        public final String zzf(String str) {
            return this.zzeh.get(str);
        }

        public final int zzg(String str) {
            Integer num = this.zzei.get(str);
            if (num != null) {
                return num.intValue();
            }
            return 0;
        }
    }

    public MediaMetadata() {
        this(0);
    }

    public MediaMetadata(int i) {
        this(new ArrayList(), new Bundle(), i);
    }

    public int getMediaType() {
        return this.zzef;
    }

    public void clear() {
        this.zzee.clear();
        this.zzed.clear();
    }

    public boolean containsKey(String str) {
        return this.zzee.containsKey(str);
    }

    public Set<String> keySet() {
        return this.zzee.keySet();
    }

    public void putString(String str, String str2) {
        zza(str, 1);
        this.zzee.putString(str, str2);
    }

    public String getString(String str) {
        zza(str, 1);
        return this.zzee.getString(str);
    }

    public void putInt(String str, int i) {
        zza(str, 2);
        this.zzee.putInt(str, i);
    }

    public int getInt(String str) {
        zza(str, 2);
        return this.zzee.getInt(str);
    }

    public void putDouble(String str, double d) {
        zza(str, 3);
        this.zzee.putDouble(str, d);
    }

    public double getDouble(String str) {
        zza(str, 3);
        return this.zzee.getDouble(str);
    }

    public void putDate(String str, Calendar calendar) {
        zza(str, 4);
        this.zzee.putString(str, zzdy.zza(calendar));
    }

    public Calendar getDate(String str) {
        zza(str, 4);
        String string = this.zzee.getString(str);
        if (string != null) {
            return zzdy.zzx(string);
        }
        return null;
    }

    public String getDateAsString(String str) {
        zza(str, 4);
        return this.zzee.getString(str);
    }

    public long getTimeMillis(String str) {
        zza(str, 5);
        return this.zzee.getLong(str);
    }

    public void putTimeMillis(String str, long j) {
        zza(str, 5);
        this.zzee.putLong(str, j);
    }

    private static void zza(String str, int i) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("null and empty keys are not allowed");
        }
        int zzg = zzec.zzg(str);
        if (zzg != i && zzg != 0) {
            String str2 = zzeb[i];
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length()).append("Value for ").append(str).append(" must be a ").append(str2).toString());
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metadataType", this.zzef);
        } catch (JSONException e) {
        }
        JSONArray zzg = zzdy.zzg(this.zzed);
        if (!(zzg == null || zzg.length() == 0)) {
            try {
                jSONObject.put("images", zzg);
            } catch (JSONException e2) {
            }
        }
        ArrayList arrayList = new ArrayList();
        switch (this.zzef) {
            case 0:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE});
                break;
            case 1:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE});
                break;
            case 2:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE});
                break;
            case 3:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ARTIST, KEY_ALBUM_TITLE, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE});
                break;
            case 4:
                Collections.addAll(arrayList, new String[]{KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE});
                break;
            case 5:
                Collections.addAll(arrayList, new String[]{KEY_CHAPTER_TITLE, KEY_CHAPTER_NUMBER, KEY_TITLE, KEY_BOOK_TITLE, KEY_SUBTITLE});
                break;
        }
        Collections.addAll(arrayList, new String[]{KEY_SECTION_DURATION, KEY_SECTION_START_TIME_IN_MEDIA, KEY_SECTION_START_ABSOLUTE_TIME, KEY_SECTION_START_TIME_IN_CONTAINER, KEY_QUEUE_ITEM_ID});
        try {
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                String str = (String) obj;
                if (this.zzee.containsKey(str)) {
                    switch (zzec.zzg(str)) {
                        case 1:
                        case 4:
                            jSONObject.put(zzec.zze(str), this.zzee.getString(str));
                            break;
                        case 2:
                            jSONObject.put(zzec.zze(str), this.zzee.getInt(str));
                            break;
                        case 3:
                            jSONObject.put(zzec.zze(str), this.zzee.getDouble(str));
                            break;
                        case 5:
                            jSONObject.put(zzec.zze(str), ((double) this.zzee.getLong(str)) / 1000.0d);
                            break;
                    }
                }
            }
            for (String str2 : this.zzee.keySet()) {
                if (!str2.startsWith("com.google.")) {
                    Object obj2 = this.zzee.get(str2);
                    if (obj2 instanceof String) {
                        jSONObject.put(str2, obj2);
                    } else if (obj2 instanceof Integer) {
                        jSONObject.put(str2, obj2);
                    } else if (obj2 instanceof Double) {
                        jSONObject.put(str2, obj2);
                    }
                }
            }
        } catch (JSONException e3) {
        }
        return jSONObject;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzf(org.json.JSONObject r13) {
        /*
            r12 = this;
            r10 = 4
            r9 = 3
            r8 = 2
            r7 = 1
            r6 = 0
            r12.clear()
            r12.zzef = r6
            java.lang.String r2 = "metadataType"
            int r2 = r13.getInt(r2)     // Catch:{ JSONException -> 0x01d5 }
            r12.zzef = r2     // Catch:{ JSONException -> 0x01d5 }
        L_0x0012:
            java.lang.String r2 = "images"
            org.json.JSONArray r2 = r13.optJSONArray(r2)
            if (r2 == 0) goto L_0x001f
            java.util.List<com.google.android.gms.common.images.WebImage> r3 = r12.zzed
            com.google.android.gms.internal.cast.zzdy.zza(r3, r2)
        L_0x001f:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            int r3 = r12.zzef
            switch(r3) {
                case 0: goto L_0x0096;
                case 1: goto L_0x00ad;
                case 2: goto L_0x00c4;
                case 3: goto L_0x00e0;
                case 4: goto L_0x010c;
                case 5: goto L_0x0138;
                default: goto L_0x0029;
            }
        L_0x0029:
            boolean r3 = com.google.android.gms.internal.cast.zzdl.zzaaa
            if (r3 == 0) goto L_0x0042
            java.lang.String[] r3 = new java.lang.String[r10]
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SECTION_DURATION"
            r3[r6] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA"
            r3[r7] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME"
            r3[r8] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER"
            r3[r9] = r4
            java.util.Collections.addAll(r2, r3)
        L_0x0042:
            java.lang.String[] r3 = new java.lang.String[r7]
            java.lang.String r4 = "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID"
            r3[r6] = r4
            java.util.Collections.addAll(r2, r3)
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>(r2)
            java.util.Iterator r5 = r13.keys()     // Catch:{ JSONException -> 0x01b1 }
        L_0x0054:
            boolean r2 = r5.hasNext()     // Catch:{ JSONException -> 0x01b1 }
            if (r2 == 0) goto L_0x01b2
            java.lang.Object r2 = r5.next()     // Catch:{ JSONException -> 0x01b1 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ JSONException -> 0x01b1 }
            java.lang.String r3 = "metadataType"
            boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x01b1 }
            if (r3 != 0) goto L_0x0054
            com.google.android.gms.cast.MediaMetadata$zza r3 = zzec     // Catch:{ JSONException -> 0x01b1 }
            java.lang.String r6 = r3.zzf(r2)     // Catch:{ JSONException -> 0x01b1 }
            if (r6 == 0) goto L_0x01a0
            boolean r3 = r4.contains(r6)     // Catch:{ JSONException -> 0x01b1 }
            if (r3 == 0) goto L_0x0054
            java.lang.Object r3 = r13.get(r2)     // Catch:{ JSONException -> 0x0094 }
            if (r3 == 0) goto L_0x0054
            com.google.android.gms.cast.MediaMetadata$zza r7 = zzec     // Catch:{ JSONException -> 0x0094 }
            int r7 = r7.zzg(r6)     // Catch:{ JSONException -> 0x0094 }
            switch(r7) {
                case 1: goto L_0x0086;
                case 2: goto L_0x016b;
                case 3: goto L_0x017c;
                case 4: goto L_0x0154;
                case 5: goto L_0x018d;
                default: goto L_0x0085;
            }     // Catch:{ JSONException -> 0x0094 }
        L_0x0085:
            goto L_0x0054
        L_0x0086:
            boolean r2 = r3 instanceof java.lang.String     // Catch:{ JSONException -> 0x0094 }
            if (r2 == 0) goto L_0x0054
            android.os.Bundle r7 = r12.zzee     // Catch:{ JSONException -> 0x0094 }
            r0 = r3
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x0094 }
            r2 = r0
            r7.putString(r6, r2)     // Catch:{ JSONException -> 0x0094 }
            goto L_0x0054
        L_0x0094:
            r2 = move-exception
            goto L_0x0054
        L_0x0096:
            java.lang.String[] r3 = new java.lang.String[r10]
            java.lang.String r4 = "com.google.android.gms.cast.metadata.TITLE"
            r3[r6] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.ARTIST"
            r3[r7] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SUBTITLE"
            r3[r8] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.RELEASE_DATE"
            r3[r9] = r4
            java.util.Collections.addAll(r2, r3)
            goto L_0x0029
        L_0x00ad:
            java.lang.String[] r3 = new java.lang.String[r10]
            java.lang.String r4 = "com.google.android.gms.cast.metadata.TITLE"
            r3[r6] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.STUDIO"
            r3[r7] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SUBTITLE"
            r3[r8] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.RELEASE_DATE"
            r3[r9] = r4
            java.util.Collections.addAll(r2, r3)
            goto L_0x0029
        L_0x00c4:
            r3 = 5
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.String r4 = "com.google.android.gms.cast.metadata.TITLE"
            r3[r6] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SERIES_TITLE"
            r3[r7] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SEASON_NUMBER"
            r3[r8] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.EPISODE_NUMBER"
            r3[r9] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.BROADCAST_DATE"
            r3[r10] = r4
            java.util.Collections.addAll(r2, r3)
            goto L_0x0029
        L_0x00e0:
            r3 = 8
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.String r4 = "com.google.android.gms.cast.metadata.TITLE"
            r3[r6] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.ALBUM_TITLE"
            r3[r7] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.ARTIST"
            r3[r8] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.ALBUM_ARTIST"
            r3[r9] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.COMPOSER"
            r3[r10] = r4
            r4 = 5
            java.lang.String r5 = "com.google.android.gms.cast.metadata.TRACK_NUMBER"
            r3[r4] = r5
            r4 = 6
            java.lang.String r5 = "com.google.android.gms.cast.metadata.DISC_NUMBER"
            r3[r4] = r5
            r4 = 7
            java.lang.String r5 = "com.google.android.gms.cast.metadata.RELEASE_DATE"
            r3[r4] = r5
            java.util.Collections.addAll(r2, r3)
            goto L_0x0029
        L_0x010c:
            r3 = 8
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.String r4 = "com.google.android.gms.cast.metadata.TITLE"
            r3[r6] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.ARTIST"
            r3[r7] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.LOCATION_NAME"
            r3[r8] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE"
            r3[r9] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE"
            r3[r10] = r4
            r4 = 5
            java.lang.String r5 = "com.google.android.gms.cast.metadata.WIDTH"
            r3[r4] = r5
            r4 = 6
            java.lang.String r5 = "com.google.android.gms.cast.metadata.HEIGHT"
            r3[r4] = r5
            r4 = 7
            java.lang.String r5 = "com.google.android.gms.cast.metadata.CREATION_DATE"
            r3[r4] = r5
            java.util.Collections.addAll(r2, r3)
            goto L_0x0029
        L_0x0138:
            r3 = 5
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.String r4 = "com.google.android.gms.cast.metadata.CHAPTER_TITLE"
            r3[r6] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.CHAPTER_NUMBER"
            r3[r7] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.TITLE"
            r3[r8] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.BOOK_TITLE"
            r3[r9] = r4
            java.lang.String r4 = "com.google.android.gms.cast.metadata.SUBTITLE"
            r3[r10] = r4
            java.util.Collections.addAll(r2, r3)
            goto L_0x0029
        L_0x0154:
            boolean r2 = r3 instanceof java.lang.String     // Catch:{ JSONException -> 0x0094 }
            if (r2 == 0) goto L_0x0054
            r0 = r3
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x0094 }
            r2 = r0
            java.util.Calendar r2 = com.google.android.gms.internal.cast.zzdy.zzx(r2)     // Catch:{ JSONException -> 0x0094 }
            if (r2 == 0) goto L_0x0054
            android.os.Bundle r2 = r12.zzee     // Catch:{ JSONException -> 0x0094 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x0094 }
            r2.putString(r6, r3)     // Catch:{ JSONException -> 0x0094 }
            goto L_0x0054
        L_0x016b:
            boolean r2 = r3 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x0094 }
            if (r2 == 0) goto L_0x0054
            android.os.Bundle r2 = r12.zzee     // Catch:{ JSONException -> 0x0094 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ JSONException -> 0x0094 }
            int r3 = r3.intValue()     // Catch:{ JSONException -> 0x0094 }
            r2.putInt(r6, r3)     // Catch:{ JSONException -> 0x0094 }
            goto L_0x0054
        L_0x017c:
            double r2 = r13.optDouble(r2)     // Catch:{ JSONException -> 0x0094 }
            boolean r7 = java.lang.Double.isNaN(r2)     // Catch:{ JSONException -> 0x0094 }
            if (r7 != 0) goto L_0x0054
            android.os.Bundle r7 = r12.zzee     // Catch:{ JSONException -> 0x0094 }
            r7.putDouble(r6, r2)     // Catch:{ JSONException -> 0x0094 }
            goto L_0x0054
        L_0x018d:
            android.os.Bundle r3 = r12.zzee     // Catch:{ JSONException -> 0x0094 }
            long r8 = r13.optLong(r2)     // Catch:{ JSONException -> 0x0094 }
            double r8 = (double) r8     // Catch:{ JSONException -> 0x0094 }
            r10 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r8 = r8 * r10
            long r8 = (long) r8     // Catch:{ JSONException -> 0x0094 }
            r3.putLong(r6, r8)     // Catch:{ JSONException -> 0x0094 }
            goto L_0x0054
        L_0x01a0:
            java.lang.Object r3 = r13.get(r2)     // Catch:{ JSONException -> 0x01b1 }
            boolean r6 = r3 instanceof java.lang.String     // Catch:{ JSONException -> 0x01b1 }
            if (r6 == 0) goto L_0x01b3
            android.os.Bundle r6 = r12.zzee     // Catch:{ JSONException -> 0x01b1 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x01b1 }
            r6.putString(r2, r3)     // Catch:{ JSONException -> 0x01b1 }
            goto L_0x0054
        L_0x01b1:
            r2 = move-exception
        L_0x01b2:
            return
        L_0x01b3:
            boolean r6 = r3 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x01b1 }
            if (r6 == 0) goto L_0x01c4
            android.os.Bundle r6 = r12.zzee     // Catch:{ JSONException -> 0x01b1 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ JSONException -> 0x01b1 }
            int r3 = r3.intValue()     // Catch:{ JSONException -> 0x01b1 }
            r6.putInt(r2, r3)     // Catch:{ JSONException -> 0x01b1 }
            goto L_0x0054
        L_0x01c4:
            boolean r6 = r3 instanceof java.lang.Double     // Catch:{ JSONException -> 0x01b1 }
            if (r6 == 0) goto L_0x0054
            android.os.Bundle r6 = r12.zzee     // Catch:{ JSONException -> 0x01b1 }
            java.lang.Double r3 = (java.lang.Double) r3     // Catch:{ JSONException -> 0x01b1 }
            double r8 = r3.doubleValue()     // Catch:{ JSONException -> 0x01b1 }
            r6.putDouble(r2, r8)     // Catch:{ JSONException -> 0x01b1 }
            goto L_0x0054
        L_0x01d5:
            r2 = move-exception
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaMetadata.zzf(org.json.JSONObject):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        if (!zza(this.zzee, mediaMetadata.zzee) || !this.zzed.equals(mediaMetadata.zzed)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 17;
        Iterator it = this.zzee.keySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return (i2 * 31) + this.zzed.hashCode();
            }
            i = this.zzee.get((String) it.next()).hashCode() + (i2 * 31);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getImages(), false);
        SafeParcelWriter.writeBundle(parcel, 3, this.zzee, false);
        SafeParcelWriter.writeInt(parcel, 4, getMediaType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public List<WebImage> getImages() {
        return this.zzed;
    }

    public boolean hasImages() {
        return this.zzed != null && !this.zzed.isEmpty();
    }

    public void clearImages() {
        this.zzed.clear();
    }

    public void addImage(WebImage webImage) {
        this.zzed.add(webImage);
    }

    private final boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !zza((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null) {
                if (obj2 != null || !bundle2.containsKey(str)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }
}
