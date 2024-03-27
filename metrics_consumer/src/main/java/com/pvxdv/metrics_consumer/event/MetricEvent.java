package com.pvxdv.metrics_consumer.event;



import com.pvxdv.metrics_consumer.enums.MetricType;
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
    private Date data;
}
