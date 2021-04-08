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
public class Stock implements java.io.Serializable{
    private int stock;
    private Unit unit;
    
    public enum Unit {
        darab, csomag, doboz, centiméter, méter, gramm, kilogramm, liter, deciliter
    }

    public Stock(){}

    public Stock(int stock, String unit) {
        this.setStock(stock);
        this.getUnit(unit);
    }

    public int getStock() {
        return stock;
    }

    public String getUnit() {
        return (this.unit == null) ? "" : unit.toString();
    }

    public void setStock(int stock) {
        if(stock<0) throw new IllegalArgumentException("A készlet nem lehet negatív!");
        this.stock = stock;
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
        return stock + " " + unit;
    }
}
