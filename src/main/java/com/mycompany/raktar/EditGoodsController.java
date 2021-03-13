/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Goods;
import com.mycompany.raktar.model.Price.Currency;
import com.mycompany.raktar.model.Stock.UnitOfMeasure;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő
 */
public class EditGoodsController implements Initializable {
    @FXML
    private ComboBox<String> itemCategory, itemName, newItemCategory;
    
    @FXML
    private ComboBox itemStockUnitOfMeasure, itemPriceCurrency;
    
    @FXML
    private TextField itemRename, itemVendorRename, stockNewValue, priceNewValue;
    
    @FXML
    private TextArea itemDescriptionUpdate;
    
    @FXML
    private CheckBox unlockChk;
    
    @FXML
    private Button saveBtn;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemCategory.setItems(FXCollections.observableArrayList(App.wh.getKeys()));
    }
    
    @FXML
    private void selectedCategory(ActionEvent event){
        itemName.setItems(FXCollections.observableArrayList(App.wh.getCategory(itemCategory.getValue()).getKeys()));
        itemName.setDisable(false);
    }
    
    @FXML
    private void selectedItem(ActionEvent event){
        Goods selected = App.wh.getCategory(itemCategory.getValue()).getProduct(itemName.getValue());
        if(selected != null){
            newItemCategory.setItems(FXCollections.observableArrayList(App.wh.getKeys()));
            newItemCategory.setValue(itemCategory.getValue());
            itemStockUnitOfMeasure.setItems(FXCollections.observableArrayList(Arrays.asList(UnitOfMeasure.values())));
            itemStockUnitOfMeasure.setValue(""+selected.getStock().getUnitOfMeasure());
            itemPriceCurrency.setItems(FXCollections.observableArrayList(Arrays.asList(Currency.values())));
            itemPriceCurrency.setValue(""+selected.getPrice().getCurrency());
            itemRename.setText(selected.getName());
            itemVendorRename.setText(selected.getVendor());
            itemDescriptionUpdate.setText(selected.getDescription());
            stockNewValue.setText(""+selected.getStock().getStock());
            priceNewValue.setText(""+selected.getPrice().getPrice());
            stockNewValue.setDisable(false);
            priceNewValue.setDisable(false);
            unlockChk.setDisable(false);
            saveBtn.setDisable(false);
        }else{
            unlockChk.setSelected(false);
            unlockChk.setDisable(true);
            stockNewValue.setDisable(true);
            stockNewValue.setText(null);
            priceNewValue.setDisable(true);
            priceNewValue.setText(null);
            itemRename.setDisable(true);
            itemRename.setText(null);
            itemVendorRename.setDisable(true);
            itemVendorRename.setText(null);
            itemDescriptionUpdate.setDisable(true);
            itemDescriptionUpdate.setText(null);
            newItemCategory.setDisable(true);
            newItemCategory.setValue(null);
            itemPriceCurrency.setDisable(true);
            itemStockUnitOfMeasure.setDisable(true);
            itemStockUnitOfMeasure.setValue(null);
            itemPriceCurrency.setValue(null);
            saveBtn.setDisable(true);
        }        
    }
    
    @FXML
    private void changeUnlockChk(ActionEvent event){
        if(this.unlockChk.isSelected()){
            itemRename.setDisable(false);
            itemVendorRename.setDisable(false);
            itemDescriptionUpdate.setDisable(false);
            newItemCategory.setDisable(false);
            itemPriceCurrency.setDisable(false);
            itemStockUnitOfMeasure.setDisable(false);
        }else{
            itemRename.setDisable(true);
            itemVendorRename.setDisable(true);
            itemDescriptionUpdate.setDisable(true);
            newItemCategory.setDisable(true);
            itemPriceCurrency.setDisable(true);
            itemStockUnitOfMeasure.setDisable(true);
        }
    }
    
    @FXML
    private void edit(ActionEvent event){
        App.mainController.refresh();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML
    private void cancel(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }
}
