/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import com.mycompany.raktar.model.Price.Currency;

/**
 *
 * @author Kovács Gergő
 */
public class Goods {
    private final int articleNumber; //Cikkszám
    
    private String name; //Név
    private String vendor; //Gyártó
    private String description; //Leírás
    
    private int stock;  //Készlet
    private String unitOfMeasure; //Mértékegység
    
    private Price price; //Ár
    
    private enum UnitOfMeasure {
        db, cm, m, g, kg, csomag
    }

    public Goods(
        int articleNumber, 
        String name, 
        String vendor, 
        String description, 
        int stock, 
        String unitOfMeasure, 
        Price price
    ) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.vendor = vendor;
        this.description = description;
        this.stock = stock;
        this.unitOfMeasure = unitOfMeasure;
        this.price = price;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDescription() {
        return description;
    }

    public String getStock() {
        return stock + " " + unitOfMeasure;
    }

    public String getPrice() {
        return price.toString();
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public void setPrice(int price) {
        this.price.setPrice(price);
    }

    public void setCurrency(Currency currency) {
        this.price.setCurrency(currency);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.articleNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Goods other = (Goods) obj;
        if (this.articleNumber != other.articleNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Termék{" + "Cikkszám: " + articleNumber + 
               ", Név: " + name + 
                ", Gyártó: " + vendor + 
                ", Leírás: " + description + 
                ", Készlet: " + stock +" " +  unitOfMeasure + 
                ", Ár: " + price;
    }
    
    
}
