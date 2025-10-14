package com.amazon.device.ads;

import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.Metrics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

class MetricsCollector {
    private static final String LOGTAG = MetricsCollector.class.getSimpleName();
    private String adTypeMetricTag;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    protected Vector<MetricHit> metricHits = new Vector<>(60);

    public Vector<MetricHit> getMetricHits() {
        return this.metricHits;
    }

    public void incrementMetric(Metrics.MetricType metric) {
        this.logger.d("METRIC Increment " + metric.toString());
        this.metricHits.add(new MetricHitIncrement(metric, 1));
    }

    public void setMetricString(Metrics.MetricType metric, String s) {
        this.logger.d("METRIC Set " + metric.toString() + ": " + s);
        this.metricHits.add(new MetricHitString(metric, s));
    }

    public void publishMetricInMilliseconds(Metrics.MetricType metric, long value) {
        this.logger.d("METRIC Publish " + metric.toString());
        this.metricHits.add(new MetricHitTotalTime(metric, value));
    }

    public void publishMetricInMillisecondsFromNanoseconds(Metrics.MetricType metric, long value) {
        publishMetricInMilliseconds(metric, NumberUtils.convertToMillisecondsFromNanoseconds(value));
    }

    public void startMetricInMillisecondsFromNanoseconds(Metrics.MetricType metric, long startTime) {
        this.logger.d("METRIC Start " + metric.toString());
        this.metricHits.add(new MetricHitStartTime(metric, NumberUtils.convertToMillisecondsFromNanoseconds(startTime)));
    }

    public void startMetric(Metrics.MetricType metric) {
        startMetricInMillisecondsFromNanoseconds(metric, System.nanoTime());
    }

    public void stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType metric, long stopTime) {
        this.logger.d("METRIC Stop " + metric.toString());
        this.metricHits.add(new MetricHitStopTime(metric, NumberUtils.convertToMillisecondsFromNanoseconds(stopTime)));
    }

    public void stopMetric(Metrics.MetricType metric) {
        stopMetricInMillisecondsFromNanoseconds(metric, System.nanoTime());
    }

    public void setAdType(AdProperties.AdType adType) {
        this.adTypeMetricTag = adType.getAdTypeMetricTag();
    }

    public String getAdTypeMetricTag() {
        return this.adTypeMetricTag;
    }

    static class CompositeMetricsCollector extends MetricsCollector {
        private final ArrayList<MetricsCollector> collectors;

        public CompositeMetricsCollector(ArrayList<MetricsCollector> collectors2) {
            this.collectors = collectors2;
        }

        public void incrementMetric(Metrics.MetricType metric) {
            Iterator i$ = this.collectors.iterator();
            while (i$.hasNext()) {
                i$.next().incrementMetric(metric);
            }
        }

        public void setMetricString(Metrics.MetricType metric, String s) {
            Iterator i$ = this.collectors.iterator();
            while (i$.hasNext()) {
                i$.next().setMetricString(metric, s);
            }
        }

        public void publishMetricInMilliseconds(Metrics.MetricType metric, long value) {
            Iterator i$ = this.collectors.iterator();
            while (i$.hasNext()) {
                i$.next().publishMetricInMilliseconds(metric, value);
            }
        }

        public void publishMetricInMillisecondsFromNanoseconds(Metrics.MetricType metric, long value) {
            Iterator i$ = this.collectors.iterator();
            while (i$.hasNext()) {
                i$.next().publishMetricInMillisecondsFromNanoseconds(metric, value);
            }
        }

        public void startMetricInMillisecondsFromNanoseconds(Metrics.MetricType metric, long startTime) {
            Iterator i$ = this.collectors.iterator();
            while (i$.hasNext()) {
                i$.next().startMetricInMillisecondsFromNanoseconds(metric, startTime);
            }
        }

        public void startMetric(Metrics.MetricType metric) {
            Iterator i$ = this.collectors.iterator();
            while (i$.hasNext()) {
                i$.next().startMetric(metric);
            }
        }

        public void stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType metric, long stopTime) {
            Iterator i$ = this.collectors.iterator();
            while (i$.hasNext()) {
                i$.next().stopMetricInMillisecondsFromNanoseconds(metric, stopTime);
            }
        }

        public void stopMetric(Metrics.MetricType metric) {
            Iterator i$ = this.collectors.iterator();
            while (i$.hasNext()) {
                i$.next().stopMetric(metric);
            }
        }
    }

    public boolean isMetricsCollectorEmpty() {
        return this.metricHits.isEmpty();
    }

    static class MetricHit {
        public final Metrics.MetricType metric;

        public MetricHit(Metrics.MetricType metric2) {
            this.metric = metric2;
        }
    }

    static class MetricHitStartTime extends MetricHit {
        public final long startTime;

        public MetricHitStartTime(Metrics.MetricType metric, long startTime2) {
            super(metric);
            this.startTime = startTime2;
        }
    }

    static class MetricHitStopTime extends MetricHit {
        public final long stopTime;

        public MetricHitStopTime(Metrics.MetricType metric, long stopTime2) {
            super(metric);
            this.stopTime = stopTime2;
        }
    }

    static class MetricHitTotalTime extends MetricHit {
        public final long totalTime;

        public MetricHitTotalTime(Metrics.MetricType metric, long totalTime2) {
            super(metric);
            this.totalTime = totalTime2;
        }
    }

    static class MetricHitIncrement extends MetricHit {
        public final int increment;

        public MetricHitIncrement(Metrics.MetricType metric, int increment2) {
            super(metric);
            this.increment = increment2;
        }
    }

    static class MetricHitString extends MetricHit {
        public final String text;

        public MetricHitString(Metrics.MetricType metric, String text2) {
            super(metric);
            this.text = text2;
        }
    }
}
