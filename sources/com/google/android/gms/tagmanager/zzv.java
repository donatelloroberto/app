package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tagmanager.ContainerHolder;

@VisibleForTesting
final class zzv implements ContainerHolder {
    private final Looper zzaek;
    private Container zzael;
    private Container zzaem;
    private Status zzaen;
    private zzx zzaeo;
    private zzw zzaep;
    private boolean zzaeq;
    private TagManager zzaer;

    public zzv(Status status) {
        this.zzaen = status;
        this.zzaek = null;
    }

    public zzv(TagManager tagManager, Looper looper, Container container, zzw zzw) {
        this.zzaer = tagManager;
        this.zzaek = looper == null ? Looper.getMainLooper() : looper;
        this.zzael = container;
        this.zzaep = zzw;
        this.zzaen = Status.RESULT_SUCCESS;
        tagManager.zza(this);
    }

    public final Status getStatus() {
        return this.zzaen;
    }

    public final synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.zzaeq) {
                zzdi.zzav("ContainerHolder is released.");
            } else {
                if (this.zzaem != null) {
                    this.zzael = this.zzaem;
                    this.zzaem = null;
                }
                container = this.zzael;
            }
        }
        return container;
    }

    public final synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener containerAvailableListener) {
        if (this.zzaeq) {
            zzdi.zzav("ContainerHolder is released.");
        } else if (containerAvailableListener == null) {
            this.zzaeo = null;
        } else {
            this.zzaeo = new zzx(this, containerAvailableListener, this.zzaek);
            if (this.zzaem != null) {
                zzhd();
            }
        }
    }

    public final synchronized void refresh() {
        if (this.zzaeq) {
            zzdi.zzav("Refreshing a released ContainerHolder.");
        } else {
            this.zzaep.zzhe();
        }
    }

    public final synchronized void release() {
        if (this.zzaeq) {
            zzdi.zzav("Releasing a released ContainerHolder.");
        } else {
            this.zzaeq = true;
            this.zzaer.zzb(this);
            this.zzael.release();
            this.zzael = null;
            this.zzaem = null;
            this.zzaep = null;
            this.zzaeo = null;
        }
    }

    public final synchronized void zza(Container container) {
        if (!this.zzaeq) {
            this.zzaem = container;
            zzhd();
        }
    }

    public final synchronized void zzan(String str) {
        if (!this.zzaeq) {
            this.zzael.zzan(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final String getContainerId() {
        if (!this.zzaeq) {
            return this.zzael.getContainerId();
        }
        zzdi.zzav("getContainerId called on a released ContainerHolder.");
        return "";
    }

    /* access modifiers changed from: package-private */
    public final void zzao(String str) {
        if (this.zzaeq) {
            zzdi.zzav("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzaep.zzao(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzhc() {
        if (!this.zzaeq) {
            return this.zzaep.zzhc();
        }
        zzdi.zzav("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    private final void zzhd() {
        if (this.zzaeo != null) {
            zzx zzx = this.zzaeo;
            zzx.sendMessage(zzx.obtainMessage(1, this.zzaem.zzha()));
        }
    }
}
