package com.pvxdv.metrics_consumer.api;


import com.pvxdv.metrics_consumer.dto.MetricDto;
import com.pvxdv.metrics_consumer.dto.PageResponseDto;
import com.pvxdv.metrics_consumer.enums.MetricType;
import com.pvxdv.metrics_consumer.service.MetricService;
import com.pvxdv.metrics_consumer.util.searchFilter.MetricFilter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/metrics")
@RequiredArgsConstructor
public class MetricController {
    private final MetricService metricService;

    @Operation(
            summary = "Get all metrics with filtration and pagination",
            description = "Allow filter by name (accepts a regular expression), " +
                    "by type (accepts a regular expression), " +
                    "and by current date."
    )
    @GetMapping()
    public ResponseEntity<PageResponseDto<MetricDto>> getProductsByFilter(@RequestParam Optional<String> name,
                                                                          @RequestParam Optional<MetricType> type,
                                                                          @RequestParam Optional<Date> date,
                                                                          @RequestParam @Min(0) Integer offset,
                                                                          @RequestParam @Min(1) Integer limit) {
        MetricFilter metricFilter = new MetricFilter(name.orElse(null), type.orElse(null),
                date.orElse(null), offset, limit);
        Page<MetricDto> page = metricService.getMetricsByFiler(metricFilter);
        return new ResponseEntity<>(PageResponseDto.of(page), HttpStatus.OK);
    }

    @Operation(
            summary = "Get current metric",
            description = "Get current metric by metric_id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<MetricDto> getMetricById(@PathVariable("id") @Min(0) Long id) {
        return new ResponseEntity<>(metricService.findMetricById(id), HttpStatus.OK);
    }
}
