/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő
 */
public class DelGoodsController implements Initializable {
    @FXML
    private ComboBox<String> itemCategory, itemName;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemCategory.setItems(FXCollections.observableArrayList(App.wh.getKeys()));
        itemName.setDisable(true);
    }    
    
    @FXML
    private void selectedCategory(ActionEvent event){
        itemName.setItems(FXCollections.observableArrayList(App.wh.getCategory(itemCategory.getValue()).getKeys()));
        itemName.setDisable(false);        
    }
    
    @FXML
    private void del(ActionEvent event){
        App.wh.getCategory(itemCategory.getValue()).delProduct(itemName.getValue());
        App.mainController.refresh();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML
    private void cancel(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }
}
