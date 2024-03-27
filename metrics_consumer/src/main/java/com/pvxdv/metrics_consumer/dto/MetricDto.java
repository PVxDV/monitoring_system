package com.pvxdv.metrics_consumer.dto;

import com.pvxdv.metrics_consumer.enums.MetricType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Date;

@Schema(description = "Metric object")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class MetricDto {
    @Schema(description = "metric_id", example = "0")
    @PositiveOrZero
    private Long id;
    @Schema(description = "metric_name", example = "disk.free")
    @NotBlank
    private String name;
    @Schema(description = "metric_value", example = "100000")
    @NotBlank
    private String value;
    @Schema(description = "available metric type : APPLICATION, DISK, JVM, SYSTEM", example = "DISK")
    @Pattern(regexp = "APPLICATION|DISK|JVM|SYSTEM", message = "available metric type : APPLICATION, DISK, JVM, SYSTEM")
    private MetricType type;
    @Schema(description = "metric_timestamp", example = "Sun Mar 24 21:50:30 MSK 2024")
    private Date date;
}
