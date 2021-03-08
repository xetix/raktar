package com.mycompany.raktar;

import com.mycompany.raktar.model.Price.Currency;
import com.mycompany.raktar.model.Stock.UnitOfMeasure;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class newGoodsController implements Initializable  {
    @FXML
    private ComboBox catCB;
    @FXML
    private ComboBox stockCB;
    @FXML
    private ComboBox priceCB;

    @FXML
    private void toHomeScene() throws IOException {
        App.setRoot("home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.wh.getCategories().forEach(cat -> {
            catCB.getItems().add(cat.getName());
        });
        stockCB.getItems().addAll(Arrays.asList(UnitOfMeasure.values()));
        priceCB.getItems().addAll(Arrays.asList(Currency.values()));
    }
}