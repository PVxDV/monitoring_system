package com.pvxdv.metrics_producer.service;

import com.pvxdv.metrics_producer.dto.MetricDto;

import java.util.List;

public interface MetricsService {
    void sendMetricsAuto();
    void sendMetrics(List<MetricDto> metrics);
}
