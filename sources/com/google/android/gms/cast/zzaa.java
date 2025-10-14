package com.google.android.gms.cast;

import android.support.annotation.NonNull;
import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

final class zzaa implements OnCompleteListener<Void> {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzaa(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    public final void onComplete(@NonNull Task<Void> task) {
        if (!task.isSuccessful()) {
            this.zzci.zzb("Unable to stop the remote display, result unsuccessful");
            if (this.zzci.zzbs.get() != null) {
                ((CastRemoteDisplayLocalService.Callbacks) this.zzci.zzbs.get()).onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_STOPPING_SERVICE_FAILED));
            }
        } else {
            this.zzci.zzb("remote display stopped");
        }
        Display unused = this.zzci.zzbz = null;
    }
}
