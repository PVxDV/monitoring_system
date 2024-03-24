package com.pvxdv.metrics_producer.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Schema(description = "Metric object")
@RequiredArgsConstructor
@Getter
@ToString
public class MetricDto {
    @Schema(description = "metric_name", example = "disk.free")
    @NotBlank
    private final String name;
    @Schema(description = "metric_value", example = "100000")
    @NotBlank
    private final String value;
    @Schema(description = "available metric type : APPLICATION, DISK, JVM, SYSTEM", example = "DISK")
    @Pattern(regexp = "APPLICATION|DISK|JVM|SYSTEM", message = "available metric type : APPLICATION, DISK, JVM, SYSTEM")
    private final MetricType type;
    @Schema(description = "metric_timestamp", example = "Sun Mar 24 21:50:30 MSK 2024")
    private final Date data = new Date();

    public enum MetricType {
        APPLICATION,
        DISK,
        JVM,
        SYSTEM
    }
}
