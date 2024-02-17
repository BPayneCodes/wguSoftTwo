module com.example.wgusofttwo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;

    opens Controller to javafx.fxml;
    exports Controller;
    opens DBAccess to javafx.fxml;
    exports DBAccess;
    opens DBUtility to javafx.fxml;
    exports DBUtility;
    opens Model to javafx.fxml;
    exports Model;
    opens Reports to javafx.fxml;
    exports Reports;
    opens Main to javafx.fxml;
    exports Main;
}