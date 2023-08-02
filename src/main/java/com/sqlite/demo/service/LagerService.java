package com.sqlite.demo.service;


import com.sqlite.demo.model.lager.Hefe;
import com.sqlite.demo.model.lager.Hopfen;
import com.sqlite.demo.model.lager.Malz;
import com.sqlite.demo.model.lager.WeitereZutaten;
import com.sqlite.demo.repository.lager.HefeRepository;
import com.sqlite.demo.repository.lager.HopfenRepository;
import com.sqlite.demo.repository.lager.MalzRepository;
import com.sqlite.demo.repository.lager.WeitereZutatenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
public class LagerService {
    @Autowired
    private HefeRepository hefeRepository;
    @Autowired
    private HopfenRepository hopfenRepository;
    @Autowired
    private MalzRepository malzRepository;
    @Autowired
    private WeitereZutatenRepository weitereZutatenRepository;

    public List<Hefe> getHefe() {
        return StreamSupport.stream(hefeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertHefe() {
        return hefeRepository.findPricesOfStockedItems();
    }

    public List<Hopfen> getHopfen() {
        return StreamSupport.stream(hopfenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertHopfen() {
        return hopfenRepository.findPricesOfStockedItems();
    }

    public List<Malz> getMalz() {
        return StreamSupport.stream(malzRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertMalz() {
        return malzRepository.findPricesOfStockedItems();
    }

    public List<WeitereZutaten> getWeitereZutaten() {
        return StreamSupport.stream(weitereZutatenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertWeitereZutaten() throws IllegalArgumentException {
        List<WeitereZutaten> retrievedItems = StreamSupport.stream(weitereZutatenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return calculateValueWeitereZutaten(retrievedItems);
    }


    // Util Functions
    private Float calculateValueWeitereZutaten(List<WeitereZutaten> listOfPrices) {
        return listOfPrices.stream().map(singleRecord -> {
                    if (singleRecord.getEinheit() == 0 || singleRecord.getEinheit() == 3) {
                        return singleRecord.getMenge() * singleRecord.getPreis();
                    } else if (singleRecord.getEinheit() == 1) {
                        return singleRecord.getMenge() * (singleRecord.getPreis() / 1000);
                    } else if (singleRecord.getEinheit() == 2) {
                        return null;
                    } else {
                        throw new IllegalArgumentException("Einheitentyp nicht bekannt: " + singleRecord.getEinheit());
                    }
                })
                .reduce(0.0f, Float::sum);
    }

    public Float getWertLager() {
        return hefeRepository.findPricesOfStockedItems() +
                malzRepository.findPricesOfStockedItems() +
                hopfenRepository.findPricesOfStockedItems() +
                getWertWeitereZutaten();
    }
}
