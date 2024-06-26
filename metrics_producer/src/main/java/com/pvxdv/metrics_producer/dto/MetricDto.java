package com.pvxdv.metrics_producer.dto;


import com.pvxdv.metrics_producer.enums.MetricType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Schema(description = "Metric object")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class MetricDto {
    @Schema(description = "metric_name", example = "disk.free")
    @NotBlank
    private String name;
    @Schema(description = "metric_value", example = "100000")
    @NotBlank
    private String value;
    @Schema(description = "available metric type : APPLICATION, DISK, JVM, SYSTEM", example = "DISK")
    @Pattern(regexp = "APPLICATION|DISK|JVM|SYSTEM", message = "available metric type : APPLICATION, DISK, JVM, SYSTEM")
    private MetricType type;
}
