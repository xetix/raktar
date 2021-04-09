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
import com.mycompany.raktar.model.Stock.Unit;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class EditGoodsController implements Initializable {
    @FXML
    private ComboBox<String> itemCategory, itemName, newItemCategory;
    
    @FXML
    private ComboBox itemStockUnit, itemPriceCurrency;
    
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
        addNumericValidation(stockNewValue, false);
        addNumericValidation(priceNewValue, true);
    }
    
    private static void addNumericValidation(TextField field, Boolean isFloat) {
        field.setTextFormatter(new TextFormatter<>(c -> {
            if (c.isContentChange()) {
                if (c.getControlNewText().length() == 0) {
                    return c;
                }
                try {
                    if(isFloat){
                        Float.parseFloat(c.getControlNewText());
                    }else{
                        Integer.parseInt(c.getControlNewText());
                    }
                    return c;
                } catch (NumberFormatException e) {}
                return null;
            }
            return c;
        }));
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
            itemStockUnit.setItems(FXCollections.observableArrayList(Arrays.asList(Unit.values())));
            itemStockUnit.setValue(""+selected.getStock().getUnit());
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
            itemStockUnit.setDisable(true);
            itemStockUnit.setValue(null);
            itemPriceCurrency.setValue(null);
            saveBtn.setDisable(true);
        }        
    }
    
    @FXML
    private void changeUnlockChk(ActionEvent event){
        if(this.unlockChk.isSelected()){
            unlockChk.setDisable(true);
            itemRename.setDisable(false);
            itemVendorRename.setDisable(false);
            itemDescriptionUpdate.setDisable(false);
            newItemCategory.setDisable(false);
            itemPriceCurrency.setDisable(false);
            itemStockUnit.setDisable(false);
        }else{
            itemRename.setDisable(true);
            itemVendorRename.setDisable(true);
            itemDescriptionUpdate.setDisable(true);
            newItemCategory.setDisable(true);
            itemPriceCurrency.setDisable(true);
            itemStockUnit.setDisable(true);
        }
    }
    
    @FXML
    private void edit(ActionEvent event){
        try{
            Goods source = App.wh.getCategory(itemCategory.getValue()).getProduct(itemName.getValue());
            Stock stock = new Stock(Integer.parseInt(this.stockNewValue.getText()), this.itemStockUnit.getValue().toString());
            Price price = new Price(Float.parseFloat(this.priceNewValue.getText()), this.itemPriceCurrency.getValue().toString());
            Goods target = new Goods(
                    this.itemRename.getText(),
                    this.itemVendorRename.getText(),
                    this.itemDescriptionUpdate.getText(),
                    stock,
                    price
            );
            App.wh.getCategory(itemCategory.getValue()).delProduct(source.getName());
            App.wh.getCategory(newItemCategory.getValue()).addProduct(target);
            App.mainController.refresh();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }catch(Exception e){
            App.mainController.alert(e.getMessage());
        }
    }
    
    @FXML
    private void cancel(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }
}
