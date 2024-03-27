package com.pvxdv.metrics_consumer.service.impl;

import com.pvxdv.metrics_consumer.Mapper.impl.MetricEventToMetricMapper;
import com.pvxdv.metrics_consumer.event.ListOfMetricEvent;
import com.pvxdv.metrics_consumer.model.Metric;
import com.pvxdv.metrics_consumer.repository.MetricRepository;
import com.pvxdv.metrics_consumer.service.ListOfMetricEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@KafkaListener(topics = "metrics-topic")
@RequiredArgsConstructor
public class ListOfMetricEventServiceImpl implements ListOfMetricEventService {
    private final MetricRepository metricRepository;
    private final MetricEventToMetricMapper metricEventToMetricMapper;

    @KafkaHandler
    @Override
    public void handleListOfMetricEvent(ListOfMetricEvent listOfMetricEvent) {
        log.info("Received: %s".formatted(listOfMetricEvent));
        List<Metric> metricsToSave = listOfMetricEvent.getMetricEvents().stream()
                        .map(metricEventToMetricMapper::map).toList();
        metricRepository.saveAll(metricsToSave);
        log.info("Saved: %s".formatted(metricsToSave));
    }
}
