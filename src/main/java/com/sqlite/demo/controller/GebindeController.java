package com.sqlite.demo.controller;

import com.sqlite.demo.model.Gebinde;
import com.sqlite.demo.model.GebindeDTO;
import com.sqlite.demo.service.GebindeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GebindeController {
    @Autowired
    private GebindeService gebindeService;

    @PostMapping("/gebinde/add")
    public ResponseEntity<Gebinde> saveNewGebinde(@RequestBody GebindeDTO gebindeToAddDTO) {
        return new ResponseEntity<>(gebindeService.saveNewGebinde(gebindeToAddDTO), HttpStatus.CREATED);
    }
}
