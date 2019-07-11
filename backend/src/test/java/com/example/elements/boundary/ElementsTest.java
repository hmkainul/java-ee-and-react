package com.example.elements.boundary;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Test;
import com.example.elements.entity.Element;

public class ElementsTest {

    Elements service = new Elements();

    @Test
    public void getHydrogen() {
        Element e = service.get(1);
        assertEquals(1, e.getNumber());
        assertEquals("H", e.getSymbol());
        assertEquals("Hydrogen", e.getName());
        assertEquals(new BigDecimal("1.008"), e.getWeight());
    }

}
