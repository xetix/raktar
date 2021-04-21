package com.mycompany.raktar.currencyXML;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Deviza {
    @XmlElement(name = "item")
    private List<Item> item;

    public Deviza() {}

    public Deviza(List<Item> item) {
        super();
        this.item = item;
    }
}
