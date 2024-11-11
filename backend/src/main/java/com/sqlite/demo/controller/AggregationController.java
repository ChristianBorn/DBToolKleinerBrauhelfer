package com.sqlite.demo.controller;

import com.sqlite.demo.model.aggregation.YearlyProduction;
import com.sqlite.demo.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregation")

public class AggregationController {
    @Autowired
    AggregationService aggregationService;

    @GetMapping("/yearly-production")
    public List<YearlyProduction> getYearlyProduction() {
        return aggregationService.getYearlyProduction();
    }
}
