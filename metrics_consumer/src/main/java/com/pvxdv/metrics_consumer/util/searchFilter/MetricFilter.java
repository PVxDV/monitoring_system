package com.pvxdv.metrics_consumer.util.searchFilter;

import com.pvxdv.metrics_consumer.enums.MetricType;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Value;

import java.util.Date;

@Value
public class MetricFilter {
    String name;
    MetricType type;
    Date date;
    @PositiveOrZero
    int offset;
    @Positive
    int limit;
}
