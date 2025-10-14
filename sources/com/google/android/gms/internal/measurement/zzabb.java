package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzabb extends zzaba<FieldDescriptorType, Object> {
    zzabb(int i) {
        super(i, (zzabb) null);
    }

    public final void zzrp() {
        if (!isImmutable()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= zzus()) {
                    break;
                }
                Map.Entry zzah = zzah(i2);
                if (((zzzq) zzah.getKey()).zztt()) {
                    zzah.setValue(Collections.unmodifiableList((List) zzah.getValue()));
                }
                i = i2 + 1;
            }
            for (Map.Entry entry : zzut()) {
                if (((zzzq) entry.getKey()).zztt()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzrp();
    }
}
