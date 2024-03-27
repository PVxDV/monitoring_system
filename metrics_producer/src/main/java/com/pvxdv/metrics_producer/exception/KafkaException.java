package com.pvxdv.metrics_producer.exception;

public class KafkaException extends RuntimeException{
    public KafkaException(String message) {
        super(message);
    }
}
