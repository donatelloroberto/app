package com.amazon.device.ads;

import android.content.Context;

interface PreferredMarketplaceRetriever {
    String retrievePreferredMarketplace(Context context);

    public static class NullPreferredMarketplaceRetriever implements PreferredMarketplaceRetriever {
        public String retrievePreferredMarketplace(Context context) {
            return null;
        }
    }
}
