package com.pvxdv.metrics_producer.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ListOfMetricEvent {
    private List<MetricEvent> metricEvents;

}
