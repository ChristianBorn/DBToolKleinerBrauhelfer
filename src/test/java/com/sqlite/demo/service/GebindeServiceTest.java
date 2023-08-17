package com.sqlite.demo.service;

import com.sqlite.demo.model.gebinde.Gebinde;
import com.sqlite.demo.model.gebinde.GebindeDTO;
import com.sqlite.demo.model.gebinde.GebindeStatus;
import com.sqlite.demo.repository.gebinde.GebindeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GebindeServiceTest {
    private final GebindeRepository gebindeRepository = mock(GebindeRepository.class);
    private final GebindeService gebindeService = new GebindeService(gebindeRepository);
    @Test
    void saveNewGebinde_Successful() {
        GebindeDTO transmittedGebinde = new GebindeDTO("name",
                0,
                0.0F,
                GebindeStatus.LEER);
        Gebinde gebindeToSave = new Gebinde(
                transmittedGebinde.getName(),
                transmittedGebinde.getAnzahl(),
                transmittedGebinde.getFassungsvermoegen(),
                transmittedGebinde.getStatus().getDisplayName());
        Iterable<Gebinde> gebindePairToSave = new ArrayList<>(List.of(gebindeToSave, gebindeToSave.gebindeWithStatus("voll")));
        when(gebindeRepository.saveAll(gebindePairToSave)).thenReturn(gebindePairToSave);

        Iterable<Gebinde> expected = gebindePairToSave;
        Iterable<Gebinde> actual = gebindeService.saveNewGebinde(transmittedGebinde);

        assertEquals(expected, actual);
    }
    @Test
    void getFreeCapacities() {
        Float result = 10.0F;
        when(gebindeRepository.getFreeCapacities()).thenReturn(result);

        String expected = "Freie Kapazitäten: " + result + " Liter";
        String actual = gebindeService.getFreeCapacities();

        assertEquals(expected, actual);
    }
    @Test
    void getGebinde_ReturnEmptyList() {
        when(gebindeRepository.findAll()).thenReturn(List.of());

        List<Gebinde> expected = List.of();
        List<Gebinde> actual = gebindeService.getGebinde();

        assertEquals(expected, actual);
    }
    @Test
    void getGebinde_ReturnList() {
        Gebinde returnedItem = new Gebinde();
        when(gebindeRepository.findAll()).thenReturn(List.of(returnedItem));

        List<Gebinde> expected = List.of(returnedItem);
        List<Gebinde> actual = gebindeService.getGebinde();

        assertEquals(expected, actual);
    }

    @Test
    void fillGebinde_Succesfull() {
        String gebindeNameToAlter = "test";
        int numberOfGebindeToFill = 1;
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(true);
        gebindeService.fillGebinde(gebindeNameToAlter, numberOfGebindeToFill);
        verify(gebindeRepository).reduceEmptyByName(gebindeNameToAlter, numberOfGebindeToFill);
        verify(gebindeRepository).increaseFullByName(gebindeNameToAlter, numberOfGebindeToFill);
    }
    @Test
    void fillGebinde_numberParamLowerThan0() {
        String gebindeNameToAlter = "test";
        int numberOfGebindeToFill = -1;
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(true);
        try {
            gebindeService.fillGebinde(gebindeNameToAlter, numberOfGebindeToFill);
            fail();
        } catch (IllegalArgumentException  e) {
            assertEquals("Die Anzahl der zu füllenden Gebinde muss größer 0 sein", e.getMessage());
        }
    }
    @Test
    void fillGebinde_numberParamExceedsObjectsInDB() {
        String gebindeNameToAlter = "test";
        int numberOfGebindeToFill = 10;
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(true);
        doThrow(new JpaSystemException(new RuntimeException())).when(gebindeRepository).reduceEmptyByName(gebindeNameToAlter, numberOfGebindeToFill);
        try {
            gebindeService.fillGebinde(gebindeNameToAlter, numberOfGebindeToFill);
            fail();
        } catch (JpaSystemException  e) {
            assertEquals("nested exception is java.lang.RuntimeException", e.getMessage());
        }
    }
    @Test
    void fillGebinde_givenNameDoesNotExistInDB() {
        String gebindeNameToAlter = "test";
        int numberOfGebindeToFill = 1;
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(false);
        try {
            gebindeService.fillGebinde(gebindeNameToAlter, numberOfGebindeToFill);
            fail();
        } catch (IllegalArgumentException  e) {
            assertEquals("Das angegebene Gebinde existiert nicht unter diesem Namen", e.getMessage());
        }
    }
}