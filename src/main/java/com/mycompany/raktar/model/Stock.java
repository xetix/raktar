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
public class Stock {
    private int stock;  //Készlet
    private UnitOfMeasure unitOfMeasure; //Mértékegység
    
    enum UnitOfMeasure {
        db, cm, m, g, kg, csomag
    }

    public Stock(int stock, UnitOfMeasure unitOfMeasure) {
        this.stock = stock;
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getStock() {
        return stock;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public String toString() {
        return stock + " " + unitOfMeasure;
    }
}
