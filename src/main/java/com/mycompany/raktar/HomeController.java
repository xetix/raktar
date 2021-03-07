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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Kovács Gergő
 */
public class HomeController implements Initializable {
    @FXML
    private Label textOut;
    
    @FXML
    private void toNewCategoryScene() throws IOException{
        App.setRoot("newCategory");
    }
    
    @FXML
    private void toNewGoodsScene() throws IOException{
        App.setRoot("newGoods");
    }
    
    @FXML
    private void reflashTest(){
        textOut.setText(App.wh.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reflashTest();
    }
}
