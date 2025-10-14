package android.support.v7.media;

import android.media.MediaRouter;
import android.support.annotation.RequiresApi;

@RequiresApi(24)
final class MediaRouterApi24 {
    MediaRouterApi24() {
    }

    public static final class RouteInfo {
        public static int getDeviceType(Object routeObj) {
            return ((MediaRouter.RouteInfo) routeObj).getDeviceType();
        }
    }
}
