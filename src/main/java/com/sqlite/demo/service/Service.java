package com.sqlite.demo.service;


import com.sqlite.demo.model.Ausruestung;
import com.sqlite.demo.model.Geraete;
import com.sqlite.demo.model.Hefe;
import com.sqlite.demo.repository.AusruestungRepository;
import com.sqlite.demo.repository.GeraeteRepository;
import com.sqlite.demo.repository.HefeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private GeraeteRepository geraeteRepository;
    @Autowired
    private AusruestungRepository ausruestungRepository;
    @Autowired
    private HefeRepository hefeRepository;

    public List<Geraete> getGeraete() {
        return StreamSupport.stream(geraeteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Ausruestung> getAusruestung() {
        return StreamSupport.stream(ausruestungRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Hefe> getHefe() {
        return StreamSupport.stream(hefeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertHefe() {
        return hefeRepository.findPricesOfStockedItems().stream().reduce(0.0F, Float::sum);
    }
}
