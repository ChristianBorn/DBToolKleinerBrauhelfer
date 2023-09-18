package com.sqlite.demo.service;

import com.sqlite.demo.model.ausruestung.Ausruestung;
import com.sqlite.demo.model.ausruestung.Geraet;
import com.sqlite.demo.repository.ausruestung.AusruestungRepository;
import com.sqlite.demo.repository.ausruestung.GeraeteRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AusruestungServiceTest {
    private final AusruestungRepository ausruestungRepository = mock(AusruestungRepository.class);
    private final GeraeteRepository geraeteRepository = mock(GeraeteRepository.class);
    private final AusruestungService ausruestungService = new AusruestungService(geraeteRepository, ausruestungRepository);
    @Test
    void getGeraete_ReturnEmptyList() {
        when(geraeteRepository.findAll()).thenReturn(new ArrayList<>(List.of()));
        List<Geraet> expected = new ArrayList<>(List.of());
        List<Geraet> actual = ausruestungService.getGeraete();
        assertEquals(expected, actual);
    }
    @Test
    void getGeraete_ReturnList() {
        Geraet returnedItem = new Geraet();
        when(geraeteRepository.findAll()).thenReturn(new ArrayList<>(List.of(returnedItem)));
        List<Geraet> expected = new ArrayList<>(List.of(returnedItem));
        List<Geraet> actual = ausruestungService.getGeraete();
        assertEquals(expected, actual);
    }
    @Test
    void getAusruestung_ReturnEmptyList() {
        when(ausruestungRepository.findAll()).thenReturn(new ArrayList<>(List.of()));
        List<Ausruestung> expected = new ArrayList<>(List.of());
        List<Ausruestung> actual = ausruestungService.getAusruestung();
        assertEquals(expected, actual);
    }
    @Test
    void getAusruestung_ReturnList() {
        Ausruestung returnedItem = new Ausruestung();
        when(ausruestungRepository.findAll()).thenReturn(new ArrayList<>(List.of(returnedItem)));
        List<Ausruestung> expected = new ArrayList<>(List.of(returnedItem));
        List<Ausruestung> actual = ausruestungService.getAusruestung();
        assertEquals(expected, actual);
    }
}