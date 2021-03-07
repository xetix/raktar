package com.mycompany.raktar;

import com.mycompany.raktar.model.Category;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class newCategoryController {
    @FXML
    private TextField newCategoryName;
    
    @FXML
    private void toHomeScene() throws IOException {
        this.newCategoryName.setText("");
        App.setRoot("home");
    }
    
    @FXML
    private void addNewCategory() throws IOException{
        Category cat = new Category(this.newCategoryName.getText());
        App.wh.addCategory(cat);
        this.newCategoryName.setText("");
        App.setRoot("home");
    }    
}
