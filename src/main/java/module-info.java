module example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jfreechart;
    requires jcommon;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.View;
    opens com.example.demo.View to javafx.fxml;
    exports com.example.demo.Controller;
    opens com.example.demo.Controller to javafx.fxml;

}