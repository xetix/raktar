/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import java.util.Objects;

/**
 *
 * @author Kovács Gergő
 */
public class Goods implements java.io.Serializable{
    private String name;
    private String vendor="";
    private String description="";

    private Stock stock = new Stock();
    private Price price = new Price();

    public Goods(String name){
        this.name = name;
    }

    public Goods(
        String name, 
        String vendor, 
        String description, 
        Stock stock,
        Price price
    ) {
        this.name = name;
        this.setVendor(vendor);
        this.setDescription(description);
        this.setStock(stock);
        this.setPrice(price);
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

    public Stock getStock() {
        return stock;
    }

    public Price getPrice() {
        return price;
    }
    
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.vendor);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.vendor, other.vendor);
    }    

    @Override
    public String toString() {
        return "Név: " + name + ", " +
               "Gyártó: " + vendor + ", " + 
               "Leírás: " + description + ", " + 
               "Készlet: " + stock + ", " +
               "Ár: " + price + " / " + stock.getUnit() + ".";
    }   
    
}
