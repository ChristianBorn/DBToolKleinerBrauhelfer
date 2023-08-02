package com.sqlite.demo.service;

import com.sqlite.demo.model.sud.Sud;
import com.sqlite.demo.repository.sud.SudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SudService {

    @Autowired
    private SudRepository sudRepository;

    public List<Sud> getSud() {
        return StreamSupport.stream(sudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
