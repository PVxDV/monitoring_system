package com.pvxdv.metrics_producer.service.impl;

import com.pvxdv.metrics_producer.dto.MetricDto;
import com.pvxdv.metrics_producer.enums.MetricType;
import com.pvxdv.metrics_producer.event.ListOfMetricEvent;
import com.pvxdv.metrics_producer.event.MetricEvent;
import com.pvxdv.metrics_producer.mapper.impl.MetricDtoToMetricEventMapper;
import com.pvxdv.metrics_producer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {
    private final MetricsEndpoint metricsEndpoint;
    private final KafkaTemplate<String, ListOfMetricEvent> kafkaTemplate;
    private final MetricDtoToMetricEventMapper metricDtoToMetricEventMapper;

    @Override
    public void sendMetricsAuto() {
        List<MetricEvent> metricsToSend = new ArrayList<>(9);

        metricsToSend.add(getApplicationReadyTime());
        metricsToSend.add(getApplicationStartedTime());
        metricsToSend.add(getDiskFree());
        metricsToSend.add(getDiskTotal());
        metricsToSend.add(getJvmBufferMemoryUsed());
        metricsToSend.add(getJvmBufferTotalCapacity());
        metricsToSend.add(getJvmMemoryUsed());
        metricsToSend.add(getJvmThreadsLive());
        metricsToSend.add(getSystemCpuUsage());
        metricsToSend = metricsToSend.stream().filter(Objects::nonNull).toList();
        ListOfMetricEvent listOfMetricEvent = new ListOfMetricEvent(metricsToSend);

        CompletableFuture<SendResult<String, ListOfMetricEvent>> future = kafkaTemplate
                .send("metrics-topic", listOfMetricEvent);

        List<MetricEvent> finalMetricEvents = metricsToSend;
        future.whenComplete((result, exception) -> {
            if(exception != null) {
                log.info("Failed to auto send metrics {} to kafka", result.getRecordMetadata());
            } else {
                StringBuilder metadata = new StringBuilder();
                metadata.append("topic: %s, ".formatted(result.getRecordMetadata().topic()));
                metadata.append("partition: %s, ".formatted(result.getRecordMetadata().partition()));
                metadata.append("offset: %s, ".formatted(result.getRecordMetadata().offset()));
                log.info("Auto send sent %s metrics to kafka. MetaData: %s".formatted(finalMetricEvents, metadata));
            }
        });
    }

    @Override
    public String sendMetrics(List<MetricDto> metricEvents) throws ExecutionException, InterruptedException {
        List<MetricEvent> metricsToSend = metricEvents.stream().map(metricDtoToMetricEventMapper::map).toList();
        ListOfMetricEvent listOfMetricEvent = new ListOfMetricEvent(metricsToSend);

        SendResult<String, ListOfMetricEvent> result = kafkaTemplate
                .send("metrics-topic", listOfMetricEvent).get();

        StringBuilder response = new StringBuilder();
        response.append("topic: %s, ".formatted(result.getRecordMetadata().topic()));
        response.append("partition: %s, ".formatted(result.getRecordMetadata().partition()));
        response.append("offset: %s, ".formatted(result.getRecordMetadata().offset()));

        return response.toString();
    }


    private MetricEvent getDiskFree() {
        try {
            String value = metricsEndpoint.metric("disk.free", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("disk.free", value, MetricType.DISK);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricEvent getDiskTotal() {
        try {
            String value = metricsEndpoint.metric("disk.total", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("disk.total", value, MetricType.DISK);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricEvent getJvmBufferMemoryUsed() {
        try {
            String value = metricsEndpoint.metric("jvm.buffer.memory.used", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("jvm.buffer.memory.used", value, MetricType.JVM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricEvent getJvmBufferTotalCapacity() {
        try {
            String value = metricsEndpoint.metric("jvm.buffer.total.capacity", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("jvm.buffer.total.capacity", value, MetricType.JVM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricEvent getJvmMemoryUsed() {
        try {
            String value = metricsEndpoint.metric("jvm.memory.used", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("jvm.memory.used", value, MetricType.JVM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricEvent getJvmThreadsLive() {
        try {
            String value = metricsEndpoint.metric("jvm.threads.live", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("jvm.threads.live", value, MetricType.JVM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricEvent getSystemCpuUsage() {
        try {
            String value = metricsEndpoint.metric("system.cpu.usage", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("system.cpu.usage", value, MetricType.SYSTEM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricEvent getApplicationReadyTime() {
        try {
            String value = metricsEndpoint.metric("application.ready.time", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("application.ready.time", value, MetricType.APPLICATION);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricEvent getApplicationStartedTime() {
        try {
            String value = metricsEndpoint.metric("application.started.time", null)
                    .getMeasurements().getFirst().toString();
            return new MetricEvent("application.started.time", value, MetricType.APPLICATION);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
