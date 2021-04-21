module com.mycompany.raktar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires jakarta.xml.bind;
    opens com.mycompany.raktar to jakarta.xml.bind, javafx.fxml;
    opens com.mycompany.raktar.currencyXML to jakarta.xml.bind;

    //opens com.mycompany.raktar to javafx.fxml;
    exports com.mycompany.raktar;
}
