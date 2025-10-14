package acidhax.cordova.chromecast;

import android.net.Uri;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ImagesContract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChromecastMediaController {
    private RemoteMediaPlayer remote = null;

    public ChromecastMediaController(RemoteMediaPlayer mRemoteMediaPlayer) {
        this.remote = mRemoteMediaPlayer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.cast.MediaInfo createLoadUrlRequest(java.lang.String r10, java.lang.String r11, long r12, java.lang.String r14, org.json.JSONObject r15) {
        /*
            r9 = this;
            r7 = 1
            com.google.android.gms.cast.MediaMetadata r3 = new com.google.android.gms.cast.MediaMetadata
            r3.<init>()
            java.lang.String r6 = "metadataType"
            boolean r6 = r15.has(r6)     // Catch:{ Exception -> 0x0070 }
            if (r6 == 0) goto L_0x0068
            java.lang.String r6 = "metadataType"
            int r5 = r15.getInt(r6)     // Catch:{ Exception -> 0x0070 }
        L_0x0014:
            if (r5 != 0) goto L_0x0045
            com.google.android.gms.cast.MediaMetadata r4 = new com.google.android.gms.cast.MediaMetadata     // Catch:{ Exception -> 0x0070 }
            r4.<init>()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r8 = "com.google.android.gms.cast.metadata.TITLE"
            java.lang.String r6 = "title"
            boolean r6 = r15.has(r6)     // Catch:{ Exception -> 0x008e }
            if (r6 == 0) goto L_0x006a
            java.lang.String r6 = "title"
            java.lang.String r6 = r15.getString(r6)     // Catch:{ Exception -> 0x008e }
        L_0x002b:
            r4.putString(r8, r6)     // Catch:{ Exception -> 0x008e }
            java.lang.String r8 = "com.google.android.gms.cast.metadata.SUBTITLE"
            java.lang.String r6 = "title"
            boolean r6 = r15.has(r6)     // Catch:{ Exception -> 0x008e }
            if (r6 == 0) goto L_0x006d
            java.lang.String r6 = "subtitle"
            java.lang.String r6 = r15.getString(r6)     // Catch:{ Exception -> 0x008e }
        L_0x003e:
            r4.putString(r8, r6)     // Catch:{ Exception -> 0x008e }
            com.google.android.gms.cast.MediaMetadata r3 = r9.addImages(r15, r4)     // Catch:{ Exception -> 0x008e }
        L_0x0045:
            r0 = 1
            java.lang.String r6 = "buffered"
            boolean r6 = r14.equals(r6)
            if (r6 == 0) goto L_0x007a
        L_0x004e:
            com.google.android.gms.cast.MediaInfo$Builder r6 = new com.google.android.gms.cast.MediaInfo$Builder
            r6.<init>(r10)
            com.google.android.gms.cast.MediaInfo$Builder r6 = r6.setContentType(r11)
            com.google.android.gms.cast.MediaInfo$Builder r6 = r6.setStreamType(r0)
            com.google.android.gms.cast.MediaInfo$Builder r6 = r6.setStreamDuration(r12)
            com.google.android.gms.cast.MediaInfo$Builder r6 = r6.setMetadata(r3)
            com.google.android.gms.cast.MediaInfo r2 = r6.build()
            return r2
        L_0x0068:
            r5 = r7
            goto L_0x0014
        L_0x006a:
            java.lang.String r6 = "[Title not set]"
            goto L_0x002b
        L_0x006d:
            java.lang.String r6 = "[Subtitle not set]"
            goto L_0x003e
        L_0x0070:
            r1 = move-exception
        L_0x0071:
            r1.printStackTrace()
            com.google.android.gms.cast.MediaMetadata r3 = new com.google.android.gms.cast.MediaMetadata
            r3.<init>(r7)
            goto L_0x0045
        L_0x007a:
            java.lang.String r6 = "live"
            boolean r6 = r14.equals(r6)
            if (r6 == 0) goto L_0x0084
            r0 = 2
            goto L_0x004e
        L_0x0084:
            java.lang.String r6 = "other"
            boolean r6 = r14.equals(r6)
            if (r6 == 0) goto L_0x004e
            r0 = 0
            goto L_0x004e
        L_0x008e:
            r1 = move-exception
            r3 = r4
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: acidhax.cordova.chromecast.ChromecastMediaController.createLoadUrlRequest(java.lang.String, java.lang.String, long, java.lang.String, org.json.JSONObject):com.google.android.gms.cast.MediaInfo");
    }

    public void play(GoogleApiClient apiClient, ChromecastSessionCallback callback) {
        this.remote.play(apiClient).setResultCallback(createMediaCallback(callback));
    }

    public void pause(GoogleApiClient apiClient, ChromecastSessionCallback callback) {
        this.remote.pause(apiClient).setResultCallback(createMediaCallback(callback));
    }

    public void stop(GoogleApiClient apiClient, ChromecastSessionCallback callback) {
        this.remote.stop(apiClient).setResultCallback(createMediaCallback(callback));
    }

    public void seek(long seekPosition, String resumeState, GoogleApiClient apiClient, ChromecastSessionCallback callback) {
        PendingResult<RemoteMediaPlayer.MediaChannelResult> res = null;
        if (resumeState != null && !resumeState.equals("")) {
            res = resumeState.equals("PLAYBACK_PAUSE") ? this.remote.seek(apiClient, seekPosition, 2) : resumeState.equals("PLAYBACK_START") ? this.remote.seek(apiClient, seekPosition, 1) : this.remote.seek(apiClient, seekPosition, 0);
        }
        if (res == null) {
            res = this.remote.seek(apiClient, seekPosition);
        }
        res.setResultCallback(createMediaCallback(callback));
    }

    public void setVolume(double volume, GoogleApiClient apiClient, ChromecastSessionCallback callback) {
        this.remote.setStreamVolume(apiClient, volume).setResultCallback(createMediaCallback(callback));
    }

    public void setMuted(boolean muted, GoogleApiClient apiClient, ChromecastSessionCallback callback) {
        this.remote.setStreamMute(apiClient, muted).setResultCallback(createMediaCallback(callback));
    }

    private ResultCallback<RemoteMediaPlayer.MediaChannelResult> createMediaCallback(final ChromecastSessionCallback callback) {
        return new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {
            public void onResult(RemoteMediaPlayer.MediaChannelResult result) {
                if (result.getStatus().isSuccess()) {
                    callback.onSuccess();
                } else {
                    callback.onError("channel_error");
                }
            }
        };
    }

    private MediaMetadata addImages(JSONObject metadata, MediaMetadata mediaMetadata) throws JSONException {
        if (metadata.has("images")) {
            JSONArray imageUrls = metadata.getJSONArray("images");
            for (int i = 0; i < imageUrls.length(); i++) {
                JSONObject imageObj = imageUrls.getJSONObject(i);
                String imageUrl = imageObj.has(ImagesContract.URL) ? imageObj.getString(ImagesContract.URL) : "undefined";
                if (imageUrl.indexOf("http://") >= 0) {
                    mediaMetadata.addImage(new WebImage(Uri.parse(imageUrl)));
                }
            }
        }
        return mediaMetadata;
    }
}
