package com.sqlite.demo.service;


import com.sqlite.demo.model.lager.Hefe;
import com.sqlite.demo.model.lager.Hopfen;
import com.sqlite.demo.model.lager.Malz;
import com.sqlite.demo.model.lager.WeitereZutaten;
import com.sqlite.demo.repository.lager.HefeRepository;
import com.sqlite.demo.repository.lager.HopfenRepository;
import com.sqlite.demo.repository.lager.MalzRepository;
import com.sqlite.demo.repository.lager.WeitereZutatenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
@AllArgsConstructor
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
        Float result = hefeRepository.findPricesOfStockedItems();
        return Objects.requireNonNullElse(result, 0.0f);
    }

    public List<Hopfen> getHopfen() {
        return StreamSupport.stream(hopfenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertHopfen() {
        Float result = hopfenRepository.findPricesOfStockedItems();
        return Objects.requireNonNullElse(result, 0.0f);
    }

    public List<Malz> getMalz() {
        return StreamSupport.stream(malzRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertMalz() {
        Float result = malzRepository.findPricesOfStockedItems();
        return Objects.requireNonNullElse(result, 0.0f);
    }

    public List<WeitereZutaten> getWeitereZutaten() {
        return StreamSupport.stream(weitereZutatenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Float getWertWeitereZutaten() throws IllegalArgumentException {
        List<WeitereZutaten> retrievedItems = StreamSupport.stream(weitereZutatenRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if (retrievedItems.isEmpty()) {
            return 0.0f;
        }
        return calculateValueWeitereZutaten(retrievedItems);
    }


    // Util Functions
    private Float calculateValueWeitereZutaten(List<WeitereZutaten> listOfPrices) {
        return listOfPrices.stream().map(singleRecord -> {
                    if (singleRecord.getEinheit() == 0 || singleRecord.getEinheit() == 3) {
                        return singleRecord.getMenge() * singleRecord.getPreis();
                    } else if (singleRecord.getEinheit() == 1) {
                        return singleRecord.getMenge() * (singleRecord.getPreis() / 1000);
                    } else {
                        throw new IllegalArgumentException("Einheitentyp nicht bekannt: " + singleRecord.getEinheit() +
                                "in Tabelle 'WeitereZutaten'");
                    }
                })
                .reduce(0.0f, Float::sum);
    }

    public Float getWertLager() {
        Float wertWeitereZutaten;
        try {
            wertWeitereZutaten = getWertWeitereZutaten();
        } catch (IllegalArgumentException e) {
            wertWeitereZutaten = 0.0f;
        }
        return getWertHefe() +
                getWertMalz() +
                getWertHopfen() +
                wertWeitereZutaten;
    }
}
