package com.pvxdv.metrics_producer.config;

import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfig {
    @Bean
    @ConditionalOnBean(io.micrometer.core.instrument.MeterRegistry.class)
    @ConditionalOnMissingBean
    public MetricsEndpoint metricsEndpoint(io.micrometer.core.instrument.MeterRegistry registry){
        return new MetricsEndpoint(registry);
    }
}
