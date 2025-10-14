package com.amazon.device.ads;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringUtils {
    private static final String LOGTAG = StringUtils.class.getSimpleName();
    private static final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    private StringUtils() {
    }

    public static boolean containsRegEx(String regex, String input) {
        return Pattern.compile(regex).matcher(input).find();
    }

    public static String getFirstMatch(String regex, String input) {
        Matcher m = Pattern.compile(regex).matcher(input);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

    public static final boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    public static final boolean isNullOrWhiteSpace(String s) {
        return isNullOrEmpty(s) || s.trim().equals("");
    }

    protected static boolean doesExceptionContainLockedDatabaseMessage(Exception e) {
        if (e == null || e.getMessage() == null) {
            return false;
        }
        return e.getMessage().contains("database is locked");
    }

    public static String sha1(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(Integer.toHexString((b & 255) | 256).substring(1));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public static String readStringFromInputStream(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        while (true) {
            try {
                int i = inputStream.read(b);
                if (i != -1) {
                    out.append(new String(b, 0, i));
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        logger.e("IOException while trying to close the stream.");
                    }
                }
            } catch (IOException e2) {
                logger.e("Unable to read the stream.");
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    logger.e("IOException while trying to close the stream.");
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    logger.e("IOException while trying to close the stream.");
                }
                throw th;
            }
        }
        inputStream.close();
        return out.toString();
    }
}
