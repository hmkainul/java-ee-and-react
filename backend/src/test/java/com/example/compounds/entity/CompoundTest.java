package com.example.compounds.entity;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.example.elements.entity.Element;

public class CompoundTest {

    private Compound water;
    private Compound silverAcetate;
    private Element h;
    private Element o;
    private Element c;
    private Element aG;

    @Before
    public void initialize() {
        createElements();
        createWater();
        createSilverAcetate();
    }

    private void createElements() {
        h = new Element(1, "H", "Hydrogen", new BigDecimal("1.008"));
        o = new Element(8, "O", "Oxygen", new BigDecimal("15.999"));
        c = new Element(6, "C", "Carbon", new BigDecimal("12.011"));
        aG = new Element(47, "Ag", "Silver", new BigDecimal("107.8682"));
    }

    private void createWater() {
        water = new Compound();
        water.setElementCount(h, 2);
        water.setElementCount(o, 1);
    }

    private void createSilverAcetate() {
        silverAcetate = new Compound();
        silverAcetate.setElementCount(c, 2);
        silverAcetate.setElementCount(h, 3);
        silverAcetate.setElementCount(aG, 1);
        silverAcetate.setElementCount(o, 2);
    }

    @Test
    public void testFormula() {
        assertEquals("H2O", water.formula());
        assertEquals("C2H3AgO2", silverAcetate.formula());
    }

    @Test
    public void testWeight() {
        assertEquals(new BigDecimal("18.015"), water.weight());
        assertEquals(new BigDecimal("166.9122"), silverAcetate.weight());
    }

}
