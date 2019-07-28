package com.example.elements.entity;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Test;

public class ElementTest {

    private static final int NUMBER = 1;
    private static final String SYMBOL = "H";
    private static final String NAME = "Hydrogen";
    private static final BigDecimal WEIGHT = new BigDecimal("1.008");

    @Test
    public void createElement() {
        Element e = new Element(NUMBER, SYMBOL, NAME, WEIGHT);
        assertEquals(NUMBER, e.getNumber());
        assertEquals(SYMBOL, e.getSymbol());
        assertEquals(NAME, e.getName());
        assertEquals(WEIGHT, e.getWeight());
    }

}
