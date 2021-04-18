/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

/**
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class Price implements java.io.Serializable {
    private String priceNumber;
    private Currency currency;

    public enum Currency {
        HUF, USD, EUR, GBP, JPY, RUB, CNY
    }

    public Price() {
    }

    public Price(String priceNumber, String currency) {
        this.setPriceNumber(priceNumber);
        this.setCurrency(currency);
    }

    public void setPriceNumber(String priceNumber) {
        if (Float.parseFloat(priceNumber) < 0) throw new IllegalArgumentException("Az ár nem lehet negatív!");
        this.priceNumber = priceNumber;
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
        if (currency.equals("HUF"))
            return String.valueOf(priceNumber).substring(String.valueOf(priceNumber).indexOf(".")) + " " + currency;
        else
            return priceNumber + " " + currency;
    }

}
