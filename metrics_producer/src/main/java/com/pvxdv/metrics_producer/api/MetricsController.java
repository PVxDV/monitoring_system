package com.pvxdv.metrics_producer.api;

import com.pvxdv.metrics_producer.dto.ListOfMetricDto;
import com.pvxdv.metrics_producer.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MetricsController", description = "Send some metrics to kafka server")
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;
    @Operation(
            summary = "Send custom metrics to kafka server",
            description = "Send custom metrics to kafka server"
    )
    @PostMapping("/metrics")
    @ResponseStatus(HttpStatus.OK)
    public void sendMetrics (@RequestBody @Valid ListOfMetricDto metrics){
        metricsService.sendMetrics(metrics.metrics());
    }
}
