package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzbdq extends zzbdp<FieldDescriptorType, Object> {
    zzbdq(int i) {
        super(i, (zzbdq) null);
    }

    public final void zzaaz() {
        if (!isImmutable()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= zzafs()) {
                    break;
                }
                Map.Entry zzcy = zzcy(i2);
                if (((zzbbi) zzcy.getKey()).zzada()) {
                    zzcy.setValue(Collections.unmodifiableList((List) zzcy.getValue()));
                }
                i = i2 + 1;
            }
            for (Map.Entry entry : zzaft()) {
                if (((zzbbi) entry.getKey()).zzada()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzaaz();
    }
}
