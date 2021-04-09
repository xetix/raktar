/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Category;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class DelCategoryController implements Initializable {

    @FXML
    private Button delBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> itemCategory;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemCategory.setItems(FXCollections.observableArrayList(App.wh.getKeys()));
        itemCategory.getSelectionModel().selectFirst();
        if (itemCategory.getSelectionModel().isEmpty())
            delBtn.setDisable(true);

        //Ez azért így kell, mert az initalize-ban a fókuszt nem lehet áthelyezni még alapból
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (!itemCategory.getSelectionModel().isEmpty())
                    delBtn.requestFocus();
            }
        });
    }

    @FXML
    public void changeCategory() {
        delBtn.setDisable(false);
        delBtn.requestFocus();
    }

    @FXML
    public void keyPressed(KeyEvent e) {
        if (e.getCharacter().getBytes()[0]==27)
            cancelBtn.fire();
    }

    @FXML
    public void del(ActionEvent event) {
        try
        {
            Category cat = App.wh.getCategory(itemCategory.getValue());
            App.wh.delCategory(cat);
            App.mainController.refresh();
            this.cancel(event);
        }
        catch (Exception e)
        {
            App.mainController.alert(e.getMessage());
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

}
