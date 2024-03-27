package com.pvxdv.metrics_producer.api;

import com.pvxdv.metrics_producer.dto.ListOfMetricDto;
import com.pvxdv.metrics_producer.exception.KafkaException;
import com.pvxdv.metrics_producer.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MetricsController", description = "Send some metrics to kafka server")
@Slf4j
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
    public ResponseEntity<String> sendMetrics(@RequestBody @Valid ListOfMetricDto metrics) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("You have sent %d metrics to kafka. MetaData: %s"
                            .formatted(metrics.metrics().size(), metricsService.sendMetrics(metrics.metrics())));
        } catch (Exception e) {
            log.error("Failed to send metrics to kafka, message:%s".formatted(e.getMessage()));
            throw new KafkaException("Failed to send metrics to kafka, message:%s".formatted(e.getMessage()));
        }
    }
}
