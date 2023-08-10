package com.sqlite.demo.service;

import com.sqlite.demo.model.gebinde.Gebinde;
import com.sqlite.demo.model.gebinde.GebindeDTO;
import com.sqlite.demo.repository.gebinde.GebindeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class GebindeService {
    @Autowired
    private GebindeRepository gebindeRepository;

    public Gebinde saveNewGebinde(GebindeDTO gebindeToAddDTO) {
        Gebinde gebindeToAdd = new Gebinde(gebindeToAddDTO.getName(),
                gebindeToAddDTO.getAnzahl(),
                gebindeToAddDTO.getFassungsvermoegen(),
                gebindeToAddDTO.getStatus().getDisplayName());
        return gebindeRepository.save(gebindeToAdd);
    }
    public String getFreeCapacities() {
        return "Freie Kapazitäten: " + gebindeRepository.getFreeCapacities() + " Liter";
    }
    public List<Gebinde> getGebinde() {
        return StreamSupport.stream(gebindeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
