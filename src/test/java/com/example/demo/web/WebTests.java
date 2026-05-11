package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    void TestGetStatistiques() throws Exception {
        when(statistiqueImpl.prixMoyen()).thenReturn(new Echantillon(1, 5000));

        mockMvc.perform(get("/statistique"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDeVoitures").value(1))
                .andExpect(jsonPath("$.prixMoyen").value(5000));
        
        verify(statistiqueImpl, times(1)).prixMoyen();

    }

    @Test
    void TestGetStatistiquesError(){
        when(statistiqueImpl).thenThrow(new ArithmeticException());

        mockMvc.perform(get("/statistique"))
            .andExcept(status().isBadRequest());
    }

    @Test
    void testCreerVoiture() throws Exception{
    Voiture v = new Voiture("abc", 5000);

    ObjectMapper mapper = new ObjectMapper();
    String jsonVoiture = mapper.writeValueAsString(v);

    mockMvc.perform(post("/voiture"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonVoiture)
        .andExpect(status().isOk());

    verify(statistiqueImpl, times(1)).ajouter(any(Voiture.class));
    }

}
