/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Goods;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő
 */
public class HomeController implements Initializable {

    @FXML
    private TreeTableView<Goods> out;

    @FXML
    private TreeTableColumn<Goods,String> name, vendor, description, stock, unitOfMeasure, price, currency;

    public void openNewCatDialog(){
        this.openNewDialog("newCategory", 120);
    }
    
    public void openEditCatDialog(){
        this.openNewDialog("editCategory", 160);
    }
    
    public void openDelCatDialog(){
        this.openNewDialog("delCategory", 120);
    }
    
    public void openNewGoodsDialog(){
        this.openNewDialog("newGoods", 390);
    }
    
    public void openDelGoodsDialog(){
        this.openNewDialog("delGoods", 170);
    }
    
    public void openEditGoodsDialog(){
        this.openNewDialog("editGoods", 528);
    }
    
    private void openNewDialog(String name, int height){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(name+".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 250, height);
            Stage addCatScene = new Stage();
            addCatScene.setScene(scene);
            addCatScene.initModality(Modality.WINDOW_MODAL);
            addCatScene.initOwner(App.s);
            addCatScene.show();
        } catch (Exception e) {
            this.alert("Nem sikerült létrhozni a dialógust.\n\nHiba leírása:\n" + e.toString());
        }
    }
    
    private void updateTreeTableView(){
        TreeItem<Goods> root = new TreeItem<>(new Goods("Készlet"));

        App.wh.getCategories().entrySet().stream().map(entry -> {
            TreeItem<Goods> cat = new TreeItem<>(new Goods(entry.getKey()));
            entry.getValue().getProducts().forEach((key, value) -> cat.getChildren().add(new TreeItem<>(value)));
            return cat;
        }).forEach(cat -> root.getChildren().add(cat));

        out.setRoot(root);
    }
    
    public void refresh(){
        App.serialization(App.wh);
        this.updateTreeTableView();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.mainController = this;

        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getName()));
        vendor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getVendor()));
        description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getDescription()));
        stock.setCellValueFactory(cellData -> new SimpleStringProperty(
                (cellData.getValue().getValue().getStock().getUnitOfMeasure().equals("")) ?
                        "" : cellData.getValue().getValue().getStock().getStock()+"")
        );
        unitOfMeasure.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getStock().getUnitOfMeasure()));
        price.setCellValueFactory(cellData -> new SimpleStringProperty(
                (cellData.getValue().getValue().getPrice().getCurrency().equals("")) ?
                        "" : cellData.getValue().getValue().getPrice().getPrice()+"")
        );
        currency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getPrice().getCurrency()));

        this.updateTreeTableView();
    } 
    
    void alert(String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
}
