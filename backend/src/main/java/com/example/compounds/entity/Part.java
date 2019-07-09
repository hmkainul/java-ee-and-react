package com.example.compounds.entity;

import java.math.BigDecimal;
import com.example.elements.entity.Element;

public class Part {

    private Element element;
    private int count;

    public Part() {
    }

    public Part(Element element, int count) {
        this.element = element;
        this.count = count;
    }

    public String formula() {
        return element.getSymbol() + (count > 1 ? count : "");
    }

    public BigDecimal weight() {
        return element.getWeight().multiply(new BigDecimal(count));
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
