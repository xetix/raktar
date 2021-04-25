package com.mycompany.raktar.currencyXML;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    private String penznem;
    private BigDecimal kozep;

    public Item() {}

    public Item(String penznem, BigDecimal kozep) {
        super();
        this.penznem = penznem;
        this.kozep = kozep;
    }

    public String getPenznem() {
        return penznem;
    }

    public BigDecimal getKozep() {
        return kozep;
    }
}
