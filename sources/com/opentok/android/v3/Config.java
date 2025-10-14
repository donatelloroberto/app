package com.opentok.android.v3;

import com.opentok.android.v3.debug.DebugInterface;
import com.opentok.android.v3.debug.DebugSystem;
import com.opentok.android.v3.test.TestInterface;
import com.opentok.android.v3.test.TestSystem;

public class Config {
    public static DebugInterface debugInterface() {
        return DebugSystem.getDebugInterface();
    }

    public static TestInterface testInterface() {
        return TestSystem.getTestInterface();
    }
}
