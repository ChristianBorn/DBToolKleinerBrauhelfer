package com.sqlite.demo.service;

import com.sqlite.demo.model.gebinde.Capacity;
import com.sqlite.demo.model.gebinde.Gebinde;
import com.sqlite.demo.model.gebinde.GebindeDTO;
import com.sqlite.demo.model.gebinde.GebindeFormDTO;
import com.sqlite.demo.repository.gebinde.GebindeRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class GebindeService {
    @Autowired
    private GebindeRepository gebindeRepository;

    public Iterable<Gebinde> saveNewEmptyGebinde(GebindeDTO gebindeToAddDTO) {
        Gebinde gebindeToAdd = new Gebinde(gebindeToAddDTO.getName(),
                gebindeToAddDTO.getAnzahl(),
                gebindeToAddDTO.getFassungsvermoegen(),
                gebindeToAddDTO.getStatus().getDisplayName());

        List<Gebinde> gebindePairToSave = new ArrayList<>(
                List.of(gebindeToAdd,
                        gebindeToAdd
                                .gebindeWithStatus("voll")
                                .gebindeWithAnzahl(0)));
        return gebindeRepository.saveAll(gebindePairToSave);
    }

    public String getFreeCapacities() {
        return "Freie Kapazitäten: " + gebindeRepository.getFreeCapacities() + " Liter";
    }

    public List<Capacity> getFreeCapacitiesGrouped() {
        return gebindeRepository.getFreeCapacitiesGrouped();
    }

    public List<Gebinde> getGebinde() {
        return StreamSupport.stream(gebindeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void fillGebinde(GebindeFormDTO body) throws IllegalArgumentException {
        int numberOfGebindeToFill = body.getNumber();
        String gebindeNameToAlter = body.getName();
        if (numberOfGebindeToFill > 0 && gebindeRepository.existsByName(gebindeNameToAlter)) {
            try {
                gebindeRepository.reduceEmptyByName(gebindeNameToAlter, numberOfGebindeToFill);
                gebindeRepository.increaseFullByName(gebindeNameToAlter, numberOfGebindeToFill);
            } catch (JpaSystemException e) {
                throw new IllegalArgumentException("Menge zu füllender Gebinde ist größer als die Zahl der verfügbaren Gebinde");
            }
        } else if (numberOfGebindeToFill <= 0) {
            throw new IllegalArgumentException("Die Anzahl der zu füllenden Gebinde muss größer 0 sein");
        } else {
            throw new IllegalArgumentException("Das angegebene Gebinde existiert nicht unter diesem Namen");
        }
    }

    public void emptyGebinde(GebindeFormDTO body) throws IllegalArgumentException {
        int numberOfGebindeToEmpty = body.getNumber();
        String gebindeNameToAlter = body.getName();
        if (numberOfGebindeToEmpty > 0 && gebindeRepository.existsByName(gebindeNameToAlter)) {
            try {
                gebindeRepository.reduceFullByName(gebindeNameToAlter, numberOfGebindeToEmpty);
                gebindeRepository.increaseEmptyByName(gebindeNameToAlter, numberOfGebindeToEmpty);
            } catch (JpaSystemException e) {
                throw new IllegalArgumentException("Menge zu leerender Gebinde ist größer als die Zahl der verfügbaren Gebinde");
            }
        } else if (numberOfGebindeToEmpty <= 0) {
            throw new IllegalArgumentException("Die Anzahl der zu leerenden Gebinde muss größer 0 sein");
        } else {
            throw new IllegalArgumentException("Das angegebene Gebinde existiert nicht unter diesem Namen");
        }
    }
    public void deleteGebinde(String name) throws NotFoundException {
        if (!gebindeRepository.existsByName(name)) {
            throw new NotFoundException("Gebinde mit angegebenem Namen existiert nicht");
        }
        gebindeRepository.deleteAllByName(name);

    }
}
