package com.example.elements.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class Element {

    @Id
    private int number;
    private String symbol;
    private String name;
    @Column(precision = 32, scale = 16)
    private BigDecimal weight;

    public Element() {
    }

    public Element(int number, String symbol, String name, BigDecimal weight) {
        this.number = number;
        this.symbol = symbol;
        this.name = name;
        this.weight = weight;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

}
