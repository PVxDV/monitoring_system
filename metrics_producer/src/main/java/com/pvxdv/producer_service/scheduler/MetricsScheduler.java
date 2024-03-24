package com.pvxdv.producer_service.scheduler;

import com.pvxdv.producer_service.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetricsScheduler {

    private final MetricsService metricsService;
    @Scheduled(fixedDelay = 3_000)
    public void sendMetrics(){
        metricsService.sendMetricsAuto();
    }
}
