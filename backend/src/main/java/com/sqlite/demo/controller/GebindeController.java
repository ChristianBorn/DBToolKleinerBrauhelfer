package com.sqlite.demo.controller;

import com.sqlite.demo.model.gebinde.Capacity;
import com.sqlite.demo.model.gebinde.Gebinde;
import com.sqlite.demo.model.gebinde.GebindeDTO;
import com.sqlite.demo.model.gebinde.GebindeFormDTO;
import com.sqlite.demo.service.GebindeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@ControllerAdvice
public class GebindeController {
    @Autowired
    private GebindeService gebindeService;


    @PutMapping("/gebinde/add")
    public ResponseEntity<Iterable<Gebinde>> saveNewGebinde(@RequestBody GebindeDTO gebindeToAddDTO) {
        return new ResponseEntity<>(gebindeService.saveNewEmptyGebinde(gebindeToAddDTO), HttpStatus.CREATED);
    }

    @PutMapping("/gebinde/fill")
    public ResponseEntity<String> fillGebinde(@RequestBody GebindeFormDTO body) throws JpaSystemException {
        gebindeService.fillGebinde(body);
        return new ResponseEntity<>(String.format("Erfolgreich befüllt: %d x %s", body.getNumber(), body.getName()) , HttpStatus.OK);
    }

    @PutMapping("/gebinde/empty")
    public ResponseEntity<String> emptyGebinde(@RequestBody GebindeFormDTO body) throws JpaSystemException {
        gebindeService.emptyGebinde(body);
        return new ResponseEntity<>(String.format("Erfolgreich geleert: %d x %s", body.getNumber(), body.getName()), HttpStatus.OK);
    }

    @GetMapping("/gebinde/kapazität")
    public ResponseEntity<String> getFreeCapacities() {
        return new ResponseEntity<>(gebindeService.getFreeCapacities(), HttpStatus.OK);
    }

    @GetMapping("/gebinde/kapazität/grouped")
    public ResponseEntity<List<Capacity>> getFreeCapacitiesGrouped() {
        return new ResponseEntity<>(gebindeService.getFreeCapacitiesGrouped(), HttpStatus.OK);
    }

    @GetMapping("/gebinde")
    public ResponseEntity<List<Gebinde>> getGebinde() {
        return new ResponseEntity<>(gebindeService.getGebinde(), HttpStatus.OK);
    }

    @DeleteMapping("/gebinde/delete/{name}")
    public ResponseEntity<String> deleteGebinde(@PathVariable String name) throws NotFoundException {
        gebindeService.deleteGebinde(name);
        return new ResponseEntity<>(String.format("Gebinde mit Name \"%s\" gelöscht", name), HttpStatus.OK);
    }
}
