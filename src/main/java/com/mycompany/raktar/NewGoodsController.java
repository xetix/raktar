/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Goods;
import com.mycompany.raktar.model.Price;
import com.mycompany.raktar.model.Price.Currency;
import com.mycompany.raktar.model.Stock;
import com.mycompany.raktar.model.Stock.UnitOfMeasure;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő
 */
public class NewGoodsController implements Initializable {
    @FXML
    private ComboBox<String> newItemCategory;
    
    @FXML
    private TextField newItemName;

    @FXML
    private TextField newItemVendor;

    @FXML
    private TextArea newItemDescription;

    @FXML
    private TextField newItemStock;

    @FXML
    private ComboBox newItemStockUnitOfMeasure;

    @FXML
    private TextField newItemPrice;

    @FXML
    private ComboBox newItemPriceCurrency;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newItemCategory.setItems(FXCollections.observableArrayList(App.wh.getKeys()));
        newItemStockUnitOfMeasure.getItems().addAll(Arrays.asList(UnitOfMeasure.values()));
        newItemPriceCurrency.getItems().addAll(Arrays.asList(Currency.values()));
    }    

    @FXML
    private void addButtonOnAction(ActionEvent event){
        Stock stock = new Stock(Integer.parseInt(this.newItemStock.getText()), this.newItemStockUnitOfMeasure.getValue().toString());
        Price price = new Price(Float.parseFloat(this.newItemPrice.getText()), this.newItemPriceCurrency.getValue().toString());
        Goods g = new Goods(
                this.newItemName.getText(),
                this.newItemVendor.getText(),
                this.newItemDescription.getText(),
                stock,
                price
        );
        App.wh.addGoods(this.newItemCategory.getValue(), g);
        App.mainController.refresh();
        exitButtonOnAction(event);
    }
    
    @FXML
    private void exitButtonOnAction(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }
}