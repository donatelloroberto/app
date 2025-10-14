package com.amazon.device.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

class GraphicsUtils {
    private static final String LOGTAG = GraphicsUtils.class.getSimpleName();
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    GraphicsUtils() {
    }

    public Bitmap createBitmapImage(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 32768);
        bufferedInputStream.mark(32768);
        Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
        try {
            bufferedInputStream.close();
        } catch (IOException e) {
            this.logger.e("IOException while trying to close the input stream.");
        }
        BufferedInputStream bufferedInputStream2 = bufferedInputStream;
        return bitmap;
    }

    public String insertImageInMediaStore(Context context, Bitmap bitmap, String name, String description) {
        return MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, name, description);
    }
}
