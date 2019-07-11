package com.example.elements.boundary;

import com.example.elements.entity.Element;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class Elements {

    public Element get(int number) {
        return new Element(
            1,
            "H",
            "Hydrogen",
            new BigDecimal("1.008")
        );
    }

    public List<Element> getAll() {
        List<Element> result = new ArrayList<>();
        result.add(get(1));
        return result;
    }

    public Element save(Element e) {
        return e;
    }

    public boolean delete(int number) {
        return false;
    }

}
