package com.sqlite.demo.controller;

import com.sqlite.demo.model.gebinde.Gebinde;
import com.sqlite.demo.model.gebinde.GebindeDTO;
import com.sqlite.demo.service.GebindeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GebindeController {
    @Autowired
    private GebindeService gebindeService;

    @PutMapping("/gebinde/add")
    public ResponseEntity<Iterable<Gebinde>> saveNewGebinde(@RequestBody GebindeDTO gebindeToAddDTO) {
        return new ResponseEntity<>(gebindeService.saveNewGebinde(gebindeToAddDTO), HttpStatus.CREATED);
    }
    @PutMapping("/gebinde/fill")
    public ResponseEntity<String> fillGebinde(@RequestParam String name,
                                                         @RequestParam int number) {
        gebindeService.fillGebinde(name, number);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @GetMapping("/gebinde/kapazität")
    public ResponseEntity<String> getFreeCapacities() {
        return new ResponseEntity<>(gebindeService.getFreeCapacities(), HttpStatus.OK);
    }
    @GetMapping("/gebinde")
    public ResponseEntity<List<Gebinde>> getGebinde() {
        return new ResponseEntity<>(gebindeService.getGebinde(), HttpStatus.OK);
    }
}
