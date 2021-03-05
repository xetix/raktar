package com.mycompany.raktar;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrimaryController {
    @FXML 
    private Label outText;
    
    @FXML
    private void switchToSecondary() throws IOException {
        //App.setRoot("secondary");
        this.outText.setText("Proba gomb nyomás");
    }
    
}
