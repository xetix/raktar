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
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
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
    private ComboBox newItemStockUnit;

    @FXML
    private TextField newItemPrice;

    @FXML
    private ComboBox newItemPriceCurrency;

    @FXML
    private Button newItemAddBtn;

    @FXML
    private Button newItemCancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list=FXCollections.observableArrayList(App.wh.getKeys());
        Collections.sort(list);
        newItemCategory.setItems(list);
        Goods.numericValidation(newItemStock);
        newItemStockUnit.getItems().addAll(Arrays.asList(Unit.values()));
        Goods.numericValidation(newItemPrice);
        newItemPriceCurrency.getItems().addAll(Arrays.asList(Currency.values()));
        newItemStockUnit.getSelectionModel().selectFirst();
        newItemPriceCurrency.getSelectionModel().selectFirst();
        newItemAddBtn.setDisable(true);
    }

    @FXML
    private void fieldValidator() {
        if (
                newItemCategory.getSelectionModel().getSelectedIndex() != -1 &&
                newItemName.getText().trim().length() != 0 &&
                newItemVendor.getText().trim().length() != 0 &&
                newItemStock.getText().trim().length() != 0 &&
                newItemPrice.getText().trim().length() != 0 &&
                !newItemStock.getText().substring(newItemStock.getText().length()-1).equals(".") &&
                !newItemPrice.getText().substring(newItemPrice.getText().length()-1).equals(".")
        )
            newItemAddBtn.setDisable(false);
        else
            newItemAddBtn.setDisable(true);
    }

    @FXML
    public void keyPressed(KeyEvent e) {
        fieldValidator();
        if (!e.getSource().equals(newItemDescription) && e.getCharacter().equals("\r"))     //Entert nyomtak
            newItemAddBtn.fire();
        if (e.getSource().equals(newItemDescription) && e.getCharacter().equals("\n"))      //ha a leírás mezőben CTRL+Entert nyomtak
            newItemAddBtn.fire();
        if (e.getCharacter().getBytes()[0] == 27)       //ESC-et nyomtak
            newItemCancelBtn.fire();
    }

    @FXML
    private void addButtonOnAction(ActionEvent event) {
        try
        {
            Stock stock = new Stock(this.newItemStock.getText().replace(',', '.'), this.newItemStockUnit.getValue().toString());
            Price price = new Price(this.newItemPrice.getText().replace(',', '.'), this.newItemPriceCurrency.getValue().toString());
            Goods g = new Goods(
                    this.newItemName.getText().trim(),
                    this.newItemVendor.getText().trim(),
                    this.newItemDescription.getText().trim(),
                    stock,
                    price
            );
            App.wh.addGoods(this.newItemCategory.getValue(), g);
            App.mainController.refresh();
            exitButtonOnAction(event);
        }
        catch (Exception e)
        {
            App.mainController.alert("Hiba",e.getMessage());
        }
    }

    @FXML
    private void exitButtonOnAction(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
