package com.sqlite.demo.controller;

import com.sqlite.demo.model.Ausruestung;
import com.sqlite.demo.model.Geraete;
import com.sqlite.demo.model.Hefe;
import com.sqlite.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("/geraete")
    public List<Geraete> getGeraete() {
        return service.getGeraete();
    }
    @GetMapping("/ausruestung")
    public List<Ausruestung> getAusruestung() {
        return service.getAusruestung();
    }
    @GetMapping("/hefe")
    public List<Hefe> getHefe() {
        return service.getHefe();
    }
    @GetMapping("/hefe/wert")
    public Float getWertHefe() {
        return service.getWertHefe();
    }
}
