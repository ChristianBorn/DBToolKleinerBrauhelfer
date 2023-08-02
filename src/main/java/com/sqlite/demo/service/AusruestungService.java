package com.sqlite.demo.service;


import com.sqlite.demo.model.ausruestung.Ausruestung;
import com.sqlite.demo.model.ausruestung.Geraete;
import com.sqlite.demo.repository.ausruestung.AusruestungRepository;
import com.sqlite.demo.repository.ausruestung.GeraeteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
public class AusruestungService {
    @Autowired
    private GeraeteRepository geraeteRepository;
    @Autowired
    private AusruestungRepository ausruestungRepository;

    public List<Geraete> getGeraete() {
        return StreamSupport.stream(geraeteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Ausruestung> getAusruestung() {
        return StreamSupport.stream(ausruestungRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
