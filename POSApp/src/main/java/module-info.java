module com.example.posapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.posapp to javafx.fxml;
    exports com.example.posapp;
}