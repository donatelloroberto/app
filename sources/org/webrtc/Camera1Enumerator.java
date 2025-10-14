package org.webrtc;

import android.hardware.Camera;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraVideoCapturer;

public class Camera1Enumerator implements CameraEnumerator {
    private static final String TAG = "Camera1Enumerator";
    private static List<List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats;
    private final boolean captureToTexture;

    public Camera1Enumerator() {
        this(true);
    }

    public Camera1Enumerator(boolean captureToTexture2) {
        this.captureToTexture = captureToTexture2;
    }

    public String[] getDeviceNames() {
        ArrayList<String> namesList = new ArrayList<>();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            String name = getDeviceName(i);
            if (name != null) {
                namesList.add(name);
                Logging.d(TAG, "Index: " + i + ". " + name);
            } else {
                Logging.e(TAG, "Index: " + i + ". Failed to query camera name.");
            }
        }
        return (String[]) namesList.toArray(new String[namesList.size()]);
    }

    public boolean isFrontFacing(String deviceName) {
        Camera.CameraInfo info = getCameraInfo(getCameraIndex(deviceName));
        if (info == null || info.facing != 1) {
            return false;
        }
        return true;
    }

    public boolean isBackFacing(String deviceName) {
        Camera.CameraInfo info = getCameraInfo(getCameraIndex(deviceName));
        return info != null && info.facing == 0;
    }

    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String deviceName) {
        return getSupportedFormats(getCameraIndex(deviceName));
    }

    public CameraVideoCapturer createCapturer(String deviceName, CameraVideoCapturer.CameraEventsHandler eventsHandler) {
        return new Camera1Capturer(deviceName, eventsHandler, this.captureToTexture);
    }

    private static Camera.CameraInfo getCameraInfo(int index) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(index, info);
            return info;
        } catch (Exception e) {
            Logging.e(TAG, "getCameraInfo failed on index " + index, e);
            return null;
        }
    }

    static synchronized List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int cameraId) {
        List<CameraEnumerationAndroid.CaptureFormat> list;
        synchronized (Camera1Enumerator.class) {
            if (cachedSupportedFormats == null) {
                cachedSupportedFormats = new ArrayList();
                for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                    cachedSupportedFormats.add(enumerateFormats(i));
                }
            }
            list = cachedSupportedFormats.get(cameraId);
        }
        return list;
    }

    private static List<CameraEnumerationAndroid.CaptureFormat> enumerateFormats(int cameraId) {
        List<CameraEnumerationAndroid.CaptureFormat> formatList;
        Logging.d(TAG, "Get supported formats for camera index " + cameraId + ".");
        long startTimeMs = SystemClock.elapsedRealtime();
        Camera camera = null;
        try {
            Logging.d(TAG, "Opening camera with index " + cameraId);
            camera = Camera.open(cameraId);
            Camera.Parameters parameters = camera.getParameters();
            if (camera != null) {
                camera.release();
            }
            formatList = new ArrayList<>();
            int minFps = 0;
            int maxFps = 0;
            try {
                List<int[]> listFpsRange = parameters.getSupportedPreviewFpsRange();
                if (listFpsRange != null) {
                    int[] range = listFpsRange.get(listFpsRange.size() - 1);
                    minFps = range[0];
                    maxFps = range[1];
                }
                for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
                    formatList.add(new CameraEnumerationAndroid.CaptureFormat(size.width, size.height, minFps, maxFps));
                }
            } catch (Exception e) {
                Logging.e(TAG, "getSupportedFormats() failed on camera index " + cameraId, e);
            }
            Logging.d(TAG, "Get supported formats for camera index " + cameraId + " done. Time spent: " + (SystemClock.elapsedRealtime() - startTimeMs) + " ms.");
        } catch (RuntimeException e2) {
            Logging.e(TAG, "Open camera failed on camera index " + cameraId, e2);
            formatList = new ArrayList<>();
            if (camera != null) {
                camera.release();
            }
        } catch (Throwable th) {
            if (camera != null) {
                camera.release();
            }
            throw th;
        }
        return formatList;
    }

    static List<Size> convertSizes(List<Camera.Size> cameraSizes) {
        List<Size> sizes = new ArrayList<>();
        for (Camera.Size size : cameraSizes) {
            sizes.add(new Size(size.width, size.height));
        }
        return sizes;
    }

    static List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates(List<int[]> arrayRanges) {
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> ranges = new ArrayList<>();
        for (int[] range : arrayRanges) {
            ranges.add(new CameraEnumerationAndroid.CaptureFormat.FramerateRange(range[0], range[1]));
        }
        return ranges;
    }

    static int getCameraIndex(String deviceName) {
        Logging.d(TAG, "getCameraIndex: " + deviceName);
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            if (deviceName.equals(getDeviceName(i))) {
                return i;
            }
        }
        throw new IllegalArgumentException("No such camera: " + deviceName);
    }

    static String getDeviceName(int index) {
        Camera.CameraInfo info = getCameraInfo(index);
        if (info == null) {
            return null;
        }
        return "Camera " + index + ", Facing " + (info.facing == 1 ? "front" : "back") + ", Orientation " + info.orientation;
    }
}
