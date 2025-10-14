package io.card.payment;

public class CardIONativeLibsConfig {
    private static String alternativeLibsPath;

    public static void init(String path) {
        alternativeLibsPath = path;
    }

    static String getAlternativeLibsPath() {
        return alternativeLibsPath;
    }
}
