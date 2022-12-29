module com.schedular.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.scheduler.app to javafx.fxml;
    exports com.scheduler.app;
}