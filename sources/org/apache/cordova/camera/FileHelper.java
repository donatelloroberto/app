package org.apache.cordova.camera;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import com.adobe.phonegap.push.PushConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.cordova.CordovaInterface;

public class FileHelper {
    private static final String LOG_TAG = "FileUtils";
    private static final String _DATA = "_data";

    public static String getRealPath(Uri uri, CordovaInterface cordova2) {
        if (Build.VERSION.SDK_INT < 11) {
            return getRealPathFromURI_BelowAPI11(cordova2.getActivity(), uri);
        }
        return getRealPathFromURI_API11_And_Above(cordova2.getActivity(), uri);
    }

    public static String getRealPath(String uriString, CordovaInterface cordova2) {
        return getRealPath(Uri.parse(uriString), cordova2);
    }

    @SuppressLint({"NewApi"})
    public static String getRealPathFromURI_API11_And_Above(Context context, Uri uri) {
        boolean isKitKat;
        if (Build.VERSION.SDK_INT >= 19) {
            isKitKat = true;
        } else {
            isKitKat = false;
        }
        if (!isKitKat || !DocumentsContract.isDocumentUri(context, uri)) {
            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, (String) null, (String[]) null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            } else {
                return null;
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
            return null;
        } else if (isDownloadsDocument(uri)) {
            String id = DocumentsContract.getDocumentId(uri);
            if (id == null || id.length() <= 0) {
                return null;
            }
            if (id.startsWith("raw:")) {
                return id.replaceFirst("raw:", "");
            }
            try {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id).longValue()), (String) null, (String[]) null);
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (!isMediaDocument(uri)) {
            return null;
        } else {
            String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
            String type = split2[0];
            Uri contentUri = null;
            if (PushConstants.IMAGE.equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(context, contentUri, "_id=?", new String[]{split2[1]});
        }
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri) {
        try {
            Cursor cursor = context.getContentResolver().query(contentUri, new String[]{_DATA}, (String) null, (String[]) null, (String) null);
            int column_index = cursor.getColumnIndexOrThrow(_DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return null;
        }
    }

    public static InputStream getInputStreamFromUriString(String uriString, CordovaInterface cordova2) throws IOException {
        InputStream returnValue;
        if (uriString.startsWith(FirebaseAnalytics.Param.CONTENT)) {
            return cordova2.getActivity().getContentResolver().openInputStream(Uri.parse(uriString));
        } else if (!uriString.startsWith("file://")) {
            return new FileInputStream(uriString);
        } else {
            int question = uriString.indexOf("?");
            if (question > -1) {
                uriString = uriString.substring(0, question);
            }
            if (uriString.startsWith("file:///android_asset/")) {
                return cordova2.getActivity().getAssets().open(Uri.parse(uriString).getPath().substring(15));
            }
            try {
                returnValue = cordova2.getActivity().getContentResolver().openInputStream(Uri.parse(uriString));
            } catch (Exception e) {
                returnValue = null;
            }
            if (returnValue == null) {
                return new FileInputStream(getRealPath(uriString, cordova2));
            }
            return returnValue;
        }
    }

    public static String stripFileProtocol(String uriString) {
        if (uriString.startsWith("file://")) {
            return uriString.substring(7);
        }
        return uriString;
    }

    public static String getMimeTypeForExtension(String path) {
        String extension = path;
        int lastDot = extension.lastIndexOf(46);
        if (lastDot != -1) {
            extension = extension.substring(lastDot + 1);
        }
        String extension2 = extension.toLowerCase(Locale.getDefault());
        if (extension2.equals("3ga")) {
            return "audio/3gpp";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension2);
    }

    public static String getMimeType(String uriString, CordovaInterface cordova2) {
        Uri uri = Uri.parse(uriString);
        if (uriString.startsWith("content://")) {
            return cordova2.getActivity().getContentResolver().getType(uri);
        }
        return getMimeTypeForExtension(uri.getPath());
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        try {
            Cursor cursor2 = context.getContentResolver().query(uri, new String[]{_DATA}, selection, selectionArgs, (String) null);
            if (cursor2 == null || !cursor2.moveToFirst()) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                return null;
            }
            String string = cursor2.getString(cursor2.getColumnIndexOrThrow(_DATA));
            if (cursor2 == null) {
                return string;
            }
            cursor2.close();
            return string;
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
