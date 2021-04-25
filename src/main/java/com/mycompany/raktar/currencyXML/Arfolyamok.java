package com.mycompany.raktar.currencyXML;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Arfolyamok {
    private Deviza deviza;

    public Arfolyamok() {
    }
    public Arfolyamok(Deviza deviza) {
        super();
        this.deviza=deviza;
    }

    public Deviza getDeviza() {
        return deviza;
    }
}
