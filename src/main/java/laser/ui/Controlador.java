package laser.ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import laser.ui.controles.ControlDelJuego;

public class Controlador {
    ControlDelJuego controlDelJuego = new ControlDelJuego();

    // Botones de los niveles
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

    // Etiqueta del nivel
    @FXML
    private Label label_nivel;

    // Panel del juego
    @FXML
    private Pane juego_pane;

    @FXML
    public void initialize() {
        boton_nivel1.setOnAction(e -> {
            String numero_nivel = "1";
            controlDelJuego.iniciarJuego(numero_nivel ,juego_pane);
            label_nivel.setText("Nivel " + numero_nivel);
        });

        boton_nivel2.setOnAction(e -> {
            String numero_nivel = "2";
            controlDelJuego.iniciarJuego(numero_nivel ,juego_pane);
            label_nivel.setText("Nivel " + numero_nivel);
        });

        boton_nivel3.setOnAction(e -> {
            String numero_nivel = "3";
            controlDelJuego.iniciarJuego(numero_nivel ,juego_pane);
            label_nivel.setText("Nivel " + numero_nivel);
        });
        boton_nivel4.setOnAction(e -> {
            String numero_nivel = "4";
            controlDelJuego.iniciarJuego(numero_nivel ,juego_pane);
            label_nivel.setText("Nivel " + numero_nivel);
        });
        boton_nivel5.setOnAction(e -> {
            String numero_nivel = "5";
            controlDelJuego.iniciarJuego(numero_nivel ,juego_pane);
            label_nivel.setText("Nivel " + numero_nivel);
        });
        boton_nivel6.setOnAction(e -> {
            String numero_nivel = "6";
            controlDelJuego.iniciarJuego(numero_nivel ,juego_pane);
            label_nivel.setText("Nivel " + numero_nivel);
        });
    }
}
