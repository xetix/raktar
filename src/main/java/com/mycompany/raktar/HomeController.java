/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        this.openNewDialog("newCategory", 250, 120);
    }
    
    public void openEditCatDialog(){
        this.openNewDialog("editCategory", 250, 160);
    }
    
    public void openDelCatDialog(){
        this.openNewDialog("delCategory", 250, 120);
    }
    
    public void openNewGoodsDialog(){
        this.openNewDialog("newGoods", 250, 390);
    }
    
    public void openDelGoodsDialog(){
        this.openNewDialog("delGoods", 250, 170);
    }
    
    public void openEditGoodsDialog(){
        this.openNewDialog("editGoods", 250, 528);
    }
    
    private void openNewDialog(String name, int width, int height){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(name+".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            Stage addCatScene = new Stage();
            addCatScene.setScene(scene);
            addCatScene.initModality(Modality.WINDOW_MODAL);
            addCatScene.initOwner(App.s);
            addCatScene.show();
        } catch (Exception e) {
            this.alert("Hiba", "Nem sikerült létrhozni a dialógust.\n\nHiba leírása:\n" + e.toString());
        }
    }
    
    @FXML
    public void updateTreeView(){
        TreeItem rootItem = new TreeItem("Készlet");
        
        App.wh.getCategories().entrySet().stream().map(entry -> {
            TreeItem catItem = new TreeItem(entry.getKey());
            App.wh.getCategories().get(entry.getKey()).getProducts().entrySet().forEach(e -> {
                catItem.getChildren().add(new TreeItem(e.getValue().toString()));
            });
            return catItem;
        }).forEachOrdered(catItem -> {
            rootItem.getChildren().add(catItem);
        });
        
        warehouseTreeView.setRoot(rootItem);
    }
    
    public void refresh(){
        App.serialization(App.wh);
        this.updateTreeView();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.mainController = this;
        this.updateTreeView();
    } 
    
    void alert(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }
    
}
