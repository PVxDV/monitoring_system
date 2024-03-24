package com.pvxdv.metrics_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MetricsProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsProducerApplication.class, args);
    }

}
