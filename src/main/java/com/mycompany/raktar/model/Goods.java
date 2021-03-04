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
public class Goods {
    private int articleNumber; //Cikkszám
    private String name; //Név
    private String description; //Leírás
    private int stock;  //Készlet
    private String unitOfMeasure; //Mértékegység
    private int price; //Ár
    private Currency currency; //Pénznem
    
    private enum Currency{
        HUF, USD, GBP, EUR, JPY, RUB, CNY
    }
    
    private enum UnitOfMeasure{
        db, cm, m, g, kg, csomag
    }
}
