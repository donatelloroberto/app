package org.webrtc;

import java.util.Map;

public class RTCStatsReport {
    private final Map<String, RTCStats> stats;
    private final long timestampUs;

    public RTCStatsReport(long timestampUs2, Map<String, RTCStats> stats2) {
        this.timestampUs = timestampUs2;
        this.stats = stats2;
    }

    public double getTimestampUs() {
        return (double) this.timestampUs;
    }

    public Map<String, RTCStats> getStatsMap() {
        return this.stats;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ timestampUs: ").append(this.timestampUs).append(", stats: [\n");
        boolean first = true;
        for (RTCStats stat : this.stats.values()) {
            if (!first) {
                builder.append(",\n");
            }
            builder.append(stat);
            first = false;
        }
        builder.append(" ] }");
        return builder.toString();
    }
}
