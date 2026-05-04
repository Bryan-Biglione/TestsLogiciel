package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoitureTest {

    private Voiture v;
    @Before
    void init (){
        v = new Voiture("abc", 100000);
    }

    @Test
    void creerVoiture(){
        v.setId(1);
        assertEquals("abc", v.getMarque());
        assertEquals(1,v.getId());
        assertEquals(10000, v.getPrix());
    }
}
