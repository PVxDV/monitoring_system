package com.pvxdv.producer_service.service;

import com.pvxdv.producer_service.dto.MetricDto;

import java.util.List;

public interface MetricsService {
    void sendMetricsAuto();
    void sendMetrics(List<MetricDto> metricDtos);
}
