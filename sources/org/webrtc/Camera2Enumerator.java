package org.webrtc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.AndroidException;
import android.util.Range;
import android.util.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraVideoCapturer;

@TargetApi(21)
public class Camera2Enumerator implements CameraEnumerator {
    private static final double NANO_SECONDS_PER_SECOND = 1.0E9d;
    private static final String TAG = "Camera2Enumerator";
    private static final Map<String, List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats = new HashMap();
    final CameraManager cameraManager;
    final Context context;

    public Camera2Enumerator(Context context2) {
        this.context = context2;
        this.cameraManager = (CameraManager) context2.getSystemService("camera");
    }

    public String[] getDeviceNames() {
        try {
            return this.cameraManager.getCameraIdList();
        } catch (AndroidException e) {
            Logging.e(TAG, "Camera access exception: " + e);
            return new String[0];
        }
    }

    public boolean isFrontFacing(String deviceName) {
        CameraCharacteristics characteristics = getCameraCharacteristics(deviceName);
        return characteristics != null && ((Integer) characteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
    }

    public boolean isBackFacing(String deviceName) {
        CameraCharacteristics characteristics = getCameraCharacteristics(deviceName);
        return characteristics != null && ((Integer) characteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 1;
    }

    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String deviceName) {
        return getSupportedFormats(this.context, deviceName);
    }

    public CameraVideoCapturer createCapturer(String deviceName, CameraVideoCapturer.CameraEventsHandler eventsHandler) {
        return new Camera2Capturer(this.context, deviceName, eventsHandler);
    }

    private CameraCharacteristics getCameraCharacteristics(String deviceName) {
        try {
            return this.cameraManager.getCameraCharacteristics(deviceName);
        } catch (AndroidException e) {
            Logging.e(TAG, "Camera access exception: " + e);
            return null;
        }
    }

    public static boolean isSupported(Context context2) {
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        CameraManager cameraManager2 = (CameraManager) context2.getSystemService("camera");
        try {
            for (String id : cameraManager2.getCameraIdList()) {
                if (((Integer) cameraManager2.getCameraCharacteristics(id).get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                    return false;
                }
            }
            return true;
        } catch (AndroidException e) {
            Logging.e(TAG, "Camera access exception: " + e);
            return false;
        }
    }

    static int getFpsUnitFactor(Range<Integer>[] fpsRanges) {
        if (fpsRanges.length == 0) {
            return 1000;
        }
        return fpsRanges[0].getUpper().intValue() < 1000 ? 1000 : 1;
    }

    static List<Size> getSupportedSizes(CameraCharacteristics cameraCharacteristics) {
        int supportLevel = ((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
        List<Size> sizes = convertSizes(((StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(SurfaceTexture.class));
        if (Build.VERSION.SDK_INT >= 22 || supportLevel != 2) {
            return sizes;
        }
        Rect activeArraySize = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        ArrayList<Size> filteredSizes = new ArrayList<>();
        for (Size size : sizes) {
            if (activeArraySize.width() * size.height == activeArraySize.height() * size.width) {
                filteredSizes.add(size);
            }
        }
        return filteredSizes;
    }

    static List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(Context context2, String cameraId) {
        return getSupportedFormats((CameraManager) context2.getSystemService("camera"), cameraId);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<org.webrtc.CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(android.hardware.camera2.CameraManager r28, java.lang.String r29) {
        /*
            java.util.Map<java.lang.String, java.util.List<org.webrtc.CameraEnumerationAndroid$CaptureFormat>> r22 = cachedSupportedFormats
            monitor-enter(r22)
            java.util.Map<java.lang.String, java.util.List<org.webrtc.CameraEnumerationAndroid$CaptureFormat>> r21 = cachedSupportedFormats     // Catch:{ all -> 0x00b0 }
            r0 = r21
            r1 = r29
            boolean r21 = r0.containsKey(r1)     // Catch:{ all -> 0x00b0 }
            if (r21 == 0) goto L_0x001d
            java.util.Map<java.lang.String, java.util.List<org.webrtc.CameraEnumerationAndroid$CaptureFormat>> r21 = cachedSupportedFormats     // Catch:{ all -> 0x00b0 }
            r0 = r21
            r1 = r29
            java.lang.Object r21 = r0.get(r1)     // Catch:{ all -> 0x00b0 }
            java.util.List r21 = (java.util.List) r21     // Catch:{ all -> 0x00b0 }
            monitor-exit(r22)     // Catch:{ all -> 0x00b0 }
        L_0x001c:
            return r21
        L_0x001d:
            java.lang.String r21 = "Camera2Enumerator"
            java.lang.StringBuilder r23 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b0 }
            r23.<init>()     // Catch:{ all -> 0x00b0 }
            java.lang.String r24 = "Get supported formats for camera index "
            java.lang.StringBuilder r23 = r23.append(r24)     // Catch:{ all -> 0x00b0 }
            r0 = r23
            r1 = r29
            java.lang.StringBuilder r23 = r0.append(r1)     // Catch:{ all -> 0x00b0 }
            java.lang.String r24 = "."
            java.lang.StringBuilder r23 = r23.append(r24)     // Catch:{ all -> 0x00b0 }
            java.lang.String r23 = r23.toString()     // Catch:{ all -> 0x00b0 }
            r0 = r21
            r1 = r23
            org.webrtc.Logging.d(r0, r1)     // Catch:{ all -> 0x00b0 }
            long r18 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x00b0 }
            android.hardware.camera2.CameraCharacteristics r4 = r28.getCameraCharacteristics(r29)     // Catch:{ Exception -> 0x0089 }
            android.hardware.camera2.CameraCharacteristics$Key r21 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP     // Catch:{ all -> 0x00b0 }
            r0 = r21
            java.lang.Object r20 = r4.get(r0)     // Catch:{ all -> 0x00b0 }
            android.hardware.camera2.params.StreamConfigurationMap r20 = (android.hardware.camera2.params.StreamConfigurationMap) r20     // Catch:{ all -> 0x00b0 }
            android.hardware.camera2.CameraCharacteristics$Key r21 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES     // Catch:{ all -> 0x00b0 }
            r0 = r21
            java.lang.Object r10 = r4.get(r0)     // Catch:{ all -> 0x00b0 }
            android.util.Range[] r10 = (android.util.Range[]) r10     // Catch:{ all -> 0x00b0 }
            int r21 = getFpsUnitFactor(r10)     // Catch:{ all -> 0x00b0 }
            r0 = r21
            java.util.List r12 = convertFramerates(r10, r0)     // Catch:{ all -> 0x00b0 }
            java.util.List r17 = getSupportedSizes(r4)     // Catch:{ all -> 0x00b0 }
            r5 = 0
            java.util.Iterator r21 = r12.iterator()     // Catch:{ all -> 0x00b0 }
        L_0x0072:
            boolean r23 = r21.hasNext()     // Catch:{ all -> 0x00b0 }
            if (r23 == 0) goto L_0x00b3
            java.lang.Object r11 = r21.next()     // Catch:{ all -> 0x00b0 }
            org.webrtc.CameraEnumerationAndroid$CaptureFormat$FramerateRange r11 = (org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange) r11     // Catch:{ all -> 0x00b0 }
            int r0 = r11.max     // Catch:{ all -> 0x00b0 }
            r23 = r0
            r0 = r23
            int r5 = java.lang.Math.max(r5, r0)     // Catch:{ all -> 0x00b0 }
            goto L_0x0072
        L_0x0089:
            r8 = move-exception
            java.lang.String r21 = "Camera2Enumerator"
            java.lang.StringBuilder r23 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b0 }
            r23.<init>()     // Catch:{ all -> 0x00b0 }
            java.lang.String r24 = "getCameraCharacteristics(): "
            java.lang.StringBuilder r23 = r23.append(r24)     // Catch:{ all -> 0x00b0 }
            r0 = r23
            java.lang.StringBuilder r23 = r0.append(r8)     // Catch:{ all -> 0x00b0 }
            java.lang.String r23 = r23.toString()     // Catch:{ all -> 0x00b0 }
            r0 = r21
            r1 = r23
            org.webrtc.Logging.e(r0, r1)     // Catch:{ all -> 0x00b0 }
            java.util.ArrayList r21 = new java.util.ArrayList     // Catch:{ all -> 0x00b0 }
            r21.<init>()     // Catch:{ all -> 0x00b0 }
            monitor-exit(r22)     // Catch:{ all -> 0x00b0 }
            goto L_0x001c
        L_0x00b0:
            r21 = move-exception
            monitor-exit(r22)     // Catch:{ all -> 0x00b0 }
            throw r21
        L_0x00b3:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x00b0 }
            r9.<init>()     // Catch:{ all -> 0x00b0 }
            java.util.Iterator r21 = r17.iterator()     // Catch:{ all -> 0x00b0 }
        L_0x00bc:
            boolean r23 = r21.hasNext()     // Catch:{ all -> 0x00b0 }
            if (r23 == 0) goto L_0x0162
            java.lang.Object r16 = r21.next()     // Catch:{ all -> 0x00b0 }
            org.webrtc.Size r16 = (org.webrtc.Size) r16     // Catch:{ all -> 0x00b0 }
            r14 = 0
            java.lang.Class<android.graphics.SurfaceTexture> r23 = android.graphics.SurfaceTexture.class
            android.util.Size r24 = new android.util.Size     // Catch:{ Exception -> 0x01a6 }
            r0 = r16
            int r0 = r0.width     // Catch:{ Exception -> 0x01a6 }
            r25 = r0
            r0 = r16
            int r0 = r0.height     // Catch:{ Exception -> 0x01a6 }
            r26 = r0
            r24.<init>(r25, r26)     // Catch:{ Exception -> 0x01a6 }
            r0 = r20
            r1 = r23
            r2 = r24
            long r14 = r0.getOutputMinFrameDuration(r1, r2)     // Catch:{ Exception -> 0x01a6 }
        L_0x00e7:
            r24 = 0
            int r23 = (r14 > r24 ? 1 : (r14 == r24 ? 0 : -1))
            if (r23 != 0) goto L_0x014a
            r13 = r5
        L_0x00ee:
            org.webrtc.CameraEnumerationAndroid$CaptureFormat r23 = new org.webrtc.CameraEnumerationAndroid$CaptureFormat     // Catch:{ all -> 0x00b0 }
            r0 = r16
            int r0 = r0.width     // Catch:{ all -> 0x00b0 }
            r24 = r0
            r0 = r16
            int r0 = r0.height     // Catch:{ all -> 0x00b0 }
            r25 = r0
            r26 = 0
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r26
            r0.<init>(r1, r2, r3, r13)     // Catch:{ all -> 0x00b0 }
            r0 = r23
            r9.add(r0)     // Catch:{ all -> 0x00b0 }
            java.lang.String r23 = "Camera2Enumerator"
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b0 }
            r24.<init>()     // Catch:{ all -> 0x00b0 }
            java.lang.String r25 = "Format: "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b0 }
            r0 = r16
            int r0 = r0.width     // Catch:{ all -> 0x00b0 }
            r25 = r0
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b0 }
            java.lang.String r25 = "x"
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b0 }
            r0 = r16
            int r0 = r0.height     // Catch:{ all -> 0x00b0 }
            r25 = r0
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b0 }
            java.lang.String r25 = "@"
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b0 }
            r0 = r24
            java.lang.StringBuilder r24 = r0.append(r13)     // Catch:{ all -> 0x00b0 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b0 }
            org.webrtc.Logging.d(r23, r24)     // Catch:{ all -> 0x00b0 }
            goto L_0x00bc
        L_0x014a:
            r24 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
            double r0 = (double) r14     // Catch:{ all -> 0x00b0 }
            r26 = r0
            double r24 = r24 / r26
            long r24 = java.lang.Math.round(r24)     // Catch:{ all -> 0x00b0 }
            r0 = r24
            int r0 = (int) r0     // Catch:{ all -> 0x00b0 }
            r23 = r0
            r0 = r23
            int r13 = r0 * 1000
            goto L_0x00ee
        L_0x0162:
            java.util.Map<java.lang.String, java.util.List<org.webrtc.CameraEnumerationAndroid$CaptureFormat>> r21 = cachedSupportedFormats     // Catch:{ all -> 0x00b0 }
            r0 = r21
            r1 = r29
            r0.put(r1, r9)     // Catch:{ all -> 0x00b0 }
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x00b0 }
            java.lang.String r21 = "Camera2Enumerator"
            java.lang.StringBuilder r23 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b0 }
            r23.<init>()     // Catch:{ all -> 0x00b0 }
            java.lang.String r24 = "Get supported formats for camera index "
            java.lang.StringBuilder r23 = r23.append(r24)     // Catch:{ all -> 0x00b0 }
            r0 = r23
            r1 = r29
            java.lang.StringBuilder r23 = r0.append(r1)     // Catch:{ all -> 0x00b0 }
            java.lang.String r24 = " done. Time spent: "
            java.lang.StringBuilder r23 = r23.append(r24)     // Catch:{ all -> 0x00b0 }
            long r24 = r6 - r18
            java.lang.StringBuilder r23 = r23.append(r24)     // Catch:{ all -> 0x00b0 }
            java.lang.String r24 = " ms."
            java.lang.StringBuilder r23 = r23.append(r24)     // Catch:{ all -> 0x00b0 }
            java.lang.String r23 = r23.toString()     // Catch:{ all -> 0x00b0 }
            r0 = r21
            r1 = r23
            org.webrtc.Logging.d(r0, r1)     // Catch:{ all -> 0x00b0 }
            monitor-exit(r22)     // Catch:{ all -> 0x00b0 }
            r21 = r9
            goto L_0x001c
        L_0x01a6:
            r23 = move-exception
            goto L_0x00e7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.Camera2Enumerator.getSupportedFormats(android.hardware.camera2.CameraManager, java.lang.String):java.util.List");
    }

    private static List<Size> convertSizes(Size[] cameraSizes) {
        List<Size> sizes = new ArrayList<>();
        for (Size size : cameraSizes) {
            sizes.add(new Size(size.getWidth(), size.getHeight()));
        }
        return sizes;
    }

    static List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates(Range<Integer>[] arrayRanges, int unitFactor) {
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> ranges = new ArrayList<>();
        for (Range<Integer> range : arrayRanges) {
            ranges.add(new CameraEnumerationAndroid.CaptureFormat.FramerateRange(range.getLower().intValue() * unitFactor, range.getUpper().intValue() * unitFactor));
        }
        return ranges;
    }
}
