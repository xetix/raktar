/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Kovács Gergő
 */
public class Category implements java.io.Serializable{
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
    
    public HashMap<String, Goods> getProducts() {
        return this.products;
    }
    
    public List<String> getKeys(){
        List<String> keys = new ArrayList<>(this.products.keySet());
        return keys;
    }
    
    public void delProduct(String name){
        this.products.remove(name);
    }
    
    public Goods getProduct(String name){
        return this.products.get(name);
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
        return "Category{\n" + "name=" + name + ",\nproducts=" + products + "\n}";
    }    
}
