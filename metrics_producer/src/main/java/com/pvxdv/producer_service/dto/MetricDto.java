package com.pvxdv.producer_service.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@RequiredArgsConstructor
@Getter
@ToString
public class MetricDto {
    private final String name;
    private final String value;
    private final MetricType type;
    private final LocalDate data = LocalDate.now();

    public enum MetricType {
        APPLICATION,
        DISK,
        JVM,
        SYSTEM
    }
}
