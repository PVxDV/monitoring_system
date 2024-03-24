package com.pvxdv.metrics_producer.service.impl;

import com.pvxdv.metrics_producer.dto.MetricDto;
import com.pvxdv.metrics_producer.dto.MetricDto.MetricType;
import com.pvxdv.metrics_producer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {
    private final MetricsEndpoint metricsEndpoint;

    @Override
    public void sendMetricsAuto() {

        List<MetricDto> metrics = new ArrayList<>();

        metrics.add(getApplicationReadyTime());
        metrics.add(getApplicationStartedTime());
        metrics.add(getDiskFree());
        metrics.add(getDiskTotal());
        metrics.add(getJvmBufferMemoryUsed());
        metrics.add(getJvmBufferTotalCapacity());
        metrics.add(getJvmMemoryUsed());
        metrics.add(getJvmThreadsLive());
        metrics.add(getSystemCpuUsage());

        metrics = metrics.stream().filter(Objects::nonNull).toList();

        System.out.println(metrics);

        //todo
        // отправить метрики в кафку
    }

    @Override
    public void sendMetrics(List<MetricDto> metricDto) {
        //todo
        // отправить метрики в кафку
    }


    private MetricDto getDiskFree() {
        try {
            String value = metricsEndpoint.metric("disk.free", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("disk.free", value, MetricType.DISK);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricDto getDiskTotal() {
        try {
            String value = metricsEndpoint.metric("disk.total", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("disk.total", value, MetricType.DISK);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricDto getJvmBufferMemoryUsed() {
        try {
            String value = metricsEndpoint.metric("jvm.buffer.memory.used", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("jvm.buffer.memory.used", value, MetricType.JVM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricDto getJvmBufferTotalCapacity() {
        try {
            String value = metricsEndpoint.metric("jvm.buffer.total.capacity", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("jvm.buffer.total.capacity", value, MetricType.JVM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricDto getJvmMemoryUsed() {
        try {
            String value = metricsEndpoint.metric("jvm.memory.used", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("jvm.memory.used", value, MetricType.JVM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricDto getJvmThreadsLive() {
        try {
            String value = metricsEndpoint.metric("jvm.threads.live", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("jvm.threads.live", value, MetricType.JVM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricDto getSystemCpuUsage() {
        try {
            String value = metricsEndpoint.metric("system.cpu.usage", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("system.cpu.usage", value, MetricType.SYSTEM);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricDto getApplicationReadyTime() {
        try {
            String value = metricsEndpoint.metric("application.ready.time", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("application.ready.time", value, MetricType.APPLICATION);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private MetricDto getApplicationStartedTime() {
        try {
            String value = metricsEndpoint.metric("application.started.time", null)
                    .getMeasurements().getFirst().toString();
            return new MetricDto("application.started.time", value, MetricType.APPLICATION);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
