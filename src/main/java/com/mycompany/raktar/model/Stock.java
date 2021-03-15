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
public final class Stock implements java.io.Serializable{
    private int stock;  //Készlet
    private UnitOfMeasure unitOfMeasure; //Mértékegység
    
    public static enum UnitOfMeasure {
        db, cm, m, g, kg, csomag
    }

    public Stock(int stock, String unitOfMeasure) {
        this.setStock(stock);
        this.setUnitOfMeasure(unitOfMeasure);
    }

    public int getStock() {
        return stock;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setStock(int stock) {
        if(stock<0) throw new IllegalArgumentException("A készlet nem lehet negatív!");
        this.stock = stock;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        try{
            this.unitOfMeasure = UnitOfMeasure.valueOf(unitOfMeasure);
        }catch( Exception e ){
            throw new IllegalArgumentException("Nem megfelelő mértékegység!");
        }
    }

    @Override
    public String toString() {
        return stock + " " + unitOfMeasure;
    }
}
