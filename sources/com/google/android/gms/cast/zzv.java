package com.google.android.gms.cast;

import android.support.v7.media.MediaRouter;

final class zzv extends MediaRouter.Callback {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzv(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        this.zzci.zzb("onRouteUnselected");
        if (this.zzci.zzby == null) {
            this.zzci.zzb("onRouteUnselected, no device was selected");
        } else if (!CastDevice.getFromBundle(routeInfo.getExtras()).getDeviceId().equals(this.zzci.zzby.getDeviceId())) {
            this.zzci.zzb("onRouteUnselected, device does not match");
        } else {
            CastRemoteDisplayLocalService.stopService();
        }
    }
}
