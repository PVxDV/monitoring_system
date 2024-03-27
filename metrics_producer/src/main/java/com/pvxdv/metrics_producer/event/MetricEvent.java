package com.pvxdv.metrics_producer.event;


import com.pvxdv.metrics_producer.enums.MetricType;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class MetricEvent {
    private String name;
    private String value;
    private MetricType type;
    private final Date data = new Date();
}
