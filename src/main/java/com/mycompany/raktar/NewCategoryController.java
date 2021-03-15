/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Category;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő
 */
public class NewCategoryController implements Initializable {
    @FXML
    private TextField newCategoryName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void addButtonOnAction(ActionEvent event){
        try{
            Category cat = new Category(this.newCategoryName.getText());
            if(App.wh.getCategory(cat.getName()) != null) throw new IllegalArgumentException("Létező kategória név!");
            App.wh.addCategory(cat);
            App.mainController.refresh();
            exitButtonOnAction(event);
        }catch(Exception e){
            App.mainController.alert("Hiba", e.getMessage());
        }
    }
    
    @FXML
    private void exitButtonOnAction(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }
}
