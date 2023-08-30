package com.sqlite.demo.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqlite.demo.model.gebinde.Gebinde;
import com.sqlite.demo.model.gebinde.GebindeStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class GebindeIntegrationTest {

    private final String urlParamName = "Fass";
    private final int urlParamNumber = 1;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    @Transactional
    @Rollback
    void addGebinde_ExpectSuccess() throws Exception {
        String requestBody = "{" +
                "    \"name\": \"Test\"," +
                "    \"anzahl\": 42,\n" +
                "    \"fassungsvermoegen\": 0.42," +
                "    \"status\": \"leer\"" +
                "}";
        String result = mockMvc.perform(put("/gebinde/add")
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status()
                        .isCreated())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        List<Gebinde> resultList = objectMapper.readValue(result, new TypeReference<>() {
        });
        assertEquals(2, resultList.size());
        assertTrue(resultList.get(0).getName().equals("Test") &&
                resultList.get(1).getName().equals("Test"));
        assertTrue(resultList.get(0).getAnzahl().equals(42) &&
                resultList.get(1).getAnzahl().equals(0));
        assertTrue(resultList.get(0).getFassungsvermoegen().equals(0.42F) &&
                resultList.get(1).getFassungsvermoegen().equals(0.42F));
        assertTrue(resultList.get(0).getStatus().equals(GebindeStatus.LEER.getDisplayName()) &&
                resultList.get(1).getStatus().equals(GebindeStatus.VOLL.getDisplayName()));
    }
    @Test
    @DirtiesContext
    @Transactional
    @Rollback
    void fillGebinde_ExpectSuccess() throws Exception {
        String requestUrl = "/gebinde/fill?name=" + urlParamName + "&number=" + urlParamNumber;
        System.out.println(requestUrl);
        String result = mockMvc.perform(put(requestUrl))
                .andExpect(status()
                        .isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("OK", result);
    }
    @Test
    @DirtiesContext
    @Transactional
    @Rollback
    void emptyGebinde_ExpectSuccess() throws Exception {
        String requestUrl = "/gebinde/empty?name=" + urlParamName + "&number=" + urlParamNumber;
        System.out.println(requestUrl);
        String result = mockMvc.perform(put(requestUrl))
                .andExpect(status()
                        .isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("OK", result);
    }
}
