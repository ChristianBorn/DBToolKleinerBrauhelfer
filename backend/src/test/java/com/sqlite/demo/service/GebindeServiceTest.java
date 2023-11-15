package com.sqlite.demo.service;

import com.sqlite.demo.model.gebinde.*;
import com.sqlite.demo.repository.gebinde.GebindeRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
        Iterable<Gebinde> actual = gebindeService.saveNewEmptyGebinde(transmittedGebinde);

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
    void getFreeCapacitiesGrouped() {
        when(gebindeRepository.getFreeCapacitiesGrouped()).thenReturn(List.of());

        List<Capacity> expected = List.of();
        List<Capacity> actual = gebindeService.getFreeCapacitiesGrouped();

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
        GebindeFormDTO formData = new GebindeFormDTO("test", 1);
        String gebindeNameToAlter = formData.getName();
        int numberOfGebindeToFill = formData.getNumber();
        when(gebindeRepository.existsByName(formData.getName())).thenReturn(true);
        gebindeService.fillGebinde(formData);
        verify(gebindeRepository).reduceEmptyByName(gebindeNameToAlter, numberOfGebindeToFill);
        verify(gebindeRepository).increaseFullByName(gebindeNameToAlter, numberOfGebindeToFill);
    }
    @Test
    void fillGebinde_numberParamLowerThan0() {
        GebindeFormDTO formData = new GebindeFormDTO("test", -1);
        String gebindeNameToAlter = formData.getName();

        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(true);
        try {
            gebindeService.fillGebinde(formData);
            fail();
        } catch (IllegalArgumentException  e) {
            assertEquals("Die Anzahl der zu füllenden Gebinde muss größer 0 sein", e.getMessage());
        }
    }
    @Test
    void fillGebinde_numberParamExceedsObjectsInDB() {
        GebindeFormDTO formData = new GebindeFormDTO("test", 1000);
        String gebindeNameToAlter = formData.getName();
        int numberOfGebindeToFill = formData.getNumber();
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(true);
        doThrow(new JpaSystemException(new RuntimeException())).when(gebindeRepository).reduceEmptyByName(gebindeNameToAlter, numberOfGebindeToFill);
        try {
            gebindeService.fillGebinde(formData);
            fail();
        } catch (IllegalArgumentException  e) {
            assertEquals("Menge zu füllender Gebinde ist größer als die Zahl der verfügbaren Gebinde", e.getMessage());
        }
    }
    @Test
    void fillGebinde_givenNameDoesNotExistInDB() {
        GebindeFormDTO formData = new GebindeFormDTO("test", 1);
        String gebindeNameToAlter = formData.getName();

        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(false);
        try {
            gebindeService.fillGebinde(formData);
            fail();
        } catch (IllegalArgumentException  e) {
            assertEquals("Das angegebene Gebinde existiert nicht unter diesem Namen", e.getMessage());
        }
    }
    @Test
    void emptyGebinde_Succesfull() {
        GebindeFormDTO formData = new GebindeFormDTO("test", 1);
        String gebindeNameToAlter = formData.getName();
        int numberOfGebindeToEmpty = formData.getNumber();
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(true);
        gebindeService.emptyGebinde(formData);
        verify(gebindeRepository).reduceFullByName(gebindeNameToAlter, numberOfGebindeToEmpty);
        verify(gebindeRepository).increaseEmptyByName(gebindeNameToAlter, numberOfGebindeToEmpty);
    }
    @Test
    void emptyGebinde_numberParamLowerThan0() {
        GebindeFormDTO formData = new GebindeFormDTO("test", -1);
        String gebindeNameToAlter = formData.getName();
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(true);
        try {
            gebindeService.emptyGebinde(formData);
            fail();
        } catch (IllegalArgumentException  e) {
            assertEquals("Die Anzahl der zu leerenden Gebinde muss größer 0 sein", e.getMessage());
        }
    }
    @Test
    void emptyGebinde_numberParamExceedsObjectsInDB() {
        GebindeFormDTO formData = new GebindeFormDTO("test", 1);
        String gebindeNameToAlter = formData.getName();
        int numberOfGebindeToEmpty = formData.getNumber();
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(true);
        doThrow(new JpaSystemException(new RuntimeException())).when(gebindeRepository).reduceFullByName(gebindeNameToAlter, numberOfGebindeToEmpty);
        try {
            gebindeService.emptyGebinde(formData);
            fail();
        } catch (IllegalArgumentException  e) {
            assertEquals("Menge zu leerender Gebinde ist größer als die Zahl der verfügbaren Gebinde", e.getMessage());
        }
    }
    @Test
    void emptyGebinde_givenNameDoesNotExistInDB() {
        GebindeFormDTO formData = new GebindeFormDTO("test", 1);
        String gebindeNameToAlter = formData.getName();
        when(gebindeRepository.existsByName(gebindeNameToAlter)).thenReturn(false);
        try {
            gebindeService.emptyGebinde(formData);
            fail();
        } catch (IllegalArgumentException  e) {
            assertEquals("Das angegebene Gebinde existiert nicht unter diesem Namen", e.getMessage());
        }
    }

    @Test
    void deleteGebinde_Success() {
        String gebindeToDelete = "Test";
        when(gebindeRepository.existsByName(gebindeToDelete)).thenReturn(true);
        try {
            gebindeService.deleteGebinde(gebindeToDelete);
        } catch (Exception e) {
            fail();
        }
        verify(gebindeRepository).existsByName(gebindeToDelete);
        verify(gebindeRepository).deleteAllByName(gebindeToDelete);
    }

    @Test
    void deleteGebinde_nameDoesNotExistInDB() {
        String gebindeToDelete = "Test";
        when(gebindeRepository.existsByName(gebindeToDelete)).thenReturn(false);
        try {
            gebindeService.deleteGebinde(gebindeToDelete);
            fail();
        } catch (NotFoundException e) {
            assertEquals("Gebinde mit angegebenem Namen existiert nicht", e.getMessage());
        }
    }
}