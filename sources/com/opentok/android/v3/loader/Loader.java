package com.opentok.android.v3.loader;

public class Loader {
    private static boolean loaded = false;
    private static final String nativeLibsCustomPath = System.getProperty("native_libs_custom_directory_absolute_path");

    public static synchronized void load() {
        synchronized (Loader.class) {
            if (!loaded) {
                if (nativeLibsCustomPath != null) {
                    System.load(nativeLibsCustomPath);
                } else {
                    System.loadLibrary("opentok");
                }
                loaded = true;
            }
        }
    }
}
