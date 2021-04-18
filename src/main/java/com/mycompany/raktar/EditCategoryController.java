/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Category;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class EditCategoryController implements Initializable {

    @FXML
    private Button editBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> itemCategory;

    @FXML
    private Label newNameLabel;

    @FXML
    private TextField newCategoryName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list=FXCollections.observableArrayList(App.wh.getKeys());
        Collections.sort(list);
        itemCategory.setItems(list);
        /*---  Elsőt választja ki automatikusan -> rollback: véletlenül rossz kategória-átnevezés elkerülése végett
                + a menüben került letiltásra a gomb, így ha nincs felvéve kategória, nincs mit átnevezni, nem lehet
                  megnyitni az ablakot, nincs mit lekezelni/letiltani ---
        itemCategory.getSelectionModel().selectFirst();
        */
        editBtn.setDisable(true);/*
        if (itemCategory.getSelectionModel().isEmpty())
        {*/
            newCategoryName.setDisable(true);
            newNameLabel.setDisable(true);
        /*}


        //Ez azért így kell, mert az initalize-ban a fókuszt nem lehet áthelyezni még alapból
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (!itemCategory.getSelectionModel().isEmpty())
                {
                    newCategoryName.setText(itemCategory.getValue());
                    newCategoryName.requestFocus();
                }
            }
        });*/
    }

    @FXML
    public void changeCategory() {
        newNameLabel.setDisable(false);
        newCategoryName.setDisable(false);
        newCategoryName.setText(itemCategory.getValue());
        newCategoryName.requestFocus();
    }

    @FXML
    public void keyPressed(KeyEvent e) {
        if (newCategoryName.getText().trim().length() == 0 || newCategoryName.getText().trim().equals(itemCategory.getValue().trim()))
            editBtn.setDisable(true);
        else
        {
            editBtn.setDisable(false);
            if (e.getCharacter().equals("\r"))
                editBtn.fire();
        }
        if (e.getCharacter().getBytes()[0] == 27)
            cancelBtn.fire();
    }

    @FXML
    public void edit(ActionEvent event) {
        try
        {
            Category oldCat = App.wh.getCategory(itemCategory.getValue());
            Category newCat = new Category(newCategoryName.getText().trim(), oldCat.getProducts());
            if (App.wh.getCategory(newCat.getName()) != null)
                throw new IllegalArgumentException("Létező kategórianév!");
            App.wh.delCategory(oldCat);
            App.wh.addCategory(newCat);
            App.mainController.refresh();
            this.cancel(event);
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
