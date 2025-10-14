package com.google.android.gms.internal.gtm;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zztd extends zztc<FieldDescriptorType, Object> {
    zztd(int i) {
        super(i, (zztd) null);
    }

    public final void zzmi() {
        if (!isImmutable()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= zzra()) {
                    break;
                }
                Map.Entry zzbv = zzbv(i2);
                if (((zzqv) zzbv.getKey()).zzoz()) {
                    zzbv.setValue(Collections.unmodifiableList((List) zzbv.getValue()));
                }
                i = i2 + 1;
            }
            for (Map.Entry entry : zzrb()) {
                if (((zzqv) entry.getKey()).zzoz()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzmi();
    }
}
