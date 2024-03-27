package com.pvxdv.metrics_producer.mapper.impl;

import com.pvxdv.metrics_producer.dto.MetricDto;
import com.pvxdv.metrics_producer.event.MetricEvent;
import com.pvxdv.metrics_producer.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MetricDtoToMetricEventMapper implements Mapper<MetricDto, MetricEvent> {
    @Override
    public MetricEvent map(MetricDto metricDto) {
        return new MetricEvent(metricDto.getName(), metricDto.getValue(), metricDto.getType());
    }
}
