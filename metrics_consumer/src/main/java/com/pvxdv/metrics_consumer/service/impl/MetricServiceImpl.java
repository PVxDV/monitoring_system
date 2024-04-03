package com.pvxdv.metrics_consumer.service.impl;

import com.pvxdv.metrics_consumer.mapper.impl.MetricToMetricDtoMapper;
import com.pvxdv.metrics_consumer.dto.MetricDto;
import com.pvxdv.metrics_consumer.exception.ResourceNotFoundException;
import com.pvxdv.metrics_consumer.repository.MetricRepository;
import com.pvxdv.metrics_consumer.service.MetricService;
import com.pvxdv.metrics_consumer.util.searchFilter.MetricFilter;
import com.pvxdv.metrics_consumer.util.searchFilter.QPredicates;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.pvxdv.metrics_consumer.model.QMetric.metric;

@Slf4j
@Service
@AllArgsConstructor
public class MetricServiceImpl implements MetricService {
    private final MetricRepository metricRepository;
    private final MetricToMetricDtoMapper metricToMetricDtoMapper;
    @Override
    public Page<MetricDto> getMetricsByFiler(MetricFilter filterDTO) {
        var predicate = QPredicates.builder();
        predicate.add(filterDTO.getName(), metric.name::containsIgnoreCase);
        predicate.add(filterDTO.getDate(), metric.date::eq);
        predicate.add(filterDTO.getType(), metric.type::eq);

        return metricRepository.findAll(predicate.build(), PageRequest.of(filterDTO.getOffset(), filterDTO.getLimit()))
                .map(metricToMetricDtoMapper::map);
    }

    @Override
    public MetricDto findMetricById(Long metricId) {
        return metricToMetricDtoMapper.map(metricRepository.findById(metricId)
                .orElseThrow(()-> new ResourceNotFoundException("Metric with id=%d not found".formatted(metricId))));
    }
}
