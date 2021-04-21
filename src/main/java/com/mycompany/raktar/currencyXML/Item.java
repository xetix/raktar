package com.mycompany.raktar.currencyXML;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    private String penznem;
    private Float kozep;

    public Item() {}

    public Item(String penznem, Float kozep) {
        super();
        this.penznem = penznem;
        this.kozep = kozep;
    }

    public String getPenznem() {
        return penznem;
    }

    public Float getKozep() {
        return kozep;
    }
}
