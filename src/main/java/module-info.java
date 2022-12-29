module com.example.schedular {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.schedular to javafx.fxml;
    exports com.example.schedular;
}