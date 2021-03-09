package com.mycompany.raktar;

import com.mycompany.raktar.model.Category;
import com.mycompany.raktar.model.Price;
import com.mycompany.raktar.model.Price.Currency;
import com.mycompany.raktar.model.Stock.UnitOfMeasure;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ApplicationController implements Initializable {

    @FXML
    private TextField newCatName;

    @FXML
    private Button newCatAdd;

    @FXML
    private Button newCatCancel;

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
    private ComboBox newItemStockUnitOfMessure;

    @FXML
    private TextField newItemPrice;

    @FXML
    private ComboBox newItemPriceCurrency;

    @FXML
    private Button newItemAdd;

    @FXML
    private Button newItemCancel;

    @FXML
    private Label testOut;
    
    @FXML
    private void reflashTest(){
        newItemCategory.getItems().clear();
        App.wh.getCategories().keySet().forEach(key -> {
            newItemCategory.getItems().add(key);
        });
        newItemStockUnitOfMessure.getItems().clear();
        newItemStockUnitOfMessure.getItems().addAll(Arrays.asList(UnitOfMeasure.values()));
        newItemPriceCurrency.getItems().clear();
        newItemPriceCurrency.getItems().addAll(Arrays.asList(Currency.values()));
        testOut.setText(App.wh.toString());
    }
    
    @FXML
    private void addNewCategory() throws IOException{
        Category cat = new Category(this.newCatName.getText());
        App.wh.addCategory(cat);
        this.newCatName.setText("");
        this.reflashTest();
    }
    
    @FXML
    private void cancelNewCategory(){
        this.newCatName.setText("");
    }
    
    @FXML
    private void addNewItem(){
        
    }
    
    @FXML
    private void cancelNewItem(){
        this.newItemCategory.setValue("");
        this.newItemName.setText("");
        this.newItemVendor.setText("");
        this.newItemDescription.setText("");
        this.newItemStock.setText("");
        this.newItemPrice.setText("");
        this.newItemStockUnitOfMessure.setValue("");
        this.newItemPriceCurrency.setValue("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reflashTest();
    }
}