module application.numbergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.numbergui to javafx.fxml;
    exports application.numbergui;
}