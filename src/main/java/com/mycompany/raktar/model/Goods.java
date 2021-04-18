/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.Objects;

/**
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
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

    public static void numericValidation(TextField field) {
        field.setTextFormatter(new TextFormatter<>(c -> {
            if (c.isContentChange())
            {
                String newText = c.getControlNewText();
                String newChar = c.getText();
                if (newText.length() == 0)   // ne nézze tovább, ha üresen marad a szöveg (pl. csak belekattintottál)
                    return c;
                if (newText.contains(" "))   // nem tartalmazhat space-t
                    return null;
                if (newText.length() == 10)  // maximális szám: 999 999 999 vagy 999 999 99.9
                    return null;
                if (newText.contains("-"))   // nem tartalmazhat mínuszjelet
                    return null;
                if (newChar.equals(","))
                {
                    c.setText(".");          // ',' helyett '.' jelenjen meg
                    newText = c.getControlNewText();
                }
                try
                {
                    Integer.parseInt(newText.replace(".", ""));   // 'd' és 'f' Float szerint még elfogadott
                    Float.parseFloat(newText);
                    return c;
                }
                catch (NumberFormatException e)
                {
                }
                return null;
            }
            return c;
        }));
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
