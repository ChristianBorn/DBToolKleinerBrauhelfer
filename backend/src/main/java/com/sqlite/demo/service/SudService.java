package com.sqlite.demo.service;

import com.sqlite.demo.model.sud.Sud;
import com.sqlite.demo.repository.sud.SudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class SudService {
    private SudRepository sudRepository;

    public List<Sud> getSud() {
        return StreamSupport.stream(sudRepository.findAll().spliterator(), false)
                .map(sud -> {
                    if (sud.getAbfuelldatum() == null) sud.setAbgefuellteMenge(0f);
                    return sud;
                })
                .collect(Collectors.toList());
    }
}
