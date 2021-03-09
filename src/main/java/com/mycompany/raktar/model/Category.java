/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Kovács Gergő
 */
public class Category {
    private final String name;
    private HashMap<String, Goods> products = new HashMap<String, Goods>();

    public Category(String name) {
        if( name.isEmpty() ) throw new IllegalArgumentException("Nincs megadva a kategória név.");
        this.name = name;
    }    
    
    public Category(String name, HashMap<String, Goods> products) {
        if( name.isEmpty() ) throw new IllegalArgumentException("Nincs megadva a kategória név.");
        this.name = name;
        this.products = products;
    } 

    public String getName() {
        return name;
    }

    public void setProducts(HashMap<String, Goods> products) {
        this.products = products;
    }
    
    public void addProduct(Goods product){
        this.products.put( product.getName(), product );
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final Category other = (Category) obj;
        return Objects.equals(this.name, other.name);
    }
    
    @Override
    public String toString() {
        return "Category{" + "name=" + name + ", products=" + products + '}';
    }    
}
