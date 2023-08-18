package com.sqlite.demo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AusruestungIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAusruestung_ExpectEmptyList() throws Exception {
        mockMvc.perform(get("/ausruestung"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void getGeraete_ExpectEmptyList() throws Exception {
        mockMvc.perform(get("/geraete"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
