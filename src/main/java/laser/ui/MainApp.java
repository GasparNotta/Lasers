package laser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crea un contenedor vacío
        StackPane root = new StackPane();
        
        // Crea una escena con el contenedor
        Scene scene = new Scene(root, 800, 600); // Tamaño de la ventana: 800x600 píxeles

        // Configura el escenario
        primaryStage.setTitle("Ventana Vacía");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
