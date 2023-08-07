package com.sqlite.demo.controller;

import com.sqlite.demo.service.GebindeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GebindeController {
    @Autowired
    private GebindeService gebindeService;
}
