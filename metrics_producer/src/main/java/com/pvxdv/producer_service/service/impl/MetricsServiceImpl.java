package com.pvxdv.producer_service.service.impl;

import com.pvxdv.producer_service.dto.MetricDto;
import com.pvxdv.producer_service.dto.MetricDto.MetricType;
import com.pvxdv.producer_service.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {
    private final MetricsEndpoint metricsEndpoint;
    @Override
    public void sendMetricsAuto() {
        List<MetricDto> metrics = new ArrayList<>(List.of(
                getDiskFree(),
                getDiskTotal(),
                getJvmBufferMemoryUsed(),
                getJvmBufferTotalCapacity(),
                getJvmMemoryUsed(),
                getJvmThreadsLive(),
                getSystemCpuUsage()
        ));

        System.out.println(metrics);

        //todo
        // отправить метрики в кафку
    }

    @Override
    public void sendMetrics(List<MetricDto> metricDto) {
        //todo
        // отправить метрики в кафку
    }

    private MetricDto getDiskFree(){
        String value = metricsEndpoint.metric("disk.free", null)
                .getMeasurements().getFirst().toString();
        return new MetricDto("disk.free", value, MetricType.DISK);
    }

    private MetricDto getDiskTotal(){
        String value = metricsEndpoint.metric("disk.total", null)
                .getMeasurements().getFirst().toString();
        return new MetricDto("disk.total", value, MetricType.DISK);
    }

    private MetricDto getJvmBufferMemoryUsed(){
        String value = metricsEndpoint.metric("jvm.buffer.memory.used", null)
                .getMeasurements().getFirst().toString();
        return new MetricDto("jvm.buffer.memory.used", value, MetricType.JVM);
    }

    private MetricDto getJvmBufferTotalCapacity(){
        String value = metricsEndpoint.metric("jvm.buffer.total.capacity", null)
                .getMeasurements().getFirst().toString();
        return new MetricDto("jvm.buffer.total.capacity", value, MetricType.JVM);
    }

    private MetricDto  getJvmMemoryUsed(){
        String value = metricsEndpoint.metric( "jvm.memory.used", null)
                .getMeasurements().getFirst().toString();
        return new MetricDto( "jvm.memory.used", value, MetricType.JVM);
    }

    private MetricDto getJvmThreadsLive(){
        String value = metricsEndpoint.metric("jvm.threads.live", null)
                .getMeasurements().getFirst().toString();
        return new MetricDto("jvm.threads.live", value, MetricType.JVM);
    }

    private MetricDto getSystemCpuUsage(){
        String value = metricsEndpoint.metric("system.cpu.usage", null)
                .getMeasurements().getFirst().toString();
        return new MetricDto("system.cpu.usage", value, MetricType.SYSTEM);
    }
}
