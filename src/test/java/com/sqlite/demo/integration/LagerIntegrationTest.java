package com.sqlite.demo.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqlite.demo.model.lager.Hefe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LagerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getHefe_ExpectList() throws Exception {
        String result =  mockMvc.perform(get("/hefe")).andReturn().getResponse().getContentAsString();
        List<Hefe> retrievedObjects = objectMapper.readValue(result, new TypeReference<>() {});
        mockMvc.perform(get("/hefe"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].menge").isNotEmpty())
                .andExpect(jsonPath("$[0].preis").isNotEmpty());
    }
    @Test
    @DirtiesContext
    void getWertHefe_ExpectFloat() throws Exception {
        Float result = Float.parseFloat(mockMvc.perform(get("/hefe/wert"))
                .andReturn()
                .getResponse()
                .getContentAsString());
        mockMvc.perform(get("/hefe/wert"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
