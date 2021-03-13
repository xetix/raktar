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
public class EditGoodsController implements Initializable {
    @FXML
    private ComboBox<String> itemCategory;
    
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
    private void edit(ActionEvent event){
        App.mainController.refresh();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML
    private void cancel(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }
}
