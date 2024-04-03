package com.pvxdv.metrics_consumer.mapper.impl;

import com.pvxdv.metrics_consumer.mapper.Mapper;
import com.pvxdv.metrics_consumer.event.MetricEvent;
import com.pvxdv.metrics_consumer.model.Metric;
import org.springframework.stereotype.Component;

@Component
public class MetricEventToMetricMapper implements Mapper<MetricEvent, Metric> {
    @Override
    public Metric map(MetricEvent metricEvent) {
        return new Metric(null, metricEvent.getName(), metricEvent.getValue(), metricEvent.getType(), metricEvent.getData());
    }
}
