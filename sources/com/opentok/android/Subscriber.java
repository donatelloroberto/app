package com.opentok.android;

import android.content.Context;
import com.opentok.android.SubscriberKit;

public class Subscriber extends SubscriberKit {

    public static class Builder extends SubscriberKit.Builder {
        public Builder(Context context, Stream stream) {
            super(context, stream);
        }

        public Builder renderer(BaseVideoRenderer renderer) {
            this.renderer = renderer;
            return this;
        }

        public Subscriber build() {
            return new Subscriber(this.context, this.stream, this.renderer);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected Subscriber(android.content.Context r1, com.opentok.android.Stream r2, com.opentok.android.BaseVideoRenderer r3) {
        /*
            r0 = this;
            if (r3 != 0) goto L_0x0008
            if (r1 == 0) goto L_0x0008
            com.opentok.android.BaseVideoRenderer r3 = com.opentok.android.VideoRenderFactory.constructRenderer(r1)
        L_0x0008:
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.opentok.android.Subscriber.<init>(android.content.Context, com.opentok.android.Stream, com.opentok.android.BaseVideoRenderer):void");
    }

    @Deprecated
    public Subscriber(Context context, Stream stream) {
        this(context, stream, (BaseVideoRenderer) null);
    }
}
