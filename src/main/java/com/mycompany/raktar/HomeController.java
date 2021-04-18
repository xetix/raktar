/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.model.Category;
import com.mycompany.raktar.model.Goods;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő, Baranyai Richárd, Bedő Ákos
 */
public class HomeController implements Initializable {
    @FXML
    private MenuItem editCatMenuItem;
    @FXML
    private MenuItem delCatMenuItem;
    @FXML
    private Menu goodsMenu;
    @FXML
    private MenuItem editGoodsMenuItem;
    @FXML
    private MenuItem delGoodsMenuItem;

    @FXML
    private TreeTableView<Goods> out;

    @FXML
    private TreeTableColumn<Goods,String> name, vendor, description, stock, unit, price, currency;

    public void openNewCatDialog(){
        this.openNewDialog("newCategory", 95,"Új kategória hozzáadása");
    }
    
    public void openEditCatDialog(){
        this.openNewDialog("editCategory", 170, "Kategória átnevezése");
    }
    
    public void openDelCatDialog(){
        this.openNewDialog("delCategory", 160,"Kategória törlése");
    }
    
    public void openNewGoodsDialog(){
        this.openNewDialog("newGoods", 410,"Új termék hozzáadása");
    }

    public void openEditGoodsDialog(){
        this.openNewDialog("editGoods", 595,"Termék szerkesztése");
    }

    public void openDelGoodsDialog(){
        this.openNewDialog("delGoods", 170,"Termék törlése");
    }
    
    private void openNewDialog(String name, int height, String windowTitle){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(name+".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, height);
            Stage popupScene = new Stage();
            popupScene.setScene(scene);
            popupScene.initModality(Modality.WINDOW_MODAL);
            popupScene.initOwner(App.s);
            popupScene.setTitle(windowTitle);
            popupScene.setResizable(false);
            popupScene.getIcons().add(new Image("file:warehouse.png"));
            popupScene.show();
        } catch (Exception e) {
            this.alert("Nem sikerült létrehozni a dialógust","Hiba leírása:\n" + e.toString());
        }
    }
    
    private void updateTreeTableView(){
        TreeItem<Goods> root = new TreeItem<>(new Goods("Készlet"));
        out.setRoot(root);
        boolean isThereAnyProductsAdded=false;

        for (Category category: App.wh.getCategories().values()) {
            TreeItem<Goods> cat = new TreeItem<>(new Goods(category.getName()));
            for (Goods item: category.getProducts().values()) {
                cat.getChildren().add(new TreeItem<>(item));
                isThereAnyProductsAdded=true;
            }
            cat.setExpanded(true);
            root.getChildren().add(cat);
        }
        /*
        App.wh.getCategories().entrySet().stream().map(entry -> {
            TreeItem<Goods> cat = new TreeItem<>(new Goods(entry.getKey()));
            entry.getValue().getProducts().forEach((key, value) -> cat.getChildren().add(new TreeItem<>(value)));
            return cat;
        }).forEach(cat -> root.getChildren().add(cat));
        */

        if (App.wh.getCategories().size()==0)
        {
            editCatMenuItem.setDisable(true);
            delCatMenuItem.setDisable(true);
            goodsMenu.setDisable(true);
            editGoodsMenuItem.setDisable(true);
            delGoodsMenuItem.setDisable(true);
        }
        else
        {
            editCatMenuItem.setDisable(false);
            delCatMenuItem.setDisable(false);
            goodsMenu.setDisable(false);
            if(isThereAnyProductsAdded)
            {
                editGoodsMenuItem.setDisable(false);
                delGoodsMenuItem.setDisable(false);
            }
            else
            {
                editGoodsMenuItem.setDisable(true);
                delGoodsMenuItem.setDisable(true);
            }
        }
        out.getSortOrder().add(name);
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
                (cellData.getValue().getValue().getStock().getUnit().equals("")) ?
                        "" : cellData.getValue().getValue().getStock().getStockNumber()+"")
        );
        unit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getStock().getUnit()));
        price.setCellValueFactory(cellData -> new SimpleStringProperty(
                (cellData.getValue().getValue().getPrice().getCurrency().equals("")) ?
                        "" : cellData.getValue().getValue().getPrice().getPriceNumber()+"")
        );
        currency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getPrice().getCurrency()));

        this.updateTreeTableView();
    } 
    
    void alert(String msg, String details){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba");
        alert.setHeaderText(msg);
        alert.initStyle(StageStyle.UTILITY);
        alert.setContentText(details);
        alert.showAndWait();
    }
    
}
