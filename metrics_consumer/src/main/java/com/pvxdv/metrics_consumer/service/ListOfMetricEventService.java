package com.pvxdv.metrics_consumer.service;

import com.pvxdv.metrics_consumer.event.ListOfMetricEvent;

public interface ListOfMetricEventService {
    void handleListOfMetricEvent (ListOfMetricEvent listOfMetricEvent);
}
