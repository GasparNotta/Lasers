package laser.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import javafx.stage.Stage;
import laser.Nivel;
import laser.Tablero;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventana.fxml"));
            Parent root = loader.load();
            
            // Leer el nivel
            Nivel nivel1 = new Nivel("src/main/resources/levels/level1.dat");
            nivel1.leerArchivo();
            // Imprimir el contenido del nivel
            System.out.println(nivel1.getFilas());
            System.out.println(nivel1.getColumnas());
            System.out.println(nivel1.getConfiguracionBloques());
            System.out.println(nivel1.getConfiguracionElementos());

            // Crear el tablero lógico
            Tablero tablero = new Tablero(nivel1);

            // Crear la vista del juego y generar el tablero visual
            VistaDelJuego vistaDelJuego = new VistaDelJuego();
            vistaDelJuego.generarTableroVisual(tablero);

            // Obtener el Pane del FXML y añadir el tablero visual
            Pane panelJuego = (Pane) root.lookup("#juego_pane"); // Asegúrate de que tu FXML tiene un Pane con fx:id="panelJuego"
            panelJuego.getChildren().add(vistaDelJuego.getGridPane());



            // Configurar la escena y el escenario
            Scene scene = new Scene(root);

            stage.setTitle("Mi Aplicación JavaFX");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }

}