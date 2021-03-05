/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import java.util.List;

/**
 *
 * @author Kovács Gergő
 */
public class Warehouse {
    private List<Category> categories;

    public Warehouse() {
    }

    public Warehouse(List<Category> categories) {
        this.categories = categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }
    
    public void addCategory(Category category) {
        this.categories.add(category);
    }
    
    public void delCategory(Category category){
        this.categories.remove(category);
    }

    @Override
    public String toString() {
        return "Warehouse{" + "categories=" + categories + '}';
    }  
    
}
