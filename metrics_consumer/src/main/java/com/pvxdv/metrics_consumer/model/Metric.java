package com.pvxdv.metrics_consumer.model;

import com.pvxdv.metrics_consumer.enums.MetricType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "metrics")
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MetricType type;
    @Column(name = "date")
    private Date date;
}
