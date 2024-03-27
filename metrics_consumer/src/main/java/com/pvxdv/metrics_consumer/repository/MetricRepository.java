package com.pvxdv.metrics_consumer.repository;

import com.pvxdv.metrics_consumer.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MetricRepository extends JpaRepository<Metric, Long>, QuerydslPredicateExecutor<Metric> {
}
