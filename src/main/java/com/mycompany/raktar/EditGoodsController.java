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
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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
    private Button saveBtn, cancelBtn;

    @FXML
    private Label itemNameLbl, newItemCategoryLbl, itemRenameLbl, itemVendorRenameLbl, itemDescriptionUpdateLbl, priceNewValueLbl, stockNewValueLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Goods goods = new Goods();
        ObservableList<String> list=FXCollections.observableArrayList(App.wh.getKeys());
        Collections.sort(list);
        itemCategory.setItems(list);
        goods.numericValidation(stockNewValue);
        goods.numericValidation(priceNewValue);
        goods.textValidation(itemRename);
        goods.textValidation(itemVendorRename);
        goods.descValidation(itemDescriptionUpdate);
    }

    @FXML
    private void selectedCategory(ActionEvent event) {
        ObservableList<String> list=FXCollections.observableArrayList(App.wh.getCategory(itemCategory.getValue()).getKeys());
        if (list.size()==0)
        {
            list.add("Válasszon terméket...");
            itemName.setItems(list);
            itemName.getSelectionModel().selectFirst();
            itemName.setDisable(true);
            itemNameLbl.setDisable(true);
        }
        else
        {
            itemName.setItems(null);
            itemName.setDisable(false);
            itemNameLbl.setDisable(false);
            Collections.sort(list);
            itemName.setItems(list);
            itemName.getSelectionModel().selectFirst();
        }
        //itemName.requestFocus();        //megnehezíti a billentyűzettel való kezelést, ha nem a legelsőt szeretném választani
    }

    private Goods selected;

    @FXML
    private void selectedItem(ActionEvent event) {
        if (itemName.getValue()==null || itemName.getValue().equals("Válasszon terméket..."))
        {
            itemRename.setText("");
            itemVendorRename.setText("");
            itemDescriptionUpdate.setText("");
            stockNewValue.setText("");
            priceNewValue.setText("");
            newItemCategory.getSelectionModel().clearSelection(); //Kategória
            List<String> list = new ArrayList<String>();
            list.add("Kategória");
            newItemCategory.setItems(FXCollections.observableArrayList(list));
            newItemCategory.getSelectionModel().selectFirst();
            itemStockUnit.setItems(null);
            itemStockUnit.setValue("Kategória");
            itemStockUnit.getSelectionModel().clearSelection();
            itemStockUnit.setItems(null);
            itemStockUnit.setValue("");
            itemPriceCurrency.getSelectionModel().clearSelection();
            itemPriceCurrency.setItems(null);
            return;
        }
        selected = App.wh.getCategory(itemCategory.getValue()).getProduct(itemName.getValue());
        unlockChk.setDisable(false);
        ObservableList<String> list=FXCollections.observableArrayList(App.wh.getKeys());
        Collections.sort(list);
        newItemCategory.setItems(list);
        newItemCategory.setValue(itemCategory.getValue());
        //itemStockUnit.setItems(FXCollections.observableArrayList(Arrays.asList(Unit.values())));
        itemStockUnit.setItems(null);
        itemStockUnit.setItems(FXCollections.observableArrayList(Unit.values()));
        itemStockUnit.setValue("" + selected.getStock().getUnit());
        //itemPriceCurrency.setItems(FXCollections.observableArrayList(Arrays.asList(Currency.values())));
        itemPriceCurrency.setItems(null);
        itemPriceCurrency.setItems(FXCollections.observableArrayList(Currency.values()));
        //itemPriceCurrency.setValue("" + selected.getDisplayedPrice().getCurrency());
        switch (selected.getOriginalPrice().getCurrency())
        {
            case "HUF":
                itemPriceCurrency.getSelectionModel().select(0);
                break;
            case "USD":
                itemPriceCurrency.getSelectionModel().select(1);
                break;
            case "EUR":
                itemPriceCurrency.getSelectionModel().select(2);
                break;
            case "GBP":
                itemPriceCurrency.getSelectionModel().select(3);
                break;
        }
        itemRename.setText(selected.getName());
        itemVendorRename.setText(selected.getVendor());
        itemDescriptionUpdate.setText(selected.getDescription());
        stockNewValue.setText("" + selected.getStock().getStockNumber().replace(" ",""));
        priceNewValue.setText("" + selected.getOriginalPrice().getPriceNumber().replace(" ",""));
        stockNewValue.setDisable(false);
        stockNewValueLbl.setDisable(false);
        priceNewValue.setDisable(false);
        priceNewValueLbl.setDisable(false);
        if (unlockChk.isSelected())
            unlockChk.fire();
        saveBtn.setDisable(true);
        /*}else{
            unlockChk.setSelected(false);
            unlockChk.setDisable(true);
            stockNewValue.setDisable(true);
            stockNewValueLbl.setDisable(true);
            stockNewValue.setText(null);
            priceNewValue.setDisable(true);
            priceNewValueLbl.setDisable(true);
            priceNewValue.setText(null);
            itemRename.setDisable(true);
            itemRenameLbl.setDisable(true);
            itemRename.setText(null);
            itemVendorRename.setDisable(true);
            itemVendorRenameLbl.setDisable(true);
            itemVendorRename.setText(null);
            itemDescriptionUpdate.setDisable(true);
            itemDescriptionUpdateLbl.setDisable(true);
            itemDescriptionUpdate.setText(null);
            newItemCategory.setDisable(true);
            newItemCategoryLbl.setDisable(true);
            newItemCategory.setValue(null);
            itemPriceCurrency.setDisable(true);
            itemStockUnit.setDisable(true);
            itemStockUnit.setValue(null);
            itemPriceCurrency.setValue(null);
            unlockChk.setDisable(true);
            saveBtn.setDisable(true);
        }        */
    }

    @FXML
    private void changeUnlockChk(ActionEvent event) {
        if (this.unlockChk.isSelected())
        {
            unlockChk.setDisable(true);
            itemRename.setDisable(false);
            itemRenameLbl.setDisable(false);
            itemVendorRename.setDisable(false);
            itemVendorRenameLbl.setDisable(false);
            itemDescriptionUpdate.setDisable(false);
            itemDescriptionUpdateLbl.setDisable(false);
            newItemCategory.setDisable(false);
            newItemCategoryLbl.setDisable(false);
            itemPriceCurrency.setDisable(false);
            itemStockUnit.setDisable(false);
        }
        else
        {
            itemRename.setDisable(true);
            itemRenameLbl.setDisable(true);
            itemVendorRename.setDisable(true);
            itemVendorRenameLbl.setDisable(true);
            itemDescriptionUpdate.setDisable(true);
            itemDescriptionUpdateLbl.setDisable(true);
            newItemCategory.setDisable(true);
            newItemCategoryLbl.setDisable(true);
            itemPriceCurrency.setDisable(true);
            itemStockUnit.setDisable(true);
        }
    }

    @FXML
    private void fieldValidator() {
        if (
                itemCategory.getSelectionModel().getSelectedIndex() != -1 &&
                itemName.getSelectionModel().getSelectedIndex() != -1 &&
                newItemCategory.getSelectionModel().getSelectedIndex() != -1 &&
                itemRename.getText().trim().length() != 0 &&
                itemVendorRename.getText().trim().length() != 0 &&
                stockNewValue.getText().trim().length() != 0 &&
                priceNewValue.getText().trim().length() != 0 &&
                //!stockNewValue.getText().substring(stockNewValue.getText().length()-1).equals(".") &&
                !stockNewValue.getText().endsWith(".") &&
                //!priceNewValue.getText().substring(priceNewValue.getText().length()-1).equals(".") &&
                !priceNewValue.getText().endsWith(".") &&
                (
                        newItemCategory.getSelectionModel().getSelectedIndex() != itemCategory.getSelectionModel().getSelectedIndex() ||
                        !itemRename.getText().trim().equals(selected.getName()) ||
                        !itemVendorRename.getText().trim().equals(selected.getVendor()) ||
                        !itemDescriptionUpdate.getText().equals(selected.getDescription()) ||
                        !stockNewValue.getText().replace(',', '.').equals(selected.getStock().getStockNumber().replace(" ","")) ||
                        !priceNewValue.getText().replace(',', '.').equals(selected.getOriginalPrice().getPriceNumber().replace(" ","")) ||
                        !itemStockUnit.getSelectionModel().getSelectedItem().toString().equals(selected.getStock().getUnit()) ||
                        !itemPriceCurrency.getSelectionModel().getSelectedItem().toString().equals(selected.getOriginalPrice().getCurrency())
                )

        )
            saveBtn.setDisable(false);
        else
            saveBtn.setDisable(true);
    }

    @FXML
    public void keyPressed(KeyEvent e) {
        fieldValidator();
        if (!e.getSource().equals(itemDescriptionUpdate) && e.getCharacter().equals("\r"))     //Entert nyomtak
            saveBtn.fire();
        if (e.getSource().equals(itemDescriptionUpdate) && e.getCharacter().equals("\n"))      //ha a leírás mezőben CTRL+Entert nyomtak
            saveBtn.fire();
        if (e.getCharacter().getBytes()[0] == 27)   //ESC-et nyomtak
            cancelBtn.fire();
    }

    @FXML
    private void edit(ActionEvent event) {
        try
        {
            Stock stock = new Stock(this.stockNewValue.getText().replace(',', '.').trim(), this.itemStockUnit.getValue().toString());
            Price price = new Price(this.priceNewValue.getText().replace(',', '.').trim(), this.itemPriceCurrency.getValue().toString());
            Goods target = new Goods(
                    this.itemRename.getText(),
                    this.itemVendorRename.getText(),
                    this.itemDescriptionUpdate.getText(),
                    stock,
                    price
            );
            App.wh.getCategory(itemCategory.getValue()).delProduct(selected.getName());
            App.wh.getCategory(newItemCategory.getValue()).addProduct(target);
            App.mainController.refresh();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }
        catch (Exception e)
        {
            App.mainController.alert("Hiba",e.getMessage());
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
