package com.sqlite.demo.service;

import com.sqlite.demo.model.lager.Hefe;
import com.sqlite.demo.model.lager.Hopfen;
import com.sqlite.demo.model.lager.Malz;
import com.sqlite.demo.model.lager.WeitereZutaten;
import com.sqlite.demo.repository.lager.HefeRepository;
import com.sqlite.demo.repository.lager.HopfenRepository;
import com.sqlite.demo.repository.lager.MalzRepository;
import com.sqlite.demo.repository.lager.WeitereZutatenRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LagerServiceTest {
    private final HefeRepository hefeRepository = mock(HefeRepository.class);
    private final HopfenRepository hopfenRepository = mock(HopfenRepository.class);
    private final MalzRepository malzRepository = mock(MalzRepository.class);
    private final WeitereZutatenRepository weitereZutatenRepository = mock(WeitereZutatenRepository.class);
    private final LagerService lagerService = new LagerService(hefeRepository, hopfenRepository,
            malzRepository, weitereZutatenRepository);

    @Test
    void getHefe_ReturnEmptyList() {
        when(hefeRepository.findAll()).thenReturn(new ArrayList<>(List.of()));
        List<Hefe> expected = new ArrayList<>(List.of());
        List<Hefe> actual = lagerService.getHefe();
        assertEquals(expected, actual);
    }

    @Test
    void getHefe_ReturnList() {
        Hefe returnedItem = new Hefe();
        when(hefeRepository.findAll()).thenReturn(new ArrayList<>(List.of(returnedItem)));
        List<Hefe> expected = new ArrayList<>(List.of(returnedItem));
        List<Hefe> actual = lagerService.getHefe();
        assertEquals(expected, actual);
    }

    @Test
    void getWertHefe_WithResult() {
        when(hefeRepository.findPricesOfStockedItems()).thenReturn(4.0f);
        Float expected = 4.0f;
        Float actual = lagerService.getWertHefe();
        assertEquals(expected, actual);
    }

    @Test
    void getWertHefe_WithoutResult() {
        when(hefeRepository.findPricesOfStockedItems()).thenReturn(null);
        Float expected = 0.0f;
        Float actual = lagerService.getWertHefe();
        assertEquals(expected, actual);
    }

    @Test
    void getHopfen_ReturnEmptyList() {
        when(hopfenRepository.findAll()).thenReturn(new ArrayList<>(List.of()));
        List<Hopfen> expected = new ArrayList<>(List.of());
        List<Hopfen> actual = lagerService.getHopfen();
        assertEquals(expected, actual);
    }

    @Test
    void getHopfen_ReturnList() {
        Hopfen returnedItem = new Hopfen();
        when(hopfenRepository.findAll()).thenReturn(new ArrayList<>(List.of(returnedItem)));
        List<Hopfen> expected = new ArrayList<>(List.of(returnedItem));
        List<Hopfen> actual = lagerService.getHopfen();
        assertEquals(expected, actual);
    }

    @Test
    void getWertHopfen_WithResult() {
        when(hopfenRepository.findPricesOfStockedItems()).thenReturn(4.0f);
        Float expected = 4.0f;
        Float actual = lagerService.getWertHopfen();
        assertEquals(expected, actual);
    }

    @Test
    void getWertHopfen_WithoutResult() {
        when(hopfenRepository.findPricesOfStockedItems()).thenReturn(null);
        Float expected = 0.0f;
        Float actual = lagerService.getWertHopfen();
        assertEquals(expected, actual);
    }


    @Test
    void getMalz_ReturnEmptyList() {
        when(malzRepository.findAll()).thenReturn(new ArrayList<>(List.of()));
        List<Malz> expected = new ArrayList<>(List.of());
        List<Malz> actual = lagerService.getMalz();
        assertEquals(expected, actual);
    }

    @Test
    void getMalz_ReturnList() {
        Malz returnedItem = new Malz();
        when(malzRepository.findAll()).thenReturn(new ArrayList<>(List.of(returnedItem)));
        List<Malz> expected = new ArrayList<>(List.of(returnedItem));
        List<Malz> actual = lagerService.getMalz();
        assertEquals(expected, actual);
    }

    @Test
    void getWertMalz_WithResult() {
        when(malzRepository.findPricesOfStockedItems()).thenReturn(4.0f);
        Float expected = 4.0f;
        Float actual = lagerService.getWertMalz();
        assertEquals(expected, actual);
    }

    @Test
    void getWertMalz_WithoutResult() {
        when(malzRepository.findPricesOfStockedItems()).thenReturn(null);
        Float expected = 0.0f;
        Float actual = lagerService.getWertMalz();
        assertEquals(expected, actual);
    }

    @Test
    void getWeitereZutaten_ReturnEmptyList() {
        when(weitereZutatenRepository.findAll()).thenReturn(new ArrayList<>(List.of()));
        List<WeitereZutaten> expected = new ArrayList<>(List.of());
        List<WeitereZutaten> actual = lagerService.getWeitereZutaten();
        assertEquals(expected, actual);
    }

    @Test
    void getWeitereZutaten_ReturnList() {
        WeitereZutaten returnedItem = new WeitereZutaten();
        when(weitereZutatenRepository.findAll()).thenReturn(new ArrayList<>(List.of(returnedItem)));
        List<WeitereZutaten> expected = new ArrayList<>(List.of(returnedItem));
        List<WeitereZutaten> actual = lagerService.getWeitereZutaten();
        assertEquals(expected, actual);
    }

    @Test
    void getWertWeitereZutaten_WithResult() {
        WeitereZutaten retrievedItem = new WeitereZutaten();
        retrievedItem.setMenge(1.0f);
        retrievedItem.setPreis(2.0f);
        retrievedItem.setEinheit(0);
        when(weitereZutatenRepository.findAll()).thenReturn(new ArrayList<>(List.of(retrievedItem)));
        Float expected = retrievedItem.getMenge() * retrievedItem.getPreis();
        try {
            Float actual = lagerService.getWertWeitereZutaten();
            assertEquals(expected, actual);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void getWertWeitereZutaten_WithResultEinheitIs1() {
        WeitereZutaten retrievedItem = new WeitereZutaten();
        retrievedItem.setMenge(1.0f);
        retrievedItem.setPreis(2.0f);
        retrievedItem.setEinheit(1);
        when(weitereZutatenRepository.findAll()).thenReturn(new ArrayList<>(List.of(retrievedItem)));
        Float expected = retrievedItem.getMenge() * (retrievedItem.getPreis() / 1000);
        try {
            Float actual = lagerService.getWertWeitereZutaten();
            assertEquals(expected, actual);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void getWertWeitereZutaten_WithUnknownEinheit() {
        WeitereZutaten retrievedItem = new WeitereZutaten();
        retrievedItem.setMenge(1.0f);
        retrievedItem.setPreis(2.0f);
        retrievedItem.setEinheit(10);
        when(weitereZutatenRepository.findAll()).thenReturn(new ArrayList<>(List.of(retrievedItem)));
        try {
            lagerService.getWertWeitereZutaten();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Einheitentyp nicht bekannt: " + retrievedItem.getEinheit() +
                            " in Tabelle 'WeitereZutaten'",
                    e.getMessage());
        }
    }

    @Test
    void getWertWeitereZutaten_WithoutResults() {
        when(weitereZutatenRepository.findAll()).thenReturn(new ArrayList<>(List.of()));
        Float expected = 0.0f;
        try {
            Float actual = lagerService.getWertWeitereZutaten();
            assertEquals(expected, actual);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void getWertLager_WithValidResults() {
        WeitereZutaten retrievedItem = new WeitereZutaten();
        retrievedItem.setPreis(1.0f);
        retrievedItem.setMenge(1.0f);
        retrievedItem.setEinheit(0);
        when(hefeRepository.findPricesOfStockedItems()).thenReturn(1.0f);
        when(malzRepository.findPricesOfStockedItems()).thenReturn(1.0f);
        when(hopfenRepository.findPricesOfStockedItems()).thenReturn(1.0f);
        when(weitereZutatenRepository.findAll()).thenReturn(List.of(retrievedItem));

        Float expected = 4.0f;
        Float actual = lagerService.getWertLager();
        assertEquals(expected, actual);
    }

    @Test
    void getWertLager_WithoutValidEinheit() {
        WeitereZutaten retrievedItem = new WeitereZutaten();
        retrievedItem.setPreis(1.0f);
        retrievedItem.setMenge(1.0f);
        retrievedItem.setEinheit(10);
        when(hefeRepository.findPricesOfStockedItems()).thenReturn(1.0f);
        when(malzRepository.findPricesOfStockedItems()).thenReturn(1.0f);
        when(hopfenRepository.findPricesOfStockedItems()).thenReturn(1.0f);
        when(weitereZutatenRepository.findAll()).thenReturn(List.of(retrievedItem));

        Float expected = 3.0f;
        Float actual = lagerService.getWertLager();
        assertEquals(expected, actual);
    }
}