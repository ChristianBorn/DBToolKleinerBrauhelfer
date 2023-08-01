package com.sqlite.demo.service;


import com.sqlite.demo.model.Ausruestung;
import com.sqlite.demo.model.Geraete;
import com.sqlite.demo.model.lager.Hefe;
import com.sqlite.demo.model.lager.Hopfen;
import com.sqlite.demo.model.lager.Malz;
import com.sqlite.demo.model.lager.WeitereZutaten;
import com.sqlite.demo.repository.*;
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
    @Autowired
    private HopfenRepository hopfenRepository;
    @Autowired
    private MalzRepository malzRepository;
    @Autowired
    private WeitereZutatenRepository weitereZutatenRepository;

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
        return hefeRepository.findPricesOfStockedItems();
    }

    public List<Hopfen> getHopfen() {
        return StreamSupport.stream(hopfenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertHopfen() {
        return hopfenRepository.findPricesOfStockedItems();
    }

    public List<Malz> getMalz() {
        return StreamSupport.stream(malzRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertMalz() {
        return malzRepository.findPricesOfStockedItems();
    }

    public List<WeitereZutaten> getWeitereZutaten() {
        return StreamSupport.stream(weitereZutatenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
