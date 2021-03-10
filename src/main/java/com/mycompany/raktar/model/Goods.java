/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import com.mycompany.raktar.model.Price.Currency;
import com.mycompany.raktar.model.Stock.UnitOfMeasure;
import java.util.Objects;

/**
 *
 * @author Kovács Gergő
 */
public class Goods {
    private String name; //Név
    private String vendor; //Gyártó
    private String description; //Leírás
    
    private Stock stock;  //Készlet
    private Price price; //Ár

    public Goods(
        String name, 
        String vendor, 
        String description, 
        Stock stock,
        Price price
    ) {
        this.name = name;
        this.vendor = vendor;
        this.description = description;
        this.stock = stock;
        this.price = price;
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
        return stock.toString();
    }

    public String getPrice() {
        return price.toString();
    }
    
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock, UnitOfMeasure unitOfMeasure) {
        this.stock.setStock(stock);
        this.stock.setUnitOfMeasure(unitOfMeasure);
    }

    public void setPrice(int price, Currency currency) {
        this.price.setPrice(price);
        this.price.setCurrency(currency);
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
        return "Termék{\n" + 
               "\nNév: " + name + 
               ",\nGyártó: " + vendor + 
               ",\nLeírás: " + description + 
               ",\nKészlet: " + stock + 
               ",\nÁr: " + price +
               "\n}";
    }
    
    
}
