package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class StatistiqueTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Test
    void testAjouter(){
        
        Voiture v = new Voiture("abc", 10000);

        statistiqueImpl.ajouter(v);

        verify(statistiqueImpl, times(1)).ajouter(v);
    }

    @Test
    void testPrixMoyen(){

        Echantillon fauxResultat = new Echantillon(2, 5000);

        when(statistiqueImpl.prixMoyen()).thenReturn(fauxResultat);

        Echantillon result = statistiqueImpl.prixMoyen();

        verify(statistiqueImpl, times(1)).prixMoyen();
        assertThat(result).isNotNull();
        assertThat(result.getNombreDeVoitures()).isEqualTo(2);
        assertThat(result.getPrixMoyen()).isEqualTo(5000);
    }

    @Test
    void testPrixMoyenException(){

        when(statistiqueImpl.prixMoyen()).thenThrow(new ArithmeticException("erreur"));

        assertThrows(ArithmeticException.class, () -> {
            statistiqueImpl.prixMoyen();
        });

        verify(statistiqueImpl).prixMoyen();
    }



}
