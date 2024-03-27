package com.pvxdv.metrics_producer.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Metric type")
public enum MetricType {
    APPLICATION,
    DISK,
    JVM,
    SYSTEM
}
