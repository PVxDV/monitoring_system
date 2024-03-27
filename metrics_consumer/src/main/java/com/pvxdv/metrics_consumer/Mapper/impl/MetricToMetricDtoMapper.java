package com.pvxdv.metrics_consumer.Mapper.impl;

import com.pvxdv.metrics_consumer.Mapper.Mapper;
import com.pvxdv.metrics_consumer.dto.MetricDto;
import com.pvxdv.metrics_consumer.model.Metric;
import org.springframework.stereotype.Component;

@Component
public class MetricToMetricDtoMapper implements Mapper<Metric, MetricDto> {
    @Override
    public MetricDto map(Metric metric) {
        return new MetricDto(metric.getId(), metric.getName(), metric.getValue(), metric.getType(), metric.getDate());
    }
}
