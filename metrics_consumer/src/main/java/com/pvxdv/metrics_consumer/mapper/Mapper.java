package com.pvxdv.metrics_consumer.mapper;

public interface Mapper <F, T>{
    T map(F object);
}
