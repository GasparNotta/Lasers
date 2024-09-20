module laser {
    requires javafx.controls;
    requires javafx.fxml;

    opens laser.ui to javafx.fxml;
    
    exports laser;
}
