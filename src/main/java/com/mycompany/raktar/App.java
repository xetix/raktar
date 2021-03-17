package com.mycompany.raktar;

import com.mycompany.raktar.model.Warehouse;
import java.io.*;
import java.lang.ModuleLayer.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final String serFileName = "warehouse.ser";
    private static Scene scene;
    static Warehouse wh = null;
    static Stage s;
    static HomeController mainController = null;

    @Override
    public void start(final Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"));
        s = stage;
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        try (
            FileInputStream fileIn = new FileInputStream(serFileName);
            ObjectInputStream in = new ObjectInputStream(fileIn)
        ){
            App.wh = (Warehouse) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            App.wh = new Warehouse();
        }
        launch();
    }
    
    public static void serialization(Warehouse wh){
        try (
            FileOutputStream fileOut = new FileOutputStream(serFileName); 
            ObjectOutputStream out = new ObjectOutputStream(fileOut)
        ){
            out.writeObject(App.wh);
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }
}