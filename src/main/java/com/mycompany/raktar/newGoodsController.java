package com.mycompany.raktar;

import java.io.IOException;
import javafx.fxml.FXML;

public class newGoodsController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("home");
    }
}