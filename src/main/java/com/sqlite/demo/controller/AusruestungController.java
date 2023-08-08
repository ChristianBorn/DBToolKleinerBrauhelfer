package com.sqlite.demo.controller;

import com.sqlite.demo.model.ausruestung.Ausruestung;
import com.sqlite.demo.model.ausruestung.Geraet;
import com.sqlite.demo.service.AusruestungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class AusruestungController {
    @Autowired
    private AusruestungService ausruestungService;

    @GetMapping("/geraete")
    public List<Geraet> getGeraete() {
        return ausruestungService.getGeraete();
    }

    @GetMapping("/ausruestung")
    public List<Ausruestung> getAusruestung() {
        return ausruestungService.getAusruestung();
    }
}
