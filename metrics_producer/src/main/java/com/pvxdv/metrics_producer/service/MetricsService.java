package com.pvxdv.metrics_producer.service;

import com.pvxdv.metrics_producer.dto.MetricDto;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface MetricsService {
    void sendMetricsAuto();

    String sendMetrics(List<MetricDto> metricEvents) throws ExecutionException, InterruptedException;
}
