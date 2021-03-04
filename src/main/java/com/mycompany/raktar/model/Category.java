/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import com.mycompany.raktar.model.Goods;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kovács Gergő
 */
public class Category {
    private String name;
    private List<Goods> products;

    public Category(String name, List<Goods> products) {
        this.name = name;
        this.products = products;
    } 
    
}
