module laser.ui {

    requires javafx.controls;

    requires javafx.fxml;

    requires transitive javafx.graphics;


    exports controller;
    exports view;
    exports model;

    opens controller to javafx.fxml;
    opens view to javafx.fxml;
}
