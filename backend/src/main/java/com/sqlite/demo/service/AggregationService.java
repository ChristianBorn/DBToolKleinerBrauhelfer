package com.sqlite.demo.service;

import com.sqlite.demo.model.aggregation.YearlyProduction;
import com.sqlite.demo.repository.aggregation.AggregationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AggregationService {
    @Autowired
    AggregationRepository aggregationRepository;

    public List<YearlyProduction> getYearlyProduction() {
        return aggregationRepository.getYearlyProduction();
    }
}
