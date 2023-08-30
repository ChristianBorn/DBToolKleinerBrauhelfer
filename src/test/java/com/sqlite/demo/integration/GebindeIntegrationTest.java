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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@Rollback
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
    void addGebinde_ExpectSuccess() throws Exception {
        String requestBody = "{" +
                "    \"name\": \"Test\"," +
                "    \"anzahl\": 42,\n" +
                "    \"fassungsvermoegen\": 0.42," +
                "    \"status\": \"leer\"" +
                "}";
        String requestUrl = "/gebinde/add";
        String result = mockMvc.perform(put(requestUrl)
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
    void fillGebinde_ExpectException_InvalidNumberParam() throws Exception {
        String requestUrl = "/gebinde/fill?name=" + urlParamName + "&number=" + (urlParamNumber - 1);
        System.out.println(requestUrl);
        String result = mockMvc.perform(put(requestUrl))
                .andExpect(status()
                        .isBadRequest())
                .andReturn().getResponse().getContentAsString();
        assertEquals("Die Anzahl der zu füllenden Gebinde muss größer 0 sein", result);
    }

    @Test
    @DirtiesContext
    @Transactional
    void fillGebinde_ExpectException_InvalidNameParam() throws Exception {
        String requestUrl = "/gebinde/fill?name=" + (urlParamName + "a") + "&number=" + urlParamNumber;
        System.out.println(requestUrl);
        String result = mockMvc.perform(put(requestUrl))
                .andExpect(status()
                        .isBadRequest())
                .andReturn().getResponse().getContentAsString();
        assertEquals("Das angegebene Gebinde existiert nicht unter diesem Namen", result);
    }

    @Test
    @DirtiesContext
    @Transactional
    void fillGebinde_ExpectException_NumberParamTooBig() throws Exception {
        String requestUrl = "/gebinde/fill?name=" + urlParamName + "&number=" + (urlParamNumber + 100);
        System.out.println(requestUrl);
        String result = mockMvc.perform(put(requestUrl))
                .andExpect(status()
                        .isInternalServerError())
                .andReturn().getResponse().getContentAsString();
        assertEquals("Fehler bei Interaktion mit der Datenbank: could not execute statement; " +
                "nested exception is org.hibernate.exception.GenericJDBCException: could not execute statement", result);
    }

    @Test
    @DirtiesContext
    @Transactional
    void emptyGebinde_ExpectSuccess() throws Exception {
        String requestUrl = "/gebinde/empty?name=" + urlParamName + "&number=" + urlParamNumber;
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
    void emptyGebinde_ExpectException_InvalidNumberParam() throws Exception {
        String requestUrl = "/gebinde/empty?name=" + urlParamName + "&number=" + (urlParamNumber - 1);
        System.out.println(requestUrl);
        String result = mockMvc.perform(put(requestUrl))
                .andExpect(status()
                        .isBadRequest())
                .andReturn().getResponse().getContentAsString();
        assertEquals("Die Anzahl der zu leerenden Gebinde muss größer 0 sein", result);
    }

    @Test
    @DirtiesContext
    @Transactional
    void emptyGebinde_ExpectException_InvalidNameParam() throws Exception {
        String requestUrl = "/gebinde/empty?name=" + (urlParamName + "a") + "&number=" + urlParamNumber;
        System.out.println(requestUrl);
        String result = mockMvc.perform(put(requestUrl))
                .andExpect(status()
                        .isBadRequest())
                .andReturn().getResponse().getContentAsString();
        assertEquals("Das angegebene Gebinde existiert nicht unter diesem Namen", result);
    }

    @Test
    @DirtiesContext
    @Transactional
    void emptyGebinde_ExpectException_NumberParamTooBig() throws Exception {
        String requestUrl = "/gebinde/empty?name=" + urlParamName + "&number=" + (urlParamNumber + 100);
        System.out.println(requestUrl);
        String result = mockMvc.perform(put(requestUrl))
                .andExpect(status()
                        .isInternalServerError())
                .andReturn().getResponse().getContentAsString();
        assertEquals("Fehler bei Interaktion mit der Datenbank: could not execute statement; " +
                "nested exception is org.hibernate.exception.GenericJDBCException: could not execute statement", result);
    }

    @Test
    void getFreeCapacities() throws Exception {
        String requestUrl = "/gebinde/kapazität";
        String result = mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("Freie Kapazitäten: 34.11 Liter", result);
    }

    @Test
    void getFreeCapacitiesGrouped() throws Exception {
        String requestUrl = "/gebinde/kapazität/grouped";
        String result = mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Object> returnedObject = objectMapper.readValue(result, new TypeReference<>() {});
        assertEquals(3, returnedObject.size());
    }

    @Test
    void getGebinde() throws Exception {
        String requestUrl = "/gebinde";
        String result = mockMvc.perform(get(requestUrl))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Gebinde> returnedObject = objectMapper.readValue(result, new TypeReference<>() {});
        assertEquals(6, returnedObject.size());
    }
}
