package com.pvxdv.metrics_producer.scheduler;

import com.pvxdv.metrics_producer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetricsScheduler {

    private final MetricsService metricsService;
    @Scheduled(fixedDelay = 5_000)
    public void sendMetrics(){
        metricsService.sendMetricsAuto();
    }
}
