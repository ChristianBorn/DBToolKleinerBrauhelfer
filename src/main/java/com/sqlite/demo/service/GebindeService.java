package com.sqlite.demo.service;

import com.sqlite.demo.model.Gebinde;
import com.sqlite.demo.model.GebindeDTO;
import com.sqlite.demo.repository.GebindeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class GebindeService {
    @Autowired
    private GebindeRepository gebindeRepository;

    public Gebinde saveNewGebinde(GebindeDTO gebindeToAddDTO) {
        Gebinde gebindeToAdd = new Gebinde(gebindeToAddDTO.getName(),
                gebindeToAddDTO.getAnzahl(),
                gebindeToAddDTO.getFassungsvermoegen(),
                gebindeToAddDTO.getStatus());
        return gebindeRepository.save(gebindeToAdd);
    }
}
