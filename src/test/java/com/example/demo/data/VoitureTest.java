package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class VoitureTest {

    private Voiture v;
    @BeforeEach
    void init (){
        v = new Voiture("abc", 10000);
    }

    @Test
    void creerVoiture(){
        v.setId(1);
        assertEquals("abc", v.getMarque());
        assertEquals(1,v.getId());
        assertEquals(10000, v.getPrix());
    }

    @Test
    void creerVoitureFalse(){
        assertNotEquals("def", v.getMarque());
        assertNotEquals(1, v.getPrix());
    }
}
