package com.sqlite.demo.service;

import com.sqlite.demo.model.gebinde.Gebinde;
import com.sqlite.demo.model.gebinde.GebindeDTO;
import com.sqlite.demo.model.gebinde.GebindeStatus;
import com.sqlite.demo.repository.gebinde.GebindeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
                transmittedGebinde.getStatus());
        when(gebindeRepository.save(gebindeToSave)).thenReturn(gebindeToSave);

        Gebinde expected = gebindeToSave;
        Gebinde actual = gebindeService.saveNewGebinde(transmittedGebinde);

        assertEquals(expected, actual);
    }
}