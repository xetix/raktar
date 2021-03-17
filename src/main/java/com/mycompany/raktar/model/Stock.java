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
    private UnitOfMeasure unitOfMeasure;
    
    public enum UnitOfMeasure {
        db, cm, m, g, kg, csomag
    }

    public Stock(){}

    public Stock(int stock, String unitOfMeasure) {
        this.setStock(stock);
        this.setUnitOfMeasure(unitOfMeasure);
    }

    public int getStock() {
        return stock;
    }

    public String getUnitOfMeasure() {
        return (this.unitOfMeasure == null) ? "" : unitOfMeasure.toString();
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
