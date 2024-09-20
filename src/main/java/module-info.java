module laser.ui {

    requires javafx.controls;

    requires javafx.fxml;

    requires transitive javafx.graphics;



    opens laser.ui to javafx.fxml;

    exports laser.ui;

}
