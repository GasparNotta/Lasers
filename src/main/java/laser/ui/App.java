package laser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.io.IOException;

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
            
            // Configurar la escena y el escenario
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mi Aplicación JavaFX");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo adicional del error
        }

    }

    public static void main(String[] args) {
        launch();
    }

}