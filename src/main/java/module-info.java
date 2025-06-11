module ucr.lab.laboratory12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucr.lab.laboratory12 to javafx.fxml;
    exports ucr.lab.laboratory12;
    exports controller;
    opens controller to javafx.fxml;
}