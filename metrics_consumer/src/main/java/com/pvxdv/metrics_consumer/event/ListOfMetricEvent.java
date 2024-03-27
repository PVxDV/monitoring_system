package com.pvxdv.metrics_consumer.event;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ListOfMetricEvent {
    private List<MetricEvent> metricEvents;

}
