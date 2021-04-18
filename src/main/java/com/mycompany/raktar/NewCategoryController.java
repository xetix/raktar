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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class NewCategoryController implements Initializable {
    @FXML
    private TextField newCategoryName;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBtn.setDisable(true);
    }

    @FXML
    public void keyPressed(KeyEvent e) {
        if (newCategoryName.getText().trim().length()==0)
            addBtn.setDisable(true);
        else
        {
            addBtn.setDisable(false);
            if (e.getCharacter().equals("\r"))
                addBtn.fire();
        }
        if (e.getCharacter().getBytes()[0]==27)
            cancelBtn.fire();
    }

    @FXML
    private void addButtonOnAction(ActionEvent event) {
        try
        {
            Category cat = new Category(this.newCategoryName.getText().trim());
            if (App.wh.getCategory(cat.getName()) != null) throw new IllegalArgumentException("Létező kategórianév!");
            App.wh.addCategory(cat);
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
