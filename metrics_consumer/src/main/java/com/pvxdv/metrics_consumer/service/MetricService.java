package com.pvxdv.metrics_consumer.service;

import com.pvxdv.metrics_consumer.dto.MetricDto;
import com.pvxdv.metrics_consumer.util.searchFilter.MetricFilter;
import org.springframework.data.domain.Page;

public interface MetricService {
    Page<MetricDto> getMetricsByFiler(MetricFilter metricFilterDTO);
    MetricDto findMetricById(Long metricId);
}
