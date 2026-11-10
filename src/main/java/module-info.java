module org.example.inventarioappg1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;          // JDBC
    requires mysql.connector.j;      // driver de MySQL
    requires static lombok;

    opens cesur.dam.inventarioappg1 to javafx.fxml;
    exports cesur.dam.inventarioappg1;
    exports cesur.dam.inventarioappg1.models;
    opens cesur.dam.inventarioappg1.models to javafx.fxml;
    exports cesur.dam.inventarioappg1.controllers;
    opens cesur.dam.inventarioappg1.controllers to javafx.fxml;
}
