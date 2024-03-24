package com.pvxdv.metrics_producer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "List of metrics")
public record ListOfMetricDto(@NotNull List<MetricDto> metrics) {
}
