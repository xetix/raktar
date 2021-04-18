/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

/**
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class Stock implements java.io.Serializable{
    private String stockNumber;
    private Unit unit;
    
    public enum Unit {
        darab, csomag, doboz, méter, centiméter, milliméter, kilogramm, dekagramm, gramm, liter, deciliter, milliliter
    }

    public Stock(){}

    public Stock(String stockNumber, String unit) {
        this.setStockNumber(stockNumber);
        this.getUnit(unit);
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public String getUnit() {
        return (this.unit == null) ? "" : unit.toString();
    }

    public void setStockNumber(String stockNumber) {
        if(Float.parseFloat(stockNumber) < 0) throw new IllegalArgumentException("A készlet nem lehet negatív!");
        this.stockNumber = stockNumber;
    }

    public void getUnit(String unit) {
        try{
            this.unit = Unit.valueOf(unit);
        }catch( Exception e ){
            throw new IllegalArgumentException("Nem megfelelő mértékegység!");
        }
    }

    @Override
    public String toString() {
        return stockNumber + " " + unit;
    }
}
