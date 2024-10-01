package laser.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import laser.Juego;
import laser.Nivel;

public class BarraNiveles {
    // Acceder a los botones por fx::id
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
    private Pane juego_pane;

    // Inicializar los botones
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

    // Método para mostrar un nivel en la interfaz
    private void mostrarNivel(String numero_nivel) {
        
            

            // Crear el tablero lógico
            Juego juego = new Juego();
            juego.cargarNivel(numero_nivel);
            
            // Crear la vista del juego y generar el tablero visual
            VistaDelJuego vistaDelJuego = new VistaDelJuego();
            vistaDelJuego.generarTableroVisual(juego);

            // Limpiar el panel antes de añadir el nuevo tablero
            juego_pane.getChildren().clear();
            juego_pane.getChildren().add(vistaDelJuego.getRootPane());

            // Actualizar la etiqueta del nivel
            label_nivel.setText("Nivel " + numero_nivel);
        
    }
}