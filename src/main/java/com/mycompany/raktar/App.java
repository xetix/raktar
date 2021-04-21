package com.mycompany.raktar;

import com.mycompany.raktar.currencyXML.Arfolyamok;
import com.mycompany.raktar.model.Warehouse;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
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
    private static final String serFileName = "warehouse.ser";
    private static Scene scene;
    static Warehouse wh = null;
    static Arfolyamok arfolyamok;
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
        currencyUpdater();
        try (
                FileInputStream fileIn = new FileInputStream(serFileName);
                ObjectInputStream in = new ObjectInputStream(fileIn)
        )
        {
            App.wh = (Warehouse) in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            App.wh = new Warehouse();
        }
        launch();
    }

    public static void serialization(Warehouse wh) {
        try (
                FileOutputStream fileOut = new FileOutputStream(serFileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)
        )
        {
            out.writeObject(App.wh);
        }
        catch (IOException e)
        {
            System.err.print(e.getMessage());
        }
    }

    public static void currencyUpdater() {
        InputStream in = null;
        try
        {
            //XML file letöltése a napiarfolyam.hu-tól
            in = new URL("http://api.napiarfolyam.hu/?bank=mnb").openStream();
            Files.copy(in, Paths.get("arfolyamok.xml"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e)
        {
        }

        try
        {
            //XML adatok betöltése
            File file = new File("arfolyamok.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Arfolyamok.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            arfolyamok = (Arfolyamok) jaxbUnmarshaller.unmarshal(file);
        }
        catch (Exception e)
        {
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
    }
}