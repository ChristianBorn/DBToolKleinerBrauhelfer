package com.sqlite.demo.controller;

import com.sqlite.demo.model.gebinde.Gebinde;
import com.sqlite.demo.model.gebinde.GebindeDTO;
import com.sqlite.demo.service.GebindeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping
@ControllerAdvice
public class GebindeController {
    @Autowired
    private GebindeService gebindeService;

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    private ResponseEntity<String> handleException(RuntimeException ex) {
        String bodyOfResponse = "Benötigter Parameter ist nicht vorhanden oder fehlerhaft: "+ex.getCause();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/gebinde/add")
    public ResponseEntity<Iterable<Gebinde>> saveNewGebinde(@RequestBody GebindeDTO gebindeToAddDTO) {
        return new ResponseEntity<>(gebindeService.saveNewEmptyGebinde(gebindeToAddDTO), HttpStatus.CREATED);
    }
    @PutMapping("/gebinde/fill")
    public ResponseEntity<String> fillGebinde(@RequestParam String name, @RequestParam int number) throws JpaSystemException {
            gebindeService.fillGebinde(name, number);
            return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @PutMapping("/gebinde/empty")
    public ResponseEntity<String> emptyGebinde(@RequestParam String name, @RequestParam int number) {
            gebindeService.emptyGebinde(name, number);
            return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @GetMapping("/gebinde/kapazität")
    public ResponseEntity<String> getFreeCapacities() {
        return new ResponseEntity<>(gebindeService.getFreeCapacities(), HttpStatus.OK);
    }
    @GetMapping("/gebinde/kapazität/grouped")
    public ResponseEntity<List<Object>> getFreeCapacitiesGrouped() {
        return new ResponseEntity<>(gebindeService.getFreeCapacitiesGrouped(), HttpStatus.OK);
    }
    @GetMapping("/gebinde")
    public ResponseEntity<List<Gebinde>> getGebinde() {
        return new ResponseEntity<>(gebindeService.getGebinde(), HttpStatus.OK);
    }
}
