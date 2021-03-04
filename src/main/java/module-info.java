module com.mycompany.raktar {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.raktar to javafx.fxml;
    exports com.mycompany.raktar;
}
