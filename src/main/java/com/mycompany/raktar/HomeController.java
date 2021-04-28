/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raktar;

import com.mycompany.raktar.currencyXML.Arfolyamok;
import com.mycompany.raktar.currencyXML.Item;
import com.mycompany.raktar.model.Category;
import com.mycompany.raktar.model.Goods;
import com.mycompany.raktar.model.Price;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArraySet;

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
    private RadioMenuItem hufRadioMenuItem;
    @FXML
    private RadioMenuItem usdRadioMenuItem;
    @FXML
    private RadioMenuItem eurRadioMenuItem;
    @FXML
    private RadioMenuItem gbpRadioMenuItem;
    @FXML
    private MenuItem currencyUpdaterBtn;
    @FXML
    private Label currencyInfo;

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
                item.setDisplayedprice(currencyAtvalto(item));
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
                (cellData.getValue().getValue().getDisplayedPrice().getCurrency().equals("")) ?
                        "" : cellData.getValue().getValue().getDisplayedPrice().getPriceNumber()+"")
        );
        currency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getDisplayedPrice().getCurrency()));

        currencyUpdater();
        switch (App.preferredCurrency)
        {
            case "HUF":
            {
                hufRadioMenuItem.setSelected(true);
            }
            break;
            case "USD":
            {
                usdRadioMenuItem.setSelected(true);
            }
            break;
            case "EUR":
            {
                eurRadioMenuItem.setSelected(true);
            }
            break;
            case "GBP":
            {
                gbpRadioMenuItem.setSelected(true);
            }
            break;
        }
    } 
    
    void alert(String msg, String details){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba");
        alert.setHeaderText(msg);
        alert.initStyle(StageStyle.UTILITY);
        alert.setContentText(details);
        alert.showAndWait();
    }

    public void currencyUpdater() {
        InputStream in = null;
        try
        {
            //XML file letöltése a napiarfolyam.hu-tól
            in = new URL("http://api.napiarfolyam.hu/?bank=mnb").openStream();
            Files.copy(in, Paths.get("arfolyamok.xml"), StandardCopyOption.REPLACE_EXISTING);
            currencyInfo.setText("Az árfolyam-adatok naprakészek");
        }
        catch (Exception e)
        {
            currencyInfo.setText("Az árfolyam-adatok frissítése nem sikerült. A megjelenített árak a legutóbb ismert értékek.");
        }

        try
        {
            //XML adatok betöltése
            File file = new File("arfolyamok.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Arfolyamok.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            App.arfolyamok = (Arfolyamok) jaxbUnmarshaller.unmarshal(file);
            for (Item item : App.arfolyamok.getDeviza().getItems())
            {
                if (item.getPenznem().equals("GBP"))
                    App.HUFtoGBP=item.getKozep();
                else
                {
                    if (item.getPenznem().equals("USD"))
                        App.HUFtoUSD=item.getKozep();
                    else
                    {
                        if (item.getPenznem().equals("EUR"))
                            App.HUFtoEUR = item.getKozep();
                    }
                }
            }
            hufRadioMenuItem.setDisable(false);
            usdRadioMenuItem.setDisable(false);
            eurRadioMenuItem.setDisable(false);
            gbpRadioMenuItem.setDisable(false);
        }
        catch (Exception e)
        {
            currencyInfo.setText("Árfolyam-adatok betöltése nem sikerült. A megjelenített árak a legutóbb ismert értékek.");
            hufRadioMenuItem.setDisable(true);
            usdRadioMenuItem.setDisable(true);
            eurRadioMenuItem.setDisable(true);
            gbpRadioMenuItem.setDisable(true);
        }

        // Hosszabb megoldás csak a letöltésre:
        /*try (BufferedInputStream in = new BufferedInputStream(new URL("http://api.napiarfolyam.hu/?bank=mnb").openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("arfolyamok.xml")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (Exception e) {
            // handle exception
        }*/

        this.updateTreeTableView();
    }


    public Price currencyAtvalto (Goods eredeti) {
        if (eredeti.getOriginalPrice().getCurrency().equals(App.preferredCurrency))
            return eredeti.getOriginalPrice();
        BigDecimal x = new BigDecimal(eredeti.getOriginalPrice().getPriceNumber().replace(" ", ""));
        // displayedCurrency != App.preferredCurrency &&
        switch (App.preferredCurrency)
        {
            case "HUF":
            {
                switch (eredeti.getOriginalPrice().getCurrency())
                {
                    case "USD":
                    {
                        x=x.multiply(App.HUFtoUSD);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                    case "EUR":
                    {
                        x=x.multiply(App.HUFtoEUR);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                    case "GBP":
                    {
                        x=x.multiply(App.HUFtoGBP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                }
            }
            break;
            case "USD":
            {
                switch (eredeti.getOriginalPrice().getCurrency())
                {
                    case "HUF":
                    {
                        x=x.divide(App.HUFtoUSD,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                    case "EUR":
                    {
                        x=x.multiply(App.HUFtoEUR);
                        x=x.divide(App.HUFtoUSD,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                    case "GBP":
                    {
                        x=x.multiply(App.HUFtoGBP);
                        x=x.divide(App.HUFtoUSD,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                }
            }
            break;
            case "EUR":
            {
                switch (eredeti.getOriginalPrice().getCurrency())
                {
                    case "HUF":
                    {
                        x=x.divide(App.HUFtoEUR,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                    case "USD":
                    {
                        x=x.multiply(App.HUFtoUSD);
                        x=x.divide(App.HUFtoEUR,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                    case "GBP":
                    {
                        x=x.multiply(App.HUFtoGBP);
                        x=x.divide(App.HUFtoEUR,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                }
            }
            break;
            case "GBP":
            {
                switch (eredeti.getOriginalPrice().getCurrency())
                {
                    case "HUF":
                    {
                        x=x.divide(App.HUFtoGBP,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                    case "USD":
                    {
                        x=x.multiply(App.HUFtoUSD);
                        x=x.divide(App.HUFtoGBP,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                    case "EUR":
                    {
                        x=x.multiply(App.HUFtoEUR);
                        x=x.divide(App.HUFtoGBP,3, RoundingMode.HALF_UP);
                        return new Price(x.toPlainString(), App.preferredCurrency);
                    }
                }
            }
        }
        return new Price("1.0", App.preferredCurrency);
    }

    public void currencyHUFSelected(ActionEvent e)
    {
        App.preferredCurrency="HUF";
        refresh();

    }
    public void currencyUSDSelected(ActionEvent e)
    {
        App.preferredCurrency="USD";
        refresh();
    }
    public void currencyEURSelected(ActionEvent e)
    {
        App.preferredCurrency="EUR";
        refresh();
    }
    public void currencyGBPSelected(ActionEvent e)
    {
        App.preferredCurrency="GBP";
        refresh();
    }

}
