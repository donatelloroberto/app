package com.opentok.android.v3;

class Utility {
    Utility() {
    }

    static int SDBMHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = ((str.charAt(i) + (hash << 6)) + (hash << 16)) - hash;
        }
        return hash;
    }

    static void androidAssert(boolean arg) {
    }
}
