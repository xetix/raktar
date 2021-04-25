/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar.model;

import java.math.BigDecimal;

/**
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class Stock implements java.io.Serializable {
    private String stockNumber;
    private Unit unit;

    public enum Unit {
        darab, csomag, doboz, raklap, zsák, méter, centiméter, milliméter, kilogramm, dekagramm, gramm, liter, deciliter, milliliter
    }

    public Stock() {
    }

    public Stock(String stockNumber, String unit) {
        this.setStockNumber(stockNumber);
        this.getUnit(unit);
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public String getUnit() {
        return (this.unit == null) ? "" : unit.toString();
    }

    public void setStockNumber(String stockNumber) {
        if (new BigDecimal(stockNumber.replace(" ", "")).compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("A készlet nem lehet negatív!");
        stockNumber = new BigDecimal(stockNumber).toPlainString();
        if (new BigDecimal(stockNumber).compareTo(BigDecimal.ZERO) == 0)
        {
            this.stockNumber = "0";
            return;
        }
        String ujStockNumber = "";
        String[] reszek = stockNumber.split("\\.");
        if (new BigDecimal(reszek[0].replace(" ", "")).compareTo(new BigDecimal(999)) > 0)  // ha az egészrész nagyobb mint 999, tegyen bele szóközöket
        {
            int i = reszek[0].length() - 3;
            for (; i >= 0; i -= 3)
            {
                ujStockNumber = " " + reszek[0].substring(i, i + 3) + ujStockNumber;
            }
            if (i != -3)                                                    // ha még nem ért az elejére
                ujStockNumber = reszek[0].substring(0, i + 3) + ujStockNumber;
            else                                                         // ha berakta minden részét, akkor a vezető space-t szedje le
                ujStockNumber = ujStockNumber.substring(1);
        }
        else
            ujStockNumber = reszek[0];
        if (reszek.length > 1)              // ha van tizedespont benne, akkor tegyük vissza+3 számot
        {
            if (reszek[1].length() >= 3)
                ujStockNumber += "." + reszek[1].substring(0, 3);
            else
            {
                if (Integer.parseInt(reszek[1]) != 0)
                    ujStockNumber += "." + reszek[1];
            }
        }
        this.stockNumber = ujStockNumber;
    }

    public void getUnit(String unit) {
        try
        {
            this.unit = Unit.valueOf(unit);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Nem megfelelő mértékegység!");
        }
    }

    @Override
    public String toString() {
        return stockNumber + " " + unit;
    }
}
