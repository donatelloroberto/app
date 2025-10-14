package acidhax.cordova.chromecast;

import android.support.v7.media.MediaRouter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ChromecastMediaRouterCallback extends MediaRouter.Callback {
    private Chromecast callback = null;
    private volatile ArrayList<MediaRouter.RouteInfo> routes = new ArrayList<>();

    public void registerCallbacks(Chromecast instance) {
        this.callback = instance;
    }

    public synchronized MediaRouter.RouteInfo getRoute(String id) {
        MediaRouter.RouteInfo i;
        Iterator<MediaRouter.RouteInfo> it = this.routes.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = null;
                break;
            }
            i = it.next();
            if (i.getId().equals(id)) {
                break;
            }
        }
        return i;
    }

    public synchronized MediaRouter.RouteInfo getRoute(int index) {
        return this.routes.get(index);
    }

    public synchronized Collection<MediaRouter.RouteInfo> getRoutes() {
        return this.routes;
    }

    public synchronized void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo route) {
        this.routes.add(route);
        if (this.callback != null) {
            this.callback.onRouteAdded(router, route);
        }
    }

    public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo route) {
        this.routes.remove(route);
        if (this.callback != null) {
            this.callback.onRouteRemoved(router, route);
        }
    }

    public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo info) {
        if (this.callback != null) {
            this.callback.onRouteSelected(router, info);
        }
    }

    public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo info) {
        if (this.callback != null) {
            this.callback.onRouteUnselected(router, info);
        }
    }
}
