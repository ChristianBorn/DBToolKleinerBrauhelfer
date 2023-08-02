package com.sqlite.demo.controller;

import com.sqlite.demo.model.lager.Hefe;
import com.sqlite.demo.model.lager.Hopfen;
import com.sqlite.demo.model.lager.Malz;
import com.sqlite.demo.model.lager.WeitereZutaten;
import com.sqlite.demo.service.LagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class LagerController {
    @Autowired
    private LagerService lagerService;

    @GetMapping("/hefe")
    public List<Hefe> getHefe() {
        return lagerService.getHefe();
    }

    @GetMapping("/hefe/wert")
    public Float getWertHefe() {
        return lagerService.getWertHefe();
    }

    @GetMapping("/hopfen")
    public List<Hopfen> getHopfen() {
        return lagerService.getHopfen();
    }

    @GetMapping("/hopfen/wert")
    public Float getWertHopfen() {
        return lagerService.getWertHopfen();
    }

    @GetMapping("/malz")
    public List<Malz> getMalz() {
        return lagerService.getMalz();
    }

    @GetMapping("/malz/wert")
    public Float getWertMalz() {
        return lagerService.getWertMalz();
    }

    @GetMapping("/weitere-zutaten")
    public List<WeitereZutaten> getWeitereZutaten() {
        return lagerService.getWeitereZutaten();
    }

    @GetMapping("/weitere-zutaten/wert")
    public ResponseEntity<Object> getWertWeitereZutaten() {
        try {
            return new ResponseEntity<>(lagerService.getWertWeitereZutaten(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lager/wert")
    public Float getWertLager() {
        return lagerService.getWertLager();
    }
}
