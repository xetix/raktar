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
    private int price;
    private Currency currency;
    
    public enum Currency {
        HUF, USD, GBP, EUR, JPY, RUB, CNY
    }

    public Price(int price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getPrice() {
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
