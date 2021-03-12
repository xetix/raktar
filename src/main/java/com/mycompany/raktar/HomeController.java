/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Category;
import com.mycompany.raktar.model.Goods;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő
 */
public class HomeController implements Initializable {

    @FXML
    private MenuItem newCatBtn;
    
    @FXML
    private TreeView warehouseTreeView;
    
    public void openNewCatDialog(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("newCategory.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 200, 120);
            Stage addCatScene = new Stage();
            addCatScene.setScene(scene);
            addCatScene.initModality(Modality.WINDOW_MODAL);
            addCatScene.initOwner(App.s);
            addCatScene.show();
        } catch (IOException e) {
            System.out.print("Failed to create new Window: "+e);
        }
    }
    
    @FXML
    public void updateTreeView(){
        TreeItem rootItem = new TreeItem("Készlet");
        
        for(Map.Entry<String, Category> entry : App.wh.getCategories().entrySet()) {
            TreeItem catItem = new TreeItem(entry.getKey());
            
            for(Map.Entry<String, Goods> e : App.wh.getCategories().get(entry.getKey()).getProducts().entrySet()) {
                catItem.getChildren().add(new TreeItem(e.getValue().toString()));
            }            
            
            rootItem.getChildren().add(catItem);
        }
        
        warehouseTreeView.setRoot(rootItem);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.updateTreeView();
    }    
    
}