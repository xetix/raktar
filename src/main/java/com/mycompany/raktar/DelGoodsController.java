/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class DelGoodsController implements Initializable {
    @FXML
    private ComboBox<String> itemCategory, itemName;
    @FXML
    private Label delGoodsNameLabel;

    @FXML
    private Button delBtn;

    @FXML
    private Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list=FXCollections.observableArrayList(App.wh.getKeys());
        Collections.sort(list);
        itemCategory.setItems(list);
        delGoodsNameLabel.setDisable(true);
        itemName.setDisable(true);
        delBtn.setDisable(true);
    }

    @FXML
    private void selectedCategory(ActionEvent event) {
        ObservableList<String> list=FXCollections.observableArrayList(App.wh.getCategory(itemCategory.getValue()).getKeys());
        itemName.setItems(list);
        if (list.size()==0)
        {
            itemName.setItems(null);
            itemName.setDisable(true);
            delGoodsNameLabel.setDisable(true);
        }
        else
        {
            itemName.setDisable(false);
            delGoodsNameLabel.setDisable(false);
            Collections.sort(list);
            itemName.setItems(list);
        }
        //itemName.requestFocus();        //megnehezíti a billentyűzettel való kezelést, ha nem a legelsőt szeretném választani
    }

    @FXML
    private void selectedGoods(ActionEvent event) {
        delBtn.setDisable(false);
        //delBtn.requestFocus();        //megnehezíti a billentyűzettel való kezelést, ha nem a legelsőt szeretném választani
    }

    @FXML
    public void keyPressed(KeyEvent e) {
        if (e.getCharacter().getBytes()[0]==27)
            cancelBtn.fire();
    }

    @FXML
    private void del(ActionEvent event) {
        try
        {
            App.wh.getCategory(itemCategory.getValue()).delProduct(itemName.getValue());
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
