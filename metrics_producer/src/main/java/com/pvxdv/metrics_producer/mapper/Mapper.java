package com.pvxdv.metrics_producer.mapper;

public interface Mapper<F, T>{
    T map(F object);
}
