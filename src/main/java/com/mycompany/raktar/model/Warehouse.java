/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import java.util.HashMap;

/**
 *
 * @author Kovács Gergő
 */
public class Warehouse implements java.io.Serializable{
    private HashMap<String, Category> categories = new HashMap<String, Category>();

    public Warehouse() {
    }

    public void setCategories(HashMap<String, Category> categories) {
        this.categories = categories;
    }

    public HashMap<String, Category> getCategories() {
        return categories;
    }
    
    public void addCategory(Category category) {
        this.categories.put(category.getName(), category);
    }
    
    public void delCategory(Category category){
        this.categories.remove(category.getName());
    }
    
    public void addGoods(String category, Goods goods){
        this.categories.get(category).addProduct(goods);
    }

    @Override
    public String toString() {
        return "Warehouse{\n" + "categories=" + categories + "\n}";
    }  
    
}
