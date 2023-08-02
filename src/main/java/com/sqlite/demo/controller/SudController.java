package com.sqlite.demo.controller;

import com.sqlite.demo.model.sud.Sud;
import com.sqlite.demo.service.SudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SudController {
    @Autowired
    private SudService sudService;

    @GetMapping("/sud")
    public List<Sud> getSud() {
        return sudService.getSud();
    }

}
