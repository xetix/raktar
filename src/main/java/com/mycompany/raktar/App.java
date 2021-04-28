package com.mycompany.raktar;

import com.mycompany.raktar.currencyXML.Arfolyamok;
import com.mycompany.raktar.model.Warehouse;
import java.io.*;
import java.math.BigDecimal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final String serWHFileName = "warehouse.ser";
    private static final String serCurrencyFileName = "preferred_currency.ser";
    private static Scene scene;
    static Warehouse wh = null;
    static Arfolyamok arfolyamok;
    static String preferredCurrency;
    static BigDecimal HUFtoUSD=BigDecimal.ONE;
    static BigDecimal HUFtoEUR=BigDecimal.ONE;
    static BigDecimal HUFtoGBP=BigDecimal.ONE;
    static Stage s;
    static HomeController mainController = null;

    @Override
    public void start(final Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"));
        s = stage;
        stage.setScene(scene);
        stage.setTitle("Raktárkezelő - SFM Projekt");
        stage.getIcons().add(new Image("file:warehouse.png"));
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
                FileInputStream whFileIn = new FileInputStream(serWHFileName);
                ObjectInputStream whIn = new ObjectInputStream(whFileIn)
        )
        {
            App.wh = (Warehouse) whIn.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            App.wh = new Warehouse();
        }

        try (
                FileInputStream currencyFileIn = new FileInputStream(serCurrencyFileName);
                ObjectInputStream currencyIn = new ObjectInputStream(currencyFileIn)
        )
        {
            App.preferredCurrency = currencyIn.readObject().toString();
        }
        catch (IOException | ClassNotFoundException e)
        {
            App.preferredCurrency = "HUF";
        }
        launch();
    }

    public static void serialization(Warehouse wh) {
        try (
                FileOutputStream fileOut = new FileOutputStream(serWHFileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)
        )
        {
            out.writeObject(App.wh);
        }
        catch (IOException e)
        {
            System.err.print(e.getMessage());
        }
        try (
                FileOutputStream fileOut = new FileOutputStream(serCurrencyFileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)
        )
        {
            out.writeObject(App.preferredCurrency);
        }
        catch (IOException e)
        {
            System.err.print(e.getMessage());
        }
    }

}