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
public final class Price implements java.io.Serializable{
    private float price;
    private Currency currency;
    
    public static enum Currency {
        HUF, USD, GBP, EUR, JPY, RUB, CNY
    }

    public Price(float price, String currency) {
        this.setPrice(price);
        this.setCurrency(currency);
    }

    public void setPrice(float price) {
        if(price<0) throw new IllegalArgumentException("Az ár nem lehet negatív!");
        this.price = price;
    }

    public void setCurrency(String currency) {
        try{
            this.currency = Currency.valueOf(currency);
        }catch( Exception e ){
            throw new IllegalArgumentException("Nem megfelelő pénznem!");
        }
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
