package com.example.elements.boundary;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import com.example.elements.entity.Element;

public class ElementsTest {

    private static final int NUMBER = 1;
    private static final String SYMBOL = "H";
    private static final String NAME = "Hydrogen";
    private static final BigDecimal WEIGHT = new BigDecimal("1.008");

    Elements service;

    @Before
    public void setup() {
        service = mock(Elements.class);
        Element hydrogen = new Element(NUMBER, SYMBOL, NAME, WEIGHT);
        when(service.get(NUMBER)).thenReturn(hydrogen);
    }

    @Test
    public void getHydrogen() {
        Element e = service.get(NUMBER);
        assertEquals(NUMBER, e.getNumber());
        assertEquals(SYMBOL, e.getSymbol());
        assertEquals(NAME, e.getName());
        assertEquals(WEIGHT, e.getWeight());
    }

}
