module com.mycompany.raktar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.raktar to javafx.fxml;
    exports com.mycompany.raktar;
}
