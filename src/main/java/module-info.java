module com.schedular.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.scheduler.app to javafx.fxml;
    opens com.scheduler.pojo to javafx.fxml;

    exports com.scheduler.pojo;
    exports com.scheduler.app;
}