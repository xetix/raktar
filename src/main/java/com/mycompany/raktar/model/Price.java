/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

/**
 *
 * @author Kovács Gergő
 */
public final class Price {
    private float price;
    private Currency currency;
    
    public static enum Currency {
        HUF, USD, GBP, EUR, JPY, RUB, CNY
    }

    public Price(float price, String currency) {
        this.price = price;
        this.currency = Currency.valueOf(currency);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return price + " " + currency;
    }    
    
}
