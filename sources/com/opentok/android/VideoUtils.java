package com.opentok.android;

public class VideoUtils {

    public static class Size {
        public int height;
        public int width;

        public Size() {
        }

        public Size(int width2, int height2) {
            this.width = width2;
            this.height = height2;
        }

        public Size(Size size) {
            this.width = size.width;
            this.height = size.height;
        }

        public final void set(int width2, int height2) {
            this.width = width2;
            this.height = height2;
        }

        public final void set(Size size) {
            this.width = size.width;
            this.height = size.height;
        }

        public final boolean equals(int width2, int height2) {
            return this.width == width2 && this.height == height2;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof Size) && (obj == this || equals(((Size) obj).width, ((Size) obj).height));
        }
    }
}
