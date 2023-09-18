package com.sqlite.demo.service;


import com.sqlite.demo.model.ausruestung.Ausruestung;
import com.sqlite.demo.model.ausruestung.Geraet;
import com.sqlite.demo.repository.ausruestung.AusruestungRepository;
import com.sqlite.demo.repository.ausruestung.GeraeteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AusruestungService {
    @Autowired
    private GeraeteRepository geraeteRepository;
    @Autowired
    private AusruestungRepository ausruestungRepository;

    public List<Geraet> getGeraete() {
        return StreamSupport.stream(geraeteRepository.findAll().spliterator(), false)
                .toList();
    }

    public List<Ausruestung> getAusruestung() {
        return StreamSupport.stream(ausruestungRepository.findAll().spliterator(), false)
                .toList();
    }
}
