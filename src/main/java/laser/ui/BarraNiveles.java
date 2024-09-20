package laser.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BarraNiveles {
    @FXML
    private Button boton_nivel1;

    @FXML
    private Button boton_nivel2;

    @FXML
    private Button boton_nivel3;

    @FXML
    private Button boton_nivel4;

    @FXML
    private Button boton_nivel5;

    @FXML
    private Button boton_nivel6;

    @FXML
    private Button boton_nivel7;

    @FXML
    private Button boton_nivel8;

    @FXML
    private Label label_nivel;

    @FXML
    public void initialize() {
        boton_nivel1.setOnAction(e -> mostrarNivel("1"));
        boton_nivel2.setOnAction(e -> mostrarNivel("2"));
        boton_nivel3.setOnAction(e -> mostrarNivel("3"));
        boton_nivel4.setOnAction(e -> mostrarNivel("4"));
        boton_nivel5.setOnAction(e -> mostrarNivel("5"));
        boton_nivel6.setOnAction(e -> mostrarNivel("6"));
        boton_nivel7.setOnAction(e -> mostrarNivel("7"));
        boton_nivel8.setOnAction(e -> mostrarNivel("8"));
    }

    private void mostrarNivel(String nivel) {
        label_nivel.setText("Este es el nivel " + nivel);
    }
}