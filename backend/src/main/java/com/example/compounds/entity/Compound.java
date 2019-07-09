package com.example.compounds.entity;

import java.math.BigDecimal;
import java.util.*;
import com.example.elements.entity.Element;

public class Compound {

    private List<Part> parts = new ArrayList<>();

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public void setElementCount(Element element, int count) {
        Optional<Part> optional = parts.stream()
            .filter(part -> element.getNumber() == part.getElement().getNumber())
            .findAny();
        if (optional.isPresent()) {
            optional.get().setCount(count);
        } else {
            Part part = new Part(element, count);
            parts.add(part);
            sortParts();
        }
    }

    private void sortParts() {
        boolean containCarbon = parts.stream()
            .anyMatch(part -> isCarbon(part));
        parts.sort((a, b) -> {
            if (containCarbon) {
                if (isCarbon(a))
                    return -1;
                if (isCarbon(b))
                    return 1;
                if (isHydrogen(a))
                    return -1;
                if (isHydrogen(b))
                    return 1;
            }
            String x = a.getElement().getSymbol();
            String y = b.getElement().getSymbol();
            return x.compareTo(y);
        });
    }

    private boolean isCarbon(Part part) {
        return "C".equals(part.getElement().getSymbol());
    }

    private boolean isHydrogen(Part part) {
        return "H".equals(part.getElement().getSymbol());
    }

    public String formula() {
        return parts.stream()
            .map(part -> part.formula())
            .reduce("", String::concat);
    }

    public BigDecimal weight() {
        return parts.stream()
            .map(part -> part.weight())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
