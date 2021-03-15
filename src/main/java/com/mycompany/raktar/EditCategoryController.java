/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Category;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő
 */
public class EditCategoryController implements Initializable {

    @FXML
    private Button editBtn;
    
    @FXML
    private ComboBox<String> itemCategory;
    
    @FXML
    private TextField newCategoryName;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editBtn.setDisable(true);
        itemCategory.setItems(FXCollections.observableArrayList(App.wh.getKeys()));
    }  
    
    @FXML
    public void changeCategory(){
        editBtn.setDisable(false);
    }
    
    @FXML
    public void edit(ActionEvent event){
        try{
            Category oldCat = App.wh.getCategory(itemCategory.getValue());
            Category newCat = new Category(newCategoryName.getText(),oldCat.getProducts());
            if(App.wh.getCategory(newCat.getName()) != null) throw new IllegalArgumentException("Létező kategória név!");
            App.wh.delCategory(oldCat);
            App.wh.addCategory(newCat);
            App.mainController.refresh();
            this.cancel(event);
        }catch(Exception e){
            App.mainController.alert("Hiba", e.getMessage());
        }
    }
    
    @FXML
    private void cancel(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }
    
}
