package com.sqlite.demo.service;

import com.sqlite.demo.model.sud.Sud;
import com.sqlite.demo.repository.sud.SudRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SudServiceTest {
    private final SudRepository mockSudRepository = mock(SudRepository.class);
    private final SudService sudService = new SudService(mockSudRepository);
    @Test
    void getSud_returnListOfSud() {
        //GIVEN
        Sud retrievedSud = new Sud();
        //WHEN
        when(mockSudRepository.findAll()).thenReturn(List.of(retrievedSud));
        List<Sud> expected = new ArrayList<>(List.of(retrievedSud));
        List<Sud> actual = sudService.getSud();
        //THEN
        assertEquals(expected, actual);
    }
    @Test
    void getSud_returnEmptyList() {
        //GIVEN
        //WHEN
        when(mockSudRepository.findAll()).thenReturn(List.of());
        List<Sud> expected = new ArrayList<>();
        List<Sud> actual = sudService.getSud();
        //THEN
        assertEquals(expected, actual);
    }
}