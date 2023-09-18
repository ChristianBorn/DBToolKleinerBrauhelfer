package com.sqlite.demo.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqlite.demo.model.lager.Hefe;
import com.sqlite.demo.model.lager.Hopfen;
import com.sqlite.demo.model.lager.Malz;
import com.sqlite.demo.model.lager.WeitereZutaten;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class LagerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getHefe_ExpectList() throws Exception {
        String result =  mockMvc.perform(get("/hefe")).andReturn().getResponse().getContentAsString();
        List<Hefe> retrievedObjects = objectMapper.readValue(result, new TypeReference<>() {});
        assertTrue(retrievedObjects.size() > 0);
        mockMvc.perform(get("/hefe"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].menge").isNotEmpty())
                .andExpect(jsonPath("$[0].preis").isNotEmpty());
    }
    @Test
    void getWertHefe_ExpectFloat() throws Exception {
        Float result = Float.parseFloat(mockMvc.perform(get("/hefe/wert"))
                .andReturn()
                .getResponse()
                .getContentAsString());
        assertEquals(45.04F, result);
        mockMvc.perform(get("/hefe/wert"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void getHopfen_ExpectList() throws Exception {
        String result =  mockMvc.perform(get("/hopfen")).andReturn().getResponse().getContentAsString();
        List<Hopfen> retrievedObjects = objectMapper.readValue(result, new TypeReference<>() {});
        assertTrue(retrievedObjects.size() > 0);
        mockMvc.perform(get("/hopfen"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].menge").isNotEmpty())
                .andExpect(jsonPath("$[0].preis").isNotEmpty());
    }
    @Test
    void getWertHopfen_ExpectFloat() throws Exception {
        Float result = Float.parseFloat(mockMvc.perform(get("/hopfen/wert"))
                .andReturn()
                .getResponse()
                .getContentAsString());
        assertEquals(115.727554F, result);
        mockMvc.perform(get("/hopfen/wert"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void getMalz_ExpectList() throws Exception {
        String result =  mockMvc.perform(get("/malz")).andReturn().getResponse().getContentAsString();
        List<Malz> retrievedObjects = objectMapper.readValue(result, new TypeReference<>() {});
        assertTrue(retrievedObjects.size() > 0);
        mockMvc.perform(get("/malz"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].menge").isNotEmpty())
                .andExpect(jsonPath("$[0].preis").isNotEmpty());
    }
    @Test
    void getWertMalz_ExpectFloat() throws Exception {
        Float result = Float.parseFloat(mockMvc.perform(get("/malz/wert"))
                .andReturn()
                .getResponse()
                .getContentAsString());
        assertEquals(222.39519F, result);
        mockMvc.perform(get("/malz/wert"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void getWeitereZutaten_ExpectList() throws Exception {
        String result =  mockMvc.perform(get("/weitere-zutaten")).andReturn().getResponse().getContentAsString();
        List<WeitereZutaten> retrievedObjects = objectMapper.readValue(result, new TypeReference<>() {});
        assertTrue(retrievedObjects.size() > 0);
        mockMvc.perform(get("/weitere-zutaten"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].einheit").isNotEmpty())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].menge").isNotEmpty())
                .andExpect(jsonPath("$[0].preis").isNotEmpty());
    }
    @Test
    void getWertWeitereZutaten_ExpectFloat() throws Exception {
        Float result = Float.parseFloat(mockMvc.perform(get("/weitere-zutaten/wert"))
                .andReturn()
                .getResponse()
                .getContentAsString());
        assertEquals(39.894756F, result);
        mockMvc.perform(get("/weitere-zutaten/wert"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void getWertLager_ExpectFloat() throws Exception {
        Float result = Float.parseFloat(mockMvc.perform(get("/lager/wert"))
                .andReturn()
                .getResponse()
                .getContentAsString());
        assertEquals(423.05746F, result);
        mockMvc.perform(get("/lager/wert"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
