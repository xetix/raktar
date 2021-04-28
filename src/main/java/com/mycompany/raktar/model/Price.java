/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import java.math.BigDecimal;

/**
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class Price implements java.io.Serializable {
    private String priceNumber;
    private Currency currency;

    public enum Currency {
        HUF, USD, EUR, GBP
    }

    public Price() {
    }

    public Price(String priceNumber, String currency) {
        /*if (currency.equals("HUF") && priceNumber.indexOf('.') != -1)     // ez azért volt, hogy a HUF esetében a tizedespontot és utána letörölje
            this.setPriceNumber(priceNumber.substring(0, priceNumber.indexOf('.')));
        else*/
        this.setPriceNumber(priceNumber);
        this.setCurrency(currency);
    }

    public void setPriceNumber(String priceNumber) {
        if (new BigDecimal(priceNumber.replace(" ", "")).compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Az ár nem lehet negatív!");
        priceNumber = new BigDecimal(priceNumber).toPlainString();
        if (new BigDecimal(priceNumber).compareTo(BigDecimal.ZERO) == 0)
        {
            this.priceNumber = "0";
            return;
        }
        String ujPriceNumber = "";
        String[] reszek = priceNumber.split("\\.");
        if (new BigDecimal(reszek[0].replace(" ", "")).compareTo(new BigDecimal(999)) > 0)  // ha az egészrész nagyobb mint 999, tegyen bele szóközöket
        {

            int i = reszek[0].length() - 3;
            for (; i >= 0; i -= 3)
            {
                ujPriceNumber = " " + reszek[0].substring(i, i + 3) + ujPriceNumber;
            }
            if (i != -3)                                                    // ha még nem ért az elejére
                ujPriceNumber = reszek[0].substring(0, i + 3) + ujPriceNumber;
            else                                                         // ha berakta minden részét, akkor a vezető space-t szedje le
                ujPriceNumber = ujPriceNumber.substring(1);
        }
        else
            ujPriceNumber = reszek[0];
        if (reszek.length > 1)              // ha van tizedespont benne, akkor tegyük vissza+3 számot
        {
            if (reszek[1].length() >= 3)
                ujPriceNumber += "." + reszek[1].substring(0, 3);
            else
            {
                if (Integer.parseInt(reszek[1]) != 0)
                    ujPriceNumber += "." + reszek[1];
            }
        }
        this.priceNumber = ujPriceNumber;
    }

    public void setCurrency(String currency) {
        try
        {
            this.currency = Currency.valueOf(currency);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Nem megfelelő pénznem!");
        }
    }

    public String getPriceNumber() {
        return priceNumber;
    }

    public String getCurrency() {
        return (currency == null) ? "" : currency.toString();
    }

    @Override
    public String toString() {
        /*if (currency.equals("HUF"))               // ez azért volt, hogy a HUF esetében a tizedespontot és utána letörölje
            return String.valueOf(priceNumber).substring(String.valueOf(priceNumber).indexOf(".")) + " " + currency;
        else*/
        return priceNumber + " " + currency;
    }

}
