package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class VistaJuego extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventana.fxml"));
            Parent root = loader.load();
            // Configurar la escena y el escenario
            Scene scene = new Scene(root);
            //Titulo de la ventana
            stage.setTitle("Lasers Gaspar Notta");
            //Mostrar la ventana
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