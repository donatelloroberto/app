package com.amazon.device.ads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.device.ads.WebRequest;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

class WebUtils {
    private static final String LOGTAG = WebUtils.class.getSimpleName();
    private static final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    WebUtils() {
    }

    public static boolean launchActivityForIntentLink(String url, Context context) {
        if (url == null || url.equals("")) {
            url = "about:blank";
        }
        logger.d("Launch Intent: " + url);
        Intent actionIntent = new Intent();
        if (url.startsWith("intent:")) {
            try {
                actionIntent = Intent.parseUri(url, 1);
            } catch (URISyntaxException e) {
                return false;
            }
        } else {
            actionIntent.setData(Uri.parse(url));
        }
        actionIntent.setAction("android.intent.action.VIEW");
        actionIntent.setFlags(268435456);
        try {
            context.startActivity(actionIntent);
            return true;
        } catch (ActivityNotFoundException e2) {
            String action = actionIntent.getAction();
            logger.w("Could not handle " + (action.startsWith("market://") ? "market" : "intent") + " action: " + action);
            return false;
        }
    }

    public static final String getURLEncodedString(String s) {
        if (s == null) {
            return null;
        }
        try {
            return URLEncoder.encode(s, WebRequest.CHARSET_UTF_8).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLEncodedString threw: %s", e);
            return s;
        }
    }

    public static final String getURLDecodedString(String s) {
        if (s == null) {
            return null;
        }
        try {
            return URLDecoder.decode(s, WebRequest.CHARSET_UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLDecodedString threw: %s", e);
            return s;
        }
    }

    public static final String getScheme(String url) {
        String scheme = Uri.parse(url).getScheme();
        if (scheme != null) {
            return scheme.toLowerCase(Locale.US);
        }
        return scheme;
    }

    public static final String encloseHtml(String html, boolean isHTML5) {
        if (html == null) {
            return html;
        }
        if (html.indexOf("<html>") == -1) {
            html = "<html>" + html + "</html>";
        }
        if (!isHTML5 || html.indexOf("<!DOCTYPE html>") != -1) {
            return html;
        }
        return "<!DOCTYPE html>" + html;
    }

    public static final void executeWebRequestInThread(final String url, final boolean disconnectEnabled) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                WebRequest request = new WebRequest.WebRequestFactory().createWebRequest();
                request.enableLog(true);
                request.setUrlString(url);
                request.setDisconnectEnabled(disconnectEnabled);
                try {
                    request.makeCall();
                } catch (WebRequest.WebRequestException e) {
                }
            }
        });
    }
}
