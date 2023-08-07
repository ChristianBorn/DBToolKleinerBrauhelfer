package com.sqlite.demo.service;

import com.sqlite.demo.repository.GebindeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class GebindeService {
    @Autowired
    private GebindeRepository gebindeRepository;
}
